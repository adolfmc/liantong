package com.sinovatech.unicom.common;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BrandUtil {
    public static boolean isXiaoMi() {
        return DeviceHelper.getDeviceBrand().contains("mi");
    }
}
