package com.bytedance.sdk.openadsdk;

import android.app.Activity;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface TTInteractionAd extends TTClientBidding {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface AdInteractionListener {
        void onAdClicked();

        void onAdDismiss();

        void onAdShow();
    }

    int getInteractionType();

    Map<String, Object> getMediaExtraInfo();

    void setAdInteractionListener(AdInteractionListener adInteractionListener);

    void setDownloadListener(TTAppDownloadListener tTAppDownloadListener);

    void setShowDislikeIcon(TTAdDislike.DislikeInteractionCallback dislikeInteractionCallback);

    void showInteractionAd(Activity activity);
}
