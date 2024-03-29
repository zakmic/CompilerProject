package com.zakmicallef;

import com.zakmicallef.AST.ASTProgramNode;
import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class Main {
    public static void main(String[] args) {
        String xmlpath = "XML/draftXML1.xml";
        Lexer.Lex();
        ASTProgramNode programNode = Parser.parse();
        XMLVisitor xmlVisitor = new XMLVisitor();
        SemanticAnalysis semanticVisitor = new SemanticAnalysis();
        xmlVisitor.visit(programNode);
        semanticVisitor.visit(programNode);
        FileInput.writeToFile(xmlVisitor.xml, xmlpath);
        InterpreterExecution interpreter = new InterpreterExecution();
        interpreter.visit(programNode);
    }
}
