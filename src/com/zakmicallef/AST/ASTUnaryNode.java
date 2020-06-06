package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTUnaryNode extends ASTExpr {
    public String lexeme;
    public ASTExpr expr;

    public ASTUnaryNode(String lexeme, ASTExpr expr) {
        this.lexeme = lexeme;
        this.expr = expr;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public ASTExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTExpr expr) {
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }
}
