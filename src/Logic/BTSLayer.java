package Logic;



import Handlers.BTSListener;
import Handlers.NullListener;
import Handlers.UpdateStationPanelUIEvent;
import UI.BTSLayerUI;
import UI.BTSPanelUI;

import java.util.ArrayList;
import java.util.List;

public class BTSLayer implements BTSListener {
    private List<BTS> btsList;
    private int layerNumber;
//    private boolean isLastLayer;
    private BTSListener listener;

    public BTSLayer (int layerNumber) {
        this.btsList = new ArrayList<BTS>();
        this.layerNumber = layerNumber;

        this.listener = new NullListener();


//        // stan uruchomieniowy:
//        addBTS(new BTS(this));


    }

    public void newBTS () {
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
//        System.out.println("Panel sent a panel to: " + listener.getClass().getSimpleName());
    }

    public void setListener (BTSListener listener) {
        this.listener = listener;
//        System.out.println("BTSLayer has a listener now: " + listener.getClass().getSimpleName());
    }






    public List<BTS> getBtsList () {
        return btsList;
    }

    public int getLayerNumber () {
        return layerNumber;
    }

    @Override
    public void AddNewBTSLayerUI (BTSLayerUI ui) {
        
    }

    

    @Override
    public void updateBTSPanel (UpdateStationPanelUIEvent evt) {

    }
}
