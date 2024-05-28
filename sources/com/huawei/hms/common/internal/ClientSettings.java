package com.huawei.hms.common.internal;

import android.app.Activity;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ClientSettings {

    /* renamed from: a */
    private String f11138a;

    /* renamed from: b */
    private String f11139b;

    /* renamed from: c */
    private List<Scope> f11140c;

    /* renamed from: d */
    private String f11141d;

    /* renamed from: e */
    private List<String> f11142e;

    /* renamed from: f */
    private String f11143f;

    /* renamed from: g */
    private SubAppInfo f11144g;

    /* renamed from: h */
    private WeakReference<Activity> f11145h;

    /* renamed from: i */
    private boolean f11146i;

    /* renamed from: j */
    private String f11147j;

    /* renamed from: k */
    private boolean f11148k;

    public ClientSettings(String str, String str2, List<Scope> list, String str3, List<String> list2) {
        this.f11138a = str;
        this.f11139b = str2;
        this.f11140c = list;
        this.f11141d = str3;
        this.f11142e = list2;
    }

    public List<String> getApiName() {
        return this.f11142e;
    }

    public String getAppID() {
        return this.f11141d;
    }

    public String getClientClassName() {
        return this.f11139b;
    }

    public String getClientPackageName() {
        return this.f11138a;
    }

    public Activity getCpActivity() {
        WeakReference<Activity> weakReference = this.f11145h;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public String getCpID() {
        return this.f11143f;
    }

    public String getInnerHmsPkg() {
        return this.f11147j;
    }

    public List<Scope> getScopes() {
        return this.f11140c;
    }

    public SubAppInfo getSubAppID() {
        return this.f11144g;
    }

    public boolean isHasActivity() {
        return this.f11146i;
    }

    public boolean isUseInnerHms() {
        return this.f11148k;
    }

    public void setApiName(List<String> list) {
        this.f11142e = list;
    }

    public void setAppID(String str) {
        this.f11141d = str;
    }

    public void setClientClassName(String str) {
        this.f11139b = str;
    }

    public void setClientPackageName(String str) {
        this.f11138a = str;
    }

    public void setCpActivity(Activity activity) {
        this.f11145h = new WeakReference<>(activity);
        this.f11146i = true;
    }

    public void setCpID(String str) {
        this.f11143f = str;
    }

    public void setInnerHmsPkg(String str) {
        this.f11147j = str;
    }

    public void setScopes(List<Scope> list) {
        this.f11140c = list;
    }

    public void setSubAppId(SubAppInfo subAppInfo) {
        this.f11144g = subAppInfo;
    }

    public void setUseInnerHms(boolean z) {
        this.f11148k = z;
    }

    public ClientSettings(String str, String str2, List<Scope> list, String str3, List<String> list2, SubAppInfo subAppInfo) {
        this(str, str2, list, str3, list2);
        this.f11144g = subAppInfo;
    }
}
