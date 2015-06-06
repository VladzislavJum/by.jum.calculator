package by.jum.calculator.constants;

/**
 * Created by Vlad on 05.06.2015.
 */
public enum Names {
    VIEW("View"),
    TRIGONOMEETRIC("Trigonometric"),
    SIMPLE("Simple"),
    ADD("+"),
    DIFF("-"),
    COUNT("="),
    MULT("*"),
    DIV("/"),
    SQRT("√"),
    PI ("PI"),
    COS("cos"),
    SIN ("sin"),
    TG ("tg"),
    CTG ("ctg"),
    SINH ("sinh"),
    COSH ("cosh"),
    TGH ("tgh"),
    CTGH ("ctgh"),
    POINT("."),
    OPPOSITE("1/x"),
    PERCENT("%"),
    DELETE(" ← "),
    CLEAR("C"),
    BRACKET_LEFT("("),
    BRACKET_RIGHT(")"),
    FOLDING("  <  "),
    UNFOLDING(">");

    private String name;

    Names(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
