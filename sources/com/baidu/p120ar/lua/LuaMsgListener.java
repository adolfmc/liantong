package com.baidu.p120ar.lua;

import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.lua.LuaMsgListener */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface LuaMsgListener {
    List<String> getMsgKeyListened();

    void onLuaMessage(HashMap<String, Object> hashMap);
}
