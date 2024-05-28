package com.baidu.minivideo.arface;

import android.os.Environment;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DuArResConfig {
    public static final String AR_ASSETS_SOURCE = "file:///android_asset/arsource/";
    public static final String AR_SDK_FOLDER = "4.9";
    private static final String FILTER_CONFIGPATH = "/filter_config.json";
    public static boolean RES_COPY_NEEN = true;
    public static String SP_KEY_ARFACE_MAIN_RES_VERSION = "arface_main_res_version";
    public static int SP_VALUE_ARFACE_MAIN_RES_VERSION = 0;
    private static final String TAG = "DUAR_DuArResConfig";
    private static String sBeautyDir = null;
    private static String sBeautySkinSmallVideoPath = null;
    private static String sBeautySkinStreamPath = null;
    private static String sDataPath = null;
    private static String sDefaltFilterPath = null;
    private static String sExtDataPath = null;
    private static String sFilterEditInitDir = null;
    private static String sFilterInitDir = null;
    private static String sFilterPath = null;
    private static String sFilterYuanTuPath = null;
    private static String sFiltersDir = null;
    private static String sMainDataPath = null;
    private static String sMakeupDir = null;
    private static String sVipListPath = null;
    private static String sdlModelsPath = null;
    public static String sfaceBeautyFilterPath = "global/res/filter";
    private static String sfaceModelsPath;

    public static String getFilterConfig() {
        return "/filter_config.json";
    }

    public DuArResConfig(String str) {
        setPath(str);
    }

    private static void setPath(String str) {
        sDataPath = str + "/";
        sMainDataPath = sDataPath;
        sExtDataPath = sDataPath + "ext/";
        sfaceModelsPath = sMainDataPath + "faceModels/";
        if (TextUtils.isEmpty(str)) {
            sFilterYuanTuPath = "file:///android_asset/arsource/filters/yuantu/yuantu.png";
            sBeautySkinStreamPath = "file:///android_asset/arsource/filter/beauty_skin_stream.png";
            sBeautySkinSmallVideoPath = "file:///android_asset/arsource/filter/beauty_skin_small_video.png";
            sfaceModelsPath = "file:///android_asset/arsource/faceModels/";
            String str2 = Environment.getExternalStorageDirectory() + "/baidu/quanminvideo/arsource";
            sMakeupDir = str2 + "/makeup";
            sBeautyDir = str2 + "/beauty";
            sFilterInitDir = str2 + "/arFilterInit";
            sFilterEditInitDir = str2 + "/arFilterEditInit";
        } else {
            sFilterYuanTuPath = sMainDataPath + "filters/yuantu/yuantu.png";
            sBeautySkinStreamPath = sMainDataPath + "filter/beauty_skin_stream.png";
            sBeautySkinSmallVideoPath = sMainDataPath + "filter/beauty_skin_small_video.png";
            sMakeupDir = sDataPath + "makeup/";
            sBeautyDir = sDataPath + "beauty/";
            sFilterInitDir = sDataPath + "arFilterInit/";
            sFilterEditInitDir = sDataPath + "arFilterEditInit/";
        }
        sFilterPath = sMainDataPath + "global";
        sVipListPath = sMainDataPath + "vip_list.json";
        sFiltersDir = sMainDataPath + "filters";
        sdlModelsPath = sMainDataPath + "dlModels/";
        sDefaltFilterPath = sFiltersDir + "/all";
        sfaceBeautyFilterPath = sMainDataPath + "global/res/filter";
    }

    public static String getFilterYuanTuPath() {
        return sFilterYuanTuPath;
    }

    public static String getDataPath() {
        return sDataPath;
    }

    public static String getExtDataPath() {
        return sExtDataPath;
    }

    public static String getMainDataPath() {
        return sMainDataPath;
    }

    public static String getfaceModelsPath() {
        return sfaceModelsPath;
    }

    public static String getdlModelsPath() {
        return sdlModelsPath;
    }

    public static String getFilterPath() {
        return sFilterPath;
    }

    public static String getDefaultFilterPath() {
        return sDefaltFilterPath;
    }

    public static String getFaceBeautyFilterPath() {
        return sfaceBeautyFilterPath;
    }

    public static String getFileFilterDir() {
        return sFiltersDir;
    }

    public static String getVipListPath() {
        return sVipListPath;
    }

    public static String getMakeupDir() {
        return sMakeupDir;
    }

    public static String getBeautyDir() {
        return sBeautyDir;
    }

    public static String getInitFilterDir(boolean z) {
        return z ? sFilterInitDir : sFilterEditInitDir;
    }

    public static String getBeautySkinSmallVideoPath() {
        return sBeautySkinSmallVideoPath;
    }
}
