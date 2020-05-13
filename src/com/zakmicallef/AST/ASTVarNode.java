package com.zakmicallef.AST;

import com.zakmicallef.Token;

public class ASTVarNode extends ASTstsmt {
    String id;
    String type;
    ASTExprNode expression;

    public ASTVarNode(String id, String type, ASTExprNode expression) {
        this.id = id;
        this.type = type;
        this.expression = expression;
    }
}
