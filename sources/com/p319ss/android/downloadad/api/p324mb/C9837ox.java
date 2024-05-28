package com.p319ss.android.downloadad.api.p324mb;

import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.model.DeepLink;
import com.p319ss.android.download.api.p320b.C9779ox;
import com.p319ss.android.downloadad.api.constant.AdBaseConstants;
import com.p319ss.android.downloadad.api.download.AdDownloadController;
import com.p319ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.p319ss.android.downloadad.api.download.AdDownloadModel;
import com.p319ss.android.downloadlib.addownload.C9940x;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadad.api.mb.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9837ox implements InterfaceC9836mb {

    /* renamed from: a */
    private int f18840a;

    /* renamed from: al */
    private String f18841al;

    /* renamed from: b */
    public final AtomicBoolean f18842b;

    /* renamed from: bv */
    private boolean f18843bv;

    /* renamed from: cd */
    private long f18844cd;

    /* renamed from: df */
    private JSONObject f18845df;

    /* renamed from: e */
    private String f18846e;

    /* renamed from: ep */
    private String f18847ep;

    /* renamed from: fb */
    private long f18848fb;

    /* renamed from: fu */
    private int f18849fu;

    /* renamed from: g */
    private long f18850g;

    /* renamed from: ge */
    private long f18851ge;

    /* renamed from: gm */
    private boolean f18852gm;

    /* renamed from: h */
    private long f18853h;

    /* renamed from: he */
    private boolean f18854he;

    /* renamed from: hj */
    public final AtomicBoolean f18855hj;
    @AdBaseConstants.FunnelType

    /* renamed from: i */
    private int f18856i;

    /* renamed from: io */
    private int f18857io;

    /* renamed from: jb */
    private String f18858jb;

    /* renamed from: je */
    private String f18859je;

    /* renamed from: jq */
    private int f18860jq;

    /* renamed from: kg */
    private boolean f18861kg;

    /* renamed from: kk */
    private boolean f18862kk;

    /* renamed from: km */
    private boolean f18863km;

    /* renamed from: ko */
    private String f18864ko;

    /* renamed from: l */
    private String f18865l;

    /* renamed from: lc */
    private int f18866lc;

    /* renamed from: lz */
    private String f18867lz;

    /* renamed from: m */
    private String f18868m;

    /* renamed from: mb */
    protected boolean f18869mb;

    /* renamed from: n */
    private boolean f18870n;

    /* renamed from: nf */
    private int f18871nf;

    /* renamed from: ng */
    private long f18872ng;

    /* renamed from: nk */
    private int f18873nk;

    /* renamed from: nq */
    private boolean f18874nq;

    /* renamed from: o */
    private int f18875o;

    /* renamed from: on */
    private boolean f18876on;

    /* renamed from: ot */
    private long f18877ot;

    /* renamed from: ox */
    protected boolean f18878ox;

    /* renamed from: pa */
    private boolean f18879pa;

    /* renamed from: q */
    private boolean f18880q;

    /* renamed from: qa */
    private String f18881qa;

    /* renamed from: r */
    private int f18882r;

    /* renamed from: s */
    private boolean f18883s;

    /* renamed from: sa */
    private boolean f18884sa;

    /* renamed from: sr */
    private boolean f18885sr;

    /* renamed from: sw */
    private boolean f18886sw;

    /* renamed from: tl */
    private boolean f18887tl;

    /* renamed from: u */
    private long f18888u;

    /* renamed from: up */
    private transient boolean f18889up;

    /* renamed from: vy */
    private String f18890vy;

    /* renamed from: w */
    private boolean f18891w;

    /* renamed from: wn */
    private String f18892wn;

    /* renamed from: ww */
    private int f18893ww;

    /* renamed from: x */
    private String f18894x;

    /* renamed from: xa */
    private long f18895xa;

    /* renamed from: yr */
    private boolean f18896yr;

    /* renamed from: z */
    private int f18897z;

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: gm */
    public int mo7490gm() {
        return -1;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: io */
    public JSONObject mo7487io() {
        return null;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: jb */
    public JSONObject mo7486jb() {
        return null;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: l */
    public JSONObject mo7482l() {
        return null;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: lc */
    public Object mo7481lc() {
        return null;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: o */
    public List<String> mo7476o() {
        return null;
    }

    private C9837ox() {
        this.f18893ww = 1;
        this.f18852gm = true;
        this.f18876on = false;
        this.f18860jq = 0;
        this.f18840a = 0;
        this.f18886sw = false;
        this.f18880q = false;
        this.f18843bv = true;
        this.f18891w = true;
        this.f18869mb = true;
        this.f18878ox = true;
        this.f18842b = new AtomicBoolean(false);
        this.f18855hj = new AtomicBoolean(false);
        this.f18856i = 1;
        this.f18884sa = true;
        this.f18844cd = -1L;
    }

    public C9837ox(DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        this(downloadModel, downloadEventConfig, downloadController, 0);
    }

    public C9837ox(DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, int i) {
        this.f18893ww = 1;
        this.f18852gm = true;
        this.f18876on = false;
        this.f18860jq = 0;
        this.f18840a = 0;
        this.f18886sw = false;
        this.f18880q = false;
        this.f18843bv = true;
        this.f18891w = true;
        this.f18869mb = true;
        this.f18878ox = true;
        this.f18842b = new AtomicBoolean(false);
        this.f18855hj = new AtomicBoolean(false);
        this.f18856i = 1;
        this.f18884sa = true;
        this.f18844cd = -1L;
        this.f18853h = downloadModel.getId();
        this.f18888u = downloadModel.getExtraValue();
        this.f18864ko = downloadModel.getLogExtra();
        this.f18867lz = downloadModel.getPackageName();
        this.f18845df = downloadModel.getExtra();
        this.f18852gm = downloadModel.isAd();
        this.f18857io = downloadModel.getVersionCode();
        this.f18846e = downloadModel.getVersionName();
        this.f18894x = downloadModel.getDownloadUrl();
        if (downloadModel.getDeepLink() != null) {
            this.f18858jb = downloadModel.getDeepLink().getOpenUrl();
            this.f18859je = downloadModel.getDeepLink().getWebUrl();
        }
        this.f18873nk = downloadModel.getModelType();
        this.f18865l = downloadModel.getName();
        this.f18868m = downloadModel.getAppIcon();
        this.f18892wn = downloadModel.getMimeType();
        this.f18841al = downloadEventConfig.getClickButtonTag();
        this.f18890vy = downloadEventConfig.getRefer();
        this.f18879pa = downloadEventConfig.isEnableV3Event();
        this.f18876on = downloadController.isEnableBackDialog();
        this.f18875o = downloadController.getLinkMode();
        this.f18866lc = downloadController.getDownloadMode();
        this.f18884sa = downloadController.enableShowComplianceDialog();
        this.f18885sr = downloadController.isAutoDownloadOnCardShow();
        this.f18843bv = downloadController.enableNewActivity();
        this.f18869mb = downloadController.enableAH();
        this.f18878ox = downloadController.enableAM();
        this.f18882r = i;
        this.f18850g = System.currentTimeMillis();
        this.f18872ng = this.f18850g;
        this.f18880q = downloadModel.shouldDownloadWithPatchApply();
    }

    /* renamed from: on */
    public int m7762on() {
        return this.f18860jq;
    }

    /* renamed from: mb */
    public void m7774mb(int i) {
        this.f18860jq = i;
    }

    /* renamed from: jq */
    public synchronized void m7789jq() {
        this.f18860jq++;
    }

    /* renamed from: a */
    public int m7822a() {
        return this.f18840a;
    }

    /* renamed from: ox */
    public void m7760ox(int i) {
        this.f18840a = i;
    }

    /* renamed from: ng */
    public synchronized void m7767ng() {
        this.f18840a++;
    }

    /* renamed from: ge */
    public long m7809ge() {
        long j = this.f18872ng;
        return j == 0 ? this.f18850g : j;
    }

    /* renamed from: mb */
    public void m7773mb(long j) {
        this.f18872ng = j;
    }

    /* renamed from: xa */
    public long m7732xa() {
        return this.f18851ge;
    }

    /* renamed from: ox */
    public void m7759ox(long j) {
        this.f18851ge = j;
    }

    /* renamed from: b */
    public void m7819b(long j) {
        this.f18895xa = j;
    }

    /* renamed from: nf */
    public int m7768nf() {
        return this.f18871nf;
    }

    /* renamed from: b */
    public void m7820b(int i) {
        this.f18871nf = i;
    }

    /* renamed from: fu */
    public int m7811fu() {
        return this.f18849fu;
    }

    /* renamed from: hj */
    public void m7802hj(int i) {
        this.f18849fu = i;
    }

    /* renamed from: ep */
    public String m7813ep() {
        return this.f18847ep;
    }

    /* renamed from: mb */
    public void m7772mb(String str) {
        this.f18847ep = str;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: ox */
    public long mo7474ox() {
        return this.f18853h;
    }

    /* renamed from: hj */
    public void m7801hj(long j) {
        this.f18853h = j;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: je */
    public long mo7485je() {
        return this.f18888u;
    }

    /* renamed from: h */
    public void m7806h(long j) {
        this.f18888u = j;
    }

    /* renamed from: sw */
    public int m7749sw() {
        return this.f18893ww;
    }

    /* renamed from: h */
    public void m7807h(int i) {
        this.f18893ww = i;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: h */
    public String mo7489h() {
        return this.f18867lz;
    }

    /* renamed from: ox */
    public void m7758ox(String str) {
        this.f18867lz = str;
    }

    /* renamed from: wn */
    public long m7740wn() {
        return this.f18850g;
    }

    /* renamed from: u */
    public void m7746u(long j) {
        if (j > 0) {
            this.f18850g = j;
        }
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: hj */
    public String mo7488hj() {
        return this.f18864ko;
    }

    /* renamed from: b */
    public void m7818b(String str) {
        this.f18864ko = str;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: b */
    public boolean mo7494b() {
        return this.f18852gm;
    }

    /* renamed from: mb */
    public void m7770mb(boolean z) {
        this.f18852gm = z;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: ko */
    public JSONObject mo7483ko() {
        return this.f18845df;
    }

    /* renamed from: mb */
    public void m7771mb(JSONObject jSONObject) {
        this.f18845df = jSONObject;
    }

    /* renamed from: u */
    public void m7747u(int i) {
        this.f18857io = i;
    }

    /* renamed from: hj */
    public void m7800hj(String str) {
        this.f18846e = str;
    }

    /* renamed from: q */
    public int m7754q() {
        return this.f18857io;
    }

    /* renamed from: bv */
    public String m7816bv() {
        return this.f18846e;
    }

    /* renamed from: ko */
    public void m7785ko(int i) {
        this.f18882r = i;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: m */
    public int mo7479m() {
        return this.f18882r;
    }

    /* renamed from: ox */
    public void m7756ox(boolean z) {
        this.f18879pa = z;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: nk */
    public boolean mo7477nk() {
        return this.f18879pa;
    }

    /* renamed from: ww */
    public void m7739ww(int i) {
        this.f18897z = i;
    }

    /* renamed from: w */
    public int m7741w() {
        return this.f18897z;
    }

    /* renamed from: lz */
    public void m7779lz(int i) {
        this.f18856i = i;
    }

    /* renamed from: h */
    public void m7805h(String str) {
        this.f18859je = str;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: ww */
    public int mo7471ww() {
        return this.f18856i;
    }

    /* renamed from: b */
    public void m7817b(boolean z) {
        this.f18876on = z;
    }

    /* renamed from: al */
    public boolean m7821al() {
        return this.f18876on;
    }

    /* renamed from: u */
    public void m7745u(String str) {
        this.f18841al = str;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: x */
    public String mo7470x() {
        return this.f18841al;
    }

    /* renamed from: ko */
    public void m7783ko(String str) {
        this.f18890vy = str;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: lz */
    public String mo7480lz() {
        return this.f18890vy;
    }

    /* renamed from: ww */
    public void m7737ww(String str) {
        this.f18894x = str;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: mb */
    public String mo7478mb() {
        return this.f18894x;
    }

    /* renamed from: lz */
    public void m7777lz(String str) {
        this.f18858jb = str;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: u */
    public String mo7472u() {
        return this.f18858jb;
    }

    /* renamed from: ko */
    public void m7784ko(long j) {
        this.f18844cd = j;
    }

    /* renamed from: vy */
    public long m7742vy() {
        return this.f18844cd;
    }

    /* renamed from: x */
    public void m7734x(String str) {
        this.f18865l = str;
    }

    /* renamed from: pa */
    public String m7755pa() {
        return this.f18865l;
    }

    /* renamed from: jb */
    public void m7795jb(String str) {
        this.f18868m = str;
    }

    /* renamed from: x */
    public void m7735x(int i) {
        this.f18875o = i;
    }

    /* renamed from: yr */
    public int m7730yr() {
        return this.f18875o;
    }

    /* renamed from: jb */
    public void m7796jb(int i) {
        this.f18866lc = i;
    }

    /* renamed from: je */
    public void m7793je(int i) {
        this.f18873nk = i;
    }

    /* renamed from: hj */
    public void m7799hj(boolean z) {
        this.f18887tl = z;
    }

    /* renamed from: z */
    public String m7729z() {
        return this.f18881qa;
    }

    /* renamed from: je */
    public void m7792je(String str) {
        this.f18881qa = str;
    }

    /* renamed from: i */
    public boolean m7798i() {
        return this.f18870n;
    }

    /* renamed from: h */
    public void m7804h(boolean z) {
        this.f18870n = z;
    }

    /* renamed from: ot */
    public boolean m7761ot() {
        return this.f18896yr;
    }

    /* renamed from: u */
    public void m7744u(boolean z) {
        this.f18896yr = z;
    }

    /* renamed from: fb */
    public boolean m7812fb() {
        return this.f18883s;
    }

    /* renamed from: ko */
    public void m7782ko(boolean z) {
        this.f18883s = z;
    }

    /* renamed from: sa */
    public long m7751sa() {
        return this.f18877ot;
    }

    /* renamed from: ww */
    public void m7738ww(long j) {
        this.f18877ot = j;
    }

    /* renamed from: sr */
    public long m7750sr() {
        return this.f18848fb;
    }

    /* renamed from: lz */
    public void m7778lz(long j) {
        this.f18848fb = j;
    }

    /* renamed from: qa */
    public boolean m7753qa() {
        return this.f18886sw;
    }

    /* renamed from: ww */
    public void m7736ww(boolean z) {
        this.f18886sw = z;
    }

    /* renamed from: tl */
    public String m7748tl() {
        return this.f18892wn;
    }

    /* renamed from: nk */
    public void m7766nk(String str) {
        this.f18892wn = str;
    }

    /* renamed from: n */
    public boolean m7769n() {
        return this.f18880q;
    }

    /* renamed from: lz */
    public void m7776lz(boolean z) {
        this.f18880q = z;
    }

    /* renamed from: s */
    public boolean m7752s() {
        return this.f18889up;
    }

    /* renamed from: x */
    public void m7733x(boolean z) {
        this.f18889up = z;
    }

    /* renamed from: cd */
    public boolean m7815cd() {
        return this.f18874nq;
    }

    /* renamed from: jb */
    public void m7794jb(boolean z) {
        this.f18874nq = z;
    }

    /* renamed from: up */
    public boolean m7743up() {
        return this.f18861kg;
    }

    /* renamed from: je */
    public void m7791je(boolean z) {
        this.f18861kg = z;
    }

    /* renamed from: nq */
    public boolean m7764nq() {
        return this.f18862kk;
    }

    /* renamed from: nk */
    public void m7765nk(boolean z) {
        this.f18862kk = z;
    }

    /* renamed from: kk */
    public boolean m7787kk() {
        return this.f18863km;
    }

    /* renamed from: o */
    public void m7763o(boolean z) {
        this.f18863km = z;
    }

    /* renamed from: kg */
    public boolean m7788kg() {
        return this.f18854he;
    }

    /* renamed from: lc */
    public void m7780lc(boolean z) {
        this.f18854he = z;
    }

    /* renamed from: io */
    public void m7797io(boolean z) {
        this.f18884sa = z;
    }

    /* renamed from: e */
    public void m7814e(boolean z) {
        this.f18885sr = z;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: e */
    public boolean mo7492e() {
        return this.f18843bv;
    }

    /* renamed from: l */
    public void m7781l(boolean z) {
        this.f18843bv = z;
    }

    /* renamed from: m */
    public void m7775m(boolean z) {
        this.f18891w = z;
    }

    /* renamed from: gm */
    public void m7808gm(boolean z) {
        this.f18869mb = z;
    }

    /* renamed from: g */
    public void m7810g(boolean z) {
        this.f18878ox = z;
    }

    /* renamed from: km */
    public JSONObject m7786km() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mId", this.f18853h);
            jSONObject.put("mExtValue", this.f18888u);
            jSONObject.put("mLogExtra", this.f18864ko);
            jSONObject.put("mDownloadStatus", this.f18893ww);
            jSONObject.put("mPackageName", this.f18867lz);
            jSONObject.put("mIsAd", this.f18852gm);
            jSONObject.put("mTimeStamp", this.f18850g);
            jSONObject.put("mExtras", this.f18845df);
            jSONObject.put("mVersionCode", this.f18857io);
            jSONObject.put("mVersionName", this.f18846e);
            jSONObject.put("mDownloadId", this.f18882r);
            jSONObject.put("mIsV3Event", this.f18879pa);
            jSONObject.put("mScene", this.f18897z);
            jSONObject.put("mEventTag", this.f18841al);
            jSONObject.put("mEventRefer", this.f18890vy);
            jSONObject.put("mDownloadUrl", this.f18894x);
            jSONObject.put("mEnableBackDialog", this.f18876on);
            jSONObject.put("hasSendInstallFinish", this.f18842b.get());
            jSONObject.put("hasSendDownloadFailedFinally", this.f18855hj.get());
            jSONObject.put("mLastFailedErrCode", this.f18849fu);
            jSONObject.put("mLastFailedErrMsg", this.f18847ep);
            jSONObject.put("mOpenUrl", this.f18858jb);
            jSONObject.put("mLinkMode", this.f18875o);
            jSONObject.put("mDownloadMode", this.f18866lc);
            jSONObject.put("mModelType", this.f18873nk);
            jSONObject.put("mAppName", this.f18865l);
            jSONObject.put("mAppIcon", this.f18868m);
            jSONObject.put("mDownloadFailedTimes", this.f18860jq);
            jSONObject.put("mRecentDownloadResumeTime", this.f18872ng == 0 ? this.f18850g : this.f18872ng);
            jSONObject.put("mClickPauseTimes", this.f18840a);
            jSONObject.put("mJumpInstallTime", this.f18851ge);
            jSONObject.put("mCancelInstallTime", this.f18895xa);
            jSONObject.put("mLastFailedResumeCount", this.f18871nf);
            jSONObject.put("mIsUpdateDownload", this.f18886sw);
            jSONObject.put("mOriginMimeType", this.f18892wn);
            jSONObject.put("mIsPatchApplyHandled", this.f18880q);
            jSONObject.put("downloadFinishReason", this.f18881qa);
            jSONObject.put("clickDownloadTime", this.f18877ot);
            jSONObject.put("clickDownloadSize", this.f18848fb);
            jSONObject.put("installAfterCleanSpace", this.f18896yr);
            jSONObject.put("funnelType", this.f18856i);
            jSONObject.put("webUrl", this.f18859je);
            jSONObject.put("enableShowComplianceDialog", this.f18884sa);
            jSONObject.put("isAutoDownloadOnCardShow", this.f18885sr);
            int i = 1;
            jSONObject.put("enable_new_activity", this.f18843bv ? 1 : 0);
            jSONObject.put("enable_pause", this.f18891w ? 1 : 0);
            jSONObject.put("enable_ah", this.f18869mb ? 1 : 0);
            if (!this.f18878ox) {
                i = 0;
            }
            jSONObject.put("enable_am", i);
        } catch (Exception e) {
            C9940x.m7363m().mo7282mb(e, "NativeDownloadModel toJson");
        }
        return jSONObject;
    }

    /* renamed from: ox */
    public static C9837ox m7757ox(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        C9837ox c9837ox = new C9837ox();
        try {
            c9837ox.m7801hj(C9779ox.m7968mb(jSONObject, "mId"));
            c9837ox.m7806h(C9779ox.m7968mb(jSONObject, "mExtValue"));
            c9837ox.m7818b(jSONObject.optString("mLogExtra"));
            c9837ox.m7807h(jSONObject.optInt("mDownloadStatus"));
            c9837ox.m7758ox(jSONObject.optString("mPackageName"));
            boolean z = true;
            c9837ox.m7770mb(jSONObject.optBoolean("mIsAd", true));
            c9837ox.m7746u(C9779ox.m7968mb(jSONObject, "mTimeStamp"));
            c9837ox.m7747u(jSONObject.optInt("mVersionCode"));
            c9837ox.m7800hj(jSONObject.optString("mVersionName"));
            c9837ox.m7785ko(jSONObject.optInt("mDownloadId"));
            c9837ox.m7756ox(jSONObject.optBoolean("mIsV3Event"));
            c9837ox.m7739ww(jSONObject.optInt("mScene"));
            c9837ox.m7745u(jSONObject.optString("mEventTag"));
            c9837ox.m7783ko(jSONObject.optString("mEventRefer"));
            c9837ox.m7737ww(jSONObject.optString("mDownloadUrl"));
            c9837ox.m7817b(jSONObject.optBoolean("mEnableBackDialog"));
            c9837ox.f18842b.set(jSONObject.optBoolean("hasSendInstallFinish"));
            c9837ox.f18855hj.set(jSONObject.optBoolean("hasSendDownloadFailedFinally"));
            c9837ox.m7802hj(jSONObject.optInt("mLastFailedErrCode"));
            c9837ox.m7772mb(jSONObject.optString("mLastFailedErrMsg"));
            c9837ox.m7777lz(jSONObject.optString("mOpenUrl"));
            c9837ox.m7735x(jSONObject.optInt("mLinkMode"));
            c9837ox.m7796jb(jSONObject.optInt("mDownloadMode"));
            c9837ox.m7793je(jSONObject.optInt("mModelType"));
            c9837ox.m7734x(jSONObject.optString("mAppName"));
            c9837ox.m7795jb(jSONObject.optString("mAppIcon"));
            c9837ox.m7774mb(jSONObject.optInt("mDownloadFailedTimes", 0));
            c9837ox.m7773mb(C9779ox.m7968mb(jSONObject, "mRecentDownloadResumeTime"));
            c9837ox.m7760ox(jSONObject.optInt("mClickPauseTimes"));
            c9837ox.m7759ox(C9779ox.m7968mb(jSONObject, "mJumpInstallTime"));
            c9837ox.m7819b(C9779ox.m7968mb(jSONObject, "mCancelInstallTime"));
            c9837ox.m7820b(jSONObject.optInt("mLastFailedResumeCount"));
            c9837ox.m7792je(jSONObject.optString("downloadFinishReason"));
            c9837ox.m7778lz(jSONObject.optLong("clickDownloadSize"));
            c9837ox.m7738ww(jSONObject.optLong("clickDownloadTime"));
            c9837ox.m7736ww(jSONObject.optBoolean("mIsUpdateDownload"));
            c9837ox.m7766nk(jSONObject.optString("mOriginMimeType"));
            c9837ox.m7776lz(jSONObject.optBoolean("mIsPatchApplyHandled"));
            c9837ox.m7744u(jSONObject.optBoolean("installAfterCleanSpace"));
            c9837ox.m7779lz(jSONObject.optInt("funnelType", 1));
            c9837ox.m7805h(jSONObject.optString("webUrl"));
            c9837ox.m7797io(jSONObject.optBoolean("enableShowComplianceDialog", true));
            c9837ox.m7814e(jSONObject.optBoolean("isAutoDownloadOnCardShow"));
            c9837ox.m7781l(jSONObject.optInt("enable_new_activity", 1) == 1);
            c9837ox.m7775m(jSONObject.optInt("enable_pause", 1) == 1);
            c9837ox.m7808gm(jSONObject.optInt("enable_ah", 1) == 1);
            if (jSONObject.optInt("enable_am", 1) != 1) {
                z = false;
            }
            c9837ox.m7810g(z);
            c9837ox.m7771mb(jSONObject.optJSONObject("mExtras"));
        } catch (Exception e) {
            C9940x.m7363m().mo7282mb(e, "NativeDownloadModel fromJson");
        }
        return c9837ox;
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: g */
    public DownloadModel mo7491g() {
        return m7803he();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: df */
    public DownloadEventConfig mo7493df() {
        return m7731y();
    }

    @Override // com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb
    /* renamed from: r */
    public DownloadController mo7473r() {
        return m7790jm();
    }

    /* renamed from: he */
    public AdDownloadModel m7803he() {
        return new AdDownloadModel.Builder().setAdId(this.f18853h).setExtraValue(this.f18888u).setLogExtra(this.f18864ko).setPackageName(this.f18867lz).setExtra(this.f18845df).setIsAd(this.f18852gm).setVersionCode(this.f18857io).setVersionName(this.f18846e).setDownloadUrl(this.f18894x).setModelType(this.f18873nk).setMimeType(this.f18892wn).setAppName(this.f18865l).setAppIcon(this.f18868m).setDeepLink(new DeepLink(this.f18858jb, this.f18859je, null)).build();
    }

    /* renamed from: y */
    public AdDownloadEventConfig m7731y() {
        return new AdDownloadEventConfig.Builder().setClickButtonTag(this.f18841al).setRefer(this.f18890vy).setIsEnableV3Event(this.f18879pa).build();
    }

    /* renamed from: jm */
    public AdDownloadController m7790jm() {
        return new AdDownloadController.Builder().setIsEnableBackDialog(this.f18876on).setLinkMode(this.f18875o).setDownloadMode(this.f18866lc).setEnableShowComplianceDialog(this.f18884sa).setEnableAH(this.f18869mb).setEnableAM(this.f18878ox).build();
    }
}
