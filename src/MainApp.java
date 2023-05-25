import Logic.MainLogic;
import UI.MainFrame;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class MainApp {

    public static void main (String[] args) throws InterruptedException, InvocationTargetException {



//        _1_MainFrame graphics = new _1_MainFrame();
//        SwingUtilities.invokeLater((Runnable) graphics);

//        SwingUtilities.invokeAndWait(_1_MainFrame::new);

        SwingUtilities.invokeAndWait(() -> {



            MainLogic logic = new MainLogic();


            MainFrame graphics = new MainFrame();

            logic.setVbdListener(graphics.getSender());
            logic.getBscManager().setListener(graphics.getStations());
            logic.getBtsManager().setListener(graphics.getStations());
            logic.setVrdListener(graphics.getReceiver());


            graphics.getSender().setListener(logic);
            graphics.getStations().setBSClistener(logic.getBscManager());
            graphics.getReceiver().setListener(logic);

            // stan uruchomieniowy:
            logic.getBtsManager().NewBTSLayer();
            logic.getBscManager().AddNewBSCLayer();
            logic.getBtsManager().NewBTSLayer();



//            graphics.getStations().refresh();





//            logic.setListenerForUI();

//            SwingUtilities.invokeLater(() -> graphics.repaint());
//            SwingUtilities.invokeLater(() -> graphics.revalidate());

        });






//        SwingUtilities.invokeAndWait();


    }
}





