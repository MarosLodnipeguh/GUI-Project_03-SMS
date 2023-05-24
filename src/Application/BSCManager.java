package Application;

import Listeners.BSCListener;
import SMS.Message;
import UI.BSCLayerUI;

import java.util.ArrayList;
import java.util.List;

public class BSCManager implements BSCListener {

    private static List<BSCLayer> bscLayers;
    private static int lastLayerNumber;

    public BSCManager () {
        bscLayers = new ArrayList<BSCLayer>();
        lastLayerNumber = 0;

        // stan uruchiemniowy:
        addLayer();
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

    public static void addLayer () {
        bscLayers.add(new BSCLayer(lastLayerNumber));
        lastLayerNumber++;
    }

    public static int getLastLayerNumber () {
        return lastLayerNumber;
    }

//    public static BSCLayerUI getBscLayerUI (int i) {
//        return bscLayers.get(i).layer;
//    }

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
    public void onMessageReceived (BSC bsc, Message message) {

    }

    @Override
    public void onMessageProcessed (BSC bsc, Message message) {

    }
}
