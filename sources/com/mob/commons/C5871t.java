package com.mob.commons;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.utils.C6152DH;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.t */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5871t {
    /* renamed from: a */
    public static void m12193a(boolean z) {
        try {
            C5879w.m12165a(z);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
        }
    }

    /* renamed from: a */
    public static String m12196a() {
        if (TextUtils.isEmpty(C5868q.f14468a) && MobSDK.getContext() != null) {
            C5868q.m12207a(MobSDK.getContext());
        }
        if (TextUtils.isEmpty(C5868q.f14468a)) {
            return C5868q.f14470c;
        }
        return C5868q.f14468a;
    }

    /* renamed from: b */
    public static int m12192b() {
        int m12156d = C5879w.m12156d();
        if (m12156d == 1) {
            return 1;
        }
        return m12156d == 0 ? -1 : 0;
    }

    /* renamed from: c */
    public static boolean m12191c() {
        int m12192b = m12192b();
        if (m12192b == 2 || m12192b == 1) {
            return C5747b.m12572b();
        }
        return false;
    }

    /* renamed from: d */
    public static boolean m12190d() {
        int m12192b = m12192b();
        if (m12192b == 2 || m12192b == 1) {
            C5879w.m12153g();
            return true ^ C5747b.m12589a();
        }
        return true;
    }

    /* renamed from: a */
    public static HashMap<String, Object> m12195a(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(C5731l.m12674a("006ekk^em]g0el"), m12196a());
        hashMap.put(C5731l.m12674a("006ekkkIemff"), C6152DH.SyncMtd.getPackageName());
        hashMap.put(C5731l.m12674a("006ekkFee1gGek"), C6152DH.SyncMtd.getAppVersionName());
        hashMap.put(C5731l.m12674a("004khej"), String.valueOf(C6152DH.SyncMtd.getPlatformCode()));
        hashMap.put(C5731l.m12674a("011fgj=ggfeekem,j4elDkg"), str);
        String m12317b = C5831e.m12317b();
        if (!TextUtils.isEmpty(m12317b)) {
            hashMap.put(C5731l.m12674a("004Jedehejed"), m12317b);
        }
        return hashMap;
    }

    /* renamed from: e */
    public static HashMap<String, Object> m12189e() {
        final HashMap<String, Object>[] hashMapArr = {new HashMap<>()};
        C6152DH.requester(MobSDK.getContext()).getCarrier().getDetailNetworkTypeForStatic().getMIUIVersion().getSignMD5().getODH().request(new C6152DH.DHResponder() { // from class: com.mob.commons.t.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                hashMapArr[0] = C5871t.m12195a(dHResponse.getDetailNetworkTypeForStatic());
                hashMapArr[0].put(C5731l.m12674a("006KgiedemeeSgQek"), Integer.valueOf(MobSDK.SDK_VERSION_CODE));
                hashMapArr[0].put(C5731l.m12674a("004[edehejed"), C5831e.m12319a((MobProduct) null));
                hashMapArr[0].put(C5731l.m12674a("006ekk,ee3gAek"), Integer.valueOf(C6152DH.SyncMtd.getAppVersion()));
                hashMapArr[0].put(C5731l.m12674a("007de?ekekej.gKek"), dHResponse.getCarrier());
                hashMapArr[0].put(C5731l.m12674a("0053egfeedJgh"), C6152DH.SyncMtd.getModel());
                hashMapArr[0].put(C5731l.m12674a("007:fg;edj(feekel"), C6152DH.SyncMtd.getManufacturer());
                hashMapArr[0].put(C5731l.m12674a("0064gielgiee'gQek"), C6152DH.SyncMtd.getOSVersionName());
                hashMapArr[0].put(C5731l.m12674a("005Kehejee!gLek"), dHResponse.getMIUIVersion());
                hashMapArr[0].put(C5731l.m12674a("0090gielgiee7g'ekejRfj"), Integer.valueOf(C6152DH.SyncMtd.getOSVersionInt()));
                hashMapArr[0].put(C5731l.m12674a("010dhBejLgfj%flejegKg"), Long.valueOf(System.currentTimeMillis()));
                hashMapArr[0].put(C5731l.m12674a("006ekkWegedij"), dHResponse.getSignMD5());
                hashMapArr[0].put(C5731l.m12674a("005-gfekUefMed"), C6152DH.SyncMtd.getBrand());
                hashMapArr[0].put("usridt", C5895z.m12120c());
                hashMapArr[0].put(C5731l.m12674a("004Qegfeejed"), dHResponse.getODH());
            }
        });
        return hashMapArr[0];
    }

    /* renamed from: a */
    public static String m12194a(String str, String str2, String str3, boolean z) {
        if (m12190d()) {
            MobLog.getInstance().m11342d("isForb: true", new Object[0]);
            return null;
        }
        return C5847i.m12275a().m12269a(str, str2, str3, z);
    }
}
