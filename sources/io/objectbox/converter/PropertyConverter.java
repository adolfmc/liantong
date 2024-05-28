package io.objectbox.converter;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface PropertyConverter<P, D> {
    D convertToDatabaseValue(P p);

    P convertToEntityProperty(D d);
}
