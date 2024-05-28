package com.gmrz.appsdk.commlib.api;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class GmrzFaceAdapter implements IGmrzAdapter {
    public static String faceAAID;

    @Override // com.gmrz.appsdk.commlib.api.IGmrzAdapter
    public String getAAID() {
        return faceAAID;
    }

    public void setFaceAAID(String str) {
        faceAAID = str;
    }

    @Override // com.gmrz.appsdk.commlib.api.IGmrzAdapter
    public void setFaceListener(Object obj, Context context) {
    }

    @Override // com.gmrz.appsdk.commlib.api.IGmrzAdapter
    public void setFaceListener(Object obj, Context context, String str) {
    }

    @Override // com.gmrz.appsdk.commlib.api.IGmrzAdapter
    public void setVoiceListener(Object obj, Context context, String str) {
    }
}
