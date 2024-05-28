package p391g0;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import java.lang.reflect.Type;
import p389f0.InterfaceC11927b;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: g0.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11952c extends AbstractC14257d<WPQOpenResultBean> {

    /* renamed from: c */
    public final /* synthetic */ String f24302c;

    /* renamed from: d */
    public final /* synthetic */ C11954d f24303d;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: g0.c$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C11953a extends TypeToken<WPResult<WPQOpenResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C11952c(C11954d c11954d, AbstractC12375a abstractC12375a, String str) {
        super(abstractC12375a, 1);
        this.f24303d = c11954d;
        this.f24302c = str;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPQOpenResultBean> wPResult) {
        if (this.f24303d.m1795b()) {
            if ("4066041007".equals(wPResult.getCode())) {
                if (TextUtils.isEmpty(this.f24302c)) {
                    return;
                }
                this.f24303d.m1798a().mo2030c(wPResult.getMsg());
            } else if ("4066041002".equals(wPResult.getCode())) {
                this.f24303d.m1798a().mo2033b();
                this.f24303d.m1798a().mo2034a(wPResult.getMsg());
            } else if ("4066049811".equals(wPResult.getCode())) {
                this.f24303d.m1798a().mo2032b(wPResult.getMsg());
            } else {
                super.mo10a(wPResult);
                this.f24303d.m1798a().mo2039a();
            }
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPQOpenResultBean> wPResult) {
        InterfaceC11927b m1798a;
        boolean z;
        if (this.f24303d.m1795b()) {
            WPQOpenResultBean data = wPResult.getData();
            if (data == null) {
                this.f24303d.m1798a().mo2039a();
                return;
            }
            if (data.isSignSuccess()) {
                this.f24303d.m1798a().mo2029d();
                m1798a = this.f24303d.m1798a();
                z = true;
            } else if ("001".equals(data.getSignResultCode())) {
                this.f24303d.m1798a().mo2029d();
                this.f24303d.m1798a().mo2037a(wPResult.getData());
                return;
            } else {
                this.f24303d.m1798a().mo2039a();
                m1798a = this.f24303d.m1798a();
                z = false;
            }
            m1798a.mo2036a(data, z);
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C11953a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f24303d.m1795b()) {
            this.f24303d.m1798a().mo2039a();
        }
    }
}
