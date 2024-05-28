package p394h0;

import android.text.TextUtils;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.qpay.open.p360ui.WPOpenQPayActivity;

/* renamed from: h0.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12019b implements DataCallback {

    /* renamed from: a */
    public final /* synthetic */ WPOpenQPayActivity f24359a;

    public C12019b(WPOpenQPayActivity wPOpenQPayActivity) {
        this.f24359a = wPOpenQPayActivity;
    }

    @Override // com.unicom.pay.common.callback.DataCallback
    public final void onResult(String str) {
        if (TextUtils.isEmpty(str) || UnicomPaySDK.getInstance().getNativeFunctionCallback() == null) {
            return;
        }
        UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(this.f24359a, str);
    }
}
