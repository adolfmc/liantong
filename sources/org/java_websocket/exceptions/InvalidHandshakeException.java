package org.java_websocket.exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class InvalidHandshakeException extends InvalidDataException {
    private static final long serialVersionUID = -1426533877490484964L;

    public InvalidHandshakeException() {
        super(1002);
    }

    public InvalidHandshakeException(String str, Throwable th) {
        super(1002, str, th);
    }

    public InvalidHandshakeException(String str) {
        super(1002, str);
    }

    public InvalidHandshakeException(Throwable th) {
        super(1002, th);
    }
}
