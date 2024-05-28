package org.codehaus.jackson.map.deser;

import java.lang.Enum;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class EnumResolver<T extends Enum<T>> extends org.codehaus.jackson.map.util.EnumResolver<T> {
    private EnumResolver(Class<T> cls, T[] tArr, HashMap<String, T> hashMap) {
        super(cls, tArr, hashMap);
    }
}
