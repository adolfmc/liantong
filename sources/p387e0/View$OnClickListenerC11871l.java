package p387e0;

import android.text.TextUtils;
import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPFqInfoBean;
import com.unicom.pay.normal.order.bean.WPFqNumInfoBean;
import com.unicom.pay.normal.order.bean.WPUnionFqInfoBean;
import com.unicom.pay.normal.order.p359ui.WPInstalmentActivity;
import java.util.Iterator;
import p091c0.C1506b;
import p387e0.C11873n;
import p470p0.C13647j;

@NBSInstrumented
/* renamed from: e0.l */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC11871l implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ C11873n.C11874a f24172a;

    /* renamed from: b */
    public final /* synthetic */ WPFqInfoBean f24173b;

    /* renamed from: c */
    public final /* synthetic */ C11873n f24174c;

    public View$OnClickListenerC11871l(C11873n c11873n, C11873n.C11874a c11874a, WPFqInfoBean wPFqInfoBean) {
        this.f24174c = c11873n;
        this.f24172a = c11874a;
        this.f24173b = wPFqInfoBean;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        WPUnionFqInfoBean wPUnionFqInfoBean;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        C11873n c11873n = this.f24174c;
        if (!c11873n.f24182e) {
            NBSActionInstrumentation.onClickEventExit();
            return;
        }
        if (c11873n.f24180c != null) {
            C13647j.m180a(c11873n.f24178a, C10531R.C10533drawable.up_item_loading, this.f24172a.f24195m);
            if (this.f24173b.isJumpWeb()) {
                ((WPInstalmentActivity) this.f24174c.f24180c).m6144a(this.f24173b);
            } else {
                C11873n.InterfaceC11875b interfaceC11875b = this.f24174c.f24180c;
                WPFqInfoBean wPFqInfoBean = this.f24173b;
                WPInstalmentActivity wPInstalmentActivity = (WPInstalmentActivity) interfaceC11875b;
                if (wPInstalmentActivity.f20245P) {
                    if (wPFqInfoBean == null || (wPUnionFqInfoBean = wPInstalmentActivity.f20241L) == null || wPUnionFqInfoBean.getExtInfo() == null || TextUtils.isEmpty(wPInstalmentActivity.f20241L.getExtInfo().getJumpUrl())) {
                        if (wPFqInfoBean != null && wPInstalmentActivity.f20243N != null) {
                            if (wPFqInfoBean.getFqNumInfos() != null && !wPFqInfoBean.getFqNumInfos().isEmpty()) {
                                Iterator<WPFqNumInfoBean> it = wPFqInfoBean.getFqNumInfos().iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    WPFqNumInfoBean next = it.next();
                                    if (next.isDefaultChecked()) {
                                        ((C1506b) wPInstalmentActivity.f24311a).m22183a(next.getExpand(), wPInstalmentActivity.f20243N.getData().getTradeOrderNo());
                                        break;
                                    }
                                }
                            } else {
                                ((C1506b) wPInstalmentActivity.f24311a).m22183a(wPFqInfoBean.getExpand(), wPInstalmentActivity.f20243N.getData().getTradeOrderNo());
                            }
                        }
                        wPInstalmentActivity.m1991k("信用卡-优惠框");
                    } else if (WPFqInfoBean.WFQ_XYK_PWD.equals(wPFqInfoBean.getType())) {
                        wPInstalmentActivity.m6135e(wPFqInfoBean.getJumpUrl());
                    } else {
                        wPInstalmentActivity.m2085l(wPInstalmentActivity.f20241L.getExtInfo().getJumpUrl());
                    }
                }
            }
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
