package com.p319ss.android.socialbase.downloader.impls;

import com.p319ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator;
import com.p319ss.android.socialbase.downloader.network.NetworkQuality;

/* renamed from: com.ss.android.socialbase.downloader.impls.DefaultChunkAdjustCalculator */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultChunkAdjustCalculator implements IChunkAdjustCalculator {
    @Override // com.p319ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator
    public int calculateChunkCount(int i, NetworkQuality networkQuality) {
        if (networkQuality.ordinal() <= NetworkQuality.MODERATE.ordinal()) {
            return 1;
        }
        return networkQuality == NetworkQuality.GOOD ? i - 1 : i;
    }
}
