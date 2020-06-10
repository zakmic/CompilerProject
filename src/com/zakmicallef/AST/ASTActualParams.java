package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

import java.util.ArrayList;

public class ASTActualParams extends ASTNode {
    public ArrayList<ASTExpr> params;

    public ASTActualParams(ArrayList<ASTExpr> params) {
        this.params = params;
    }

    public void accept(XMLVisitor astVisitor) {
        astVisitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }


    public void accept(InterpreterExecution visitor) { visitor.visit(this); }
}

