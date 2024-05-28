package com.tencent.p348mm.opensdk.modelbiz;

import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.p348mm.opensdk.modelbase.BaseReq;
import com.tencent.p348mm.opensdk.modelbase.BaseResp;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelbiz.WXLaunchWxaRedirectingPage */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXLaunchWxaRedirectingPage {
    public static final String URI_PATH = "launchWxaOpenApiRedirectingPage";

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXLaunchWxaRedirectingPage$ConstantsWxaRedirectingPage */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    interface ConstantsWxaRedirectingPage {
        public static final String KEY_CALLBACK_ACTIVITY = "_launch_wx_wxa_redirecting_page_callback_activity";
        public static final String KEY_CALLBACK_MSG = "_launch_wx_wxa_redirecting_page_callback_msg";
        public static final String KEY_INVOKE_TICKET = "_launch_wx_wxa_redirecting_page_invoke_ticket";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXLaunchWxaRedirectingPage$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Req extends BaseReq {
        private static final String TAG = "MicroMsg.SDK.WXLaunchWxaRedirectingPage.Req";
        public String callbackActivity;
        public String invokeTicket;

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public boolean checkArgs() {
            return !TextUtils.isEmpty(this.invokeTicket);
        }

        public void fromArray(String[] strArr) {
            this.invokeTicket = strArr[0];
            this.callbackActivity = strArr[1];
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.invokeTicket = bundle.getString("_launch_wx_wxa_redirecting_page_invoke_ticket");
            this.callbackActivity = bundle.getString("_launch_wx_wxa_redirecting_page_callback_activity");
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public int getType() {
            return 30;
        }

        public String[] toArray() {
            return new String[]{this.invokeTicket, this.callbackActivity};
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_launch_wx_wxa_redirecting_page_invoke_ticket", this.invokeTicket);
            bundle.putString("_launch_wx_wxa_redirecting_page_callback_activity", this.callbackActivity);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXLaunchWxaRedirectingPage$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Resp extends BaseResp {
        private static final String TAG = "MicroMsg.SDK.WXLaunchWxaFRedirectingPage.Resp";
        public String callbackActivity;
        public String invokeTicket;

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public boolean checkArgs() {
            return true;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.invokeTicket = bundle.getString("_launch_wx_wxa_redirecting_page_invoke_ticket");
            this.callbackActivity = bundle.getString("_launch_wx_wxa_redirecting_page_callback_activity");
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public int getType() {
            return 30;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_launch_wx_wxa_redirecting_page_invoke_ticket", this.invokeTicket);
            bundle.putString("_launch_wx_wxa_redirecting_page_callback_activity", this.callbackActivity);
        }
    }
}
