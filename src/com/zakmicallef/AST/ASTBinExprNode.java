package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTBinExprNode extends ASTExpr {
    public ASTExpr term;
    public ASTExpr simpleExpr;
    public String lexeme;


    public ASTBinExprNode(ASTExpr term, ASTExpr simpleExpr, String lexeme) {
        this.term = term;
        this.simpleExpr = simpleExpr;
        this.lexeme = lexeme;
    }

    public ASTExpr getTerm() {
        return term;
    }

    public void setTerm(ASTExpr term) {
        this.term = term;
    }

    public ASTExpr getSimpleExpr() {
        return simpleExpr;
    }

    public void setSimpleExpr(ASTExpr simpleExpr) {
        this.simpleExpr = simpleExpr;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }


    public void accept(SemanticAnalysis visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(InterpreterExecution visitor) { visitor.visit(this); }


}
