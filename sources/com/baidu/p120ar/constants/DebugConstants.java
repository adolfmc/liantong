package com.baidu.p120ar.constants;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.constants.DebugConstants */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class DebugConstants {
    public static boolean AR_IS_NEED_PRINT_FRAME_LOG = false;
    public static boolean DEBUG = true;
    public static boolean DEBUG_CAPTURE = false;
    public static boolean DEBUG_LOG = false;
    public static boolean DEBUG_LOG2FILE = false;
    public static boolean DEBUG_PREVIEW_FRAME = false;
    public static final boolean DEBUG_QA_AUTO = false;
    public static final boolean DEBUG_QA_BLACK = false;
    public static final boolean DEBUG_QA_IMU_MOCK = false;
    public static final boolean DEBUG_QA_LOG = false;
    public static final boolean DEBUG_QA_SCREENSHOT = false;
    public static final boolean DEBUG_QA_VIDEO_MOCK = false;
    public static boolean DEBUG_TOAST = false;
    public static boolean DEBUG_TRACK_EDGE = false;
    public static boolean DEBUG_TRACK_JIT = false;
    public static boolean FORECE_COPY_WEBROOT = true;
    public static final int HTTP_ERRCODE_NOT_FIND = 1032;
    public static final int HTTP_ERRCODE_VERSION_HIGH = 1033;
    public static final int HTTP_ERRCODE_VERSION_LOW = 1044;
    public static String IMU_MOCK_PATH = null;
    public static int PRINT_FPS_INTERVAL = 0;
    public static boolean RE_EXTRACT = true;
    public static String VIDEO_MOCK_PATH;

    static {
        boolean z = DEBUG;
        DEBUG_LOG = z;
        DEBUG_TOAST = z;
        DEBUG_TRACK_EDGE = z;
        DEBUG_TRACK_JIT = z;
        DEBUG_LOG2FILE = DEBUG_TRACK_JIT;
        DEBUG_CAPTURE = false;
        DEBUG_PREVIEW_FRAME = false;
        PRINT_FPS_INTERVAL = 100;
    }
}
