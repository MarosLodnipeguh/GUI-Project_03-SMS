package Logic;

import Handlers.BSCListener;
import Handlers.NullListener;
import Handlers.UpdateStationPanelUIEvent;
import UI.BSCLayerUI;
import UI.BSCPanelUI;

import java.util.ArrayList;
import java.util.List;

public class BSCLayer implements BSCListener {

    private List<BSC> bscList;
    private int layerNumber;
    private BSCListener listener;


    public BSCLayer (int layerNumber) {
        this.bscList = new ArrayList<BSC>();
        this.layerNumber = layerNumber;

        this.listener = new NullListener();

        // stan uruchomieniowy:
//        newBSC();
    }


    public void newBSC () {
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
//        System.out.println("Panel sent a panel to: " + listener.getClass().getSimpleName());
    }

    public void setListener (BSCListener listener) {
        this.listener = listener;
//        System.out.println("BSCLayer has a listener now: " + listener.getClass().getSimpleName());
    }

    public List<BSC> getBscList () {
        return bscList;
    }

    public int getLayerNumber () {
        return layerNumber;
    }

    public void stopLayer () {
        for (BSC bsc : bscList) {
            bsc.stopBSC();
        }
    }




    @Override
    public void AddNewBSCLayerUI (BSCLayerUI ui) {

    }



    @Override
    public void AddNewBSCLayer () {

    }

    @Override
    public void RemoveLastBSCLayer () {

    }



    @Override
    public void updateBSCPanel (UpdateStationPanelUIEvent evt) {

    }


}

