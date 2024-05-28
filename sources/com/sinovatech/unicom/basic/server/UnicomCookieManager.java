package com.sinovatech.unicom.basic.server;

import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UnicomCookieManager {
    public static UserManager userManager = UserManager.getInstance();

    public static void addLocationCookie() {
        try {
            CookieSyncManager.createInstance(App.getInstance().getApplicationContext());
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setCookie("10010.com", "city=" + userManager.getLocateProvinceCode() + "|" + userManager.getLocateCityCode() + "; domain=10010.com");
            StringBuilder sb = new StringBuilder();
            sb.append("MUT_S=");
            sb.append(DeviceHelper.getDeviceOSVersion());
            sb.append("; domain=10010.com");
            cookieManager.setCookie("10010.com", sb.toString());
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addYuFaBuCookie() {
        try {
            if (URLSet.PrePublic_mode) {
                CookieSyncManager.createInstance(App.getInstance().getApplicationContext());
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.setCookie("client.10010.com", URLEnvironmentConfig.getPrepubCookie() + "; domain=client.10010.com");
                CookieSyncManager.getInstance().sync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDevicedCookie() {
        try {
            CookieSyncManager.createInstance(App.getInstance().getApplicationContext());
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setCookie("10010.com", "devicedId=" + DeviceHelper.getUUIDForInterceptor() + "; domain=10010.com");
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addSessionIdCookie(String str) {
        try {
            CookieSyncManager.createInstance(App.getInstance().getApplicationContext());
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setCookie("10010.com", "PvSessionId=" + str + "; domain=10010.com");
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addCVersionCookie() {
        try {
            CookieSyncManager.createInstance(App.getInstance().getApplicationContext());
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setCookie("10010.com", "c_version=" + App.instance.getApplicationContext().getString(2131886969) + "; domain=10010.com");
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addLoginCookie() {
        try {
            if (App.hasLogined()) {
                CookieSyncManager.createInstance(App.getInstance().getApplicationContext());
                CookieManager cookieManager = CookieManager.getInstance();
                ArrayList<Cookie> oKHttpCacheCookies = App.getAsyncHttpClient().getOKHttpCacheCookies();
                UIUtils.logD("OKHttp3-CESHI", "cookies的长度" + oKHttpCacheCookies.size());
                Iterator<Cookie> it = oKHttpCacheCookies.iterator();
                while (it.hasNext()) {
                    Cookie next = it.next();
                    String str = next.name() + "=" + next.value() + "; domain=" + next.domain();
                    cookieManager.setCookie(URLSet.getCookiedomain(), str);
                    UIUtils.logD("addLoginCookie", str + next.path() + "");
                }
                CookieSyncManager.getInstance().sync();
            }
        } catch (Exception unused) {
        }
    }

    public static void addLoginCookieUnlogin() {
        try {
            CookieSyncManager.createInstance(App.getInstance().getApplicationContext());
            CookieManager cookieManager = CookieManager.getInstance();
            Iterator<Cookie> it = App.getAsyncHttpClient().getOKHttpCacheCookies().iterator();
            while (it.hasNext()) {
                Cookie next = it.next();
                cookieManager.setCookie(URLSet.getCookiedomain(), next.name() + "=" + next.value() + "; domain=" + next.domain());
            }
            CookieSyncManager.getInstance().sync();
        } catch (Exception unused) {
        }
    }

    public static void removeSession() {
        CookieSyncManager.createInstance(App.getInstance().getApplicationContext());
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    public static void removeSessionCookieAndResetNecessaryCookie() {
        removeSession();
        addLocationCookie();
        addYuFaBuCookie();
        addDevicedCookie();
        addCVersionCookie();
    }
}
