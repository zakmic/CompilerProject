package com.zakmicallef.Visitor;

import com.zakmicallef.AST.ASTFuncDecl;
import com.zakmicallef.AST.ASTType;
import com.zakmicallef.Exception.InterpreterError;

public class Value {
    double floatVal;
    boolean boolVal;
    int intVal;
    ASTFuncDecl func;
    ASTType.Type type;

    public Value(Value val) {
        this.floatVal = val.floatVal;
        this.boolVal = val.boolVal;
        this.intVal = val.intVal;
        this.func = val.func;
        this.type = val.type;
    }

    public Value(double floatVal, boolean boolVal, int intVal, ASTFuncDecl func, ASTType.Type type) {
        this.floatVal = floatVal;
        this.boolVal = boolVal;
        this.intVal = intVal;
        this.func = func;
        this.type = type;
    }

    public static void print(Value val) {
        System.out.println("Printing: " + val.type);
        switch (val.type) {
            case Float:
                System.out.println(val.floatVal);
                break;
            case Auto:
                System.out.println("Auto Val");
                break;
            case Int:
                System.out.println(val.intVal);
                break;
            case Bool:
                System.out.println(val.boolVal);
                break;
            case Func:
                System.out.println(val.func.id);
                break;
            default:
                System.out.println("Invalid Print");
        }
    }

    @Override
    public String toString() {
        return
                +floatVal + "" + boolVal + "" + intVal + "" + func + "" + type;
    }

    public Value() {
    }

    public Value(int intVal, ASTType.Type type) {
        this.intVal = intVal;
        this.type = type;
    }

    public Value(double floatVal, ASTType.Type type) {
        this.floatVal = floatVal;
        this.type = type;
    }

    public Value(boolean boolVal, ASTType.Type type) {
        this.boolVal = boolVal;
        this.type = type;
    }

    public Value(ASTFuncDecl func, ASTType.Type type) {
        this.func = func;
        this.type = type;
    }

    public void setVal(double floatVal) {
        this.floatVal = floatVal;
        this.type = ASTType.Type.Float;
    }

    public void setVal(ASTFuncDecl func) {
        this.func = func;
        this.type = ASTType.Type.Func;
    }

    public void setVal(int intVal) {
        this.intVal = intVal;
        this.type = ASTType.Type.Int;
    }

    public void setVal(boolean boolVal) {
        this.boolVal = boolVal;
        this.type = ASTType.Type.Bool;
    }

    public double getNumVal() {
        switch (type) {
            case Float:
                return floatVal;
            case Int:
                return intVal;
            case Bool:
                throw new InterpreterError("Type of ID is Bool not Numeric");
            case Func:
                throw new InterpreterError("ID is a function call not a Numeric value");
            case Auto:
                throw new InterpreterError("Auto not implemented");
        }
        throw new InterpreterError("Invalid Variable Retrieval");
    }

    public ASTType.Type getType() {
        if (intVal != 0) {
            return ASTType.Type.Int;
        } else if (floatVal != 0) {
            return ASTType.Type.Float;
        } else if (func != null) {
            return ASTType.Type.Func;
        } else {
            return ASTType.Type.Bool;
        }
    }
}
