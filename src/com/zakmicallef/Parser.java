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
            ASTstsmt stsmt = parseStmt();
            if (stsmt == null) {
                System.out.println("EoF Reached");
                break;
            }
            stms.add(stsmt);
            System.out.println("Added Statement");
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
        System.out.println("Doing Statement");
        switch (lookahead.getTokenType()) {
            case TK_Let: // variable declaration
                return parseVariableDeclaration();
            case TK_Print: // print statement
                return parsePrint();
            case TK_If: // if statement
                return parseIf();
            case TK_While: // while statement
                return parseWhile();
            case TK_For: // for statement
                return parseFor();
            case TK_Return: // return statement
                return parseReturn();
            case TK_Def: // function declaration
                return parseFunctionDeclaration();
            case TK_OpenCurly: // block
                return parseBlock();
            case TK_Identifier: // assignment
                return parseAssignment();
            default:
                throw new SyntaxError("Invalid Statement Start");
        }
    }

    private static ASTFor parseFor() {
        consume(TK_For);
        consume(TK_OpenBrck);
        ASTVarNode var = null;
        ASTAssignment assignment = null;
        if(lookahead.getTokenType() == TK_Let) {
            var = parseVariableDeclaration();
        }else {
            consume(TK_Semicolon);
        }
        ASTExpr expr = parseExpression();
        consume(TK_Semicolon);
        if(lookahead.getTokenType() == TK_Identifier) {
            assignment = parseAssignmentFor();
        }
        consume(TK_ClosBrck);
        ASTBlock block = parseBlock();

        return new ASTFor(var, expr, assignment, block);
    }

    private static ASTWhile parseWhile() {
        System.out.println("Doing While");
        consume(TK_While);
        consume(TK_OpenBrck);
        ASTExpr expr = parseExpression();
        consume(TK_ClosBrck);
        ASTBlock block = parseBlock();
        return new ASTWhile(expr, block);
    }

    private static ASTIf parseIf() {
        System.out.println("Parsing If");
        consume(TK_If);
        consume(TK_OpenBrck);
        ASTExpr expr = parseExpression();
        consume(TK_ClosBrck);
        ASTBlock ifBlock = parseBlock();
        ASTBlock elseBlock;

        if (lookahead.getTokenType() == TK_Else) {
            consume(TK_Else);
            elseBlock = parseBlock();
        } else {
            elseBlock = null;
        }

        return new ASTIf(expr, ifBlock, elseBlock);
    }

    private static ASTFuncDecl parseFunctionDeclaration() {
        System.out.println("Parsing Func Decl");
        consume(TK_Def);
        Token id = consume(TK_Identifier);
        consume(TK_OpenBrck);
        ASTFormalParams params = parseFormalParams();
        consume(TK_ClosBrck);
        consume(TK_Colon);
        Token type = consume(TK_Type);
        ASTBlock block = parseBlock();
//        return new ASTFuncDecl(new ASTidNode(id.getLexeme()), params, getType(type.getTokenType()), block);
        return new ASTFuncDecl(new ASTidNode(id.getLexeme()), params, type.getLexeme(), block);
    }

    private static ASTReturn parseReturn() {
        System.out.println("Parsing Return");
        consume(TK_Return);
        ASTExpr expr = parseExpression();
        consume(TK_Semicolon);
        return new ASTReturn(expr);
    }

    private static ASTPrint parsePrint() {
        System.out.println("Parsing Print");
        consume(TK_Print);
        ASTExpr expr = parseExpression();
        consume(TK_Semicolon);
        return new ASTPrint(expr);
    }

    private static ASTBlock parseBlock() {
        System.out.println("Parsing Block");
        ArrayList<ASTstsmt> stmts = new ArrayList<>();
        consume(TK_OpenCurly);

        while (lookahead.getTokenType() != TK_ClosCurly) {
            stmts.add(parseStmt());
        }
        consume(TK_ClosCurly);
        return new ASTBlock(stmts);
    }

    private static ASTFormalParams parseFormalParams() {
        System.out.println("Parsing Formal Params");
        ArrayList<ASTFormalParam> formalParams = new ArrayList<>();
        if (lookahead.getTokenType() != TK_ClosBrck) {
            System.out.println(lookahead.getTokenType());
            formalParams.add(parseFormalParam());
            while (lookahead.getTokenType() == TK_Comma) {
                consume(TK_Comma);
                formalParams.add(parseFormalParam());
            }
        }
        return new ASTFormalParams(formalParams);
    }

    private static ASTFormalParam parseFormalParam() {
        System.out.println("Parsing Formal Param");
        Token id = consume(TK_Identifier);
        consume(TK_Colon);
        Token type = consume(TK_Type);
        return new ASTFormalParam(new ASTidNode(id.getLexeme()), type.getLexeme());
    }

    private static ASTAssignment parseAssignment() {
        System.out.println("Parsing Assign");
        Token id = consume(TK_Identifier);
        consume(TK_Equals);
        ASTExpr expr = parseExpression();
        consume(TK_Semicolon);
        return new ASTAssignment(new ASTidNode(id.getLexeme()), expr);
    }

    private static ASTAssignment parseAssignmentFor() {
        Token id = consume(TK_Identifier);
        consume(TK_Equals);
        ASTExpr expr = parseExpression();
        return new ASTAssignment(new ASTidNode(id.getLexeme()), expr);
    }


    private static ASTVarNode parseVariableDeclaration() throws SyntaxError {
        System.out.println("Parsing Var Decl");
        consume(TK_Let);
        Token id = consume(TK_Identifier);
        consume(TK_Colon);
        String type = consume(TK_Type).getLexeme();
        consume(TK_Equals);
        ASTExpr expr = parseExpression();
        consume(TK_Semicolon);
        return new ASTVarNode(new ASTidNode(id.getLexeme()), type, expr);
    }


    private static ASTExpr parseExpression() {
        ASTExpr simpleExpr = parseSimpleExpression();

        if (lookahead.getTokenType() == TK_RelOp) {
            Token op = consume(TK_RelOp);
            ASTExpr expr = parseExpression();
            return new ASTBinExprNode(simpleExpr, expr, op.getLexeme());
        } else {
            return simpleExpr;
        }
    }

    private static ASTExpr parseSimpleExpression() {
        ASTExpr term = parseTerm();

        if (lookahead.getTokenType() == TK_Add) {
            Token addOp = consume(TK_Add);
            ASTExpr simpleExpr = parseSimpleExpression();
            return new ASTBinExprNode(term, simpleExpr, addOp.getLexeme());
        } else {
            return term;
        }
    }

    private static ASTExpr parseTerm() {
        ASTExpr factor = parseFactor();

        if (lookahead.getTokenType() == TK_Mult) {
            Token addOp = consume(TK_Mult);
            ASTExpr term = parseTerm();
            return new ASTBinExprNode(factor, term, addOp.getLexeme());
        } else {
            return factor;
        }
    }

    private static ASTExpr parseFactor() {
        System.out.println("parseFactor: " + lookahead.getLexeme());
        switch (lookahead.getTokenType()) {
            case TK_Bool:
                return parseBool();
            case TK_Int:
                return new ASTIntegerNode(consume(TK_Int).getValue());
            case TK_Float:
                return new ASTFloatNode(consume(TK_Float).getValue());
            case TK_Auto:
                return new ASTAuto(consume(TK_Auto).getValue());
            case TK_Identifier:
                return parseIdentifier();
            case TK_OpenBrck:
                return parseSubExpr();
            case TK_Add:
                return parseUnary();
            default:
                throw new SyntaxError("Invalid Factor Token");
        }
    }

    private static ASTUnaryNode parseUnary() {
        if (lookahead.getLexeme().equalsIgnoreCase("-") ||
                lookahead.getLexeme().equalsIgnoreCase("not")) {
            Token op = consume(TK_Add);
            ASTExpr expr = parseExpression();
            return new ASTUnaryNode(op.getLexeme(), expr);
        } else {
            throw new SyntaxError("Invalid Unary Operator");
        }
    }

    private static ASTSubExprNode parseSubExpr() {
        consume(TK_OpenBrck);
        ASTExpr expr = parseExpression();
        consume(TK_ClosBrck);
        return new ASTSubExprNode(expr);
    }

    private static ASTExpr parseIdentifier() { //Function Call or Identifier
        Token id = consume(TK_Identifier);

        if (lookahead.getTokenType() == TK_OpenBrck) {
            consume(TK_OpenBrck);
            ASTActualParams actualParams = parseActualParams();
            consume(TK_ClosBrck);
            return new ASTFuncCallNode(new ASTidNode(id.getLexeme()), actualParams);
        } else {
            return new ASTidNode(id.getLexeme());
        }
    }

    private static ASTActualParams parseActualParams() {
        ArrayList<ASTExpr> params = new ArrayList<>();

        if (lookahead.getTokenType() != TK_ClosBrck) {
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

    public static ASTType.Type getType(Token.TokenType type) {

        switch (type) {
            case TK_Int:
                return ASTType.Type.Int;
            case TK_Float:
                return ASTType.Type.Float;
            case TK_Auto:
                return ASTType.Type.Auto;
            case TK_Bool:
                return ASTType.Type.Bool;
            default:
                throw new SyntaxError("Invalid Type Name");
        }
    }

    public static ASTType.Type getType(String type) {

        switch (type) {
            case "int":
                return ASTType.Type.Int;
            case "float":
                return ASTType.Type.Float;
            case "auto":
                return ASTType.Type.Auto;
            case "bool":
                return ASTType.Type.Bool;
            default:
                throw new SyntaxError("Invalid Type Name");
        }
    }
}
