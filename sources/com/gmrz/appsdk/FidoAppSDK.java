package com.gmrz.appsdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.support.p083v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.gmrz.appsdk.attestation.KeyASecurityType;
import com.gmrz.appsdk.commlib.FidoMode;
import com.gmrz.appsdk.commlib.UAFIntentType;
import com.gmrz.appsdk.commlib.UafAppSDKProxy;
import com.gmrz.appsdk.commlib.UafLocalSDKProxy;
import com.gmrz.appsdk.commlib.api.FidoCallback;
import com.gmrz.appsdk.commlib.api.FidoParam;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.commlib.api.FidoType;
import com.gmrz.appsdk.commlib.api.IAppSDK;
import com.gmrz.appsdk.commlib.api.IGmrzAdapter;
import com.gmrz.appsdk.commlib.api.UACPlugin;
import com.gmrz.appsdk.entity.CheckDeviceAbility;
import com.gmrz.appsdk.entity.OperationHeader;
import com.gmrz.appsdk.recorder.api.Record;
import com.gmrz.appsdk.task.InitTask;
import com.gmrz.appsdk.task.ProcessTask;
import com.gmrz.appsdk.util.Base64Util;
import com.gmrz.appsdk.util.Compatibility;
import com.gmrz.appsdk.util.Constant;
import com.gmrz.appsdk.util.DiscoveryUtil;
import com.gmrz.appsdk.util.FingerprintSetUtil;
import com.gmrz.appsdk.util.FingerprintUtil;
import com.gmrz.appsdk.util.FpUtil;
import com.gmrz.appsdk.util.HwUtil;
import com.gmrz.appsdk.util.Logger;
import com.gmrz.appsdk.util.SignUtil;
import com.gmrz.appsdk.util.ThreatDetector;
import com.gmrz.appsdk.util.UACUtil;
import com.gmrz.fido.offline.Converter;
import com.gmrz.fido.offline.CryptoFileSuit;
import com.gmrz.fido.offline.Noter;
import com.gmrz.fido.offline.ProcessCallback;
import com.gmrz.fido.offline.SnGenerator;
import com.gmrz.fido.offline.StrongBox;
import com.mabeijianxi.smallvideorecord2.MediaRecorderBase;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FidoAppSDK {
    private static final String TAG = FidoAppSDK.class.getSimpleName() + "_fido";
    private static final Lock mLock = new ReentrantLock();
    private HashMap<String, HashSet<String>> mAAIDsServerSupportLogin;
    private HashMap<String, HashSet<String>> mAAIDsServerSupportTrade;
    private Map<UACPlugin, IGmrzAdapter> mAuthAbilty;
    private final FidoParam mFidoparam;
    private String serverRespTemp;
    private String transType;
    private Set<UACPlugin> uacPlugins;

    /* renamed from: com.gmrz.appsdk.FidoAppSDK$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC4396a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ FidoCallback f10188a;

        /* renamed from: b */
        final /* synthetic */ Context f10189b;

        /* renamed from: c */
        final /* synthetic */ FidoIn f10190c;

        RunnableC4396a(FidoCallback fidoCallback, Context context, FidoIn fidoIn) {
            this.f10188a = fidoCallback;
            this.f10189b = context;
            this.f10190c = fidoIn;
        }

        @Override // java.lang.Runnable
        public void run() {
            FidoCallback fidoCallback = this.f10188a;
            if (fidoCallback != null) {
                fidoCallback.onFidoProcess(FidoAppSDK.this.checkNetSupportBase64(this.f10189b, this.f10190c));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.gmrz.appsdk.FidoAppSDK$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static /* synthetic */ class C4397b {

        /* renamed from: a */
        static final /* synthetic */ int[] f10192a;

        static {
            int[] iArr = new int[FidoType.values().length];
            f10192a = iArr;
            try {
                iArr[FidoType.FINGER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10192a[FidoType.IRIS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10192a[FidoType.FACE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10192a[FidoType.GESTURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* renamed from: com.gmrz.appsdk.FidoAppSDK$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC4398c implements Runnable {

        /* renamed from: a */
        final /* synthetic */ FidoCallback f10193a;

        /* renamed from: b */
        final /* synthetic */ Context f10194b;

        RunnableC4398c(FidoCallback fidoCallback, Context context) {
            this.f10193a = fidoCallback;
            this.f10194b = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            FidoCallback fidoCallback = this.f10193a;
            if (fidoCallback != null) {
                fidoCallback.onFidoProcess(FidoAppSDK.this.initFido(this.f10194b));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.gmrz.appsdk.FidoAppSDK$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC4399d implements Runnable {

        /* renamed from: a */
        final /* synthetic */ FidoCallback f10196a;

        /* renamed from: b */
        final /* synthetic */ Context f10197b;

        /* renamed from: c */
        final /* synthetic */ FidoParam f10198c;

        /* renamed from: d */
        final /* synthetic */ Map f10199d;

        RunnableC4399d(FidoCallback fidoCallback, Context context, FidoParam fidoParam, Map map) {
            this.f10196a = fidoCallback;
            this.f10197b = context;
            this.f10198c = fidoParam;
            this.f10199d = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            FidoCallback fidoCallback = this.f10196a;
            if (fidoCallback != null) {
                fidoCallback.onFidoProcess(FidoAppSDK.this.initFido(this.f10197b, this.f10198c, this.f10199d));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.gmrz.appsdk.FidoAppSDK$f */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC4401f implements Runnable {

        /* renamed from: a */
        final /* synthetic */ FidoCallback f10205a;

        /* renamed from: b */
        final /* synthetic */ Context f10206b;

        /* renamed from: c */
        final /* synthetic */ FidoIn f10207c;

        RunnableC4401f(FidoCallback fidoCallback, Context context, FidoIn fidoIn) {
            this.f10205a = fidoCallback;
            this.f10206b = context;
            this.f10207c = fidoIn;
        }

        @Override // java.lang.Runnable
        public void run() {
            FidoCallback fidoCallback = this.f10205a;
            if (fidoCallback != null) {
                fidoCallback.onFidoProcess(FidoAppSDK.this.process(this.f10206b, this.f10207c));
            }
        }
    }

    /* renamed from: com.gmrz.appsdk.FidoAppSDK$g */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC4402g implements Runnable {

        /* renamed from: a */
        final /* synthetic */ FidoCallback f10209a;

        /* renamed from: b */
        final /* synthetic */ Context f10210b;

        /* renamed from: c */
        final /* synthetic */ FidoIn f10211c;

        RunnableC4402g(FidoCallback fidoCallback, Context context, FidoIn fidoIn) {
            this.f10209a = fidoCallback;
            this.f10210b = context;
            this.f10211c = fidoIn;
        }

        @Override // java.lang.Runnable
        public void run() {
            FidoCallback fidoCallback = this.f10209a;
            if (fidoCallback != null) {
                fidoCallback.onFidoProcess(FidoAppSDK.this.processBase64(this.f10210b, this.f10211c));
            }
        }
    }

    /* renamed from: com.gmrz.appsdk.FidoAppSDK$h */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC4403h implements Runnable {

        /* renamed from: a */
        final /* synthetic */ FidoCallback f10213a;

        /* renamed from: b */
        final /* synthetic */ Context f10214b;

        /* renamed from: c */
        final /* synthetic */ FidoIn f10215c;

        RunnableC4403h(FidoCallback fidoCallback, Context context, FidoIn fidoIn) {
            this.f10213a = fidoCallback;
            this.f10214b = context;
            this.f10215c = fidoIn;
        }

        @Override // java.lang.Runnable
        public void run() {
            FidoCallback fidoCallback = this.f10213a;
            if (fidoCallback != null) {
                fidoCallback.onFidoProcess(FidoAppSDK.this.checkPolicy(this.f10214b, this.f10215c));
            }
        }
    }

    /* renamed from: com.gmrz.appsdk.FidoAppSDK$i */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC4404i implements Runnable {

        /* renamed from: a */
        final /* synthetic */ FidoCallback f10217a;

        /* renamed from: b */
        final /* synthetic */ Context f10218b;

        /* renamed from: c */
        final /* synthetic */ FidoIn f10219c;

        RunnableC4404i(FidoCallback fidoCallback, Context context, FidoIn fidoIn) {
            this.f10217a = fidoCallback;
            this.f10218b = context;
            this.f10219c = fidoIn;
        }

        @Override // java.lang.Runnable
        public void run() {
            FidoCallback fidoCallback = this.f10217a;
            if (fidoCallback != null) {
                fidoCallback.onFidoProcess(FidoAppSDK.this.checkPolicyBase64(this.f10218b, this.f10219c));
            }
        }
    }

    /* renamed from: com.gmrz.appsdk.FidoAppSDK$j */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC4405j implements Runnable {

        /* renamed from: a */
        final /* synthetic */ FidoCallback f10221a;

        /* renamed from: b */
        final /* synthetic */ Context f10222b;

        /* renamed from: c */
        final /* synthetic */ FidoType f10223c;

        RunnableC4405j(FidoCallback fidoCallback, Context context, FidoType fidoType) {
            this.f10221a = fidoCallback;
            this.f10222b = context;
            this.f10223c = fidoType;
        }

        @Override // java.lang.Runnable
        public void run() {
            FidoCallback fidoCallback = this.f10221a;
            if (fidoCallback != null) {
                fidoCallback.onFidoProcess(FidoAppSDK.this.checkSupport(this.f10222b, this.f10223c));
            }
        }
    }

    /* renamed from: com.gmrz.appsdk.FidoAppSDK$k */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC4406k implements Runnable {

        /* renamed from: a */
        final /* synthetic */ FidoCallback f10225a;

        /* renamed from: b */
        final /* synthetic */ Context f10226b;

        /* renamed from: c */
        final /* synthetic */ FidoIn f10227c;

        RunnableC4406k(FidoCallback fidoCallback, Context context, FidoIn fidoIn) {
            this.f10225a = fidoCallback;
            this.f10226b = context;
            this.f10227c = fidoIn;
        }

        @Override // java.lang.Runnable
        public void run() {
            FidoCallback fidoCallback = this.f10225a;
            if (fidoCallback != null) {
                fidoCallback.onFidoProcess(FidoAppSDK.this.checkNetSupport(this.f10226b, this.f10227c));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.gmrz.appsdk.FidoAppSDK$l */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4407l {

        /* renamed from: a */
        private static final FidoAppSDK f10229a = new FidoAppSDK(null);
    }

    /* synthetic */ FidoAppSDK(RunnableC4398c runnableC4398c) {
        this();
    }

    private void checkParamsInOfflineAuthMode(FidoIn fidoIn) {
        if (!TextUtils.isEmpty(fidoIn.getTransType())) {
            if (fidoIn.getAuthType() != null && fidoIn.getAuthType().length != 0) {
                if (TextUtils.isEmpty(fidoIn.getUsername())) {
                    throw new IllegalArgumentException("in offline auth mode username can not set null");
                }
                return;
            }
            throw new IllegalArgumentException("in offline auth mode authType can not set null");
        }
        throw new IllegalArgumentException("in offline auth mode transType can not set null");
    }

    private boolean checkPermission(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    private FidoReInfo checkProcess(Context context, FidoIn fidoIn) {
        FidoReInfo fidoReInfo = new FidoReInfo();
        if (fidoIn != null && !TextUtils.isEmpty(fidoIn.getFidoIn())) {
            ProcessTask processTask = new ProcessTask();
            fidoIn.setCheckpolicy(true);
            fidoIn.setFidoParam(this.mFidoparam);
            FidoOut m15767a = processTask.m15767a(context, fidoIn);
            FidoStatus fidoStatus = m15767a.fidoStatus;
            if (fidoStatus == FidoStatus.SUCCESS) {
                fidoReInfo.setReInfo(fidoStatus, m15767a.fidoResponse);
            } else {
                fidoReInfo.setStatus(fidoStatus);
            }
            return fidoReInfo;
        }
        fidoReInfo.setStatus(FidoStatus.PROTOCOL_ERROR);
        return fidoReInfo;
    }

    private String convertType(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 50) {
            if (str.equals("2")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 1573) {
            if (str.equals("16")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 1726) {
            if (hashCode == 48695 && str.equals("128")) {
                c = 3;
            }
            c = 65535;
        } else {
            if (str.equals("64")) {
                c = 2;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return "FINGERPRINT";
            case 1:
                return "FACE";
            case 2:
                return "IRIS";
            case 3:
                return "GESTURE";
            default:
                return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x00bd, code lost:
        if ((new org.json.JSONTokener(r0.getString(0)).nextValue() instanceof org.json.JSONObject) != false) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.gmrz.appsdk.FidoReInfo getCheckNetSupportResult(java.lang.String r11, java.lang.String r12, java.util.HashMap<java.lang.String, java.util.HashSet<java.lang.String>> r13, com.gmrz.appsdk.FidoReInfo r14) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.appsdk.FidoAppSDK.getCheckNetSupportResult(java.lang.String, java.lang.String, java.util.HashMap, com.gmrz.appsdk.FidoReInfo):com.gmrz.appsdk.FidoReInfo");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private FidoStatus getCorrespondServerSupport(String str, FidoReInfo fidoReInfo) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        switch (hashCode) {
            case MediaRecorderBase.VIDEO_BITRATE_MEDIUM /* 1536 */:
                if (str.equals("00")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1537:
                if (str.equals("01")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1538:
                if (str.equals("02")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1539:
                if (str.equals("03")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                switch (hashCode) {
                    case 1567:
                        if (str.equals("10")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1568:
                        if (str.equals("11")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1569:
                        if (str.equals("12")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1570:
                        if (str.equals("13")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1571:
                        if (str.equals("14")) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
        }
        switch (c) {
            case 0:
                return fidoReInfo.getFpStatus();
            case 1:
                return fidoReInfo.getIrisStatus();
            case 2:
                return fidoReInfo.getFidoFaceStatus();
            case 3:
                return fidoReInfo.getFidoGestureStatus();
            case 4:
                return fidoReInfo.getFaceStatus();
            case 5:
                return fidoReInfo.getVoiceStatus();
            case 6:
                return fidoReInfo.getRealNameStatus();
            case 7:
                return fidoReInfo.getRealNameFaceStatus();
            case '\b':
                return fidoReInfo.getScanQRCodeStatus();
            default:
                return FidoStatus.INVALID_PARAM;
        }
    }

    public static FidoAppSDK getInstance() {
        return C4407l.f10229a;
    }

    private static boolean isServerMessageValid(String str) {
        try {
            return ((Integer) new JSONObject(str).get("statusCode")).intValue() == 1200;
        } catch (JSONException e) {
            Logger.m15757e(TAG, e.getMessage());
            return false;
        }
    }

    private void parseCheckDevAbilityResp(String str) {
        this.mAAIDsServerSupportLogin = new HashMap<>();
        this.mAAIDsServerSupportTrade = new HashMap<>();
        ArrayList<CheckDeviceAbility> arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                CheckDeviceAbility checkDeviceAbility = new CheckDeviceAbility();
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                checkDeviceAbility.authType = optJSONObject.optString("authType");
                checkDeviceAbility.transType = optJSONObject.optString("transType");
                ArrayList arrayList2 = new ArrayList();
                checkDeviceAbility.uafRequest = arrayList2;
                arrayList2.add((String) optJSONObject.optJSONArray("uafRequest").get(0));
                arrayList.add(checkDeviceAbility);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Logger.wtf(TAG, "parse server response failed or has no uafRequest filed");
        }
        if (arrayList.size() == 0) {
            Logger.wtf(TAG, "parse server response failed or has no uafRequest filed");
            return;
        }
        for (CheckDeviceAbility checkDeviceAbility2 : arrayList) {
            if (checkDeviceAbility2.transType.equals("00")) {
                HashSet<String> hashSet = new HashSet<>();
                try {
                    JSONArray jSONArray2 = new JSONArray(checkDeviceAbility2.uafRequest.get(0));
                    if (jSONArray2.length() > 0) {
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            JSONArray jSONArray3 = jSONArray2.getJSONObject(i2).getJSONObject("policy").getJSONArray("accepted");
                            for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                                hashSet.add((String) jSONArray3.getJSONArray(i3).getJSONObject(0).getJSONArray("aaid").get(0));
                            }
                        }
                    }
                    this.mAAIDsServerSupportLogin.put(checkDeviceAbility2.authType, hashSet);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            if (checkDeviceAbility2.transType.equals("01")) {
                HashSet<String> hashSet2 = new HashSet<>();
                try {
                    JSONArray jSONArray4 = new JSONArray(checkDeviceAbility2.uafRequest.get(0));
                    if (jSONArray4.length() > 0) {
                        for (int i4 = 0; i4 < jSONArray4.length(); i4++) {
                            JSONArray jSONArray5 = jSONArray4.getJSONObject(i4).getJSONObject("policy").getJSONArray("accepted");
                            for (int i5 = 0; i5 < jSONArray5.length(); i5++) {
                                hashSet2.add((String) jSONArray5.getJSONArray(i5).getJSONObject(0).getJSONArray("aaid").get(0));
                            }
                        }
                    }
                    this.mAAIDsServerSupportTrade.put(checkDeviceAbility2.authType, hashSet2);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    private void parseCheckDevAbilityResp_V1(String str, String str2) {
        this.mAAIDsServerSupportLogin = new HashMap<>();
        HashSet<String> hashSet = new HashSet<>();
        try {
            JSONArray jSONArray = new JSONArray(str2);
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONArray jSONArray2 = new JSONArray(jSONArray.get(i).toString()).getJSONObject(0).getJSONObject("policy").getJSONArray("accepted");
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        hashSet.add((String) jSONArray2.getJSONArray(i2).getJSONObject(0).getJSONArray("aaid").get(0));
                    }
                }
            }
            this.mAAIDsServerSupportLogin.put(str, hashSet);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private FidoReInfo pluginIsIntergraded(String str, FidoReInfo fidoReInfo) {
        char c;
        fidoReInfo.setStatus(FidoStatus.FAILED);
        str.hashCode();
        switch (str.hashCode()) {
            case 1567:
                if (str.equals("10")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1568:
                if (str.equals("11")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1569:
                if (str.equals("12")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1570:
                if (str.equals("13")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1571:
                if (str.equals("14")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                for (UACPlugin uACPlugin : this.uacPlugins) {
                    if (uACPlugin == UACPlugin.REMOTEFACEPLUGIN) {
                        fidoReInfo.setStatus(FidoStatus.SUCCESS);
                    }
                }
                break;
            case 1:
                for (UACPlugin uACPlugin2 : this.uacPlugins) {
                    if (uACPlugin2 == UACPlugin.REMOTEFACEPLUGIN) {
                        fidoReInfo.setStatus(FidoStatus.SUCCESS);
                    }
                }
                break;
            case 2:
                fidoReInfo.setStatus(FidoStatus.SUCCESS);
                break;
            case 3:
                for (UACPlugin uACPlugin3 : this.uacPlugins) {
                    if (uACPlugin3 == UACPlugin.REMOTEFACEPLUGIN) {
                        fidoReInfo.setStatus(FidoStatus.SUCCESS);
                    }
                }
                break;
            case 4:
                fidoReInfo.setStatus(FidoStatus.SUCCESS);
                break;
        }
        return fidoReInfo;
    }

    public FidoReInfo checkNetSupport(Context context, FidoIn fidoIn) {
        FidoReInfo fidoReInfo = new FidoReInfo();
        if (fidoIn != null && !TextUtils.isEmpty(fidoIn.getCheckDeviceAbilityServerResp())) {
            this.transType = fidoIn.getTransType();
            String[] authType = fidoIn.getAuthType();
            if (authType != null && authType.length != 0) {
                String checkDeviceAbilityServerResp = fidoIn.getCheckDeviceAbilityServerResp();
                this.serverRespTemp = checkDeviceAbilityServerResp;
                if (!TextUtils.isEmpty(checkDeviceAbilityServerResp)) {
                    fidoReInfo.setNetStatus(true);
                    HashMap<String, HashSet<String>> clientDiscovery = DiscoveryUtil.clientDiscovery(context);
                    if (clientDiscovery != null && clientDiscovery.size() != 0) {
                        for (Map.Entry<String, HashSet<String>> entry : clientDiscovery.entrySet()) {
                            HashSet<String> hashSet = new HashSet<>();
                            Iterator<String> it = entry.getValue().iterator();
                            while (it.hasNext()) {
                                hashSet.add(it.next().split("_")[0]);
                            }
                            clientDiscovery.put(entry.getKey(), hashSet);
                        }
                        if (clientDiscovery.size() == 0) {
                            Noter.m15736a(context, Record.OPERATION.CheckNetSupport, Record.ExcType.GET_INFO_FAILED, "client discovery result is null", null, SnGenerator.m15726b());
                            Logger.wtf(TAG, "Get DISCOVERY result from SharedPreference is null");
                            return fidoReInfo;
                        } else if (authType.length > 1) {
                            HashMap<String, FidoStatus[]> hashMap = new HashMap<>();
                            FidoReInfo fidoReInfo2 = fidoReInfo;
                            for (String str : authType) {
                                fidoReInfo2 = getCheckNetSupportResult(str, this.transType, clientDiscovery, fidoReInfo2);
                                hashMap.put(str, new FidoStatus[]{fidoReInfo2.getStatus(), getCorrespondServerSupport(str, fidoReInfo2)});
                            }
                            fidoReInfo2.setCheckNetSupportResults(hashMap);
                            return fidoReInfo2;
                        } else {
                            return getCheckNetSupportResult(authType[0], this.transType, clientDiscovery, fidoReInfo);
                        }
                    }
                    Noter.m15736a(context, Record.OPERATION.CheckNetSupport, Record.ExcType.GET_INFO_FAILED, "client discovery result is null", null, SnGenerator.m15726b());
                    Logger.wtf(TAG, "Stash client discovery result is null error");
                    return fidoReInfo;
                }
                fidoReInfo.setNetStatus(false);
                Noter.m15736a(context, Record.OPERATION.CheckNetSupport, Record.ExcType.GET_INFO_FAILED, "Server response is null", null, SnGenerator.m15726b());
                Log.wtf(TAG, "Server response is null or illegal");
                return fidoReInfo;
            }
            Noter.m15736a(context, Record.OPERATION.CheckNetSupport, Record.ExcType.PARAM_ERROR, "PARAM_ERROR is error", null, SnGenerator.m15726b());
            fidoReInfo.setStatus(FidoStatus.INVALID_PARAM);
            return fidoReInfo;
        }
        Noter.m15736a(context, Record.OPERATION.CheckPolicy, Record.ExcType.PARAM_ERROR, "PARAM_ERROR is error", null, SnGenerator.m15726b());
        fidoReInfo.setStatus(FidoStatus.PROTOCOL_ERROR);
        return fidoReInfo;
    }

    public void checkNetSupportAsync(Context context, FidoIn fidoIn, FidoCallback<FidoReInfo> fidoCallback) {
        String str = TAG;
        Logger.m15757e(str, "checkSupportAsync context: " + context + " , FidoIn: " + fidoIn + " , FidoCallback: " + fidoCallback);
        new Thread(new RunnableC4406k(fidoCallback, context, fidoIn)).start();
    }

    public void checkNetSupportAsyncBase64(Context context, FidoIn fidoIn, FidoCallback<FidoReInfo> fidoCallback) {
        String str = TAG;
        Logger.m15757e(str, "checkSupportAsyncBase64 context: " + context + " , FidoIn: " + fidoIn + " , FidoCallback: " + fidoCallback);
        new Thread(new RunnableC4396a(fidoCallback, context, fidoIn)).start();
    }

    public FidoReInfo checkNetSupportBase64(Context context, FidoIn fidoIn) {
        FidoReInfo fidoReInfo = new FidoReInfo();
        fidoReInfo.status = FidoStatus.FAILED;
        try {
            JSONArray jSONArray = new JSONArray(fidoIn.getCheckDeviceAbilityServerResp());
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                optJSONObject.optJSONArray("uafRequest").put(0, Base64Util.decodeToString(optJSONObject.optJSONArray("uafRequest").optString(0)));
                jSONArray.put(i, optJSONObject);
            }
            fidoIn.setCheckDeviceAbilityServerResp(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
            return checkNetSupport(context, fidoIn);
        } catch (Exception e) {
            e.printStackTrace();
            return fidoReInfo;
        }
    }

    public FidoReInfo checkPolicy(Context context, FidoIn fidoIn) {
        Logger.m15757e(TAG, "checkPolicy::");
        new FidoReInfo();
        FidoReInfo checkProcess = checkProcess(context, fidoIn);
        if (checkProcess.getStatus() != FidoStatus.SUCCESS) {
            Noter.m15736a(context, Record.OPERATION.CheckPolicy, Record.ExcType.UNKNOWN, checkProcess.getStatus().toString(), null, SnGenerator.m15726b());
            checkProcess.setStatus(FidoStatus.FAILED);
        }
        return checkProcess;
    }

    public void checkPolicyAsync(Context context, FidoIn fidoIn, FidoCallback<FidoReInfo> fidoCallback) {
        String str = TAG;
        Logger.m15757e(str, "checkPolicyAsync context: " + context + " , FidoRequest: " + fidoIn + " ,FidoCallback: " + fidoCallback);
        new Thread(new RunnableC4403h(fidoCallback, context, fidoIn)).start();
    }

    public void checkPolicyAsyncBase64(Context context, FidoIn fidoIn, FidoCallback<FidoReInfo> fidoCallback) {
        new Thread(new RunnableC4404i(fidoCallback, context, fidoIn)).start();
    }

    public FidoReInfo checkPolicyBase64(Context context, FidoIn fidoIn) {
        FidoReInfo fidoReInfo = new FidoReInfo();
        FidoStatus fidoStatus = FidoStatus.FAILED;
        fidoReInfo.status = fidoStatus;
        try {
            fidoIn.setFidoIn(Base64Util.decodeToString(fidoIn.getFidoIn()));
            fidoReInfo = checkProcess(context, fidoIn);
            if (fidoReInfo.getStatus() != FidoStatus.SUCCESS) {
                Noter.m15736a(context, Record.OPERATION.CheckPolicy, Record.ExcType.UNKNOWN, fidoReInfo.getStatus().toString(), null, SnGenerator.m15726b());
                fidoReInfo.setStatus(fidoStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fidoReInfo;
    }

    public FidoReInfo checkSupport(Context context, FidoType fidoType) {
        String str;
        String str2 = TAG;
        Logger.m15757e(str2, "checkMfacInstall context: " + context + " , FidoType: " + fidoType);
        FidoReInfo fidoReInfo = new FidoReInfo();
        fidoReInfo.status = FidoStatus.FAILED;
        fidoReInfo.setFidoMode(FidoMode.UNKNOWN);
        if (context != null && fidoType != null) {
            switch (C4397b.f10192a[fidoType.ordinal()]) {
                case 1:
                    str = "2";
                    break;
                case 2:
                    str = "64";
                    break;
                case 3:
                    str = "16";
                    break;
                case 4:
                    str = "128";
                    break;
                default:
                    str = null;
                    break;
            }
            if (Compatibility.isHuawei() && !HwUtil.verifyAuth(UACUtil.getFacetId(context), HwUtil.getPackageName(context))) {
                Noter.m15736a(context, Record.OPERATION.CheckPolicy, Record.ExcType.NO_SUITABLE_AUTHENTICATOR, "huawei whitelist", null, SnGenerator.m15726b());
                return fidoReInfo;
            }
            HashSet<String> hashSet = DiscoveryUtil.clientDiscovery(context).get(str);
            if (hashSet != null && hashSet.size() != 0) {
                Iterator<String> it = hashSet.iterator();
                boolean z = false;
                while (true) {
                    if (it.hasNext()) {
                        String next = it.next();
                        if (!TextUtils.isEmpty(next)) {
                            z = true;
                            String str3 = TAG;
                            Log.e(str3, "iterator:" + next);
                            if (!next.contains("004A#01AE") && !next.contains("001A#3333") && !next.contains("004A#01AA") && !next.contains("004A#01AD") && !next.contains("004A#01A0") && !next.contains("004A#01AB")) {
                                fidoReInfo.setFidoMode(FidoMode.FIDO);
                            } else {
                                fidoReInfo.setFidoMode(FidoMode.KeyStore);
                            }
                        }
                    }
                }
                if (!z) {
                    Noter.m15736a(context, Record.OPERATION.CheckPolicy, Record.ExcType.UNKNOWN, "not support", null, SnGenerator.m15726b());
                    return fidoReInfo;
                }
                fidoReInfo.status = FidoStatus.SUCCESS;
                return fidoReInfo;
            }
            Noter.m15736a(context, Record.OPERATION.CheckPolicy, Record.ExcType.UNKNOWN, "not support", null, SnGenerator.m15726b());
            return fidoReInfo;
        }
        Noter.m15736a(context, Record.OPERATION.CheckPolicy, Record.ExcType.PARAM_ERROR, "PARAM_ERROR is null", null, SnGenerator.m15726b());
        fidoReInfo.setStatus(FidoStatus.INVALID_PARAM);
        return fidoReInfo;
    }

    public void checkSupportAsync(Context context, FidoType fidoType, FidoCallback<FidoReInfo> fidoCallback) {
        String str = TAG;
        Logger.m15757e(str, "checkMfacInstallAsync Activity: " + context + " , FidoType: " + fidoType + " , FidoCallback: " + fidoCallback);
        new Thread(new RunnableC4405j(fidoCallback, context, fidoType)).start();
    }

    public FidoOut clientDiscover(Context context, IAppSDK.ClientLocation clientLocation) {
        Intent intent = new Intent();
        new FidoOut();
        intent.putExtra("UAFIntentType", UAFIntentType.DISCOVER.toString());
        FidoOut process = (clientLocation == IAppSDK.ClientLocation.LOCAL_CLIENT ? new UafLocalSDKProxy() : new UafAppSDKProxy()).process(context, intent);
        if (process == null) {
            FidoOut fidoOut = new FidoOut();
            fidoOut.fidoStatus = FidoStatus.FAILED;
            return fidoOut;
        }
        return process;
    }

    @SuppressLint({"MissingPermission"})
    public FidoReInfo getDeviceInfo(Context context, String str) {
        FidoReInfo fidoReInfo = new FidoReInfo();
        if (context == null) {
            fidoReInfo.setStatus(FidoStatus.INVALID_PARAM);
            return fidoReInfo;
        } else if (TextUtils.isEmpty(str)) {
            fidoReInfo.setStatus(FidoStatus.INVALID_PARAM);
            return fidoReInfo;
        } else {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("deviceID", str);
                jSONObject.put("deviceName", Build.MODEL);
                jSONObject.put("deviceAliasName", Build.DEVICE);
                jSONObject.put("deviceType", Build.BRAND);
                jSONObject.put("osVersion", Build.VERSION.SDK_INT);
                jSONObject.put("osType", "android");
                jSONObject.put("deviceisRoot", ThreatDetector.isDeviceRooted());
                jSONObject.put("deviceVersion", Build.DISPLAY);
                if (jSONObject instanceof JSONObject) {
                    NBSJSONObjectInstrumentation.toString(jSONObject);
                } else {
                    jSONObject.toString();
                }
                fidoReInfo.setDeviceInfo(jSONObject);
                fidoReInfo.setStatus(FidoStatus.SUCCESS);
            } catch (JSONException e) {
                String str2 = TAG;
                Logger.m15757e(str2, "getDeviceInfo is error: " + e.getMessage());
            }
            return fidoReInfo;
        }
    }

    public FidoReInfo getDiscoveryData(Context context) {
        FidoReInfo fidoReInfo = new FidoReInfo();
        fidoReInfo.status = FidoStatus.FAILED;
        try {
            JSONArray clientDiscoveryDetailInfo = DiscoveryUtil.clientDiscoveryDetailInfo(context);
            if (clientDiscoveryDetailInfo.length() > 0) {
                fidoReInfo.status = FidoStatus.SUCCESS;
                fidoReInfo.discoveryData = !(clientDiscoveryDetailInfo instanceof JSONArray) ? clientDiscoveryDetailInfo.toString() : NBSJSONArrayInstrumentation.toString(clientDiscoveryDetailInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fidoReInfo;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public Map<String, Map<Constant.SecurityLevel, Constant.SecurityLevelInfo>> getSecurityLevel(Context context) {
        KeyASecurityType keyASecurityType;
        boolean z;
        char c;
        char c2;
        HashMap<String, HashSet<String>> clientDiscovery = DiscoveryUtil.clientDiscovery(context);
        TreeMap treeMap = new TreeMap();
        String uuid = UUID.randomUUID().toString();
        if (Build.VERSION.SDK_INT >= 23) {
            z = FpUtil.checkSupport(context, uuid);
            keyASecurityType = FpUtil.getASecurityLevel(uuid);
        } else {
            keyASecurityType = null;
            z = false;
        }
        List asList = Arrays.asList("004A#01AE", "001A#3333", "004A#01AA", "004A#01AB", "004A#01AC", "004A#01AD", "004A#01A0", "004A#01AF");
        for (Map.Entry<String, HashSet<String>> entry : clientDiscovery.entrySet()) {
            TreeMap treeMap2 = new TreeMap();
            String key = entry.getKey();
            key.hashCode();
            int hashCode = key.hashCode();
            if (hashCode == 50) {
                if (key.equals("2")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode == 1573) {
                if (key.equals("16")) {
                    c = 1;
                }
                c = 65535;
            } else if (hashCode != 1726) {
                if (hashCode == 48695 && key.equals("128")) {
                    c = 3;
                }
                c = 65535;
            } else {
                if (key.equals("64")) {
                    c = 2;
                }
                c = 65535;
            }
            switch (c) {
                case 0:
                    Constant.SecurityLevel securityLevel = Constant.SecurityLevel.NoAttestation;
                    Constant.SecurityLevelInfo securityLevelInfo = Constant.SecurityLevelInfo.ShouldConfig;
                    treeMap2.put(securityLevel, securityLevelInfo);
                    Constant.SecurityLevel securityLevel2 = Constant.SecurityLevel.SoftwareAttestation;
                    Constant.SecurityLevelInfo securityLevelInfo2 = Constant.SecurityLevelInfo.NotSupport;
                    treeMap2.put(securityLevel2, securityLevelInfo2);
                    Constant.SecurityLevel securityLevel3 = Constant.SecurityLevel.HardwareAttestation;
                    treeMap2.put(securityLevel3, securityLevelInfo2);
                    treeMap2.put(Constant.SecurityLevel.TEE, securityLevelInfo2);
                    if (z && keyASecurityType == KeyASecurityType.SOFTWARE && FpUtil.verifySecure(uuid, false)) {
                        treeMap2.put(securityLevel2, securityLevelInfo);
                    } else if (z && keyASecurityType == KeyASecurityType.TEE && FpUtil.verifySecure(uuid, true)) {
                        treeMap2.put(securityLevel3, securityLevelInfo);
                    }
                    Iterator<String> it = entry.getValue().iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        if (!TextUtils.isEmpty(next)) {
                            String str = next.split("_")[0];
                            str.hashCode();
                            switch (str.hashCode()) {
                                case -670122701:
                                    if (str.equals("001A#3333")) {
                                        c2 = 0;
                                        break;
                                    }
                                    c2 = 65535;
                                    break;
                                case 1992297478:
                                    if (str.equals("004A#01A0")) {
                                        c2 = 1;
                                        break;
                                    }
                                    c2 = 65535;
                                    break;
                                case 1992297495:
                                    if (str.equals("004A#01AA")) {
                                        c2 = 2;
                                        break;
                                    }
                                    c2 = 65535;
                                    break;
                                case 1992297498:
                                    if (str.equals("004A#01AD")) {
                                        c2 = 3;
                                        break;
                                    }
                                    c2 = 65535;
                                    break;
                                case 1992297499:
                                    if (str.equals("004A#01AE")) {
                                        c2 = 4;
                                        break;
                                    }
                                    c2 = 65535;
                                    break;
                                default:
                                    c2 = 65535;
                                    break;
                            }
                            switch (c2) {
                                case 0:
                                case 2:
                                    treeMap2.put(Constant.SecurityLevel.NoAttestation, Constant.SecurityLevelInfo.Support);
                                    break;
                                case 1:
                                    treeMap2.put(Constant.SecurityLevel.SoftwareAttestation, Constant.SecurityLevelInfo.Support);
                                    break;
                                case 3:
                                    treeMap2.put(Constant.SecurityLevel.HardwareAttestation, Constant.SecurityLevelInfo.Support);
                                    break;
                                case 4:
                                    treeMap2.put(Constant.SecurityLevel.HardwareAttestation, Constant.SecurityLevelInfo.Support);
                                    break;
                                default:
                                    treeMap2.put(Constant.SecurityLevel.TEE, Constant.SecurityLevelInfo.Support);
                                    break;
                            }
                        }
                    }
                    treeMap.put(convertType(entry.getKey()), treeMap2);
                    break;
                case 1:
                    treeMap2.put(Constant.SecurityLevel.NoAttestation, Constant.SecurityLevelInfo.ShouldConfig);
                    Constant.SecurityLevel securityLevel4 = Constant.SecurityLevel.SoftwareAttestation;
                    Constant.SecurityLevelInfo securityLevelInfo3 = Constant.SecurityLevelInfo.NotSupport;
                    treeMap2.put(securityLevel4, securityLevelInfo3);
                    treeMap2.put(Constant.SecurityLevel.HardwareAttestation, securityLevelInfo3);
                    treeMap2.put(Constant.SecurityLevel.TEE, securityLevelInfo3);
                    Iterator<String> it2 = entry.getValue().iterator();
                    while (it2.hasNext()) {
                        String next2 = it2.next();
                        if (!TextUtils.isEmpty(next2)) {
                            String str2 = next2.split("_")[0];
                            if (!asList.contains(str2)) {
                                treeMap2.put(Constant.SecurityLevel.TEE, Constant.SecurityLevelInfo.Support);
                            } else if (str2.equalsIgnoreCase("004A#01AB")) {
                                treeMap2.put(Constant.SecurityLevel.NoAttestation, Constant.SecurityLevelInfo.Support);
                            }
                        }
                    }
                    treeMap.put(convertType(entry.getKey()), treeMap2);
                    break;
                case 2:
                    Constant.SecurityLevel securityLevel5 = Constant.SecurityLevel.NoAttestation;
                    Constant.SecurityLevelInfo securityLevelInfo4 = Constant.SecurityLevelInfo.NotSupport;
                    treeMap2.put(securityLevel5, securityLevelInfo4);
                    treeMap2.put(Constant.SecurityLevel.SoftwareAttestation, securityLevelInfo4);
                    treeMap2.put(Constant.SecurityLevel.HardwareAttestation, securityLevelInfo4);
                    treeMap2.put(Constant.SecurityLevel.TEE, securityLevelInfo4);
                    Iterator<String> it3 = entry.getValue().iterator();
                    while (it3.hasNext()) {
                        String next3 = it3.next();
                        if (!TextUtils.isEmpty(next3) && !asList.contains(next3.split("_")[0])) {
                            treeMap2.put(Constant.SecurityLevel.TEE, Constant.SecurityLevelInfo.Support);
                        }
                    }
                    treeMap.put(convertType(entry.getKey()), treeMap2);
                    break;
                case 3:
                    treeMap2.put(Constant.SecurityLevel.NoAttestation, Constant.SecurityLevelInfo.ShouldConfig);
                    Constant.SecurityLevel securityLevel6 = Constant.SecurityLevel.SoftwareAttestation;
                    Constant.SecurityLevelInfo securityLevelInfo5 = Constant.SecurityLevelInfo.NotSupport;
                    treeMap2.put(securityLevel6, securityLevelInfo5);
                    treeMap2.put(Constant.SecurityLevel.HardwareAttestation, securityLevelInfo5);
                    treeMap2.put(Constant.SecurityLevel.TEE, securityLevelInfo5);
                    Iterator<String> it4 = entry.getValue().iterator();
                    while (it4.hasNext()) {
                        String next4 = it4.next();
                        if (!TextUtils.isEmpty(next4)) {
                            String str3 = next4.split("_")[0];
                            if (str3.equalsIgnoreCase("004A#01AC") || str3.equalsIgnoreCase("004A#01AF")) {
                                treeMap2.put(Constant.SecurityLevel.NoAttestation, Constant.SecurityLevelInfo.Support);
                            }
                        }
                    }
                    treeMap.put(convertType(entry.getKey()), treeMap2);
                    break;
            }
        }
        return treeMap;
    }

    public FidoReInfo getSystemDeviceID(Context context) {
        FidoReInfo deviceInfo = getDeviceInfo(context);
        FidoStatus fidoStatus = deviceInfo.status;
        FidoStatus fidoStatus2 = FidoStatus.SUCCESS;
        if (fidoStatus != fidoStatus2) {
            return deviceInfo;
        }
        JSONObject deviceInfo2 = deviceInfo.getDeviceInfo();
        deviceInfo.setSystemDeviceId(SignUtil.getSHA256(!(deviceInfo2 instanceof JSONObject) ? deviceInfo2.toString() : NBSJSONObjectInstrumentation.toString(deviceInfo2)));
        deviceInfo.setStatus(fidoStatus2);
        return deviceInfo;
    }

    public FidoReInfo getUniquePsuedoID(Context context) {
        FidoReInfo fidoReInfo = new FidoReInfo();
        if (context == null) {
            fidoReInfo.setStatus(FidoStatus.INVALID_PARAM);
            return fidoReInfo;
        }
        String string = context.getSharedPreferences("device_id", 0).getString("device_id", null);
        if (TextUtils.isEmpty(string)) {
            return setUniquePsuedoID(context);
        }
        fidoReInfo.setUniqueID(string);
        fidoReInfo.setStatus(FidoStatus.SUCCESS);
        return fidoReInfo;
    }

    public String getVersion() {
        return "5.4.2.a";
    }

    public FidoReInfo initFido(Context context) {
        String str = TAG;
        Logger.m15757e(str, "initFido Context: " + context);
        return initFido(context, null);
    }

    public void initFidoAsync(Context context, FidoCallback<FidoReInfo> fidoCallback) {
        String str = TAG;
        Logger.m15757e(str, "initFidoAsync Context: " + context + " , FidoCallback: " + fidoCallback);
        new Thread(new RunnableC4398c(fidoCallback, context)).start();
    }

    public boolean isFingerSetsChange(Context context, String str) {
        return FingerprintSetUtil.getOurInstance().isFingerprintSetChanged(context, str);
    }

    public FidoReInfo isHaveFinger(Context context) {
        String str = TAG;
        Logger.m15757e(str, "isHaveFinger context: " + context);
        FidoReInfo fidoReInfo = new FidoReInfo();
        if (context == null) {
            fidoReInfo.setStatus(FidoStatus.INVALID_PARAM);
            return fidoReInfo;
        } else if (!checkPermission(context, "android.permission.USE_FINGERPRINT")) {
            fidoReInfo.setStatus(FidoStatus.NO_PERMISSION);
            return fidoReInfo;
        } else {
            int findHasEnrolledFingerprints = FingerprintUtil.findHasEnrolledFingerprints(context);
            if (1 == findHasEnrolledFingerprints) {
                fidoReInfo.setStatus(FidoStatus.SUCCESS);
            } else if (2 == findHasEnrolledFingerprints) {
                fidoReInfo.setStatus(FidoStatus.FAILED);
            } else {
                fidoReInfo.setStatus(FidoStatus.FAILED);
            }
            return fidoReInfo;
        }
    }

    @SuppressLint({"ApplySharedPref"})
    public FidoReInfo process(Context context, FidoIn fidoIn) {
        SharedPreferences sharedPreferences;
        String str = TAG;
        Logger.m15757e(str, "process::");
        FidoReInfo fidoReInfo = new FidoReInfo();
        fidoReInfo.setStatus(FidoStatus.FAILED);
        Lock lock = mLock;
        if (!lock.tryLock()) {
            fidoReInfo.setStatus(FidoStatus.IS_BUSY);
            return fidoReInfo;
        }
        try {
            try {
                fidoIn.setCheckpolicy(false);
                fidoIn.setAuthAbilty(this.mAuthAbilty);
                fidoIn.setFidoParam(this.mFidoparam);
                String str2 = null;
                if (fidoIn.getOperationType() == null || fidoIn.getOperationType() != OperationHeader.OperationType.Dereg) {
                    sharedPreferences = null;
                } else {
                    Logger.m15756i(str, "*** deregister process: clear cached user info for offline authenticate ***");
                    str2 = fidoIn.getCacheFileName();
                    sharedPreferences = context.getSharedPreferences(str2, 0);
                }
                if (fidoIn.getOperationType() != null && fidoIn.isOfflineEnable() && fidoIn.getOperationType() == OperationHeader.OperationType.Auth) {
                    Logger.m15756i(str, "*** offline auth process ***");
                    checkParamsInOfflineAuthMode(fidoIn);
                    String cacheFileName = fidoIn.getCacheFileName();
                    SharedPreferences sharedPreferences2 = context.getSharedPreferences(cacheFileName, 0);
                    String string = sharedPreferences2.getString("cache", "");
                    if (TextUtils.isEmpty(string)) {
                        Logger.m15757e(str, "cached user info is null can not offline auth");
                        fidoReInfo.setStatus(FidoStatus.PROTOCOL_ERROR);
                        lock.unlock();
                        return fidoReInfo;
                    }
                    String m15719b = CryptoFileSuit.m15719b(context, cacheFileName);
                    if (TextUtils.isEmpty(m15719b)) {
                        Logger.m15757e(str, "get aes iv is null can not offline auth");
                        fidoReInfo.setStatus(FidoStatus.PROTOCOL_ERROR);
                        lock.unlock();
                        return fidoReInfo;
                    }
                    StrongBox strongBox = new StrongBox(cacheFileName);
                    strongBox.m15713a(new C4400e(this, fidoReInfo, sharedPreferences2, fidoIn, context));
                    if (Build.VERSION.SDK_INT >= 23) {
                        strongBox.m15714a(2, string, m15719b);
                    }
                    str2 = cacheFileName;
                }
                FidoOut m15767a = new ProcessTask().m15767a(context, fidoIn);
                FidoStatus fidoStatus = m15767a.fidoStatus;
                if (fidoStatus == FidoStatus.SUCCESS) {
                    fidoReInfo.setReInfo(fidoStatus, m15767a.fidoResponse);
                    if (sharedPreferences != null) {
                        Log.i(str, "cached user info for offline authenticate is cleared");
                        sharedPreferences.edit().clear().commit();
                        boolean m15723a = CryptoFileSuit.m15723a(context, str2);
                        Object[] objArr = new Object[1];
                        objArr[0] = m15723a ? "successful" : "failure";
                        Log.i(str, String.format("cached user info protect aes iv file clear %s", objArr));
                    }
                } else {
                    Noter.m15736a(context, Record.OPERATION.Process, Record.ExcType.UNKNOWN, fidoStatus.toString(), null, SnGenerator.m15726b());
                    fidoReInfo.setStatus(m15767a.fidoStatus);
                    String str3 = m15767a.fidoStatusMsg;
                    if (!TextUtils.isEmpty(str3)) {
                        fidoReInfo.setErrorMsg(str3);
                    }
                }
                lock.unlock();
                return fidoReInfo;
            } catch (Exception e) {
                e.printStackTrace();
                Logger.m15757e(TAG, "process exception: " + e.getMessage());
                fidoReInfo.setStatus(FidoStatus.FAILED);
                mLock.unlock();
                return fidoReInfo;
            }
        } catch (Throwable th) {
            mLock.unlock();
            throw th;
        }
    }

    public void processAsync(Context context, FidoIn fidoIn, FidoCallback<FidoReInfo> fidoCallback) {
        String str = TAG;
        Logger.m15757e(str, "processAsync context: " + context + " , FidoRequest: " + fidoIn + " , FidoCallback: " + fidoCallback);
        new Thread(new RunnableC4401f(fidoCallback, context, fidoIn)).start();
    }

    public void processAsyncBase64(Context context, FidoIn fidoIn, FidoCallback<FidoReInfo> fidoCallback) {
        String str = TAG;
        Logger.m15757e(str, "processAsync context: " + context + " , FidoRequest: " + fidoIn + " , FidoCallback: " + fidoCallback);
        new Thread(new RunnableC4402g(fidoCallback, context, fidoIn)).start();
    }

    public FidoReInfo processBase64(Context context, FidoIn fidoIn) {
        FidoReInfo fidoReInfo = new FidoReInfo();
        fidoReInfo.status = FidoStatus.FAILED;
        try {
            fidoIn.setFidoIn(Base64Util.decodeToString(fidoIn.getFidoIn()));
            fidoReInfo = process(context, fidoIn);
            if (fidoReInfo.getStatus() == FidoStatus.SUCCESS && !TextUtils.isEmpty(fidoReInfo.getMfacResponse())) {
                fidoReInfo.setMfacResponse(Base64.encodeToString(fidoReInfo.getMfacResponse().getBytes(StandardCharsets.UTF_8), 29));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fidoReInfo;
    }

    public String queryExceptionRecords(Context context, boolean z) {
        try {
            return Noter.m15735a(context, z);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public FidoReInfo setUniquePsuedoID(Context context) {
        FidoReInfo fidoReInfo = new FidoReInfo();
        if (context == null) {
            fidoReInfo.setStatus(FidoStatus.INVALID_PARAM);
            return fidoReInfo;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("device_id", 0);
        String trim = Compatibility.AesEncrypt(UUID.randomUUID().toString()).trim();
        if (sharedPreferences.edit().putString("device_id", trim).commit()) {
            fidoReInfo.setUniqueID(trim);
            fidoReInfo.setStatus(FidoStatus.SUCCESS);
        } else {
            fidoReInfo.setStatus(FidoStatus.FAILED);
        }
        return fidoReInfo;
    }

    public void wipeExceptionRecords(Context context) {
        Noter.m15737a(context);
    }

    private FidoAppSDK() {
        this.mFidoparam = new FidoParam();
        this.serverRespTemp = "";
    }

    public FidoReInfo initFido(Context context, FidoParam fidoParam) {
        String str = TAG;
        Logger.m15757e(str, "initFido Context:" + context + " , FidoParam:" + fidoParam);
        FidoReInfo fidoReInfo = new FidoReInfo();
        if (context == null) {
            fidoReInfo.setStatus(FidoStatus.INVALID_PARAM);
            Noter.m15736a(context, Record.OPERATION.Init, Record.ExcType.PARAM_ERROR, "initFido INVALID_PARAM", null, SnGenerator.m15726b());
            return fidoReInfo;
        } else if (Constant.CHECK_ROOT && (ThreatDetector.isDeviceRooted() || ThreatDetector.isHook(context))) {
            fidoReInfo.setStatus(FidoStatus.NOT_INITFIDO);
            return fidoReInfo;
        } else {
            FidoOut m15761a = new InitTask().m15761a(context, fidoParam);
            FidoStatus fidoStatus = m15761a.fidoStatus;
            if (fidoStatus == FidoStatus.SUCCESS) {
                this.mFidoparam.setLocation(m15761a.clientType);
                fidoReInfo.setStatus(m15761a.fidoStatus);
            } else {
                fidoReInfo.setStatus(fidoStatus);
                Noter.m15736a(context, Record.OPERATION.Init, Record.ExcType.UNKNOWN, "initFido failed", null, SnGenerator.m15726b());
            }
            return fidoReInfo;
        }
    }

    public void initFidoAsync(Context context, FidoParam fidoParam, Map<UACPlugin, IGmrzAdapter> map, FidoCallback<FidoReInfo> fidoCallback) {
        String str = TAG;
        Logger.m15757e(str, "initFidoAsync Context: " + context + " , FidoCallback: " + fidoCallback);
        new Thread(new RunnableC4399d(fidoCallback, context, fidoParam, map)).start();
    }

    public FidoReInfo initFido(Context context, FidoParam fidoParam, Map<UACPlugin, IGmrzAdapter> map) {
        new FidoReInfo().setStatus(FidoStatus.FAILED);
        FidoReInfo initFido = initFido(context, fidoParam);
        FidoStatus status = initFido.getStatus();
        FidoStatus fidoStatus = FidoStatus.SUCCESS;
        if (status != fidoStatus) {
            return initFido;
        }
        if (map == null) {
            Noter.m15736a(context, Record.OPERATION.Init, Record.ExcType.PARAM_ERROR, "initFido INVALID_PARAM", null, SnGenerator.m15726b());
            initFido.setStatus(FidoStatus.INVALID_PARAM);
            return initFido;
        }
        this.mAuthAbilty = map;
        if (map.size() > 0) {
            this.uacPlugins = this.mAuthAbilty.keySet();
        }
        initFido.setStatus(fidoStatus);
        return initFido;
    }

    private void parseCheckDevAbilityResp_V1(String str) {
        this.mAAIDsServerSupportLogin = new HashMap<>();
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = new JSONObject(jSONArray.getString(i));
                    String string = jSONObject.getString("authType");
                    HashSet<String> hashSet = new HashSet<>();
                    JSONArray jSONArray2 = jSONObject.getJSONArray("result").getJSONObject(0).getJSONObject("policy").getJSONArray("accepted");
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        hashSet.add((String) jSONArray2.getJSONArray(i2).getJSONObject(0).getJSONArray("aaid").get(0));
                    }
                    this.mAAIDsServerSupportLogin.put(string, hashSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"MissingPermission"})
    public FidoReInfo getDeviceInfo(Context context) {
        FidoReInfo fidoReInfo = new FidoReInfo();
        if (context == null) {
            fidoReInfo.setStatus(FidoStatus.INVALID_PARAM);
            return fidoReInfo;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("osType", "android");
            jSONObject.put("deviceType", Build.BRAND);
            jSONObject.put("deviceName", Build.MODEL);
            jSONObject.put("deviceAliasName", Build.DEVICE);
            jSONObject.put("androidID", Settings.System.getString(context.getContentResolver(), "android_id"));
            if (!TextUtils.isEmpty(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject))) {
                fidoReInfo.setDeviceInfo(jSONObject);
                fidoReInfo.setStatus(FidoStatus.SUCCESS);
            } else {
                fidoReInfo.setStatus(FidoStatus.FAILED);
            }
        } catch (JSONException e) {
            String str = TAG;
            Logger.m15757e(str, "getDeviceInfo is error: " + e.getMessage());
        }
        return fidoReInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.gmrz.appsdk.FidoAppSDK$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C4400e extends ProcessCallback {

        /* renamed from: a */
        final /* synthetic */ FidoReInfo f10201a;

        /* renamed from: b */
        final /* synthetic */ SharedPreferences f10202b;

        /* renamed from: c */
        final /* synthetic */ FidoIn f10203c;

        /* renamed from: d */
        final /* synthetic */ Context f10204d;

        C4400e(FidoAppSDK fidoAppSDK, FidoReInfo fidoReInfo, SharedPreferences sharedPreferences, FidoIn fidoIn, Context context) {
            this.f10201a = fidoReInfo;
            this.f10202b = sharedPreferences;
            this.f10203c = fidoIn;
            this.f10204d = context;
        }

        @Override // com.gmrz.fido.offline.ProcessCallback, com.gmrz.fido.offline.StrongBox.InterfaceC4438a
        /* renamed from: a */
        public void mo15711a(String str) {
            String format;
            if (!str.contains("@")) {
                Logger.wtf(FidoAppSDK.TAG, "cached user info decrypt failed");
                this.f10201a.setStatus(FidoStatus.PROTOCOL_ERROR);
                return;
            }
            String[] split = str.split("@");
            String str2 = split[0];
            String str3 = split[1];
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                String encodeToString = Base64.encodeToString(Converter.m15725a(str3), 11);
                String string = this.f10202b.getString("challenge", "");
                if (TextUtils.isEmpty(string)) {
                    this.f10201a.setStatus(FidoStatus.PROTOCOL_ERROR);
                    Logger.wtf(FidoAppSDK.TAG, "challenge of cached user info is null");
                    return;
                }
                String offlineAuthTransText = this.f10203c.getOfflineAuthTransText();
                if (TextUtils.isEmpty(offlineAuthTransText)) {
                    format = String.format(this.f10204d.getString(R.string.offline_auth_msg), "", string, str2, encodeToString);
                } else {
                    format = String.format(this.f10204d.getString(R.string.offline_auth_trans_msg), "", string, str2, encodeToString, offlineAuthTransText);
                }
                Logger.m15756i(FidoAppSDK.TAG, String.format("@@ build msg for offline authentication @@\n%s", format));
                this.f10203c.setFidoIn(format);
                return;
            }
            this.f10201a.setStatus(FidoStatus.PROTOCOL_ERROR);
            Logger.wtf(FidoAppSDK.TAG, "aaid or keyID of cached user info is null");
        }

        @Override // com.gmrz.fido.offline.StrongBox.InterfaceC4438a
        /* renamed from: a */
        public void mo15712a(Exception exc) {
            exc.printStackTrace();
            Logger.wtf(FidoAppSDK.TAG, "cached user info decrypt failed");
            this.f10201a.setStatus(FidoStatus.FAILED);
        }
    }
}
