package UI;

import Application.MainLogic;
import Application.VBD;
import Listeners.VBDListener;
import SMS.Message;
import SMS.PhoneBookLogic;
import SMS.NullRecipentException;
import Listeners.AddVBDEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//import static Application.MainLogic.addToVBDLayer;


// VBD SENDER DEVICE PANEL - LEFT SIDE
public class _3_SenderPanel extends JPanel /* extends DevicePanel */ implements VBDListener, MouseListener, ActionListener {

    private JScrollPane scroll;
    private JPanel devicesContainer;
    private JButton addButton;

    public _3_SenderPanel () {
        setLayout(new BorderLayout());


        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    String text = getMessageText();
                    testAddVBD(text);

                } catch (NullRecipentException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(addButton, BorderLayout.SOUTH);
        
        devicesContainer = new JPanel();
        devicesContainer.setLayout(new BoxLayout(devicesContainer, BoxLayout.Y_AXIS));

        scroll = new JScrollPane(devicesContainer);
        add(scroll);

    }
    private String getMessageText () throws NullRecipentException {
        String text = JOptionPane.showInputDialog("Enter a message:");
        if (text != null && !text.isEmpty()) {
            return text;
            
            // 1. get rand number
            // 2. get rand recipent
            // 3. create mess with rand number and recipent
            // 4. create vbd with mess



//            VBD vbd = MainLogic.addVBD(this, message);
//            addVBDPanel(vbd);
            
//            devicesContainer.revalidate();
//            devicesContainer.repaint();

//            VBDPanel vbdUI = new VBDPanel(vbd);
//            addVBDPanel(vbdUI);
        }
        return null;
    }

    // TESTING:
    public void testAddVBD (String text) {
        String senderNumber = PhoneBookLogic.generateNumber();
        String recipentNumber = PhoneBookLogic.getRandomRecipent();

        Message message = new Message(senderNumber, recipentNumber, text);
//        System.out.println("Created message: " + message.toString());

        VBD testVBD = new VBD(message);
//        addToVBDLayer(testVBD);

        VBDPanel vbdUI = new VBDPanel(testVBD);
        devicesContainer.add(vbdUI);

//        devicesContainer.add(testVBD.panel);

//        devicesContainer.revalidate();
//        devicesContainer.repaint();
        revalidate();
        repaint();

//        testVBD.run();  TAK NIE ROBIĆ
        Thread vbdThread = new Thread(testVBD);
        vbdThread.start();
    }

//    public void addVBDPanel (VBD vbd) {
//        VBDPanel vbdUI = new VBDPanel(vbd);
//        devicesContainer.add(vbdUI);
//        devicesContainer.revalidate();
//        devicesContainer.repaint();
//        revalidate();
//        repaint();
//    }

    @Override
    public void addVBDPanel (VBD vbd) {
        VBDPanel vbdUI = new VBDPanel(vbd);
        devicesContainer.add(vbdUI);
//        vbd.run();
        devicesContainer.revalidate();
        devicesContainer.repaint();
        revalidate();
        repaint();
    }

//    public void addVBDPanel(VBDPanel p) {
//        devicesContainer.add(p);
//        devicesContainer.revalidate();
//        devicesContainer.repaint();
//        revalidate();
//        repaint();
//    }


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
