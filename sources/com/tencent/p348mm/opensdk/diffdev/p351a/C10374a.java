package com.tencent.p348mm.opensdk.diffdev.p351a;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.tencent.p348mm.opensdk.diffdev.IDiffDevOAuth;
import com.tencent.p348mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.p348mm.opensdk.diffdev.OAuthListener;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.diffdev.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10374a implements IDiffDevOAuth {

    /* renamed from: c */
    private AsyncTaskC10377b f19954c;

    /* renamed from: a */
    private Handler f19952a = null;

    /* renamed from: b */
    private List<OAuthListener> f19953b = new ArrayList();

    /* renamed from: d */
    private OAuthListener f19955d = new C10375a();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.diffdev.a.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C10375a implements OAuthListener {

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.tencent.mm.opensdk.diffdev.a.a$a$a */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        class RunnableC10376a implements Runnable {
            RunnableC10376a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(C10374a.this.f19953b);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((OAuthListener) it.next()).onQrcodeScanned();
                }
            }
        }

        C10375a() {
        }

        @Override // com.tencent.p348mm.opensdk.diffdev.OAuthListener
        public void onAuthFinish(OAuthErrCode oAuthErrCode, String str) {
            C10384Log.m6211d("MicroMsg.SDK.ListenerWrapper", String.format("onAuthFinish, errCode = %s, authCode = %s", oAuthErrCode.toString(), str));
            C10374a.this.f19954c = null;
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(C10374a.this.f19953b);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((OAuthListener) it.next()).onAuthFinish(oAuthErrCode, str);
            }
        }

        @Override // com.tencent.p348mm.opensdk.diffdev.OAuthListener
        public void onAuthGotQrcode(String str, byte[] bArr) {
            C10384Log.m6211d("MicroMsg.SDK.ListenerWrapper", "onAuthGotQrcode, qrcodeImgPath = " + str);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(C10374a.this.f19953b);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((OAuthListener) it.next()).onAuthGotQrcode(str, bArr);
            }
        }

        @Override // com.tencent.p348mm.opensdk.diffdev.OAuthListener
        public void onQrcodeScanned() {
            C10384Log.m6211d("MicroMsg.SDK.ListenerWrapper", "onQrcodeScanned");
            if (C10374a.this.f19952a != null) {
                C10374a.this.f19952a.post(new RunnableC10376a());
            }
        }
    }

    @Override // com.tencent.p348mm.opensdk.diffdev.IDiffDevOAuth
    public void addListener(OAuthListener oAuthListener) {
        if (this.f19953b.contains(oAuthListener)) {
            return;
        }
        this.f19953b.add(oAuthListener);
    }

    @Override // com.tencent.p348mm.opensdk.diffdev.IDiffDevOAuth
    public boolean auth(String str, String str2, String str3, String str4, String str5, OAuthListener oAuthListener) {
        C10384Log.m6209i("MicroMsg.SDK.DiffDevOAuth", "start auth, appId = " + str);
        if (str == null || str.length() <= 0 || str2 == null || str2.length() <= 0) {
            C10384Log.m6211d("MicroMsg.SDK.DiffDevOAuth", String.format("auth fail, invalid argument, appId = %s, scope = %s", str, str2));
            return false;
        }
        if (this.f19952a == null) {
            this.f19952a = new Handler(Looper.getMainLooper());
        }
        if (!this.f19953b.contains(oAuthListener)) {
            this.f19953b.add(oAuthListener);
        }
        if (this.f19954c != null) {
            C10384Log.m6211d("MicroMsg.SDK.DiffDevOAuth", "auth, already running, no need to start auth again");
            return true;
        }
        AsyncTaskC10377b asyncTaskC10377b = new AsyncTaskC10377b(str, str2, str3, str4, str5, this.f19955d);
        this.f19954c = asyncTaskC10377b;
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTaskC10377b.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            asyncTaskC10377b.execute(new Void[0]);
        }
        return true;
    }

    @Override // com.tencent.p348mm.opensdk.diffdev.IDiffDevOAuth
    public void detach() {
        C10384Log.m6209i("MicroMsg.SDK.DiffDevOAuth", "detach");
        this.f19953b.clear();
        stopAuth();
    }

    @Override // com.tencent.p348mm.opensdk.diffdev.IDiffDevOAuth
    public void removeAllListeners() {
        this.f19953b.clear();
    }

    @Override // com.tencent.p348mm.opensdk.diffdev.IDiffDevOAuth
    public void removeListener(OAuthListener oAuthListener) {
        this.f19953b.remove(oAuthListener);
    }

    @Override // com.tencent.p348mm.opensdk.diffdev.IDiffDevOAuth
    public boolean stopAuth() {
        boolean z;
        C10384Log.m6209i("MicroMsg.SDK.DiffDevOAuth", "stopAuth");
        try {
            z = this.f19954c == null ? true : this.f19954c.m6219a();
        } catch (Exception e) {
            C10384Log.m6207w("MicroMsg.SDK.DiffDevOAuth", "stopAuth fail, ex = " + e.getMessage());
            z = false;
        }
        this.f19954c = null;
        return z;
    }
}
