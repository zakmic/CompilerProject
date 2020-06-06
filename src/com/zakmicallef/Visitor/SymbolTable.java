package com.zakmicallef.Visitor;

import com.zakmicallef.AST.ASTFormalParam;
import com.zakmicallef.AST.ASTFormalParams;
import com.zakmicallef.AST.ASTType;
import com.zakmicallef.Exception.SemanticException;
import com.zakmicallef.Exception.SymbolTableError;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Stack;

public class SymbolTable {
    //Each map represents a scope
    HashMap<String, SymbolEntry> map = new HashMap<>();
    private Stack<HashMap<String, SymbolEntry>> scopeStack = new Stack<>();

    public SymbolTable() {
        scopeStack.push(new HashMap<>());
    }

    void push() {
        scopeStack.push(map);
    }

    public void newScope() {
        scopeStack.push(new HashMap<>());
    }

    public void exitScope() {
        if (scopeStack.empty()) {
            throw new SymbolTableError("Invalid Operation: Can't remove global Scope ");
        }
        scopeStack.pop();
    }

    void insert(String key, ASTType.Type type) {
        ASTFormalParams formalParams = new ASTFormalParams();
        SymbolEntry value = new SymbolEntry(type, formalParams);
        HashMap<String, SymbolEntry> top = scopeStack.peek();

        if (map.get(key) == top.get(key)) {
            top.put(key, value);
        } else {
            throw new SymbolTableError("ID already exists in current scope");
        }
    }

    void insert(String key, ASTType.Type type, ASTFormalParams formalParams) {
        SymbolEntry value = new SymbolEntry(type, formalParams);
        HashMap<String, SymbolEntry> top = scopeStack.peek();

        if (map.get(key) == top.get(key)) {
            top.put(key, value);
        } else {
            throw new SymbolTableError("ID already exists in current scope");
        }
    }

    int lookup(String key) {
//        if (scopeStack.size() == 1) {
//            if (scopeStack.get(0).get(key) != null) {
//                return 1;
//            }
//        } else {
            for (int i = scopeStack.size() - 1; i >= 0; i--) {
                if (scopeStack.get(i).get(key) != null) {
                    return i;
                }
            }
//        }

        return -1;
    }

    SymbolEntry getValue(String key) {
        if (lookup(key) == -1) {
            throw new SymbolTableError("ID doesn't exist in symbol table");
        }
        System.out.println(lookup(key));
        System.out.println("Retting" + (scopeStack.size() - lookup(key) - 1));
        return scopeStack.get(lookup(key)).get(key);
    }

    public void pop() {
        if (scopeStack.size() > 1) {
            scopeStack.pop();
        } else {
            throw new SemanticException("Invalid POP of Global Scope");
        }
    }
}





