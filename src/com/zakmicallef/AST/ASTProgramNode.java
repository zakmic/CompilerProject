package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.SemanticAnalysis;

import java.util.ArrayList;

public class ASTProgramNode {
    public ArrayList<ASTstsmt> stms = new ArrayList<ASTstsmt>();

    public ASTProgramNode(ArrayList<ASTstsmt> stms) {
        this.stms = stms;
    }

    public static void print(ASTProgramNode program) {
    }


    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

}
