package com.xiaomi.push;

import android.os.Bundle;

/* renamed from: com.xiaomi.push.fq */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11377fq extends AbstractC11375fo {

    /* renamed from: a */
    private int f22335a;

    /* renamed from: a */
    private EnumC11378a f22336a;

    /* renamed from: a */
    private EnumC11379b f22337a;

    /* renamed from: b */
    private String f22338b;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.fq$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum EnumC11378a {
        chat,
        available,
        away,
        xa,
        dnd
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.fq$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum EnumC11379b {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error,
        probe
    }

    public C11377fq(EnumC11379b enumC11379b) {
        this.f22337a = EnumC11379b.available;
        this.f22338b = null;
        this.f22335a = Integer.MIN_VALUE;
        this.f22336a = null;
        m3772a(enumC11379b);
    }

    public C11377fq(Bundle bundle) {
        super(bundle);
        this.f22337a = EnumC11379b.available;
        this.f22338b = null;
        this.f22335a = Integer.MIN_VALUE;
        this.f22336a = null;
        if (bundle.containsKey("ext_pres_type")) {
            this.f22337a = EnumC11379b.valueOf(bundle.getString("ext_pres_type"));
        }
        if (bundle.containsKey("ext_pres_status")) {
            this.f22338b = bundle.getString("ext_pres_status");
        }
        if (bundle.containsKey("ext_pres_prio")) {
            this.f22335a = bundle.getInt("ext_pres_prio");
        }
        if (bundle.containsKey("ext_pres_mode")) {
            this.f22336a = EnumC11378a.valueOf(bundle.getString("ext_pres_mode"));
        }
    }

    @Override // com.xiaomi.push.AbstractC11375fo
    /* renamed from: a */
    public Bundle mo3776a() {
        Bundle mo3776a = super.mo3776a();
        EnumC11379b enumC11379b = this.f22337a;
        if (enumC11379b != null) {
            mo3776a.putString("ext_pres_type", enumC11379b.toString());
        }
        String str = this.f22338b;
        if (str != null) {
            mo3776a.putString("ext_pres_status", str);
        }
        int i = this.f22335a;
        if (i != Integer.MIN_VALUE) {
            mo3776a.putInt("ext_pres_prio", i);
        }
        EnumC11378a enumC11378a = this.f22336a;
        if (enumC11378a != null && enumC11378a != EnumC11378a.available) {
            mo3776a.putString("ext_pres_mode", this.f22336a.toString());
        }
        return mo3776a;
    }

    /* renamed from: a */
    public void m3772a(EnumC11379b enumC11379b) {
        if (enumC11379b == null) {
            throw new NullPointerException("Type cannot be null");
        }
        this.f22337a = enumC11379b;
    }

    /* renamed from: a */
    public void m3771a(String str) {
        this.f22338b = str;
    }

    /* renamed from: a */
    public void m3774a(int i) {
        if (i < -128 || i > 128) {
            throw new IllegalArgumentException("Priority value " + i + " is not valid. Valid range is -128 through 128.");
        }
        this.f22335a = i;
    }

    /* renamed from: a */
    public void m3773a(EnumC11378a enumC11378a) {
        this.f22336a = enumC11378a;
    }

    @Override // com.xiaomi.push.AbstractC11375fo
    /* renamed from: a */
    public String mo3775a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<presence");
        if (m3779p() != null) {
            sb.append(" xmlns=\"");
            sb.append(m3779p());
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
        if (this.f22337a != null) {
            sb.append(" type=\"");
            sb.append(this.f22337a);
            sb.append("\"");
        }
        sb.append(">");
        if (this.f22338b != null) {
            sb.append("<status>");
            sb.append(C11389fx.m3748a(this.f22338b));
            sb.append("</status>");
        }
        if (this.f22335a != Integer.MIN_VALUE) {
            sb.append("<priority>");
            sb.append(this.f22335a);
            sb.append("</priority>");
        }
        EnumC11378a enumC11378a = this.f22336a;
        if (enumC11378a != null && enumC11378a != EnumC11378a.available) {
            sb.append("<show>");
            sb.append(this.f22336a);
            sb.append("</show>");
        }
        sb.append(m3781o());
        C11381fs a = mo3776a();
        if (a != null) {
            sb.append(a.m3769a());
        }
        sb.append("</presence>");
        return sb.toString();
    }
}
