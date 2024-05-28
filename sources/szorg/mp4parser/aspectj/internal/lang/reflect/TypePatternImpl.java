package szorg.mp4parser.aspectj.internal.lang.reflect;

import szorg.mp4parser.aspectj.lang.reflect.TypePattern;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TypePatternImpl implements TypePattern {
    private String typePattern;

    public TypePatternImpl(String str) {
        this.typePattern = str;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.TypePattern
    public String asString() {
        return this.typePattern;
    }

    public String toString() {
        return asString();
    }
}
