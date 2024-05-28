package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.util.Map;

/* renamed from: com.xiaomi.push.an */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11157an implements InterfaceC11150ai {

    /* renamed from: a */
    private static volatile C11157an f21516a;

    /* renamed from: a */
    private int f21517a = C11156am.f21515a;

    /* renamed from: a */
    private long f21518a;

    /* renamed from: a */
    private Context f21519a;

    /* renamed from: a */
    private InterfaceC11150ai f21520a;

    /* renamed from: a */
    private String f21521a;

    /* renamed from: a */
    private String m4881a(String str) {
        return str == null ? "" : str;
    }

    /* renamed from: a */
    public void m4883a() {
    }

    /* renamed from: b */
    public String m4879b() {
        return null;
    }

    /* renamed from: c */
    public String m4878c() {
        return null;
    }

    /* renamed from: d */
    public String m4877d() {
        return null;
    }

    private C11157an(Context context) {
        this.f21519a = context.getApplicationContext();
        this.f21520a = C11156am.m4884a(context);
        AbstractC11049b.m5282a("create id manager is: " + this.f21517a);
    }

    /* renamed from: a */
    public static C11157an m4882a(Context context) {
        if (f21516a == null) {
            synchronized (C11157an.class) {
                if (f21516a == null) {
                    f21516a = new C11157an(context.getApplicationContext());
                }
            }
        }
        return f21516a;
    }

    @Override // com.xiaomi.push.InterfaceC11150ai
    /* renamed from: a */
    public boolean mo4862a() {
        return this.f21520a.mo4862a();
    }

    @Override // com.xiaomi.push.InterfaceC11150ai
    /* renamed from: a */
    public String mo4863a() {
        if (C11469j.m2972a(this.f21519a)) {
            return m4881a(this.f21520a.mo4863a());
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - this.f21518a) > 86400000) {
            this.f21518a = currentTimeMillis;
            String m4881a = m4881a(this.f21520a.mo4863a());
            this.f21521a = m4881a;
            return m4881a;
        }
        return m4881a(this.f21521a);
    }

    /* renamed from: a */
    public void m4880a(Map<String, String> map) {
        if (map == null) {
            return;
        }
        String m4879b = m4879b();
        if (!TextUtils.isEmpty(m4879b)) {
            map.put("udid", m4879b);
        }
        String mo4863a = mo4863a();
        if (!TextUtils.isEmpty(mo4863a)) {
            map.put("oaid", mo4863a);
        }
        String m4878c = m4878c();
        if (!TextUtils.isEmpty(m4878c)) {
            map.put("vaid", m4878c);
        }
        String m4877d = m4877d();
        if (!TextUtils.isEmpty(m4877d)) {
            map.put("aaid", m4877d);
        }
        map.put("oaid_type", String.valueOf(this.f21517a));
    }
}
