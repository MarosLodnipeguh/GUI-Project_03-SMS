package Application;

// Virtual Receiver Device (Receiver)

import SMS.Message;

import java.util.ArrayList;

import static SMS.PhoneBookLogic.*;

public class VRD implements Runnable {
    private String number;
    private ArrayList <Message> receivedMessages;
    private boolean autoDelete;

    public VRD () {
        this.number = generateNumber();
        this.receivedMessages = new ArrayList<Message>();
        this.autoDelete = false;

        phoneBook.add(number);
        recipentBook.add(number);

        //DEBUG:
        System.out.println("VRD created with number: " + number);
    }

    @Override
    public void run () {


        if (autoDelete) {
            // delete messages every 10 seconds
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                receivedMessages.clear();
            }
        }


    }

    public void receiveMessage (Message message) {
        receivedMessages.add(message);
    }

    public int receivedMessagesCount () {
        return receivedMessages.size();
    }

    public String getNumber () {
        return number;
    }

    public void setAutoDelete (boolean autoDelete) {
        this.autoDelete = autoDelete;
    }



}
