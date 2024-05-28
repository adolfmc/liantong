package com.baidu.minivideo.arface;

import android.content.Context;
import android.opengl.EGLContext;
import android.util.Log;
import com.baidu.minivideo.arface.utils.ARSourceCopyManager;
import com.baidu.minivideo.arface.utils.BaseTask;
import com.baidu.minivideo.arface.utils.DuMainDataSoloaderChecker;
import com.baidu.minivideo.arface.utils.ITask;
import com.baidu.p120ar.DefaultParams;
import com.baidu.p120ar.bean.DuMixARConfig;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class ArFaceSdk {
    public static final String DEFAULT_GLOBAL_FILTER = "filter_pipeline = function()\n\n    fm = ae.FilterManager:get_instance();\n\n    global_copy_filter = fm:create_filter(\"Tex2DFilter\", \"globalTex2DFilter\", true);\n\n    skin_filter = fm:create_filter(\"SkinFilter\", \"globalSkinFilter\", true);\n    face_filter = fm:create_filter(\"FaceFilter\", \"globalFaceFilter\", true);\n    fm:reset_pipeline();\n    fm:connect_filters_by_id(skin_filter, global_copy_filter);\n    fm:connect_filters_by_id(global_copy_filter, face_filter);\n\n    fm:connect_filter_to_camera(skin_filter);\n    fm:connect_filter_to_output(face_filter);\n\nend\nfilter_pipeline()\n\n";
    private static Context mAppContext = null;
    private static byte[] mLicense = null;
    private static String sFetchUrl = "";
    private static Callback sMainCallback;
    private static ITask.Callback sMainDataCb;
    private static BaseTask sMainDataLoader;
    private static DuArResConfig sResConfig;
    private static File sSoDownloadDir;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface Callback {
        void onResult(boolean z, String str);
    }

    public static void init(Context context) {
        mAppContext = context.getApplicationContext();
    }

    public static void init(Context context, String str, String str2, String str3, DuArResConfig duArResConfig) {
        sResConfig = duArResConfig;
        DuMixARConfig.setAppId(str);
        DuMixARConfig.setAPIKey(str2);
        DuMixARConfig.setSecretKey(str3);
    }

    public static void setFetchUrl(String str) {
        sFetchUrl = str;
    }

    public static File getSoDownloadDir() {
        if (sSoDownloadDir == null) {
            sSoDownloadDir = new File(ARControllerProxy.getSoDownloadDir(mAppContext));
        }
        return sSoDownloadDir;
    }

    public static void loadFaceAssets(Context context, Callback callback) {
        sMainCallback = callback;
        if (!DuArResConfig.RES_COPY_NEEN) {
            sMainDataLoader = DuMainDataSoloaderChecker.getInstance();
        } else {
            ARSourceCopyManager aRSourceCopyManager = ARSourceCopyManager.getInstance(context);
            getResConfig();
            aRSourceCopyManager.copyArResource("file:///android_asset/arsource/", new File(DuArResConfig.getMainDataPath()), false);
            sMainDataLoader = aRSourceCopyManager;
        }
        sMainDataLoader.start(getCallback(context));
    }

    private static ITask.Callback getCallback(Context context) {
        ITask.Callback callback = sMainDataCb;
        if (callback != null) {
            return callback;
        }
        sMainDataCb = new ITask.Callback() { // from class: com.baidu.minivideo.arface.ArFaceSdk.1
            @Override // com.baidu.minivideo.arface.utils.ITask.Callback
            public void onResult(int i, ITask iTask) {
                if (ArFaceSdk.sMainDataLoader != iTask) {
                    return;
                }
                if (i == 2) {
                    if (ArFaceSdk.sMainCallback != null) {
                        ArFaceSdk.sMainCallback.onResult(true, ArFaceSdk.reportMsg(iTask instanceof DuMainDataSoloaderChecker ? "soloaderChecker" : "assetsCopy", true));
                        Callback unused = ArFaceSdk.sMainCallback = null;
                    }
                } else if (i != 3 || ArFaceSdk.sMainCallback == null) {
                } else {
                    ArFaceSdk.sMainCallback.onResult(false, ArFaceSdk.reportMsg(iTask instanceof DuMainDataSoloaderChecker ? "soloaderChecker" : "assetsCopy", false));
                    Callback unused2 = ArFaceSdk.sMainCallback = null;
                }
            }
        };
        return sMainDataCb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String reportMsg(String str, boolean z) {
        getResConfig();
        File file = new File(DuArResConfig.getFilterPath());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("loc", str);
            jSONObject.put("result", z);
            jSONObject.put("resFile", file.getAbsoluteFile());
            jSONObject.put("resExist", file.exists());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    /* renamed from: d */
    private static void m18109d(String str) {
        Log.e("DuAr_DuController", "ar->" + str);
    }

    public static DuArResConfig getResConfig() {
        return sResConfig;
    }

    public static void setResConfig(DuArResConfig duArResConfig) {
        sResConfig = duArResConfig;
    }

    public static int getVersion() {
        return ARControllerProxy.getVersion();
    }

    public static String getVersionName() {
        return ARControllerProxy.getVersionName();
    }

    public static DefaultParams getDuMixDefaultParams(EGLContext eGLContext) {
        DuArResConfig duArResConfig = sResConfig;
        String str = DuArResConfig.getfaceModelsPath();
        DefaultParams defaultParams = new DefaultParams();
        defaultParams.setFaceAlgoModelPath(str);
        defaultParams.setFetchUrl(sFetchUrl);
        defaultParams.setUseBeautyFilter(true);
        defaultParams.setUseFaceFilter(true);
        defaultParams.setUseMakeupFilter(false);
        if (eGLContext != null) {
            defaultParams.setUseTextureIO(true);
            defaultParams.setShareContext(eGLContext);
        }
        return defaultParams;
    }

    public static void setArLicense(byte[] bArr) {
        mLicense = bArr;
    }

    public static byte[] getArLicense() {
        return mLicense;
    }
}
