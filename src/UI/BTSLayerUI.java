package UI;

import Application.BTS;
import Application.BTSLayer;

import javax.swing.*;
import java.awt.*;

public class BTSLayerUI extends JPanel {

    private JPanel stationsContainer;
    public BTSLayerUI (BTSLayer layer) {

        setPreferredSize(new Dimension(130, 200));
//        setBorder(BorderFactory.createTitledBorder("BTS Layer"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        stationsContainer = new JPanel();
        stationsContainer.setLayout(new BoxLayout(stationsContainer, BoxLayout.PAGE_AXIS));


        JScrollPane scroll = new JScrollPane(stationsContainer);
        scroll.setBorder(BorderFactory.createTitledBorder("BTS Layer"));
        add(scroll, BorderLayout.CENTER);

    }

    public void addNewPanel (BTS bts) {
        SwingUtilities.invokeLater(() -> stationsContainer.add(bts.panel));
        SwingUtilities.invokeLater(() -> stationsContainer.revalidate());
        SwingUtilities.invokeLater(() -> stationsContainer.repaint());

        Thread btsThread = new Thread(bts);
        btsThread.start();
//            revalidate();
//            repaint();
    }

}
