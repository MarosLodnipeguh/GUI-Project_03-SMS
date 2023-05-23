import Application.MainLogic;
import UI._1_MainFrame;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class MainApp {

    public static void main (String[] args) throws InterruptedException, InvocationTargetException {




        new MainLogic();

        SwingUtilities.invokeAndWait(_1_MainFrame::new);



//        SwingUtilities.invokeLater(new _1_MainFrame() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
//
//    private static void createAndShowGUI() {
//        // Tworzenie i konfiguracja interfejsu użytkownika
//
//        // Pętla główna, działająca w osobnym wątku
//        Thread loopThread = new Thread(new _1_MainFrame() {
//            public void run() {
//                while (true) {
//                    // Aktualizacja danych interfejsu użytkownika
//
//                    // Odświeżenie interfejsu w EDT
//                    SwingUtilities.invokeLater(new _1_MainFrame() {
//                        public void run() {
//                            // Aktualizacja komponentów interfejsu
//                        }
//                    });
//
//                    // Poczekaj na chwilę przed następną iteracją pętli
//                    try {
//                        Thread.sleep(1000); // Przykładowy czas oczekiwania
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        loopThread.start();
    }
}





