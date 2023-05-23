package UI;

import Application.VRD;
import Listeners.VRDListener;
import SMS.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// VRD RECEIVER DEVICE PANEL - RIGHT SIDE
public class _3_ReceiverPanel extends JPanel /* extends DevicePanel */ implements VRDListener, MouseListener, ActionListener {

    private JScrollPane scroll;
    private JPanel devicesContainer;
    private JButton addButton;

    public _3_ReceiverPanel () {
        setLayout(new BorderLayout());


        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                addVRD();
            }
        });
        add(addButton, BorderLayout.SOUTH);

        devicesContainer = new JPanel();
        devicesContainer.setLayout(new BoxLayout(devicesContainer, BoxLayout.Y_AXIS));
//        devicesContainer.setBackground(Color.decode("#F5EFE6"));
//        devicesContainer.add(new VRDPanel("random"));


        scroll = new JScrollPane(devicesContainer);
//        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        add(scroll);

    }

    public void addVRD() {

        VRD vrd = new VRD();


        devicesContainer.add(vrd.panel);


        revalidate();
        repaint();

        Thread vrdThread = new Thread(vrd);
        vrdThread.start();
    }


    @Override
    public void onMessageReceived (VRD vrd, Message message) {

    }

    @Override
    public void onVRDRemoved (VRD vrd) {

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
