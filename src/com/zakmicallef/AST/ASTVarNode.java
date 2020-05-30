package com.zakmicallef.AST;

public class ASTVarNode extends ASTstsmt {
    ASTidNode id;
    String type;
    ASTExpr expression;

    public ASTVarNode(ASTidNode id, String type, ASTExpr expression) {
        this.id = id;
        this.type = type;
        this.expression = expression;
    }

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
