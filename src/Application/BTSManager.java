package Application;

import Listeners.BTSListener;
import SMS.Message;

import java.util.ArrayList;
import java.util.List;

public class BTSManager implements BTSListener {

    private int layerCount;

    static class BTSLayer {
        private List <BTS> btsList;
        private int layerNumber;

        public BTSLayer (int layerNumber) {
            this.btsList = new ArrayList <BTS>();
            this.layerNumber = layerNumber;

            // stan uruchomieniowy:
            btsList.add(new BTS());

        }
    }

    private static List <BTSLayer> btsLayers;

    public BTSManager () {
        this.btsLayers = new ArrayList<BTSLayer>();

        this.layerCount = 1;

//        btsLayers.add(new BTSLayer(1));
//        btsLayers.add(new BTSLayer(2));

    }

    public static BTS getLayer1BTS () {
        // zwraca pierwszy niezapełniony BTS z warstwy 1
        for (BTS bts : btsLayers.get(0).btsList) {
            if (bts.isFull == false) {
                return bts;
            }
        }
        return null;
    }

    public static BTS addLayer () {
        btsLayers.add(new BTSLayer(btsLayers.size() + 1));
        return btsLayers.get(btsLayers.size() - 1).btsList.get(0);
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
