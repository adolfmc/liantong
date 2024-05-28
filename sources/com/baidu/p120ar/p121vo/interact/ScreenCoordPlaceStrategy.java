package com.baidu.p120ar.p121vo.interact;

import com.baidu.p120ar.p121vo.caseconfig.VOConfig;
import com.baidu.p120ar.utils.ARLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.interact.ScreenCoordPlaceStrategy */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ScreenCoordPlaceStrategy extends DefaultPlaceStrategy implements IPlaceStrategy {
    private VOConfig mConfig;

    public ScreenCoordPlaceStrategy(IVOAction iVOAction, VOConfig vOConfig, int i, int i2) {
        super(iVOAction, i, i2);
        this.mConfig = vOConfig;
    }

    @Override // com.baidu.p120ar.p121vo.interact.DefaultPlaceStrategy, com.baidu.p120ar.p121vo.interact.IPlaceStrategy
    public boolean placeModel(float[] fArr) {
        int i;
        int i2;
        String[] split = this.mConfig.getPosition().split(",");
        String vOId = this.mAction.getVOId(this.mConfig);
        int i3 = this.mPreviewWidth / 2;
        int i4 = this.mPreviewHeight / 2;
        try {
            int[] xYByNormalizedCoordinate = this.mAction.getXYByNormalizedCoordinate(Float.parseFloat(split[0].trim()), Float.parseFloat(split[1].trim()));
            i3 = xYByNormalizedCoordinate[0];
            i2 = xYByNormalizedCoordinate[1];
            i = i3;
        } catch (NumberFormatException unused) {
            ARLog.m20420e("model position is not number !!!");
            i = i3;
            i2 = i4;
        }
        this.mAction.insertModel(vOId, i, i2, slamModelRMatrix, this.mConfig.getDistance());
        return true;
    }
}
