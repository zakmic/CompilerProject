package com.zakmicallef.Visitor;

import com.zakmicallef.AST.ASTFormalParams;
import com.zakmicallef.AST.ASTType;

public class SymbolValue {
    Value value;
    ASTFormalParams formalParams;

    public SymbolValue() {
    }

    public SymbolValue(Value value, ASTFormalParams formalParams) {
        this.value = value;
        this.formalParams = formalParams;
    }

}
