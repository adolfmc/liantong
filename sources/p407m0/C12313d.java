package p407m0;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPCloseQPayBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: m0.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12313d extends AbstractC14257d<WPCloseQPayBean> {

    /* renamed from: c */
    public final /* synthetic */ String f24929c;

    /* renamed from: d */
    public final /* synthetic */ C12308b f24930d;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: m0.d$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C12314a extends TypeToken<WPResult<WPCloseQPayBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C12313d(C12308b c12308b, AbstractC12375a abstractC12375a, String str) {
        super(abstractC12375a, 1);
        this.f24930d = c12308b;
        this.f24929c = str;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPCloseQPayBean> wPResult) {
        if (this.f24930d.m1795b()) {
            if ("4066041007".equals(wPResult.getCode())) {
                if (TextUtils.isEmpty(this.f24929c)) {
                    return;
                }
                this.f24930d.m1798a().mo1851c(wPResult.getMsg());
            } else if ("4066041002".equals(wPResult.getCode())) {
                this.f24930d.m1798a().mo1854b();
                this.f24930d.m1798a().mo1856a(wPResult.getMsg());
            } else if ("4066049811".equals(wPResult.getCode())) {
                this.f24930d.m1798a().mo1853b(wPResult.getMsg());
            } else {
                super.mo10a(wPResult);
                this.f24930d.m1798a().mo1859a();
            }
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPCloseQPayBean> wPResult) {
        if (this.f24930d.m1795b()) {
            if (wPResult.getData() == null) {
                this.f24930d.m1798a().mo1859a();
                return;
            }
            this.f24930d.m1798a().mo1850d();
            this.f24930d.m1798a().mo1849g(wPResult.getData().getUserNo());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C12314a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f24930d.m1795b()) {
            this.f24930d.m1798a().mo1859a();
        }
    }
}
