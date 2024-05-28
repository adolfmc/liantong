package com.bytedance.android.live.base.api;

import com.bytedance.android.live.base.IService;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IHostPermission extends IService {
    boolean alist();

    String getDevImei();

    String getDevOaid();

    String getMacAddress();

    LocationProvider getTTLocation();

    boolean isCanUseLocation();

    boolean isCanUsePhoneState();

    boolean isCanUseWifiState();

    boolean isCanUseWriteExternal();
}
