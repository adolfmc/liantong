package com.baidu.p120ar.bean;

import android.text.TextUtils;
import com.baidu.p120ar.ARType;
import com.baidu.p120ar.utils.MD5Utils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bean.ARConfig */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARConfig {
    private static final String AR_SDK_SALT_FIGURE = "777078ec21930de508131ba36035de6b";
    public static final int LAUNCH_AR_TAB = 2;
    public static final int LAUNCH_AR_TAB_CASE = 3;
    public static final int LAUNCH_MODE_H5 = 1;
    public static final int LAUNCH_MODE_TAKE_PICTURE = 0;
    public static int TYPE_VPS = 11;
    private static String mARExtraInfo = "";
    private static String mARId = null;
    private static int mARInitialType = -1;
    private static String mARKey = null;
    private static int mARLaunchMode = 0;
    private static String mARPath = null;
    private static int mARType = -1;
    private static String mArFrom = null;
    private static String mArValue = null;
    private static boolean mIsNeedLastPreview = false;
    private static String sCUID = "";
    public static String sFeaPointsDir;

    public static String getARKey() {
        return mARKey;
    }

    public static void setARKey(String str) {
        mARKey = str;
    }

    public static int getARType() {
        return mARType;
    }

    public static void setARType(int i) {
        mARType = i;
    }

    public static String getARPath() {
        return mARPath;
    }

    public static void setARPath(String str) {
        mARPath = str;
    }

    public static int getARInitialType() {
        return mARInitialType;
    }

    public static void setARInitialType(int i) {
        mARInitialType = i;
    }

    public static int getARLaunchMode() {
        return mARLaunchMode;
    }

    public static void setARLaunchMode(int i) {
        mARLaunchMode = i;
    }

    public static String getARId() {
        return mARId;
    }

    public static void setARId(String str) {
        mARId = str;
    }

    public static String getArFrom() {
        return mArFrom;
    }

    public static void setArFrom(String str) {
        mArFrom = str;
    }

    public static boolean isNeedLastPreview() {
        return mIsNeedLastPreview;
    }

    public static void setIsNeedLastPreview(boolean z) {
        mIsNeedLastPreview = z;
    }

    public static String getARExtraInfo() {
        return mARExtraInfo;
    }

    public static void setARExtraInfo(String str) {
        mARExtraInfo = str;
    }

    public static Object[] getSignatureAndTime() {
        return getSignatureAndTime(DuMixARConfig.getAipAppId(), DuMixARConfig.getAPIKey());
    }

    public static Object[] getSignatureAndTime(String str, String str2) {
        Object[] objArr = new Object[2];
        long currentTimeMillis = System.currentTimeMillis();
        if (isOpen(str)) {
            objArr[0] = MD5Utils.md5(str + "777078ec21930de508131ba36035de6b" + currentTimeMillis);
        } else {
            objArr[0] = MD5Utils.md5(str + str2 + "777078ec21930de508131ba36035de6b" + currentTimeMillis);
        }
        objArr[1] = Long.valueOf(currentTimeMillis);
        return objArr;
    }

    public static String getArValue() {
        return mArValue;
    }

    public static void initARConfig(String str) {
        String string;
        String string2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            mArValue = str;
            if (jSONObject.has("ar_key")) {
                string = jSONObject.getString("ar_key");
            } else {
                string = jSONObject.has("arKey") ? jSONObject.getString("arKey") : null;
            }
            setARKey(string);
            setARPath(jSONObject.has("ar_path") ? jSONObject.getString("ar_path") : null);
            if (jSONObject.has("ar_type")) {
                string2 = jSONObject.getString("ar_type");
            } else {
                string2 = jSONObject.has("arType") ? jSONObject.getString("arType") : null;
            }
            if (TextUtils.isEmpty(string2)) {
                setARType(ARType.TRACK_2D.getTypeValue());
            } else {
                setARType(Integer.parseInt(string2));
                setARInitialType(Integer.parseInt(string2));
            }
            String string3 = jSONObject.has("ar_launch_mode") ? jSONObject.getString("ar_launch_mode") : null;
            if (TextUtils.isEmpty(string3)) {
                setARLaunchMode(0);
            } else {
                setARLaunchMode(Integer.parseInt(string3));
            }
            String string4 = jSONObject.has("ar_id") ? jSONObject.getString("ar_id") : null;
            setARId(string4);
            if (TextUtils.isEmpty(getARKey()) && string4 != null) {
                try {
                    setARKey(new JSONObject(string4).optString("content_id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (jSONObject.has("ar_from")) {
                setArFrom(jSONObject.getString("ar_from"));
            }
            if (jSONObject.has("ar_last_preview")) {
                setIsNeedLastPreview(jSONObject.getBoolean("ar_last_preview"));
            }
            if (jSONObject.has("extra_info")) {
                setARExtraInfo(jSONObject.getString("extra_info"));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean isBox() {
        return "2".equals(DuMixARConfig.getAipAppId()) && !"com.baidu.ar.boxdemo".equals(DuMixARConfig.getPackageName());
    }

    public static boolean isOpen() {
        return isOpen(DuMixARConfig.getAipAppId());
    }

    public static boolean isOpen(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return Long.parseLong(str) > 10000;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getCUID() {
        return sCUID;
    }

    public static void setCUID(String str) {
        sCUID = str;
    }
}
