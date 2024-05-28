package cn.sharesdk.framework;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import cn.sharesdk.framework.p094a.p096b.ShareEvent;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.utils.UIHandler;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class Platform {
    public static final int ACTION_AUTHORIZING = 1;
    protected static final int ACTION_CUSTOMER = 655360;
    public static final int ACTION_FOLLOWING_USER = 6;
    protected static final int ACTION_GETTING_BILATERAL_LIST = 10;
    protected static final int ACTION_GETTING_FOLLOWER_LIST = 11;
    public static final int ACTION_GETTING_FRIEND_LIST = 2;
    public static final int ACTION_SENDING_DIRECT_MESSAGE = 5;
    public static final int ACTION_SHARE = 9;
    public static final int ACTION_TIMELINE = 7;
    public static final int ACTION_USER_INFOR = 8;
    public static final int CUSTOMER_ACTION_MASK = 65535;
    public static final int DY_MIXFILE = 23;
    public static final int GGP_REFUSE = 21;
    public static final int INSTAGRAM_FRIEND = 13;
    public static final int KAKAO_COMMERCE_TEMPLATE = 18;
    public static final int KAKAO_CUSTOM_TEMPLATE = 20;
    public static final int KAKAO_FEED_TEMPLATE = 16;
    public static final int KAKAO_TEXT_TEMPLATE = 19;
    public static final int KAKAO_URL_TEMPLATE = 17;
    public static final int OPEN_QQMINIPROGRAM = 22;
    public static final int OPEN_WXMINIPROGRAM = 12;
    public static final int QQ_MINI_PROGRAM = 15;
    public static final int SHARE_APPS = 7;
    public static final int SHARE_DYIM_IMG = 24;
    public static final int SHARE_DYIM_WEBPAGE = 25;
    public static final int SHARE_EMOJI = 9;
    public static final int SHARE_FILE = 8;
    public static final int SHARE_IMAGE = 2;
    public static final int SHARE_LINKCARD = 14;
    public static final int SHARE_MUSIC = 5;
    public static final int SHARE_TEXT = 1;
    public static final int SHARE_VIDEO = 6;
    public static final int SHARE_WEBPAGE = 4;
    public static final int SHARE_WXMINIPROGRAM = 11;
    public static final int SHARE_ZHIFUBAO = 10;

    /* renamed from: a */
    private PlatformImpl f2730a = new PlatformImpl(this);

    /* renamed from: db */
    public final PlatformDb f2731db = this.f2730a.m21804g();
    public PlatformActionListener listener = this.f2730a.m21802i();
    protected final PlatformActionListener uIListener = new PlatformActionListener() { // from class: cn.sharesdk.framework.Platform.1
        @Override // cn.sharesdk.framework.PlatformActionListener
        public void onError(final Platform platform, final int i, final Throwable th) {
            if (Platform.this.listener == null) {
                return;
            }
            try {
                if (AppUtils.m21720a()) {
                    Platform.this.listener.onError(platform, i, th);
                } else {
                    UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: cn.sharesdk.framework.Platform.1.1
                        @Override // android.os.Handler.Callback
                        public boolean handleMessage(Message message) {
                            Platform.this.listener.onError(platform, i, th);
                            return false;
                        }
                    });
                }
            } catch (Throwable th2) {
                SSDKLog.m21740b().m21742a(th2);
            }
        }

        @Override // cn.sharesdk.framework.PlatformActionListener
        public void onComplete(final Platform platform, final int i, final HashMap<String, Object> hashMap) {
            if (Platform.this.listener == null) {
                return;
            }
            try {
                if (AppUtils.m21720a()) {
                    Platform.this.listener.onComplete(platform, i, hashMap);
                } else {
                    UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: cn.sharesdk.framework.Platform.1.2
                        @Override // android.os.Handler.Callback
                        public boolean handleMessage(Message message) {
                            Platform.this.listener.onComplete(platform, i, hashMap);
                            return false;
                        }
                    });
                }
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
            }
        }

        @Override // cn.sharesdk.framework.PlatformActionListener
        public void onCancel(final Platform platform, final int i) {
            if (Platform.this.listener == null) {
                return;
            }
            try {
                if (AppUtils.m21720a()) {
                    Platform.this.listener.onCancel(platform, i);
                } else {
                    UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: cn.sharesdk.framework.Platform.1.3
                        @Override // android.os.Handler.Callback
                        public boolean handleMessage(Message message) {
                            Platform.this.listener.onCancel(platform, i);
                            return false;
                        }
                    });
                }
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean checkAuthorize(int i, Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void doAuthorize(String[] strArr);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void doShare(ShareParams shareParams);

    protected abstract HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ShareEvent.C1746a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void follow(String str);

    protected abstract HashMap<String, Object> getBilaterals(int i, int i2, String str);

    protected abstract HashMap<String, Object> getFollowers(int i, int i2, String str);

    protected abstract HashMap<String, Object> getFollowings(int i, int i2, String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void getFriendList(int i, int i2, String str);

    public abstract String getName();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getPlatformId();

    public abstract int getVersion();

    public abstract boolean hasShareCallback();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void initDevInfo(String str);

    public boolean isClientValid() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void setNetworkDevinfo();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void timeline(int i, int i2, String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void userInfor(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m22015a() {
        this.f2730a.m21824a(false);
        this.f2730a.m21828a(getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void copyDevinfo(String str, String str2) {
        ShareSDK.m22005a(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void copyNetworkDevinfo(int i, int i2) {
        ShareSDK.m22009a(i, i2);
    }

    public String getDevinfo(String str) {
        return getDevinfo(getName(), str);
    }

    public String getDevinfo(String str, String str2) {
        return ShareSDK.getDevinfo(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getNetworkDevinfo(String str, String str2) {
        return getNetworkDevinfo(getPlatformId(), str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getNetworkDevinfo(int i, String str, String str2) {
        return this.f2730a.m21837a(i, str, str2);
    }

    public int getId() {
        return this.f2730a.m21841a();
    }

    public int getSortId() {
        return this.f2730a.m21822b();
    }

    public void setPlatformActionListener(PlatformActionListener platformActionListener) {
        this.f2730a.m21834a(platformActionListener);
    }

    public PlatformActionListener getPlatformActionListener() {
        return this.f2730a.m21813c();
    }

    public boolean isAuthValid() {
        return this.f2730a.m21808d();
    }

    public void SSOSetting(boolean z) {
        this.f2730a.m21824a(z);
    }

    public boolean isSSODisable() {
        return this.f2730a.m21806e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean m22014b() {
        return this.f2730a.m21805f();
    }

    public void authorize() {
        authorize(null);
    }

    public void authorize(String[] strArr) {
        this.f2730a.m21823a(strArr);
    }

    public void subscribeAuth(ShareParams shareParams) {
        this.f2730a.m21835a(shareParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void innerAuthorize(int i, Object obj) {
        this.f2730a.m21838a(i, obj);
    }

    public void share(ShareParams shareParams) {
        this.f2730a.m21819b(shareParams);
    }

    public void followFriend(String str) {
        this.f2730a.m21815b(str);
    }

    public void getTimeLine(String str, int i, int i2) {
        this.f2730a.m21827a(str, i, i2);
    }

    public void showUser(String str) {
        this.f2730a.m21809c(str);
    }

    public void listFriend(int i, int i2, String str) {
        this.f2730a.m21839a(i, i2, str);
    }

    public void customerProtocol(String str, String str2, short s, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        this.f2730a.m21826a(str, str2, s, hashMap, hashMap2);
    }

    public void afterRegister(int i, Object obj) {
        this.f2730a.m21820b(i, obj);
    }

    public PlatformDb getDb() {
        return this.f2731db;
    }

    public void removeAccount(boolean z) {
        this.f2730a.m21803h();
        ShareSDK.removeCookieOnAuthorize(z);
    }

    public String getShortLintk(String str, boolean z) {
        return this.f2730a.m21825a(str, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String uploadImageToFileServer(String str) {
        return this.f2730a.m21807d(str);
    }

    protected String uploadImageToFileServer(Bitmap bitmap) {
        return this.f2730a.m21836a(bitmap);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class ShareParams extends InnerShareParams {
        public ShareParams() {
        }

        public ShareParams(HashMap<String, Object> hashMap) {
            super(hashMap);
        }

        public ShareParams(String str) {
            super(str);
        }
    }
}
