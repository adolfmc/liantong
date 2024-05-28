package com.baidu.p120ar;

import android.content.Context;
import android.view.ViewGroup;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.arrender.IGLRenderer;
import com.baidu.p120ar.auth.IDuMixAuthCallback;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.content.IContentPlatform;
import com.baidu.p120ar.libloader.ILibLoaderPlugin;
import com.baidu.p120ar.steploading.IStepLoading;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.IInternal */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IInternal {
    @Deprecated
    List<Integer> checkAuth(byte[] bArr, IDuMixAuthCallback iDuMixAuthCallback);

    @Deprecated
    List<Integer> checkAuth(byte[] bArr, ICallbackWith<List<Integer>> iCallbackWith, ICallbackWith<Integer> iCallbackWith2);

    IARRenderer getARRenderer();

    IContentPlatform getContentPlatform();

    IGLRenderer getGLRenderer();

    IStepLoading getStepLoading();

    void setAuthLicense(byte[] bArr, String str, String str2, String str3);

    void setGLWebViewUseable(Context context, ViewGroup viewGroup);

    void setLibLoadPlugin(ILibLoaderPlugin iLibLoaderPlugin);

    void setNativeWebViewUseable(Context context, ViewGroup viewGroup);
}
