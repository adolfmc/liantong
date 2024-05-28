package com.baidu.p120ar;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.p120ar.abilityscheme.AbilitySchemeControl;
import com.baidu.p120ar.arplay.core.engine.ARPScriptEnvironment;
import com.baidu.p120ar.arplay.core.pixel.PixelReadListener;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arrender.ARRenderer;
import com.baidu.p120ar.auth.ARAuth;
import com.baidu.p120ar.auth.IAuthCallback;
import com.baidu.p120ar.bean.ARConfig;
import com.baidu.p120ar.bean.CaseModel;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.filter.ARFilterManager;
import com.baidu.p120ar.libloader.ILibLoader;
import com.baidu.p120ar.libloader.LibLoader;
import com.baidu.p120ar.libloader.LocalWithPathLibLoader;
import com.baidu.p120ar.lua.EngineMsgBridge;
import com.baidu.p120ar.lua.EngineMsgListener;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.ReflectionUtils;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ControllerHelper */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ControllerHelper {
    private static final int SWITCH_CASE_INTERVAL = 100;
    private static final String TAG = "ControllerHelper";
    private List<Integer> mAREngienMessages;
    private EngineMsgListener mAREngineListener;
    private ARFilterManager mARFilterManager;
    private ARRenderer mARRenderer;
    private AbilityManager mAbilityManager;
    private CaseHandler mCaseHandler;
    private CaseModel mCaseModel2Load;
    private Context mContext;
    private String mCurrentCaseId;
    private String mCurrentCasePath;
    private DefaultParams mDefaultParams;
    protected DuMixCallback mDuMixCallback;
    private DuMixInput mDuMixInput;
    private DuMixOutput mDuMixOutput;
    private EngineMsgBridge mEngineMsgBridge;
    private CaseModel mLastCaseModel;
    private boolean mFilterSetup = false;
    private boolean mEngineSetup = false;
    private boolean mSetupCallback = false;
    private volatile boolean mIsAuthRejected = false;
    private boolean mCaseLoaded = false;
    private boolean mLoadCaseEnable = true;
    private boolean mCaseSwitched = false;
    private boolean mCaseSwitching = false;
    private boolean mCaseCleared = false;
    private boolean mIsFirstLoadCase = true;
    private ConcurrentHashMap<PixelReadParams, PixelReadListener> mCachePixelReaders = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    public void showAuthTip() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ControllerHelper(Context context, DefaultParams defaultParams, HandlerThread handlerThread) {
        this.mContext = context;
        this.mDefaultParams = defaultParams;
        this.mCaseHandler = new CaseHandler(handlerThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParams(ARRenderer aRRenderer, AbilityManager abilityManager, ARFilterManager aRFilterManager, EngineMsgBridge engineMsgBridge) {
        this.mARRenderer = aRRenderer;
        this.mAbilityManager = abilityManager;
        this.mARFilterManager = aRFilterManager;
        this.mEngineMsgBridge = engineMsgBridge;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setup(DuMixInput duMixInput, DuMixOutput duMixOutput, DuMixCallback duMixCallback) {
        this.mDuMixInput = duMixInput;
        this.mDuMixOutput = duMixOutput;
        this.mDuMixCallback = duMixCallback;
        setAREngineEventListener();
        loadAuthInfo();
        loadAllSO();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loadCase(ARType aRType, String str, String str2) {
        if (this.mIsAuthRejected) {
            ARLog.m20419e("ControllerHelper", "auth rejected");
            return;
        }
        CaseModel caseModel = new CaseModel(aRType, str, str2);
        CaseModel caseModel2 = this.mLastCaseModel;
        if (caseModel2 != null && caseModel.equals(caseModel2)) {
            ARLog.m20421d("ControllerHelper", "loadCase() case has loaded!!!");
            DuMixCallback duMixCallback = this.mDuMixCallback;
            if (duMixCallback != null) {
                duMixCallback.onCaseCreate(true, str, str2);
                return;
            }
            return;
        }
        this.mLastCaseModel = caseModel;
        LibLoader.prepareCaseRes(aRType, str, str2, new ILibLoader.CaseReadyListener() { // from class: com.baidu.ar.ControllerHelper.1
            @Override // com.baidu.p120ar.libloader.ILibLoader.CaseReadyListener
            public void onReady(ARType aRType2, String str3, String str4) {
                ControllerHelper.this.executeLoadCase(aRType2, str3, str4);
            }

            @Override // com.baidu.p120ar.libloader.ILibLoader.CaseReadyListener
            public void onError(DuMixErrorType duMixErrorType, String str3) {
                if (ControllerHelper.this.mDuMixCallback != null) {
                    ControllerHelper.this.mDuMixCallback.onError(duMixErrorType, str3, "");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearCase() {
        ARLog.m20421d("ControllerHelper", "clearCase mLoadCaseEnable = " + this.mLoadCaseEnable + " && mCaseLoaded = " + this.mCaseLoaded);
        if (this.mLoadCaseEnable && this.mCaseLoaded) {
            this.mLoadCaseEnable = false;
            CaseHandler caseHandler = this.mCaseHandler;
            if (caseHandler != null) {
                caseHandler.sendMessage(caseHandler.obtainMessage(4002));
            }
        } else {
            this.mCaseModel2Load = null;
            this.mCaseCleared = true;
        }
        this.mLastCaseModel = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeAllMessages() {
        CaseHandler caseHandler = this.mCaseHandler;
        if (caseHandler != null) {
            caseHandler.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release() {
        ConcurrentHashMap<PixelReadParams, PixelReadListener> concurrentHashMap = this.mCachePixelReaders;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
            this.mCachePixelReaders = null;
        }
        this.mARRenderer = null;
        this.mAbilityManager = null;
        this.mARFilterManager = null;
        this.mDuMixInput = null;
        this.mDuMixOutput = null;
        this.mContext = null;
        this.mDefaultParams = null;
        this.mAREngienMessages = null;
        this.mAREngineListener = null;
        this.mEngineMsgBridge = null;
        this.mCaseHandler = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initLibLoader(DuMixController duMixController) {
        String soDownLoadDir = getSoDownLoadDir(this.mContext);
        Object newInstance = ReflectionUtils.getNewInstance("com.baidu.ar.remoteres.RemoteResLoader", new Class[]{DuMixController.class, String.class}, new Object[]{duMixController, soDownLoadDir});
        if (newInstance != null) {
            LibLoader.registerLibLoader((ILibLoader) newInstance);
        } else if (!TextUtils.isEmpty(soDownLoadDir) && !LibLoader.isRegistered()) {
            LocalWithPathLibLoader localWithPathLibLoader = new LocalWithPathLibLoader(soDownLoadDir);
            localWithPathLibLoader.setErrorListener(new LocalWithPathLibLoader.IErrorListener() { // from class: com.baidu.ar.ControllerHelper.2
                @Override // com.baidu.p120ar.libloader.LocalWithPathLibLoader.IErrorListener
                public void onError(String str, String str2) {
                    if (ControllerHelper.this.mDuMixCallback != null) {
                        ControllerHelper.this.mDuMixCallback.onError(DuMixErrorType.LibraryError, str2, str);
                    }
                }
            });
            LibLoader.registerLibLoader(localWithPathLibLoader);
        } else {
            LibLoader.useDefault();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getSoDownLoadDir(Context context) {
        if (context == null) {
            ARLog.m20419e("ControllerHelper", "get so download dir error");
            return null;
        }
        File file = new File(context.getFilesDir(), "arlibs");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    private void setAREngineEventListener() {
        this.mAREngienMessages = Arrays.asList(12, 50, 6, 7, 8, 9);
        this.mAREngineListener = new EngineMsgListener() { // from class: com.baidu.ar.ControllerHelper.3
            @Override // com.baidu.p120ar.lua.EngineMsgListener
            public List<Integer> getMsgTypesListened() {
                return ControllerHelper.this.mAREngienMessages;
            }

            @Override // com.baidu.p120ar.lua.EngineMsgListener
            public void onEngineMessage(int i, int i2, HashMap<String, Object> hashMap) {
                ARLog.m20421d("ControllerHelper", "onEngineMessage msgType = " + i + " && msgId = " + i2);
                if (i == 12) {
                    if (ControllerHelper.this.mCaseHandler != null) {
                        ControllerHelper.this.mCaseHandler.sendMessage(ControllerHelper.this.mCaseHandler.obtainMessage(4003));
                    }
                } else if (i != 50) {
                    switch (i) {
                        case 6:
                            if (ControllerHelper.this.mCaseHandler != null) {
                                ControllerHelper.this.mCaseHandler.sendMessage(ControllerHelper.this.mCaseHandler.obtainMessage(4005));
                                return;
                            }
                            return;
                        case 7:
                            if (ControllerHelper.this.mCaseHandler != null) {
                                ControllerHelper.this.mCaseHandler.sendMessage(ControllerHelper.this.mCaseHandler.obtainMessage(4006));
                                return;
                            }
                            return;
                        case 8:
                            ControllerHelper.this.mARRenderer.updateDeviceOrientation();
                            if (ControllerHelper.this.mCaseHandler != null) {
                                ControllerHelper.this.mCaseHandler.sendMessage(ControllerHelper.this.mCaseHandler.obtainMessage(4007));
                                return;
                            }
                            return;
                        case 9:
                            if (ControllerHelper.this.mCaseHandler != null) {
                                ControllerHelper.this.mCaseHandler.sendMessage(ControllerHelper.this.mCaseHandler.obtainMessage(4008));
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                } else if (ControllerHelper.this.mCaseHandler != null) {
                    ControllerHelper.this.mCaseHandler.sendMessage(ControllerHelper.this.mCaseHandler.obtainMessage(4004, hashMap));
                }
            }
        };
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge != null) {
            engineMsgBridge.addEngineMsgListener(this.mAREngineListener);
        }
    }

    private void loadAllSO() {
        LibLoader.load(this.mContext, new ILibLoader.ILoadListener() { // from class: com.baidu.ar.ControllerHelper.4
            @Override // com.baidu.p120ar.libloader.ILibLoader.ILoadListener
            public void onSuccess() {
            }

            @Override // com.baidu.p120ar.libloader.ILibLoader.ILoadListener
            public void onError(DuMixErrorType duMixErrorType, Exception exc) {
                if (ControllerHelper.this.mDuMixCallback != null) {
                    ControllerHelper.this.mDuMixCallback.onSetup(false, ControllerHelper.this.mDuMixInput, ControllerHelper.this.mDuMixOutput);
                    if (exc != null) {
                        ControllerHelper.this.mDuMixCallback.onError(duMixErrorType, exc.getMessage(), "");
                    }
                }
            }
        });
    }

    private void loadAuthInfo() {
        ARAuth.loadAuthInfo(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fetchAbilityScheme(final AbilitySchemeControl abilitySchemeControl) {
        if (abilitySchemeControl != null) {
            abilitySchemeControl.setUpdateFailCallback(new ICallbackWith<String>() { // from class: com.baidu.ar.ControllerHelper.5
                @Override // com.baidu.p120ar.callback.ICallbackWith
                public void run(String str) {
                    if (ControllerHelper.this.mDuMixCallback != null) {
                        ControllerHelper.this.mDuMixCallback.onError(DuMixErrorType.AbilitySchemeFetchFail, str, null);
                    }
                }
            });
            abilitySchemeControl.startUpdate(new ICallbackWith<JSONObject>() { // from class: com.baidu.ar.ControllerHelper.6
                @Override // com.baidu.p120ar.callback.ICallbackWith
                public void run(JSONObject jSONObject) {
                    if (ControllerHelper.this.mARRenderer != null) {
                        if (jSONObject != null) {
                            ControllerHelper.this.mARRenderer.setAbilityScheme(jSONObject);
                        } else if (abilitySchemeControl != null) {
                            ControllerHelper.this.mARRenderer.setLocalDeviceGrade(abilitySchemeControl.getDeviceLevelByDefaultCpuList());
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void doAuth() {
        ARAuth.doAuth(this.mContext, new IAuthCallback() { // from class: com.baidu.ar.ControllerHelper.7
            private boolean mHasFailTip = false;

            @Override // com.baidu.p120ar.auth.IAuthCallback
            public void onSuccess() {
                ARLog.m20421d("ControllerHelper", "auth success");
            }

            @Override // com.baidu.p120ar.auth.IAuthCallback
            public void onError(String str, int i) {
                ARLog.m20419e("ControllerHelper", String.format("auth fail feature: %d msg: %s", Integer.valueOf(i), str));
                if (i == 0) {
                    ControllerHelper.this.mIsAuthRejected = true;
                    if (ControllerHelper.this.mCaseHandler != null) {
                        ControllerHelper.this.mCaseHandler.sendMessage(ControllerHelper.this.mCaseHandler.obtainMessage(4002));
                    }
                }
                if (this.mHasFailTip || !ARAuth.isShowAuthTip()) {
                    return;
                }
                this.mHasFailTip = true;
                ControllerHelper.this.showAuthTip();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendRecordMsg2Lua(String str) {
        setEventMessage2Lua("recorder_video", "msg", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendSystemMsg2Lua(String str) {
        setEventMessage2Lua("dumix_system_message", "system_message", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cachePixelReader(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener) {
        ConcurrentHashMap<PixelReadParams, PixelReadListener> concurrentHashMap = this.mCachePixelReaders;
        if (concurrentHashMap != null) {
            concurrentHashMap.put(pixelReadParams, pixelReadListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void createCachedPixelReaders() {
        ConcurrentHashMap<PixelReadParams, PixelReadListener> concurrentHashMap = this.mCachePixelReaders;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        for (Map.Entry<PixelReadParams, PixelReadListener> entry : this.mCachePixelReaders.entrySet()) {
            this.mARRenderer.createPixelReader(entry.getKey(), entry.getValue());
        }
        this.mCachePixelReaders.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeLoadCase(ARType aRType, String str, String str2) {
        ARLog.m20421d("ControllerHelper", "executeLoadCase arType = " + aRType + " && casePath = " + str + "&& mLoadCaseEnable = " + this.mLoadCaseEnable + " && mCaseSwitched = " + this.mCaseSwitched);
        if (!ARType.ON_DEVICE_IR.equals(aRType) && !ARType.CLOUD_IR.equals(aRType) && TextUtils.isEmpty(str)) {
            ARLog.m20419e("ControllerHelper", "casePath is empty!!!");
            DuMixCallback duMixCallback = this.mDuMixCallback;
            if (duMixCallback != null) {
                duMixCallback.onCaseCreate(false, str, str2);
            }
        } else if (this.mIsAuthRejected) {
            ARLog.m20419e("ControllerHelper", "auth rejected");
        } else {
            CaseModel caseModel = new CaseModel(aRType, str + File.separator + "ar", str2);
            if (this.mLoadCaseEnable) {
                this.mLoadCaseEnable = false;
                ARLog.m20421d("ControllerHelper", "executeLoadCase mCaseLoaded = " + this.mCaseLoaded);
                if (!this.mCaseLoaded) {
                    CaseHandler caseHandler = this.mCaseHandler;
                    caseHandler.sendMessage(caseHandler.obtainMessage(4001, caseModel));
                } else {
                    this.mCaseModel2Load = caseModel;
                    this.mCaseSwitched = true;
                    CaseHandler caseHandler2 = this.mCaseHandler;
                    caseHandler2.sendMessage(caseHandler2.obtainMessage(4002));
                }
            } else {
                this.mCaseModel2Load = caseModel;
                this.mCaseSwitched = true;
            }
            this.mCaseCleared = false;
        }
    }

    private void resetAbility() {
        if (this.mAbilityManager == null) {
            return;
        }
        if (this.mCaseSwitched && this.mCaseModel2Load != null && isARTypeRight()) {
            this.mAbilityManager.destroyAllARAbility();
        } else {
            this.mAbilityManager.restoreDefaultARAbility();
        }
    }

    private boolean isARTypeRight() {
        return (this.mCaseModel2Load.mCaseType == null || this.mCaseModel2Load.mCaseType == ARType.FACE) ? false : true;
    }

    private void setEventMessage2Lua(String str, String str2, String str3) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("event_name", str);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(str2, str3);
        hashMap.put("event_data", hashMap2);
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge != null) {
            engineMsgBridge.sendMsg2Engine(1902, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.ControllerHelper$CaseHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class CaseHandler extends Handler {
        public static final int MSG_CREATE_CASE = 4001;
        public static final int MSG_DESTROY_CASE = 4002;
        public static final int MSG_ON_CASE_CREATE = 4007;
        public static final int MSG_ON_CASE_DESTROY = 4008;
        public static final int MSG_ON_ENGINE_CREATE = 4005;
        public static final int MSG_ON_ENGINE_DESTROY = 4006;
        public static final int MSG_ON_FILTER_CHANGE = 4004;
        public static final int MSG_ON_FILTER_CREATE = 4003;

        public CaseHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 4001:
                    ARLog.m20421d("ControllerHelper", "CaseHandler MSG_CREATE_CASE");
                    CaseModel caseModel = (CaseModel) message.obj;
                    ControllerHelper.this.handleCreateCase(caseModel.mCaseType, caseModel.mCasePath, caseModel.mCaseId);
                    return;
                case 4002:
                    ARLog.m20421d("ControllerHelper", "CaseHandler MSG_DESTROY_CASE");
                    ControllerHelper.this.handleDestroyCase();
                    return;
                case 4003:
                    ARLog.m20421d("ControllerHelper", "CaseHandler MSG_ON_FILTER_CREATE");
                    ControllerHelper.this.handleOnFilterCreate();
                    return;
                case 4004:
                    ARLog.m20421d("ControllerHelper", "CaseHandler MSG_ON_FILTER_CHANGE");
                    ControllerHelper.this.handleOnFilterChange((List) ((HashMap) message.obj).get("filter_name_list"));
                    return;
                case 4005:
                    ARLog.m20421d("ControllerHelper", "CaseHandler MSG_ON_ENGINE_CREATE");
                    ControllerHelper.this.handleOnEngineCreate();
                    return;
                case 4006:
                    ARLog.m20421d("ControllerHelper", "CaseHandler MSG_ON_ENGINE_DESTROY");
                    ControllerHelper.this.handleOnEngineDestroy();
                    return;
                case 4007:
                    ARLog.m20421d("ControllerHelper", "CaseHandler MSG_ON_CASE_CREATE");
                    ControllerHelper.this.handleOnCaseCreate();
                    return;
                case 4008:
                    ARLog.m20421d("ControllerHelper", "CaseHandler MSG_ON_CASE_DESTROY");
                    ControllerHelper.this.handleOnCaseDestroy();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCreateCase(ARType aRType, String str, String str2) {
        AbilityManager abilityManager;
        if (this.mIsAuthRejected) {
            ARLog.m20419e("ControllerHelper", "handleCreateCase ignored; auth rejected");
            return;
        }
        if (aRType != null) {
            ARConfig.setARType(aRType.getTypeValue());
            saveInteractionInfoInEnvironment(aRType);
        }
        ARConfig.setARKey(str2);
        boolean z = false;
        if (this.mIsFirstLoadCase) {
            this.mIsFirstLoadCase = false;
            StatisticApi.onEvent("event_case_first");
        }
        StatisticApi.onEventStart("event_case_start");
        if (this.mARRenderer != null && !TextUtils.isEmpty(str)) {
            this.mCurrentCasePath = str.substring(0, str.lastIndexOf(File.separator + "ar"));
            this.mCurrentCaseId = str2;
            ARFilterManager aRFilterManager = this.mARFilterManager;
            if (aRFilterManager != null) {
                aRFilterManager.setCasePath(this.mCurrentCasePath);
            }
            ARRenderer aRRenderer = this.mARRenderer;
            if (!this.mDefaultParams.isUseInputSizeInEngine() && aRType != ARType.FACE && aRType != ARType.VPAS) {
                z = true;
            }
            aRRenderer.touchEngineToOutputSize(z);
            this.mARRenderer.createCase(str);
        }
        if (aRType == null || (abilityManager = this.mAbilityManager) == null) {
            return;
        }
        abilityManager.createARAbilityByType(aRType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDestroyCase() {
        resetAbility();
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.destroyCase();
        }
        StatisticApi.onEventEnd("event_case_end");
        ARFilterManager aRFilterManager = this.mARFilterManager;
        if (aRFilterManager != null) {
            aRFilterManager.clearCaseLutFilter();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnFilterCreate() {
        this.mFilterSetup = true;
        ARFilterManager aRFilterManager = this.mARFilterManager;
        if (aRFilterManager != null && !this.mCaseSwitching) {
            aRFilterManager.onPipelineCreate();
        }
        setupCallback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnFilterChange(List<String> list) {
        ARFilterManager aRFilterManager = this.mARFilterManager;
        if (aRFilterManager != null) {
            aRFilterManager.onPipelineChanged(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnEngineCreate() {
        AbilityManager abilityManager = this.mAbilityManager;
        if (abilityManager != null) {
            abilityManager.startDefaultAbilitys();
        }
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer != null) {
            aRRenderer.setEngineCreate(true);
        }
        this.mEngineSetup = true;
        setupCallback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnEngineDestroy() {
        DuMixCallback duMixCallback = this.mDuMixCallback;
        if (duMixCallback != null) {
            duMixCallback.onRelease();
            this.mDuMixCallback = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnCaseCreate() {
        CaseHandler caseHandler;
        this.mCaseLoaded = true;
        this.mCaseSwitching = false;
        AbilityManager abilityManager = this.mAbilityManager;
        if (abilityManager != null) {
            abilityManager.onCaseCreate(this.mCurrentCasePath + File.separator + "ar");
        }
        if ((this.mCaseSwitched || this.mCaseCleared) && (caseHandler = this.mCaseHandler) != null) {
            caseHandler.removeMessages(4002);
            CaseHandler caseHandler2 = this.mCaseHandler;
            caseHandler2.sendMessage(caseHandler2.obtainMessage(4002));
        } else {
            this.mLoadCaseEnable = true;
        }
        DuMixCallback duMixCallback = this.mDuMixCallback;
        if (duMixCallback != null) {
            duMixCallback.onCaseCreate(true, this.mCurrentCasePath, this.mCurrentCaseId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnCaseDestroy() {
        CaseModel caseModel;
        this.mCaseLoaded = false;
        resetAbility();
        AbilityManager abilityManager = this.mAbilityManager;
        if (abilityManager != null) {
            abilityManager.onCaseDestroy();
        }
        if (this.mCaseSwitched && (caseModel = this.mCaseModel2Load) != null && this.mCaseHandler != null) {
            this.mCaseSwitching = true;
            CaseModel caseModel2 = new CaseModel(caseModel.mCaseType, this.mCaseModel2Load.mCasePath, this.mCaseModel2Load.mCaseId);
            this.mCaseHandler.removeMessages(4001);
            CaseHandler caseHandler = this.mCaseHandler;
            caseHandler.sendMessageDelayed(caseHandler.obtainMessage(4001, caseModel2), 100L);
        } else {
            this.mLoadCaseEnable = true;
            this.mCurrentCasePath = null;
            this.mCurrentCaseId = null;
            ARFilterManager aRFilterManager = this.mARFilterManager;
            if (aRFilterManager != null) {
                aRFilterManager.setCasePath(this.mCurrentCasePath);
            }
        }
        this.mCaseModel2Load = null;
        this.mCaseSwitched = false;
        this.mCaseCleared = false;
        DuMixCallback duMixCallback = this.mDuMixCallback;
        if (duMixCallback != null) {
            duMixCallback.onCaseDestroy();
        }
    }

    private void setupCallback() {
        DuMixCallback duMixCallback;
        if (!this.mFilterSetup || !this.mEngineSetup || this.mSetupCallback || (duMixCallback = this.mDuMixCallback) == null) {
            return;
        }
        this.mSetupCallback = true;
        duMixCallback.onSetup(true, this.mDuMixInput, this.mDuMixOutput);
    }

    private void saveInteractionInfoInEnvironment(ARType aRType) {
        switch (aRType) {
            case IMU:
                setIMUSharedEnvKV(1);
                return;
            case TRACK_2D:
            case CLOUD_IR:
            case ON_DEVICE_IR:
                setIMUSharedEnvKV(0);
                return;
            case VO:
                setVOSharedEnvKV();
                return;
            default:
                return;
        }
    }

    private void setIMUSharedEnvKV(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("continuous_mapping", getGestureMap("interaction_space_move"));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("limit_radius_invariant", Integer.valueOf(i));
        hashMap.put("space_move_config", hashMap2);
        ARPScriptEnvironment.getInstance().setSharedEnvironmentKV("interactioninfo", hashMap);
    }

    private void setVOSharedEnvKV() {
        HashMap hashMap = new HashMap();
        hashMap.put("continuous_mapping", getGestureMap("interaction_plane_move"));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("limit_radius", 1);
        hashMap2.put("radius_min", Float.valueOf(0.0f));
        hashMap2.put("radius_max", Float.valueOf(3000.0f));
        hashMap2.put("limit_step_length", 1);
        hashMap2.put("step_length", Float.valueOf(80.0f));
        hashMap2.put("limit_far_frustum", 1);
        hashMap2.put("move_leave_callback", 1);
        hashMap.put("plane_move_config", hashMap2);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("limit_world_axis", 1);
        hashMap3.put("world_axis", "y");
        hashMap.put("rotate_config", hashMap3);
        HashMap hashMap4 = new HashMap();
        hashMap4.put("limit_distance_factor", 1);
        hashMap.put("scale_config", hashMap4);
        ARPScriptEnvironment.getInstance().setSharedEnvironmentKV("interactioninfo", hashMap);
    }

    private HashMap<String, Object> getGestureMap(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("gesture_scroll", str);
        hashMap.put("gesture_two_finger_scroll", "interaction_rotate");
        hashMap.put("gesture_two_finger_pinch", "interaction_scale_down");
        hashMap.put("gesture_two_finger_unpinch", "interaction_scale_up");
        return hashMap;
    }
}
