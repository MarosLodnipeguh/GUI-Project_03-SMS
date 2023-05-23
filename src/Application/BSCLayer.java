package Application;

import UI.BSCLayerUI;

import java.util.ArrayList;
import java.util.List;

public class BSCLayer {

    private List<BSC> bscList;
    private boolean isLastLayer;
    private int layerNumber;

    public BSCLayerUI layer;

    public BSCLayer (boolean isLastLayer) {
        this.bscList = new ArrayList<BSC>();
        this.isLastLayer = isLastLayer;

        this.layerNumber = BSCManager.getLayerNumber();

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

    public boolean isLastLayer () {
        return isLastLayer;
    }

    public void setLastLayer (boolean lastLayer) {
        isLastLayer = lastLayer;
    }
}


