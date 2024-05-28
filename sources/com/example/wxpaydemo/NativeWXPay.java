package com.example.wxpaydemo;

import android.app.Activity;
import com.tencent.p348mm.sdk.modelpay.PayReq;
import com.tencent.p348mm.sdk.openapi.WXAPIFactory;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class NativeWXPay {
    public static final String PATH_SCHEMA = "unipay://wxapp/";
    private static String callbackurl;
    private static NativeWXPay instance;
    private static String prepayid;
    private Activity context;

    public NativeWXPay(Activity activity) {
        this.context = activity;
    }

    private void setActivity(Activity activity) {
        this.context = activity;
    }

    public static NativeWXPay getInstance(Activity activity) {
        NativeWXPay nativeWXPay = instance;
        if (nativeWXPay == null) {
            instance = new NativeWXPay(activity);
        } else {
            nativeWXPay.setActivity(activity);
        }
        return instance;
    }

    public boolean pay(String str) {
        Map<String, String> buildParam2Map = buildParam2Map(str);
        PayReq payReq = new PayReq();
        payReq.appId = buildParam2Map.get("appid");
        payReq.partnerId = buildParam2Map.get("partnerid");
        payReq.prepayId = buildParam2Map.get("prepayid");
        prepayid = buildParam2Map.get("prepayid");
        callbackurl = buildParam2Map.get("callbackurl");
        payReq.nonceStr = buildParam2Map.get("noncestr");
        payReq.timeStamp = buildParam2Map.get("timestamp");
        payReq.packageValue = buildParam2Map.get("package");
        payReq.sign = buildParam2Map.get("sign");
        return WXAPIFactory.createWXAPI(this.context, buildParam2Map.get("appid")).sendReq(payReq);
    }

    private Map<String, String> buildParam2Map(String str) {
        String[] split;
        HashMap hashMap = new HashMap();
        String str2 = "";
        try {
            str2 = URLDecoder.decode(str.substring(15), "utf-8").replaceAll("\\s", "+");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (String str3 : str2.split("&")) {
            if (!"".equals(str3)) {
                int indexOf = str3.indexOf("=");
                hashMap.put(str3.substring(0, indexOf), str3.substring(indexOf + 1, str3.length()));
            }
        }
        return hashMap;
    }

    public static String getPrepayid() {
        return prepayid;
    }

    public static String getCallbackurl() {
        return callbackurl;
    }
}
