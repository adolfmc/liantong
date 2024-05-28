package com.baidu.p120ar.p121vo.detector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.detector.VOAlgoSetting */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VOAlgoSetting {
    public int frameHeight;
    public int frameWidth;
    public ImuProvider imuProvider;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.vo.detector.VOAlgoSetting$ImuProvider */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface ImuProvider {
        float getAngle();

        float[] getImuMatrix();
    }
}
