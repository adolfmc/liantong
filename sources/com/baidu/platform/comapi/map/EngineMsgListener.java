package com.baidu.platform.comapi.map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface EngineMsgListener {
    void onEnterIndoorMapMode(IndoorMapInfo indoorMapInfo);

    void onExitIndoorMapMode();

    void onLongLinkConnect();

    void onLongLinkDisConnect();
}
