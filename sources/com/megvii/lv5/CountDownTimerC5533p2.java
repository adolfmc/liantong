package com.megvii.lv5;

import android.os.CountDownTimer;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.p2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CountDownTimerC5533p2 extends CountDownTimer {

    /* renamed from: a */
    public WeakReference<TextView> f13167a;

    /* renamed from: b */
    public long f13168b;

    /* renamed from: c */
    public long f13169c;

    /* renamed from: d */
    public InterfaceC5534a f13170d;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.p2$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5534a {
        /* renamed from: a */
        void mo13067a();
    }

    public CountDownTimerC5533p2(TextView textView, long j, long j2, InterfaceC5534a interfaceC5534a) {
        super(j, j2);
        this.f13170d = null;
        this.f13167a = new WeakReference<>(textView);
        this.f13168b = j;
        this.f13170d = interfaceC5534a;
    }

    /* renamed from: a */
    public void m13211a() {
        cancel();
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        if (this.f13167a.get() == null) {
            cancel();
            return;
        }
        this.f13167a.get().setText("");
        this.f13170d = null;
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        if (this.f13167a.get() == null) {
            cancel();
            return;
        }
        this.f13167a.get().setText((j / 1000) + "s");
        this.f13169c = j;
        InterfaceC5534a interfaceC5534a = this.f13170d;
        if (interfaceC5534a != null) {
            if (((j + 999) / 1000) * 2 == this.f13168b / 1000) {
                interfaceC5534a.mo13067a();
            }
        }
    }
}
