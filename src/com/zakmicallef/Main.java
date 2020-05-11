package com.zakmicallef;

import java.util.ArrayList;
import java.util.Stack;

import com.zakmicallef.States.*;

import static com.zakmicallef.States.State.*;
import static com.zakmicallef.States.isFinalState;
import static com.zakmicallef.Token.charCategory;
import static java.lang.Character.isWhitespace;

public class Main {
    static String code;
    static int pos = 0;
    static int currLine = 0;

    public static void main(String[] args) {
        ArrayList<Token> tokens = new ArrayList<Token>();
        code = FileInput.readFile("code.txt");
        while (pos < code.length() - 1) {
            try {
                Token nextToken = getNextToken();
                if (nextToken != null) {
                    tokens.add(nextToken);
                } else {
                    break;
                }
            } catch (LexerException e) {
                System.out.println("Error");
                e.getMessage();
                e.printStackTrace();
                break;
            }
        }

        for (Token token : tokens) {
            System.out.println(token.getTokenType() + ": \t" + token.getLexeme());
        }
    }

    private static Token getNextToken() throws LexerException {
        char c;
        String lexeme = "";
        State currState = S00;
        Stack<State> stateStack = new Stack<State>();
        stateStack.push(DEF);

        //Clear Prefixed Whitespace
        do {
            c = nextChar();
            if (c == 0) {
                return null;
            }
        } while (whitespace(c));
        c = rollback();

        while (currState != ERR) {

            do {
                c = nextChar();
                if (c == 0) {
                    break;
                }
            } while (c == '\r');

            lexeme += c;

            if (isFinalState(currState)) {
                stateStack.clear();
            }
            stateStack.push(currState);

            currState = States.TransitionTable[currState.getValue()][charCategory(c).getValue()];
        }

        while (!isFinalState(currState) && currState != DEF) {
            currState = stateStack.peek();
            stateStack.pop();
            if (lexeme.length() > 1) {
                lexeme = lexeme.substring(0, lexeme.length() - 1);
            } else {
                lexeme = lexeme.trim();
            }
            c = rollback();

            if (peekChar() == '\n') {
                currLine--;
            }
        }

        if (isFinalState(currState)) {
            try {
                Token token = Token.tokenize(currState, lexeme);
                return token.getTokenType() == Token.TokenType.TK_Comment ? getNextToken() : token;
            } catch (LexerException le) {
                le.getStackTrace();
                le.getMessage();
            }
        } else {
            throw new LexerException("Token Return Error");
        }
        throw new LexerException("Final");
    }

    private static char rollback() {
        char c = code.charAt(--pos);
        if (c == '\n') {
            currLine--;
        }
        return c;
    }


    private static boolean whitespace(char c) {
        return Character.isWhitespace(c); //includes /n
    }


    private static char nextChar() {
        try {
            char c = code.charAt(++pos);
            if (c == '\n') {
                currLine++;
            }
            return c;
        } catch (StringIndexOutOfBoundsException s) {
            return 0;
        }
    }

    private static char peekChar() {
        try {
            return code.charAt(pos);
        } catch (StringIndexOutOfBoundsException s) {
            return 0;
        }
    }

}
