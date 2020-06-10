package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
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
    public void accept(XMLVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

    @Override
    public void accept(InterpreterExecution visitor) { visitor.visit(this); }


}
