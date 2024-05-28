package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BasicHeaderElementIterator implements HeaderElementIterator {
    private CharArrayBuffer buffer;
    private HeaderElement currentElement;
    private ParserCursor cursor;
    private final HeaderIterator headerIt;
    private final HeaderValueParser parser;

    public BasicHeaderElementIterator(HeaderIterator headerIterator, HeaderValueParser headerValueParser) {
        this.currentElement = null;
        this.buffer = null;
        this.cursor = null;
        if (headerIterator == null) {
            throw new IllegalArgumentException("Header iterator may not be null");
        }
        if (headerValueParser == null) {
            throw new IllegalArgumentException("Parser may not be null");
        }
        this.headerIt = headerIterator;
        this.parser = headerValueParser;
    }

    public BasicHeaderElementIterator(HeaderIterator headerIterator) {
        this(headerIterator, BasicHeaderValueParser.DEFAULT);
    }

    private void bufferHeaderValue() {
        this.cursor = null;
        this.buffer = null;
        while (this.headerIt.hasNext()) {
            Header nextHeader = this.headerIt.nextHeader();
            if (nextHeader instanceof FormattedHeader) {
                FormattedHeader formattedHeader = (FormattedHeader) nextHeader;
                CharArrayBuffer buffer = formattedHeader.getBuffer();
                this.buffer = buffer;
                ParserCursor parserCursor = new ParserCursor(0, buffer.length());
                this.cursor = parserCursor;
                parserCursor.updatePos(formattedHeader.getValuePos());
                return;
            }
            String value = nextHeader.getValue();
            if (value != null) {
                CharArrayBuffer charArrayBuffer = new CharArrayBuffer(value.length());
                this.buffer = charArrayBuffer;
                charArrayBuffer.append(value);
                this.cursor = new ParserCursor(0, this.buffer.length());
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseNextElement() {
        /*
            r3 = this;
        L0:
            org.apache.http.HeaderIterator r0 = r3.headerIt
            boolean r0 = r0.hasNext()
            if (r0 != 0) goto Le
            org.apache.http.message.ParserCursor r0 = r3.cursor
            if (r0 == 0) goto Ld
            goto Le
        Ld:
            return
        Le:
            org.apache.http.message.ParserCursor r0 = r3.cursor
            if (r0 == 0) goto L18
            boolean r0 = r0.atEnd()
            if (r0 == 0) goto L1b
        L18:
            r3.bufferHeaderValue()
        L1b:
            org.apache.http.message.ParserCursor r0 = r3.cursor
            if (r0 == 0) goto L0
        L1f:
            org.apache.http.message.ParserCursor r0 = r3.cursor
            boolean r0 = r0.atEnd()
            if (r0 != 0) goto L46
            org.apache.http.message.HeaderValueParser r0 = r3.parser
            org.apache.http.util.CharArrayBuffer r1 = r3.buffer
            org.apache.http.message.ParserCursor r2 = r3.cursor
            org.apache.http.HeaderElement r0 = r0.parseHeaderElement(r1, r2)
            java.lang.String r1 = r0.getName()
            int r1 = r1.length()
            if (r1 != 0) goto L43
            java.lang.String r1 = r0.getValue()
            if (r1 == 0) goto L42
            goto L43
        L42:
            goto L1f
        L43:
            r3.currentElement = r0
            return
        L46:
            org.apache.http.message.ParserCursor r0 = r3.cursor
            boolean r0 = r0.atEnd()
            if (r0 == 0) goto L54
            r0 = 0
            r3.cursor = r0
            r3.buffer = r0
            goto L0
        L54:
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.message.BasicHeaderElementIterator.parseNextElement():void");
    }

    @Override // org.apache.http.HeaderElementIterator, java.util.Iterator
    public boolean hasNext() {
        if (this.currentElement == null) {
            parseNextElement();
        }
        return this.currentElement != null;
    }

    @Override // org.apache.http.HeaderElementIterator
    public HeaderElement nextElement() throws NoSuchElementException {
        if (this.currentElement == null) {
            parseNextElement();
        }
        HeaderElement headerElement = this.currentElement;
        if (headerElement == null) {
            throw new NoSuchElementException("No more header elements available");
        }
        this.currentElement = null;
        return headerElement;
    }

    @Override // java.util.Iterator
    public final Object next() throws NoSuchElementException {
        return nextElement();
    }

    @Override // java.util.Iterator
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
