package com.zakmicallef.AST;

public class ASTidNode extends ASTExpr {
    public String lexeme;

    public ASTidNode(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }
}
