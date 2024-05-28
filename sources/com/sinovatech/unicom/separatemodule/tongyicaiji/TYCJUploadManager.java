package com.sinovatech.unicom.separatemodule.tongyicaiji;

import android.text.TextUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.ZipManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TYCJUploadManager {
    private static boolean isUploading;
    private static Disposable tycjIntervalObserver;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$upoloadTongyicaiji$0(String str) throws Exception {
        return str;
    }

    public static synchronized void upoloadTongyicaiji() {
        synchronized (TYCJUploadManager.class) {
            try {
            } catch (Exception e) {
                isUploading = false;
                e.printStackTrace();
            }
            if (isUploading) {
                return;
            }
            MsLogUtil.m7979d("shangchuan", "触发上传规则 再上传");
            boolean isNeedZip = TYCJConfigUtil.isNeedZip(TYCJBoxManager.logLength);
            HashMap hashMap = new HashMap();
            if (isNeedZip) {
                hashMap.put("uz", "1");
            } else {
                hashMap.put("uz", "0");
            }
            hashMap.put("udc", DeviceHelper.getDeviceID(true));
            hashMap.put("uve", App.getInstance().getString(2131886969));
            hashMap.put("ut", String.valueOf(System.currentTimeMillis()));
            final TYCJBoxManager tYCJBoxManager = TYCJBoxManager.getInstance();
            final HashMap<String, String> allData = tYCJBoxManager.getAllData();
            if (allData.size() == 0) {
                return;
            }
            AsyncHttpClient asyncHttpClient = App.getAsyncHttpClient();
            if (isNeedZip) {
                isUploading = true;
                ZipManager.getInstance(App.getInstance()).upLoadZipFile(ZipManager.getInstance(App.getInstance()).mapToJson(allData), hashMap, new ZipManager.ToZipInterFace() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJUploadManager.1
                    @Override // com.sinovatech.unicom.separatemodule.tongyicaiji.ZipManager.ToZipInterFace
                    public void upLoadSucess(String str) {
                        MsLogUtil.m7979d("zipupload", str);
                        TYCJUploadManager.handleSuccess(str, allData, tYCJBoxManager);
                    }

                    @Override // com.sinovatech.unicom.separatemodule.tongyicaiji.ZipManager.ToZipInterFace
                    public void fail(String str) {
                        TYCJUploadManager.handleFail();
                        MsLogUtil.m7979d("zipupload", str);
                    }
                });
            } else {
                isUploading = true;
                asyncHttpClient.rxPost(TYCJConfigUtil.getUpLoadUrl(), allData, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).subscribeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.-$$Lambda$TYCJUploadManager$SCpbYbb2AX5t9LEtXUQCo_zNmkA
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        return TYCJUploadManager.lambda$upoloadTongyicaiji$0((String) obj);
                    }
                }).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.-$$Lambda$TYCJUploadManager$CoYvyIa3DRqZ2K7ox-VPvYhGjbY
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        TYCJUploadManager.handleSuccess((String) obj, allData, tYCJBoxManager);
                    }
                }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.-$$Lambda$TYCJUploadManager$19cnmrt51j5ebV5ETiaxtCo9QoQ
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        Throwable th = (Throwable) obj;
                        TYCJUploadManager.handleFail();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleSuccess(String str, HashMap<String, String> hashMap, TYCJBoxManager tYCJBoxManager) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if ("0000".equals(jSONObject.optString("code"))) {
                for (String str2 : hashMap.keySet()) {
                    tYCJBoxManager.clearBoxData(str2);
                }
                TYInsertDataManager.getInstance().clearTYBox();
            }
            upDataConfig(jSONObject.optString("channelSerial"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        isUploading = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleFail() {
        isUploading = false;
    }

    public static void startInterval() {
        try {
            if (tycjIntervalObserver != null) {
                tycjIntervalObserver.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Observable.interval(TYCJConfigUtil.getUploadTimeInterval(), TYCJConfigUtil.getUploadTimeInterval(), TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJUploadManager.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                Disposable unused = TYCJUploadManager.tycjIntervalObserver = disposable;
            }

            @Override // io.reactivex.Observer
            public void onNext(Long l) {
                boolean unused = TYCJUploadManager.isUploading = false;
                TYCJUploadManager.upoloadTongyicaiji();
                MsLogUtil.m7979d("TYCJUploadManager", "统一采集定时器循环中");
            }
        });
    }

    public static void releaseInterval() {
        try {
            if (tycjIntervalObserver != null) {
                tycjIntervalObserver.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void upDataConfig(String str) {
        try {
            if (!TextUtils.isEmpty(str) && TYCJConfigUtil.isUpDataConfig(str)) {
                TYCJConfigUtil.requestTongyicaijiConfigDataAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
