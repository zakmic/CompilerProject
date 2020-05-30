package com.zakmicallef.AST;

import com.zakmicallef.Token;

import static com.zakmicallef.Parser.getType;

public class ASTAssignment extends ASTstsmt {

    public ASTidNode id;
    public ASTExpr expr;

    public ASTAssignment(ASTidNode id, ASTExpr expr) {
        this.id = id;
        this.expr = expr;
    }


}
