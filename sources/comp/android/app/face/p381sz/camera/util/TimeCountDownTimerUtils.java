package comp.android.app.face.p381sz.camera.util;

import android.os.CountDownTimer;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: comp.android.app.face.sz.camera.util.TimeCountDownTimerUtils */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TimeCountDownTimerUtils extends CountDownTimer {
    private InterfaceC11780a timeCountDownTimerListening;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: comp.android.app.face.sz.camera.util.TimeCountDownTimerUtils$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11780a {
        /* renamed from: a */
        void m2153a();

        /* renamed from: a */
        void m2152a(long j);
    }

    public TimeCountDownTimerUtils(long j, long j2, InterfaceC11780a interfaceC11780a) {
        super(j, j2);
        this.timeCountDownTimerListening = interfaceC11780a;
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        this.timeCountDownTimerListening.m2153a();
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        this.timeCountDownTimerListening.m2152a(j);
    }
}
