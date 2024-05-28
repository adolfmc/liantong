package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wechat.utils.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WechatHandler {

    /* renamed from: a */
    private Platform f3286a;

    /* renamed from: b */
    private Platform.ShareParams f3287b;

    /* renamed from: c */
    private PlatformActionListener f3288c;

    /* renamed from: d */
    private AuthorizeListener f3289d;

    /* renamed from: e */
    private WXAuthHelper f3290e;

    public WechatHandler(Platform platform) {
        this.f3286a = platform;
    }

    /* renamed from: a */
    public void m21284a(AuthorizeListener authorizeListener) {
        this.f3289d = authorizeListener;
    }

    /* renamed from: a */
    public void m21285a(Platform.ShareParams shareParams, PlatformActionListener platformActionListener) {
        this.f3287b = shareParams;
        this.f3288c = platformActionListener;
    }

    /* renamed from: a */
    public void m21282a(WXAuthHelper wXAuthHelper) {
        this.f3290e = wXAuthHelper;
    }

    /* renamed from: a */
    public void m21283a(WechatResp wechatResp) {
        AuthorizeListener authorizeListener;
        int i = wechatResp.f3247g;
        if (i == 0) {
            switch (wechatResp.mo21305a()) {
                case 1:
                    if (this.f3289d != null) {
                        Bundle bundle = new Bundle();
                        wechatResp.mo21303b(bundle);
                        this.f3290e.m21316a(bundle, this.f3289d);
                        return;
                    }
                    return;
                case 2:
                    if (this.f3288c != null) {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("ShareParams", this.f3287b);
                        this.f3288c.onComplete(this.f3286a, 9, hashMap);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
        switch (i) {
            case -4:
                HashMap hashMap2 = new HashMap();
                hashMap2.put("errCode", Integer.valueOf(wechatResp.f3247g));
                hashMap2.put("errStr", wechatResp.f3248h);
                hashMap2.put("transaction", wechatResp.f3249i);
                Throwable th = new Throwable(new Hashon().fromHashMap(hashMap2));
                if (wechatResp.mo21305a() == 1 && (authorizeListener = this.f3289d) != null) {
                    authorizeListener.onError(th);
                    return;
                }
                return;
            case -3:
                HashMap hashMap3 = new HashMap();
                hashMap3.put("errCode", Integer.valueOf(wechatResp.f3247g));
                hashMap3.put("errStr", wechatResp.f3248h);
                hashMap3.put("transaction", wechatResp.f3249i);
                Throwable th2 = new Throwable(new Hashon().fromHashMap(hashMap3));
                switch (wechatResp.mo21305a()) {
                    case 1:
                        AuthorizeListener authorizeListener2 = this.f3289d;
                        if (authorizeListener2 != null) {
                            authorizeListener2.onError(th2);
                            return;
                        }
                        return;
                    case 2:
                        PlatformActionListener platformActionListener = this.f3288c;
                        if (platformActionListener != null) {
                            platformActionListener.onError(this.f3286a, 9, th2);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            case -2:
                switch (wechatResp.mo21305a()) {
                    case 1:
                        AuthorizeListener authorizeListener3 = this.f3289d;
                        if (authorizeListener3 != null) {
                            authorizeListener3.onCancel();
                            return;
                        }
                        return;
                    case 2:
                        PlatformActionListener platformActionListener2 = this.f3288c;
                        if (platformActionListener2 != null) {
                            platformActionListener2.onCancel(this.f3286a, 9);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            default:
                HashMap hashMap4 = new HashMap();
                hashMap4.put("req", wechatResp.getClass().getSimpleName());
                hashMap4.put("errCode", Integer.valueOf(wechatResp.f3247g));
                hashMap4.put("errStr", wechatResp.f3248h);
                hashMap4.put("transaction", wechatResp.f3249i);
                Throwable th3 = new Throwable(new Hashon().fromHashMap(hashMap4));
                PlatformActionListener platformActionListener3 = this.f3288c;
                if (platformActionListener3 != null) {
                    platformActionListener3.onError(this.f3286a, 9, th3);
                }
                AuthorizeListener authorizeListener4 = this.f3289d;
                if (authorizeListener4 != null) {
                    authorizeListener4.onError(th3);
                    return;
                }
                return;
        }
    }

    /* renamed from: a */
    public Platform.ShareParams m21286a() {
        return this.f3287b;
    }

    /* renamed from: b */
    public Platform m21281b() {
        return this.f3286a;
    }

    /* renamed from: c */
    public PlatformActionListener m21280c() {
        return this.f3288c;
    }
}
