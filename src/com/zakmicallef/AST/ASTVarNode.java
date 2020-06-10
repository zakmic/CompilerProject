package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTVarNode extends ASTstsmt {
    ASTidNode id;
    String type;
    ASTExpr expression;

    public ASTVarNode(ASTidNode id, String type, ASTExpr expression) {
        this.id = id;
        this.type = type;
        this.expression = expression;
    }

    @Override
    public void accept(XMLVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

    @Override
    public void accept(InterpreterExecution visitor) { visitor.visit(this); }


    public ASTidNode getId() {
        return id;
    }

    public void setId(ASTidNode id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ASTExpr getExpression() {
        return expression;
    }

    public void setExpression(ASTExpr expression) {
        this.expression = expression;
    }
}
