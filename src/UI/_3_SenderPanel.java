package UI;

import Application.MainLogic;
import Application.VBD;
import Listeners.VBDListener;
import SMS.Message;
import SMS.PhoneBookLogic;
import SMS.NullRecipentException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


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
                    showMessageInput();
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
    private void showMessageInput () throws NullRecipentException {
        String text = JOptionPane.showInputDialog("Enter a message:");
        if (text != null && !text.isEmpty()) {



            // VBD vbd = new VBD(message, true, 1);
            // vbd.start();
            // vbd.addVBDListener(this);



            // 1. get rand number
            // 2. get rand recipent
            // 3. create mess with rand number and recipent
            // 4. create vbd with mess

            String senderNumber = PhoneBookLogic.generateNumber();
            String recipentNumber = PhoneBookLogic.getRandomRecipent();

            Message message = new Message(senderNumber, recipentNumber, text);

            VBD vbd = MainLogic.addVBD(message);

            VBDPanel vbdUI = new VBDPanel(vbd);
            addVBDPanel(vbdUI);
        }
    }

    public void addVBDPanel(VBDPanel p) {
        devicesContainer.add(p);
        revalidate();
        repaint();
    }


    @Override
    public void onMessageSent (VBD vbd, Message message) {
        // nowy thread VBD
    }

    @Override
    public void onVBDRemoved (VBD vbd) {

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
