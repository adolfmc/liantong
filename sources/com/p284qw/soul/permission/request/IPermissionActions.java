package com.p284qw.soul.permission.request;

import android.annotation.TargetApi;
import android.support.annotation.Nullable;
import com.p284qw.soul.permission.bean.Special;
import com.p284qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.p284qw.soul.permission.callbcak.RequestPermissionListener;
import com.p284qw.soul.permission.callbcak.SpecialPermissionListener;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qw.soul.permission.request.IPermissionActions */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface IPermissionActions {
    void goAppDetail(@Nullable GoAppDetailCallBack goAppDetailCallBack);

    @TargetApi(23)
    void requestPermissions(String[] strArr, RequestPermissionListener requestPermissionListener);

    void requestSpecialPermission(Special special, SpecialPermissionListener specialPermissionListener);
}
