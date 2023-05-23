package Application;



import UI.BTSLayerUI;

import java.util.ArrayList;
import java.util.List;

public class BTSLayer {
    private List<BTS> btsList;
    private boolean isLastLayer;

    public BTSLayerUI layer;

    public BTSLayer (boolean isLastLayer) {
        this.btsList = new ArrayList<BTS>();
        
        this.isLastLayer = isLastLayer;
        
        // UI:
        layer = new BTSLayerUI(this);

        // stan uruchomieniowy:
        addBTS(new BTS(this));


    }

    public void addBTS (BTS bts) {
        btsList.add(bts);
        layer.addNewPanel(bts);
    }

    public BTS getBTS (int listIndex) {
        return btsList.get(listIndex);
    }

    public List<BTS> getBtsList () {
        return btsList;
    }

    public boolean isLastLayer () {
        return isLastLayer;
    }

    public void setLastLayer (boolean lastLayer) {
        isLastLayer = lastLayer;
    }

}
