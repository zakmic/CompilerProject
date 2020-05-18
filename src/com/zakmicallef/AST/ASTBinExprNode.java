package com.zakmicallef.AST;

public class ASTBinExprNode extends ASTExpr {
    ASTExpr term;
    ASTExpr simpleExpr;
    String lexeme;

//    public ASTBinExprNode(ASTExpr term, ASTExpr simpleExpr, String lexeme) {
//        super();
//    }


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
}
