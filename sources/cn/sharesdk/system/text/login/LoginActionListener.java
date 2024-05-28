package cn.sharesdk.system.text.login;

import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface LoginActionListener {
    void onCancel();

    void onFail(Throwable th);

    void onSuccess(HashMap<String, Object> hashMap);
}
