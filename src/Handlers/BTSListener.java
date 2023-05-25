package Handlers;

import UI.BTSLayerUI;
import UI.BTSPanelUI;

public interface BTSListener {

    // Logic to UI:
    void AddNewBTSLayerUI (BTSLayerUI ui); // BTSManager -> StationsPanel
    void AddNewBTSPanelUI (BTSPanelUI ui); // BTSLayer -> BTSLayerUI
    void updateBTSPanel(UpdateStationPanelUIEvent evt); // BTS -> BTSPanelUI




//    void onMessageReceived(BTS bts, Message message); // Metoda wywoływana po otrzymaniu wiadomości w stacji bazowej
//    void onMessageProcessed(BTS bts, Message message); // Metoda wywoływana po przetworzeniu wiadomości w stacji bazowej
}
