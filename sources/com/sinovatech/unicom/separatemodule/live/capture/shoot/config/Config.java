package com.sinovatech.unicom.separatemodule.live.capture.shoot.config;

import android.hardware.Camera;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Config {
    public static final String BDPLAYER_AK = "3927c43912004909bf897578e93bc5f9";
    public static final String BDPLAYER_APPID = "87683699241530982401p";
    public static final boolean DEBUG = false;
    public static final String FACE_BEAUTIFICATION_NAME = "";
    public static final String LICENSE_APPID = "801126566693444609041";
    public static final String appId = "801126566693444609041";
    public static final boolean isPlugin = false;
    private static int mMaxHeight = 0;
    private static int mMaxWidth = 0;
    private static List<Camera.Size> sCammeraSizeList = null;
    private static int sMaxPixe = 0;
    public static final String sdkVersion = "11.4.1";
    public static final String secretKey = "2931cab37bf742169ab44089b55013f9";

    public static void setCamerSize(List<Camera.Size> list) {
        sCammeraSizeList = list;
    }

    public static int getMaxCamerPixe() {
        List<Camera.Size> list;
        if (sMaxPixe <= 0 && (list = sCammeraSizeList) != null && list.size() > 0) {
            for (Camera.Size size : sCammeraSizeList) {
                int i = size.width * size.height;
                if (i > sMaxPixe) {
                    sMaxPixe = i;
                    mMaxWidth = size.width;
                    mMaxHeight = size.height;
                }
            }
        }
        return sMaxPixe;
    }

    public static int getMaxWith() {
        if (mMaxWidth <= 0) {
            getMaxCamerPixe();
        }
        return mMaxWidth;
    }

    public static int getMaxHeight() {
        if (mMaxHeight <= 0) {
            getMaxCamerPixe();
        }
        return mMaxHeight;
    }
}
