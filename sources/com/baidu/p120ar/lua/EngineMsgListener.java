package com.baidu.p120ar.lua;

import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.lua.EngineMsgListener */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface EngineMsgListener {
    List<Integer> getMsgTypesListened();

    void onEngineMessage(int i, int i2, HashMap<String, Object> hashMap);
}
