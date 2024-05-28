package com.bytedance.applog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.s */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3684s {

    /* renamed from: a */
    public static final LinkedList<AbstractC3628m1> f8795a = new LinkedList<>();

    /* renamed from: b */
    public static final LinkedList<String> f8796b = new LinkedList<>();

    /* renamed from: a */
    public static int m17126a(ArrayList<AbstractC3628m1> arrayList) {
        int size;
        synchronized (f8795a) {
            size = f8795a.size();
            arrayList.addAll(f8795a);
            f8795a.clear();
        }
        return size;
    }

    /* renamed from: a */
    public static void m17127a(AbstractC3628m1 abstractC3628m1) {
        synchronized (f8795a) {
            if (f8795a.size() > 300) {
                f8795a.poll();
            }
            f8795a.add(abstractC3628m1);
        }
    }

    /* renamed from: a */
    public static void m17125a(String[] strArr) {
        synchronized (f8796b) {
            if (f8796b.size() > 300) {
                f8796b.poll();
            }
            f8796b.addAll(Arrays.asList(strArr));
        }
    }
}
