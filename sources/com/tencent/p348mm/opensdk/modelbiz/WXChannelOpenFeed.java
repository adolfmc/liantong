package com.tencent.p348mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelbase.BaseReq;
import com.tencent.p348mm.opensdk.modelbase.BaseResp;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import com.tencent.p348mm.opensdk.utils.C10386b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelbiz.WXChannelOpenFeed */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXChannelOpenFeed {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXChannelOpenFeed$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Req extends BaseReq {
        private static final int LENGTH_LIMIT = 1024;
        private static final String TAG = "MicroMsg.SDK.WXChannelOpenFeed.Req";
        public String feedID;
        public String nonceID;
        public boolean notGetReleatedList;

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public boolean checkArgs() {
            String str;
            String str2;
            if (C10386b.m6202b(this.feedID)) {
                str = "MicroMsg.SDK.WXChannelOpenFeed.Req";
                str2 = "feedID is null";
            } else if (C10386b.m6202b(this.nonceID)) {
                str = "MicroMsg.SDK.WXChannelOpenFeed.Req";
                str2 = "nonceID is null";
            } else if (this.feedID.length() > 1024) {
                str = "MicroMsg.SDK.WXChannelOpenFeed.Req";
                str2 = "feedID.length too long!";
            } else if (this.nonceID.length() <= 1024) {
                return true;
            } else {
                str = "MicroMsg.SDK.WXChannelOpenFeed.Req";
                str2 = "nonceID.length too long!";
            }
            C10384Log.m6210e(str, str2);
            return false;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.feedID = bundle.getString("_wxapi_finder_feedID");
            this.nonceID = bundle.getString("_wxapi_finder_nonceID");
            this.notGetReleatedList = bundle.getBoolean("_wxapi_finder_notGetReleatedList");
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public int getType() {
            return 36;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_finder_feedID", this.feedID);
            bundle.putString("_wxapi_finder_nonceID", this.nonceID);
            bundle.putBoolean("_wxapi_finder_notGetReleatedList", this.notGetReleatedList);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXChannelOpenFeed$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Resp extends BaseResp {
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
            this.extMsg = bundle.getString("_wxapi_finder_extMsg");
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public int getType() {
            return 36;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_finder_extMsg", this.extMsg);
        }
    }
}
