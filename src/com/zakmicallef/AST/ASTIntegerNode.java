package com.zakmicallef.AST;

public class ASTIntegerNode extends ASTExpr {
    int value;

    public ASTIntegerNode(double value) {
        super();
        this.value = (int) value;
    }
}
