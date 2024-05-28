package com.mob.mcl;

import com.mob.mgs.OnIdChangeListener;
import com.mob.tools.proguard.EverythingKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MCLSDK implements EverythingKeeper {
    public static void getTcpStatus(BusinessCallBack<Boolean> businessCallBack) {
        MobMCL.getTcpStatus(businessCallBack);
    }

    public static void getClientTcpStatus(BusinessCallBack<Boolean> businessCallBack) {
        MobMCL.getClientTcpStatus(businessCallBack);
    }

    public static void deleteMsg(String str) {
        MobMCL.deleteMsg(str);
    }

    public static void addBusinessMessageListener(int i, BusinessMessageListener businessMessageListener) {
        MobMCL.addBusinessMessageListener(i, businessMessageListener);
    }

    public static void getSuid(OnIdChangeListener onIdChangeListener) {
        MobMCL.getSuid(onIdChangeListener);
    }
}
