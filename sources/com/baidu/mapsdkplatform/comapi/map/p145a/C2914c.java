package com.baidu.mapsdkplatform.comapi.map.p145a;

import android.os.Looper;
import android.os.Message;
import com.baidu.mapapi.map.track.TraceAnimationListener;
import com.baidu.mapapi.map.track.TraceOptions;
import com.baidu.mapapi.map.track.TraceOverlay;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapsdkplatform.comapi.map.C2925d;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.C3004ag;
import com.baidu.platform.comapi.map.C3014ap;
import com.baidu.platform.comapi.map.C3016ar;
import com.baidu.platform.comapi.map.MapSurfaceView;
import com.baidu.platform.comapi.map.MapTextureView;
import com.baidu.platform.comapi.util.AbstractHandlerC3096h;
import com.baidu.platform.comapi.util.C3097i;
import com.baidu.platform.comjni.engine.MessageProxy;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2914c {

    /* renamed from: a */
    private C2912a f7215a;

    /* renamed from: b */
    private C2925d f7216b;

    /* renamed from: c */
    private int f7217c;

    /* renamed from: d */
    private TraceAnimationListener f7218d;

    /* renamed from: f */
    private InterfaceC2913b f7220f;

    /* renamed from: g */
    private MapSurfaceView f7221g;

    /* renamed from: h */
    private MapTextureView f7222h;

    /* renamed from: e */
    private HandlerC2915a f7219e = new HandlerC2915a();

    /* renamed from: i */
    private volatile boolean f7223i = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.map.a.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class HandlerC2915a extends AbstractHandlerC3096h {
        HandlerC2915a() {
            super(Looper.getMainLooper());
        }

        @Override // com.baidu.platform.comapi.util.AbstractHandlerC3096h
        /* renamed from: a */
        public void mo17681a(Message message) {
            if (message.what != 65302) {
                if (message.what != 65303 || C2914c.this.f7218d == null) {
                    return;
                }
                C2914c.this.f7218d.onTraceUpdatePosition(CoordUtil.mc2ll(new GeoPoint(message.arg2 / 100.0f, message.arg1 / 100.0f)));
                return;
            }
            if (message.arg1 > 0 && message.arg1 <= 1000 && C2914c.this.f7218d != null) {
                C2914c.this.f7218d.onTraceAnimationUpdate(message.arg1 / 10);
            }
            if (message.arg2 != 1 || C2914c.this.f7218d == null) {
                return;
            }
            C2914c.this.f7218d.onTraceAnimationFinish();
        }
    }

    public C2914c(MapSurfaceView mapSurfaceView) {
        this.f7217c = 1;
        if (mapSurfaceView == null) {
            return;
        }
        this.f7215a = new C2912a();
        this.f7221g = mapSurfaceView;
        this.f7216b = mapSurfaceView.getBaseMap();
        mapSurfaceView.addOverlay(this.f7215a);
        this.f7215a.SetOverlayShow(true);
        this.f7217c = 1;
    }

    public C2914c(MapTextureView mapTextureView) {
        this.f7217c = 1;
        if (mapTextureView == null) {
            return;
        }
        this.f7215a = new C2912a();
        this.f7222h = mapTextureView;
        this.f7216b = mapTextureView.getBaseMap();
        mapTextureView.addOverlay(this.f7215a);
        this.f7215a.SetOverlayShow(true);
        this.f7217c = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m18414a(TraceOverlay traceOverlay) {
        if (traceOverlay != null && this.f7215a != null) {
            m18407c();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m18410b(TraceOverlay traceOverlay) {
        C2912a c2912a;
        if (traceOverlay == null || (c2912a = this.f7215a) == null) {
            return;
        }
        c2912a.clear();
        C3097i.m17678b().execute(new RunnableC2918f(this, traceOverlay));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m18406c(TraceOverlay traceOverlay) {
        if (this.f7215a == null || traceOverlay == null) {
            return;
        }
        this.f7215a.m18418a(traceOverlay.isAnimate(), traceOverlay.getAnimationTime(), traceOverlay.getAnimationDuration(), traceOverlay.getAnimationType());
        C3004ag c3004ag = new C3004ag(new C3014ap().m17943a(-15794282).m17941b(14));
        c3004ag.m17979a(m18402d(traceOverlay));
        c3004ag.m17980a(new C3016ar().m17937d(1032).m17943a(traceOverlay.getColor()).m17941b(traceOverlay.getWidth()));
        c3004ag.f7972c = traceOverlay.isTrackMove();
        this.f7215a.m18419a(c3004ag);
    }

    /* renamed from: d */
    private List<GeoPoint> m18402d(TraceOverlay traceOverlay) {
        if (traceOverlay == null || traceOverlay.getPoints() == null) {
            return null;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng : traceOverlay.getPoints()) {
            arrayList.add(CoordUtil.ll2mc(latLng));
            builder.include(latLng);
        }
        return arrayList;
    }

    /* renamed from: a */
    public TraceOverlay m18415a(TraceOptions traceOptions) {
        if (traceOptions == null) {
            return null;
        }
        TraceOverlay overlay = traceOptions.getOverlay();
        overlay.mListener = this.f7220f;
        C3097i.m17678b().execute(new RunnableC2917e(this, overlay));
        return overlay;
    }

    /* renamed from: a */
    public void m18417a() {
        this.f7220f = new C2916d(this);
        MessageProxy.registerMessageHandler(65302, this.f7219e);
        MessageProxy.registerMessageHandler(65303, this.f7219e);
    }

    /* renamed from: a */
    public void m18416a(TraceAnimationListener traceAnimationListener) {
        this.f7218d = traceAnimationListener;
    }

    /* renamed from: b */
    public void m18411b() {
        C2912a c2912a = this.f7215a;
        if (c2912a == null) {
            return;
        }
        c2912a.clear();
        this.f7215a.m18420a();
    }

    /* renamed from: c */
    public void m18407c() {
        MapTextureView mapTextureView;
        MapSurfaceView mapSurfaceView;
        MessageProxy.unRegisterMessageHandler(65302, this.f7219e);
        MessageProxy.unRegisterMessageHandler(65303, this.f7219e);
        if (this.f7217c == 1 && (mapSurfaceView = this.f7221g) != null) {
            mapSurfaceView.removeOverlay(this.f7215a);
        } else if (this.f7217c == 2 && (mapTextureView = this.f7222h) != null) {
            mapTextureView.removeOverlay(this.f7215a);
        }
        if (this.f7218d != null) {
            this.f7218d = null;
        }
        this.f7223i = true;
    }

    /* renamed from: d */
    public boolean m18403d() {
        return this.f7223i;
    }
}
