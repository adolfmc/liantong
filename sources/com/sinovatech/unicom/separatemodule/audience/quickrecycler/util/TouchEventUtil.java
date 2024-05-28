package com.sinovatech.unicom.separatemodule.audience.quickrecycler.util;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TouchEventUtil {
    public static String getTouchAction(int i) {
        String str = "Unknow:id=" + i;
        switch (i) {
            case 0:
                return "ACTION_DOWN";
            case 1:
                return "ACTION_UP";
            case 2:
                return "ACTION_MOVE";
            case 3:
                return "ACTION_CANCEL";
            case 4:
                return "ACTION_OUTSIDE";
            default:
                return str;
        }
    }
}
