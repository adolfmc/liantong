package com.p319ss.android.downloadlib.p334ox;

import android.support.annotation.NonNull;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C9982hj;

/* renamed from: com.ss.android.downloadlib.ox.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C10036ox {
    /* renamed from: mb */
    public void m7114mb(@NonNull final C9837ox c9837ox, @NonNull final InterfaceC10030ko interfaceC10030ko, int i) {
        C9982hj.m7254mb().m7252mb(new Runnable() { // from class: com.ss.android.downloadlib.ox.ox.1
            @Override // java.lang.Runnable
            public void run() {
                if (C10031lz.m7128mb(c9837ox)) {
                    interfaceC10030ko.mo7129mb(false);
                } else if (!C10039u.m7108mb(c9837ox)) {
                    interfaceC10030ko.mo7129mb(false);
                } else {
                    C10039u.m7106mb(c9837ox, new InterfaceC10043ww() { // from class: com.ss.android.downloadlib.ox.ox.1.1
                        @Override // com.p319ss.android.downloadlib.p334ox.InterfaceC10043ww
                        /* renamed from: mb */
                        public void mo7100mb(boolean z) {
                            interfaceC10030ko.mo7129mb(z);
                        }
                    });
                }
            }
        }, i);
    }
}
