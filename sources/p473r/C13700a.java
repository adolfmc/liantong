package p473r;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.result.bean.WPBannerDiscountBean;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import java.lang.reflect.Type;
import java.util.HashMap;
import p411o.AbstractC12375a;
import p471q.AbstractC13673a;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: r.a */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13700a extends AbstractC13673a {

    /* renamed from: r.a$a */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C13701a extends AbstractC14257d<WPUnionPayResultBean> {

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: r.a$a$a */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        class C13702a extends TypeToken<WPResult<WPUnionPayResultBean>> {
        }

        public C13701a(AbstractC12375a abstractC12375a) {
            super(abstractC12375a, 1);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPUnionPayResultBean> wPResult) {
            if (C13700a.this.m1795b()) {
                C13700a.this.m1798a().mo154a(wPResult);
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPUnionPayResultBean> wPResult) {
            if (C13700a.this.m1795b()) {
                C13700a.this.m1798a().mo154a(wPResult);
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C13702a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            if (C13700a.this.m1795b()) {
                m27a();
                WPResult<WPUnionPayResultBean> wPResult = new WPResult<>();
                wPResult.setCode("406114034");
                wPResult.setMsg("支付未完成，请重新支付");
                C13700a.this.m1798a().mo154a(wPResult);
            }
        }
    }

    /* renamed from: r.a$b */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C13703b extends AbstractC14257d {

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: r.a$b$a */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        class C13704a extends TypeToken<WPResult> {
        }

        public C13703b(AbstractC12375a abstractC12375a) {
            super(abstractC12375a, 1);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult wPResult) {
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult wPResult) {
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C13704a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
        }
    }

    /* renamed from: r.a$c */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C13705c extends AbstractC14257d<WPBannerDiscountBean> {

        /* renamed from: c */
        public final /* synthetic */ int f27566c;

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: r.a$c$a */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        class C13706a extends TypeToken<WPResult<WPBannerDiscountBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13705c(AbstractC12375a abstractC12375a, int i) {
            super(abstractC12375a, 0);
            this.f27566c = i;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPBannerDiscountBean> wPResult) {
            super.mo10a(wPResult);
            if (C13700a.this.m1795b()) {
                C13700a.this.m1798a().mo156H();
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPBannerDiscountBean> wPResult) {
            if (C13700a.this.m1795b()) {
                C13700a.this.m1798a().mo153a(wPResult.getData(), this.f27566c);
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C13706a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            super.mo11d(str);
            if (C13700a.this.m1795b()) {
                C13700a.this.m1798a().mo156H();
            }
        }
    }

    /* renamed from: a */
    public final void m129a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("tradeOrderNo", str);
        if (m1795b()) {
            hashMap.put("recommendedScene", m1798a().mo142g());
        }
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27822l, hashMap, false, new C13703b(this)));
    }

    /* renamed from: b */
    public final void m127b(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("tradeOrderNo", str);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27790C, hashMap, false, new C13701a(this)));
    }

    /* renamed from: a */
    public final void m128a(String str, String str2, String str3, int i) {
        HashMap m126a = C13707b.m126a("tradeOrderNo", str, "ruleId", str2);
        m126a.put("bannerDiscountMsg", str3);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27810W, m126a, false, new C13705c(this, i)));
    }
}
