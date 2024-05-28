package p407m0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.qpay.setting.bean.WPSettingInfoBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: m0.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12306a extends AbstractC14257d<WPSettingInfoBean> {

    /* renamed from: c */
    public final /* synthetic */ C12308b f24925c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: m0.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C12307a extends TypeToken<WPResult<WPSettingInfoBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C12306a(C12308b c12308b, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f24925c = c12308b;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPSettingInfoBean> wPResult) {
        if (this.f24925c.m1795b()) {
            this.f24925c.m1798a().mo1857a(wPResult.getData());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C12307a().getType();
    }
}
