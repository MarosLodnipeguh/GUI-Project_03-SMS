package Application;

// Virtual Receiver Device (Receiver)

import SMS.Message;
import UI.VRDPanel;

import java.util.ArrayList;

import static SMS.PhoneBookLogic.*;

public class VRD implements Runnable {
    private String number;
    private ArrayList <Message> receivedMessages;
    private boolean autoDelete;
    public VRDPanel panel;

    public VRD () {
        this.number = generateNumber();
        this.receivedMessages = new ArrayList<Message>();
        this.autoDelete = false;

        phoneBook.add(number);
        recipentBook.add(number);

        VRDManager.addVRD(this);

        //DEBUG:
        System.out.println("VRD created with number: " + number);

        // UI:
        panel = new VRDPanel(this);
    }

    @Override
    public void run () {
        System.out.println("VRD: " + number + " started");

        while (true) {

            if (receivedMessagesCount() > 0) {

//                panel.updateReceivedMessagesNumber(receivedMessagesCount());

//                if (autoDelete) {
//                    // delete messages every 10 seconds
//
//                }
//                else continue;

                panel.updateReceivedMessagesNumber(receivedMessagesCount());

            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

             }


        }
    }





    public void receiveMessage (Message message) {
        receivedMessages.add(message);
        System.out.println("VRD: " + number + " received message ");
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
