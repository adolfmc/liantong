package com.mob;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.mob.commons.C5868q;
import com.mob.commons.C5869r;
import com.mob.commons.C5871t;
import com.mob.commons.C5873u;
import com.mob.commons.C5879w;
import com.mob.commons.CSCenter;
import com.mob.commons.InternationalDomain;
import com.mob.commons.MobProduct;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.UIHandler;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MobSDK implements PublicMemberKeeper {
    public static final int CHANNEL_APICLOUD = 5;
    public static final int CHANNEL_COCOS = 1;
    public static final int CHANNEL_FLUTTER = 4;
    public static final int CHANNEL_JS = 3;
    public static final int CHANNEL_NATIVE = 0;
    public static final int CHANNEL_QUICKSDK = 6;
    public static final int CHANNEL_REACT_NATIVE = 8;
    public static final int CHANNEL_UNIAPP = 7;
    public static final int CHANNEL_UNITY = 2;
    public static final int SDK_VERSION_CODE;
    public static final String SDK_VERSION_NAME;

    /* renamed from: a */
    private static volatile Context f13981a;

    public static boolean getDefaultPrivacy() {
        return true;
    }

    static {
        int i;
        String str = "1.0.0";
        try {
            str = "2024-03-06".replace("-", ".");
            i = Integer.parseInt("2024-03-06".replace("-", ""));
        } catch (Throwable unused) {
            i = 1;
        }
        SDK_VERSION_CODE = i;
        SDK_VERSION_NAME = str;
    }

    public static synchronized void init(Context context) {
        synchronized (MobSDK.class) {
            init(context, null, null);
        }
    }

    public static synchronized void init(Context context, String str) {
        synchronized (MobSDK.class) {
            init(context, str, null);
        }
    }

    public static synchronized void init(Context context, String str, String str2) {
        synchronized (MobSDK.class) {
            if (context == null) {
                Log.e("MobSDK", "MobSDK init error, context is null");
                return;
            }
            if (f13981a == null) {
                f13981a = context.getApplicationContext();
                C5868q.f14468a = str;
                C5868q.f14469b = str2;
                C5871t.m12193a(false);
            } else if (!TextUtils.isEmpty(str) && !str.equals(C5868q.f14468a)) {
                C5868q.f14468a = str;
                C5868q.f14469b = str2;
                C5871t.m12193a(true);
            }
        }
    }

    public static InternationalDomain getDomain() {
        return C5868q.f14472e == null ? InternationalDomain.DEFAULT : C5868q.f14472e;
    }

    public static boolean checkForceHttps() {
        return C5868q.f14473f;
    }

    public static boolean checkV6() {
        return C5868q.f14474g;
    }

    public static String getAppkey() {
        if (C5879w.m12152h()) {
            return C5871t.m12196a();
        }
        return null;
    }

    public static String getAppSecret() {
        if (TextUtils.isEmpty(C5868q.f14469b)) {
            return C5868q.f14471d;
        }
        return C5868q.f14469b;
    }

    public static Context getContextSafely() {
        return f13981a;
    }

    public static Context getContext() {
        if (f13981a == null) {
            try {
                Context m12188a = C5873u.m12188a();
                if (m12188a != null) {
                    init(m12188a);
                }
            } catch (Throwable unused) {
            }
        }
        return f13981a;
    }

    public static final boolean isGppVer() {
        return C5868q.f14476i;
    }

    public static final boolean isMob() {
        return C5871t.m12191c();
    }

    public static final boolean isForb() {
        return C5871t.m12190d();
    }

    public static final int isAuth() {
        return C5871t.m12192b();
    }

    public static void setChannel(MobProduct mobProduct, int i) {
        C5869r.m12202a().m12201a(mobProduct, i);
    }

    public static void submitPolicyGrantResult(boolean z) {
        submitPolicyGrantResult((MobCustomController) null, z);
    }

    public static void submitPolicyGrantResult(MobCustomController mobCustomController, boolean z) {
        C5879w.m12161b(z);
        updateMobCustomController(mobCustomController);
    }

    @Deprecated
    public static void submitPolicyGrantResult(boolean z, final OperationCallback<Void> operationCallback) {
        C5879w.m12161b(z);
        if (operationCallback != null) {
            UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.MobSDK.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    OperationCallback operationCallback2 = OperationCallback.this;
                    if (operationCallback2 != null) {
                        operationCallback2.onComplete(null);
                        return false;
                    }
                    return false;
                }
            });
        }
    }

    public static int getPrivacyGrantedStatus() {
        return C5879w.m12158c();
    }

    public static String syncGetBSDM(String str, String str2, String str3, boolean z) {
        return C5871t.m12194a(str, str2, str3, z);
    }

    @Deprecated
    public static String checkRequestUrl(String str) {
        return C5873u.m12181a(str);
    }

    @Deprecated
    public static String dynamicModifyUrl(String str) {
        return C5873u.m12181a(str);
    }

    @Deprecated
    public static void canIContinueBusiness(final MobProduct mobProduct, Object obj, final Object obj2) {
        if (obj2 == null) {
            throw new IllegalArgumentException("callback can not be null");
        }
        new Thread(new Runnable() { // from class: com.mob.MobSDK.2
            @Override // java.lang.Runnable
            public void run() {
                if (MobProduct.this == null) {
                    ReflectHelper.invokeInstanceMethodNoThrow(obj2, "onFailure", null, new Throwable("MobProduct can not be null"));
                } else {
                    ReflectHelper.invokeInstanceMethodNoThrow(obj2, "onComplete", null, true);
                }
            }
        }).start();
    }

    public static void updateMobCustomController(MobCustomController mobCustomController) {
        CSCenter.getInstance().updateCustomController(mobCustomController);
    }
}
