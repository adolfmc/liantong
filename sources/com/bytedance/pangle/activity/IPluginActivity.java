package com.bytedance.pangle.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import com.bytedance.pangle.plugin.Plugin;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IPluginActivity {
    void _requestPermissions(String[] strArr, int i);

    void attachBaseContext(Context context);

    String getPluginPkgName();

    void onCreate(Bundle bundle);

    void setPluginProxyActivity(InterfaceC3773b interfaceC3773b, Plugin plugin);

    void setProxyTheme2Plugin(int i);
}
