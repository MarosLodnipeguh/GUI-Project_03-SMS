package Logic;


import Handlers.BSCListener;
import Handlers.NullListener;
import Handlers.UpdateStationPanelUIEvent;
import UI.BSCLayerUI;
import UI.BSCPanelUI;

import java.util.ArrayList;
import java.util.List;

public class BSCManager implements BSCListener {

    private static List<BSCLayer> bscLayers;
    private static int lastLayerNumber;

    private static BSCListener listener;

    public BSCManager () {
        bscLayers = new ArrayList<BSCLayer>();
        lastLayerNumber = -1;

        listener = new NullListener();


        // stan uruchiemniowy:

//        AddNewBSCLayer();
    }

    public static BSC getLayerXbsc (int x) {

        // wybierz ten BSC z warstwy, który zawiera najmniej SMSów:
        BSC selectedBSC = null;
        int minMessages = Integer.MAX_VALUE;

        for (BSC bsc : bscLayers.get(x).getBscList()) {
            if (!bsc.isFull) {

                if (bsc.getWaitingMessages() < minMessages) {
                    minMessages = bsc.getWaitingMessages();
                    selectedBSC = bsc;
                }

//                System.out.println("selectedBSC: " + selectedBSC.getId());
                return selectedBSC;
            }

        }

        // jeżeli w danej warstwie ilość SMS w każdym z BSC lub BTS jest większa od 5, automatycznie dodawany jest nowy BTS/BSC;
        bscLayers.get(x).newBSC();
        return getLayerXbsc(x);
    }


    @Override
    public void AddNewBSCLayer () {
        lastLayerNumber++;
        BSCLayer layer = new BSCLayer(lastLayerNumber);
        bscLayers.add(layer);

        // UI:
        BSCLayerUI layerUI = new BSCLayerUI(layer);
        layer.setListener(layerUI);
        AddNewBSCLayerUI(layerUI);
        layer.newBSC();

    }

    @Override
    public void AddNewBSCLayerUI (BSCLayerUI ui) {
        listener.AddNewBSCLayerUI(ui);
//        System.out.println("Layer sent to: " + listener.getClass().getSimpleName());
    }




    public static int getLastLayerNumber () {
        return lastLayerNumber;
    }

    @Override
    public void RemoveLastBSCLayer () {
        bscLayers.get(getLastLayerNumber()).stopLayer();
        bscLayers.remove(getLastLayerNumber());
        lastLayerNumber--;

    }

    public void setListener (BSCListener listener) {
        BSCManager.listener = listener;
//        System.out.println("BSCManager has a listener now: " + listener.getClass().getSimpleName());
    }

    public static List<BSCLayer> getBscLayers () {
        return bscLayers;
    }

    public static BSCLayer getBscLayerAt (int index) {
        return bscLayers.get(index);
    }




    public static int getLayerNumber () {
        return bscLayers.size();
    }




    @Override
    public void AddNewBSCPanelUI (BSCPanelUI ui) {

    }





    @Override
    public void updateBSCPanel (UpdateStationPanelUIEvent evt) {

    }




}
