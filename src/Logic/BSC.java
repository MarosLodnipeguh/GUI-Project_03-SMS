package Logic;

import Handlers.BSCListener;
import Handlers.UpdateStationPanelUIEvent;
import SMS.Message;
import SMS.PhoneBookLogic;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BSC implements Runnable {

    private int id;
    private ConcurrentLinkedQueue<Message> gatheredMessages;
    private AtomicInteger waitingMessages;
    private volatile boolean isFull;
    private AtomicInteger processedMessages;
    private BSC connectedBSC;
    private BTS connectedBTS;
    private final int layerNumber;
    public BSCLayer layer;
    private BSCListener listener;
    private volatile boolean running = true;
    private final Object lock;

    public BSC(BSCLayer layer) {
        this.id = PhoneBookLogic.StationsCounter;
        PhoneBookLogic.StationsCounter++;

        gatheredMessages = new ConcurrentLinkedQueue<>();
        waitingMessages = new AtomicInteger(0);
        isFull = false;
        processedMessages = new AtomicInteger(0);

        this.layer = layer;
        layerNumber = layer.getLayerNumber();

        connectedBSC = null;
        connectedBTS = null;

        lock = new Object();
    }

    @Override
    public void run() {
        System.out.println("BSC: " + id + " started");

        while (running) {

            synchronized (lock) {
                if (!gatheredMessages.isEmpty()) {
                    listener.updateBSCPanel(new UpdateStationPanelUIEvent(this, this.id, this.getProcessedMessages(), this.getWaitingMessages()));
                    processNextMessage();
                    listener.updateBSCPanel(new UpdateStationPanelUIEvent(this, this.id, this.getProcessedMessages(), this.getWaitingMessages()));
                } else {
                    try {
                        lock.wait(100); // czekaj przy braku wiadomości
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        System.out.println("BSC: " + id + " stopped");
    }

    public void addMessage(Message message) {
        synchronized (lock) {
            gatheredMessages.add(message);
            waitingMessages.incrementAndGet();

            if (gatheredMessages.size() > 5) {
                isFull = true;
            }
        }
    }

    public void connectToBSC(BSC bsc) {
        synchronized (lock) {
            this.connectedBSC = bsc;
        }
    }

    public void connectToBTS(BTS bts) {
        synchronized (lock) {
            this.connectedBTS = bts;
        }
    }

    public synchronized void processNextMessage() {
        try {
            Thread.sleep((long) (Math.random() * 10000 + 5000)); // wait for random time (5-15s)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Message m;

        synchronized (lock) {
            if (!gatheredMessages.isEmpty()) {
                m = gatheredMessages.poll();
                waitingMessages.decrementAndGet();
                processedMessages.incrementAndGet();
                isFull = false;
            } else {
                return; // No messages to process
            }
        }

        if (layerNumber != BSCManager.getLastLayerNumber()) { // not last layer - pass to next BSC
            synchronized (BSCManager.class) {
                connectToBSC(BSCManager.getLayerXbsc(layerNumber + 1));
            }
        } else { // last layer - pass to BTS
            synchronized (BTSManager.class) {
                connectToBTS(BTSManager.getLayerXBTS(1));
            }
        }

        synchronized (lock) {
            if (connectedBSC != null) {
                connectedBSC.addMessage(m);
                connectedBSC = null;
            } else if (connectedBTS != null) {
                connectedBTS.addMessage(m);
                connectedBTS = null;
            } else {
                System.out.println("BSChandlers " + id + " is not connected to any BTS or BSC");
            }
        }
    }

    public void InstantlyPassAllMessages() {
        synchronized (lock) {
            isFull = true;
            for (Message m : gatheredMessages) {
                if (layerNumber != BSCManager.getLastLayerNumber()) { // not last layer - pass to next BSC
                    connectToBSC(BSCManager.getLayerXbsc(layerNumber + 1));
                } else { // last layer - pass to BTS
                    connectToBTS(BTSManager.getLayerXBTS(1));
                }

                if (connectedBSC != null) {
                    connectedBSC.addMessage(m);
                    connectedBSC = null;
                } else if (connectedBTS != null) {
                    connectedBTS.addMessage(m);
                    connectedBTS = null;
                } else {
                    System.out.println("BSChandlers " + id + " is not connected to any BTS or BSC");
                }

                waitingMessages.decrementAndGet();
                processedMessages.incrementAndGet();
            }
            gatheredMessages.clear();
        }
    }

    public void stopBSC() {
        running = false;
        InstantlyPassAllMessages();
    }

    public void setListener(BSCListener listener) {
        synchronized (lock) {
            this.listener = listener;
        }
    }

    public int getId() {
        return id;
    }

    public int getProcessedMessages() {
        return processedMessages.get();
    }

    public int getWaitingMessages() {
        return waitingMessages.get();
    }

    public boolean getisFull () {
        return isFull;
    }
}
