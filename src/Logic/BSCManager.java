package Logic;

import Handlers.BSCListener;
import Handlers.NullListener;
import Handlers.UpdateStationPanelUIEvent;
import UI.BSCLayerUI;
import UI.BSCPanelUI;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BSCManager implements BSCListener {

    private static List<BSCLayer> bscLayers;
    private static int lastLayerNumber;
    private static BSCListener listener;

    public BSCManager () {
        bscLayers = new CopyOnWriteArrayList<>();
        lastLayerNumber = -1;

        listener = new NullListener();
    }

    public static synchronized BSC getLayerXbsc (int x) {
        // Wybierz ten BSC z warstwy, który zawiera najmniej SMSów:
        BSC selectedBSC = null;
        int minMessages = Integer.MAX_VALUE;

        for (BSC bsc : bscLayers.get(x).getBscList()) {
            if (!bsc.getisFull()) {
                if (bsc.getWaitingMessages() < minMessages) {
                    minMessages = bsc.getWaitingMessages();
                    selectedBSC = bsc;
                }
                return selectedBSC;
            }
        }

        // Jeżeli w danej warstwie ilość SMS w każdym z BSC lub BTS jest większa od 5, automatycznie dodawany jest nowy BTS/BSC;
        bscLayers.get(x).newBSC();
        return getLayerXbsc(x);
    }

    @Override
    public synchronized void AddNewBSCLayer () {
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
    }

    public synchronized static int getLastLayerNumber () {
        return lastLayerNumber;
    }

    @Override
    public synchronized void RemoveLastBSCLayer () {
        if (!bscLayers.isEmpty()) {
            bscLayers.get(getLastLayerNumber()).stopLayer();
            bscLayers.remove(getLastLayerNumber());
            lastLayerNumber--;
        }
    }

    public void stopAllLayers () {
        for (BSCLayer layer : bscLayers) {
            layer.stopLayer();
        }
    }

    public void setListener (BSCListener listener) {
        BSCManager.listener = listener;
    }

    @Override
    public void AddNewBSCPanelUI (BSCPanelUI ui) {}

    @Override
    public void updateBSCPanel (UpdateStationPanelUIEvent evt) {}
}
