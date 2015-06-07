package by.jum.calculator.operations;

import by.jum.calculator.constants.Names;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created by Vlad on 07.06.2015.
 */
public class Calculation {
    private final String OPERATIONS[] = {"sin", "cos", "tg", "ctg", "sinh", "cosh", "tgh", "ctgh", "^", "sqrt", "/", "*", "-", "+"};
    private HashMap<String, Integer> operations;
    private String string;
    private Vector<String> forTree = new Vector<String>();

    public Calculation(String string) {
        this.string = string;
        operations = new HashMap<String, Integer>();
        operations.put("sin", 5);
        operations.put("cos", 5);
        operations.put("tg", 5);
        operations.put("ctg", 5);
        operations.put("sinh", 5);
        operations.put("cosh", 5);
        operations.put("tgh", 5);
        operations.put("ctgh", 5);
        operations.put("^", 4);
        operations.put("sqrt", 4);
        operations.put("/", 3);
        operations.put("*", 3);
        operations.put("-", 2);
        operations.put("+", 2);
    }

    public void priority() {
        char charStr[] = string.toCharArray();


//
//        while (string.contains(Names.BRACKET_LEFT.getName())) {
//            boolean contain = true;
//            for (String operation: OPERATIONS) {
//                if (string.contains(operation)) {
//                    contain = false;
//                }
//            }
//            if (contain) {
//                return string;
//            }
//        }

        /*for (String operation : OPERATIONS) {
            if (string.contains(operation)) {
                int indexOper = string.indexOf(operation);
                if (String.valueOf(string.charAt(indexOper - 1)).equals(Names.BRACKET_RIGHT.getName()) ){
                        //|| String.valueOf(string.charAt(indexOper + 1)).equals(Names.BRACKET_LEFT.getName())) {
                    int x = string.indexOf(Names.BRACKET_LEFT.getName(), ){}
                }
            }
        }*/


    }

    private void add() {

        int k = string.indexOf(Names.ADD.getName());

        String s1 = string.substring(0, k);
        String s2 = string.substring(k + 1, string.length());

        System.out.println(Double.parseDouble(s1) + Double.parseDouble(s2));
    }
}
