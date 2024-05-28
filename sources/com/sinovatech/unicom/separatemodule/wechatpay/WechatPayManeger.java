package com.sinovatech.unicom.separatemodule.wechatpay;

import android.app.Activity;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.tencent.p348mm.opensdk.modelpay.PayReq;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WechatPayManeger {
    public static void wxPay(Activity activity, String str, String str2, String str3, String str4, String str5, String str6) {
        if (App.getWXAPI().isWXAppInstalled()) {
            App.weixinPayFlag = true;
            PayReq payReq = new PayReq();
            payReq.appId = "wxa13d0b8c5270d1ff";
            payReq.partnerId = str;
            payReq.prepayId = str2;
            payReq.packageValue = str3;
            payReq.nonceStr = str4;
            payReq.timeStamp = str5;
            payReq.sign = str6;
            App.getWXAPI().sendReq(payReq);
            return;
        }
        UIUtils.toast("尚未安装微信客户端");
    }
}
