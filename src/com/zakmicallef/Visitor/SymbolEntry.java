package com.zakmicallef.Visitor;

import com.zakmicallef.AST.ASTFormalParams;
import com.zakmicallef.AST.ASTType;

public class SymbolEntry {
    ASTType.Type type;
    ASTFormalParams formalParams;

    public SymbolEntry(ASTType.Type type, ASTFormalParams formalParams) {
        this.type = type;
        this.formalParams = formalParams;
    }
}
