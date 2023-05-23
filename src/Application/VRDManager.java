package Application;

import java.util.ArrayList;
import java.util.List;

public class VRDManager {

    private static List<VRD> vrdList;

    public VRDManager () {
        vrdList = new ArrayList<VRD>();


    }

    public static void addVRD (VRD vrd) {
        vrdList.add(vrd);
    }

    public static VRD getVRD (String number) {
        for (VRD vrd : vrdList) {
            if (vrd.getNumber().equals(number)) {
                return vrd;
            }
        }
        return null;
    }
}
