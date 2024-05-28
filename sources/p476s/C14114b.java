package p476s;

import android.text.TextUtils;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.modules.result.p356ui.WPPayResultActivity;

/* renamed from: s.b */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14114b implements DataCallback {

    /* renamed from: a */
    public final /* synthetic */ WPPayResultActivity f27651a;

    public C14114b(WPPayResultActivity wPPayResultActivity) {
        this.f27651a = wPPayResultActivity;
    }

    @Override // com.unicom.pay.common.callback.DataCallback
    public final void onResult(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(this.f27651a, str);
    }
}
