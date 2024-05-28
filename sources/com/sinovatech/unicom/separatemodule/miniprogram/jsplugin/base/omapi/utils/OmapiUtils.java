package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils;

import android.text.TextUtils;
import android.util.Log;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.SmartCard;
import java.util.Objects;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class OmapiUtils {
    private static final String TAG = "OmapiUtils";

    public static boolean checkSw(String str) {
        return !TextUtils.isEmpty(str) && "9000".equalsIgnoreCase(str);
    }

    public static boolean checkADPU(CardResult cardResult) {
        return cardResult != null && cardResult.getStatus().equals("0000") && checkSw(cardResult.getSw());
    }

    public static CardResult openAid(String str) {
        try {
            return SmartCard.getInstance().openChannel(str);
        } catch (Exception e) {
            CardResult cardResult = new CardResult();
            cardResult.setStatus("11");
            cardResult.setMsg("打开通道异常：" + ((String) Objects.requireNonNull(e.getMessage())));
            Log.e("打开通道异常：", (String) Objects.requireNonNull(e.getMessage()));
            return cardResult;
        }
    }

    public static CardResult closeAid() {
        try {
            return SmartCard.getInstance().closeChannel();
        } catch (Exception e) {
            CardResult cardResult = new CardResult();
            cardResult.setStatus("11");
            cardResult.setMsg("关闭通道异常：" + ((String) Objects.requireNonNull(e.getMessage())));
            Log.e("关闭通道异常：", (String) Objects.requireNonNull(e.getMessage()));
            return cardResult;
        }
    }

    public static CardResult closeSEService() {
        try {
            return SmartCard.getInstance().closeService();
        } catch (Exception e) {
            CardResult cardResult = new CardResult();
            cardResult.setStatus("11");
            cardResult.setMsg("关闭通道异常：" + ((String) Objects.requireNonNull(e.getMessage())));
            Log.e("关闭通道异常：", (String) Objects.requireNonNull(e.getMessage()));
            return cardResult;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0048, code lost:
        r0.add(r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult> getCardResultList(org.json.JSONArray r5) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r5 == 0) goto L79
            int r1 = r5.length()     // Catch: java.lang.Exception -> L52
            if (r1 <= 0) goto L79
            r1 = 0
        Le:
            int r2 = r5.length()     // Catch: java.lang.Exception -> L52
            if (r1 >= r2) goto L79
            org.json.JSONObject r2 = r5.optJSONObject(r1)     // Catch: java.lang.Exception -> L52
            java.lang.String r3 = "apdu"
            java.lang.String r3 = r2.getString(r3)     // Catch: java.lang.Exception -> L52
            java.lang.String r4 = "sw"
            java.lang.String r2 = r2.getString(r4)     // Catch: java.lang.Exception -> L52
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> L52
            if (r4 == 0) goto L34
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.SmartCard r2 = com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.SmartCard.getInstance()     // Catch: java.lang.Exception -> L52
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult r2 = r2.execute(r3)     // Catch: java.lang.Exception -> L52
            goto L3c
        L34:
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.SmartCard r4 = com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.SmartCard.getInstance()     // Catch: java.lang.Exception -> L52
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult r2 = r4.execute(r3, r2)     // Catch: java.lang.Exception -> L52
        L3c:
            java.lang.String r3 = r2.getStatus()     // Catch: java.lang.Exception -> L52
            java.lang.String r4 = "0000"
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Exception -> L52
            if (r3 != 0) goto L4c
            r0.add(r2)     // Catch: java.lang.Exception -> L52
            goto L79
        L4c:
            r0.add(r2)     // Catch: java.lang.Exception -> L52
            int r1 = r1 + 1
            goto Le
        L52:
            r5 = move-exception
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult r1 = new com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult
            r1.<init>()
            java.lang.String r2 = "10"
            r1.setStatus(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "发送异常："
            r2.append(r3)
            java.lang.String r5 = r5.getMessage()
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            r1.setMsg(r5)
            r0.add(r1)
        L79:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.OmapiUtils.getCardResultList(org.json.JSONArray):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0055, code lost:
        r0.add(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult> getCardAllList(java.lang.String r4, org.json.JSONArray r5) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult r4 = openAid(r4)     // Catch: java.lang.Exception -> L5f
            r0.add(r4)     // Catch: java.lang.Exception -> L5f
            boolean r4 = checkADPU(r4)     // Catch: java.lang.Exception -> L5f
            if (r4 == 0) goto L89
            if (r5 == 0) goto L89
            int r4 = r5.length()     // Catch: java.lang.Exception -> L5f
            if (r4 <= 0) goto L89
            r4 = 0
        L1b:
            int r1 = r5.length()     // Catch: java.lang.Exception -> L5f
            if (r4 >= r1) goto L89
            org.json.JSONObject r1 = r5.optJSONObject(r4)     // Catch: java.lang.Exception -> L5f
            java.lang.String r2 = "apdu"
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Exception -> L5f
            java.lang.String r3 = "sw"
            java.lang.String r1 = r1.getString(r3)     // Catch: java.lang.Exception -> L5f
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> L5f
            if (r3 == 0) goto L41
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.SmartCard r1 = com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.SmartCard.getInstance()     // Catch: java.lang.Exception -> L5f
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult r1 = r1.execute(r2)     // Catch: java.lang.Exception -> L5f
            goto L49
        L41:
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.SmartCard r3 = com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.SmartCard.getInstance()     // Catch: java.lang.Exception -> L5f
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult r1 = r3.execute(r2, r1)     // Catch: java.lang.Exception -> L5f
        L49:
            java.lang.String r2 = r1.getStatus()     // Catch: java.lang.Exception -> L5f
            java.lang.String r3 = "0000"
            boolean r2 = r2.equals(r3)     // Catch: java.lang.Exception -> L5f
            if (r2 != 0) goto L59
            r0.add(r1)     // Catch: java.lang.Exception -> L5f
            goto L89
        L59:
            r0.add(r1)     // Catch: java.lang.Exception -> L5f
            int r4 = r4 + 1
            goto L1b
        L5f:
            r4 = move-exception
            r0.clear()
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult r5 = new com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.CardResult
            r5.<init>()
            java.lang.String r1 = "10"
            r5.setStatus(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "发送异常："
            r1.append(r2)
            java.lang.String r4 = r4.getMessage()
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r5.setMsg(r4)
            r0.add(r5)
        L89:
            closeAid()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.OmapiUtils.getCardAllList(java.lang.String, org.json.JSONArray):java.util.List");
    }
}
