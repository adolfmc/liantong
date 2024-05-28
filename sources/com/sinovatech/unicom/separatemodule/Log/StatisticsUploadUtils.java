package com.sinovatech.unicom.separatemodule.Log;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.sinovatech.unicom.basic.datacenter.HistoryDataCenter;
import com.sinovatech.unicom.basic.p314po.HistoryUpDataCenter;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DateUtils;
import com.sinovatech.unicom.p318ui.App;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class StatisticsUploadUtils {
    private static final String TAG = "StatisticsUploadUtils";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static String lastestPvLog = "";
    public static String bizProcessZhuD = "1";
    public static String bizProcessBeiD = "0";

    public static void upload(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        upload(context, str, str2, str3, str4, str5, str6, "", "", "");
    }

    public static void upload(String str, String str2, String str3, String str4, String str5, String str6) {
        upload(App.getInstance(), str, str2, str3, str4, str5, str6, "", "", "");
    }

    public static void upload(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        upload(context, str, str2, str3, str4, str5, str6, str7, "", "");
    }

    public static void upload(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        upload(context, str, str2, str3, str4, str5, str6, str7, str8, UserManager.getInstance().getLocateProvinceCode(), UserManager.getInstance().getYwCodeDefault());
    }

    public static void upload(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        uploadCommon(context, str, str2, str3, str4, str5, str6, str7, "", "", "", "", "", "", "", "", "");
    }

    public static void upload(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        uploadCommon(context, str, str2, str3, str4, str5, str6, str7, "", "", str8, "", str9, str10, "", "", "");
    }

    public static void uploadXinLao(Context context, String str, String str2, String str3, String str4) {
        Log.d("新老用户日志", "uploadXinLao: " + str + str2);
        uploadCommon(context, str, "", str2, "", "", str3, "", "", "", str4, "", "", "", "", "", bizProcessBeiD);
    }

    public static void uploadBeiDong(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        uploadCommon(context, str, str2, str3, str4, str5, str6, str7, "", "", str8, "", str9, str10, "", "", bizProcessBeiD);
    }

    public static void uploadBeiDong(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        uploadCommon(context, str, str2, str3, str4, str5, str6, str7, "", "", str8, str9, str10, str11, "", "", bizProcessBeiD);
    }

    public static void uploadPingJiaShiChang(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        uploadCommon(context, str, str2, str3, str4, str5, "", "", "", "", str8, str7, "", str6, "", "", "");
    }

    public static void uploadPingJiaShiChang(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        uploadCommon(context, str, str2, str3, str4, str5, "", "", "", "", "", str7, "", str6, "", "", "");
    }

    public static void uploadRealTime(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        uploadCommon(context, str, str2, str3, str4, str5, str6, "", "", "", "", "", UserManager.getInstance().getLocateProvinceCode(), UserManager.getInstance().getYwCodeDefault(), "", "", "");
    }

    public static void uploadRealTime(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        uploadCommon(context, str, str2, str3, str4, str5, str6, "", "", "", str7, "", str8, str9, "", "", "");
    }

    public static void uploadRealTimeBeiDong(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        uploadCommon(context, str, str2, str3, str4, str5, str6, "", "", "", "", "", UserManager.getInstance().getLocateProvinceCode(), UserManager.getInstance().getYwCodeDefault(), "", "", bizProcessBeiD);
    }

    public static void uploadRealTime2(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        uploadCommon(context, str, str2, str3, str4, str5, str6, "", "", "", str8, "", str7, UserManager.getInstance().getYwCodeDefault(), "", "", "");
    }

    public static void uploadSmallVideoPVLog(Context context, String str, String str2, String str3, String str4, String str5) {
        uploadCommon(context, str, str2, str3, "", str4, "", "", "", "", "", "", str5, "", "", "", "");
    }

    public static void uploadRealTime2BeiDong(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        uploadCommon(context, str, str2, str3, str4, str5, str6, "", "", "", str8, "", str7, UserManager.getInstance().getYwCodeDefault(), "", "", bizProcessBeiD);
    }

    private static String getValueByName(String str, String str2) {
        String[] split;
        for (String str3 : str.substring(str.indexOf("?") + 1).split("&")) {
            if (str3.contains(str2)) {
                return str3.replace(str2 + "=", "");
            }
        }
        return "";
    }

    public static void uoloadPVSmootoLog(String str, String str2, String str3, String str4) {
        App app = App.getInstance();
        uploadCommon(app, "sliulan0002", str, str2, "", "", "", "", "", "", DateUtils.timeStamp2Date(System.currentTimeMillis() + ""), "", "", str3, "", "", str4);
    }

    public static void uoloadPVWatch(String str, String str2) {
        upload("androidWatch0001", "", str, str2, "", "");
    }

    /* JADX WARN: Removed duplicated region for block: B:91:0x024d A[Catch: Exception -> 0x0301, TryCatch #10 {Exception -> 0x0301, blocks: (B:3:0x0016, B:5:0x0027, B:7:0x002e, B:13:0x0048, B:15:0x004e, B:17:0x0054, B:21:0x0061, B:23:0x0067, B:25:0x006d, B:29:0x007a, B:31:0x0084, B:35:0x009e, B:40:0x00b4, B:89:0x0224, B:91:0x024d, B:93:0x0266, B:92:0x0260, B:88:0x0221, B:26:0x0072, B:18:0x0059, B:12:0x0045, B:9:0x0037), top: B:117:0x0016, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0260 A[Catch: Exception -> 0x0301, TryCatch #10 {Exception -> 0x0301, blocks: (B:3:0x0016, B:5:0x0027, B:7:0x002e, B:13:0x0048, B:15:0x004e, B:17:0x0054, B:21:0x0061, B:23:0x0067, B:25:0x006d, B:29:0x007a, B:31:0x0084, B:35:0x009e, B:40:0x00b4, B:89:0x0224, B:91:0x024d, B:93:0x0266, B:92:0x0260, B:88:0x0221, B:26:0x0072, B:18:0x0059, B:12:0x0045, B:9:0x0037), top: B:117:0x0016, inners: #9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void uploadCommon(android.content.Context r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, java.lang.String r29, java.lang.String r30, java.lang.String r31, java.lang.String r32, java.lang.String r33, java.lang.String r34, java.lang.String r35, java.lang.String r36) {
        /*
            Method dump skipped, instructions count: 774
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils.uploadCommon(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static void recordHistoryMenu(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        try {
            StatisticsEntity statisticsEntity = new StatisticsEntity();
            statisticsEntity.setKey(UUID.randomUUID().toString());
            statisticsEntity.setTransId(str);
            statisticsEntity.setActCode(str2);
            statisticsEntity.setUpType(str3);
            statisticsEntity.setMenuId(str4);
            statisticsEntity.setTitleName(str5);
            statisticsEntity.setUrlApp(str6);
            statisticsEntity.setMobile(str16);
            statisticsEntity.setCityId(str8);
            statisticsEntity.setProvId(str9);
            statisticsEntity.setRemark1(str10);
            statisticsEntity.setRemark2(str11);
            statisticsEntity.setRemark3(str12);
            statisticsEntity.setRemark4(str13);
            statisticsEntity.setVersion(context.getResources().getString(2131886969));
            statisticsEntity.setClientType("Android");
            statisticsEntity.setIconUrl(str7);
            statisticsEntity.setTime(String.valueOf(System.currentTimeMillis() / 1000));
            if (!"导航".equals(str3) || "浏览记录".equals(statisticsEntity.getTitleName()) || TextUtils.isEmpty(statisticsEntity.getUrlApp())) {
                return;
            }
            HistoryDataCenter historyDataCenter = new HistoryDataCenter(context);
            HistoryUpDataCenter historyUpDataCenter = new HistoryUpDataCenter(context);
            historyDataCenter.insertStatisticsRecord(statisticsEntity);
            historyUpDataCenter.insertStatisticsRecord(statisticsEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateSessionId() {
        String str = "";
        try {
            String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            App.pvLogSessionIdTime = format;
            String uuid = UUID.randomUUID().toString();
            str = format + (uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24));
            UnicomCookieManager.addSessionIdCookie(str);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}
