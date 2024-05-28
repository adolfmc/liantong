package com.huawei.hms.p224ui;

import android.os.Bundle;
import com.huawei.hms.base.p214ui.LogUtil;

/* renamed from: com.huawei.hms.ui.SafeBundle */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SafeBundle {

    /* renamed from: a */
    private final Bundle f11779a;

    public SafeBundle() {
        this(new Bundle());
    }

    public boolean containsKey(String str) {
        try {
            return this.f11779a.containsKey(str);
        } catch (Exception unused) {
            LogUtil.m15163e("SafeBundle", "containsKey exception. key:");
            return false;
        }
    }

    public Object get(String str) {
        try {
            return this.f11779a.get(str);
        } catch (Exception e) {
            LogUtil.m15162e("SafeBundle", "get exception: " + e.getMessage(), true);
            return null;
        }
    }

    public Bundle getBundle() {
        return this.f11779a;
    }

    public int getInt(String str) {
        return getInt(str, 0);
    }

    public String getString(String str) {
        try {
            return this.f11779a.getString(str);
        } catch (Exception e) {
            LogUtil.m15162e("SafeBundle", "getString exception: " + e.getMessage(), true);
            return "";
        }
    }

    public boolean isEmpty() {
        try {
            return this.f11779a.isEmpty();
        } catch (Exception unused) {
            LogUtil.m15163e("SafeBundle", "isEmpty exception");
            return true;
        }
    }

    public int size() {
        try {
            return this.f11779a.size();
        } catch (Exception unused) {
            LogUtil.m15163e("SafeBundle", "size exception");
            return 0;
        }
    }

    public String toString() {
        return this.f11779a.toString();
    }

    public SafeBundle(Bundle bundle) {
        this.f11779a = bundle == null ? new Bundle() : bundle;
    }

    public int getInt(String str, int i) {
        try {
            return this.f11779a.getInt(str, i);
        } catch (Exception e) {
            LogUtil.m15162e("SafeBundle", "getInt exception: " + e.getMessage(), true);
            return i;
        }
    }

    public String getString(String str, String str2) {
        try {
            return this.f11779a.getString(str, str2);
        } catch (Exception e) {
            LogUtil.m15162e("SafeBundle", "getString exception: " + e.getMessage(), true);
            return str2;
        }
    }
}
