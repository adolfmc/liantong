package com.p281qq.p282e.comm.p283pi;

import com.p281qq.p282e.ads.nativ.ADSize;
import com.p281qq.p282e.comm.adevent.ADListener;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.NEADVI */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface NEADVI extends LADI {
    void destroy();

    void preloadVideo();

    void render();

    void reportAdNegative();

    void setAdListener(ADListener aDListener);

    void setAdSize(ADSize aDSize);
}
