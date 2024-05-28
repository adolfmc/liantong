package com.baidu.cloud.frameprocessor.p133ar;

import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.baidu.minivideo.arface.ARControllerProxy;
import com.baidu.minivideo.arface.ArFaceSdk;
import com.baidu.minivideo.arface.DuArResConfig;
import com.baidu.minivideo.arface.IDumixRenderer;
import com.baidu.minivideo.arface.bean.BeautyType;
import com.baidu.minivideo.arface.bean.Filter;
import com.baidu.minivideo.arface.bean.Makeup;
import com.baidu.minivideo.arface.bean.Sticker;
import com.baidu.minivideo.arface.utils.ThreadPool;
import com.baidu.p120ar.ARType;
import com.baidu.p120ar.DuMixCallback;
import com.baidu.p120ar.DuMixErrorType;
import com.baidu.p120ar.DuMixInput;
import com.baidu.p120ar.DuMixOutput;
import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.frameprocessor.ar.ARProcessor */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARProcessor extends ARBaseProcessor implements IDumixRenderer {
    public static final String DEF_FILTER_ID = "500001";
    private static final float DEF_FILTER_VALUE = 0.35f;
    private static final String TAG = "com.baidu.cloud.frameprocessor.ar.ARProcessor";
    private boolean isShowDefBeautifulValue;
    private boolean isShowDefFilterValue;
    private String mAbilityPath;
    private Sticker mBackUp;
    private ConcurrentHashMap<BeautyType, Object> mBeautyMap;
    private String mCaseId;
    private String mCasePath;
    private DuMixCallback mDuMixCallback;
    private boolean mEnableDefaultBeauty;
    private volatile boolean mImageQualityOpen;
    private volatile boolean mIsOverrideParm;
    private Sticker mSticker;
    private String mStickerTabNm;
    private Filter mfilter;

    public ARProcessor(Context context) {
        super(context);
        this.mBeautyMap = new SafeConcurrentHashMap();
        this.isShowDefFilterValue = true;
        this.isShowDefBeautifulValue = true;
        this.mIsOverrideParm = false;
        this.mEnableDefaultBeauty = true;
    }

    @Override // com.baidu.cloud.frameprocessor.p133ar.ARBaseProcessor
    protected boolean checkEffectData() {
        ConcurrentHashMap<BeautyType, Object> concurrentHashMap = this.mBeautyMap;
        return ((concurrentHashMap == null || concurrentHashMap.size() == 0) && this.mSticker == null && !this.mImageQualityOpen && this.mCasePath == null) ? false : true;
    }

    public void setDuMixCallback(DuMixCallback duMixCallback) {
        this.mDuMixCallback = duMixCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initEffectValue() {
        if (this.mEnableDefaultBeauty) {
            initDefParams();
            setFilter(getFilter());
            setBeautyValues();
            initDefaultMakeUp();
        }
        setSticker(getSticker());
        loadCase(this.mCasePath, this.mCaseId, this.mAbilityPath);
    }

    private void initDefParams() {
        if (isSetup()) {
            if (ArFaceSdk.getResConfig() != null) {
                ArFaceSdk.getResConfig();
                setInitFilterPath(DuArResConfig.getInitFilterDir(true));
            }
            initCurve();
            setOverrideDefaultParm(this.mIsOverrideParm);
        }
    }

    private void initCurve() {
        if (this.mEffect != null) {
            this.mEffect.initCurve(true);
        }
    }

    private void initDefaultMakeUp() {
        setInitFilterPath(getBlackEyeCirclePath());
    }

    private String getMakeupDir() {
        String makeupDir = ArFaceSdk.getResConfig() != null ? DuArResConfig.getMakeupDir() : null;
        if (TextUtils.isEmpty(makeupDir) || makeupDir.charAt(makeupDir.length() - 1) == File.separatorChar) {
            return makeupDir;
        }
        return makeupDir + File.separator;
    }

    private String getBlackEyeCirclePath() {
        return getMakeupDir() + "blackeyecircle/open";
    }

    private String getLaughlinePath() {
        return getMakeupDir() + "laughline/open";
    }

    private void setBeautyValues() {
        if (this.mEffect != null) {
            this.mEffect.setBeautyValues(this.mBeautyMap);
        }
    }

    public void setEnableDefaultBeauty(boolean z) {
        if (this.mEnableDefaultBeauty != z && z) {
            initEffectValue();
        }
        this.mEnableDefaultBeauty = z;
    }

    public void setBeautyWhite(float f) {
        BeautyType beautyType = BeautyType.whiten;
        this.mBeautyMap.put(beautyType, Float.valueOf(f));
        if (this.mEffect != null) {
            this.mEffect.setBeautyValue(beautyType, f);
        }
    }

    public void setBeautyBlure(float f) {
        BeautyType beautyType = BeautyType.smooth;
        this.mBeautyMap.put(beautyType, Float.valueOf(f));
        if (this.mEffect != null) {
            this.mEffect.setBeautyValue(beautyType, f);
        }
    }

    public void setEnlargeEye(float f) {
        BeautyType beautyType = BeautyType.eye;
        this.mBeautyMap.put(beautyType, Float.valueOf(f));
        if (this.mEffect != null) {
            this.mEffect.setBeautyValue(beautyType, f);
        }
    }

    public void setCheekThin(float f) {
        BeautyType beautyType = BeautyType.thinFace;
        this.mBeautyMap.put(beautyType, Float.valueOf(f));
        if (this.mEffect != null) {
            this.mEffect.setBeautyValue(beautyType, f);
        }
    }

    public float getBeautyWhite() {
        Object obj = this.mBeautyMap.get(BeautyType.whiten);
        if (obj instanceof Float) {
            return ((Float) obj).floatValue();
        }
        return 0.0f;
    }

    public float getBeautyBlure() {
        Object obj = this.mBeautyMap.get(BeautyType.smooth);
        if (obj instanceof Float) {
            return ((Float) obj).floatValue();
        }
        return 0.0f;
    }

    public float getEnlargeEye() {
        Object obj = this.mBeautyMap.get(BeautyType.eye);
        if (obj instanceof Float) {
            return ((Float) obj).floatValue();
        }
        return 0.0f;
    }

    public float getCheekThin() {
        Object obj = this.mBeautyMap.get(BeautyType.thinFace);
        if (obj instanceof Float) {
            return ((Float) obj).floatValue();
        }
        return 0.0f;
    }

    public void setSticker(Sticker sticker) {
        if (!isSetup() || this.mEffect == null || this.mEffect.isPaused()) {
            this.mBackUp = sticker;
        } else if (sticker == null) {
            this.mSticker = sticker;
            this.mEffect.clearCase();
        } else if (checkTipResFile(sticker)) {
            Sticker.AbilityModel abilityModel = sticker.getAbilityModel();
            this.mSticker = sticker;
            if (abilityModel != null) {
                this.mEffect.setMdlModelPath(abilityModel.getPath());
            }
            int arType = sticker.getArType();
            String id = sticker.getId();
            this.mSticker = sticker;
            this.mEffect.loadCase(ARType.valueOf(arType), sticker.getPath(), id);
        }
    }

    public void clearBackUpSticker() {
        this.mBackUp = null;
    }

    public void loadCase(String str, String str2) {
        loadCase(str, str2, null);
    }

    public void loadCase(String str, String str2, String str3) {
        this.mCasePath = str;
        this.mCaseId = str2;
        this.mAbilityPath = str3;
        if (!isSetup() || this.mEffect == null || TextUtils.isEmpty(this.mCasePath)) {
            return;
        }
        if (!TextUtils.isEmpty(str3)) {
            this.mEffect.setMdlModelPath(str3);
        }
        this.mEffect.loadCase(str, str2);
    }

    public void clearCase() {
        if (this.mEffect != null) {
            this.mEffect.clearCase();
        }
        this.mCasePath = null;
    }

    private boolean loadLib(String str) {
        try {
            System.load(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkTipResFile(Sticker sticker) {
        return sticker.isSupport(ARControllerProxy.getVersion()) && !TextUtils.isEmpty(sticker.getPath()) && new File(sticker.getPath()).exists() && ARControllerProxy.verifyStickPath(sticker.getPath());
    }

    public void setFilter(Filter filter2) {
        String str;
        float f;
        this.mfilter = filter2;
        if (filter2 == null && !this.isShowDefFilterValue) {
            f = 0.0f;
            str = null;
        } else if (filter2 == null || "500001".equals(filter2.getParam())) {
            if (ArFaceSdk.getResConfig() != null) {
                ArFaceSdk.getResConfig();
                str = DuArResConfig.getFilterYuanTuPath();
            } else {
                str = null;
            }
            f = 0.35f;
        } else {
            File lutFile = getLutFile(filter2.getFile());
            String absolutePath = lutFile != null ? lutFile.getAbsolutePath() : null;
            f = filter2.getLevel();
            str = absolutePath;
        }
        Object obj = this.mBeautyMap.get(BeautyType.lutFile);
        String str2 = obj instanceof String ? (String) obj : null;
        if (str != null && !str.equals(str2)) {
            this.mBeautyMap.put(BeautyType.lutFile, str);
            if (this.mEffect != null && str != null) {
                this.mEffect.setBeautyValue(BeautyType.lutFile, str);
            }
        }
        setFilterLevel(f);
    }

    public void setLutFilterForDebug(String str) {
        this.mEffect.setBeautyValue(BeautyType.lutFile, str);
        setFilterLevel(0.4f);
    }

    private File getLutFile(File file) {
        if (file == null) {
            return null;
        }
        if (file.isFile()) {
            return file;
        }
        File[] listFiles = file.listFiles();
        for (int i = 0; listFiles != null && i < listFiles.length; i++) {
            String name = listFiles[i].getName();
            if (name.substring(name.lastIndexOf(".") + 1).toLowerCase().equals("png")) {
                return listFiles[i];
            }
        }
        return null;
    }

    public void setFilterLevel(float f) {
        BeautyType beautyType = BeautyType.lutIntensity;
        Object obj = this.mBeautyMap.get(beautyType);
        if ((obj instanceof Float) && ((Float) obj).floatValue() == f) {
            return;
        }
        this.mBeautyMap.put(beautyType, Float.valueOf(f));
        if (this.mEffect != null) {
            this.mEffect.setBeautyValue(beautyType, f);
        }
    }

    public float getFilterLevel() {
        Object obj = this.mBeautyMap.get(BeautyType.lutIntensity);
        if (obj instanceof Float) {
            return ((Float) obj).floatValue();
        }
        return 0.0f;
    }

    public void setMakeup(Makeup makeup) {
        if (makeup == null) {
            return;
        }
        BeautyType type = makeup.getType();
        this.mBeautyMap.put(type, makeup);
        if (this.mEffect != null) {
            this.mEffect.setBeautyValue(type, makeup);
        }
    }

    public void setMakeupValue(BeautyType beautyType, float f) {
        Object obj = this.mBeautyMap.get(beautyType);
        if (obj instanceof Makeup) {
            ((Makeup) obj).setValue(f);
        }
        if (this.mEffect != null) {
            this.mEffect.setBeautyValue(beautyType, f);
        }
    }

    @Override // com.baidu.minivideo.arface.IDumixRenderer
    public void setBeautyValue(BeautyType beautyType, int i) {
        this.mBeautyMap.put(beautyType, Integer.valueOf(i));
        if (this.mEffect == null || !this.mIsSetup) {
            return;
        }
        this.mEffect.setBeautyValue(beautyType, i);
    }

    @Override // com.baidu.minivideo.arface.IDumixRenderer
    public void setBeautyValue(BeautyType beautyType, float f) {
        this.mBeautyMap.put(beautyType, Float.valueOf(f));
        if (this.mEffect == null || !this.mIsSetup) {
            return;
        }
        this.mEffect.setBeautyValue(beautyType, f);
    }

    @Override // com.baidu.minivideo.arface.IDumixRenderer
    public void setBeautyValue(BeautyType beautyType, String str) {
        this.mBeautyMap.put(beautyType, str);
        if (this.mEffect == null || !this.mIsSetup) {
            return;
        }
        this.mEffect.setBeautyValue(beautyType, str);
    }

    @Override // com.baidu.minivideo.arface.IDumixRenderer
    public void setBeautyValue(BeautyType beautyType, float[] fArr) {
        this.mBeautyMap.put(beautyType, fArr);
        if (this.mEffect == null || !this.mIsSetup) {
            return;
        }
        this.mEffect.setBeautyValue(beautyType, fArr);
    }

    public void setInitFilterPath(String str) {
        if (this.mEffect != null) {
            this.mEffect.setInitFilterPath(str);
        }
    }

    public void setCurve(List<List<Point>> list) {
        if (this.mEffect != null) {
            this.mEffect.setCurve(list);
        }
    }

    public void setOverrideDefaultParm(boolean z) {
        this.mIsOverrideParm = z;
        if (isSetup() && this.mEffect != null && this.mIsOverrideParm) {
            this.mEffect.setAllQualityParmForCartoon();
        }
    }

    public Sticker getSticker() {
        Sticker sticker = this.mSticker;
        return sticker == null ? this.mBackUp : sticker;
    }

    public String getStickerTab() {
        return this.mStickerTabNm;
    }

    public Filter getFilter() {
        return this.mfilter;
    }

    public void setShowDefFilterValue(boolean z) {
        this.isShowDefFilterValue = z;
    }

    public boolean isMale() {
        return this.mMale;
    }

    @Override // com.baidu.cloud.frameprocessor.p133ar.ARBaseProcessor
    protected DuMixCallback generateDuMixCallback() {
        return new DuMixCallback() { // from class: com.baidu.cloud.frameprocessor.ar.ARProcessor.1
            @Override // com.baidu.p120ar.DuMixCallback
            public void onSetup(boolean z, DuMixInput duMixInput, DuMixOutput duMixOutput) {
                ARProcessor.this.onSetup(z);
                if (ARProcessor.this.mDuMixCallback != null) {
                    ARProcessor.this.mDuMixCallback.onSetup(z, duMixInput, duMixOutput);
                }
                if (!z) {
                    String str = ARProcessor.TAG;
                    Log.d(str, "onSetup返回失败：" + z);
                }
                ThreadPool.computation().execute(new Runnable() { // from class: com.baidu.cloud.frameprocessor.ar.ARProcessor.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ARProcessor.this.initEffectValue();
                    }
                });
            }

            @Override // com.baidu.p120ar.DuMixCallback
            public void onCaseCreate(boolean z, String str, String str2) {
                if (ARProcessor.this.mDuMixCallback != null) {
                    ARProcessor.this.mDuMixCallback.onCaseCreate(z, str, str2);
                }
            }

            @Override // com.baidu.p120ar.DuMixCallback
            public void onCaseDestroy() {
                if (ARProcessor.this.mDuMixCallback != null) {
                    ARProcessor.this.mDuMixCallback.onCaseDestroy();
                }
            }

            @Override // com.baidu.p120ar.DuMixCallback
            public void onRelease() {
                if (ARProcessor.this.mDuMixCallback != null) {
                    ARProcessor.this.mDuMixCallback.onRelease();
                }
                ARProcessor.this.mIsSetup = false;
            }

            @Override // com.baidu.p120ar.DuMixCallback
            public void onError(DuMixErrorType duMixErrorType, String str, String str2) {
                if (ARProcessor.this.mDuMixCallback != null) {
                    ARProcessor.this.mDuMixCallback.onError(duMixErrorType, str, str2);
                }
            }
        };
    }

    public boolean onTouchEvent(View view, MotionEvent motionEvent) {
        Sticker sticker = getSticker();
        return (sticker != null && sticker.isTouchAble()) && this.mEffect != null && this.mEffect.onTouchEvent(view, motionEvent);
    }

    public boolean setStickerEffect(Sticker sticker, String str) {
        this.mSticker = sticker;
        this.mStickerTabNm = str;
        if (sticker == null) {
            clearBackUpSticker();
        }
        setSticker(sticker);
        return true;
    }

    public void updateFilterBrightness(float f) {
        if (this.mEffect != null) {
            this.mEffect.updateFilterBrightness(f);
        }
    }

    public void updateFilterContrast(float f) {
        if (this.mEffect != null) {
            this.mEffect.updateFilterContrast(f);
        }
    }

    public void updateFilterSaturation(float f) {
        if (this.mEffect != null) {
            this.mEffect.updateFilterSaturation(f);
        }
    }

    public void resetAllQualityParm() {
        if (this.mEffect != null) {
            this.mEffect.resetAllQualityParm();
        }
    }
}
