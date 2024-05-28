package com.mob.mcl;

import android.content.Context;
import android.os.Bundle;
import com.mob.mcl.p234b.C5906a;
import com.mob.mcl.p235c.C5941h;
import com.mob.mgs.OnIdChangeListener;
import com.mob.tools.proguard.EverythingKeeper;
import com.mob.tools.utils.AbstractC6201c;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MobMCL implements EverythingKeeper {
    public static final String SDK_TAG = "MobMCL";

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ELPMessageListener extends EverythingKeeper {
        boolean messageReceived(Bundle bundle);
    }

    public static void initMCLink(Context context, String str, String str2) {
        C5906a.m12089a(context, str, str2);
    }

    public static String getSuid() {
        return C5906a.m12094a();
    }

    public static void getSuid(OnIdChangeListener onIdChangeListener) {
        C5906a.m12087a(onIdChangeListener);
    }

    public static long getCreateSuidTime() {
        return C5906a.m12077b();
    }

    public static void syncSuid(String str, long j, AbstractC6201c<Boolean> abstractC6201c) {
        C5906a.m12084a(str, j, abstractC6201c);
    }

    public static void getTcpStatus(BusinessCallBack<Boolean> businessCallBack) {
        C5941h.m11988b().m11985b(businessCallBack);
    }

    public static void getClientTcpStatus(BusinessCallBack<Boolean> businessCallBack) {
        C5941h.m11988b().m12006a(businessCallBack);
    }

    public static void deleteMsg(String str) {
        C5941h.m11988b().m11996a(str);
    }

    public static void addELPMessageListener(ELPMessageListener eLPMessageListener) {
        C5906a.m12088a(eLPMessageListener);
    }

    public static void addBusinessMessageListener(int i, BusinessMessageListener businessMessageListener) {
        C5906a.m12091a(i, businessMessageListener);
    }
}
