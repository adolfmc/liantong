package p385d0;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.normal.order.bean.WPAgreementMsg;
import com.unicom.pay.normal.order.p359ui.WPUpMobileLoginActivity;

@NBSInstrumented
/* renamed from: d0.m */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11827m extends ClickableSpan {

    /* renamed from: a */
    public final /* synthetic */ WPAgreementMsg f24089a;

    /* renamed from: b */
    public final /* synthetic */ WPUpMobileLoginActivity f24090b;

    public C11827m(WPUpMobileLoginActivity wPUpMobileLoginActivity, WPAgreementMsg wPAgreementMsg) {
        this.f24090b = wPUpMobileLoginActivity;
        this.f24089a = wPAgreementMsg;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        ((TextView) view).setHighlightColor(this.f24090b.getResources().getColor(C10531R.C10532color.wp_transparent));
        UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(this.f24090b, this.f24089a.getUrl());
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        textPaint.bgColor = this.f24090b.getResources().getColor(C10531R.C10532color.wp_transparent);
        textPaint.setColor(this.f24090b.getResources().getColor(C10531R.C10532color.up_hyperlink_or_protocol_text_color));
        textPaint.setUnderlineText(false);
    }
}
