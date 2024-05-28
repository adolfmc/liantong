package com.baidu.mapapi.http;

import android.os.Build;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AsyncHttpClient {

    /* renamed from: a */
    private int f5873a = 10000;

    /* renamed from: b */
    private int f5874b = 10000;

    /* renamed from: c */
    private ExecutorService f5875c = Executors.newCachedThreadPool();

    static {
        if (Build.VERSION.SDK_INT <= 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    protected boolean isAuthorized() {
        int permissionCheck = PermissionCheck.permissionCheck();
        return permissionCheck == 0 || permissionCheck == 602 || permissionCheck == 601;
    }

    public void get(String str, HttpClient.ProtoResultCallback protoResultCallback) {
        if (str == null) {
            throw new IllegalArgumentException("URI cannot be null");
        }
        this.f5875c.submit(new C2743a(this, protoResultCallback, str));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapapi.http.AsyncHttpClient$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static abstract class AbstractRunnableC2741a implements Runnable {
        /* renamed from: a */
        public abstract void mo19028a();

        private AbstractRunnableC2741a() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ AbstractRunnableC2741a(C2743a c2743a) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            mo19028a();
        }
    }
}
