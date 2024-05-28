package com.xiaomi.push;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

/* renamed from: com.xiaomi.push.de */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11275de implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private Context f21858a;

    /* renamed from: a */
    private String f21859a;

    /* renamed from: b */
    private String f21860b;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    public C11275de(Context context, String str) {
        this.f21859a = "";
        this.f21858a = context;
        this.f21859a = str;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (TextUtils.isEmpty(this.f21860b)) {
            this.f21860b = activity.getLocalClassName();
        }
        this.f21859a = String.valueOf(System.currentTimeMillis() / 1000);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        String localClassName = activity.getLocalClassName();
        if (TextUtils.isEmpty(this.f21859a) || TextUtils.isEmpty(localClassName)) {
            return;
        }
        this.f21860b = "";
        if (!TextUtils.isEmpty(this.f21860b) && !TextUtils.equals(this.f21860b, localClassName)) {
            this.f21859a = "";
            return;
        }
        m4366a(this.f21858a.getPackageName() + "|" + localClassName + ":" + this.f21859a + "," + String.valueOf(System.currentTimeMillis() / 1000));
        this.f21859a = "";
        this.f21860b = "";
    }

    /* renamed from: a */
    private void m4366a(String str) {
        C11412gn c11412gn = new C11412gn();
        c11412gn.m3626a(str);
        c11412gn.m3630a(System.currentTimeMillis());
        c11412gn.m3629a(EnumC11406gh.ActivityActiveTimeStamp);
        AbstractC11284dl.m4346a(this.f21858a, c11412gn);
    }
}
