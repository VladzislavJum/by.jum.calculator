package by.jum.calculator.gui;

import by.jum.calculator.constants.Names;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;


public class TrigonometricButtons {
    private List<JButton> trigonometricList;

    public JPanel addTrigonometricPanel() {
        JPanel trigonometricPanel = new JPanel();

        trigonometricList = new ArrayList<JButton>();
        trigonometricPanel.setVisible(false);
        trigonometricPanel.setLayout(new GridLayout(9, 1));

        trigonometricList.add(new JButton(Names.PI.getName()));
        trigonometricList.add(new JButton(Names.SIN.getName()));
        trigonometricList.add(new JButton(Names.COS.getName()));
        trigonometricList.add(new JButton(Names.SINH.getName()));
        trigonometricList.add(new JButton(Names.COSH.getName()));
        trigonometricList.add(new JButton(Names.TG.getName()));
        trigonometricList.add(new JButton(Names.CTG.getName()));
        trigonometricList.add(new JButton(Names.TGH.getName()));
        trigonometricList.add(new JButton(Names.CTGH.getName()));

        for (JButton button : trigonometricList) {
            button.setFont(new Font("Calibri", Font.BOLD, 20));

            trigonometricPanel.add(button);
        }

        return trigonometricPanel;
    }

    public List<JButton> getTrigonometricList() {
        return trigonometricList;
    }
}
