package by.jum.calculator;

import by.jum.calculator.gui.CalculatorUI;

import javax.swing.SwingUtilities;

/**
 * Created by Vlad on 10.06.2015.
 */
public class Runner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CalculatorUI calculatorUI = new CalculatorUI();
                calculatorUI.createMainWindow();
                calculatorUI.createMenu();
                calculatorUI.addScoreboard();
                calculatorUI.addTree();
                calculatorUI.addButtonListeners();
            }
        });
    }
}
