package com.bytedance.pangle.util;

import android.text.TextUtils;
import com.bytedance.pangle.util.p183a.C3939a;
import com.bytedance.pangle.util.p183a.C3940b;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.util.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3943c {
    /* renamed from: a */
    public static String[] m16644a(File file) {
        String[] m16655a = C3940b.m16655a(file);
        return TextUtils.isEmpty(m16655a[0]) ? C3939a.m16656a(file) : m16655a;
    }
}
