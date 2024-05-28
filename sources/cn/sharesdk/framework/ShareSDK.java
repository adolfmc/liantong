package cn.sharesdk.framework;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.sharesdk.framework.loopshare.LoopSharePasswordListener;
import cn.sharesdk.framework.loopshare.LoopShareResultListener;
import cn.sharesdk.framework.loopshare.MoblinkActionListener;
import cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener;
import cn.sharesdk.framework.loopshare.watermark.WaterMarkListener;
import cn.sharesdk.framework.p094a.p095a.SharePrefrenceUtil;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.commons.ForbThrowable;
import com.mob.commons.dialog.PolicyThrowable;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareSDK {
    public static final String SDK_TAG = "SHARESDK";
    public static final int SDK_VERSION_CODE;
    public static final String SDK_VERSION_NAME = "3.10.3";
    public static final String SHARESDK_MOBLINK_RESTORE = "sharesdk_moblink_restore";

    /* renamed from: a */
    private static ShareSDKCoreThread f2754a = null;

    /* renamed from: b */
    private static boolean f2755b = true;

    /* renamed from: c */
    private static String f2756c;

    /* renamed from: d */
    private static HashMap<String, Object> f2757d;

    /* renamed from: e */
    private static List<HashMap<String, Object>> f2758e;

    /* renamed from: f */
    private static int f2759f;

    @Deprecated
    public static HashMap<String, Object> getCustomDataFromLoopShare() {
        return null;
    }

    @Deprecated
    public static void getFirstQrImage(Context context, ReadQrImageListener readQrImageListener) {
    }

    @Deprecated
    public static Bitmap getQRCodeBitmap(String str, int i, int i2) throws Throwable {
        return null;
    }

    @Deprecated
    public static void makeVideoWaterMark(String str, String str2, String str3, String str4, WaterMarkListener waterMarkListener) {
    }

    @Deprecated
    public static void mobLinkGetMobID(HashMap<String, Object> hashMap, MoblinkActionListener moblinkActionListener) {
    }

    @Deprecated
    public static void prepareLoopShare(LoopShareResultListener loopShareResultListener) {
    }

    @Deprecated
    public static void preparePassWord(HashMap<String, Object> hashMap, String str, LoopSharePasswordListener loopSharePasswordListener) {
    }

    @Deprecated
    public static void readPassWord(boolean z, LoopSharePasswordListener loopSharePasswordListener) {
    }

    /* renamed from: h */
    static /* synthetic */ int m21997h() {
        int i = f2759f;
        f2759f = i + 1;
        return i;
    }

    static {
        int i = 0;
        for (String str : "3.10.3".split("\\.")) {
            i = (i * 100) + Integer.parseInt(str);
        }
        SDK_VERSION_CODE = i;
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK static main catch ", new Object[0]);
        }
    }

    /* renamed from: j */
    private static boolean m21995j() throws Throwable {
        if (!MobSDK.isForb()) {
            switch (MobSDK.isAuth()) {
                case 1:
                case 2:
                    return true;
                default:
                    throw new PolicyThrowable();
            }
        }
        throw new ForbThrowable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public static synchronized void m21994k() throws Throwable {
        synchronized (ShareSDK.class) {
            m21995j();
            if (f2754a == null) {
                ShareSDKCoreThread shareSDKCoreThread = new ShareSDKCoreThread();
                shareSDKCoreThread.mo21687d();
                f2754a = shareSDKCoreThread;
            }
        }
    }

    public static void registerService(Class<? extends Service> cls) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21775a(cls);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK  registerService catch ", new Object[0]);
        }
    }

    public static void unregisterService(Class<? extends Service> cls) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21764b(cls);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK  unregisterService catch ", new Object[0]);
        }
    }

    public static <T extends Service> T getService(Class<T> cls) throws Throwable {
        m21994k();
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return (T) shareSDKCoreThread.m21758c(cls);
        }
        return null;
    }

    public static void registerPlatform(Class<? extends CustomPlatform> cls) throws Throwable {
        m21994k();
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            shareSDKCoreThread.m21755d(cls);
        }
    }

    public static void unregisterPlatform(Class<? extends CustomPlatform> cls) throws Throwable {
        m21994k();
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            shareSDKCoreThread.m21753e(cls);
        }
    }

    public static void setConnTimeout(int i) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21784a(i);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK setConnTimeout catch", new Object[0]);
        }
    }

    public static void setReadTimeout(int i) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21765b(i);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK setReadTimeout catch", new Object[0]);
        }
    }

    public static void removeCookieOnAuthorize(boolean z) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21756c(z);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK removeCookieOnAuthorize catch ", new Object[0]);
        }
    }

    public static void deleteCache() {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21749i();
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK deleteCache catch ", new Object[0]);
        }
    }

    public static Platform[] getPlatformList() {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK getPlatformList catch ", new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21754e();
        }
        return null;
    }

    public static Platform getPlatform(String str) {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK ensureInit getPlatform catch", new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21774a(str);
        }
        return null;
    }

    public static void logDemoEvent(int i, Platform platform) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21782a(i, platform);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK logDemoEvent catch ", new Object[0]);
        }
    }

    public static void logApiEvent(String str, int i) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21773a(str, i);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK logApiEvent catch ", new Object[0]);
        }
    }

    public static void setPlatformDevInfo(String str, HashMap<String, Object> hashMap) {
        try {
            f2756c = str;
            f2757d = hashMap;
            if (!MobSDK.isForb() && MobSDK.isAuth() == 1) {
                m21994k();
                if (f2754a != null) {
                    f2754a.m21771a(str, hashMap);
                }
            } else {
                m21993l();
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK setPlatformDevInfo catch ", new Object[0]);
        }
    }

    public static void setPlatformDevInfos(List<HashMap<String, Object>> list) {
        try {
            f2758e = list;
            if (!MobSDK.isForb() && MobSDK.isAuth() == 1) {
                m21994k();
                if (f2754a != null) {
                    f2754a.m21768a(f2758e);
                }
            } else {
                m21993l();
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK setPlatformDevInfo catch ", new Object[0]);
        }
    }

    /* renamed from: l */
    private static void m21993l() {
        new Thread(new Runnable() { // from class: cn.sharesdk.framework.ShareSDK.1
            @Override // java.lang.Runnable
            public void run() {
                Looper.prepare();
                final Handler handler = new Handler(Looper.myLooper()) { // from class: cn.sharesdk.framework.ShareSDK.1.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        super.handleMessage(message);
                        if (message == null || message.what != 3) {
                            return;
                        }
                        try {
                            ShareSDK.m21994k();
                            if (ShareSDK.f2754a != null) {
                                if (ShareSDK.f2757d == null || ShareSDK.f2757d.size() <= 0) {
                                    ShareSDK.f2754a.m21768a(ShareSDK.f2758e);
                                } else {
                                    ShareSDK.f2754a.m21771a(ShareSDK.f2756c, ShareSDK.f2757d);
                                }
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                };
                handler.post(new Runnable() { // from class: cn.sharesdk.framework.ShareSDK.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        switch (MobSDK.isAuth()) {
                            case 0:
                                ShareSDK.m21997h();
                                if (ShareSDK.f2759f == 90) {
                                    handler.removeCallbacks(this);
                                    return;
                                }
                                SSDKLog.m21740b().m21733d("ShareSDK , Privacy Agreement is not agree, Please agree to the privacy agreement first ", new Object[0]);
                                handler.postDelayed(this, 500L);
                                return;
                            case 1:
                            case 2:
                                if (MobSDK.isForb()) {
                                    return;
                                }
                                handler.removeCallbacks(this);
                                Message obtain = Message.obtain();
                                obtain.what = 3;
                                handler.sendMessage(obtain);
                                return;
                            default:
                                handler.removeCallbacks(this);
                                return;
                        }
                    }
                });
                Looper.loop();
            }
        }).start();
    }

    public static String platformIdToName(int i) {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("ShareSDK platformIdToName catch: " + th, new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21759c(i);
        }
        return null;
    }

    public static int platformNameToId(String str) {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK platformNameToId catch ", new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21763b(str);
        }
        return -1;
    }

    public static boolean isRemoveCookieOnAuthorize() {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("ShareSDK isRemoveCookieOnAuthorize catch: " + th, new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21752f();
        }
        return false;
    }

    public static void closeDebug() {
        f2755b = false;
    }

    public static boolean isDebug() {
        return f2755b;
    }

    public static void setEnableAuthTag(boolean z) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21761b(z);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK setEnableAuthTag catch", new Object[0]);
        }
    }

    public static void setActivity(Activity activity) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21780a(activity);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK setActivity is catch ", new Object[0]);
        }
    }

    public static Activity getAuthActivity() {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK getAuthActivity catch ", new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21785a();
        }
        return null;
    }

    public static void setFBInstagram(boolean z) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21767a(z);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK setFBInstagram catch ", new Object[0]);
        }
    }

    public static boolean isFBInstagram() {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("ShareSDK isFBInstagram catch: " + th, new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21766b();
        }
        return false;
    }

    public static boolean getEnableAuthTag() {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK getEnableAuthTag catch", new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21760c();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m22005a(String str, String str2) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21772a(str, str2);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK copyDevinfo ", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m22009a(int i, int i2) {
        try {
            m21994k();
            if (f2754a != null) {
                f2754a.m21783a(i, i2);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK copyNetworkDevinfo catch ", new Object[0]);
        }
    }

    public static String getDevinfo(String str, String str2) {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK getDevinfo catch ", new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21762b(str, str2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m22008a(int i, String str) {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK getNetworkDevinfo catch ", new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21781a(i, str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m22010a() {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK isNetworkDevinfoRequested is catch ", new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21751g();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static boolean m22003b() throws Throwable {
        m21994k();
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21750h();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m22004a(String str, boolean z, int i, String str2) {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK getShortLink catch ", new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21770a(str, z, i, str2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m22006a(String str) {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK uploadImageToFileServer catch: ", new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21757c(str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m22007a(Bitmap bitmap) {
        try {
            m21994k();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21741a(th, "ShareSDK uploadImageToFileServer catch ", new Object[0]);
        }
        ShareSDKCoreThread shareSDKCoreThread = f2754a;
        if (shareSDKCoreThread != null) {
            return shareSDKCoreThread.m21779a(bitmap);
        }
        return null;
    }

    public static void setCloseGppService(boolean z) {
        try {
            SharePrefrenceUtil.m21961a().m21951b(z);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
    }
}
