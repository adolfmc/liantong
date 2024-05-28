package com.baidu.location.p136a;

import android.content.Context;
import android.util.Log;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2621a implements LBSAuthManagerListener {

    /* renamed from: b */
    private static Object f5115b = new Object();

    /* renamed from: c */
    private static C2621a f5116c = null;

    /* renamed from: d */
    private int f5118d = 0;

    /* renamed from: e */
    private Context f5119e = null;

    /* renamed from: f */
    private long f5120f = 0;

    /* renamed from: g */
    private String f5121g = null;

    /* renamed from: a */
    public int f5117a = 0;

    /* renamed from: a */
    public static C2621a m19575a() {
        C2621a c2621a;
        synchronized (f5115b) {
            if (f5116c == null) {
                f5116c = new C2621a();
            }
            c2621a = f5116c;
        }
        return c2621a;
    }

    /* renamed from: b */
    public static String m19572b(Context context) {
        try {
            return LBSAuthManager.getInstance(context).getPublicKey(context);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static String m19571c(Context context) {
        try {
            return LBSAuthManager.getInstance(context).getMCode();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void m19574a(Context context) {
        this.f5119e = context;
        LBSAuthManager.getInstance(this.f5119e).authenticate(false, "lbs_locsdk", null, this);
        this.f5120f = System.currentTimeMillis();
    }

    /* renamed from: b */
    public boolean m19573b() {
        int i = this.f5118d;
        boolean z = i == 0 || i == 602 || i == 601 || i == -10 || i == -11;
        if (this.f5119e != null) {
            long currentTimeMillis = System.currentTimeMillis() - this.f5120f;
            if (!z ? currentTimeMillis < 0 || currentTimeMillis > 10000 : currentTimeMillis > 86400000) {
                LBSAuthManager.getInstance(this.f5119e).authenticate(false, "lbs_locsdk", null, this);
                this.f5120f = System.currentTimeMillis();
            }
        }
        return z;
    }

    @Override // com.baidu.lbsapi.auth.LBSAuthManagerListener
    public void onAuthResult(int i, String str) {
        this.f5118d = i;
        if (this.f5118d == 0) {
            Log.i("baidu_location_service", "LocationAuthManager Authentication AUTHENTICATE_SUCC");
        } else {
            Log.i("baidu_location_service", "LocationAuthManager Authentication Error errorcode = " + i + " , msg = " + str);
        }
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("token") && jSONObject.getString("token") != null) {
                    this.f5121g = jSONObject.getString("token");
                }
                if (!jSONObject.has("ak_permission") || jSONObject.getInt("ak_permission") == 0) {
                    return;
                }
                this.f5117a = jSONObject.getInt("ak_permission");
                Log.i("baidu_location_service", "LocationAuthManager ak_permission = " + this.f5117a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
