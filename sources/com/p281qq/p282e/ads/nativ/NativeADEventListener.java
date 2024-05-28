package com.p281qq.p282e.ads.nativ;

import com.p281qq.p282e.comm.util.AdError;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.nativ.NativeADEventListener */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface NativeADEventListener {
    void onADClicked();

    void onADError(AdError adError);

    void onADExposed();

    void onADStatusChanged();
}
