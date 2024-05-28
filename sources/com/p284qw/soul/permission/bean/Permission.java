package com.p284qw.soul.permission.bean;

import android.content.Context;
import android.support.annotation.NonNull;
import com.p284qw.soul.C6889R;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.debug.PermissionDebug;

/* renamed from: com.qw.soul.permission.bean.Permission */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class Permission {
    private static final int FLAG_IS_GRANTED = 1;
    private static final int FLAG_SHOULD_RATIONALE = 2;
    private static final String TAG = "Permission";
    private int mFlags = 0;
    public String permissionName;

    public static Permission getDefault(@NonNull String str) {
        return new Permission(str, -1, false);
    }

    public static Permission getDefaultSuccess(@NonNull String str) {
        return new Permission(str, 0, false);
    }

    public Permission(String str, int i, boolean z) {
        this.permissionName = str;
        if (i == 0) {
            this.mFlags |= 1;
        }
        if (z) {
            this.mFlags |= 2;
        }
    }

    public boolean isGranted() {
        return (this.mFlags & 1) != 0;
    }

    public boolean shouldRationale() {
        return (this.mFlags & 2) != 0;
    }

    public String toString() {
        return this.permissionName + " isGranted: " + isGranted() + " shouldRationale " + shouldRationale();
    }

    public String getPermissionNameDesc() {
        Context context = SoulPermission.getInstance().getContext();
        if (context == null) {
            PermissionDebug.m8225e(TAG, "soul permission do not init");
            return "";
        }
        String str = this.permissionName;
        char c = 65535;
        switch (str.hashCode()) {
            case -2062386608:
                if (str.equals("android.permission.READ_SMS")) {
                    c = 30;
                    break;
                }
                break;
            case -1928411001:
                if (str.equals("android.permission.READ_CALENDAR")) {
                    c = 21;
                    break;
                }
                break;
            case -1921431796:
                if (str.equals("android.permission.READ_CALL_LOG")) {
                    c = '\b';
                    break;
                }
                break;
            case -1888586689:
                if (str.equals("android.permission.ACCESS_FINE_LOCATION")) {
                    c = 17;
                    break;
                }
                break;
            case -1639857183:
                if (str.equals("android.permission-group.CONTACTS")) {
                    c = 2;
                    break;
                }
                break;
            case -1479758289:
                if (str.equals("android.permission.RECEIVE_WAP_PUSH")) {
                    c = 31;
                    break;
                }
                break;
            case -1410061184:
                if (str.equals("android.permission-group.PHONE")) {
                    c = 6;
                    break;
                }
                break;
            case -1250730292:
                if (str.equals("android.permission-group.CALENDAR")) {
                    c = 20;
                    break;
                }
                break;
            case -1238066820:
                if (str.equals("android.permission.BODY_SENSORS")) {
                    c = 26;
                    break;
                }
                break;
            case -1140935117:
                if (str.equals("android.permission-group.CAMERA")) {
                    c = 0;
                    break;
                }
                break;
            case -895679497:
                if (str.equals("android.permission.RECEIVE_MMS")) {
                    c = ' ';
                    break;
                }
                break;
            case -895673731:
                if (str.equals("android.permission.RECEIVE_SMS")) {
                    c = 29;
                    break;
                }
                break;
            case -406040016:
                if (str.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                    c = 15;
                    break;
                }
                break;
            case -63024214:
                if (str.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                    c = 18;
                    break;
                }
                break;
            case -5573545:
                if (str.equals("android.permission.READ_PHONE_STATE")) {
                    c = 19;
                    break;
                }
                break;
            case 52602690:
                if (str.equals("android.permission.SEND_SMS")) {
                    c = 28;
                    break;
                }
                break;
            case 112197485:
                if (str.equals("android.permission.CALL_PHONE")) {
                    c = 7;
                    break;
                }
                break;
            case 214526995:
                if (str.equals("android.permission.WRITE_CONTACTS")) {
                    c = 4;
                    break;
                }
                break;
            case 421761675:
                if (str.equals("android.permission-group.SENSORS")) {
                    c = 25;
                    break;
                }
                break;
            case 463403621:
                if (str.equals("android.permission.CAMERA")) {
                    c = 1;
                    break;
                }
                break;
            case 603653886:
                if (str.equals("android.permission.WRITE_CALENDAR")) {
                    c = 22;
                    break;
                }
                break;
            case 610633091:
                if (str.equals("android.permission.WRITE_CALL_LOG")) {
                    c = '\t';
                    break;
                }
                break;
            case 784519842:
                if (str.equals("android.permission.USE_SIP")) {
                    c = 11;
                    break;
                }
                break;
            case 828638019:
                if (str.equals("android.permission-group.LOCATION")) {
                    c = 16;
                    break;
                }
                break;
            case 852078861:
                if (str.equals("android.permission-group.STORAGE")) {
                    c = '\r';
                    break;
                }
                break;
            case 952819282:
                if (str.equals("android.permission.PROCESS_OUTGOING_CALLS")) {
                    c = '\f';
                    break;
                }
                break;
            case 1271781903:
                if (str.equals("android.permission.GET_ACCOUNTS")) {
                    c = 5;
                    break;
                }
                break;
            case 1365911975:
                if (str.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                    c = 14;
                    break;
                }
                break;
            case 1581272376:
                if (str.equals("android.permission-group.MICROPHONE")) {
                    c = 23;
                    break;
                }
                break;
            case 1795181803:
                if (str.equals("android.permission-group.SMS")) {
                    c = 27;
                    break;
                }
                break;
            case 1831139720:
                if (str.equals("android.permission.RECORD_AUDIO")) {
                    c = 24;
                    break;
                }
                break;
            case 1977429404:
                if (str.equals("android.permission.READ_CONTACTS")) {
                    c = 3;
                    break;
                }
                break;
            case 2133799037:
                if (str.equals("com.android.voicemail.permission.ADD_VOICEMAIL")) {
                    c = '\n';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
                return context.getResources().getString(C6889R.string.permission_camera);
            case 2:
            case 3:
            case 4:
            case 5:
                return context.getResources().getString(C6889R.string.permission_contact);
            case 6:
            case 7:
            case '\b':
            case '\t':
            case '\n':
            case 11:
            case '\f':
                return context.getResources().getString(C6889R.string.permission_call);
            case '\r':
            case 14:
            case 15:
                return context.getResources().getString(C6889R.string.permission_storage);
            case 16:
            case 17:
            case 18:
                return context.getResources().getString(C6889R.string.permission_location);
            case 19:
                return context.getResources().getString(C6889R.string.permission_phone_status);
            case 20:
            case 21:
            case 22:
                return context.getResources().getString(C6889R.string.permission_calender);
            case 23:
            case 24:
                return context.getResources().getString(C6889R.string.permission_microphone);
            case 25:
            case 26:
                return context.getResources().getString(C6889R.string.permission_sensor);
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case ' ':
                return context.getResources().getString(C6889R.string.permission_sms);
            default:
                return context.getResources().getString(C6889R.string.permission_undefined);
        }
    }
}
