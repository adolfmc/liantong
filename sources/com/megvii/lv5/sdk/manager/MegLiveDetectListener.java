package com.megvii.lv5.sdk.manager;

import com.megvii.lv5.sdk.bean.MegliveLocalFileInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface MegLiveDetectListener {
    void onDetectFinish(int i, String str, String str2);

    void onLivenessFileCallback(String str);

    void onLivenessLocalFileCallBack(MegliveLocalFileInfo megliveLocalFileInfo);

    void onPreDetectFinish(int i, String str);
}
