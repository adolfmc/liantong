package com.mob.tools.p238b;

import android.content.Intent;
import android.os.Looper;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5741aa;
import com.mob.commons.C5747b;
import com.mob.commons.C5829d;
import com.mob.commons.C5868q;
import com.mob.commons.CSCenter;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.p237a.C6031c;
import com.mob.tools.p237a.C6032d;
import com.mob.tools.p237a.InterfaceC6028a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.tools.b.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6120a {

    /* renamed from: a */
    public static ThreadLocal<Boolean> f14982a = new ThreadLocal<>();

    /* renamed from: b */
    public static ThreadLocal<Boolean> f14983b = new ThreadLocal<>();

    /* renamed from: c */
    public static ThreadLocal<Boolean> f14984c = new ThreadLocal<>();

    /* renamed from: e */
    private static volatile String f14986e = null;

    /* renamed from: d */
    private static final List<String> f14985d = Arrays.asList("bgmdl", "gmnft", "gbrd", "govsit", "govsnm", "golgu", "gocnty", "galgu", "gtmne", "gsnmd", "gpgnm", "gpnmmt", "gpvsnm", "gpvsme", "cinmnps", "ckpmsi", "gaplcn", "gpgif", "gpgiffist", "gcrtpcnm", "gscpt", "cird", "cknavbl", "ipgist", "ckua", "ubenbl", "dvenbl", "vnmt", "iwpxy", "cx", "degb", "gdtlnktpfs", "gpgiffcin", "gpgifstrg", "gtaif", "gtaifprm", "rsaciy", "gsnmdfp", "gcrie", "gcriefce", "gdvk", "gdvkfc", "godhm", "godm", "gmpfis");

    @InterfaceC6121b
    /* renamed from: a */
    public static Object m11364a(String str, ArrayList<Object> arrayList) {
        try {
            return m11362b(str, arrayList);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* renamed from: b */
    private static Object m11362b(String str, ArrayList<Object> arrayList) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        InterfaceC6028a m11365a = m11365a(str);
        if ("gmpfis".equals(str)) {
            if (arrayList != null && arrayList.size() == 4) {
                return m11365a.mo11539b(((Boolean) arrayList.get(0)).booleanValue(), ((Integer) arrayList.get(1)).intValue(), (String) arrayList.get(2), ((Integer) arrayList.get(3)).intValue());
            }
            throw new Throwable("array illegal: " + arrayList);
        } else if ("cird".equals(str)) {
            return Boolean.valueOf(m11365a.mo11590a());
        } else {
            if ("cx".equals(str)) {
                return Boolean.valueOf(m11365a.mo11547b());
            }
            if ("ckpd".equals(str)) {
                return Boolean.valueOf(m11365a.mo11538c());
            }
            if ("degb".equals(str)) {
                return Boolean.valueOf(m11365a.mo11534d());
            }
            if ("vnmt".equals(str)) {
                return Boolean.valueOf(m11365a.mo11531e());
            }
            if ("ckua".equals(str)) {
                return Boolean.valueOf(m11365a.mo11528f());
            }
            if ("dvenbl".equals(str)) {
                return Boolean.valueOf(m11365a.mo11525g());
            }
            if ("ubenbl".equals(str)) {
                return Boolean.valueOf(m11365a.mo11522h());
            }
            if ("iwpxy".equals(str)) {
                return Boolean.valueOf(m11365a.mo11520i());
            }
            if ("gavti".equals(str)) {
                return m11365a.mo11519j();
            }
            if ("gsimt".equals(str)) {
                return m11365a.mo11575a(false);
            }
            if ("gsimtfce".equals(str)) {
                if (arrayList != null && arrayList.size() == 1) {
                    return m11365a.mo11575a(((Boolean) arrayList.get(0)).booleanValue());
                }
                throw new Throwable("array illegal: " + arrayList);
            } else if ("gbsi".equals(str)) {
                return m11365a.mo11540b(false);
            } else {
                if ("gbsifce".equals(str)) {
                    if (arrayList != null && arrayList.size() == 1) {
                        return m11365a.mo11540b(((Boolean) arrayList.get(0)).booleanValue());
                    }
                    throw new Throwable("array illegal: " + arrayList);
                } else if ("gcrie".equals(str)) {
                    return m11365a.mo11535c(false);
                } else {
                    if ("gcriefce".equals(str)) {
                        if (arrayList != null && arrayList.size() == 1) {
                            return m11365a.mo11535c(((Boolean) arrayList.get(0)).booleanValue());
                        }
                        throw new Throwable("array illegal: " + arrayList);
                    } else if ("gcrnmfce".equals(str)) {
                        if (arrayList != null && arrayList.size() == 1) {
                            return m11365a.mo11532d(((Boolean) arrayList.get(0)).booleanValue());
                        }
                        throw new Throwable("array illegal: " + arrayList);
                    } else if ("gcrnm".equals(str)) {
                        return m11365a.mo11532d(false);
                    } else {
                        if ("gmivsn".equals(str)) {
                            return m11365a.mo11518k();
                        }
                        if ("bgmdl".equals(str)) {
                            return m11365a.mo11517l();
                        }
                        if ("gmnft".equals(str)) {
                            return m11365a.mo11516m();
                        }
                        if ("gbrd".equals(str)) {
                            return m11365a.mo11515n();
                        }
                        if ("gdvtp".equals(str)) {
                            return m11365a.mo11514o();
                        }
                        if ("gtecloc".equals(str)) {
                            return m11365a.mo11513p();
                        }
                        if ("gnbclin".equals(str)) {
                            return m11365a.mo11512q();
                        }
                        if ("wmcwi".equals(str)) {
                            return m11365a.mo11529e(false);
                        }
                        if ("wmcwifce".equals(str)) {
                            if (arrayList != null && arrayList.size() == 1) {
                                return m11365a.mo11529e(((Boolean) arrayList.get(0)).booleanValue());
                            }
                            throw new Throwable("array illegal: " + arrayList);
                        } else if ("govsit".equals(str)) {
                            return Integer.valueOf(m11365a.mo11510s());
                        } else {
                            if ("govsnm".equals(str)) {
                                return m11365a.mo11509t();
                            }
                            if ("golgu".equals(str)) {
                                return m11365a.mo11508u();
                            }
                            if ("gocnty".equals(str)) {
                                return m11365a.mo11507v();
                            }
                            if ("gcuin".equals(str)) {
                                return m11365a.mo11506w();
                            }
                            if ("gtydvin".equals(str)) {
                                return m11365a.mo11505x();
                            }
                            if ("gqmkn".equals(str)) {
                                return m11365a.mo11504y();
                            }
                            if ("gszin".equals(str)) {
                                return m11365a.mo11503z();
                            }
                            if ("gmrin".equals(str)) {
                                return m11365a.mo11616A();
                            }
                            if ("galgu".equals(str)) {
                                return m11365a.mo11615B();
                            }
                            if ("gscsz".equals(str)) {
                                return m11365a.mo11614C();
                            }
                            if ("gneyp".equals(str)) {
                                return m11365a.mo11526f(false);
                            }
                            if ("gneypnw".equals(str)) {
                                return m11365a.mo11613D();
                            }
                            if ("gneypfce".equals(str)) {
                                if (arrayList != null && arrayList.size() == 1) {
                                    return m11365a.mo11526f(((Boolean) arrayList.get(0)).booleanValue());
                                }
                                throw new Throwable("array illegal: " + arrayList);
                            } else if ("gnktpfs".equals(str)) {
                                return m11365a.mo11612E();
                            } else {
                                if ("gdtlnktpfs".equals(str)) {
                                    return m11365a.mo11611F();
                                }
                                if ("cknavbl".equals(str)) {
                                    return Boolean.valueOf(m11365a.mo11610G());
                                }
                                if ("gdntp".equals(str)) {
                                    return Integer.valueOf(m11365a.mo11609H());
                                }
                                if ("gtmne".equals(str)) {
                                    return m11365a.mo11608I();
                                }
                                if ("gflv".equals(str)) {
                                    return m11365a.mo11607J();
                                }
                                if ("gbsbd".equals(str)) {
                                    return m11365a.mo11606K();
                                }
                                if ("gbfspy".equals(str)) {
                                    return m11365a.mo11605L();
                                }
                                if ("gbplfo".equals(str)) {
                                    return m11365a.mo11604M();
                                }
                                if ("giads".equals(str)) {
                                    return m11365a.mo11603N();
                                }
                                if ("gia".equals(str)) {
                                    if (C5747b.m12585a(C5868q.m12203b("003cff")) && C5741aa.m12650a().m12616h() != 42) {
                                        if (arrayList != null && arrayList.size() == 1) {
                                            return m11365a.mo11571a(((Boolean) arrayList.get(0)).booleanValue(), false);
                                        }
                                        throw new Throwable("array illegal: " + arrayList);
                                    }
                                    return new ArrayList();
                                } else if ("giafce".equals(str)) {
                                    if (C5747b.m12585a(C5868q.m12203b("003cff")) && C5741aa.m12650a().m12616h() != 42) {
                                        if (arrayList != null && arrayList.size() == 2) {
                                            return m11365a.mo11571a(((Boolean) arrayList.get(0)).booleanValue(), ((Boolean) arrayList.get(1)).booleanValue());
                                        }
                                        throw new Throwable("array illegal: " + arrayList);
                                    }
                                    return new ArrayList();
                                } else if ("gal".equals(str)) {
                                    if (C5747b.m12585a(C5868q.m12203b("003cff")) && C5741aa.m12650a().m12616h() != 42) {
                                        return m11365a.mo11602O();
                                    }
                                    return new ArrayList();
                                } else if ("gsl".equals(str)) {
                                    if (C5747b.m12585a(C5868q.m12203b("003cff")) && C5741aa.m12650a().m12616h() != 42) {
                                        return m11365a.mo11601P();
                                    }
                                    return new ArrayList();
                                } else if ("glctn".equals(str)) {
                                    if (arrayList != null && arrayList.size() == 3) {
                                        return m11365a.mo11589a(((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(1)).intValue(), ((Boolean) arrayList.get(2)).booleanValue());
                                    }
                                    throw new Throwable("array illegal: " + arrayList);
                                } else if ("gstmpts".equals(str)) {
                                    if (arrayList != null && arrayList.size() == 1) {
                                        return m11365a.mo11582a((String) arrayList.get(0));
                                    }
                                    throw new Throwable("array illegal: " + arrayList);
                                } else if ("gdvk".equals(str)) {
                                    return m11365a.mo11600Q();
                                } else {
                                    if ("gdvkfc".equals(str)) {
                                        if (arrayList != null && arrayList.size() == 1) {
                                            return m11365a.mo11523g(((Boolean) arrayList.get(0)).booleanValue());
                                        }
                                        throw new Throwable("array illegal: " + arrayList);
                                    } else if ("ipgist".equals(str)) {
                                        if (arrayList != null && arrayList.size() == 1) {
                                            return Boolean.valueOf(m11365a.mo11543b((String) arrayList.get(0)));
                                        }
                                        throw new Throwable("array illegal: " + arrayList);
                                    } else if ("gscpt".equals(str)) {
                                        return m11365a.mo11599R();
                                    } else {
                                        if ("gsnmd".equals(str)) {
                                            return m11365a.mo11598S();
                                        }
                                        if ("gsnmdfp".equals(str)) {
                                            if (arrayList != null && arrayList.size() == 1) {
                                                return m11365a.mo11537c((String) arrayList.get(0));
                                            }
                                            throw new Throwable("array illegal: " + arrayList);
                                        } else if ("gpgnm".equals(str)) {
                                            return m11365a.mo11597T();
                                        } else {
                                            if ("gpnmmt".equals(str)) {
                                                return m11365a.mo11596U();
                                            }
                                            if ("gpnmfp".equals(str)) {
                                                if (arrayList != null && arrayList.size() == 1) {
                                                    return m11365a.mo11533d((String) arrayList.get(0));
                                                }
                                                throw new Throwable("array illegal: " + arrayList);
                                            } else if ("gpvsnm".equals(str)) {
                                                return Integer.valueOf(m11365a.mo11595V());
                                            } else {
                                                if ("gpvsme".equals(str)) {
                                                    return m11365a.mo11594W();
                                                }
                                                if ("cinmnps".equals(str)) {
                                                    return Boolean.valueOf(m11365a.mo11593X());
                                                }
                                                if ("gcrtpcnm".equals(str)) {
                                                    return m11365a.mo11592Y();
                                                }
                                                if ("ciafgd".equals(str)) {
                                                    return Boolean.valueOf(m11365a.mo11591Z());
                                                }
                                                if ("ckpmsi".equals(str)) {
                                                    if (arrayList != null && arrayList.size() == 1) {
                                                        return Boolean.valueOf(m11365a.mo11530e((String) arrayList.get(0)));
                                                    }
                                                    throw new Throwable("array illegal: " + arrayList);
                                                } else if ("gaplcn".equals(str)) {
                                                    return m11365a.mo11570aa();
                                                } else {
                                                    if ("qritsvc".equals(str)) {
                                                        if (arrayList != null && arrayList.size() == 2) {
                                                            return m11365a.mo11588a((Intent) arrayList.get(0), ((Integer) arrayList.get(1)).intValue());
                                                        }
                                                        throw new Throwable("array illegal: " + arrayList);
                                                    } else if ("rsaciy".equals(str)) {
                                                        if (arrayList != null && arrayList.size() == 2) {
                                                            return m11365a.mo11545b((Intent) arrayList.get(0), ((Integer) arrayList.get(1)).intValue());
                                                        }
                                                        throw new Throwable("array illegal: " + arrayList);
                                                    } else if ("gpgif".equals(str)) {
                                                        if (arrayList != null && arrayList.size() == 2) {
                                                            return m11365a.mo11574a(false, 0, (String) arrayList.get(0), ((Integer) arrayList.get(1)).intValue());
                                                        }
                                                        throw new Throwable("array illegal: " + arrayList);
                                                    } else if ("gpgiffcin".equals(str)) {
                                                        if (arrayList != null && arrayList.size() == 3) {
                                                            return m11365a.mo11574a(((Boolean) arrayList.get(0)).booleanValue(), 0, (String) arrayList.get(1), ((Integer) arrayList.get(2)).intValue());
                                                        }
                                                        throw new Throwable("array illegal: " + arrayList);
                                                    } else if ("gpgifstrg".equals(str)) {
                                                        if (arrayList != null && arrayList.size() == 3) {
                                                            return m11365a.mo11574a(false, ((Integer) arrayList.get(0)).intValue(), (String) arrayList.get(1), ((Integer) arrayList.get(2)).intValue());
                                                        }
                                                        throw new Throwable("array illegal: " + arrayList);
                                                    } else if ("gpgiffist".equals(str)) {
                                                        if (arrayList != null && arrayList.size() == 4) {
                                                            return m11365a.mo11574a(((Boolean) arrayList.get(0)).booleanValue(), ((Integer) arrayList.get(1)).intValue(), (String) arrayList.get(2), ((Integer) arrayList.get(3)).intValue());
                                                        }
                                                        throw new Throwable("array illegal: " + arrayList);
                                                    } else if ("gdvda".equals(str)) {
                                                        return m11365a.mo11569ab();
                                                    } else {
                                                        if ("gdvdtnas".equals(str)) {
                                                            return m11365a.mo11568ac();
                                                        }
                                                        if ("galtut".equals(str)) {
                                                            return Long.valueOf(m11365a.mo11567ad());
                                                        }
                                                        if ("gcrup".equals(str)) {
                                                            return m11365a.mo11565af();
                                                        }
                                                        if ("gcifm".equals(str)) {
                                                            return m11365a.mo11564ag();
                                                        }
                                                        if ("godm".equals(str)) {
                                                            String mo11563ah = m11365a.mo11563ah();
                                                            if (TextUtils.isEmpty(f14986e)) {
                                                                f14986e = C5741aa.m12650a().m12632b("key_ched_od", (String) null);
                                                            }
                                                            if (TextUtils.isEmpty(mo11563ah) || CSCenter.getInstance().invocationRecord().m12826a()) {
                                                                return (C5829d.m12332a() || TextUtils.isEmpty(f14986e)) ? mo11563ah : f14986e;
                                                            } else if (TextUtils.equals(f14986e, mo11563ah)) {
                                                                return mo11563ah;
                                                            } else {
                                                                f14986e = mo11563ah;
                                                                C5741aa.m12650a().m12641a("key_ched_od", mo11563ah);
                                                                return mo11563ah;
                                                            }
                                                        } else if ("godhm".equals(str)) {
                                                            return m11365a.mo11562ai();
                                                        } else {
                                                            if ("galdm".equals(str)) {
                                                                return m11365a.mo11561aj();
                                                            }
                                                            if ("gtaif".equals(str)) {
                                                                return m11365a.mo11560ak();
                                                            }
                                                            if ("gtaifok".equals(str)) {
                                                                return m11365a.mo11559al();
                                                            }
                                                            if ("gtaifprm".equals(str)) {
                                                                if (arrayList != null && arrayList.size() == 2) {
                                                                    return m11365a.mo11581a((String) arrayList.get(0), ((Integer) arrayList.get(1)).intValue());
                                                                }
                                                                throw new Throwable("array illegal: " + arrayList);
                                                            } else if ("gtaifprmfce".equals(str)) {
                                                                if (arrayList != null && arrayList.size() == 3) {
                                                                    return m11365a.mo11573a(((Boolean) arrayList.get(0)).booleanValue(), (String) arrayList.get(1), ((Integer) arrayList.get(2)).intValue());
                                                                }
                                                                throw new Throwable("array illegal: " + arrayList);
                                                            } else if ("gtbdt".equals(str)) {
                                                                return Long.valueOf(m11365a.mo11558am());
                                                            } else {
                                                                if ("gtscnin".equals(str)) {
                                                                    return Double.valueOf(m11365a.mo11557an());
                                                                }
                                                                if ("gtscnppi".equals(str)) {
                                                                    return Integer.valueOf(m11365a.mo11556ao());
                                                                }
                                                                if ("ishmos".equals(str)) {
                                                                    return Boolean.valueOf(m11365a.mo11555ap());
                                                                }
                                                                if ("gthmosv".equals(str)) {
                                                                    return m11365a.mo11554aq();
                                                                }
                                                                if ("gthmosdtlv".equals(str)) {
                                                                    return m11365a.mo11553ar();
                                                                }
                                                                if ("gthmpmst".equals(str)) {
                                                                    return Integer.valueOf(m11365a.mo11552as());
                                                                }
                                                                if ("gthmepmst".equals(str)) {
                                                                    return Integer.valueOf(m11365a.mo11551at());
                                                                }
                                                                if ("gtinnerlangmt".equals(str)) {
                                                                    return m11365a.mo11550au();
                                                                }
                                                                if ("gtgramgendt".equals(str)) {
                                                                    return Integer.valueOf(m11365a.mo11549av());
                                                                }
                                                                if ("ctedebbing".equals(str)) {
                                                                    return Boolean.valueOf(m11365a.mo11548aw());
                                                                }
                                                                if ("gtelcme".equals(str)) {
                                                                    if (arrayList != null && arrayList.size() == 3) {
                                                                        return m11365a.mo11546b(((Integer) arrayList.get(0)).intValue(), ((Integer) arrayList.get(1)).intValue(), ((Boolean) arrayList.get(2)).booleanValue());
                                                                    }
                                                                    throw new Throwable("array illegal: " + arrayList);
                                                                }
                                                                NLog mobLog = MobLog.getInstance();
                                                                mobLog.m11342d("Not found: " + str, new Object[0]);
                                                                return null;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static InterfaceC6028a m11365a(String str) {
        CountDownLatch m11678d;
        CountDownLatch m11678d2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11326w("WARNING: Call in main: key = " + str);
            m11363b();
        }
        if (!(f14982a.get() == null ? false : f14982a.get().booleanValue())) {
            if (!f14985d.contains(str) && !C6032d.m11682c() && (m11678d2 = C6032d.m11700a(MobSDK.getContext()).m11678d()) != null) {
                try {
                    NLog mobLog2 = MobLog.getInstance();
                    mobLog2.m11342d("dhs_ivkr k: " + str + ", cdl: " + m11678d2, new Object[0]);
                    m11678d2.await(3500L, TimeUnit.MILLISECONDS);
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                }
            }
        } else {
            boolean booleanValue = f14983b.get() == null ? false : f14983b.get().booleanValue();
            boolean booleanValue2 = f14984c.get() == null ? false : f14984c.get().booleanValue();
            if (booleanValue) {
                MobLog.getInstance().m11342d("isGCFThread true", new Object[0]);
            }
            if (!booleanValue && !booleanValue2 && !C6032d.m11682c() && (m11678d = C6032d.m11700a(MobSDK.getContext()).m11678d()) != null) {
                try {
                    NLog mobLog3 = MobLog.getInstance();
                    mobLog3.m11342d("dhs_ivkr_new k: " + str + ", cdl: " + m11678d, new Object[0]);
                    m11678d.await(3500L, TimeUnit.MILLISECONDS);
                } catch (Throwable th2) {
                    MobLog.getInstance().m11341d(th2);
                }
            }
        }
        return m11366a();
    }

    /* renamed from: a */
    private static InterfaceC6028a m11366a() {
        if (C6032d.m11682c()) {
            return C6031c.m11708a(MobSDK.getContext()).m11703e();
        }
        return C6031c.m11708a(MobSDK.getContext()).m11705c();
    }

    /* renamed from: b */
    private static void m11363b() {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null) {
                String str = "";
                for (StackTraceElement stackTraceElement : stackTrace) {
                    if (stackTraceElement != null) {
                        str = str + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")\n";
                    }
                }
                MobLog.getInstance().m11342d(str, new Object[0]);
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }
}
