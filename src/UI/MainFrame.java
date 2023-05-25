package UI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    MainPanel mainPanel;

    SenderPanel sender;
    StationsPanel stations;
    ReceiverPanel receiver;


    public MainFrame () throws HeadlessException {
        setTitle("Symulator SMS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 600);

        mainPanel = new MainPanel();

        add(mainPanel);
        setVisible(true);


        sender = mainPanel.getSender();
        stations = mainPanel.getStations();
        receiver = mainPanel.getReceiver();
    }



    public SenderPanel getSender () {
        return sender;
    }

    public StationsPanel getStations () {
        return stations;
    }

    public ReceiverPanel getReceiver () {
        return receiver;
    }
}
