package com.baidu.p120ar.recg;

import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.AbstractARProxy;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.recg.OnDeviceIRARProxy */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OnDeviceIRARProxy extends AbstractARProxy implements IOnDeviceIR {
    private WeakReference<IOnDeviceIR> mRealAR;
    private IOnDeviceIRStateChangedListener mStateListener;

    @Override // com.baidu.p120ar.AbstractARProxy
    public void setARCase(AbstractAR abstractAR) {
        if (abstractAR instanceof IOnDeviceIR) {
            IOnDeviceIR iOnDeviceIR = (IOnDeviceIR) abstractAR;
            this.mRealAR = new WeakReference<>(iOnDeviceIR);
            IOnDeviceIRStateChangedListener iOnDeviceIRStateChangedListener = this.mStateListener;
            if (iOnDeviceIRStateChangedListener != null) {
                iOnDeviceIR.setStateChangedListener(iOnDeviceIRStateChangedListener);
            }
        }
    }

    @Override // com.baidu.p120ar.AbstractARProxy
    public void release() {
        WeakReference<IOnDeviceIR> weakReference = this.mRealAR;
        if (weakReference != null) {
            weakReference.clear();
            this.mRealAR = null;
        }
        this.mStateListener = null;
    }

    @Override // com.baidu.p120ar.recg.IOnDeviceIR
    public void setStateChangedListener(IOnDeviceIRStateChangedListener iOnDeviceIRStateChangedListener) {
        WeakReference<IOnDeviceIR> weakReference;
        this.mStateListener = iOnDeviceIRStateChangedListener;
        if (this.mStateListener == null || (weakReference = this.mRealAR) == null || weakReference.get() == null) {
            return;
        }
        this.mRealAR.get().setStateChangedListener(this.mStateListener);
    }

    @Override // com.baidu.p120ar.recg.IOnDeviceIR
    public void retry() {
        WeakReference<IOnDeviceIR> weakReference = this.mRealAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mRealAR.get().retry();
    }
}
