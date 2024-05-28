package com.mob.mcl;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import com.mob.apc.C5677a;
import com.mob.mcl.C5901a;
import com.mob.mcl.p234b.C5923b;
import com.mob.tools.network.HttpConnection;
import com.mob.tools.network.HttpResponseCallback;
import com.mob.tools.utils.ActivityTracker;
import com.mob.tools.utils.HashonHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.mcl.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5901a {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.mcl.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5902a {
        /* renamed from: a */
        public void mo11883a() {
        }

        /* renamed from: b */
        public void mo11964b() {
        }
    }

    /* renamed from: a */
    public static HttpConnection m12111a(final C5923b c5923b) {
        return new HttpConnection() { // from class: com.mob.mcl.Tmpc$1
            @Override // com.mob.tools.network.HttpConnection
            public int getResponseCode() throws IOException {
                return C5923b.this.m12054b();
            }

            @Override // com.mob.tools.network.HttpConnection
            public InputStream getInputStream() throws IOException {
                return C5923b.this.m12053c();
            }

            @Override // com.mob.tools.network.HttpConnection
            public InputStream getErrorStream() throws IOException {
                return C5923b.this.m12052d();
            }

            @Override // com.mob.tools.network.HttpConnection
            public Map<String, List<String>> getHeaderFields() throws IOException {
                return C5923b.this.m12051e();
            }
        };
    }

    /* renamed from: a */
    public static HttpResponseCallback m12110a(String str, final C5677a c5677a) {
        return new HttpResponseCallback() { // from class: com.mob.mcl.Tmpc$2
            @Override // com.mob.tools.network.HttpResponseCallback
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                if (httpConnection instanceof C5923b) {
                    Bundle bundle = new Bundle();
                    new HashonHelper();
                    bundle.putString("data", HashonHelper.fromHashMap(((C5923b) httpConnection).m12056a()));
                    C5677a.this.f14000e = bundle;
                }
            }
        };
    }

    /* renamed from: a */
    public static ActivityTracker.Tracker m12112a(final C5902a c5902a) {
        return new ActivityTracker.Tracker() { // from class: com.mob.mcl.Tmpc$3

            /* renamed from: b */
            private long f14545b;

            /* renamed from: c */
            private String f14546c;

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onCreated(Activity activity, Bundle bundle) {
            }

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onDestroyed(Activity activity) {
            }

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onPaused(Activity activity) {
            }

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onSaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onStarted(Activity activity) {
            }

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onResumed(Activity activity) {
                try {
                    if (this.f14545b == 0) {
                        this.f14545b = SystemClock.elapsedRealtime();
                        C5901a.C5902a.this.mo11883a();
                    }
                    this.f14546c = activity == null ? null : activity.toString();
                } catch (Throwable unused) {
                }
            }

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onStopped(Activity activity) {
                try {
                    if (this.f14546c != null) {
                        if (!this.f14546c.equals(activity == null ? null : activity.toString())) {
                            return;
                        }
                    }
                    this.f14545b = 0L;
                    this.f14546c = null;
                    C5901a.C5902a.this.mo11964b();
                } catch (Throwable unused) {
                }
            }
        };
    }
}
