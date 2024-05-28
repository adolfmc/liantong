package com.tencent.p348mm.opensdk.diffdev.p351a;

import android.os.AsyncTask;
import com.tencent.p348mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.p348mm.opensdk.diffdev.OAuthListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.diffdev.a.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class AsyncTaskC10379c extends AsyncTask<Void, Void, C10380a> {

    /* renamed from: a */
    private String f19970a;

    /* renamed from: b */
    private String f19971b;

    /* renamed from: c */
    private OAuthListener f19972c;

    /* renamed from: d */
    private int f19973d;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.diffdev.a.c$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C10380a {

        /* renamed from: a */
        public OAuthErrCode f19974a;

        /* renamed from: b */
        public String f19975b;

        /* renamed from: c */
        public int f19976c;

        C10380a() {
        }
    }

    public AsyncTaskC10379c(String str, OAuthListener oAuthListener) {
        this.f19970a = str;
        this.f19972c = oAuthListener;
        this.f19971b = String.format("https://long.open.weixin.qq.com/connect/l/qrconnect?f=json&uuid=%s", str);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0172 A[SYNTHETIC] */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected com.tencent.p348mm.opensdk.diffdev.p351a.AsyncTaskC10379c.C10380a doInBackground(java.lang.Void[] r13) {
        /*
            Method dump skipped, instructions count: 442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p348mm.opensdk.diffdev.p351a.AsyncTaskC10379c.doInBackground(java.lang.Object[]):java.lang.Object");
    }

    @Override // android.os.AsyncTask
    protected void onPostExecute(C10380a c10380a) {
        C10380a c10380a2 = c10380a;
        this.f19972c.onAuthFinish(c10380a2.f19974a, c10380a2.f19975b);
    }
}
