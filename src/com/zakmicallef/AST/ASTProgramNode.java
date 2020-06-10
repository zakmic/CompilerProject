package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

import java.util.ArrayList;

public class ASTProgramNode extends ASTNode {
    public ArrayList<ASTstsmt> stms = new ArrayList<ASTstsmt>();

    public ASTProgramNode(ArrayList<ASTstsmt> stms) {
        this.stms = stms;
    }

    public static void print(ASTProgramNode program) {
    }


    public void accept(XMLVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }


    public void accept(InterpreterExecution visitor) { visitor.visit(this); }


}
