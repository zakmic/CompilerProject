package com.zakmicallef.Visitor;

import com.zakmicallef.AST.*;

import java.util.ArrayList;

public class XMLVisitor {
    public ArrayList<String> xml = new ArrayList<>();
    String indentation = "";

    void removeLastChar() {
        indentation = indentation.substring(0, indentation.length() - 1);
    }

    public void visit(ASTProgramNode node) {
        xml.add("<AbstractSyntaxTree>");
        indentation += '\t';
        for (ASTstsmt stmt : node.stms) {
            stmt.accept(this);
        }
        removeLastChar();
        xml.add("</AbstractSyntaxTree>");
    }

    public void visit(ASTVarNode node) {
        xml.add(indentation + "<Variable>");

        indentation += '\t';
        node.getId().accept(this);

        xml.add(indentation + "<Expression>");
        node.getExpression().accept(this);
        removeLastChar();
        xml.add(indentation + "</Expression>");
//        removeLastChar();

        xml.add(indentation + "</Variable>");
    }

    public void visit(ASTAssignment node) {
        xml.add(indentation + "<Assignment>");
        indentation += '\t';

        node.id.accept(this);
        indentation += '\t';

        node.expr.accept(this);

        removeLastChar();
        xml.add(indentation + "</Assignment>");
    }

    public void visit(ASTPrint node) {
        xml.add(indentation + "<Print>");
        indentation += '\t';

        node.expr.accept(this);

        removeLastChar();
        xml.add(indentation + "</Print>");
    }

    public void visit(ASTIf node) {
        xml.add(indentation + "<If>");
        indentation += '\t';

        node.expr.accept(this);
        node.ifBlock.accept(this);

        removeLastChar();
        xml.add(indentation + "</If>");
    }

    public void visit(ASTFor node) {
        xml.add(indentation + "<For>");
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
        xml.add(indentation + "</For>");
    }

    public void visit(ASTWhile node) {
        xml.add(indentation + "<While>");
        indentation += '\t';

        node.expr.accept(this);
        node.block.accept(this);

        removeLastChar();
        xml.add(indentation + "</While>");
    }

    public void visit(ASTReturn node) {
        xml.add(indentation + "<Return>");
        indentation += '\t';

        node.expr.accept(this);

        removeLastChar();
        xml.add(indentation + "</Return>");
    }

    public void visit(ASTFuncDecl node) {
        xml.add(indentation + "<FunctionDecl>");
        indentation += '\t';

        node.id.accept(this);
        node.params.accept(this);
        node.block.accept(this);
        //Type

        removeLastChar();
        xml.add(indentation + "</FunctionDecl>");
    }

    public void visit(ASTBlock node) {
        xml.add(indentation + "<Block>");
        indentation += '\t';

        for (ASTstsmt stmt : node.stmts) {
            stmt.accept(this);
        }

        removeLastChar();
        xml.add(indentation + "</Block>");
    }

    public void visit(ASTFormalParams node) {
        xml.add(indentation + "<FormalParams>");
        indentation += '\t';

        for (ASTFormalParam param : node.astFormalParams) {
            param.accept(this);
        }

        removeLastChar();
        xml.add(indentation + "</FormalParams>");
    }

    public void visit(ASTFormalParam node) {
        xml.add(indentation + "<FormalParam>");
        indentation += '\t';

        node.lexeme.accept(this);
        //Type

        removeLastChar();
        xml.add(indentation + "</FormalParam>");
    }

    public void visit(ASTBinExprNode node) {
        xml.add(indentation + "<BinaryExpression>");
        indentation += '\t';

        node.simpleExpr.accept(this);
        node.term.accept(this);
        xml.add(indentation + "Binary: " + node.getLexeme());

        removeLastChar();
        xml.add(indentation + "</BinaryExpression>");
    }

    public void visit(ASTBool node) {
        String boolStr = node.value ? "true" : "false";

        xml.add(indentation + "<Boolean>");
        indentation += '\t';
        xml.add(indentation + boolStr);
        removeLastChar();
        xml.add(indentation + "</Boolean>");
    }

    public void visit(ASTIntegerNode node) {
        xml.add(indentation + "<Integer>");
        indentation += '\t';
        xml.add(indentation + node.value);
        removeLastChar();
        xml.add(indentation + "</Integer>");
    }

    public void visit(ASTAuto node) {
        xml.add(indentation + "<Auto>");
        indentation += '\t';
        if (node.isBool) {
            xml.add(indentation + node.boolVal);
        } else if (node.isInt) {
            xml.add(indentation + node.intVal);
        } else if (node.isFloat) {
            xml.add(indentation + node.floatVal);
        }
        removeLastChar();
        xml.add(indentation + "</Auto>");
    }


    public void visit(ASTFloatNode node) {
        xml.add(indentation + "<Float>");
        indentation += '\t';
        xml.add(indentation + node.value);
        removeLastChar();
        xml.add(indentation + "</Float>");
    }

    public void visit(ASTidNode node) {
        xml.add(indentation + "<Identifier>");
        indentation += '\t';
        xml.add(indentation + node.getLexeme());
        removeLastChar();
        xml.add(indentation + "</Identifier>");
    }

    public void visit(ASTFuncCallNode node) {
        xml.add(indentation + "<FunctionCall>");
        indentation += '\t';

        node.id.accept(this);
        node.params.accept(this);

        removeLastChar();
        xml.add(indentation + "</FunctionCall>");
    }

    public void visit(ASTActualParams node) {
        xml.add(indentation + "<ActualParameter>");
        indentation += '\t';

        for (ASTExpr param : node.params) {
            param.accept(this);
        }

        removeLastChar();
        xml.add(indentation + "</ActualParameter>");
    }

    public void visit(ASTSubExprNode node) {
        xml.add(indentation + "<SubExpr>");
        indentation += '\t';

        node.getExps().accept(this);

        removeLastChar();
        xml.add(indentation + "</SubExpr>");
    }

    public void visit(ASTUnaryNode node) {
        xml.add(indentation + "<Unary>");
        xml.add(indentation + "OP: " + node.getLexeme());

        indentation += '\t';
        node.expr.accept(this);
        removeLastChar();

        xml.add(indentation + "</Unary>");
    }

}
