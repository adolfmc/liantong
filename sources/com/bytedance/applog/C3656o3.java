package com.bytedance.applog;

import android.support.annotation.NonNull;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.applog.o3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3656o3 {

    /* renamed from: a */
    public final String f8677a;

    /* renamed from: b */
    public final String f8678b;

    /* renamed from: c */
    public final Boolean f8679c;

    /* renamed from: d */
    public final Long f8680d;

    /* renamed from: e */
    public final Long f8681e;

    /* renamed from: f */
    public final Integer f8682f;

    /* renamed from: g */
    public final Long f8683g;

    public C3656o3(String str, String str2, Boolean bool, Long l, Long l2, Integer num, Long l3) {
        this.f8677a = str;
        this.f8678b = str2;
        this.f8679c = bool;
        this.f8680d = l;
        this.f8681e = l2;
        this.f8682f = num;
        this.f8683g = l3;
    }

    @NonNull
    /* renamed from: a */
    public Map<String, String> m17184a() {
        HashMap hashMap = new HashMap();
        C3630m3.m17223a(hashMap, "id", this.f8677a);
        C3630m3.m17223a(hashMap, "req_id", this.f8678b);
        C3630m3.m17223a(hashMap, "is_track_limited", String.valueOf(this.f8679c));
        C3630m3.m17223a(hashMap, "take_ms", String.valueOf(this.f8680d));
        C3630m3.m17223a(hashMap, "time", String.valueOf(this.f8681e));
        C3630m3.m17223a(hashMap, "query_times", String.valueOf(this.f8682f));
        C3630m3.m17223a(hashMap, "hw_id_version_code", String.valueOf(this.f8683g));
        return hashMap;
    }

    @NonNull
    /* renamed from: b */
    public JSONObject m17183b() {
        JSONObject jSONObject = new JSONObject();
        C3630m3.m17222a(jSONObject, "id", this.f8677a);
        C3630m3.m17222a(jSONObject, "req_id", this.f8678b);
        C3630m3.m17222a(jSONObject, "is_track_limited", this.f8679c);
        C3630m3.m17222a(jSONObject, "take_ms", this.f8680d);
        C3630m3.m17222a(jSONObject, "time", this.f8681e);
        C3630m3.m17222a(jSONObject, "query_times", this.f8682f);
        C3630m3.m17222a(jSONObject, "hw_id_version_code", this.f8683g);
        return jSONObject;
    }

    public String toString() {
        JSONObject m17183b = m17183b();
        return !(m17183b instanceof JSONObject) ? m17183b.toString() : NBSJSONObjectInstrumentation.toString(m17183b);
    }
}
