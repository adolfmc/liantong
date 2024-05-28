package org.codehaus.jackson.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class TypeReference<T> implements Comparable<TypeReference<T>> {
    final Type _type;

    public int compareTo(TypeReference<T> typeReference) {
        return 0;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((TypeReference) ((TypeReference) obj));
    }

    protected TypeReference() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        }
        this._type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return this._type;
    }
}
