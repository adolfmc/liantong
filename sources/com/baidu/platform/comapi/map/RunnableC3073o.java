package com.baidu.platform.comapi.map;

import com.baidu.platform.comapi.map.MapController;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC3073o implements Runnable {

    /* renamed from: a */
    final /* synthetic */ MapController.HandlerC2987b f7984a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3073o(MapController.HandlerC2987b handlerC2987b) {
        this.f7984a = handlerC2987b;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (MapController.this.mListeners != null) {
            for (int i = 0; i < MapController.this.mListeners.size(); i++) {
                InterfaceC3010al interfaceC3010al = MapController.this.mListeners.get(i);
                if (interfaceC3010al != null) {
                    interfaceC3010al.mo17962b();
                }
            }
        }
    }
}
