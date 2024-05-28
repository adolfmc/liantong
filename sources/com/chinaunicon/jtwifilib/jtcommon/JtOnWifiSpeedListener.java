package com.chinaunicon.jtwifilib.jtcommon;

import com.chinaunicon.jtwifilib.jtcommon.model.JtSpeedParams;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface JtOnWifiSpeedListener {
    void filed(String str);

    void onWifiSpeed(float f, int i);

    void onWifiSpeed(float f, int i, String str);

    void onWifiSpeed(float f, int i, String str, String str2, String str3);

    void success(JtSpeedParams jtSpeedParams);
}
