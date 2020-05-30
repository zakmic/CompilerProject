package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;

import java.util.ArrayList;

public class ASTBlock extends ASTstsmt {
    public ArrayList<ASTstsmt> stmts;

    public ASTBlock(ArrayList<ASTstsmt> stmts) {
        this.stmts = stmts;
    }

    public ArrayList<ASTstsmt> getStmts() {
        return stmts;
    }

    public void setStmts(ArrayList<ASTstsmt> stmts) {
        this.stmts = stmts;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
