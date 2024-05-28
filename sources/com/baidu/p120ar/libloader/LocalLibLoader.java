package com.baidu.p120ar.libloader;

import android.content.Context;
import com.baidu.p120ar.ARType;
import com.baidu.p120ar.libloader.ILibLoader;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.libloader.LocalLibLoader */
/* loaded from: E:\10201592_dexfile_execute.dex */
class LocalLibLoader implements ILibLoader {
    private boolean mIsLoaded = false;
    private ReadyEventDispatcher mReadyDispatcher = new ReadyEventDispatcher();

    @Override // com.baidu.p120ar.libloader.ILibLoader
    public void setLibLoadPlugin(ILibLoaderPlugin iLibLoaderPlugin) {
    }

    @Override // com.baidu.p120ar.libloader.ILibLoader
    public void require(String str) {
        System.loadLibrary(str);
    }

    @Override // com.baidu.p120ar.libloader.ILibLoader
    public void load(Context context, ILibLoader.ILoadListener iLoadListener) {
        this.mIsLoaded = true;
        this.mReadyDispatcher.dispatchAll();
        this.mReadyDispatcher.clearAll();
        if (iLoadListener != null) {
            iLoadListener.onSuccess();
        }
    }

    @Override // com.baidu.p120ar.libloader.ILibLoader
    public void setLibReadyListener(String str, ILibLoader.ReadyListener readyListener) {
        if (readyListener != null) {
            if (this.mIsLoaded) {
                readyListener.onReady();
            } else {
                this.mReadyDispatcher.addListener(str, readyListener);
            }
        }
    }

    @Override // com.baidu.p120ar.libloader.ILibLoader
    public void prepareCaseRes(ARType aRType, String str, String str2, ILibLoader.CaseReadyListener caseReadyListener) {
        if (caseReadyListener != null) {
            caseReadyListener.onReady(aRType, str, str2);
        }
    }

    @Override // com.baidu.p120ar.libloader.ILibLoader
    public void release() {
        ReadyEventDispatcher readyEventDispatcher = this.mReadyDispatcher;
        if (readyEventDispatcher != null) {
            readyEventDispatcher.clearAll();
        }
    }
}
