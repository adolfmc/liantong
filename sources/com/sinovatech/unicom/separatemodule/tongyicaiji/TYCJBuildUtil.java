package com.sinovatech.unicom.separatemodule.tongyicaiji;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
import com.megvii.livenesslib.util.FoldUtils;
import com.megvii.livenesslib.util.RootUtil;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.utils.CpuManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.unicom.pay.utils.buried.WPBusinessInfoBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TYCJBuildUtil {
    private static final String TAG = "TYCJBuildUtil";

    public static String getDeviceInfo(Activity activity) throws Exception {
        UserManager userManager = UserManager.getInstance();
        String deviceID = DeviceHelper.getDeviceID(false);
        String valueOf = String.valueOf(SystemTimeUtil.currentTimeMillis());
        String defaultPhoneNumber = userManager.getDefaultPhoneNumber();
        String currentProvinceCode = userManager.getCurrentProvinceCode();
        String userAreaid = userManager.getUserAreaid();
        if ("0".equals(defaultPhoneNumber)) {
            defaultPhoneNumber = "-";
            currentProvinceCode = "-";
            userAreaid = "-";
        }
        String deviceID2 = DeviceHelper.getDeviceID(false);
        String pvLogSessionId = App.getPvLogSessionId();
        String packageName = activity.getPackageName();
        String valueOf2 = String.valueOf(UIUtils.getScreenWidth(activity));
        String valueOf3 = String.valueOf(UIUtils.getScreenHeight(activity));
        String str = DeviceHelper.totlaMem(DeviceHelper.getMemInfo());
        String str2 = RootUtil.isDeviceRooted() ? "1" : "0";
        String str3 = DeviceHelper.isHarmonyOS() ? "harmony" : "android";
        String str4 = FoldUtils.isFold(activity) ? "3" : "1";
        String str5 = DeviceHelper.isHarmonyOS() ? "harmony" : "android";
        String deviceOSVersion = DeviceHelper.getDeviceOSVersion();
        String language = DeviceHelper.getLanguage();
        String deviceBranD = DeviceHelper.getDeviceBranD();
        String deviceBrand = DeviceHelper.getDeviceBrand();
        String deviceModel = DeviceHelper.getDeviceModel();
        String str6 = str4;
        String string = activity.getString(2131886249);
        String valueOf4 = String.valueOf(Build.VERSION.SDK_INT);
        String androidID = DeviceHelper.getAndroidID();
        ArrayList arrayList = new ArrayList();
        arrayList.add(deviceID);
        arrayList.add(valueOf);
        arrayList.add(defaultPhoneNumber);
        arrayList.add(currentProvinceCode);
        arrayList.add(userAreaid);
        arrayList.add(deviceID2);
        arrayList.add(pvLogSessionId);
        arrayList.add(packageName);
        arrayList.add(valueOf2);
        arrayList.add(valueOf3);
        arrayList.add("-");
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add(str3);
        arrayList.add(str6);
        arrayList.add(str5);
        arrayList.add(deviceOSVersion);
        arrayList.add(language);
        arrayList.add(deviceBranD);
        arrayList.add(deviceBrand);
        arrayList.add(deviceModel);
        arrayList.add(string);
        arrayList.add(valueOf4);
        arrayList.add(androidID);
        arrayList.add("-");
        arrayList.add("-");
        arrayList.add("-");
        MsLogUtil.m7979d("getParamsString", "=======collectUnicomF==开始======\n");
        String stringBuilder = getStringBuilder(arrayList);
        MsLogUtil.m7979d("getParamsString", "=======collectUnicomF==结束======\n");
        return stringBuilder;
    }

    public static String getAppInString(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        UserManager userManager = UserManager.getInstance();
        String deviceID = DeviceHelper.getDeviceID(false);
        String valueOf = String.valueOf(SystemTimeUtil.currentTimeMillis());
        String defaultPhoneNumber = userManager.getDefaultPhoneNumber();
        String currentProvinceCode = userManager.getCurrentProvinceCode();
        String userAreaid = userManager.getUserAreaid();
        if ("0".equals(defaultPhoneNumber)) {
            defaultPhoneNumber = "-";
            currentProvinceCode = "-";
            userAreaid = "-";
        }
        String pvLogSessionId = App.getPvLogSessionId();
        String string = App.getInstance().getString(2131886969);
        ArrayList arrayList = new ArrayList();
        arrayList.add(deviceID);
        arrayList.add(valueOf);
        arrayList.add(defaultPhoneNumber);
        arrayList.add(currentProvinceCode);
        arrayList.add(userAreaid);
        arrayList.add(pvLogSessionId);
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add(str3);
        arrayList.add(str4);
        arrayList.add(str5);
        arrayList.add(str6);
        arrayList.add(str7);
        arrayList.add(str8);
        arrayList.add(str9);
        arrayList.add(str10);
        arrayList.add(string);
        MsLogUtil.m7979d("getParamsString", "=======collectAppIn==开始======\n");
        String stringBuilder = getStringBuilder(arrayList);
        MsLogUtil.m7979d("getParamsString", "=======collectAppIn==结束======\n");
        return stringBuilder;
    }

    public static String getSessionString(String str, String str2) {
        String str3;
        String str4;
        String str5;
        String deviceID = DeviceHelper.getDeviceID(false);
        String valueOf = String.valueOf(SystemTimeUtil.currentTimeMillis());
        String currentProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
        String userAreaid = UserManager.getInstance().getUserAreaid();
        if ("0".equals(str)) {
            str5 = "-";
            str4 = "-";
            str3 = "-";
        } else {
            str3 = userAreaid;
            str4 = currentProvinceCode;
            str5 = str;
        }
        String pvLogSessionId = App.getPvLogSessionId();
        String nETType = DeviceHelper.getNETType(App.getInstance());
        String str6 = "";
        String property = System.getProperty("http.proxyHost");
        String property2 = System.getProperty("http.proxyPort");
        if (!TextUtils.isEmpty(property) && !"null".equals(property)) {
            str6 = property + "/" + property2;
        }
        String valueOf2 = String.valueOf(DeviceHelper.getCarrierType(App.getInstance()));
        String str7 = TYCJAddressUtil.getAddressEntity().getLatitude() + "";
        String str8 = TYCJAddressUtil.getAddressEntity().getLongitude() + "";
        String str9 = "-";
        if (!TextUtils.isEmpty(str7) && "0.0".equals(str7)) {
            str9 = str7 + "," + str8;
        }
        String locateProvinceCode = TYCJAddressUtil.getAddressEntity().getLocateProvinceCode();
        String locateCityCode = TYCJAddressUtil.getAddressEntity().getLocateCityCode();
        String cpuPercent = CpuManager.getCpuPercent();
        String leftMem = DeviceHelper.leftMem(DeviceHelper.getMemInfo());
        String string = App.getInstance().getString(2131886969);
        ArrayList arrayList = new ArrayList();
        arrayList.add(deviceID);
        arrayList.add(valueOf);
        arrayList.add(str5);
        arrayList.add(str4);
        arrayList.add(str3);
        arrayList.add(pvLogSessionId);
        arrayList.add(nETType);
        arrayList.add(str6);
        arrayList.add(valueOf2);
        arrayList.add(str9);
        arrayList.add(locateProvinceCode);
        arrayList.add(locateCityCode);
        arrayList.add(cpuPercent);
        arrayList.add(leftMem);
        arrayList.add(str2);
        arrayList.add(string);
        MsLogUtil.m7979d("getParamsString", "=======Unicom_v===开始=====\n");
        String stringBuilder = getStringBuilder(arrayList);
        MsLogUtil.m7979d("getParamsString", "=======Unicom_v===结束=====\n");
        return stringBuilder;
    }

    public static String getAppOutString(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        App app = App.getInstance();
        UserManager userManager = UserManager.getInstance();
        String deviceID = DeviceHelper.getDeviceID(false);
        String valueOf = String.valueOf(SystemTimeUtil.currentTimeMillis());
        String defaultPhoneNumber = userManager.getDefaultPhoneNumber();
        String currentProvinceCode = userManager.getCurrentProvinceCode();
        String userAreaid = userManager.getUserAreaid();
        if ("0".equals(defaultPhoneNumber)) {
            defaultPhoneNumber = "-";
            currentProvinceCode = "-";
            userAreaid = "-";
        }
        String pvLogSessionId = App.getPvLogSessionId();
        String string = app.getString(2131886969);
        ArrayList arrayList = new ArrayList();
        arrayList.add(deviceID);
        arrayList.add(valueOf);
        arrayList.add(defaultPhoneNumber);
        arrayList.add(currentProvinceCode);
        arrayList.add(userAreaid);
        arrayList.add(pvLogSessionId);
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add(str3);
        arrayList.add(str4);
        arrayList.add(str5);
        arrayList.add(str6);
        arrayList.add(str7);
        arrayList.add(str8);
        arrayList.add(string);
        MsLogUtil.m7979d("getParamsString", "=======appOut==开始======\n");
        String stringBuilder = getStringBuilder(arrayList);
        MsLogUtil.m7979d("getParamsString", "=======appOut==结束======\n");
        return stringBuilder;
    }

    public static String getH5Click(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add(str3);
        arrayList.add(str4);
        arrayList.add(str5);
        arrayList.add(str6);
        arrayList.add(str7);
        arrayList.add(str8);
        arrayList.add(str9);
        arrayList.add(str10);
        arrayList.add(str11);
        arrayList.add(App.getInstance().getString(2131886969));
        MsLogUtil.m7979d("getParamsString", "=======H5Click==开始======\n");
        String stringBuilder = getStringBuilder(arrayList);
        MsLogUtil.m7979d("getParamsString", "=======H5Click==结束======\n");
        return stringBuilder;
    }

    public static String getNetWork(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(DeviceHelper.getDeviceID(false));
        arrayList.add(UserManager.getInstance().getCurrentPhoneNumber());
        arrayList.add(App.getPvLogSessionId());
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add(str3);
        arrayList.add(str4);
        arrayList.add(str5);
        arrayList.add(str6);
        arrayList.add(str7);
        arrayList.add(str8);
        arrayList.add(str9);
        arrayList.add(str10);
        arrayList.add(str11);
        arrayList.add(str12);
        arrayList.add(str13);
        arrayList.add(App.getInstance().getVersion());
        return getStringBuilder(arrayList);
    }

    public static String getH5Info(JSONObject jSONObject, Map<String, String> map) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        ArrayList arrayList = new ArrayList();
        try {
            String optString = jSONObject.optString("link_url");
            String str8 = TextUtils.isEmpty(optString) ? map.get("urlApp") : optString;
            String str9 = "-";
            try {
                if (!TextUtils.isEmpty(str8) && str8.contains("?")) {
                    String[] split = str8.split("\\?");
                    if (split.length > 0) {
                        str8 = split[0];
                        if (split.length > 1) {
                            str9 = split[1];
                        }
                    }
                }
                if (str8.endsWith("#/")) {
                    str8 = str8.substring(0, str8.length() - 2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String optString2 = jSONObject.optString("sender_name");
            String optString3 = jSONObject.optString("page_title");
            String optString4 = jSONObject.optString("timestamp");
            String optString5 = jSONObject.optString("ses_id");
            String optString6 = jSONObject.optString("request_id");
            String optString7 = jSONObject.optString("p");
            String optString8 = jSONObject.optString("dl");
            String optString9 = jSONObject.optString("le");
            String optString10 = jSONObject.optString("ns");
            String optString11 = jSONObject.optString("ft");
            if (TextUtils.isEmpty(optString4)) {
                StringBuilder sb = new StringBuilder();
                str = optString3;
                sb.append(SystemTimeUtil.currentTimeMillis());
                sb.append("");
                optString4 = sb.toString();
            } else {
                str = optString3;
            }
            if (TextUtils.isEmpty(optString5)) {
                optString5 = App.getPvLogSessionId();
            }
            UserManager userManager = UserManager.getInstance();
            String deviceID = DeviceHelper.getDeviceID(false);
            String defaultPhoneNumber = userManager.getDefaultPhoneNumber();
            String currentProvinceCode = userManager.getCurrentProvinceCode();
            String currentCityCode = userManager.getCurrentCityCode();
            StringBuilder sb2 = new StringBuilder();
            String str10 = str8;
            sb2.append(UIUtils.getScreenWidth(App.getInstance()));
            sb2.append("");
            String sb3 = sb2.toString();
            StringBuilder sb4 = new StringBuilder();
            String str11 = str9;
            sb4.append(UIUtils.getScreenHeight(App.getInstance()));
            sb4.append("");
            String sb5 = sb4.toString();
            String str12 = WebFragment.pageStartTime + "";
            String str13 = WebFragment.pageLeftTime + "";
            String str14 = WebFragment.pageLoadTime + "";
            String str15 = WebFragment.pageClickCount + "";
            String str16 = map.get("transId");
            String str17 = map.get("actCode");
            String str18 = map.get("page");
            String str19 = map.get("titleName");
            String str20 = map.get("version");
            String str21 = map.get("clientType");
            String str22 = map.get("urlApp");
            String str23 = map.get("touchcode");
            String str24 = map.get("bizcode");
            String str25 = map.get("remark2");
            String str26 = map.get("biz_proecess");
            String str27 = map.get("page_new_old_user");
            String str28 = map.get("upType");
            String str29 = map.get("remark3");
            String str30 = map.get("remark4");
            String valueOf = String.valueOf(BaseActivity.appOnCreateTime);
            String string = App.getInstance().getString(2131886969);
            if ("0".equals(defaultPhoneNumber)) {
                defaultPhoneNumber = "-";
                str4 = string;
                str3 = "-";
                str2 = "-";
            } else {
                str2 = currentProvinceCode;
                str3 = currentCityCode;
                str4 = string;
            }
            if (TextUtils.isEmpty(str16)) {
                str5 = "";
                str6 = "";
                str7 = "";
            } else {
                str5 = str25;
                str6 = str26;
                str7 = str27;
            }
            arrayList.add(optString2);
            arrayList.add(str);
            arrayList.add(optString4);
            arrayList.add(optString5);
            arrayList.add(optString6);
            arrayList.add(optString7);
            arrayList.add(optString8);
            arrayList.add(optString9);
            arrayList.add(optString10);
            arrayList.add(optString11);
            arrayList.add(deviceID);
            arrayList.add(defaultPhoneNumber);
            arrayList.add(str2);
            arrayList.add(str3);
            arrayList.add(sb3);
            arrayList.add(sb5);
            arrayList.add(str12);
            arrayList.add(str13);
            arrayList.add(str14);
            arrayList.add(str15);
            arrayList.add(str16);
            arrayList.add(str17);
            arrayList.add(str18);
            arrayList.add(str19);
            arrayList.add(str20);
            arrayList.add(str21);
            arrayList.add(str22);
            arrayList.add(str23);
            arrayList.add(str24);
            arrayList.add(str5);
            arrayList.add(str6);
            arrayList.add(str7);
            arrayList.add(str28);
            arrayList.add(str29);
            arrayList.add(str30);
            arrayList.add(str11);
            arrayList.add(valueOf);
            arrayList.add(str4);
            MsLogUtil.m7979d("getParamsString", "=======H5Info==开始======\n");
            String stringBuilder = getStringBuilder(arrayList);
            MsLogUtil.m7979d("getParamsString", "=======H5Info==结束======\n");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("url", str10);
            jSONObject2.put("value", stringBuilder);
            return !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getImageInfo(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(DeviceHelper.getDeviceID(false));
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        arrayList.add((TextUtils.isEmpty(currentPhoneNumber) || currentPhoneNumber.equals("0")) ? "-" : "-");
        arrayList.add(str);
        arrayList.add(App.getPvLogSessionId());
        arrayList.add(str8);
        arrayList.add(str2);
        arrayList.add(str3);
        arrayList.add(str4);
        arrayList.add(str5);
        arrayList.add(activity.getString(2131886969));
        arrayList.add(str6);
        arrayList.add(str7);
        return getStringBuilder(arrayList);
    }

    public static String getSdkInfo(Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(map.get("transId"));
        arrayList.add(map.get("actCode"));
        arrayList.add(map.get("titleName"));
        arrayList.add(map.get("currentPhoneNumber"));
        arrayList.add(map.get("provId"));
        arrayList.add(map.get("cityId"));
        arrayList.add(map.get("upTime"));
        arrayList.add(map.get("version"));
        arrayList.add(map.get("clientType"));
        arrayList.add(map.get("urlApp"));
        arrayList.add(map.get("remark1"));
        arrayList.add(map.get("remark2"));
        arrayList.add(map.get("remark3"));
        arrayList.add(map.get("sessionid"));
        arrayList.add(map.get("biz_proecess"));
        return getStringBuilder(arrayList);
    }

    public static Map<String, String> getPvCommon(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        HashMap hashMap = new HashMap();
        UserManager userManager = UserManager.getInstance();
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String str11 = "";
        String str12 = "";
        if (!TextUtils.isEmpty(str6)) {
            str11 = getValueByName(str6, "touchcode");
            str12 = getValueByName(str6, "bizcode");
        }
        String currentPhoneNumber = App.hasLogined() ? userManager.getCurrentPhoneNumber() : "";
        String str13 = "";
        if (App.hasLogined()) {
            String currentPhoneType = userManager.getCurrentPhoneType();
            if (UserManager.getInstance().isYiwang()) {
                str13 = "异网";
            } else if ("01".equals(currentPhoneType)) {
                str13 = "本网-移网";
            } else if ("02".equals(currentPhoneType)) {
                str13 = "固话";
            } else {
                str13 = "03".equals(currentPhoneType) ? "宽带" : "其他";
            }
        }
        hashMap.put("transId", getStringValue(str));
        hashMap.put("actCode", getStringValue(str2));
        hashMap.put("upType", getStringValue(str3));
        hashMap.put("menuId", getStringValue(str4));
        hashMap.put("titleName", getStringValue(str5));
        hashMap.put("urlApp", getStringValue(str6));
        hashMap.put("currentPhoneNumber", currentPhoneNumber);
        hashMap.put("cityId", UserManager.getInstance().getUserAreaid());
        hashMap.put("provId", UserManager.getInstance().getCurrentProvinceCode());
        hashMap.put("remark1", DeviceHelper.getDeviceID(true));
        hashMap.put("remark2", str13);
        hashMap.put("remark3", getStringValue(str7));
        hashMap.put("remark4", getStringValue(str8));
        hashMap.put("version", activity.getResources().getString(2131886969));
        hashMap.put("clientType", "Android");
        hashMap.put("touchcode", getStringValue(str11));
        hashMap.put("bizcode", getStringValue(str12));
        hashMap.put("sessionid", App.getPvLogSessionId());
        hashMap.put("biz_proecess", getStringValue(str9));
        hashMap.put("baseConvert", "YES");
        hashMap.put("upTime", format);
        return hashMap;
    }

    private static String getStringValue(String str) {
        return TextUtils.isEmpty(str) ? "-" : str;
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

    public static String getInfo(String str, List<String> list) {
        try {
            String stringBuilder = getStringBuilder(list);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", str);
            jSONObject.put("value", stringBuilder);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getStringBuilder(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < list.size()) {
            String str = list.get(i);
            if (TextUtils.isEmpty(str)) {
                str = "-";
            }
            if (str.length() > 300) {
                str = str.substring(0, 299);
            }
            sb.append(str);
            i++;
            if (i < list.size()) {
                sb.append(WPBusinessInfoBean.SPLIT);
            }
        }
        return sb.toString();
    }
}
