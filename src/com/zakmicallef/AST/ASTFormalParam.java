package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;

import java.util.ArrayList;

public class ASTFormalParam {
    public ASTidNode lexeme;
    public String type;

    public ASTFormalParam(ASTidNode lexeme, String type) {
        this.lexeme = lexeme;
        this.type = type;
    }

    public void accept(ASTVisitor astVisitor) {
        astVisitor.visit(this);
    }
}
