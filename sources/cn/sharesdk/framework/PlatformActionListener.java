package cn.sharesdk.framework;

import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface PlatformActionListener {
    void onCancel(Platform platform, int i);

    void onComplete(Platform platform, int i, HashMap<String, Object> hashMap);

    void onError(Platform platform, int i, Throwable th);
}
