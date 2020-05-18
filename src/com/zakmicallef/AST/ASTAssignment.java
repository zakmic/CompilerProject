package com.zakmicallef.AST;

import com.zakmicallef.Token;

import static com.zakmicallef.Parser.getType;

public class ASTAssignment extends ASTstsmt {

    String id;
    ASTExpr expr;

    public ASTAssignment(String id, ASTExpr expr) {
        this.id = id;
        this.expr = expr;
    }


}
