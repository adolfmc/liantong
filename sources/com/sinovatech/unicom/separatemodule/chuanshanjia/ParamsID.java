package com.sinovatech.unicom.separatemodule.chuanshanjia;

import com.sinovatech.unicom.common.URLEnvironmentConfig;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ParamsID {
    public static String AppId = "5049584";
    public static String bannerID = "945074132";
    public static String kaipingId = "887300373";
    public static String xinxiliuId = "945074239";

    public static String getRewardID() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "945114203" : URLEnvironmentConfig.isPrepubEnvironment() ? "945067111" : "945074541";
    }
}
