package com.bytedance.sdk.openadsdk.p186mb;

import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import com.bykv.p167vk.openvk.api.proto.Bridge;
import com.bykv.p167vk.openvk.api.proto.EventListener;
import com.bykv.p167vk.openvk.api.proto.ValueSet;
import com.bytedance.pangle.Zeus;
import com.bytedance.sdk.openadsdk.TTAdBridge;
import com.bytedance.sdk.openadsdk.TTAdEvent;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.api.plugin.C4021u;
import com.bytedance.sdk.openadsdk.p186mb.C4069mb;
import java.util.Map;

/* renamed from: com.bytedance.sdk.openadsdk.mb.ox */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4071ox implements Bridge, TTAdBridge {

    /* renamed from: mb */
    private static volatile C4071ox f9689mb;

    /* renamed from: ox */
    private C4069mb f9690ox = new C4069mb();

    @Override // com.bytedance.sdk.openadsdk.TTAdBridge
    public String call(int i, Bundle bundle) {
        return null;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdBridge
    public <T> T getObj(Class<T> cls) {
        return null;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdBridge
    public <T> T getObj(Class<T> cls, int i, Map<String, Object> map) {
        return null;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdBridge
    public void init(Bundle bundle) {
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdBridge
    public void removeObj(Object obj) {
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdBridge
    public void setObj(Object obj) {
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdBridge
    public void unsubscribe(TTAdEvent tTAdEvent) {
    }

    @Override // com.bykv.p167vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return null;
    }

    private C4071ox() {
    }

    /* renamed from: mb */
    public static final C4071ox m16352mb() {
        if (f9689mb == null) {
            synchronized (C4071ox.class) {
                if (f9689mb == null) {
                    f9689mb = new C4071ox();
                }
            }
        }
        return f9689mb;
    }

    /* renamed from: ox */
    public Application.ActivityLifecycleCallbacks m16350ox() {
        return this.f9690ox;
    }

    /* JADX WARN: Type inference failed for: r1v17, types: [java.lang.CharSequence, T, java.lang.String] */
    @Override // com.bytedance.sdk.openadsdk.TTAdBridge
    public <T> T callMethod(Class<T> cls, int i, Map<String, Object> map) {
        switch (i) {
            case 2:
                return (T) this.f9690ox.m16354mb();
            case 3:
                return (T) TTAppContextHolder.getContext();
            case 4:
                return (T) Zeus.getPlugin("com.byted.csj.ext").mClassLoader;
            case 5:
                return (T) Boolean.valueOf(Zeus.loadPlugin("com.byted.csj.ext"));
            case 6:
                return (T) Boolean.valueOf(Zeus.isPluginInstalled("com.byted.csj.ext"));
            case 7:
                return (T) Boolean.valueOf(Zeus.isPluginLoaded("com.byted.csj.ext"));
            case 8:
                ?? r1 = (T) C4021u.m16438mb("com.byted.csj.ext");
                return TextUtils.isEmpty(r1) ? "0.0.0.0" : r1;
            default:
                return null;
        }
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdBridge
    public void subscribe(final TTAdEvent tTAdEvent) {
        this.f9690ox.m16353mb(new C4069mb.InterfaceC4070mb() { // from class: com.bytedance.sdk.openadsdk.mb.ox.1
            @Override // com.bytedance.sdk.openadsdk.p186mb.C4069mb.InterfaceC4070mb
            /* renamed from: mb */
            public void mo16349mb() {
                tTAdEvent.onEvent(0, null);
            }

            @Override // com.bytedance.sdk.openadsdk.p186mb.C4069mb.InterfaceC4070mb
            /* renamed from: ox */
            public void mo16348ox() {
                tTAdEvent.onEvent(1, null);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v18, types: [java.lang.CharSequence, T, java.lang.String] */
    @Override // com.bykv.p167vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        switch (i) {
            case 2:
                return (T) this.f9690ox.m16354mb();
            case 3:
                return (T) TTAppContextHolder.getContext();
            case 4:
                return (T) Zeus.getPlugin(valueSet.stringValue(0)).mClassLoader;
            case 5:
                return (T) Boolean.valueOf(Zeus.loadPlugin(valueSet.stringValue(0)));
            case 6:
                return (T) Boolean.valueOf(Zeus.isPluginInstalled(valueSet.stringValue(0)));
            case 7:
                return (T) Boolean.valueOf(Zeus.isPluginLoaded(valueSet.stringValue(0)));
            case 8:
                ?? r1 = (T) C4021u.m16438mb(valueSet.stringValue(0));
                return TextUtils.isEmpty(r1) ? "0.0.0.0" : r1;
            case 9:
                Object objectValue = valueSet.objectValue(0, Object.class);
                if (objectValue instanceof TTAdEvent) {
                    subscribe((TTAdEvent) objectValue);
                    return null;
                } else if (objectValue instanceof EventListener) {
                    m16351mb((EventListener) objectValue);
                    return null;
                } else {
                    return null;
                }
            default:
                return null;
        }
    }

    /* renamed from: mb */
    private void m16351mb(final EventListener eventListener) {
        this.f9690ox.m16353mb(new C4069mb.InterfaceC4070mb() { // from class: com.bytedance.sdk.openadsdk.mb.ox.2
            @Override // com.bytedance.sdk.openadsdk.p186mb.C4069mb.InterfaceC4070mb
            /* renamed from: mb */
            public void mo16349mb() {
                eventListener.onEvent(0, null);
            }

            @Override // com.bytedance.sdk.openadsdk.p186mb.C4069mb.InterfaceC4070mb
            /* renamed from: ox */
            public void mo16348ox() {
                eventListener.onEvent(1, null);
            }
        });
    }
}
