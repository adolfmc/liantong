package com.baidu.p120ar.cloud;

import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.AbstractARProxy;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.cloud.CloudIRARProxy */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CloudIRARProxy extends AbstractARProxy implements ICloudIR {
    private WeakReference<ICloudIR> mRealAR;
    private ICloudIRStateChangedListener mStateListener;

    @Override // com.baidu.p120ar.AbstractARProxy
    public void setARCase(AbstractAR abstractAR) {
        if (abstractAR instanceof ICloudIR) {
            ICloudIR iCloudIR = (ICloudIR) abstractAR;
            this.mRealAR = new WeakReference<>(iCloudIR);
            ICloudIRStateChangedListener iCloudIRStateChangedListener = this.mStateListener;
            if (iCloudIRStateChangedListener != null) {
                iCloudIR.setStateChangedListener(iCloudIRStateChangedListener);
            }
        }
    }

    @Override // com.baidu.p120ar.AbstractARProxy
    public void release() {
        WeakReference<ICloudIR> weakReference = this.mRealAR;
        if (weakReference != null) {
            weakReference.clear();
            this.mRealAR = null;
        }
        this.mStateListener = null;
    }

    @Override // com.baidu.p120ar.cloud.ICloudIR
    public void setStateChangedListener(ICloudIRStateChangedListener iCloudIRStateChangedListener) {
        WeakReference<ICloudIR> weakReference;
        this.mStateListener = iCloudIRStateChangedListener;
        if (this.mStateListener == null || (weakReference = this.mRealAR) == null || weakReference.get() == null) {
            return;
        }
        this.mRealAR.get().setStateChangedListener(this.mStateListener);
    }

    @Override // com.baidu.p120ar.cloud.ICloudIR
    public void resume() {
        WeakReference<ICloudIR> weakReference = this.mRealAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mRealAR.get().resume();
    }

    @Override // com.baidu.p120ar.cloud.ICloudIR
    public void pause() {
        WeakReference<ICloudIR> weakReference = this.mRealAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mRealAR.get().pause();
    }
}
