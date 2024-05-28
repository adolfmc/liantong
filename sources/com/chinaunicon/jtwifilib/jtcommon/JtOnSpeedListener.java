package com.chinaunicon.jtwifilib.jtcommon;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface JtOnSpeedListener {
    void onAverageSpeed(String str);

    void onCurrentSpeed(String str);

    void onFiled(String str);

    void onFinish(int i, int i2, int i3);

    void onMaxSpeed(String str);

    void onMinSpeed(String str);

    void onSpeed(float f, float f2, float f3, float f4);
}
