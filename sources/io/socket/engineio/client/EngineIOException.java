package io.socket.engineio.client;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class EngineIOException extends Exception {
    public Object code;
    public String transport;

    public EngineIOException() {
    }

    public EngineIOException(String str) {
        super(str);
    }

    public EngineIOException(String str, Throwable th) {
        super(str, th);
    }

    public EngineIOException(Throwable th) {
        super(th);
    }
}
