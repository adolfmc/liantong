package com.p281qq.p282e.ads.cfg;

import com.p281qq.p282e.comm.util.GDTLogger;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.cfg.MultiProcessFlag */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MultiProcessFlag {

    /* renamed from: a */
    private static boolean f17768a;

    /* renamed from: b */
    private static boolean f17769b;

    public static boolean isMultiProcess() {
        return f17768a;
    }

    public static void setMultiProcess(boolean z) {
        if (f17769b) {
            GDTLogger.m8231w("MultiProcessFlag已经设置过，再次设置无效");
            return;
        }
        f17769b = true;
        f17768a = z;
    }
}
