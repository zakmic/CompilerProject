package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

import java.util.ArrayList;

public class ASTFormalParams extends ASTNode {

    public ArrayList<ASTFormalParam> astFormalParams = new ArrayList<>();

    public ASTFormalParams() {
    }

    public ASTFormalParams(ArrayList<ASTFormalParam> astFormalParams) {
        this.astFormalParams = astFormalParams;
    }

    public ArrayList<ASTFormalParam> getAstFormalParams() {
        return astFormalParams;
    }

    public void setAstFormalParams(ArrayList<ASTFormalParam> astFormalParams) {
        this.astFormalParams = astFormalParams;
    }

    public void accept(XMLVisitor astVisitor) {
        astVisitor.visit(this);
    }
    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

    public void accept(InterpreterExecution visitor) { visitor.visit(this); }


}
