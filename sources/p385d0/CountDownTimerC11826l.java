package p385d0;

import android.os.CountDownTimer;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.p359ui.WPUpMobileLoginActivity;

/* renamed from: d0.l */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CountDownTimerC11826l extends CountDownTimer {

    /* renamed from: a */
    public final /* synthetic */ WPUpMobileLoginActivity f24088a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CountDownTimerC11826l(WPUpMobileLoginActivity wPUpMobileLoginActivity) {
        super(60000L, 1000L);
        this.f24088a = wPUpMobileLoginActivity;
    }

    @Override // android.os.CountDownTimer
    public final void onFinish() {
        this.f24088a.f20357r.setEnabled(true);
        WPUpMobileLoginActivity wPUpMobileLoginActivity = this.f24088a;
        wPUpMobileLoginActivity.f20357r.setTextColor(wPUpMobileLoginActivity.getResources().getColor(C10531R.C10532color.up_hyperlink_or_protocol_text_color));
        this.f24088a.f20357r.setText(C10531R.string.wp_comm_verify_reset);
    }

    @Override // android.os.CountDownTimer
    public final void onTick(long j) {
        this.f24088a.f20357r.setEnabled(false);
        WPUpMobileLoginActivity wPUpMobileLoginActivity = this.f24088a;
        wPUpMobileLoginActivity.f20357r.setTextColor(wPUpMobileLoginActivity.getResources().getColor(C10531R.C10532color.up_auxiliary_description_text_color));
        WPUpMobileLoginActivity wPUpMobileLoginActivity2 = this.f24088a;
        wPUpMobileLoginActivity2.f20357r.setText(wPUpMobileLoginActivity2.getString(C10531R.string.wp_sms_verify_time, Integer.valueOf((int) (j / 1000))));
    }
}
