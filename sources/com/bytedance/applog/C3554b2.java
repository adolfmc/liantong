package com.bytedance.applog;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.LruCache;
import android.util.SparseArray;
import android.view.View;
import android.widget.AbsSeekBar;
import android.widget.AdapterView;
import java.util.HashSet;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@TargetApi(12)
/* renamed from: com.bytedance.applog.b2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3554b2 {

    /* renamed from: a */
    public static SparseArray<String> f8375a;

    /* renamed from: b */
    public static Set<Integer> f8376b;

    /* renamed from: c */
    public static LruCache<Class, String> f8377c = new LruCache<>(100);

    /* renamed from: a */
    public static int m17332a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: a */
    public static String m17329a(View view, boolean z) {
        Object tag = view.getTag(84159242);
        if (tag == null || !(tag instanceof String)) {
            if (z) {
                return null;
            }
            if (f8375a == null) {
                f8375a = new SparseArray<>();
            }
            if (f8376b == null) {
                f8376b = new HashSet();
            }
            int id = view.getId();
            if (id > 2130706432 && !f8376b.contains(Integer.valueOf(id))) {
                String str = f8375a.get(id);
                if (str != null) {
                    return str;
                }
                try {
                    String resourceEntryName = view.getResources().getResourceEntryName(id);
                    f8375a.put(id, resourceEntryName);
                    return resourceEntryName;
                } catch (Exception unused) {
                    f8376b.add(Integer.valueOf(id));
                }
            }
            return null;
        }
        return (String) tag;
    }

    /* renamed from: a */
    public static String m17328a(Class cls) {
        String str = f8377c.get(cls);
        if (TextUtils.isEmpty(str)) {
            str = cls.getSimpleName();
            if (TextUtils.isEmpty(str)) {
                str = "Anonymous";
            }
            f8377c.put(cls, str);
            if (!C3737y1.f8958j && !C3737y1.f8953e && !C3737y1.f8949a && str.contains("RecyclerView")) {
                try {
                    if (C3737y1.m17004a((Class<?>) cls) != null && C3737y1.f8951c != null) {
                        C3737y1.f8950b = cls;
                        C3737y1.f8949a = true;
                    }
                } catch (Exception e) {
                    C3704u2.m17108a("U SHALL NOT PASS!", e);
                }
            }
        }
        return str;
    }

    /* renamed from: a */
    public static String m17327a(String str) {
        return str == null ? "" : (TextUtils.isEmpty(str) || str.length() <= 20) ? str : str.substring(0, 20);
    }

    /* renamed from: a */
    public static boolean m17331a(View view) {
        return view == null || view.getTag(C3527R.C3530id.tag_ignore) != null;
    }

    /* renamed from: b */
    public static boolean m17326b(View view) {
        boolean z = false;
        boolean z2 = view.isClickable() || (view instanceof AbsSeekBar);
        if (view.getParent() instanceof AdapterView) {
            AdapterView adapterView = (AdapterView) view.getParent();
            if (adapterView.isClickable() || adapterView.getOnItemClickListener() != null || adapterView.getOnItemSelectedListener() != null) {
                z = true;
            }
            return z2 | z;
        }
        return z2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00bb, code lost:
        if (r0.getText() != null) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00fc, code lost:
        if (r0.getText() != null) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00fe, code lost:
        r0 = r0.getText();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0124, code lost:
        if (r0 != null) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0139, code lost:
        if (r0 != null) goto L4;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<java.lang.String> m17330a(android.view.View r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 363
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3554b2.m17330a(android.view.View, java.lang.String):java.util.ArrayList");
    }
}
