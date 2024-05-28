package com.megvii.lv5;

import android.content.Context;
import java.util.HashMap;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.y0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5658y0 {

    /* renamed from: a */
    public static C5658y0 f13922a;

    /* renamed from: a */
    public static C5658y0 m12893a() {
        if (f13922a == null) {
            f13922a = new C5658y0();
        }
        return f13922a;
    }

    /* renamed from: a */
    public void m12892a(Context context, String str, String str2, int i, byte[] bArr, int i2, InterfaceC5546r0 interfaceC5546r0) {
        C5382a5 c5382a5 = new C5382a5();
        c5382a5.m13614a("base64_decode", "1");
        c5382a5.m13614a("biz_token", str2);
        c5382a5.m13614a("type", i + "");
        c5382a5.m13613a("meglive_data", bArr, "application/octet-stream", c5382a5.f12376a, "meglive_data");
        if (i2 > -1) {
            c5382a5.m13614a("credit_agree", i2 + "");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", new C5418e(null).m13557b());
        C5619u0 c5619u0 = new C5619u0(this, str + "/faceid/v5/sdk/internal/upload", new C5552s0(this, interfaceC5546r0), new C5613t0(this, interfaceC5546r0), hashMap);
        c5619u0.f13909h = false;
        c5619u0.f12398l = c5382a5;
        C5423e4.m13554a((Context) null).f12545b = 5000;
        C5423e4.m13554a((Context) null).m13553a(c5619u0);
    }
}
