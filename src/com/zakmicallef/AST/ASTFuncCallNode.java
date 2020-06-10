package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTFuncCallNode extends ASTExpr {
    public ASTidNode id;
    public ASTActualParams params;

    public ASTFuncCallNode(ASTidNode id, ASTActualParams params) {
        this.id = id;
        this.params = params;
    }

    public ASTidNode getId() {
        return id;
    }

    public void setId(ASTidNode id) {
        this.id = id;
    }

    public ASTActualParams getParams() {
        return params;
    }

    public void setParams(ASTActualParams params) {
        this.params = params;
    }

    @Override
    public void accept(XMLVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

    @Override
    public void accept(InterpreterExecution visitor) { visitor.visit(this); }


}
