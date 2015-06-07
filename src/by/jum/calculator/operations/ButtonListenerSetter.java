package by.jum.calculator.operations;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ButtonListenerSetter {
    private List<JButton> numberList;
    private List<JButton> buttonList;
    private TextWriter textWriter;
    private List<JButton> trigonometricButtonList;

    public ButtonListenerSetter(List<JButton> numberList, List<JButton> buttonList, List<JButton> trigonometricButtonList, JTextField textField) {
        this.numberList = numberList;
        this.buttonList = buttonList;
        this.trigonometricButtonList = trigonometricButtonList;
        textWriter = new TextWriter(textField);
        addNumberListeners();
        addOperationButtonListeners();
        addTrigonometricListeners();
        addClearButtonListener(buttonList.get(5));
        addRemoveButtonListener(buttonList.get(4));
        addCountListener(buttonList.get(15), textField);
    }


    private void addNumberListeners() {
        for (JButton button : numberList) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textWriter.setNumber(e.getActionCommand());
                }
            });
        }
    }

    private void addOperationButtonListeners() {
        for (int number = 6; number < 12; number++) {
            buttonList.get(number).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textWriter.setNumber(e.getActionCommand());
                }
            });
        }

        for (int number = 0; number < 2; number++) {
            buttonList.get(number).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textWriter.setNumber(e.getActionCommand());
                }
            });
        }
    }

    private void addTrigonometricListeners() {
        for (JButton button : trigonometricButtonList) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textWriter.setNumber(e.getActionCommand());
                }
            });
        }
    }

    private void addClearButtonListener(JButton clearButton) {
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textWriter.clearTextField();
            }
        });
    }

    private void addRemoveButtonListener(JButton removeButton) {
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textWriter.remove();
            }
        });
    }

    private void addCountListener(JButton countButton, JTextField textField) {
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textWriter.count();
            }
        });

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textWriter.count();
            }
        });
    }

}
