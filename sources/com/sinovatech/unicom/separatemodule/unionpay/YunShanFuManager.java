package com.sinovatech.unicom.separatemodule.unionpay;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebView;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.common.AesJieMiUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.unionpay.UPPayAssistEx;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class YunShanFuManager {
    private static String backUrl = null;
    public static String orderId = null;
    private static PayResultLister payResultLister = null;
    private static String payResultType = "03";
    private static String salt;
    static YunShanFuManager yunShanFuManager;
    private Context activiyContext;

    /* renamed from: wv */
    private WebView f18622wv;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface PayResultLister {
        void payResult(String str, String str2);
    }

    public static YunShanFuManager getYunShanFuManager() {
        if (yunShanFuManager == null) {
            synchronized (YunShanFuManager.class) {
                if (yunShanFuManager == null) {
                    yunShanFuManager = new YunShanFuManager();
                }
            }
        }
        return yunShanFuManager;
    }

    public void setData(Context context, WebView webView) {
        this.activiyContext = context;
        this.f18622wv = webView;
    }

    public void collectData(Activity activity, String str, String str2) {
        TYCJBoxManager.getInstance().collectClickSdk(activity, "S2ndpage1214", str2, "云闪付", str, "com.unionpay", "1");
    }

    public void startPay(String str, String str2) {
        UPPayAssistEx.startPay(this.activiyContext, null, null, str, str2);
    }

    public void startSplitPay(String str) {
        if (str != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                App.yunshanfuPayType = 0;
                String str2 = str.split("unipay://unionpay//yunshanfu/")[1];
                if (str2.contains("payPara=")) {
                    backUrl = str2.substring(0, str2.indexOf("&payPara="));
                    MsLogUtil.m7979d("支付回调地址", backUrl);
                    String str3 = str2.split("&payPara=")[1];
                    MsLogUtil.m7979d("支付解密前参数", str3);
                    String decrypt = AesJieMiUtils.decrypt(str3);
                    MsLogUtil.m7979d("支付解密参数", decrypt);
                    String[] split = decrypt.split("\\|");
                    if (split.length >= 4) {
                        String str4 = split[0].split("payType=")[1];
                        String str5 = split[1].split("orderTn=")[1];
                        String str6 = split[2].split("mode=")[1];
                        salt = split[3].split("salt=")[1];
                        MsLogUtil.m7979d("支付参数分割", str4 + "--" + str5 + "--" + str6 + "--" + salt);
                        UPPayAssistEx.startPay(this.activiyContext, null, null, str5, str6);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                payResultType = "04";
                loadBackUrl();
            }
        }
    }

    public void getPayResult(String str) {
        char c;
        String str2 = "支付成功";
        int hashCode = str.hashCode();
        if (hashCode == -1867169789) {
            if (str.equals("success")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -1367724422) {
            if (hashCode == 3135262 && str.equals("fail")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("cancel")) {
                c = 2;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                payResultType = "00";
                str2 = "支付成功";
                break;
            case 1:
                payResultType = "01";
                str2 = "支付失败";
                break;
            case 2:
                payResultType = "02";
                str2 = "支付取消";
                break;
        }
        if (App.yunshanfuPayType == 0) {
            loadBackUrl();
        }
        PayResultLister payResultLister2 = payResultLister;
        if (payResultLister2 != null) {
            payResultLister2.payResult(payResultType, str2);
        }
    }

    private void loadBackUrl() {
        String encrypt = AesJieMiUtils.encrypt("procSts=" + payResultType + "|salt=" + salt);
        MsLogUtil.m7979d("支付加密内容", encrypt);
        MsLogUtil.m7979d("支付解密内容", AesJieMiUtils.decrypt(encrypt));
        backUrl += "&procSts=" + encrypt;
        WebView webView = this.f18622wv;
        String str = backUrl;
        if (webView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView, str);
        } else {
            webView.loadUrl(str);
        }
    }

    public void addPayResultLister(PayResultLister payResultLister2) {
        payResultLister = payResultLister2;
    }
}
