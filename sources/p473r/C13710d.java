package p473r;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p471q.InterfaceC13674b;
import p482w.AbstractC14257d;

/* renamed from: r.d */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13710d extends AbstractC14257d<WPQOpenResultBean> {

    /* renamed from: c */
    public final /* synthetic */ String f27570c;

    /* renamed from: d */
    public final /* synthetic */ C13700a f27571d;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: r.d$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C13711a extends TypeToken<WPResult<WPQOpenResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C13710d(C13700a c13700a, AbstractC12375a abstractC12375a, String str) {
        super(abstractC12375a, 1);
        this.f27571d = c13700a;
        this.f27570c = str;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPQOpenResultBean> wPResult) {
        if (this.f27571d.m1795b()) {
            if ("4066041007".equals(wPResult.getCode())) {
                if (TextUtils.isEmpty(this.f27570c)) {
                    return;
                }
                this.f27571d.m1798a().mo144c(wPResult.getMsg());
            } else if ("4066041002".equals(wPResult.getCode())) {
                this.f27571d.m1798a().mo147b();
                this.f27571d.m1798a().mo148a(wPResult.getMsg());
            } else if ("4066049811".equals(wPResult.getCode())) {
                this.f27571d.m1798a().mo146b(wPResult.getMsg());
            } else {
                super.mo10a(wPResult);
                this.f27571d.m1798a().mo155a();
            }
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPQOpenResultBean> wPResult) {
        InterfaceC13674b m1798a;
        boolean z;
        if (this.f27571d.m1795b()) {
            WPQOpenResultBean data = wPResult.getData();
            if (data == null) {
                this.f27571d.m1798a().mo155a();
                return;
            }
            if (data.isSignSuccess()) {
                this.f27571d.m1798a().mo143d();
                m1798a = this.f27571d.m1798a();
                z = true;
            } else if ("001".equals(data.getSignResultCode())) {
                this.f27571d.m1798a().mo143d();
                this.f27571d.m1798a().mo150a(wPResult.getData());
                return;
            } else {
                this.f27571d.m1798a().mo155a();
                m1798a = this.f27571d.m1798a();
                z = false;
            }
            m1798a.mo149a(data, z);
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C13711a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f27571d.m1795b()) {
            this.f27571d.m1798a().mo155a();
        }
    }
}
