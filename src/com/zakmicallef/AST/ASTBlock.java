package com.zakmicallef.AST;

import java.util.ArrayList;

public class ASTBlock extends ASTstsmt {
    ArrayList<ASTstsmt> stmts;

    public ASTBlock(ArrayList<ASTstsmt> stmts) {
        this.stmts = stmts;
    }

    public ArrayList<ASTstsmt> getStmts() {
        return stmts;
    }

    public void setStmts(ArrayList<ASTstsmt> stmts) {
        this.stmts = stmts;
    }
}
