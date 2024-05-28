package com.megvii.lv5;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import com.megvii.lv5.sdk.C5559R;
import com.megvii.lv5.sdk.detect.guide.GrantActivity;
import com.megvii.lv5.sdk.detect.guide.UserAgreementActivity;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.k0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class View$OnTouchListenerC5487k0 implements View.OnTouchListener {

    /* renamed from: a */
    public final /* synthetic */ GrantActivity f12836a;

    public View$OnTouchListenerC5487k0(GrantActivity grantActivity) {
        this.f12836a = grantActivity;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            GrantActivity grantActivity = this.f12836a;
            grantActivity.f13488f.setTextColor(grantActivity.getResources().getColor(C5667z2.m12879a(this.f12836a).m12878a(this.f12836a.getResources().getString(C5559R.string.key_liveness_home_agreementpage_bottom_button_after_click_color))));
            this.f12836a.f13488f.getPaint().setUnderlineText(true);
        } else if (action == 1) {
            GrantActivity grantActivity2 = this.f12836a;
            grantActivity2.f13488f.setTextColor(grantActivity2.getResources().getColor(C5667z2.m12879a(this.f12836a).m12878a(this.f12836a.getResources().getString(C5559R.string.key_liveness_home_agreementpage_bottom_button_before_click_color))));
            this.f12836a.f13488f.getPaint().setUnderlineText(false);
            GrantActivity grantActivity3 = this.f12836a;
            String str = grantActivity3.f13480C;
            int i = UserAgreementActivity.f13509g;
            Intent intent = new Intent(grantActivity3, UserAgreementActivity.class);
            intent.putExtra("link_type", 0);
            intent.putExtra("language", str);
            grantActivity3.startActivity(intent);
        }
        return true;
    }
}
