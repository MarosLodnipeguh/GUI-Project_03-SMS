package Listeners;

import Application.VBD;
import SMS.Message;

public interface VBDListener {
    void onMessageSent(VBD vbd, Message message); // Metoda wywoływana po wysłaniu wiadomości przez wirtualne urządzenie nadawcze
    void onVBDRemoved(VBD vbd); // Metoda wywoływana po usunięciu wirtualnego urządzenia nadawczego

//    void addVBD(Message m);
}
