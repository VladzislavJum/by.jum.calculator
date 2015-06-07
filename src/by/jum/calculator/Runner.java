package by.jum.calculator;

import by.jum.calculator.gui.CalculatorUI;

import javax.swing.SwingUtilities;

public class Runner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CalculatorUI mainWindow = new CalculatorUI();
                mainWindow.createMainWindow();
                mainWindow.createMenu();
                mainWindow.addScoreboard();
                mainWindow.addTree();
            }
        });
    }
}
