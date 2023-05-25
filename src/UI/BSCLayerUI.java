package UI;

import Logic.BSCLayer;
import Handlers.BSCListener;
import Handlers.UpdateStationPanelUIEvent;

import javax.swing.*;
import java.awt.*;

public class BSCLayerUI extends JPanel implements BSCListener {

    private JPanel stationsContainer;
    JScrollPane scroll;
    private BSCLayer bscLayer;

    public BSCLayerUI (BSCLayer layer) {

        bscLayer = layer;

        setPreferredSize(new Dimension(130, 474));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        stationsContainer = new JPanel();
        stationsContainer.setLayout(new BoxLayout(stationsContainer, BoxLayout.PAGE_AXIS));

        scroll = new JScrollPane(stationsContainer);
        scroll.setBorder(BorderFactory.createTitledBorder("BSC Layer " + layer.getLayerNumber()));
        add(scroll, BorderLayout.CENTER);


    }

    @Override
    public void AddNewBSCPanelUI (BSCPanelUI ui) {
//        System.out.println("AddNewBSCPanelUI");
        SwingUtilities.invokeLater(() -> stationsContainer.add(ui));
        SwingUtilities.invokeLater(() -> stationsContainer.revalidate());
        SwingUtilities.invokeLater(() -> stationsContainer.repaint());
        SwingUtilities.invokeLater(() -> scroll.repaint());

        revalidate();
        repaint();
    }




//    public void addNewPanel (BSC bsc) {
////        SwingUtilities.invokeLater(() -> stationsContainer.add(bsc.panel));
//        SwingUtilities.invokeLater(() -> stationsContainer.add(new BSCPanelUI(bsc)));
//        SwingUtilities.invokeLater(() -> stationsContainer.revalidate());
//        SwingUtilities.invokeLater(() -> stationsContainer.repaint());
//
//        Thread btsThread = new Thread(bsc);
//        btsThread.start();
////            revalidate();
////            repaint();
//    }







    @Override
    public void updateBSCPanel (UpdateStationPanelUIEvent evt) {

    }

    @Override
    public void AddNewBSCLayer () {

    }

    @Override
    public void RemoveLastBSCLayer () {

    }
    @Override
    public void AddNewBSCLayerUI (BSCLayerUI ui) {

    }
}