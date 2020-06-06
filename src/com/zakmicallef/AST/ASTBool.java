package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.SemanticAnalysis;

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

    @Override
    public void accept(ASTVisitor visit) {
        visit.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

}
