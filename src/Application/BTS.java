package Application;

// Base Station

import SMS.Message;
import SMS.PhoneBookLogic;
import UI.BTSPanel;

import java.util.ArrayList;
import java.util.List;

public class BTS implements Runnable{

    private int id;
    private List <Message> gatheredMessages;
    private int WaitingMessages; // podczas przekazania SMSa do kolejnej warstwy zawsze wybierany jest ten BTS/BSC który zawiera najmniej SMSów
    boolean isFull;
    private int ProcessedMessages;
    private BSC connectedBSC;
    private VRD connectedVRD;

    // UI:
    public BTSPanel panel;
    public BTSLayer layer;


    public BTS (BTSLayer layer) {
        this.id = PhoneBookLogic.StationsCounter;
        PhoneBookLogic.StationsCounter++;

        gatheredMessages = new ArrayList <Message>();
        WaitingMessages = 0;
        isFull = false;

        ProcessedMessages = 0;

        connectedBSC = null;
        connectedVRD = null;

        // UI:
        panel = new BTSPanel(this);

        this.layer = layer;
    }


    void addMessage(Message message) {
        if (gatheredMessages.size() > 5) {
            isFull = true;
//            System.out.println("BTS " + id + " is full");
        } else {
            gatheredMessages.add(message);
            WaitingMessages = gatheredMessages.size();
//            System.out.println(gatheredMessages.size() + " messages in BTS " + id);
        }

    }


    @Override
    public void run() {
        System.out.println("BTS: " + id + " started");

        while (true) {

            if (gatheredMessages.size() > 0) {

                panel.updateWaitingMessagesNumber(getWaitingMessages());
                panel.updateProcessedMessagesNumber(getProcessedMessages());

                try {
//                    System.out.println("BTS: " + id + " waiting for 3 seconds");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if (!layer.isLastLayer()) {
                    connectToBSC(BSCManager.getLayerXbsc(0)); // pierwsza wartswa BSC
                }
                else {
                    connectToVRD();
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
//                continue;
            }



        }
    }

    public void connectToBSC (BSC bsc) {
        this.connectedBSC = bsc;
    }

    public void connectToVRD () {
        this.connectedVRD = VRDManager.getVRD(gatheredMessages.get(0).getRecipient());
    }

    public void processNextMessage() {
        Message m = gatheredMessages.get(0);

        // Przetwarzanie wiadomości...
        if (connectedBSC != null) {
            connectedBSC.addMessage(m);
        } else if (connectedVRD != null) {
            connectedVRD.receiveMessage(m);
        } else {
            System.out.println("BTS " + id + " is not connected to any BSC or VRD");
        }
//        System.out.println("BTS " + id + ": processed message ");

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
