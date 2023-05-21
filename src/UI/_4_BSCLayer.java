package UI;

import javax.swing.*;
import java.awt.*;

public class _4_BSCLayer extends JPanel {

    public _4_BSCLayer () {

        setPreferredSize(new Dimension(160, 474));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        setBorder(BorderFactory.createTitledBorder("BSC Layer"));



        JPanel stationsContainer = new JPanel();
        stationsContainer.setLayout(new BoxLayout(stationsContainer, BoxLayout.PAGE_AXIS));
        stationsContainer.add(new BSCPanel("2"));
//        stationsContainer.add(new BSCPanel("2"));
//        stationsContainer.add(new BSCPanel("2"));
//        stationsContainer.add(new BSCPanel("2"));
//        stationsContainer.add(new BSCPanel("2"));
//        stationsContainer.add(new BSCPanel("2"));
//        stationsContainer.add(new BSCPanel("2"));

        JScrollPane scroll = new JScrollPane(stationsContainer);
        scroll.setBorder(BorderFactory.createTitledBorder("BSC Layer"));
        add(scroll, BorderLayout.CENTER);


    }
}