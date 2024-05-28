package com.baidu.p120ar;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.DuMixCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface DuMixCallback {
    void onCaseCreate(boolean z, String str, String str2);

    void onCaseDestroy();

    void onError(DuMixErrorType duMixErrorType, String str, String str2);

    void onRelease();

    void onSetup(boolean z, DuMixInput duMixInput, DuMixOutput duMixOutput);
}
