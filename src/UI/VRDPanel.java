package UI;

import Application.VRD;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class VRDPanel extends JPanel {
//    private JLabel numberLabel;
//    private JTextField deviceNumberTextField;
    private JLabel receivedMessagesNumber;
    private JCheckBox autoMessageDelete;
    private JButton stopButton;



    public VRDPanel (VRD vrd) {
        setPreferredSize(new Dimension(210, 110));
        setBorder(BorderFactory.createTitledBorder("VRD"));


        // ===================================== CONTENT ===================================== //
//        numberLabel = new JLabel("Number: ");
//        deviceNumberTextField = new JTextField(deviceNumber);
//        deviceNumberTextField.setEditable(false);
        // ===================================== CHECK BOX ===================================== //
        autoMessageDelete = new JCheckBox("Auto delete messages (10s)");

        autoMessageDelete.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (autoMessageDelete.isSelected()) {

//                    int value = 100000;
//
////                    autoMessageDelete.setText("Auto delete messages No. every 10s");
//                    receivedMessagesNumber.setText("Received messages: " + value);

                } else {

//                    int value = 0;
//
////                    autoMessageDelete.setText("Don't auto delete");
//                    receivedMessagesNumber.setText("Received messages: " + value);

                }
            }
        });

        // ===================================== RECEIVED NUMBER ===================================== //
        receivedMessagesNumber = new JLabel("Received messages: " + vrd.receivedMessagesCount());
        // ===================================== STOP BUTTON ===================================== //
        stopButton = new JButton("Remove VRD");


//        add(numberLabel);
//        add(deviceNumberTextField);
        add(receivedMessagesNumber);
        add(autoMessageDelete);
        add(stopButton);

    }

    public void updateReceivedMessagesNumber (int count) {
        SwingUtilities.invokeLater(() -> receivedMessagesNumber.setText("Received messages: " + count));
    }
}
