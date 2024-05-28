package com.baidu.mapapi.map;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MapPoi {

    /* renamed from: d */
    private static final String f6140d = "MapPoi";

    /* renamed from: a */
    String f6141a;

    /* renamed from: b */
    LatLng f6142b;

    /* renamed from: c */
    String f6143c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m18932a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.f6141a = jSONObject.optString("tx");
        String str = this.f6141a;
        if (str != null && !str.equals("")) {
            this.f6141a = this.f6141a.replaceAll("\\\\", "").replaceAll("/?[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
        }
        this.f6142b = CoordUtil.decodeNodeLocation(jSONObject.optString("geo"));
        this.f6143c = jSONObject.optString("ud");
    }

    public String getName() {
        return this.f6141a;
    }

    public LatLng getPosition() {
        return this.f6142b;
    }

    public String getUid() {
        return this.f6143c;
    }
}
