package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTFormalParam extends ASTNode {
    public ASTidNode lexeme;
    public String type;

    public ASTFormalParam(ASTidNode lexeme, String type) {
        this.lexeme = lexeme;
        this.type = type;
    }

    public void accept(XMLVisitor astVisitor) {
        astVisitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

    public void accept(InterpreterExecution visitor) { visitor.visit(this); }

}
