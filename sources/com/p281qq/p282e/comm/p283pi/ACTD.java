package com.p281qq.p282e.comm.p283pi;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.ACTD */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface ACTD {
    public static final String APPID_KEY = "appid";
    public static final String DELEGATE_NAME_KEY = "gdt_activity_delegate_name";

    void onActivityResult(int i, int i2, Intent intent);

    void onAfterCreate(Bundle bundle);

    void onBackPressed();

    void onBeforeCreate(Bundle bundle);

    void onConfigurationChanged(Configuration configuration);

    void onDestroy();

    void onPause();

    void onResume();

    void onStop();
}
