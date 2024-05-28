package com.baidu.cloud.plugin;

import com.baidu.cloud.util.INotProguard;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ISoCallback extends INotProguard {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class AbsSoCallback implements ISoCallback {
        @Override // com.baidu.cloud.plugin.ISoCallback
        public void onDownloadProgress(float f) {
        }

        @Override // com.baidu.cloud.plugin.ISoCallback
        public void onDownloadStart(String str) {
        }
    }

    void onDownloadFail(String str, int i, String str2);

    void onDownloadProgress(float f);

    void onDownloadStart(String str);

    void onDownloadSuccess(String str, String str2);
}
