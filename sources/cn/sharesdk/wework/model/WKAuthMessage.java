package cn.sharesdk.wework.model;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.proguard.PrivateMemberKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WKAuthMessage implements PrivateMemberKeeper {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.sharesdk.wework.model.WKAuthMessage$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C1885a extends WKBaseMessage {

        /* renamed from: j */
        public String f3342j;

        /* renamed from: k */
        public String f3343k;

        @Override // cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
        /* renamed from: a */
        public boolean mo21167a() {
            return (TextUtils.isEmpty(this.f3353l) || TextUtils.isEmpty(this.f3343k)) ? false : true;
        }

        @Override // cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
        /* renamed from: a */
        public void mo21166a(Bundle bundle) {
            bundle.putString("_wwauthmsg_state", this.f3342j);
            bundle.putString("_wwauthmsg_schema", this.f3343k);
            super.mo21166a(bundle);
        }

        @Override // cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
        /* renamed from: b */
        public void mo21165b(Bundle bundle) {
            this.f3342j = bundle.getString("_wwauthmsg_state");
            this.f3343k = bundle.getString("_wwauthmsg_schema");
            super.mo21165b(bundle);
            if (TextUtils.isEmpty(this.f3355n)) {
                this.f3355n = this.f3343k;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Resp extends WKBaseRespMessage implements PrivateMemberKeeper {

        /* renamed from: j */
        public String f3340j;

        /* renamed from: k */
        public String f3341k;

        @Override // cn.sharesdk.wework.model.WKBaseRespMessage, cn.sharesdk.wework.model.BaseMessage
        /* renamed from: a */
        public void mo21166a(Bundle bundle) {
            super.mo21166a(bundle);
            bundle.putString("_wwauthrsp_code", this.f3340j);
            bundle.putString("_wwauthrsp_state", this.f3341k);
            bundle.putInt("_wwauthrsp_err", this.f3359n);
            bundle.putString("_wwauthrsp_errmsg", this.f3358m);
        }

        @Override // cn.sharesdk.wework.model.WKBaseRespMessage, cn.sharesdk.wework.model.BaseMessage
        /* renamed from: b */
        public void mo21165b(Bundle bundle) {
            super.mo21165b(bundle);
            this.f3340j = bundle.getString("_wwauthrsp_code");
            this.f3341k = bundle.getString("_wwauthrsp_state");
            this.f3359n = bundle.getInt("_wwauthrsp_err", 1);
            this.f3358m = bundle.getString("_wwauthrsp_errmsg");
        }

        @Override // cn.sharesdk.wework.model.WKBaseRespMessage, cn.sharesdk.wework.model.BaseMessage
        /* renamed from: a */
        public void mo21171a(Uri uri) {
            this.f3359n = 2;
            super.mo21171a(uri);
            if (uri != null) {
                try {
                    if (Integer.parseInt(uri.getQueryParameter("cancel")) == 1) {
                        this.f3359n = 1;
                    }
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21742a(th);
                }
                try {
                    String queryParameter = uri.getQueryParameter("error");
                    if (!TextUtils.isEmpty(queryParameter)) {
                        this.f3358m = queryParameter;
                    }
                } catch (Throwable th2) {
                    SSDKLog.m21740b().m21742a(th2);
                }
                this.f3340j = uri.getQueryParameter("code");
                this.f3341k = uri.getQueryParameter("state");
                if (TextUtils.isEmpty(this.f3340j)) {
                    return;
                }
                this.f3359n = 0;
            }
        }
    }
}
