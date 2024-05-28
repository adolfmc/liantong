package com.baidu.minivideo.arface.utils;

import com.baidu.minivideo.arface.ArFaceSdk;
import com.baidu.minivideo.arface.DuArResConfig;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DuMainDataSoloaderChecker extends BaseTask {
    private static DuMainDataSoloaderChecker sInstance;

    private DuMainDataSoloaderChecker() {
    }

    public static DuMainDataSoloaderChecker getInstance() {
        if (sInstance == null) {
            init();
        }
        return sInstance;
    }

    private static synchronized void init() {
        synchronized (DuMainDataSoloaderChecker.class) {
            if (sInstance == null) {
                sInstance = new DuMainDataSoloaderChecker();
            }
        }
    }

    @Override // com.baidu.minivideo.arface.utils.ITask
    public void run() {
        ArFaceSdk.getResConfig();
        setState(new File(DuArResConfig.getFilterPath()).exists() ? 2 : 3);
    }
}
