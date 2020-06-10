package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTstsmt extends ASTNode {
    public ASTstsmt() {
        super();
    }

    @Override
    public String toString() {
        return "ASTstsmt{}";
    }

    public void accept(XMLVisitor visit) {
        visit.visit((ASTAssignment) this);
    }

    public void accept(SemanticAnalysis visitor) {
    }

    public void accept(InterpreterExecution interpreterExecution) {
    }


}
