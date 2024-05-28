package com.megvii.lv5;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.m */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC5500m {

    /* renamed from: a */
    public int f12867a = C5486k.f12831a;

    /* renamed from: b */
    public int f12868b = C5486k.f12832b;

    /* renamed from: c */
    public int f12869c = C5486k.f12835e;

    /* renamed from: d */
    public int f12870d = C5486k.f12833c;

    /* renamed from: e */
    public int f12871e = C5486k.f12834d;

    /* renamed from: f */
    public Map<Integer, String> f12872f = new HashMap();

    /* renamed from: g */
    public boolean f12873g = false;

    /* renamed from: h */
    public Map<Integer, Integer> f12874h = new HashMap();

    /* renamed from: i */
    public boolean f12875i = false;

    /* renamed from: j */
    public int f12876j = -1000;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.m$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5501a {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.m$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5502b {
        /* renamed from: a */
        void mo12939a();

        /* renamed from: a */
        void mo12930a(ArrayList<Camera.Size> arrayList);

        /* renamed from: b */
        void mo12927b();

        /* renamed from: c */
        void mo12923c();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.m$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5503c {
        void onPreviewFrame(byte[] bArr, Camera camera);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.m$d */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5504d {
        /* renamed from: a */
        void mo12929a(byte[] bArr);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.m$e */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5505e {
        /* renamed from: a */
        void mo13438a(double d);
    }

    /* renamed from: a */
    public abstract int mo13274a(Context context);

    /* renamed from: a */
    public abstract void mo13277a();

    /* renamed from: a */
    public abstract void mo13276a(int i);

    /* renamed from: a */
    public abstract void mo13273a(SurfaceTexture surfaceTexture);

    /* renamed from: a */
    public void mo13439a(InterfaceC5501a interfaceC5501a) {
        mo13439a(interfaceC5501a);
    }

    /* renamed from: a */
    public abstract void mo13271a(InterfaceC5503c interfaceC5503c);

    /* renamed from: a */
    public abstract void mo13270a(InterfaceC5504d interfaceC5504d);

    /* renamed from: a */
    public abstract void mo13269a(InterfaceC5505e interfaceC5505e);

    /* renamed from: a */
    public abstract void mo13267a(String str);

    /* renamed from: a */
    public void mo13266a(boolean z, Context context, InterfaceC5502b interfaceC5502b) {
        HashMap hashMap = new HashMap();
        String str = (String) C5527o2.m13252a(context, "white_balance_info", "");
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArray = jSONObject.getJSONArray("frame_sequence");
                JSONArray jSONArray2 = jSONObject.getJSONArray("model_sequence");
                if (jSONArray.length() > 0 && jSONArray2.length() > 0 && jSONArray.length() == jSONArray2.length()) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        hashMap.put(Integer.valueOf(jSONArray.optInt(i)), jSONArray2.optString(i));
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.f12872f = hashMap;
        this.f12873g = C5527o2.m13221j(context);
        this.f12875i = C5527o2.m13226e(context);
        HashMap hashMap2 = new HashMap();
        String str2 = (String) C5527o2.m13252a(context, "exposure_info", "");
        if (!TextUtils.isEmpty(str2)) {
            try {
                JSONObject jSONObject2 = new JSONObject(str2);
                JSONArray jSONArray3 = jSONObject2.getJSONArray("frame_sequence");
                int[] iArr = jSONObject2.optInt("trend", 0) == 1 ? new int[]{-100, 100} : new int[]{100, -100};
                if (jSONArray3.length() > 0 && jSONArray3.length() == 2) {
                    for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                        hashMap2.put(Integer.valueOf(jSONArray3.optInt(i2)), Integer.valueOf(iArr[i2]));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.f12874h = hashMap2;
    }

    /* renamed from: b */
    public abstract void mo13264b(int i);

    /* renamed from: b */
    public abstract int[] mo13265b();

    /* renamed from: c */
    public abstract void mo13262c();

    /* renamed from: c */
    public abstract void mo13261c(int i);
}
