package com.heytap.mcssdk.p205c;

import com.heytap.msp.push.mode.BaseMode;
import java.util.List;

/* renamed from: com.heytap.mcssdk.c.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4720b extends BaseMode {

    /* renamed from: a */
    private static final String f10591a = "&";

    /* renamed from: b */
    private String f10592b;

    /* renamed from: c */
    private String f10593c;

    /* renamed from: d */
    private String f10594d;

    /* renamed from: e */
    private String f10595e;

    /* renamed from: f */
    private int f10596f;

    /* renamed from: g */
    private String f10597g;

    /* renamed from: h */
    private int f10598h = -2;

    /* renamed from: i */
    private String f10599i;

    /* renamed from: j */
    private String f10600j;

    /* renamed from: a */
    public static <T> String m15566a(List<T> list) {
        StringBuilder sb = new StringBuilder();
        for (T t : list) {
            sb.append(t);
            sb.append("&");
        }
        return sb.toString();
    }

    /* renamed from: a */
    public String m15569a() {
        return this.f10592b;
    }

    /* renamed from: a */
    public void m15568a(int i) {
        this.f10596f = i;
    }

    /* renamed from: a */
    public void m15567a(String str) {
        this.f10592b = str;
    }

    /* renamed from: b */
    public String m15565b() {
        return this.f10593c;
    }

    /* renamed from: b */
    public void m15564b(int i) {
        this.f10598h = i;
    }

    /* renamed from: b */
    public void m15563b(String str) {
        this.f10593c = str;
    }

    /* renamed from: c */
    public String m15562c() {
        return this.f10594d;
    }

    /* renamed from: c */
    public void m15561c(String str) {
        this.f10594d = str;
    }

    /* renamed from: d */
    public String m15560d() {
        return this.f10595e;
    }

    /* renamed from: d */
    public void m15559d(String str) {
        this.f10595e = str;
    }

    /* renamed from: e */
    public int m15558e() {
        return this.f10596f;
    }

    /* renamed from: e */
    public void m15557e(String str) {
        this.f10597g = str;
    }

    /* renamed from: f */
    public String m15556f() {
        return this.f10597g;
    }

    /* renamed from: f */
    public void m15555f(String str) {
        this.f10600j = str;
    }

    /* renamed from: g */
    public int m15554g() {
        return this.f10598h;
    }

    /* renamed from: g */
    public void m15553g(String str) {
        this.f10599i = str;
    }

    @Override // com.heytap.msp.push.mode.BaseMode
    public int getType() {
        return 4105;
    }

    /* renamed from: h */
    public String m15552h() {
        return this.f10600j;
    }

    /* renamed from: i */
    public String m15551i() {
        return this.f10599i;
    }

    public String toString() {
        return "CallBackResult{, mRegisterID='" + this.f10594d + "', mSdkVersion='" + this.f10595e + "', mCommand=" + this.f10596f + "', mContent='" + this.f10597g + "', mAppPackage=" + this.f10599i + "', mResponseCode=" + this.f10598h + ", miniProgramPkg=" + this.f10600j + '}';
    }
}
