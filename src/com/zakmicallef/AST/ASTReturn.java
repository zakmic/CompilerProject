package com.zakmicallef.AST;

public class ASTReturn extends ASTstsmt {
    ASTExpr expr;

    public ASTReturn(ASTExpr expr) {
        this.expr = expr;
    }

    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }
}
