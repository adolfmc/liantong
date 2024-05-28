package com.baidu.p120ar.track2d;

import android.graphics.Bitmap;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.AbstractARProxy;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.track2d.Track2DARProxy */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Track2DARProxy extends AbstractARProxy implements ITrack2D {
    private WeakReference<ITrack2D> mRealAR;
    private ITrack2DStateChangedListener mStateListener;

    @Override // com.baidu.p120ar.AbstractARProxy
    public void setARCase(AbstractAR abstractAR) {
        if (abstractAR instanceof ITrack2D) {
            ITrack2D iTrack2D = (ITrack2D) abstractAR;
            this.mRealAR = new WeakReference<>(iTrack2D);
            ITrack2DStateChangedListener iTrack2DStateChangedListener = this.mStateListener;
            if (iTrack2DStateChangedListener != null) {
                iTrack2D.setStateChangedListener(iTrack2DStateChangedListener);
            }
        }
    }

    @Override // com.baidu.p120ar.AbstractARProxy
    public void release() {
        WeakReference<ITrack2D> weakReference = this.mRealAR;
        if (weakReference != null) {
            weakReference.clear();
            this.mRealAR = null;
        }
        this.mStateListener = null;
    }

    @Override // com.baidu.p120ar.track2d.ITrack2D
    public void setStateChangedListener(ITrack2DStateChangedListener iTrack2DStateChangedListener) {
        WeakReference<ITrack2D> weakReference;
        this.mStateListener = iTrack2DStateChangedListener;
        if (this.mStateListener == null || (weakReference = this.mRealAR) == null || weakReference.get() == null) {
            return;
        }
        this.mRealAR.get().setStateChangedListener(this.mStateListener);
    }

    @Override // com.baidu.p120ar.track2d.ITrack2D
    public void haltTrack() {
        WeakReference<ITrack2D> weakReference = this.mRealAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mRealAR.get().haltTrack();
    }

    @Override // com.baidu.p120ar.track2d.ITrack2D
    public void resumeTrack() {
        WeakReference<ITrack2D> weakReference = this.mRealAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mRealAR.get().resumeTrack();
    }

    @Override // com.baidu.p120ar.track2d.ITrack2D
    public Bitmap getTargetBitmap() {
        WeakReference<ITrack2D> weakReference = this.mRealAR;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.mRealAR.get().getTargetBitmap();
    }
}
