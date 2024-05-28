package com.huawei.secure.android.common.util;

/* renamed from: com.huawei.secure.android.common.util.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5124a {

    /* renamed from: a */
    private String f12164a;

    /* renamed from: b */
    private Character f12165b;

    /* renamed from: c */
    private Character f12166c;

    /* renamed from: d */
    private int f12167d = 0;

    /* renamed from: e */
    private int f12168e = 0;

    public C5124a(String str) {
        this.f12164a = str;
    }

    /* renamed from: c */
    public static boolean m13775c(Character ch) {
        char charValue;
        return ch != null && (charValue = ch.charValue()) >= '0' && charValue <= '7';
    }

    /* renamed from: a */
    public void m13779a(Character ch) {
        this.f12165b = ch;
    }

    /* renamed from: b */
    public int m13778b() {
        return this.f12167d;
    }

    /* renamed from: d */
    public Character m13774d() {
        Character ch = this.f12165b;
        if (ch != null) {
            this.f12165b = null;
            return ch;
        }
        String str = this.f12164a;
        if (str == null || str.length() == 0 || this.f12167d >= this.f12164a.length()) {
            return null;
        }
        String str2 = this.f12164a;
        int i = this.f12167d;
        this.f12167d = i + 1;
        return Character.valueOf(str2.charAt(i));
    }

    /* renamed from: e */
    public Character m13773e() {
        Character m13774d = m13774d();
        if (m13774d != null && m13777b(m13774d)) {
            return m13774d;
        }
        return null;
    }

    /* renamed from: f */
    public Character m13772f() {
        Character m13774d = m13774d();
        if (m13774d != null && m13775c(m13774d)) {
            return m13774d;
        }
        return null;
    }

    /* renamed from: g */
    public Character m13771g() {
        Character ch = this.f12165b;
        if (ch != null) {
            return ch;
        }
        String str = this.f12164a;
        if (str == null || str.length() == 0 || this.f12167d >= this.f12164a.length()) {
            return null;
        }
        return Character.valueOf(this.f12164a.charAt(this.f12167d));
    }

    /* renamed from: h */
    protected String m13770h() {
        String substring = this.f12164a.substring(this.f12167d);
        if (this.f12165b != null) {
            return this.f12165b + substring;
        }
        return substring;
    }

    /* renamed from: i */
    public void m13769i() {
        this.f12165b = this.f12166c;
        this.f12167d = this.f12168e;
    }

    /* renamed from: b */
    public static boolean m13777b(Character ch) {
        if (ch == null) {
            return false;
        }
        char charValue = ch.charValue();
        return (charValue >= '0' && charValue <= '9') || (charValue >= 'a' && charValue <= 'f') || (charValue >= 'A' && charValue <= 'F');
    }

    /* renamed from: a */
    public boolean m13781a() {
        if (this.f12165b != null) {
            return true;
        }
        String str = this.f12164a;
        return (str == null || str.length() == 0 || this.f12167d >= this.f12164a.length()) ? false : true;
    }

    /* renamed from: c */
    public void m13776c() {
        this.f12166c = this.f12165b;
        this.f12168e = this.f12167d;
    }

    /* renamed from: a */
    public boolean m13780a(char c) {
        Character ch = this.f12165b;
        if (ch == null || ch.charValue() != c) {
            String str = this.f12164a;
            return str != null && str.length() != 0 && this.f12167d < this.f12164a.length() && this.f12164a.charAt(this.f12167d) == c;
        }
        return true;
    }
}
