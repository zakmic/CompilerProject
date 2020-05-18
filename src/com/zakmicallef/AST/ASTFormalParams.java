package com.zakmicallef.AST;

import java.util.ArrayList;

public class ASTFormalParams extends ASTNode {

    ArrayList<ASTFormalParam> astFormalParams = new ArrayList<>();

    public ASTFormalParams(ArrayList<ASTFormalParam> astFormalParams) {
        this.astFormalParams = astFormalParams;
    }

    public ArrayList<ASTFormalParam> getAstFormalParams() {
        return astFormalParams;
    }

    public void setAstFormalParams(ArrayList<ASTFormalParam> astFormalParams) {
        this.astFormalParams = astFormalParams;
    }
}
