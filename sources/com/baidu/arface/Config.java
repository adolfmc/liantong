package com.baidu.arface;

import android.os.Environment;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Config {
    public static final String AR_ASSETS_SOURCE = "arsource";
    public static final String AR_FILE = "ar_file";
    public static final String AR_KEY = "ar_key";
    public static final String AR_TYPE = "ar_type";
    public static final String apiKey = "2288883fb087c4a37fbaf12bce65916e";
    public static final String appId = "10000";
    public static final String secretKey = "";
    public static String sDataPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String AR_SOURCE_PATH = sDataPath + "/arsource/main/";
    public static String AR_SOURCE_EXT_PATH = sDataPath + "/arsource/ext/";
    public static String AR_FACE_MASKS_CONFIG_PATH = AR_SOURCE_PATH + "masksStyle.json";
    public static final String AR_FACE_BEAUTY_FILTER_PATH = AR_SOURCE_PATH + "global/res/filter";
    public static final String AR_MODELS_PATH = AR_SOURCE_PATH + "Models/";
    public static String AR_FACE_PHONE_LIST_CONFIG_PATH = AR_SOURCE_PATH + "vip_list.json";
    public static String AR_FILTER_RES_FILE_PATH = AR_SOURCE_PATH + "global";
    public static String AR_FILTER_CONFIG_FILE_PATH = "/res/filter/filter_config.json";
    public static String SP_KEY_ARFACE_MAIN_RES_VERSION = "arface_main_res_version";
    public static int SP_VALUE_ARFACE_MAIN_RES_VERSION = 2;
}
