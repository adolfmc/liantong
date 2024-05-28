package org.codehaus.jackson.map.util;

import java.lang.Enum;
import java.util.HashMap;
import org.codehaus.jackson.map.AnnotationIntrospector;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class EnumResolver<T extends Enum<T>> {
    protected final Class<T> _enumClass;
    protected final T[] _enums;
    protected final HashMap<String, T> _enumsById;

    /* JADX INFO: Access modifiers changed from: protected */
    public EnumResolver(Class<T> cls, T[] tArr, HashMap<String, T> hashMap) {
        this._enumClass = cls;
        this._enums = tArr;
        this._enumsById = hashMap;
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructFor(Class<ET> cls, AnnotationIntrospector annotationIntrospector) {
        ET[] enumConstants = cls.getEnumConstants();
        if (enumConstants == null) {
            throw new IllegalArgumentException("No enum constants for class " + cls.getName());
        }
        HashMap hashMap = new HashMap();
        for (ET et : enumConstants) {
            hashMap.put(annotationIntrospector.findEnumValue(et), et);
        }
        return new EnumResolver<>(cls, enumConstants, hashMap);
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructUsingToString(Class<ET> cls) {
        ET[] enumConstants = cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        int length = enumConstants.length;
        while (true) {
            length--;
            if (length >= 0) {
                ET et = enumConstants[length];
                hashMap.put(et.toString(), et);
            } else {
                return new EnumResolver<>(cls, enumConstants, hashMap);
            }
        }
    }

    public static EnumResolver<?> constructUnsafe(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return constructFor(cls, annotationIntrospector);
    }

    public static EnumResolver<?> constructUnsafeUsingToString(Class<?> cls) {
        return constructUsingToString(cls);
    }

    public T findEnum(String str) {
        return this._enumsById.get(str);
    }

    public T getEnum(int i) {
        if (i >= 0) {
            T[] tArr = this._enums;
            if (i >= tArr.length) {
                return null;
            }
            return tArr[i];
        }
        return null;
    }

    public Class<T> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }
}
