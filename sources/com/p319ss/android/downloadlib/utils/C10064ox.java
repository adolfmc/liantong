package com.p319ss.android.downloadlib.utils;

import android.annotation.TargetApi;
import android.os.AsyncTask;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.utils.ox */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10064ox {

    /* renamed from: mb */
    static final C10066mb f19399mb = new C10067ox();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.utils.ox$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class C10066mb {
        private C10066mb() {
        }

        /* renamed from: mb */
        public <T> void mo6981mb(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
            try {
                asyncTask.execute(tArr);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @TargetApi(11)
    /* renamed from: com.ss.android.downloadlib.utils.ox$ox */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class C10067ox extends C10066mb {
        private C10067ox() {
            super();
        }

        @Override // com.p319ss.android.downloadlib.utils.C10064ox.C10066mb
        /* renamed from: mb */
        public <T> void mo6981mb(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
            try {
                asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, tArr);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: mb */
    public static <T> void m6982mb(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
        f19399mb.mo6981mb(asyncTask, tArr);
    }
}
