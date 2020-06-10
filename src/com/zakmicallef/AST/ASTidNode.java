package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTidNode extends ASTExpr {
    public String lexeme;

    public ASTidNode(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public void accept(XMLVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

    @Override
    public void accept(InterpreterExecution visitor) { visitor.visit(this); }


}
