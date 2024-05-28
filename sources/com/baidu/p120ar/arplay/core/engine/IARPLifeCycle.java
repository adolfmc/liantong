package com.baidu.p120ar.arplay.core.engine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.IARPLifeCycle */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IARPLifeCycle extends IBaseLifeCycle {
    boolean createEngine(ARPEngineParams aRPEngineParams);

    void pauseScene();

    void resumeScene();
}
