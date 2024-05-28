package com.baidu.platform.comapi.map;

import android.view.Surface;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class VulkanDetect {
    VulkanDetect() {
    }

    public static native long getNativeWindow(Surface surface);

    public static native boolean isSupportedVulkan();

    public static native void releaseNativeWindow(long j);
}
