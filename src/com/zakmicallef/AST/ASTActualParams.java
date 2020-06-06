package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.SemanticAnalysis;

import java.util.ArrayList;

public class ASTActualParams extends ASTNode {
    public ArrayList<ASTExpr> params;

    public ASTActualParams(ArrayList<ASTExpr> params) {
        this.params = params;
    }

    public void accept(ASTVisitor astVisitor) {
        astVisitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

}
