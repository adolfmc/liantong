package com.p319ss.android.downloadlib.utils;

import android.text.TextUtils;
import com.p319ss.android.downloadlib.activity.TTDelegateActivity;
import com.p319ss.android.downloadlib.addownload.C9940x;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.utils.lz */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10059lz {

    /* renamed from: mb */
    private static Map<String, InterfaceC10060mb> f19397mb = Collections.synchronizedMap(new HashMap());

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.utils.lz$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC10060mb {
        /* renamed from: mb */
        void mo6991mb();

        /* renamed from: mb */
        void mo6990mb(String str);
    }

    /* renamed from: mb */
    public static void m6993mb(String[] strArr, InterfaceC10060mb interfaceC10060mb) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        String valueOf = String.valueOf(System.currentTimeMillis());
        m6995mb(valueOf, interfaceC10060mb);
        TTDelegateActivity.m7715mb(valueOf, strArr);
    }

    /* renamed from: mb */
    public static void m6996mb(String str) {
        InterfaceC10060mb m6997b;
        if (TextUtils.isEmpty(str) || (m6997b = m6997b(str)) == null) {
            return;
        }
        m6997b.mo6991mb();
    }

    /* renamed from: mb */
    public static void m6994mb(String str, String str2) {
        InterfaceC10060mb m6997b;
        if (TextUtils.isEmpty(str) || (m6997b = m6997b(str)) == null) {
            return;
        }
        m6997b.mo6990mb(str2);
    }

    /* renamed from: mb */
    private static void m6995mb(String str, InterfaceC10060mb interfaceC10060mb) {
        if (TextUtils.isEmpty(str) || interfaceC10060mb == null) {
            return;
        }
        f19397mb.put(str, interfaceC10060mb);
    }

    /* renamed from: b */
    private static InterfaceC10060mb m6997b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return f19397mb.remove(str);
    }

    /* renamed from: ox */
    public static boolean m6992ox(String str) {
        return C9940x.m7372h().mo7900mb(C9940x.getContext(), str);
    }
}
