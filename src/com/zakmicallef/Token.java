package com.zakmicallef;

import com.zakmicallef.Exception.LexerException;

import java.util.HashMap;
import java.util.Map;

import static com.zakmicallef.States.*;
import static com.zakmicallef.States.State.S04;
import static com.zakmicallef.States.State.S19;
import static com.zakmicallef.Token.TokenType.*;

public class Token {
    private TokenType tokenType;
    private String lexeme;
    private double value;

    public Token(TokenType tokenType, String lexeme) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
    }

    public Token(TokenType tokenType, String lexeme, double value) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
        this.value = value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public static enum TokenType {
        TK_Bool,
        TK_Int,
        TK_Float,
        TK_String,
        TK_Auto,
        TK_Identifier,
        TK_Mult,
        TK_Add,
        TK_RelOp,
        TK_Equals,
        TK_Colon,
        TK_OpenBrck,
        TK_ClosBrck,
        TK_Comma,
        TK_Semicolon,
        TK_OpenCurly,
        TK_ClosCurly,
        TK_Assign,
        TK_Let,
        TK_Def,
        TK_Print,
        TK_Return,
        TK_If,
        TK_Else,
        TK_For,
        TK_While,
        TK_Type,
        TK_Comment,
        TK_EOF;
    }


    static Token tokenize(State state, String lexeme) throws LexerException {
        if (isFinal(state)) {
            switch (state) {
                case S01:
                    return new Token(TK_Int, lexeme, Integer.parseInt(lexeme));
                case S03:
                    return new Token(TK_Float, lexeme, Double.parseDouble(lexeme));
                case S06:
                    return new Token(TK_String, lexeme.substring(1, lexeme.length() - 2)); // without inverted commas
                case S07:
                case S13:
                    return new Token(TK_Mult, lexeme);
                case S09:
                case S12:
                    return new Token(TK_Comment, lexeme);
                case S14:
                    return new Token(TK_Add, lexeme);
                case S15:
                case S18:
                    return new Token(TK_RelOp, lexeme);
                case S16:
                    return new Token(TK_Equals, lexeme);
                case S20:
                    return new Token(TK_EOF, "EoF");
                default:
                    break;
            }

            if (state == S04) {
                switch (lexeme) {
                    case "set":
                        return new Token(TK_Assign, lexeme);
                    case "let":
                        return new Token(TK_Let, lexeme);
                    case "ff":
                        return new Token(TK_Def, lexeme);
                    case "print":
                        return new Token(TK_Print, lexeme);
                    case "return":
                        return new Token(TK_Return, lexeme);
                    case "if":
                        return new Token(TK_If, lexeme);
                    case "for":
                        return new Token(TK_For, lexeme);
                    case "else":
                        return new Token(TK_Else, lexeme);
                    case "while":
                        return new Token(TK_While, lexeme);
                    case "or":
                    case "not":
                        return new Token(TK_Add, lexeme);
                    case "and":
                    case "*":
                        return new Token(TK_Mult, lexeme);
                    case "float":
                    case "int":
                    case "bool":
                    case "auto":
                        return new Token(TK_Type, lexeme);
                    case "true":
                    case "false":
                        return new Token(TK_Bool, lexeme);
                    default:
                        return new Token(TK_Identifier, lexeme);
                }
            } else if (state == S19) {
                if (lexeme.length() == 1) {
                    switch (lexeme.charAt(0)) {
                        case ':':
                            return new Token(TK_Colon, lexeme);
                        case '(':
                            return new Token(TK_OpenBrck, lexeme);
                        case ')':
                            return new Token(TK_ClosBrck, lexeme);
                        case ',':
                            return new Token(TK_Comma, lexeme);
                        case ';':
                            return new Token(TK_Semicolon, lexeme);
                        case '{':
                            return new Token(TK_OpenCurly, lexeme);
                        case '}':
                            return new Token(TK_ClosCurly, lexeme);
                        default:
                            System.out.println("Punctuation Error");
                    }
                } else {
                    System.out.println("Punctuation Longer than 1 Char");
                }
            }
        } else {
            throw new LexerException("State Exception");
        }

        throw new LexerException("Final Exception");
    }

    public static enum TokGroup {
        Digit(0),
        DecimalDot(1),
        Alphabet(2),
        String(3),
        Asterisk(4),
        FwdSlash(5),
        PlusMinus(6),
        Inequality(7),
        Exclamation(8),
        Equals(9),
        Underscore(10),
        Quotes(11),
        Punct(12),
        NewLine(13),
        EoF(14),
        Other(15);

        private int value;
        private static Map map = new HashMap<>();


        private TokGroup(int value) {
            this.value = value;
        }

        static {
            for (TokGroup tokGroup : TokGroup.values()) {
                map.put(tokGroup.value, tokGroup);
            }
        }

        public static TokGroup valueOf(int tokGroup) {
            return (TokGroup) map.get(tokGroup);
        }

        public int getValue() {
            return value;
        }

    }

    static TokGroup charGroup(char c) {
        switch (c) {
            case '.':
                return TokGroup.DecimalDot;
            case '+':
            case '-':
                return TokGroup.PlusMinus;
            case '*':
                return TokGroup.Asterisk;
            case '/':
                return TokGroup.FwdSlash;
            case '>':
            case '<':
                return TokGroup.Inequality;
            case '!':
                return TokGroup.Exclamation;
            case '=':
                return TokGroup.Equals;
            case '_':
                return TokGroup.Underscore;
            case '"':
                return TokGroup.Quotes;
            case ':':
            case '(':
            case ')':
            case ',':
            case ';':
            case '{':
            case '}':
                return TokGroup.Punct;
            case '\n':
                return TokGroup.NewLine;
            default:
                break;
        }

        if (c >= '0' && c <= '9') {
            return TokGroup.Digit;
        } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return TokGroup.Alphabet;
        } else {
            return TokGroup.Other;
        }
    }

}
