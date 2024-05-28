package p485z;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import p393h.AbstractView$OnClickListenerC12011e;
import p411o.AbstractC12375a;

@NBSInstrumented
/* renamed from: z.b */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14286b extends AbstractView$OnClickListenerC12011e {

    /* renamed from: k */
    public static final /* synthetic */ int f27841k = 0;

    /* renamed from: g */
    public ImageView f27842g;

    /* renamed from: h */
    public TextView f27843h;

    /* renamed from: i */
    public TextView f27844i;

    /* renamed from: j */
    public TextView f27845j;

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a */
    public final void mo5a(Dialog dialog) {
        this.f27842g = (ImageView) dialog.findViewById(C10531R.C10534id.up_auth_credit_logo_iv);
        this.f27843h = (TextView) dialog.findViewById(C10531R.C10534id.up_auth_credit_title_tv);
        this.f27844i = (TextView) dialog.findViewById(C10531R.C10534id.up_auth_credit_desc_tv);
        TextView textView = (TextView) dialog.findViewById(C10531R.C10534id.up_auth_credit_confirm_tv);
        this.f27845j = textView;
        textView.setOnClickListener(this);
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a0 */
    public final int mo4a0() {
        return C10531R.C10535layout.up_auth_credit_tips;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: c0 */
    public final float mo3c0() {
        return 0.0f;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: d0 */
    public final void mo2d0() {
        TextView textView;
        int i;
        if ("1".equals(getArguments().getString("type", "1"))) {
            this.f27842g.setImageResource(C10531R.C10533drawable.up_bank_tips_front);
            this.f27843h.setText(C10531R.string.up_auth_credit_time_tips_title);
            textView = this.f27844i;
            i = C10531R.string.up_auth_credit_time_tips_desc;
        } else {
            this.f27842g.setImageResource(C10531R.C10533drawable.up_bank_tips_back);
            this.f27843h.setText(C10531R.string.up_auth_credit_cvv2_tips_title);
            textView = this.f27844i;
            i = C10531R.string.up_auth_credit_cvv2_tips_desc;
        }
        textView.setText(i);
        this.f27845j.setText(C10531R.string.up_auth_credit_tips_confirm);
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
        NBSActionInstrumentation.onClickEventEnter(view, this);
        if (view.getId() == C10531R.C10534id.up_auth_credit_confirm_tv) {
            dismissAllowingStateLoss();
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
