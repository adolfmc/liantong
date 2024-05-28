package com.baidu.p120ar.p121vo;

import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.IEngineMsgHandler */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IEngineMsgHandler {
    void sendMsg2Engine(int i, HashMap<String, Object> hashMap);

    void sendMsg2Lua(HashMap<String, Object> hashMap);
}
