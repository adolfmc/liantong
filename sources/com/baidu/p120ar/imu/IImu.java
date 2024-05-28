package com.baidu.p120ar.imu;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.imu.IImu */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IImu {
    void destroy();

    void setContext(Context context);

    boolean start(ImuParams imuParams, ImuListener imuListener);

    void stop(ImuListener imuListener);
}
