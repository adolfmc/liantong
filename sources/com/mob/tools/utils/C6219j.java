package com.mob.tools.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5741aa;
import com.mob.commons.C5829d;
import com.mob.commons.C5855l;
import com.mob.commons.C5873u;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.C6152DH;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* renamed from: com.mob.tools.utils.j */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6219j {

    /* renamed from: a */
    private static volatile C6219j f15350a;

    /* renamed from: b */
    private BroadcastReceiver f15351b;

    /* renamed from: c */
    private final ConcurrentHashMap<String, InterfaceC6223a> f15352c = new ConcurrentHashMap<>();

    /* renamed from: d */
    private volatile long f15353d = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.utils.j$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC6223a {
        /* renamed from: a */
        void mo10990a();
    }

    private C6219j() {
        this.f15351b = null;
        if (C5829d.m12329c() || C5829d.m12328d()) {
            this.f15351b = new BroadcastReceiver() { // from class: com.mob.tools.utils.j.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    C6219j.m10995a().m10994a(context, intent);
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(C5855l.m12238a("029fgHfeflgffkfehfZghkKhfhhfkghfkhfglgmgngmiifjilhlgngjkfii"));
            C5873u.m12185a(this.f15351b, intentFilter);
        }
    }

    /* renamed from: a */
    public static C6219j m10995a() {
        if (f15350a == null) {
            synchronized (C6219j.class) {
                if (f15350a == null) {
                    f15350a = new C6219j();
                }
            }
        }
        return f15350a;
    }

    /* renamed from: a */
    public void m10992a(String str, InterfaceC6223a interfaceC6223a) {
        if (interfaceC6223a == null || str == null || this.f15352c.containsKey(str)) {
            return;
        }
        this.f15352c.put(str, interfaceC6223a);
    }

    /* renamed from: a */
    public void m10994a(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            if (!C5855l.m12238a("029fgFfeflgffkfehf(ghk9hfhhfkghfkhfglgmgngmiifjilhlgngjkfii").equals(intent.getAction()) || intent.getParcelableExtra(C5855l.m12238a("011ghk[hhgfflfngk(gDghgf")) == null) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f15353d > 2000) {
                this.f15353d = currentTimeMillis;
                C5731l.m12681a().m12666e(2500L, new AbstractRunnableC6217h() { // from class: com.mob.tools.utils.j.2
                    @Override // com.mob.tools.utils.AbstractRunnableC6217h
                    /* renamed from: a */
                    protected void mo10991a() {
                        C6152DH.requester(MobSDK.getContext()).getMwfoForce(true).request(new C6152DH.DHResponder() { // from class: com.mob.tools.utils.j.2.1
                            @Override // com.mob.tools.utils.C6152DH.DHResponder
                            public void onResponse(C6152DH.DHResponse dHResponse) {
                                HashMap<String, Object> mwfoForce = dHResponse.getMwfoForce(new int[0]);
                                if (mwfoForce == null) {
                                    return;
                                }
                                String str = (String) mwfoForce.get("ssmt");
                                String str2 = (String) mwfoForce.get("bsmt");
                                NLog mobLog = MobLog.getInstance();
                                mobLog.m11342d("[MCM] cdi " + str + " bcdi " + str2 + " len " + C6219j.m10995a().f15352c.size(), new Object[0]);
                                if (TextUtils.isEmpty(str2) && (TextUtils.isEmpty(str) || C5855l.m12238a("014Skgfi8gHfn1g'gfhh5g_khhjhjfkfeki").equalsIgnoreCase(str))) {
                                    return;
                                }
                                TreeMap treeMap = new TreeMap();
                                treeMap.put("ssmt", str);
                                treeMap.put("bsmt", str2);
                                JSONObject jSONObject = new JSONObject(treeMap);
                                String MD5 = Data.MD5(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                                String m12632b = C5741aa.m12650a().m12632b(C5741aa.f14142i, (String) null);
                                if (m12632b == null || !m12632b.equals(MD5)) {
                                    for (InterfaceC6223a interfaceC6223a : C6219j.this.f15352c.values()) {
                                        interfaceC6223a.mo10990a();
                                    }
                                }
                            }
                        });
                    }
                });
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }
}
