package Handlers;

import UI.BTSLayerUI;
import UI.BTSPanelUI;

public interface BTSListener {

    // Logic to UI:
    void AddNewBTSLayerUI (BTSLayerUI ui); // BTSManager -> StationsPanel
    void AddNewBTSPanelUI (BTSPanelUI ui); // BTSLayer -> BTSLayerUI
    void updateBTSPanel(UpdateStationPanelUIEvent evt); // BTS -> BTSPanelUI

}
