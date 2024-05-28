package com.sinovatech.unicom.separatemodule.audience;

import android.content.Context;
import android.os.Environment;
import com.baidu.license.LicenseManager;
import com.sinovatech.unicom.separatemodule.audience.custom.ContextConstant;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BDCloudSdkInitManager {
    private static BDCloudSdkInitManager instance;

    public static BDCloudSdkInitManager getInstance() {
        if (instance == null) {
            synchronized (BDCloudSdkInitManager.class) {
                if (instance == null) {
                    instance = new BDCloudSdkInitManager();
                }
            }
        }
        return instance;
    }

    public void onCreate(Context context, String str) {
        ContextConstant.setContext(context);
        LicenseManager.init(context, str);
    }

    public static File getBaiDuUgcCacheFile(Context context) {
        File file = new File(isSDMounted() ? context.getApplicationContext().getExternalFilesDir(null) : null, "shoot");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static boolean isSDMounted() {
        return Environment.getExternalStorageState().equals("mounted");
    }
}
