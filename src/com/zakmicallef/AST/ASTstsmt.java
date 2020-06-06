package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTstsmt {
    public ASTstsmt() {
        super();
    }

    @Override
    public String toString() {
        return "ASTstsmt{}";
    }

    public void accept(ASTVisitor visit) {
        visit.visit((ASTAssignment) this);
    }

    public void accept(SemanticAnalysis visitor) {
    }
}
