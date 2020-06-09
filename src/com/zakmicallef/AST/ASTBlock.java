package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

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

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

    @Override
    public void accept(InterpreterExecution visitor) { visitor.visit(this); }


}
