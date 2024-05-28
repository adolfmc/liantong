package com.baidu.p120ar.libloader;

import android.content.Context;
import com.baidu.p120ar.ARType;
import com.baidu.p120ar.libloader.ILibLoader;
import com.baidu.p120ar.utils.ARLog;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.libloader.LocalWithPathLibLoader */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LocalWithPathLibLoader extends LocalLibLoader {
    private static final String TAG = "LocalWithPathLibLoader";
    private IErrorListener mErrorListener;
    private String mLibraryPath;
    private List<String> mLoadedLibraries = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.libloader.LocalWithPathLibLoader$IErrorListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface IErrorListener {
        void onError(String str, String str2);
    }

    @Override // com.baidu.p120ar.libloader.LocalLibLoader, com.baidu.p120ar.libloader.ILibLoader
    public /* bridge */ /* synthetic */ void load(Context context, ILibLoader.ILoadListener iLoadListener) {
        super.load(context, iLoadListener);
    }

    @Override // com.baidu.p120ar.libloader.LocalLibLoader, com.baidu.p120ar.libloader.ILibLoader
    public /* bridge */ /* synthetic */ void prepareCaseRes(ARType aRType, String str, String str2, ILibLoader.CaseReadyListener caseReadyListener) {
        super.prepareCaseRes(aRType, str, str2, caseReadyListener);
    }

    @Override // com.baidu.p120ar.libloader.LocalLibLoader, com.baidu.p120ar.libloader.ILibLoader
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    @Override // com.baidu.p120ar.libloader.LocalLibLoader, com.baidu.p120ar.libloader.ILibLoader
    public /* bridge */ /* synthetic */ void setLibLoadPlugin(ILibLoaderPlugin iLibLoaderPlugin) {
        super.setLibLoadPlugin(iLibLoaderPlugin);
    }

    public LocalWithPathLibLoader(String str) {
        this.mLibraryPath = str;
    }

    public void setErrorListener(IErrorListener iErrorListener) {
        this.mErrorListener = iErrorListener;
    }

    @Override // com.baidu.p120ar.libloader.LocalLibLoader, com.baidu.p120ar.libloader.ILibLoader
    public void require(String str) {
        boolean z;
        ARLog.m20417i("LocalWithPathLibLoader", "require libName = " + str);
        try {
            super.require(str);
            if (this.mLoadedLibraries.contains(str)) {
                return;
            }
            this.mLoadedLibraries.add(str);
        } finally {
            if (z) {
            }
        }
    }

    private void loadLibraryFile(String str) {
        try {
            System.load(str);
        } catch (Throwable th) {
            IErrorListener iErrorListener = this.mErrorListener;
            if (iErrorListener != null) {
                iErrorListener.onError(str, th.getMessage());
            }
            throw th;
        }
    }

    @Override // com.baidu.p120ar.libloader.LocalLibLoader, com.baidu.p120ar.libloader.ILibLoader
    public void setLibReadyListener(String str, ILibLoader.ReadyListener readyListener) {
        if (readyListener != null && this.mLoadedLibraries.contains(str)) {
            readyListener.onReady();
        } else {
            super.setLibReadyListener(str, readyListener);
        }
    }
}
