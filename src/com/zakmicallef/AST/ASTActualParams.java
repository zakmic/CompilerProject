package com.zakmicallef.AST;

import java.util.ArrayList;

public class ASTActualParams extends ASTNode {
    public ArrayList<ASTExpr> params;

    public ASTActualParams(ArrayList<ASTExpr> params) {
        this.params = params;
    }
}
