package com.zakmicallef.AST;

public class ASTWhile extends ASTstsmt {
    public ASTExpr expr;
    public ASTBlock block;

    public ASTWhile(ASTExpr expr, ASTBlock block) {
        this.expr = expr;
        this.block = block;
    }

    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }

    public ASTBlock getBlock() {
        return block;
    }

    public void setBlock(ASTBlock block) {
        this.block = block;
    }
}
