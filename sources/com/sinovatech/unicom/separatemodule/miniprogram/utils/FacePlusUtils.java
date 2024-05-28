package com.sinovatech.unicom.separatemodule.miniprogram.utils;

import android.text.TextUtils;
import com.megvii.livenesslib.LivenessActivity;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FacePlusUtils {
    private static Disposable disposable = null;
    public static String jsonString = "";
    public static long longtime;

    public static void getCode() {
        new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.utils.FacePlusUtils.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    FacePlusUtils.longtime = System.currentTimeMillis();
                    App.getAsyncHttpClient().rxGet(URLSet.getImageCode(), null).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.utils.FacePlusUtils.1.1
                        @Override // io.reactivex.Observer
                        public void onComplete() {
                        }

                        @Override // io.reactivex.Observer
                        public void onSubscribe(@NonNull Disposable disposable2) {
                            Disposable unused = FacePlusUtils.disposable = disposable2;
                        }

                        @Override // io.reactivex.Observer
                        public void onNext(@NonNull String str) {
                            FacePlusUtils.longtime = System.currentTimeMillis() - FacePlusUtils.longtime;
                            try {
                                if (TextUtils.isEmpty(str)) {
                                    return;
                                }
                                JSONObject jSONObject = new JSONObject(str);
                                String optString = jSONObject.optString("respCode");
                                FacePlusUtils.jsonString = str;
                                if (TextUtils.isEmpty(optString) || !"0000".equals(optString)) {
                                    return;
                                }
                                String optString2 = jSONObject.optString("respData");
                                LivenessActivity.faceProCid = jSONObject.optString("faceProcId");
                                LivenessActivity.imageCode = optString2;
                            } catch (Exception e) {
                                MsLogUtil.m7979d("天盾code异常:", e.getMessage());
                                FacePlusUtils.cancleGetCode();
                                FacePlusUtils.jsonString = "";
                                FacePlusUtils.longtime = 0L;
                            }
                        }

                        @Override // io.reactivex.Observer
                        public void onError(@NonNull Throwable th) {
                            MsLogUtil.m7977e("天盾code异常:", th.getMessage());
                            FacePlusUtils.cancleGetCode();
                            FacePlusUtils.jsonString = "";
                            FacePlusUtils.longtime = 0L;
                        }
                    });
                } catch (Exception e) {
                    FacePlusUtils.jsonString = "";
                    FacePlusUtils.longtime = 0L;
                    MsLogUtil.m7977e("天盾code异常:", e.getMessage());
                    FacePlusUtils.cancleGetCode();
                }
            }
        }).start();
    }

    public static void cancleGetCode() {
        Disposable disposable2 = disposable;
        if (disposable2 != null && !disposable2.isDisposed()) {
            disposable.dispose();
        }
        LivenessActivity.faceProCid = "";
        LivenessActivity.imageCode = "";
    }
}
