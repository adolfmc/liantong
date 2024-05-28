package com.mob.tools.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import com.mob.MobSDK;
import com.mob.commons.C5873u;
import com.mob.commons.C5892y;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.p237a.C6031c;
import com.mob.tools.p238b.C6120a;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.tools.utils.DH */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6152DH {
    public static final int GPI_STRATEGY_VALIDITY_3_MINUTE = 180000;
    public static final int GPI_STRATEGY_VALIDITY_ALL = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.utils.DH$DHResponder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface DHResponder {
        void onResponse(DHResponse dHResponse) throws Throwable;
    }

    public static RequestBuilder requester(Context context) {
        return new RequestBuilder(context);
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* renamed from: com.mob.tools.utils.DH$RequestBuilder */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class RequestBuilder {

        /* renamed from: a */
        private final Context f15162a;

        /* renamed from: b */
        private final LinkedList<C6156a> f15163b;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: 5 in method: com.mob.tools.utils.DH.RequestBuilder.a(java.lang.String, java.lang.Object[]):java.lang.Object, file: E:\11480076_dexfile_execute.dex.fixout.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            Caused by: java.lang.ArrayIndexOutOfBoundsException: 5
            	at jadx.plugins.input.dex.insns.DexInsnData.toString(DexInsnData.java:251)
            	at java.lang.String.valueOf(String.java:2994)
            	at java.lang.StringBuilder.append(StringBuilder.java:131)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:55)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	... 6 more
            */
        /* renamed from: a */
        private java.lang.Object m11279a(java.lang.String r1, java.lang.Object[] r2) throws java.lang.Throwable {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: 5 in method: com.mob.tools.utils.DH.RequestBuilder.a(java.lang.String, java.lang.Object[]):java.lang.Object, file: E:\11480076_dexfile_execute.dex.fixout.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.C6152DH.RequestBuilder.m11279a(java.lang.String, java.lang.Object[]):java.lang.Object");
        }

        public RequestBuilder getBtM() {
            return this;
        }

        public RequestBuilder getDeviceId() {
            return this;
        }

        public RequestBuilder getDrID() {
            return this;
        }

        public RequestBuilder getIMEI() {
            return this;
        }

        public RequestBuilder getIMSI() {
            return this;
        }

        public RequestBuilder getSdcardState() {
            return this;
        }

        public RequestBuilder getSerialno() {
            return this;
        }

        public RequestBuilder getSimSerialNumber() {
            return this;
        }

        public RequestBuilder getTopActivity() {
            return this;
        }

        public RequestBuilder getUpM() {
            return this;
        }

        public RequestBuilder queryIMEI() {
            return this;
        }

        public RequestBuilder queryIMSI() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.mob.tools.utils.DH$RequestBuilder$a */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class C6156a {

            /* renamed from: a */
            public final String f15171a;

            /* renamed from: b */
            public final Object[] f15172b;

            private C6156a(String str, Object... objArr) {
                this.f15171a = str;
                this.f15172b = objArr;
            }
        }

        private RequestBuilder(Context context) {
            this.f15163b = new LinkedList<>();
            this.f15162a = context;
        }

        public void request(final DHResponder dHResponder) {
            try {
                boolean z = Looper.getMainLooper() == Looper.myLooper();
                final Boolean bool = C6120a.f14983b.get();
                final Boolean bool2 = C6120a.f14984c.get();
                final boolean z2 = z;
                Runnable runnable = new Runnable() { // from class: com.mob.tools.utils.DH.RequestBuilder.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            C6120a.f14982a.set(true);
                            C6120a.f14983b.set(bool);
                            C6120a.f14984c.set(bool2);
                            final DHResponse m11283a = RequestBuilder.this.m11283a();
                            if (dHResponder != null) {
                                if (z2) {
                                    UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.tools.utils.DH.RequestBuilder.1.1
                                        @Override // android.os.Handler.Callback
                                        public boolean handleMessage(Message message) {
                                            try {
                                                dHResponder.onResponse(m11283a);
                                            } catch (Throwable th) {
                                                MobLog.getInstance().m11340d(th, "Error from caller", new Object[0]);
                                            }
                                            return false;
                                        }
                                    });
                                } else {
                                    dHResponder.onResponse(m11283a);
                                }
                            }
                            C6120a.f14982a.set(false);
                            C6120a.f14983b.set(false);
                            C6120a.f14984c.set(false);
                        } catch (Throwable th) {
                            MobLog.getInstance().m11341d(th);
                            RequestBuilder.this.m11282a(dHResponder);
                        }
                    }
                };
                if (z) {
                    C5892y.f14529g.execute(runnable);
                } else {
                    runnable.run();
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                if (dHResponder != null) {
                    m11282a(dHResponder);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m11282a(DHResponder dHResponder) {
            if (dHResponder != null) {
                try {
                    dHResponder.onResponse(new DHResponse());
                } catch (Throwable th) {
                    MobLog.getInstance().m11340d(th, "Error from caller", new Object[0]);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public DHResponse m11283a() {
            DHResponse dHResponse = new DHResponse();
            for (int i = 0; i < this.f15163b.size(); i++) {
                C6156a c6156a = this.f15163b.get(i);
                try {
                    dHResponse.m11287a(c6156a.f15171a, m11279a(c6156a.f15171a, c6156a.f15172b));
                } catch (Throwable th) {
                    try {
                        MobLog.getInstance().m11341d(th);
                        dHResponse.m11286a(c6156a.f15171a, (Object) null, true);
                    } catch (Throwable th2) {
                        MobLog.getInstance().m11341d(th2);
                    }
                }
            }
            return dHResponse;
        }

        public RequestBuilder isRooted() {
            this.f15163b.add(new C6156a("cird", new Object[0]));
            return this;
        }

        public RequestBuilder getSSID() {
            this.f15163b.add(new C6156a("gsimt", new Object[0]));
            return this;
        }

        public RequestBuilder getSSIDForce(boolean z) {
            this.f15163b.add(new C6156a("gsimtfce", new Object[]{Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder getBssid() {
            this.f15163b.add(new C6156a("gbsi", new Object[0]));
            return this;
        }

        public RequestBuilder getBssidForce(boolean z) {
            this.f15163b.add(new C6156a("gbsifce", new Object[]{Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder getSystemProperties(String str) {
            this.f15163b.add(new C6156a("gstmpts", new Object[]{str}));
            return this;
        }

        public RequestBuilder getScreenSize() {
            this.f15163b.add(new C6156a("gscsz", new Object[0]));
            return this;
        }

        public RequestBuilder getCarrier() {
            this.f15163b.add(new C6156a("gcrie", new Object[0]));
            return this;
        }

        public RequestBuilder getCarrierForce(boolean z) {
            this.f15163b.add(new C6156a("gcriefce", new Object[]{Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder getCarrierName() {
            this.f15163b.add(new C6156a("gcrnm", new Object[0]));
            return this;
        }

        public RequestBuilder getCarrierNameForce(boolean z) {
            this.f15163b.add(new C6156a("gcrnmfce", new Object[]{Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder getSignMD5() {
            this.f15163b.add(new C6156a("gsnmd", new Object[0]));
            return this;
        }

        public RequestBuilder getSignMD5ForPkg(String str) {
            this.f15163b.add(new C6156a("gsnmdfp", new Object[]{str}));
            return this;
        }

        @Deprecated
        public RequestBuilder getNetworkType() {
            this.f15163b.add(new C6156a("gneyp", new Object[0]));
            return this;
        }

        public RequestBuilder getNetworkTypeNew() {
            this.f15163b.add(new C6156a("gneypnw", new Object[0]));
            return this;
        }

        public RequestBuilder getNetworkTypeForce(boolean z) {
            this.f15163b.add(new C6156a("gneypfce", new Object[]{Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder checkNetworkAvailable() {
            this.f15163b.add(new C6156a("cknavbl", new Object[0]));
            return this;
        }

        public RequestBuilder getNetworkTypeForStatic() {
            this.f15163b.add(new C6156a("gnktpfs", new Object[0]));
            return this;
        }

        public RequestBuilder getDetailNetworkTypeForStatic() {
            this.f15163b.add(new C6156a("gdtlnktpfs", new Object[0]));
            return this;
        }

        public RequestBuilder getDeviceKey() {
            this.f15163b.add(new C6156a("gdvk", new Object[0]));
            return this;
        }

        public RequestBuilder getDeviceKeyFromCache(boolean z) {
            this.f15163b.add(new C6156a("gdvkfc", new Object[]{Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder getAppName() {
            this.f15163b.add(new C6156a("gpnmmt", new Object[0]));
            return this;
        }

        public RequestBuilder getAppNameForPkg(String str) {
            this.f15163b.add(new C6156a("gpnmfp", new Object[]{str}));
            return this;
        }

        public RequestBuilder getIA(boolean z) {
            this.f15163b.add(new C6156a("gia", new Object[]{Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder getIAForce(boolean z, boolean z2) {
            this.f15163b.add(new C6156a("giafce", new Object[]{Boolean.valueOf(z), Boolean.valueOf(z2)}));
            return this;
        }

        public RequestBuilder getSA() {
            this.f15163b.add(new C6156a("gsl", new Object[0]));
            return this;
        }

        public RequestBuilder getAdvertisingID() {
            this.f15163b.add(new C6156a("gavti", new Object[0]));
            return this;
        }

        public RequestBuilder getLocation(int i, int i2, boolean z) {
            this.f15163b.add(new C6156a("glctn", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder getCLoc() {
            this.f15163b.add(new C6156a("gtecloc", new Object[0]));
            return this;
        }

        public RequestBuilder getNeighboringCellInfo() {
            this.f15163b.add(new C6156a("gnbclin", new Object[0]));
            return this;
        }

        public RequestBuilder getDeviceType() {
            this.f15163b.add(new C6156a("gdvtp", new Object[0]));
            return this;
        }

        public RequestBuilder getCurrentWifiInfo() {
            this.f15163b.add(new C6156a("wmcwi", new Object[0]));
            return this;
        }

        public RequestBuilder isPackageInstalled(String str) {
            this.f15163b.add(new C6156a("ipgist", new Object[]{str}));
            return this;
        }

        public RequestBuilder getCPUInfo() {
            this.f15163b.add(new C6156a("gcuin", new Object[0]));
            return this;
        }

        public RequestBuilder getTTYDriversInfo() {
            this.f15163b.add(new C6156a("gtydvin", new Object[0]));
            return this;
        }

        public RequestBuilder getQemuKernel() {
            this.f15163b.add(new C6156a("gqmkn", new Object[0]));
            return this;
        }

        public RequestBuilder getSizeInfo() {
            this.f15163b.add(new C6156a("gszin", new Object[0]));
            return this;
        }

        public RequestBuilder getMemoryInfo() {
            this.f15163b.add(new C6156a("gmrin", new Object[0]));
            return this;
        }

        public RequestBuilder getMIUIVersion() {
            this.f15163b.add(new C6156a("gmivsn", new Object[0]));
            return this;
        }

        /* renamed from: cx */
        public RequestBuilder m11278cx() {
            this.f15163b.add(new C6156a("cx", new Object[0]));
            return this;
        }

        public RequestBuilder checkPad() {
            this.f15163b.add(new C6156a("ckpd", new Object[0]));
            return this;
        }

        public RequestBuilder usbEnable() {
            this.f15163b.add(new C6156a("ubenbl", new Object[0]));
            return this;
        }

        public RequestBuilder devEnable() {
            this.f15163b.add(new C6156a("dvenbl", new Object[0]));
            return this;
        }

        public RequestBuilder checkUA() {
            this.f15163b.add(new C6156a("ckua", new Object[0]));
            return this;
        }

        public RequestBuilder vpn() {
            this.f15163b.add(new C6156a("vnmt", new Object[0]));
            return this;
        }

        public RequestBuilder debugable() {
            this.f15163b.add(new C6156a("degb", new Object[0]));
            return this;
        }

        public RequestBuilder isWifiProxy() {
            this.f15163b.add(new C6156a("iwpxy", new Object[0]));
            return this;
        }

        public RequestBuilder getFlavor() {
            this.f15163b.add(new C6156a("gflv", new Object[0]));
            return this;
        }

        public RequestBuilder getBaseband() {
            this.f15163b.add(new C6156a("gbsbd", new Object[0]));
            return this;
        }

        public RequestBuilder getBoardFromSysProperty() {
            this.f15163b.add(new C6156a("gbfspy", new Object[0]));
            return this;
        }

        public RequestBuilder getBoardPlatform() {
            this.f15163b.add(new C6156a("gbplfo", new Object[0]));
            return this;
        }

        public RequestBuilder getDataNtType() {
            this.f15163b.add(new C6156a("gdntp", new Object[0]));
            return this;
        }

        public RequestBuilder queryIntentServices(Intent intent, int i) {
            this.f15163b.add(new C6156a("qritsvc", new Object[]{intent, Integer.valueOf(i)}));
            return this;
        }

        public RequestBuilder resolveActivity(Intent intent, int i) {
            this.f15163b.add(new C6156a("rsaciy", new Object[]{intent, Integer.valueOf(i)}));
            return this;
        }

        public RequestBuilder getPInfo(String str, int i) {
            this.f15163b.add(new C6156a("gpgif", new Object[]{str, Integer.valueOf(i)}));
            return this;
        }

        public RequestBuilder getPInfoForce(boolean z, String str, int i) {
            this.f15163b.add(new C6156a("gpgiffcin", new Object[]{Boolean.valueOf(z), str, Integer.valueOf(i)}));
            return this;
        }

        public RequestBuilder getPInfoStrategy(int i, String str, int i2) {
            this.f15163b.add(new C6156a("gpgifstrg", new Object[]{Integer.valueOf(i), str, Integer.valueOf(i2)}));
            return this;
        }

        public RequestBuilder getIPAddress() {
            this.f15163b.add(new C6156a("giads", new Object[0]));
            return this;
        }

        public RequestBuilder getDeviceData() {
            this.f15163b.add(new C6156a("gdvda", new Object[0]));
            return this;
        }

        public RequestBuilder getDeviceDataNotAES() {
            this.f15163b.add(new C6156a("gdvdtnas", new Object[0]));
            return this;
        }

        public RequestBuilder getAppLastUpdateTime() {
            this.f15163b.add(new C6156a("galtut", new Object[0]));
            return this;
        }

        public RequestBuilder getDeviceName() {
            this.f15163b.add(new C6156a("gdvme", new Object[0]));
            return this;
        }

        public RequestBuilder getCgroup() {
            this.f15163b.add(new C6156a("gcrup", new Object[0]));
            return this;
        }

        public RequestBuilder getCInfo() {
            this.f15163b.add(new C6156a("gcifm", new Object[0]));
            return this;
        }

        public RequestBuilder getOD() {
            this.f15163b.add(new C6156a("godm", new Object[0]));
            return this;
        }

        public RequestBuilder getODH() {
            this.f15163b.add(new C6156a("godhm", new Object[0]));
            return this;
        }

        public RequestBuilder getALLD() {
            this.f15163b.add(new C6156a("galdm", new Object[0]));
            return this;
        }

        public RequestBuilder getAInfo() {
            this.f15163b.add(new C6156a("gtaif", new Object[0]));
            return this;
        }

        public RequestBuilder getAInfoForPkg(String str, int i) {
            this.f15163b.add(new C6156a("gtaifprm", new Object[]{str, Integer.valueOf(i)}));
            return this;
        }

        public RequestBuilder getAInfoForPkgForce(boolean z, String str, int i) {
            this.f15163b.add(new C6156a("gtaifprmfce", new Object[]{Boolean.valueOf(z), str, Integer.valueOf(i)}));
            return this;
        }

        public RequestBuilder getBdT() {
            this.f15163b.add(new C6156a("gtbdt", new Object[0]));
            return this;
        }

        public RequestBuilder getScreenInch() {
            this.f15163b.add(new C6156a("gtscnin", new Object[0]));
            return this;
        }

        public RequestBuilder getScreenPpi() {
            this.f15163b.add(new C6156a("gtscnppi", new Object[0]));
            return this;
        }

        public RequestBuilder isHmOs() {
            this.f15163b.add(new C6156a("ishmos", new Object[0]));
            return this;
        }

        public RequestBuilder getHmOsVer() {
            this.f15163b.add(new C6156a("gthmosv", new Object[0]));
            return this;
        }

        public RequestBuilder getHmOsDetailedVer() {
            this.f15163b.add(new C6156a("gthmosdtlv", new Object[0]));
            return this;
        }

        public RequestBuilder getHmPMState() {
            this.f15163b.add(new C6156a("gthmpmst", new Object[0]));
            return this;
        }

        public RequestBuilder getHmEPMState() {
            this.f15163b.add(new C6156a("gthmepmst", new Object[0]));
            return this;
        }

        public RequestBuilder getInnerAppLanguage() {
            this.f15163b.add(new C6156a("gtinnerlangmt", new Object[0]));
            return this;
        }

        public RequestBuilder getGrammaticalGender() {
            this.f15163b.add(new C6156a("gtgramgendt", new Object[0]));
            return this;
        }

        public RequestBuilder getPosComm(int i, int i2, boolean z) {
            this.f15163b.add(new C6156a("gtelcme", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder getMwfo() {
            this.f15163b.add(new C6156a("gtmwfo", new Object[0]));
            return this;
        }

        public RequestBuilder getMwfoForce(boolean z) {
            this.f15163b.add(new C6156a("wmcwifce", new Object[]{Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder getMwlfo() {
            this.f15163b.add(new C6156a("gtaifok", new Object[0]));
            return this;
        }

        public RequestBuilder getMcdi() {
            this.f15163b.add(new C6156a("gtmcdi", new Object[0]));
            return this;
        }

        public RequestBuilder getMcdiForce(boolean z) {
            this.f15163b.add(new C6156a("gtmcdifce", new Object[]{Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder getMbcdi() {
            this.f15163b.add(new C6156a("gtmbcdi", new Object[0]));
            return this;
        }

        public RequestBuilder getMbcdiForce(boolean z) {
            this.f15163b.add(new C6156a("gtmbcdifce", new Object[]{Boolean.valueOf(z)}));
            return this;
        }

        public RequestBuilder isMwpy() {
            this.f15163b.add(new C6156a("miwpy", new Object[0]));
            return this;
        }

        public RequestBuilder getMnbclfo() {
            this.f15163b.add(new C6156a("gtmnbclfo", new Object[0]));
            return this;
        }

        public RequestBuilder getMpfo(String str, int i) {
            this.f15163b.add(new C6156a("gmpfo", new Object[]{str, Integer.valueOf(i)}));
            return this;
        }

        public RequestBuilder getMpfof(boolean z, String str, int i) {
            this.f15163b.add(new C6156a("gmpfofce", new Object[]{Boolean.valueOf(z), str, Integer.valueOf(i)}));
            return this;
        }

        public RequestBuilder getMpfos(int i, String str, int i2) {
            this.f15163b.add(new C6156a("getMpfos", new Object[]{Integer.valueOf(i), str, Integer.valueOf(i2)}));
            return this;
        }

        public RequestBuilder checkDebbing() {
            this.f15163b.add(new C6156a("ctedebbing", new Object[0]));
            return this;
        }
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* renamed from: com.mob.tools.utils.DH$DHResponse */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class DHResponse {

        /* renamed from: A */
        private String f15065A;

        /* renamed from: C */
        private Object f15067C;

        /* renamed from: D */
        private ArrayList<HashMap<String, Object>> f15068D;

        /* renamed from: E */
        private String f15069E;

        /* renamed from: F */
        private HashMap<String, Object> f15070F;

        /* renamed from: H */
        private HashMap<String, Object> f15072H;

        /* renamed from: I */
        private ArrayList<ArrayList<String>> f15073I;

        /* renamed from: J */
        private String f15074J;

        /* renamed from: K */
        private HashMap<String, HashMap<String, Long>> f15075K;

        /* renamed from: L */
        private HashMap<String, Long> f15076L;

        /* renamed from: M */
        private String f15077M;

        /* renamed from: N */
        private boolean f15078N;

        /* renamed from: O */
        private boolean f15079O;

        /* renamed from: P */
        private boolean f15080P;

        /* renamed from: Q */
        private boolean f15081Q;

        /* renamed from: R */
        private boolean f15082R;

        /* renamed from: S */
        private boolean f15083S;

        /* renamed from: T */
        private boolean f15084T;

        /* renamed from: U */
        private boolean f15085U;

        /* renamed from: V */
        private String f15086V;

        /* renamed from: W */
        private String f15087W;

        /* renamed from: X */
        private String f15088X;

        /* renamed from: Y */
        private String f15089Y;

        /* renamed from: Z */
        private int f15090Z;

        /* renamed from: a */
        private boolean f15091a;

        /* renamed from: aA */
        private String f15092aA;

        /* renamed from: aB */
        private int f15093aB;

        /* renamed from: aC */
        private int f15094aC;

        /* renamed from: aD */
        private String f15095aD;

        /* renamed from: aE */
        private int f15096aE;

        /* renamed from: aG */
        private HashMap<String, Object> f15098aG;

        /* renamed from: aI */
        private ArrayList<HashMap<String, Object>> f15100aI;

        /* renamed from: aJ */
        private String f15101aJ;

        /* renamed from: aL */
        private String f15103aL;

        /* renamed from: aN */
        private boolean f15105aN;

        /* renamed from: aO */
        private ArrayList<HashMap<String, Object>> f15106aO;

        /* renamed from: aS */
        private boolean f15110aS;

        /* renamed from: af */
        private String f15116af;

        /* renamed from: ag */
        private String f15117ag;

        /* renamed from: ah */
        private String f15118ah;

        /* renamed from: ai */
        private long f15119ai;

        /* renamed from: aj */
        private String f15120aj;

        /* renamed from: ak */
        private String f15121ak;

        /* renamed from: al */
        private String f15122al;

        /* renamed from: am */
        private String f15123am;

        /* renamed from: an */
        private String f15124an;

        /* renamed from: ao */
        private HashMap<String, Object> f15125ao;

        /* renamed from: ap */
        private ApplicationInfo f15126ap;

        /* renamed from: as */
        private String f15129as;

        /* renamed from: at */
        private String f15130at;

        /* renamed from: au */
        private String f15131au;

        /* renamed from: av */
        private long f15132av;

        /* renamed from: aw */
        private double f15133aw;

        /* renamed from: ax */
        private int f15134ax;

        /* renamed from: ay */
        private boolean f15135ay;

        /* renamed from: az */
        private String f15136az;

        /* renamed from: b */
        private String f15137b;

        /* renamed from: d */
        private String f15139d;

        /* renamed from: g */
        private String f15142g;

        /* renamed from: h */
        private String f15143h;

        /* renamed from: j */
        private String f15145j;

        /* renamed from: l */
        private String f15147l;

        /* renamed from: n */
        private String f15149n;

        /* renamed from: o */
        private String f15150o;

        /* renamed from: q */
        private boolean f15152q;

        /* renamed from: r */
        private String f15153r;

        /* renamed from: s */
        private String f15154s;

        /* renamed from: t */
        private String f15155t;

        /* renamed from: v */
        private String f15157v;

        /* renamed from: z */
        private ArrayList<HashMap<String, String>> f15161z;

        /* renamed from: c */
        private LinkedList<String> f15138c = new LinkedList<>();

        /* renamed from: e */
        private LinkedList<String> f15140e = new LinkedList<>();

        /* renamed from: f */
        private LinkedList<String> f15141f = new LinkedList<>();

        /* renamed from: i */
        private LinkedList<String> f15144i = new LinkedList<>();

        /* renamed from: k */
        private LinkedList<String> f15146k = new LinkedList<>();

        /* renamed from: m */
        private LinkedList<String> f15148m = new LinkedList<>();

        /* renamed from: p */
        private LinkedList<String> f15151p = new LinkedList<>();

        /* renamed from: u */
        private LinkedList<String> f15156u = new LinkedList<>();

        /* renamed from: w */
        private LinkedList<String> f15158w = new LinkedList<>();

        /* renamed from: x */
        private LinkedList<ArrayList<HashMap<String, String>>> f15159x = new LinkedList<>();

        /* renamed from: y */
        private LinkedList<ArrayList<HashMap<String, String>>> f15160y = new LinkedList<>();

        /* renamed from: B */
        private LinkedList<Location> f15066B = new LinkedList<>();

        /* renamed from: G */
        private LinkedList<Boolean> f15071G = new LinkedList<>();

        /* renamed from: aa */
        private LinkedList<List<ResolveInfo>> f15111aa = new LinkedList<>();

        /* renamed from: ab */
        private LinkedList<ResolveInfo> f15112ab = new LinkedList<>();

        /* renamed from: ac */
        private LinkedList<PackageInfo> f15113ac = new LinkedList<>();

        /* renamed from: ad */
        private LinkedList<PackageInfo> f15114ad = new LinkedList<>();

        /* renamed from: ae */
        private LinkedList<PackageInfo> f15115ae = new LinkedList<>();

        /* renamed from: aq */
        private LinkedList<ApplicationInfo> f15127aq = new LinkedList<>();

        /* renamed from: ar */
        private LinkedList<ApplicationInfo> f15128ar = new LinkedList<>();

        /* renamed from: aF */
        private LinkedList<Object> f15097aF = new LinkedList<>();

        /* renamed from: aH */
        private LinkedList<HashMap<String, Object>> f15099aH = new LinkedList<>();

        /* renamed from: aK */
        private LinkedList<String> f15102aK = new LinkedList<>();

        /* renamed from: aM */
        private LinkedList<String> f15104aM = new LinkedList<>();

        /* renamed from: aP */
        private LinkedList<Object> f15107aP = new LinkedList<>();

        /* renamed from: aQ */
        private LinkedList<Object> f15108aQ = new LinkedList<>();

        /* renamed from: aR */
        private LinkedList<Object> f15109aR = new LinkedList<>();

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: ArrayIndexOutOfBoundsException: 5 in method: com.mob.tools.utils.DH.DHResponse.a(java.lang.String, java.lang.Object, boolean):void, file: E:\11480076_dexfile_execute.dex.fixout.dex
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            Caused by: java.lang.ArrayIndexOutOfBoundsException: 5
            	at jadx.plugins.input.dex.insns.DexInsnData.toString(DexInsnData.java:251)
            	at java.lang.String.valueOf(String.java:2994)
            	at java.lang.StringBuilder.append(StringBuilder.java:131)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:55)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	... 6 more
            */
        /* renamed from: a */
        void m11286a(java.lang.String r1, java.lang.Object r2, boolean r3) throws java.lang.Throwable {
            /*
            // Can't load method instructions: Load method exception: ArrayIndexOutOfBoundsException: 5 in method: com.mob.tools.utils.DH.DHResponse.a(java.lang.String, java.lang.Object, boolean):void, file: E:\11480076_dexfile_execute.dex.fixout.dex
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.C6152DH.DHResponse.m11286a(java.lang.String, java.lang.Object, boolean):void");
        }

        public String getDeviceId() {
            return null;
        }

        public String getIMEI() {
            return null;
        }

        public String getIMSI() {
            return null;
        }

        public boolean getSdcardState() {
            return false;
        }

        public String getSerialno() {
            return null;
        }

        public String getSimSerialNumber() {
            return null;
        }

        public Activity getTopActivity() {
            return null;
        }

        public String[] queryIMEI() {
            return null;
        }

        public String[] queryIMSI() {
            return null;
        }

        /* renamed from: a */
        void m11287a(String str, Object obj) throws Throwable {
            m11286a(str, obj, false);
        }

        public boolean isRooted() {
            return this.f15091a;
        }

        public String getSSID() {
            return this.f15137b;
        }

        public String getSSIDForce(int... iArr) {
            return (String) m11285a(this.f15138c, (Object) null, iArr);
        }

        public String getBssid() {
            return this.f15139d;
        }

        public String getBssidForce(int... iArr) {
            return (String) m11285a(this.f15140e, (Object) null, iArr);
        }

        public String getSystemProperties(int... iArr) {
            return (String) m11285a(this.f15141f, (Object) null, iArr);
        }

        public String getScreenSize() {
            return this.f15142g;
        }

        public String getCarrier() {
            return this.f15143h;
        }

        public String getCarrierForce(int... iArr) {
            return (String) m11285a(this.f15144i, "-1", iArr);
        }

        public String getCarrierName() {
            return this.f15145j;
        }

        public String getCarrierNameForce(int... iArr) {
            return (String) m11285a(this.f15146k, (Object) null, iArr);
        }

        public String getSignMD5() {
            return this.f15147l;
        }

        public String getSignMD5ForPkg(int... iArr) {
            return (String) m11285a(this.f15148m, (Object) null, iArr);
        }

        public String getNetworkType() {
            return this.f15149n;
        }

        public String getNetworkTypeNew() {
            return this.f15150o;
        }

        public String getNetworkTypeForce(int... iArr) {
            return (String) m11285a(this.f15151p, C5731l.m12674a("004f0fe[fg"), iArr);
        }

        public boolean checkNetworkAvailable() {
            return this.f15152q;
        }

        public String getNetworkTypeForStatic() {
            return this.f15153r;
        }

        public String getDetailNetworkTypeForStatic() {
            return this.f15154s;
        }

        public String getDeviceKey() {
            return this.f15155t;
        }

        public String getDeviceKeyFromCache(int... iArr) {
            return (String) m11285a(this.f15156u, (Object) null, iArr);
        }

        public String getAppName() {
            return this.f15157v;
        }

        public String getAppNameForPkg(int... iArr) {
            return (String) m11285a(this.f15158w, (Object) null, iArr);
        }

        public ArrayList<HashMap<String, String>> getIA(int... iArr) {
            return (ArrayList) m11285a(this.f15159x, new ArrayList(), iArr);
        }

        public ArrayList<HashMap<String, String>> getIAForce(int... iArr) {
            return (ArrayList) m11285a(this.f15160y, new ArrayList(), iArr);
        }

        public ArrayList<HashMap<String, String>> getSA() {
            return this.f15161z;
        }

        public String getAdvertisingID() {
            return this.f15065A;
        }

        public Location getLocation(int... iArr) {
            return (Location) m11285a(this.f15066B, (Object) null, iArr);
        }

        public Object getCLoc() {
            return this.f15067C;
        }

        public ArrayList<HashMap<String, Object>> getNeighboringCellInfo() {
            return this.f15068D;
        }

        public String getDeviceType() {
            return this.f15069E;
        }

        public HashMap<String, Object> getCurrentWifiInfo() {
            return this.f15070F;
        }

        public boolean isPackageInstalled(int... iArr) {
            return ((Boolean) m11285a((LinkedList<boolean>) this.f15071G, false, iArr)).booleanValue();
        }

        public HashMap<String, Object> getCPUInfo() {
            return this.f15072H;
        }

        public ArrayList<ArrayList<String>> getTTYDriversInfo() {
            return this.f15073I;
        }

        public String getQemuKernel() {
            return this.f15074J;
        }

        public HashMap<String, HashMap<String, Long>> getSizeInfo() {
            return this.f15075K;
        }

        public HashMap<String, Long> getMemoryInfo() {
            return this.f15076L;
        }

        public String getMIUIVersion() {
            return this.f15077M;
        }

        /* renamed from: cx */
        public boolean m11284cx() {
            return this.f15078N;
        }

        public boolean checkPad() {
            return this.f15079O;
        }

        public boolean usbEnable() {
            return this.f15080P;
        }

        public boolean devEnable() {
            return this.f15081Q;
        }

        public boolean checkUA() {
            return this.f15082R;
        }

        public boolean vpn() {
            return this.f15083S;
        }

        public boolean debugable() {
            return this.f15084T;
        }

        public boolean isWifiProxy() {
            return this.f15085U;
        }

        public String getFlavor() {
            return this.f15086V;
        }

        public String getBaseband() {
            return this.f15087W;
        }

        public String getBoardFromSysProperty() {
            return this.f15088X;
        }

        public String getBoardPlatform() {
            return this.f15089Y;
        }

        public int getDataNtType() {
            return this.f15090Z;
        }

        public List<ResolveInfo> queryIntentServices(int... iArr) {
            return (List) m11285a(this.f15111aa, (Object) null, iArr);
        }

        public ResolveInfo resolveActivity(int... iArr) {
            return (ResolveInfo) m11285a(this.f15112ab, (Object) null, iArr);
        }

        public PackageInfo getPInfo(int... iArr) {
            return (PackageInfo) m11285a(this.f15113ac, (Object) null, iArr);
        }

        public PackageInfo getPInfoForce(int... iArr) {
            return (PackageInfo) m11285a(this.f15114ad, (Object) null, iArr);
        }

        public PackageInfo getPInfoStrategy(int... iArr) {
            return (PackageInfo) m11285a(this.f15115ae, (Object) null, iArr);
        }

        public String getIPAddress() {
            return this.f15116af;
        }

        public String getDeviceData() {
            return this.f15117ag;
        }

        public String getDeviceDataNotAES() {
            return this.f15118ah;
        }

        public long getAppLastUpdateTime() {
            return this.f15119ai;
        }

        public String getDeviceName() {
            return this.f15120aj;
        }

        public String getCgroup() {
            return this.f15121ak;
        }

        public String getCInfo() {
            return this.f15122al;
        }

        public String getOD() {
            return this.f15123am;
        }

        public String getODH() {
            return this.f15124an;
        }

        public HashMap<String, Object> getALLD() {
            return this.f15125ao;
        }

        public ApplicationInfo getAInfo() {
            return this.f15126ap;
        }

        public ApplicationInfo getAInfoForPkg(int... iArr) {
            return (ApplicationInfo) m11285a(this.f15127aq, (Object) null, iArr);
        }

        public ApplicationInfo getAInfoForPkgForce(int... iArr) {
            return (ApplicationInfo) m11285a(this.f15128ar, (Object) null, iArr);
        }

        public String getDrID() {
            return this.f15129as;
        }

        public String getBtM() {
            return this.f15130at;
        }

        public String getUpM() {
            return this.f15131au;
        }

        public long getBdT() {
            return this.f15132av;
        }

        public double getScreenInch() {
            return this.f15133aw;
        }

        public int getScreenPpi() {
            return this.f15134ax;
        }

        public boolean isHmOs() {
            return this.f15135ay;
        }

        public String getHmOsVer() {
            return this.f15136az;
        }

        public String getHmOsDetailedVer() {
            return this.f15092aA;
        }

        public int getHmPMState() {
            return this.f15093aB;
        }

        public int getHmEPMState() {
            return this.f15094aC;
        }

        public String getInnerAppLanguage() {
            return this.f15095aD;
        }

        public int getGrammaticalGender() {
            return this.f15096aE;
        }

        public Object getPosComm(int... iArr) {
            return m11285a(this.f15097aF, (Object) null, iArr);
        }

        public HashMap<String, Object> getMwfo() {
            return this.f15098aG;
        }

        public HashMap<String, Object> getMwfoForce(int... iArr) {
            return (HashMap) m11285a(this.f15099aH, (Object) null, iArr);
        }

        public ArrayList<HashMap<String, Object>> getMwlfo() {
            return this.f15100aI;
        }

        public String getMcdi() {
            return this.f15101aJ;
        }

        public String getMcdiForce(int... iArr) {
            return (String) m11285a(this.f15102aK, (Object) null, iArr);
        }

        public String getMbcdi() {
            return this.f15103aL;
        }

        public String getMbcdiForce(int... iArr) {
            return (String) m11285a(this.f15104aM, (Object) null, iArr);
        }

        public boolean isMwpy() {
            return this.f15105aN;
        }

        public ArrayList<HashMap<String, Object>> getMnbclfo() {
            return this.f15106aO;
        }

        public Object getMpfo(int... iArr) {
            return m11285a(this.f15107aP, (Object) null, iArr);
        }

        public Object getMpfof(int... iArr) {
            return m11285a(this.f15108aQ, (Object) null, iArr);
        }

        public Object getMpfos(int... iArr) {
            return m11285a(this.f15109aR, (Object) null, iArr);
        }

        public boolean checkDebbing() {
            return this.f15110aS;
        }

        /* renamed from: a */
        private static <T> T m11285a(LinkedList<T> linkedList, T t, int... iArr) {
            if (linkedList != null) {
                try {
                    if (iArr.length == 0) {
                        return linkedList.get(0);
                    }
                    if (iArr[0] < linkedList.size()) {
                        return linkedList.get(iArr[0]);
                    }
                    NLog mobLog = MobLog.getInstance();
                    mobLog.m11326w("WARNING: " + iArr[0] + " out of bound, size: " + linkedList.size());
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                }
            }
            return t;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.utils.DH$SyncMtd */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class SyncMtd {
        public static int getPlatformCode() {
            return 1;
        }

        public static <T> T invokeInstanceMethod(Object obj, String str, Object... objArr) {
            return (T) ReflectHelper.invokeInstanceMethodNoThrow(obj, str, null, objArr);
        }

        public static <T> T invokeInstanceMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr) {
            try {
                return (T) ReflectHelper.invokeInstanceMethod(obj, str, objArr, clsArr);
            } catch (Throwable th) {
                if (th instanceof InvocationTargetException) {
                    String name = th.getClass().getName();
                    String message = th.getMessage();
                    Throwable cause = th.getCause();
                    if (cause != null) {
                        name = cause.getClass().getName();
                        message = cause.getMessage();
                    }
                    NLog mobLog = MobLog.getInstance();
                    mobLog.m11342d("Exception: " + name + ": " + message, new Object[0]);
                    return null;
                } else if (th instanceof PackageManager.NameNotFoundException) {
                    NLog mobLog2 = MobLog.getInstance();
                    mobLog2.m11342d("Exception: " + th.getClass().getName() + ": " + th.getMessage(), new Object[0]);
                    return null;
                } else {
                    MobLog.getInstance().m11341d(th);
                    return null;
                }
            }
        }

        public static Object getSystemServiceSafe(String str) {
            return C5873u.m12171d(str);
        }

        public static String getSandboxPath() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11599R();
        }

        public static boolean checkPermission(String str) {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11530e(str);
        }

        public static boolean isInMainProcess() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11593X();
        }

        public static String getPackageName() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11597T();
        }

        public static String getCurrentProcessName() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11592Y();
        }

        public static String getAppVersionName() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11594W();
        }

        public static String Base64AES(String str, String str2) {
            return Data.Base64AES(str, str2);
        }

        public static Object currentActivityThread() {
            return C5873u.m12178b();
        }

        public static Context getApplication() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11570aa();
        }

        public static int getOSVersionInt() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11510s();
        }

        public static String getOSVersionName() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11509t();
        }

        public static String getOSLanguage() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11508u();
        }

        public static String getAppLanguage() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11615B();
        }

        public static String getOSCountry() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11507v();
        }

        public static String getTimezone() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11608I();
        }

        public static void hideSoftInput(View view) {
            C5873u.m12184a(view);
        }

        public static void showSoftInput(View view) {
            C5873u.m12177b(view);
        }

        public static String getModel() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11517l();
        }

        public static String getManufacturer() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11516m();
        }

        public static String getBrand() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11515n();
        }

        public static int getAppVersion() {
            return C6031c.m11708a(MobSDK.getContext()).m11704d().mo11595V();
        }

        public static String getSystemProperties(String str) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final String[] strArr = new String[1];
            C6152DH.requester(MobSDK.getContext()).getSystemProperties(str).request(new DHResponder() { // from class: com.mob.tools.utils.DH.SyncMtd.1
                @Override // com.mob.tools.utils.C6152DH.DHResponder
                public void onResponse(DHResponse dHResponse) {
                    strArr[0] = dHResponse.getSystemProperties(new int[0]);
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await(3000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                MobLog.getInstance().m11341d(e);
            }
            return strArr[0];
        }
    }
}
