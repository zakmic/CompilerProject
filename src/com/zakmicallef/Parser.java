package com.zakmicallef;

import com.zakmicallef.AST.*;
import com.zakmicallef.Exception.SyntaxError;

import java.util.ArrayList;

import static com.zakmicallef.Token.TokenType.*;

public class Parser {
    public static Token lookahead = Lexer.getNextToken();

    public static ASTProgramNode parse() {
        ArrayList<ASTstsmt> stms = new ArrayList<ASTstsmt>();

        while (lookahead.getTokenType() != Token.TokenType.TK_EOF) {
            stms.add(parseStmt());
        }

        return new ASTProgramNode(stms);
    }

    private static Token consume(Token.TokenType type) throws SyntaxError {
        if (lookahead.getTokenType() == type) {
            Token temp = lookahead;
            lookahead = Lexer.getNextToken();
            return temp;
        } else {
            throw new SyntaxError("Unexpected Token");
        }
    }

    private static ASTstsmt parseStmt() throws SyntaxError {
        switch (lookahead.getTokenType()) {
            case TK_KW_Var: // variable declaration
                return parseVariableDeclaration();
            case TK_KW_Let: // assignment
                return parseAssignment();
            case TK_KW_Prnt: // print statement
                return parsePrint();
            case TK_KW_If: // if statement
                return parseIf();
            case TK_KW_While: // while statement
                return parseWhile();
            case TK_KW_For: // while statement
                return parseFor();
            case TK_KW_Rtrn: // return statement
                return parseReturn();
            case TK_KW_Def: // function declaration
                return parseFunctionDeclaration();
            case TK_OpenCurly: // block
                return parseBlock();
            default:
                throw new SyntaxError("Invalid Statement Start");
        }
    }

    private static ASTstsmt parseFor() {
    }

    private static ASTstsmt parseBlock() {
    }

    private static ASTstsmt parseFunctionDeclaration() {
    }

    private static ASTstsmt parseReturn() {
    }

    private static ASTstsmt parseWhile() {
    }

    private static ASTstsmt parseIf() {
    }

    private static ASTstsmt parsePrint() {
    }

    private static ASTstsmt parseAssignment() {
    }


    private static ASTVarNode parseVariableDeclaration() throws SyntaxError {
        consume(TK_KW_Var);
        Token id = consume(TK_Identifier);
        consume(TK_Colon);
        String type = getType(consume(TK_Type).getLexeme());
        ASTExprNode expr = parseExpression();
        consume(TK_Semicolon);
        return new ASTVarNode(id.getLexeme(), type, expr);
    }

    private static ASTExprNode parseFactor() {
        switch (lookahead.getTokenType()) {
            case TK_Bool:
                return parseBool();
            case TK_Integer:
                return new ASTIntegerNode(consume(TK_Integer).getValue());
            case TK_Float:
                return new ASTFloatNode(consume(TK_Float).getValue());
            case TK_Identifier:
                return parseIdentifier();
            case TK_OpenBrck:
                return parseSubExpr();
            case TK_AddOp:
                return parseUnary();
            default:
                throw new SyntaxError("Invalid Factor Token");
        }
    }

    private static ASTExprNode parseExpression() {
        ASTExprNode simpleExpr = parseSimpleExpression();

        if (lookahead.getTokenType() == TK_RelOp) {
            Token op = consume(TK_RelOp);
            ASTExprNode expr = parseExpression();
            return new ASTBinExprNode(simpleExpr, expr, op.getLexeme());
        } else {
            return simpleExpr;
        }

    }

    private static ASTExprNode parseSimpleExpression() {
        ASTExprNode term = parseTerm();

        if (lookahead.getTokenType() == TK_AddOp) {
            Token addOp = consume(TK_AddOp);
            ASTExprNode simpleExpr = parseSimpleExpression();
            return new ASTBinExprNode(term, simpleExpr, addOp.getLexeme());
        } else {
            return term;
        }
    }

    private static ASTExprNode parseTerm() {
        ASTExprNode factor = parseFactor();

        if (lookahead.getTokenType() == TK_AddOp) {
            Token addOp = consume(TK_AddOp);
            ASTExprNode simpleExpr = parseSimpleExpression();
            return new ASTBinExprNode(factor, simpleExpr, addOp.getLexeme());
        } else {
            return factor;
        }
    }

    private static ASTUnaryNode parseUnary() {
        if (lookahead.getLexeme().equalsIgnoreCase("-") ||
                lookahead.getLexeme().equalsIgnoreCase("not")) {
            Token op = consume(TK_RelOp);
            ASTExprNode expr = parseExpression();
            return new ASTUnaryNode(op.getLexeme(), expr);
        } else {
            throw new SyntaxError("Invalid Unary Operator");
        }
    }

    private static ASTSubExprNode parseSubExpr() {
        consume(TK_OpenBrck);
        ASTExprNode expr = parseExpression();
        consume(TK_ClosBrck);
        return new ASTSubExprNode(expr);
    }

    private static ASTExprNode parseIdentifier() {
        Token id = consume(TK_Identifier);

        if (lookahead.getTokenType() == TK_OpenBrck) {
            consume(TK_OpenBrck);
            ASTActualParams actualParams = parseActualParams();
            consume(TK_ClosBrck);
            return new ASTFuncCallNode(id.getLexeme(), actualParams);
        } else {
            return new ASTidNode(id.getLexeme());
        }
    }

    private static ASTActualParams parseActualParams() {
        ArrayList<ASTExprNode> params = new ArrayList<>();

        if (lookahead.getTokenType() == TK_ClosBrck) {
            params.add(parseExpression());
            while (lookahead.getTokenType() == TK_Comma) {
                consume(TK_Comma);
                params.add(parseExpression());
            }
        }
        return new ASTActualParams(params);
    }

    private static ASTBool parseBool() {
        Token bool = consume(TK_Bool);
        if (bool.getLexeme().equalsIgnoreCase("true")) {
            return new ASTBool(true);
        } else if (bool.getLexeme().equalsIgnoreCase("false")) {
            return new ASTBool(false);
        } else {
            throw new SyntaxError("Invalid Boolean State");
        }
    }

    private static String getType(String type) {
        switch (type) {
            case "int":
                return "int";
            case "float":
                return "float";
            case "auto":
                return "auto";
            case "bool":
                return "bool";
            default:
                throw new SyntaxError("Invalid Type Name");
        }
    }
}
