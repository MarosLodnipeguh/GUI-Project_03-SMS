package Handlers;

import UI.BSCLayerUI;
import UI.BSCPanelUI;

public interface BSCListener {

    // Logic to UI:
    void AddNewBSCLayerUI(BSCLayerUI ui); // BSCManager -> StationsPanel
    void AddNewBSCPanelUI(BSCPanelUI ui); // BSCLayer -> BSCLayerUI
    void updateBSCPanel(UpdateStationPanelUIEvent evt); // BSC -> BSCPanelUI

    // Logic to Logic: ???
//    void newBSC(NewBSCEvent evt); // BSC -> BSCLayer




    // UI to Logic: (+ - buttons)
    void AddNewBSCLayer(); // StationsPanel -> BSCManager
    void RemoveLastBSCLayer(); // StationsPanel -> BSCManager

//    void onMessageReceived(BSC bsc, Message message); // Metoda wywoływana po otrzymaniu wiadomości w kontrolerze BSC
//    void onMessageProcessed(BSC bsc, Message message); // Metoda wywoływana po przetworzeniu wiadomości w kontrolerze BSC
}
