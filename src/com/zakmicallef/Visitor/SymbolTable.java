package com.zakmicallef.Visitor;

import com.zakmicallef.AST.ASTFormalParams;
import com.zakmicallef.AST.ASTType;
import com.zakmicallef.Exception.SemanticException;
import com.zakmicallef.Exception.SymbolTableError;

import java.util.*;


public class SymbolTable {
    //Each map represents a scope
    HashMap<String, SymbolEntry> map = new HashMap<>();
    private Stack<HashMap<String, SymbolEntry>> scopeStack = new Stack<>();

    public SymbolTable() {
        scopeStack.push(new HashMap<>());
    }


    public void pop() {
        if (scopeStack.size() > 1) {
//            scopeStack.get(scopeStack.size() - 1).clear();
            scopeStack.pop();
        } else {
            throw new SymbolTableError("Invalid POP of Global Scope");
        }
    }


    public void push() {
        scopeStack.push(new HashMap<>(map));
    }

    //    void push() {
//        HashMap<String, SymbolEntry> newMap = new HashMap<>(map);
//        scopeStack.push(newMap);
//    }


    void insert(String key, ASTType.Type type) {
        ASTFormalParams formalParams = new ASTFormalParams();
        SymbolEntry value = new SymbolEntry(type, formalParams);
        HashMap<String, SymbolEntry> top = scopeStack.peek();
        

        if (top.get(key) == null || map.get(key) == top.get(key)) {
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

//    int lookup(String key) {
//        for (int i = scopeStack.size() - 1; i >= 0; i--) {
//            if (scopeStack.get(i).get(key) != null) {
//                return i;
//            }
//        }
//        return -1;
//    }

    int lookup(String key) {
        int scopeCount = 1;
        for (int i = scopeStack.size() - 1; i >= 0; i--) {
            if (scopeStack.get(i).get(key) != null) {
                return scopeCount;
            }
            scopeCount++;
        }
        return -1;
    }


    SymbolEntry getValue(String key) {
        if (lookup(key) == -1) {
            throw new SymbolTableError("ID doesn't exist in symbol table");
        }
        System.out.println(lookup(key));
//        System.out.println("Retting" + (scopeStack.size() - lookup(key) - 1));
        return scopeStack.get(scopeStack.size() - lookup(key)).get(key);
    }
}





