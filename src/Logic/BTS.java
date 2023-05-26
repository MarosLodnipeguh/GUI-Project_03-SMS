package Logic;

import Handlers.BTSListener;
import Handlers.UpdateStationPanelUIEvent;
import SMS.InvalidRecipientException;
import SMS.Message;
import SMS.PhoneBookLogic;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BTS implements Runnable {
    private int id;
    private final ConcurrentLinkedQueue<String> gatheredMessages;
    private AtomicInteger waitingMessages;
    private volatile boolean isFull;
    private AtomicInteger processedMessages;
    private BSC connectedBSC;
    private VRD connectedVRD;
    private int layerNumber;
    public BTSLayer layer;
    private BTSListener listener;
    private volatile boolean running = true;
    private final Object lock;


    public BTS(BTSLayer layer) {
        this.id = PhoneBookLogic.StationsCounter;
        PhoneBookLogic.StationsCounter++;

        gatheredMessages = new ConcurrentLinkedQueue<>();
        waitingMessages = new AtomicInteger(0);
        isFull = false;
        processedMessages = new AtomicInteger(0);

        this.layer = layer;
        layerNumber = layer.getLayerNumber();

        connectedBSC = null;
        connectedVRD = null;

        lock = new Object();
    }

    @Override
    public void run() {
        System.out.println("BTS: " + id + " started");

        while (running) {
            synchronized (lock) {

                if (!gatheredMessages.isEmpty()) {
                    listener.updateBTSPanel(new UpdateStationPanelUIEvent(this, this.id, this.getProcessedMessages(), this.getWaitingMessages()));
                    processNextMessage();
                    listener.updateBTSPanel(new UpdateStationPanelUIEvent(this, this.id, this.getProcessedMessages(), this.getWaitingMessages()));
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

    public void addMessage(String message) {
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

    public void connectToVRD(VRD vrd) {
        synchronized (lock) {
            this.connectedVRD = vrd;
        }
    }

    public void processNextMessage() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String m = null;

        synchronized (gatheredMessages) {
            if (!gatheredMessages.isEmpty()) {
                m = gatheredMessages.poll();
                waitingMessages.decrementAndGet();
                processedMessages.incrementAndGet();
                isFull = false;
            } else {
                return; // No messages to process
            }
        }

        if (layerNumber == 0) {
            synchronized (BSCManager.class) {
                connectToBSC(BSCManager.getLayerXbsc(0));
            }
        } else {
            synchronized (MainLogic.class) {
                String recipent = decodeRecipientFromPDU(m);
                int recipentInt = Integer.parseInt(recipent.substring(0, 1));
                connectToVRD(MainLogic.getVRD(recipentInt));
            }
        }

        synchronized (lock) {
            try {
                if (connectedBSC != null) {
                    connectedBSC.addMessage(m);
                    connectedBSC = null;
                } else if (connectedVRD != null) {
                    connectedVRD.addMessage(m);
                    connectedVRD = null;
                } else {
                    throw new InvalidRecipientException();
                }
            } catch (InvalidRecipientException e) {
                e.printStackTrace();
            }
        }
    }

    public void setListener(BTSListener listener) {
        this.listener = listener;
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

    public boolean getIsFull () {
        return isFull;
    }

    public void stopBTS () {
        running = false;
    }

    // ==================================================== PDU DECODING =============================================================

    public static String decodeRecipientFromPDU(String pdu) {
        // PDU length should be even
        if (pdu.length() % 2 != 0) {
            throw new IllegalArgumentException("Invalid PDU length");
        }

        // Skip the first octet (PDU type)
        int startIndex = 2;

        // Check if the second nibble indicates the length of the recipient number
        int secondNibble = Integer.parseInt(pdu.substring(1, 2), 16);
        if (secondNibble % 2 == 1) {
            startIndex += 1;
        }

        // Extract the recipient number from the PDU
        StringBuilder recipientBuilder = new StringBuilder();
        for (int i = startIndex; i < pdu.length(); i += 2) {
            String semiOctet = pdu.substring(i, i + 2);
            recipientBuilder.append(Integer.parseInt(semiOctet, 16));
        }

        return recipientBuilder.toString();
    }
}
