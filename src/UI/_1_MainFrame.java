package UI;

import javax.swing.*;
import java.awt.*;

public class _1_MainFrame extends JFrame {

    public _1_MainFrame () throws HeadlessException {
        setTitle("Symulator SMS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 600);

        _2_MainPanel mainPanel = new _2_MainPanel();
        add(mainPanel);
        setVisible(true);
    }


}
