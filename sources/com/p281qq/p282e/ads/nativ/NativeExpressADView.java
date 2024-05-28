package com.p281qq.p282e.ads.nativ;

import android.content.Context;
import android.widget.FrameLayout;
import com.p281qq.p282e.comm.compliance.DownloadConfirmListener;
import com.p281qq.p282e.comm.p283pi.AdData;
import com.p281qq.p282e.comm.p283pi.LADI;
import com.p281qq.p282e.comm.p283pi.NFBI;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.nativ.NativeExpressADView */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class NativeExpressADView extends FrameLayout implements DownloadConfirmListener, LADI, NFBI {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.ads.nativ.NativeExpressADView$ViewBindStatusListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ViewBindStatusListener {
        void onAttachedToWindow();

        void onDetachedFromWindow();

        void onFinishTemporaryDetach();

        void onStartTemporaryDetach();
    }

    public NativeExpressADView(Context context) {
        super(context);
    }

    public abstract void destroy();

    public abstract AdData getBoundData();

    public abstract void negativeFeedback();

    public abstract void preloadVideo();

    public abstract void render();

    @Deprecated
    public abstract void setAdSize(ADSize aDSize);

    public abstract void setMediaListener(NativeExpressMediaListener nativeExpressMediaListener);

    public abstract void setViewBindStatusListener(ViewBindStatusListener viewBindStatusListener);
}
