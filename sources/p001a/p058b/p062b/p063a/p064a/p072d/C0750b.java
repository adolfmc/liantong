package p001a.p058b.p062b.p063a.p064a.p072d;

import android.util.Log;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;

/* renamed from: a.b.b.a.a.d.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0750b implements InterfaceC3321a {

    /* renamed from: a */
    public int f2300a = 3;

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    /* renamed from: D */
    public void mo17428D(String str) {
        if (this.f2300a == 5) {
            Log.d("com.baidu.uaq.agent.android", str);
        }
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    /* renamed from: E */
    public void mo17427E(String str) {
        if (this.f2300a >= 4) {
            Log.v("com.baidu.uaq.agent.android", str);
        }
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    /* renamed from: a */
    public void mo17426a(String str, Throwable th) {
        if (this.f2300a >= 1) {
            Log.e("com.baidu.uaq.agent.android", str, th);
        }
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    public void error(String str) {
        if (this.f2300a >= 1) {
            Log.e("com.baidu.uaq.agent.android", str);
        }
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    public int getLevel() {
        return this.f2300a;
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    public void info(String str) {
        if (this.f2300a >= 3) {
            Log.i("com.baidu.uaq.agent.android", str);
        }
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    public void setLevel(int i) {
        if (i > 5 || i < 1) {
            throw new IllegalArgumentException("Log level is not between ERROR and DEBUG");
        }
        this.f2300a = i;
    }

    @Override // com.baidu.uaq.agent.android.logging.InterfaceC3321a
    public void warning(String str) {
        if (this.f2300a >= 2) {
            Log.w("com.baidu.uaq.agent.android", str);
        }
    }
}
