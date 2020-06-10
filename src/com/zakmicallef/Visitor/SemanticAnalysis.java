package com.zakmicallef.Visitor;

import com.zakmicallef.AST.ASTType;
import com.zakmicallef.AST.*;
import com.zakmicallef.Exception.InterpreterError;
import com.zakmicallef.Exception.SemanticException;
import com.zakmicallef.Exception.SymbolTableError;

import java.util.ArrayList;

import static com.zakmicallef.Parser.getType;

public class SemanticAnalysis {

    SymbolTable table = new SymbolTable();
    ASTType.Type currentType;
    ArrayList<ASTType.Type> typeList = new ArrayList<>();
    boolean blockScope;

    public void visit(ASTProgramNode node) {
        blockScope = true;
        try {
            for (ASTstsmt stmt : node.stms) {
                stmt.accept(this);
            }
        } catch (InterpreterError err) {
//            try {
//                while (true) {
//                    table.pop();
//                }
//            } catch (SymbolTableError st) {
//                st.getCause();
//            }
        }
    }

    public void visit(ASTBool node) {
        currentType = ASTType.Type.Bool;
    }

    public void visit(ASTIntegerNode node) {
        currentType = ASTType.Type.Int;
    }

    public void visit(ASTFloatNode node) {
        currentType = ASTType.Type.Float;
    }

    public void visit(ASTAuto node) {
        currentType = ASTType.Type.Auto;
    }

    public void visit(ASTBinExprNode node) {
        node.simpleExpr.accept(this);
        ASTType.Type type1 = currentType;

        node.term.accept(this);
        ASTType.Type type2 = currentType;

        String op = node.lexeme;
        //AND, OR only applied to booleans
        if (op.equals("and") || op.equals("or")) {
            if (type1 != ASTType.Type.Bool || type2 != ASTType.Type.Bool) {
                throw new SemanticException("Boolean Operators can only be applied to Booleans");
            }
            currentType = ASTType.Type.Bool;
        } else if (op.equals("+") || (op.equals("-")) || (op.equals("*")) || op.equals("/")) {
            if (type1 == ASTType.Type.Float || type2 == ASTType.Type.Float) {
                currentType = ASTType.Type.Float;
            } else {
                currentType = ASTType.Type.Int;
            }
        } else if (op.charAt(0) == '>' || op.charAt(0) == '<' || op.equals("==") || op.equals("!=")) {
            //Cannot be applied to Boolean
            if (type1 == ASTType.Type.Bool || type2 == ASTType.Type.Bool) {
                throw new SemanticException("Relation Operators cannot be applied to booleans");
            }
            currentType = ASTType.Type.Bool;
        } else {
            throw new SemanticException("Invalid Operator");
        }
    }

    public void visit(ASTUnaryNode node) {
        String op = node.getLexeme();
        if (op.equals("not") && currentType != ASTType.Type.Bool) {
            throw new SemanticException("Boolean Operator Not can only be applied to Booleans");
        } else if (op.equals("-") && (currentType == ASTType.Type.Bool)) {
            throw new SemanticException("Boolean Operator Not can only be applied to Booleans");
        } else if (!op.equals("not") && !op.equals("-")) {
            throw new SemanticException("Unary Operator not recognized");
        }
    }

    public void visit(ASTSubExprNode node) {
        node.getExps().accept(this);
    }

    public void visit(ASTidNode node) {
        exists(node);

        SymbolEntry value = table.getValue(node.lexeme);
        if (value.formalParams != null) {
            if (value.formalParams.astFormalParams.size() != 0) {
                throw new SemanticException("Function already used");
            }
        }
        currentType = value.type;
    }

