package com.zakmicallef.AST;

import java.util.ArrayList;

public class ASTActualParams extends ASTExpr {
    ArrayList<ASTExpr> params;

    public ASTActualParams(ArrayList<ASTExpr> params) {
        this.params = params;
    }

    public ArrayList<ASTExpr> getParams() {
        return params;
    }

    public void setParams(ArrayList<ASTExpr> params) {
        this.params = params;
    }
}
