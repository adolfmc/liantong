package cn.sharesdk.framework.p094a.p095a;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.tools.utils.SharePrefrenceHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a.a.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SharePrefrenceUtil {

    /* renamed from: b */
    private static SharePrefrenceUtil f2776b;

    /* renamed from: a */
    private SharePrefrenceHelper f2777a = new SharePrefrenceHelper(MobSDK.getContext());

    private SharePrefrenceUtil() {
        this.f2777a.open("share_sdk", 1);
    }

    /* renamed from: a */
    public static SharePrefrenceUtil m21961a() {
        if (f2776b == null) {
            f2776b = new SharePrefrenceUtil();
        }
        return f2776b;
    }

    /* renamed from: b */
    public long m21954b() {
        return this.f2777a.getLong("service_time");
    }

    /* renamed from: c */
    public boolean m21950c() {
        String string = this.f2777a.getString("upload_device_info");
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: d */
    public boolean m21947d() {
        String string = this.f2777a.getString("upload_user_info");
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: e */
    public boolean m21944e() {
        String string = this.f2777a.getString("trans_short_link");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: f */
    public int m21942f() {
        String string = this.f2777a.getString("upload_share_content");
        if ("true".equals(string)) {
            return 1;
        }
        return "false".equals(string) ? -1 : 0;
    }

    /* renamed from: g */
    public boolean m21940g() {
        String string = this.f2777a.getString("open_login_plus");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: a */
    public void m21959a(String str) {
        this.f2777a.putString("trans_short_link", str);
    }

    /* renamed from: b */
    public void m21952b(String str) {
        this.f2777a.putString("upload_device_info", str);
    }

    /* renamed from: c */
    public void m21949c(String str) {
        this.f2777a.putString("upload_user_info", str);
    }

    /* renamed from: d */
    public void m21946d(String str) {
        this.f2777a.putString("upload_share_content", str);
    }

    /* renamed from: e */
    public void m21943e(String str) {
        this.f2777a.putString("open_login_plus", str);
    }

    /* renamed from: f */
    public void m21941f(String str) {
        this.f2777a.putString("open_sina_link_card", str);
    }

    /* renamed from: g */
    public void m21939g(String str) {
        SharePrefrenceHelper sharePrefrenceHelper = this.f2777a;
        sharePrefrenceHelper.putString("buffered_snsconf_" + MobSDK.getAppkey(), str);
    }

    /* renamed from: h */
    public String m21938h() {
        SharePrefrenceHelper sharePrefrenceHelper = this.f2777a;
        return sharePrefrenceHelper.getString("buffered_snsconf_" + MobSDK.getAppkey());
    }

    /* renamed from: a */
    public void m21955a(boolean z) {
        this.f2777a.putBoolean("gpp_ver_sent", Boolean.valueOf(z));
    }

    /* renamed from: b */
    public void m21951b(boolean z) {
        this.f2777a.putBoolean("no_use_gpp", Boolean.valueOf(z));
    }

    /* renamed from: a */
    public void m21960a(long j) {
        this.f2777a.putLong("device_time", Long.valueOf(j));
    }

    /* renamed from: i */
    public Long m21936i() {
        return Long.valueOf(this.f2777a.getLong("device_time"));
    }

    /* renamed from: c */
    public void m21948c(boolean z) {
        this.f2777a.putBoolean("connect_server", Boolean.valueOf(z));
    }

    /* renamed from: j */
    public boolean m21934j() {
        return this.f2777a.getBoolean("connect_server");
    }

    /* renamed from: b */
    public void m21953b(long j) {
        this.f2777a.putLong("connect_server_time", Long.valueOf(j));
    }

    /* renamed from: k */
    public Long m21932k() {
        return Long.valueOf(this.f2777a.getLong("connect_server_time"));
    }

    /* renamed from: d */
    public void m21945d(boolean z) {
        this.f2777a.putBoolean("sns_info_buffered", Boolean.valueOf(z));
    }

    /* renamed from: l */
    public boolean m21930l() {
        return this.f2777a.getBoolean("sns_info_buffered");
    }

    /* renamed from: h */
    public boolean m21937h(String str) {
        return this.f2777a.getBoolean(str);
    }

    /* renamed from: a */
    public void m21957a(String str, Long l) {
        this.f2777a.putLong(str, l);
    }

    /* renamed from: i */
    public long m21935i(String str) {
        return this.f2777a.getLong(str);
    }

    /* renamed from: a */
    public void m21958a(String str, int i) {
        this.f2777a.putInt(str, Integer.valueOf(i));
    }

    /* renamed from: j */
    public int m21933j(String str) {
        return this.f2777a.getInt(str);
    }

    /* renamed from: a */
    public void m21956a(String str, Object obj) {
        this.f2777a.put(str, obj);
    }

    /* renamed from: k */
    public Object m21931k(String str) {
        return this.f2777a.get(str);
    }
}
