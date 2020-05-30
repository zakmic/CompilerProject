package com.zakmicallef.AST;

public class ASTFloatNode extends ASTExpr {
    public double value;
//    public ASTFloatNode(double value) {
//        super();
//    }

    public ASTFloatNode(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
