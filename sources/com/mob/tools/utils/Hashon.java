package com.mob.tools.utils;

import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Hashon implements PublicMemberKeeper {
    public <T> HashMap<String, T> fromJson(String str) {
        return HashonHelper.fromJson(str);
    }

    public <T> String fromHashMap(HashMap<String, T> hashMap) {
        return HashonHelper.fromHashMap(hashMap);
    }

    public String format(String str) {
        return HashonHelper.format(str);
    }

    public String fromObject(Object obj) {
        return HashonHelper.fromObject(obj);
    }

    public <T> T fromJson(String str, Class<T> cls) {
        return (T) HashonHelper.fromJson(str, cls);
    }
}
