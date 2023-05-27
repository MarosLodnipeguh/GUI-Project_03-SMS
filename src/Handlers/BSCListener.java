package Handlers;

import UI.BSCLayerUI;
import UI.BSCPanelUI;

public interface BSCListener {

    // Logic to UI:
    void AddNewBSCLayerUI(BSCLayerUI ui); // BSCManager -> StationsPanel
    void AddNewBSCPanelUI(BSCPanelUI ui); // BSCLayer -> BSCLayerUI
    void updateBSCPanel(UpdateStationPanelUIEvent evt); // BSC -> BSCPanelUI


    // UI to Logic: (+ - buttons)
    void AddNewBSCLayer(); // StationsPanel -> BSCManager
    void RemoveLastBSCLayer(); // StationsPanel -> BSCManager

}
