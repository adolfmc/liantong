package cn.sharesdk.framework.p094a.p096b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a.b.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ApiEvent extends BaseEvent {

    /* renamed from: c */
    private static int f2781c;

    /* renamed from: d */
    private static long f2782d;

    /* renamed from: a */
    public int f2783a;

    /* renamed from: b */
    public String f2784b;

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: a */
    protected String mo21927a() {
        return "[API]";
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: b */
    protected int mo21925b() {
        return 5000;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: c */
    protected int mo21924c() {
        return 50;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    public String toString() {
        return super.toString() + '|' + this.f2783a + '|' + this.f2784b;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: d */
    protected long mo21923d() {
        return f2781c;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: e */
    protected long mo21922e() {
        return f2782d;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: f */
    protected void mo21921f() {
        f2781c++;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: a */
    protected void mo21926a(long j) {
        f2782d = j;
    }
}
