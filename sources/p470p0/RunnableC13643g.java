package p470p0;

import android.app.Activity;
import android.text.TextUtils;
import com.gmrz.appsdk.FidoAppSDK;
import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoCallback;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.util.Constant;
import java.util.Map;
import p470p0.C13644h;

/* renamed from: p0.g */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class RunnableC13643g implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ Activity f27488a;

    /* renamed from: b */
    public final /* synthetic */ FidoCallback f27489b;

    /* renamed from: c */
    public final /* synthetic */ C13644h f27490c = C13644h.C13645a.f27491a;

    public RunnableC13643g(Activity activity, FidoCallback fidoCallback) {
        this.f27488a = activity;
        this.f27489b = fidoCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FidoReInfo fidoReInfo = new FidoReInfo();
        fidoReInfo.setFpStatus(FidoStatus.FAILED);
        fidoReInfo.setIrisStatus(FidoStatus.FAILED);
        fidoReInfo.setFidoFaceStatus(FidoStatus.FAILED);
        fidoReInfo.setFidoGestureStatus(FidoStatus.FAILED);
        for (Map.Entry<String, Map<Constant.SecurityLevel, Constant.SecurityLevelInfo>> entry : FidoAppSDK.getInstance().getSecurityLevel(this.f27488a).entrySet()) {
            String key = entry.getKey();
            if (TextUtils.equals("IRIS", key) && C13644h.m183a(this.f27490c, entry)) {
                fidoReInfo.setIrisStatus(FidoStatus.SUCCESS);
            }
            if (TextUtils.equals("FACE", key) && C13644h.m183a(this.f27490c, entry)) {
                fidoReInfo.setFidoFaceStatus(FidoStatus.SUCCESS);
            }
            if (TextUtils.equals("GESTURE", key) && C13644h.m183a(this.f27490c, entry)) {
                fidoReInfo.setFidoGestureStatus(FidoStatus.SUCCESS);
            }
            if (TextUtils.equals("FINGERPRINT", key) && C13644h.m183a(this.f27490c, entry)) {
                fidoReInfo.setFpStatus(FidoStatus.SUCCESS);
            }
        }
        FidoCallback fidoCallback = this.f27489b;
        if (fidoCallback != null) {
            fidoCallback.onFidoProcess(fidoReInfo);
        }
    }
}
