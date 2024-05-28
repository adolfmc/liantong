package com.sdk.p285a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.p290f.C6998d;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressLint({"NewApi"})
/* renamed from: com.sdk.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6954a {

    /* renamed from: c */
    public static final String f17978c = "com.sdk.a.a";

    /* renamed from: d */
    public static Boolean f17979d = Boolean.valueOf(C6998d.f18135a);

    /* renamed from: e */
    public static Network f17980e;

    /* renamed from: f */
    public static boolean f17981f;

    /* renamed from: g */
    public static ConnectivityManager.NetworkCallback f17982g;

    /* renamed from: a */
    public HttpURLConnection f17983a;

    /* renamed from: b */
    public ConnectivityManager f17984b;

    /* renamed from: com.sdk.a.a$a */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C6955a extends ConnectivityManager.NetworkCallback {

        /* renamed from: a */
        public final /* synthetic */ URL f17985a;

        public C6955a(URL url) {
            this.f17985a = url;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            C6954a.f17980e = network;
            try {
                C6954a.this.f17983a = (HttpURLConnection) network.openConnection(this.f17985a);
            } catch (IOException unused) {
                String str = C6954a.f17978c;
                Log.d(str, "onAvailable: " + C6954a.this.f17983a.getURL());
            }
        }
    }

    public C6954a() {
        this.f17984b = null;
        f17981f = true;
        f17982g = null;
        f17980e = null;
        this.f17983a = null;
    }

    public C6954a(Context context, URL url) {
        LogUtils.d_yl(f17978c, "public CellularConnection 开始", 0);
        this.f17984b = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        try {
            Network network = f17980e;
            if (network == null || f17981f) {
                f17981f = false;
                C6955a c6955a = new C6955a(url);
                f17982g = c6955a;
                m8220a(c6955a);
                return;
            }
            try {
                this.f17983a = (HttpURLConnection) network.openConnection(url);
            } catch (IOException unused) {
                String str = f17978c;
                Log.d(str, "CellularConnection: " + this.f17983a);
            }
        } catch (Exception e) {
            LogUtils.m8186e(f17978c, e.toString(), f17979d);
        }
    }

    /* renamed from: a */
    public static void m8219a(ConnectivityManager connectivityManager, ConnectivityManager.NetworkCallback networkCallback) {
        if (connectivityManager == null || networkCallback == null) {
            return;
        }
        try {
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addTransportType(0).addCapability(12);
            NetworkRequest build = builder.build();
            String str = f17978c;
            LogUtils.d_yl(str, "请求网络 requestNetwork 前  ", 0);
            connectivityManager.requestNetwork(build, networkCallback);
            LogUtils.d_yl(str, "请求网络 requestNetwork 后  ", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public HttpURLConnection m8221a() {
        HttpURLConnection httpURLConnection;
        long currentTimeMillis = System.currentTimeMillis();
        do {
            if (System.currentTimeMillis() - currentTimeMillis > 2000) {
                return null;
            }
            httpURLConnection = this.f17983a;
        } while (httpURLConnection == null);
        return httpURLConnection;
    }

    /* renamed from: a */
    public void m8220a(ConnectivityManager.NetworkCallback networkCallback) {
        if (Build.VERSION.SDK_INT >= 21) {
            m8219a(this.f17984b, networkCallback);
        }
    }
}
