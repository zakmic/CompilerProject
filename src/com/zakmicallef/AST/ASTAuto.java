package com.zakmicallef.AST;

import com.zakmicallef.Visitor.ASTVisitor;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTAuto extends ASTExpr {
    public double floatVal;
    public boolean boolVal;
    public int intVal;

    public boolean isBool;
    public boolean isInt;
    public boolean isFloat;

    public ASTAuto(double value) {
        super();
        this.isFloat = true;
        this.floatVal = value;
    }

    public ASTAuto(int value) {
        super();
        this.isInt = true;
        this.intVal = value;
    }


    public ASTAuto(boolean value) {
        super();
        this.isBool = true;
        this.boolVal = (boolean) value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

}
