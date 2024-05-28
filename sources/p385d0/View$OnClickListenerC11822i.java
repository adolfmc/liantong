package p385d0;

import android.text.TextUtils;
import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.callback.NativeFunctionCallBack;
import com.unicom.pay.normal.order.bean.WPDefaultOrderInfoBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import java.util.HashMap;
import p091c0.C1523g;
import p091c0.C1529j;
import p470p0.C13647j;
import p482w.C14255c;
import p482w.C14262f;

@NBSInstrumented
/* renamed from: d0.i */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC11822i implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ WPOrderActivity f24082a;

    public View$OnClickListenerC11822i(WPOrderActivity wPOrderActivity) {
        this.f24082a = wPOrderActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        WPDefaultOrderInfoBean wPDefaultOrderInfoBean;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        try {
            wPDefaultOrderInfoBean = this.f24082a.f20315s0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wPDefaultOrderInfoBean != null && wPDefaultOrderInfoBean.getData() != null) {
            WPOrderActivity wPOrderActivity = this.f24082a;
            if (wPOrderActivity.f20325x0) {
                if (wPOrderActivity.f20313r0.getOrderDetailNotices() != null && !this.f24082a.f20313r0.getOrderDetailNotices().isEmpty() && this.f24082a.f20313r0.getOrderDetailNotices().get(0) != null) {
                    if ("h5".equals(this.f24082a.f20313r0.getOrderDetailNotices().get(0).getNoticeEventType())) {
                        if (!TextUtils.isEmpty(this.f24082a.f20313r0.getOrderDetailNotices().get(0).getNoticeH5Url())) {
                            NativeFunctionCallBack nativeFunctionCallback = UnicomPaySDK.getInstance().getNativeFunctionCallback();
                            WPOrderActivity wPOrderActivity2 = this.f24082a;
                            nativeFunctionCallback.openWebview(wPOrderActivity2, wPOrderActivity2.f20313r0.getOrderDetailNotices().get(0).getNoticeH5Url());
                        }
                    } else {
                        WPOrderActivity wPOrderActivity3 = this.f24082a;
                        C13647j.m180a(wPOrderActivity3, C10531R.C10533drawable.up_notice_loading, wPOrderActivity3.f20297b0);
                        WPOrderActivity wPOrderActivity4 = this.f24082a;
                        C1529j c1529j = (C1529j) wPOrderActivity4.f24311a;
                        String tradeOrderNo = wPOrderActivity4.f20315s0.getData().getTradeOrderNo();
                        c1529j.getClass();
                        HashMap hashMap = new HashMap();
                        hashMap.put("tradeOrderNo", tradeOrderNo);
                        c1529j.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27807T, hashMap, false, new C1523g(c1529j, c1529j)));
                    }
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                NBSActionInstrumentation.onClickEventExit();
                return;
            }
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
