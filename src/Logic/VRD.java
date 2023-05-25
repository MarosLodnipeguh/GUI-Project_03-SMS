package Logic;

// Virtual Receiver Device (Receiver)

import Handlers.VRDListener;
import SMS.Message;
import SMS.PhoneBookLogic;
import UI.VRDPanelUI;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static SMS.PhoneBookLogic.phoneBook;
import static SMS.PhoneBookLogic.recipentBook;


public class VRD implements Runnable, VRDListener {
    private String number;
    private ArrayList <Message> receivedMessages;
    private boolean autoDelete;
    private VRDListener listener;
    private volatile boolean running = true;

    public VRD () {
        this.number = PhoneBookLogic.generateNumber();
        this.receivedMessages = new ArrayList<Message>();
        this.autoDelete = false;

        phoneBook.add(number);
        recipentBook.add(number);

        //DEBUG:
        System.out.println("VRD created with number: " + number);

    }

    @Override
    public void run() {
        System.out.println("VRD: " + number + " started");

        while (running) {

            if (autoDelete) {

                receivedMessages.clear();
                UpdateVRDPanelUI(receivedMessagesCount());

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println("VRD: " + number + " stopped");
    }






    public void addMessage (Message message) {
        receivedMessages.add(message);
        UpdateVRDPanelUI(receivedMessagesCount());
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

    @Override
    public void UpdateVRDPanelUI (int receivedCount) {
        listener.UpdateVRDPanelUI(receivedCount);
        System.out.println("VRD: " + number + " updated UI");
    }


    public void stopVRD () {
        running = false;
    }

    public void setListener (VRDListener listener) {
        this.listener = listener;
    }

    @Override
    public void AddNewVRDPanelUI (VRDPanelUI ui) {

    }
    @Override
    public void AddNewVRD () {

    }
    @Override
    public void RemoveVRD (VRD vrd) {

    }
    @Override
    public void RemoveVRDPanelUI (VRDPanelUI ui) {

    }
}
