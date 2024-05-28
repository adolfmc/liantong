package com.huawei.hms.framework.network.grs;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IQueryUrlsCallBack {
    void onCallBackFail(int i);

    void onCallBackSuccess(Map<String, String> map);
}
