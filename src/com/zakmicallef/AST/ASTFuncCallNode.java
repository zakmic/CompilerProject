package com.zakmicallef.AST;

public class ASTFuncCallNode extends ASTExpr {
    String id;
    ASTActualParams params;

    public ASTFuncCallNode(String id, ASTActualParams params) {
        this.id = id;
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ASTActualParams getParams() {
        return params;
    }

    public void setParams(ASTActualParams params) {
        this.params = params;
    }
}
