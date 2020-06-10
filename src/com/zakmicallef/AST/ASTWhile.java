package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

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

    @Override
    public void accept(XMLVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

    @Override
    public void accept(InterpreterExecution visitor) { visitor.visit(this); }
}
