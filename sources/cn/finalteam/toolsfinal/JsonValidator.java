package cn.finalteam.toolsfinal;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class JsonValidator {

    /* renamed from: c */
    private char f2715c;
    private int col;

    /* renamed from: it */
    private CharacterIterator f2716it;

    public boolean validate(String str) {
        return valid(str.trim());
    }

    private boolean valid(String str) {
        if ("".equals(str)) {
            return true;
        }
        this.f2716it = new StringCharacterIterator(str);
        this.f2715c = this.f2716it.first();
        this.col = 1;
        if (value()) {
            skipWhiteSpace();
            return this.f2715c == 65535;
        }
        return false;
    }

    private boolean value() {
        return literal("true") || literal("false") || literal("null") || string() || number() || object() || array();
    }

    private boolean literal(String str) {
        StringCharacterIterator stringCharacterIterator = new StringCharacterIterator(str);
        if (this.f2715c != stringCharacterIterator.first()) {
            return false;
        }
        int i = this.col;
        boolean z = true;
        while (true) {
            char next = stringCharacterIterator.next();
            if (next != 65535) {
                if (next != nextCharacter()) {
                    z = false;
                    break;
                }
            } else {
                break;
            }
        }
        nextCharacter();
        return z;
    }

    private boolean array() {
        return aggregate('[', ']', false);
    }

    private boolean object() {
        return aggregate('{', '}', true);
    }

    private boolean aggregate(char c, char c2, boolean z) {
        if (this.f2715c != c) {
            return false;
        }
        nextCharacter();
        skipWhiteSpace();
        if (this.f2715c == c2) {
            nextCharacter();
            return true;
        }
        while (true) {
            if (z) {
                int i = this.col;
                if (!string()) {
                    return false;
                }
                skipWhiteSpace();
                if (this.f2715c != ':') {
                    return false;
                }
                nextCharacter();
                skipWhiteSpace();
            }
            if (!value()) {
                return false;
            }
            skipWhiteSpace();
            char c3 = this.f2715c;
            if (c3 != ',') {
                if (c3 == c2) {
                    nextCharacter();
                    return true;
                }
                return false;
            }
            nextCharacter();
            skipWhiteSpace();
        }
    }

    private boolean number() {
        if (Character.isDigit(this.f2715c) || this.f2715c == '-') {
            int i = this.col;
            if (this.f2715c == '-') {
                nextCharacter();
            }
            char c = this.f2715c;
            if (c == '0') {
                nextCharacter();
            } else if (!Character.isDigit(c)) {
                return false;
            } else {
                while (Character.isDigit(this.f2715c)) {
                    nextCharacter();
                }
            }
            if (this.f2715c == '.') {
                nextCharacter();
                if (!Character.isDigit(this.f2715c)) {
                    return false;
                }
                while (Character.isDigit(this.f2715c)) {
                    nextCharacter();
                }
            }
            char c2 = this.f2715c;
            if (c2 == 'e' || c2 == 'E') {
                nextCharacter();
                char c3 = this.f2715c;
                if (c3 == '+' || c3 == '-') {
                    nextCharacter();
                }
                if (Character.isDigit(this.f2715c)) {
                    while (Character.isDigit(this.f2715c)) {
                        nextCharacter();
                    }
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean string() {
        if (this.f2715c != '\"') {
            return false;
        }
        nextCharacter();
        boolean z = false;
        while (true) {
            char c = this.f2715c;
            if (c == 65535) {
                return false;
            }
            if (!z && c == '\\') {
                z = true;
            } else if (z) {
                if (!escape()) {
                    return false;
                }
                z = false;
            } else if (this.f2715c == '\"') {
                nextCharacter();
                return true;
            }
            nextCharacter();
        }
    }

    private boolean escape() {
        if (" \\\"/bfnrtu".indexOf(this.f2715c) < 0) {
            return false;
        }
        if (this.f2715c == 'u') {
            return ishex(nextCharacter()) && ishex(nextCharacter()) && ishex(nextCharacter()) && ishex(nextCharacter());
        }
        return true;
    }

    private boolean ishex(char c) {
        return "0123456789abcdefABCDEF".indexOf(this.f2715c) >= 0;
    }

    private char nextCharacter() {
        this.f2715c = this.f2716it.next();
        this.col++;
        return this.f2715c;
    }

    private void skipWhiteSpace() {
        while (Character.isWhitespace(this.f2715c)) {
            nextCharacter();
        }
    }
}
