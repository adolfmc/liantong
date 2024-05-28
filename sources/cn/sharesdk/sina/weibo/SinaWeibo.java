package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p094a.p096b.ShareEvent;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SinaWeibo extends Platform {
    public static final String NAME = "SinaWeibo";
    public static volatile boolean initFlag;

    /* renamed from: a */
    private String f2984a;

    /* renamed from: b */
    private String f2985b;

    /* renamed from: c */
    private String f2986c;

    /* renamed from: d */
    private boolean f2987d;

    @Override // cn.sharesdk.framework.Platform
    public int getPlatformId() {
        return 1;
    }

    @Override // cn.sharesdk.framework.Platform
    public int getVersion() {
        return 1;
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean hasShareCallback() {
        return true;
    }

    @Override // cn.sharesdk.framework.Platform
    public void initDevInfo(String str) {
        this.f2984a = getDevinfo("AppKey");
        this.f2985b = getDevinfo("AppSecret");
        this.f2986c = getDevinfo("RedirectUrl");
        this.f2987d = !"false".equals(getDevinfo("ShareByAppClient"));
    }

    @Override // cn.sharesdk.framework.Platform
    public String getName() {
        return NAME;
    }

    @Override // cn.sharesdk.framework.Platform
    public void setNetworkDevinfo() {
        this.f2984a = getNetworkDevinfo("app_key", "AppKey");
        this.f2985b = getNetworkDevinfo("app_secret", "AppSecret");
        this.f2986c = getNetworkDevinfo("redirect_uri", "RedirectUrl");
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean isClientValid() {
        return Weibo.m21649a(this).m21638b();
    }

    /* renamed from: c */
    private boolean m21660c() {
        if (TextUtils.isEmpty(getDb().get("refresh_token"))) {
            return false;
        }
        Weibo m21649a = Weibo.m21649a(this);
        m21649a.m21643a(this.f2984a, this.f2985b);
        m21649a.m21644a(this.f2986c);
        return m21649a.m21652a();
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean checkAuthorize(int i, Object obj) {
        Weibo m21649a = Weibo.m21649a(this);
        m21649a.m21631c(this.f2731db.getToken());
        m21649a.m21643a(this.f2984a, this.f2985b);
        m21649a.m21644a(this.f2986c);
        m21649a.m21630d();
        if (i == 9 || isAuthValid() || m21660c()) {
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    @Override // cn.sharesdk.framework.Platform
    public void doAuthorize(String[] strArr) {
        final Weibo m21649a = Weibo.m21649a(this);
        m21649a.m21643a(this.f2984a, this.f2985b);
        m21649a.m21644a(this.f2986c);
        m21649a.m21639a(strArr);
        m21649a.m21647a(new AuthorizeListener() { // from class: cn.sharesdk.sina.weibo.SinaWeibo.1
            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onComplete(Bundle bundle) {
                long j;
                String string = bundle.getString("uid");
                String string2 = bundle.getString("access_token");
                String string3 = bundle.getString("expires_in");
                String string4 = bundle.getString("refresh_token");
                if (bundle.containsKey("username")) {
                    SinaWeibo.this.f2731db.put("nickname", bundle.getString("userName"));
                }
                SinaWeibo.this.f2731db.putToken(string2);
                try {
                    j = ResHelper.parseLong(string3);
                } catch (Throwable unused) {
                    j = 0;
                }
                SinaWeibo.this.f2731db.putExpiresIn(j);
                SinaWeibo.this.f2731db.put("refresh_token", string4);
                SinaWeibo.this.f2731db.putUserId(string);
                m21649a.m21631c(string2);
                SinaWeibo.this.afterRegister(1, null);
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onError(Throwable th) {
                if (SinaWeibo.this.listener != null) {
                    SinaWeibo.this.listener.onError(SinaWeibo.this, 1, th);
                }
            }

            @Override // cn.sharesdk.framework.authorize.AuthorizeListener
            public void onCancel() {
                if (SinaWeibo.this.listener != null) {
                    SinaWeibo.this.listener.onCancel(SinaWeibo.this, 1);
                }
            }
        }, isSSODisable());
    }

    @Override // cn.sharesdk.framework.Platform
    public void follow(String str) {
        try {
            HashMap<String, Object> m21625e = Weibo.m21649a(this).m21625e(str);
            if (m21625e == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 6, new Throwable());
                }
            } else if (m21625e.containsKey("error_code") && ((Integer) m21625e.get("error_code")).intValue() != 0) {
                if (this.listener != null) {
                    this.listener.onError(this, 6, new Throwable(new Hashon().fromHashMap(m21625e)));
                }
            } else if (this.listener != null) {
                this.listener.onComplete(this, 6, m21625e);
            }
        } catch (Throwable th) {
            this.listener.onError(this, 6, th);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void timeline(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.get("nickname");
        }
        if (TextUtils.isEmpty(str)) {
            if (this.listener != null) {
                this.listener.onError(this, 7, new RuntimeException("Both weibo id and screen_name are null"));
                return;
            }
            return;
        }
        try {
            HashMap<String, Object> m21651a = Weibo.m21649a(this).m21651a(i, i2, str);
            if (m21651a == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 7, new Throwable());
                }
            } else if (m21651a.containsKey("error_code") && ((Integer) m21651a.get("error_code")).intValue() != 0) {
                if (this.listener != null) {
                    this.listener.onError(this, 7, new Throwable(new Hashon().fromHashMap(m21651a)));
                }
            } else if (this.listener != null) {
                this.listener.onComplete(this, 7, m21651a);
            }
        } catch (Throwable th) {
            this.listener.onError(this, 7, th);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void userInfor(String str) {
        boolean z;
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.getUserId();
            z = true;
        } else {
            z = false;
        }
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.get("nickname");
            z = true;
        }
        if (TextUtils.isEmpty(str)) {
            if (this.listener != null) {
                this.listener.onError(this, 8, new RuntimeException("Both weibo id and screen_name are null"));
                return;
            }
            return;
        }
        try {
            HashMap<String, Object> m21627d = Weibo.m21649a(this).m21627d(str);
            if (m21627d == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable());
                }
            } else if (m21627d.containsKey("error_code") && ((Integer) m21627d.get("error_code")).intValue() != 0) {
                if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(m21627d)));
                }
            } else {
                if (z) {
                    this.f2731db.putUserId(String.valueOf(m21627d.get("id")));
                    this.f2731db.put("nickname", String.valueOf(m21627d.get("screen_name")));
                    this.f2731db.put("icon", String.valueOf(m21627d.get("avatar_hd")));
                    if (String.valueOf(m21627d.get("verified")).equals("true")) {
                        this.f2731db.put("secretType", "1");
                    } else {
                        this.f2731db.put("secretType", "0");
                    }
                    this.f2731db.put("secret", String.valueOf(m21627d.get("verified_reason")));
                    String valueOf = String.valueOf(m21627d.get("gender"));
                    if (valueOf.equals("m")) {
                        this.f2731db.put("gender", "0");
                    } else if (valueOf.equals("f")) {
                        this.f2731db.put("gender", "1");
                    } else {
                        this.f2731db.put("gender", "2");
                    }
                    this.f2731db.put("snsUserUrl", "http://weibo.com/" + String.valueOf(m21627d.get("profile_url")));
                    this.f2731db.put("resume", String.valueOf(m21627d.get("description")));
                    this.f2731db.put("followerCount", String.valueOf(m21627d.get("followers_count")));
                    this.f2731db.put("favouriteCount", String.valueOf(m21627d.get("friends_count")));
                    this.f2731db.put("shareCount", String.valueOf(m21627d.get("statuses_count")));
                    this.f2731db.put("snsregat", String.valueOf(ResHelper.dateToLong(String.valueOf(m21627d.get("created_at")))));
                }
                if (this.listener != null) {
                    this.listener.onComplete(this, 8, m21627d);
                }
            }
        } catch (Throwable th) {
            this.listener.onError(this, 8, th);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void getFriendList(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.get("nickname");
        }
        if (TextUtils.isEmpty(str)) {
            if (this.listener != null) {
                this.listener.onError(this, 2, new RuntimeException("Both weibo id and screen_name are null"));
                return;
            }
            return;
        }
        try {
            HashMap<String, Object> m21637b = Weibo.m21649a(this).m21637b(i, i2, str);
            if (m21637b == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable());
                }
            } else if (m21637b.containsKey("error_code") && ((Integer) m21637b.get("error_code")).intValue() != 0) {
                if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable(new Hashon().fromHashMap(m21637b)));
                }
            } else if (this.listener != null) {
                this.listener.onComplete(this, 2, m21637b);
            }
        } catch (Throwable th) {
            this.listener.onError(this, 2, th);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        try {
            HashMap<String, Object> m21642a = Weibo.m21649a(this).m21642a(str, str2, hashMap, hashMap2);
            if (m21642a != null && m21642a.size() > 0) {
                if (m21642a.containsKey("error_code") && ((Integer) m21642a.get("error_code")).intValue() != 0) {
                    if (this.listener != null) {
                        this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(m21642a)));
                        return;
                    }
                    return;
                } else if (this.listener != null) {
                    this.listener.onComplete(this, i, m21642a);
                    return;
                } else {
                    return;
                }
            }
            if (this.listener != null) {
                this.listener.onError(this, i, new Throwable());
            }
        } catch (Throwable th) {
            this.listener.onError(this, i, th);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void doShare(Platform.ShareParams shareParams) {
        SSDKLog m21740b = SSDKLog.m21740b();
        m21740b.m21744a("Weibo start Share with Appkey:" + this.f2984a + ",appSecret:" + this.f2985b + ",redirectUrl:" + this.f2986c, new Object[0]);
        SSDKLog m21740b2 = SSDKLog.m21740b();
        StringBuilder sb = new StringBuilder();
        sb.append("Weibo ShareParams:");
        sb.append(shareParams.toString());
        m21740b2.m21744a(sb.toString(), new Object[0]);
        Weibo m21649a = Weibo.m21649a(this);
        m21649a.m21643a(this.f2984a, this.f2985b);
        if (this.f2987d && m21649a.m21638b()) {
            try {
                m21649a.m21650a(shareParams, this.listener);
                return;
            } catch (Throwable th) {
                this.listener.onError(this, 9, th);
                return;
            }
        }
        try {
            m21649a.m21636b(shareParams, this.listener);
        } catch (Throwable th2) {
            this.listener.onError(this, 9, th2);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public ShareEvent.C1746a filterShareContent(Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        ShareEvent.C1746a c1746a = new ShareEvent.C1746a();
        c1746a.f2816b = shareParams.getText();
        if (hashMap != null) {
            c1746a.f2815a = String.valueOf(hashMap.get("id"));
            c1746a.f2818d.add(String.valueOf(hashMap.get("original_pic")));
            c1746a.f2821g = hashMap;
        }
        return c1746a;
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> getFollowings(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.get("nickname");
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            HashMap<String, Object> m21637b = Weibo.m21649a(this).m21637b(i, i2, str);
            if (m21637b == null || m21637b.containsKey("error_code")) {
                return null;
            }
            m21637b.put("current_cursor", Integer.valueOf(i2));
            return filterFriendshipInfo(2, m21637b);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return null;
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.get("nickname");
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            HashMap<String, Object> m21633c = Weibo.m21649a(this).m21633c(i, i2, str);
            if (m21633c == null || m21633c.containsKey("error_code")) {
                return null;
            }
            m21633c.put("page_count", Integer.valueOf(i));
            m21633c.put("current_cursor", Integer.valueOf(i2));
            return filterFriendshipInfo(10, m21633c);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return null;
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> getFollowers(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.f2731db.get("nickname");
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            HashMap<String, Object> m21629d = Weibo.m21649a(this).m21629d(i, i2, str);
            if (m21629d == null || m21629d.containsKey("error_code")) {
                return null;
            }
            m21629d.put("current_cursor", Integer.valueOf(i2));
            return filterFriendshipInfo(11, m21629d);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return null;
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        Object obj;
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        String str2;
        HashMap<String, Object> hashMap2 = new HashMap<>();
        if (i == 2) {
            hashMap2.put("type", "FOLLOWING");
        } else {
            switch (i) {
                case 10:
                    hashMap2.put("type", "FRIENDS");
                    break;
                case 11:
                    hashMap2.put("type", "FOLLOWERS");
                    break;
                default:
                    return null;
            }
        }
        hashMap2.put("snsplat", Integer.valueOf(getPlatformId()));
        hashMap2.put("snsuid", this.f2731db.getUserId());
        int parseInt = Integer.parseInt(String.valueOf(hashMap.get("current_cursor")));
        int parseInt2 = Integer.parseInt(String.valueOf(hashMap.get("total_number")));
        if (parseInt2 == 0 || (obj = hashMap.get("users")) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) obj;
        if (arrayList2.size() <= 0) {
            return null;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                HashMap hashMap4 = new HashMap();
                hashMap4.put("snsuid", String.valueOf(hashMap3.get("id")));
                hashMap4.put("nickname", String.valueOf(hashMap3.get("screen_name")));
                hashMap4.put("icon", String.valueOf(hashMap3.get("avatar_hd")));
                if (String.valueOf(hashMap3.get("verified")).equals("true")) {
                    hashMap4.put("secretType", "1");
                } else {
                    hashMap4.put("secretType", "0");
                }
                hashMap4.put("secret", String.valueOf(hashMap3.get("verified_reason")));
                String valueOf = String.valueOf(hashMap3.get("gender"));
                if (valueOf.equals("m")) {
                    hashMap4.put("gender", "0");
                } else if (valueOf.equals("f")) {
                    hashMap4.put("gender", "1");
                } else {
                    hashMap4.put("gender", "2");
                }
                hashMap4.put("snsUserUrl", "http://weibo.com/" + String.valueOf(hashMap3.get("profile_url")));
                hashMap4.put("resume", String.valueOf(hashMap3.get("description")));
                hashMap4.put("followerCount", String.valueOf(hashMap3.get("followers_count")));
                hashMap4.put("favouriteCount", String.valueOf(hashMap3.get("friends_count")));
                hashMap4.put("shareCount", String.valueOf(hashMap3.get("statuses_count")));
                hashMap4.put("snsregat", String.valueOf(ResHelper.dateToLong(String.valueOf(hashMap3.get("created_at")))));
                arrayList.add(hashMap4);
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        if (10 == i) {
            int i2 = parseInt + 1;
            if (((Integer) hashMap.get("page_count")).intValue() * i2 >= parseInt2) {
                sb2 = new StringBuilder();
                sb2.append(parseInt);
                str2 = "_true";
            } else {
                sb2 = new StringBuilder();
                sb2.append(i2);
                str2 = "_false";
            }
            sb2.append(str2);
            hashMap2.put("nextCursor", sb2.toString());
        } else {
            int size = parseInt + arrayList.size();
            if (size >= parseInt2) {
                sb = new StringBuilder();
                sb.append(parseInt2);
                str = "_true";
            } else {
                sb = new StringBuilder();
                sb.append(size);
                str = "_false";
            }
            sb.append(str);
            hashMap2.put("nextCursor", sb.toString());
        }
        hashMap2.put("list", arrayList);
        return hashMap2;
    }
}
