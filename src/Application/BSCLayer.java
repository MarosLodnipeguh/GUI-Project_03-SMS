package Application;

import UI.BSCLayerUI;

import java.util.ArrayList;
import java.util.List;

public class BSCLayer {

    private List<BSC> bscList;
//    private boolean isLastLayer;
    private int layerNumber;

    //UI:
    public BSCLayerUI layer;

    public BSCLayer (/*boolean isLastLayer*/ int layerNumber) {
        this.bscList = new ArrayList<BSC>();
//        this.isLastLayer = isLastLayer;

        this.layerNumber = layerNumber;

        // UI:
        layer = new BSCLayerUI(this);

        // stan uruchomieniowy:
        addBSC(new BSC(this));

    }

    public void addBSC (BSC bsc) {
        bscList.add(bsc);
        layer.addNewPanel(bsc);
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


