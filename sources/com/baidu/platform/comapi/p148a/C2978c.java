package com.baidu.platform.comapi.p148a;

import com.baidu.platform.comapi.C2981b;
import com.baidu.platform.comapi.util.SysOSUtil;
import com.baidu.platform.comjni.base.logstatistics.NALogStatistics;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2978c {

    /* renamed from: a */
    private NALogStatistics f7519a;

    /* renamed from: b */
    private ArrayList<InterfaceC2977b> f7520b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.a.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2979a {

        /* renamed from: a */
        private static final C2978c f7521a = new C2978c();
    }

    private C2978c() {
        this.f7519a = null;
        this.f7520b = new ArrayList<>();
        m18075b();
    }

    /* renamed from: a */
    public static C2978c m18078a() {
        return C2979a.f7521a;
    }

    /* renamed from: b */
    private boolean m18075b() {
        if (this.f7519a == null) {
            this.f7519a = new NALogStatistics();
            return true;
        }
        return true;
    }

    /* renamed from: a */
    public boolean m18077a(int i, int i2, String str, String str2) {
        if (this.f7519a != null) {
            if (C2981b.m18067e() || C2981b.m18065g()) {
                m18076a(new C2976a(i, i2, str, str2));
            }
            return this.f7519a.m17677a(i, i2, SysOSUtil.getInstance().getNetType(), str, str2);
        }
        return false;
    }

    /* renamed from: a */
    public boolean m18076a(C2976a c2976a) {
        ArrayList<InterfaceC2977b> arrayList = this.f7520b;
        if (arrayList == null || arrayList.isEmpty()) {
            return false;
        }
        Iterator<InterfaceC2977b> it = this.f7520b.iterator();
        while (it.hasNext()) {
            it.next().m18079a(c2976a);
        }
        return false;
    }
}
