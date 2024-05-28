package com.megvii.lv5;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.g */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5445g extends CameraCaptureSession.CaptureCallback {

    /* renamed from: a */
    public final /* synthetic */ C5426f f12608a;

    public C5445g(C5426f c5426f) {
        this.f12608a = c5426f;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
        C5426f c5426f = this.f12608a;
        c5426f.getClass();
        try {
            if (c5426f.f12552D == null) {
                return;
            }
            c5426f.f12551C.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
            c5426f.f12552D.capture(c5426f.f12551C.build(), c5426f.f12562N, c5426f.f12567m);
            c5426f.f12560L = 0;
            c5426f.f12552D.setRepeatingRequest(c5426f.f12551C.build(), null, c5426f.f12567m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
