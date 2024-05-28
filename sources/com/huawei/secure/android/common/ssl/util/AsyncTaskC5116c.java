package com.huawei.secure.android.common.ssl.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import java.io.InputStream;

/* renamed from: com.huawei.secure.android.common.ssl.util.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AsyncTaskC5116c extends AsyncTask<Context, Integer, Boolean> {

    /* renamed from: a */
    private static final String f12109a = "c";

    /* renamed from: b */
    private static final long f12110b = 432000000;

    /* renamed from: c */
    private static final String f12111c = "lastCheckTime";

    /* renamed from: d */
    private static volatile boolean f12112d;

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static void m13874a() {
        if (m13870b()) {
            C5118e.m13853c(f12109a, "checkUpgradeBks, execute check task");
            new AsyncTaskC5116c().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ContextUtil.getInstance());
        }
    }

    /* renamed from: b */
    private static boolean m13870b() {
        if (f12112d) {
            return false;
        }
        Context contextUtil = ContextUtil.getInstance();
        if (contextUtil == null) {
            C5118e.m13851e(f12109a, "checkUpgradeBks, context is null");
            return false;
        }
        f12112d = true;
        long m13846a = C5120g.m13846a(f12111c, 0L, contextUtil);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - m13846a > f12110b) {
            C5120g.m13841b(f12111c, currentTimeMillis, contextUtil);
            return true;
        }
        C5118e.m13853c(f12109a, "checkUpgradeBks, ignore");
        return false;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        C5118e.m13856a(f12109a, "onPreExecute");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public Boolean doInBackground(Context... contextArr) {
        InputStream inputStream;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            inputStream = BksUtil.getBksFromTss(contextArr[0]);
        } catch (Exception e) {
            String str = f12109a;
            C5118e.m13854b(str, "doInBackground: exception : " + e.getMessage());
            inputStream = null;
        }
        String str2 = f12109a;
        C5118e.m13856a(str2, "doInBackground: get bks from hms tss cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        if (inputStream != null) {
            AbstractC5117d.m13866a(inputStream);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(Boolean bool) {
        if (bool.booleanValue()) {
            C5118e.m13853c(f12109a, "onPostExecute: upate done");
        } else {
            C5118e.m13854b(f12109a, "onPostExecute: upate failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public void onProgressUpdate(Integer... numArr) {
        C5118e.m13853c(f12109a, "onProgressUpdate");
    }
}
