package org.java_websocket.exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class IncompleteHandshakeException extends RuntimeException {
    private static final long serialVersionUID = 7906596804233893092L;
    private int newsize;

    public IncompleteHandshakeException(int i) {
        this.newsize = i;
    }

    public IncompleteHandshakeException() {
        this.newsize = 0;
    }

    public int getPreferedSize() {
        return this.newsize;
    }
}
