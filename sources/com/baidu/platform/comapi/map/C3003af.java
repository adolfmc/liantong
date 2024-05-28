package com.baidu.platform.comapi.map;

import android.os.Bundle;
import com.baidu.platform.comjni.map.basemap.AppBaseMap;
import com.baidu.platform.comjni.map.basemap.InterfaceC3098a;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.af */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C3003af implements InterfaceC3098a {

    /* renamed from: a */
    static final String f7732a = "com.baidu.platform.comapi.map.af";

    /* renamed from: b */
    Map<Long, InnerOverlay> f7733b = new ConcurrentHashMap();

    /* renamed from: c */
    AppBaseMap f7734c;

    public C3003af(AppBaseMap appBaseMap) {
        this.f7734c = null;
        this.f7734c = appBaseMap;
    }

    @Override // com.baidu.platform.comjni.map.basemap.InterfaceC3098a
    /* renamed from: a */
    public int mo17646a(Bundle bundle, long j, int i) {
        long currentTimeMillis = C3084z.f8021a ? System.currentTimeMillis() : 0L;
        InnerOverlay innerOverlay = this.f7733b.get(Long.valueOf(j));
        if (innerOverlay != null) {
            String data = innerOverlay.getData();
            if (this.f7734c.LayersIsShow(j)) {
                bundle.putString("jsondata", data);
                Bundle param = innerOverlay.getParam();
                if (param != null) {
                    bundle.putBundle("param", param);
                }
            } else {
                bundle.putString("jsondata", null);
            }
            if (C3084z.f8021a) {
                String str = f7732a;
                C3084z.m17719a(str, "MapLayerDataReq:" + j + " tag:" + innerOverlay.getLayerTag() + " [" + (System.currentTimeMillis() - currentTimeMillis) + "ms] LayerData:" + data);
            }
            return innerOverlay.getType();
        }
        return 0;
    }

    /* renamed from: a */
    public void m17983a() {
        if (this.f7734c != null) {
            for (Long l : this.f7733b.keySet()) {
                if (l.longValue() > 0) {
                    this.f7734c.ClearLayer(l.longValue());
                    this.f7734c.RemoveLayer(l.longValue());
                }
            }
        }
        this.f7733b.clear();
    }

    /* renamed from: a */
    public void m17982a(InnerOverlay innerOverlay) {
        this.f7733b.put(Long.valueOf(innerOverlay.mLayerID), innerOverlay);
        innerOverlay.SetMapParam(innerOverlay.mLayerID, this.f7734c);
    }

    /* renamed from: a */
    public void m17981a(Overlay overlay) {
        this.f7733b.remove(Long.valueOf(overlay.mLayerID));
    }

    @Override // com.baidu.platform.comjni.map.basemap.InterfaceC3098a
    /* renamed from: a */
    public boolean mo17647a(long j) {
        return this.f7733b.containsKey(Long.valueOf(j));
    }
}
