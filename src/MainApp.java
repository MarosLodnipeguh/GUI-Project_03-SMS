import Application.MainLogic;
import UI._1_MainFrame;

import javax.swing.*;

public class MainApp {

    public static void main (String[] args) {

        // Tworzenie instancji klasy MainFrame w wątku zdarzeń
        SwingUtilities.invokeLater(_1_MainFrame::new);

        MainLogic logic = new MainLogic();




    }
}
