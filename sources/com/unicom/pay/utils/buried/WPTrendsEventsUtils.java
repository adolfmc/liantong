package com.unicom.pay.utils.buried;

import android.text.TextUtils;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.callback.NativeFunctionCallBack;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import p470p0.C13652o;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPTrendsEventsUtils {
    public static final String ChannelType = "mcashierwap";
    public static final String Scence = "00";
    public static final String Unicom = "a0||a1||a2||a3||a4||a5||a6||a7||a8||a9||a10||a11||a12||a13||a14||a15||a16||a17";
    private static StringBuilder activityStack = new StringBuilder("-");
    private static String sBizType = "-";
    private static String sChannelCode = "-";
    private static String sFqNum = "-";
    private static String sHbAmount = "-";
    private static String sMerNo = "-";
    private static String sOrderAmount = "-";
    private static String sPayMethod = "-";
    private static String sPreferentialAmount = "-";
    private static String sProjectType = "-";
    private static String sRealPayAmount = "-";
    private static String sYhAmount = "-";

    public static void addWindow(String str) {
        if (TextUtils.isEmpty(str) || "-".equals(str)) {
            return;
        }
        if (activityStack.length() == 1) {
            activityStack.replace(0, 1, "");
        }
        StringBuilder sb = activityStack;
        sb.append(str);
        sb.append("-");
    }

    private static void clearWindow() {
        activityStack = new StringBuilder("-");
    }

    public static String getBizType() {
        return sBizType;
    }

    public static String getChannelCode() {
        return sChannelCode;
    }

    public static String getCurrentSort() {
        String sb = activityStack.toString();
        try {
            return sb.substring(0, sb.length() - 1);
        } catch (Exception unused) {
            return sb;
        }
    }

    public static String getFqNum() {
        return sFqNum;
    }

    public static String getHbAmount() {
        return sHbAmount;
    }

    public static String getMerNo() {
        return sMerNo;
    }

    public static String getOrderAmount() {
        return sOrderAmount;
    }

    public static String getPayMethod() {
        return sPayMethod;
    }

    public static String getPreferentialAmount() {
        return sPreferentialAmount;
    }

    public static String getProjectType() {
        return sProjectType;
    }

    public static String getRealPayAmount() {
        return sRealPayAmount;
    }

    public static String getYhAmount() {
        return sYhAmount;
    }

    public static void initData() {
        clearWindow();
        sOrderAmount = "-";
        sPreferentialAmount = "-";
        sRealPayAmount = "-";
        sPayMethod = "-";
        sBizType = "-";
        sChannelCode = "-";
        sMerNo = "-";
        sHbAmount = "-";
        sYhAmount = "-";
        sFqNum = "-";
        sProjectType = "-";
    }

    public static void setBizType(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        sBizType = str;
    }

    public static void setChannelCode(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        sChannelCode = str;
    }

    public static void setFqNum(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        sFqNum = str;
    }

    public static void setHbAmount(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        sHbAmount = str;
    }

    public static void setMerNo(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        sMerNo = str;
    }

    public static void setOrderAmount(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        sOrderAmount = str;
    }

    public static void setPayMethod(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        sPayMethod = str;
    }

    public static void setPreferentialAmount(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        sPreferentialAmount = str;
    }

    public static void setProjectType(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        sProjectType = str;
    }

    public static void setRealPayAmount(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        sRealPayAmount = str;
    }

    public static void setYhAmount(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-";
        }
        sYhAmount = str;
    }

    public static void trendsPageButtonData(WPBusinessInfoBean wPBusinessInfoBean, String str, String str2, String str3, String str4) {
        try {
            if (TextUtils.isEmpty(str) || "-".equals(str) || TextUtils.isEmpty(str4) || "-".equals(str4) || wPBusinessInfoBean == null) {
                return;
            }
            wPBusinessInfoBean.setPage_name(str);
            wPBusinessInfoBean.setPage_code(str2);
            wPBusinessInfoBean.setPage_short_code(str3);
            wPBusinessInfoBean.setOrder_amount(getOrderAmount());
            wPBusinessInfoBean.setPayment_amount(getRealPayAmount());
            wPBusinessInfoBean.setPreferential_amount(getPreferentialAmount());
            wPBusinessInfoBean.setPayment_method(getPayMethod());
            wPBusinessInfoBean.setSort_code(getCurrentSort());
            wPBusinessInfoBean.setClient_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date()));
            wPBusinessInfoBean.setLocal_time(wPBusinessInfoBean.getClient_time());
            wPBusinessInfoBean.setMerchant(getMerNo());
            wPBusinessInfoBean.setBusiness_type(getBizType());
            wPBusinessInfoBean.setBusinessChannel(getChannelCode());
            wPBusinessInfoBean.setYh_amount(getYhAmount());
            wPBusinessInfoBean.setHb_amount(getHbAmount());
            wPBusinessInfoBean.setFqNum(getFqNum());
            wPBusinessInfoBean.setFqApr(getProjectType());
            wPBusinessInfoBean.setPb_name(str4);
            NativeFunctionCallBack nativeFunctionCallback = UnicomPaySDK.getInstance().getNativeFunctionCallback();
            if (nativeFunctionCallback != null) {
                C13652o.m174a("wcy", "触发埋点");
                nativeFunctionCallback.trendsEvent("a0||a1||a2||a3||a4||a5||a6||a7||a8||a9||a10||a11||a12||a13||a14||a15||a16||a17", wPBusinessInfoBean.toBusinessStr(), "00", "mcashierwap");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trendsPageButtonData(String str, String str2, String str3, String str4) {
        try {
            if (!TextUtils.isEmpty(str) && !"-".equals(str)) {
                trendsPageButtonData(WPBusinessInfoBean.generateButtonEntity(), str, str2, str3, str4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trendsPageData(WPBusinessInfoBean wPBusinessInfoBean, String str, String str2, String str3, String str4) {
        try {
            if (TextUtils.isEmpty(str) || "-".equals(str) || wPBusinessInfoBean == null) {
                return;
            }
            wPBusinessInfoBean.setPage_name(str);
            wPBusinessInfoBean.setPage_code(str2);
            wPBusinessInfoBean.setPage_short_code(str3);
            wPBusinessInfoBean.setOrder_amount(getOrderAmount());
            wPBusinessInfoBean.setPayment_amount(getRealPayAmount());
            wPBusinessInfoBean.setPreferential_amount(getPreferentialAmount());
            wPBusinessInfoBean.setPayment_method(getPayMethod());
            wPBusinessInfoBean.setSort_code(getCurrentSort());
            wPBusinessInfoBean.setClient_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date()));
            wPBusinessInfoBean.setLocal_time(wPBusinessInfoBean.getClient_time());
            wPBusinessInfoBean.setMerchant(getMerNo());
            wPBusinessInfoBean.setBusiness_type(getBizType());
            wPBusinessInfoBean.setBusinessChannel(getChannelCode());
            wPBusinessInfoBean.setYh_amount(getYhAmount());
            wPBusinessInfoBean.setHb_amount(getHbAmount());
            wPBusinessInfoBean.setFqNum(getFqNum());
            wPBusinessInfoBean.setFqApr(getProjectType());
            wPBusinessInfoBean.setTime_spent(str4);
            NativeFunctionCallBack nativeFunctionCallback = UnicomPaySDK.getInstance().getNativeFunctionCallback();
            if (nativeFunctionCallback != null) {
                C13652o.m174a("wcy", "触发页面埋点");
                nativeFunctionCallback.trendsEvent("a0||a1||a2||a3||a4||a5||a6||a7||a8||a9||a10||a11||a12||a13||a14||a15||a16||a17", wPBusinessInfoBean.toBusinessStr(), "00", "mcashierwap");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trendsPageData(String str, String str2, String str3) {
        try {
            if (!TextUtils.isEmpty(str) && !"-".equals(str)) {
                WPBusinessInfoBean generatePageEntity = WPBusinessInfoBean.generatePageEntity();
                addWindow(str3);
                trendsPageData(generatePageEntity, str, str2, str3, "-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
