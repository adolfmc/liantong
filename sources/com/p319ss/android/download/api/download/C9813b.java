package com.p319ss.android.download.api.download;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: com.ss.android.download.api.download.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C9813b implements DownloadEventConfig {

    /* renamed from: b */
    private String f18732b;

    /* renamed from: e */
    private String f18733e;

    /* renamed from: h */
    private String f18734h;

    /* renamed from: hj */
    private String f18735hj;

    /* renamed from: io */
    private String f18736io;

    /* renamed from: jb */
    private String f18737jb;

    /* renamed from: je */
    private Object f18738je;

    /* renamed from: ko */
    private String f18739ko;

    /* renamed from: lc */
    private boolean f18740lc;

    /* renamed from: lz */
    private String f18741lz;

    /* renamed from: mb */
    private String f18742mb;

    /* renamed from: nk */
    private boolean f18743nk;

    /* renamed from: o */
    private boolean f18744o;

    /* renamed from: ox */
    private boolean f18745ox;

    /* renamed from: u */
    private String f18746u;

    /* renamed from: ww */
    private String f18747ww;

    /* renamed from: x */
    private String f18748x;

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public String getClickItemTag() {
        return null;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public int getDownloadScene() {
        return 0;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public JSONObject getExtraJson() {
        return null;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public JSONObject getParamsJson() {
        return null;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public void setDownloadScene(int i) {
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public void setRefer(String str) {
    }

    public C9813b() {
    }

    private C9813b(C9815mb c9815mb) {
        this.f18742mb = c9815mb.f18759mb;
        this.f18745ox = c9815mb.f18762ox;
        this.f18732b = c9815mb.f18749b;
        this.f18735hj = c9815mb.f18752hj;
        this.f18734h = c9815mb.f18751h;
        this.f18746u = c9815mb.f18763u;
        this.f18739ko = c9815mb.f18756ko;
        this.f18747ww = c9815mb.f18764ww;
        this.f18741lz = c9815mb.f18758lz;
        this.f18748x = c9815mb.f18765x;
        this.f18737jb = c9815mb.f18754jb;
        this.f18738je = c9815mb.f18755je;
        this.f18743nk = c9815mb.f18760nk;
        this.f18744o = c9815mb.f18761o;
        this.f18740lc = c9815mb.f18757lc;
        this.f18736io = c9815mb.f18753io;
        this.f18733e = c9815mb.f18750e;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public String getRefer() {
        return this.f18733e;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public String getClickButtonTag() {
        return this.f18742mb;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public String getClickLabel() {
        return this.f18732b;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public String getClickStartLabel() {
        return this.f18735hj;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public String getClickPauseLabel() {
        return this.f18734h;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public String getClickContinueLabel() {
        return this.f18746u;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public String getClickInstallLabel() {
        return this.f18739ko;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public String getStorageDenyLabel() {
        return this.f18748x;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public Object getExtraEventObject() {
        return this.f18738je;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public boolean isEnableClickEvent() {
        return this.f18745ox;
    }

    @Override // com.p319ss.android.download.api.download.DownloadEventConfig
    public boolean isEnableV3Event() {
        return this.f18743nk;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.download.api.download.b$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C9815mb {

        /* renamed from: b */
        private String f18749b;

        /* renamed from: e */
        private String f18750e;

        /* renamed from: h */
        private String f18751h;

        /* renamed from: hj */
        private String f18752hj;

        /* renamed from: io */
        private String f18753io;

        /* renamed from: jb */
        private String f18754jb;

        /* renamed from: je */
        private Object f18755je;

        /* renamed from: ko */
        private String f18756ko;

        /* renamed from: lc */
        private boolean f18757lc;

        /* renamed from: lz */
        private String f18758lz;

        /* renamed from: mb */
        private String f18759mb;

        /* renamed from: nk */
        private boolean f18760nk;

        /* renamed from: o */
        private boolean f18761o;

        /* renamed from: ox */
        private boolean f18762ox;

        /* renamed from: u */
        private String f18763u;

        /* renamed from: ww */
        private String f18764ww;

        /* renamed from: x */
        private String f18765x;

        /* renamed from: mb */
        public C9813b m7919mb() {
            return new C9813b(this);
        }
    }
}
