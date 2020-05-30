package com.zakmicallef.AST;

public class ASTIf extends ASTstsmt {
    public ASTExpr expr;
    public ASTBlock ifBlock;
    public ASTBlock elseBlock;

    public ASTIf(ASTExpr expr, ASTBlock ifBlock, ASTBlock elseBlock) {
        this.expr = expr;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }
}
