package com.zakmicallef.Visitor;

import com.zakmicallef.AST.*;

public class ASTVisitor {
    String indentation = "";

    void removeLastChar() {
        indentation = indentation.substring(0, indentation.length() - 1);
    }

    void visit(ASTProgramNode node) {
        System.out.println("<AbstractSyntaxTree>");
        indentation += '\t';
        for (ASTstsmt stmt : node.stms) {
            stmt.accept();
        }
        removeLastChar();
        System.out.println("</AbstractSyntaxTree>");
    }

    void visit(ASTVarNode node) {
        System.out.println(indentation + "<Variable>");

        indentation += '\t';
        node.getId().accept(this);

        System.out.println("Expression");
        node.getExpression().accept(this);
        removeLastChar();
        System.out.println("/Expression");
        removeLastChar();

        System.out.println(indentation + "</Variable>");
    }

    void visit(ASTAssignment node) {
        System.out.println(indentation + "<Assignment>");
        indentation += '\t';

        node.id.accept(this);
        indentation += '\t';

        node.expr.accept(this);

        removeLastChar();
        System.out.println(indentation + "</Assignment>");
    }

    void visit(ASTPrint node) {
        System.out.println(indentation + "<Print>");
        indentation += '\t';

        node.expr.accept(this);

        removeLastChar();
        System.out.println(indentation + "</Print>");
    }

    void visit(ASTIf node) {
        System.out.println(indentation + "<If>");
        indentation += '\t';

        node.expr.accept(this);
        node.ifBlock.accept(this);

        removeLastChar();
        System.out.println(indentation + "</If>");
    }

    void visit(ASTWhile node) {
        System.out.println(indentation + "<While>");
        indentation += '\t';

        node.expr.accept(this);
        node.block.accept(this);

        removeLastChar();
        System.out.println(indentation + "</While>");
    }

    void visit(ASTReturn node) {
        System.out.println(indentation + "<Return>");
        indentation += '\t';

        node.expr.accept(this);

        removeLastChar();
        System.out.println(indentation + "</Return>");
    }

    void visit(ASTFuncDecl node) {
        System.out.println(indentation + "<Function Decl>");
        indentation += '\t';

        node.lexeme.accept(this);
        node.params.accept(this);
        node.block.accept(this);
        //Type

        removeLastChar();
        System.out.println(indentation + "</Function Decl>");
    }

    public void visit(ASTBlock node) {
        System.out.println(indentation + "<Block>");
        indentation += '\t';

        for (ASTstsmt stmt : node.stmts) {
            stmt.accept();
        }

        removeLastChar();
        System.out.println(indentation + "</Block>");
    }

    public void visit(ASTFormalParams node) {
        System.out.println(indentation + "<Formal Params>");
        indentation += '\t';

        for (ASTFormalParam param : node.astFormalParams) {
            param.accept(this);
        }

        removeLastChar();
        System.out.println(indentation + "</Formal Params>");
    }

    public void visit(ASTFormalParam node) {
        System.out.println(indentation + "<Formal Param>");
        indentation += '\t';

        node.lexeme.accept(this);
        //Type

        removeLastChar();
        System.out.println(indentation + "</Formal Param>");
    }

    void visit(ASTBinExprNode node) {
        System.out.println(indentation + "<BinaryExpression>");
        indentation += '\t';

        node.simpleExpr.accept(this);
        node.term.accept(this);
        System.out.println("Binary: " + node.getLexeme());

        removeLastChar();
        System.out.println(indentation + "</BinaryExpression>");
    }

    void visit(ASTBool node) {
        String boolStr = node.value ? "true" : "false";

        System.out.println(indentation + "<Boolean>");
        indentation += '\t';
        System.out.println(boolStr);
        removeLastChar();
        System.out.println(indentation + "</Boolean>");
    }

    void visit(ASTIntegerNode node) {
        System.out.println(indentation + "<Integer>");
        indentation += '\t';
        System.out.println(node.value);
        removeLastChar();
        System.out.println(indentation + "</Integer>");
    }

    void visit(ASTFloatNode node) {
        System.out.println(indentation + "<Float>");
        indentation += '\t';
        System.out.println(node.value);
        removeLastChar();
        System.out.println(indentation + "</Float>");
    }

    void visit(ASTidNode node) {
        System.out.println(indentation + "<Identifier>");
        indentation += '\t';
        System.out.println(node.getLexeme());
        removeLastChar();
        System.out.println(indentation + "</Identifier>");
    }

    void visit(ASTFuncCallNode node) {
        System.out.println(indentation + "<FunctionCall>");
        indentation += '\t';

        node.id.accept(this);
        node.params.accept(this);

        removeLastChar();
        System.out.println(indentation + "</FunctionCall>");
    }

    void visit(ASTActualParams node) {
        System.out.println(indentation + "<Actual Parameter>");
        indentation += '\t';

        for (ASTExpr param : node.params) {
            param.accept(this);
        }

        removeLastChar();
        System.out.println(indentation + "</Actual Parameter>");
    }

    void visit(ASTSubExprNode node) {
        System.out.println(indentation + "<SubExpr>");
        indentation += '\t';

        node.getExps().accept(this);

        removeLastChar();
        System.out.println(indentation + "</SubExpr>");
    }

    void visit(ASTUnaryNode node) {
        System.out.println(indentation + "<Unary>");
        System.out.println("op: " + node.getLexeme());

        indentation += '\t';
        node.expr.accept(this);
        removeLastChar();

        System.out.println(indentation + "</Unary>");
    }

}
