package p091c0;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPFidoResultBean;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import com.unicom.pay.normal.order.bean.WPPayBeforeBean;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import java.lang.reflect.Type;
import java.util.HashMap;
import p089b0.AbstractC1471e;
import p089b0.InterfaceC1472f;
import p411o.AbstractC12375a;
import p470p0.C13638c;
import p473r.C13707b;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: c0.o */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC1556o<V extends InterfaceC1472f> extends AbstractC1471e<V> {

    /* renamed from: c0.o$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1557a extends AbstractC14257d<WPUnionPayResultBean> {

        /* renamed from: c */
        public final /* synthetic */ String f2585c;

        /* renamed from: d */
        public final /* synthetic */ WPPayBeforeBean f2586d;

        /* renamed from: e */
        public final /* synthetic */ String f2587e;

        /* renamed from: f */
        public final /* synthetic */ String f2588f;

        /* renamed from: g */
        public final /* synthetic */ String f2589g;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.o$a$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1558a extends TypeToken<WPResult<WPUnionPayResultBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1557a(AbstractC12375a abstractC12375a, int i, String str, WPPayBeforeBean wPPayBeforeBean, String str2, String str3, String str4) {
            super(abstractC12375a, i);
            this.f2585c = str;
            this.f2586d = wPPayBeforeBean;
            this.f2587e = str2;
            this.f2588f = str3;
            this.f2589g = str4;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPUnionPayResultBean> wPResult) {
            if (AbstractC1556o.this.m1795b()) {
                ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo1793I();
                if ("406114018".equals(wPResult.getCode())) {
                    ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2088d();
                    ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2102a(wPResult.getData(), this.f2585c, this.f2586d);
                } else if ("406114019".equals(wPResult.getCode())) {
                    ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2095b();
                    wPResult.getData();
                    ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2108E();
                } else if ("406114030".equals(wPResult.getCode())) {
                    if (TextUtils.isEmpty(this.f2587e)) {
                        ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2101a(this.f2586d, wPResult.getMsg());
                    } else {
                        ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2090c(wPResult.getMsg());
                    }
                } else if ("406114035".equals(wPResult.getCode())) {
                    ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2095b();
                    ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2098a(wPResult.getMsg());
                } else if ("406114045".equals(wPResult.getCode())) {
                    super.mo10a(wPResult);
                } else if ("406114055".equals(wPResult.getCode())) {
                    ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2094b(wPResult.getMsg());
                } else if ("406114056".equals(wPResult.getCode()) || "406114027".equals(wPResult.getCode()) || "406114026".equals(wPResult.getCode())) {
                    ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2095b();
                    ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo6093d(this.f2588f);
                } else {
                    super.mo10a(wPResult);
                    m27a();
                    if (wPResult.getData() == null) {
                        wPResult.setData(new WPUnionPayResultBean());
                    }
                    ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2095b();
                }
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPUnionPayResultBean> wPResult) {
            if (AbstractC1556o.this.m1795b()) {
                ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo1793I();
                ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2088d();
                ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2093b(!TextUtils.isEmpty(this.f2589g));
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1558a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            super.mo11d(str);
            if (AbstractC1556o.this.m1795b()) {
                ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo1793I();
                ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2106a();
            }
        }
    }

    /* renamed from: c0.o$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1559b extends AbstractC14257d<WPUnionPayResultBean> {

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.o$b$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1560a extends TypeToken<WPResult<WPUnionPayResultBean>> {
        }

        public C1559b(AbstractC12375a abstractC12375a) {
            super(abstractC12375a);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPUnionPayResultBean> wPResult) {
            if (AbstractC1556o.this.m1795b()) {
                ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2107T();
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1560a().getType();
        }
    }

    /* renamed from: c0.o$c */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1561c extends AbstractC14257d<WPFidoResultBean> {

        /* renamed from: c */
        public final /* synthetic */ boolean f2592c;

        /* renamed from: d */
        public final /* synthetic */ WPQPayUserInfoBean f2593d;

        /* renamed from: e */
        public final /* synthetic */ WPPayBeforeBean f2594e;

        /* renamed from: f */
        public final /* synthetic */ String f2595f;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.o$c$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1562a extends TypeToken<WPResult<WPFidoResultBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1561c(AbstractC12375a abstractC12375a, boolean z, WPQPayUserInfoBean wPQPayUserInfoBean, WPPayBeforeBean wPPayBeforeBean, String str) {
            super(abstractC12375a, 1);
            this.f2592c = z;
            this.f2593d = wPQPayUserInfoBean;
            this.f2594e = wPPayBeforeBean;
            this.f2595f = str;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPFidoResultBean> wPResult) {
            if (AbstractC1556o.this.m1795b()) {
                if (this.f2592c) {
                    m27a();
                    super.mo10a(wPResult);
                } else {
                    m27a();
                }
                ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2100a(this.f2593d, this.f2594e, this.f2595f);
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPFidoResultBean> wPResult) {
            WPFidoResultBean data = wPResult.getData();
            if (!AbstractC1556o.this.m1795b() || data == null) {
                return;
            }
            ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2097a(data.getUafRequest(), this.f2595f, this.f2593d, this.f2594e);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1562a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            if (AbstractC1556o.this.m1795b()) {
                if (this.f2592c) {
                    m27a();
                    super.mo11d(str);
                } else {
                    m27a();
                }
                ((InterfaceC1472f) AbstractC1556o.this.m1798a()).mo2100a(this.f2593d, this.f2594e, this.f2595f);
            }
        }
    }

    /* renamed from: a */
    public final void m22169a(WPQPayUserInfoBean wPQPayUserInfoBean, WPPayBeforeBean wPPayBeforeBean) {
        if (wPQPayUserInfoBean != null) {
            try {
                if (!TextUtils.isEmpty(wPQPayUserInfoBean.getCurrentFido())) {
                    m22167a(wPQPayUserInfoBean.getCurrentFido(), wPQPayUserInfoBean, wPPayBeforeBean, false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ((InterfaceC1472f) m1798a()).mo2091c(wPPayBeforeBean);
    }

    /* renamed from: a */
    public final void m22167a(String str, WPQPayUserInfoBean wPQPayUserInfoBean, WPPayBeforeBean wPPayBeforeBean, boolean z) {
        ((InterfaceC1472f) m1798a()).mo1792Y();
        HashMap hashMap = new HashMap();
        hashMap.put("authorType", str);
        hashMap.put("deviceId", C10546a.C10576i.f20125a.f20061k);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27803P, hashMap, false, new C1561c(this, z, wPQPayUserInfoBean, wPPayBeforeBean, str)));
    }

    /* renamed from: a */
    public final void m22166a(String str, String str2, String str3, String str4, String str5) {
        HashMap m126a = C13707b.m126a("riskCheckToken", str, "faceToken", str2);
        m126a.put("expand", str3);
        m126a.put("tradeOrderNo", str4);
        m126a.put("payType", str5);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27835y, m126a, false, new C1559b(this)));
    }

    /* renamed from: a */
    public final void m22168a(String str, WPPayBeforeBean wPPayBeforeBean, String str2, String str3, String str4, String str5) {
        String str6;
        String str7;
        HashMap hashMap = new HashMap();
        hashMap.put("uuid", wPPayBeforeBean.getUuid());
        hashMap.put("tradeOrderNo", str2);
        hashMap.put("toolExpand", str);
        hashMap.put("payPassword", TextUtils.isEmpty(str4) ? "" : str4);
        hashMap.put("perCertSn", "");
        hashMap.put("perSignMsg", "");
        if ("225".equals(C10546a.C10576i.f20125a.f20060j)) {
            str6 = "canFaceCheck";
            str7 = "0";
        } else {
            str6 = "canFaceCheck";
            str7 = "1";
        }
        hashMap.put(str6, str7);
        hashMap.put("fapAgrNo", wPPayBeforeBean.getFapAgrNo());
        hashMap.put("fundChnCode", wPPayBeforeBean.getFundChnCode());
        hashMap.put("orderCache", str5);
        hashMap.put("uafResponse", TextUtils.isEmpty(str3) ? "" : C13638c.m187a(str3));
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27832v, hashMap, false, new C1557a(this, TextUtils.isEmpty(str3) ? 1 : 0, str, wPPayBeforeBean, str4, str2, str3)));
    }
}
