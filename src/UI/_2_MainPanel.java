package UI;

import javax.swing.*;
import java.awt.*;

public class _2_MainPanel extends JPanel {
    private _3_SenderPanel sender;
    private _3_StationsPanel stations;
    private _3_ReceiverPanel receiver;

    public _2_MainPanel () {
        setLayout(new BorderLayout());

        sender = new _3_SenderPanel();
//        sender.setBackground(Color.decode("#F5EFE6"));
        sender.setBorder(BorderFactory.createTitledBorder("Send"));
        sender.setPreferredSize(new Dimension(260, 600));

        stations = new _3_StationsPanel();
//        stations.setLayout(new GridLayout(1, 2)); //GridLayout o 1 wierszu i 2 kolumnach, aby umieścić w nim stacje bazowe i kontrolery w dwóch kolumnach.
//        stations.setBackground(Color.decode("#AEBDCA"));
        stations.setBorder(BorderFactory.createTitledBorder("Controller Stations"));
//        stations.setPreferredSize(new Dimension(600, 600));

        receiver = new _3_ReceiverPanel();
//        receiver.setBackground(Color.decode("#F5EFE6"));
        receiver.setBorder(BorderFactory.createTitledBorder("Receive"));
        receiver.setPreferredSize(new Dimension(260, 600));

        add(sender, BorderLayout.WEST);
        add(stations, BorderLayout.CENTER);
        add(receiver, BorderLayout.EAST);
    }
}