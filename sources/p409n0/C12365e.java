package p409n0;

import android.text.TextUtils;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.qpay.setting.p361ui.WPSettingLimitActivity;

/* renamed from: n0.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12365e implements DataCallback {

    /* renamed from: a */
    public final /* synthetic */ WPSettingLimitActivity f25024a;

    public C12365e(WPSettingLimitActivity wPSettingLimitActivity) {
        this.f25024a = wPSettingLimitActivity;
    }

    @Override // com.unicom.pay.common.callback.DataCallback
    public final void onResult(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(this.f25024a, str);
    }
}
