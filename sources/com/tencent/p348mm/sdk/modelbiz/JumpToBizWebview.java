package com.tencent.p348mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.sdk.modelbase.BaseReq;
import com.tencent.p348mm.sdk.p354b.C10393b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizWebview */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class JumpToBizWebview {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizWebview$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Req extends BaseReq {
        private static final int EXT_MSG_LENGTH = 1024;
        private static final String TAG = "MicroMsg.SDK.JumpToBizWebview.Req";
        public String extMsg;
        public int scene = 1;
        public String toUserName;
        public int webType;

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public boolean checkArgs() {
            String str;
            String str2;
            String str3 = this.toUserName;
            if (str3 == null || str3.length() <= 0) {
                str = "MicroMsg.SDK.JumpToBizWebview.Req";
                str2 = "checkArgs fail, toUserName is invalid";
            } else {
                String str4 = this.extMsg;
                if (str4 == null || str4.length() <= 1024) {
                    return true;
                }
                str = "MicroMsg.SDK.JumpToBizWebview.Req";
                str2 = "ext msg is not null, while the length exceed 1024 bytes";
            }
            C10393b.m6195a(str, str2);
            return false;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public int getType() {
            return 8;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_jump_to_biz_webview_req_to_user_name", this.toUserName);
            bundle.putString("_wxapi_jump_to_biz_webview_req_ext_msg", this.extMsg);
            bundle.putInt("_wxapi_jump_to_biz_webview_req_web_type", this.webType);
            bundle.putInt("_wxapi_jump_to_biz_webview_req_scene", this.scene);
        }
    }
}
