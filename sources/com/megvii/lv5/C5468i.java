package com.megvii.lv5;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.i */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5468i extends CameraCaptureSession.StateCallback {

    /* renamed from: a */
    public final /* synthetic */ C5426f f12724a;

    public C5468i(C5426f c5426f) {
        this.f12724a = c5426f;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
        C5426f c5426f = this.f12724a;
        if (c5426f.m13547a(c5426f.f12570p)) {
            try {
                this.f12724a.f12551C.set(CaptureRequest.CONTROL_AF_MODE, 3);
                C5426f c5426f2 = this.f12724a;
                c5426f2.f12552D = cameraCaptureSession;
                cameraCaptureSession.setRepeatingRequest(c5426f2.f12551C.build(), null, this.f12724a.f12567m);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
