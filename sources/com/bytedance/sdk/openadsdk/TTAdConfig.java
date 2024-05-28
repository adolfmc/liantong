package com.bytedance.sdk.openadsdk;

import com.bytedance.sdk.openadsdk.AdConfig;
import com.bytedance.sdk.openadsdk.live.ITTLiveTokenInjectionAuth;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class TTAdConfig implements AdConfig {

    /* renamed from: b */
    private boolean f9441b;

    /* renamed from: e */
    private ITTLiveTokenInjectionAuth f9442e;

    /* renamed from: h */
    private String f9443h;

    /* renamed from: hj */
    private String f9444hj;

    /* renamed from: io */
    private int f9445io;

    /* renamed from: jb */
    private boolean f9446jb;

    /* renamed from: je */
    private boolean f9447je;

    /* renamed from: ko */
    private boolean f9448ko;

    /* renamed from: lc */
    private TTCustomController f9449lc;

    /* renamed from: lz */
    private boolean f9450lz;

    /* renamed from: mb */
    private String f9451mb;

    /* renamed from: nk */
    private int f9452nk;

    /* renamed from: o */
    private Map<String, Object> f9453o;

    /* renamed from: ox */
    private String f9454ox;

    /* renamed from: u */
    private int f9455u;

    /* renamed from: ww */
    private boolean f9456ww;

    /* renamed from: x */
    private int[] f9457x;

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    @Deprecated
    public boolean isAsyncInit() {
        return true;
    }

    @Deprecated
    public void setAsyncInit(boolean z) {
    }

    @Deprecated
    public void setNeedClearTaskReset(String... strArr) {
    }

    private TTAdConfig() {
        this.f9441b = false;
        this.f9455u = 0;
        this.f9448ko = true;
        this.f9456ww = false;
        this.f9450lz = false;
        this.f9446jb = true;
        this.f9447je = false;
        this.f9452nk = 0;
        this.f9453o = new HashMap();
        this.f9453o.put("_sdk_is_p_", true);
        this.f9453o.put("_sdk_v_c_", 5102);
        this.f9453o.put("_sdk_v_n_", "5.1.0.2");
        this.f9453o.put("_sdk_p_n_", "com.byted.pangle");
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public ITTLiveTokenInjectionAuth getInjectionAuth() {
        return this.f9442e;
    }

    public void setInjectionAuth(ITTLiveTokenInjectionAuth iTTLiveTokenInjectionAuth) {
        this.f9442e = iTTLiveTokenInjectionAuth;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public String getAppId() {
        return this.f9451mb;
    }

    public void setAppId(String str) {
        this.f9451mb = str;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public String getAppName() {
        return this.f9454ox;
    }

    public void setAppName(String str) {
        this.f9454ox = str;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isPaid() {
        return this.f9441b;
    }

    public void setPaid(boolean z) {
        this.f9441b = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public String getKeywords() {
        return this.f9444hj;
    }

    public void setKeywords(String str) {
        this.f9444hj = str;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public String getData() {
        return this.f9443h;
    }

    public void setData(String str) {
        this.f9443h = str;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public int getTitleBarTheme() {
        return this.f9455u;
    }

    public void setTitleBarTheme(int i) {
        this.f9455u = i;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isAllowShowNotify() {
        return this.f9448ko;
    }

    public void setAllowShowNotify(boolean z) {
        this.f9448ko = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isDebug() {
        return this.f9456ww;
    }

    public void setDebug(boolean z) {
        this.f9456ww = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isAllowShowPageWhenScreenLock() {
        return this.f9450lz;
    }

    public void setAllowShowPageWhenScreenLock(boolean z) {
        this.f9450lz = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public int[] getDirectDownloadNetworkType() {
        return this.f9457x;
    }

    public void setDirectDownloadNetworkType(int... iArr) {
        this.f9457x = iArr;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isUseTextureView() {
        return this.f9446jb;
    }

    public void setUseTextureView(boolean z) {
        this.f9446jb = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public boolean isSupportMultiProcess() {
        return this.f9447je;
    }

    public void setSupportMultiProcess(boolean z) {
        this.f9447je = z;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    @Deprecated
    public String[] getNeedClearTaskReset() {
        return new String[0];
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public void setAgeGroup(int i) {
        this.f9453o.put("age_group", Integer.valueOf(i));
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public TTCustomController getCustomController() {
        return this.f9449lc;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    @Deprecated
    public Object getExtra(String str) {
        return this.f9453o.get(str);
    }

    public void setThemeStatus(int i) {
        this.f9445io = i;
    }

    public int getThemeStatus() {
        return this.f9445io;
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public Object removeExtra(String str) {
        return this.f9453o.remove(str);
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    @Deprecated
    public void setExtra(String str, Object obj) {
        this.f9453o.put(str, obj);
    }

    @Override // com.bytedance.sdk.openadsdk.AdConfig
    public AdConfig.SdkInfo getSdkInfo() {
        return new AdConfig.SdkInfo() { // from class: com.bytedance.sdk.openadsdk.TTAdConfig.1
            @Override // com.bytedance.sdk.openadsdk.AdConfig.SdkInfo
            public boolean isPlugin() {
                return true;
            }

            @Override // com.bytedance.sdk.openadsdk.AdConfig.SdkInfo
            public String pluginName() {
                return "com.byted.pangle";
            }

            @Override // com.bytedance.sdk.openadsdk.AdConfig.SdkInfo
            public int sdkVersionCode() {
                return 5102;
            }

            @Override // com.bytedance.sdk.openadsdk.AdConfig.SdkInfo
            public String sdkVersionName() {
                return "5.1.0.2";
            }
        };
    }

    public void setCustomController(TTCustomController tTCustomController) {
        this.f9449lc = tTCustomController;
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Builder {

        /* renamed from: h */
        private String f9461h;

        /* renamed from: hj */
        private String f9462hj;

        /* renamed from: io */
        private int f9463io;

        /* renamed from: lc */
        private TTCustomController f9468lc;

        /* renamed from: m */
        private ITTLiveTokenInjectionAuth f9470m;

        /* renamed from: mb */
        private String f9471mb;

        /* renamed from: nk */
        private String[] f9472nk;

        /* renamed from: ox */
        private String f9474ox;

        /* renamed from: x */
        private int[] f9477x;

        /* renamed from: b */
        private boolean f9459b = false;

        /* renamed from: u */
        private int f9475u = 0;

        /* renamed from: ko */
        private boolean f9466ko = true;

        /* renamed from: ww */
        private boolean f9476ww = false;

        /* renamed from: lz */
        private boolean f9469lz = false;

        /* renamed from: jb */
        private boolean f9464jb = true;

        /* renamed from: je */
        private boolean f9465je = false;

        /* renamed from: o */
        private boolean f9473o = false;

        /* renamed from: e */
        private int f9460e = 2;

        /* renamed from: l */
        private int f9467l = 0;

        public Builder injectionAuth(ITTLiveTokenInjectionAuth iTTLiveTokenInjectionAuth) {
            this.f9470m = iTTLiveTokenInjectionAuth;
            return this;
        }

        public Builder appId(String str) {
            this.f9471mb = str;
            return this;
        }

        public Builder appName(String str) {
            this.f9474ox = str;
            return this;
        }

        public Builder paid(boolean z) {
            this.f9459b = z;
            return this;
        }

        public Builder keywords(String str) {
            this.f9462hj = str;
            return this;
        }

        public Builder data(String str) {
            this.f9461h = str;
            return this;
        }

        public Builder titleBarTheme(int i) {
            this.f9475u = i;
            return this;
        }

        public Builder allowShowNotify(boolean z) {
            this.f9466ko = z;
            return this;
        }

        public Builder debug(boolean z) {
            this.f9476ww = z;
            return this;
        }

        @Deprecated
        public Builder allowShowPageWhenScreenLock(boolean z) {
            this.f9469lz = z;
            return this;
        }

        public Builder directDownloadNetworkType(int... iArr) {
            this.f9477x = iArr;
            return this;
        }

        public Builder useTextureView(boolean z) {
            this.f9464jb = z;
            return this;
        }

        public Builder supportMultiProcess(boolean z) {
            this.f9465je = z;
            return this;
        }

        public Builder needClearTaskReset(String... strArr) {
            this.f9472nk = strArr;
            return this;
        }

        public Builder asyncInit(boolean z) {
            this.f9473o = z;
            return this;
        }

        public Builder customController(TTCustomController tTCustomController) {
            this.f9468lc = tTCustomController;
            return this;
        }

        public Builder themeStatus(int i) {
            this.f9463io = i;
            return this;
        }

        public Builder setPluginUpdateConfig(int i) {
            this.f9460e = i;
            return this;
        }

        public Builder setAgeGroup(int i) {
            this.f9467l = i;
            return this;
        }

        public TTAdConfig build() {
            TTAdConfig tTAdConfig = new TTAdConfig();
            tTAdConfig.setAppId(this.f9471mb);
            tTAdConfig.setAppName(this.f9474ox);
            tTAdConfig.setPaid(this.f9459b);
            tTAdConfig.setKeywords(this.f9462hj);
            tTAdConfig.setData(this.f9461h);
            tTAdConfig.setTitleBarTheme(this.f9475u);
            tTAdConfig.setAllowShowNotify(this.f9466ko);
            tTAdConfig.setDebug(this.f9476ww);
            tTAdConfig.setAllowShowPageWhenScreenLock(this.f9469lz);
            tTAdConfig.setDirectDownloadNetworkType(this.f9477x);
            tTAdConfig.setUseTextureView(this.f9464jb);
            tTAdConfig.setSupportMultiProcess(this.f9465je);
            tTAdConfig.setNeedClearTaskReset(this.f9472nk);
            tTAdConfig.setAsyncInit(this.f9473o);
            tTAdConfig.setCustomController(this.f9468lc);
            tTAdConfig.setThemeStatus(this.f9463io);
            tTAdConfig.setExtra("plugin_update_conf", Integer.valueOf(this.f9460e));
            tTAdConfig.setExtra("age_group", Integer.valueOf(this.f9467l));
            tTAdConfig.setInjectionAuth(this.f9470m);
            return tTAdConfig;
        }
    }
}
