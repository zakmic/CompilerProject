package com.zakmicallef.AST;

public class ASTSubExprNode extends ASTExpr {
    public ASTExpr exps;

    public ASTSubExprNode(ASTExpr exps) {
        this.exps = exps;
    }

    public ASTExpr getExps() {
        return exps;
    }

    public void setExps(ASTExpr exps) {
        this.exps = exps;
    }
}
