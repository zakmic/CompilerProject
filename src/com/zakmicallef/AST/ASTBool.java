package com.zakmicallef.AST;

public class ASTBool extends ASTExpr {
    public boolean value;

    public ASTBool(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
