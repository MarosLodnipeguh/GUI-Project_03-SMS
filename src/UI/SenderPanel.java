package UI;

import Logic.VBD;
import Handlers.VBDListener;

import javax.swing.*;
import java.awt.*;

// VBD SENDER DEVICE PANEL - LEFT SIDE
public class SenderPanel extends JPanel implements VBDListener {

    private JScrollPane scroll;
    private JPanel devicesContainer;
    private JButton addButton;
    private VBDListener listener;

    public SenderPanel () {
        setLayout(new BorderLayout());


        addButton = new JButton("Add");
        addButton.addActionListener(e ->  {
            String text = getMessageText();
            AddNewVBD(text);
        });
        add(addButton, BorderLayout.SOUTH);
        
        devicesContainer = new JPanel();
        devicesContainer.setLayout(new BoxLayout(devicesContainer, BoxLayout.Y_AXIS));

        scroll = new JScrollPane(devicesContainer);
        add(scroll);


    }
    private String getMessageText () {
        String text = JOptionPane.showInputDialog("Enter a message:");
        if (text != null && !text.isEmpty()) {
            return text;
        }
        return null;
    }

    @Override
    public void AddNewVBD (String messageText) {
        listener.AddNewVBD(messageText);
    }




    @Override
    public void AddNewVBDPanelUI (VBDPanelUI ui) {
        ui.setUIListener(this);
        devicesContainer.add(ui);
        devicesContainer.revalidate();
        devicesContainer.repaint();

        revalidate();
        repaint();
    }

    @Override
    public void RemoveVBDPanelUI (VBDPanelUI ui) {
        devicesContainer.remove(ui);

        revalidate();
        repaint();
    }



    public void setListener (VBDListener listener) {
        this.listener = listener;
    }


    @Override
    public void RemoveVBD (VBD vbd) {

    }





}
