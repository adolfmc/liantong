package com.alipay.sdk.app.statistic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.sys.C2033b;
import com.alipay.sdk.tid.C2035b;
import com.alipay.sdk.util.C2037a;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.app.statistic.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2002c {

    /* renamed from: A */
    public static final String f3603A = "ClientBindException";

    /* renamed from: B */
    public static final String f3604B = "SaveTradeTokenError";

    /* renamed from: C */
    public static final String f3605C = "ClientBindServiceFailed";

    /* renamed from: D */
    public static final String f3606D = "BindWaitTimeoutEx";

    /* renamed from: E */
    public static final String f3607E = "CheckClientExistEx";

    /* renamed from: F */
    public static final String f3608F = "CheckClientSignEx";

    /* renamed from: G */
    public static final String f3609G = "GetInstalledAppEx";

    /* renamed from: H */
    public static final String f3610H = "ParserTidClientKeyEx";

    /* renamed from: I */
    public static final String f3611I = "GetInstalledAppEx";

    /* renamed from: J */
    public static final String f3612J = "StartLaunchAppTransEx";

    /* renamed from: K */
    public static final String f3613K = "CheckLaunchAppExistEx";

    /* renamed from: L */
    public static final String f3614L = "LogCurrentAppLaunchSwitch";

    /* renamed from: M */
    public static final String f3615M = "LogCurrentQueryTime";

    /* renamed from: N */
    public static final String f3616N = "LogCalledPackage";

    /* renamed from: O */
    public static final String f3617O = "LogBindCalledH5";

    /* renamed from: P */
    public static final String f3618P = "LogCalledH5";

    /* renamed from: Q */
    public static final String f3619Q = "LogHkLoginByIntent";

    /* renamed from: R */
    public static final String f3620R = "SchemePayWrongHashEx";

    /* renamed from: S */
    public static final String f3621S = "LogAppLaunchSwitchEnabled";

    /* renamed from: T */
    public static final String f3622T = "H5CbUrlEmpty";

    /* renamed from: U */
    public static final String f3623U = "H5CbEx";

    /* renamed from: V */
    public static final String f3624V = "BuildSchemePayUriError";

    /* renamed from: W */
    public static final String f3625W = "StartActivityEx";

    /* renamed from: X */
    public static final String f3626X = "JSONEx";

    /* renamed from: Y */
    public static final String f3627Y = "ParseBundleSerializableError";

    /* renamed from: Z */
    public static final String f3628Z = "ParseSchemeQueryError";

    /* renamed from: a */
    public static final String f3629a = "net";

    /* renamed from: aa */
    public static final String f3630aa = "tid_context_null";

    /* renamed from: ab */
    public static final String f3631ab = "partner";

    /* renamed from: ac */
    public static final String f3632ac = "out_trade_no";

    /* renamed from: ad */
    public static final String f3633ad = "trade_no";

    /* renamed from: b */
    public static final String f3634b = "biz";

    /* renamed from: c */
    public static final String f3635c = "cp";

    /* renamed from: d */
    public static final String f3636d = "auth";

    /* renamed from: e */
    public static final String f3637e = "third";

    /* renamed from: f */
    public static final String f3638f = "tid";

    /* renamed from: g */
    public static final String f3639g = "FormatResultEx";

    /* renamed from: h */
    public static final String f3640h = "GetApdidEx";

    /* renamed from: i */
    public static final String f3641i = "GetApdidNull";

    /* renamed from: j */
    public static final String f3642j = "GetApdidTimeout";

    /* renamed from: k */
    public static final String f3643k = "GetUtdidEx";

    /* renamed from: l */
    public static final String f3644l = "GetPackageInfoEx";

    /* renamed from: m */
    public static final String f3645m = "NotIncludeSignatures";

    /* renamed from: n */
    public static final String f3646n = "GetInstalledPackagesEx";

    /* renamed from: o */
    public static final String f3647o = "GetPublicKeyFromSignEx";

    /* renamed from: p */
    public static final String f3648p = "H5PayNetworkError";

    /* renamed from: q */
    public static final String f3649q = "H5AuthNetworkError";

    /* renamed from: r */
    public static final String f3650r = "SSLError";

    /* renamed from: s */
    public static final String f3651s = "SSLProceed";

    /* renamed from: t */
    public static final String f3652t = "SSLDenied";

    /* renamed from: u */
    public static final String f3653u = "H5PayDataAnalysisError";

    /* renamed from: v */
    public static final String f3654v = "H5AuthDataAnalysisError";

    /* renamed from: w */
    public static final String f3655w = "PublicKeyUnmatch";

    /* renamed from: x */
    public static final String f3656x = "ClientBindFailed";

    /* renamed from: y */
    public static final String f3657y = "TriDesEncryptError";

    /* renamed from: z */
    public static final String f3658z = "TriDesDecryptError";

    /* renamed from: ae */
    private String f3659ae;

    /* renamed from: af */
    private String f3660af;

    /* renamed from: ag */
    private String f3661ag;

    /* renamed from: ah */
    private String f3662ah;

    /* renamed from: ai */
    private String f3663ai;

    /* renamed from: aj */
    private String f3664aj;

    /* renamed from: ak */
    private String f3665ak;

    /* renamed from: al */
    private String f3666al;

    /* renamed from: am */
    private String f3667am = "";

    /* renamed from: an */
    private String f3668an;

    public C2002c(Context context) {
        context = context != null ? context.getApplicationContext() : context;
        this.f3659ae = m20886b();
        this.f3661ag = m20893a(context);
        this.f3662ah = m20883c();
        this.f3663ai = m20881d();
        this.f3664aj = m20885b(context);
        this.f3665ak = "-";
        this.f3666al = "-";
        this.f3668an = "-";
    }

    /* renamed from: a */
    public boolean m20894a() {
        return TextUtils.isEmpty(this.f3667am);
    }

    /* renamed from: a */
    public void m20889a(String str, String str2, Throwable th) {
        m20891a(str, str2, m20887a(th));
    }

    /* renamed from: a */
    public void m20888a(String str, String str2, Throwable th, String str3) {
        m20890a(str, str2, m20887a(th), str3);
    }

    /* renamed from: a */
    public void m20890a(String str, String str2, String str3, String str4) {
        String str5 = "";
        if (!TextUtils.isEmpty(this.f3667am)) {
            str5 = "^";
        }
        this.f3667am += (str5 + String.format("%s,%s,%s,%s", str, str2, m20884b(str3), str4));
    }

    /* renamed from: a */
    public void m20891a(String str, String str2, String str3) {
        m20890a(str, str2, str3, "-");
    }

    /* renamed from: b */
    private String m20884b(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(",", "，").replace("-", "=").replace("^", "~");
    }

    /* renamed from: a */
    private String m20887a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName());
            stringBuffer.append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (int i = 0; i < stackTrace.length; i++) {
                    stringBuffer.append(stackTrace[i].toString() + " 》 ");
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public String m20892a(String str) {
        if (m20894a()) {
            return "";
        }
        this.f3660af = m20882c(str);
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", this.f3659ae, this.f3660af, this.f3661ag, this.f3662ah, this.f3663ai, this.f3664aj, this.f3665ak, this.f3666al, this.f3667am, this.f3668an);
    }

    @SuppressLint({"SimpleDateFormat"})
    /* renamed from: b */
    private String m20886b() {
        return String.format("123456789,%s", new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
    }

    /* renamed from: c */
    private String m20882c(String str) {
        String str2;
        String[] split = str.split("&");
        String str3 = null;
        if (split != null) {
            str2 = null;
            String str4 = null;
            for (String str5 : split) {
                String[] split2 = str5.split("=");
                if (split2 != null && split2.length == 2) {
                    if (split2[0].equalsIgnoreCase("partner")) {
                        split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase("out_trade_no")) {
                        str2 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase("trade_no")) {
                        str4 = split2[1].replace("\"", "");
                    }
                }
            }
            str3 = str4;
        } else {
            str2 = null;
        }
        String m20884b = m20884b(str3);
        String m20884b2 = m20884b(str2);
        return String.format("%s,%s,-,%s,-,-,-", m20884b, m20884b2, m20884b(m20884b2));
    }

    /* renamed from: a */
    private String m20893a(Context context) {
        String str = "-";
        String str2 = "-";
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                str = applicationContext.getPackageName();
                str2 = applicationContext.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (Throwable unused) {
            }
        }
        return String.format("%s,%s,-,-,-", str, str2);
    }

    /* renamed from: c */
    private String m20883c() {
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", m20884b("15.6.8"), m20884b("h.a.3.6.8"));
    }

    /* renamed from: d */
    private String m20881d() {
        return String.format("%s,%s,-,-,-", m20884b(C2035b.m20758a(C2033b.m20772a().m20770b()).m20759a()), m20884b(C2033b.m20772a().m20767e()));
    }

    /* renamed from: b */
    private String m20885b(Context context) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", m20884b(C2037a.m20720d(context)), "android", m20884b(Build.VERSION.RELEASE), m20884b(Build.MODEL), "-", m20884b(C2037a.m20728a(context).m20729a()), m20884b(C2037a.m20725b(context).m20706b()), "gw", m20884b(C2037a.m20728a(context).m20726b()));
    }
}
