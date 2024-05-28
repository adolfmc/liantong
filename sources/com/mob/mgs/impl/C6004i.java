package com.mob.mgs.impl;

import com.mob.MobSDK;
import com.mob.tools.utils.SharePrefrenceHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.mgs.impl.i */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C6004i {

    /* renamed from: a */
    private static SharePrefrenceHelper f14786a;

    /* renamed from: e */
    private static void m11832e() {
        if (f14786a == null) {
            f14786a = new SharePrefrenceHelper(MobSDK.getContext());
            f14786a.open("gu", 0);
        }
    }

    /* renamed from: a */
    public static synchronized void m11838a(boolean z) {
        synchronized (C6004i.class) {
            m11832e();
            f14786a.putInt("device_switch_local_cache", Integer.valueOf(z ? 1 : 0));
        }
    }

    /* renamed from: a */
    public static synchronized Boolean m11840a() {
        Boolean bool;
        synchronized (C6004i.class) {
            m11832e();
            int i = f14786a.getInt("device_switch_local_cache", -1);
            if (i == 1) {
                bool = true;
            } else {
                bool = i == 0 ? false : null;
            }
        }
        return bool;
    }

    /* renamed from: b */
    public static synchronized void m11835b(boolean z) {
        synchronized (C6004i.class) {
            m11832e();
            f14786a.putInt("device_switch_remote_cache", Integer.valueOf(z ? 1 : 0));
        }
    }

    /* renamed from: b */
    public static synchronized Boolean m11837b() {
        Boolean bool;
        synchronized (C6004i.class) {
            m11832e();
            int i = f14786a.getInt("device_switch_remote_cache", -1);
            if (i == 1) {
                bool = true;
            } else {
                bool = i == 0 ? false : null;
            }
        }
        return bool;
    }

    /* renamed from: a */
    public static synchronized void m11839a(String str) {
        synchronized (C6004i.class) {
            m11832e();
            f14786a.putString("duid_remote_cache", str);
        }
    }

    /* renamed from: c */
    public static synchronized String m11834c() {
        String string;
        synchronized (C6004i.class) {
            m11832e();
            string = f14786a.getString("duid_remote_cache", "");
        }
        return string;
    }

    /* renamed from: b */
    public static synchronized void m11836b(String str) {
        synchronized (C6004i.class) {
            m11832e();
            f14786a.putString("guard_id_remote_cache", str);
        }
    }

    /* renamed from: d */
    public static synchronized String m11833d() {
        String string;
        synchronized (C6004i.class) {
            m11832e();
            string = f14786a.getString("guard_id_remote_cache", "");
        }
        return string;
    }
}
