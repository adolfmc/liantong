package com.baidu.p120ar.content;

import com.baidu.p120ar.callback.ICancellable;
import com.baidu.p120ar.ihttp.IProgressCallback;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.content.IContentPlatform */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IContentPlatform {
    ICancellable checkCaseUpdate(String str, CaseUpdateListener caseUpdateListener);

    ICancellable downloadCase(String str, IRequestCallback<IARCaseInfo> iRequestCallback, IProgressCallback iProgressCallback);

    ICancellable downloadCase(String str, String str2, IRequestCallback<IARCaseInfo> iRequestCallback, IProgressCallback iProgressCallback);

    ICancellable queryRecommendCases(String str, IRequestCallback<List<IARCaseInfo>> iRequestCallback);
}
