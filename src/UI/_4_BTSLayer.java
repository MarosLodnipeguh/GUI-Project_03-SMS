package UI;

import Application.BTS;
import Application.BTSManager;

import javax.swing.*;
import java.awt.*;

public class _4_BTSLayer extends JPanel {

    public _4_BTSLayer () {

        setPreferredSize(new Dimension(130, 200));
        setBorder(BorderFactory.createTitledBorder("BTS Layer"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



//        new BTSPanel(BTSManager.addLayer());
        new BTSPanel(new BTS());
    }
}
