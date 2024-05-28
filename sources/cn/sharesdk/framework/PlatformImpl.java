package cn.sharesdk.framework;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import cn.sharesdk.framework.AgreementDialog;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ProvicyCanContinue;
import cn.sharesdk.framework.loopshare.MoblinkActionListener;
import cn.sharesdk.framework.p094a.p095a.SharePrefrenceUtil;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.commons.ForbThrowable;
import com.mob.commons.dialog.PolicyThrowable;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.ResHelper;
import java.lang.reflect.Field;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PlatformImpl {

    /* renamed from: a */
    private Platform f2893a;

    /* renamed from: b */
    private PlatformDb f2894b;

    /* renamed from: c */
    private InnerPlatformActionListener f2895c;

    /* renamed from: d */
    private int f2896d;

    /* renamed from: e */
    private int f2897e;

    /* renamed from: f */
    private boolean f2898f;

    /* renamed from: g */
    private boolean f2899g = true;

    /* renamed from: h */
    private boolean f2900h;

    /* renamed from: b */
    private String m21821b(int i) {
        switch (i) {
            case 1:
                return "ACTION_AUTHORIZING";
            case 2:
                return "ACTION_GETTING_FRIEND_LIST";
            case 3:
            case 4:
            default:
                return "ACTION_CUSTOMER";
            case 5:
                return "ACTION_SENDING_DIRECT_MESSAGE";
            case 6:
                return "ACTION_FOLLOWING_USER";
            case 7:
                return "ACTION_TIMELINE";
            case 8:
                return "ACTION_USER_INFOR";
            case 9:
                return "ACTION_SHARE";
            case 10:
                return "ACTION_GETTING_BILATERAL_LIST";
            case 11:
                return "ACTION_GETTING_FOLLOWER_LIST";
        }
    }

    public PlatformImpl(Platform platform) {
        this.f2893a = platform;
        String name = platform.getName();
        this.f2894b = new PlatformDb(name, platform.getVersion());
        m21828a(name);
        this.f2895c = new InnerPlatformActionListener();
        ForbSwitchFunction.m21859a();
    }

    /* renamed from: a */
    public void m21828a(String str) {
        try {
            this.f2896d = ResHelper.parseInt(String.valueOf(ShareSDK.getDevinfo(str, "Id")).trim());
        } catch (Throwable unused) {
            if (!(this.f2893a instanceof CustomPlatform)) {
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21744a(this.f2893a.getName() + " failed to parse Id, this will cause method getId() always returens 0", new Object[0]);
            }
        }
        try {
            this.f2897e = ResHelper.parseInt(String.valueOf(ShareSDK.getDevinfo(str, "SortId")).trim());
        } catch (Throwable unused2) {
            if (!(this.f2893a instanceof CustomPlatform)) {
                SSDKLog m21740b2 = SSDKLog.m21740b();
                m21740b2.m21744a(this.f2893a.getName() + " failed to parse SortId, this won't cause any problem, don't worry", new Object[0]);
            }
        }
        String devinfo = ShareSDK.getDevinfo(str, "Enable");
        if (devinfo == null) {
            this.f2900h = true;
            if (!(this.f2893a instanceof CustomPlatform)) {
                SSDKLog m21740b3 = SSDKLog.m21740b();
                m21740b3.m21744a(this.f2893a.getName() + " failed to parse Enable, this will cause platform always be enable", new Object[0]);
            }
        } else {
            this.f2900h = "true".equals(devinfo.trim());
        }
        this.f2893a.initDevInfo(str);
    }

    /* renamed from: a */
    public int m21841a() {
        return this.f2896d;
    }

    /* renamed from: b */
    public int m21822b() {
        return this.f2897e;
    }

    /* renamed from: a */
    public void m21834a(PlatformActionListener platformActionListener) {
        this.f2895c.m21852a(platformActionListener);
    }

    /* renamed from: c */
    public PlatformActionListener m21813c() {
        return this.f2895c.m21857a();
    }

    /* renamed from: d */
    public boolean m21808d() {
        return this.f2894b.isValid();
    }

    /* renamed from: a */
    public void m21824a(boolean z) {
        this.f2898f = z;
    }

    /* renamed from: e */
    public boolean m21806e() {
        return this.f2898f;
    }

    /* renamed from: f */
    public boolean m21805f() {
        return this.f2900h;
    }

    /* renamed from: a */
    private String m21840a(int i) {
        return "ShareSDK_" + this.f2893a.getName() + "_" + m21821b(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public boolean m21801j() {
        if (ShareSDK.m22010a()) {
            String m21837a = m21837a(this.f2893a.getPlatformId(), "covert_url", (String) null);
            if (m21837a != null) {
                m21837a.trim();
            }
            this.f2899g = !"false".equals(m21837a);
            this.f2893a.setNetworkDevinfo();
            return true;
        }
        try {
            if (ShareSDK.m22003b()) {
                String m21837a2 = m21837a(this.f2893a.getPlatformId(), "covert_url", (String) null);
                if (m21837a2 != null) {
                    m21837a2.trim();
                }
                this.f2899g = !"false".equals(m21837a2);
                this.f2893a.setNetworkDevinfo();
                return true;
            }
            return false;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
            return false;
        }
    }

    /* renamed from: a */
    public String m21837a(int i, String str, String str2) {
        String m22008a = ShareSDK.m22008a(i, str);
        if (TextUtils.isEmpty(m22008a) || "null".equals(m22008a)) {
            Platform platform = this.f2893a;
            return platform.getDevinfo(platform.getName(), str2);
        }
        return m22008a;
    }

    /* renamed from: a */
    public void m21838a(int i, Object obj) {
        this.f2895c.m21855a(this.f2893a, i, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00f2 -> B:58:0x0162). Please submit an issue!!! */
    /* renamed from: b */
    public void m21820b(int i, Object obj) {
        Field[] fields;
        Object obj2;
        switch (i) {
            case 1:
                InnerPlatformActionListener innerPlatformActionListener = this.f2895c;
                if (innerPlatformActionListener != null) {
                    innerPlatformActionListener.onComplete(this.f2893a, 1, null);
                    return;
                }
                return;
            case 2:
                Object[] objArr = (Object[]) obj;
                this.f2893a.getFriendList(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), (String) objArr[2]);
                return;
            case 3:
            case 4:
            case 5:
            default:
                Object[] objArr2 = (Object[]) obj;
                this.f2893a.doCustomerProtocol(String.valueOf(objArr2[0]), String.valueOf(objArr2[1]), i, (HashMap) objArr2[2], (HashMap) objArr2[3]);
                return;
            case 6:
                this.f2893a.follow((String) obj);
                return;
            case 7:
                Object[] objArr3 = (Object[]) obj;
                this.f2893a.timeline(((Integer) objArr3[0]).intValue(), ((Integer) objArr3[1]).intValue(), (String) objArr3[2]);
                return;
            case 8:
                this.f2893a.userInfor(obj != null ? (String) obj : null);
                return;
            case 9:
                final Platform.ShareParams shareParams = (Platform.ShareParams) obj;
                HashMap<String, Object> map = shareParams.toMap();
                for (Field field : shareParams.getClass().getFields()) {
                    if (map.get(field.getName()) == null) {
                        field.setAccessible(true);
                        try {
                            obj2 = field.get(shareParams);
                        } catch (Throwable th) {
                            SSDKLog.m21740b().m21737b(th);
                            obj2 = null;
                        }
                        if (obj2 != null) {
                            map.put(field.getName(), obj2);
                        }
                    }
                }
                InnerPlatformActionListener innerPlatformActionListener2 = this.f2895c;
                if (innerPlatformActionListener2 instanceof InnerPlatformActionListener) {
                    innerPlatformActionListener2.m21853a(this.f2893a, shareParams);
                }
                try {
                    if (shareParams.getLoopshareCustomParams().size() > 0 && shareParams.getLoopshareCustomParams() != null) {
                        if (this.f2893a.getName().equals("QQ")) {
                            if (!TextUtils.isEmpty(shareParams.getTitleUrl())) {
                                ShareSDK.mobLinkGetMobID(shareParams.getLoopshareCustomParams(), new MoblinkActionListener() { // from class: cn.sharesdk.framework.f.1
                                    /* JADX WARN: Type inference failed for: r4v2, types: [cn.sharesdk.framework.f$1$1] */
                                    @Override // cn.sharesdk.framework.loopshare.MoblinkActionListener
                                    public void onResult(Object obj3) {
                                        if (!TextUtils.isEmpty(Uri.parse(shareParams.getTitleUrl()).getEncodedQuery())) {
                                            Platform.ShareParams shareParams2 = shareParams;
                                            shareParams2.setTitleUrl(shareParams.getTitleUrl() + "&mobid=" + obj3);
                                        } else {
                                            Platform.ShareParams shareParams3 = shareParams;
                                            shareParams3.setTitleUrl(shareParams.getTitleUrl() + "?mobid=" + obj3);
                                        }
                                        new Thread() { // from class: cn.sharesdk.framework.f.1.1
                                            @Override // java.lang.Thread, java.lang.Runnable
                                            public void run() {
                                                super.run();
                                                PlatformImpl.this.f2893a.doShare(shareParams);
                                            }
                                        }.start();
                                    }

                                    @Override // cn.sharesdk.framework.loopshare.MoblinkActionListener
                                    public void onError(Throwable th2) {
                                        if (PlatformImpl.this.f2895c != null) {
                                            PlatformImpl.this.f2895c.onError(PlatformImpl.this.f2893a, 9, th2);
                                        }
                                    }
                                });
                            } else if (this.f2895c != null) {
                                this.f2895c.onError(this.f2893a, 9, new Throwable("TitleUrl cannot be empty if setLoopshareCustomParams is used in QQ"));
                            }
                        } else if (!TextUtils.isEmpty(shareParams.getUrl())) {
                            if (this.f2895c != null) {
                                ShareSDK.mobLinkGetMobID(shareParams.getLoopshareCustomParams(), new MoblinkActionListener() { // from class: cn.sharesdk.framework.f.2
                                    /* JADX WARN: Type inference failed for: r4v2, types: [cn.sharesdk.framework.f$2$1] */
                                    @Override // cn.sharesdk.framework.loopshare.MoblinkActionListener
                                    public void onResult(Object obj3) {
                                        if (!TextUtils.isEmpty(Uri.parse(shareParams.getUrl()).getEncodedQuery())) {
                                            Platform.ShareParams shareParams2 = shareParams;
                                            shareParams2.setUrl(shareParams.getUrl() + "&mobid=" + obj3);
                                        } else {
                                            Platform.ShareParams shareParams3 = shareParams;
                                            shareParams3.setUrl(shareParams.getUrl() + "?mobid=" + obj3);
                                        }
                                        new Thread() { // from class: cn.sharesdk.framework.f.2.1
                                            @Override // java.lang.Thread, java.lang.Runnable
                                            public void run() {
                                                super.run();
                                                PlatformImpl.this.f2893a.doShare(shareParams);
                                            }
                                        }.start();
                                    }

                                    @Override // cn.sharesdk.framework.loopshare.MoblinkActionListener
                                    public void onError(Throwable th2) {
                                        if (PlatformImpl.this.f2895c != null) {
                                            PlatformImpl.this.f2895c.onError(PlatformImpl.this.f2893a, 9, th2);
                                        }
                                    }
                                });
                            }
                        } else if (this.f2895c != null) {
                            this.f2895c.onError(this.f2893a, 9, new Throwable("SetUrl cannot be empty if setLoopshareCustomParams is used"));
                        }
                    } else {
                        this.f2893a.doShare(shareParams);
                    }
                } catch (Throwable th2) {
                    SSDKLog.m21740b().m21744a("PlatformImpl platform.doshare() " + th2, new Object[0]);
                }
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21833a(InnerPlatformActionListener innerPlatformActionListener, int i) {
        if (innerPlatformActionListener != null) {
            innerPlatformActionListener.onError(this.f2893a, i, new ForbThrowable());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m21818b(InnerPlatformActionListener innerPlatformActionListener, int i) {
        if (innerPlatformActionListener != null) {
            innerPlatformActionListener.onError(this.f2893a, i, new Throwable("'appkey' is illegal"));
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [cn.sharesdk.framework.f$3] */
    /* JADX WARN: Type inference failed for: r0v1, types: [cn.sharesdk.framework.f$4] */
    /* renamed from: c */
    protected void m21812c(final int i, final Object obj) {
        new Thread(m21840a(i)) { // from class: cn.sharesdk.framework.f.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    if (MobSDK.isForb() || !CheckAppKey.m21992a()) {
                        return;
                    }
                    switch (MobSDK.isAuth()) {
                        case 1:
                        case 2:
                            PlatformImpl.this.m21801j();
                            SSDKLog.m21740b().m21743a("The user is using the privacy version without a popup newThreadJob 001");
                            return;
                        default:
                            return;
                    }
                } catch (Throwable th) {
                    SSDKLog m21740b = SSDKLog.m21740b();
                    m21740b.m21743a("newThreadJob  " + th);
                }
            }
        }.start();
        new Thread() { // from class: cn.sharesdk.framework.f.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    if (MobSDK.isForb()) {
                        PlatformImpl.this.m21833a(PlatformImpl.this.f2895c, i);
                    } else {
                        try {
                            switch (MobSDK.isAuth()) {
                                case 1:
                                case 2:
                                    if (!CheckAppKey.m21992a()) {
                                        PlatformImpl.this.m21818b(PlatformImpl.this.f2895c, i);
                                        break;
                                    } else if (PlatformImpl.this.f2893a.checkAuthorize(i, obj)) {
                                        PlatformImpl.this.m21820b(i, obj);
                                        SSDKLog.m21740b().m21743a("The user is using the privacy version without a popup newThreadJob 002");
                                        break;
                                    }
                                    break;
                                default:
                                    if (PlatformImpl.this.f2895c != null) {
                                        PlatformImpl.this.f2895c.onError(PlatformImpl.this.f2893a, i, new PolicyThrowable());
                                        break;
                                    }
                                    break;
                            }
                        } catch (Throwable unused) {
                            ProvicyCanContinue.m22013a().m22012a(new ProvicyCanContinue.OnBusinessListener() { // from class: cn.sharesdk.framework.f.4.1
                                @Override // cn.sharesdk.framework.ProvicyCanContinue.OnBusinessListener
                                public void onContinue() {
                                    if (!CheckAppKey.m21992a()) {
                                        PlatformImpl.this.m21818b(PlatformImpl.this.f2895c, i);
                                    } else if (PlatformImpl.this.f2893a.checkAuthorize(i, obj)) {
                                        PlatformImpl.this.m21820b(i, obj);
                                    }
                                    SSDKLog.m21740b().m21743a("The user is using the framed privacy version newThreadJob 002");
                                }

                                @Override // cn.sharesdk.framework.ProvicyCanContinue.OnBusinessListener
                                public void onStop() {
                                    if (PlatformImpl.this.f2895c != null) {
                                        PlatformImpl.this.f2895c.onError(PlatformImpl.this.f2893a, i, new PolicyThrowable());
                                    }
                                }

                                @Override // cn.sharesdk.framework.ProvicyCanContinue.OnBusinessListener
                                public void onError(Throwable th) {
                                    if (PlatformImpl.this.f2895c != null) {
                                        PlatformImpl.this.f2895c.onError(PlatformImpl.this.f2893a, i, th);
                                    }
                                }
                            });
                        }
                    }
                } catch (Throwable th) {
                    SSDKLog m21740b = SSDKLog.m21740b();
                    m21740b.m21744a("new Thread(getThreadName(action)) " + th, new Object[0]);
                }
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [cn.sharesdk.framework.f$5] */
    /* renamed from: a */
    public void m21835a(final Platform.ShareParams shareParams) {
        if (shareParams == null) {
            InnerPlatformActionListener innerPlatformActionListener = this.f2895c;
            if (innerPlatformActionListener != null) {
                innerPlatformActionListener.onError(this.f2893a, 9, new NullPointerException());
                return;
            }
            return;
        }
        new Thread(m21840a(1)) { // from class: cn.sharesdk.framework.f.5
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    PlatformImpl.this.f2893a.subscribeAuth(shareParams);
                    SSDKLog.m21740b().m21744a("ShareSDK", "subscribeAuth start on PlatformImpl");
                } catch (Throwable th) {
                    SSDKLog m21740b = SSDKLog.m21740b();
                    m21740b.m21744a("ShareSDK", " subscribeAuth catch " + th);
                }
            }
        }.start();
    }

    /* renamed from: a */
    public void m21823a(final String[] strArr) {
        try {
            if (MobSDK.isGppVer() && !SharePrefrenceUtil.m21961a().m21937h("no_use_gpp")) {
                if (!SharePrefrenceUtil.m21961a().m21937h("gpp_ver_sent")) {
                    AgreementDialog agreementDialog = new AgreementDialog();
                    agreementDialog.setShareParam(new AgreementDialog.OnDialogDismiss() { // from class: cn.sharesdk.framework.f.6
                        @Override // cn.sharesdk.framework.AgreementDialog.OnDialogDismiss
                        public void consent() {
                            SharePrefrenceUtil.m21961a().m21955a(true);
                            PlatformImpl.this.m21814b(strArr);
                        }

                        @Override // cn.sharesdk.framework.AgreementDialog.OnDialogDismiss
                        public void refuse() {
                            if (PlatformImpl.this.f2895c != null) {
                                PlatformImpl.this.f2895c.onError(PlatformImpl.this.f2893a, 21, null);
                            }
                        }
                    });
                    agreementDialog.show(MobSDK.getContext(), null);
                } else {
                    m21814b(strArr);
                }
            } else {
                m21814b(strArr);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
            m21814b(strArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [cn.sharesdk.framework.f$7] */
    /* JADX WARN: Type inference failed for: r0v1, types: [cn.sharesdk.framework.f$8] */
    /* renamed from: b */
    public void m21814b(final String[] strArr) {
        new Thread(m21840a(1)) { // from class: cn.sharesdk.framework.f.7
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    if (MobSDK.isForb() || !CheckAppKey.m21992a()) {
                        return;
                    }
                    switch (MobSDK.isAuth()) {
                        case 1:
                        case 2:
                            PlatformImpl.this.m21801j();
                            SSDKLog.m21740b().m21743a("The user is using the privacy version without a popup authorize 001");
                            return;
                        default:
                            return;
                    }
                } catch (Throwable th) {
                    SSDKLog m21740b = SSDKLog.m21740b();
                    m21740b.m21744a("authorize(final String[] permissions) " + th, new Object[0]);
                }
            }
        }.start();
        new Thread() { // from class: cn.sharesdk.framework.f.8
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    if (MobSDK.isForb()) {
                        PlatformImpl.this.m21833a(PlatformImpl.this.f2895c, 1);
                    } else {
                        try {
                            switch (MobSDK.isAuth()) {
                                case 1:
                                case 2:
                                    if (!CheckAppKey.m21992a()) {
                                        PlatformImpl.this.m21818b(PlatformImpl.this.f2895c, 1);
                                        break;
                                    } else {
                                        PlatformImpl.this.f2893a.doAuthorize(strArr);
                                        break;
                                    }
                                default:
                                    if (PlatformImpl.this.f2895c != null) {
                                        PlatformImpl.this.f2895c.onError(PlatformImpl.this.f2893a, 1, new PolicyThrowable());
                                        break;
                                    }
                                    break;
                            }
                        } catch (Throwable unused) {
                            ProvicyCanContinue.m22013a().m22012a(new ProvicyCanContinue.OnBusinessListener() { // from class: cn.sharesdk.framework.f.8.1
                                @Override // cn.sharesdk.framework.ProvicyCanContinue.OnBusinessListener
                                public void onContinue() {
                                    if (!CheckAppKey.m21992a()) {
                                        PlatformImpl.this.m21818b(PlatformImpl.this.f2895c, 1);
                                    } else {
                                        PlatformImpl.this.f2893a.doAuthorize(strArr);
                                    }
                                    SSDKLog.m21740b().m21743a("The user is using the privacy version with a pop-up box authorize 002");
                                }

                                @Override // cn.sharesdk.framework.ProvicyCanContinue.OnBusinessListener
                                public void onStop() {
                                    if (PlatformImpl.this.f2895c != null) {
                                        PlatformImpl.this.f2895c.onError(PlatformImpl.this.f2893a, 1, new PolicyThrowable());
                                    }
                                }

                                @Override // cn.sharesdk.framework.ProvicyCanContinue.OnBusinessListener
                                public void onError(Throwable th) {
                                    if (PlatformImpl.this.f2895c != null) {
                                        PlatformImpl.this.f2895c.onError(PlatformImpl.this.f2893a, 1, new PolicyThrowable());
                                    }
                                }
                            });
                        }
                    }
                } catch (Throwable th) {
                    SSDKLog m21740b = SSDKLog.m21740b();
                    m21740b.m21744a("new Thread Platform.ACTION_AUTHORIZING " + th, new Object[0]);
                }
            }
        }.start();
    }

    /* renamed from: b */
    public void m21819b(final Platform.ShareParams shareParams) {
        try {
            if (MobSDK.isGppVer() && !SharePrefrenceUtil.m21961a().m21937h("no_use_gpp")) {
                if (!SharePrefrenceUtil.m21961a().m21937h("gpp_ver_sent")) {
                    AgreementDialog agreementDialog = new AgreementDialog();
                    agreementDialog.setShareParam(new AgreementDialog.OnDialogDismiss() { // from class: cn.sharesdk.framework.f.9
                        @Override // cn.sharesdk.framework.AgreementDialog.OnDialogDismiss
                        public void consent() {
                            SharePrefrenceUtil.m21961a().m21955a(true);
                            PlatformImpl.this.m21811c(shareParams);
                        }

                        @Override // cn.sharesdk.framework.AgreementDialog.OnDialogDismiss
                        public void refuse() {
                            if (PlatformImpl.this.f2895c != null) {
                                PlatformImpl.this.f2895c.onError(PlatformImpl.this.f2893a, 21, new Throwable("The user rejected the request to read the applist"));
                            }
                        }
                    });
                    agreementDialog.show(MobSDK.getContext(), null);
                    return;
                }
                m21811c(shareParams);
                return;
            }
            m21811c(shareParams);
        } catch (Throwable unused) {
            m21811c(shareParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m21811c(Platform.ShareParams shareParams) {
        try {
            if (shareParams == null) {
                if (this.f2895c != null) {
                    this.f2895c.onError(this.f2893a, 9, new NullPointerException());
                    return;
                }
                return;
            }
            try {
                if (!shareParams.getOpenCustomEven()) {
                    ShareSDK.logDemoEvent(3, this.f2893a);
                }
            } catch (Throwable unused) {
            }
            m21812c(9, shareParams);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21732d(th);
        }
    }

    /* renamed from: b */
    public void m21815b(String str) {
        m21812c(6, str);
    }

    /* renamed from: a */
    public void m21827a(String str, int i, int i2) {
        m21812c(7, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str});
    }

    /* renamed from: c */
    public void m21809c(String str) {
        m21812c(8, str);
    }

    /* renamed from: a */
    public void m21839a(int i, int i2, String str) {
        m21812c(2, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str});
    }

    /* renamed from: a */
    public void m21826a(String str, String str2, short s, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        m21812c(s | 655360, new Object[]{str, str2, hashMap, hashMap2});
    }

    /* renamed from: g */
    public PlatformDb m21804g() {
        return this.f2894b;
    }

    /* renamed from: h */
    public void m21803h() {
        this.f2894b.removeAccount();
    }

    /* renamed from: a */
    public String m21825a(String str, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.f2899g) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21738b("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis));
            return str;
        } else if (TextUtils.isEmpty(str)) {
            SSDKLog m21740b2 = SSDKLog.m21740b();
            m21740b2.m21738b("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis));
            return str;
        } else {
            String m22004a = ShareSDK.m22004a(str, z, this.f2893a.getPlatformId(), m21800k());
            SSDKLog m21740b3 = SSDKLog.m21740b();
            m21740b3.m21738b("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis));
            return m22004a;
        }
    }

    /* renamed from: k */
    private String m21800k() {
        StringBuilder sb = new StringBuilder();
        try {
            if ("TencentWeibo".equals(this.f2893a.getName())) {
                SSDKLog.m21740b().m21735c("user id %s ==>>", m21804g().getUserName());
                sb.append(Data.urlEncode(m21804g().getUserName(), "utf-8"));
            } else {
                sb.append(Data.urlEncode(m21804g().getUserId(), "utf-8"));
            }
            sb.append("|");
            sb.append(Data.urlEncode(m21804g().get("secretType"), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(m21804g().get("gender"), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(m21804g().get("birthday"), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(m21804g().get("educationJSONArrayStr"), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(m21804g().get("workJSONArrayStr"), "utf-8"));
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: i */
    public PlatformActionListener m21802i() {
        return this.f2895c;
    }

    /* renamed from: d */
    public String m21807d(String str) {
        return ShareSDK.m22006a(str);
    }

    /* renamed from: a */
    public String m21836a(Bitmap bitmap) {
        return ShareSDK.m22007a(bitmap);
    }
}
