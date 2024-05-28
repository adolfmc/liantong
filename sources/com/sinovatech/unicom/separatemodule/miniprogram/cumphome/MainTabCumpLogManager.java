package com.sinovatech.unicom.separatemodule.miniprogram.cumphome;

import android.content.Context;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MainTabCumpLogManager {
    public static String TAG = "MainTabCumpLogManager";
    private static MainTabCumpLogManager instance;
    private Context context;

    public static synchronized MainTabCumpLogManager getInstance(Context context) {
        MainTabCumpLogManager mainTabCumpLogManager;
        synchronized (MainTabCumpLogManager.class) {
            if (instance == null) {
                synchronized (MainTabCumpLogManager.class) {
                    if (instance == null) {
                        instance = new MainTabCumpLogManager(context);
                    }
                }
            }
            mainTabCumpLogManager = instance;
        }
        return mainTabCumpLogManager;
    }

    private MainTabCumpLogManager(Context context) {
        this.context = context;
    }

    public void log_SCE01_Sub01(String str, String str2, String str3) {
        PvCurrencyLogUtils.pvHomeCumpLauncherLog(str, str2, "1", "1", "", "", str3, "", "");
    }

    public void log_SCE01_Sub02(String str, String str2, String str3) {
        try {
            PvCurrencyLogUtils.pvHomeCumpLauncherLog(str, str2, "1", "2", "", "", str3, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log_SCE01_Sub03(String str, String str2, String str3) {
        try {
            PvCurrencyLogUtils.pvHomeCumpLauncherLog(str, str2, "1", "3", "", "", str3, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log_SCE01_Sub04(String str, String str2, String str3) {
        try {
            PvCurrencyLogUtils.pvHomeCumpLauncherLog(str, str2, "1", "4", "", "", str3, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log_SCE01_Sub05(String str, String str2) {
        try {
            PvCurrencyLogUtils.pvHomeCumpLauncherLog(str, str2, "1", "5", "", "", "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log_SCE01_Sub06(String str, String str2, String str3) {
        try {
            PvCurrencyLogUtils.pvHomeCumpLauncherLog(str, str2, "1", "6", "", "", str3, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log_SCE02_Sub01(String str, String str2, String str3, String str4) {
        try {
            PvCurrencyLogUtils.pvHomeCumpLauncherLog(str, "", "2", "1", str2, str3, "", str4, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log_SCE02_Sub02(String str, String str2, String str3, String str4, String str5) {
        try {
            PvCurrencyLogUtils.pvHomeCumpLauncherLog(str, str2, "2", "2", str3, str4, "", str5, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log_SCE02_Sub03(String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            PvCurrencyLogUtils.pvHomeCumpLauncherLog(str, str2, "2", "3", str3, str4, str5, str6, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log_SCE02_Sub04(String str, String str2, String str3, String str4, String str5) {
        try {
            PvCurrencyLogUtils.pvHomeCumpLauncherLog(str, str2, "2", "4", str3, str4, "", str5, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
