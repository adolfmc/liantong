package com.tencent.p348mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.sdk.modelbase.BaseReq;
import java.net.URLEncoder;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelbiz.OpenWebview */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class OpenWebview {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelbiz.OpenWebview$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Req extends BaseReq {
        private static final int MAX_URL_LENGHT = 10240;
        public String url;

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public boolean checkArgs() {
            String str = this.url;
            return str != null && str.length() >= 0 && this.url.length() <= 10240;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public int getType() {
            return 12;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_jump_to_webview_url", URLEncoder.encode(this.url));
        }
    }
}
