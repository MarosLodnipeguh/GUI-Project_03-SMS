package UI;

import Application.BSC;

import javax.swing.*;
import java.awt.*;

public class BSCPanel extends JPanel {
    private JLabel numberLabel;
    private JLabel processedMessagesNumber;
    private JLabel waitingMessagesNumber;

    public BSCPanel (BSC bsc) {
        setPreferredSize(new Dimension(130, 75));
        setBorder(BorderFactory.createTitledBorder("BSC"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // ===================================== CONTENT ===================================== //
        numberLabel = new JLabel("Station ID: " + bsc.getId());
        processedMessagesNumber = new JLabel("Processed: " + bsc.getProcessedMessages());
        waitingMessagesNumber = new JLabel("Waiting: " + bsc.getWaitingMessages());

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
