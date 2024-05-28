package com.baidu.location.p137b;

import com.baidu.location.p140e.C2735k;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2648j extends Thread {

    /* renamed from: a */
    final /* synthetic */ C2644h f5277a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2648j(C2644h c2644h) {
        this.f5277a = c2644h;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.f5277a.m19467a(new File(C2735k.m19040h() + "/baidu/tempdata", "intime.dat"), "https://itsdata.map.baidu.com/long-conn-gps/sdk.php");
    }
}
