package com.mob.mcl.p233a;

import android.os.Bundle;
import android.text.TextUtils;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.StringPart;
import com.mob.tools.utils.HashonHelper;
import java.io.Serializable;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.mcl.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5905b implements Serializable {

    /* renamed from: a */
    public String f14553a;

    /* renamed from: b */
    public String f14554b;

    /* renamed from: c */
    public String f14555c;

    /* renamed from: d */
    public String f14556d;

    /* renamed from: e */
    public int f14557e;

    /* renamed from: f */
    public int f14558f;

    /* renamed from: g */
    public int f14559g;

    /* renamed from: a */
    public static HashMap<String, String> m12096a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return HashonHelper.fromJson(str);
    }

    /* renamed from: a */
    public static C5905b m12097a(Bundle bundle) {
        C5905b c5905b = new C5905b();
        if (bundle != null) {
            c5905b.f14553a = bundle.getString("type");
            c5905b.f14554b = bundle.getString("url");
            c5905b.f14555c = bundle.getString("headers");
            c5905b.f14557e = bundle.getInt("chunkLength");
            c5905b.f14556d = bundle.getString("body");
            c5905b.f14558f = bundle.getInt("readTimout");
            c5905b.f14559g = bundle.getInt("connectionTimeout");
        }
        return c5905b;
    }

    /* renamed from: a */
    public static Bundle m12095a(String str, String str2, HashMap<String, String> hashMap, StringPart stringPart, int i, NetworkHelper.NetworkTimeOut networkTimeOut) {
        Bundle bundle = new Bundle();
        bundle.putString("type", str);
        bundle.putString("url", str2);
        HashMap hashMap2 = new HashMap();
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        new HashonHelper();
        bundle.putString("headers", HashonHelper.fromHashMap(hashMap2));
        bundle.putInt("chunkLength", i);
        if (stringPart != null) {
            bundle.putString("body", stringPart.toString());
        }
        bundle.putInt("readTimout", networkTimeOut.readTimout);
        bundle.putInt("connectionTimeout", networkTimeOut.connectionTimeout);
        return bundle;
    }
}
