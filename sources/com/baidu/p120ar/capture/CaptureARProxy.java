package com.baidu.p120ar.capture;

import android.graphics.Bitmap;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.AbstractARProxy;
import com.baidu.p120ar.callback.ICallbackWith;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.capture.CaptureARProxy */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CaptureARProxy extends AbstractARProxy implements ICapture {
    private ICaptureAbilityListener mAbilityListener;
    private WeakReference<ICapture> mRef;

    @Override // com.baidu.p120ar.AbstractARProxy
    public void setARCase(AbstractAR abstractAR) {
        if (abstractAR == null) {
            WeakReference<ICapture> weakReference = this.mRef;
            if (weakReference != null) {
                weakReference.clear();
                this.mRef = null;
            }
        } else if (abstractAR instanceof ICapture) {
            this.mRef = new WeakReference<>((ICapture) abstractAR);
            if (this.mAbilityListener != null) {
                this.mRef.get().setAbilityListener(this.mAbilityListener);
            }
        }
    }

    @Override // com.baidu.p120ar.AbstractARProxy
    public void release() {
        this.mAbilityListener = null;
        WeakReference<ICapture> weakReference = this.mRef;
        if (weakReference != null) {
            weakReference.clear();
            this.mRef = null;
        }
    }

    @Override // com.baidu.p120ar.capture.ICapture
    public void setAbilityListener(ICaptureAbilityListener iCaptureAbilityListener) {
        WeakReference<ICapture> weakReference = this.mRef;
        if (weakReference != null && weakReference.get() != null) {
            this.mRef.get().setAbilityListener(iCaptureAbilityListener);
        } else {
            this.mAbilityListener = iCaptureAbilityListener;
        }
    }

    @Override // com.baidu.p120ar.capture.ICapture
    public void setCaptureCallback(ICallbackWith<ICaptureResult> iCallbackWith) {
        WeakReference<ICapture> weakReference = this.mRef;
        if (weakReference == null || weakReference.get() == null || iCallbackWith == null) {
            return;
        }
        this.mRef.get().setCaptureCallback(iCallbackWith);
    }

    @Override // com.baidu.p120ar.capture.ICapture
    public void capture(ICallbackWith<ICaptureResult> iCallbackWith) {
        WeakReference<ICapture> weakReference = this.mRef;
        if (weakReference == null || weakReference.get() == null || iCallbackWith == null) {
            return;
        }
        this.mRef.get().capture(iCallbackWith);
    }

    @Override // com.baidu.p120ar.capture.ICapture
    public void sendImageToLua(Bitmap... bitmapArr) {
        WeakReference<ICapture> weakReference = this.mRef;
        if (weakReference == null || weakReference.get() == null || bitmapArr == null || bitmapArr.length <= 0) {
            return;
        }
        this.mRef.get().sendImageToLua(bitmapArr);
    }

    @Override // com.baidu.p120ar.capture.ICapture
    public void sendBase64ImageToLua(String... strArr) {
        WeakReference<ICapture> weakReference = this.mRef;
        if (weakReference == null || weakReference.get() == null || strArr == null || strArr.length <= 0) {
            return;
        }
        this.mRef.get().sendBase64ImageToLua(strArr);
    }
}
