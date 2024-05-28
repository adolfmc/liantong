package com.sinovatech.unicom.separatemodule.tongyicaiji;

import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.tydic.tydic_tracker.app.TYApplication;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ManagerTianyan {
    private static final String TAG = "ManagerTianyan";

    public static void init() {
        try {
            new TYApplication().init(App.getInstance(), URLEnvironmentConfig.isForPublish() ? "prod" : "test");
            collectAnr();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setHotStartTime() {
        try {
            if (TYCJConfigUtil.isOpen("tianYan")) {
                TYApplication.setHotStartTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void collectInfo(final String str, final String str2, final int i, final String str3) {
        if (TYCJConfigUtil.isOpen("tianYan")) {
            Observable.just(0).subscribeOn(Schedulers.m1934io()).map(new Function<Integer, Object>() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.ManagerTianyan.1
                @Override // io.reactivex.functions.Function
                public Object apply(@NonNull Integer num) throws Exception {
                    try {
                        MsLogUtil.m7979d("ManagerTianyan", "---collectInfo");
                        TYApplication.InfoTracker(str, str2, i, str3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return num;
                }
            }).subscribe();
        }
    }

    public static void collectLunchTime() {
        if (TYCJConfigUtil.isOpen("tianYan")) {
            Observable.just(0).subscribeOn(Schedulers.m1934io()).map(new Function<Integer, Object>() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.ManagerTianyan.2
                @Override // io.reactivex.functions.Function
                public Object apply(@NonNull Integer num) throws Exception {
                    try {
                        MsLogUtil.m7979d("ManagerTianyan", "开始执行收集启动时间---collectLunchTime");
                        TYApplication.setEndMilli();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return num;
                }
            }).subscribe();
        }
    }

    public static void collectAnr() {
        try {
            if (TYCJConfigUtil.isOpen("tianYan")) {
                TYApplication.startANRWatchDog(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void collectCrash(final int i, final String str, final String str2, final String str3) {
        if (TYCJConfigUtil.isOpen("tianYan")) {
            Observable.just(0).subscribeOn(Schedulers.m1934io()).map(new Function<Integer, Object>() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.ManagerTianyan.3
                @Override // io.reactivex.functions.Function
                public Object apply(@NonNull Integer num) throws Exception {
                    try {
                        MsLogUtil.m7979d("ManagerTianyan", "开始执行收集闪退---collectCrash");
                        TYApplication.collectCrash(i, str, str2, str3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return num;
                }
            }).subscribe();
        }
    }

    public static void collectH5info(String str) {
        if (TYCJConfigUtil.isOpen("tianYan")) {
            TYCJBoxManager.getInstance().setTianyanH5Info(str);
        }
    }
}
