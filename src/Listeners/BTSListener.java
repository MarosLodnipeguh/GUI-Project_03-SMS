package Listeners;

import Application.BTS;
import SMS.Message;

public interface BTSListener {
    void onMessageReceived(BTS bts, Message message); // Metoda wywoływana po otrzymaniu wiadomości w stacji bazowej
    void onMessageProcessed(BTS bts, Message message); // Metoda wywoływana po przetworzeniu wiadomości w stacji bazowej
}
