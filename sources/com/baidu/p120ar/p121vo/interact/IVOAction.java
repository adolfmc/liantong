package com.baidu.p120ar.p121vo.interact;

import com.baidu.p120ar.p121vo.caseconfig.VOConfig;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.interact.IVOAction */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IVOAction {
    void enableOffScreenGuide();

    String getVOId(VOConfig vOConfig);

    int[] getXYByNormalizedCoordinate(float f, float f2);

    void handleGestureInteraction(GestureInteractionInfo gestureInteractionInfo);

    void hideGuide();

    void insertModel(String str, int i, int i2, float[] fArr, float f);

    void release();

    void showGuideDown();

    void showGuideUp();

    void updateRT(float[] fArr);
}
