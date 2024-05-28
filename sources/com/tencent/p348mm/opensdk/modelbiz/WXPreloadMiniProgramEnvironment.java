package com.tencent.p348mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelbase.BaseReq;
import com.tencent.p348mm.opensdk.modelbase.BaseResp;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelbiz.WXPreloadMiniProgramEnvironment */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXPreloadMiniProgramEnvironment {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXPreloadMiniProgramEnvironment$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Req extends BaseReq {
        private static final String TAG = "MicroMsg.SDK.WXPreloadMiniProgramEnvironment.Req";
        public String extData = "";

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public boolean checkArgs() {
            return true;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public int getType() {
            return 32;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_preload_wxminiprogram_environment_extData", this.extData);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXPreloadMiniProgramEnvironment$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Resp extends BaseResp {
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
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public int getType() {
            return 32;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
        }
    }
}
