package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.normal.order.bean.WPUnionFqInfoBean;
import java.lang.reflect.Type;
import java.util.HashMap;
import p089b0.AbstractC1473g;
import p411o.AbstractC12375a;
import p473r.C13707b;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: c0.p */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1563p extends AbstractC1473g {

    /* renamed from: c0.p$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1564a extends AbstractC14257d<WPUnionFqInfoBean> {

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.p$a$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1565a extends TypeToken<WPResult<WPUnionFqInfoBean>> {
        }

        public C1564a(AbstractC12375a abstractC12375a) {
            super(abstractC12375a);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPUnionFqInfoBean> wPResult) {
            if (C1563p.this.m1795b()) {
                C1563p.this.m1798a().mo6075M();
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1565a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            super.mo11d(str);
        }
    }

    /* renamed from: c0.p$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1566b extends AbstractC14257d<WPUnionFqInfoBean> {

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.p$b$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1567a extends TypeToken<WPResult<WPUnionFqInfoBean>> {
        }

        public C1566b(AbstractC12375a abstractC12375a) {
            super(abstractC12375a);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPUnionFqInfoBean> wPResult) {
            if (C1563p.this.m1795b()) {
                wPResult.getCommonResp().getTgtid();
                C1563p.this.m1798a().mo6074X();
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1567a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            super.mo11d(str);
        }
    }

    /* renamed from: a */
    public final void m22164a(String str, String str2, String str3, String str4) {
        HashMap m126a = C13707b.m126a("authId", str, "phone", str2);
        m126a.put("code", str3);
        m126a.put("tradeOrderNo", str4);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27804Q, m126a, false, new C1566b(this)));
    }

    /* renamed from: a */
    public final void m22165a(String str, String str2, String str3) {
        HashMap m126a = C13707b.m126a("authId", str, "phone", str2);
        m126a.put("tradeOrderNo", str3);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27805R, m126a, false, new C1564a(this)));
    }
}
