package Application;

// Virtual Base Device (Sender)

import SMS.Message;

import static SMS.PhoneBookLogic.generateNumber;
import static SMS.PhoneBookLogic.phoneBook;

public class VBD implements Runnable {
    private String number;
    private Message message;
    private boolean isSending;
    private double sendFrequency;
    private BTS connectedBTS;

    public VBD (Message message) {
        this.number = message.getSender();
        this.message = message;
        this.isSending = false;
        this.sendFrequency = 5000;

        phoneBook.add(number);

        run();

        // DEBUG:
        System.out.println("VBD created with number: " + number);
    }

    public void connectToBTS (BTS bts) {
        this.connectedBTS = bts;
    }

    public void sendMessage () {
        connectedBTS.addMessage(message);
    }

    @Override
    public void run () {
        while (true) {
            try {
                Thread.sleep((long) sendFrequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            connectedBTS = BTSManager.getLayer1BTS();

            sendMessage();
            //DEBUG:
            System.out.println("VBD: " + number + " sent message to BTS: " + connectedBTS.getId());
        }
    }

    public String getNumber () {
        return number;
    }

    public void setMessage (Message message) {
        this.message = message;
    }


}
