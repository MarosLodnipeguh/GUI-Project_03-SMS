package Logic;

import Handlers.VBDListener;
import Handlers.VRDListener;
import UI.VBDPanelUI;
import UI.VRDPanelUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainLogic implements VBDListener, VRDListener {
    
    private final List <VBD> VBDs;
    private VBDListener vbdListener;

    private final BTSManager btsManager;
    private final BSCManager bscManager;

    private static List <VRD> VRDs;
    private VRDListener vrdListener;


    public MainLogic () {
        this.VBDs = new CopyOnWriteArrayList<VBD>();

        btsManager = new BTSManager ();

        bscManager = new BSCManager ();

        this.VRDs = new CopyOnWriteArrayList<VRD>();

    }

    // ======================================================= VBD =======================================================
    @Override
    public /*synchronized*/ void AddNewVBD (String messageText) {
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
    public /*synchronized*/ void RemoveVBD (VBD vbd) {
        vbd.stopVBD();
        VBDs.remove(vbd);
    }

    // ======================================================= VRD =======================================================
    @Override
    public /*synchronized*/ void AddNewVRD () {
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
    public /*synchronized*/ void RemoveVRD (VRD vrd) {
        vrd.stopVRD();
        VRDs.remove(vrd);
    }

    


    public BTSManager getBtsManager () {
        return btsManager;
    }

    public BSCManager getBscManager () {
        return bscManager;
    }

    public /*synchronized*/ void setVbdListener (VBDListener vbdListener) {
        this.vbdListener = vbdListener;
    }

    public /*synchronized*/ void setVrdListener (VRDListener vrdListener) {
        this.vrdListener = vrdListener;
    }

    public static VRD getVRD (int number) {
        for (VRD vrd : VRDs) {
            if (vrd.getNumber() == number) {
                return vrd;
            }
        }
        return null;
    }

    public void stopAllThreads () {

        for (VBD vbd : VBDs) {
            vbd.stopVBD();
        }

        btsManager.stopAllLayers();

        bscManager.stopAllLayers();

        for (VRD vrd : VRDs) {
            vrd.stopVRD();
        }
    }


    public void writeVBDsDataToFile () {

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String filename = "VBDs Dump " + formattedDateTime + ".bin";
        for (VBD vbd : VBDs) {
            vbd.writeAllMessagesToFile(filename);
        }
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
