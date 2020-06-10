package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;

public class ASTReturn extends ASTstsmt {
    public ASTExpr expr;

    public ASTReturn(ASTExpr expr) {
        this.expr = expr;
    }

    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }

    @Override
    public void accept(XMLVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(InterpreterExecution visitor) { visitor.visit(this); }

}
