package Logic;

import Handlers.BSCListener;
import Handlers.NullListener;
import Handlers.UpdateStationPanelUIEvent;
import UI.BSCLayerUI;
import UI.BSCPanelUI;

import java.util.concurrent.ConcurrentLinkedQueue;

public class BSCLayer implements BSCListener {

    private final ConcurrentLinkedQueue<BSC> bscList;
    private int layerNumber;
    private BSCListener listener;


    public BSCLayer (int layerNumber) {
        this.bscList = new ConcurrentLinkedQueue<>();
        this.layerNumber = layerNumber;

        this.listener = new NullListener();
    }

    public synchronized void newBSC () {
        BSC bsc = new BSC(this);
        bscList.add(bsc);

        //UI:
        BSCPanelUI ui = new BSCPanelUI(bsc);
        bsc.setListener(ui);

        AddNewBSCPanelUI(ui);


        Thread bscThread = new Thread(bsc);
        bscThread.start();
    }

    @Override
    public void AddNewBSCPanelUI (BSCPanelUI ui) {
        listener.AddNewBSCPanelUI(ui);
    }

    public void setListener (BSCListener listener) {
        this.listener = listener;
    }

    public ConcurrentLinkedQueue<BSC> getBscList () {
        return bscList;
    }

    public int getLayerNumber () {
        return layerNumber;
    }

    public synchronized void stopLayer () {
        for (BSC bsc : bscList) {
            bsc.stopBSC();
        }
    }




    @Override
    public void AddNewBSCLayerUI (BSCLayerUI ui) {}
    @Override
    public void AddNewBSCLayer () {}
    @Override
    public void RemoveLastBSCLayer () {}
    @Override
    public void updateBSCPanel (UpdateStationPanelUIEvent evt) {}

}


