package org.codehaus.jackson;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class JsonProcessingException extends IOException {
    static final long serialVersionUID = 123;
    protected JsonLocation mLocation;

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this.mLocation = jsonLocation;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(String str, JsonLocation jsonLocation) {
        this(str, jsonLocation, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(String str, Throwable th) {
        this(str, null, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(Throwable th) {
        this(null, null, th);
    }

    public JsonLocation getLocation() {
        return this.mLocation;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        JsonLocation location = getLocation();
        if (location != null) {
            return message + "\n at " + location.toString();
        }
        return message;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }
}
