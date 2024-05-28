package com.tencent.p348mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.sdk.modelbase.BaseReq;
import com.tencent.p348mm.sdk.p354b.C10393b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizTempSession */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class JumpToBizTempSession {
    public static final int SHOW_CHAT = 1;
    public static final int SHOW_MENU = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizTempSession$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Req extends BaseReq {
        private static final int MAX_SESSION_FROM_LENGTH = 1024;
        private static final String TAG = "MicroMsg.SDK.JumpToBizTempSession.Req";
        public String sessionFrom;
        public int showType;
        public String toUserName;

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public boolean checkArgs() {
            String str;
            String str2;
            String str3 = this.toUserName;
            if (str3 == null || str3.length() <= 0) {
                str = "MicroMsg.SDK.JumpToBizTempSession.Req";
                str2 = "checkArgs fail, toUserName is invalid";
            } else {
                String str4 = this.sessionFrom;
                if (str4 != null && str4.length() <= 1024) {
                    return true;
                }
                str = "MicroMsg.SDK.JumpToBizTempSession.Req";
                str2 = "checkArgs fail, sessionFrom is invalid";
            }
            C10393b.m6195a(str, str2);
            return false;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public int getType() {
            return 10;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_jump_to_biz_webview_req_to_user_name", this.toUserName);
            bundle.putString("_wxapi_jump_to_biz_webview_req_session_from", this.sessionFrom);
            bundle.putInt("_wxapi_jump_to_biz_webview_req_show_type", this.showType);
        }
    }
}
