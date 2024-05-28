package com.tencent.p348mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.sdk.modelbase.BaseReq;
import com.tencent.p348mm.sdk.p354b.C10393b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizProfile */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class JumpToBizProfile {
    public static final int JUMP_TO_HARD_WARE_BIZ_PROFILE = 1;
    public static final int JUMP_TO_NORMAL_BIZ_PROFILE = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizProfile$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Req extends BaseReq {
        private static final int EXT_MSG_LENGTH = 1024;
        private static final String TAG = "MicroMsg.SDK.JumpToBizProfile.Req";
        public String extMsg;
        public int profileType = 0;
        public String toUserName;

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public boolean checkArgs() {
            String str;
            String str2;
            String str3;
            String str4 = this.toUserName;
            if (str4 == null || str4.length() == 0) {
                str = "MicroMsg.SDK.JumpToBizProfile.Req";
                str2 = "checkArgs fail, toUserName is invalid";
            } else {
                String str5 = this.extMsg;
                if (str5 != null && str5.length() > 1024) {
                    str = "MicroMsg.SDK.JumpToBizProfile.Req";
                    str2 = "ext msg is not null, while the length exceed 1024 bytes";
                } else if (this.profileType != 1 || ((str3 = this.extMsg) != null && str3.length() != 0)) {
                    return true;
                } else {
                    str = "MicroMsg.SDK.JumpToBizProfile.Req";
                    str2 = "scene is jump to hardware profile, while extmsg is null";
                }
            }
            C10393b.m6195a(str, str2);
            return false;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.toUserName = bundle.getString("_wxapi_jump_to_biz_profile_req_to_user_name");
            this.extMsg = bundle.getString("_wxapi_jump_to_biz_profile_req_ext_msg");
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public int getType() {
            return 7;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_jump_to_biz_profile_req_to_user_name", this.toUserName);
            bundle.putString("_wxapi_jump_to_biz_profile_req_ext_msg", this.extMsg);
            bundle.putInt("_wxapi_jump_to_biz_profile_req_scene", 0);
            bundle.putInt("_wxapi_jump_to_biz_profile_req_profile_type", this.profileType);
        }
    }
}
