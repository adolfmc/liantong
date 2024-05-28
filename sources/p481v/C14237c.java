package p481v;

import android.text.TextUtils;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.modules.verify.p357ui.WPValidatePayPassActivity;

/* renamed from: v.c */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14237c implements DataCallback {

    /* renamed from: a */
    public final /* synthetic */ WPValidatePayPassActivity f27734a;

    public C14237c(WPValidatePayPassActivity wPValidatePayPassActivity) {
        this.f27734a = wPValidatePayPassActivity;
    }

    @Override // com.unicom.pay.common.callback.DataCallback
    public final void onResult(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(this.f27734a, str);
    }
}
