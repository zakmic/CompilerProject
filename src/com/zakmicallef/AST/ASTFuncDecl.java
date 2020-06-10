package com.zakmicallef.AST;

import com.zakmicallef.Visitor.XMLVisitor;
import com.zakmicallef.Visitor.InterpreterExecution;
import com.zakmicallef.Visitor.SemanticAnalysis;

public class ASTFuncDecl extends ASTstsmt {

    public ASTidNode id;
    public ASTFormalParams params;
//    public ASTType.Type type;
    public String type;
    public ASTBlock block;

    public ASTFuncDecl(ASTidNode id, ASTFormalParams params, String type, ASTBlock block) {
        this.id = id;
        this.params = params;
        this.type = type;
        this.block = block;
    }

    @Override
    public void accept(XMLVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SemanticAnalysis visitor) { visitor.visit(this); }

    @Override
    public void accept(InterpreterExecution visitor) { visitor.visit(this); }


}
