package com.sinovatech.unicom.separatemodule.unicompay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.PayTask;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.basic.p314po.PayResult;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJParseManager;
import com.sinovatech.unicom.separatemodule.unionpay.YunShanFuManager;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.common.callback.NativeFunctionCallBack;
import com.unicom.pay.utils.buried.WPBusinessInfoBean;
import java.util.Map;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/unicomPay")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomPayJSPlugin extends BaseJSPlugin {
    public static String TAG = "UnicomPayJSPlugin";
    private final int JS_PAY_FLAG = 200;
    private Handler mHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.unicompay.UnicomPayJSPlugin.4
        @Override // android.os.Handler
        @SuppressLint({"HandlerLeak"})
        public void handleMessage(Message message) {
            if (message.what != 200) {
                return;
            }
            try {
                PayResult payResult = new PayResult((String) message.obj);
                String result = payResult.getResult();
                String memo = payResult.getMemo();
                String resultStatus = payResult.getResultStatus();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("resultStatus", resultStatus);
                jSONObject.put("result", result);
                jSONObject.put("memo", memo);
                UnicomPayJSPlugin.this.payCallBack.onResult(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            } catch (Exception e) {
                e.printStackTrace();
                UnicomPayJSPlugin.this.callbackFail("10", e.getMessage());
                MsLogUtil.m7979d("", e.getMessage());
            }
        }
    };
    private DataCallback payCallBack;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if (this.parameterJO != null) {
                JSONObject jSONObject = this.parameterJO;
                if (!(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).isEmpty()) {
                    final String optString = this.parameterJO.optString("action");
                    final String optString2 = this.parameterJO.optString("jsonData");
                    UnicomPaySDK.getInstance().setNativeFunctionCallback(new NativeFunctionCallBack() { // from class: com.sinovatech.unicom.separatemodule.unicompay.UnicomPayJSPlugin.1
                        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
                        public void getCode(String str, String str2, String str3, String str4, DataCallback dataCallback) {
                            MsLogUtil.m7979d(UnicomPayJSPlugin.TAG, "getCode");
                            UnicomPayJSPlugin.this.getTicket(dataCallback, UserManager.getInstance().getPriToken(), str, str3, str4);
                        }

                        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
                        public void loadUrl(String str, boolean z) {
                            if (UnicomPayJSPlugin.this.f18589wv == null) {
                                return;
                            }
                            if (z) {
                                Map<String, String> headerMap = UnicomPayUtils.getInstance(UnicomPayJSPlugin.this.activityContext).getHeaderMap();
                                headerMap.put("Referer", UnicomPayJSPlugin.this.f18589wv.getUrl());
                                WebView webView = UnicomPayJSPlugin.this.f18589wv;
                                if (webView instanceof Object) {
                                    NBSWebLoadInstrument.loadUrl((Object) webView, str, headerMap);
                                    return;
                                } else {
                                    webView.loadUrl(str, headerMap);
                                    return;
                                }
                            }
                            WebView webView2 = UnicomPayJSPlugin.this.f18589wv;
                            if (webView2 instanceof Object) {
                                NBSWebLoadInstrument.loadUrl((Object) webView2, str);
                            } else {
                                webView2.loadUrl(str);
                            }
                        }

                        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
                        public void reloadUrl() {
                            MsLogUtil.m7979d(UnicomPayJSPlugin.TAG, "reloadUrl");
                            if (UnicomPayJSPlugin.this.f18589wv != null) {
                                UnicomPayJSPlugin.this.f18589wv.reload();
                            }
                        }

                        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
                        public void openWebview(Context context, String str) {
                            String str2 = UnicomPayJSPlugin.TAG;
                            MsLogUtil.m7979d(str2, "openWebview" + str);
                            UnicomPayUtils.getInstance(UnicomPayJSPlugin.this.activityContext).gotoWebViewActivity(UnicomPayJSPlugin.this.activityContext, str, "", "");
                        }

                        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
                        public void openWebview(Context context, String str, String str2) {
                            UnicomPayUtils.getInstance(UnicomPayJSPlugin.this.activityContext).gotoWebViewActivity(UnicomPayJSPlugin.this.activityContext, str, "", str2);
                        }

                        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
                        public void aliPay(Context context, String str, DataCallback dataCallback) {
                            MsLogUtil.m7979d(UnicomPayJSPlugin.TAG, "aliPay");
                            try {
                                UnicomPayJSPlugin.this.payCallBack = dataCallback;
                                if (!TextUtils.isEmpty(str) && str.startsWith("{")) {
                                    UnicomPayJSPlugin.this.startAliPay(new JSONObject(str).optString("orderStr"));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
                        public void unionPay(Context context, String str, DataCallback dataCallback) {
                            try {
                                if (!TextUtils.isEmpty(str) && str.startsWith("{")) {
                                    String str2 = UnicomPayJSPlugin.TAG;
                                    MsLogUtil.m7979d(str2, "SDK透传过来的支付参数" + str);
                                    JSONObject jSONObject2 = new JSONObject(str);
                                    String optString3 = jSONObject2.optString("orderTn");
                                    String str3 = UnicomPayJSPlugin.TAG;
                                    MsLogUtil.m7979d(str3, "解析tn" + optString3);
                                    String optString4 = jSONObject2.optString("mode");
                                    String str4 = UnicomPayJSPlugin.TAG;
                                    MsLogUtil.m7979d(str4, "解析mode" + optString4);
                                    App.yunshanfuPayType = 1;
                                    YunShanFuManager yunShanFuManager = YunShanFuManager.getYunShanFuManager();
                                    yunShanFuManager.setData(context, UnicomPayJSPlugin.this.f18589wv);
                                    yunShanFuManager.collectData(UnicomPayJSPlugin.this.activityContext, UnicomPayJSPlugin.this.getCurrentURL(), UnicomPayJSPlugin.this.f18589wv.getTitle());
                                    yunShanFuManager.startPay(optString3, optString4);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                String str5 = UnicomPayJSPlugin.TAG;
                                MsLogUtil.m7979d(str5, "云闪付拉起异常" + e.getMessage());
                            }
                        }

                        @Override // com.unicom.pay.common.callback.NativeFunctionCallBack
                        public void trendsEvent(String str, String str2, String str3, String str4) {
                            String str5 = UnicomPayJSPlugin.TAG;
                            MsLogUtil.m7979d(str5, "trendsEvent" + str + WPBusinessInfoBean.SPLIT + str2 + WPBusinessInfoBean.SPLIT + str3 + WPBusinessInfoBean.SPLIT + str4);
                            TYCJParseManager.getInstanse().loadWorkData(str, str2, str3, str4);
                        }
                    });
                    UnicomPaySDK.getInstance().pay(this.activityContext, getCurrentURL(), optString, optString2, new DataCallback() { // from class: com.sinovatech.unicom.separatemodule.unicompay.UnicomPayJSPlugin.2
                        @Override // com.unicom.pay.common.callback.DataCallback
                        public void onResult(String str) {
                            try {
                                String str2 = UnicomPayJSPlugin.TAG;
                                MsLogUtil.m7979d(str2, optString + "===" + optString2);
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("data", str);
                                UnicomPayJSPlugin.this.callbackSuccess(jSONObject2);
                            } catch (Exception e) {
                                e.printStackTrace();
                                MsLogUtil.m7979d(UnicomPayJSPlugin.TAG, e.getMessage());
                            }
                        }
                    });
                    return;
                }
            }
            callbackFail("11", "参数错误");
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAliPay(final String str) {
        new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.unicompay.UnicomPayJSPlugin.3
            @Override // java.lang.Runnable
            public void run() {
                String pay = new PayTask(UnicomPayJSPlugin.this.activityContext).pay(str, true);
                Message message = new Message();
                message.what = 200;
                message.obj = pay;
                UnicomPayJSPlugin.this.mHandler.sendMessage(message);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getTicket(final DataCallback dataCallback, String str, String str2, String str3, String str4) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("pri_token", str);
        requestParams.put("appid", str2);
        requestParams.put("timestamp", str4);
        requestParams.put("signstr", str3);
        String str5 = TAG;
        MsLogUtil.m7979d(str5, "ticket接口" + URLSet.getTicket());
        String str6 = TAG;
        MsLogUtil.m7979d(str6, "pri_token" + str);
        String str7 = TAG;
        MsLogUtil.m7979d(str7, "appid" + str2);
        String str8 = TAG;
        MsLogUtil.m7979d(str8, "timestamp" + str4);
        String str9 = TAG;
        MsLogUtil.m7979d(str9, "signstr" + str3);
        App.getAsyncHttpClient().get(URLSet.getTicket(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.unicompay.UnicomPayJSPlugin.5
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str10) {
                super.onSuccess(i, str10);
                String str11 = UnicomPayJSPlugin.TAG;
                MsLogUtil.m7979d(str11, "onSuccess: " + str10);
                try {
                    dataCallback.onResult(str10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str10) {
                super.onFailure(th, str10);
                dataCallback.onResult(str10);
                String str11 = UnicomPayJSPlugin.TAG;
                MsLogUtil.m7979d(str11, "onFailure: " + str10);
            }
        });
    }
}
