package com.huawei.hms.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hms.framework.network.grs.IQueryUrlCallBack;
import com.huawei.hms.hatool.HmsHiAnalyticsUtils;
import com.huawei.hms.stats.AnalyticsCacheManager;
import com.huawei.hms.stats.HianalyticsExist;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HMSBIInitializer {

    /* renamed from: d */
    private static final Object f11808d = new Object();

    /* renamed from: e */
    private static HMSBIInitializer f11809e;

    /* renamed from: f */
    private static HiAnalyticsInstance f11810f;

    /* renamed from: a */
    private final Context f11811a;

    /* renamed from: b */
    private AtomicBoolean f11812b = new AtomicBoolean(false);

    /* renamed from: c */
    private boolean f11813c = HianalyticsExist.m14168a();

    /* renamed from: com.huawei.hms.utils.HMSBIInitializer$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class C5090a implements IQueryUrlCallBack {
        C5090a() {
        }

        @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
        public void onCallBackFail(int i) {
            HMSLog.m14112e("HMSBIInitializer", "get grs failed, the errorcode is " + i);
            HMSBIInitializer.this.f11812b.set(false);
            AnalyticsCacheManager.m14174c().m14177a();
        }

        @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
        public void onCallBackSuccess(String str) {
            if (!TextUtils.isEmpty(str)) {
                if (!HMSBIInitializer.this.f11813c) {
                    HmsHiAnalyticsUtils.init(HMSBIInitializer.this.f11811a, false, false, false, str, "com.huawei.hwid");
                } else {
                    HiAnalyticsConfig build = new HiAnalyticsConfig.Builder().setEnableImei(false).setEnableUDID(false).setEnableSN(false).setCollectURL(str).build();
                    HiAnalyticsInstance unused = HMSBIInitializer.f11810f = new HiAnalyticsInstance.Builder(HMSBIInitializer.this.f11811a).setOperConf(build).setMaintConf(new HiAnalyticsConfig.Builder().setEnableImei(false).setEnableUDID(false).setEnableSN(false).setCollectURL(str).build()).create("hms_config_tag");
                    HMSBIInitializer.f11810f.setAppid("com.huawei.hwid");
                }
                HMSLog.m14110i("HMSBIInitializer", "BI URL acquired successfully");
            }
            HMSBIInitializer.this.f11812b.set(false);
            AnalyticsCacheManager.m14174c().m14175b();
        }
    }

    /* renamed from: com.huawei.hms.utils.HMSBIInitializer$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class AsyncTaskC5091b extends AsyncTask<String, Integer, Void> {
        private AsyncTaskC5091b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Void doInBackground(String... strArr) {
            HMSBIInitializer.this.m14087a(strArr[0]);
            return null;
        }

        /* synthetic */ AsyncTaskC5091b(HMSBIInitializer hMSBIInitializer, C5090a c5090a) {
            this();
        }
    }

    private HMSBIInitializer(Context context) {
        this.f11811a = context;
    }

    public static HMSBIInitializer getInstance(Context context) {
        synchronized (f11808d) {
            if (f11809e == null) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    f11809e = new HMSBIInitializer(applicationContext);
                } else {
                    f11809e = new HMSBIInitializer(context);
                }
            }
        }
        return f11809e;
    }

    public HiAnalyticsInstance getAnalyticsInstance() {
        return f11810f;
    }

    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:java.lang.Object) type: DIRECT call: java.lang.Object.<init>():void, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0003: IPUT  (r1 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.a java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0005: IPUT  (r2 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.b java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0007: IPUT  (r3 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.c java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0009: IPUT  (r4 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.d java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x000b: IPUT  (r5 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.e java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x000d: INVOKE  (r1 I:java.lang.Long) = (r6 I:long) type: STATIC call: java.lang.Long.valueOf(long):java.lang.Long, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo] */
    /* JADX WARN: Type inference failed for: r6v0, types: [long] */
    public void initBI() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        ?? r6;
        ?? obj = new Object();
        ((HMSPackageManager.PackagePriorityInfo) obj).f11833a = str;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11834b = str2;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11835c = str3;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11836d = str4;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11837e = str5;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11838f = Long.valueOf((long) r6);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:java.lang.Object) type: DIRECT call: java.lang.Object.<init>():void, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0003: IPUT  (r1 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.a java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0005: IPUT  (r2 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.b java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0007: IPUT  (r3 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.c java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0009: IPUT  (r4 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.d java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x000b: IPUT  (r5 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.e java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x000d: INVOKE  (r1 I:java.lang.Long) = (r6 I:long) type: STATIC call: java.lang.Long.valueOf(long):java.lang.Long, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo] */
    /* JADX WARN: Type inference failed for: r6v0, types: [long] */
    public void initHaSDK() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        ?? r6;
        ?? obj = new Object();
        ((HMSPackageManager.PackagePriorityInfo) obj).f11833a = str;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11834b = str2;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11835c = str3;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11836d = str4;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11837e = str5;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11838f = Long.valueOf((long) r6);
    }

    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:java.lang.Object) type: DIRECT call: java.lang.Object.<init>():void, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0003: IPUT  (r1 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.a java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0005: IPUT  (r2 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.b java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0007: IPUT  (r3 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.c java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0009: IPUT  (r4 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.d java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x000b: IPUT  (r5 I:java.lang.String), (r0 I:com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo) com.huawei.hms.utils.HMSPackageManager.PackagePriorityInfo.e java.lang.String, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x000d: INVOKE  (r1 I:java.lang.Long) = (r6 I:long) type: STATIC call: java.lang.Long.valueOf(long):java.lang.Long, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, com.huawei.hms.utils.HMSPackageManager$PackagePriorityInfo] */
    /* JADX WARN: Type inference failed for: r6v0, types: [long] */
    public boolean isInit() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        ?? r6;
        ?? obj = new Object();
        ((HMSPackageManager.PackagePriorityInfo) obj).f11833a = str;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11834b = str2;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11835c = str3;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11836d = str4;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11837e = str5;
        ((HMSPackageManager.PackagePriorityInfo) obj).f11838f = Long.valueOf((long) r6);
        return;
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m14087a(java.lang.String r0) {
        /*
            r-1 = this;
            boolean r0 = r0.f11813c
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.utils.HMSBIInitializer.m14087a(java.lang.String):void");
    }
}
