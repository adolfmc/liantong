package com.p284qw.soul.permission.callbcak;

import com.p284qw.soul.permission.bean.Special;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qw.soul.permission.callbcak.SpecialPermissionListener */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface SpecialPermissionListener {
    void onDenied(Special special);

    void onGranted(Special special);
}
