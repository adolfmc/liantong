package org.aspectj.lang.reflect;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import org.aspectj.internal.lang.reflect.AjTypeImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class AjTypeSystem {
    private static Map<Class, WeakReference<AjType>> ajTypes = Collections.synchronizedMap(new WeakHashMap());

    public static <T> AjType<T> getAjType(Class<T> cls) {
        AjTypeImpl ajTypeImpl;
        Map<Class, WeakReference<AjType>> map;
        WeakReference<AjType> weakReference;
        WeakReference<AjType> weakReference2 = ajTypes.get(cls);
        if (weakReference2 != null) {
            AjType<T> ajType = weakReference2.get();
            if (ajType != null) {
                return ajType;
            }
            ajTypeImpl = new AjTypeImpl(cls);
            map = ajTypes;
            weakReference = new WeakReference<>(ajTypeImpl);
        } else {
            ajTypeImpl = new AjTypeImpl(cls);
            map = ajTypes;
            weakReference = new WeakReference<>(ajTypeImpl);
        }
        map.put(cls, weakReference);
        return ajTypeImpl;
    }
}
