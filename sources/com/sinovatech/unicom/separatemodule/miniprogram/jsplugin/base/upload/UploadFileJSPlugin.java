package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.cumphome.PrefetchCumpLauncher;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/uploadFile")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UploadFileJSPlugin extends BaseJSPlugin {
    private static Disposable uploadPageDispoasble;
    private Handler handler;
    private String lastUploadPageUrl = "";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("上传文件 onExec ");
            JSONObject jSONObject = this.parameterJO;
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            MsLogUtil.m7980d(sb.toString());
            this.handler = new Handler();
            String string = this.parameterJO.getString("type");
            String string2 = this.parameterJO.getString("uploadId");
            if ("startUpload".equals(string)) {
                String string3 = this.parameterJO.getString("url");
                String string4 = this.parameterJO.getString("filePath");
                String string5 = this.parameterJO.getString("name");
                String optString = this.parameterJO.optString("start");
                String optString2 = this.parameterJO.optString("sendLength");
                String optString3 = this.parameterJO.optString("timeOut");
                JSONObject optJSONObject = this.parameterJO.optJSONObject("header");
                JSONObject optJSONObject2 = this.parameterJO.optJSONObject("formData");
                Map<String, String> transformJSONObjectToMap = transformJSONObjectToMap(optJSONObject);
                Map<String, String> transformJSONObjectToMap2 = transformJSONObjectToMap(optJSONObject2);
                File file = new File(string4);
                if (!file.exists()) {
                    throw new RuntimeException("文件路径不存在 filePath=" + string4);
                }
                long length = file.length();
                long parseLong = TextUtils.isEmpty(optString) ? 0L : Long.parseLong(optString);
                if (!TextUtils.isEmpty(optString2)) {
                    length = Long.parseLong(optString2);
                }
                int parseInt = TextUtils.isEmpty(optString3) ? 2147483 : Integer.parseInt(optString3);
                FileUploadParams fileUploadParams = new FileUploadParams();
                fileUploadParams.setUploadId(string2);
                fileUploadParams.setUrl(string3);
                fileUploadParams.setFilePath(string4);
                fileUploadParams.setFileKeyName(string5);
                fileUploadParams.setUploadStartPosition(parseLong);
                fileUploadParams.setUploadLength(length);
                fileUploadParams.setTimeOut(parseInt);
                fileUploadParams.setHeader(transformJSONObjectToMap);
                fileUploadParams.setFormData(transformJSONObjectToMap2);
                FileUploadManager.getInstance().uploadFile(fileUploadParams, new FileUploadListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.UploadFileJSPlugin.1
                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.FileUploadListener
                    public void onComplete(final int i, final String str) {
                        UploadFileJSPlugin.this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.UploadFileJSPlugin.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MsLogUtil.m7980d("上传文件 onComplete " + i + " " + str);
                                try {
                                    JSONObject jSONObject2 = new JSONObject();
                                    JSONObject jSONObject3 = new JSONObject();
                                    jSONObject3.put("httpStatusCode", i);
                                    jSONObject3.put("response", str);
                                    jSONObject2.put("action", PrefetchCumpLauncher.PrefetchStatus_Complete);
                                    jSONObject2.put("data", jSONObject3);
                                    UploadFileJSPlugin.this.callbackSuccess(jSONObject2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.FileUploadListener
                    public void onError(final String str) {
                        MsLogUtil.m7978e("上传文件 onError " + str);
                        UploadFileJSPlugin.this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.UploadFileJSPlugin.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                UploadFileJSPlugin.this.callbackFail(str);
                            }
                        });
                    }

                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.FileUploadListener
                    public void onProgressUpdate(final long j, final long j2, final String str) {
                        UploadFileJSPlugin.this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.UploadFileJSPlugin.1.3
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    JSONObject jSONObject2 = new JSONObject();
                                    JSONObject jSONObject3 = new JSONObject();
                                    jSONObject3.put("totalBytesSent", j);
                                    jSONObject3.put("totalBytesExpectedToSend", j2);
                                    jSONObject3.put("speed", str);
                                    jSONObject2.put("action", "progressUpdate");
                                    jSONObject2.put("data", jSONObject3);
                                    UploadFileJSPlugin.this.callbackSuccess(jSONObject2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.FileUploadListener
                    public void onCancel() {
                        UploadFileJSPlugin.this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.UploadFileJSPlugin.1.4
                            @Override // java.lang.Runnable
                            public void run() {
                                MsLogUtil.m7980d("上传文件 onCancel");
                                try {
                                    JSONObject jSONObject2 = new JSONObject();
                                    JSONObject jSONObject3 = new JSONObject();
                                    jSONObject2.put("action", "cancel");
                                    jSONObject2.put("data", jSONObject3);
                                    UploadFileJSPlugin.this.callbackSuccess(jSONObject2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                this.webFragment.addLifeListener("/MsJSPlugin/uploadFile", new BaseWebFragment.LifeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.UploadFileJSPlugin.2
                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment.LifeListener
                    public void onDestory() {
                        try {
                            if (UploadFileJSPlugin.uploadPageDispoasble != null && !UploadFileJSPlugin.uploadPageDispoasble.isDisposed()) {
                                UploadFileJSPlugin.uploadPageDispoasble.dispose();
                                Disposable unused = UploadFileJSPlugin.uploadPageDispoasble = null;
                            }
                            FileUploadManager.getInstance().stopAllUpload();
                        } catch (Exception e) {
                            e.printStackTrace();
                            MsLogUtil.m7978e(e.getMessage());
                        }
                    }
                });
                this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.UploadFileJSPlugin.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            UploadFileJSPlugin.this.lastUploadPageUrl = new URL(UploadFileJSPlugin.this.f18589wv.getUrl()).getHost();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (uploadPageDispoasble == null) {
                    Observable.interval(1L, TimeUnit.SECONDS).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.UploadFileJSPlugin.4
                        @Override // io.reactivex.Observer
                        public void onComplete() {
                        }

                        @Override // io.reactivex.Observer
                        public void onSubscribe(@NonNull Disposable disposable) {
                            Disposable unused = UploadFileJSPlugin.uploadPageDispoasble = disposable;
                        }

                        @Override // io.reactivex.Observer
                        public void onNext(@NonNull Long l) {
                            try {
                                String host = new URL(UploadFileJSPlugin.this.f18589wv.getUrl()).getHost();
                                if (TextUtils.isEmpty(UploadFileJSPlugin.this.lastUploadPageUrl) || UploadFileJSPlugin.this.lastUploadPageUrl.equals(host)) {
                                    return;
                                }
                                FileUploadManager.getInstance().stopAllUpload();
                                if (UploadFileJSPlugin.uploadPageDispoasble == null || UploadFileJSPlugin.uploadPageDispoasble.isDisposed()) {
                                    return;
                                }
                                UploadFileJSPlugin.uploadPageDispoasble.dispose();
                                Disposable unused = UploadFileJSPlugin.uploadPageDispoasble = null;
                            } catch (Exception e) {
                                e.printStackTrace();
                                MsLogUtil.m7978e(e.getMessage());
                            }
                        }

                        @Override // io.reactivex.Observer
                        public void onError(@NonNull Throwable th) {
                            MsLogUtil.m7978e("上传定时器错误：" + th.getMessage());
                        }
                    });
                }
            } else if ("stopUpload".equals(string)) {
                FileUploadManager.getInstance().stopUpload(string2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e(e.getMessage());
            callbackFail(e.getMessage());
        }
    }

    private Map<String, String> transformJSONObjectToMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.getString(next));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }
}
