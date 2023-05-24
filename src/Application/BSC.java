package Application;

// Controller Station

import SMS.Message;
import SMS.PhoneBookLogic;
import UI.BSCPanel;

import java.util.ArrayList;
import java.util.List;

public class BSC implements Runnable {

    private int id;
    private List <Message> gatheredMessages;
    private int WaitingMessages;
    boolean isFull;
    private int ProcessedMessages;
    private BSC connectedBSC;
    private BTS connectedBTS;


    private int layerNumber;
    public BSCLayer layer;

    // UI:
    public BSCPanel panel;




    public BSC (BSCLayer layer) {
        this.id = PhoneBookLogic.StationsCounter;
        PhoneBookLogic.StationsCounter++;

        gatheredMessages = new ArrayList<Message>();
        WaitingMessages = 0;
        isFull = false;

        ProcessedMessages = 0;

        this.layer = layer;
        layerNumber = layer.getLayerNumber();

        connectedBSC = null;
        connectedBTS = null;
//        BSCManager.updateLastLayer();

        // UI:
        panel = new BSCPanel(this);


    }

    void addMessage(Message message) {
        if (gatheredMessages.size() > 5) {
            isFull = true;
        } else {
            gatheredMessages.add(message);
            WaitingMessages = gatheredMessages.size();
//            System.out.println(gatheredMessages.size() + " messages in BSC " + id);
        }

    }

    @Override
    public void run() {
        System.out.println("BSC: " + id + " started");

        while (true) {

            if (gatheredMessages.size() > 0) {

                panel.updateWaitingMessagesNumber(getWaitingMessages());
                panel.updateProcessedMessagesNumber(getProcessedMessages());

                try {
                    Thread.sleep((long) (Math.random() * 10000 + 5000)); // wait for random time (5-15s)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if (/*!layer.isLastLayer()*/ layerNumber == BSCManager.getLastLayerNumber()) {
                    connectToBTS(BTSManager.getLayer2BTS());

                }
                else {
                    connectToBSC(BSCManager.getLayerXbsc(/*BSCManager.getLayerNumber() -1*/ layerNumber + 1));
                }

                processNextMessage();

                panel.updateWaitingMessagesNumber(getWaitingMessages());
                panel.updateProcessedMessagesNumber(getProcessedMessages());

            } else {
                try {
                    Thread.sleep(100); // Jeżeli nie ma oczekujących wiadomości, czekaj przed kolejną iteracją pętli
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void connectToBSC (BSC bsc) {
        this.connectedBSC = bsc;
    }

    public void connectToBTS (BTS bts) {
        this.connectedBTS = bts;
    }

    public void processNextMessage() {
        Message m = gatheredMessages.get(0);

        // Przetwarzanie wiadomości...
        if (connectedBSC != null) {
            connectedBSC.addMessage(m);
        } else if (connectedBTS != null) {
            connectedBTS.addMessage(m);
        } else {
            System.out.println("BSC " + id + " is not connected to any BTS or BSC");
        }


        gatheredMessages.remove(0);
        WaitingMessages = gatheredMessages.size();
        ProcessedMessages++;
        isFull = false; // Aktualizacja flagi isFull
    }

    public int getId () {
        return id;
    }
    public int getProcessedMessages () {
        return ProcessedMessages;
    }

    public int getWaitingMessages () {
        return WaitingMessages;
    }


}


