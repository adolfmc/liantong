package com.p319ss.android.socialbase.appdownloader;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10089h;
import com.p319ss.android.socialbase.downloader.constants.EnqueueType;
import com.p319ss.android.socialbase.downloader.constants.ExecutorGroup;
import com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.p319ss.android.socialbase.downloader.depend.IDownloadDepend;
import com.p319ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler;
import com.p319ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.p319ss.android.socialbase.downloader.depend.IDownloadListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloadMonitorDepend;
import com.p319ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.p319ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator;
import com.p319ss.android.socialbase.downloader.downloader.IChunkCntCalculator;
import com.p319ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import com.p319ss.android.socialbase.downloader.notification.AbsNotificationItem;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.ss.android.socialbase.appdownloader.u */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10149u {

    /* renamed from: a */
    private long f19564a;

    /* renamed from: al */
    private int f19565al;

    /* renamed from: b */
    private String f19566b;

    /* renamed from: cd */
    private long f19568cd;

    /* renamed from: df */
    private boolean f19569df;

    /* renamed from: e */
    private boolean f19570e;

    /* renamed from: fb */
    private boolean f19572fb;

    /* renamed from: fu */
    private String f19573fu;

    /* renamed from: g */
    private AbsNotificationItem f19574g;

    /* renamed from: gm */
    private IRetryDelayTimeCalculator f19576gm;

    /* renamed from: h */
    private String f19577h;

    /* renamed from: he */
    private int[] f19578he;

    /* renamed from: hj */
    private List<String> f19579hj;

    /* renamed from: i */
    private IDownloadFileUriProvider f19580i;

    /* renamed from: jq */
    private String f19584jq;

    /* renamed from: km */
    private String f19587km;

    /* renamed from: ko */
    private String f19588ko;

    /* renamed from: l */
    private IChunkAdjustCalculator f19589l;

    /* renamed from: lc */
    private String f19590lc;

    /* renamed from: m */
    private IChunkCntCalculator f19592m;

    /* renamed from: mb */
    private Activity f19593mb;

    /* renamed from: nf */
    private boolean f19595nf;

    /* renamed from: ng */
    private boolean f19596ng;

    /* renamed from: nk */
    private IDownloadListener f19597nk;

    /* renamed from: nq */
    private boolean f19598nq;

    /* renamed from: o */
    private IDownloadListener f19599o;

    /* renamed from: on */
    private String f19600on;

    /* renamed from: ot */
    private IDownloadDiskSpaceHandler f19601ot;

    /* renamed from: ox */
    private Context f19602ox;

    /* renamed from: pa */
    private IDownloadMonitorDepend f19603pa;

    /* renamed from: q */
    private boolean f19604q;

    /* renamed from: qa */
    private JSONObject f19605qa;

    /* renamed from: r */
    private boolean f19606r;

    /* renamed from: s */
    private int f19607s;

    /* renamed from: sa */
    private INotificationClickCallback f19608sa;

    /* renamed from: sr */
    private boolean f19609sr;

    /* renamed from: sw */
    private boolean f19610sw;

    /* renamed from: tl */
    private String f19611tl;

    /* renamed from: u */
    private String f19612u;

    /* renamed from: up */
    private int f19613up;

    /* renamed from: wn */
    private boolean f19616wn;

    /* renamed from: ww */
    private List<HttpHeader> f19617ww;

    /* renamed from: xa */
    private int f19619xa;

    /* renamed from: yr */
    private IDownloadDepend f19620yr;

    /* renamed from: z */
    private InterfaceC10089h f19621z;

    /* renamed from: lz */
    private boolean f19591lz = true;

    /* renamed from: x */
    private boolean f19618x = false;

    /* renamed from: jb */
    private boolean f19582jb = true;

    /* renamed from: je */
    private boolean f19583je = false;

    /* renamed from: io */
    private String f19581io = "application/vnd.android.package-archive";

    /* renamed from: ge */
    private int f19575ge = 5;

    /* renamed from: ep */
    private boolean f19571ep = true;

    /* renamed from: bv */
    private EnqueueType f19567bv = EnqueueType.ENQUEUE_NONE;

    /* renamed from: w */
    private int f19615w = 150;

    /* renamed from: vy */
    private boolean f19614vy = true;

    /* renamed from: n */
    private List<IDownloadCompleteHandler> f19594n = new ArrayList();

    /* renamed from: kk */
    private boolean f19586kk = true;

    /* renamed from: kg */
    private boolean f19585kg = true;

    public C10149u(@NonNull Context context, @NonNull String str) {
        this.f19602ox = context.getApplicationContext();
        this.f19566b = str;
    }

    public Activity getActivity() {
        return this.f19593mb;
    }

    public Context getContext() {
        return this.f19602ox;
    }

    /* renamed from: mb */
    public String m6648mb() {
        return this.f19566b;
    }

    /* renamed from: ox */
    public String m6626ox() {
        return this.f19577h;
    }

    /* renamed from: b */
    public String m6690b() {
        return this.f19588ko;
    }

    /* renamed from: hj */
    public List<HttpHeader> m6672hj() {
        return this.f19617ww;
    }

    /* renamed from: h */
    public boolean m6676h() {
        return this.f19591lz;
    }

    /* renamed from: u */
    public boolean m6612u() {
        return this.f19618x;
    }

    /* renamed from: ko */
    public boolean m6658ko() {
        return this.f19582jb;
    }

    /* renamed from: ww */
    public boolean m6604ww() {
        return this.f19583je;
    }

    /* renamed from: lz */
    public IDownloadListener m6652lz() {
        return this.f19597nk;
    }

    /* renamed from: x */
    public IDownloadListener m6601x() {
        return this.f19599o;
    }

    /* renamed from: jb */
    public String m6665jb() {
        return this.f19590lc;
    }

    /* renamed from: je */
    public String m6663je() {
        return this.f19581io;
    }

    /* renamed from: nk */
    public boolean m6633nk() {
        return this.f19570e;
    }

    /* renamed from: o */
    public AbsNotificationItem m6630o() {
        return this.f19574g;
    }

    /* renamed from: lc */
    public IChunkCntCalculator m6654lc() {
        return this.f19592m;
    }

    /* renamed from: io */
    public IChunkAdjustCalculator m6667io() {
        return this.f19589l;
    }

    /* renamed from: e */
    public boolean m6683e() {
        return this.f19569df;
    }

    /* renamed from: l */
    public boolean m6655l() {
        return this.f19606r;
    }

    /* renamed from: m */
    public int m6649m() {
        return this.f19613up;
    }

    /* renamed from: mb */
    public void m6647mb(int i) {
        this.f19613up = i;
    }

    /* renamed from: gm */
    public String m6677gm() {
        return this.f19600on;
    }

    /* renamed from: g */
    public String m6679g() {
        return this.f19584jq;
    }

    /* renamed from: df */
    public long m6684df() {
        return this.f19564a;
    }

    /* renamed from: r */
    public int m6618r() {
        return this.f19575ge;
    }

    /* renamed from: on */
    public int m6628on() {
        return this.f19619xa;
    }

    /* renamed from: jq */
    public boolean m6661jq() {
        return this.f19595nf;
    }

    /* renamed from: a */
    public String m6692a() {
        return this.f19573fu;
    }

    /* renamed from: ng */
    public boolean m6634ng() {
        return this.f19571ep;
    }

    /* renamed from: ge */
    public boolean m6678ge() {
        return this.f19610sw;
    }

    /* renamed from: xa */
    public IRetryDelayTimeCalculator m6598xa() {
        return this.f19576gm;
    }

    /* renamed from: nf */
    public int m6635nf() {
        return this.f19615w;
    }

    /* renamed from: fu */
    public int m6680fu() {
        return this.f19565al;
    }

    /* renamed from: ep */
    public boolean m6682ep() {
        return this.f19616wn;
    }

    /* renamed from: sw */
    public boolean m6614sw() {
        return this.f19604q;
    }

    /* renamed from: wn */
    public boolean m6605wn() {
        return this.f19614vy;
    }

    /* renamed from: q */
    public boolean m6620q() {
        return this.f19572fb;
    }

    /* renamed from: bv */
    public EnqueueType m6686bv() {
        return this.f19567bv;
    }

    /* renamed from: w */
    public boolean m6606w() {
        return this.f19596ng;
    }

    /* renamed from: al */
    public String m6691al() {
        return this.f19612u;
    }

    /* renamed from: vy */
    public IDownloadMonitorDepend m6607vy() {
        return this.f19603pa;
    }

    /* renamed from: pa */
    public IDownloadDepend m6621pa() {
        return this.f19620yr;
    }

    /* renamed from: yr */
    public InterfaceC10089h m6597yr() {
        return this.f19621z;
    }

    /* renamed from: z */
    public IDownloadFileUriProvider m6596z() {
        return this.f19580i;
    }

    /* renamed from: i */
    public INotificationClickCallback m6668i() {
        return this.f19608sa;
    }

    /* renamed from: ot */
    public List<IDownloadCompleteHandler> m6627ot() {
        return this.f19594n;
    }

    /* renamed from: fb */
    public boolean m6681fb() {
        return this.f19609sr;
    }

    /* renamed from: sa */
    public int m6616sa() {
        return this.f19607s;
    }

    /* renamed from: sr */
    public long m6615sr() {
        return this.f19568cd;
    }

    /* renamed from: qa */
    public boolean m6619qa() {
        return this.f19585kg;
    }

    /* renamed from: tl */
    public String m6613tl() {
        return this.f19587km;
    }

    /* renamed from: n */
    public int[] m6636n() {
        return this.f19578he;
    }

    /* renamed from: s */
    public boolean m6617s() {
        return this.f19598nq;
    }

    /* renamed from: cd */
    public boolean m6685cd() {
        return this.f19586kk;
    }

    /* renamed from: mb */
    public C10149u m6640mb(String str) {
        this.f19577h = str;
        return this;
    }

    /* renamed from: ox */
    public C10149u m6624ox(String str) {
        this.f19612u = str;
        return this;
    }

    /* renamed from: b */
    public C10149u m6688b(@NonNull String str) {
        this.f19588ko = str;
        return this;
    }

    /* renamed from: mb */
    public C10149u m6639mb(List<HttpHeader> list) {
        this.f19617ww = list;
        return this;
    }

    /* renamed from: mb */
    public C10149u m6637mb(boolean z) {
        this.f19591lz = z;
        return this;
    }

    /* renamed from: ox */
    public C10149u m6622ox(boolean z) {
        this.f19618x = z;
        return this;
    }

    /* renamed from: b */
    public C10149u m6687b(boolean z) {
        this.f19583je = z;
        return this;
    }

    /* renamed from: mb */
    public C10149u m6641mb(IDownloadListener iDownloadListener) {
        this.f19597nk = iDownloadListener;
        return this;
    }

    /* renamed from: hj */
    public C10149u m6670hj(String str) {
        this.f19590lc = str;
        return this;
    }

    /* renamed from: h */
    public C10149u m6674h(String str) {
        this.f19581io = str;
        return this;
    }

    /* renamed from: hj */
    public C10149u m6669hj(boolean z) {
        this.f19570e = z;
        return this;
    }

    /* renamed from: h */
    public C10149u m6673h(boolean z) {
        this.f19569df = z;
        return this;
    }

    /* renamed from: u */
    public C10149u m6609u(boolean z) {
        this.f19606r = z;
        return this;
    }

    /* renamed from: u */
    public C10149u m6610u(String str) {
        this.f19600on = str;
        return this;
    }

    /* renamed from: ko */
    public C10149u m6657ko(String str) {
        this.f19584jq = str;
        return this;
    }

    /* renamed from: mb */
    public C10149u m6646mb(long j) {
        this.f19564a = j;
        return this;
    }

    /* renamed from: ox */
    public C10149u m6625ox(int i) {
        this.f19575ge = i;
        return this;
    }

    /* renamed from: b */
    public C10149u m6689b(int i) {
        this.f19619xa = i;
        return this;
    }

    /* renamed from: ko */
    public C10149u m6656ko(boolean z) {
        this.f19595nf = z;
        return this;
    }

    /* renamed from: ww */
    public C10149u m6603ww(String str) {
        this.f19573fu = str;
        return this;
    }

    /* renamed from: ww */
    public C10149u m6602ww(boolean z) {
        this.f19572fb = z;
        return this;
    }

    /* renamed from: lz */
    public C10149u m6650lz(boolean z) {
        this.f19571ep = z;
        return this;
    }

    /* renamed from: x */
    public C10149u m6599x(boolean z) {
        this.f19610sw = z;
        return this;
    }

    /* renamed from: jb */
    public C10149u m6664jb(boolean z) {
        this.f19616wn = z;
        return this;
    }

    /* renamed from: je */
    public C10149u m6662je(boolean z) {
        this.f19604q = z;
        return this;
    }

    /* renamed from: hj */
    public C10149u m6671hj(int i) {
        this.f19615w = i;
        return this;
    }

    /* renamed from: h */
    public C10149u m6675h(int i) {
        this.f19565al = i;
        return this;
    }

    /* renamed from: nk */
    public C10149u m6632nk(boolean z) {
        this.f19614vy = z;
        return this;
    }

    /* renamed from: mb */
    public C10149u m6645mb(EnqueueType enqueueType) {
        this.f19567bv = enqueueType;
        return this;
    }

    /* renamed from: o */
    public C10149u m6629o(boolean z) {
        this.f19596ng = z;
        return this;
    }

    /* renamed from: mb */
    public C10149u m6642mb(IDownloadFileUriProvider iDownloadFileUriProvider) {
        this.f19580i = iDownloadFileUriProvider;
        return this;
    }

    /* renamed from: lz */
    public C10149u m6651lz(String str) {
        this.f19587km = str;
        return this;
    }

    /* renamed from: x */
    public C10149u m6600x(String str) {
        this.f19611tl = str;
        return this;
    }

    /* renamed from: up */
    public String m6608up() {
        return this.f19611tl;
    }

    /* renamed from: u */
    public C10149u m6611u(@ExecutorGroup int i) {
        this.f19607s = i;
        return this;
    }

    /* renamed from: nq */
    public List<String> m6631nq() {
        return this.f19579hj;
    }

    /* renamed from: ox */
    public C10149u m6623ox(List<String> list) {
        this.f19579hj = list;
        return this;
    }

    /* renamed from: kk */
    public IDownloadDiskSpaceHandler m6659kk() {
        return this.f19601ot;
    }

    /* renamed from: mb */
    public C10149u m6643mb(IDownloadDiskSpaceHandler iDownloadDiskSpaceHandler) {
        this.f19601ot = iDownloadDiskSpaceHandler;
        return this;
    }

    /* renamed from: kg */
    public JSONObject m6660kg() {
        return this.f19605qa;
    }

    /* renamed from: mb */
    public C10149u m6638mb(JSONObject jSONObject) {
        this.f19605qa = jSONObject;
        return this;
    }

    /* renamed from: mb */
    public C10149u m6644mb(IDownloadCompleteHandler iDownloadCompleteHandler) {
        synchronized (this.f19594n) {
            if (iDownloadCompleteHandler != null) {
                if (!this.f19594n.contains(iDownloadCompleteHandler)) {
                    this.f19594n.add(iDownloadCompleteHandler);
                    return this;
                }
            }
            return this;
        }
    }

    /* renamed from: lc */
    public C10149u m6653lc(boolean z) {
        this.f19598nq = z;
        return this;
    }

    /* renamed from: io */
    public C10149u m6666io(boolean z) {
        this.f19586kk = z;
        return this;
    }
}
