package com.sinovatech.unicom.basic.p315ui.home.manager;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeLogEntity;
import com.sinovatech.unicom.basic.p315ui.utils.UnicomHomeConstants;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.sinovatech.unicom.basic.ui.home.manager.UnicomHomeLogUtils */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomHomeLogUtils {
    public static final String LOG_TYPE_BG = "LOG_TYPE_BG";
    public static final String LOG_TYPE_CITY = "LOG_TYPE_CITY";
    public static final String LOG_TYPE_HF = "LOG_TYPE_HF";
    public static final String LOG_TYPE_JGQ = "LOG_TYPE_JGQ";
    public static final String LOG_TYPE_RDL_HM = "LOG_TYPE_RDL_HM";
    public static final String LOG_TYPE_RDL_RQ = "LOG_TYPE_RDL_RQ";
    public static final String LOG_TYPE_SEARCH = "LOG_TYPE_SEARCH";
    public static final String LOG_TYPE_SERVICE = "LOG_TYPE_SERVICE";
    public static final String LOG_TYPE_TAB = "LOG_TYPE_TAB";
    public static final String LOG_TYPE_TITLE = "LOG_TYPE_TITLE";
    public static final String LOG_TYPE_TOP_TAB = "LOG_TYPE_TOP_TAB";
    public static final String LOG_TYPE_UNLOGIN = "LOG_TYPE_UNLOGIN";
    public static final String LOG_TYPE_YWJZ = "LOG_TYPE_YWJZ";
    private static final String TAG = "UnicomHomeLogUtils";
    private static UnicomHomeLogUtils instance = null;
    public static boolean isBGVisiable = true;
    public static boolean isHFVisiable = true;
    public static boolean isJQUVisiable = true;
    public static boolean isRDLVisiable = true;
    public static boolean isUNLOGINVisiable = true;
    public static boolean isYWJZVisiable = true;
    public HashMap<String, List<HomeLogEntity>> logLockMap;
    public HashMap<String, List<HomeLogEntity>> logMap;
    private final String[] trainsId = {"1090201", "1010106", "1990101", "2990101", "5990101"};

    public static synchronized UnicomHomeLogUtils getInstance() {
        UnicomHomeLogUtils unicomHomeLogUtils;
        synchronized (UnicomHomeLogUtils.class) {
            if (instance == null) {
                synchronized (UnicomHomeLogUtils.class) {
                    if (instance == null) {
                        instance = new UnicomHomeLogUtils();
                    }
                }
            }
            unicomHomeLogUtils = instance;
        }
        return unicomHomeLogUtils;
    }

    private UnicomHomeLogUtils() {
        this.logMap = new HashMap<>();
        this.logLockMap = new HashMap<>();
        this.logMap = new HashMap<>();
        this.logLockMap = new HashMap<>();
    }

    public HashMap<String, List<HomeLogEntity>> getLogMap() {
        return this.logMap;
    }

    public HashMap<String, List<HomeLogEntity>> getLogLockMap() {
        return this.logLockMap;
    }

    public void putLogData(String str, List<HomeLogEntity> list) {
        this.logMap.put(str, list);
        MsLogUtil.m7979d("baoguang", "可以落曝光了" + this.logMap.toString());
    }

    public void putLockLogData(String str, List<HomeLogEntity> list) {
        this.logLockMap.put(str, list);
        MsLogUtil.m7979d("baoguang", "可以落曝光了" + this.logMap.toString());
    }

    public void putLogData(String str, HomeLogEntity homeLogEntity) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(homeLogEntity);
        this.logMap.put(str, arrayList);
    }

    public void putLockLogData(String str, HomeLogEntity homeLogEntity) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(homeLogEntity);
        this.logLockMap.put(str, arrayList);
    }

    public void uploadBGLog(int i) {
        MsLogUtil.m7979d("JIA_BG_LOG", "首页曝光日志");
        HashMap<String, List<HomeLogEntity>> logLockMap = getLogLockMap();
        if (i == 0) {
            logLockMap.putAll(getLogMap());
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, List<HomeLogEntity>> entry : logLockMap.entrySet()) {
            String key = entry.getKey();
            if (!TextUtils.equals(LOG_TYPE_HF, key) || isHFVisiable) {
                if ((!TextUtils.equals(LOG_TYPE_RDL_RQ, key) && !TextUtils.equals(LOG_TYPE_RDL_HM, key)) || isRDLVisiable) {
                    if (!TextUtils.equals(LOG_TYPE_UNLOGIN, key) || isUNLOGINVisiable) {
                        if (!TextUtils.equals(LOG_TYPE_JGQ, key) || isJQUVisiable) {
                            if (!TextUtils.equals(LOG_TYPE_YWJZ, key) || isYWJZVisiable) {
                                if (!TextUtils.equals(LOG_TYPE_TAB, key) || UnicomHomeConstants.isTabVisiable) {
                                    if (!TextUtils.equals(LOG_TYPE_BG, key) || UnicomHomeConstants.isBackGround) {
                                        for (HomeLogEntity homeLogEntity : entry.getValue()) {
                                            arrayList.add(homeLogEntity);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String title = TextUtils.isEmpty(((HomeLogEntity) arrayList.get(i2)).getTitle()) ? "-" : ((HomeLogEntity) arrayList.get(i2)).getTitle();
            if (TextUtils.isEmpty(stringBuffer.toString())) {
                stringBuffer.append(title);
            } else {
                stringBuffer.append("@@@" + title);
            }
            if (TextUtils.isEmpty(stringBuffer2.toString())) {
                stringBuffer2.append(((HomeLogEntity) arrayList.get(i2)).getTransId());
            } else {
                stringBuffer2.append("," + ((HomeLogEntity) arrayList.get(i2)).getTransId());
            }
        }
        MsLogUtil.m7979d("JIA_BG_LOG", "========== 名称 ==========");
        MsLogUtil.m7979d("JIA_BG_LOG", stringBuffer.toString());
        MsLogUtil.m7979d("JIA_BG_LOG", "========== ID ==========");
        MsLogUtil.m7979d("JIA_BG_LOG", stringBuffer2.toString());
        UnicomCollectManager.getInstance().lightCollect(CollectDataEntity.newBuilder().setExposure_Name(stringBuffer.toString()).setPageName("首页").setCodeId(stringBuffer2.toString()).build());
    }

    public void clickLog(String str, String str2) {
        clickLog(str, str2, "", "");
    }

    public void clickLog(String str, String str2, String str3, String str4) {
        String str5 = "";
        if (!TextUtils.isEmpty(str) && str.length() >= 3) {
            str5 = str.substring(0, 3);
        }
        if (!TextUtils.isEmpty(str3) && str3.length() >= 3) {
            str3 = str3.substring(1, 3);
        }
        CollectDataEntity build = CollectDataEntity.newBuilder().setCodeId(str).setPbName(str2).setPageName("首页").setStoreyCode(str5).setMarket_city(str4).setMarket_provine(str3).build();
        if (Arrays.asList(this.trainsId).contains(str) || "首页-下拉刷新".equals(str2)) {
            UnicomCollectManager.getInstance().clickCollect(build, false);
        } else {
            UnicomCollectManager.getInstance().clickCollect(build);
        }
    }

    public void clickUserLog(String str, String str2, String str3) {
        UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setCodeId(str).setPbName(str2).setPageName("首页").setTargetUrl(str3).build());
    }

    public void removeLog(String str) {
        this.logMap.remove(str);
        this.logLockMap.remove(str);
    }

    public void removeAll() {
        this.logLockMap.clear();
        this.logMap.clear();
    }
}
