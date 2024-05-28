package com.xiaomi.mipush.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11303ds;
import com.xiaomi.push.C11305dt;
import java.util.HashSet;
import java.util.Set;

@TargetApi(14)
/* renamed from: com.xiaomi.mipush.sdk.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11086a implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private Set<String> f21350a = new HashSet();

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
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

    /* renamed from: a */
    public static void m5158a(Context context) {
        m5159a((Application) context.getApplicationContext());
    }

    /* renamed from: a */
    private static void m5159a(Application application) {
        application.registerActivityLifecycleCallbacks(new C11086a());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        try {
            Intent intent = activity.getIntent();
            if (intent == null) {
                return;
            }
            String stringExtra = intent.getStringExtra("messageId");
            int intExtra = intent.getIntExtra("eventMessageType", -1);
            if (!TextUtils.isEmpty(stringExtra) && intExtra > 0 && !this.f21350a.contains(stringExtra)) {
                this.f21350a.add(stringExtra);
                if (intExtra == 3000) {
                    C11305dt.m4117a(activity.getApplicationContext()).m4111a(activity.getPackageName(), C11303ds.m4131a(intExtra), stringExtra, 3008, null);
                } else if (intExtra == 1000) {
                    C11305dt.m4117a(activity.getApplicationContext()).m4111a(activity.getPackageName(), C11303ds.m4131a(intExtra), stringExtra, 1008, null);
                }
            }
        } catch (Throwable th) {
            AbstractC11049b.m5268d("An error occurred in onActivityResumed method: " + th);
        }
    }
}
