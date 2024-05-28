package com.sinovatech.unicom.separatemodule.miniprogram.dic;

import com.sinovatech.unicom.p318ui.App;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ManagerWebCacheDictionary {
    private static final String TAG = "ManagerWebCacheDictionary";
    private static final String Unicom_Cache_Root_Dic = App.getInstance().getFilesDir().getAbsolutePath() + File.separator + "unicom_cache_root_dic";
    private static final String Edop_Root_Dic = Unicom_Cache_Root_Dic + File.separator + "edop_root_dic";
    private static final String Edop_Appdist_Dic = Edop_Root_Dic + File.separator + "appdist";
    private static final String Edop_LowcodeRender_Dic = Edop_Root_Dic + File.separator + "lowcoderender";
    private static final String Webview_ResourceIntercept_Normal_Dic = Unicom_Cache_Root_Dic + File.separator + "webview_intercept_normal_dic";
    private static final String Webview_ResourceIntercept_LowcodeComponent_Dic = Unicom_Cache_Root_Dic + File.separator + "webview_intercept_lowcodecomponent_dic";
    private static final String Glide_Image_Dic = Unicom_Cache_Root_Dic + File.separator + "glide_image_dic";

    public static File getUnicomCacheRootDic(boolean z) {
        File file = new File(Unicom_Cache_Root_Dic);
        if (!file.exists() && z) {
            file.mkdirs();
        }
        return file;
    }

    public static File getEdopAppRuntimeDic(boolean z, String str) {
        File file = new File(Edop_Appdist_Dic, str);
        if (!file.exists() && z) {
            file.mkdirs();
        }
        return file;
    }

    public static File getEdopLowcodeRenderDic(boolean z) {
        File file = new File(Edop_LowcodeRender_Dic);
        if (!file.exists() && z) {
            file.mkdirs();
        }
        return file;
    }

    public static File getWebviewResourceIntercept_Normal_Dic(boolean z) {
        File file = new File(Webview_ResourceIntercept_Normal_Dic);
        if (!file.exists() && z) {
            file.mkdirs();
        }
        return file;
    }

    public static File getWebviewResourceIntercept_LowcodeComponent_Dic(boolean z) {
        File file = new File(Webview_ResourceIntercept_LowcodeComponent_Dic);
        if (!file.exists() && z) {
            file.mkdirs();
        }
        return file;
    }

    public static File getGlideImageDic(boolean z) {
        File file = new File(Glide_Image_Dic);
        if (!file.exists() && z) {
            file.mkdirs();
        }
        return file;
    }

    public static void clearUnicomCacheRootDic() throws Exception {
        deleteDir(new File(Unicom_Cache_Root_Dic));
    }

    public static void clearEdopRootDic() throws Exception {
        deleteDir(new File(Edop_Root_Dic));
    }

    public static void clearEdopAppDistDic() throws Exception {
        deleteDir(new File(Edop_Appdist_Dic));
    }

    public static void clearEdopLowcodeRenderDic() throws Exception {
        deleteDir(new File(Edop_LowcodeRender_Dic));
    }

    public static void clearEdopAppRuntimeDic(String str) throws Exception {
        deleteDir(getEdopAppRuntimeDic(false, str));
    }

    public static void clearWebviewResourceInterceptDic() throws Exception {
        deleteDir(new File(Webview_ResourceIntercept_Normal_Dic));
        deleteDir(new File(Webview_ResourceIntercept_LowcodeComponent_Dic));
    }

    public static void clearWebviewResourceIntercept_Normal_Dic() throws Exception {
        deleteDir(new File(Webview_ResourceIntercept_Normal_Dic));
    }

    public static void clearWebviewResourceIntercept_Lowcode_Dic() throws Exception {
        deleteDir(new File(Webview_ResourceIntercept_LowcodeComponent_Dic));
    }

    public static void clearGlideImage() throws Exception {
        deleteDir(new File(Glide_Image_Dic));
    }

    public static void deleteDir(File file) throws Exception {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (String str : file.list()) {
                    deleteDir(new File(file, str));
                }
            }
            file.delete();
        }
    }
}
