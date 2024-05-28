package cn.sharesdk.framework.p094a.p096b;

import android.text.TextUtils;
import cn.sharesdk.framework.p094a.p095a.SharePrefrenceUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a.b.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ExitEvent extends BaseEvent {

    /* renamed from: b */
    private static int f2804b;

    /* renamed from: c */
    private static long f2805c;

    /* renamed from: a */
    public long f2806a;

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: a */
    protected String mo21927a() {
        return "[EXT]";
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: b */
    protected int mo21925b() {
        return 5000;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: c */
    protected int mo21924c() {
        return 5;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: g */
    public boolean mo21929g() {
        SharePrefrenceUtil m21961a = SharePrefrenceUtil.m21961a();
        f2804b = m21961a.m21933j("insertExitEventCount");
        f2805c = m21961a.m21935i("lastInsertExitEventTime");
        return super.mo21929g();
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: h */
    public void mo21928h() {
        super.mo21928h();
        SharePrefrenceUtil m21961a = SharePrefrenceUtil.m21961a();
        m21961a.m21957a("lastInsertExitEventTime", Long.valueOf(f2805c));
        m21961a.m21958a("insertExitEventCount", f2804b);
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: d */
    protected long mo21923d() {
        return f2804b;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: e */
    protected long mo21922e() {
        return f2805c;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: f */
    protected void mo21921f() {
        f2804b++;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: a */
    protected void mo21926a(long j) {
        f2805c = j;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|');
        if (!TextUtils.isEmpty(this.f2798l)) {
            sb.append(this.f2798l);
        }
        sb.append('|');
        sb.append(Math.round(((float) this.f2806a) / 1000.0f));
        return sb.toString();
    }
}
