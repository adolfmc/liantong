package com.unicom.pay.normal.order.p359ui;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import p393h.AbstractView$OnClickListenerC12011e;
import p411o.AbstractC12375a;

@NBSInstrumented
/* renamed from: com.unicom.pay.normal.order.ui.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10679a extends AbstractView$OnClickListenerC12011e {

    /* renamed from: n */
    public static final /* synthetic */ int f20369n = 0;

    /* renamed from: g */
    public TextView f20370g;

    /* renamed from: h */
    public TextView f20371h;

    /* renamed from: i */
    public TextView f20372i;

    /* renamed from: j */
    public TextView f20373j;

    /* renamed from: k */
    public InterfaceC10680a f20374k;

    /* renamed from: l */
    public String f20375l;

    /* renamed from: m */
    public String f20376m;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10680a {
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "添加银行卡弹窗";
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "wp175";
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a */
    public final void mo5a(Dialog dialog) {
        this.f20370g = (TextView) dialog.findViewById(C10531R.C10534id.wopay_bank_title_tv);
        this.f20371h = (TextView) dialog.findViewById(C10531R.C10534id.wopay_bank_content_tv);
        this.f20372i = (TextView) dialog.findViewById(C10531R.C10534id.wopay_bank_confirm_tv);
        this.f20373j = (TextView) dialog.findViewById(C10531R.C10534id.wopay_bank_cancel_tv);
        ((ImageView) dialog.findViewById(C10531R.C10534id.wopay_half_close_iv)).setOnClickListener(this);
        this.f20372i.setOnClickListener(this);
        this.f20373j.setOnClickListener(this);
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a0 */
    public final int mo4a0() {
        return C10531R.C10535layout.wp_add_bank_dialog;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: c0 */
    public final float mo3c0() {
        return 0.0f;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: d0 */
    public final void mo2d0() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        int i = arguments.getInt("title", 0);
        int i2 = arguments.getInt("desc", 0);
        int i3 = arguments.getInt("cancel", 0);
        int i4 = arguments.getInt("confirm", 0);
        if (i != 0) {
            this.f20370g.setText(i);
        }
        if (i2 != 0) {
            this.f20371h.setText(i2);
        }
        if (i3 != 0) {
            this.f20373j.setText(i3);
        }
        if (i4 != 0) {
            this.f20372i.setText(i4);
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: f0 */
    public final float mo1f0() {
        return 0.8f;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: m */
    public final AbstractC12375a mo0m() {
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0075, code lost:
        if (r7.getId() == com.unicom.pay.C10531R.C10534id.wopay_half_close_iv) goto L14;
     */
    @Override // p393h.AbstractView$OnClickListenerC12011e, android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onClick(android.view.View r7) {
        /*
            r6 = this;
            com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation.onClickEventEnter(r7, r6)
            int r0 = r7.getId()
            int r1 = com.unicom.pay.C10531R.C10534id.wopay_bank_cancel_tv
            if (r0 != r1) goto L53
            java.lang.String r7 = "点击-添卡享优惠弹窗-添加银行卡"
            r6.m1984f(r7)
            com.unicom.pay.normal.order.ui.a$a r7 = r6.f20374k
            if (r7 == 0) goto L77
            java.lang.String r3 = r6.f20376m
            com.unicom.pay.normal.order.ui.WPOrderActivity$i r7 = (com.unicom.pay.normal.order.p359ui.WPOrderActivity.C10664i) r7
            com.unicom.pay.normal.order.ui.WPOrderActivity r7 = com.unicom.pay.normal.order.p359ui.WPOrderActivity.this
            int r0 = com.unicom.pay.normal.order.p359ui.WPOrderActivity.f20256N0
            com.unicom.pay.normal.order.bean.WPPayInfoBean r0 = r7.m6084q0()
            if (r0 == 0) goto L77
            com.unicom.pay.normal.order.bean.WPDefaultOrderInfoBean r1 = r7.f20315s0
            if (r1 == 0) goto L77
            com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean r1 = r7.f20313r0
            if (r1 == 0) goto L77
            com.unicom.pay.normal.order.bean.WPOrderInfoBean r1 = r1.getOrderInfo()
            if (r1 == 0) goto L77
            P extends o.a r1 = r7.f24311a
            c0.j r1 = (p091c0.C1529j) r1
            java.lang.String r2 = r7.mo6077x()
            java.lang.String r4 = r0.getToolExpand()
            com.unicom.pay.normal.order.bean.WPDefaultOrderInfoBean r7 = r7.f20315s0
            com.unicom.pay.normal.order.bean.WPDefaultOrderDataBean r7 = r7.getData()
            java.lang.String r7 = r7.getTradeOrderNo()
            java.lang.String r5 = r0.getToolCode()
            r0 = r1
            r1 = r2
            r2 = r4
            r4 = r7
            r0.m22171b(r1, r2, r3, r4, r5)
            goto L77
        L53:
            int r0 = r7.getId()
            int r1 = com.unicom.pay.C10531R.C10534id.wopay_bank_confirm_tv
            if (r0 != r1) goto L6f
            java.lang.String r7 = "点击-添卡享优惠弹窗-重新选择优惠"
            r6.m1984f(r7)
            com.unicom.pay.normal.order.ui.a$a r7 = r6.f20374k
            if (r7 == 0) goto L77
            java.lang.String r0 = r6.f20375l
            com.unicom.pay.normal.order.ui.WPOrderActivity$i r7 = (com.unicom.pay.normal.order.p359ui.WPOrderActivity.C10664i) r7
            com.unicom.pay.normal.order.ui.WPOrderActivity r7 = com.unicom.pay.normal.order.p359ui.WPOrderActivity.this
            r7.m2085l(r0)
            goto L77
        L6f:
            int r7 = r7.getId()
            int r0 = com.unicom.pay.C10531R.C10534id.wopay_half_close_iv
            if (r7 != r0) goto L7a
        L77:
            r6.dismissAllowingStateLoss()
        L7a:
            com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation.onClickEventExit()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.normal.order.p359ui.C10679a.onClick(android.view.View):void");
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return "98U01170wp175";
    }
}
