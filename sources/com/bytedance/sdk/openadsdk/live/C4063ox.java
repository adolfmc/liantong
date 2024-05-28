package com.bytedance.sdk.openadsdk.live;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import com.bykv.p167vk.openvk.api.proto.Bridge;
import com.bykv.p167vk.openvk.api.proto.EventListener;
import com.bykv.p167vk.openvk.api.proto.ValueSet;
import com.bytedance.android.live.base.api.ILiveHostContextParam;
import com.bytedance.android.live.base.api.ILiveInitCallback;
import com.bytedance.android.live.base.api.IOuterLiveRoomService;
import com.bytedance.android.live.base.api.MethodChannelService;
import com.bytedance.android.live.base.api.callback.Callback;
import com.bytedance.android.openliveplugin.LivePluginHelper;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdEvent;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTPluginListener;
import com.bytedance.sdk.openadsdk.api.C3969b;
import com.bytedance.sdk.openadsdk.api.C3972mb;
import com.bytedance.sdk.openadsdk.api.C3973ox;
import com.bytedance.sdk.openadsdk.api.plugin.C4021u;
import com.bytedance.sdk.openadsdk.downloadnew.AbstractC4052mb;
import com.bytedance.sdk.openadsdk.live.core.C4056mb;
import com.bytedance.sdk.openadsdk.live.core.C4057ox;
import com.bytedance.sdk.openadsdk.live.core.ITTLiveConfig;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.sdk.openadsdk.live.ox */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4063ox extends AbstractC4052mb implements Bridge, Serializable {

    /* renamed from: lz */
    private ITTLiveTokenInjectionAuth f9677lz;

    /* renamed from: ox */
    private static final C4063ox f9674ox = new C4063ox();

    /* renamed from: b */
    private static final AtomicBoolean f9670b = new AtomicBoolean(false);

    /* renamed from: hj */
    private static final AtomicBoolean f9672hj = new AtomicBoolean(false);

    /* renamed from: h */
    private static final AtomicBoolean f9671h = new AtomicBoolean(false);

    /* renamed from: u */
    private static final AtomicBoolean f9675u = new AtomicBoolean(false);

    /* renamed from: mb */
    public static C4068mb f9673mb = null;

    /* renamed from: ko */
    private ITTLiveConfig f9676ko = null;

    /* renamed from: ww */
    private JSONObject f9678ww = null;

    @Override // com.bykv.p167vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return null;
    }

    private C4063ox() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.sdk.openadsdk.live.ox$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class C4068mb implements TTAdEvent {

        /* renamed from: mb */
        private TTAdEvent f9684mb;

        /* renamed from: ox */
        private EventListener f9685ox;

        private C4068mb(TTAdEvent tTAdEvent) {
            this.f9684mb = tTAdEvent;
        }

        private C4068mb(EventListener eventListener) {
            this.f9685ox = eventListener;
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdEvent
        public void onEvent(int i, Bundle bundle) {
            TTAdEvent tTAdEvent = this.f9684mb;
            if (tTAdEvent != null) {
                tTAdEvent.onEvent(i, bundle);
            }
            if (this.f9685ox != null) {
                this.f9685ox.onEvent(i, C3973ox.m16542mb().m16541mb(0).m16539mb(true).m16540mb(C3969b.m16559mb().m16558mb(0, bundle).m16555ox()).m16538ox());
            }
        }
    }

    /* renamed from: mb */
    public static C4063ox m16367mb() {
        return f9674ox;
    }

    /* renamed from: mb */
    public void m16365mb(ITTLiveTokenInjectionAuth iTTLiveTokenInjectionAuth) {
        this.f9677lz = iTTLiveTokenInjectionAuth;
    }

    /* renamed from: mb */
    public void m16366mb(C4021u c4021u, Bundle bundle) {
        if (f9670b.get()) {
            C3972mb.m16544ox("TTLiveSDkBridge", "live PL is loading...just wait");
        } else if (f9672hj.get()) {
            C3972mb.m16544ox("TTLiveSDkBridge", "live PL already loaded, dont load again");
        } else {
            TTPluginListener tTPluginListener = new TTPluginListener() { // from class: com.bytedance.sdk.openadsdk.live.ox.1
                @Override // com.bytedance.sdk.openadsdk.TTPluginListener
                public Bundle config() {
                    return null;
                }

                @Override // com.bytedance.sdk.openadsdk.TTPluginListener
                public String packageName() {
                    return null;
                }

                @Override // com.bytedance.sdk.openadsdk.TTPluginListener
                public void onPluginListener(int i, ClassLoader classLoader, Resources resources, Bundle bundle2) {
                    if (1000 == i) {
                        C3972mb.m16544ox("TTLiveSDkBridge", "live PL install success ， try to init live sdk");
                        C4063ox.f9672hj.set(true);
                        C4063ox.f9670b.set(false);
                        C4063ox.this.m16370h();
                    } else if (1001 == i) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("live PL install failed, errorCode: ");
                        sb.append(bundle2 == null ? null : bundle2.get("code"));
                        C3972mb.m16544ox("TTLiveSDkBridge", sb.toString());
                        C4063ox.f9672hj.set(false);
                        C4063ox.f9670b.set(false);
                    }
                    C4063ox.this.m16356ww();
                }
            };
            f9670b.set(true);
            f9672hj.set(false);
            C4059mb.m16374mb(c4021u, bundle, tTPluginListener);
        }
    }

    @Override // com.bytedance.sdk.openadsdk.downloadnew.AbstractC4052mb, com.bytedance.sdk.openadsdk.TTAdBridge
    public <T> T getObj(Class<T> cls, int i, Map<String, Object> map) {
        return (T) super.getObj(cls, i, map);
    }

    @Override // com.bytedance.sdk.openadsdk.downloadnew.AbstractC4052mb, com.bytedance.sdk.openadsdk.TTAdBridge
    public void subscribe(TTAdEvent tTAdEvent) {
        f9673mb = new C4068mb(tTAdEvent);
        m16356ww();
    }

    @Override // com.bytedance.sdk.openadsdk.downloadnew.AbstractC4052mb, com.bytedance.sdk.openadsdk.TTAdBridge
    public void init(Bundle bundle) {
        super.init(bundle);
        ITTLiveConfig iTTLiveConfig = this.f9676ko;
        if (iTTLiveConfig != null && iTTLiveConfig.isValid() && !TextUtils.isEmpty(this.f9676ko.getGeneralAppId()) && !TextUtils.isEmpty(this.f9676ko.getPartner()) && !TextUtils.isEmpty(this.f9676ko.getPartnerSecret())) {
            C3972mb.m16544ox("TTLiveSDkBridge", "The configuration has been obtained. Do not repeat initialization");
            return;
        }
        Serializable serializable = bundle.getSerializable("liveInitConfig");
        if (serializable instanceof ITTLiveConfig) {
            this.f9676ko = (ITTLiveConfig) serializable;
        }
        try {
            this.f9678ww = new JSONObject(bundle.getString("liveInitExtra"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m16370h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m16370h() {
        String str;
        ITTLiveConfig iTTLiveConfig;
        Plugin plugin;
        C3972mb.m16554b("TTLiveSDkBridge", "hasLiveSDKInited：" + f9675u.get() + ", hasLiveInstalled：" + f9672hj.get());
        if (this.f9676ko != null) {
            str = "GeneralAppId：" + this.f9676ko.getGeneralAppId() + "，isValid：" + this.f9676ko.isValid();
        } else {
            str = null;
        }
        C3972mb.m16554b("TTLiveSDkBridge", str);
        if (f9675u.get() || !f9672hj.get() || (iTTLiveConfig = this.f9676ko) == null || !iTTLiveConfig.isValid() || (plugin = Zeus.getPlugin("com.byted.live.lite")) == null || f9671h.get()) {
            return;
        }
        f9671h.set(true);
        if (!C4059mb.m16381b(plugin.getVersion())) {
            C3972mb.m16544ox("TTLiveSDkBridge", "live sdk init crash more than consecutive 5 times , live plugin had uninstalled ! App cold start will request new live plugin ！");
            C4059mb.m16380hj(plugin.getVersion());
            f9671h.set(false);
            return;
        }
        ILiveHostContextParam.Builder hostActionParam = new ILiveHostContextParam.Builder().setAppName(this.f9676ko.getAppName()).setChannel(this.f9676ko.getChannel()).setIsDebug(this.f9676ko.isDebug()).setECHostAppId(this.f9676ko.getECHostAppId()).setPartner(this.f9676ko.getPartner()).provideMethodChannel(new MethodChannelService() { // from class: com.bytedance.sdk.openadsdk.live.ox.2
            @Override // com.bytedance.android.live.base.api.MethodChannelService
            public String identity() {
                return "pangle";
            }

            @Override // com.bytedance.android.live.base.api.MethodChannelService
            public Object invokeMethod(String str2, Object... objArr) {
                if (TextUtils.isEmpty(str2)) {
                    return null;
                }
                char c = 65535;
                if (str2.hashCode() == -955478604 && str2.equals("getBiddingToken")) {
                    c = 0;
                }
                if (c != 0) {
                    return null;
                }
                return C4063ox.this.m16360mb(objArr);
            }
        }).setPartnerSecret(this.f9676ko.getPartnerSecret()).setHostPermission(this.f9676ko.getHostPermission()).setHostActionParam(new C4056mb(this.f9676ko.getLiveHostAction()));
        ITTLiveTokenInjectionAuth iTTLiveTokenInjectionAuth = this.f9677lz;
        if (iTTLiveTokenInjectionAuth != null) {
            hostActionParam.setInjectionAuth(new C4057ox(iTTLiveTokenInjectionAuth));
        }
        ILiveInitCallback iLiveInitCallback = new ILiveInitCallback() { // from class: com.bytedance.sdk.openadsdk.live.ox.3
            @Override // com.bytedance.android.live.base.api.ILiveInitCallback
            public final void onLiveInitFinish() {
                C3972mb.m16544ox("TTLiveSDkBridge", "onLiveInitFinish - live sdk init succeed！");
                C3972mb.m16544ox("TTLiveSDkBridge", "execute commerce initLiveCommerce method start");
                boolean m16379mb = C4059mb.m16379mb();
                C3972mb.m16544ox("TTLiveSDkBridge", "execute commerce initLiveCommerce end , result: " + m16379mb);
                C4063ox.f9675u.set(true);
                C4063ox.this.m16356ww();
                if (C4063ox.f9673mb != null) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("live_sdk_init_status", C4063ox.f9675u.get());
                    C4063ox.f9673mb.onEvent(2, bundle);
                }
            }
        };
        if (TTAppContextHolder.getContext() instanceof Application) {
            hostActionParam.setContext((Application) TTAppContextHolder.getContext());
        }
        C4059mb.m16378mb(plugin.getVersion());
        StringBuilder sb = new StringBuilder();
        sb.append("execute live sdk initLive method start, GeneralAppId:");
        ITTLiveConfig iTTLiveConfig2 = this.f9676ko;
        sb.append(iTTLiveConfig2 != null ? iTTLiveConfig2.getGeneralAppId() : null);
        C3972mb.m16544ox("TTLiveSDkBridge", sb.toString());
        Context context = TTAppContextHolder.getContext();
        ITTLiveConfig iTTLiveConfig3 = this.f9676ko;
        C3972mb.m16544ox("TTLiveSDkBridge", "execute live sdk initLive method end, (方法顺利执行结果)result: " + C4059mb.m16375mb(context, iTTLiveConfig3 != null ? iTTLiveConfig3.getGeneralAppId() : null, hostActionParam, iLiveInitCallback));
        f9671h.set(false);
        C4059mb.m16372ox((long) plugin.getVersion());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mb */
    public Object m16360mb(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        try {
            Integer num = (Integer) objArr[0];
            TTAdManager adManager = TTAdSdk.getAdManager();
            if (adManager != null) {
                return adManager.getBiddingToken(new AdSlot.Builder().setAdType(num.intValue()).build());
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    @Override // com.bytedance.sdk.openadsdk.downloadnew.AbstractC4052mb, com.bytedance.sdk.openadsdk.TTAdBridge
    public <T> T callMethod(Class<T> cls, int i, Map<String, Object> map) {
        switch (i) {
            case 0:
                if (!f9675u.get()) {
                    return (T) 1;
                }
                if (!C4059mb.m16376mb(getContext(map.get("context")), m16362mb(map.get("bundle")))) {
                    return (T) 2;
                }
                return (T) 0;
            case 1:
                return (T) f9675u;
            case 2:
                m16361mb(map);
                return null;
            case 3:
                m16368ko();
                return null;
            case 4:
                return (T) m16357u();
            default:
                return (T) super.callMethod(cls, i, map);
        }
    }

    /* renamed from: u */
    private Boolean m16357u() {
        try {
            Object callExpandMethod = LivePluginHelper.getLiveRoomService().callExpandMethod("hasAuthenticated", new Object[0]);
            if (callExpandMethod != null && (callExpandMethod instanceof Boolean)) {
                return (Boolean) callExpandMethod;
            }
        } catch (Throwable th) {
            C3972mb.m16546mb("TTLiveSDkBridge", th);
        }
        return false;
    }

    /* renamed from: ko */
    private void m16368ko() {
        try {
            LivePluginHelper.getLiveRoomService().callExpandMethod("warmingUpBeforeEnter", new Object[0]);
        } catch (Throwable th) {
            C3972mb.m16546mb("TTLiveSDkBridge", th);
        }
    }

    /* renamed from: mb */
    private void m16361mb(Map<String, Object> map) {
        try {
            long longValue = ((Long) map.get("room_id")).longValue();
            Object obj = map.get("event");
            final C4068mb c4068mb = obj instanceof TTAdEvent ? new C4068mb((TTAdEvent) obj) : new C4068mb((EventListener) obj);
            IOuterLiveRoomService liveRoomService = LivePluginHelper.getLiveRoomService();
            System.currentTimeMillis();
            Object callExpandMethod = liveRoomService.callExpandMethod("checkRoomAlive", new Callback<Boolean>() { // from class: com.bytedance.sdk.openadsdk.live.ox.4
                @Override // com.bytedance.android.live.base.api.callback.Callback
                /* renamed from: mb */
                public void invoke(Boolean bool) {
                    if (c4068mb != null) {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("live_room_status", bool.booleanValue());
                        c4068mb.onEvent(0, bundle);
                    }
                }
            }, Long.valueOf(longValue), 300);
            if (callExpandMethod == null) {
                synchronized (c4068mb) {
                    try {
                        c4068mb.notifyAll();
                    } catch (Throwable unused) {
                    }
                }
            }
            C3972mb.m16544ox("TTLiveSDkBridge", "has checkRoomAlive :" + callExpandMethod);
        } catch (Throwable th) {
            C3972mb.m16547mb("TTLiveSDkBridge", "getRoomState: exception:", th);
        }
    }

    private Context getContext(Object obj) {
        if (obj instanceof Context) {
            return (Context) obj;
        }
        return null;
    }

    /* renamed from: mb */
    private Bundle m16362mb(Object obj) {
        if (obj instanceof Bundle) {
            return (Bundle) obj;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ww */
    public void m16356ww() {
        if (f9673mb != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putBoolean("live_plugin_installed", f9672hj.get());
                bundle.putBoolean("live_plugin_inited", f9675u.get());
                f9673mb.onEvent(3, bundle);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.bykv.p167vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        switch (i) {
            case 5:
                init((Bundle) valueSet.objectValue(0, Bundle.class));
                return null;
            case 6:
                Object objectValue = valueSet.objectValue(0, Object.class);
                if (objectValue instanceof TTAdEvent) {
                    subscribe((TTAdEvent) valueSet.objectValue(0, TTAdEvent.class));
                } else if (objectValue instanceof EventListener) {
                    f9673mb = new C4068mb((EventListener) objectValue);
                    m16356ww();
                }
                return null;
            default:
                return (T) callMethod(cls, i, (Map) valueSet.objectValue(0, Map.class));
        }
    }
}
