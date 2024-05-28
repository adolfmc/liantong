package com.bytedance.sdk.openadsdk.api.plugin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.CSJAdError;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTPluginListener;
import com.bytedance.sdk.openadsdk.api.C3972mb;
import com.bytedance.sdk.openadsdk.common.CommonListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.ExitInstallListener;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.bytedance.sdk.openadsdk.api.plugin.mb */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3986mb implements TTAdManager {

    /* renamed from: mb */
    static final C3986mb f9528mb = new C3986mb();

    /* renamed from: ox */
    private volatile TTAdManager f9529ox;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.mb$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    interface InterfaceC3996b<T> extends InterfaceC4010mb<T> {
        /* renamed from: mb */
        void mo16500mb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.mb$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC4010mb<T> {
        /* renamed from: mb */
        void mo16478mb(T t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.mb$ox */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC4011ox<T> {
        /* renamed from: mb */
        void mo16467mb(InterfaceC4010mb<T> interfaceC4010mb);
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public String getSDKVersion() {
        return "5.1.0.2";
    }

    C3986mb() {
    }

    /* renamed from: mb */
    public void m16511mb(TTAdManager tTAdManager) {
        this.f9529ox = tTAdManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.mb$1 */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C39871 implements InterfaceC4011ox<TTAdNative> {

        /* renamed from: mb */
        TTAdNative f9531mb;

        /* renamed from: ox */
        final /* synthetic */ WeakReference f9532ox;

        C39871(WeakReference weakReference) {
            this.f9532ox = weakReference;
        }

        @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4011ox
        /* renamed from: mb */
        public void mo16467mb(final InterfaceC4010mb<TTAdNative> interfaceC4010mb) {
            TTAdNative tTAdNative = this.f9531mb;
            if (tTAdNative == null) {
                C3986mb.this.call(new InterfaceC4010mb<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.1.1
                    @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                    /* renamed from: mb  reason: avoid collision after fix types in other method */
                    public void mo16478mb(TTAdManager tTAdManager) {
                        C39871 c39871 = C39871.this;
                        c39871.f9531mb = tTAdManager.createAdNative((Context) c39871.f9532ox.get());
                        interfaceC4010mb.mo16478mb(C39871.this.f9531mb);
                    }
                });
            } else {
                interfaceC4010mb.mo16478mb(tTAdNative);
            }
        }
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public TTAdNative createAdNative(Context context) {
        return new C3997hj(new C39871(new WeakReference(context)));
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public void register(final Object obj) {
        final Bundle bundle;
        if (obj instanceof TTPluginListener) {
            TTPluginListener tTPluginListener = (TTPluginListener) obj;
            bundle = C4021u.m16446mb(TTAppContextHolder.getContext()).m16436mb(tTPluginListener.packageName(), tTPluginListener.config());
        } else {
            bundle = obj;
        }
        call(new InterfaceC4010mb<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.2
            @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
            /* renamed from: mb  reason: avoid collision after fix types in other method */
            public void mo16478mb(TTAdManager tTAdManager) {
                tTAdManager.register(bundle);
                if (obj instanceof TTPluginListener) {
                    C4021u.m16446mb(TTAppContextHolder.getContext()).m16444mb((TTPluginListener) obj);
                }
            }
        });
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public void unregister(final Object obj) {
        call(new InterfaceC4010mb<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.3
            @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
            /* renamed from: mb  reason: avoid collision after fix types in other method */
            public void mo16478mb(TTAdManager tTAdManager) {
                tTAdManager.unregister(obj);
            }
        });
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public <T> T getExtra(final Class<T> cls, final Bundle bundle) {
        if (this.f9529ox != null) {
            return (T) this.f9529ox.getExtra(cls, bundle);
        }
        if (cls == Bundle.class && bundle != null && bundle.getInt("action", 0) == 1) {
            call(new InterfaceC3996b<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.4
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC3996b
                /* renamed from: mb */
                public void mo16500mb() {
                    C3978hj.m16525mb(bundle);
                }

                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb */
                public void mo16478mb(TTAdManager tTAdManager) {
                    tTAdManager.getExtra(cls, bundle);
                }
            });
            return null;
        }
        call(new InterfaceC4010mb<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.5
            @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
            /* renamed from: mb  reason: avoid collision after fix types in other method */
            public void mo16478mb(TTAdManager tTAdManager) {
                tTAdManager.getExtra(cls, bundle);
            }
        });
        return null;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public void requestPermissionIfNecessary(final Context context) {
        call(new InterfaceC4010mb<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.6
            @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
            /* renamed from: mb  reason: avoid collision after fix types in other method */
            public void mo16478mb(TTAdManager tTAdManager) {
                tTAdManager.requestPermissionIfNecessary(context);
            }
        });
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public boolean tryShowInstallDialogWhenExit(Activity activity, ExitInstallListener exitInstallListener) {
        return this.f9529ox != null && this.f9529ox.tryShowInstallDialogWhenExit(activity, exitInstallListener);
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public String getPluginVersion() {
        return this.f9529ox != null ? this.f9529ox.getPluginVersion() : "";
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public String getBiddingToken(AdSlot adSlot) {
        if (this.f9529ox != null) {
            return this.f9529ox.getBiddingToken(adSlot);
        }
        return null;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public String getBiddingToken(AdSlot adSlot, boolean z, int i) {
        if (this.f9529ox != null) {
            return this.f9529ox.getBiddingToken(adSlot, z, i);
        }
        return null;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public void setThemeStatus(final int i) {
        call(new InterfaceC4010mb<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.7
            @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
            /* renamed from: mb  reason: avoid collision after fix types in other method */
            public void mo16478mb(TTAdManager tTAdManager) {
                C3986mb.this.f9529ox.setThemeStatus(i);
            }
        });
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public int getThemeStatus() {
        if (this.f9529ox != null) {
            return this.f9529ox.getThemeStatus();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void call(final InterfaceC4010mb<TTAdManager> interfaceC4010mb) {
        if (this.f9529ox != null) {
            try {
                interfaceC4010mb.mo16478mb(this.f9529ox);
            } catch (Throwable th) {
                C3972mb.m16552hj("PluginDefaultAdManager", "Unexpected manager call error: " + th.getMessage());
                C4021u.m16435mb(th);
            }
        } else if (C3982ko.f9517mb != null) {
            C3982ko.f9517mb.submit(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.8
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (C3986mb.this.f9529ox != null) {
                            interfaceC4010mb.mo16478mb(C3986mb.this.f9529ox);
                            return;
                        }
                        if (interfaceC4010mb instanceof InterfaceC3996b) {
                            ((InterfaceC3996b) interfaceC4010mb).mo16500mb();
                        }
                        C3972mb.m16552hj("PluginDefaultAdManager", "Not ready, no manager");
                    } catch (Throwable th2) {
                        C3972mb.m16552hj("PluginDefaultAdManager", "Unexpected manager call error: " + th2.getMessage());
                        C4021u.m16435mb(th2);
                    }
                }
            });
        } else {
            C3972mb.m16552hj("PluginDefaultAdManager", "Not ready, no executor");
        }
    }

    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.mb$hj */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static final class C3997hj implements TTAdNative {

        /* renamed from: mb */
        private InterfaceC4011ox<TTAdNative> f9552mb;

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadInteractionExpressAd(AdSlot adSlot, TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
        }

        public C3997hj(InterfaceC4011ox<TTAdNative> interfaceC4011ox) {
            this.f9552mb = interfaceC4011ox;
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadFeedAd(final AdSlot adSlot, final TTAdNative.FeedAdListener feedAdListener) {
            m16492mb(feedAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.1
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadFeedAd(adSlot, feedAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadStream(final AdSlot adSlot, final TTAdNative.FeedAdListener feedAdListener) {
            m16492mb(feedAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.5
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadStream(adSlot, feedAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadDrawFeedAd(final AdSlot adSlot, final TTAdNative.DrawFeedAdListener drawFeedAdListener) {
            m16492mb(drawFeedAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.6
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadDrawFeedAd(adSlot, drawFeedAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadNativeAd(final AdSlot adSlot, final TTAdNative.NativeAdListener nativeAdListener) {
            m16492mb(nativeAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.7
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadNativeAd(adSlot, nativeAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadSplashAd(final AdSlot adSlot, final TTAdNative.SplashAdListener splashAdListener, final int i) {
            m16492mb(splashAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.8
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadSplashAd(adSlot, splashAdListener, i);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadSplashAd(final AdSlot adSlot, final TTAdNative.CSJSplashAdListener cSJSplashAdListener, final int i) {
            m16493mb(cSJSplashAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.9
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadSplashAd(adSlot, cSJSplashAdListener, i);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadSplashAd(final AdSlot adSlot, final TTAdNative.SplashAdListener splashAdListener) {
            m16492mb(splashAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.10
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadSplashAd(adSlot, splashAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadRewardVideoAd(final AdSlot adSlot, final TTAdNative.RewardVideoAdListener rewardVideoAdListener) {
            m16492mb(rewardVideoAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.11
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadRewardVideoAd(adSlot, rewardVideoAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadFullScreenVideoAd(final AdSlot adSlot, final TTAdNative.FullScreenVideoAdListener fullScreenVideoAdListener) {
            m16492mb(fullScreenVideoAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.12
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadFullScreenVideoAd(adSlot, fullScreenVideoAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadNativeExpressAd(final AdSlot adSlot, final TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
            m16492mb(nativeExpressAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.2
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadNativeExpressAd(adSlot, nativeExpressAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadExpressDrawFeedAd(final AdSlot adSlot, final TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
            m16492mb(nativeExpressAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.3
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadExpressDrawFeedAd(adSlot, nativeExpressAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadBannerExpressAd(final AdSlot adSlot, final TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
            m16492mb(nativeExpressAdListener, new InterfaceC4010mb<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.mb.hj.4
                @Override // com.bytedance.sdk.openadsdk.api.plugin.C3986mb.InterfaceC4010mb
                /* renamed from: mb  reason: avoid collision after fix types in other method */
                public void mo16478mb(TTAdNative tTAdNative) {
                    tTAdNative.loadBannerExpressAd(adSlot, nativeExpressAdListener);
                }
            });
        }

        /* renamed from: mb */
        private final void m16492mb(CommonListener commonListener, InterfaceC4010mb<TTAdNative> interfaceC4010mb) {
            try {
                this.f9552mb.mo16467mb(interfaceC4010mb);
            } catch (Throwable th) {
                if (commonListener != null) {
                    commonListener.onError(4202, "Load ad failed: " + th.getMessage());
                }
            }
        }

        /* renamed from: mb */
        private final void m16493mb(TTAdNative.CSJSplashAdListener cSJSplashAdListener, InterfaceC4010mb<TTAdNative> interfaceC4010mb) {
            try {
                this.f9552mb.mo16467mb(interfaceC4010mb);
            } catch (Throwable th) {
                if (cSJSplashAdListener != null) {
                    cSJSplashAdListener.onSplashLoadFail(new CSJAdError(4202, "Load ad failed: " + th.getMessage()));
                }
            }
        }
    }
}
