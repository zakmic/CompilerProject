package com.zakmicallef.AST;

public class ASTFuncDecl extends ASTstsmt {

    String lexeme;
    ASTFormalParams params;
    String type;
    ASTBlock block;

    public ASTFuncDecl(String lexeme, ASTFormalParams params, String type, ASTBlock block) {
        this.lexeme = lexeme;
        this.params = params;
        this.type = type;
        this.block = block;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public ASTFormalParams getParams() {
        return params;
    }

    public void setParams(ASTFormalParams params) {
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ASTBlock getBlock() {
        return block;
    }

    public void setBlock(ASTBlock block) {
        this.block = block;
    }
}
