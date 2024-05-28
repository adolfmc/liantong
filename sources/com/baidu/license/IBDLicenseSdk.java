package com.baidu.license;

import com.baidu.cloud.download.base.DownloadCallback;
import com.baidu.license.sticker.DownStickerHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IBDLicenseSdk extends INotProguard {
    void downSticker(String str, String str2, String str3, DownloadCallback downloadCallback);

    void getOneSticker(String str, DownStickerHelper.StickerDataListener stickerDataListener, int i);

    void getStickerList(DownStickerHelper.StickerDataListener stickerDataListener, int i);
}
