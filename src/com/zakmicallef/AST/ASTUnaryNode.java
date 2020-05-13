package com.zakmicallef.AST;

import com.zakmicallef.AST.ASTExprNode;

public class ASTUnaryNode extends ASTExprNode {
    String lexeme;
    ASTExprNode expr;

    public ASTUnaryNode(String lexeme, ASTExprNode expr) {
        this.lexeme = lexeme;
        this.expr = expr;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public ASTExprNode getExpr() {
        return expr;
    }

    public void setExpr(ASTExprNode expr) {
        this.expr = expr;
    }
}
