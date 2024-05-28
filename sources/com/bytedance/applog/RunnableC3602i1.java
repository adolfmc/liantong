package com.bytedance.applog;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.bytedance.applog.profile.UserProfileCallback;
import java.util.HashMap;

/* renamed from: com.bytedance.applog.i1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC3602i1 implements Runnable {

    /* renamed from: f */
    public static final Handler f8501f = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    public String f8502a;

    /* renamed from: b */
    public String f8503b;

    /* renamed from: c */
    public String f8504c;

    /* renamed from: d */
    public final UserProfileCallback f8505d;

    /* renamed from: e */
    public Context f8506e;

    public RunnableC3602i1(String str, String str2, String str3, UserProfileCallback userProfileCallback, Context context) {
        this.f8502a = str;
        this.f8503b = str2;
        this.f8504c = str3;
        this.f8505d = userProfileCallback;
        this.f8506e = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (!C3730x2.m17030c(this.f8506e)) {
                f8501f.post(new RunnableC3588g1(this, 0));
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/json");
            hashMap.put("X-APIKEY", this.f8503b);
            AppLog.getNetClient().post(this.f8502a, this.f8504c.getBytes(), hashMap);
            f8501f.post(new RunnableC3595h1(this));
        } catch (Throwable th) {
            th.printStackTrace();
            f8501f.post(new RunnableC3588g1(this, 1));
        }
    }
}
