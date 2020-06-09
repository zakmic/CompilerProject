package com.zakmicallef.Visitor;

import com.zakmicallef.AST.ASTFormalParam;
import com.zakmicallef.AST.ASTFormalParams;
import com.zakmicallef.Exception.SemanticException;
import com.zakmicallef.Exception.SymbolTableError;
import com.zakmicallef.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static com.zakmicallef.Parser.getType;

public class SymbolValueTable {
    //Each map represents a scope
    HashMap<String, SymbolValue> map = new HashMap<>();
    private Stack<HashMap<String, SymbolValue>> scopeStack = new Stack<>();

    public SymbolValueTable() {
        scopeStack.push(new HashMap<>());
    }

    void push() {
        HashMap<String, SymbolValue> newMap = new HashMap<>(map);
        scopeStack.push(newMap);
    }

    public void pop() {
        if (scopeStack.size() > 1) {
//            removeRefrences(scopeStack.get(scopeStack.size() - 1));
//            scopeStack.get(scopeStack.size() - 1).clear();
            scopeStack.pop();
        } else {
            throw new SemanticException("Invalid POP of Global Scope");
        }
    }


    public void newScope() {
        scopeStack.push(new HashMap<>(map));
    }

    public void exitScope() {
        if (scopeStack.empty()) {
            throw new SymbolTableError("Invalid Operation: Can't remove global Scope ");
        }
        scopeStack.pop();
    }

    void insert(String key, Value val) {
        ASTFormalParams formalParams = new ASTFormalParams();
        SymbolValue value = new SymbolValue(val, formalParams);
        HashMap<String, SymbolValue> top = scopeStack.peek();

        if (top.get(key) == null || map.get(key) == top.get(key)) {
            top.put(key, value);
        } else {
            throw new SymbolTableError("ID already exists in current scope");
        }
    }

    void insert(String key, Value val, ASTFormalParams formalParams) {
        SymbolValue value = new SymbolValue(val, formalParams);
        HashMap<String, SymbolValue> top = scopeStack.peek();

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


    SymbolValue getValue(String key) {
        if (lookup(key) == -1) {
            throw new SymbolTableError("ID doesn't exist in symbol table");
        }
//        System.out.println("Retting" + (scopeStack.size() - lookup(key) - 1));
        return scopeStack.get(scopeStack.size() - lookup(key)).get(key);
    }

    public void edit(String key, Value value) {
        ASTFormalParams params = new ASTFormalParams();
        int scopeCount = lookup(key);
        if (scopeCount == -1) {
            throw new SymbolTableError("ID doesn't exist");
        }
        SymbolValue val = new SymbolValue(value, params);
        scopeStack.get(scopeStack.size() - scopeCount).remove(key);
        scopeStack.get(scopeStack.size() - scopeCount).put(key, val);
    }
}
