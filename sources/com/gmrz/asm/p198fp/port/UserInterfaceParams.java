package com.gmrz.asm.p198fp.port;

import android.graphics.Color;
import com.gmrz.fpasm.C4439R;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.port.UserInterfaceParams */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UserInterfaceParams {
    public static int RES_ID_LAYOUT_MATCHER_UI = C4439R.C4443layout.layout_matcher_ui;
    public static boolean IS_MATCH_UI_WIDTH_MATCH_PARENT = false;
    public static int LOCATION_IN_SCREEN = 17;
    public static int RES_ID_ICON_FINGER_NORMAL = C4439R.C4441drawable.icon_fingerprint;
    public static int RES_ID_ICON_FINGER_FAILED = C4439R.C4441drawable.matcher_ui_icon_fp_failed;
    public static int RES_ID_ICON_FINGER_CORRECT = C4439R.C4441drawable.matcher_ui_icon_fp_correct;
    public static int COLOR_ID_TEXT_NORMAL = Color.parseColor("#666666");
    public static int COLOR_ID_TEXT_FAILED = -65536;
    public static int COLOR_ID_TEXT_CORRECT = Color.rgb(23, 209, 38);
    public static int STRING_ID_HINT_NORMAL = C4439R.string.touch_finger_hint;
    public static int STRING_ID_HINT_AUTH_CORRECT = C4439R.string.fingerprint_matched;
    public static int STRING_ID_HINT_AUTH_FAILED = C4439R.string.fingerprint_mismatched;
    public static int STRING_ID_HINT_NO_ENROLL_FINGERPRINTS_IN_SYSTEM = C4439R.string.manage_hint;
    public static int STRING_ID_HINT_NO_SET_LOCK_SCREEN = C4439R.string.secure_hint;
    public static int STRING_ID_HINT_TOO_MANY_ATTEMPTS = C4439R.string.toomanyattempts;
    public static int STRING_ID_HINT_TIME_OUT = C4439R.string.timeout;
    public static int STRING_ID_HINT_UNKNOWN_ERROR = C4439R.string.unknown_error;

    public static void setMatchUiWidthMatchParent(boolean z) {
        IS_MATCH_UI_WIDTH_MATCH_PARENT = z;
    }

    public static void setLocationInScreen(int i) {
        LOCATION_IN_SCREEN = i;
    }

    public static void setResIdLayoutMatcherUi(int i) {
        RES_ID_LAYOUT_MATCHER_UI = i;
    }

    public static void setResIdIconFingerNormal(int i) {
        RES_ID_ICON_FINGER_NORMAL = i;
    }

    public static void setResIdIconFingerFailed(int i) {
        RES_ID_ICON_FINGER_FAILED = i;
    }

    public static void setResIdIconFingerCorrect(int i) {
        RES_ID_ICON_FINGER_CORRECT = i;
    }

    public static void setColorIdTextNormal(int i) {
        COLOR_ID_TEXT_NORMAL = i;
    }

    public static void setColorIdTextFailed(int i) {
        COLOR_ID_TEXT_FAILED = i;
    }

    public static void setColorIdTextCorrect(int i) {
        COLOR_ID_TEXT_CORRECT = i;
    }

    public static void setStringIdHintNormal(int i) {
        STRING_ID_HINT_NORMAL = i;
    }

    public static void setStringIdHintAuthCorrect(int i) {
        STRING_ID_HINT_AUTH_CORRECT = i;
    }

    public static void setStringIdHintAuthFailed(int i) {
        STRING_ID_HINT_AUTH_FAILED = i;
    }

    public static void setStringIdHintNoEnrollFingerprintsInSystem(int i) {
        STRING_ID_HINT_NO_ENROLL_FINGERPRINTS_IN_SYSTEM = i;
    }

    public static void setStringIdHintNoSetLockScreen(int i) {
        STRING_ID_HINT_NO_SET_LOCK_SCREEN = i;
    }

    public static void setStringIdHintTooManyAttempts(int i) {
        STRING_ID_HINT_TOO_MANY_ATTEMPTS = i;
    }

    public static void setStringIdHintTimeOut(int i) {
        STRING_ID_HINT_TIME_OUT = i;
    }

    public static void setStringIdHintUnknownError(int i) {
        STRING_ID_HINT_UNKNOWN_ERROR = i;
    }
}
