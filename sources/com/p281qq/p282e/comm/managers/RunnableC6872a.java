package com.p281qq.p282e.comm.managers;

import com.p281qq.p282e.comm.managers.plugin.C6875PM;
import com.p281qq.p282e.comm.managers.plugin.C6884e;
import com.p281qq.p282e.comm.util.GDTLogger;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.qq.e.comm.managers.a */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RunnableC6872a implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C6873b f17915a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC6872a(C6873b c6873b) {
        this.f17915a = c6873b;
    }

    @Override // java.lang.Runnable
    public void run() {
        C6875PM c6875pm;
        try {
            c6875pm = this.f17915a.f17920d;
            c6875pm.getPOFactory();
            this.f17915a.f17918b = true;
        } catch (C6884e e) {
            GDTLogger.m8233e(e.getMessage(), e);
        }
    }
}
