package UI;

import Application.BSC;
import Application.BSCLayer;

import javax.swing.*;
import java.awt.*;

public class BSCLayerUI extends JPanel {

    private JPanel stationsContainer;

    public BSCLayerUI (BSCLayer layer) {

        setPreferredSize(new Dimension(160, 474));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        stationsContainer = new JPanel();
        stationsContainer.setLayout(new BoxLayout(stationsContainer, BoxLayout.PAGE_AXIS));

        JScrollPane scroll = new JScrollPane(stationsContainer);
        scroll.setBorder(BorderFactory.createTitledBorder("BSC Layer"));
        add(scroll, BorderLayout.CENTER);


    }

    public void addNewPanel (BSC bsc) {
        SwingUtilities.invokeLater(() -> stationsContainer.add(bsc.panel));
        SwingUtilities.invokeLater(() -> stationsContainer.revalidate());
        SwingUtilities.invokeLater(() -> stationsContainer.repaint());

        Thread btsThread = new Thread(bsc);
        btsThread.start();
//            revalidate();
//            repaint();
    }

}