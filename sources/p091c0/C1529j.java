package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.C10531R;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPQPayResultBean;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import com.unicom.pay.normal.discount.bean.WPDiscountListBean;
import com.unicom.pay.normal.order.bean.WPDiscountQueryBean;
import com.unicom.pay.normal.order.bean.WPOrderPayBeforeBean;
import com.unicom.pay.normal.order.bean.WPOtherPayResultBean;
import com.unicom.pay.normal.order.bean.WPPayInfoUpdateBean;
import com.unicom.pay.normal.order.bean.WPUpdateMethodResultBean;
import com.unicom.pay.qpay.open.bean.WPAgreementBean;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import p089b0.AbstractC1469c;
import p089b0.InterfaceC1470d;
import p411o.AbstractC12375a;
import p473r.C13707b;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: c0.j */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1529j extends AbstractC1469c {

    /* renamed from: c0.j$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1530a extends AbstractC14257d<WPQPayResultBean> {

        /* renamed from: c */
        public final /* synthetic */ String f2559c;

        /* renamed from: d */
        public final /* synthetic */ String f2560d;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.j$a$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1531a extends TypeToken<WPResult<WPQPayResultBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1530a(AbstractC12375a abstractC12375a, String str, String str2) {
            super(abstractC12375a);
            this.f2559c = str;
            this.f2560d = str2;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPQPayResultBean> wPResult) {
            super.mo10a(wPResult);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPQPayResultBean> wPResult) {
            WPQPayResultBean data;
            if (!C1529j.this.m1795b() || (data = wPResult.getData()) == null) {
                return;
            }
            if ("001".equals(data.getResultCode())) {
                ((InterfaceC1470d) C1529j.this.m1798a()).mo6120a(data, this.f2559c, this.f2560d);
            } else if ("002".equals(data.getResultCode())) {
                ((InterfaceC1470d) C1529j.this.m1798a()).mo6130F();
            } else if (!"003".equals(data.getResultCode())) {
                ((InterfaceC1470d) C1529j.this.m1798a()).mo1790i(data.getResultMsg());
            } else {
                ((InterfaceC1470d) C1529j.this.m1798a()).mo1790i(data.getResultMsg());
                ((InterfaceC1470d) C1529j.this.m1798a()).mo6089h();
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1531a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            if (C1529j.this.m1795b()) {
                m27a();
                ((InterfaceC1470d) C1529j.this.m1798a()).mo1791a(C10531R.string.wp_detail_qpay_pay_fail);
            }
        }
    }

    /* renamed from: c0.j$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1532b extends AbstractC14257d {

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.j$b$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1533a extends TypeToken<WPResult> {
        }

        public C1532b(AbstractC12375a abstractC12375a) {
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
            return new C1533a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
        }
    }

    /* renamed from: c0.j$c */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1534c extends AbstractC14257d<WPAgreementBean> {

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.j$c$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1535a extends TypeToken<WPResult<WPAgreementBean>> {
        }

        public C1534c(AbstractC12375a abstractC12375a) {
            super(abstractC12375a, 1);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPAgreementBean> wPResult) {
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPAgreementBean> wPResult) {
            if (!C1529j.this.m1795b() || wPResult.getData() == null) {
                return;
            }
            ((InterfaceC1470d) C1529j.this.m1798a()).mo6106a(wPResult.getData());
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1535a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
        }
    }

    /* renamed from: c0.j$d */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1536d extends AbstractC14257d<WPDiscountQueryBean> {

        /* renamed from: c */
        public final /* synthetic */ String f2563c;

        /* renamed from: d */
        public final /* synthetic */ String f2564d;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.j$d$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1537a extends TypeToken<WPResult<WPDiscountQueryBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1536d(AbstractC12375a abstractC12375a, String str, String str2) {
            super(abstractC12375a, 1);
            this.f2563c = str;
            this.f2564d = str2;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPDiscountQueryBean> wPResult) {
            try {
                super.mo10a(wPResult);
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6079u();
                }
            } catch (Exception unused) {
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPDiscountQueryBean> wPResult) {
            try {
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6118a(wPResult.getData(), this.f2563c, this.f2564d);
                }
            } catch (Exception unused) {
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1537a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            try {
                super.mo11d(str);
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6079u();
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: c0.j$e */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1538e extends AbstractC14257d<WPUpdateMethodResultBean> {

        /* renamed from: c */
        public final /* synthetic */ int f2566c;

        /* renamed from: d */
        public final /* synthetic */ int f2567d;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.j$e$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1539a extends TypeToken<WPResult<WPUpdateMethodResultBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1538e(AbstractC12375a abstractC12375a, int i, int i2) {
            super(abstractC12375a, 1);
            this.f2566c = i;
            this.f2567d = i2;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPUpdateMethodResultBean> wPResult) {
            try {
                super.mo10a(wPResult);
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6126a(this.f2566c, this.f2567d);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPUpdateMethodResultBean> wPResult) {
            try {
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6109a(wPResult.getData(), this.f2566c, this.f2567d);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1539a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            try {
                super.mo11d(str);
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6126a(this.f2566c, this.f2567d);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: c0.j$f */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1540f extends AbstractC14257d<WPOrderPayBeforeBean> {

        /* renamed from: c */
        public final /* synthetic */ String f2569c;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.j$f$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1541a extends TypeToken<WPResult<WPOrderPayBeforeBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1540f(AbstractC12375a abstractC12375a, String str) {
            super(abstractC12375a);
            this.f2569c = str;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPOrderPayBeforeBean> wPResult) {
            WPOrderPayBeforeBean data;
            C1529j c1529j;
            InterfaceC1470d interfaceC1470d;
            String jumpUrl;
            String str;
            try {
                if (!C1529j.this.m1795b() || (data = wPResult.getData()) == null) {
                    return;
                }
                if ("1".equals(data.getPayBeforeType())) {
                    if ("4061140081".equals(wPResult.getCode())) {
                        interfaceC1470d = (InterfaceC1470d) C1529j.this.m1798a();
                        jumpUrl = data.getWappayPayBeforeResp().getJumpUrl();
                        str = "3";
                    } else if ("4061140082".equals(wPResult.getCode())) {
                        interfaceC1470d = (InterfaceC1470d) C1529j.this.m1798a();
                        jumpUrl = data.getWappayPayBeforeResp().getJumpUrl();
                        str = "4";
                    } else if (!"4061140083".equals(wPResult.getCode())) {
                        if ("406114007".equals(wPResult.getCode())) {
                            ((InterfaceC1470d) C1529j.this.m1798a()).mo6090e(data.getWappayPayBeforeResp().getJumpUrl());
                            return;
                        } else if ("406114009".equals(wPResult.getCode())) {
                            ((InterfaceC1470d) C1529j.this.m1798a()).mo6099b(data.getWappayPayBeforeResp());
                            return;
                        } else {
                            if ("406114056".equals(wPResult.getCode())) {
                                c1529j = C1529j.this;
                            } else {
                                if (!"406114027".equals(wPResult.getCode()) && !"406114026".equals(wPResult.getCode())) {
                                    if ("4061140433".equals(wPResult.getCode())) {
                                        ((InterfaceC1470d) C1529j.this.m1798a()).mo6091e(data.getWappayPayBeforeResp());
                                        return;
                                    } else if ("406114043".equals(wPResult.getCode())) {
                                        ((InterfaceC1470d) C1529j.this.m1798a()).mo6094d(data.getWappayPayBeforeResp());
                                        return;
                                    }
                                }
                                c1529j = C1529j.this;
                            }
                            ((InterfaceC1470d) c1529j.m1798a()).mo6093d(this.f2569c);
                            return;
                        }
                    } else {
                        interfaceC1470d = (InterfaceC1470d) C1529j.this.m1798a();
                        jumpUrl = data.getWappayPayBeforeResp().getJumpUrl();
                        str = "2";
                    }
                    interfaceC1470d.mo6098b(jumpUrl, str);
                    return;
                } else if (!"2".equals(data.getPayBeforeType())) {
                    return;
                }
                super.mo10a(wPResult);
            } catch (Exception unused) {
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPOrderPayBeforeBean> wPResult) {
            try {
                if (C1529j.this.m1795b()) {
                    WPOrderPayBeforeBean data = wPResult.getData();
                    if ("1".equals(data.getPayBeforeType())) {
                        ((InterfaceC1470d) C1529j.this.m1798a()).mo6116a(data.getWappayPayBeforeResp());
                    } else if ("2".equals(data.getPayBeforeType())) {
                        ((InterfaceC1470d) C1529j.this.m1798a()).mo6100b(wPResult);
                    }
                }
            } catch (Exception unused) {
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1541a().getType();
        }
    }

    /* renamed from: c0.j$g */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1542g extends AbstractC14257d<WPDiscountListBean> {

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.j$g$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1543a extends TypeToken<WPResult<WPDiscountListBean>> {
        }

        public C1542g(AbstractC12375a abstractC12375a) {
            super(abstractC12375a, 1);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPDiscountListBean> wPResult) {
            try {
                super.mo10a(wPResult);
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6083r();
                }
            } catch (Exception unused) {
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPDiscountListBean> wPResult) {
            try {
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6119a(wPResult.getData());
                }
            } catch (Exception unused) {
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1543a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            try {
                super.mo11d(str);
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6083r();
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: c0.j$h */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1544h extends AbstractC14257d<WPOtherPayResultBean> {

        /* renamed from: c */
        public final /* synthetic */ String f2572c;

        /* renamed from: d */
        public final /* synthetic */ String f2573d;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.j$h$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1545a extends TypeToken<WPResult<WPOtherPayResultBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1544h(AbstractC12375a abstractC12375a, String str, String str2) {
            super(abstractC12375a);
            this.f2572c = str;
            this.f2573d = str2;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPOtherPayResultBean> wPResult) {
            try {
                if ("406114056".equals(wPResult.getCode())) {
                    C1529j.this.m22172b(this.f2572c);
                } else {
                    super.mo10a(wPResult);
                }
            } catch (Exception unused) {
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPOtherPayResultBean> wPResult) {
            try {
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6105a(this.f2573d, wPResult.getData());
                }
            } catch (Exception unused) {
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1545a().getType();
        }
    }

    /* renamed from: c0.j$i */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C1546i extends AbstractC14257d<WPUnionPayResultBean> {

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: c0.j$i$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        class C1547a extends TypeToken<WPResult<WPUnionPayResultBean>> {
        }

        public C1546i(AbstractC12375a abstractC12375a) {
            super(abstractC12375a);
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPUnionPayResultBean> wPResult) {
            try {
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6124a(wPResult);
                }
            } catch (Exception unused) {
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPUnionPayResultBean> wPResult) {
            try {
                if (C1529j.this.m1795b()) {
                    ((InterfaceC1470d) C1529j.this.m1798a()).mo6124a(wPResult);
                }
            } catch (Exception unused) {
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C1547a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            try {
                m27a();
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public final void m22179a(String str) {
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27822l, C13707b.m126a("tradeOrderNo", str, "recommendedScene", "PAY_DETAIL"), false, new C1532b(this)));
    }

    /* renamed from: b */
    public final void m22171b(String str, String str2, String str3, String str4, String str5) {
        HashMap m126a = C13707b.m126a("orderCache", str, "toolExpand", str2);
        m126a.put("discountLevel", str3);
        m126a.put("tradeOrderNo", str4);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27828r, m126a, false, new C1536d(this, str3, str5)));
    }

    /* renamed from: d */
    public final void m22170d() {
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27815e, new HashMap(), false, new C1534c(this)));
    }

    /* renamed from: a */
    public final void m22177a(String str, String str2, String str3, String str4) {
        HashMap m126a = C13707b.m126a("toolExpand", str, "orderCache", str4);
        m126a.put("tradeOrderNo", str3);
        m126a.put("isWXClient", "0");
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27791D, m126a, false, new C1544h(this, str3, str2)));
    }

    /* renamed from: b */
    public final void m22172b(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("tradeOrderNo", str);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27790C, hashMap, false, new C1546i(this)));
    }

    /* renamed from: a */
    public final void m22178a(String str, String str2, String str3) {
        HashMap m126a = C13707b.m126a("orderCache", str, "toolExpand", str2);
        m126a.put("tradeOrderNo", str3);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27831u, m126a, false, new C1540f(this, str3)));
    }

    /* renamed from: a */
    public final void m22173a(String str, String str2, String str3, String str4, boolean z, String str5) {
        HashMap m126a = C13707b.m126a("referUrl", str4, "bankUid", str2);
        m126a.put("tradeOrderNo", str3);
        m126a.put("sdkBackFrom", str);
        m126a.put("queryScene", str5);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27826p, m126a, false, new C1519e(this, this, z, str)));
    }

    /* renamed from: a */
    public final void m22175a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        HashMap m126a = C13707b.m126a("tradeOrderNo", str, "qpayUrl", str3);
        m126a.put("hmac", str2);
        m126a.put("cdkey", "");
        m126a.put("verifyCode", "");
        m126a.put("userClientUid", C10546a.C10576i.f20125a.f20059i);
        m126a.put("orderCache", str6);
        m126a.put("toolExpand", str7);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27808U, m126a, false, new C1530a(this, str2, str3)));
    }

    /* renamed from: a */
    public final void m22174a(String str, String str2, String str3, String str4, String str5, String str6, List<WPPayInfoUpdateBean> list) {
        HashMap m126a = C13707b.m126a("checked", str5, "discountExpand", str2);
        m126a.put("orderCache", str6);
        m126a.put("tradeOrderNo", str4);
        m126a.put("selectedToolExpand", str3);
        m126a.put("payInfoUpdates", list);
        m126a.put("discountLevel", str);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27830t, m126a, false, new C1542g(this)));
    }

    /* renamed from: a */
    public final void m22176a(String str, String str2, String str3, String str4, int i, int i2) {
        HashMap m126a = C13707b.m126a("fromToolExpand", str2, "orderCache", str);
        m126a.put("toolExpand", str3);
        m126a.put("tradeOrderNo", str4);
        m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27829s, m126a, false, new C1538e(this, i, i2)));
    }
}
