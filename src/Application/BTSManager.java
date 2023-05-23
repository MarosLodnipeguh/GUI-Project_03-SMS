package Application;

import Listeners.BTSListener;
import SMS.Message;
import UI.BTSLayerUI;

import java.util.ArrayList;
import java.util.List;

public class BTSManager implements BTSListener {

    private static List <BTSLayer> btsLayers;

    public BTSManager () {
        btsLayers = new ArrayList<BTSLayer>();


        btsLayers.add(new BTSLayer(false));
        btsLayers.add(new BTSLayer(true));

    }

    public static BTS getLayer1BTS () {

        // wybierz ten BTS z warstwy, który zawiera najmniej SMSów:
        BTS selectedBTS = null;
        int minMessages = Integer.MAX_VALUE;

        for (BTS bts : btsLayers.get(0).getBtsList()) {
            if (!bts.isFull) {

                if (bts.getWaitingMessages() < minMessages) {
                    minMessages = bts.getWaitingMessages();
                    selectedBTS = bts;
                }

//                System.out.println("selectedBTS: " + selectedBTS.getId());
                return selectedBTS;
            }

        }

        // jeżeli w danej warstwie ilość SMS w każdym z BSC lub BTS jest większa od 5, automatycznie dodawany jest nowy BTS/BSC;
        btsLayers.get(0).addBTS(new BTS(btsLayers.get(0)) );
        return getLayer1BTS();
    }

    public static BTS getLayer2BTS () {

        // wybierz ten BTS z warstwy, który zawiera najmniej SMSów:
        BTS selectedBTS = null;
        int minMessages = Integer.MAX_VALUE;

        for (BTS bts : btsLayers.get(1).getBtsList()) {
            if (!bts.isFull) {

                if (bts.getWaitingMessages() < minMessages) {
                    minMessages = bts.getWaitingMessages();
                    selectedBTS = bts;
                }

//                System.out.println("selectedBTS: " + selectedBTS.getId());
                return selectedBTS;
            }

        }

        // jeżeli w danej warstwie ilość SMS w każdym z BSC lub BTS jest większa od 5, automatycznie dodawany jest nowy BTS/BSC;
        btsLayers.get(1).addBTS(new BTS(btsLayers.get(1)) );
        return getLayer1BTS();
    }

    public void layerOverFill () {
        // jeżeli w danej warstwie ilość SMS w każdym z BSC lub BTS jest większa od 5, automatycznie dodawany jest nowy BTS/BSC;

    }

//    public static BTS getLayer2BTS () {
//        // zwraca pierwszy niezapełniony BTS z warstwy 2
//        for (BTS bts : btsLayers.get(1).btsList) {
//            if (!bts.isFull) {
//                return bts;
//            }
//        }
//        return null;
//    }

//    public static BTS addLayer () {
//        BTSLayer newLayer = new BTSLayer(btsLayers.size() + 1);
//        btsLayers.add(newLayer);
//        return newLayer.btsList.get(0); // zwraca pierwszego BTSa z nowej warstwy, żeby dodać jego panel w UI
//    }

    public static BTSLayerUI getBTSLayerUI (int i) {
        return btsLayers.get(i).layer;
    }

    public void addBTSEnd() {
        // jeżeli w danej warstwie ilość SMS w każdym z BSC lub BTS jest większa od 5, automatycznie dodawany jest nowy BTS/BSC;
    }

    public void removeBTS() {

    }

    public void getNextBTS() {
        // podczas przekazania SMSa do kolejnej warstwy zawsze wybierany jest ten BTS/BSC który zawiera najmniej SMSów;
    }


    @Override
    public void onMessageReceived (BTS bts, Message message) {

    }

    @Override
    public void onMessageProcessed (BTS bts, Message message) {

    }
}
