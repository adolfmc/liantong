package com.baidu.p120ar.content;

import com.baidu.p120ar.bean.ARCaseBundleInfo;
import com.baidu.p120ar.bean.FunctionType;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.content.IARCaseInfo */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IARCaseInfo {
    String getARKey();

    int getARType();

    String getAcId();

    String getArCasePath();

    ARCaseBundleInfo getCaseBundleInfo();

    Map<FunctionType, Boolean> getFeatures();

    String[] getMultiResourceUrl();

    String getRedirectUrl();

    String getResourceUrl();

    String getThumbnailUrl();

    String getVersionCode();

    String getZipMd5();

    boolean isHardwareSatisfied();

    boolean isRefused();

    boolean isShowAudioDialog();
}
