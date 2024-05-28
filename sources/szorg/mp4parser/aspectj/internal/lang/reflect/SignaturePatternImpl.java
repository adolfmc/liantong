package szorg.mp4parser.aspectj.internal.lang.reflect;

import szorg.mp4parser.aspectj.lang.reflect.SignaturePattern;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SignaturePatternImpl implements SignaturePattern {
    private String sigPattern;

    public SignaturePatternImpl(String str) {
        this.sigPattern = str;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.SignaturePattern
    public String asString() {
        return this.sigPattern;
    }

    public String toString() {
        return asString();
    }
}
