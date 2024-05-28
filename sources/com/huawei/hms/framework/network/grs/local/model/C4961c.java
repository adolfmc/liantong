package com.huawei.hms.framework.network.grs.local.model;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.huawei.hms.framework.network.grs.local.model.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4961c {

    /* renamed from: a */
    private String f11335a;

    /* renamed from: b */
    private final Map<String, C4962d> f11336b = new ConcurrentHashMap(16);

    /* renamed from: c */
    private List<C4960b> f11337c = new ArrayList(16);

    /* renamed from: a */
    public C4962d m14834a(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.m15045w("Service", "In servings.getServing(String groupId), the groupId is Empty or null");
            return null;
        }
        return this.f11336b.get(str);
    }

    /* renamed from: a */
    public List<C4960b> m14835a() {
        return this.f11337c;
    }

    /* renamed from: a */
    public void m14833a(String str, C4962d c4962d) {
        if (TextUtils.isEmpty(str) || c4962d == null) {
            return;
        }
        this.f11336b.put(str, c4962d);
    }

    /* renamed from: a */
    public void m14832a(List<C4960b> list) {
        this.f11337c = list;
    }

    /* renamed from: b */
    public String m14831b() {
        return this.f11335a;
    }

    /* renamed from: b */
    public void m14830b(String str) {
    }

    /* renamed from: c */
    public void m14829c(String str) {
        this.f11335a = str;
    }
}
