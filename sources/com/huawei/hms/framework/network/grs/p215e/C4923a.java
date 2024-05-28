package com.huawei.hms.framework.network.grs.p215e;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.C4916a;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.p217g.C4937d;
import com.huawei.hms.framework.network.grs.p217g.C4942h;
import com.huawei.hms.framework.network.grs.p217g.p219k.C4949c;
import com.huawei.hms.framework.network.grs.p220h.C4956e;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.huawei.hms.framework.network.grs.e.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4923a {

    /* renamed from: f */
    private static final String f11224f = "a";

    /* renamed from: a */
    private final Map<String, Map<String, Map<String, String>>> f11225a = new ConcurrentHashMap(16);

    /* renamed from: b */
    private final Map<String, Long> f11226b = new ConcurrentHashMap(16);

    /* renamed from: c */
    private final C4925c f11227c;

    /* renamed from: d */
    private final C4925c f11228d;

    /* renamed from: e */
    private final C4942h f11229e;

    public C4923a(C4925c c4925c, C4925c c4925c2, C4942h c4942h) {
        this.f11228d = c4925c2;
        this.f11227c = c4925c;
        this.f11229e = c4942h;
        this.f11229e.m14893a(this);
    }

    /* renamed from: a */
    private void m15002a(GrsBaseInfo grsBaseInfo, C4924b c4924b, Context context, String str) {
        Long l = this.f11226b.get(grsBaseInfo.getGrsParasKey(true, true, context));
        if (C4956e.m14853a(l)) {
            c4924b.m14994a(2);
            return;
        }
        if (C4956e.m14852a(l, 300000L)) {
            this.f11229e.m14889a(new C4949c(grsBaseInfo, context), null, str, this.f11228d);
        }
        c4924b.m14994a(1);
    }

    /* renamed from: a */
    private void m15000a(GrsBaseInfo grsBaseInfo, String str, Context context) {
        if (C4956e.m14852a(this.f11226b.get(str), 300000L)) {
            this.f11229e.m14889a(new C4949c(grsBaseInfo, context), null, null, this.f11228d);
        }
    }

    /* renamed from: a */
    public C4925c m15004a() {
        return this.f11227c;
    }

    /* renamed from: a */
    public Map<String, String> m14999a(GrsBaseInfo grsBaseInfo, String str, C4924b c4924b, Context context) {
        Map<String, Map<String, String>> map = this.f11225a.get(grsBaseInfo.getGrsParasKey(true, true, context));
        if (map == null || map.isEmpty()) {
            return new HashMap();
        }
        m15002a(grsBaseInfo, c4924b, context, str);
        return map.get(str);
    }

    /* renamed from: a */
    public void m15003a(GrsBaseInfo grsBaseInfo, Context context) {
        String grsParasKey = grsBaseInfo.getGrsParasKey(true, true, context);
        C4925c c4925c = this.f11227c;
        c4925c.m14988b(grsParasKey + "time", "0");
        Map<String, Long> map = this.f11226b;
        map.remove(grsParasKey + "time");
        this.f11225a.remove(grsParasKey);
        this.f11229e.m14887a(grsParasKey);
    }

    /* renamed from: a */
    public void m15001a(GrsBaseInfo grsBaseInfo, C4937d c4937d, Context context, C4949c c4949c) {
        if (c4937d.m14911f() == 2) {
            Logger.m15045w(f11224f, "update cache from server failed");
        } else if (c4949c.m14876d().size() != 0) {
            this.f11227c.m14988b("geoipCountryCode", c4937d.m14906j());
            this.f11227c.m14988b("geoipCountryCodetime", c4937d.m14930a());
        } else {
            String grsParasKey = grsBaseInfo.getGrsParasKey(true, true, context);
            if (c4937d.m14903m()) {
                this.f11225a.put(grsParasKey, C4916a.m15039a(this.f11227c.m14990a(grsParasKey, "")));
            } else {
                this.f11227c.m14988b(grsParasKey, c4937d.m14906j());
                this.f11225a.put(grsParasKey, C4916a.m15039a(c4937d.m14906j()));
            }
            if (!TextUtils.isEmpty(c4937d.m14913e())) {
                C4925c c4925c = this.f11227c;
                c4925c.m14988b(grsParasKey + "ETag", c4937d.m14913e());
            }
            C4925c c4925c2 = this.f11227c;
            c4925c2.m14988b(grsParasKey + "time", c4937d.m14930a());
            this.f11226b.put(grsParasKey, Long.valueOf(Long.parseLong(c4937d.m14930a())));
        }
    }

    /* renamed from: b */
    public C4942h m14998b() {
        return this.f11229e;
    }

    /* renamed from: b */
    public void m14997b(GrsBaseInfo grsBaseInfo, Context context) {
        String grsParasKey = grsBaseInfo.getGrsParasKey(true, true, context);
        String m14990a = this.f11227c.m14990a(grsParasKey, "");
        C4925c c4925c = this.f11227c;
        String m14990a2 = c4925c.m14990a(grsParasKey + "time", "0");
        long j = 0;
        if (!TextUtils.isEmpty(m14990a2) && m14990a2.matches("\\d+")) {
            try {
                j = Long.parseLong(m14990a2);
            } catch (NumberFormatException e) {
                Logger.m15044w(f11224f, "convert urlParamKey from String to Long catch NumberFormatException.", e);
            }
        }
        this.f11225a.put(grsParasKey, C4916a.m15039a(m14990a));
        this.f11226b.put(grsParasKey, Long.valueOf(j));
        m15000a(grsBaseInfo, grsParasKey, context);
    }

    /* renamed from: c */
    public C4925c m14996c() {
        return this.f11228d;
    }
}
