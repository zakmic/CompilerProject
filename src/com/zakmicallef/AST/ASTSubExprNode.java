package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.SemanticAnalysis;

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

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

}
