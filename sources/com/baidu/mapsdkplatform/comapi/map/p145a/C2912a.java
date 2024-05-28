package com.baidu.mapsdkplatform.comapi.map.p145a;

import com.baidu.platform.comapi.map.AbstractC3068j;
import com.baidu.platform.comapi.map.InnerOverlay;
import com.baidu.platform.comapi.util.JsonBuilder;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2912a extends InnerOverlay {

    /* renamed from: a */
    public final List<AbstractC3068j> f7209a;

    /* renamed from: d */
    private boolean f7210d;

    /* renamed from: e */
    private int f7211e;

    /* renamed from: f */
    private int f7212f;

    /* renamed from: g */
    private int f7213g;

    /* renamed from: h */
    private boolean f7214h;

    public C2912a() {
        super(36);
        this.f7210d = false;
        this.f7211e = 0;
        this.f7212f = 0;
        this.f7213g = 0;
        this.f7209a = new ArrayList();
        this.f7214h = true;
    }

    /* renamed from: a */
    public void m18420a() {
        this.f7214h = true;
        UpdateOverlay();
    }

    /* renamed from: a */
    public void m18418a(boolean z, int i, int i2, int i3) {
        this.f7210d = z;
        this.f7211e = i;
        this.f7212f = i2;
        this.f7213g = i3;
    }

    /* renamed from: a */
    public boolean m18419a(AbstractC3068j abstractC3068j) {
        synchronized (this.f7209a) {
            if (this.f7209a.contains(abstractC3068j)) {
                return false;
            }
            this.f7214h = this.f7209a.add(abstractC3068j);
            return this.f7214h;
        }
    }

    @Override // com.baidu.platform.comapi.map.InnerOverlay
    public void clear() {
        synchronized (this.f7209a) {
            this.f7209a.clear();
        }
        super.clear();
    }

    @Override // com.baidu.platform.comapi.map.InnerOverlay
    public String getData() {
        if (this.f7214h) {
            synchronized (this.f7209a) {
                if (this.f7209a.size() == 0) {
                    return "";
                }
                JsonBuilder jsonBuilder = new JsonBuilder();
                jsonBuilder.object();
                jsonBuilder.key("dataset").arrayValue();
                for (AbstractC3068j abstractC3068j : this.f7209a) {
                    jsonBuilder.objectValue(abstractC3068j.mo17737a());
                }
                jsonBuilder.endArrayValue();
                jsonBuilder.key("startValue").value(0);
                jsonBuilder.key("endValue").value(1);
                if (this.f7210d) {
                    jsonBuilder.key("isNeedRouteAnimate").value(1);
                    jsonBuilder.key("durationTime").value(this.f7211e);
                    jsonBuilder.key("delayTime").value(this.f7212f);
                    jsonBuilder.key("easingCurve").value(this.f7213g);
                    this.f7210d = false;
                } else {
                    jsonBuilder.key("isNeedRouteAnimate").value(0);
                    jsonBuilder.key("durationTime").value(0);
                    jsonBuilder.key("delayTime").value(0);
                    jsonBuilder.key("easingCurve").value(0);
                }
                jsonBuilder.endObject();
                setData(jsonBuilder.getJson());
                this.f7214h = false;
            }
        }
        return super.getData();
    }

    @Override // com.baidu.platform.comapi.map.InnerOverlay
    public void setData(String str) {
        super.setData(str);
        this.f7214h = true;
    }
}
