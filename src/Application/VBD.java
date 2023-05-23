package Application;

// Virtual Base Device (Sender)

import Listeners.VBDListener;
import SMS.Message;
import Listeners.AddVBDEvent;

import javax.swing.*;

import java.util.ArrayList;

import static SMS.PhoneBookLogic.phoneBook;

public class VBD implements Runnable {
    private String number;
    private Message message;
    private boolean isSending;
    private double sendFrequency;
    private BTS connectedBTS;

    public VBD (Message message) {
        this.message = message;
        this.number = message.getSender();

        this.isSending = false;
        this.sendFrequency = 500; // 5000

        phoneBook.add(number);

        // DEBUG:
        System.out.println("VBD created with number: " + number);

//        fireAddVBDPanel(this);

    }

    public void connectToBTS (BTS bts) {
        this.connectedBTS = bts;
    }

    public void sendMessage () {
        connectedBTS.addMessage(message);
    }

    @Override
    public void run () {
        this.isSending = true;

        while (true) {

            connectedBTS = BTSManager.getLayer1BTS();

            sendMessage();
            //DEBUG:
            System.out.println("VBD: " + number + " sent message to BTS: " + connectedBTS.getId());

            try {
                System.out.println("\nsleeping for " + sendFrequency + "ms\n");
                Thread.sleep((long) sendFrequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public String getNumber () {
        return number;
    }

    public void setMessage (Message message) {
        this.message = message;
    }


    // Listener test

    private ArrayList<VBDListener> listeners = new ArrayList<>();

    public void addVBDListener(VBDListener listener){ // argument tutaj to klasa docelowa która nasłuchuje na event! w której sie coś stanie
        this.listeners.add(listener);
    }

    public void fireAddVBDPanel(VBD vbd){
//        AddVBDEvent evt = new AddVBDEvent( this, this);
        for(VBDListener listener : listeners)
            listener.addVBDPanel(vbd);
    }




}
