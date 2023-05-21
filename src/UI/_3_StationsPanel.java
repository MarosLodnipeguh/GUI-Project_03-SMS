package UI;

import Application.BSC;
import Application.BTS;
import Listeners.BSCListener;
import Listeners.BTSListener;
import SMS.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// BTS & BSC CONTROLLER STATIONS PANEL - MIDDLE
public class _3_StationsPanel extends JPanel implements BTSListener, BSCListener, MouseListener, ActionListener {

    private JScrollPane scroll;
    private JPanel layersContainer;
    private JPanel BSCLayersContainer;
    private JPanel buttonPanel;
    private JButton addButton;
    private JButton removeButton;

    public _3_StationsPanel () {
        setLayout(new BorderLayout());


        addButton = new JButton("Add Layer");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                addLayer();
            }
        });


        removeButton = new JButton("Remove Layer");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                removeLayer();
            }
        });


        layersContainer = new JPanel();
        layersContainer.setLayout(new BoxLayout(layersContainer, BoxLayout.X_AXIS));
//        layersContainer.setBackground(Color.decode("#AEBDCA"));

        BSCLayersContainer = new JPanel();
//        BSCLayersContainer.setBackground(Color.decode("#AEBDCA"));
        BSCLayersContainer.add(new _4_BSCLayer());
        JScrollPane BSCscroll = new JScrollPane(BSCLayersContainer);

        layersContainer.add(new _4_BTSLayer());
        layersContainer.add(BSCscroll);
        layersContainer.add(new _4_BTSLayer());

//        scroll = new JScrollPane(layersContainer);


        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

//        add(scroll, BorderLayout.CENTER);
        add(layersContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    public void addLayer() {
        _4_BSCLayer layer = new _4_BSCLayer();

        BSCLayersContainer.add(layer, getComponentCount()-1);
        revalidate();
        repaint();
    }

    public void removeLayer() {

        revalidate();
        repaint();
    }



    @Override
    public void onMessageReceived (BSC bsc, Message message) {

    }

    @Override
    public void onMessageProcessed (BSC bsc, Message message) {

    }

    @Override
    public void onMessageReceived (BTS bts, Message message) {

    }

    @Override
    public void onMessageProcessed (BTS bts, Message message) {

    }

    @Override
    public void actionPerformed (ActionEvent e) {

    }

    @Override
    public void mouseClicked (MouseEvent e) {

    }

    @Override
    public void mousePressed (MouseEvent e) {

    }

    @Override
    public void mouseReleased (MouseEvent e) {

    }

    @Override
    public void mouseEntered (MouseEvent e) {

    }

    @Override
    public void mouseExited (MouseEvent e) {

    }
}
