package com.bytedance.applog;

import com.bytedance.applog.profile.UserProfileCallback;

/* renamed from: com.bytedance.applog.g1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC3588g1 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ int f8455a;

    /* renamed from: b */
    public final /* synthetic */ RunnableC3602i1 f8456b;

    public RunnableC3588g1(RunnableC3602i1 runnableC3602i1, int i) {
        this.f8456b = runnableC3602i1;
        this.f8455a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        UserProfileCallback userProfileCallback = this.f8456b.f8505d;
        if (userProfileCallback != null) {
            userProfileCallback.onFail(this.f8455a);
        }
    }
}
