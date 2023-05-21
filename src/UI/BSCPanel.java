package UI;

import javax.swing.*;
import java.awt.*;

public class BSCPanel extends JPanel {
    private JLabel numberLabel;
    private JLabel processedMessagesNumber;
    private JLabel waitingMessagesNumber;

    public BSCPanel (String stationNumber) {
        setPreferredSize(new Dimension(130, 75));
        setBorder(BorderFactory.createTitledBorder("BSC"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // ===================================== CONTENT ===================================== //
        numberLabel = new JLabel("Station Number: " + stationNumber);
        processedMessagesNumber = new JLabel("Processed: 100000");
        waitingMessagesNumber = new JLabel("Waiting: 0");

        add(numberLabel);
        add(processedMessagesNumber);
        add(waitingMessagesNumber);


    }
}
