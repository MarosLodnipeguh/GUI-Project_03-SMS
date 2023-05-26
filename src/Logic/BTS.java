package Logic;

// Base Station

import Handlers.BTSListener;
import Handlers.UpdateStationPanelUIEvent;
import SMS.InvalidRecipentException;
import SMS.Message;
import SMS.PhoneBookLogic;

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

    private int layerNumber;
    public BTSLayer layer;

    private BTSListener listener;


    public BTS (BTSLayer layer) {
        this.id = PhoneBookLogic.StationsCounter;
        PhoneBookLogic.StationsCounter++;

        gatheredMessages = new ArrayList <Message>();
        WaitingMessages = 0;
        isFull = false;

        ProcessedMessages = 0;

        this.layer = layer;
        layerNumber = layer.getLayerNumber();

        connectedBSC = null;
        connectedVRD = null;

    }

    public void setListener(BTSListener listener) {
        this.listener = listener;
    }


    public void addMessage(Message message) {

        gatheredMessages.add(message);
        WaitingMessages = gatheredMessages.size();

        if (gatheredMessages.size() > 5) {
            isFull = true;
        }

    }


    @Override
    public void run() {
        System.out.println("BTS: " + id + " started");

        while (true) {

            if (gatheredMessages.size() > 0) {

                listener.updateBTSPanel(new UpdateStationPanelUIEvent(this, this.id, this.getProcessedMessages(), this.WaitingMessages));

//                try {
////                    System.out.println("BTS: " + id + " waiting for 3 seconds");
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }


                if (layerNumber == 0) {
                    connectToBSC(BSCManager.getLayerXbsc(0)); // pierwsza wartswa BSC
                }
                else {
                    connectToVRD();
                }


//                try {
//                    processNextMessage();
//                } catch (InvalidRecipentException e) {
//                    e.printStackTrace();
//                }

                processNextMessage();

                listener.updateBTSPanel(new UpdateStationPanelUIEvent(this, this.id, this.getProcessedMessages(), this.WaitingMessages));

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
        this.connectedVRD = MainLogic.getVRD(gatheredMessages.get(0).getRecipient());
    }

    public void processNextMessage() {

        try {
//                    System.out.println("BTS: " + id + " waiting for 3 seconds");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Message m = gatheredMessages.get(0);

        try {
            // Przetwarzanie wiadomości...
            if (connectedBSC != null) {
                connectedBSC.addMessage(m);
            } else if (connectedVRD != null) {
                connectedVRD.addMessage(m);
            } else {
                throw new InvalidRecipentException();
            }

            System.out.println("BTS " + id + ": processed message");
            gatheredMessages.remove(0);
            WaitingMessages = gatheredMessages.size();
            ProcessedMessages++;
            isFull = false; // Aktualizacja flagi isFull
        } catch (InvalidRecipentException e) {
            e.printStackTrace();
        }
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
