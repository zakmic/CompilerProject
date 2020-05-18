package com.zakmicallef.AST;

import java.util.ArrayList;

public class ASTProgramNode {
    public ArrayList<ASTstsmt> stms = new ArrayList<ASTstsmt>();

    public ASTProgramNode(ArrayList<ASTstsmt> stms) {
        this.stms = stms;
    }

    public static void print(ASTProgramNode program) {
    }
}
