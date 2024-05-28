package com.tencent.p348mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelbase.BaseReq;
import com.tencent.p348mm.opensdk.modelbase.BaseResp;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import com.tencent.p348mm.opensdk.utils.C10386b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgramWithToken */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXLaunchMiniProgramWithToken {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgramWithToken$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Req extends BaseReq {
        private static final String TAG = "MicroMsg.SDK.WXLaunchMiniProgramWithToken.Req";
        public String token;

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public boolean checkArgs() {
            if (C10386b.m6202b(this.token)) {
                C10384Log.m6210e("MicroMsg.SDK.WXLaunchMiniProgramWithToken.Req", "token is null");
                return false;
            }
            return true;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public int getType() {
            return 29;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_launch_wxminiprogram_token", this.token);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgramWithToken$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Resp extends BaseResp {
        public static final int ERR_INVALID_TOKEN = -1000;
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
            return 29;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_launch_wxminiprogram_ext_msg", this.extMsg);
        }
    }
}
