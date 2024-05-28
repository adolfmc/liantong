package com.baidu.cloud.statistics;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Constructor;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StatisticLiveProxy {
    public static IStatisticLive sStatisticLiveApi;

    public static void init(Context context) {
        if (sStatisticLiveApi == null) {
            try {
                Constructor<?> declaredConstructor = Class.forName(StatisticLiveImpl.class.getName()).getDeclaredConstructor(Context.class);
                if (declaredConstructor != null) {
                    declaredConstructor.setAccessible(true);
                    sStatisticLiveApi = (IStatisticLive) declaredConstructor.newInstance(context);
                }
            } catch (Throwable th) {
                sStatisticLiveApi = null;
                Log.e("StatisticLiveProxy", "Statistic live init fail");
                th.printStackTrace();
            }
        }
    }

    public static void onLiveConnected(long j, int i, String str) {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.onLiveConnected(j, i, str);
        }
    }

    public static void onLiveEnd(int i, long j) {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.onLiveEnd(i, j);
        }
    }

    public static void onLiveError(int i) {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.onLiveError(i);
        }
    }

    public static void onLiveMetadata(int i, int i2) {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.onLiveMetadata(i, i2);
        }
    }

    public static void onLiveMute(int i) {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.onLiveMute(i);
        }
    }

    public static void onLivePause(boolean z) {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.onLivePause(z);
        }
    }

    public static void onLivePushImage(int i) {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.onLivePushImage(i);
        }
    }

    public static void onLiveStart() {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.onLiveStart();
        }
    }

    public static void onLiveUpdateInfo(int i, int i2, int i3, double d, double d2, float f, String str) {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.onLiveUpdateInfo(i, i2, i3, d, d2, f, str);
        }
    }

    public static void release() {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.release();
            sStatisticLiveApi = null;
        }
    }

    public static void setLiveRelated(int i, int i2, int i3, int i4) {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.setLiveRelated(i, i2, i3, i4);
        }
    }

    public static void updateSession(String str) {
        IStatisticLive iStatisticLive = sStatisticLiveApi;
        if (iStatisticLive != null) {
            iStatisticLive.updateSession(str);
        }
    }
}
