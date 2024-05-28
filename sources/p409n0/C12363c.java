package p409n0;

import android.text.TextUtils;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.qpay.setting.p361ui.WPQPaySettingActivity;

/* renamed from: n0.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12363c implements DataCallback {

    /* renamed from: a */
    public final /* synthetic */ WPQPaySettingActivity f25022a;

    public C12363c(WPQPaySettingActivity wPQPaySettingActivity) {
        this.f25022a = wPQPaySettingActivity;
    }

    @Override // com.unicom.pay.common.callback.DataCallback
    public final void onResult(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(this.f25022a, str);
    }
}
