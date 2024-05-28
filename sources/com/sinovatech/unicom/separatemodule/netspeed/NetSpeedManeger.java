package com.sinovatech.unicom.separatemodule.netspeed;

import android.app.Activity;
import com.chinaunicon.jtwifilib.core.global.JtApp;
import com.chinaunicon.jtwifilib.jtcommon.XWIFI;
import com.sinovatech.unicom.p318ui.App;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NetSpeedManeger {
    public static final String INIT = "init";
    public static final String PAUSE = "pause";
    public static final String XWIFI_END_NETWORK_STATUS = "XWIFI.END_NETWORK_STATUS";
    public static final String XWIFI_END_SPEED = "XWIFI.END_SPEED";
    public static final String XWIFI_END_UPLOAD_SPEED = "XWIFI.END_UPLOAD_SPEED";
    public static final String XWIFI_GET_INTERNET_END = "XWIFI.GET_INTERNET_END";
    public static final String XWIFI_GET_INTERNET_START = "XWIFI.GET_INTERNET_START";
    public static final String XWIFI_SPEEDING = "XWIFI.SPEEDING";
    public static final String XWIFI_START_NETWORK_STATUS = "XWIFI.START_NETWORK_STATUS";
    public static final String XWIFI_START_SPEED = "XWIFI.START_SPEED";
    public static final String XWIFI_START_UPLOAD_SPEED = "XWIFI.START_UPLOAD_SPEED";
    public static final String XWIFI_SUCCESS = "XWIFI.SUCCESS";
    private static volatile XWIFI xwifi;

    private NetSpeedManeger() {
    }

    public static void initSDk() {
        JtApp.getInstance().init(App.getInstance(), true);
    }

    public static XWIFI getInstance(Activity activity) {
        if (xwifi == null) {
            synchronized (XWIFI.class) {
                if (xwifi == null) {
                    xwifi = new XWIFI.Builder(activity).create();
                }
            }
        }
        return xwifi;
    }
}
