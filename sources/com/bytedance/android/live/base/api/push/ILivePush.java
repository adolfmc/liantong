package com.bytedance.android.live.base.api.push;

import android.app.Activity;
import android.content.Context;
import com.bytedance.android.live.base.api.push.model.PushData;
import com.bytedance.android.live.base.api.push.model.PushUIConfig;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ILivePush {
    public static final String TAG = "livePush";

    void addPushInterceptor(PushInterceptor pushInterceptor);

    void clickPush(PushData pushData, Context context);

    void dismiss();

    void enterRoom(Context context, PushData pushData);

    boolean isShowing();

    void removePushInterceptor(PushInterceptor pushInterceptor);

    void reportPushClick(PushData pushData, ClickType clickType);

    void reportShow(PushData pushData);

    void requestPush(PushCallback pushCallback);

    void requestPushAndShow(boolean z, Activity activity, PushType pushType, PushCallback pushCallback, PushUIConfig pushUIConfig);

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum PushType {
        SELF_DRIVING(1),
        CLOD_LAUNCH(2),
        VIDEO(3);
        
        private final int value;

        public final int getValue() {
            return this.value;
        }

        PushType(int i) {
            this.value = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum ClickType {
        LIVE("live"),
        CLOSE("close");
        
        private final String value;

        public final String getValue() {
            return this.value;
        }

        ClickType(String str) {
            this.value = str;
        }
    }
}
