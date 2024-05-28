package cn.sharesdk.system.text;

import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ActionListener {
    void onComplete(HashMap<String, Object> hashMap);

    void onError(Throwable th);

    void onStart(HashMap<String, Object> hashMap);
}
