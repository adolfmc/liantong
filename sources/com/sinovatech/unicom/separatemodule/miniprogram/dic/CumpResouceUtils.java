package com.sinovatech.unicom.separatemodule.miniprogram.dic;

import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity_;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.Box;
import java.io.File;
import net.lingala.zip4j.ZipFile;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CumpResouceUtils {
    public static Box<CumpEntity> getCumpBox() {
        return App.getBoxStore().boxFor(CumpEntity.class);
    }

    public static File createAPPDirs(String str) throws Exception {
        return ManagerWebCacheDictionary.getEdopAppRuntimeDic(true, str);
    }

    public static void deleteApp(String str) {
        try {
            deleteAppResourceFile(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            CumpEntity appInfoFromBox = getAppInfoFromBox(str);
            if (appInfoFromBox != null) {
                getCumpBox().remove((Box<CumpEntity>) appInfoFromBox);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void deleteAppResourceFile(String str) throws Exception {
        ManagerWebCacheDictionary.clearEdopAppRuntimeDic(str);
    }

    public static CumpEntity getAppInfoFromBox(String str) {
        try {
            return getCumpBox().query().equal(CumpEntity_.appId, str).build().findUnique();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void unzipFile(String str, String str2) throws Exception {
        new ZipFile(str).extractAll(str2);
    }

    public static boolean isExitFile(String str, String str2) {
        try {
            if (new File(createAPPDirs(str2), "index.html").exists()) {
                if (isJsFileExit(createAPPDirs(str2))) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d(str, "isExitFile报错：" + e.getMessage());
            return false;
        }
    }

    private static boolean isJsFileExit(File file) {
        File[] listFiles;
        try {
            if (file.exists() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    if (file2.isFile() && file2.getName().endsWith(".js")) {
                        return true;
                    }
                    if (file2.isDirectory() && isJsFileExit(file2)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
