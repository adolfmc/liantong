package com.baidu.p120ar.auth;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.IDuMixAuthCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IDuMixAuthCallback {
    void onAvailFeaturesUpdate(List<Integer> list);

    void onFeatureRejected(int i);

    void onResult(boolean z);
}
