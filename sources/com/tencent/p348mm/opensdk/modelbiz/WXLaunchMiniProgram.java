package com.tencent.p348mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelbase.BaseReq;
import com.tencent.p348mm.opensdk.modelbase.BaseResp;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import com.tencent.p348mm.opensdk.utils.C10386b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXLaunchMiniProgram {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Req extends BaseReq {
        public static final int MINIPROGRAM_TYPE_PREVIEW = 2;
        public static final int MINIPROGRAM_TYPE_TEST = 1;
        public static final int MINIPTOGRAM_TYPE_RELEASE = 0;
        private static final String TAG = "MicroMsg.SDK.WXLaunchMiniProgram.Req";
        public String userName;
        public String path = "";
        public int miniprogramType = 0;
        public String extData = "";

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public boolean checkArgs() {
            String str;
            String str2;
            if (C10386b.m6202b(this.userName)) {
                str = "MicroMsg.SDK.WXLaunchMiniProgram.Req";
                str2 = "userName is null";
            } else {
                int i = this.miniprogramType;
                if (i >= 0 && i <= 2) {
                    return true;
                }
                str = "MicroMsg.SDK.WXLaunchMiniProgram.Req";
                str2 = "miniprogram type should between MINIPTOGRAM_TYPE_RELEASE and MINIPROGRAM_TYPE_PREVIEW";
            }
            C10384Log.m6210e(str, str2);
            return false;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public int getType() {
            return 19;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_launch_wxminiprogram_username", this.userName);
            bundle.putString("_launch_wxminiprogram_path", this.path);
            bundle.putString("_launch_wxminiprogram_extData", this.extData);
            bundle.putInt("_launch_wxminiprogram_type", this.miniprogramType);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Resp extends BaseResp {
        public String extMsg;

        public Resp() {
        }

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
            this.extMsg = bundle.getString("_launch_wxminiprogram_ext_msg");
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public int getType() {
            return 19;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_launch_wxminiprogram_ext_msg", this.extMsg);
        }
    }
}
