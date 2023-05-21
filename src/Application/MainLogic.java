package Application;

import SMS.Message;

import java.util.ArrayList;
import java.util.List;

public class MainLogic {
    
    private static List <VBD> vbdLayer;

    private List <BSCLayer> bscLayer;

    private List <VRD> vrdLayer;

    public MainLogic () {
        this.vbdLayer = new ArrayList<VBD>();

        this.bscLayer = new ArrayList<BSCLayer>();

        this.vrdLayer = new ArrayList<VRD>();

//        VBDManager vbdManager = new VBDManager ();
        BTSManager btsManager = new BTSManager ();
        BSCManager bscManager = new BSCManager ();

        // stan uruchomieniowy:

        bscLayer.add(new BSCLayer());

    }

    public static VBD addVBD (Message message) { // wywoływane przez UI
        VBD vbd = new VBD(message);
        vbdLayer.add(vbd);
        return vbd;
    }

    void addBTS1 (BTS bts) { // wywoływane przez BTSManager
//        btsLayerBegin.add(bts);
    }

    void addBSCLayer (BSCLayer bscLayer) { // wywoływane przez BSCManager
        this.bscLayer.add(bscLayer);
    }

    void addBTS2 (BTS bts) { // wywoływane przez BTSManager
//        btsLayerEnd.add(bts);
    }

    void addVRD (VRD vrd) { // wywoływane przez UI
        vrdLayer.add(vrd);
    }
}
