package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.service.C11615q;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.xiaomi.push.cp */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11246cp {

    /* renamed from: a */
    private final String f21739a = "disconnection_event";

    /* renamed from: b */
    private final String f21740b = "count";

    /* renamed from: c */
    private final String f21741c = "host";

    /* renamed from: d */
    private final String f21742d = "network_state";

    /* renamed from: e */
    private final String f21743e = "reason";

    /* renamed from: f */
    private final String f21744f = "ping_interval";

    /* renamed from: g */
    private final String f21745g = "network_type";

    /* renamed from: h */
    private final String f21746h = "wifi_digest";

    /* renamed from: i */
    private final String f21747i = "duration";

    /* renamed from: j */
    private final String f21748j = "disconnect_time";

    /* renamed from: k */
    private final String f21749k = "connect_time";

    /* renamed from: l */
    private final String f21750l = "xmsf_vc";

    /* renamed from: m */
    private final String f21751m = "android_vc";

    /* renamed from: n */
    private final String f21752n = "uuid";

    /* renamed from: a */
    public void m4487a(Context context, List<C11245co> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        C11244cn.m4516a("upload size = " + list.size());
        String m2429a = C11615q.m2429a(context);
        for (C11245co c11245co : list) {
            HashMap hashMap = new HashMap();
            hashMap.put("count", Integer.valueOf(c11245co.m4513a()));
            hashMap.put("host", c11245co.m4511a());
            hashMap.put("network_state", Integer.valueOf(c11245co.m4507b()));
            hashMap.put("reason", Integer.valueOf(c11245co.m4501c()));
            hashMap.put("ping_interval", Long.valueOf(c11245co.m4512a()));
            hashMap.put("network_type", Integer.valueOf(c11245co.m4497d()));
            hashMap.put("wifi_digest", c11245co.m4505b());
            hashMap.put("connected_network_type", Integer.valueOf(c11245co.m4493e()));
            hashMap.put("duration", Long.valueOf(c11245co.m4506b()));
            hashMap.put("disconnect_time", Long.valueOf(c11245co.m4500c()));
            hashMap.put("connect_time", Long.valueOf(c11245co.m4496d()));
            hashMap.put("xmsf_vc", Integer.valueOf(c11245co.m4491f()));
            hashMap.put("android_vc", Integer.valueOf(c11245co.m4489g()));
            hashMap.put("uuid", m2429a);
            C11321eh.m4047a().mo4045a("disconnection_event", hashMap);
        }
    }
}