    public void visit(ASTFuncCallNode node) {
        exists(node.id);
        SymbolEntry value = table.getValue(node.id.lexeme);
        ASTFormalParams formalParams = value.formalParams;

        if (formalParams == null) {
            throw new SemanticException("Invalid Function Call. No formal params");
        } else if (node.params.params.size() != formalParams.astFormalParams.size()) {
            throw new SemanticException("Function has incorrect Number of args");
        }

        int paramArg = 0;
        for (ASTExpr params : node.params.params) {
            params.accept(this);
            ASTFormalParam formalParam = formalParams.astFormalParams.get(paramArg++);
            System.out.println(formalParam.type + " " + formalParam.lexeme.lexeme);
            if (currentType != getType(formalParam.type)) {
                throw new SemanticException("Incorrect Function.");
            }
        }
        currentType = value.type;
    }

    public void visit(ASTActualParams node) {
    }

    public void visit(ASTVarNode node) {
        isntDuplicate(node.getId());
        node.getExpression().accept(this);
        ASTType.Type expected = getType(node.getType());
        if (!matchType(currentType, expected)) {
            throw new SemanticException("Invalid Type");
        }
        table.insert(node.getId().lexeme, getType(node.getType()));
    }

    private boolean matchType(ASTType.Type currentType, ASTType.Type expected) {
        if (expected == ASTType.Type.Auto) {
            return true;
        } else {
            return expected == currentType;
        }
    }

    public void visit(ASTAssignment node) {

        exists(node.id);
        node.expr.accept(this);

        ASTType.Type expected = table.getValue(node.id.lexeme).type;
        if (currentType != expected) {
            throw new SemanticException("");
        }
    }

    public void visit(ASTReturn node) {
        node.expr.accept(this);
        if (typeList.size() == 0) {
            throw new SymbolTableError("Invalid Return");
        } else if (currentType != typeList.get(typeList.size() - 1)) {
            throw new SymbolTableError("Invalid Return Type");
        }
    }

    public void visit(ASTIf node) {
        node.expr.accept(this);

        if (currentType != ASTType.Type.Bool)
            throw new SemanticException("If statement requires boolean");

        node.ifBlock.accept(this);

        if (node.elseBlock != null)
            node.elseBlock.accept(this);
    }

    public void visit(ASTFor node) {
        if (node.var != null) {
            node.var.accept(this);
        }
        node.expr.accept(this);
        if (currentType != ASTType.Type.Bool) {
            throw new SemanticException("For expression requires boolean");
        }
        if (node.assignment != null) {
            node.assignment.accept(this);
        }
        node.block.accept(this);
    }

    public void visit(ASTWhile node) {
        node.expr.accept(this);
        if (currentType != ASTType.Type.Bool) {
            throw new SemanticException("");
        }
        node.block.accept(this);
    }

    public void visit(ASTFuncDecl node) {
        isntDuplicate(node.id);
        table.insert(node.id.lexeme, getType(node.type), node.params);

//        table.push();
        table.push();
        typeList.add(getType(node.type));
        for (ASTFormalParam param : node.params.astFormalParams) {
            table.insert(param.lexeme.lexeme, getType(param.type));
        }

        blockScope = false;
        node.block.accept(this);

        table.pop();
        typeList.remove(typeList.size() - 1);

    }

    public void visit(ASTFormalParams node) {
    }

    public void visit(ASTFormalParam node) {
    }

    public void visit(ASTBlock node) {
        boolean currentScope = blockScope;
        blockScope = true;

        if (currentScope) {
//            table.push();
            table.push();
        }

        for (ASTstsmt stmt : node.stmts) {
            stmt.accept(this);
        }
        if (currentScope) {
            table.pop();
        }
    }

    private void exists(ASTidNode node) {
        System.out.println("Exists Lexeme: " + node.lexeme);
        if (table.lookup(node.lexeme) == -1) {
            throw new SemanticException("ID not Found");
        }
    }


    private void isntDuplicate(ASTidNode node) {
        System.out.println("Lexeme dup " + node.lexeme);
        if (table.lookup(node.lexeme) == 1) {
            throw new SemanticException("Duplicate Declaration of: " + node.lexeme);
        }
    }

    public void visit(ASTPrint node) {
        node.expr.accept(this);
    }
}
