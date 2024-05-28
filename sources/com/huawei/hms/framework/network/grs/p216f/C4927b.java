package com.huawei.hms.framework.network.grs.p216f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.AssetsUtil;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.local.model.C4959a;
import com.huawei.hms.framework.network.grs.p215e.C4923a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.huawei.hms.framework.network.grs.f.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4927b {

    /* renamed from: b */
    private static final Map<String, C4927b> f11241b = new ConcurrentHashMap(16);

    /* renamed from: c */
    private static final Object f11242c = new Object();

    /* renamed from: a */
    private AbstractC4926a f11243a;

    public C4927b(Context context, GrsBaseInfo grsBaseInfo, boolean z) {
        m14965a(context, z);
        Map<String, C4927b> map = f11241b;
        map.put(context.getPackageName() + grsBaseInfo.uniqueCode(), this);
    }

    /* renamed from: a */
    public static C4927b m14963a(String str, GrsBaseInfo grsBaseInfo) {
        Map<String, C4927b> map = f11241b;
        return map.get(str + grsBaseInfo.uniqueCode());
    }

    /* renamed from: a */
    public static void m14968a(Context context, GrsBaseInfo grsBaseInfo) {
        C4927b m14963a = m14963a(context.getPackageName(), grsBaseInfo);
        if (m14963a != null) {
            Logger.m15049i("LocalManagerProxy", "appGrs is not null and clear services.");
            synchronized (f11242c) {
                m14963a.f11243a.m14987a();
            }
        }
    }

    /* renamed from: a */
    public C4959a m14969a() {
        return this.f11243a.m14979b();
    }

    /* renamed from: a */
    public String m14967a(Context context, C4923a c4923a, GrsBaseInfo grsBaseInfo, String str, String str2, boolean z) {
        synchronized (f11242c) {
            String m14986a = this.f11243a.m14986a(context, c4923a, grsBaseInfo, str, str2, z);
            if (TextUtils.isEmpty(m14986a) && this.f11243a.m14974d()) {
                m14965a(context, true);
                m14964a(grsBaseInfo);
                Map<String, C4927b> map = f11241b;
                map.put(context.getPackageName() + grsBaseInfo.uniqueCode(), this);
                return this.f11243a.m14986a(context, c4923a, grsBaseInfo, str, str2, z);
            }
            return m14986a;
        }
    }

    /* renamed from: a */
    public Map<String, String> m14966a(Context context, C4923a c4923a, GrsBaseInfo grsBaseInfo, String str, boolean z) {
        synchronized (f11242c) {
            Map<String, String> m14985a = this.f11243a.m14985a(context, c4923a, grsBaseInfo, str, z);
            if ((m14985a == null || m14985a.isEmpty()) && this.f11243a.m14974d()) {
                m14965a(context, true);
                m14964a(grsBaseInfo);
                Map<String, C4927b> map = f11241b;
                map.put(context.getPackageName() + grsBaseInfo.uniqueCode(), this);
                return this.f11243a.m14985a(context, c4923a, grsBaseInfo, str, z);
            }
            return m14985a;
        }
    }

    /* renamed from: a */
    public void m14965a(Context context, boolean z) {
        String[] list = AssetsUtil.list(context, GrsApp.getInstance().getBrand(""));
        List<String> arrayList = list == null ? new ArrayList<>() : Arrays.asList(list);
        String appConfigName = GrsApp.getInstance().getAppConfigName();
        Logger.m15049i("LocalManagerProxy", "appConfigName is: " + appConfigName);
        this.f11243a = new C4929d(false, z);
        if (arrayList.contains("grs_app_global_route_config.json") || !TextUtils.isEmpty(appConfigName)) {
            this.f11243a = new C4929d(context, appConfigName, z);
        }
        if (!this.f11243a.m14972e() && arrayList.contains("grs_sdk_global_route_config.json")) {
            this.f11243a = new C4928c(context, z);
        }
        this.f11243a.m14984a(context, arrayList);
    }

    /* renamed from: a */
    public void m14964a(GrsBaseInfo grsBaseInfo) {
        this.f11243a.m14983a(grsBaseInfo);
    }

    /* renamed from: b */
    public Set<String> m14962b() {
        return this.f11243a.m14976c();
    }
}
