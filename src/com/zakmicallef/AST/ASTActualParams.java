package com.zakmicallef.AST;

import java.util.ArrayList;

public class ASTActualParams extends ASTExprNode {
    ArrayList<ASTExprNode> params;

    public ASTActualParams(ArrayList<ASTExprNode> params) {
        this.params = params;
    }

    public ArrayList<ASTExprNode> getParams() {
        return params;
    }

    public void setParams(ArrayList<ASTExprNode> params) {
        this.params = params;
    }
}
