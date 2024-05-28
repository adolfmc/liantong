package com.baidu.p120ar.armdl;

import android.content.Context;
import android.os.Build;
import android.util.SparseArray;
import com.baidu.p120ar.mdl.ARMdlInterfaceJNI;
import com.baidu.p120ar.mdl.MdlConfig;
import com.baidu.p120ar.utils.ARLog;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.armdl.ARMdlManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARMdlManager {
    private static final String TAG = "ARMdlManager";
    private static ARMdlManager sInstance;
    private SparseArray<MdlConfig> configs = new SparseArray<>();
    private SparseArray<Integer> openMdlList = new SparseArray<>();
    private SparseArray<Boolean> runningMdl = new SparseArray<>();
    private boolean hasSetUp = false;

    public static synchronized ARMdlManager getInstance() {
        ARMdlManager aRMdlManager;
        synchronized (ARMdlManager.class) {
            if (sInstance == null) {
                sInstance = new ARMdlManager();
            }
            aRMdlManager = sInstance;
        }
        return aRMdlManager;
    }

    private ARMdlManager() {
    }

    public synchronized boolean checkMdlInit(int i) {
        return this.openMdlList.get(i) != null;
    }

    private void setup(Context context) {
        if (this.hasSetUp) {
            return;
        }
        if (context == null) {
            ARLog.m20419e(TAG, "setup context = null");
            return;
        }
        try {
            ARMdlInterfaceJNI.setAssetManager(context.getAssets());
            this.hasSetUp = true;
        } catch (Exception e) {
            ARLog.m20419e(TAG, "setup error");
            e.printStackTrace();
        }
    }

    public synchronized void registerMdl(int i) {
        this.openMdlList.put(i, Integer.valueOf(i));
    }

    public synchronized void unRegisterMdl(int i) {
        this.openMdlList.remove(i);
    }

    public boolean getMdlStateByType(int i) {
        Boolean bool;
        synchronized (this.runningMdl) {
            bool = this.runningMdl.get(i);
        }
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public void setMdlState(int i, boolean z) {
        synchronized (this.runningMdl) {
            this.runningMdl.put(i, Boolean.valueOf(z));
        }
    }

    public void setConfigs(Context context, SparseArray<MdlConfig> sparseArray) {
        this.configs = sparseArray;
        setup(context);
    }

    public void setCacheDir(Context context) {
        if (context == null) {
            ARLog.m20419e(TAG, "setCacheDir context = null");
        } else if (Build.VERSION.SDK_INT < 29) {
            File file = new File(context.getFilesDir(), "snpe");
            if (!file.exists()) {
                file.mkdirs();
            }
            ARMdlInterfaceJNI.setCacheDir(file.getAbsolutePath());
        }
    }

    public MdlConfig getMdlConfigByType(int i) {
        return this.configs.get(i);
    }
}
