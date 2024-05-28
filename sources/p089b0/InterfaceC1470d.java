package p089b0;

import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.result.bean.WPUserStatusBean;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import com.unicom.pay.modules.verify.bean.WPQPayResultBean;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import com.unicom.pay.normal.discount.bean.WPDiscountListBean;
import com.unicom.pay.normal.order.bean.WPDiscountQueryBean;
import com.unicom.pay.normal.order.bean.WPNoticeListInfoBean;
import com.unicom.pay.normal.order.bean.WPOrderPayBeforeBean;
import com.unicom.pay.normal.order.bean.WPOtherPayResultBean;
import com.unicom.pay.normal.order.bean.WPPayBeforeBean;
import com.unicom.pay.normal.order.bean.WPQueryPayInfoListBean;
import com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean;
import com.unicom.pay.normal.order.bean.WPUpdateMethodResultBean;
import com.unicom.pay.qpay.open.bean.WPAgreementBean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: b0.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface InterfaceC1470d extends InterfaceC1472f {
    /* renamed from: F */
    void mo6130F();

    /* renamed from: K */
    void mo6129K();

    /* renamed from: S */
    void mo6128S();

    /* renamed from: a */
    void mo6126a(int i, int i2);

    /* renamed from: a */
    void mo6124a(WPResult<WPUnionPayResultBean> wPResult);

    /* renamed from: a */
    void mo6123a(WPUserStatusBean wPUserStatusBean);

    /* renamed from: a */
    void mo6122a(WPQOpenResultBean wPQOpenResultBean);

    /* renamed from: a */
    void mo6121a(WPQOpenResultBean wPQOpenResultBean, boolean z);

    /* renamed from: a */
    void mo6120a(WPQPayResultBean wPQPayResultBean, String str, String str2);

    /* renamed from: a */
    void mo6119a(WPDiscountListBean wPDiscountListBean);

    /* renamed from: a */
    void mo6118a(WPDiscountQueryBean wPDiscountQueryBean, String str, String str2);

    /* renamed from: a */
    void mo6117a(WPNoticeListInfoBean wPNoticeListInfoBean);

    /* renamed from: a */
    void mo6116a(WPPayBeforeBean wPPayBeforeBean);

    /* renamed from: a */
    void mo6111a(WPQueryPayInfoListBean wPQueryPayInfoListBean, int i);

    /* renamed from: a */
    void mo6109a(WPUpdateMethodResultBean wPUpdateMethodResultBean, int i, int i2);

    /* renamed from: a */
    void mo6106a(WPAgreementBean wPAgreementBean);

    /* renamed from: a */
    void mo6105a(String str, WPOtherPayResultBean wPOtherPayResultBean);

    /* renamed from: a */
    void mo6104a(String str, String str2);

    /* renamed from: a */
    void mo6102a(String str, String str2, WPUnionOrderInfoBean wPUnionOrderInfoBean);

    /* renamed from: b */
    void mo6101b(int i);

    /* renamed from: b */
    void mo6100b(WPResult<WPOrderPayBeforeBean> wPResult);

    /* renamed from: b */
    void mo6099b(WPPayBeforeBean wPPayBeforeBean);

    /* renamed from: b */
    void mo6098b(String str, String str2);

    /* renamed from: c */
    void mo6097c(WPResult<WPUnionPayResultBean> wPResult);

    /* renamed from: d */
    void mo6095d(WPResult<WPUnionPayResultBean> wPResult);

    /* renamed from: d */
    void mo6094d(WPPayBeforeBean wPPayBeforeBean);

    /* renamed from: e */
    void mo6091e(WPPayBeforeBean wPPayBeforeBean);

    /* renamed from: e */
    void mo6090e(String str);

    /* renamed from: h */
    void mo6089h();

    /* renamed from: r */
    void mo6083r();

    /* renamed from: u */
    void mo6079u();
}
