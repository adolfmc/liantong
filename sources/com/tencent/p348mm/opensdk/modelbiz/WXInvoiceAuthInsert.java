package com.tencent.p348mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelbase.BaseReq;
import com.tencent.p348mm.opensdk.modelbase.BaseResp;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import com.tencent.p348mm.opensdk.utils.C10386b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelbiz.WXInvoiceAuthInsert */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXInvoiceAuthInsert {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXInvoiceAuthInsert$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Req extends BaseReq {
        private static final String TAG = "MicroMsg.SDK.WXInvoiceAuthInsert.Req";
        private static final int URL_LENGTH_LIMIT = 10240;
        public String url;

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public boolean checkArgs() {
            if (C10386b.m6202b(this.url)) {
                C10384Log.m6209i("MicroMsg.SDK.WXInvoiceAuthInsert.Req", "url should not be empty");
                return false;
            } else if (this.url.length() > 10240) {
                C10384Log.m6210e("MicroMsg.SDK.WXInvoiceAuthInsert.Req", "url must be in 10k");
                return false;
            } else {
                return true;
            }
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.url = bundle.getString("_wxapi_invoice_auth_insert_req_url");
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public int getType() {
            return 20;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.fromBundle(bundle);
            bundle.putString("_wxapi_invoice_auth_insert_req_url", this.url);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.WXInvoiceAuthInsert$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Resp extends BaseResp {
        public String wxOrderId;

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public boolean checkArgs() {
            return true;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.wxOrderId = bundle.getString("_wxapi_invoice_auth_insert_order_id");
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public int getType() {
            return 20;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.fromBundle(bundle);
            bundle.putString("_wxapi_invoice_auth_insert_order_id", this.wxOrderId);
        }
    }
}
