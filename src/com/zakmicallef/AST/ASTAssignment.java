package com.zakmicallef.AST;

import com.zakmicallef.Token;
import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.SemanticAnalysis;

import static com.zakmicallef.Parser.getType;

public class ASTAssignment extends ASTstsmt {

    public ASTidNode id;
    public ASTExpr expr;

    public ASTAssignment(ASTidNode id, ASTExpr expr) {
        this.id = id;
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }


}
