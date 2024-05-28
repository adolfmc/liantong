package com.sinovatech.unicom.separatemodule.Log;

import android.content.Context;
import android.text.TextUtils;
import com.blankj.utilcode.util.ActivityUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.datacenter.HistoryDataCenter;
import com.sinovatech.unicom.basic.p314po.HistoryUpDataCenter;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p315ui.CityChangeManager;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJConfigUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJPVParamsUtil;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PvCurrencyLogUtils {
    public static final int BAOGUANG = 3;
    public static final int DIANJI = 2;
    public static final int LIULAN = 1;
    private static String TAG = "PvCurrencyLogUtils";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static String timeS = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$pvCurrency$0(String str) throws Exception {
    }

    public static void pvLogMainDJ(String str, String str2, String str3, String str4) {
        pvLogMainDJ(str, str2, str3, str4, "", "", "", "");
    }

    public static void pvLogMainDJ(String str, String str2, String str3, String str4, String str5) {
        pvLogMainDJ(str, str2, str3, str4, str5, "", "", "");
    }

    public static void pvLogMainDJ(String str, String str2, String str3, String str4, String str5, String str6) {
        pvLogMainDJ(str, str2, str3, str4, str5, str6, "", "");
    }

    public static void pvLogMainDJNoUpType(String str, String str2, String str3, String str4, String str5, String str6) {
        pvLogMainDJ(str, str4, str5, str3, str2, "", str6, "");
    }

    public static void pvLogMainDJNoUpType(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        pvLogMainDJ(str, str4, str5, str3, str2, str7, str6, str8);
    }

    public static void pvLogMainDJ(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        pvLogMainDJ(str, str2, str3, str4, str5, str6, str7, "");
    }

    public static void pvLogMainDJ(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        pvCurrencyLog("homePage", 2, str, str2, str3, str4, str5, str6, str7, str8);
    }

    public static void pvLogMainDJ(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        pvCurrencyLog(str9, 2, str, str2, str3, str4, str5, str6, str7, str8);
    }

    public static void pvLogMainLL(String str, String str2, String str3, String str4) {
        pvCurrencyLog("homePage", 1, str, str2, str3, str4, "", "", "", "");
    }

    public static void pvLogMainLL(String str, String str2, String str3, String str4, String str5) {
        pvCurrencyLog("homePage", 1, str, str2, str3, str4, str5, "", "", "");
    }

    public static void pvLogMainLL(String str, String str2, String str3, String str4, String str5, String str6) {
        pvCurrencyLog("homePage", 1, str, str2, str3, str4, str5, str6, "", "");
    }

    public static void pvLogMainDHLL(String str, String str2, String str3) {
        MsLogUtil.m7979d("pvLogMainDHLL", "remark3:" + str + "----remark4:" + str2);
        pvCurrencyLog("homePage", 1, "107", "", str, "", "首页-金刚区", "", str2, str3);
    }

    public static void pvLogKacaoBG(String str, String str2, String str3, String str4, String str5) {
        pvCurrencyLog("", 2, str, "", str4, str3, str2, "", str5, "");
    }

    public static void pvLogMainBG(String str, String str2, String str3, String str4) {
        pvCurrencyLog("homePage", 3, str, str2, str3, str4, "", "", "", "");
    }

    public static void pvLogMainBG(String str, String str2, String str3, String str4, String str5) {
        pvCurrencyLog("homePage", 3, str, str2, str3, str4, "", str5, "", "");
    }

    public static void pvLogMainBG(String str, String str2, String str3, String str4, String str5, String str6) {
        pvCurrencyLog("homePage", 3, str, str2, str3, str4, str5, str6, "", "");
    }

    public static void pvLogUserBG(String str, String str2, String str3, String str4, String str5) {
        pvCurrencyLog("myPage", 3, str, str2, str3, str4, str5, "", "", "");
    }

    public static void pvLogMainBG(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        pvCurrencyLog("homePage", 3, str, str2, str3, str4, str5, str7, str6, "");
    }

    public static void pvLogMainBG(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        pvCurrencyLog("homePage", 3, str, str2, str3, str4, str5, str7, str6, str8);
    }

    public static void pvLogMainYiJianFanKui(boolean z, Context context) {
        String str;
        int i;
        String homeFanKui = new ConfigManager(context).getHomeFanKui();
        if (z) {
            str = "点击意见反馈入口";
            i = 2;
        } else {
            str = "展示";
            i = 3;
        }
        pvCurrencyLog("APP", i, "S2ndpage1180", homeFanKui, str, "意见反馈", "客户端吐槽入口", "", "", "");
    }

    public static void pvLogInitOmo(String str, String str2) {
        pvCurrencyLog("homePage", 2, "S2ndpage1204", "", str, "流策预加载接口调用失败", str2, "", "", "");
    }

    public static void pvLogUserDJ(String str, String str2, String str3, String str4, String str5, String str6) {
        pvCurrencyLog("myPage", 2, str, str4, str5, str3, str2, "", str6, "");
    }

    public static void pvLogUserDJ(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        pvCurrencyLog("myPage", 2, str, str4, str5, str3, str2, str7, str6, str8);
    }

    public static void pvLogHomeJingangqu(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        pvCurrencyLog("homePage", 2, str, str4, str5, str3, str2, "", str6, str7);
    }

    public static void pvlogUserBG(String str, String str2, String str3, String str4) {
        pvLogUserBG(str, "", str4, str3, str2);
    }

    public static void pvLogUserLL(String str, String str2, String str3, String str4, String str5) {
        pvCurrencyLog("myPage", 1, str, str2, str3, str4, str5, "", "", "");
    }

    public static void pvLogDJCamera2Api(String str, String str2, String str3) {
        pvLogMainDJ("S2ndpage1199", PhotographActivity.url, str2, str, PhotographActivity.serviceName, "按钮", "", str3);
    }

    public static void pvLogUserBG(String str, String str2, String str3, String str4) {
        pvCurrencyLog("myPage", 3, str, str2, str3, str4, "", "", "", "");
    }

    public static void pvSwitchApp(String str, String str2, String str3) {
        pvCurrency(str, str.substring(0, 3), "2", str2, "", "", "", "1", str3, "", "", "", "");
    }

    public static void pvAnquanzhongxin(String str, String str2, String str3, String str4, String str5) {
        pvCurrency("S2ndpage1164", "", "2", "", "", LoginManager.getAccountType(), str4, "1", str, str2, str3, str5, "");
    }

    public static void pvAnquanzhongxin(String str, String str2) {
        pvCurrency("S2ndjspage1164", "", "2", "", "", LoginManager.getAccountType(), str, "1", "", "二次验证JS", "调用", str2, "");
    }

    public static void pvXuanCaiV3(String str, String str2, String str3, String str4, String str5) {
        pvCurrency("S2ndpage1220", "", "2", "", zhuanHuanText(str2), LoginManager.getAccountType(), zhuanHuanText(str4), "1", zhuanHuanText(str), "炫彩v3活体", zhuanHuanText(str3), zhuanHuanText(str5), "");
    }

    public static void pvXiala(String str, String str2, String str3) {
        pvCurrency("1010409", "", "2", str, "", LoginManager.getAccountType(), str3, "1", "", str2, "", "", "");
    }

    public static void pvLogTabClick(String str, String str2) {
        pvCurrencyLog("myPage", 2, str, "", "", "", str2, "", "", "");
        UnicomHomeLogUtils.getInstance().clickLog(str, str2);
    }

    public static void pvLogMainOmoBG(String str, String str2, String str3) {
        timeS = String.valueOf(System.currentTimeMillis());
        pvLogBG("homePage", "13", "", str2, str3, "首页-TAB-停留", "", timeS, "");
        if (TYCJConfigUtil.isWhiteUrl("H5Info", str, "")) {
            TYCJBoxManager.getInstance().collectH5Info(new JSONObject(), TYCJPVParamsUtil.getHomeTabMap(str, str2, str3));
        }
    }

    public static void pvloginPasswoedTip(String str, String str2) {
        pvCurrency("S2ndpage1192", "", str2, "", "", LoginManager.getAccountType(), "", "1", "客户端", "", str, "", "");
    }

    public static void pvLogDJ(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        pvCurrencyLog(str, 2, str2, str3, str4, str5, str6, str7, str8, str9);
    }

    public static void pvLogBG(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        pvCurrencyLog(str, 3, str2, str3, str4, str5, str6, str7, str8, str9);
    }

    public static void pvLogLL(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        pvCurrencyLog(str, 1, str2, str3, str4, str5, str6, str7, str8, str9);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00a3 A[Catch: Exception -> 0x00c7, TryCatch #0 {Exception -> 0x00c7, blocks: (B:2:0x0000, B:6:0x000c, B:10:0x0018, B:14:0x0024, B:18:0x0030, B:22:0x003c, B:26:0x0048, B:30:0x0054, B:32:0x0060, B:34:0x0067, B:36:0x006f, B:38:0x0077, B:40:0x007f, B:42:0x0087, B:44:0x008f, B:49:0x009d, B:51:0x00a3, B:53:0x00ab, B:55:0x00af, B:56:0x00b7, B:57:0x00bf), top: B:62:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00af A[Catch: Exception -> 0x00c7, TryCatch #0 {Exception -> 0x00c7, blocks: (B:2:0x0000, B:6:0x000c, B:10:0x0018, B:14:0x0024, B:18:0x0030, B:22:0x003c, B:26:0x0048, B:30:0x0054, B:32:0x0060, B:34:0x0067, B:36:0x006f, B:38:0x0077, B:40:0x007f, B:42:0x0087, B:44:0x008f, B:49:0x009d, B:51:0x00a3, B:53:0x00ab, B:55:0x00af, B:56:0x00b7, B:57:0x00bf), top: B:62:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00b7 A[Catch: Exception -> 0x00c7, TryCatch #0 {Exception -> 0x00c7, blocks: (B:2:0x0000, B:6:0x000c, B:10:0x0018, B:14:0x0024, B:18:0x0030, B:22:0x003c, B:26:0x0048, B:30:0x0054, B:32:0x0060, B:34:0x0067, B:36:0x006f, B:38:0x0077, B:40:0x007f, B:42:0x0087, B:44:0x008f, B:49:0x009d, B:51:0x00a3, B:53:0x00ab, B:55:0x00af, B:56:0x00b7, B:57:0x00bf), top: B:62:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00bf A[Catch: Exception -> 0x00c7, TRY_LEAVE, TryCatch #0 {Exception -> 0x00c7, blocks: (B:2:0x0000, B:6:0x000c, B:10:0x0018, B:14:0x0024, B:18:0x0030, B:22:0x003c, B:26:0x0048, B:30:0x0054, B:32:0x0060, B:34:0x0067, B:36:0x006f, B:38:0x0077, B:40:0x007f, B:42:0x0087, B:44:0x008f, B:49:0x009d, B:51:0x00a3, B:53:0x00ab, B:55:0x00af, B:56:0x00b7, B:57:0x00bf), top: B:62:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void pvCurrencyLog(java.lang.String r14, int r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils.pvCurrencyLog(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static void pvAppTime(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            String str3 = str;
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            pvCurrency(str3, "", "3", "", str2, LoginManager.getAccountType(), "", "0", "", "APP停留时长", "", "", "");
        } catch (Exception e) {
            String str4 = TAG;
            MsLogUtil.m7977e(str4, "pvCurrencyLog:" + e.getMessage());
        }
    }

    public static void pvHuoTiFaceVideoZDLog(String str, String str2, String str3, String str4) {
        pvCurrency("S2ndpage1154", "", "", "", str2, LoginManager.getAccountType(), str4, "1", "", str, "", str3, "");
    }

    public static void pvHuoTiFaceVideoBDLog(String str, String str2, String str3, String str4) {
        pvCurrency("S2ndpage1154", "", "", "", str2, LoginManager.getAccountType(), str4, "0", "", str, "", str3, "");
    }

    public static void pvVideoAddWaterFail(String str, String str2, String str3, String str4) {
        pvCurrency("S2ndpage1154", "", "", str4, str3, LoginManager.getAccountType(), "", "0", str, str2, "", "", "");
    }

    public static void pvJianBanZDLog(String str) {
        String str2;
        String str3;
        if ("1".equals(str)) {
            str3 = "协议弹窗进入简版";
            str2 = "协议弹窗";
        } else if ("2".equals(str)) {
            str3 = "设置弹窗解除授权";
            str2 = "设置";
        } else if ("3".equals(str)) {
            str3 = "简版内进入正常版本";
            str2 = "简版";
        } else {
            str2 = null;
            str3 = null;
        }
        pvCurrency("S2ndpage1179", "", "2", "", "", LoginManager.getAccountType(), "", "1", str2, str3, "", "", "");
    }

    public static void pvHomeCumpLauncherLog(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        try {
            pvCurrency(str, str2, str4, str5, str6, str7, str8, "0", str3, "", "", str9, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void userDevicePvLog(String str, String str2, String str3, String str4, String str5) {
        try {
            pvCurrency(str, (TextUtils.isEmpty(str) || str.length() < 3) ? "" : str.substring(0, 3), "2", "My page", str2, "", str5, "1", str3, str4, "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toCaoClickPvLog(String str) {
        try {
            pvCurrency("S2ndpage1259", "", "2", "", str, "", "", "1", "更多-吐槽", "吐槽", "客户端", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void pvCurrency(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
        try {
            UserManager userManager = UserManager.getInstance();
            HashMap hashMap = new HashMap();
            hashMap.put("transId", str);
            hashMap.put("bizcode", str2);
            hashMap.put("upTime", "time:" + sdf.format(new Date()));
            hashMap.put("mobile", userManager.getCurrentPhoneNumber());
            hashMap.put("touchcode", str3);
            hashMap.put("provId", userManager.getCurrentProvinceCode());
            hashMap.put("cityId", userManager.getCurrentCityCode());
            hashMap.put("page", str4);
            hashMap.put("urlApp", !TextUtils.isEmpty(str5) ? str5 : "");
            hashMap.put("version", App.getInstance().getResources().getString(2131886969));
            hashMap.put("clientType", "Android");
            hashMap.put("remark1", DeviceHelper.getDeviceID(false));
            hashMap.put("remark2", str6);
            hashMap.put("remark3", str7);
            hashMap.put("remark4", str12);
            hashMap.put("sessionid", App.getPvLogSessionId());
            hashMap.put("biz_proecess", str8);
            hashMap.put("page_new_old_user", CacheDataCenter.getInstance().getPageNewOldUser());
            hashMap.put("actCode", str9);
            hashMap.put("titleName", str10);
            hashMap.put("menuId", str13);
            hashMap.put("upType", str11);
            hashMap.put("baseConvert", "");
            MsLogUtil.m7979d("PV_LOG_INFO", hashMap.toString());
            App.getAsyncHttpClient(5, 5, 5).rxPost(new ConfigManager(App.getInstance()).getStasticsUploadUrlKey(), hashMap).subscribeOn(Schedulers.m1934io()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.Log.-$$Lambda$PvCurrencyLogUtils$rQqJqfR_-fNJwQRMXlsgo8qUOTY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    PvCurrencyLogUtils.lambda$pvCurrency$0((String) obj);
                }
            });
        } catch (Exception e) {
            String str14 = TAG;
            UIUtils.logE(str14, "记录9.0 --> pv日志异常：" + e.getMessage());
        }
    }

    public static String getPostion(String str, int i) {
        int i2 = i + 1;
        try {
            if (i2 < 10) {
                return str + "0" + i2;
            }
            return str + i2;
        } catch (Exception e) {
            String str2 = TAG;
            MsLogUtil.m7977e(str2, "getPostion() = " + e.getMessage());
            return "";
        }
    }

    public static String getActCode(String str) {
        char c = 65535;
        try {
            int hashCode = str.hashCode();
            switch (hashCode) {
                case 48626:
                    if (str.equals("101")) {
                        c = 0;
                        break;
                    }
                    break;
                case 48627:
                    if (str.equals("102")) {
                        c = 1;
                        break;
                    }
                    break;
                case 48628:
                    if (str.equals("103")) {
                        c = 2;
                        break;
                    }
                    break;
                case 48629:
                    if (str.equals("104")) {
                        c = 3;
                        break;
                    }
                    break;
                case 48630:
                    if (str.equals("105")) {
                        c = 4;
                        break;
                    }
                    break;
                case 48631:
                    if (str.equals("106")) {
                        c = 5;
                        break;
                    }
                    break;
                case 48632:
                    if (str.equals("107")) {
                        c = 6;
                        break;
                    }
                    break;
                case 48633:
                    if (str.equals("108")) {
                        c = 7;
                        break;
                    }
                    break;
                case 48634:
                    if (str.equals("109")) {
                        c = '\b';
                        break;
                    }
                    break;
                default:
                    switch (hashCode) {
                        case 48656:
                            if (str.equals(CityChangeManager.DEFAULT_SELECT_CITY_CODE)) {
                                c = '\t';
                                break;
                            }
                            break;
                        case 48657:
                            if (str.equals("111")) {
                                c = '\n';
                                break;
                            }
                            break;
                        case 48658:
                            if (str.equals("112")) {
                                c = 11;
                                break;
                            }
                            break;
                    }
            }
            switch (c) {
                case 0:
                    return "背景区域";
                case 1:
                    return "余量展示";
                case 2:
                    return "本网精准营销";
                case 3:
                    return "软登录";
                case 4:
                    return "首页-OMO";
                case 5:
                    return "首页-提醒组件";
                case 6:
                    return "首页-金刚区";
                case 7:
                    return "首页-快讯";
                case '\b':
                    return "首页标签";
                case '\t':
                    return "异网卡片";
                case '\n':
                    if (App.hasLogined()) {
                        if (UserManager.getInstance().isYiwang()) {
                            return "首页-异网-轮播图";
                        }
                    }
                    return "首页-未登录-轮播图";
                case 11:
                    return "未登录入口";
                default:
                    return "";
            }
        } catch (Exception e) {
            String str2 = TAG;
            MsLogUtil.m7977e(str2, "getActCode() = " + e.getMessage());
            return "";
        }
    }

    public static String zhuanHuanText(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static void sendServiceViewPvLog(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        pvCurrency(str, (TextUtils.isEmpty(str) || str.length() <= 3) ? "" : str.substring(0, 3), str6, str3, str5, LoginManager.getAccountType(), "", str7, str2, str4, str8, "", "");
    }

    public static void sendServicePvLog(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        pvCurrency(str, (TextUtils.isEmpty(str) || str.length() <= 3) ? "" : str.substring(0, 3), str6, str3, str5, LoginManager.getAccountType(), "", str7, str2, str4, str8, "", str9);
    }

    public static void sendServicePvLog(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        pvCurrency(str, (TextUtils.isEmpty(str) || str.length() <= 3) ? "" : str.substring(0, 3), str6, str3, str5, LoginManager.getAccountType(), "", str7, str2, str4, str8, "", "");
    }

    public static void sendYwUserPvLog(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        pvCurrency(str, (TextUtils.isEmpty(str) || str.length() <= 3) ? "" : str.substring(0, 3), str6, str3, str5, LoginManager.getAccountType(), "", str7, str2, str4, str8, "", "");
    }

    public static void pvLogFuWu(String str, String str2, String str3, String str4, String str5) {
        MsLogUtil.m7979d("服务大厅", "日志：transId:" + str + ",titleName:" + str4 + ",remark3:" + str5);
        pvCurrencyLog("fuWuPage", 2, str, str3, str5, str4, str2, "", "", "");
    }

    public static void pvLogLive(String str, int i, String str2, String str3, String str4, String str5, String str6) {
        MsLogUtil.m7979d("直播pv", "日志：transId:szhibo0001,titleName:" + str3 + ",upType:" + str4 + ",remark3:" + str2 + ",remark4:" + str5 + ",menuId:" + str6 + ",url:" + str);
        pvCurrencyLog("直播", i, "szhibo0001", str, str2, str3, "直播Live", str4, str5, str6);
    }

    public static void addMenu(MenuEntity menuEntity) {
        try {
            StatisticsEntity statisticsEntity = new StatisticsEntity();
            statisticsEntity.setMobile(App.hasLogined() ? UserManager.getInstance().getCurrentPhoneNumber() : "");
            statisticsEntity.setTitleName(menuEntity.getMenuTitle());
            statisticsEntity.setMenuId(menuEntity.getNavJson());
            statisticsEntity.setTime(String.valueOf(System.currentTimeMillis() / 1000));
            HistoryDataCenter historyDataCenter = new HistoryDataCenter(ActivityUtils.getTopActivity());
            HistoryUpDataCenter historyUpDataCenter = new HistoryUpDataCenter(ActivityUtils.getTopActivity());
            historyDataCenter.insertStatisticsRecord(statisticsEntity);
            historyUpDataCenter.insertStatisticsRecord(statisticsEntity);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }
}
