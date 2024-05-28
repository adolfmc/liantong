package com.android.client.asm.core;

import com.gmrz.android.client.utils.Logger;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GetInfoParams {

    /* renamed from: a */
    private static final String f4047a = "GetInfoParams";

    /* renamed from: b */
    private String f4048b;

    /* renamed from: c */
    private String f4049c;

    /* renamed from: d */
    private Boolean f4050d;

    /* renamed from: e */
    private String f4051e;

    /* renamed from: f */
    private String f4052f;

    /* renamed from: g */
    private String f4053g;

    /* renamed from: h */
    private Boolean f4054h;

    /* renamed from: i */
    private String f4055i;
    public boolean mIdentifyForReg;
    public boolean strictMode;

    public static String getDomainName(String str) {
        String host;
        String str2;
        String str3 = null;
        if (str != null) {
            try {
                host = new URL(str).getHost();
            } catch (Exception e) {
                e = e;
            }
            try {
                return host.startsWith("www.") ? host.substring(4) : host;
            } catch (Exception e2) {
                str3 = str2;
                e = e2;
                Logger.m15891e(f4047a, "Failed to parse URL", e);
                return str3;
            }
        }
        return str3;
    }

    public static String normalizeURL(String str) {
        MalformedURLException e;
        URL url;
        String str2;
        if (str != null) {
            try {
                url = new URL(str);
                str2 = url.getProtocol() + "://" + url.getHost();
            } catch (MalformedURLException e2) {
                e = e2;
            }
            try {
                if (url.getPort() != -1) {
                    str2 = str2 + ":" + url.getPort();
                }
                if (url.getPath() == null && url.getPath().length() == 0) {
                    return str2;
                }
                return str2 + url.getPath();
            } catch (MalformedURLException e3) {
                e = e3;
                Logger.m15891e(f4047a, "Failed to parse URL", e);
                return null;
            }
        }
        return null;
    }

    public static String getLogoPath(String str, String str2) {
        if (str != null) {
            return str + str2;
        }
        return null;
    }

    public GetInfoParams(String str, String str2, Boolean bool, String str3, String str4, Boolean bool2, String str5) {
        this.f4048b = null;
        this.f4049c = null;
        this.f4050d = Boolean.FALSE;
        this.f4051e = null;
        this.f4052f = null;
        this.f4053g = null;
        this.f4054h = Boolean.FALSE;
        this.f4055i = null;
        this.mIdentifyForReg = false;
        this.strictMode = false;
        this.f4048b = str;
        this.f4049c = getLogoPath(str2, "/ostp/info");
        this.f4052f = getDomainName(str2);
        this.f4050d = bool;
        this.f4051e = str3;
        this.f4054h = bool2;
        this.f4053g = str4;
        this.f4055i = str5;
    }

    public GetInfoParams(String str, String str2, String str3) {
        this.f4048b = null;
        this.f4049c = null;
        this.f4050d = Boolean.FALSE;
        this.f4051e = null;
        this.f4052f = null;
        this.f4053g = null;
        this.f4054h = Boolean.FALSE;
        this.f4055i = null;
        this.mIdentifyForReg = false;
        this.strictMode = false;
        this.f4048b = str;
        this.f4049c = getLogoPath(str2, "/ostp/info");
        this.f4052f = getDomainName(str2);
        this.f4055i = str3;
    }

    public GetInfoParams() {
        this.f4048b = null;
        this.f4049c = null;
        this.f4050d = Boolean.FALSE;
        this.f4051e = null;
        this.f4052f = null;
        this.f4053g = null;
        this.f4054h = Boolean.FALSE;
        this.f4055i = null;
        this.mIdentifyForReg = false;
        this.strictMode = false;
        this.f4048b = null;
        this.f4049c = null;
        this.f4052f = null;
    }

    public GetInfoParams(String str) {
        this.f4048b = null;
        this.f4049c = null;
        this.f4050d = Boolean.FALSE;
        this.f4051e = null;
        this.f4052f = null;
        this.f4053g = null;
        this.f4054h = Boolean.FALSE;
        this.f4055i = null;
        this.mIdentifyForReg = false;
        this.strictMode = false;
        this.f4048b = null;
        this.f4049c = null;
        this.f4052f = null;
        this.f4055i = str;
    }

    public String getTransText() {
        return this.f4048b;
    }

    public void setTransText(String str) {
        this.f4048b = str;
    }

    public String getRPLogoPath() {
        return this.f4049c;
    }

    public void setRPLogoPath(String str) {
        this.f4049c = str;
    }

    public String getDomainName() {
        return this.f4052f;
    }

    public void setDomainName(String str) {
        this.f4052f = str;
    }

    public Boolean getIsButtonPresent() {
        return this.f4050d;
    }

    public void setIsButtonPresent(Boolean bool) {
        this.f4050d = bool;
    }

    public String getButtonText() {
        return this.f4051e;
    }

    public void setButtonText(String str) {
        this.f4051e = str;
    }

    public String getChangeTokenButtonText() {
        return this.f4053g;
    }

    public void setChangeTokenButtonText(String str) {
        this.f4053g = str;
    }

    public Boolean getChangeTokenButtonIsEnabled() {
        return this.f4054h;
    }

    public void setChangeTokenButtonIsEnabled(Boolean bool) {
        this.f4054h = bool;
    }

    public String getCustomUIJson() {
        return this.f4055i;
    }

    public void setCustomUIJson(String str) {
        this.f4055i = str;
    }
}
