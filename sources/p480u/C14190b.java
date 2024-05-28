package p480u;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.b */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14190b extends AbstractC14257d<WPQOpenResultBean> {

    /* renamed from: c */
    public final /* synthetic */ String f27693c;

    /* renamed from: d */
    public final /* synthetic */ C14200g f27694d;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.b$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14191a extends TypeToken<WPResult<WPQOpenResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14190b(C14200g c14200g, AbstractC12375a abstractC12375a, String str) {
        super(abstractC12375a, 0);
        this.f27694d = c14200g;
        this.f27693c = str;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPQOpenResultBean> wPResult) {
        if (this.f27694d.m1795b()) {
            if ("4066041007".equals(wPResult.getCode())) {
                if (TextUtils.isEmpty(this.f27693c)) {
                    return;
                }
                this.f27694d.m1798a().mo104c(wPResult.getMsg());
            } else if ("4066041002".equals(wPResult.getCode())) {
                this.f27694d.m1798a().mo108a(wPResult.getMsg());
            } else if ("4066049811".equals(wPResult.getCode())) {
                this.f27694d.m1798a().mo105b(wPResult.getMsg());
            } else {
                super.mo10a(wPResult);
            }
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPQOpenResultBean> wPResult) {
        WPQOpenResultBean data;
        if (!this.f27694d.m1795b() || (data = wPResult.getData()) == null) {
            return;
        }
        if (!data.isSignSuccess() && "001".equals(data.getSignResultCode())) {
            this.f27694d.m1798a().mo109a(wPResult.getData());
        } else {
            this.f27694d.m1798a().mo106b(data);
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14191a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
    }
}
