package com.mob.mgs.impl;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5847i;
import com.mob.commons.C5871t;
import com.mob.mcl.p234b.C5906a;
import com.mob.tools.network.NetCommunicator;
import com.mob.tools.utils.C6152DH;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.mgs.impl.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5992d {

    /* renamed from: a */
    private static NetCommunicator f14758a;

    /* renamed from: a */
    public static <T> T m11863a(List<HashMap<String, String>> list, String str, boolean z) throws Throwable {
        HashMap<String, Object> m11868a = m11868a();
        m11868a.put("guardId", str);
        m11868a.put("targetAppInfoDtoList", list);
        m11868a.put("deviceSwitch", Integer.valueOf(z ? 1 : 0));
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String[] strArr = new String[1];
        final int[] iArr = new int[1];
        C6152DH.requester(MobSDK.getContext()).getHmOsDetailedVer().getHmEPMState().request(new C6152DH.DHResponder() { // from class: com.mob.mgs.impl.d.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                strArr[0] = dHResponse.getHmOsDetailedVer();
                iArr[0] = dHResponse.getHmEPMState();
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        m11868a.put("hmv", strArr[0]);
        m11868a.put("ep", Integer.valueOf(iArr[0]));
        C5994e m11860a = C5994e.m11860a();
        m11860a.m11859a("[GD][/v6/gd] request: " + m11868a);
        return (T) m11865a("/v6/gd", m11868a);
    }

    /* renamed from: a */
    public static <T> T m11864a(List<HashMap<String, Object>> list, String str, String str2) throws Throwable {
        HashMap<String, Object> m11868a = m11868a();
        m11868a.put("guardId", str);
        m11868a.put("workId", str2);
        m11868a.put("pkgList", list);
        C5994e m11860a = C5994e.m11860a();
        m11860a.m11859a("[request][/v6/pu] request: " + m11868a);
        return (T) m11865a("/v6/pu", m11868a);
    }

    /* renamed from: a */
    public static <T> T m11866a(String str, String str2, String str3, String str4, String str5, String str6, int i) throws Throwable {
        HashMap<String, Object> m11868a = m11868a();
        m11868a.put("guardId", str5);
        m11868a.put("workId", str6);
        m11868a.put("pullDuid", str);
        m11868a.put("pullAppkey", str2);
        m11868a.put("pullPkg", str3);
        m11868a.put("pullGuardId", str4);
        m11868a.put("pullTime", Long.valueOf(System.currentTimeMillis()));
        m11868a.put("actType", Integer.valueOf(i));
        C5994e m11860a = C5994e.m11860a();
        m11860a.m11859a("[request][/v6/bpu] request: " + m11868a);
        return (T) m11865a("/v6/bpu", m11868a);
    }

    /* renamed from: a */
    public static <T> T m11867a(String str, String str2, String str3) throws Throwable {
        HashMap<String, Object> m11868a = m11868a();
        m11868a.put("workId", str3);
        m11868a.put("oldGuardId", str);
        m11868a.put("newGuardId", str2);
        C5994e m11860a = C5994e.m11860a();
        m11860a.m11859a("[request][guardId/uploadV5] request: " + m11868a);
        return (T) m11865a("/guard/guardId/uploadV5", m11868a);
    }

    /* renamed from: a */
    public static <T> T m11862a(boolean z, boolean z2) throws Throwable {
        HashMap<String, Object> m11868a = m11868a();
        m11868a.put("guardId", C5906a.m12094a());
        if (z2) {
            if (!TextUtils.isEmpty(C6004i.m11834c())) {
                m11868a.put("duid", C6004i.m11834c());
            }
            if (!TextUtils.isEmpty(C6004i.m11833d())) {
                m11868a.put("guardId", C6004i.m11833d());
            }
        }
        m11868a.put("deviceSwitch", Integer.valueOf(z ? 1 : 0));
        C5994e m11860a = C5994e.m11860a();
        m11860a.m11859a("[request][deviceSwitch/updateV5] request: " + m11868a);
        return (T) m11865a("/v6/dsu", m11868a);
    }

    /* renamed from: a */
    private static HashMap<String, Object> m11868a() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("versionTime", "2021.11.17 18:38");
        hashMap.put("appkey", C5871t.m12196a());
        hashMap.put("appver", Integer.valueOf(C6152DH.SyncMtd.getAppVersion()));
        hashMap.put("platVersion", C6152DH.SyncMtd.getOSVersionName());
        hashMap.put("apppkg", MobSDK.getContext().getPackageName());
        hashMap.put("sdkver", 50000);
        hashMap.put("duid", C5995f.m11854a().m11848f());
        hashMap.put("product", 1);
        hashMap.put("plat", 1);
        hashMap.put("brand", C6152DH.SyncMtd.getManufacturer());
        hashMap.put("model", C6152DH.SyncMtd.getModel());
        hashMap.put("modelVersion", C6152DH.SyncMtd.getOSVersionName());
        return hashMap;
    }

    /* renamed from: b */
    private static synchronized NetCommunicator m11861b() {
        NetCommunicator netCommunicator;
        synchronized (C5992d.class) {
            if (f14758a == null) {
                f14758a = new NetCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1");
            }
            netCommunicator = f14758a;
        }
        return netCommunicator;
    }

    /* renamed from: a */
    private static <T> T m11865a(String str, HashMap<String, Object> hashMap) throws Throwable {
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("versionTime", "2021.11.17 18:38");
        NetCommunicator m11861b = m11861b();
        return (T) m11861b.requestSynchronized(hashMap2, hashMap, C5847i.m12275a().m12272a("gdg") + str, false);
    }
}
