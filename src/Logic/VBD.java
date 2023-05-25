package Logic;

// Virtual Base Device (Sender)

import Handlers.VBDListener;
import SMS.Message;
import SMS.PhoneBookLogic;
import UI.VBDPanelUI;

import static SMS.PhoneBookLogic.phoneBook;

public class VBD implements Runnable {
    private String number;
    private String recipent;
    private String messageText;
    private boolean isSending;
    private double sendFrequency;
    private BTS connectedBTS;
    private volatile boolean running = true;


    public VBD (String messageText) {

        this.number = PhoneBookLogic.generateNumber();

        this.messageText = messageText;

        this.isSending = false;
        this.sendFrequency = 5000; // 5000

        phoneBook.add(number);

        // DEBUG:
        System.out.println("VBD created with number: " + number);

    }



    public void connectToBTS (BTS bts) {
        this.connectedBTS = bts;
    }



    @Override
    public void run () {

        while (running) {

            if (isSending) {
                // GENERATE MESSAGE:
                recipent = PhoneBookLogic.getRandomRecipent();
                Message message = new Message(this.number, recipent, this.messageText);

                // SEND MESSAGE:
                connectToBTS(BTSManager.getLayerXBTS(0));
                connectedBTS.addMessage(message);

                try {
//                System.out.println("\nsleeping for " + sendFrequency + "ms\n");
                    Thread.sleep((long) sendFrequency);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println("VBD: " + number + " stopped");

    }

    public String getNumber () {
        return number;
    }


    public void setSending (boolean sending) {
        System.out.println("VBD: " + number + " is sending: " + sending);
        isSending = sending;
    }

    public void setSendFrequency (double sendFrequency) {
        this.sendFrequency = sendFrequency;
    }

    // Listener test

//    private ArrayList<VBDListener> listeners = new ArrayList<>();
//
//    public void addVBDListener(VBDListener listener){ // argument tutaj to klasa docelowa która nasłuchuje na event! w której sie coś stanie
//        this.listeners.add(listener);
//    }
//
//    public void fireAddVBDPanel(VBD vbd){
////        AddVBDEvent evt = new AddVBDEvent( this, this);
//        for(VBDListener listener : listeners)
//            listener.addVBDPanel(vbd);
//    }


    public void stopVBD () {
        running = false;
    }




}

