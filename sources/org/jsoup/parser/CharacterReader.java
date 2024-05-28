package org.jsoup.parser;

import java.util.Locale;
import org.jsoup.helper.Validate;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class CharacterReader {
    static final char EOF = 65535;
    private final char[] input;
    private final int length;
    private int pos = 0;
    private int mark = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharacterReader(String str) {
        Validate.notNull(str);
        this.input = str.toCharArray();
        this.length = this.input.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int pos() {
        return this.pos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEmpty() {
        return this.pos >= this.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char current() {
        if (isEmpty()) {
            return (char) 65535;
        }
        return this.input[this.pos];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char consume() {
        char c = isEmpty() ? (char) 65535 : this.input[this.pos];
        this.pos++;
        return c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unconsume() {
        this.pos--;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void advance() {
        this.pos++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void mark() {
        this.mark = this.pos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void rewindToMark() {
        this.pos = this.mark;
    }

    String consumeAsString() {
        char[] cArr = this.input;
        int i = this.pos;
        this.pos = i + 1;
        return new String(cArr, i, 1);
    }

    int nextIndexOf(char c) {
        for (int i = this.pos; i < this.length; i++) {
            if (c == this.input[i]) {
                return i - this.pos;
            }
        }
        return -1;
    }

    int nextIndexOf(CharSequence charSequence) {
        char charAt = charSequence.charAt(0);
        int i = this.pos;
        while (i < this.length) {
            if (charAt != this.input[i]) {
                do {
                    i++;
                    if (i >= this.length) {
                        break;
                    }
                } while (charAt != this.input[i]);
            }
            if (i < this.length) {
                int i2 = i + 1;
                int length = (charSequence.length() + i2) - 1;
                for (int i3 = 1; i2 < length && charSequence.charAt(i3) == this.input[i2]; i3++) {
                    i2++;
                }
                if (i2 == length) {
                    return i - this.pos;
                }
            }
            i++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeTo(char c) {
        int nextIndexOf = nextIndexOf(c);
        if (nextIndexOf != -1) {
            String str = new String(this.input, this.pos, nextIndexOf);
            this.pos += nextIndexOf;
            return str;
        }
        return consumeToEnd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeTo(String str) {
        int nextIndexOf = nextIndexOf(str);
        if (nextIndexOf != -1) {
            String str2 = new String(this.input, this.pos, nextIndexOf);
            this.pos += nextIndexOf;
            return str2;
        }
        return consumeToEnd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeToAny(char... cArr) {
        int i = this.pos;
        loop0: while (this.pos < this.length) {
            for (char c : cArr) {
                if (this.input[this.pos] == c) {
                    break loop0;
                }
            }
            this.pos++;
        }
        int i2 = this.pos;
        return i2 > i ? new String(this.input, i, i2 - i) : "";
    }

    String consumeToEnd() {
        char[] cArr = this.input;
        int i = this.pos;
        String str = new String(cArr, i, this.length - i);
        this.pos = this.length;
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeLetterSequence() {
        char c;
        int i = this.pos;
        while (true) {
            int i2 = this.pos;
            if (i2 >= this.length || (((c = this.input[i2]) < 'A' || c > 'Z') && (c < 'a' || c > 'z'))) {
                break;
            }
            this.pos++;
        }
        return new String(this.input, i, this.pos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeLetterThenDigitSequence() {
        char c;
        int i = this.pos;
        while (true) {
            int i2 = this.pos;
            if (i2 >= this.length || (((c = this.input[i2]) < 'A' || c > 'Z') && (c < 'a' || c > 'z'))) {
                break;
            }
            this.pos++;
        }
        while (!isEmpty()) {
            char[] cArr = this.input;
            int i3 = this.pos;
            char c2 = cArr[i3];
            if (c2 < '0' || c2 > '9') {
                break;
            }
            this.pos = i3 + 1;
        }
        return new String(this.input, i, this.pos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeHexSequence() {
        char c;
        int i = this.pos;
        while (true) {
            int i2 = this.pos;
            if (i2 >= this.length || (((c = this.input[i2]) < '0' || c > '9') && ((c < 'A' || c > 'F') && (c < 'a' || c > 'f')))) {
                break;
            }
            this.pos++;
        }
        return new String(this.input, i, this.pos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeDigitSequence() {
        char c;
        int i = this.pos;
        while (true) {
            int i2 = this.pos;
            if (i2 >= this.length || (c = this.input[i2]) < '0' || c > '9') {
                break;
            }
            this.pos = i2 + 1;
        }
        return new String(this.input, i, this.pos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matches(char c) {
        return !isEmpty() && this.input[this.pos] == c;
    }

    boolean matches(String str) {
        int length = str.length();
        if (length > this.length - this.pos) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) != this.input[this.pos + i]) {
                return false;
            }
        }
        return true;
    }

    boolean matchesIgnoreCase(String str) {
        int length = str.length();
        if (length > this.length - this.pos) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (Character.toUpperCase(str.charAt(i)) != Character.toUpperCase(this.input[this.pos + i])) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchesAny(char... cArr) {
        if (isEmpty()) {
            return false;
        }
        char c = this.input[this.pos];
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchesLetter() {
        if (isEmpty()) {
            return false;
        }
        char c = this.input[this.pos];
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchesDigit() {
        char c;
        return !isEmpty() && (c = this.input[this.pos]) >= '0' && c <= '9';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchConsume(String str) {
        if (matches(str)) {
            this.pos += str.length();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchConsumeIgnoreCase(String str) {
        if (matchesIgnoreCase(str)) {
            this.pos += str.length();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean containsIgnoreCase(String str) {
        return nextIndexOf(str.toLowerCase(Locale.ENGLISH)) > -1 || nextIndexOf(str.toUpperCase(Locale.ENGLISH)) > -1;
    }

    public String toString() {
        char[] cArr = this.input;
        int i = this.pos;
        return new String(cArr, i, this.length - i);
    }
}
