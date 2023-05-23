package Application;

import Listeners.BSCListener;
import SMS.Message;
import UI.BSCLayerUI;

import java.util.ArrayList;
import java.util.List;

public class BSCManager implements BSCListener {

    private static List<BSCLayer> bscLayers;
//    private static int lastLayer;

    public BSCManager () {
        bscLayers = new ArrayList<BSCLayer>();
        
        bscLayers.add(new BSCLayer(true));
//        lastLayer = 0;
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
        bscLayers.get(x).addBSC(new BSC(bscLayers.get(x)) );
        return getLayerXbsc(x);
    }


    public static BSCLayerUI getBscLayerUI (int i) {
        return bscLayers.get(i).layer;
    }

    public static BSCLayer createNewBscLayer () {
        BSCLayer newLayer = new BSCLayer(true);
        bscLayers.add(newLayer);

        for (BSCLayer bscL : bscLayers) {
            bscL.setLastLayer(false);
        }
//        updateLastLayer();
        return newLayer;
    }

//    public static void updateLastLayer () {
//        lastLayer = bscLayers.size() - 1;
//    }

//    public static int getLastLayer () {
//        return lastLayer;
//    }

    public static int getLayerNumber () {
        return bscLayers.size();
    }

    @Override
    public void onMessageReceived (BSC bsc, Message message) {

    }

    @Override
    public void onMessageProcessed (BSC bsc, Message message) {

    }
}
