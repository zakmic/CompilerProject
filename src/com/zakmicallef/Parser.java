package com.zakmicallef;

import com.zakmicallef.AST.ASTProgramNode;
import com.zakmicallef.AST.ASTstsmt;
import com.zakmicallef.Exception.SyntaxError;

import java.util.ArrayList;

public class Parser {
    public static Token currentToken = Lexer.getNextToken();
    public static Token lookahead = Lexer.getNextToken();

    public static ASTProgramNode parse(){
        ArrayList<ASTstsmt> stms = new ArrayList<ASTstsmt>();

        while(lookahead.getTokenType() != Token.TokenType.TK_EOF){
            try {
                stms.add(parseStmt());
            } catch (SyntaxError syntaxError) {
                syntaxError.printStackTrace();
            }
        }

        return new ASTProgramNode(stms);
    }

    private static ASTstsmt parseStmt() throws SyntaxError {
        switch (lookahead.getTokenType()){
            case TK_KW_Var: // variable declaration
                return parseVariableDeclaration();
            case TK_KW_Let: // assignment
                return parseAssignment();
            case TK_KW_Prnt: // print statement
                return parsePrintStatement();
            case TK_KW_If: // if statement
                return parseIfStatement();
            case TK_KW_While: // while statement
                return parseWhileStatement();
            case TK_KW_Rtrn: // return statement
                return parseReturnStatement();
            case TK_KW_Def: // function declaration
                return parseFuncDeclaration();
            case TK_OpenCurly: // block
                return parseBlock();
            default:
                throw new SyntaxError("Invalid Statement Start");
        }
    }

    private static ASTstsmt parseVariableDeclaration() {
        
    }


}
