package com.example.asus.detectionandalign.utils;

import android.os.CountDownTimer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.utils.j */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CountDownTimerC4299j extends CountDownTimer {

    /* renamed from: a */
    private InterfaceC4300a f10097a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.example.asus.detectionandalign.utils.j$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC4300a {
        /* renamed from: a */
        void mo15939a();

        /* renamed from: a */
        void mo15938a(long j);
    }

    public CountDownTimerC4299j(long j, long j2, InterfaceC4300a interfaceC4300a) {
        super(j, j2);
        this.f10097a = interfaceC4300a;
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        this.f10097a.mo15939a();
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        this.f10097a.mo15938a(j);
    }
}
