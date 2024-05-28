package com.unicom.pay.normal.order.p359ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import p393h.AbstractView$OnClickListenerC12011e;
import p411o.AbstractC12375a;

@NBSInstrumented
/* renamed from: com.unicom.pay.normal.order.ui.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10681b extends AbstractView$OnClickListenerC12011e {

    /* renamed from: o */
    public static final /* synthetic */ int f20377o = 0;

    /* renamed from: g */
    public ImageView f20378g;

    /* renamed from: h */
    public TextView f20379h;

    /* renamed from: i */
    public TextView f20380i;

    /* renamed from: j */
    public TextView f20381j;

    /* renamed from: k */
    public TextView f20382k;

    /* renamed from: l */
    public int f20383l = 0;

    /* renamed from: m */
    public InterfaceC10682a f20384m;

    /* renamed from: n */
    public String f20385n;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.normal.order.ui.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10682a {
        /* renamed from: f */
        void mo6069f(String str);

        /* renamed from: h */
        void mo6068h(String str);

        /* renamed from: m */
        void mo6067m();
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return this.f20383l == 1 ? "设置支付密码弹窗" : "-";
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return this.f20383l == 1 ? "wp180" : "-";
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a */
    public final void mo5a(Dialog dialog) {
        this.f20378g = (ImageView) dialog.findViewById(C10531R.C10534id.wopay_improve_logo_iv);
        this.f20379h = (TextView) dialog.findViewById(C10531R.C10534id.wopay_improve_title_tv);
        this.f20380i = (TextView) dialog.findViewById(C10531R.C10534id.wopay_improve_content_tv);
        this.f20381j = (TextView) dialog.findViewById(C10531R.C10534id.wopay_improve_confirm_tv);
        this.f20382k = (TextView) dialog.findViewById(C10531R.C10534id.wopay_improve_cancel_tv);
        this.f20381j.setOnClickListener(this);
        this.f20382k.setOnClickListener(this);
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a0 */
    public final int mo4a0() {
        return C10531R.C10535layout.wp_improve_real_name_level;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: d0 */
    public final void mo2d0() {
        TextView textView;
        int i;
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        int i2 = arguments.getInt("type", 0);
        this.f20383l = i2;
        if (i2 == 1) {
            this.f20379h.setText(C10531R.string.wp_improve_title);
            this.f20378g.setImageResource(C10531R.C10533drawable.wp_improve_pwd);
            this.f20380i.setText(C10531R.string.wp_improve_pwd_content);
            this.f20381j.setText(C10531R.string.wp_improve_pwd_confirm);
            textView = this.f20382k;
            i = C10531R.string.wp_improve_cancel;
        } else {
            this.f20379h.setText(C10531R.string.up_bank_title);
            this.f20378g.setImageResource(C10531R.C10533drawable.wp_improve_real_name);
            this.f20380i.setText(C10531R.string.up_bank_content);
            this.f20381j.setText(C10531R.string.up_bank_confirm);
            textView = this.f20382k;
            i = C10531R.string.up_bank_cancel;
        }
        textView.setText(i);
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: e0 */
    public final boolean mo1985e0() {
        return false;
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

    @Override // p393h.AbstractView$OnClickListenerC12011e, android.view.View.OnClickListener
    public final void onClick(View view) {
        InterfaceC10682a interfaceC10682a;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (view.getId() != C10531R.C10534id.wopay_improve_cancel_tv) {
            if (view.getId() == C10531R.C10534id.wopay_improve_confirm_tv) {
                m1984f("点击-设置支付密码弹窗-设置密码");
                InterfaceC10682a interfaceC10682a2 = this.f20384m;
                if (interfaceC10682a2 != null) {
                    if (this.f20383l == 0) {
                        interfaceC10682a2.mo6068h(this.f20385n);
                    } else {
                        interfaceC10682a2.mo6069f(this.f20385n);
                    }
                }
            }
            NBSActionInstrumentation.onClickEventExit();
        }
        m1984f("点击-设置支付密码弹窗-放弃");
        if (this.f20383l == 0 && (interfaceC10682a = this.f20384m) != null) {
            interfaceC10682a.mo6067m();
        }
        dismissAllowingStateLoss();
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return this.f20383l == 1 ? "98U01170wp180" : "-";
    }
}
