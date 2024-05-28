package p001a.p058b.p062b.p063a.p064a.p072d;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;

/* renamed from: a.b.b.a.a.d.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0751c implements InterfaceC3321a {

    /* renamed from: a */
    public InterfaceC3321a f2301a = new C0752d();

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    /* renamed from: D */
    public void mo17428D(String str) {
        synchronized (this) {
            this.f2301a.mo17428D(str);
        }
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    /* renamed from: E */
    public void mo17427E(String str) {
        synchronized (this) {
            this.f2301a.mo17427E(str);
        }
    }

    /* renamed from: a */
    public void m22270a(InterfaceC3321a interfaceC3321a) {
        synchronized (this) {
            this.f2301a = interfaceC3321a;
        }
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    /* renamed from: a */
    public void mo17426a(String str, Throwable th) {
        synchronized (this) {
            this.f2301a.mo17426a(str, th);
        }
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    public void error(String str) {
        synchronized (this) {
            this.f2301a.error(str);
        }
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    public int getLevel() {
        int level;
        synchronized (this) {
            level = this.f2301a.getLevel();
        }
        return level;
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    public void info(String str) {
        synchronized (this) {
            this.f2301a.info(str);
        }
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    public void setLevel(int i) {
        synchronized (this) {
            this.f2301a.setLevel(i);
        }
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    public void warning(String str) {
        synchronized (this) {
            this.f2301a.warning(str);
        }
    }
}
