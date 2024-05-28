package com.baidu.p120ar.libloader;

import android.content.Context;
import com.baidu.p120ar.ARType;
import com.baidu.p120ar.DuMixErrorType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.libloader.ILibLoader */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ILibLoader {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.libloader.ILibLoader$CaseReadyListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface CaseReadyListener {
        void onError(DuMixErrorType duMixErrorType, String str);

        void onReady(ARType aRType, String str, String str2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.libloader.ILibLoader$ILoadListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface ILoadListener {
        void onError(DuMixErrorType duMixErrorType, Exception exc);

        void onSuccess();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.libloader.ILibLoader$ReadyListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface ReadyListener {
        void onReady();
    }

    void load(Context context, ILoadListener iLoadListener);

    void prepareCaseRes(ARType aRType, String str, String str2, CaseReadyListener caseReadyListener);

    void release();

    void require(String str);

    void setLibLoadPlugin(ILibLoaderPlugin iLibLoaderPlugin);

    void setLibReadyListener(String str, ReadyListener readyListener);
}
