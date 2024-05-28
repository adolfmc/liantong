package com.bykv.p167vk.openvk.api.proto;

import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bykv.vk.openvk.api.proto.ValueSet */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ValueSet {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bykv.vk.openvk.api.proto.ValueSet$ValueGetter */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ValueGetter<T> {
        T get();
    }

    <T> T[] arrayValue(int i, Class<T> cls);

    boolean booleanValue(int i);

    boolean booleanValue(int i, boolean z);

    boolean containsKey(int i);

    double doubleValue(int i);

    float floatValue(int i);

    float floatValue(int i, float f);

    int intValue(int i);

    int intValue(int i, int i2);

    boolean isEmpty();

    Set<Integer> keys();

    long longValue(int i);

    long longValue(int i, long j);

    <T> T objectValue(int i, Class<T> cls);

    int size();

    String stringValue(int i);

    String stringValue(int i, String str);
}
