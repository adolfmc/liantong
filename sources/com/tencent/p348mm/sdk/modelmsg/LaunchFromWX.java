package com.tencent.p348mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.sdk.modelbase.BaseReq;
import com.tencent.p348mm.sdk.modelbase.BaseResp;
import com.tencent.p348mm.sdk.p354b.C10393b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelmsg.LaunchFromWX */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class LaunchFromWX {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelmsg.LaunchFromWX$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Req extends BaseReq {
        private static final int MESSAGE_ACTION_LENGTH_LIMIT = 2048;
        private static final int MESSAGE_EXT_LENGTH_LIMIT = 2048;
        private static final String TAG = "MicroMsg.SDK.LaunchFromWX.Req";
        public String country;
        public String lang;
        public String messageAction;
        public String messageExt;

        public Req() {
        }

        public Req(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public boolean checkArgs() {
            String str;
            String str2;
            String str3 = this.messageAction;
            if (str3 == null || str3.length() <= 2048) {
                String str4 = this.messageExt;
                if (str4 == null || str4.length() <= 2048) {
                    return true;
                }
                str = "MicroMsg.SDK.LaunchFromWX.Req";
                str2 = "checkArgs fail, messageExt is too long";
            } else {
                str = "MicroMsg.SDK.LaunchFromWX.Req";
                str2 = "checkArgs fail, messageAction is too long";
            }
            C10393b.m6195a(str, str2);
            return false;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.messageAction = bundle.getString("_wxobject_message_action");
            this.messageExt = bundle.getString("_wxobject_message_ext");
            this.lang = bundle.getString("_wxapi_launch_req_lang");
            this.country = bundle.getString("_wxapi_launch_req_country");
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public int getType() {
            return 6;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxobject_message_action", this.messageAction);
            bundle.putString("_wxobject_message_ext", this.messageExt);
            bundle.putString("_wxapi_launch_req_lang", this.lang);
            bundle.putString("_wxapi_launch_req_country", this.country);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelmsg.LaunchFromWX$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Resp extends BaseResp {
        public Resp() {
        }

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseResp
        public boolean checkArgs() {
            return true;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseResp
        public int getType() {
            return 6;
        }
    }

    private LaunchFromWX() {
    }
}
