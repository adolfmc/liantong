package com.crb.jscard.p192js;

import android.webkit.JavascriptInterface;
import com.crb.jscard.CardService;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.crb.jscard.js.MyJavascriptInterface */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MyJavascriptInterface {
    @JavascriptInterface
    public void closeChannel() {
        CardService.close();
    }

    @JavascriptInterface
    public void closeWebview() {
        CardService.CloseWebview();
    }

    @JavascriptInterface
    public void downloadPersonalizedData(String str, String str2, String str3) {
        CardService.downloadPersonalizedData(str, str2, str3);
    }

    @JavascriptInterface
    public String getBanlaceAndCardNumber(String str) {
        return CardService.getBanlaceAndCardNumber(str);
    }

    @JavascriptInterface
    public String getSimCardAndDeviceInfo() {
        return CardService.getSimCardAndDeviceInfo();
    }

    @JavascriptInterface
    public String getTransactionRecords(String str) {
        return CardService.getTransactionRecords(str);
    }

    @JavascriptInterface
    public String openChannel(String str) {
        return CardService.openChannel(str);
    }

    @JavascriptInterface
    public String setDefaultCard(String str, String str2) {
        return CardService.setDefaultApplication(str, str2);
    }

    @JavascriptInterface
    public void toRecharge(String str, String str2) {
        CardService.toReCharge(str, str2);
    }

    @JavascriptInterface
    public void transmit(String str) {
        CardService.transmit(str);
    }
}
