package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTFor extends ASTstsmt {

    public ASTVarNode var;
    public ASTExpr expr;
    public ASTAssignment assignment;
    public ASTBlock block;

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

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

    @Override
    public void accept(InterpreterExecution visitor) { visitor.visit(this); }



}
