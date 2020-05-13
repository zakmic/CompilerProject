package com.zakmicallef.AST;

public class ASTSubExprNode extends ASTExprNode {
    ASTExprNode exps;

    public ASTSubExprNode(ASTExprNode exps) {
        this.exps = exps;
    }

    public ASTExprNode getExps() {
        return exps;
    }

    public void setExps(ASTExprNode exps) {
        this.exps = exps;
    }
}
