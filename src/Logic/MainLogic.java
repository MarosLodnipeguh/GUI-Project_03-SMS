package Logic;

import Handlers.VBDListener;
import Handlers.VRDListener;
import UI.VBDPanelUI;
import UI.VRDPanelUI;

import java.util.ArrayList;
import java.util.List;

public class MainLogic implements VBDListener, VRDListener {
    
    private List <VBD> VBDs;
    private VBDListener vbdListener;

    private BTSManager btsManager;
    private BSCManager bscManager;

    private static List <VRD> VRDs;
    private VRDListener vrdListener;


    public MainLogic () {
        this.VBDs = new ArrayList<VBD>();

        btsManager = new BTSManager ();

        bscManager = new BSCManager ();

        this.VRDs = new ArrayList<VRD>();


    }

    // ======================================================= VBD =======================================================
    @Override
    public void AddNewVBD (String messageText) {
        VBD vbd = new VBD(messageText);
        VBDs.add(vbd);

        //UI:
        VBDPanelUI ui = new VBDPanelUI(vbd);
        ui.setLogicListener(this);
        AddNewVBDPanelUI(ui);

        Thread vbdThread = new Thread(vbd);
        vbdThread.start();
    }
    
    @Override
    public void AddNewVBDPanelUI (VBDPanelUI ui) {
        vbdListener.AddNewVBDPanelUI(ui);
    }

    @Override
    public void RemoveVBD (VBD vbd) {
        vbd.stopVBD();
        VBDs.remove(vbd);
    }

    // ======================================================= VRD =======================================================
    @Override
    public void AddNewVRD () {
        VRD vrd = new VRD();
        VRDs.add(vrd);

        //UI:
        VRDPanelUI ui = new VRDPanelUI(vrd);
        vrd.setListener(ui);
        ui.setLogicListener(this);
        AddNewVRDPanelUI(ui);

        Thread VRDThread = new Thread(vrd);
        VRDThread.start();
    }
    
    @Override
    public void AddNewVRDPanelUI (VRDPanelUI ui) {
        vrdListener.AddNewVRDPanelUI(ui);
    }

    @Override
    public void RemoveVRD (VRD vrd) {
        vrd.stopVRD();
        VRDs.remove(vrd);
    }

    


    public BTSManager getBtsManager () {
        return btsManager;
    }

    public BSCManager getBscManager () {
        return bscManager;
    }

    public void setVbdListener (VBDListener vbdListener) {
        this.vbdListener = vbdListener;
    }

    public void setVrdListener (VRDListener vrdListener) {
        this.vrdListener = vrdListener;
    }

    public static VRD getVRD (String number) {
        for (VRD vrd : VRDs) {
            if (vrd.getNumber().equals(number)) {
                return vrd;
            }
        }
        return null;
    }
    
    // ======================================================= UNUSED =======================================================

    

    @Override
    public void UpdateVRDPanelUI (int receivedCount) {

    }

    

    @Override
    public void RemoveVBDPanelUI (VBDPanelUI ui) {

    }

    

    @Override
    public void RemoveVRDPanelUI (VRDPanelUI ui) {

    }
}
