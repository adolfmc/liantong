package com.baidu.p120ar.p121vo.interact;

import com.baidu.p120ar.p121vo.caseconfig.VOConfig;
import com.baidu.p120ar.p121vo.detector.IVOAlgoInteraction;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.interact.SpaceCoordPlaceStrategy */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SpaceCoordPlaceStrategy extends DefaultPlaceStrategy implements IPlaceStrategy {
    private IVOAlgoInteraction mAlgo;
    private VOConfig mConfig;

    public SpaceCoordPlaceStrategy(IVOAction iVOAction, VOConfig vOConfig, int i, int i2) {
        super(iVOAction, i, i2);
        this.mConfig = vOConfig;
    }

    public void setAlgoInteraction(IVOAlgoInteraction iVOAlgoInteraction) {
        this.mAlgo = iVOAlgoInteraction;
    }

    @Override // com.baidu.p120ar.p121vo.interact.DefaultPlaceStrategy, com.baidu.p120ar.p121vo.interact.IPlaceStrategy
    public boolean placeModel(float[] fArr) {
        float[] calcModelPosition = this.mAlgo.calcModelPosition(this.mConfig.getPitchAngle(), fArr);
        if (calcModelPosition != null) {
            String vOId = this.mAction.getVOId(this.mConfig);
            int i = (int) calcModelPosition[0];
            if (i == 0) {
                int promptUpDown = promptUpDown(fArr, this.mConfig.getPitchAngle());
                if (promptUpDown == 0) {
                    this.mAction.showGuideDown();
                } else if (promptUpDown == 1) {
                    this.mAction.showGuideUp();
                }
                this.mAction.updateRT(new float[16]);
            } else if (i == 1) {
                this.mAction.insertModel(vOId, (int) calcModelPosition[1], (int) calcModelPosition[2], slamModelRMatrix, this.mConfig.getDistance());
                this.mAction.hideGuide();
                return true;
            }
        }
        return false;
    }

    private int promptUpDown(float[] fArr, int i) {
        return 90.0f - ((float) (((Math.acos((double) (-fArr[10])) * 180.0d) * 1.0d) / 3.141592653589793d)) > ((float) i) ? 0 : 1;
    }
}
