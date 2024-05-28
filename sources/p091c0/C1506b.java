package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import com.unicom.pay.normal.order.bean.WPDiscountQueryBean;
import com.unicom.pay.normal.order.bean.WPPayBeforeBean;
import com.unicom.pay.normal.order.bean.WPUnionFqInfoBean;
import java.lang.reflect.Type;
import java.util.HashMap;
import p089b0.AbstractC1467a;
import p089b0.InterfaceC1468b;
import p411o.AbstractC12375a;
import p473r.C13707b;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: c0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1506b extends AbstractC1467a {

    /* renamed from: c0.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1507a extends AbstractC14257d<WPDiscountQueryBean> {

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.b$a$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1508a extends TypeToken<WPResult<WPDiscountQueryBean>> {
        }

        public C1507a(AbstractC12375a abstractC12375a) {
            super(abstractC12375a, 1);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPDiscountQueryBean> wPResult) {
            super.mo10a(wPResult);
            if (C1506b.this.m1795b()) {
                ((InterfaceC1468b) C1506b.this.m1798a()).mo6134o();
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPDiscountQueryBean> wPResult) {
            if (C1506b.this.m1795b()) {
                ((InterfaceC1468b) C1506b.this.m1798a()).mo6145a(wPResult.getData());
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1508a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            super.mo11d(str);
            if (C1506b.this.m1795b()) {
                ((InterfaceC1468b) C1506b.this.m1798a()).mo6134o();
            }
        }
    }

    /* renamed from: c0.b$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1509b extends AbstractC14257d<WPUnionFqInfoBean> {

        /* renamed from: c */
        public final /* synthetic */ int f2539c;

        /* renamed from: d */
        public final /* synthetic */ int f2540d;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.b$b$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1510a extends TypeToken<WPResult<WPUnionFqInfoBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1509b(AbstractC12375a abstractC12375a, int i, int i2) {
            super(abstractC12375a);
            this.f2539c = i;
            this.f2540d = i2;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPUnionFqInfoBean> wPResult) {
            if (C1506b.this.m1795b()) {
                ((InterfaceC1468b) C1506b.this.m1798a()).mo6139a(wPResult.getData(), this.f2539c, this.f2540d);
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1510a().getType();
        }
    }

    /* renamed from: c0.b$c */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1511c extends AbstractC14257d<WPPayBeforeBean> {

        /* renamed from: c */
        public final /* synthetic */ String f2542c;

        /* renamed from: d */
        public final /* synthetic */ String f2543d;

        /* renamed from: e */
        public final /* synthetic */ String f2544e;

        /* renamed from: f */
        public final /* synthetic */ String f2545f;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.b$c$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1512a extends TypeToken<WPResult<WPPayBeforeBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1511c(AbstractC12375a abstractC12375a, String str, String str2, String str3, String str4) {
            super(abstractC12375a);
            this.f2542c = str;
            this.f2543d = str2;
            this.f2544e = str3;
            this.f2545f = str4;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPPayBeforeBean> wPResult) {
            if (C1506b.this.m1795b()) {
                if ("406114043".equals(wPResult.getCode())) {
                    wPResult.getData();
                    ((InterfaceC1468b) C1506b.this.m1798a()).mo6151B();
                } else if ("406114046".equals(wPResult.getCode())) {
                    wPResult.getData();
                    ((InterfaceC1468b) C1506b.this.m1798a()).mo6150C();
                } else if ("406114047".equals(wPResult.getCode())) {
                    wPResult.getData();
                    ((InterfaceC1468b) C1506b.this.m1798a()).mo6149L();
                } else if ("406114007".equals(wPResult.getCode())) {
                } else {
                    if ("406114056".equals(wPResult.getCode())) {
                        C1506b.this.m22184a(this.f2545f);
                    } else {
                        super.mo10a(wPResult);
                    }
                }
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPPayBeforeBean> wPResult) {
            if (C1506b.this.m1795b()) {
                ((InterfaceC1468b) C1506b.this.m1798a()).mo6141a(wPResult.getData());
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1512a().getType();
        }
    }

    /* renamed from: c0.b$d */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1513d extends AbstractC14257d<WPUnionPayResultBean> {

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.b$d$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1514a extends TypeToken<WPResult<WPUnionPayResultBean>> {
        }

        public C1513d(AbstractC12375a abstractC12375a) {
            super(abstractC12375a);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPUnionPayResultBean> wPResult) {
            if (C1506b.this.m1795b()) {
                ((InterfaceC1468b) C1506b.this.m1798a()).mo6146a(wPResult);
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPUnionPayResultBean> wPResult) {
            if (C1506b.this.m1795b()) {
                ((InterfaceC1468b) C1506b.this.m1798a()).mo6146a(wPResult);
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1514a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            m27a();
        }
    }

    /* renamed from: a */
    public final void m22182a(String str, String str2, int i, int i2) {
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27795H, C13707b.m126a("expand", str, "tradeOrderNo", str2), false, new C1509b(this, i, i2)));
    }

    /* renamed from: b */
    public final void m22180b(String str, String str2, String str3, String str4, String str5) {
        HashMap m126a = C13707b.m126a("expand", str, "tradeOrderNo", str2);
        m126a.put("payType", str5);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27831u, m126a, false, new C1511c(this, str, str3, str4, str2)));
    }

    /* renamed from: a */
    public final void m22183a(String str, String str2) {
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27793F, C13707b.m126a("expand", str, "tradeOrderNo", str2), false, new C1507a(this)));
    }

    /* renamed from: b */
    public final void m22181b(String str, String str2) {
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27792E, C13707b.m126a("bankUid", str, "tradeOrderNo", str2), false, new C1504a(this, this)));
    }

    /* renamed from: a */
    public final void m22184a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("tradeOrderNo", str);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27790C, hashMap, false, new C1513d(this)));
    }
}
