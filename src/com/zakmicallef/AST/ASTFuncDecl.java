package com.zakmicallef.AST;

public class ASTFuncDecl extends ASTstsmt {

    public ASTidNode lexeme;
    public ASTFormalParams params;
    public ASTType.Type type;
    public ASTBlock block;

    public ASTFuncDecl(ASTidNode lexeme, ASTFormalParams params, ASTType.Type type, ASTBlock block) {
        this.lexeme = lexeme;
        this.params = params;
        this.type = type;
        this.block = block;
    }
}
