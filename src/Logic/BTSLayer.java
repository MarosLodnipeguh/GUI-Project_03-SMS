package Logic;

import Handlers.BTSListener;
import Handlers.NullListener;
import Handlers.UpdateStationPanelUIEvent;
import UI.BTSLayerUI;
import UI.BTSPanelUI;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class BTSLayer implements BTSListener {
    private final ConcurrentLinkedQueue <BTS> btsList;
    private int layerNumber;
    private BTSListener listener;

    public BTSLayer (int layerNumber) {
        this.btsList = new ConcurrentLinkedQueue <BTS>();
        this.layerNumber = layerNumber;

        this.listener = new NullListener();
    }

    public synchronized void newBTS () {
        BTS bts = new BTS(this);
        btsList.add(bts);

        //UI:
        BTSPanelUI ui = new BTSPanelUI(bts);
        bts.setListener(ui);

        AddNewBTSPanelUI(ui);

        Thread btsThread = new Thread(bts);
        btsThread.start();
    }

    @Override
    public void AddNewBTSPanelUI (BTSPanelUI ui) {
        listener.AddNewBTSPanelUI(ui);
    }

    public void setListener (BTSListener listener) {
        this.listener = listener;
    }

    public ConcurrentLinkedQueue <BTS> getBtsList () {
        return btsList;
    }

    public int getLayerNumber () {
        return layerNumber;
    }

    @Override
    public void AddNewBTSLayerUI (BTSLayerUI ui) {}
    @Override
    public void updateBTSPanel (UpdateStationPanelUIEvent evt) {}

    public void stopLayer () {
        for (BTS bts : btsList) {
            bts.stopBTS();
        }
    }
}
