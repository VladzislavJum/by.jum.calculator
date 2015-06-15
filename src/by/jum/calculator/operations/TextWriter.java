package by.jum.calculator.operations;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TextWriter {
    private JTextField textField;

    public TextWriter(JTextField textField) {
        this.textField = textField;
    }

    public void setExpression(String expression) {
        int caretPosition = textField.getCaretPosition();
        String text = textField.getText();
        textField.setText(text.substring(0, caretPosition) + expression + text.substring(caretPosition, text.length()));
        textField.requestFocusInWindow();
        textField.setCaretPosition(caretPosition + expression.length());
    }

    public void clearTextField(JTree tree) {
        textField.setText("");
        tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("")));
        textField.requestFocusInWindow();
    }

    public void remove() {
        String text = textField.getText();
        if (text.length() == 0) return;
        int caretPosition = textField.getCaretPosition();
        textField.setText(text.substring(0, caretPosition - 1) + text.substring(caretPosition, text.length()));
        textField.requestFocusInWindow();
        textField.setCaretPosition(caretPosition - 1);
    }

    public void count(JTree tree) {
        ExpressionCalculator expressionUtils = new ExpressionCalculator(tree);

        textField.setText(String.valueOf(new BigDecimal(Double.parseDouble(expressionUtils.calculateExpression(textField.getText()))).setScale(3, RoundingMode.UP).doubleValue()));
    }

}
