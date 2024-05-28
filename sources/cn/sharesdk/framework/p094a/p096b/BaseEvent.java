package cn.sharesdk.framework.p094a.p096b;

import com.mob.MobSDK;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a.b.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class BaseEvent {

    /* renamed from: e */
    public long f2791e;

    /* renamed from: f */
    public String f2792f;

    /* renamed from: g */
    public String f2793g;

    /* renamed from: h */
    public int f2794h;

    /* renamed from: i */
    public String f2795i;

    /* renamed from: j */
    public int f2796j;

    /* renamed from: k */
    public String f2797k;

    /* renamed from: l */
    public String f2798l;

    /* renamed from: a */
    protected abstract String mo21927a();

    /* renamed from: a */
    protected abstract void mo21926a(long j);

    /* renamed from: b */
    protected abstract int mo21925b();

    /* renamed from: c */
    protected abstract int mo21924c();

    /* renamed from: d */
    protected abstract long mo21923d();

    /* renamed from: e */
    protected abstract long mo21922e();

    /* renamed from: f */
    protected abstract void mo21921f();

    /* renamed from: g */
    public boolean mo21929g() {
        int mo21925b = mo21925b();
        int mo21924c = mo21924c();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - mo21922e() < mo21925b) {
            return mo21923d() < ((long) mo21924c);
        }
        mo21926a(currentTimeMillis);
        return true;
    }

    /* renamed from: h */
    public void mo21928h() {
        mo21921f();
    }

    public String toString() {
        return mo21927a() + ':' + this.f2791e + '|' + this.f2792f + '|' + MobSDK.getAppkey() + '|' + this.f2793g + '|' + this.f2794h + '|' + this.f2795i + '|' + this.f2796j + '|' + this.f2797k;
    }
}
