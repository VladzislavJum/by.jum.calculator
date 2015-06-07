package by.jum.calculator.operations;

import javax.swing.JTextField;

public class TextWriter {
    private JTextField textField;

    public TextWriter(JTextField textField) {
        this.textField = textField;
    }

    public void setNumber(String number) {
        textField.setText(textField.getText() + number);
        textField.requestFocusInWindow();
    }

    public void clearTextField() {
        textField.setText("");
        textField.requestFocusInWindow();
    }

    public void remove() {
        String text = textField.getText();
        if (text.length() == 0) return;
        text = text.substring(0, text.length() - 1);
        textField.setText(text);
        textField.requestFocusInWindow();
    }

    public void count() {
         Parser parser = new Parser(textField);
       // Calculation calculation = new Calculation(textField.getText());
        //calculation.priority();
    }

}
