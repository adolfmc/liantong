package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.HeaderIterator;
import org.apache.http.ParseException;
import org.apache.http.TokenIterator;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BasicTokenIterator implements TokenIterator {
    public static final String HTTP_SEPARATORS = " ,;=()<>@:\\\"/[]?{}\t";
    protected String currentHeader;
    protected String currentToken;
    protected final HeaderIterator headerIt;
    protected int searchPos;

    public BasicTokenIterator(HeaderIterator headerIterator) {
        if (headerIterator == null) {
            throw new IllegalArgumentException("Header iterator must not be null.");
        }
        this.headerIt = headerIterator;
        this.searchPos = findNext(-1);
    }

    @Override // org.apache.http.TokenIterator, java.util.Iterator
    public boolean hasNext() {
        return this.currentToken != null;
    }

    @Override // org.apache.http.TokenIterator
    public String nextToken() throws NoSuchElementException, ParseException {
        String str = this.currentToken;
        if (str == null) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        this.searchPos = findNext(this.searchPos);
        return str;
    }

    @Override // java.util.Iterator
    public final Object next() throws NoSuchElementException, ParseException {
        return nextToken();
    }

    @Override // java.util.Iterator
    public final void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Removing tokens is not supported.");
    }

    protected int findNext(int i) throws ParseException {
        int findTokenSeparator;
        if (i < 0) {
            if (!this.headerIt.hasNext()) {
                return -1;
            }
            this.currentHeader = this.headerIt.nextHeader().getValue();
            findTokenSeparator = 0;
        } else {
            findTokenSeparator = findTokenSeparator(i);
        }
        int findTokenStart = findTokenStart(findTokenSeparator);
        if (findTokenStart < 0) {
            this.currentToken = null;
            return -1;
        }
        int findTokenEnd = findTokenEnd(findTokenStart);
        this.currentToken = createToken(this.currentHeader, findTokenStart, findTokenEnd);
        return findTokenEnd;
    }

    protected String createToken(String str, int i, int i2) {
        return str.substring(i, i2);
    }

    protected int findTokenStart(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Search position must not be negative: " + i);
        }
        boolean z = false;
        while (!z) {
            String str = this.currentHeader;
            if (str == null) {
                break;
            }
            int length = str.length();
            while (!z && i < length) {
                char charAt = this.currentHeader.charAt(i);
                if (isTokenSeparator(charAt) || isWhitespace(charAt)) {
                    i++;
                } else if (isTokenChar(this.currentHeader.charAt(i))) {
                    z = true;
                } else {
                    throw new ParseException("Invalid character before token (pos " + i + "): " + this.currentHeader);
                }
            }
            if (!z) {
                if (this.headerIt.hasNext()) {
                    this.currentHeader = this.headerIt.nextHeader().getValue();
                    i = 0;
                } else {
                    this.currentHeader = null;
                }
            }
        }
        if (z) {
            return i;
        }
        return -1;
    }

    protected int findTokenSeparator(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Search position must not be negative: " + i);
        }
        boolean z = false;
        int length = this.currentHeader.length();
        while (!z && i < length) {
            char charAt = this.currentHeader.charAt(i);
            if (isTokenSeparator(charAt)) {
                z = true;
            } else if (isWhitespace(charAt)) {
                i++;
            } else if (isTokenChar(charAt)) {
                throw new ParseException("Tokens without separator (pos " + i + "): " + this.currentHeader);
            } else {
                throw new ParseException("Invalid character after token (pos " + i + "): " + this.currentHeader);
            }
        }
        return i;
    }

    protected int findTokenEnd(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Token start position must not be negative: " + i);
        }
        int length = this.currentHeader.length();
        int i2 = i + 1;
        while (i2 < length && isTokenChar(this.currentHeader.charAt(i2))) {
            i2++;
        }
        return i2;
    }

    protected boolean isTokenSeparator(char c) {
        return c == ',';
    }

    protected boolean isWhitespace(char c) {
        return c == '\t' || Character.isSpaceChar(c);
    }

    protected boolean isTokenChar(char c) {
        if (Character.isLetterOrDigit(c)) {
            return true;
        }
        return (Character.isISOControl(c) || isHttpSeparator(c)) ? false : true;
    }

    protected boolean isHttpSeparator(char c) {
        return " ,;=()<>@:\\\"/[]?{}\t".indexOf(c) >= 0;
    }
}
