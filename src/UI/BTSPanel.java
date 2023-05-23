package UI;

import Application.BTS;

import javax.swing.*;
import java.awt.*;

public class BTSPanel extends JPanel {
    private JLabel numberLabel;
    private JLabel processedMessagesNumber;
    private JLabel waitingMessagesNumber;

    public BTSPanel (BTS bts) {
        setPreferredSize(new Dimension(150, 75));
        setBorder(BorderFactory.createTitledBorder("BTS"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // ===================================== CONTENT ===================================== //
        numberLabel = new JLabel("Station ID: " + bts.getId());
        processedMessagesNumber = new JLabel("Processed: " + bts.getProcessedMessages());
        waitingMessagesNumber = new JLabel("Waiting: " + bts.getWaitingMessages());

        add(numberLabel);
        add(processedMessagesNumber);
        add(waitingMessagesNumber);

    }

    public void updateProcessedMessagesNumber (int processedMessages) {
        SwingUtilities.invokeLater(() -> processedMessagesNumber.setText("Processed: " + processedMessages));
    }

    public void updateWaitingMessagesNumber (int waitingMessages) {
        SwingUtilities.invokeLater(() -> waitingMessagesNumber.setText("Waiting: " + waitingMessages));
    }
}
