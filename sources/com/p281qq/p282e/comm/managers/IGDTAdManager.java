package com.p281qq.p282e.comm.managers;

import com.p281qq.p282e.ads.dfa.GDTAppDialogClickListener;
import com.p281qq.p282e.comm.managers.devtool.DevTools;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.managers.IGDTAdManager */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface IGDTAdManager {
    String getBuyerId(Map<String, Object> map);

    DevTools getDevTools();

    String getSDKInfo(String str);

    int showOpenOrInstallAppDialog(GDTAppDialogClickListener gDTAppDialogClickListener);
}
