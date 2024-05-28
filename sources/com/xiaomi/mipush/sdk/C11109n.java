package com.xiaomi.mipush.sdk;

import android.text.TextUtils;

/* renamed from: com.xiaomi.mipush.sdk.n */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class C11109n {

    /* renamed from: a */
    int f21398a = 0;

    /* renamed from: a */
    String f21399a = "";

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof C11109n)) {
            return false;
        }
        C11109n c11109n = (C11109n) obj;
        return !TextUtils.isEmpty(c11109n.f21399a) && c11109n.f21399a.equals(this.f21399a);
    }
}
