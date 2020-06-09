package com.zakmicallef.Visitor;

import com.zakmicallef.AST.*;
import com.zakmicallef.Exception.InterpreterError;
import com.zakmicallef.Exception.SemanticException;

import java.util.ArrayList;

public class InterpreterExecution {
    Value currentValue = new Value();
    boolean blockScope = true;
    boolean functionReturned = false;
    SymbolValueTable table = new SymbolValueTable();

    public void visit(ASTProgramNode node) {

        System.out.println("Interpreter");
        blockScope = true;
        functionReturned = false;

        try {
            for (ASTstsmt stmt : node.stms) {
                stmt.accept(this);
            }
        } catch (InterpreterError err) {
            err.getStackTrace();
            err.getMessage();
        }
    }

    public void visit(ASTBinExprNode node) {
        node.term.accept(this);
        Value val1 = new Value(currentValue.floatVal, currentValue.boolVal, currentValue.intVal, currentValue.func, currentValue.type);
        node.simpleExpr.accept(this);
        Value val2 = new Value(currentValue.floatVal, currentValue.boolVal, currentValue.intVal, currentValue.func, currentValue.type);


        String op = node.getLexeme();
        switch (op) {
            case "and":
                currentValue.setVal(val1.boolVal && val2.boolVal);
                return;
            case "or":
                currentValue.setVal(val1.boolVal || val2.boolVal);
                return;
            case "+":
                currentValue.setVal(val1.getNumVal() + val2.getNumVal());
                break;
            case "-":
                currentValue.setVal(val1.getNumVal() - val2.getNumVal());
                break;
            case "*":
                currentValue.setVal(val1.getNumVal() * val2.getNumVal());
                break;
            case "/":
                currentValue.setVal(val1.getNumVal() / val2.getNumVal());
                break;
            case ">":
                currentValue.setVal(val1.getNumVal() > val2.getNumVal());
                return;
            case ">=":
                currentValue.setVal(val1.getNumVal() >= val2.getNumVal());
                return;
            case "<":
                currentValue.setVal(val1.getNumVal() < val2.getNumVal());
                return;
            case "<=":
                currentValue.setVal(val1.getNumVal() <= val2.getNumVal());
                return;
            case "==":
                currentValue.setVal(val1.getNumVal() == val2.getNumVal());
                return;
            case "!=":
                currentValue.setVal(val1.getNumVal() != val2.getNumVal());
                return;
            default:
                throw new InterpreterError("Unrecognized Operator");
        }
    }

    public void visit(ASTSubExprNode node) {
        node.exps.accept(this);
    }

    public void visit(ASTBool node) {
        currentValue.setVal(node.value);
    }

    public void visit(ASTIntegerNode node) {
        currentValue.setVal(node.value);
    }

    public void visit(ASTFloatNode node) {
        currentValue.setVal(node.value);
    }

    public void visit(ASTAuto node) {
        if (node.isFloat) {
            currentValue.setVal(node.floatVal);
        } else if (node.isBool) {
            currentValue.setVal(node.boolVal);
        } else if (node.isInt) {
            currentValue.setVal(node.intVal);
        } else {
            throw new InterpreterError("Unable to deferr Auto Node");
        }
    }

    public void visit(ASTUnaryNode node) {
        node.expr.accept(this);

        switch (currentValue.type) {
            case Bool:
                currentValue.setVal(!currentValue.boolVal);
                break;
            case Float:
                currentValue.setVal(-currentValue.floatVal);
                break;
            case Int:
                currentValue.setVal(-currentValue.intVal);
                break;
            case Auto:
                break;
            default:
                throw new InterpreterError("Unary Invalid " + node.lexeme);
        }
    }

    public void visit(ASTidNode node) {
        currentValue = new Value(table.getValue(node.lexeme).value);
    }

    public void visit(ASTPrint node) {
        node.expr.accept(this);
        Value.print(currentValue);
    }

    public void visit(ASTFuncCallNode node) {
        SymbolValue value = table.getValue(node.id.lexeme);
        ArrayList<ASTExpr> exprs = node.params.params;
        ArrayList<ASTFormalParam> parmas = value.formalParams.astFormalParams;
        table.newScope();
        for (int i = 0; i < parmas.size(); i++) {
            exprs.get(i).accept(this);
            table.insert(parmas.get(i).lexeme.lexeme, new Value(currentValue.floatVal, currentValue.boolVal, currentValue.intVal, currentValue.func, currentValue.type));
        }

        for (ASTstsmt stmt : value.value.func.block.stmts) {
            stmt.accept(this);
            if (functionReturned) {
                break;
            }
        }

        if (!functionReturned) {
            throw new InterpreterError("Function " + value.value.func.id.lexeme + "didnt return value");
        }
        functionReturned = false;

        //Last val should be return
        table.pop();
    }

    public void visit(ASTActualParams node) {
    }

    public void visit(ASTVarNode node) {
        node.getExpression().accept(this);
        table.insert(node.getId().lexeme, new Value(currentValue.floatVal, currentValue.boolVal, currentValue.intVal, currentValue.func, currentValue.type));
    }

    public void visit(ASTAssignment node) {
        node.expr.accept(this);
        table.edit(node.id.lexeme, new Value(currentValue.floatVal, currentValue.boolVal, currentValue.intVal, currentValue.func, currentValue.type));
    }

    public void visit(ASTIf node) {
        node.expr.accept(this);
        if (currentValue.boolVal) {
            node.ifBlock.accept(this);
        } else if (node.elseBlock != null) {
            node.elseBlock.accept(this);
        }
    }

    public void visit(ASTWhile node) {
        while (true) {
            node.expr.accept(this);
            if (currentValue.boolVal) {
                node.block.accept(this);
            } else {
                break;
            }
        }
    }

    public void visit(ASTFor node) {
        if (node.var != null) {
            node.var.accept(this);
        }
        while (true) {
            node.expr.accept(this);
            if (currentValue.boolVal) {
                if (node.assignment != null) {
                    node.assignment.accept(this);
                }
                node.block.accept(this);
            } else {
                break;
            }
        }
    }

    public void visit(ASTReturn node) {
        node.expr.accept(this);
        functionReturned = true;
    }

    public void visit(ASTFuncDecl node) {
        Value block = new Value();
        block.setVal(node);
        table.insert(node.id.lexeme, block, node.params);
    }

    public void visit(ASTFormalParams node) {
    }

    public void visit(ASTFormalParam node) {
    }

    public void visit(ASTBlock node) {
        boolean newScope = blockScope;
        blockScope = true;

        if (newScope) {
            table.newScope();
        }

        for (ASTstsmt stms : node.stmts) {
            stms.accept(this);
        }

        if (blockScope) {
            table.pop();
        }
    }
}
