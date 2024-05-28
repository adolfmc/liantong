package com.baidu.mapapi;

import com.baidu.mapsdkplatform.comapi.util.MapSDKAdvancedPermission;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PermissionUtils {
    private PermissionUtils() {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapapi.PermissionUtils$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2738a {

        /* renamed from: a */
        private static final PermissionUtils f5844a = new PermissionUtils();
    }

    public static PermissionUtils getInstance() {
        return C2738a.f5844a;
    }

    public boolean isIndoorNaviAuthorized() {
        return MapSDKAdvancedPermission.m18161a().m18156b();
    }

    public boolean isWalkARNaviAuthorized() {
        return MapSDKAdvancedPermission.m18161a().m18155c();
    }
}
