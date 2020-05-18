package com.zakmicallef.AST;

public class ASTVarNode extends ASTstsmt {
    String id;
    String type;
    ASTExpr expression;

    public ASTVarNode(String id, String type, ASTExpr expression) {
        this.id = id;
        this.type = type;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
