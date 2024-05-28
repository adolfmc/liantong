package com.baidu.p120ar.arplay.util;

import com.baidu.p120ar.arplay.util.NetUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.util.INetChangeObserver */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface INetChangeObserver {
    void onNetConnected(NetUtils.NetType netType);

    void onNetDisConnect();
}
