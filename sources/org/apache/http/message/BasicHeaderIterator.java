package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BasicHeaderIterator implements HeaderIterator {
    protected final Header[] allHeaders;
    protected int currentIndex;
    protected String headerName;

    public BasicHeaderIterator(Header[] headerArr, String str) {
        if (headerArr == null) {
            throw new IllegalArgumentException("Header array must not be null.");
        }
        this.allHeaders = headerArr;
        this.headerName = str;
        this.currentIndex = findNext(-1);
    }

    protected int findNext(int i) {
        if (i < -1) {
            return -1;
        }
        int length = this.allHeaders.length - 1;
        boolean z = false;
        while (!z && i < length) {
            i++;
            z = filterHeader(i);
        }
        if (z) {
            return i;
        }
        return -1;
    }

    protected boolean filterHeader(int i) {
        String str = this.headerName;
        return str == null || str.equalsIgnoreCase(this.allHeaders[i].getName());
    }

    @Override // org.apache.http.HeaderIterator, java.util.Iterator
    public boolean hasNext() {
        return this.currentIndex >= 0;
    }

    @Override // org.apache.http.HeaderIterator
    public Header nextHeader() throws NoSuchElementException {
        int i = this.currentIndex;
        if (i < 0) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        this.currentIndex = findNext(i);
        return this.allHeaders[i];
    }

    @Override // java.util.Iterator
    public final Object next() throws NoSuchElementException {
        return nextHeader();
    }

    @Override // java.util.Iterator
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Removing headers is not supported.");
    }
}
