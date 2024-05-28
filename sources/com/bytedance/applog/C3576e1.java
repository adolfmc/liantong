package com.bytedance.applog;

import android.webkit.WebView;
import com.bytedance.applog.C3565d1;
import com.example.asus.detectionandalign.DetectionAuthentic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.e1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3576e1 {

    /* renamed from: e */
    public static int f8429e;

    /* renamed from: f */
    public static Map<String, Double> f8430f = new HashMap();

    /* renamed from: a */
    public double f8431a;

    /* renamed from: b */
    public WebView f8432b;

    /* renamed from: c */
    public int[] f8433c = new int[2];

    /* renamed from: d */
    public boolean f8434d;

    public C3576e1(WebView webView, boolean z) {
        this.f8432b = webView;
        this.f8434d = z;
    }

    /* renamed from: a */
    public final C3565d1.C3567b m17310a(JSONObject jSONObject) {
        String optString = jSONObject.optString("nodeName");
        JSONObject optJSONObject = jSONObject.optJSONObject(DetectionAuthentic.FRAME);
        C3565d1.C3566a c3566a = new C3565d1.C3566a((int) ((optJSONObject.optInt("x") * this.f8431a) + (this.f8434d ? 0 : this.f8433c[0])), (int) ((optJSONObject.optInt("y") * this.f8431a) + (this.f8434d ? 0 : this.f8433c[1])), (int) (optJSONObject.optInt("width") * this.f8431a), (int) (optJSONObject.optInt("height") * this.f8431a));
        String optString2 = jSONObject.optString("_element_path");
        String optString3 = jSONObject.optString("element_path");
        JSONArray optJSONArray = jSONObject.optJSONArray("positions");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.optString(i));
        }
        int optInt = jSONObject.optInt("zIndex");
        JSONArray optJSONArray2 = jSONObject.optJSONArray("texts");
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
            arrayList2.add(optJSONArray2.optString(i2));
        }
        String optString4 = jSONObject.optString("href");
        boolean optBoolean = jSONObject.optBoolean("_checkList");
        JSONArray optJSONArray3 = jSONObject.optJSONArray("fuzzy_positions");
        ArrayList arrayList3 = new ArrayList();
        if (optJSONArray3 != null) {
            for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                arrayList3.add(optJSONArray3.optString(i3));
            }
        }
        JSONArray optJSONArray4 = jSONObject.optJSONArray("children");
        ArrayList arrayList4 = new ArrayList();
        if (optJSONArray4 != null && optJSONArray4.length() > 0) {
            for (int i4 = 0; i4 < optJSONArray4.length(); i4++) {
                arrayList4.add(m17310a(optJSONArray4.optJSONObject(i4)));
            }
        }
        return new C3565d1.C3567b(optString, c3566a, optString2, optString3, arrayList, optInt, arrayList2, arrayList4, optString4, optBoolean, arrayList3);
    }
}
