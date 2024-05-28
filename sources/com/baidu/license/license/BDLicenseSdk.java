package com.baidu.license.license;

import android.text.TextUtils;
import com.baidu.cloud.download.base.DownloadCallback;
import com.baidu.license.IBDLicenseSdk;
import com.baidu.license.LicenseManager;
import com.baidu.license.SDKHttpConfig;
import com.baidu.license.sticker.DownStickerHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BDLicenseSdk implements IBDLicenseSdk {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.license.license.BDLicenseSdk$oi */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2607oi {

        /* renamed from: a */
        public static BDLicenseSdk f4999a = new BDLicenseSdk();
    }

    public static BDLicenseSdk getInstance() {
        return C2607oi.f4999a;
    }

    @Override // com.baidu.license.IBDLicenseSdk
    public void downSticker(String str, String str2, String str3, DownloadCallback downloadCallback) {
        DownStickerHelper.downLoadSticker(str, str2, str3, downloadCallback);
    }

    @Override // com.baidu.license.IBDLicenseSdk
    public void getOneSticker(String str, DownStickerHelper.StickerDataListener stickerDataListener, int i) {
        new DownStickerHelper(LicenseManager.sContext, SDKHttpConfig.appId, i).loadOneStickerList(Long.parseLong(str), stickerDataListener);
    }

    @Override // com.baidu.license.IBDLicenseSdk
    public void getStickerList(DownStickerHelper.StickerDataListener stickerDataListener, int i) {
        new DownStickerHelper(LicenseManager.sContext, SDKHttpConfig.appId, i).loadStickerList(stickerDataListener);
    }

    public BDLicenseSdk() {
        if (TextUtils.isEmpty(SDKHttpConfig.appId) || TextUtils.isEmpty(SDKHttpConfig.packageName)) {
            throw new IllegalArgumentException("Please call this after LicenseManager.init()");
        }
    }
}
