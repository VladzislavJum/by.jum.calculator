package by.jum.calculator.operations;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class ExpressionCalculator {
    public final Map<String, Integer> MATH_OPERATIONS;

    {
        MATH_OPERATIONS = new HashMap<String, Integer>();
        MATH_OPERATIONS.put("s", 1); //sin
        MATH_OPERATIONS.put("c", 1); //cos
        MATH_OPERATIONS.put("t", 1); //tg
        MATH_OPERATIONS.put("g", 1); //ctg
        MATH_OPERATIONS.put("z", 1); //sinh
        MATH_OPERATIONS.put("x", 1); //cosh
        MATH_OPERATIONS.put("v", 1); //tgh
        MATH_OPERATIONS.put("b", 1); //ctgh
        MATH_OPERATIONS.put("q", 2); //sqrt
        MATH_OPERATIONS.put("^", 2); //pow
        MATH_OPERATIONS.put("*", 3);
        MATH_OPERATIONS.put("/", 3);
        MATH_OPERATIONS.put("+", 4);
        MATH_OPERATIONS.put("-", 4);
    }

    //  private final String[] TRIGONOMETRIC_REDUCTION = {String.valueOf(Math.PI), "z", "x", "b", "v", "s", "c", "g", "t", "q"};
    // private final String[] TRIGONOMETRIC = {"PI", "sinh", "cosh", "ctgh", "tgh", "sin", "cos", "ctg", "tg", "sqrt"};
    public final List<String> TRIGONOMETRIC_OPERATIONS_LIST;

    {
        TRIGONOMETRIC_OPERATIONS_LIST = new ArrayList<String>();
        TRIGONOMETRIC_OPERATIONS_LIST.add("PI"); //sqrt
        TRIGONOMETRIC_OPERATIONS_LIST.add("sinh"); //sinh
        TRIGONOMETRIC_OPERATIONS_LIST.add("cosh"); //cosh
        TRIGONOMETRIC_OPERATIONS_LIST.add("ctgh"); //ctgh
        TRIGONOMETRIC_OPERATIONS_LIST.add("tgh"); //tgh
        TRIGONOMETRIC_OPERATIONS_LIST.add("sin"); //sin
        TRIGONOMETRIC_OPERATIONS_LIST.add("cos"); //cos
        TRIGONOMETRIC_OPERATIONS_LIST.add("ctg"); //ctg
        TRIGONOMETRIC_OPERATIONS_LIST.add("tg"); //tg
        TRIGONOMETRIC_OPERATIONS_LIST.add("sqrt"); //sqrt
    }

    public final List<String> TRIGONOMETRIC_REDUCTION;

    {
        TRIGONOMETRIC_REDUCTION = new ArrayList<String>();
        TRIGONOMETRIC_REDUCTION.add(String.valueOf(Math.PI)); //sqrt
        TRIGONOMETRIC_REDUCTION.add("z"); //sinh
        TRIGONOMETRIC_REDUCTION.add("x"); //cosh
        TRIGONOMETRIC_REDUCTION.add("b"); //ctgh
        TRIGONOMETRIC_REDUCTION.add("v"); //tgh
        TRIGONOMETRIC_REDUCTION.add("s"); //sin
        TRIGONOMETRIC_REDUCTION.add("c"); //cos
        TRIGONOMETRIC_REDUCTION.add("g"); //ctg
        TRIGONOMETRIC_REDUCTION.add("t"); //tg
        TRIGONOMETRIC_REDUCTION.add("q"); //sqrt
    }

    private JTree tree;

    public ExpressionCalculator(JTree tree) {
        this.tree = tree;

    }

    // из инфиксной нотации в обратную польскую
    public String sortingStation(String expression, Map<String, Integer> operations, String leftBracket, String rightBracket) {


        expression = expression.replace(" ", "");
        // Выходная строка, разбитая на "символы" - операции и операнды..
        List<String> out = new ArrayList<String>();
        // Стек операций.
        Stack<String> stack = new Stack<String>();
        // Множество "символов", не являющихся операндами (операции и скобки).
        Set<String> operationSymbols = new HashSet<String>(operations.keySet());
        operationSymbols.add(leftBracket);
        operationSymbols.add(rightBracket);
        // Индекс, на котором закончился разбор строки на прошлой итерации.
        int index = 0;
        // Признак необходимости поиска следующего элемента.
        boolean findNext = true;
        while (findNext) {
            int nextOperationIndex = expression.length();
            String nextOperation = "";
            // Поиск следующего оператора или скобки.
            for (String operation : operationSymbols) {
                int i = expression.indexOf(operation, index);
                if (i >= 0 && i < nextOperationIndex) {
                    nextOperation = operation;
                    nextOperationIndex = i;
                }
            }
            // Оператор не найден.
            if (nextOperationIndex == expression.length()) {
                findNext = false;
            } else {
                // Если оператору или скобке предшествует операнд, добавляем его в выходную строку.
                if (index != nextOperationIndex) {
                    out.add(expression.substring(index, nextOperationIndex));
                }
                // Обработка операторов и скобок.
                // Открывающая скобка.
                if (nextOperation.equals(leftBracket)) {
                    stack.push(nextOperation);
                }
                // Закрывающая скобка.
                else if (nextOperation.equals(rightBracket)) {
                    while (!stack.peek().equals(leftBracket)) {
                        out.add(stack.pop());
                        if (stack.empty()) {
                            throw new IllegalArgumentException("Unmatched brackets");
                        }
                    }
                    stack.pop();
                }
                // Операция.
                else {
                    while (!stack.empty() && !stack.peek().equals(leftBracket) &&
                            (operations.get(nextOperation) >= operations.get(stack.peek()))) {
                        out.add(stack.pop());
                    }
                    stack.push(nextOperation);
                }
                index = nextOperationIndex + nextOperation.length();
            }
        }
        // Добавление в выходную строку операндов после последнего операнда.
        if (index != expression.length()) {
            out.add(expression.substring(index));
        }
        // Пробразование выходного списка к выходной строке.
        while (!stack.empty()) {
            out.add(stack.pop());
        }
        StringBuffer result = new StringBuffer();
        if (!out.isEmpty()) {
            String s = out.remove(0);
            //System.out.println(s);

            result.append(s);
        }
        while (!out.isEmpty()) {

            String s = out.remove(0);
            result.append(" ").append(s);
        }


        // node.add(new DefaultMutableTreeNode(operand1));
        // node.add(new DefaultMutableTreeNode(operand2));
        System.out.println(result);
        return result.toString();
    }


    public String sortingStation(String expression, Map<String, Integer> operations) {
        return sortingStation(expression, operations, "(", ")");
    }

    // вычисление
    public String calculateExpression(String expression) {

        expression = replace(expression);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("");


        if (expression.length() == 0) {
            return "";
        }
        Stack<String> stringStack = new Stack<String>();
        Operations operations = new Operations();
        String rpn = sortingStation(expression, MATH_OPERATIONS);
        StringTokenizer tokenizer = new StringTokenizer(rpn, " ");


        StringTokenizer tokenizerTree = new StringTokenizer(rpn, " ");

        while (tokenizerTree.hasMoreTokens()) {
            stringStack.add(tokenizerTree.nextToken());
        }

        boolean b = true;
        int q = 0;
        int i = 0;
        while (!stringStack.isEmpty()) {
            String string = stringStack.pop();
            System.out.println("Next" + string);
            if (!MATH_OPERATIONS.keySet().contains(string)) {
                if (++q == 2 && TRIGONOMETRIC_REDUCTION.contains(string)) {
                    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) treeNode.getParent();
                    parent.add(new DefaultMutableTreeNode(string));

                } else if (++i != 3) {
                    treeNode.add(new DefaultMutableTreeNode(string));
                } else {
                    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) treeNode.getParent();
                    parent.add(new DefaultMutableTreeNode(string));
                }

            } else if (b) {
                i = 0;
                b = false;
                if (TRIGONOMETRIC_REDUCTION.contains(string)) {
                    q = 0;
                    treeNode = new DefaultMutableTreeNode(TRIGONOMETRIC_OPERATIONS_LIST.get(TRIGONOMETRIC_REDUCTION.indexOf(string)));

                } else {
                    treeNode = new DefaultMutableTreeNode(string);
                }
                root.add(treeNode);

            } else {
                DefaultMutableTreeNode node;
                i = 0;
                if (TRIGONOMETRIC_REDUCTION.contains(string)) {
                    q = 0;
                    node = new DefaultMutableTreeNode(TRIGONOMETRIC_OPERATIONS_LIST.get(TRIGONOMETRIC_REDUCTION.indexOf(string)));
                }else {
                   node = new DefaultMutableTreeNode(string);
                }
                treeNode.add(node);
                treeNode = node;
            }


        }


        tree.setModel(new DefaultTreeModel(root));
        for (int rowCout = 0; rowCout < tree.getRowCount(); rowCout++) {
            tree.expandRow(rowCout);
        }


        // System.out.println("TO" + tokenizer.countTokens());
        Stack<Double> stack = new Stack<Double>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            //  System.out.println("Next" + token);
            // Операнд.
            if (!MATH_OPERATIONS.keySet().contains(token)) {
                stack.push(Double.parseDouble(token));
            } else {

                Double operand2 = stack.pop();
                Double operand1 = stack.empty() ? 0 : stack.pop();


                if (token.equals("*")) {
                    stack.push(operations.multiplication(operand1, operand2));
                } else if (token.equals("/")) {
                    stack.push(operations.div(operand1, operand2));
                } else if (token.equals("+")) {
                    stack.push(operations.add(operand1, operand2));
                } else if (token.equals("-")) {
                    stack.push(operations.diff(operand1, operand2));
                } else if (token.equals("^")) {
                    stack.push(operations.pow(operand1, operand2));
                } else if (token.equals("s")) {
                    stack.push(operations.sin(operand2));
                } else if (token.equals("c")) {
                    stack.push(operations.cos(operand1));
                } else if (token.equals("t")) {
                    stack.push(operations.tg(operand1));
                } else if (token.equals("g")) {
                    stack.push(operations.ctg(operand1));
                } else if (token.equals("z")) {
                    stack.push(operations.sinh(operand1));
                } else if (token.equals("x")) {
                    stack.push(operations.cosh(operand1));
                } else if (token.equals("v")) {
                    stack.push(operations.tgh(operand1));
                } else if (token.equals("b")) {
                    stack.push(operations.ctgh(operand1));
                } else if (token.equals("q")) {
                    stack.push(operations.sqrt(operand1));
                }
            }
        }

        return stack.pop().toString();
    }


    String replace(String expression) {

        for (int i = 0; i < TRIGONOMETRIC_OPERATIONS_LIST.size(); i++) {
            String string = TRIGONOMETRIC_OPERATIONS_LIST.get(i);
            if (expression.contains(string)) {
                expression = expression.replaceAll(string, TRIGONOMETRIC_REDUCTION.get(i));
            }
        }

        return expression;
    }

    String replaceBack(String expression) {

        for (int i = 0; i < TRIGONOMETRIC_REDUCTION.size(); i++) {
            String string = TRIGONOMETRIC_REDUCTION.get(i);
            if (expression.contains(string)) {
                //  expression = expression.replaceAll(string, TRIGONOMETRIC_OPERATIONS.get(string));
            }
        }

        return expression;
    }
}

