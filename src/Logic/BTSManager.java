package Logic;

import Handlers.BTSListener;
import Handlers.NullListener;
import Handlers.UpdateStationPanelUIEvent;
import UI.BTSLayerUI;
import UI.BTSPanelUI;

import java.util.ArrayList;
import java.util.List;

public class BTSManager implements BTSListener {

    private static List <BTSLayer> btsLayers;
    private int lastLayerNumber;
    private BTSListener listener;

    public BTSManager () {
        btsLayers = new ArrayList<BTSLayer>();
        lastLayerNumber = 0;

        listener = new NullListener();


//        btsLayers.add(new BTSLayer(false));
//        btsLayers.add(new BTSLayer(true));

    }

    public static BTS getLayerXBTS (int x) {

        // wybierz ten BTS z warstwy, który zawiera najmniej SMSów:
        BTS selectedBTS = null;
        int minMessages = Integer.MAX_VALUE;

        for (BTS bts : btsLayers.get(x).getBtsList()) {
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
        btsLayers.get(x).newBTS();
        return getLayerXBTS(x);
    }


    public void NewBTSLayer () {
        BTSLayer layer = new BTSLayer(lastLayerNumber);
        lastLayerNumber++;
        btsLayers.add(layer);

        // UI:
        BTSLayerUI layerUI = new BTSLayerUI(layer);
        layer.setListener(layerUI);
        AddNewBTSLayerUI(layerUI);
        layer.newBTS();
    }

    @Override
    public void AddNewBTSLayerUI (BTSLayerUI ui) {
        listener.AddNewBTSLayerUI(ui);
//        System.out.println("Layer sent to: " + listener.getClass().getSimpleName());
    }

    public void setListener (BTSListener listener) {
        this.listener = listener;
//        System.out.println("BTSManager set listener to: " + listener.getClass().getSimpleName());
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
    public void AddNewBTSPanelUI (BTSPanelUI ui) {

    }

    @Override
    public void updateBTSPanel (UpdateStationPanelUIEvent evt) {

    }
}
