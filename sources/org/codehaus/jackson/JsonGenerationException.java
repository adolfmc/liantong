package org.codehaus.jackson;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class JsonGenerationException extends JsonProcessingException {
    static final long serialVersionUID = 123;

    public JsonGenerationException(Throwable th) {
        super(th);
    }

    public JsonGenerationException(String str) {
        super(str, (JsonLocation) null);
    }

    public JsonGenerationException(String str, Throwable th) {
        super(str, null, th);
    }
}
