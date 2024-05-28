package com.vivo.push;

import android.os.Bundle;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.vivo.push.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class BundleWapper {

    /* renamed from: a */
    private Bundle f20935a;

    /* renamed from: b */
    private String f20936b;

    /* renamed from: c */
    private String f20937c;

    public BundleWapper(String str, String str2, Bundle bundle) {
        this.f20936b = str;
        this.f20937c = str2;
        this.f20935a = bundle;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (android.text.TextUtils.isEmpty(r2) == false) goto L10;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.vivo.push.BundleWapper m5735a(android.content.Intent r5) {
        /*
            r0 = 0
            if (r5 != 0) goto Lb
            java.lang.String r5 = "BundleWapper"
            java.lang.String r1 = "create error : intent is null"
            com.vivo.push.util.LogUtil.m5354a(r5, r1)
            return r0
        Lb:
            android.os.Bundle r1 = r5.getExtras()
            if (r1 == 0) goto L1e
            java.lang.String r2 = "client_pkgname"
            java.lang.String r2 = r1.getString(r2)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L1e
            goto L1f
        L1e:
            r2 = r0
        L1f:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L2c
            java.lang.String r3 = "BundleWapper"
            java.lang.String r4 = "create warning: pkgName is null"
            com.vivo.push.util.LogUtil.m5346b(r3, r4)
        L2c:
            java.lang.String r3 = r5.getPackage()
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 == 0) goto L53
            android.content.ComponentName r3 = r5.getComponent()
            if (r3 != 0) goto L3d
            goto L45
        L3d:
            android.content.ComponentName r5 = r5.getComponent()
            java.lang.String r0 = r5.getPackageName()
        L45:
            r3 = r0
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            if (r5 == 0) goto L53
            java.lang.String r5 = "BundleWapper"
            java.lang.String r0 = "create warning: targetPkgName is null"
            com.vivo.push.util.LogUtil.m5346b(r5, r0)
        L53:
            com.vivo.push.d r5 = new com.vivo.push.d
            r5.<init>(r2, r3, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.BundleWapper.m5735a(android.content.Intent):com.vivo.push.d");
    }

    /* renamed from: a */
    public final void m5733a(String str, int i) {
        if (this.f20935a == null) {
            this.f20935a = new Bundle();
        }
        this.f20935a.putInt(str, i);
    }

    /* renamed from: a */
    public final void m5732a(String str, long j) {
        if (this.f20935a == null) {
            this.f20935a = new Bundle();
        }
        this.f20935a.putLong(str, j);
    }

    /* renamed from: a */
    public final void m5730a(String str, String str2) {
        if (this.f20935a == null) {
            this.f20935a = new Bundle();
        }
        this.f20935a.putString(str, str2);
    }

    /* renamed from: a */
    public final void m5727a(String str, byte[] bArr) {
        if (this.f20935a == null) {
            this.f20935a = new Bundle();
        }
        this.f20935a.putByteArray(str, bArr);
    }

    /* renamed from: a */
    public final void m5731a(String str, Serializable serializable) {
        if (this.f20935a == null) {
            this.f20935a = new Bundle();
        }
        this.f20935a.putSerializable(str, serializable);
    }

    /* renamed from: a */
    public final void m5728a(String str, boolean z) {
        if (this.f20935a == null) {
            this.f20935a = new Bundle();
        }
        this.f20935a.putBoolean(str, z);
    }

    /* renamed from: a */
    public final void m5729a(String str, ArrayList<String> arrayList) {
        if (this.f20935a == null) {
            this.f20935a = new Bundle();
        }
        this.f20935a.putStringArrayList(str, arrayList);
    }

    /* renamed from: a */
    public final String m5734a(String str) {
        Bundle bundle = this.f20935a;
        if (bundle == null) {
            return null;
        }
        return bundle.getString(str);
    }

    /* renamed from: b */
    public final int m5724b(String str, int i) {
        Bundle bundle = this.f20935a;
        return bundle == null ? i : bundle.getInt(str, i);
    }

    /* renamed from: b */
    public final byte[] m5725b(String str) {
        Bundle bundle = this.f20935a;
        if (bundle == null) {
            return null;
        }
        return bundle.getByteArray(str);
    }

    /* renamed from: c */
    public final ArrayList<String> m5722c(String str) {
        Bundle bundle = this.f20935a;
        if (bundle == null) {
            return null;
        }
        return bundle.getStringArrayList(str);
    }

    /* renamed from: b */
    public final long m5723b(String str, long j) {
        Bundle bundle = this.f20935a;
        return bundle == null ? j : bundle.getLong(str, j);
    }

    /* renamed from: d */
    public final Serializable m5721d(String str) {
        Bundle bundle = this.f20935a;
        if (bundle == null) {
            return null;
        }
        return bundle.getSerializable(str);
    }

    /* renamed from: e */
    public final boolean m5720e(String str) {
        Bundle bundle = this.f20935a;
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean(str, false);
    }

    /* renamed from: a */
    public final String m5736a() {
        return this.f20936b;
    }

    /* renamed from: b */
    public final Bundle m5726b() {
        return this.f20935a;
    }
}
