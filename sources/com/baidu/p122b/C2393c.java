package com.baidu.p122b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.p122b.p125c.p129d.C2408a;
import com.baidu.p122b.p125c.p129d.C2412e;
import com.baidu.p122b.p125c.p129d.InterfaceC2411d;
import com.baidu.p122b.p130d.C2415a;
import com.baidu.p122b.p130d.C2417c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2393c {

    /* renamed from: a */
    private InterfaceC2411d f4183a;

    /* renamed from: b */
    private List<C2371b> f4184b;

    public C2393c() {
        m20299a();
    }

    /* renamed from: a */
    private static String m20296a(byte[] bArr) {
        StringBuilder sb;
        if (bArr != null) {
            String str = "";
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb = new StringBuilder();
                    sb.append(str);
                    str = "0";
                } else {
                    sb = new StringBuilder();
                }
                sb.append(str);
                sb.append(hexString);
                str = sb.toString();
            }
            return str.toLowerCase();
        }
        throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
    }

    /* renamed from: a */
    private void m20299a() {
        this.f4183a = new C2412e(C2418e.m20207a(), C2418e.m20206b());
    }

    /* renamed from: a */
    private boolean m20293a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            hashSet.add(str);
        }
        HashSet hashSet2 = new HashSet();
        for (String str2 : strArr2) {
            hashSet2.add(str2);
        }
        return hashSet.equals(hashSet2);
    }

    /* renamed from: a */
    private static byte[] m20295a(byte[] bArr, InterfaceC2411d interfaceC2411d) {
        C2408a m20242a = C2408a.m20242a();
        m20242a.m20241a(2, interfaceC2411d);
        return m20242a.m20240a(bArr);
    }

    /* renamed from: a */
    private String[] m20294a(Signature[] signatureArr) {
        String[] strArr = new String[signatureArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = m20296a(C2417c.m20208a(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    /* renamed from: a */
    List<C2371b> m20297a(Context context, Intent intent, boolean z) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.applicationInfo != null) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            String string = bundle.getString("galaxy_data");
                            if (!TextUtils.isEmpty(string)) {
                                byte[] m20212a = C2415a.m20212a(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(m20212a));
                                C2371b c2371b = new C2371b();
                                c2371b.f4124b = jSONObject.getInt("priority");
                                c2371b.f4123a = resolveInfo.activityInfo.applicationInfo;
                                if (context.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    c2371b.f4126d = true;
                                }
                                if (z) {
                                    String string2 = bundle.getString("galaxy_sf");
                                    if (!TextUtils.isEmpty(string2)) {
                                        PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = jSONObject.getJSONArray("sigs");
                                        String[] strArr = new String[jSONArray.length()];
                                        for (int i = 0; i < strArr.length; i++) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (m20293a(strArr, m20294a(packageInfo.signatures))) {
                                            byte[] m20295a = m20295a(C2415a.m20212a(string2.getBytes()), this.f4183a);
                                            if (m20295a != null && Arrays.equals(m20295a, C2417c.m20208a(m20212a))) {
                                                c2371b.f4125c = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(c2371b);
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        Collections.sort(arrayList, new C2414d(this));
        return arrayList;
    }

    /* renamed from: a */
    public boolean m20298a(Context context) {
        List<C2371b> m20297a = m20297a(context, new Intent("com.baidu.intent.action.GALAXY").setPackage(context.getPackageName()), true);
        if (m20297a == null || m20297a.size() == 0) {
            for (int i = 0; i < 3; i++) {
                Log.w("CuidBuddyInfoManager", "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
            }
            return false;
        }
        C2371b c2371b = m20297a.get(0);
        boolean z = c2371b.f4125c;
        if (!c2371b.f4125c) {
            for (int i2 = 0; i2 < 3; i2++) {
                Log.w("CuidBuddyInfoManager", "galaxy config err, In the release version of the signature should be matched");
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public List<C2371b> m20292b(Context context) {
        List<C2371b> list = this.f4184b;
        if (list != null) {
            return list;
        }
        m20298a(context);
        List<C2371b> m20297a = m20297a(context, new Intent("com.baidu.intent.action.GALAXY"), true);
        this.f4184b = m20297a;
        return m20297a;
    }
}
