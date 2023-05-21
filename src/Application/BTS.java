package Application;

// Base Station

import SMS.Message;
import SMS.PhoneBookLogic;

import java.util.ArrayList;
import java.util.List;

public class BTS implements Runnable{

    private int id;
    private int ProcessedMessages;
    private int WaitingMessages; // podczas przekazania SMSa do kolejnej warstwy zawsze wybierany jest ten BTS/BSC który zawiera najmniej SMSów
    boolean isFull;
    private List <Message> gatheredMessages;


    public BTS () {
        this.id = PhoneBookLogic.StationsCounter;
        ProcessedMessages = 0;
        WaitingMessages = 0;
        isFull = false;
        gatheredMessages = new ArrayList <Message>();

        PhoneBookLogic.StationsCounter++;

//        run();
    }

    @Override
    public void run () {
        //każdy BTS będzie przekazywał SMS do kolejnej warstwy lub VRD po upływie 3 sek
        while (true) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (gatheredMessages.size() > 5) {
                isFull = true;
            }

            processNextMessage();
        }
    }

    void addMessage(Message message) {
        gatheredMessages.add(message);
    }

    Message getNextMessage() {
        return gatheredMessages.get(0);
    }

    void processNextMessage() {
        Message m = getNextMessage();
        // process:




        // post process:
        gatheredMessages.remove(0);
        ProcessedMessages++;
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
