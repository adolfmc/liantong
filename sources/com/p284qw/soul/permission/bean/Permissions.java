package com.p284qw.soul.permission.bean;

/* renamed from: com.qw.soul.permission.bean.Permissions */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class Permissions {
    private Permission[] permissions;

    public static Permissions build(String... strArr) {
        return new Permissions(strArr);
    }

    public static Permissions build(Permission... permissionArr) {
        return new Permissions(permissionArr);
    }

    private Permissions() {
    }

    private Permissions(String[] strArr) {
        this.permissions = new Permission[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            this.permissions[i] = Permission.getDefault(strArr[i]);
        }
    }

    private Permissions(Permission[] permissionArr) {
        this.permissions = permissionArr;
    }

    public Permission[] getPermissions() {
        return this.permissions;
    }

    public String[] getPermissionsString() {
        String[] strArr = new String[this.permissions.length];
        int i = 0;
        while (true) {
            Permission[] permissionArr = this.permissions;
            if (i >= permissionArr.length) {
                return strArr;
            }
            strArr[i] = permissionArr[i].permissionName;
            i++;
        }
    }
}
