package Handlers;

import Logic.*;
import UI.*;

public class NullListener implements VBDListener, BTSListener, BSCListener, VRDListener{
    @Override
    public BSCListener AddNewBSCLayerUI(BSCLayer layer) {
        return null;
    }

    @Override
    public BSCListener AddNewBSCPanelUI(BSC bsc) {
        return null;
    }

    @Override
    public void updateBSCPanel (UpdateStationPanelUIEvent evt) {

    }

    @Override
    public void AddNewBSCLayer () {

    }

    @Override
    public void RemoveLastBSCLayer () {

    }

    @Override
    public BTSListener AddNewBTSLayerUI (BTSLayer layer) {

        return null;
    }

    @Override
    public BTSListener AddNewBTSPanelUI (BTS bts) {

        return null;
    }

    @Override
    public void updateBTSPanel (UpdateStationPanelUIEvent evt) {

    }

    @Override
    public void AddNewVBDPanelUI (VBDPanelUI ui) {

    }

    @Override
    public void AddNewVBD (String messageText) {

    }

    @Override
    public void RemoveVBD (VBD vbd) {

    }

    @Override
    public void RemoveVBDPanelUI (VBDPanelUI ui) {

    }


    @Override
    public void AddNewVRDPanelUI (VRDPanelUI ui) {

    }

    @Override
    public void UpdateVRDPanelUI (int receivedCount) {

    }

    @Override
    public void AddNewVRD () {

    }

    @Override
    public void RemoveVRD (VRD vrd) {

    }

    @Override
    public void RemoveVRDPanelUI (VRDPanelUI ui) {

    }
}
