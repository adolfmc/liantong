package com.alipay.security.mobile.module.p114d;

import com.alipay.security.mobile.module.p110a.C2081a;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.d.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2096a {

    /* renamed from: a */
    private String f3992a;

    /* renamed from: b */
    private String f3993b;

    /* renamed from: c */
    private String f3994c;

    /* renamed from: d */
    private String f3995d;

    /* renamed from: e */
    private String f3996e;

    /* renamed from: f */
    private String f3997f;

    /* renamed from: g */
    private String f3998g;

    public C2096a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.f3992a = str;
        this.f3993b = str2;
        this.f3994c = str3;
        this.f3995d = str4;
        this.f3996e = str5;
        this.f3997f = str6;
        this.f3998g = str7;
    }

    public final String toString() {
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        String str2;
        StringBuilder sb3;
        String str3;
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append("," + this.f3992a);
        stringBuffer.append("," + this.f3993b);
        stringBuffer.append("," + this.f3994c);
        stringBuffer.append("," + this.f3995d);
        if (C2081a.m20577a(this.f3996e) || this.f3996e.length() < 20) {
            sb = new StringBuilder(",");
            str = this.f3996e;
        } else {
            sb = new StringBuilder(",");
            str = this.f3996e.substring(0, 20);
        }
        sb.append(str);
        stringBuffer.append(sb.toString());
        if (C2081a.m20577a(this.f3997f) || this.f3997f.length() < 20) {
            sb2 = new StringBuilder(",");
            str2 = this.f3997f;
        } else {
            sb2 = new StringBuilder(",");
            str2 = this.f3997f.substring(0, 20);
        }
        sb2.append(str2);
        stringBuffer.append(sb2.toString());
        if (C2081a.m20577a(this.f3998g) || this.f3998g.length() < 20) {
            sb3 = new StringBuilder(",");
            str3 = this.f3998g;
        } else {
            sb3 = new StringBuilder(",");
            str3 = this.f3998g.substring(0, 20);
        }
        sb3.append(str3);
        stringBuffer.append(sb3.toString());
        return stringBuffer.toString();
    }
}
