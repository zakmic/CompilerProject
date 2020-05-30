package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;

import java.util.ArrayList;

public class ASTFormalParams extends ASTNode {

    public ArrayList<ASTFormalParam> astFormalParams = new ArrayList<>();

    public ASTFormalParams(ArrayList<ASTFormalParam> astFormalParams) {
        this.astFormalParams = astFormalParams;
    }

    public ArrayList<ASTFormalParam> getAstFormalParams() {
        return astFormalParams;
    }

    public void setAstFormalParams(ArrayList<ASTFormalParam> astFormalParams) {
        this.astFormalParams = astFormalParams;
    }

    public void accept(ASTVisitor astVisitor) {
        astVisitor.visit(this);
    }
}
