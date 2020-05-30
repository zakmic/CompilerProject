package com.zakmicallef.AST;

public class ASTUnaryNode extends ASTExpr {
    public String lexeme;
    public ASTExpr expr;

    public ASTUnaryNode(String lexeme, ASTExpr expr) {
        this.lexeme = lexeme;
        this.expr = expr;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }


}
