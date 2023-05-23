package UI;

import Application.VBD;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class VBDPanel extends JPanel {
    private JLabel numberLabel;
    private JTextField deviceNumberTextField;
    private JComboBox<String> stateComboBox;
    private JLabel frequencyLabel;
    private JSlider frequencySlider;
    private JButton stopButton;



    public VBDPanel (VBD vbd) {
        setPreferredSize(new Dimension(210, 160));
        setBorder(BorderFactory.createTitledBorder("VBD"));


        // ===================================== CONTENT ===================================== //
        numberLabel = new JLabel("Number: ");
        // ===================================== NUMBER FIELD ===================================== //
        deviceNumberTextField = new JTextField(vbd.getNumber());
        deviceNumberTextField.setEditable(false);

        // ===================================== COMBO BOX ===================================== //
        stateComboBox = new JComboBox<>(new String[]{"WAITING", "ACTIVE"});

        stateComboBox.addActionListener(e -> {
            String selectedOption = (String) stateComboBox.getSelectedItem();
            if (selectedOption.equals("ACTIVE")) {
                vbd.setSending(true);
            } else if (selectedOption.equals("WAITING")) {
                vbd.setSending(false);
            }
        });

        // ===================================== SLIDER ===================================== //
        frequencyLabel = new JLabel("Frequency of sending: 5s");

        //Slider w ms będzie 100ms = 0.1s, 10000ms = 10s

        frequencySlider = new JSlider(100, 10000, 5000);
        frequencySlider.setMajorTickSpacing(10000); // Ustawienie większych kroków
        frequencySlider.setMinorTickSpacing(1000); // Ustawienie mniejszych kroków
        frequencySlider.setPaintTicks(true);
        frequencySlider.setPaintLabels(true);

        // Utworzenie opisów przedziału dla JSlider
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(100, new JLabel("0.1s"));
        labelTable.put(3000, new JLabel("3s"));
        labelTable.put(5000, new JLabel("5s"));
        labelTable.put(7000, new JLabel("7s"));
        labelTable.put(10000, new JLabel("10s"));
        frequencySlider.setLabelTable(labelTable);

        // Dodawanie słuchacza zmiany wartości JSlider
        frequencySlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                double value = source.getValue();
                frequencyLabel.setText("Frequency of sending: " + value/1000 + "s");
            }
        });
        // ===================================== STOP BUTTON ===================================== //
        stopButton = new JButton("Remove VBD");


        add(numberLabel);
        add(deviceNumberTextField);
        add(stateComboBox);
        add(frequencyLabel);
        add(frequencySlider);
        add(stopButton);

    }
}
