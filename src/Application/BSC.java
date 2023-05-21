package Application;

// Controller Station

import SMS.Message;
import SMS.PhoneBookLogic;

import java.util.List;

public class BSC implements Runnable{

    private int id;
    private int ProcessedMessages;
    private int WaitingMessages; // podczas przekazania SMSa do kolejnej warstwy zawsze wybierany jest ten BTS/BSC który zawiera najmniej SMSów
    private boolean isFull;
    private List <Message> gatheredMessages;
    private List<BTS> connectedBTS; //layer?
    private List<BSC> connectedBSC; //layer?



    public BSC () {
        this.id = PhoneBookLogic.StationsCounter;
        ProcessedMessages = 0;
        WaitingMessages = 0;
        isFull = false;

        PhoneBookLogic.StationsCounter++;
    }

    @Override
    public void run () {
        // każdy BSC będzie przechowywał SMS przez losowy czas (od 5 do 15 sek) a następnie będzie go przekazywał do kolejnej warstwy;
        while (true) {
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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        // post process:
        gatheredMessages.remove(0);
        ProcessedMessages++;
    }

    BTS getNextBTS() {
        return connectedBTS.get(0);
    }

    public int getId () {
        return id;
    }

}
