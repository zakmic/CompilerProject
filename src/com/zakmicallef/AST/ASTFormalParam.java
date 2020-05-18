package com.zakmicallef.AST;

import java.util.ArrayList;

public class ASTFormalParam {
    String lexeme;
    String type;

    public ASTFormalParam(String lexeme, String type) {
        this.lexeme = lexeme;
        this.type = type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
