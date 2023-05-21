package Application;

import Listeners.BSCListener;
import SMS.Message;

public class BSCManager implements BSCListener {

    public void addBSC() {
        //jeżeli w danej warstwie ilość SMS w każdym z BSC lub BTS jest większa od 5, automatycznie dodawany jest nowy BTS/BSC;
    }

    public void removeBSC() {

    }


    @Override
    public void onMessageReceived (BSC bsc, Message message) {

    }

    @Override
    public void onMessageProcessed (BSC bsc, Message message) {

    }
}
