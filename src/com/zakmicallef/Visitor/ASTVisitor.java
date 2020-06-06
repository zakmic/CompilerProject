package com.zakmicallef.Visitor;

import com.zakmicallef.AST.*;

import java.util.ArrayList;

public class ASTVisitor {
    public ArrayList<String> str = new ArrayList<>();
    String indentation = "";

    void removeLastChar() {
        indentation = indentation.substring(0, indentation.length() - 1);
    }

    public void visit(ASTProgramNode node) {
        str.add("<AbstractSyntaxTree>");
        indentation += '\t';
        for (ASTstsmt stmt : node.stms) {
            stmt.accept(this);
        }
        removeLastChar();
        str.add("</AbstractSyntaxTree>");
    }

    public void visit(ASTVarNode node) {
        str.add(indentation + "<Variable>");

        indentation += '\t';
        node.getId().accept(this);

        str.add(indentation + "<Expression>");
        node.getExpression().accept(this);
        removeLastChar();
        str.add(indentation + "</Expression>");
//        removeLastChar();

        str.add(indentation + "</Variable>");
    }

    public void visit(ASTAssignment node) {
        str.add(indentation + "<Assignment>");
        indentation += '\t';

        node.id.accept(this);
        indentation += '\t';

        node.expr.accept(this);

        removeLastChar();
        str.add(indentation + "</Assignment>");
    }

    public void visit(ASTPrint node) {
        str.add(indentation + "<Print>");
        indentation += '\t';

        node.expr.accept(this);

        removeLastChar();
        str.add(indentation + "</Print>");
    }

    public void visit(ASTIf node) {
        str.add(indentation + "<If>");
        indentation += '\t';

        node.expr.accept(this);
        node.ifBlock.accept(this);

        removeLastChar();
        str.add(indentation + "</If>");
    }

    public void visit(ASTFor node) {
        str.add(indentation + "<For>");
        indentation += '\t';
        if (node.var != null) {
            node.var.accept(this);
        }
        node.expr.accept(this);
        if (node.assignment != null) {
            node.assignment.accept(this);
        }
        node.block.accept(this);
        removeLastChar();
        str.add(indentation + "</For>");
    }

    public void visit(ASTWhile node) {
        str.add(indentation + "<While>");
        indentation += '\t';

        node.expr.accept(this);
        node.block.accept(this);

        removeLastChar();
        str.add(indentation + "</While>");
    }

    public void visit(ASTReturn node) {
        str.add(indentation + "<Return>");
        indentation += '\t';

        node.expr.accept(this);

        removeLastChar();
        str.add(indentation + "</Return>");
    }

    public void visit(ASTFuncDecl node) {
        str.add(indentation + "<FunctionDecl>");
        indentation += '\t';

        node.id.accept(this);
        node.params.accept(this);
        node.block.accept(this);
        //Type

        removeLastChar();
        str.add(indentation + "</FunctionDecl>");
    }

    public void visit(ASTBlock node) {
        str.add(indentation + "<Block>");
        indentation += '\t';

        for (ASTstsmt stmt : node.stmts) {
            stmt.accept(this);
        }

        removeLastChar();
        str.add(indentation + "</Block>");
    }

    public void visit(ASTFormalParams node) {
        str.add(indentation + "<FormalParams>");
        indentation += '\t';

        for (ASTFormalParam param : node.astFormalParams) {
            param.accept(this);
        }

        removeLastChar();
        str.add(indentation + "</FormalParams>");
    }

    public void visit(ASTFormalParam node) {
        str.add(indentation + "<FormalParam>");
        indentation += '\t';

        node.lexeme.accept(this);
        //Type

        removeLastChar();
        str.add(indentation + "</FormalParam>");
    }

    public void visit(ASTBinExprNode node) {
        str.add(indentation + "<BinaryExpression>");
        indentation += '\t';

        node.simpleExpr.accept(this);
        node.term.accept(this);
        str.add(indentation + "Binary: " + node.getLexeme());

        removeLastChar();
        str.add(indentation + "</BinaryExpression>");
    }

    public void visit(ASTBool node) {
        String boolStr = node.value ? "true" : "false";

        str.add(indentation + "<Boolean>");
        indentation += '\t';
        str.add(indentation + boolStr);
        removeLastChar();
        str.add(indentation + "</Boolean>");
    }

    public void visit(ASTIntegerNode node) {
        str.add(indentation + "<Integer>");
        indentation += '\t';
        str.add(indentation + node.value);
        removeLastChar();
        str.add(indentation + "</Integer>");
    }

    public void visit(ASTAuto node) {
        str.add(indentation + "<Auto>");
        indentation += '\t';
        if (node.isBool) {
            str.add(indentation + node.boolVal);
        } else if (node.isInt) {
            str.add(indentation + node.intVal);
        } else if (node.isFloat) {
            str.add(indentation + node.floatVal);
        }
        removeLastChar();
        str.add(indentation + "</Auto>");
    }


    public void visit(ASTFloatNode node) {
        str.add(indentation + "<Float>");
        indentation += '\t';
        str.add(indentation + node.value);
        removeLastChar();
        str.add(indentation + "</Float>");
    }

    public void visit(ASTidNode node) {
        str.add(indentation + "<Identifier>");
        indentation += '\t';
        str.add(indentation + node.getLexeme());
        removeLastChar();
        str.add(indentation + "</Identifier>");
    }

    public void visit(ASTFuncCallNode node) {
        str.add(indentation + "<FunctionCall>");
        indentation += '\t';

        node.id.accept(this);
        node.params.accept(this);

        removeLastChar();
        str.add(indentation + "</FunctionCall>");
    }

    public void visit(ASTActualParams node) {
        str.add(indentation + "<ActualParameter>");
        indentation += '\t';

        for (ASTExpr param : node.params) {
            param.accept(this);
        }

        removeLastChar();
        str.add(indentation + "</ActualParameter>");
    }

    public void visit(ASTSubExprNode node) {
        str.add(indentation + "<SubExpr>");
        indentation += '\t';

        node.getExps().accept(this);

        removeLastChar();
        str.add(indentation + "</SubExpr>");
    }

    public void visit(ASTUnaryNode node) {
        str.add(indentation + "<Unary>");
        str.add(indentation + "OP: " + node.getLexeme());

        indentation += '\t';
        node.expr.accept(this);
        removeLastChar();

        str.add(indentation + "</Unary>");
    }

}
