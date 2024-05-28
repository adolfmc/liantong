package com.baidu.p120ar.auth;

import android.content.Context;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.IAuthGuard */
/* loaded from: E:\10201592_dexfile_execute.dex */
interface IAuthGuard {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.auth.IAuthGuard$FeatureCodeUpdateListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface FeatureCodeUpdateListener {
        void onUpdate(Set<Integer> set);
    }

    void doAuth(Context context, IAuthCallback iAuthCallback);

    void setValidFeatureCodeUpdateListener(FeatureCodeUpdateListener featureCodeUpdateListener);
}
