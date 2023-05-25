package UI;

import Handlers.BTSListener;
import Handlers.UpdateStationPanelUIEvent;
import Logic.BTSLayer;

import javax.swing.*;
import java.awt.*;

public class BTSLayerUI extends JPanel implements BTSListener {

    private JPanel stationsContainer;
    private JScrollPane scroll;
    private int LayerNumber;

    public BTSLayerUI (BTSLayer layer) {

        setPreferredSize(new Dimension(150, 480));
//        setBorder(BorderFactory.createTitledBorder("BTS Layer"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        stationsContainer = new JPanel();
        stationsContainer.setLayout(new BoxLayout(stationsContainer, BoxLayout.PAGE_AXIS));


        scroll = new JScrollPane(stationsContainer);
        scroll.setBorder(BorderFactory.createTitledBorder("BTS Layer " + layer.getLayerNumber()));
        add(scroll, BorderLayout.CENTER);

        LayerNumber = layer.getLayerNumber();

    }

    @Override
    public void AddNewBTSPanelUI (BTSPanelUI ui) {
//        System.out.println("AddNewBTSPanelUI");
        SwingUtilities.invokeLater(() -> stationsContainer.add(ui));
        SwingUtilities.invokeLater(() -> stationsContainer.revalidate());
        SwingUtilities.invokeLater(() -> stationsContainer.repaint());
        SwingUtilities.invokeLater(() -> scroll.repaint());

        revalidate();
        repaint();
    }

    public int getLayerNumber () {
        return LayerNumber;
    }

    //    public void addNewPanel (BTS bts) {
//        SwingUtilities.invokeLater(() -> stationsContainer.add(bts.panel));
//        SwingUtilities.invokeLater(() -> stationsContainer.revalidate());
//        SwingUtilities.invokeLater(() -> stationsContainer.repaint());
//
//        Thread btsThread = new Thread(bts);
//        btsThread.start();
////            revalidate();
////            repaint();
//    }

    @Override
    public void AddNewBTSLayerUI (BTSLayerUI ui) {

    }



    @Override
    public void updateBTSPanel (UpdateStationPanelUIEvent evt) {

    }
}
