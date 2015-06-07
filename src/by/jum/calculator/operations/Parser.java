package by.jum.calculator.operations;

import by.jum.calculator.constants.Names;

import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final char LEFT_BRACKET = '(';
    private final char RIGHT_BRACKET = ')';
    private int check = 0;
    private List<String> stringBracketList;

    public Parser(JTextField textField) {
        String string = LEFT_BRACKET + textField.getText() + RIGHT_BRACKET;
        string = string.replace(" ", "");
        stringBracketList = new ArrayList<String>();
        searchBracket(string, 0);
    }

    public void searchBracket(String string, int startIndex) {
        char[] charString = string.toCharArray();
        startIndex = string.indexOf(Names.BRACKET_LEFT.getName(), startIndex);
        if (startIndex == -1) {
            return;
        }
        int iteratorChar = startIndex;
        do {
            if (charString[iteratorChar] == LEFT_BRACKET) {
                check++;
            } else if (charString[iteratorChar] == RIGHT_BRACKET) {
                check--;
            }
            iteratorChar++;

        } while (check != 0);

        String substring = string.substring(++startIndex, --iteratorChar);
        System.out.println(substring);
        stringBracketList.add(substring);
        searchBracket(string, iteratorChar);
        searchBracket(substring, 0);
    }

    public List<String> getStringBracketList() {
        return stringBracketList;
    }
}
