package Listeners;

import Application.VRD;
import SMS.Message;

public interface VRDListener {
    void onMessageReceived(VRD vrd, Message message); // Metoda wywoływana po otrzymaniu wiadomości przez wirtualne urządzenie odbiorcze
    void onVRDRemoved(VRD vrd); // Metoda wywoływana po usunięciu wirtualnego urządzenia odbiorczego
}
