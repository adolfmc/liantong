package com.baidu.p120ar.p121vo.interact;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.interact.DefaultPlaceStrategy */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DefaultPlaceStrategy implements IPlaceStrategy {
    protected static final float[] slamModelRMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    protected IVOAction mAction;
    protected int mPreviewHeight;
    protected int mPreviewWidth;

    public DefaultPlaceStrategy(IVOAction iVOAction, int i, int i2) {
        this.mAction = iVOAction;
        this.mPreviewWidth = i;
        this.mPreviewHeight = i2;
    }

    @Override // com.baidu.p120ar.p121vo.interact.IPlaceStrategy
    public boolean placeModel(float[] fArr) {
        IVOAction iVOAction = this.mAction;
        iVOAction.insertModel(iVOAction.getVOId(null), this.mPreviewWidth / 2, this.mPreviewHeight / 2, slamModelRMatrix, 1000.0f);
        return true;
    }
}
