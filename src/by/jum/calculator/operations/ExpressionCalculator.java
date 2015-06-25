package by.jum.calculator.operations;

import by.jum.calculator.constants.Names;

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
        MATH_OPERATIONS.put("%", 1);
        MATH_OPERATIONS.put("√", 2); //sqrt
        MATH_OPERATIONS.put("^", 2); //pow
        MATH_OPERATIONS.put("*", 3);
        MATH_OPERATIONS.put("/", 3);
        MATH_OPERATIONS.put("+", 4);
        MATH_OPERATIONS.put("-", 4);
    }

    public final List<String> TRIGONOMETRIC_OPERATIONS_LIST;

    {
        TRIGONOMETRIC_OPERATIONS_LIST = new ArrayList<String>();
        TRIGONOMETRIC_OPERATIONS_LIST.add(Names.PI.getName());
        TRIGONOMETRIC_OPERATIONS_LIST.add(Names.SINH.getName()); //sinh
        TRIGONOMETRIC_OPERATIONS_LIST.add(Names.COSH.getName()); //cosh
        TRIGONOMETRIC_OPERATIONS_LIST.add(Names.CTGH.getName()); //ctgh
        TRIGONOMETRIC_OPERATIONS_LIST.add(Names.TGH.getName()); //tgh
        TRIGONOMETRIC_OPERATIONS_LIST.add(Names.SIN.getName()); //sin
        TRIGONOMETRIC_OPERATIONS_LIST.add(Names.COS.getName()); //cos
        TRIGONOMETRIC_OPERATIONS_LIST.add(Names.CTG.getName()); //ctg
        TRIGONOMETRIC_OPERATIONS_LIST.add(Names.TG.getName()); //tg
    }

    public final List<String> TRIGONOMETRIC_REDUCTION;

    {
        TRIGONOMETRIC_REDUCTION = new ArrayList<String>();
        TRIGONOMETRIC_REDUCTION.add(String.valueOf(3.141));
        TRIGONOMETRIC_REDUCTION.add("z"); //sinh
        TRIGONOMETRIC_REDUCTION.add("x"); //cosh
        TRIGONOMETRIC_REDUCTION.add("b"); //ctgh
        TRIGONOMETRIC_REDUCTION.add("v"); //tgh
        TRIGONOMETRIC_REDUCTION.add("s"); //sin
        TRIGONOMETRIC_REDUCTION.add("c"); //cos
        TRIGONOMETRIC_REDUCTION.add("g"); //ctg
        TRIGONOMETRIC_REDUCTION.add("t"); //tg
    }

    private Stack<DefaultTreeModel> defaultTreeModelStack = new Stack<DefaultTreeModel>();
    private Stack<String> expressionStack = new Stack<String>();
    private StringTokenizer tokenizer;
    private Stack<Double> stack;
    private String expression;
    private Operations operations;
    private JTree tree;
    private Double operand1;
    private Double operand2;

    public ExpressionCalculator() {
        operations = new Operations();
    }

    // из инфиксной нотации в обратную польскую
    public String sortingStation(String expression, String leftBracket, String rightBracket) {


        expression = expression.replace(" ", "");
        // Выходная строка, разбитая на "символы" - операции и операнды..
        List<String> out = new ArrayList<String>();
        // Стек операций.
        Stack<String> stack = new Stack<String>();
        // Множество "символов", не являющихся операндами (операции и скобки).
        Set<String> operationSymbols = new HashSet<String>(MATH_OPERATIONS.keySet());
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
                            (MATH_OPERATIONS.get(nextOperation) >= MATH_OPERATIONS.get(stack.peek()))) {
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


    public String sortingStation(String expression) {
        return sortingStation(expression, "(", ")");
    }

    // вычисление
    public String calculateExpression(String expression, JTree tree) {

        if (expression.length() == 0) {
            return String.valueOf(0);
        }
        this.expression = expression;
        this.tree = tree;

        expressionStack.push(expression);

        expression = replace();
        buildTree();
        StringTokenizer tokenizer = new StringTokenizer(sortingStation(expression), " ");
        Stack<Double> stack = new Stack<Double>();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (!MATH_OPERATIONS.keySet().contains(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                calculate(stack, token);
            }
        }


        return stack.pop().toString();
    }


    private void buildTree() {
        Stack<String> stringStack = new Stack<String>();
        String rpn = sortingStation(expression);
        StringTokenizer tokenizerTree = new StringTokenizer(rpn, " ");

        while (tokenizerTree.hasMoreTokens()) {
            stringStack.add(tokenizerTree.nextToken());
        }

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("");

        boolean bool = true;
        int trigonometricCount = 0;
        int operationCount = 0;
        while (!stringStack.isEmpty()) {
            String string = stringStack.pop();
            //  System.out.println("Next" + string);
            if (!MATH_OPERATIONS.keySet().contains(string)) {
                if (++trigonometricCount == 2 && TRIGONOMETRIC_REDUCTION.contains(string)) {
                    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) treeNode.getParent();
                    parent.add(new DefaultMutableTreeNode(string));
                } else if (++operationCount != 3) {
                    treeNode.add(new DefaultMutableTreeNode(string));
                    //  DefaultMutableTreeNode d = (DefaultMutableTreeNode)root.clone();
                    //treeModelArrayList.add(new DefaultTreeModel(d));
                } else {
                    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) treeNode.getParent();
                    parent.add(new DefaultMutableTreeNode(string));
                }

            } else if (bool) {
                operationCount = 0;
                bool = false;
                if (TRIGONOMETRIC_REDUCTION.contains(string)) {
                    trigonometricCount = 0;
                    treeNode = new DefaultMutableTreeNode(TRIGONOMETRIC_OPERATIONS_LIST.
                            get(TRIGONOMETRIC_REDUCTION.indexOf(string)));

                } else {
                    treeNode = new DefaultMutableTreeNode(string);
                }
                root.add(treeNode);
            } else {
                DefaultMutableTreeNode node;
                operationCount = 0;
                if (TRIGONOMETRIC_REDUCTION.contains(string)) {
                    trigonometricCount = 0;
                    node = new DefaultMutableTreeNode(TRIGONOMETRIC_OPERATIONS_LIST.
                            get(TRIGONOMETRIC_REDUCTION.indexOf(string)));
                } else {
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

    }

    private double calculate(Stack<Double> stack, String token) {
        operand2 = stack.pop();
        operand1 = stack.empty() ? 0 : stack.pop();

        if (token.equals("*")) {
            stack.push(operations.multiplication(operand1, operand2));
        } else if (token.equals("/")) {
            stack.push(operations.div(operand1, operand2));
        } else if (token.equals("+")) {
            stack.push(operations.add(operand1, operand2));
        } else if (token.equals("%")) {
            stack.push(operand1);
            stack.push(operations.percent(operand1, operand2));
        } else if (token.equals("-")) {
            stack.push(operations.diff(operand1, operand2));
        } else if (token.equals("^")) {
            stack.push(operations.pow(operand1, operand2));
        } else if (token.equals("s")) {
            stack.push(operations.sin(operand2));
        } else if (token.equals("c")) {
            stack.push(operations.cos(operand2));
        } else if (token.equals("t")) {
            stack.push(operations.tg(operand2));
        } else if (token.equals("g")) {
            stack.push(operations.ctg(operand2));
        } else if (token.equals("z")) {
            stack.push(operations.sinh(operand2));
        } else if (token.equals("x")) {
            stack.push(operations.cosh(operand2));
        } else if (token.equals("v")) {
            stack.push(operations.tgh(operand2));
        } else if (token.equals("b")) {
            stack.push(operations.ctgh(operand2));
        } else if (token.equals("√")) {
            stack.push(operations.sqrt(operand2));//корень багнутый
        }

        double answer = stack.pop();
        stack.push(answer);
        return answer;

    }


    String replace() {
        for (int i = 0; i < TRIGONOMETRIC_OPERATIONS_LIST.size(); i++) {
            String string = TRIGONOMETRIC_OPERATIONS_LIST.get(i);
            if (expression.contains(string)) {
                expression = expression.replaceAll(string, TRIGONOMETRIC_REDUCTION.get(i));
            }
        }
        return expression;
    }

    private void getTokens() {
        expression = replace();
        tokenizer = new StringTokenizer(sortingStation(expression), " ");
        stack = new Stack<Double>();

    }

    public String folding() {
        getTokens();
        String token;
        if (tokenizer.countTokens() > 1) {
            expressionStack.push(expression);
            defaultTreeModelStack.push((DefaultTreeModel) tree.getModel());
            do {
                token = tokenizer.nextToken();
                if (!MATH_OPERATIONS.keySet().contains(token)) {
                    stack.push(Double.parseDouble(token));
                } else {
                    //       System.out.println("Calc");
                    String value = String.valueOf(calculate(stack, token));
                    if (operand1 != 0.0) {
                        //         System.out.println(operand1 + "||||" + operand2);
                        expression = expression.replace("(" + operand1 + token + operand2 + ")", value);
                        expression = expression.replace(operand1 + token + operand2, value);
                    } else {
                        expression = expression.replace("(" + token + operand2 + ")", value);
                        expression = expression.replace(token + "(" + operand2 + ")", value);
//                    expression = expression.replace(operand2+ token, value); //багииии с %
                    }
                }
            } while (!MATH_OPERATIONS.keySet().contains(token));

            String answer = calculateExpression(expression, tree);
            if (!tokenizer.hasMoreTokens()) {
                DefaultMutableTreeNode root = new DefaultMutableTreeNode();
                root.add(new DefaultMutableTreeNode(answer));
                tree.setModel(new DefaultTreeModel(root));
            }

            if (!expressionStack.contains(expression)) {
                expressionStack.push(expression);
                defaultTreeModelStack.push((DefaultTreeModel) tree.getModel());
            }
        }
        return expression;
    }

    public String unFolding() {
        if (!defaultTreeModelStack.isEmpty()) {
            tree.setModel(defaultTreeModelStack.pop());
            expression = expressionStack.pop();
            expression = expressionStack.pop();
            for (int rowCout = 0; rowCout < tree.getRowCount(); rowCout++) {
                tree.expandRow(rowCout);
            }
        }
        return expression;
    }

}

