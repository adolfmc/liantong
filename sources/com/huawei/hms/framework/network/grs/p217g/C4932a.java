package com.huawei.hms.framework.network.grs.p217g;

import android.content.Context;
import android.net.Uri;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.p215e.C4925c;
import java.util.concurrent.Callable;

/* renamed from: com.huawei.hms.framework.network.grs.g.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4932a {

    /* renamed from: a */
    protected C4937d f11246a;

    /* renamed from: b */
    private final String f11247b;

    /* renamed from: c */
    private final C4935c f11248c;

    /* renamed from: d */
    private final int f11249d;

    /* renamed from: e */
    private final Context f11250e;

    /* renamed from: f */
    private final String f11251f;

    /* renamed from: g */
    private final GrsBaseInfo f11252g;

    /* renamed from: h */
    private final C4925c f11253h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.framework.network.grs.g.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum EnumC4933a {
        GRSPOST,
        GRSGET,
        GRSDEFAULT
    }

    public C4932a(String str, int i, C4935c c4935c, Context context, String str2, GrsBaseInfo grsBaseInfo, C4925c c4925c) {
        this.f11247b = str;
        this.f11248c = c4935c;
        this.f11249d = i;
        this.f11250e = context;
        this.f11251f = str2;
        this.f11252g = grsBaseInfo;
        this.f11253h = c4925c;
    }

    /* renamed from: a */
    private String m14953a(String str) {
        return Uri.parse(str).getPath();
    }

    /* renamed from: h */
    private EnumC4933a m14946h() {
        if (this.f11247b.isEmpty()) {
            return EnumC4933a.GRSDEFAULT;
        }
        String m14953a = m14953a(this.f11247b);
        return m14953a.contains("1.0") ? EnumC4933a.GRSGET : m14953a.contains("2.0") ? EnumC4933a.GRSPOST : EnumC4933a.GRSDEFAULT;
    }

    /* renamed from: a */
    public Context m14954a() {
        return this.f11250e;
    }

    /* renamed from: b */
    public C4935c m14952b() {
        return this.f11248c;
    }

    /* renamed from: c */
    public String m14951c() {
        return this.f11247b;
    }

    /* renamed from: d */
    public int m14950d() {
        return this.f11249d;
    }

    /* renamed from: e */
    public String m14949e() {
        return this.f11251f;
    }

    /* renamed from: f */
    public C4925c m14948f() {
        return this.f11253h;
    }

    /* renamed from: g */
    public Callable<C4937d> m14947g() {
        if (EnumC4933a.GRSDEFAULT.equals(m14946h())) {
            return null;
        }
        return EnumC4933a.GRSGET.equals(m14946h()) ? new CallableC4940f(this.f11247b, this.f11249d, this.f11248c, this.f11250e, this.f11251f, this.f11252g) : new CallableC4941g(this.f11247b, this.f11249d, this.f11248c, this.f11250e, this.f11251f, this.f11252g, this.f11253h);
    }
}
