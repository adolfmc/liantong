package com.huawei.hms.framework.network.grs.local.model;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.huawei.hms.framework.network.grs.local.model.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4959a {

    /* renamed from: a */
    private String f11331a;

    /* renamed from: b */
    private final Map<String, C4961c> f11332b = new ConcurrentHashMap(16);

    /* renamed from: a */
    public C4961c m14845a(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.m15045w("ApplicationBean", "In getServing(String serviceName), the serviceName is Empty or null");
            return null;
        }
        return this.f11332b.get(str);
    }

    /* renamed from: a */
    public void m14847a() {
        Map<String, C4961c> map = this.f11332b;
        if (map != null) {
            map.clear();
        }
    }

    /* renamed from: a */
    public void m14846a(long j) {
    }

    /* renamed from: a */
    public void m14844a(String str, C4961c c4961c) {
        if (TextUtils.isEmpty(str) || c4961c == null) {
            return;
        }
        this.f11332b.put(str, c4961c);
    }

    /* renamed from: b */
    public String m14843b() {
        return this.f11331a;
    }

    /* renamed from: b */
    public void m14842b(String str) {
        this.f11331a = str;
    }
}
