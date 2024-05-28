package com.baidu.minivideo.arface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.opengl.EGLContext;
import android.opengl.GLES20;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.baidu.minivideo.arface.bean.BeautyType;
import com.baidu.minivideo.arface.bean.Makeup;
import com.baidu.minivideo.arface.utils.LogUtils;
import com.baidu.p120ar.ARType;
import com.baidu.p120ar.DefinedLuaListener;
import com.baidu.p120ar.DuMixCallback;
import com.baidu.p120ar.DuMixController;
import com.baidu.p120ar.DuMixErrorType;
import com.baidu.p120ar.DuMixInput;
import com.baidu.p120ar.DuMixInput2;
import com.baidu.p120ar.DuMixOutput;
import com.baidu.p120ar.DuMixOutput2;
import com.baidu.p120ar.DuMixStateListener;
import com.baidu.p120ar.arrender.FrameRenderListener;
import com.baidu.p120ar.arrender.IGLRenderer;
import com.baidu.p120ar.arrender.Texture;
import com.baidu.p120ar.bean.DuMixARConfig;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.capture.ICaptureAbilityListener;
import com.baidu.p120ar.capture.ICaptureResult;
import com.baidu.p120ar.face.FaceListener;
import com.baidu.p120ar.face.IFace;
import com.baidu.p120ar.filter.FilterNode;
import com.baidu.p120ar.filter.FilterParam;
import com.baidu.p120ar.filter.FilterStateListener;
import com.baidu.p120ar.lua.LuaMsgListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARControllerProxy {
    private static final boolean DEBUG = false;
    public static final int RESLTION_1080 = 1080;
    public static final int RESLTION_720 = 720;
    public static final String TAG = "DuAr_DuController";
    private static ARControllerProxy sInstance;
    private Context mContext;
    private DefinedLuaListener mDefinedLuaListener;
    private long mDestroyStartTime;
    private DuMixController mDuMixController;
    private DuMixInput mDuMixInput;
    private DuMixOutput mDuMixOutput;
    private DuMixStateListener mDuMixStateListener;
    private FaceListener mFaceListener;
    FilterStateListener mFilterStateListener;
    private IDumixHolder mHolder;
    private Texture mInputTexture;
    private LuaMsgListener mLuaMsgListener;
    private int mOutputFps;
    private Texture mOutputTexture;
    private long mSetupStartTime;
    private EGLContext mShareContext;
    public static final String FILTER_DEF_CONFIG_FILE_NAME = DuArResConfig.getFilterConfig();
    private static boolean sIsSoloaded = false;
    public static int VIDEO_CONSTANT_WIDTH_1080 = 1080;
    public static int VIDEO_CONSTANT_HEIGHT_1080 = 1920;
    private static final IDumixHolder RELEASED = null;
    private static boolean sSyncInputContent = false;
    public static String sLibName = null;
    private boolean mPaused = false;
    private boolean mReleased = false;
    private List<DuMixCallback> mDuMixCallbackList = new ArrayList();
    private int mScreenOrientation = 1;
    private int mInputWidth = 1280;
    private int mInputHeight = 720;
    private int mInputResulutionType = 720;
    private boolean mDuMixSetup = false;
    private boolean mOnSetup = false;
    private DuMixCallback mDuMixCallback = new DuMixCallback() { // from class: com.baidu.minivideo.arface.ARControllerProxy.6
        @Override // com.baidu.p120ar.DuMixCallback
        public void onSetup(boolean z, DuMixInput duMixInput, DuMixOutput duMixOutput) {
            ARControllerProxy.this.mOnSetup = z;
            if (z && ARControllerProxy.this.mDuMixController != null) {
                IFace faceAR = ARControllerProxy.this.mDuMixController.getARProxyManager() != null ? ARControllerProxy.this.mDuMixController.getARProxyManager().getFaceAR() : null;
                if (faceAR != null) {
                    faceAR.setFaceListener(new FaceListener() { // from class: com.baidu.minivideo.arface.ARControllerProxy.6.1
                        @Override // com.baidu.p120ar.face.FaceListener
                        public void onFaceResult(Object obj) {
                            if (ARControllerProxy.this.mFaceListener != null) {
                                ARControllerProxy.this.mFaceListener.onFaceResult(obj);
                            }
                        }

                        @Override // com.baidu.p120ar.face.FaceListener
                        public void onStickerLoadingFinished(List<String> list) {
                            if (ARControllerProxy.this.mFaceListener != null) {
                                ARControllerProxy.this.mFaceListener.onStickerLoadingFinished(list);
                            }
                        }

                        @Override // com.baidu.p120ar.face.FaceListener
                        public void onTriggerFired(String str) {
                            if (ARControllerProxy.this.mFaceListener != null) {
                                ARControllerProxy.this.mFaceListener.onTriggerFired(str);
                            }
                        }
                    });
                }
            }
            for (int i = 0; i < ARControllerProxy.this.mDuMixCallbackList.size(); i++) {
                DuMixCallback duMixCallback = (DuMixCallback) ARControllerProxy.this.mDuMixCallbackList.get(i);
                if (duMixCallback != null) {
                    duMixCallback.onSetup(z, duMixInput, duMixOutput);
                }
            }
        }

        @Override // com.baidu.p120ar.DuMixCallback
        public void onCaseCreate(boolean z, String str, String str2) {
            for (int i = 0; i < ARControllerProxy.this.mDuMixCallbackList.size(); i++) {
                DuMixCallback duMixCallback = (DuMixCallback) ARControllerProxy.this.mDuMixCallbackList.get(i);
                if (duMixCallback != null) {
                    duMixCallback.onCaseCreate(z, str, str2);
                }
            }
        }

        @Override // com.baidu.p120ar.DuMixCallback
        public void onCaseDestroy() {
            for (int i = 0; i < ARControllerProxy.this.mDuMixCallbackList.size(); i++) {
                DuMixCallback duMixCallback = (DuMixCallback) ARControllerProxy.this.mDuMixCallbackList.get(i);
                if (duMixCallback != null) {
                    duMixCallback.onCaseDestroy();
                }
            }
        }

        @Override // com.baidu.p120ar.DuMixCallback
        public void onRelease() {
            for (int i = 0; i < ARControllerProxy.this.mDuMixCallbackList.size(); i++) {
                DuMixCallback duMixCallback = (DuMixCallback) ARControllerProxy.this.mDuMixCallbackList.get(i);
                if (duMixCallback != null) {
                    duMixCallback.onRelease();
                }
            }
            ARControllerProxy.this.mDuMixCallbackList.clear();
        }

        @Override // com.baidu.p120ar.DuMixCallback
        public void onError(DuMixErrorType duMixErrorType, String str, String str2) {
            for (DuMixCallback duMixCallback : ARControllerProxy.this.mDuMixCallbackList) {
                duMixCallback.onError(duMixErrorType, str, str2);
            }
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface IDumixHolder {
        void onHolderChanged(IDumixHolder iDumixHolder);
    }

    /* renamed from: d */
    private static void m18110d(String str, String str2) {
    }

    public void enableProfileLog(boolean z) {
    }

    public static synchronized boolean loadSoFile() {
        String sb;
        synchronized (ARControllerProxy.class) {
            if (sIsSoloaded) {
                return sIsSoloaded;
            }
            StringBuilder sb2 = new StringBuilder();
            try {
                System.loadLibrary("c++_shared");
                System.loadLibrary("ardatabasic");
                System.loadLibrary("opencv_java3");
                System.loadLibrary("anakin_lite");
                System.loadLibrary("FaceAttributes");
                System.loadLibrary("EglCore");
                System.loadLibrary("AREngineCpp");
                String str = "ARMdlSDK";
                System.loadLibrary("ARMdlSDK");
                try {
                    sIsSoloaded = true;
                    sb = sb2.toString();
                } catch (Throwable th) {
                    str = null;
                    th = th;
                    sb2.append("|");
                    sb2.append(str);
                    LogUtils.m18108d("DuAr_UGC_SO", "so loaded: " + th.getMessage());
                    sIsSoloaded = false;
                    sb = sb2.toString();
                    sLibName = sb;
                    return sIsSoloaded;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            sLibName = sb;
            return sIsSoloaded;
        }
    }

    public static boolean isSoLoaded() {
        return sIsSoloaded;
    }

    public int getInputResulutionType() {
        return this.mInputResulutionType;
    }

    public void setInputResulutionType(int i) {
        this.mInputResulutionType = i;
        if (this.mInputResulutionType == 1080) {
            this.mInputWidth = VIDEO_CONSTANT_HEIGHT_1080;
            this.mInputHeight = VIDEO_CONSTANT_WIDTH_1080;
        }
    }

    private ARControllerProxy(Context context, IDumixHolder iDumixHolder, EGLContext eGLContext, byte[] bArr) {
        this.mContext = context.getApplicationContext();
        this.mShareContext = eGLContext;
        this.mHolder = iDumixHolder;
        this.mDuMixController = DuMixController.getInstance(this.mContext, ArFaceSdk.getDuMixDefaultParams(eGLContext));
        if (bArr != null && bArr.length > 0) {
            this.mDuMixController.setAuthLicense(bArr, DuMixARConfig.getAipAppId(), DuMixARConfig.getAPIKey(), DuMixARConfig.getSecretKey());
        }
        enableProfileLog(false);
        this.mDuMixController.addLuaMsgListener(new LuaMsgListener() { // from class: com.baidu.minivideo.arface.ARControllerProxy.1
            @Override // com.baidu.p120ar.lua.LuaMsgListener
            public List<String> getMsgKeyListened() {
                if (ARControllerProxy.this.mLuaMsgListener != null) {
                    return ARControllerProxy.this.mLuaMsgListener.getMsgKeyListened();
                }
                return null;
            }

            @Override // com.baidu.p120ar.lua.LuaMsgListener
            public void onLuaMessage(HashMap<String, Object> hashMap) {
                if (ARControllerProxy.this.mLuaMsgListener != null) {
                    ARControllerProxy.this.mLuaMsgListener.onLuaMessage(hashMap);
                }
            }
        });
        this.mDuMixController.setDefinedLuaListener(new DefinedLuaListener() { // from class: com.baidu.minivideo.arface.ARControllerProxy.2
            @Override // com.baidu.p120ar.DefinedLuaListener
            public void onRequireSwitchCamera(int i) {
                if (ARControllerProxy.this.mDefinedLuaListener != null) {
                    ARControllerProxy.this.mDefinedLuaListener.onRequireSwitchCamera(i);
                }
            }

            @Override // com.baidu.p120ar.DefinedLuaListener
            public void onOpenUrl(String str, int i, HashMap<String, Object> hashMap) {
                if (ARControllerProxy.this.mDefinedLuaListener != null) {
                    ARControllerProxy.this.mDefinedLuaListener.onOpenUrl(str, i, hashMap);
                }
            }
        });
        this.mDuMixController.setFilterStateListener(new FilterStateListener() { // from class: com.baidu.minivideo.arface.ARControllerProxy.3
            @Override // com.baidu.p120ar.filter.FilterStateListener
            public void onFilterStateChanged(HashMap<FilterNode, Boolean> hashMap, String str) {
                if (ARControllerProxy.this.mFilterStateListener != null) {
                    ARControllerProxy.this.mFilterStateListener.onFilterStateChanged(hashMap, str);
                }
            }
        });
        this.mDuMixController.setStateListener(new DuMixStateListener() { // from class: com.baidu.minivideo.arface.ARControllerProxy.4
            @Override // com.baidu.p120ar.DuMixStateListener
            public void onInputSurfaceTextureAttach(SurfaceTexture surfaceTexture) {
                if (ARControllerProxy.this.mDuMixStateListener != null) {
                    ARControllerProxy.this.mDuMixStateListener.onInputSurfaceTextureAttach(surfaceTexture);
                }
            }
        });
    }

    private static String getString(Map map) {
        StringBuilder sb = new StringBuilder();
        if (map == null) {
            sb.append("null");
        } else {
            for (Map.Entry entry : map.entrySet()) {
                if (entry.getKey() != null) {
                    sb.append(entry.getKey().toString());
                    if (entry.getValue() != null) {
                        sb.append(',');
                        sb.append(entry.getValue().toString());
                    }
                    sb.append('\n');
                }
            }
        }
        return sb.toString();
    }

    public static ARControllerProxy getInstance(Context context, IDumixHolder iDumixHolder, byte[] bArr) {
        return getInstance(context, iDumixHolder, null, bArr);
    }

    public static ARControllerProxy getInstance(Context context, IDumixHolder iDumixHolder) {
        return getInstance(context, iDumixHolder, null, null);
    }

    public static ARControllerProxy getInstance(Context context, IDumixHolder iDumixHolder, EGLContext eGLContext, byte[] bArr) {
        ARControllerProxy aRControllerProxy = sInstance;
        if (aRControllerProxy == null || aRControllerProxy.mHolder != iDumixHolder || aRControllerProxy.mShareContext != eGLContext) {
            synchronized (ARControllerProxy.class) {
                if (sInstance == null || sInstance.mHolder != iDumixHolder || sInstance.mShareContext != eGLContext) {
                    if (sInstance != null) {
                        IDumixHolder iDumixHolder2 = sInstance.mHolder;
                        sInstance.release();
                        if (iDumixHolder2 != null) {
                            iDumixHolder2.onHolderChanged(iDumixHolder);
                        }
                    }
                    sInstance = new ARControllerProxy(context, iDumixHolder, eGLContext, bArr);
                }
            }
        }
        return sInstance;
    }

    public IDumixHolder getHolder() {
        return this.mHolder;
    }

    public void resume() {
        if (sInstance == this && this.mDuMixController != null) {
            m18110d("DuAr_DuController", "resume");
            this.mDuMixController.resume();
            this.mPaused = false;
        }
    }

    public void pause() {
        if (sInstance == this && this.mDuMixController != null) {
            m18110d("DuAr_DuController", "pause");
            this.mDuMixController.pause();
            this.mPaused = true;
        }
    }

    public void release() {
        if (sInstance != this) {
            return;
        }
        this.mPaused = false;
        this.mReleased = true;
        this.mDuMixSetup = false;
        this.mOnSetup = false;
        this.mHolder = RELEASED;
        DuMixController duMixController = this.mDuMixController;
        this.mDuMixController = null;
        if (duMixController != null) {
            m18110d("DuAr_DuController", "release");
            this.mDestroyStartTime = System.currentTimeMillis();
            duMixController.release();
            long currentTimeMillis = System.currentTimeMillis() - this.mDestroyStartTime;
            m18110d("DuAr_DuController", Build.MODEL + ", release spendTime: " + currentTimeMillis);
        }
        sInstance = null;
        this.mShareContext = null;
    }

    public boolean isSetUp() {
        return this.mOnSetup;
    }

    private boolean isDumixEnable() {
        return (this.mDuMixController == null || !isSetUp() || isPaused()) ? false : true;
    }

    public boolean isReleased() {
        return this.mReleased;
    }

    public boolean isPaused() {
        return this.mPaused;
    }

    public void onCameraDrawerCreated(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.mShareContext != null) {
            this.mDuMixInput = new DuMixInput2(this.mInputTexture, i, i2);
            ((DuMixInput2) this.mDuMixInput).setSyncInputContent(sSyncInputContent);
            m18110d("DuAr_DuController", "onCameraDrawerCreated : sSyncInputContent = " + sSyncInputContent);
            this.mDuMixInput.setInputDegree(0);
            return;
        }
        this.mDuMixInput = new DuMixInput(surfaceTexture, i, i2);
    }

    public void setScreenOrientation(int i) {
        if (i != 0) {
            this.mScreenOrientation = 1;
        } else {
            this.mScreenOrientation = i;
        }
    }

    public void setOutputFPS(int i) {
        this.mOutputFps = i;
    }

    public void onARDrawerCreated(SurfaceTexture surfaceTexture, SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener, boolean z, int i, int i2, DuMixCallback duMixCallback) {
        if (this.mDuMixController == null) {
            return;
        }
        this.mSetupStartTime = System.currentTimeMillis();
        if (surfaceTexture != null) {
            surfaceTexture.setOnFrameAvailableListener(onFrameAvailableListener);
        }
        addDuMixCallback(duMixCallback);
        if (!this.mDuMixSetup) {
            this.mDuMixInput.setFrontCamera(z);
            if (this.mShareContext != null) {
                this.mDuMixOutput = new DuMixOutput2(this.mOutputTexture, i, i2);
            } else {
                this.mDuMixOutput = new DuMixOutput(surfaceTexture, i, i2);
            }
            int i3 = this.mOutputFps;
            if (i3 > 0) {
                this.mDuMixOutput.setOutputFPS(i3);
            }
            this.mDuMixController.setup(this.mDuMixInput, this.mDuMixOutput, this.mDuMixCallback);
            this.mDuMixSetup = true;
            return;
        }
        this.mDuMixController.changeOutputSize(i, i2);
    }

    public void sendMessage2Lua(HashMap<String, Object> hashMap) {
        if (this.mDuMixController == null || !isSetUp() || hashMap == null) {
            return;
        }
        this.mDuMixController.sendMsg2Lua(hashMap);
    }

    public void setMdlModelPath(String str) {
        if (!isDumixEnable() || str == null) {
            return;
        }
        this.mDuMixController.setMdlModelPath(str);
    }

    public void addDuMixCallback(DuMixCallback duMixCallback) {
        if (duMixCallback != null && !this.mDuMixCallbackList.contains(duMixCallback)) {
            this.mDuMixCallbackList.add(duMixCallback);
        }
        LogUtils.m18108d("DuAr_DuController", "addDuMixCallback size " + this.mDuMixCallbackList.size());
    }

    public void removeDuMixCallback(DuMixCallback duMixCallback) {
        if (duMixCallback != null) {
            this.mDuMixCallbackList.remove(duMixCallback);
        }
    }

    public DuMixController getDuMixController() {
        return this.mDuMixController;
    }

    public boolean onTouchEvent(View view, MotionEvent motionEvent) {
        DuMixController duMixController = this.mDuMixController;
        if (duMixController != null) {
            return duMixController.onTouch(view, motionEvent);
        }
        return false;
    }

    public void setBeautyValue(BeautyType beautyType, int i) {
        if (!isDumixEnable() || beautyType == null) {
            return;
        }
        this.mDuMixController.updateFilter(beautyType.type, i);
    }

    public void setBeautyValue(BeautyType beautyType, Makeup makeup) {
        if (makeup == null) {
            return;
        }
        DuMixController duMixController = this.mDuMixController;
        if (!isDumixEnable() || beautyType == null) {
            return;
        }
        duMixController.updateFilter(beautyType.type, 1);
        duMixController.updateFilterCase(makeup.getResPath());
        duMixController.updateFilter(beautyType.type, makeup.getValue());
    }

    public void setBeautyValue(BeautyType beautyType, float f) {
        if (!isDumixEnable() || beautyType == null) {
            return;
        }
        this.mDuMixController.updateFilter(beautyType.type, f);
    }

    public void setCurve(List<List<Point>> list) {
        DuMixController duMixController = this.mDuMixController;
        if (!isDumixEnable() || list == null) {
            return;
        }
        try {
            duMixController.updateFilter(FilterParam.TuneColorFilter.rgbPoints, list.get(0));
            duMixController.updateFilter(FilterParam.TuneColorFilter.redPoints, list.get(1));
            duMixController.updateFilter(FilterParam.TuneColorFilter.greenPoints, list.get(2));
            duMixController.updateFilter(FilterParam.TuneColorFilter.bluePoints, list.get(3));
            duMixController.updateFilter((FilterParam) FilterParam.TuneColorFilter.curve, 0.9f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initCurve(boolean z) {
        if (!z) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new Point(0, 0));
            arrayList2.add(new Point(255, 255));
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new Point(0, 0));
            arrayList3.add(new Point(255, 255));
            ArrayList arrayList4 = new ArrayList();
            arrayList4.add(new Point(0, 0));
            arrayList4.add(new Point(255, 255));
            ArrayList arrayList5 = new ArrayList();
            arrayList5.add(new Point(0, 0));
            arrayList5.add(new Point(255, 255));
            arrayList.add(arrayList2);
            arrayList.add(arrayList3);
            arrayList.add(arrayList4);
            arrayList.add(arrayList5);
            setCurve(arrayList);
            return;
        }
        resetCurveDefaultParm();
    }

    public void setBeautyValue(BeautyType beautyType, String str) {
        if (!isDumixEnable() || beautyType == null) {
            return;
        }
        if (beautyType == BeautyType.cheeks || beautyType == BeautyType.lips || beautyType == BeautyType.highlight || beautyType == BeautyType.eyeshadow || beautyType == BeautyType.eyeliner || beautyType == BeautyType.eyebrow || beautyType == BeautyType.eyeball) {
            this.mDuMixController.updateFilterCase(str);
        } else {
            this.mDuMixController.updateFilter(beautyType.type, str);
        }
    }

    public void setBeautyValue(BeautyType beautyType, float[] fArr) {
        if (!isDumixEnable() || beautyType == null) {
            return;
        }
        this.mDuMixController.updateFilter(beautyType.type, fArr);
    }

    public void setFilterBeautyValue(FilterParam filterParam, float f) {
        if (!isDumixEnable() || filterParam == null) {
            return;
        }
        this.mDuMixController.updateFilter(filterParam, f);
    }

    public void setFilterBeautyValue(FilterParam filterParam, List<Point> list) {
        if (!isDumixEnable() || filterParam == null || list == null) {
            return;
        }
        this.mDuMixController.updateFilter(filterParam, list);
    }

    public void setInitFilterPath(String str) {
        if (isDumixEnable()) {
            this.mDuMixController.updateFilterCase(str);
        }
    }

    public void setBeautyValues(Map<BeautyType, Object> map) {
        Map.Entry<BeautyType, Object> next;
        if (!isDumixEnable() || map == null) {
            return;
        }
        Iterator<Map.Entry<BeautyType, Object>> it = map.entrySet().iterator();
        while (it.hasNext() && (next = it.next()) != null && next.getKey() != null) {
            Object value = next.getValue();
            if (value instanceof Integer) {
                setBeautyValue(next.getKey(), ((Integer) value).intValue());
            } else if (value instanceof Float) {
                setBeautyValue(next.getKey(), ((Float) value).floatValue());
            } else if (value instanceof String) {
                setBeautyValue(next.getKey(), (String) value);
            } else if (value instanceof float[]) {
                setBeautyValue(next.getKey(), (float[]) value);
            } else if (value instanceof Double) {
                setBeautyValue(next.getKey(), new Float(((Double) value).doubleValue()).floatValue());
            } else if (value instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) value;
                int size = arrayList.size();
                float[] fArr = new float[size];
                for (int i = 0; i < size; i++) {
                    if (arrayList.get(i) instanceof Double) {
                        fArr[i] = new Float(((Double) arrayList.get(i)).doubleValue()).floatValue();
                    }
                }
                setBeautyValue(next.getKey(), fArr);
            } else if (value instanceof Makeup) {
                setBeautyValue(next.getKey(), (Makeup) value);
            }
        }
    }

    public void clearAllFilter() {
        DuMixController duMixController = this.mDuMixController;
        if (duMixController != null) {
            duMixController.clearAllFilter();
        }
    }

    public static String getSoDownloadDir(Context context) {
        return DuMixController.getSoDownLoadDir(context);
    }

    public static int getVersion() {
        return DuMixController.getVersionCode();
    }

    public static String getVersionName() {
        return DuMixController.getVersionName();
    }

    public void loadCase(String str, String str2) {
        loadCase(null, str, str2);
    }

    public void loadCase(ARType aRType, String str, String str2) {
        if (this.mFilterStateListener != null && TextUtils.isEmpty(str)) {
            this.mFilterStateListener.onFilterStateChanged(null, null);
        }
        if (this.mDuMixController == null || !isSetUp()) {
            return;
        }
        this.mDuMixController.loadCase(aRType, str, str2);
    }

    public void setCaptureArOnAbilityListener(ICaptureAbilityListener iCaptureAbilityListener) {
        DuMixController duMixController = this.mDuMixController;
        if (duMixController == null || duMixController.getARProxyManager() == null || this.mDuMixController.getARProxyManager().getCaptureAR() == null) {
            return;
        }
        this.mDuMixController.getARProxyManager().getCaptureAR().setAbilityListener(iCaptureAbilityListener);
    }

    public void setCaptureArOnCaptureCallback(ICallbackWith<ICaptureResult> iCallbackWith) {
        DuMixController duMixController = this.mDuMixController;
        if (duMixController == null || duMixController.getARProxyManager() == null || this.mDuMixController.getARProxyManager().getCaptureAR() == null) {
            return;
        }
        this.mDuMixController.getARProxyManager().getCaptureAR().setCaptureCallback(iCallbackWith);
    }

    public void sendCaptureArImageToLua(Object... objArr) {
        DuMixController duMixController;
        if (objArr == null || objArr.length == 0 || (duMixController = this.mDuMixController) == null || duMixController.getARProxyManager() == null || this.mDuMixController.getARProxyManager().getCaptureAR() == null) {
            return;
        }
        int i = 0;
        if (objArr[0] instanceof Bitmap) {
            Bitmap[] bitmapArr = new Bitmap[objArr.length];
            while (i < objArr.length) {
                bitmapArr[i] = (Bitmap) objArr[i];
                i++;
            }
            this.mDuMixController.getARProxyManager().getCaptureAR().sendImageToLua(bitmapArr);
        } else if (objArr[0] instanceof String) {
            String[] strArr = new String[objArr.length];
            while (i < objArr.length) {
                strArr[i] = (String) objArr[i];
                i++;
            }
            this.mDuMixController.getARProxyManager().getCaptureAR().sendBase64ImageToLua(strArr);
        }
    }

    public static boolean verifyStickPath(String str) {
        return !TextUtils.isEmpty(str);
    }

    public void setFaceListener(FaceListener faceListener) {
        this.mFaceListener = faceListener;
    }

    public void addLuaMsgListener(LuaMsgListener luaMsgListener) {
        this.mLuaMsgListener = luaMsgListener;
    }

    public boolean removeLuaMsgListener(LuaMsgListener luaMsgListener) {
        DuMixController duMixController = this.mDuMixController;
        if (duMixController != null) {
            return duMixController.removeLuaMsgListener(luaMsgListener);
        }
        return false;
    }

    public void setDefinedLuaListener(DefinedLuaListener definedLuaListener) {
        this.mDefinedLuaListener = definedLuaListener;
    }

    public void setFilterStateListener(FilterStateListener filterStateListener) {
        this.mFilterStateListener = filterStateListener;
    }

    public void setDuMixStateListener(DuMixStateListener duMixStateListener) {
        this.mDuMixStateListener = duMixStateListener;
    }

    public void clearCase() {
        FilterStateListener filterStateListener = this.mFilterStateListener;
        if (filterStateListener != null) {
            filterStateListener.onFilterStateChanged(null, null);
        }
        if (this.mDuMixController == null || !isSetUp()) {
            return;
        }
        this.mDuMixController.clearCase();
    }

    public static boolean verifyFilterPath(File file) {
        return file != null && new File(file, FILTER_DEF_CONFIG_FILE_NAME).exists();
    }

    public void setInputTexture(Texture texture) {
        this.mInputTexture = texture;
    }

    public void setOutputTexture(Texture texture) {
        this.mOutputTexture = texture;
    }

    public void destroyInputAndOutputTexture() {
        releaseTexture(this.mInputTexture);
        releaseTexture(this.mOutputTexture);
        this.mInputTexture = null;
        this.mOutputTexture = null;
    }

    private void releaseTexture(Texture texture) {
        IGLRenderer gLRenderer;
        DuMixController duMixController = this.mDuMixController;
        if (duMixController == null || texture == null || (gLRenderer = duMixController.getGLRenderer()) == null) {
            return;
        }
        gLRenderer.destroyTexture(texture);
    }

    public void resetInputAndOutputTexture() {
        this.mInputTexture = null;
        this.mOutputTexture = null;
    }

    public int getOutputTexId() {
        Texture texture = this.mOutputTexture;
        if (texture != null) {
            return texture.getId();
        }
        return 0;
    }

    public void render() {
        IGLRenderer gLRenderer;
        DuMixController duMixController = this.mDuMixController;
        if (duMixController == null || (gLRenderer = duMixController.getGLRenderer()) == null) {
            return;
        }
        if (sSyncInputContent) {
            GLES20.glFinish();
        }
        gLRenderer.render();
    }

    public void setRenderFinishListener() {
        DuMixController duMixController;
        IGLRenderer gLRenderer;
        if (!sSyncInputContent || (duMixController = this.mDuMixController) == null || (gLRenderer = duMixController.getGLRenderer()) == null) {
            return;
        }
        gLRenderer.addFrameRenderListener(new FrameRenderListener() { // from class: com.baidu.minivideo.arface.ARControllerProxy.5
            @Override // com.baidu.p120ar.arrender.FrameRenderListener
            public void onRenderStarted(long j) {
            }

            @Override // com.baidu.p120ar.arrender.FrameRenderListener
            public void onRenderFinished(long j) {
                GLES20.glFinish();
            }
        });
    }

    public static void setSyncInputContent(boolean z) {
        sSyncInputContent = z;
        m18110d("DuAr_DuController", "setSyncInputContent : " + z);
    }

    public static boolean checkProxy(ARControllerProxy aRControllerProxy, Object obj) {
        return (aRControllerProxy == null || aRControllerProxy.isReleased() || aRControllerProxy.getHolder() != obj) ? false : true;
    }

    private void cancleAllQualityFilterConfig() {
        DuMixController duMixController = this.mDuMixController;
        if (duMixController != null) {
            duMixController.updateFilter((FilterParam) FilterParam.TuneColorFilter.brightness, 0.0f);
            duMixController.updateFilter((FilterParam) FilterParam.TuneColorFilter.contrast, 1.0f);
            duMixController.updateFilter((FilterParam) FilterParam.TuneColorFilter.saturation, 1.0f);
        }
    }

    public void updateFilterBrightness(float f) {
        if (isDumixEnable()) {
            this.mDuMixController.updateFilter(FilterParam.TuneColorFilter.brightness, f);
        }
    }

    public void updateFilterContrast(float f) {
        if (isDumixEnable()) {
            this.mDuMixController.updateFilter(FilterParam.TuneColorFilter.contrast, f);
        }
    }

    public void updateFilterSaturation(float f) {
        if (isDumixEnable()) {
            this.mDuMixController.updateFilter(FilterParam.TuneColorFilter.saturation, f);
        }
    }

    public void updateFilterCurveIntensity(float f) {
        DuMixController duMixController = this.mDuMixController;
        if (duMixController != null) {
            duMixController.updateFilter(FilterParam.TuneColorFilter.curve, f);
        }
    }

    private void resetCurveDefaultParm() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Point(0, 0));
        arrayList2.add(new Point(75, 61));
        arrayList2.add(new Point(175, 172));
        arrayList2.add(new Point(255, 255));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new Point(0, 0));
        arrayList3.add(new Point(121, 122));
        arrayList3.add(new Point(255, 255));
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new Point(0, 0));
        arrayList4.add(new Point(125, 117));
        arrayList4.add(new Point(255, 255));
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(new Point(0, 0));
        arrayList5.add(new Point(127, 111));
        arrayList5.add(new Point(255, 255));
        arrayList.add(arrayList2);
        arrayList.add(arrayList3);
        arrayList.add(arrayList4);
        arrayList.add(arrayList5);
        setCurve(arrayList);
    }

    private void resetColorFilterDefaultParm() {
        DuMixController duMixController;
        if (!isDumixEnable() || (duMixController = this.mDuMixController) == null) {
            return;
        }
        duMixController.updateFilter((FilterParam) FilterParam.TuneColorFilter.brightness, 0.03f);
        duMixController.updateFilter((FilterParam) FilterParam.TuneColorFilter.contrast, 0.86f);
        duMixController.updateFilter((FilterParam) FilterParam.TuneColorFilter.saturation, 0.79f);
    }

    public void resetAllQualityParm() {
        if (isDumixEnable()) {
            resetCurveDefaultParm();
            resetColorFilterDefaultParm();
        }
    }

    public void setAllQualityParmForCartoon() {
        if (isDumixEnable()) {
            resetCurveDefaultParm();
            DuMixController duMixController = this.mDuMixController;
            if (duMixController != null) {
                duMixController.updateFilter((FilterParam) FilterParam.TuneColorFilter.brightness, -0.01f);
                duMixController.updateFilter((FilterParam) FilterParam.TuneColorFilter.contrast, 0.86f);
                duMixController.updateFilter((FilterParam) FilterParam.TuneColorFilter.saturation, 0.79f);
            }
        }
    }

    public void startCaptureAbility() {
        if (isDumixEnable()) {
            this.mDuMixController.startAbility("ability_capture", (HashMap<String, Object>) null);
        }
    }

    public void stopCaptureAbility() {
        if (isDumixEnable()) {
            this.mDuMixController.stopAbility("ability_capture");
        }
    }

    public void capture(ICallbackWith<ICaptureResult> iCallbackWith) {
        if (isDumixEnable()) {
            this.mDuMixController.getARProxyManager().getCaptureAR().capture(iCallbackWith);
        }
    }
}
