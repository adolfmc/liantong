package p481v;

import android.os.CountDownTimer;
import android.widget.TextView;
import com.unicom.pay.C10531R;
import com.unicom.pay.modules.verify.p357ui.WPVerifyCodeActivity;

/* renamed from: v.d */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class CountDownTimerC14238d extends CountDownTimer {

    /* renamed from: a */
    public final /* synthetic */ WPVerifyCodeActivity f27735a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CountDownTimerC14238d(WPVerifyCodeActivity wPVerifyCodeActivity) {
        super(60000L, 1000L);
        this.f27735a = wPVerifyCodeActivity;
    }

    @Override // android.os.CountDownTimer
    public final void onFinish() {
        TextView textView = this.f27735a.f20185m;
        if (textView == null) {
            return;
        }
        textView.setEnabled(true);
        WPVerifyCodeActivity wPVerifyCodeActivity = this.f27735a;
        wPVerifyCodeActivity.f20185m.setTextColor(wPVerifyCodeActivity.getResources().getColor(C10531R.C10532color.wp_protocol_color));
        this.f27735a.f20185m.setText(C10531R.string.wp_comm_verify_reset);
    }

    @Override // android.os.CountDownTimer
    public final void onTick(long j) {
        TextView textView = this.f27735a.f20185m;
        if (textView == null) {
            return;
        }
        textView.setEnabled(false);
        WPVerifyCodeActivity wPVerifyCodeActivity = this.f27735a;
        wPVerifyCodeActivity.f20185m.setTextColor(wPVerifyCodeActivity.getResources().getColor(C10531R.C10532color.wp_desc_text_color));
        WPVerifyCodeActivity wPVerifyCodeActivity2 = this.f27735a;
        wPVerifyCodeActivity2.f20185m.setText(wPVerifyCodeActivity2.getString(C10531R.string.wp_sms_verify_time, Integer.valueOf((int) (j / 1000))));
    }
}
