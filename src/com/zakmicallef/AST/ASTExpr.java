package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTExpr extends ASTNode {
    public ASTExpr() {
        super();
    }

    public void accept(XMLVisitor visit) {
    }

    public void accept(SemanticAnalysis visitor) {
    }

    public void accept(InterpreterExecution interpreterExecution) {
    }
}
