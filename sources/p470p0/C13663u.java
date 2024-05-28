package p470p0;

import android.text.TextUtils;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import p393h.C12014f;

/* renamed from: p0.u */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13663u {

    /* renamed from: a */
    public C12014f f27508a;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: p0.u$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class C13664a {

        /* renamed from: a */
        public static C13663u f27509a = new C13663u();
    }

    /* renamed from: a */
    public final WPQPayUserInfoBean m164a(String str) {
        try {
            return this.f27508a.m1981a(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public final void m165a(WPQPayUserInfoBean wPQPayUserInfoBean) {
        try {
            WPQPayUserInfoBean m1981a = this.f27508a.m1981a(wPQPayUserInfoBean.getUserNo());
            if (m1981a != null && !TextUtils.isEmpty(m1981a.getUserNo())) {
                m1981a.setPayToken(wPQPayUserInfoBean.getPayToken());
                m1981a.setCurrentFido(wPQPayUserInfoBean.getCurrentFido());
                m1981a.setIsSupportTwo(wPQPayUserInfoBean.getIsSupportTwo());
                this.f27508a.m1980b(m1981a);
            }
            this.f27508a.m1982a(wPQPayUserInfoBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
