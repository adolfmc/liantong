package com.baidu.p120ar.face;

import android.text.TextUtils;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.AbstractARProxy;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.FaceARProxy */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceARProxy extends AbstractARProxy implements IFace {
    private FaceListener mFaceListener;
    private String mFaceModelPath;
    private WeakReference<IFace> mWeakRefFaceAR;

    @Override // com.baidu.p120ar.AbstractARProxy
    public void setARCase(AbstractAR abstractAR) {
        if (abstractAR == null) {
            WeakReference<IFace> weakReference = this.mWeakRefFaceAR;
            if (weakReference != null) {
                weakReference.clear();
                this.mWeakRefFaceAR = null;
                return;
            }
            return;
        }
        if (abstractAR instanceof IFace) {
            this.mWeakRefFaceAR = new WeakReference<>((IFace) abstractAR);
            if (!TextUtils.isEmpty(this.mFaceModelPath)) {
                this.mWeakRefFaceAR.get().setFaceModelPath(this.mFaceModelPath);
            }
        }
        if (this.mFaceListener != null) {
            this.mWeakRefFaceAR.get().setFaceListener(this.mFaceListener);
        }
    }

    @Override // com.baidu.p120ar.face.IFace
    public void setFaceModelPath(String str) {
        this.mFaceModelPath = str;
        WeakReference<IFace> weakReference = this.mWeakRefFaceAR;
        if (weakReference != null) {
            weakReference.get().setFaceModelPath(str);
        }
    }

    @Override // com.baidu.p120ar.face.IFace
    public void setFaceListener(FaceListener faceListener) {
        this.mFaceListener = faceListener;
        WeakReference<IFace> weakReference = this.mWeakRefFaceAR;
        if (weakReference != null) {
            weakReference.get().setFaceListener(this.mFaceListener);
        }
    }

    @Override // com.baidu.p120ar.AbstractARProxy
    public void release() {
        WeakReference<IFace> weakReference = this.mWeakRefFaceAR;
        if (weakReference != null) {
            weakReference.clear();
            this.mWeakRefFaceAR = null;
        }
        this.mFaceListener = null;
    }
}
