package p481v;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import com.unicom.pay.widget.WPPassEditText;
import com.unicom.pay.widget.WPPayPwdResultView;
import p393h.AbstractView$OnClickListenerC12011e;
import p411o.AbstractC12375a;

@NBSInstrumented
/* renamed from: v.a */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14232a extends AbstractView$OnClickListenerC12011e {

    /* renamed from: g */
    public TextView f27721g;

    /* renamed from: h */
    public TextView f27722h;

    /* renamed from: i */
    public ImageView f27723i;

    /* renamed from: j */
    public WPPassEditText f27724j;

    /* renamed from: k */
    public TextView f27725k;

    /* renamed from: l */
    public TextView f27726l;

    /* renamed from: m */
    public WPPayPwdResultView f27727m;

    /* renamed from: n */
    public InterfaceC14235c f27728n;

    /* renamed from: o */
    public boolean f27729o = true;

    /* renamed from: p */
    public String f27730p;

    /* renamed from: v.a$a */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class ViewTreeObserver$OnGlobalLayoutListenerC14233a implements ViewTreeObserver.OnGlobalLayoutListener {
        public ViewTreeObserver$OnGlobalLayoutListenerC14233a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            WPPassEditText wPPassEditText = C14232a.this.f27724j;
            if (wPPassEditText == null) {
                return;
            }
            wPPassEditText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            C14232a.this.f27724j.StartPassGuardKeyBoard();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: v.a$b */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class RunnableC14234b implements Runnable {
        public RunnableC14234b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            C14232a.this.dismissAllowingStateLoss();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: v.a$c */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface InterfaceC14235c {
        /* renamed from: a */
        void mo59a();

        /* renamed from: b */
        void mo58b();
    }

    /* renamed from: h */
    public static C14232a m65h(String str) {
        C14232a c14232a = new C14232a();
        Bundle bundle = new Bundle();
        bundle.putString("sceneCode", str);
        c14232a.setArguments(bundle);
        return c14232a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public /* synthetic */ void m62j(String str) {
        WPPassEditText wPPassEditText;
        if (str.length() != 6 || !this.f27729o) {
            if (str.length() < 6) {
                this.f27729o = true;
                return;
            }
            return;
        }
        m1984f("密码弹窗-输入完6位密码");
        this.f27729o = false;
        if (this.f27728n == null || (wPPassEditText = this.f27724j) == null) {
            return;
        }
        wPPassEditText.StopPassGuardKeyBoard();
        this.f27728n.mo59a();
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return ("QPAY_SET_CLOSE".equals(this.f27730p) || "QPAY_LIMIT".equals(this.f27730p)) ? "身份确认-支付密码弹窗" : "支付密码弹窗";
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return ("QPAY_SET_CLOSE".equals(this.f27730p) || "QPAY_LIMIT".equals(this.f27730p)) ? "qp013" : "wp150";
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: Z */
    public final int mo52Z() {
        return 0;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a */
    public final void mo5a(Dialog dialog) {
        dialog.getWindow().setWindowAnimations(C10531R.C10537style.WPDialogAnimation);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f27730p = arguments.getString("sceneCode", "");
        }
        dialog.setCanceledOnTouchOutside(false);
        this.f27721g = (TextView) dialog.findViewById(C10531R.C10534id.wopay_half_title_tv);
        this.f27722h = (TextView) dialog.findViewById(C10531R.C10534id.wopay_pay_dialog_desc_tv);
        if ("QPAY_SET_CLOSE".equals(this.f27730p) || "QPAY_LIMIT".equals(this.f27730p)) {
            this.f27721g.setText(getString(C10531R.string.wp_pay_pwd_title));
            this.f27722h.setVisibility(0);
        } else {
            this.f27721g.setText(getString(C10531R.string.wp_pay_input_pwd_title));
            this.f27722h.setVisibility(8);
        }
        ImageView imageView = (ImageView) dialog.findViewById(C10531R.C10534id.wopay_half_close_iv);
        this.f27723i = imageView;
        imageView.setOnClickListener(this);
        this.f27724j = (WPPassEditText) dialog.findViewById(C10531R.C10534id.up_pop_six_pass_edt);
        TextView textView = (TextView) dialog.findViewById(C10531R.C10534id.up_pop_forget_tv);
        this.f27725k = textView;
        textView.setOnClickListener(this);
        this.f27726l = (TextView) dialog.findViewById(C10531R.C10534id.wopay_account_validate_pay_pass_auxiliary_tip_tv);
        this.f27727m = (WPPayPwdResultView) dialog.findViewById(C10531R.C10534id.up_pop_pay_result);
        m61j0();
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a0 */
    public final int mo4a0() {
        return C10531R.C10535layout.up_paypass_pop;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: b0 */
    public final int mo44b0() {
        return C10531R.C10537style.WPAdjustNothingDialog;
    }

    /* renamed from: c */
    public final String m68c(WPGmKeyBean wPGmKeyBean) {
        WPPassEditText wPPassEditText = this.f27724j;
        if (wPPassEditText == null) {
            return "";
        }
        wPPassEditText.setEccKey(wPGmKeyBean.getSm2key());
        this.f27724j.setCipherKey(wPGmKeyBean.getCipherKey());
        return this.f27724j.getSM2SM4CipherText() == null ? "" : this.f27724j.getSM2SM4CipherText();
    }

    /* renamed from: c */
    public final void m69c() {
        TextView textView = this.f27725k;
        if (textView != null) {
            textView.setEnabled(false);
        }
        WPPassEditText wPPassEditText = this.f27724j;
        if (wPPassEditText != null) {
            wPPassEditText.setEnabled(false);
        }
        ImageView imageView = this.f27723i;
        if (imageView != null) {
            imageView.setEnabled(false);
        }
        m60k("");
        WPPayPwdResultView wPPayPwdResultView = this.f27727m;
        if (wPPayPwdResultView != null) {
            wPPayPwdResultView.setVisibility(0);
            this.f27727m.setState(0);
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: c0 */
    public final float mo3c0() {
        return 0.7f;
    }

    /* renamed from: d */
    public final void m67d() {
        TextView textView = this.f27725k;
        if (textView != null) {
            textView.setEnabled(true);
        }
        WPPassEditText wPPassEditText = this.f27724j;
        if (wPPassEditText != null) {
            wPPassEditText.setEnabled(true);
        }
        ImageView imageView = this.f27723i;
        if (imageView != null) {
            imageView.setEnabled(true);
        }
        WPPassEditText wPPassEditText2 = this.f27724j;
        if (wPPassEditText2 == null) {
            return;
        }
        wPPassEditText2.postDelayed(new RunnableC14234b(), 1000L);
        WPPayPwdResultView wPPayPwdResultView = this.f27727m;
        if (wPPayPwdResultView != null) {
            wPPayPwdResultView.m6046b();
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: d0 */
    public final void mo2d0() {
    }

    /* renamed from: g0 */
    public final void m66g0() {
        this.f27729o = true;
        WPPassEditText wPPassEditText = this.f27724j;
        if (wPPassEditText == null) {
            return;
        }
        wPPassEditText.clear();
    }

    /* renamed from: h0 */
    public final void m64h0() {
        TextView textView = this.f27725k;
        if (textView != null) {
            textView.setEnabled(true);
        }
        WPPassEditText wPPassEditText = this.f27724j;
        if (wPPassEditText != null) {
            wPPassEditText.setEnabled(true);
        }
        ImageView imageView = this.f27723i;
        if (imageView != null) {
            imageView.setEnabled(true);
        }
        WPPayPwdResultView wPPayPwdResultView = this.f27727m;
        if (wPPayPwdResultView != null) {
            wPPayPwdResultView.setVisibility(8);
            this.f27727m.m6050a();
        }
    }

    /* renamed from: i0 */
    public final void m63i0() {
        WPPassEditText wPPassEditText = this.f27724j;
        if (wPPassEditText != null) {
            wPPassEditText.StartPassGuardKeyBoard();
        }
    }

    /* renamed from: j0 */
    public final void m61j0() {
        WPPassEditText wPPassEditText = this.f27724j;
        if (wPPassEditText == null) {
            return;
        }
        wPPassEditText.setVisibility(0);
        this.f27724j.m6051c();
        this.f27724j.setOnTextChangedListener(new WPPassEditText.InterfaceC10713b() { // from class: v.-$$Lambda$a$WcoQRZC9G8tIJyWnlGLa5l9DWis
            @Override // com.unicom.pay.widget.WPPassEditText.InterfaceC10713b
            /* renamed from: a */
            public final void mo57a(String str) {
                C14232a.this.m62j(str);
            }
        });
        this.f27724j.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver$OnGlobalLayoutListenerC14233a());
    }

    /* renamed from: k */
    public final void m60k(String str) {
        TextView textView = this.f27726l;
        if (textView != null) {
            textView.setText(str);
        }
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: m */
    public final AbstractC12375a mo0m() {
        return null;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (view.getId() == C10531R.C10534id.up_pop_forget_tv) {
            m1984f("点击-密码弹窗-忘记密码");
            WPPassEditText wPPassEditText = this.f27724j;
            if (wPPassEditText != null) {
                wPPassEditText.clear();
            }
            InterfaceC14235c interfaceC14235c = this.f27728n;
            if (interfaceC14235c != null) {
                interfaceC14235c.mo58b();
            }
            dismissAllowingStateLoss();
        } else if (view.getId() == C10531R.C10534id.wopay_half_close_iv) {
            m1984f("点击-密码弹窗-X");
            WPPassEditText wPPassEditText2 = this.f27724j;
            if (wPPassEditText2 != null) {
                wPPassEditText2.StopPassGuardKeyBoard();
            }
            dismissAllowingStateLoss();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return ("QPAY_SET_CLOSE".equals(this.f27730p) || "QPAY_LIMIT".equals(this.f27730p)) ? "98U01170qp013" : "98U01170wp150";
    }
}
