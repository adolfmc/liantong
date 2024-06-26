package org.apache.http.message;

import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class ParserCursor {
    private final int lowerBound;
    private int pos;
    private final int upperBound;

    public ParserCursor(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        }
        if (i > i2) {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        }
        this.lowerBound = i;
        this.upperBound = i2;
        this.pos = i;
    }

    public int getLowerBound() {
        return this.lowerBound;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public int getPos() {
        return this.pos;
    }

    public void updatePos(int i) {
        if (i < this.lowerBound) {
            throw new IndexOutOfBoundsException();
        }
        if (i > this.upperBound) {
            throw new IndexOutOfBoundsException();
        }
        this.pos = i;
    }

    public boolean atEnd() {
        return this.pos >= this.upperBound;
    }

    public String toString() {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(16);
        charArrayBuffer.append('[');
        charArrayBuffer.append(Integer.toString(this.lowerBound));
        charArrayBuffer.append('>');
        charArrayBuffer.append(Integer.toString(this.pos));
        charArrayBuffer.append('>');
        charArrayBuffer.append(Integer.toString(this.upperBound));
        charArrayBuffer.append(']');
        return charArrayBuffer.toString();
    }
}
