package p485z;

import android.os.CountDownTimer;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.bank.p358ui.WPAuthCreditBankActivity;

/* renamed from: z.a */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class CountDownTimerC14285a extends CountDownTimer {

    /* renamed from: a */
    public final /* synthetic */ WPAuthCreditBankActivity f27840a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CountDownTimerC14285a(WPAuthCreditBankActivity wPAuthCreditBankActivity) {
        super(60000L, 1000L);
        this.f27840a = wPAuthCreditBankActivity;
    }

    @Override // android.os.CountDownTimer
    public final void onFinish() {
        this.f27840a.f20208q.setEnabled(true);
        WPAuthCreditBankActivity wPAuthCreditBankActivity = this.f27840a;
        wPAuthCreditBankActivity.f20208q.setTextColor(wPAuthCreditBankActivity.getResources().getColor(C10531R.C10532color.up_hyperlink_or_protocol_text_color));
        this.f27840a.f20208q.setText(C10531R.string.up_login_get_code);
    }

    @Override // android.os.CountDownTimer
    public final void onTick(long j) {
        this.f27840a.f20208q.setEnabled(false);
        WPAuthCreditBankActivity wPAuthCreditBankActivity = this.f27840a;
        wPAuthCreditBankActivity.f20208q.setTextColor(wPAuthCreditBankActivity.getResources().getColor(C10531R.C10532color.up_auxiliary_description_text_color));
        WPAuthCreditBankActivity wPAuthCreditBankActivity2 = this.f27840a;
        wPAuthCreditBankActivity2.f20208q.setText(wPAuthCreditBankActivity2.getString(C10531R.string.wp_sms_verify_time, Integer.valueOf((int) (j / 1000))));
    }
}
