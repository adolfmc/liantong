package com.p281qq.p282e.comm.constants;

import android.text.TextUtils;
import com.p281qq.p282e.ads.ADActivity;
import com.p281qq.p282e.ads.LandscapeADActivity;
import com.p281qq.p282e.ads.PortraitADActivity;
import com.p281qq.p282e.ads.RewardvideoLandscapeADActivity;
import com.p281qq.p282e.ads.RewardvideoPortraitADActivity;
import com.p281qq.p282e.comm.DownloadService;
import com.p281qq.p282e.comm.managers.setting.GlobalSetting;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.constants.CustomPkgConstants */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CustomPkgConstants {

    /* renamed from: a */
    private static final String f17899a = DownloadService.class.getName();

    /* renamed from: b */
    private static final String f17900b = ADActivity.class.getName();

    /* renamed from: c */
    private static final String f17901c = PortraitADActivity.class.getName();

    /* renamed from: d */
    private static final String f17902d = RewardvideoPortraitADActivity.class.getName();

    /* renamed from: e */
    private static final String f17903e = LandscapeADActivity.class.getName();

    /* renamed from: f */
    private static final String f17904f = RewardvideoLandscapeADActivity.class.getName();

    public static String getADActivityName() {
        String customADActivityClassName = GlobalSetting.getCustomADActivityClassName();
        return !TextUtils.isEmpty(customADActivityClassName) ? customADActivityClassName : f17900b;
    }

    public static String getAssetPluginXorKey() {
        return "";
    }

    public static String getDownLoadServiceName() {
        return f17899a;
    }

    public static String getLandscapeADActivityName() {
        String customLandscapeActivityClassName = GlobalSetting.getCustomLandscapeActivityClassName();
        return !TextUtils.isEmpty(customLandscapeActivityClassName) ? customLandscapeActivityClassName : f17903e;
    }

    public static String getPortraitADActivityName() {
        String customPortraitActivityClassName = GlobalSetting.getCustomPortraitActivityClassName();
        return !TextUtils.isEmpty(customPortraitActivityClassName) ? customPortraitActivityClassName : f17901c;
    }

    public static String getRewardvideoLandscapeADActivityName() {
        String customRewardvideoLandscapeActivityClassName = GlobalSetting.getCustomRewardvideoLandscapeActivityClassName();
        return !TextUtils.isEmpty(customRewardvideoLandscapeActivityClassName) ? customRewardvideoLandscapeActivityClassName : f17904f;
    }

    public static String getRewardvideoPortraitADActivityName() {
        String customRewardvideoPortraitActivityClassName = GlobalSetting.getCustomRewardvideoPortraitActivityClassName();
        return !TextUtils.isEmpty(customRewardvideoPortraitActivityClassName) ? customRewardvideoPortraitActivityClassName : f17902d;
    }
}
