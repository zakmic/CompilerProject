package com.zakmicallef;

import com.zakmicallef.AST.ASTProgramNode;

public class Main {
    public static void main(String[] args) {
        Lexer.Lex();
        System.out.println("Parser");
        ASTProgramNode programNode = Parser.parse();
        ASTProgramNode.print(programNode);


    }
}
