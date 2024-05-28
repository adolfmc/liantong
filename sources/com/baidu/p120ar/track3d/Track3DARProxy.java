package com.baidu.p120ar.track3d;

import android.graphics.Bitmap;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.AbstractARProxy;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.track3d.Track3DARProxy */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Track3DARProxy extends AbstractARProxy implements ITrack3D {
    private WeakReference<ITrack3D> mRealAR;
    private ITrack3DStateChangedListener mStateListener;

    @Override // com.baidu.p120ar.AbstractARProxy
    public void setARCase(AbstractAR abstractAR) {
        if (abstractAR instanceof ITrack3D) {
            ITrack3D iTrack3D = (ITrack3D) abstractAR;
            this.mRealAR = new WeakReference<>(iTrack3D);
            ITrack3DStateChangedListener iTrack3DStateChangedListener = this.mStateListener;
            if (iTrack3DStateChangedListener != null) {
                iTrack3D.setStateChangedListener(iTrack3DStateChangedListener);
            }
        }
    }

    @Override // com.baidu.p120ar.AbstractARProxy
    public void release() {
        WeakReference<ITrack3D> weakReference = this.mRealAR;
        if (weakReference != null) {
            weakReference.clear();
            this.mRealAR = null;
        }
        this.mStateListener = null;
    }

    @Override // com.baidu.p120ar.track3d.ITrack3D
    public void setStateChangedListener(ITrack3DStateChangedListener iTrack3DStateChangedListener) {
        WeakReference<ITrack3D> weakReference;
        this.mStateListener = iTrack3DStateChangedListener;
        if (this.mStateListener == null || (weakReference = this.mRealAR) == null || weakReference.get() == null) {
            return;
        }
        this.mRealAR.get().setStateChangedListener(this.mStateListener);
    }

    @Override // com.baidu.p120ar.track3d.ITrack3D
    public void haltTrack() {
        WeakReference<ITrack3D> weakReference = this.mRealAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mRealAR.get().haltTrack();
    }

    @Override // com.baidu.p120ar.track3d.ITrack3D
    public void resumeTrack() {
        WeakReference<ITrack3D> weakReference = this.mRealAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mRealAR.get().resumeTrack();
    }

    @Override // com.baidu.p120ar.track3d.ITrack3D
    public Bitmap getTargetBitmap() {
        WeakReference<ITrack3D> weakReference = this.mRealAR;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.mRealAR.get().getTargetBitmap();
    }
}
