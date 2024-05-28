package com.sinovatech.unicom.separatemodule.push.huawei;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.push.HmsMessaging;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.push.PushManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HmsManager {
    private static HmsManager hmsManager;
    private final String TAG = "HmsManager";

    public static HmsManager getInstance() {
        if (hmsManager == null) {
            synchronized (HmsManager.class) {
                if (hmsManager == null) {
                    hmsManager = new HmsManager();
                }
            }
        }
        return hmsManager;
    }

    public String getHmsToken(final Context context) {
        if (DeviceHelper.getNeedHuawei()) {
            String string = App.getSharePreferenceUtil().getString("platformToken");
            try {
                if (TextUtils.isEmpty(string)) {
                    Observable.fromCallable(new Callable() { // from class: com.sinovatech.unicom.separatemodule.push.huawei.-$$Lambda$HmsManager$1j1YyEVTCwnmBHsmjEK2pZpzxIc
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            String token;
                            token = HmsInstanceId.getInstance(context).getToken("10059351", "HCM");
                            return token;
                        }
                    }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.push.huawei.-$$Lambda$HmsManager$gPK7ElrnbdcZF-8bH4vwaPpqhik
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            HmsManager.lambda$getHmsToken$1(HmsManager.this, (String) obj);
                        }
                    }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.push.huawei.-$$Lambda$HmsManager$qxFSMljRcInOeWDg-jBn44bXufc
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            HmsManager hmsManager2 = HmsManager.this;
                            Throwable th = (Throwable) obj;
                            MsLogUtil.m7979d("HmsManager", "获取推送token失败" + th.getMessage());
                        }
                    });
                }
            } catch (Exception e) {
                MsLogUtil.m7979d("HmsManager", "获取推送token失败" + e.getMessage());
            }
            MsLogUtil.m7979d("HmsManager", "华为token" + string);
            return string;
        }
        return "";
    }

    public static /* synthetic */ void lambda$getHmsToken$1(HmsManager hmsManager2, String str) throws Exception {
        PushManager.getInstance().setPushToken(str);
        MsLogUtil.m7979d("HmsManager", "华为token" + str);
    }

    public void enableReceiveNotifyMsg(boolean z, Context context) {
        if (z) {
            HmsMessaging.getInstance(context).turnOnPush().addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.sinovatech.unicom.separatemodule.push.huawei.HmsManager.1
                @Override // com.huawei.hmf.tasks.OnCompleteListener
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        MsLogUtil.m7979d("HmsManager", "turnOnPush Complete");
                        return;
                    }
                    MsLogUtil.m7979d("HmsManager", "turnOnPush failed: ret=" + task.getException().getMessage());
                }
            });
        } else {
            HmsMessaging.getInstance(context).turnOffPush().addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.sinovatech.unicom.separatemodule.push.huawei.HmsManager.2
                @Override // com.huawei.hmf.tasks.OnCompleteListener
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        MsLogUtil.m7979d("HmsManager", "turnOffPush Complete");
                        return;
                    }
                    MsLogUtil.m7979d("HmsManager", "turnOffPush failed: ret=" + task.getException().getMessage());
                }
            });
        }
    }
}
