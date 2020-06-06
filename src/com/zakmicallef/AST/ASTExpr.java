package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTExpr extends ASTNode {
    public ASTExpr() {
        super();
    }

    public void accept(ASTVisitor visit) {
    }

    public void accept(SemanticAnalysis visitor) {
    }
}
