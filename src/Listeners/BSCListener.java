package Listeners;

import Application.BSC;
import SMS.Message;

public interface BSCListener {
    void onMessageReceived(BSC bsc, Message message); // Metoda wywoływana po otrzymaniu wiadomości w kontrolerze BSC
    void onMessageProcessed(BSC bsc, Message message); // Metoda wywoływana po przetworzeniu wiadomości w kontrolerze BSC
}
