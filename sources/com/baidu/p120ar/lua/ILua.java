package com.baidu.p120ar.lua;

import com.baidu.p120ar.DefinedLuaListener;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.lua.ILua */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ILua {
    boolean addLuaMsgListener(LuaMsgListener luaMsgListener);

    boolean removeLuaMsgListener(LuaMsgListener luaMsgListener);

    boolean sendLuaScript2Engine(String str);

    boolean sendMsg2Lua(HashMap<String, Object> hashMap);

    void setDefinedLuaListener(DefinedLuaListener definedLuaListener);
}
