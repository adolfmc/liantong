package com.p319ss.android.socialbase.downloader.impls;

import android.text.TextUtils;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadIdGenerator;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;

/* renamed from: com.ss.android.socialbase.downloader.impls.DefaultIdGenerator */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultIdGenerator implements IDownloadIdGenerator {
    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadIdGenerator
    public int generate(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        String md5Hex = DownloadUtils.md5Hex(String.format("%s_%s", str, str2));
        if (TextUtils.isEmpty(md5Hex)) {
            return 0;
        }
        return md5Hex.hashCode();
    }
}
