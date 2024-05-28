package com.baidu.p120ar;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.ability.AbilityAuth;
import com.baidu.p120ar.ability.AbilityConstants;
import com.baidu.p120ar.abilityscheme.AbilitySchemeControl;
import com.baidu.p120ar.arplay.util.MsgParamsUtil;
import com.baidu.p120ar.arrender.ARRenderer;
import com.baidu.p120ar.arrender.CameraSwitchListener;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.DetectorManager;
import com.baidu.p120ar.filter.ARFilterManager;
import com.baidu.p120ar.filter.FilterNode;
import com.baidu.p120ar.filter.FilterParam;
import com.baidu.p120ar.imu.IImu;
import com.baidu.p120ar.libloader.ILibLoader;
import com.baidu.p120ar.libloader.LibLoader;
import com.baidu.p120ar.lua.EngineMsgBridge;
import com.baidu.p120ar.lua.EngineMsgListener;
import com.baidu.p120ar.lua.LuaMsgListener;
import com.baidu.p120ar.mdl.MdlConfigParams;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.ReflectionUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.AbilityManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AbilityManager {
    private static final String TAG = "AbilityManager";
    private ARFilterManager mARFilterManager;
    private ARProxyManager mARProxyManager;
    private ARRenderer mARRenderer;
    private AbilityHandler mAbilityHandler;
    private AbilitySchemeControl mAbilitySchemeControl;
    private CameraSwitchListener mCameraSwitchListener;
    private Context mContext;
    private DefaultParams mDefaultParams;
    private DetectorManager mDetectorManager;
    private EngineMsgBridge mEngineMsgBridge;
    private LuaMsgListener mEventAbilityListener;
    private List<String> mEventAbilityMessages;
    private IImu mIMUController;
    private LuaMsgListener mIdAbilityListener;
    private List<String> mIdAbilityMessages;
    private Looper mLooper;
    private MdlConfigParams mMdlConfigParams;
    private AbstractAR.RequireDetectorListener mRequireDetectorListener;
    private EngineMsgListener mTypeAbilityListener;
    private List<Integer> mTypeAbilityMessages;
    private ConcurrentHashMap<String, String> mSupportedARClasses = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> mSupportedDetectors = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, AbstractAR> mActiveARAbilities = new ConcurrentHashMap<>();
    private List<String> mDefaultAbilities = new ArrayList();
    private final List<String> mEnabledAbilities = new CopyOnWriteArrayList();
    private List<String> mUserOpenAbilities = new ArrayList();
    private boolean mIsFirstStartDefault = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbilityManager(Context context, Looper looper, DefaultParams defaultParams, AbilitySchemeControl abilitySchemeControl, ARFilterManager aRFilterManager) {
        this.mContext = context;
        this.mLooper = looper;
        this.mAbilityHandler = new AbilityHandler(looper);
        this.mDefaultParams = defaultParams;
        setMdlModelPath(this.mDefaultParams.getMdlAlgoModelPath());
        this.mARProxyManager = new ARProxyManager();
        this.mAbilitySchemeControl = abilitySchemeControl;
        this.mARFilterManager = aRFilterManager;
        initSupportedARClasses();
        this.mEnabledAbilities.add("ability_common_filter");
        createDetectorListener();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setup(EngineMsgBridge engineMsgBridge, ARRenderer aRRenderer) {
        this.mEngineMsgBridge = engineMsgBridge;
        this.mARRenderer = aRRenderer;
        this.mARRenderer.setEnabledAbilities(this.mEnabledAbilities);
        createCameraSwitchListener();
        this.mDetectorManager = new DetectorManager(aRRenderer, this.mLooper);
        this.mIMUController = ARPluginBuilder.buildIMUController();
        try {
            if (this.mIMUController != null) {
                this.mIMUController.setContext(this.mContext);
            }
            setARAbilityEventListener();
        } catch (Exception e) {
            ARLog.m20419e("AbilityManager", "setup exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMdlModelPath(String str) {
        if (this.mMdlConfigParams == null) {
            this.mMdlConfigParams = new MdlConfigParams();
        }
        this.mMdlConfigParams.setMdlConfigPath(str, this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pause() {
        if (this.mActiveARAbilities.isEmpty()) {
            return;
        }
        for (AbstractAR abstractAR : this.mActiveARAbilities.values()) {
            abstractAR.pause();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resume() {
        if (this.mActiveARAbilities.isEmpty()) {
            return;
        }
        for (AbstractAR abstractAR : this.mActiveARAbilities.values()) {
            abstractAR.resume();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCaseCreate(String str) {
        if (this.mActiveARAbilities.isEmpty()) {
            return;
        }
        for (AbstractAR abstractAR : this.mActiveARAbilities.values()) {
            ARLog.m20421d("AbilityManager", "onCaseCreate casePath = " + str);
            abstractAR.onCaseCreate(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCaseDestroy() {
        if (this.mActiveARAbilities.isEmpty()) {
            return;
        }
        for (AbstractAR abstractAR : this.mActiveARAbilities.values()) {
            abstractAR.onCaseDestroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ARProxyManager getARProxyManager() {
        return this.mARProxyManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addAbility(String str, List<String> list, String str2) {
        if (TextUtils.isEmpty(str) || list == null || list.size() <= 0 || !ReflectionUtils.isClassExist(str, getClass().getClassLoader())) {
            return false;
        }
        for (String str3 : list) {
            this.mSupportedARClasses.put(str3, str);
        }
        if (TextUtils.isEmpty(str2)) {
            return true;
        }
        this.mSupportedDetectors.put(str2, str);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> getSupportedAbilities() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : this.mSupportedARClasses.entrySet()) {
            arrayList.add(entry.getKey());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAbilitySupported(String str) {
        return this.mSupportedARClasses.get(str) != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> getActiveAbilities() {
        return this.mEnabledAbilities;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAbilityActive(String str) {
        return this.mEnabledAbilities.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean adjustAbility(String str, HashMap<String, Object> hashMap) {
        if (!this.mEnabledAbilities.contains(str)) {
            ARLog.m20419e("AbilityManager", "adjustAbility abilityType = " + str + " not start!!!");
            return false;
        } else if (this.mAbilityHandler != null) {
            AdjustARModel adjustARModel = new AdjustARModel(this.mSupportedARClasses.get(str), hashMap);
            AbilityHandler abilityHandler = this.mAbilityHandler;
            abilityHandler.sendMessage(abilityHandler.obtainMessage(1002, adjustARModel));
            return true;
        } else {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startDefaultAbilitys() {
        if (this.mDefaultParams == null) {
            return;
        }
        this.mDefaultAbilities.clear();
        this.mEnabledAbilities.clear();
        this.mEnabledAbilities.add("ability_common_filter");
        List<String> list = this.mUserOpenAbilities;
        if (list != null && list.size() > 0) {
            this.mEnabledAbilities.addAll(this.mUserOpenAbilities);
        }
        if (this.mDefaultParams.isUseFaceFilter() && AbilityAuth.hasAbilityTypeAuth("ability_face_filter")) {
            this.mDefaultAbilities.add("ability_face_filter");
            ARFilterManager aRFilterManager = this.mARFilterManager;
            if (aRFilterManager != null) {
                aRFilterManager.setFilterEnable(FilterParam.SkinFilter.whiten.getFilterNode().getNodeName(), true);
                this.mARFilterManager.updateAbilityState(FilterNode.faceFilter, true);
            }
            if (this.mIsFirstStartDefault) {
                this.mIsFirstStartDefault = false;
                StatisticApi.onEventDebounce("event_filter_adjust", 200L, "");
                StatisticApi.onEventDebounce("event_beautify_adjust", 200L, "");
            }
        }
        if (this.mDefaultParams.isUseMakeupFilter()) {
            if (AbilityAuth.hasAbilityTypeAuth("ability_makeup_filter")) {
                this.mDefaultAbilities.add("ability_makeup_filter");
            }
            ARFilterManager aRFilterManager2 = this.mARFilterManager;
            if (aRFilterManager2 != null) {
                aRFilterManager2.updateAbilityState(FilterNode.makeupFilter, true);
            }
        }
        ArrayList arrayList = new ArrayList();
        if (this.mDefaultAbilities.contains("ability_face_filter")) {
            arrayList.add("ability_face_filter");
        }
        if (this.mDefaultAbilities.contains("ability_makeup_filter")) {
            arrayList.add("ability_makeup_filter");
        }
        if (arrayList.size() > 0) {
            startDefaultFaceAbility(arrayList);
        }
        DefaultParams defaultParams = this.mDefaultParams;
        if (defaultParams != null) {
            String renderPipeline = defaultParams.getRenderPipeline();
            if (TextUtils.isEmpty(renderPipeline) || !renderPipeline.contains(FilterNode.highlightFilter.getNodeName())) {
                return;
            }
            this.mAbilityHandler.postDelayed(new Runnable() { // from class: com.baidu.ar.AbilityManager.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AbilityManager.this.mARFilterManager != null) {
                        AbilityManager.this.mARFilterManager.restoreBeautyState();
                    }
                }
            }, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void restoreDefaultARAbility() {
        ARProxyManager aRProxyManager = this.mARProxyManager;
        if (aRProxyManager != null) {
            aRProxyManager.unbindAllARAbility();
        }
        ArrayList<String> arrayList = new ArrayList();
        List<String> defaultARAbilityClassNames = getDefaultARAbilityClassNames();
        for (String str : this.mEnabledAbilities) {
            String str2 = TextUtils.isEmpty(str) ? null : this.mSupportedARClasses.get(str);
            if (!TextUtils.isEmpty(str2) && !defaultARAbilityClassNames.contains(str2) && !arrayList.contains(str2)) {
                arrayList.add(str2);
            }
        }
        startDefaultAbilitys();
        if (this.mAbilityHandler != null) {
            for (String str3 : arrayList) {
                if (!isUserOpenARClass(str3)) {
                    AbilityHandler abilityHandler = this.mAbilityHandler;
                    abilityHandler.sendMessage(abilityHandler.obtainMessage(1003, new StopARModel(str3)));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void destroyAllARAbility() {
        ARFilterManager aRFilterManager = this.mARFilterManager;
        if (aRFilterManager != null) {
            aRFilterManager.updateAbilityState(FilterNode.faceFilter, false);
            this.mARFilterManager.updateAbilityState(FilterNode.makeupFilter, false);
        }
        ARProxyManager aRProxyManager = this.mARProxyManager;
        if (aRProxyManager != null) {
            aRProxyManager.unbindAllARAbility();
        }
        for (String str : this.mEnabledAbilities) {
            disableAbility(str);
        }
        this.mEnabledAbilities.clear();
        this.mEnabledAbilities.add("ability_common_filter");
        List<String> list = this.mUserOpenAbilities;
        if (list != null && list.size() > 0) {
            this.mEnabledAbilities.addAll(this.mUserOpenAbilities);
        }
        if (this.mAbilityHandler != null) {
            for (Map.Entry<String, AbstractAR> entry : this.mActiveARAbilities.entrySet()) {
                if (!isUserOpenARClass(entry.getKey())) {
                    AbilityHandler abilityHandler = this.mAbilityHandler;
                    abilityHandler.sendMessage(abilityHandler.obtainMessage(1003, new StopARModel(entry.getKey())));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release() {
        ARProxyManager aRProxyManager = this.mARProxyManager;
        if (aRProxyManager != null) {
            aRProxyManager.release();
            this.mARProxyManager = null;
        }
        for (AbstractAR abstractAR : this.mActiveARAbilities.values()) {
            abstractAR.release();
        }
        this.mActiveARAbilities.clear();
        this.mSupportedARClasses.clear();
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge != null && engineMsgBridge.getLuaMsgBridge() != null) {
            this.mEngineMsgBridge.removeEngineMsgListener(this.mTypeAbilityListener);
            this.mEngineMsgBridge.getLuaMsgBridge().removeLuaMsgListener(this.mIdAbilityListener);
            this.mEngineMsgBridge.getLuaMsgBridge().removeLuaMsgListener(this.mEventAbilityListener);
            this.mEngineMsgBridge = null;
            this.mTypeAbilityListener = null;
            this.mIdAbilityListener = null;
            this.mEventAbilityListener = null;
        }
        this.mTypeAbilityMessages = null;
        this.mIdAbilityMessages = null;
        this.mEventAbilityMessages = null;
        IImu iImu = this.mIMUController;
        if (iImu != null) {
            iImu.destroy();
            this.mIMUController = null;
        }
        DetectorManager detectorManager = this.mDetectorManager;
        if (detectorManager != null) {
            detectorManager.release();
            this.mDetectorManager = null;
        }
        List<String> list = this.mUserOpenAbilities;
        if (list != null) {
            list.clear();
            this.mUserOpenAbilities = null;
        }
        this.mContext = null;
        this.mLooper = null;
        this.mDefaultParams = null;
        this.mMdlConfigParams = null;
        this.mARFilterManager = null;
        this.mARRenderer = null;
        if (this.mAbilitySchemeControl != null) {
            this.mAbilitySchemeControl = null;
        }
    }

    private void startDefaultFaceAbility(final List<String> list) {
        LibLoader.prepareCaseRes(ARType.FACE, null, null, new ILibLoader.CaseReadyListener() { // from class: com.baidu.ar.AbilityManager.2
            @Override // com.baidu.p120ar.libloader.ILibLoader.CaseReadyListener
            public void onReady(ARType aRType, String str, String str2) {
                AbilityManager.this.mEnabledAbilities.addAll(AbilityManager.this.mDefaultAbilities);
                String str3 = (String) AbilityManager.this.mSupportedARClasses.get("ability_face_filter");
                if (TextUtils.isEmpty(str3) || AbilityManager.this.mAbilityHandler == null) {
                    return;
                }
                if (AbilityManager.this.mActiveARAbilities.get(str3) != null) {
                    ((AbstractAR) AbilityManager.this.mActiveARAbilities.get(str3)).clearAbilities();
                    ((AbstractAR) AbilityManager.this.mActiveARAbilities.get(str3)).addAbilities(list);
                    return;
                }
                AbilityManager.this.mAbilityHandler.sendMessage(AbilityManager.this.mAbilityHandler.obtainMessage(1001, new StartARModel(str3, (List<String>) list, true, (HashMap<String, Object>) null)));
            }

            @Override // com.baidu.p120ar.libloader.ILibLoader.CaseReadyListener
            public void onError(DuMixErrorType duMixErrorType, String str) {
                ARLog.m20419e("AbilityManager", "startDefaultFaceAbility error!!! errorType = " + duMixErrorType.toString() + " && error message = " + str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void createARAbilityByType(ARType aRType) {
        if (aRType == ARType.FACE) {
            if (!AbilityAuth.checkAbilityTypeAuth("ability_face_model")) {
                return;
            }
            if (this.mDefaultParams.isUseFaceFilter()) {
                enableAbility("ability_face_model");
            }
        } else {
            destroyAllARAbility();
        }
        this.mARFilterManager.setFilterEnable(FilterParam.SkinFilter.whiten.getFilterNode().getNodeName(), aRType == ARType.FACE || this.mDefaultParams.isUseBeautyFilter());
        String str = AbilityConstants.ARTYPE_2_ABILITY_MAP.get(aRType);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String str2 = this.mSupportedARClasses.get(str);
        if (!TextUtils.isEmpty(str2) && this.mActiveARAbilities.get(str2) != null) {
            this.mActiveARAbilities.get(str2).addAbilities(str);
        }
        if (this.mEnabledAbilities.contains(str) || aRType == ARType.IMU || TextUtils.isEmpty(str2) || this.mAbilityHandler == null) {
            return;
        }
        this.mEnabledAbilities.add(str);
        StartARModel startARModel = new StartARModel(str2, str, false, (HashMap<String, Object>) null);
        AbilityHandler abilityHandler = this.mAbilityHandler;
        abilityHandler.sendMessage(abilityHandler.obtainMessage(1001, startARModel));
    }

    private void setARAbilityEventListener() {
        if (this.mEngineMsgBridge == null) {
            return;
        }
        this.mTypeAbilityMessages = Arrays.asList(301, 303);
        this.mTypeAbilityListener = new EngineMsgListener() { // from class: com.baidu.ar.AbilityManager.3
            @Override // com.baidu.p120ar.lua.EngineMsgListener
            public List<Integer> getMsgTypesListened() {
                return AbilityManager.this.mTypeAbilityMessages;
            }

            @Override // com.baidu.p120ar.lua.EngineMsgListener
            public void onEngineMessage(int i, int i2, HashMap<String, Object> hashMap) {
                AbilityManager.this.operateAbilityByType(i, hashMap);
            }
        };
        this.mEngineMsgBridge.addEngineMsgListener(this.mTypeAbilityListener);
        this.mIdAbilityMessages = Arrays.asList("id");
        this.mIdAbilityListener = new LuaMsgListener() { // from class: com.baidu.ar.AbilityManager.4
            @Override // com.baidu.p120ar.lua.LuaMsgListener
            public List<String> getMsgKeyListened() {
                return AbilityManager.this.mIdAbilityMessages;
            }

            @Override // com.baidu.p120ar.lua.LuaMsgListener
            public void onLuaMessage(HashMap<String, Object> hashMap) {
                AbilityManager.this.operateAbilityById(hashMap);
            }
        };
        this.mEngineMsgBridge.getLuaMsgBridge().addLuaMsgListener(this.mIdAbilityListener);
        this.mEventAbilityMessages = Arrays.asList("event_name");
        this.mEventAbilityListener = new LuaMsgListener() { // from class: com.baidu.ar.AbilityManager.5
            @Override // com.baidu.p120ar.lua.LuaMsgListener
            public List<String> getMsgKeyListened() {
                return AbilityManager.this.mEventAbilityMessages;
            }

            @Override // com.baidu.p120ar.lua.LuaMsgListener
            public void onLuaMessage(HashMap<String, Object> hashMap) {
                AbilityManager.this.operateAbilityByEvent(hashMap);
                AbilityManager.this.operateFilterState(hashMap);
            }
        };
        this.mEngineMsgBridge.getLuaMsgBridge().addLuaMsgListener(this.mEventAbilityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void operateAbilityByType(int i, HashMap<String, Object> hashMap) {
        if (i == 301) {
            if (this.mActiveARAbilities == null || !this.mEnabledAbilities.contains("ability_image_track")) {
                startAbility("ability_imu", hashMap);
            }
        } else if (i != 303) {
        } else {
            if (this.mActiveARAbilities == null || !this.mEnabledAbilities.contains("ability_image_track")) {
                stopAbility("ability_imu");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void operateAbilityById(HashMap<String, Object> hashMap) {
        int obj2Int = MsgParamsUtil.obj2Int(hashMap.get("id"), -1);
        boolean z = MsgParamsUtil.obj2Int(hashMap.get("open"), -1) == 1;
        ARLog.m20421d("AbilityManager", "operateAbilityById id = " + obj2Int + " && open = " + z);
        String str = null;
        if (obj2Int == 5001) {
            str = "ability_gesture";
        } else if (obj2Int == 5011) {
            str = "ability_image_segmentation";
        } else if (obj2Int == 3005) {
            str = "ability_logo_recognition";
            z = true;
        } else if (obj2Int == 3006) {
            z = false;
        }
        if (str != null) {
            if (z) {
                startAbility(str, hashMap);
            } else {
                stopAbility(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void operateAbilityByEvent(HashMap<String, Object> hashMap) {
        String str = (String) hashMap.get("event_name");
        ARLog.m20421d("AbilityManager", "operateAbilityByEvent eventName = " + str);
        if ("ability_operation".equals(str)) {
            String str2 = (String) hashMap.get("ability_name");
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            String str3 = (String) hashMap.get("ability_action");
            if ("open".equals(str3)) {
                startAbility(str2, hashMap);
                return;
            } else if ("close".equals(str3)) {
                stopAbility(str2);
                return;
            } else if ("adjust".equals(str3)) {
                adjustAbility(str2, hashMap);
                return;
            } else if ("query".equals(str3)) {
                sendAbilityNames2Lua();
                return;
            } else {
                return;
            }
        }
        String str4 = AbilityConstants.START_ABILITY_2_ABILITY_MAP.get(str);
        if (!TextUtils.isEmpty(str4)) {
            startAbility(str4, hashMap);
            return;
        }
        String str5 = AbilityConstants.STOP_ABILITY_2_ABILITY_MAP.get(str);
        if (TextUtils.isEmpty(str5)) {
            return;
        }
        stopAbility(str5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void operateFilterState(HashMap<String, Object> hashMap) {
        if ("ability_operation".equals((String) hashMap.get("event_name"))) {
            String str = (String) hashMap.get("ability_name");
            if (TextUtils.isEmpty(str) || !AbilityConstants.FILTER_ABILITY_LIST.contains(str)) {
                return;
            }
            ARLog.m20421d("AbilityManager", "operateFilterState abilityName = " + str);
            String str2 = (String) hashMap.get("ability_action");
            if ("open".equals(str2) && "close".equals(str2)) {
                boolean equals = "open".equals(str2);
                if (this.mARFilterManager != null) {
                    if ("ability_makeup_filter".equals(str)) {
                        this.mARFilterManager.updateAbilityState(FilterNode.makeupFilter, equals);
                    } else if ("ability_face_filter".equals(str)) {
                        this.mARFilterManager.updateAbilityState(FilterNode.faceFilter, equals);
                    } else if ("ability_beauty_filter".equals(str)) {
                        this.mARFilterManager.updateAbilityState(FilterNode.skinFilter, equals);
                    } else if ("ability_lut_filter".equals(str)) {
                        this.mARFilterManager.updateAbilityState(FilterNode.lutFilter, equals);
                    }
                    this.mARFilterManager.callbackFilterStates();
                }
            }
        }
    }

    boolean startAbility(String str, HashMap<String, Object> hashMap) {
        return startAbility(str, hashMap, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean startAbility(String str, HashMap<String, Object> hashMap, boolean z) {
        if (!AbilityAuth.checkAbilityTypeAuth(str)) {
            ARLog.m20419e("AbilityManager", "startAbility abilityType = " + str + " is no authorization!!!");
            return false;
        }
        if (z && !this.mUserOpenAbilities.contains(str)) {
            this.mUserOpenAbilities.add(str);
        }
        if (this.mEnabledAbilities.contains(str)) {
            ARLog.m20419e("AbilityManager", "startAbility abilityType = " + str + " is exist!!!");
            return false;
        }
        String str2 = this.mSupportedARClasses.get(str);
        if (!TextUtils.isEmpty(str2)) {
            enableAbility(str);
            if (this.mAbilityHandler != null) {
                StartARModel startARModel = new StartARModel(str2, str, false, hashMap);
                AbilityHandler abilityHandler = this.mAbilityHandler;
                abilityHandler.sendMessage(abilityHandler.obtainMessage(1001, startARModel));
                return true;
            }
        }
        return false;
    }

    boolean stopAbility(String str) {
        return stopAbility(str, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean stopAbility(String str, boolean z) {
        AbilityHandler abilityHandler;
        if (z && this.mUserOpenAbilities.contains(str)) {
            this.mUserOpenAbilities.remove(str);
        }
        disableAbility(str);
        ArrayList arrayList = new ArrayList();
        for (String str2 : this.mEnabledAbilities) {
            String str3 = this.mSupportedARClasses.get(str2);
            if (!TextUtils.isEmpty(str3) && !arrayList.contains(str3)) {
                arrayList.add(str3);
            }
        }
        String str4 = this.mSupportedARClasses.get(str);
        if (arrayList.contains(str4) || (abilityHandler = this.mAbilityHandler) == null) {
            return false;
        }
        abilityHandler.sendMessage(abilityHandler.obtainMessage(1003, new StopARModel(str4)));
        return true;
    }

    private void sendAbilityNames2Lua() {
        if (this.mEngineMsgBridge != null) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ability_name", this.mEnabledAbilities);
            this.mEngineMsgBridge.sendMsg2Engine(1902, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createARAbility(String str, List<String> list, boolean z, HashMap<String, Object> hashMap, String str2, DetectorCallback detectorCallback) {
        if (!AbilityAuth.checkARTypeAuth(str)) {
            ARLog.m20419e("AbilityManager", "checkARTypeAuth error!!!");
            return;
        }
        AbstractAR abstractAR = this.mActiveARAbilities.get(str);
        if (abstractAR != null) {
            ARLog.m20421d("AbilityManager", "createARAbility arClassName = " + str + " ARAbility exist!!!");
            if (list != null) {
                if (z) {
                    abstractAR.clearAbilities();
                }
                abstractAR.addAbilities(list);
            }
            abstractAR.adjust(hashMap);
        } else {
            abstractAR = createARAbility(str);
            if (abstractAR == null) {
                ARLog.m20419e("AbilityManager", "createARAbility createARAbility error!!!");
                return;
            }
            if (list != null) {
                abstractAR.addAbilities(list);
            }
            if (!TextUtils.isEmpty(this.mDefaultParams.getFaceAlgoModelPath())) {
                abstractAR.setFaceModelPath(this.mDefaultParams.getFaceAlgoModelPath());
            }
            abstractAR.setMdlConfigParams(this.mMdlConfigParams);
            abstractAR.setup(hashMap);
        }
        if (TextUtils.isEmpty(str2) || detectorCallback == null) {
            return;
        }
        abstractAR.addDetectorCallback(str2, detectorCallback);
    }

    private AbstractAR createARAbility(String str) {
        AbstractAR abstractAR = (AbstractAR) ReflectionUtils.getNewInstance(str);
        if (abstractAR == null) {
            ARLog.m20419e("AbilityManager", "createARAbility error!!!");
            return null;
        }
        this.mActiveARAbilities.put(str, abstractAR);
        abstractAR.setContextAndLooper(this.mContext, this.mLooper);
        abstractAR.setARManagers(this.mDetectorManager, this.mARRenderer, this.mARFilterManager);
        abstractAR.setEngineMsgBridge(this.mEngineMsgBridge);
        abstractAR.setImuController(this.mIMUController);
        abstractAR.setRequireDetectorListener(this.mRequireDetectorListener);
        AbilitySchemeControl abilitySchemeControl = this.mAbilitySchemeControl;
        if (abilitySchemeControl != null) {
            abstractAR.setAbilityScheme(abilitySchemeControl.getScheme());
        }
        ARProxyManager aRProxyManager = this.mARProxyManager;
        if (aRProxyManager != null && aRProxyManager.hasProxy(str)) {
            this.mARProxyManager.bindARAbility(abstractAR, str);
        }
        return abstractAR;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustARAbility(String str, HashMap<String, Object> hashMap) {
        AbstractAR abstractAR = this.mActiveARAbilities.get(str);
        if (abstractAR != null) {
            abstractAR.adjust(hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyAbility(String str, String str2, DetectorCallback detectorCallback) {
        if (TextUtils.isEmpty(str)) {
            ARLog.m20419e("AbilityManager", "destroyAbility error!!! arClassName is empty!!!");
            return;
        }
        AbstractAR abstractAR = this.mActiveARAbilities.get(str);
        if (abstractAR == null) {
            ARLog.m20419e("AbilityManager", "destroyAbility error!!! As arClassName = " + str + " not active!!!");
            return;
        }
        if (!TextUtils.isEmpty(str2) && detectorCallback != null) {
            abstractAR.removeDetectorCallback(str2, detectorCallback);
        } else {
            abstractAR.clearAbilities();
        }
        if (abstractAR.canRelease()) {
            ARProxyManager aRProxyManager = this.mARProxyManager;
            if (aRProxyManager != null && aRProxyManager.hasProxy(str)) {
                this.mARProxyManager.unbindARAbility(str);
            }
            this.mActiveARAbilities.remove(str);
            abstractAR.release();
        }
    }

    private void enableAbility(String str) {
        if (!this.mEnabledAbilities.contains(str)) {
            this.mEnabledAbilities.add(str);
            if ((str.equals("ability_makeup_filter") || str.equals("ability_face_filter")) && this.mDefaultParams.isUseMakeupFilter()) {
                this.mEnabledAbilities.add("ability_makeup_filter");
                return;
            }
            return;
        }
        ARLog.m20421d("AbilityManager", "enableAbility() abilityName " + str + " has enabled!!!");
    }

    private void disableAbility(String str) {
        ConcurrentHashMap<String, AbstractAR> concurrentHashMap;
        AbstractAR abstractAR;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ConcurrentHashMap<String, String> concurrentHashMap2 = this.mSupportedARClasses;
        if (concurrentHashMap2 != null) {
            String str2 = concurrentHashMap2.get(str);
            if (!TextUtils.isEmpty(str2) && (concurrentHashMap = this.mActiveARAbilities) != null && (abstractAR = concurrentHashMap.get(str2)) != null) {
                abstractAR.removeAbility(str);
            }
        }
        if (this.mEnabledAbilities.contains(str)) {
            if (str.equals("ability_makeup_filter") || str.equals("ability_face_filter")) {
                this.mEnabledAbilities.remove("ability_makeup_filter");
            }
            this.mEnabledAbilities.remove(str);
            return;
        }
        ARLog.m20421d("AbilityManager", "disableAbility() abilityName " + str + " has disabled!!!");
    }

    private void initSupportedARClasses() {
        long currentTimeMillis = System.currentTimeMillis();
        ClassLoader classLoader = getClass().getClassLoader();
        for (Map.Entry<String, String> entry : AbilityConstants.ABILITY_2_CLASS_MAP.entrySet()) {
            if (ReflectionUtils.isClassExist(entry.getValue(), classLoader)) {
                this.mSupportedARClasses.put(entry.getKey(), entry.getValue());
            }
        }
        this.mSupportedDetectors.putAll(AbilityConstants.DETECTOR_2_CLASS_MAP);
        ARLog.m20417i("AbilityManager", "initSupportedARClasses mSupportedARClasses = " + this.mSupportedARClasses.values());
        ARLog.m20421d("AbilityManager", "initSupportedARClasses time cost = " + (System.currentTimeMillis() - currentTimeMillis));
    }

    private void createDetectorListener() {
        this.mRequireDetectorListener = new AbstractAR.RequireDetectorListener() { // from class: com.baidu.ar.AbilityManager.6
            @Override // com.baidu.p120ar.AbstractAR.RequireDetectorListener
            public List<String> getAvailableDetectors() {
                if (AbilityManager.this.mSupportedDetectors == null || AbilityManager.this.mSupportedDetectors.size() <= 0) {
                    return null;
                }
                return Collections.list(AbilityManager.this.mSupportedDetectors.keys());
            }

            @Override // com.baidu.p120ar.AbstractAR.RequireDetectorListener
            public boolean requireStartDetector(String str, DetectorCallback detectorCallback, HashMap<String, Object> hashMap) {
                if (AbilityManager.this.mSupportedDetectors == null || !AbilityManager.this.mSupportedDetectors.containsKey(str)) {
                    return false;
                }
                String str2 = (String) AbilityManager.this.mSupportedDetectors.get(str);
                if (TextUtils.isEmpty(str2) || AbilityManager.this.mAbilityHandler == null) {
                    return false;
                }
                AbilityManager.this.mAbilityHandler.sendMessage(AbilityManager.this.mAbilityHandler.obtainMessage(1001, new StartARModel(str2, hashMap, str, detectorCallback)));
                return true;
            }

            @Override // com.baidu.p120ar.AbstractAR.RequireDetectorListener
            public boolean requireStopDetector(String str, DetectorCallback detectorCallback) {
                if (AbilityManager.this.mSupportedDetectors == null || !AbilityManager.this.mSupportedDetectors.containsKey(str)) {
                    return false;
                }
                String str2 = (String) AbilityManager.this.mSupportedDetectors.get(str);
                if (TextUtils.isEmpty(str2) || AbilityManager.this.mAbilityHandler == null) {
                    return false;
                }
                AbilityManager.this.mAbilityHandler.sendMessage(AbilityManager.this.mAbilityHandler.obtainMessage(1003, new StopARModel(str2, str, detectorCallback)));
                return true;
            }
        };
    }

    private void createCameraSwitchListener() {
        this.mCameraSwitchListener = new CameraSwitchListener() { // from class: com.baidu.ar.AbilityManager.7
            @Override // com.baidu.p120ar.arrender.CameraSwitchListener
            public void onCameraSwitch(boolean z) {
                for (AbstractAR abstractAR : AbilityManager.this.mActiveARAbilities.values()) {
                    abstractAR.onCameraSwitch(z);
                }
            }
        };
        this.mARRenderer.setCameraSwitchListener(this.mCameraSwitchListener);
    }

    private List<String> getDefaultARAbilityClassNames() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.mDefaultAbilities) {
            String str2 = this.mSupportedARClasses.get(str);
            if (!arrayList.contains(str2)) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    private boolean isUserOpenARClass(String str) {
        List<String> list;
        if (TextUtils.isEmpty(str) || (list = this.mUserOpenAbilities) == null) {
            return false;
        }
        for (String str2 : list) {
            if (str.equals(this.mSupportedARClasses.get(str2))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.AbilityManager$AbilityHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AbilityHandler extends Handler {
        public static final int MSG_ADJUST_ABILITY = 1002;
        public static final int MSG_CREATE_ABILITY = 1001;
        public static final int MSG_DESTROY_ABILITY = 1003;

        public AbilityHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 1001:
                        StartARModel startARModel = (StartARModel) message.obj;
                        AbilityManager.this.createARAbility(startARModel.mClassName, startARModel.mAbilityNames, startARModel.mResetAbility, startARModel.mParams, startARModel.mDetectorName, startARModel.mCallback);
                        break;
                    case 1002:
                        AdjustARModel adjustARModel = (AdjustARModel) message.obj;
                        AbilityManager.this.adjustARAbility(adjustARModel.mClassName, adjustARModel.mParams);
                        break;
                    case 1003:
                        StopARModel stopARModel = (StopARModel) message.obj;
                        AbilityManager.this.destroyAbility(stopARModel.mClassName, stopARModel.mDetectorName, stopARModel.mCallback);
                        break;
                }
            } catch (Exception e) {
                ARLog.m20419e("AbilityManager", "handleMessage Exception: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.AbilityManager$StartARModel */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class StartARModel {
        List<String> mAbilityNames;
        DetectorCallback mCallback;
        String mClassName;
        String mDetectorName;
        HashMap<String, Object> mParams;
        boolean mResetAbility;

        StartARModel(String str, String str2, boolean z, HashMap<String, Object> hashMap) {
            this.mClassName = str;
            this.mAbilityNames = new ArrayList();
            this.mAbilityNames.add(str2);
            this.mResetAbility = z;
            this.mParams = hashMap;
        }

        StartARModel(String str, List<String> list, boolean z, HashMap<String, Object> hashMap) {
            this.mClassName = str;
            this.mAbilityNames = list;
            this.mResetAbility = z;
            this.mParams = hashMap;
        }

        StartARModel(String str, HashMap<String, Object> hashMap, String str2, DetectorCallback detectorCallback) {
            this.mClassName = str;
            this.mDetectorName = str2;
            this.mParams = hashMap;
            this.mCallback = detectorCallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.AbilityManager$AdjustARModel */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AdjustARModel {
        String mClassName;
        HashMap<String, Object> mParams;

        AdjustARModel(String str, HashMap<String, Object> hashMap) {
            this.mClassName = str;
            this.mParams = hashMap;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.AbilityManager$StopARModel */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class StopARModel {
        DetectorCallback mCallback;
        String mClassName;
        String mDetectorName;

        StopARModel(String str) {
            this.mClassName = str;
        }

        StopARModel(String str, String str2, DetectorCallback detectorCallback) {
            this.mClassName = str;
            this.mDetectorName = str2;
            this.mCallback = detectorCallback;
        }
    }
}
