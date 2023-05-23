package Listeners;

import Application.VBD;

import java.awt.*;
import java.util.EventObject;

public
class AddVBDEvent extends EventObject {

    private VBD vbd;


    public AddVBDEvent (Object source, VBD vbd) {
        super(source);
        this.vbd = vbd;
    }

    public VBD getVBD() {
        return vbd;
    }

}
