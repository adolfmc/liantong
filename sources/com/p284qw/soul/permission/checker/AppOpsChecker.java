package com.p284qw.soul.permission.checker;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import com.p284qw.soul.permission.debug.PermissionDebug;

/* renamed from: com.qw.soul.permission.checker.AppOpsChecker */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
class AppOpsChecker implements PermissionChecker {
    private static final String TAG = "AppOpsChecker";
    private Context context;
    private String permission;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppOpsChecker(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppOpsChecker(Context context, String str) {
        this.context = context;
        this.permission = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.p284qw.soul.permission.checker.PermissionChecker
    public boolean check() {
        char c;
        String str = this.permission;
        if (str == null) {
            return true;
        }
        switch (str.hashCode()) {
            case -2062386608:
                if (str.equals("android.permission.READ_SMS")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1928411001:
                if (str.equals("android.permission.READ_CALENDAR")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1888586689:
                if (str.equals("android.permission.ACCESS_FINE_LOCATION")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1238066820:
                if (str.equals("android.permission.BODY_SENSORS")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -895673731:
                if (str.equals("android.permission.RECEIVE_SMS")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -406040016:
                if (str.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -63024214:
                if (str.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -5573545:
                if (str.equals("android.permission.READ_PHONE_STATE")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 52602690:
                if (str.equals("android.permission.SEND_SMS")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 112197485:
                if (str.equals("android.permission.CALL_PHONE")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 214526995:
                if (str.equals("android.permission.WRITE_CONTACTS")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 463403621:
                if (str.equals("android.permission.CAMERA")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 603653886:
                if (str.equals("android.permission.WRITE_CALENDAR")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1365911975:
                if (str.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1831139720:
                if (str.equals("android.permission.RECORD_AUDIO")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1977429404:
                if (str.equals("android.permission.READ_CONTACTS")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return checkOp(4);
            case 1:
                return checkOp(5);
            case 2:
                return checkOp(13);
            case 3:
                return checkOp(51);
            case 4:
                return checkOp(26);
            case 5:
                return checkOp(59);
            case 6:
                return checkOp(60);
            case 7:
                return checkOp(1);
            case '\b':
                return checkOp(0);
            case '\t':
                return checkOp(27);
            case '\n':
                return checkOp(56);
            case 11:
                return checkOp(8);
            case '\f':
                return checkOp(9);
            case '\r':
                return checkOp(20);
            case 14:
                return checkOp(14);
            case 15:
                return checkOp(16);
            default:
                return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean checkOp(int i) {
        if (Build.VERSION.SDK_INT < 19) {
            PermissionDebug.m8226d(TAG, "4.4 below");
            return true;
        }
        try {
            return ((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class).invoke((AppOpsManager) this.context.getSystemService("appops"), Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()), this.context.getPackageName())).intValue() == 0;
        } catch (Exception e) {
            PermissionDebug.m8222w(TAG, e.toString());
            e.printStackTrace();
            return true;
        }
    }
}
