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

    public BTSPanel panel;


    public BTS () {
        this.id = PhoneBookLogic.StationsCounter;
        PhoneBookLogic.StationsCounter++;

        gatheredMessages = new ArrayList <Message>();
        WaitingMessages = 0;
        isFull = false;

        ProcessedMessages = 0;

        panel = new BTSPanel(this);
    }


    void addMessage(Message message) {
        gatheredMessages.add(message);
        WaitingMessages = gatheredMessages.size();
        System.out.println(gatheredMessages.size() + " messages in BTS " + id);

//        panel.updateWaitingMessagesNumber(getWaitingMessages());
    }


    @Override
    public void run() {
        System.out.println("BTS: " + id + " started");

        while (true) {

            if (gatheredMessages.size() > 0) {

                try {
                    System.out.println("BTS: " + id + " waiting for 3 seconds");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                panel.updateWaitingMessagesNumber(getWaitingMessages());
                processNextMessage();
                panel.updateProcessedMessagesNumber(getProcessedMessages());
            } else {
                try {
                    // Jeżeli nie ma oczekujących wiadomości, czekaj przed kolejną iteracją pętli
//                    System.out.println("BTS: " + id + " waiting for messages");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                continue;
            }


//            if (gatheredMessages.size() > 5) {
//                isFull = true;
//                System.out.println("BTS " + id + " is full");
//            } else continue;
        }
    }




    public void processNextMessage() {
        Message m = gatheredMessages.get(0);
        // Przetwarzanie wiadomości...

        System.out.println("BTS " + id + ": processed message ");

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
