package com.gmrz.appsdk.commlib.api;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IGmrzAdapter {
    String getAAID();

    void setFaceListener(Object obj, Context context);

    void setFaceListener(Object obj, Context context, String str);

    void setVoiceListener(Object obj, Context context, String str);
}
