package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.service.C11615q;
import java.util.HashMap;

/* renamed from: com.xiaomi.push.cv */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11255cv {

    /* renamed from: a */
    private final String f21783a = "power_consumption_stats";

    /* renamed from: b */
    private final String f21784b = "off_up_ct";

    /* renamed from: c */
    private final String f21785c = "off_dn_ct";

    /* renamed from: d */
    private final String f21786d = "off_ping_ct";

    /* renamed from: e */
    private final String f21787e = "off_pong_ct";

    /* renamed from: f */
    private final String f21788f = "off_dur";

    /* renamed from: g */
    private final String f21789g = "on_up_ct";

    /* renamed from: h */
    private final String f21790h = "on_dn_ct";

    /* renamed from: i */
    private final String f21791i = "on_ping_ct";

    /* renamed from: j */
    private final String f21792j = "on_pong_ct";

    /* renamed from: k */
    private final String f21793k = "on_dur";

    /* renamed from: l */
    private final String f21794l = "start_time";

    /* renamed from: m */
    private final String f21795m = "end_time";

    /* renamed from: n */
    private final String f21796n = "xmsf_vc";

    /* renamed from: o */
    private final String f21797o = "android_vc";

    /* renamed from: p */
    private final String f21798p = "uuid";

    /* renamed from: a */
    public void m4425a(Context context, C11254cu c11254cu) {
        if (c11254cu == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("off_up_ct", Integer.valueOf(c11254cu.m4453a()));
        hashMap.put("off_dn_ct", Integer.valueOf(c11254cu.m4449b()));
        hashMap.put("off_ping_ct", Integer.valueOf(c11254cu.m4445c()));
        hashMap.put("off_pong_ct", Integer.valueOf(c11254cu.m4441d()));
        hashMap.put("off_dur", Long.valueOf(c11254cu.m4452a()));
        hashMap.put("on_up_ct", Integer.valueOf(c11254cu.m4437e()));
        hashMap.put("on_dn_ct", Integer.valueOf(c11254cu.m4435f()));
        hashMap.put("on_ping_ct", Integer.valueOf(c11254cu.m4433g()));
        hashMap.put("on_pong_ct", Integer.valueOf(c11254cu.m4431h()));
        hashMap.put("on_dur", Long.valueOf(c11254cu.m4448b()));
        hashMap.put("start_time", Long.valueOf(c11254cu.m4444c()));
        hashMap.put("end_time", Long.valueOf(c11254cu.m4440d()));
        hashMap.put("xmsf_vc", Integer.valueOf(c11254cu.m4429i()));
        hashMap.put("android_vc", Integer.valueOf(c11254cu.m4427j()));
        hashMap.put("uuid", C11615q.m2429a(context));
        C11321eh.m4047a().mo4045a("power_consumption_stats", hashMap);
    }
}
