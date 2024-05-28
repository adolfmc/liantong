package p407m0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.qpay.setting.bean.WPBankBean;
import com.unicom.pay.qpay.setting.bean.WPSettingInfoBean;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import p405l0.AbstractC12295a;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: m0.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12308b extends AbstractC12295a {

    /* renamed from: m0.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C12309a extends AbstractC14257d<WPSettingInfoBean> {

        /* renamed from: c */
        public final /* synthetic */ boolean f24926c;

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: m0.b$a$a */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        class C12310a extends TypeToken<WPResult<WPSettingInfoBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12309a(AbstractC12375a abstractC12375a, boolean z) {
            super(abstractC12375a);
            this.f24926c = z;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPSettingInfoBean> wPResult) {
            super.mo10a(wPResult);
            if (C12308b.this.m1795b()) {
                C12308b.this.m1798a().mo1860R();
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPSettingInfoBean> wPResult) {
            if (C12308b.this.m1795b()) {
                C12308b.this.m1798a().mo1855a(this.f24926c);
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C12310a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            super.mo11d(str);
            if (C12308b.this.m1795b()) {
                C12308b.this.m1798a().mo1860R();
            }
        }
    }

    /* renamed from: a */
    public final void m1834a(boolean z, List<WPBankBean> list) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("paymentMsgList", list);
        hashMap.put("isDefault", z ? "1" : "0");
        C14255c.C14256a.f27777a.m30a(C14262f.f27814d, hashMap, new C12309a(this, z));
    }
}
