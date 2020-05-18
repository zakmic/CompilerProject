package com.zakmicallef.AST;

public class ASTIf extends ASTstsmt {
    ASTExpr expr;
    ASTBlock ifBlock;
    ASTBlock elseBlock;

    public ASTIf(ASTExpr expr, ASTBlock ifBlock, ASTBlock elseBlock) {
        this.expr = expr;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }

    public ASTBlock getIfBlock() {
        return ifBlock;
    }

    public void setIfBlock(ASTBlock ifBlock) {
        this.ifBlock = ifBlock;
    }

    public ASTBlock getElseBlock() {
        return elseBlock;
    }

    public void setElseBlock(ASTBlock elseBlock) {
        this.elseBlock = elseBlock;
    }
}
