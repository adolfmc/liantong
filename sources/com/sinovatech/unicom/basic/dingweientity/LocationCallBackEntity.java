package com.sinovatech.unicom.basic.dingweientity;

import android.app.Activity;
import com.sinovatech.unicom.basic.myinterface.MyLocationInterface;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LocationCallBackEntity {
    private Activity activity;
    private MyLocationInterface.CallBack callBack;
    private String name;

    public MyLocationInterface.CallBack getCallBack() {
        return this.callBack;
    }

    public void setCallBack(MyLocationInterface.CallBack callBack) {
        this.callBack = callBack;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
