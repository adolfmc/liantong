package com.huawei.hms.framework.network.grs.p217g.p219k;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.p216f.C4927b;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.framework.network.grs.g.k.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4949c {

    /* renamed from: a */
    private final GrsBaseInfo f11316a;

    /* renamed from: b */
    private final Context f11317b;

    /* renamed from: c */
    private final Set<String> f11318c = new HashSet();

    public C4949c(GrsBaseInfo grsBaseInfo, Context context) {
        this.f11316a = grsBaseInfo;
        this.f11317b = context;
    }

    /* renamed from: e */
    private String m14875e() {
        Set<String> m14962b = C4927b.m14963a(this.f11317b.getPackageName(), this.f11316a).m14962b();
        if (m14962b.isEmpty()) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (String str : m14962b) {
            jSONArray.put(str);
        }
        try {
            jSONObject.put("services", jSONArray);
            Logger.m15048i("GrsRequestInfo", "post service list is:%s", jSONObject.toString());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    /* renamed from: f */
    private String m14874f() {
        Logger.m15047v("GrsRequestInfo", "getGeoipService enter");
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (String str : this.f11318c) {
            jSONArray.put(str);
        }
        try {
            jSONObject.put("services", jSONArray);
            Logger.m15046v("GrsRequestInfo", "post query service list is:%s", jSONObject.toString());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    /* renamed from: a */
    public Context m14880a() {
        return this.f11317b;
    }

    /* renamed from: a */
    public void m14879a(String str) {
        this.f11318c.add(str);
    }

    /* renamed from: b */
    public GrsBaseInfo m14878b() {
        return this.f11316a;
    }

    /* renamed from: c */
    public String m14877c() {
        return this.f11318c.size() == 0 ? m14875e() : m14874f();
    }

    /* renamed from: d */
    public Set<String> m14876d() {
        return this.f11318c;
    }
}
