package Application;

import UI.BSCLayerUI;

import java.util.ArrayList;
import java.util.List;

public class BSCLayer {

    private List<BSC> bscList;
    private int layerNumber;

    //UI:
//    public BSCLayerUI layer;

    public BSCLayer (int layerNumber) {
        this.bscList = new ArrayList<BSC>();


        this.layerNumber = layerNumber;



        // stan uruchomieniowy:
        addBSC(new BSC(this));

        // UI:
//        layer = new BSCLayerUI(this);

    }

    public void addBSC (BSC bsc) {
        bscList.add(bsc);
        //UI:
//        layer.addNewPanel(bsc);
    }

    public List<BSC> getBscList () {
        return bscList;
    }

    public int getLayerNumber () {
        return layerNumber;
    }

    //    public boolean isLastLayer () {
//        return isLastLayer;
//    }

//    public void setLastLayer (boolean lastLayer) {
//        isLastLayer = lastLayer;
//    }
}


