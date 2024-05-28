package com.sina.weibo.sdk.share;

import android.content.Context;
import android.os.AsyncTask;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.share.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class AsyncTaskC7112d extends AsyncTask<WeiboMultiMessage, Void, C7111c> {

    /* renamed from: B */
    private WeakReference<Context> f18338B;

    /* renamed from: C */
    private InterfaceC7110b f18339C;

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(C7111c c7111c) {
        C7111c c7111c2 = c7111c;
        super.onPostExecute(c7111c2);
        InterfaceC7110b interfaceC7110b = this.f18339C;
        if (interfaceC7110b != null) {
            interfaceC7110b.mo8034a(c7111c2);
        }
    }

    public AsyncTaskC7112d(Context context, InterfaceC7110b interfaceC7110b) {
        this.f18338B = new WeakReference<>(context);
        this.f18339C = interfaceC7110b;
    }

    @Override // android.os.AsyncTask
    protected final void onPreExecute() {
        super.onPreExecute();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0093 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x012d A[Catch: Throwable -> 0x018e, TryCatch #0 {Throwable -> 0x018e, blocks: (B:15:0x0029, B:17:0x002d, B:19:0x0031, B:20:0x0033, B:22:0x0037, B:24:0x003b, B:26:0x003f, B:27:0x0043, B:29:0x004a, B:30:0x0059, B:32:0x005f, B:34:0x0067, B:36:0x0071, B:38:0x007c, B:40:0x0086, B:45:0x0093, B:47:0x0097, B:48:0x009e, B:50:0x00a8, B:51:0x00b5, B:52:0x00bc, B:53:0x00bd, B:54:0x00c4, B:55:0x00c5, B:56:0x00c9, B:58:0x00cd, B:60:0x00d3, B:62:0x00dd, B:64:0x00e8, B:73:0x0119, B:75:0x011f, B:80:0x012d, B:82:0x0131, B:83:0x0149, B:85:0x0162, B:86:0x0178, B:87:0x0180, B:67:0x00f7, B:69:0x0101, B:72:0x0109, B:88:0x0181, B:89:0x0188, B:90:0x0189), top: B:98:0x0029 }] */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.sina.weibo.sdk.share.C7111c doInBackground(com.sina.weibo.sdk.api.WeiboMultiMessage... r12) {
        /*
            Method dump skipped, instructions count: 433
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.share.AsyncTaskC7112d.doInBackground(com.sina.weibo.sdk.api.WeiboMultiMessage[]):com.sina.weibo.sdk.share.c");
    }
}
