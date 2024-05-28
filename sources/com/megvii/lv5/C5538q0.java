package com.megvii.lv5;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import com.megvii.lv5.C5395c2;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
@NBSInstrumented
/* renamed from: com.megvii.lv5.q0 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5538q0 {

    /* renamed from: a */
    public static C5395c2 f13187a;

    /* renamed from: a */
    public static int m13186a(byte[] bArr) {
        if (bArr.length != 4) {
            return -1;
        }
        return ((bArr[3] & 255) << 0) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    /* renamed from: a */
    public static C5395c2 m13187a(JSONObject jSONObject, JSONObject jSONObject2) {
        C5395c2 c5395c2 = new C5395c2();
        try {
            if (jSONObject.has("files")) {
                List<C5395c2.C5397b> m13175c = m13175c(jSONObject);
                if (c5395c2.f12413a == null) {
                    c5395c2.f12413a = new ArrayList();
                }
                c5395c2.f12413a.addAll(m13175c);
            }
            if (jSONObject.has("appList")) {
                List<String> m13188a = m13188a(jSONObject);
                if (c5395c2.f12414b == null) {
                    c5395c2.f12414b = new ArrayList();
                }
                c5395c2.f12414b.addAll(m13188a);
            }
            if (jSONObject.has("property")) {
                List<C5395c2.C5398c> m13173d = m13173d(jSONObject);
                if (c5395c2.f12415c == null) {
                    c5395c2.f12415c = new ArrayList();
                }
                c5395c2.f12415c.addAll(m13173d);
            }
            if (jSONObject.has("exec")) {
                List<C5395c2.C5396a> m13178b = m13178b(jSONObject);
                if (c5395c2.f12416d == null) {
                    c5395c2.f12416d = new ArrayList();
                }
                c5395c2.f12416d.addAll(m13178b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSONObject2 != null) {
            try {
                if (jSONObject2.has("files")) {
                    List<C5395c2.C5397b> m13175c2 = m13175c(jSONObject2);
                    if (c5395c2.f12413a == null) {
                        c5395c2.f12413a = new ArrayList();
                    }
                    c5395c2.f12413a.addAll(m13175c2);
                }
                if (jSONObject2.has("appList")) {
                    List<String> m13188a2 = m13188a(jSONObject2);
                    if (c5395c2.f12414b == null) {
                        c5395c2.f12414b = new ArrayList();
                    }
                    c5395c2.f12414b.addAll(m13188a2);
                }
                if (jSONObject2.has("property")) {
                    List<C5395c2.C5398c> m13173d2 = m13173d(jSONObject2);
                    if (c5395c2.f12415c == null) {
                        c5395c2.f12415c = new ArrayList();
                    }
                    c5395c2.f12415c.addAll(m13173d2);
                }
                if (jSONObject2.has("exec")) {
                    List<C5395c2.C5396a> m13178b2 = m13178b(jSONObject2);
                    if (c5395c2.f12416d == null) {
                        c5395c2.f12416d = new ArrayList();
                    }
                    c5395c2.f12416d.addAll(m13178b2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return c5395c2;
    }

    /* renamed from: a */
    public static List<String> m13188a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("appList");
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.optString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m13194a(Activity activity) {
        View decorView;
        int i;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 > 11 && i2 < 19) {
            decorView = activity.getWindow().getDecorView();
            i = 8;
        } else if (i2 < 19) {
            return;
        } else {
            decorView = activity.getWindow().getDecorView();
            i = 4098;
        }
        decorView.setSystemUiVisibility(i);
    }

    /* renamed from: a */
    public static void m13184a(int[] iArr, int i, int i2) {
        if (iArr.length <= 1 || i >= iArr.length || i2 >= iArr.length) {
            return;
        }
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    /* renamed from: a */
    public static void m13183a(int[] iArr, boolean z) {
        if (iArr == null || iArr.length <= 1) {
            return;
        }
        if (z) {
            if (iArr.length > 2) {
                m13184a(iArr, 0, 1);
                return;
            }
            return;
        }
        int length = iArr.length;
        while (length > 1) {
            int i = length - 1;
            m13184a(iArr, i, new Random().nextInt(length));
            length = i;
        }
    }

    /* renamed from: a */
    public static boolean m13193a(Context context) {
        return Build.VERSION.SDK_INT >= 23 ? context.checkSelfPermission("android.permission.RECORD_AUDIO") == 0 : context.getPackageManager().checkPermission("android.permission.RECORD_AUDIO", context.getPackageName()) == 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:74:0x011f, code lost:
        if (r7 == null) goto L77;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m13191a(android.content.Context r5, java.lang.String r6, byte[] r7) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5538q0.m13191a(android.content.Context, java.lang.String, byte[]):boolean");
    }

    /* renamed from: a */
    public static boolean m13185a(int[] iArr) {
        int length = iArr.length;
        if (length <= 4) {
            boolean z = true;
            if (length < 1) {
                return false;
            }
            HashSet hashSet = new HashSet();
            for (int i : iArr) {
                if (i < 1 || i > 4) {
                    z = false;
                    break;
                }
                hashSet.add(Integer.valueOf(i));
            }
            if (hashSet.size() != length) {
                return false;
            }
            return z;
        }
        return false;
    }

    /* renamed from: a */
    public static int[] m13195a(int i) {
        return new int[]{(16711680 & i) >> 16, (65280 & i) >> 8, i & 255, 255};
    }

    /* renamed from: b */
    public static long m13180b(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        C5628v2.m12958b("current", currentTimeMillis + "");
        long longValue = ((Long) C5527o2.m13252a(context, "megvii_liveness_expiretime", (Object) 0L)).longValue();
        C5628v2.m12958b("cacheExpire", longValue + "");
        if (longValue > currentTimeMillis) {
            return longValue;
        }
        return 0L;
    }

    /* renamed from: b */
    public static Bitmap m13182b(int i) {
        int[] m13195a = m13195a(i);
        Bitmap createBitmap = Bitmap.createBitmap(60, 60, Bitmap.Config.ARGB_8888);
        float f = 60 / 2.0f;
        for (int i2 = 0; i2 < 60; i2++) {
            for (int i3 = 0; i3 < 60; i3++) {
                float f2 = i3 - f;
                float f3 = i2 - f;
                float sqrt = (float) (Math.sqrt((f2 * f2) + (f3 * f3)) * 1.0d);
                float f4 = sqrt >= f ? 0.0f : 0.2f * (1.0f - (sqrt / f));
                if (f4 >= 0.135f) {
                    f4 = 0.135f;
                }
                createBitmap.setPixel(i2, i3, (((int) (f4 * m13195a[3])) << 24) | (m13195a[0] << 16) | (m13195a[1] << 8) | m13195a[2]);
            }
        }
        return createBitmap;
    }

    /* renamed from: b */
    public static String m13177b(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b);
            if (hexString.length() > 3) {
                hexString = hexString.substring(6);
            } else if (hexString.length() < 2) {
                hexString = "0" + hexString;
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    public static void m13181b(Activity activity) {
        int systemUiVisibility = activity.getWindow().getDecorView().getSystemUiVisibility();
        int i = Build.VERSION.SDK_INT;
        if (i >= 14) {
            systemUiVisibility ^= 2;
        }
        if (i >= 16) {
            systemUiVisibility ^= 4;
        }
        if (i >= 19) {
            systemUiVisibility ^= 4096;
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    /* renamed from: c */
    public static String m13174c(byte[] bArr) {
        try {
            String str = "";
            for (byte b : MessageDigest.getInstance("MD5").digest(bArr)) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str = str + hexString;
            }
            return str.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: c */
    public static List<C5395c2.C5397b> m13175c(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("files");
            for (int i = 0; i < jSONArray.length(); i++) {
                C5395c2.C5397b c5397b = new C5395c2.C5397b();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                c5397b.f12419a = jSONObject2.optString("type");
                c5397b.f12420b = jSONObject2.optString("path");
                if (jSONObject2.has("content")) {
                    ArrayList arrayList2 = new ArrayList();
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("content");
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        arrayList2.add(jSONArray2.optString(i2));
                    }
                    c5397b.f12421c = arrayList2;
                }
                arrayList.add(c5397b);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: d */
    public static List<C5395c2.C5398c> m13173d(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("property");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                C5395c2.C5398c c5398c = new C5395c2.C5398c();
                c5398c.f12422a = jSONObject2.optString("name");
                JSONArray jSONArray2 = jSONObject2.getJSONArray("detail");
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    arrayList2.add(jSONArray2.optString(i2));
                }
                c5398c.f12423b = arrayList2;
                arrayList.add(c5398c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: b */
    public static List<C5395c2.C5396a> m13178b(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("exec");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                C5395c2.C5396a c5396a = new C5395c2.C5396a();
                c5396a.f12417a = jSONObject2.optString("name");
                JSONArray jSONArray2 = jSONObject2.getJSONArray("detail");
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    arrayList2.add(jSONArray2.optString(i2));
                }
                c5396a.f12418b = arrayList2;
                arrayList.add(c5396a);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: b */
    public static JSONArray m13179b(List<C5395c2.C5397b> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            try {
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        C5395c2.C5397b c5397b = list.get(i);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("type", c5397b.f12419a);
                        jSONObject.put("path", c5397b.f12420b);
                        List<String> list2 = c5397b.f12421c;
                        if (list2 != null && list2.size() > 0) {
                            List<String> list3 = c5397b.f12421c;
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i2 = 0; i2 < list3.size(); i2++) {
                                jSONArray2.put(i2, list3.get(i2));
                            }
                            jSONObject.put("content", jSONArray2);
                        }
                        jSONArray.put(i, jSONObject);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }

    /* renamed from: c */
    public static JSONArray m13176c(List<C5395c2.C5398c> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            try {
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        C5395c2.C5398c c5398c = list.get(i);
                        List<String> list2 = c5398c.f12423b;
                        if (list2 != null && list2.size() > 0) {
                            JSONObject jSONObject = new JSONObject();
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i2 = 0; i2 < list2.size(); i2++) {
                                jSONArray2.put(list2.get(i2));
                            }
                            jSONObject.put("name", c5398c.f12422a);
                            jSONObject.put("detail", jSONArray2);
                            jSONArray.put(i, jSONObject);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }

    /* JADX WARN: Removed duplicated region for block: B:148:0x00c7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0193  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.megvii.lv5.C5395c2 m13192a(android.content.Context r16, com.megvii.lv5.C5395c2 r17) {
        /*
            Method dump skipped, instructions count: 595
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5538q0.m13192a(android.content.Context, com.megvii.lv5.c2):com.megvii.lv5.c2");
    }

    /* renamed from: a */
    public static JSONObject m13190a(C5395c2 c5395c2) {
        JSONObject jSONObject = new JSONObject();
        if (c5395c2 != null) {
            try {
                jSONObject.put("files", m13179b(c5395c2.f12413a));
                List<String> list = c5395c2.f12414b;
                JSONArray jSONArray = new JSONArray();
                if (list != null) {
                    try {
                        if (list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                jSONArray.put(i, list.get(i));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                jSONObject.put("appList", jSONArray);
                jSONObject.put("property", m13176c(c5395c2.f12415c));
                jSONObject.put("exec", m13189a(c5395c2.f12416d));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static JSONArray m13189a(List<C5395c2.C5396a> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            try {
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        C5395c2.C5396a c5396a = list.get(i);
                        List<String> list2 = c5396a.f12418b;
                        if (list2 != null && list2.size() > 0) {
                            JSONObject jSONObject = new JSONObject();
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i2 = 0; i2 < list2.size(); i2++) {
                                jSONArray2.put(list2.get(i2));
                            }
                            jSONObject.put("name", c5396a.f12417a);
                            jSONObject.put("detail", jSONArray2);
                            jSONArray.put(i, jSONObject);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }
}
