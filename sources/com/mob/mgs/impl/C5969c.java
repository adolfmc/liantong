package com.mob.mgs.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.apc.C5677a;
import com.mob.apc.C5688b;
import com.mob.commons.C5747b;
import com.mob.commons.C5849j;
import com.mob.commons.C5871t;
import com.mob.elp.MobELP;
import com.mob.mcl.C5901a;
import com.mob.mcl.MobMCL;
import com.mob.mgs.MobMGS;
import com.mob.tools.C6122c;
import com.mob.tools.MobLog;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.ActivityTracker;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.mob.mgs.impl.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5969c implements C5688b.InterfaceC5689a, C5688b.InterfaceC5690b, C5688b.InterfaceC5691c {

    /* renamed from: a */
    private static final String[] f14692a = {"com.mob.intent.MOB_GUARD_SERVICE", "com.mob.intent.MOB_ID_SERVICE"};

    /* renamed from: b */
    private static final String[] f14693b = {"com.mob.guard.MobGuardPullUpService", "com.mob.id.MobIDService"};

    /* renamed from: c */
    private static final String[] f14694c = {"com.mob.guard.MobTranPullUpActivity", "com.mob.id.MobIDActivity"};

    /* renamed from: d */
    private static C5969c f14695d = new C5969c();

    /* renamed from: e */
    private static AtomicBoolean f14696e = new AtomicBoolean(false);

    /* renamed from: m */
    private boolean f14704m;

    /* renamed from: n */
    private boolean f14705n;

    /* renamed from: f */
    private ExecutorService f14697f = Executors.newSingleThreadExecutor();

    /* renamed from: g */
    private String f14698g = null;

    /* renamed from: h */
    private boolean f14699h = false;

    /* renamed from: i */
    private int f14700i = 0;

    /* renamed from: j */
    private int f14701j = 0;

    /* renamed from: k */
    private List<HashMap<String, Object>> f14702k = null;

    /* renamed from: l */
    private HashMap<String, Integer> f14703l = null;

    /* renamed from: o */
    private Context f14706o = MobSDK.getContext();

    /* renamed from: a */
    public static C5969c m11922a() {
        return f14695d;
    }

    private C5969c() {
        ActivityTracker.getInstance(MobSDK.getContext()).addTracker(C5901a.m12112a(new C5901a.C5902a() { // from class: com.mob.mgs.impl.c.1
            @Override // com.mob.mcl.C5901a.C5902a
            /* renamed from: a */
            public void mo11883a() {
                if (C5969c.this.f14704m) {
                    C5969c.this.m11902a((String) null, true);
                }
            }
        }));
    }

    /* renamed from: f */
    private void m11885f() {
        try {
            if (f14696e.compareAndSet(false, true)) {
                MobSDK.init(MobSDK.getContext());
                C5688b.m12831a(MobSDK.getContext());
                String m11848f = C5995f.m11854a().m11848f();
                MobELP.init(m11848f);
                C5688b.m12827a("MOBGUARD", this);
                C5688b.m12829a((C5688b.InterfaceC5691c) this);
                C5688b.m12830a((C5688b.InterfaceC5689a) this);
                MobMCL.initMCLink(MobSDK.getContext(), C5871t.m12196a(), m11848f);
                MobMCL.getSuid();
                C5994e m11860a = C5994e.m11860a();
                m11860a.m11859a("[Guard] init guardId:" + MobMCL.getSuid() + ", time: " + MobMCL.getCreateSuidTime());
            }
        } catch (Throwable th) {
            C5994e.m11860a().m11855b(th);
        }
    }

    /* renamed from: b */
    public void m11900b() throws Throwable {
        m11885f();
        if (!MobMGS.getDS()) {
            C5994e.m11860a().m11859a("DS off");
        } else {
            C6152DH.requester(MobSDK.getContext()).getAInfoForPkg(MobSDK.getContext().getPackageName(), 128).request(new C6152DH.DHResponder() { // from class: com.mob.mgs.impl.c.9
                @Override // com.mob.tools.utils.C6152DH.DHResponder
                public void onResponse(C6152DH.DHResponse dHResponse) {
                    Bundle bundle;
                    Object obj;
                    ApplicationInfo aInfoForPkg = dHResponse.getAInfoForPkg(new int[0]);
                    String str = null;
                    if (aInfoForPkg != null && (bundle = aInfoForPkg.metaData) != null && !bundle.isEmpty() && (obj = bundle.get("disable_mob_a_guard")) != null) {
                        str = String.valueOf(obj);
                    }
                    C5994e m11860a = C5994e.m11860a();
                    m11860a.m11859a("[Guard] run disable_mob_a_guard:" + str);
                    if ("true".equals(str)) {
                        return;
                    }
                    C5747b.m12582a("cd", "221111", 0L);
                    boolean m12253b = C5849j.m12264a().m12253b();
                    C5994e m11860a2 = C5994e.m11860a();
                    m11860a2.m11859a("[EC] isClear init: " + m12253b);
                    if (m12253b) {
                        boolean z = ((Integer) C5747b.m12582a("all", 1, 0L)).intValue() == 1;
                        C5994e m11860a3 = C5994e.m11860a();
                        m11860a3.m11859a("als on: " + z);
                        if (z) {
                            C5969c.this.m11894b(new AbstractC6201c<Boolean>() { // from class: com.mob.mgs.impl.c.9.1
                                @Override // com.mob.tools.utils.AbstractC6201c
                                /* renamed from: a  reason: avoid collision after fix types in other method */
                                public void mo11088a(Boolean bool) {
                                    try {
                                        C5994e m11860a4 = C5994e.m11860a();
                                        m11860a4.m11859a("[GD] checkAndInitGuardParams:" + bool);
                                        if (bool.booleanValue()) {
                                            if (C5969c.this.m11892c()) {
                                                LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
                                                C5994e.m11860a().m11859a("[GD] registerServerSocket");
                                                C5959a.m11949a().m11940a(linkedBlockingQueue);
                                                boolean booleanValue = ((Boolean) linkedBlockingQueue.take()).booleanValue();
                                                C5994e m11860a5 = C5994e.m11860a();
                                                m11860a5.m11859a("[GD] registerServerSocket: " + booleanValue);
                                                if (booleanValue) {
                                                    C5969c.this.m11901a(C5969c.this.f14699h, (String) null);
                                                    if (C5969c.this.f14699h) {
                                                        Thread.sleep(500L);
                                                        C5969c.this.m11884g();
                                                        return;
                                                    }
                                                    return;
                                                }
                                            }
                                            C5994e.m11860a().m11859a("[Guard] registerClientSocket");
                                            C5959a.m11949a().m11938b();
                                        }
                                    } catch (Throwable th) {
                                        MobLog.getInstance().m11341d(th);
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m11907a(final AbstractC6201c<List<HashMap<String, String>>> abstractC6201c) {
        final ArrayList arrayList = new ArrayList();
        try {
            final String packageName = MobSDK.getContext().getPackageName();
            final HashSet hashSet = new HashSet();
            final ArrayList arrayList2 = new ArrayList();
            C6152DH.RequestBuilder requestBuilder = null;
            for (int i = 0; i < f14692a.length; i++) {
                ReflectHelper.importClass("android.content.Intent");
                Intent intent = (Intent) ReflectHelper.newInstance("Intent", f14692a[i]);
                if (requestBuilder == null) {
                    requestBuilder = C6152DH.requester(MobSDK.getContext());
                }
                requestBuilder = requestBuilder.queryIntentServices(intent, 0);
            }
            if (requestBuilder != null) {
                requestBuilder.request(new C6152DH.DHResponder() { // from class: com.mob.mgs.impl.c.10
                    @Override // com.mob.tools.utils.C6152DH.DHResponder
                    public void onResponse(C6152DH.DHResponse dHResponse) {
                        for (int i2 = 0; i2 < C5969c.f14692a.length; i2++) {
                            try {
                                List<ResolveInfo> queryIntentServices = dHResponse.queryIntentServices(i2);
                                if (queryIntentServices != null && queryIntentServices.size() > 0) {
                                    arrayList2.addAll(queryIntentServices);
                                }
                            } catch (Throwable th) {
                                MobLog.getInstance().m11341d(th);
                                AbstractC6201c abstractC6201c2 = abstractC6201c;
                                if (abstractC6201c2 != null) {
                                    abstractC6201c2.mo11088a(arrayList);
                                    return;
                                }
                                return;
                            }
                        }
                        C5969c.this.f14703l = new HashMap();
                        if (arrayList2 != null && C5969c.this.f14703l != null) {
                            C6152DH.RequestBuilder requestBuilder2 = null;
                            final ArrayList arrayList3 = new ArrayList();
                            for (ResolveInfo resolveInfo : arrayList2) {
                                if (resolveInfo.serviceInfo.exported && !packageName.equals(resolveInfo.serviceInfo.packageName)) {
                                    if (requestBuilder2 == null) {
                                        requestBuilder2 = C6152DH.requester(MobSDK.getContext());
                                    }
                                    requestBuilder2 = requestBuilder2.getMpfof(true, resolveInfo.serviceInfo.packageName, 128);
                                    arrayList3.add(resolveInfo.serviceInfo.packageName);
                                }
                            }
                            if (requestBuilder2 != null) {
                                requestBuilder2.request(new C6152DH.DHResponder() { // from class: com.mob.mgs.impl.c.10.1
                                    @Override // com.mob.tools.utils.C6152DH.DHResponder
                                    public void onResponse(C6152DH.DHResponse dHResponse2) {
                                        for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                                            try {
                                                int i4 = 1;
                                                ApplicationInfo m11360a = C6122c.m11360a(dHResponse2.getMpfof(i3), (String) arrayList3.get(i3));
                                                Bundle bundle = m11360a != null ? m11360a.metaData : null;
                                                if (bundle != null && !bundle.isEmpty()) {
                                                    Object obj = bundle.get("mob_id_ver");
                                                    if (obj == null) {
                                                        obj = bundle.get("mob_guard_version");
                                                        i4 = 0;
                                                    }
                                                    if (obj != null && !hashSet.contains(((ResolveInfo) arrayList2.get(i3)).serviceInfo.packageName) && !C5969c.this.m11890c(((ResolveInfo) arrayList2.get(i3)).serviceInfo.packageName)) {
                                                        hashSet.add(((ResolveInfo) arrayList2.get(i3)).serviceInfo.packageName);
                                                        String valueOf = String.valueOf(obj);
                                                        HashMap hashMap = new HashMap();
                                                        hashMap.put("appPackage", ((ResolveInfo) arrayList2.get(i3)).serviceInfo.packageName);
                                                        hashMap.put("targetVer", valueOf);
                                                        arrayList.add(hashMap);
                                                        C5969c.this.f14703l.put(((ResolveInfo) arrayList2.get(i3)).serviceInfo.packageName, Integer.valueOf(i4));
                                                    }
                                                }
                                            } catch (Throwable th2) {
                                                MobLog.getInstance().m11341d(th2);
                                                if (abstractC6201c != null) {
                                                    abstractC6201c.mo11088a(arrayList);
                                                    return;
                                                }
                                                return;
                                            }
                                        }
                                        if (abstractC6201c != null) {
                                            abstractC6201c.mo11088a(arrayList);
                                        }
                                    }
                                });
                            }
                        } else if (abstractC6201c != null) {
                            abstractC6201c.mo11088a(arrayList);
                        }
                    }
                });
            } else if (abstractC6201c != null) {
                abstractC6201c.mo11088a(arrayList);
            }
        } catch (Throwable th) {
            C5994e.m11860a().m11855b(th);
            if (abstractC6201c != null) {
                abstractC6201c.mo11088a(arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m11894b(final AbstractC6201c<Boolean> abstractC6201c) {
        m11907a(new AbstractC6201c<List<HashMap<String, String>>>() { // from class: com.mob.mgs.impl.c.11
            @Override // com.mob.tools.utils.AbstractC6201c
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo11088a(List<HashMap<String, String>> list) {
                boolean z = false;
                try {
                    C5994e m11860a = C5994e.m11860a();
                    m11860a.m11859a("[GD] avlb uplv tg: " + list);
                    HashMap hashMap = (HashMap) C5992d.m11863a(list, MobMCL.getSuid(), MobMGS.getDS());
                    C5994e m11860a2 = C5994e.m11860a();
                    m11860a2.m11859a("[GD] gd resp:" + hashMap);
                    if (hashMap != null && !hashMap.isEmpty()) {
                        C5969c.this.f14698g = (String) ResHelper.forceCast(hashMap.get("workId"), null);
                        C5969c.this.f14699h = ((Boolean) ResHelper.forceCast(hashMap.get("syncIdState"), false)).booleanValue();
                        C5969c.this.f14701j = ((Integer) ResHelper.forceCast(hashMap.get("asMaster"), 0)).intValue();
                        C5969c.this.f14700i = ((Integer) ResHelper.forceCast(hashMap.get("pollTotal"), 0)).intValue();
                        C5969c.this.f14702k = (List) hashMap.get("pkgList");
                        z = true;
                    }
                    AbstractC6201c abstractC6201c2 = abstractC6201c;
                    if (abstractC6201c2 == null) {
                    }
                } catch (Throwable th) {
                    try {
                        C5994e.m11860a().m11855b(th);
                    } finally {
                        AbstractC6201c abstractC6201c3 = abstractC6201c;
                        if (abstractC6201c3 != null) {
                            abstractC6201c3.mo11088a(false);
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public boolean m11890c(String str) {
        String[] split;
        try {
            String string = Settings.Secure.getString(MobSDK.getContext().getContentResolver(), "app_lock_list");
            if (!TextUtils.isEmpty(string)) {
                for (String str2 : string.split(";")) {
                    if (str2 != null && str2.equals(str)) {
                        return true;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    /* renamed from: c */
    public boolean m11892c() {
        return this.f14701j == 1;
    }

    /* renamed from: a */
    public void m11902a(final String str, final boolean z) {
        this.f14697f.execute(new Runnable() { // from class: com.mob.mgs.impl.c.12
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(200L);
                    C5969c.this.m11901a(false, str);
                    if (z) {
                        Thread.sleep(500L);
                        C5969c.this.m11889d();
                    }
                } catch (Throwable unused) {
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0163 A[Catch: Throwable -> 0x01fd, TryCatch #0 {Throwable -> 0x01fd, blocks: (B:3:0x0004, B:6:0x0027, B:8:0x002e, B:9:0x0034, B:12:0x0061, B:13:0x006c, B:15:0x0072, B:18:0x009f, B:20:0x00ab, B:48:0x01d5, B:50:0x01f3, B:23:0x00bb, B:26:0x00ef, B:28:0x00f7, B:36:0x0131, B:38:0x0163, B:40:0x0196, B:41:0x019f, B:43:0x01a8, B:44:0x01aa, B:46:0x01c1, B:45:0x01ba, B:32:0x011f, B:34:0x012b), top: B:55:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01ba A[Catch: Throwable -> 0x01fd, TryCatch #0 {Throwable -> 0x01fd, blocks: (B:3:0x0004, B:6:0x0027, B:8:0x002e, B:9:0x0034, B:12:0x0061, B:13:0x006c, B:15:0x0072, B:18:0x009f, B:20:0x00ab, B:48:0x01d5, B:50:0x01f3, B:23:0x00bb, B:26:0x00ef, B:28:0x00f7, B:36:0x0131, B:38:0x0163, B:40:0x0196, B:41:0x019f, B:43:0x01a8, B:44:0x01aa, B:46:0x01c1, B:45:0x01ba, B:32:0x011f, B:34:0x012b), top: B:55:0x0004 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m11901a(boolean r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 546
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.mgs.impl.C5969c.m11901a(boolean, java.lang.String):void");
    }

    /* renamed from: b */
    public void m11893b(String str) {
        C5994e m11860a = C5994e.m11860a();
        m11860a.m11859a("[Guard] syncId newClientPkg : " + str + " syncIdFailed : " + this.f14705n);
        if (this.f14705n) {
            this.f14697f.execute(new Runnable() { // from class: com.mob.mgs.impl.c.15
                @Override // java.lang.Runnable
                public void run() {
                    if (C5969c.this.f14705n) {
                        C5969c.this.m11889d();
                    }
                }
            });
        }
    }

    /* renamed from: d */
    public void m11889d() {
        if (this.f14699h) {
            m11884g();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m11884g() {
        Bundle bundle;
        C5994e m11860a = C5994e.m11860a();
        m11860a.m11859a("[Guard] syncId upPkgList: " + this.f14702k);
        List<HashMap<String, Object>> list = this.f14702k;
        if (list == null || list.size() == 0) {
            return;
        }
        String suid = MobMCL.getSuid();
        long createSuidTime = MobMCL.getCreateSuidTime();
        long j = createSuidTime;
        String str = suid;
        for (HashMap<String, Object> hashMap : this.f14702k) {
            C5677a c5677a = null;
            String str2 = (String) ResHelper.forceCast(hashMap.get("pkg"), null);
            C5677a c5677a2 = new C5677a();
            c5677a2.f13996a = 1001;
            try {
                c5677a = C5688b.m12832a(1, str2, "MOBGUARD", c5677a2, 5000L);
            } catch (Throwable th) {
                C5994e.m11860a().m11855b(th);
            }
            C5994e m11860a2 = C5994e.m11860a();
            m11860a2.m11859a("[Guard] syncId getClientIDs sendAPCMessage pkg: " + str2 + ", response:" + c5677a);
            if (c5677a != null && (bundle = c5677a.f14000e) != null) {
                String string = bundle.getString("guardId");
                long j2 = bundle.getLong("timestamp");
                if (!TextUtils.isEmpty(string) && j2 > 0 && j2 < j) {
                    str = string;
                    j = j2;
                }
            }
        }
        C5994e m11860a3 = C5994e.m11860a();
        m11860a3.m11859a("[Guard] syncId update guardId :" + str + ", oldId: " + suid);
        boolean equals = str.equals(suid) ^ true;
        if (equals) {
            final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
            MobMCL.syncSuid(str, j, new AbstractC6201c<Boolean>() { // from class: com.mob.mgs.impl.c.16
                @Override // com.mob.tools.utils.AbstractC6201c
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo11088a(Boolean bool) {
                    linkedBlockingQueue.offer(bool);
                }
            });
            try {
                ((Boolean) linkedBlockingQueue.poll(1000L, TimeUnit.MILLISECONDS)).booleanValue();
            } catch (Throwable th2) {
                MobLog.getInstance().m11341d(th2);
            }
        }
        m11905a(str, j);
        if (equals) {
            try {
                C5992d.m11867a(suid, str, this.f14698g);
            } catch (Throwable th3) {
                C5994e.m11860a().m11857a(th3);
            }
        }
    }

    /* renamed from: a */
    private void m11905a(String str, long j) {
        this.f14705n = false;
        for (HashMap<String, Object> hashMap : this.f14702k) {
            String str2 = (String) ResHelper.forceCast(hashMap.get("pkg"), null);
            try {
                C5677a c5677a = new C5677a();
                c5677a.f13996a = 1003;
                Bundle bundle = new Bundle();
                bundle.putString("guardId", str);
                bundle.putLong("timestamp", j);
                bundle.putString("workId", this.f14698g);
                c5677a.f14000e = bundle;
                C5677a m12832a = C5688b.m12832a(1, str2, "MOBGUARD", c5677a, 5000L);
                C5994e m11860a = C5994e.m11860a();
                m11860a.m11859a("[Guard] syncId updateClientIDs sendAPCMessage :" + str2 + ", response: " + m12832a);
            } catch (Throwable th) {
                C5994e.m11860a().m11857a(th);
                this.f14705n = true;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r9v6, types: [com.mob.mgs.impl.c$2] */
    @Override // com.mob.apc.C5688b.InterfaceC5690b
    /* renamed from: a */
    public C5677a mo11904a(String str, C5677a c5677a, long j) {
        Bundle bundle;
        C5994e m11860a = C5994e.m11860a();
        m11860a.m11859a("[Guard] onAPCMessageReceive APCMessage:" + c5677a + ", pkg:" + str);
        C5677a c5677a2 = new C5677a();
        final String suid = MobMCL.getSuid();
        long createSuidTime = MobMCL.getCreateSuidTime();
        int i = c5677a.f13996a;
        if (i == 1001) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("guardId", suid);
            bundle2.putLong("timestamp", createSuidTime);
            bundle2.putString("pkg", MobSDK.getContext().getPackageName());
            c5677a2.f14000e = bundle2;
        } else if (i == 1003 && (bundle = c5677a.f14000e) != null) {
            final String string = bundle.getString("guardId");
            final long j2 = bundle.getLong("timestamp");
            final String string2 = bundle.getString("workId");
            if (string != null && j2 > 0 && !suid.equals(string) && j2 < createSuidTime) {
                new AbstractC6003h() { // from class: com.mob.mgs.impl.c.2
                    @Override // com.mob.tools.utils.AbstractC6218i
                    /* renamed from: a */
                    public void mo10997a() throws Throwable {
                        MobMCL.syncSuid(string, j2, new AbstractC6201c<Boolean>() { // from class: com.mob.mgs.impl.c.2.1
                            @Override // com.mob.tools.utils.AbstractC6201c
                            /* renamed from: a  reason: avoid collision after fix types in other method */
                            public void mo11088a(Boolean bool) {
                            }
                        });
                        C5992d.m11867a(suid, string, string2);
                    }
                }.start();
            }
        }
        return c5677a2;
    }

    @Override // com.mob.apc.C5688b.InterfaceC5691c
    /* renamed from: a */
    public void mo11917a(Bundle bundle) {
        if (bundle != null) {
            Intent intent = new Intent();
            intent.putExtra("workId", bundle.getString("workId"));
            intent.putExtra("appkey", bundle.getString("appkey"));
            intent.putExtra("duid", bundle.getString("duid"));
            intent.putExtra("guardId", bundle.getString("guardId"));
            intent.putExtra("pkg", bundle.getString("pkg"));
            intent.putExtra("acServiceType", bundle.getInt("acsActType"));
        }
    }

    @Override // com.mob.apc.C5688b.InterfaceC5689a
    /* renamed from: a */
    public HashMap<String, Object> mo11921a(int i, String str) {
        int i2 = i == 1 ? 2001 : i == 2 ? 2002 : -1;
        C5994e m11860a = C5994e.m11860a();
        m11860a.m11859a("[requestInvokeGd]finalBusType: " + i2);
        if (i2 != -1) {
            HashMap<String, Object> hashMap = new HashMap<>();
            try {
                final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
                m11920a(i2, str, new AbstractC6201c<HashMap<String, Object>>() { // from class: com.mob.mgs.impl.c.3
                    @Override // com.mob.tools.utils.AbstractC6201c
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo11088a(HashMap<String, Object> hashMap2) {
                        linkedBlockingQueue.offer(hashMap2);
                    }
                });
                return (HashMap) linkedBlockingQueue.poll(2000L, TimeUnit.MILLISECONDS);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return hashMap;
            }
        }
        return new HashMap<>();
    }

    @Override // com.mob.apc.C5688b.InterfaceC5689a
    /* renamed from: a */
    public boolean mo11906a(String str) {
        try {
            final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
            m11903a(str, new AbstractC6201c<Boolean>() { // from class: com.mob.mgs.impl.c.4
                @Override // com.mob.tools.utils.AbstractC6201c
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo11088a(Boolean bool) {
                    linkedBlockingQueue.offer(bool);
                }
            });
            return ((Boolean) linkedBlockingQueue.poll(1000L, TimeUnit.MILLISECONDS)).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: a */
    private static void m11918a(Context context, final String str, final AbstractC6201c<Boolean> abstractC6201c) {
        C6152DH.requester(context).getMpfof(true, str, 0).request(new C6152DH.DHResponder() { // from class: com.mob.mgs.impl.c.5
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                try {
                    Object mpfof = dHResponse.getMpfof(new int[0]);
                    if (mpfof != null) {
                        ApplicationInfo m11360a = C6122c.m11360a(mpfof, str);
                        boolean z = (m11360a.flags & 1) == 0 && (m11360a.flags & 128) == 0;
                        boolean z2 = (m11360a.flags & 2097152) == 0;
                        if (z && z2) {
                            if (abstractC6201c != null) {
                                abstractC6201c.mo11088a(true);
                            }
                        } else if (abstractC6201c != null) {
                            abstractC6201c.mo11088a(false);
                        }
                    } else if (abstractC6201c != null) {
                        abstractC6201c.mo11088a(false);
                    }
                } catch (Throwable th) {
                    C5994e.m11860a().m11855b(th);
                    AbstractC6201c abstractC6201c2 = abstractC6201c;
                    if (abstractC6201c2 != null) {
                        abstractC6201c2.mo11088a(false);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.mgs.impl.c$6 */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C59846 extends AbstractC6201c<Boolean> {

        /* renamed from: a */
        final /* synthetic */ String f14740a;

        /* renamed from: b */
        final /* synthetic */ AbstractC6201c f14741b;

        /* renamed from: c */
        final /* synthetic */ int f14742c;

        /* renamed from: d */
        final /* synthetic */ String f14743d;

        /* renamed from: e */
        final /* synthetic */ String f14744e;

        C59846(String str, AbstractC6201c abstractC6201c, int i, String str2, String str3) {
            this.f14740a = str;
            this.f14741b = abstractC6201c;
            this.f14742c = i;
            this.f14743d = str2;
            this.f14744e = str3;
        }

        @Override // com.mob.tools.utils.AbstractC6201c
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo11088a(Boolean bool) {
            C5994e m11860a = C5994e.m11860a();
            m11860a.m11859a("[GD]target: " + this.f14740a + ", isLv: " + bool);
            if (!bool.booleanValue()) {
                if (C5969c.this.f14703l == null) {
                    C5969c.this.m11907a(new AbstractC6201c<List<HashMap<String, String>>>() { // from class: com.mob.mgs.impl.c.6.1
                        @Override // com.mob.tools.utils.AbstractC6201c
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void mo11088a(List<HashMap<String, String>> list) {
                            if (C59846.this.f14741b != null) {
                                C5969c.this.m11899b(C59846.this.f14742c, C59846.this.f14740a, C59846.this.f14743d, C59846.this.f14744e, new AbstractC6201c<HashMap<String, Object>>() { // from class: com.mob.mgs.impl.c.6.1.1
                                    @Override // com.mob.tools.utils.AbstractC6201c
                                    /* renamed from: a  reason: avoid collision after fix types in other method */
                                    public void mo11088a(HashMap<String, Object> hashMap) {
                                        C59846.this.f14741b.mo11088a(hashMap);
                                    }
                                });
                            }
                        }
                    });
                    return;
                } else if (this.f14741b != null) {
                    C5969c.this.m11899b(this.f14742c, this.f14740a, this.f14743d, this.f14744e, new AbstractC6201c<HashMap<String, Object>>() { // from class: com.mob.mgs.impl.c.6.2
                        @Override // com.mob.tools.utils.AbstractC6201c
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void mo11088a(HashMap<String, Object> hashMap) {
                            C59846.this.f14741b.mo11088a(hashMap);
                        }
                    });
                    return;
                } else {
                    return;
                }
            }
            AbstractC6201c abstractC6201c = this.f14741b;
            if (abstractC6201c != null) {
                abstractC6201c.mo11088a(new HashMap());
            }
        }
    }

    /* renamed from: a */
    private void m11919a(int i, String str, String str2, String str3, AbstractC6201c<HashMap<String, Object>> abstractC6201c) {
        C5994e m11860a = C5994e.m11860a();
        m11860a.m11859a("[GD]busType: " + i + ", target: " + str + ", workId: " + str2 + ", duid: " + str3);
        m11918a(MobSDK.getContext(), str, new C59846(str, abstractC6201c, i, str2, str3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m11899b(int i, final String str, String str2, String str3, final AbstractC6201c<HashMap<String, Object>> abstractC6201c) {
        final HashMap<String, Object> hashMap = new HashMap<>();
        try {
            ComponentName componentName = new ComponentName(str, f14694c[((Integer) ResHelper.forceCast(this.f14703l.get(str), 0)).intValue()]);
            Intent intent = new Intent();
            intent.addFlags(411041792);
            intent.setComponent(componentName);
            intent.putExtra("workId", str2);
            intent.putExtra("duid", str3);
            intent.putExtra("appkey", C5871t.m12196a());
            intent.putExtra("pkg", MobSDK.getContext().getPackageName());
            intent.putExtra("guardId", MobMCL.getSuid());
            intent.putExtra("busType", i);
            long currentTimeMillis = System.currentTimeMillis();
            hashMap.put("startActivityTime", Long.valueOf(currentTimeMillis));
            MobSDK.getContext().startActivity(intent);
            hashMap.put("startActivityDuration", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            Thread.sleep(320L);
            m11918a(MobSDK.getContext(), str, new AbstractC6201c<Boolean>() { // from class: com.mob.mgs.impl.c.7
                @Override // com.mob.tools.utils.AbstractC6201c
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo11088a(Boolean bool) {
                    C5994e m11860a = C5994e.m11860a();
                    m11860a.m11859a("[GD] stAct rst. pkg: " + str + ", lv: " + bool);
                    if (bool.booleanValue()) {
                        hashMap.put("executeResult", "success");
                    } else {
                        hashMap.put("executeResult", "uncertain");
                    }
                    AbstractC6201c abstractC6201c2 = abstractC6201c;
                    if (abstractC6201c2 != null) {
                        abstractC6201c2.mo11088a(hashMap);
                    }
                }
            });
        } catch (Throwable th) {
            C5994e.m11860a().m11857a(th);
            C5994e m11860a = C5994e.m11860a();
            m11860a.m11859a("[GD] stAct rst.  pkg: " + str + ", exception: " + th.getMessage());
            hashMap.put("executeResult", "fail");
            if (abstractC6201c != null) {
                abstractC6201c.mo11088a(hashMap);
            }
        }
    }

    /* renamed from: a */
    public void m11920a(int i, String str, AbstractC6201c<HashMap<String, Object>> abstractC6201c) {
        m11919a(i, str, this.f14698g, C5995f.m11854a().m11848f(), abstractC6201c);
    }

    /* renamed from: a */
    private void m11903a(String str, final AbstractC6201c<Boolean> abstractC6201c) {
        C5994e m11860a;
        StringBuilder sb;
        boolean z;
        final boolean[] zArr = {false};
        try {
            if (this.f14706o.equals(str) && abstractC6201c != null) {
                zArr[0] = true;
                abstractC6201c.mo11088a(Boolean.valueOf(zArr[0]));
            }
            LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
            int m11942a = C5959a.m11949a().m11942a(str, linkedBlockingQueue);
            if (m11942a == 0) {
                m11918a(this.f14706o, str, new AbstractC6201c<Boolean>() { // from class: com.mob.mgs.impl.c.8
                    @Override // com.mob.tools.utils.AbstractC6201c
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo11088a(Boolean bool) {
                        if (abstractC6201c != null) {
                            zArr[0] = bool.booleanValue();
                            abstractC6201c.mo11088a(Boolean.valueOf(zArr[0]));
                        }
                    }
                });
            } else if (m11942a == 1) {
                Boolean bool = (Boolean) linkedBlockingQueue.poll(2000L, TimeUnit.MILLISECONDS);
                if (bool != null) {
                    zArr[0] = bool.booleanValue();
                }
                if (abstractC6201c != null) {
                    abstractC6201c.mo11088a(Boolean.valueOf(zArr[0]));
                }
            }
            m11860a = C5994e.m11860a();
            sb = new StringBuilder();
            sb.append("checkAppLive appStatus: ");
            sb.append(m11942a);
            sb.append(", isLive: ");
            z = zArr[0];
        } catch (Throwable th) {
            try {
                C5994e.m11860a().m11857a(th);
                if (abstractC6201c != null) {
                    abstractC6201c.mo11088a(Boolean.valueOf(zArr[0]));
                }
                m11860a = C5994e.m11860a();
                sb = new StringBuilder();
                sb.append("checkAppLive appStatus: ");
                sb.append(-1);
                sb.append(", isLive: ");
                z = zArr[0];
            } catch (Throwable th2) {
                C5994e m11860a2 = C5994e.m11860a();
                m11860a2.m11859a("checkAppLive appStatus: -1, isLive: " + zArr[0]);
                throw th2;
            }
        }
        sb.append(z);
        m11860a.m11859a(sb.toString());
    }
}
