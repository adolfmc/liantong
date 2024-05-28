package com.baidu.location;

import com.baidu.location.p140e.C2735k;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2687c extends Thread {

    /* renamed from: a */
    final /* synthetic */ LocationClient f5488a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2687c(LocationClient locationClient) {
        this.f5488a = locationClient;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            if (this.f5488a.f5079C != null) {
                if (C2735k.m19042f(this.f5488a.f5089f) > 0) {
                    this.f5488a.f5079C.m19510a();
                }
                this.f5488a.f5079C.m19494c();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
