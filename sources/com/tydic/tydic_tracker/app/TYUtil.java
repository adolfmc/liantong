package com.tydic.tydic_tracker.app;

import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TYUtil {
    public static final String TAG = "tyLog";

    public static final long getCurrTime() {
        return System.currentTimeMillis();
    }

    public static final String getRandom() {
        return String.valueOf(new Random().nextInt(900) + 100 + getCurrTime());
    }
}
