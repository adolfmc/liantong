package cn.sharesdk.framework.p094a.p096b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a.b.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DemoEvent extends BaseEvent {

    /* renamed from: d */
    private static int f2799d;

    /* renamed from: m */
    private static long f2800m;

    /* renamed from: a */
    public String f2801a;

    /* renamed from: b */
    public int f2802b;

    /* renamed from: c */
    public String f2803c = "";

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: a */
    protected String mo21927a() {
        return "[EVT]";
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: b */
    protected int mo21925b() {
        return 5000;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: c */
    protected int mo21924c() {
        return 30;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    public String toString() {
        return super.toString() + '|' + this.f2801a + '|' + this.f2802b + '|' + this.f2803c;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: d */
    protected long mo21923d() {
        return f2799d;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: e */
    protected long mo21922e() {
        return f2800m;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: f */
    protected void mo21921f() {
        f2799d++;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: a */
    protected void mo21926a(long j) {
        f2800m = j;
    }
}
