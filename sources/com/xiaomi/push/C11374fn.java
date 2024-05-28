package com.xiaomi.push;

import android.os.Bundle;
import android.text.TextUtils;

/* renamed from: com.xiaomi.push.fn */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11374fn extends AbstractC11375fo {

    /* renamed from: a */
    private boolean f22307a;

    /* renamed from: b */
    private String f22308b;

    /* renamed from: b */
    private boolean f22309b;

    /* renamed from: c */
    private String f22310c;

    /* renamed from: d */
    private String f22311d;

    /* renamed from: e */
    private String f22312e;

    /* renamed from: f */
    private String f22313f;

    /* renamed from: g */
    private String f22314g;

    /* renamed from: h */
    private String f22315h;

    /* renamed from: i */
    private String f22316i;

    /* renamed from: j */
    private String f22317j;

    /* renamed from: k */
    private String f22318k;

    /* renamed from: l */
    private String f22319l;

    public C11374fn() {
        this.f22308b = null;
        this.f22310c = null;
        this.f22307a = false;
        this.f22316i = "";
        this.f22317j = "";
        this.f22318k = "";
        this.f22319l = "";
        this.f22309b = false;
    }

    public C11374fn(Bundle bundle) {
        super(bundle);
        this.f22308b = null;
        this.f22310c = null;
        this.f22307a = false;
        this.f22316i = "";
        this.f22317j = "";
        this.f22318k = "";
        this.f22319l = "";
        this.f22309b = false;
        this.f22308b = bundle.getString("ext_msg_type");
        this.f22311d = bundle.getString("ext_msg_lang");
        this.f22310c = bundle.getString("ext_msg_thread");
        this.f22312e = bundle.getString("ext_msg_sub");
        this.f22313f = bundle.getString("ext_msg_body");
        this.f22314g = bundle.getString("ext_body_encode");
        this.f22315h = bundle.getString("ext_msg_appid");
        this.f22307a = bundle.getBoolean("ext_msg_trans", false);
        this.f22309b = bundle.getBoolean("ext_msg_encrypt", false);
        this.f22316i = bundle.getString("ext_msg_seq");
        this.f22317j = bundle.getString("ext_msg_mseq");
        this.f22318k = bundle.getString("ext_msg_fseq");
        this.f22319l = bundle.getString("ext_msg_status");
    }

    /* renamed from: b */
    public String m3816b() {
        return this.f22308b;
    }

    /* renamed from: a */
    public void m3817a(boolean z) {
        this.f22307a = z;
    }

    /* renamed from: c */
    public String m3813c() {
        return this.f22315h;
    }

    /* renamed from: a */
    public void m3819a(String str) {
        this.f22315h = str;
    }

    /* renamed from: d */
    public String m3811d() {
        return this.f22316i;
    }

    /* renamed from: b */
    public void m3815b(String str) {
        this.f22316i = str;
    }

    /* renamed from: e */
    public String m3809e() {
        return this.f22317j;
    }

    /* renamed from: c */
    public void m3812c(String str) {
        this.f22317j = str;
    }

    /* renamed from: f */
    public String m3807f() {
        return this.f22318k;
    }

    /* renamed from: d */
    public void m3810d(String str) {
        this.f22318k = str;
    }

    /* renamed from: g */
    public String m3805g() {
        return this.f22319l;
    }

    /* renamed from: e */
    public void m3808e(String str) {
        this.f22319l = str;
    }

    /* renamed from: f */
    public void m3806f(String str) {
        this.f22308b = str;
    }

    /* renamed from: b */
    public void m3814b(boolean z) {
        this.f22309b = z;
    }

    /* renamed from: g */
    public void m3804g(String str) {
        this.f22312e = str;
    }

    /* renamed from: h */
    public void m3802h(String str) {
        this.f22313f = str;
    }

    /* renamed from: a */
    public void m3818a(String str, String str2) {
        this.f22313f = str;
        this.f22314g = str2;
    }

    /* renamed from: i */
    public void m3801i(String str) {
        this.f22310c = str;
    }

    /* renamed from: h */
    public String m3803h() {
        return this.f22311d;
    }

    /* renamed from: j */
    public void m3800j(String str) {
        this.f22311d = str;
    }

    @Override // com.xiaomi.push.AbstractC11375fo
    /* renamed from: a */
    public Bundle mo3776a() {
        Bundle mo3776a = super.mo3776a();
        if (!TextUtils.isEmpty(this.f22308b)) {
            mo3776a.putString("ext_msg_type", this.f22308b);
        }
        String str = this.f22311d;
        if (str != null) {
            mo3776a.putString("ext_msg_lang", str);
        }
        String str2 = this.f22312e;
        if (str2 != null) {
            mo3776a.putString("ext_msg_sub", str2);
        }
        String str3 = this.f22313f;
        if (str3 != null) {
            mo3776a.putString("ext_msg_body", str3);
        }
        if (!TextUtils.isEmpty(this.f22314g)) {
            mo3776a.putString("ext_body_encode", this.f22314g);
        }
        String str4 = this.f22310c;
        if (str4 != null) {
            mo3776a.putString("ext_msg_thread", str4);
        }
        String str5 = this.f22315h;
        if (str5 != null) {
            mo3776a.putString("ext_msg_appid", str5);
        }
        if (this.f22307a) {
            mo3776a.putBoolean("ext_msg_trans", true);
        }
        if (!TextUtils.isEmpty(this.f22316i)) {
            mo3776a.putString("ext_msg_seq", this.f22316i);
        }
        if (!TextUtils.isEmpty(this.f22317j)) {
            mo3776a.putString("ext_msg_mseq", this.f22317j);
        }
        if (!TextUtils.isEmpty(this.f22318k)) {
            mo3776a.putString("ext_msg_fseq", this.f22318k);
        }
        if (this.f22309b) {
            mo3776a.putBoolean("ext_msg_encrypt", true);
        }
        if (!TextUtils.isEmpty(this.f22319l)) {
            mo3776a.putString("ext_msg_status", this.f22319l);
        }
        return mo3776a;
    }

    @Override // com.xiaomi.push.AbstractC11375fo
    /* renamed from: a */
    public String mo3775a() {
        C11381fs a;
        StringBuilder sb = new StringBuilder();
        sb.append("<message");
        if (m3779p() != null) {
            sb.append(" xmlns=\"");
            sb.append(m3779p());
            sb.append("\"");
        }
        if (this.f22311d != null) {
            sb.append(" xml:lang=\"");
            sb.append(m3803h());
            sb.append("\"");
        }
        if (m3790j() != null) {
            sb.append(" id=\"");
            sb.append(m3790j());
            sb.append("\"");
        }
        if (m3787l() != null) {
            sb.append(" to=\"");
            sb.append(C11389fx.m3748a(m3787l()));
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(m3811d())) {
            sb.append(" seq=\"");
            sb.append(m3811d());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(m3809e())) {
            sb.append(" mseq=\"");
            sb.append(m3809e());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(m3807f())) {
            sb.append(" fseq=\"");
            sb.append(m3807f());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(m3805g())) {
            sb.append(" status=\"");
            sb.append(m3805g());
            sb.append("\"");
        }
        if (m3785m() != null) {
            sb.append(" from=\"");
            sb.append(C11389fx.m3748a(m3785m()));
            sb.append("\"");
        }
        if (m3789k() != null) {
            sb.append(" chid=\"");
            sb.append(C11389fx.m3748a(m3789k()));
            sb.append("\"");
        }
        if (this.f22307a) {
            sb.append(" transient=\"true\"");
        }
        if (!TextUtils.isEmpty(this.f22315h)) {
            sb.append(" appid=\"");
            sb.append(m3813c());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(this.f22308b)) {
            sb.append(" type=\"");
            sb.append(this.f22308b);
            sb.append("\"");
        }
        if (this.f22309b) {
            sb.append(" s=\"1\"");
        }
        sb.append(">");
        if (this.f22312e != null) {
            sb.append("<subject>");
            sb.append(C11389fx.m3748a(this.f22312e));
            sb.append("</subject>");
        }
        if (this.f22313f != null) {
            sb.append("<body");
            if (!TextUtils.isEmpty(this.f22314g)) {
                sb.append(" encode=\"");
                sb.append(this.f22314g);
                sb.append("\"");
            }
            sb.append(">");
            sb.append(C11389fx.m3748a(this.f22313f));
            sb.append("</body>");
        }
        if (this.f22310c != null) {
            sb.append("<thread>");
            sb.append(this.f22310c);
            sb.append("</thread>");
        }
        if ("error".equalsIgnoreCase(this.f22308b) && (a = mo3776a()) != null) {
            sb.append(a.m3769a());
        }
        sb.append(m3781o());
        sb.append("</message>");
        return sb.toString();
    }

    @Override // com.xiaomi.push.AbstractC11375fo
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C11374fn c11374fn = (C11374fn) obj;
        if (super.equals(c11374fn)) {
            String str = this.f22313f;
            if (str == null ? c11374fn.f22313f == null : str.equals(c11374fn.f22313f)) {
                String str2 = this.f22311d;
                if (str2 == null ? c11374fn.f22311d == null : str2.equals(c11374fn.f22311d)) {
                    String str3 = this.f22312e;
                    if (str3 == null ? c11374fn.f22312e == null : str3.equals(c11374fn.f22312e)) {
                        String str4 = this.f22310c;
                        if (str4 == null ? c11374fn.f22310c == null : str4.equals(c11374fn.f22310c)) {
                            return this.f22308b == c11374fn.f22308b;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // com.xiaomi.push.AbstractC11375fo
    public int hashCode() {
        String str = this.f22308b;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f22313f;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.f22310c;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.f22311d;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.f22312e;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }
}
