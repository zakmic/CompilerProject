package com.zakmicallef.AST;

public class ASTFor extends ASTstsmt {

    ASTVarNode var;
    ASTExpr expr;
    ASTAssignment assignment;
    ASTBlock block;

    public ASTFor(ASTVarNode var, ASTExpr expr, ASTAssignment assignment, ASTBlock block) {
        this.var = var;
        this.expr = expr;
        this.assignment = assignment;
        this.block = block;
    }

    public ASTVarNode getVar() {
        return var;
    }

    public void setVar(ASTVarNode var) {
        this.var = var;
    }

    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }

    public ASTAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(ASTAssignment assignment) {
        this.assignment = assignment;
    }

    public ASTBlock getBlock() {
        return block;
    }

    public void setBlock(ASTBlock block) {
        this.block = block;
    }
}
