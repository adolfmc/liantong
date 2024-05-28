package com.bytedance.applog;

import android.accounts.Account;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bytedance.applog.network.INetworkClient;
import com.bytedance.mpaas.IEncryptor;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class InitConfig {

    /* renamed from: A */
    public String f8271A;

    /* renamed from: B */
    public Map<String, Object> f8272B;

    /* renamed from: C */
    public Account f8273C;

    /* renamed from: D */
    public boolean f8274D;

    /* renamed from: F */
    public INetworkClient f8276F;

    /* renamed from: H */
    public boolean f8278H;

    /* renamed from: P */
    public ISensitiveInfoProvider f8286P;

    /* renamed from: S */
    public String f8289S;

    /* renamed from: T */
    public boolean f8290T;

    /* renamed from: a */
    public String f8292a;

    /* renamed from: c */
    public String f8294c;

    /* renamed from: d */
    public String f8295d;

    /* renamed from: e */
    public IEncryptor f8296e;

    /* renamed from: f */
    public String f8297f;

    /* renamed from: g */
    public String f8298g;

    /* renamed from: h */
    public ILogger f8299h;

    /* renamed from: i */
    public String f8300i;

    /* renamed from: j */
    public String f8301j;

    /* renamed from: k */
    public IPicker f8302k;

    /* renamed from: l */
    public boolean f8303l;

    /* renamed from: n */
    public boolean f8305n;

    /* renamed from: p */
    public String f8307p;

    /* renamed from: q */
    public boolean f8308q;

    /* renamed from: r */
    public String f8309r;

    /* renamed from: s */
    public UriConfig f8310s;

    /* renamed from: t */
    public String f8311t;

    /* renamed from: u */
    public String f8312u;

    /* renamed from: v */
    public int f8313v;

    /* renamed from: w */
    public int f8314w;

    /* renamed from: x */
    public int f8315x;

    /* renamed from: y */
    public String f8316y;

    /* renamed from: z */
    public String f8317z;

    /* renamed from: b */
    public boolean f8293b = true;

    /* renamed from: m */
    public boolean f8304m = false;

    /* renamed from: o */
    public int f8306o = 0;

    /* renamed from: E */
    public INetworkClient f8275E = new C3583f2();

    /* renamed from: G */
    public boolean f8277G = true;

    /* renamed from: I */
    public boolean f8279I = false;

    /* renamed from: J */
    public boolean f8280J = true;

    /* renamed from: K */
    public boolean f8281K = false;

    /* renamed from: L */
    public boolean f8282L = true;

    /* renamed from: M */
    public boolean f8283M = true;

    /* renamed from: N */
    public String f8284N = "bd_tea_agent.db";

    /* renamed from: O */
    public String f8285O = "applog_stats";

    /* renamed from: Q */
    public boolean f8287Q = true;

    /* renamed from: R */
    public boolean f8288R = true;

    /* renamed from: U */
    public IpcDataChecker f8291U = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface IpcDataChecker {
        boolean checkIpcData(String[] strArr);
    }

    public InitConfig(@NonNull String str, @NonNull String str2) {
        this.f8292a = str;
        this.f8294c = str2;
        if (TextUtils.isEmpty(str2)) {
            Log.e("InitConfig", "channel is empty, please check!!!");
        }
    }

    /* renamed from: a */
    public boolean m17352a() {
        return this.f8282L;
    }

    public boolean autoStart() {
        return this.f8293b;
    }

    public void clearDidAndIid(String str) {
        this.f8274D = true;
        this.f8295d = str;
    }

    public Account getAccount() {
        return this.f8273C;
    }

    public String getAid() {
        return this.f8292a;
    }

    public String getAliyunUdid() {
        return this.f8301j;
    }

    public boolean getAnonymous() {
        return this.f8303l;
    }

    public String getAppImei() {
        return this.f8289S;
    }

    public String getAppName() {
        return this.f8309r;
    }

    public String getChannel() {
        return this.f8294c;
    }

    public String getClearKey() {
        return this.f8295d;
    }

    public Map<String, Object> getCommonHeader() {
        return this.f8272B;
    }

    public String getDbName() {
        return this.f8284N;
    }

    public IEncryptor getEncryptor() {
        return this.f8296e;
    }

    public String getGoogleAid() {
        return this.f8297f;
    }

    public IpcDataChecker getIpcDataChecker() {
        return this.f8291U;
    }

    public String getLanguage() {
        return this.f8298g;
    }

    public boolean getLocalTest() {
        return this.f8304m;
    }

    public ILogger getLogger() {
        return this.f8299h;
    }

    public String getManifestVersion() {
        return this.f8316y;
    }

    public int getManifestVersionCode() {
        return this.f8315x;
    }

    public INetworkClient getNetworkClient() {
        INetworkClient iNetworkClient = this.f8276F;
        return iNetworkClient != null ? iNetworkClient : this.f8275E;
    }

    public boolean getNotReuqestSender() {
        return this.f8308q;
    }

    public IPicker getPicker() {
        return this.f8302k;
    }

    public InterfaceC3696t3 getPreInstallCallback() {
        return null;
    }

    public int getProcess() {
        return this.f8306o;
    }

    public String getRegion() {
        return this.f8300i;
    }

    public String getReleaseBuild() {
        return this.f8307p;
    }

    public ISensitiveInfoProvider getSensitiveInfoProvider() {
        return this.f8286P;
    }

    public String getSpName() {
        return this.f8285O;
    }

    public String getTweakedChannel() {
        return this.f8312u;
    }

    public int getUpdateVersionCode() {
        return this.f8314w;
    }

    public UriConfig getUriConfig() {
        return this.f8310s;
    }

    public String getVersion() {
        return this.f8311t;
    }

    public int getVersionCode() {
        return this.f8313v;
    }

    public String getVersionMinor() {
        return this.f8317z;
    }

    public String getZiJieCloudPkg() {
        return this.f8271A;
    }

    public boolean isAbEnable() {
        return this.f8279I;
    }

    public boolean isAntiCheatingEnable() {
        return this.f8281K;
    }

    public boolean isAutoActive() {
        return this.f8277G;
    }

    public boolean isAutoTrackEnabled() {
        return this.f8280J;
    }

    public boolean isClearDidAndIid() {
        return this.f8274D;
    }

    public boolean isCongestionControlEnable() {
        return this.f8283M;
    }

    public boolean isEventFilterEnable() {
        return this.f8290T;
    }

    public boolean isImeiEnable() {
        return this.f8288R;
    }

    public boolean isMacEnable() {
        return this.f8287Q;
    }

    public boolean isPlayEnable() {
        return this.f8305n;
    }

    public boolean isSilenceInBackground() {
        return this.f8278H;
    }

    public InitConfig putCommonHeader(Map<String, Object> map) {
        this.f8272B = map;
        return this;
    }

    public void setAbEnable(boolean z) {
        this.f8279I = z;
    }

    public InitConfig setAccount(Account account) {
        this.f8273C = account;
        return this;
    }

    public InitConfig setAliyunUdid(String str) {
        this.f8301j = str;
        return this;
    }

    public InitConfig setAnonymous(boolean z) {
        this.f8303l = z;
        return this;
    }

    public void setAppImei(String str) {
        this.f8289S = str;
    }

    public InitConfig setAppName(String str) {
        this.f8309r = str;
        return this;
    }

    public void setAutoActive(boolean z) {
        this.f8277G = z;
    }

    public InitConfig setAutoStart(boolean z) {
        this.f8293b = z;
        return this;
    }

    public void setAutoTrackEnabled(boolean z) {
        this.f8280J = z;
    }

    public void setChannel(@NonNull String str) {
        this.f8294c = str;
    }

    public void setCongestionControlEnable(boolean z) {
        this.f8283M = z;
    }

    public InitConfig setDbName(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f8284N = str;
        }
        return this;
    }

    @NonNull
    public InitConfig setEnablePlay(boolean z) {
        this.f8305n = z;
        return this;
    }

    public InitConfig setEncryptor(IEncryptor iEncryptor) {
        this.f8296e = iEncryptor;
        return this;
    }

    public void setEventFilterEnable(boolean z) {
        this.f8290T = z;
    }

    @NonNull
    public InitConfig setGoogleAid(String str) {
        this.f8297f = str;
        return this;
    }

    public void setHandleLifeCycle(boolean z) {
        this.f8282L = z;
    }

    public void setImeiEnable(boolean z) {
        this.f8288R = z;
    }

    public InitConfig setIpcDataChecker(IpcDataChecker ipcDataChecker) {
        this.f8291U = ipcDataChecker;
        return this;
    }

    @NonNull
    public InitConfig setLanguage(String str) {
        this.f8298g = str;
        return this;
    }

    public InitConfig setLocalTest(boolean z) {
        this.f8304m = z;
        return this;
    }

    public InitConfig setLogger(ILogger iLogger) {
        this.f8299h = iLogger;
        return this;
    }

    public void setMacEnable(boolean z) {
        this.f8287Q = z;
    }

    public InitConfig setManifestVersion(String str) {
        this.f8316y = str;
        return this;
    }

    public InitConfig setManifestVersionCode(int i) {
        this.f8315x = i;
        return this;
    }

    public void setNeedAntiCheating(boolean z) {
        this.f8281K = z;
    }

    public InitConfig setNetworkClient(INetworkClient iNetworkClient) {
        this.f8276F = iNetworkClient;
        return this;
    }

    public InitConfig setNotRequestSender(boolean z) {
        this.f8308q = z;
        return this;
    }

    @NonNull
    public InitConfig setPicker(IPicker iPicker) {
        this.f8302k = iPicker;
        return this;
    }

    public InitConfig setPreInstallChannelCallback(InterfaceC3696t3 interfaceC3696t3) {
        return this;
    }

    @NonNull
    public InitConfig setProcess(boolean z) {
        this.f8306o = z ? 1 : 2;
        return this;
    }

    @NonNull
    public InitConfig setRegion(String str) {
        this.f8300i = str;
        return this;
    }

    @NonNull
    public InitConfig setReleaseBuild(String str) {
        this.f8307p = str;
        return this;
    }

    public void setSensitiveInfoProvider(ISensitiveInfoProvider iSensitiveInfoProvider) {
        this.f8286P = iSensitiveInfoProvider;
    }

    public void setSilenceInBackground(boolean z) {
        this.f8278H = z;
    }

    public InitConfig setSpName(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f8285O = str;
        }
        return this;
    }

    public InitConfig setTweakedChannel(String str) {
        this.f8312u = str;
        return this;
    }

    public InitConfig setUpdateVersionCode(int i) {
        this.f8314w = i;
        return this;
    }

    public InitConfig setUriConfig(int i) {
        this.f8310s = UriConfig.createUriConfig(i);
        return this;
    }

    public InitConfig setUriConfig(UriConfig uriConfig) {
        this.f8310s = uriConfig;
        return this;
    }

    public InitConfig setVersion(String str) {
        this.f8311t = str;
        return this;
    }

    public InitConfig setVersionCode(int i) {
        this.f8313v = i;
        return this;
    }

    public InitConfig setVersionMinor(String str) {
        this.f8317z = str;
        return this;
    }

    public InitConfig setZiJieCloudPkg(String str) {
        this.f8271A = str;
        return this;
    }
}
