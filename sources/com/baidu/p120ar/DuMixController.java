package com.baidu.p120ar;

import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.baidu.p120ar.ability.AbilityType;
import com.baidu.p120ar.ability.IAbility;
import com.baidu.p120ar.abilityscheme.AbilitySchemeControl;
import com.baidu.p120ar.arplay.core.engine.rotate.OrientationManager;
import com.baidu.p120ar.arplay.core.pixel.IPixelReader;
import com.baidu.p120ar.arplay.core.pixel.PixelReadListener;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelRotation;
import com.baidu.p120ar.arrender.ARRenderFpsCallback;
import com.baidu.p120ar.arrender.ARRenderer;
import com.baidu.p120ar.arrender.ARRenderer2;
import com.baidu.p120ar.arrender.FrameRenderListener;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.arrender.IGLRenderer;
import com.baidu.p120ar.arrender.Texture;
import com.baidu.p120ar.async.AsyncTaskManager;
import com.baidu.p120ar.auth.ARAuth;
import com.baidu.p120ar.auth.ARAuthAPI;
import com.baidu.p120ar.auth.IAuthenticator;
import com.baidu.p120ar.auth.IDuMixAuthCallback;
import com.baidu.p120ar.auth.IOfflineAuthenticator;
import com.baidu.p120ar.bean.CaseModel;
import com.baidu.p120ar.bean.Watermark;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.content.IContentPlatform;
import com.baidu.p120ar.filter.ARFilterManager;
import com.baidu.p120ar.filter.FilterNode;
import com.baidu.p120ar.filter.FilterParam;
import com.baidu.p120ar.filter.FilterStateListener;
import com.baidu.p120ar.filter.IFilter;
import com.baidu.p120ar.libloader.ILibLoaderPlugin;
import com.baidu.p120ar.libloader.LibLoader;
import com.baidu.p120ar.lua.EngineMsgBridge;
import com.baidu.p120ar.lua.ILua;
import com.baidu.p120ar.lua.LuaMsgHelper;
import com.baidu.p120ar.lua.LuaMsgListener;
import com.baidu.p120ar.photo.IPhoto;
import com.baidu.p120ar.photo.PhotoCallback;
import com.baidu.p120ar.photo.PhotoTask;
import com.baidu.p120ar.record.ARRecorder;
import com.baidu.p120ar.record.IRecord;
import com.baidu.p120ar.record.RecordCallback;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.steploading.IStepLoading;
import com.baidu.p120ar.utils.ARFileUtils;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.ARSDKInfo;
import com.baidu.p120ar.utils.ReflectionUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.DuMixController */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DuMixController implements IDuMix, IInternal, IAbility, IPixelReader, IFilter, ILua, IPhoto, IRecord {
    private static final long LOCK_WAIT_TIME_MAX = 3000;
    private static final int MSG_CHANGE_INPUT_SIZE = 3010;
    private static final int MSG_CHANGE_OUTPUT = 3007;
    private static final int MSG_CHANGE_OUTPUT_SIZE = 3006;
    private static final int MSG_CLEAR_CASE = 3005;
    private static final int MSG_DUMIX_PAUSE = 3001;
    private static final int MSG_DUMIX_RELEASE = 3003;
    private static final int MSG_DUMIX_RESUME = 3002;
    private static final int MSG_DUMIX_SETUP = 3000;
    private static final int MSG_LOAD_CASE = 3004;
    private static final int MSG_PAUSE_SCENE = 3008;
    private static final int MSG_RESUME_SCENE = 3009;
    private static final int STATE_RELEASING = 3;
    private static final int STATE_RUNNINT = 2;
    private static final int STATE_SETUPING = 1;
    private static final int STATE_STOPPED = 0;
    private static final String TAG = "DuMixController";
    private static volatile DuMixController sInstance;
    private static volatile Object sLock = new Object();
    private static volatile int sState;
    private ARFilterManager mARFilterManager;
    private ARRecorder mARRecorder;
    private ARRenderer mARRenderer;
    private AbilityManager mAbilityManager;
    private AbilitySchemeControl mAbilitySchemeControl;
    private Handler mCallbackHandler;
    private DuMixCallback mCallbackProxy;
    private IContentPlatform mContentPlatform;
    private Context mContext;
    private Handler mControlHandler;
    private HandlerThread mControlThread;
    private ControllerHelper mControllerHelper;
    private DefaultParams mDefaultParams;
    protected DuMixCallback mDuMixCallback;
    private DuMixInput mDuMixInput;
    private DuMixOutput mDuMixOutput;
    private EngineMsgBridge mEngineMsgBridge;
    private LuaMsgHelper mLuaMsgHelper;
    private OrientationManager mOrientationManager;

    private DuMixController(Context context, DefaultParams defaultParams) {
        this.mContext = context;
        if (defaultParams != null) {
            this.mDefaultParams = defaultParams;
        } else {
            this.mDefaultParams = new DefaultParams();
        }
        ARLog.m20421d("DuMixController", "create DuMixController sState = " + sState);
        if (sState == 3) {
            synchronized (sLock) {
                try {
                    ARLog.m20421d("DuMixController", "create DuMixController wait for release!");
                    sLock.wait(3000L);
                } catch (Exception unused) {
                    ARLog.m20419e("DuMixController", "create DuMixController wait error!!!");
                }
            }
        }
        this.mControlThread = new HandlerThread("DuMixController");
        this.mControlThread.start();
        this.mControlHandler = new ControlHandler(this.mControlThread.getLooper());
        AsyncTaskManager.getInstance().setControllerLooper(this.mControlThread.getLooper());
        createManagers(this.mContext, this.mDefaultParams);
    }

    public static DuMixController getInstance(Context context, DefaultParams defaultParams) {
        if (context == null) {
            ARLog.m20419e("DuMixController", "getInstance() context must be set!!!");
            return null;
        }
        if (sInstance == null) {
            synchronized (DuMixController.class) {
                if (sInstance == null) {
                    sInstance = new DuMixController(context, defaultParams);
                }
            }
        }
        return sInstance;
    }

    @Override // com.baidu.p120ar.IDuMix
    public void setup(DuMixInput duMixInput, DuMixOutput duMixOutput, DuMixCallback duMixCallback) {
        ARLog.m20421d("DuMixController", "setup() sState = " + sState);
        if (duMixInput == null || duMixOutput == null) {
            ARLog.m20419e("DuMixController", "setup error!!! params maybe null!!!");
            if (duMixCallback != null) {
                duMixCallback.onSetup(false, duMixInput, duMixOutput);
                return;
            }
            return;
        }
        this.mDuMixInput = duMixInput;
        this.mDuMixOutput = duMixOutput;
        this.mDuMixCallback = duMixCallback;
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(3000));
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void changeInputSize(int i, int i2) {
        ARLog.m20421d("DuMixController", "changeInputSize width * height = " + i + " * " + i2);
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(3010, i, i2, null));
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void changeInputSize(SurfaceTexture surfaceTexture, int i, int i2) {
        ARLog.m20421d("DuMixController", "changeInputSize width * height = " + i + " * " + i2 + " && texture = " + surfaceTexture);
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(3010, i, i2, surfaceTexture));
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void changeInputSize(Texture texture, int i, int i2) {
        ARLog.m20421d("DuMixController", "changeInputSize width * height = " + i + " * " + i2 + " && texture = " + texture);
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(3010, i, i2, texture));
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void changeOutputSize(int i, int i2) {
        ARLog.m20421d("DuMixController", "changeOutputSize width * height = " + i + " * " + i2);
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(3006, i, i2));
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void changeOutputObject(Object obj, int i, int i2) {
        ARRenderer aRRenderer;
        if (obj == null || (aRRenderer = this.mARRenderer) == null) {
            return;
        }
        aRRenderer.changeOutput(obj, i, i2);
    }

    @Override // com.baidu.p120ar.IDuMix
    public void changeOutput(DuMixOutput duMixOutput) {
        Handler handler;
        if (duMixOutput == null || (handler = this.mControlHandler) == null) {
            return;
        }
        handler.sendMessage(handler.obtainMessage(3007, duMixOutput));
    }

    @Override // com.baidu.p120ar.IDuMix
    public void addOutput(DuMixOutput duMixOutput) {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.addOutputSurface(duMixOutput);
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void removeOutput(DuMixOutput duMixOutput) {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.removeOutputSurface(duMixOutput);
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void addFrameRenderListener(FrameRenderListener frameRenderListener) {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.addFrameRenderListener(frameRenderListener);
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void removeFrameRenderListener(FrameRenderListener frameRenderListener) {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.removeFrameRenderListener(frameRenderListener);
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void loadCase(String str, String str2) {
        loadCase(null, str, str2);
    }

    @Override // com.baidu.p120ar.IDuMix
    public void loadCase(ARType aRType, String str, String str2) {
        ARLog.m20421d("DuMixController", "AR loadCase");
        if (this.mControlHandler != null) {
            CaseModel caseModel = new CaseModel(aRType, str, str2);
            Handler handler = this.mControlHandler;
            handler.sendMessage(handler.obtainMessage(3004, caseModel));
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void clearCase() {
        ARLog.m20421d("DuMixController", "AR clearCase");
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(3005));
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void pause() {
        ARLog.m20421d("DuMixController", "AR pause");
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(3001));
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void resume() {
        ARLog.m20421d("DuMixController", "AR resume");
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(3002));
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void pauseScene() {
        ARLog.m20421d("DuMixController", "AR pauseScene");
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(3008));
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void resumeScene() {
        ARLog.m20421d("DuMixController", "AR resumeScene");
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(3009));
        }
    }

    @Override // com.baidu.p120ar.IDuMix
    public void setStateListener(DuMixStateListener duMixStateListener) {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.setStateListener(duMixStateListener);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.mARRenderer == null || sState != 2) {
            return false;
        }
        return this.mARRenderer.onTouch(view, motionEvent);
    }

    @Override // com.baidu.p120ar.IDuMix
    public void release() {
        ARLog.m20421d("DuMixController", "release() sState = " + sState);
        if (sState == 1) {
            ARRenderer aRRenderer = this.mARRenderer;
            if (aRRenderer != null && aRRenderer.isEngineLoading()) {
                this.mARRenderer.setInterruptLoading(true);
                sState = 0;
                sInstance = null;
            } else {
                synchronized (sLock) {
                    try {
                        ARLog.m20421d("DuMixController", "release DuMixController wait for setup!");
                        sLock.wait(3000L);
                    } catch (Exception unused) {
                        ARLog.m20419e("DuMixController", "release DuMixController wait error!!!");
                    }
                }
            }
        }
        if (sState == 0) {
            sInstance = null;
        } else if (sState == 3) {
        } else {
            if (sState != 2) {
                ARLog.m20419e("DuMixController", "release error!!!");
                return;
            }
            sState = 3;
            Handler handler = this.mControlHandler;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                Handler handler2 = this.mControlHandler;
                handler2.sendMessage(handler2.obtainMessage(3003));
            }
            sInstance = null;
        }
    }

    @Override // com.baidu.p120ar.lua.ILua
    public boolean sendMsg2Lua(HashMap<String, Object> hashMap) {
        if (this.mEngineMsgBridge == null || sState != 2) {
            return false;
        }
        this.mEngineMsgBridge.sendMsg2Engine(1902, hashMap);
        return true;
    }

    @Override // com.baidu.p120ar.lua.ILua
    public boolean addLuaMsgListener(LuaMsgListener luaMsgListener) {
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge == null || engineMsgBridge.getLuaMsgBridge() == null) {
            return false;
        }
        return this.mEngineMsgBridge.getLuaMsgBridge().addLuaMsgListener(luaMsgListener);
    }

    @Override // com.baidu.p120ar.lua.ILua
    public boolean removeLuaMsgListener(LuaMsgListener luaMsgListener) {
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge == null || engineMsgBridge.getLuaMsgBridge() == null) {
            return false;
        }
        return this.mEngineMsgBridge.getLuaMsgBridge().removeLuaMsgListener(luaMsgListener);
    }

    @Override // com.baidu.p120ar.lua.ILua
    public boolean sendLuaScript2Engine(String str) {
        if (this.mEngineMsgBridge == null || sState != 2) {
            return false;
        }
        this.mEngineMsgBridge.sendLuaScript2Engine(str);
        return true;
    }

    @Override // com.baidu.p120ar.lua.ILua
    public void setDefinedLuaListener(DefinedLuaListener definedLuaListener) {
        if (this.mLuaMsgHelper == null) {
            this.mLuaMsgHelper = new LuaMsgHelper(this.mEngineMsgBridge);
        }
        this.mLuaMsgHelper.setDefinedLuaListener(definedLuaListener);
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean addAbility(String str, String str2) {
        return addAbility(str, str2, null);
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean addAbility(String str, String str2, String str3) {
        AbilityManager abilityManager = this.mAbilityManager;
        if (abilityManager != null) {
            return abilityManager.addAbility(str, Arrays.asList(str2), str3);
        }
        return false;
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean addAbility(String str, List<String> list) {
        AbilityManager abilityManager = this.mAbilityManager;
        if (abilityManager != null) {
            return abilityManager.addAbility(str, list, null);
        }
        return false;
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public List<String> getSupportedAbilities() {
        if (this.mAbilityManager == null || sState != 2) {
            return null;
        }
        return this.mAbilityManager.getSupportedAbilities();
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean isAbilitySupported(String str) {
        if (this.mAbilityManager == null || sState != 2) {
            return false;
        }
        return this.mAbilityManager.isAbilitySupported(str);
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public List<String> getActiveAbilities() {
        if (this.mAbilityManager == null || sState != 2) {
            return null;
        }
        return this.mAbilityManager.getActiveAbilities();
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean isAbilityActive(String str) {
        if (this.mAbilityManager == null || TextUtils.isEmpty(str) || sState != 2) {
            return false;
        }
        return this.mAbilityManager.isAbilityActive(str);
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean isAbilityActive(AbilityType abilityType) {
        if (this.mAbilityManager == null || abilityType == null || sState != 2) {
            return false;
        }
        return this.mAbilityManager.isAbilityActive(abilityType.getTypeValue());
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean startAbility(String str, HashMap<String, Object> hashMap) {
        if (this.mAbilityManager == null || TextUtils.isEmpty(str) || sState != 2) {
            return false;
        }
        return this.mAbilityManager.startAbility(str, hashMap, true);
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean startAbility(AbilityType abilityType, HashMap<String, Object> hashMap) {
        if (this.mAbilityManager == null || abilityType == null || sState != 2) {
            return false;
        }
        return this.mAbilityManager.startAbility(abilityType.getTypeValue(), hashMap, true);
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean adjustAbility(String str, HashMap<String, Object> hashMap) {
        if (this.mAbilityManager == null || TextUtils.isEmpty(str) || sState != 2) {
            return false;
        }
        return this.mAbilityManager.adjustAbility(str, hashMap);
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean adjustAbility(AbilityType abilityType, HashMap<String, Object> hashMap) {
        if (this.mAbilityManager == null || abilityType == null || sState != 2) {
            return false;
        }
        return this.mAbilityManager.adjustAbility(abilityType.getTypeValue(), hashMap);
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean stopAbility(String str) {
        if (this.mAbilityManager == null || TextUtils.isEmpty(str) || sState != 2) {
            return false;
        }
        return this.mAbilityManager.stopAbility(str, true);
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public boolean stopAbility(AbilityType abilityType) {
        if (this.mAbilityManager == null || abilityType == null || sState != 2) {
            return false;
        }
        return this.mAbilityManager.stopAbility(abilityType.getTypeValue(), true);
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public ARProxyManager getARProxyManager() {
        AbilityManager abilityManager = this.mAbilityManager;
        if (abilityManager != null) {
            return abilityManager.getARProxyManager();
        }
        return null;
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public void setMdlModelPath(String str) {
        AbilityManager abilityManager = this.mAbilityManager;
        if (abilityManager != null) {
            abilityManager.setMdlModelPath(str);
        }
    }

    @Override // com.baidu.p120ar.ability.IAbility
    public Map<String, Object> getGradingInfo() {
        AbilitySchemeControl abilitySchemeControl = this.mAbilitySchemeControl;
        if (abilitySchemeControl != null) {
            return abilitySchemeControl.getGradingInfo();
        }
        return null;
    }

    @Override // com.baidu.p120ar.filter.IFilter
    public void setFilterStateListener(FilterStateListener filterStateListener) {
        ARFilterManager aRFilterManager = this.mARFilterManager;
        if (aRFilterManager != null) {
            aRFilterManager.setFilterStateListener(filterStateListener);
        }
    }

    @Override // com.baidu.p120ar.filter.IFilter
    public String updateFilterCase(String str) {
        if (this.mARFilterManager == null || sState != 2) {
            return null;
        }
        return this.mARFilterManager.updateFilterCase(str);
    }

    @Override // com.baidu.p120ar.filter.IFilter
    public void updateFilter(FilterParam filterParam, int i) {
        if (this.mARFilterManager == null || sState != 2) {
            return;
        }
        this.mARFilterManager.updateFilter(filterParam, Integer.valueOf(i));
    }

    @Override // com.baidu.p120ar.filter.IFilter
    public void updateFilter(FilterParam filterParam, float f) {
        if (this.mARFilterManager == null || sState != 2) {
            return;
        }
        this.mARFilterManager.updateFilter(filterParam, Float.valueOf(f));
    }

    @Override // com.baidu.p120ar.filter.IFilter
    public void updateFilter(FilterParam filterParam, String str) {
        if (this.mARFilterManager == null || sState != 2) {
            return;
        }
        this.mARFilterManager.updateFilter(filterParam, str);
    }

    @Override // com.baidu.p120ar.filter.IFilter
    public void updateFilter(FilterParam filterParam, float[] fArr) {
        if (this.mARFilterManager == null || sState != 2) {
            return;
        }
        this.mARFilterManager.updateFilter(filterParam, fArr);
    }

    @Override // com.baidu.p120ar.filter.IFilter
    public void updateFilter(FilterParam filterParam, List<Point> list) {
        if (list == null || sState != 2) {
            return;
        }
        float[] fArr = new float[list.size() * 2];
        for (int i = 0; i < list.size(); i++) {
            int i2 = i * 2;
            fArr[i2] = list.get(i).x;
            fArr[i2 + 1] = list.get(i).y;
        }
        ARFilterManager aRFilterManager = this.mARFilterManager;
        if (aRFilterManager == null || filterParam == null) {
            return;
        }
        aRFilterManager.updateFilter(filterParam.getFilterNode().getNodeName(), filterParam.getParamName(), Integer.valueOf(list.size()), "_count", false);
        this.mARFilterManager.updateFilter(filterParam, fArr);
    }

    @Override // com.baidu.p120ar.filter.IFilter
    public void updateFilter(String str, String str2, Object obj) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.mARFilterManager == null || sState != 2) {
            return;
        }
        this.mARFilterManager.updateFilter(str, str2, obj);
    }

    @Override // com.baidu.p120ar.filter.IFilter
    public void updateFaceFilterWithKneadJson(String str) {
        if (this.mARFilterManager == null || TextUtils.isEmpty(str) || sState != 2) {
            return;
        }
        this.mARFilterManager.updateFilter(FilterNode.faceFilter.getNodeName(), FilterParam.FaceFilter.kneadJsonStr.getParamName(), str);
    }

    @Override // com.baidu.p120ar.filter.IFilter
    public void resetAllFilter() {
        if (this.mARFilterManager == null || sState != 2) {
            return;
        }
        this.mARFilterManager.resetAllFilter();
    }

    @Override // com.baidu.p120ar.filter.IFilter
    public void clearAllFilter() {
        if (this.mARFilterManager == null || sState != 2) {
            return;
        }
        this.mARFilterManager.clearAllFilter();
    }

    @Override // com.baidu.p120ar.record.IRecord
    public void setRecordWatermark(Watermark watermark) {
        ARRenderer aRRenderer;
        if (sState == 2) {
            if (this.mARRecorder == null && (aRRenderer = this.mARRenderer) != null) {
                this.mARRecorder = new ARRecorder(this.mContext, aRRenderer);
            }
            ARRecorder aRRecorder = this.mARRecorder;
            if (aRRecorder != null) {
                aRRecorder.setRecordWatermark(watermark);
            }
        }
    }

    @Override // com.baidu.p120ar.record.IRecord
    public void startRecord(String str, long j, RecordCallback recordCallback) {
        DefaultParams defaultParams;
        ARRenderer aRRenderer;
        if (sState == 2) {
            if (this.mARRecorder == null && (aRRenderer = this.mARRenderer) != null) {
                this.mARRecorder = new ARRecorder(this.mContext, aRRenderer);
            }
            ControllerHelper controllerHelper = this.mControllerHelper;
            if (controllerHelper != null) {
                controllerHelper.sendRecordMsg2Lua("start");
            }
            if (this.mARRecorder != null) {
                if (this.mDuMixOutput != null && (defaultParams = this.mDefaultParams) != null && defaultParams.isRecordAutoCrop()) {
                    this.mARRecorder.setCenterCrop(this.mDuMixOutput.getOutputWidth(), this.mDuMixOutput.getOutputHeight());
                }
                this.mARRecorder.startRecord(str, j, recordCallback);
            }
        }
    }

    @Override // com.baidu.p120ar.record.IRecord
    public void pauseRecord() {
        if (this.mARRecorder == null || sState != 2) {
            return;
        }
        this.mARRecorder.pauseRecord();
    }

    @Override // com.baidu.p120ar.record.IRecord
    public void resumeRecord() {
        if (this.mARRecorder == null || sState != 2) {
            return;
        }
        this.mARRecorder.resumeRecord();
    }

    @Override // com.baidu.p120ar.record.IRecord
    public void stopRecord() {
        if (sState == 2) {
            ARRecorder aRRecorder = this.mARRecorder;
            if (aRRecorder != null) {
                aRRecorder.stopRecord();
                this.mARRecorder = null;
            }
            ControllerHelper controllerHelper = this.mControllerHelper;
            if (controllerHelper != null) {
                controllerHelper.sendRecordMsg2Lua("stop");
            }
        }
    }

    @Override // com.baidu.p120ar.photo.IPhoto
    public void takePicture(String str, PhotoCallback photoCallback) {
        if (this.mARRenderer == null || sState != 2) {
            return;
        }
        new PhotoTask().takePicture(this.mARRenderer, str, photoCallback);
    }

    @Override // com.baidu.p120ar.arplay.core.pixel.IPixelReader
    public void createPixelReader(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener) {
        ControllerHelper controllerHelper;
        ARRenderer aRRenderer;
        if ((sState == 0 || sState == 1) && (controllerHelper = this.mControllerHelper) != null) {
            controllerHelper.cachePixelReader(pixelReadParams, pixelReadListener);
        } else if (sState != 2 || (aRRenderer = this.mARRenderer) == null) {
        } else {
            aRRenderer.createPixelReader(pixelReadParams, pixelReadListener);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.pixel.IPixelReader
    public void updatePixelReader(PixelReadParams pixelReadParams, PixelRotation pixelRotation) {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.updatePixelReader(pixelReadParams, pixelRotation);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.pixel.IPixelReader
    public void destroyPixelReader(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener) {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.destroyPixelReader(pixelReadParams, pixelReadListener);
        }
    }

    @Override // com.baidu.p120ar.IInternal
    public void setAuthLicense(byte[] bArr, String str, String str2, String str3) {
        ARAuth.setAuthLicense(bArr, str, str2, str3);
    }

    @Override // com.baidu.p120ar.IInternal
    @Deprecated
    public List<Integer> checkAuth(byte[] bArr, ICallbackWith<List<Integer>> iCallbackWith, ICallbackWith<Integer> iCallbackWith2) {
        return ARAuth.checkAuth(this.mContext, bArr, iCallbackWith, iCallbackWith2);
    }

    @Override // com.baidu.p120ar.IInternal
    @Deprecated
    public List<Integer> checkAuth(byte[] bArr, IDuMixAuthCallback iDuMixAuthCallback) {
        return ARAuth.checkAuth(this.mContext, bArr, iDuMixAuthCallback);
    }

    @Override // com.baidu.p120ar.IInternal
    public void setLibLoadPlugin(ILibLoaderPlugin iLibLoaderPlugin) {
        LibLoader.setLibLoadPlugin(iLibLoaderPlugin);
    }

    @Override // com.baidu.p120ar.IInternal
    public IStepLoading getStepLoading() {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            return aRRenderer.getStepLoadingModule();
        }
        return null;
    }

    @Override // com.baidu.p120ar.IInternal
    public IContentPlatform getContentPlatform() {
        if (this.mContentPlatform == null) {
            this.mContentPlatform = (IContentPlatform) ReflectionUtils.getNewInstance("com.baidu.ar.content.ContentCloud", new Class[]{Context.class}, new Object[]{this.mContext});
        }
        return this.mContentPlatform;
    }

    @Override // com.baidu.p120ar.IInternal
    public IGLRenderer getGLRenderer() {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer == null || !(aRRenderer instanceof IGLRenderer)) {
            return null;
        }
        return (IGLRenderer) aRRenderer;
    }

    @Override // com.baidu.p120ar.IInternal
    public IARRenderer getARRenderer() {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer == null || !(aRRenderer instanceof IARRenderer)) {
            return null;
        }
        return aRRenderer;
    }

    @Override // com.baidu.p120ar.IInternal
    public void setGLWebViewUseable(Context context, ViewGroup viewGroup) {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.setGLWebViewUseable(context, viewGroup);
        }
    }

    @Override // com.baidu.p120ar.IInternal
    public void setNativeWebViewUseable(Context context, ViewGroup viewGroup) {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.setNativeWebViewUseable(context, viewGroup);
        }
    }

    public static IAuthenticator getAsyncAuthenticator(String str, String str2, String str3) {
        return ARAuthAPI.getAsyncAuthenticator(str, str2, str3);
    }

    public static IAuthenticator getAuthenticator() {
        return ARAuthAPI.getAuthenticator();
    }

    public static IOfflineAuthenticator getOfflineAuthenticator() {
        return ARAuthAPI.getOfflineAuthenticator();
    }

    public static int getVersionCode() {
        return ARSDKInfo.getVersionCode();
    }

    public static String getVersionName() {
        return ARSDKInfo.getVersionName();
    }

    public static String getSoDownLoadDir(Context context) {
        return ControllerHelper.getSoDownLoadDir(context);
    }

    private void createManagers(Context context, DefaultParams defaultParams) {
        ARLog.m20421d("DuMixController", "createManagers start!!!");
        if (this.mCallbackHandler == null) {
            this.mCallbackHandler = new Handler(context.getMainLooper());
        }
        ARLog.setDebugEnable(defaultParams.isLogEnable());
        ARFileUtils.setPackageName(context.getPackageName());
        if (this.mOrientationManager == null) {
            this.mOrientationManager = new OrientationManager(context);
        }
        StatisticApi.init(context);
        if (this.mControllerHelper == null) {
            this.mControllerHelper = new ControllerHelper(context, defaultParams, this.mControlThread);
            this.mControllerHelper.initLibLoader(this);
        }
        if (this.mAbilitySchemeControl == null) {
            this.mAbilitySchemeControl = new AbilitySchemeControl(context);
            this.mAbilitySchemeControl.setDefaultScheme(defaultParams.getGradingConfig());
            this.mAbilitySchemeControl.setFetchUrl(defaultParams.getFetchUrl());
        }
        if (this.mARFilterManager == null) {
            this.mARFilterManager = new ARFilterManager(defaultParams);
            this.mAbilityManager = new AbilityManager(context, this.mControlThread.getLooper(), defaultParams, this.mAbilitySchemeControl, this.mARFilterManager);
        }
        if (this.mEngineMsgBridge == null) {
            this.mEngineMsgBridge = new EngineMsgBridge(context);
            this.mEngineMsgBridge.setUserPlayAudio(defaultParams.isUserPlayAudio());
        }
        if (this.mARRenderer == null) {
            if (defaultParams.isUseTextureIO()) {
                this.mARRenderer = new ARRenderer2(context, this.mControlThread.getLooper(), this.mEngineMsgBridge, defaultParams.getShareContext(), defaultParams.get3dShaderDBPath());
            } else {
                this.mARRenderer = new ARRenderer(context, this.mControlThread.getLooper(), this.mEngineMsgBridge, defaultParams.get3dShaderDBPath());
            }
            if (!TextUtils.isEmpty(defaultParams.getRenderPipeline())) {
                this.mARRenderer.setDefaultPipeLine(defaultParams.getRenderPipeline());
            }
        }
        ARLog.m20421d("DuMixController", "createManagers end!!!");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.DuMixController$ControlHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class ControlHandler extends Handler {
        public ControlHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 3000:
                    DuMixController.this.handleSetup();
                    return;
                case 3001:
                    DuMixController.this.handlePause();
                    return;
                case 3002:
                    DuMixController.this.handleResume();
                    return;
                case 3003:
                    DuMixController.this.handleRelease();
                    return;
                case 3004:
                    DuMixController.this.handleLoadCase((CaseModel) message.obj);
                    return;
                case 3005:
                    DuMixController.this.handleClearCase();
                    return;
                case 3006:
                    if (DuMixController.this.mARRenderer != null) {
                        DuMixController.this.mARRenderer.changeOutputSize(message.arg1, message.arg2);
                        return;
                    }
                    return;
                case 3007:
                    DuMixController.this.mDuMixOutput = (DuMixOutput) message.obj;
                    if (DuMixController.this.mARRenderer != null) {
                        DuMixController.this.mARRenderer.changeOutput(DuMixController.this.mDuMixOutput);
                        return;
                    }
                    return;
                case 3008:
                    if (DuMixController.this.mARRenderer != null) {
                        DuMixController.this.mARRenderer.pauseScene();
                        return;
                    }
                    return;
                case 3009:
                    if (DuMixController.this.mARRenderer != null) {
                        DuMixController.this.mARRenderer.resumeScene();
                        return;
                    }
                    return;
                case 3010:
                    if (DuMixController.this.mARRenderer != null) {
                        DuMixController.this.mARRenderer.changeInput(message.obj, message.arg1, message.arg2);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSetup() {
        ARLog.m20421d("DuMixController", "handleSetup() sState = " + sState);
        if (sState != 0 || this.mControllerHelper == null || this.mARRenderer == null || this.mARFilterManager == null || this.mAbilityManager == null || this.mAbilitySchemeControl == null) {
            return;
        }
        sState = 1;
        this.mCallbackProxy = getDuMixCallbackProxy();
        this.mControllerHelper.setParams(this.mARRenderer, this.mAbilityManager, this.mARFilterManager, this.mEngineMsgBridge);
        this.mControllerHelper.setup(this.mDuMixInput, this.mDuMixOutput, this.mCallbackProxy);
        StatisticApi.setPubParam("frame_data_from", this.mDuMixInput.isCameraInput() ? "camera" : "video");
        StatisticApi.onEventStart("event_sdk_start");
        this.mOrientationManager.addOrientationListener(this.mARRenderer);
        this.mOrientationManager.enable();
        this.mARFilterManager.setARRenderer(this.mARRenderer);
        AbilitySchemeControl abilitySchemeControl = this.mAbilitySchemeControl;
        if (abilitySchemeControl != null) {
            JSONObject scheme = abilitySchemeControl.getScheme();
            if (scheme != null) {
                this.mARRenderer.setAbilityScheme(scheme);
            } else {
                this.mARRenderer.setLocalDeviceGrade(this.mAbilitySchemeControl.getDeviceLevelByDefaultCpuList());
            }
        }
        this.mAbilityManager.setup(this.mEngineMsgBridge, this.mARRenderer);
        this.mARRenderer.setup(this.mDuMixInput, this.mDuMixOutput);
        this.mControllerHelper.fetchAbilityScheme(this.mAbilitySchemeControl);
        this.mControllerHelper.doAuth();
    }

    private DuMixCallback getDuMixCallbackProxy() {
        return new DuMixCallback() { // from class: com.baidu.ar.DuMixController.1
            @Override // com.baidu.p120ar.DuMixCallback
            public void onSetup(final boolean z, final DuMixInput duMixInput, final DuMixOutput duMixOutput) {
                ARLog.m20421d("DuMixController", "getDuMixCallbackProxy onSetup sState = " + DuMixController.sState);
                if (z) {
                    int unused = DuMixController.sState = 2;
                } else {
                    int unused2 = DuMixController.sState = 0;
                }
                if (DuMixController.this.mControllerHelper != null) {
                    DuMixController.this.mControllerHelper.createCachedPixelReaders();
                }
                if (DuMixController.this.mCallbackHandler != null) {
                    DuMixController.this.mCallbackHandler.post(new Runnable() { // from class: com.baidu.ar.DuMixController.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (DuMixController.this.mDuMixCallback != null) {
                                ARLog.m20421d("DuMixController", "mDuMixCallback.onSetup()");
                                DuMixController.this.mDuMixCallback.onSetup(z, duMixInput, duMixOutput);
                            }
                        }
                    });
                }
                synchronized (DuMixController.sLock) {
                    try {
                        DuMixController.sLock.notifyAll();
                    } catch (Exception unused3) {
                        ARLog.m20421d("DuMixController", "onSetup normal!!!");
                    }
                }
            }

            @Override // com.baidu.p120ar.DuMixCallback
            public void onCaseCreate(final boolean z, final String str, final String str2) {
                if (DuMixController.this.mCallbackHandler != null) {
                    DuMixController.this.mCallbackHandler.post(new Runnable() { // from class: com.baidu.ar.DuMixController.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (DuMixController.this.mDuMixCallback != null) {
                                DuMixController.this.mDuMixCallback.onCaseCreate(z, str, str2);
                            }
                        }
                    });
                }
            }

            @Override // com.baidu.p120ar.DuMixCallback
            public void onCaseDestroy() {
                if (DuMixController.this.mCallbackHandler != null) {
                    DuMixController.this.mCallbackHandler.post(new Runnable() { // from class: com.baidu.ar.DuMixController.1.3
                        @Override // java.lang.Runnable
                        public void run() {
                            if (DuMixController.this.mDuMixCallback != null) {
                                DuMixController.this.mDuMixCallback.onCaseDestroy();
                            }
                        }
                    });
                }
            }

            @Override // com.baidu.p120ar.DuMixCallback
            public void onRelease() {
                ARLog.m20421d("DuMixController", "getDuMixCallbackProxy onRelease sState = " + DuMixController.sState);
                int unused = DuMixController.sState = 0;
                synchronized (DuMixController.sLock) {
                    try {
                        DuMixController.sLock.notifyAll();
                    } catch (Exception unused2) {
                        ARLog.m20421d("DuMixController", "onRelease normal!!!");
                    }
                }
                if (DuMixController.this.mCallbackHandler != null) {
                    DuMixController.this.mCallbackHandler.post(new Runnable() { // from class: com.baidu.ar.DuMixController.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            if (DuMixController.this.mDuMixCallback != null) {
                                DuMixController.this.mDuMixCallback.onRelease();
                                DuMixController.this.mDuMixCallback = null;
                            }
                        }
                    });
                    DuMixController.this.mCallbackHandler = null;
                }
            }

            @Override // com.baidu.p120ar.DuMixCallback
            public void onError(final DuMixErrorType duMixErrorType, final String str, final String str2) {
                if (DuMixController.this.mCallbackHandler != null) {
                    DuMixController.this.mCallbackHandler.post(new Runnable() { // from class: com.baidu.ar.DuMixController.1.5
                        @Override // java.lang.Runnable
                        public void run() {
                            if (DuMixController.this.mDuMixCallback != null) {
                                DuMixController.this.mDuMixCallback.onError(duMixErrorType, str, str2);
                            }
                        }
                    });
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePause() {
        if (sState != 2) {
            return;
        }
        AbilityManager abilityManager = this.mAbilityManager;
        if (abilityManager != null) {
            abilityManager.pause();
        }
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.pause();
        }
        OrientationManager orientationManager = this.mOrientationManager;
        if (orientationManager != null) {
            orientationManager.disable();
        }
        StatisticApi.pause();
        ControllerHelper controllerHelper = this.mControllerHelper;
        if (controllerHelper != null) {
            controllerHelper.sendSystemMsg2Lua("pause");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResume() {
        if (sState != 2) {
            return;
        }
        ControllerHelper controllerHelper = this.mControllerHelper;
        if (controllerHelper != null) {
            controllerHelper.sendSystemMsg2Lua("resume");
        }
        StatisticApi.resume();
        OrientationManager orientationManager = this.mOrientationManager;
        if (orientationManager != null) {
            orientationManager.enable();
        }
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.resume();
        }
        AbilityManager abilityManager = this.mAbilityManager;
        if (abilityManager != null) {
            abilityManager.resume();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRelease() {
        ARLog.m20421d("DuMixController", "handleRelease() sState = " + sState);
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mControlHandler = null;
        }
        this.mContentPlatform = null;
        ControllerHelper controllerHelper = this.mControllerHelper;
        if (controllerHelper != null) {
            controllerHelper.removeAllMessages();
        }
        LuaMsgHelper luaMsgHelper = this.mLuaMsgHelper;
        if (luaMsgHelper != null) {
            luaMsgHelper.release();
            this.mLuaMsgHelper = null;
        }
        ARFilterManager aRFilterManager = this.mARFilterManager;
        if (aRFilterManager != null) {
            aRFilterManager.clearAllFilter();
            this.mARFilterManager.release();
            this.mARFilterManager = null;
        }
        AbilityManager abilityManager = this.mAbilityManager;
        if (abilityManager != null) {
            abilityManager.release();
            this.mAbilityManager = null;
        }
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.release();
            this.mARRenderer = null;
        }
        OrientationManager orientationManager = this.mOrientationManager;
        if (orientationManager != null) {
            orientationManager.destroy();
            this.mOrientationManager = null;
        }
        AbilitySchemeControl abilitySchemeControl = this.mAbilitySchemeControl;
        if (abilitySchemeControl != null) {
            abilitySchemeControl.release();
            this.mAbilitySchemeControl = null;
        }
        LibLoader.release();
        ARAuth.release();
        StatisticApi.onEventEnd("event_case_end");
        StatisticApi.onEventEnd("event_sdk_end");
        StatisticApi.release();
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge != null) {
            engineMsgBridge.destroy();
            this.mEngineMsgBridge = null;
        }
        ControllerHelper controllerHelper2 = this.mControllerHelper;
        if (controllerHelper2 != null) {
            controllerHelper2.release();
            this.mControllerHelper = null;
        }
        this.mDuMixInput = null;
        this.mDuMixOutput = null;
        this.mDefaultParams = null;
        this.mCallbackProxy = null;
        this.mContext = null;
        ARLog.m20421d("DuMixController", "handleRelease() end");
        HandlerThread handlerThread = this.mControlThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mControlThread = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLoadCase(CaseModel caseModel) {
        if (this.mControllerHelper == null || sState != 2) {
            ARLog.m20419e("DuMixController", "handleLoadCase DuMix has not setup!!!!!!");
            DuMixCallback duMixCallback = this.mCallbackProxy;
            if (duMixCallback != null) {
                duMixCallback.onCaseCreate(false, caseModel.mCasePath, caseModel.mCaseId);
                return;
            }
            return;
        }
        this.mControllerHelper.loadCase(caseModel.mCaseType, caseModel.mCasePath, caseModel.mCaseId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleClearCase() {
        if (this.mControllerHelper == null || sState != 2) {
            ARLog.m20419e("DuMixController", "handleClearCase DuMix has not setup!!!!!!");
        } else {
            this.mControllerHelper.clearCase();
        }
    }

    public void setARRenderFpsCallback(ARRenderFpsCallback aRRenderFpsCallback) {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.setARRenderFpsCallback(aRRenderFpsCallback);
        }
    }
}
