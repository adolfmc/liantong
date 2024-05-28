package cn.sharesdk.sina.weibo.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.IBinder;
import android.text.TextUtils;
import cn.sharesdk.framework.authorize.SSOAuthorizeActivity;
import cn.sharesdk.framework.authorize.SSOProcessor;
import cn.sharesdk.framework.utils.AppUtils;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.sina.weibo.utils.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SinaWeiboSSOProcessor extends SSOProcessor implements ServiceConnection {

    /* renamed from: d */
    private String f3030d;

    /* renamed from: e */
    private String f3031e;

    /* renamed from: f */
    private String[] f3032f;

    public SinaWeiboSSOProcessor(SSOAuthorizeActivity sSOAuthorizeActivity) {
        super(sSOAuthorizeActivity);
    }

    /* renamed from: a */
    public void m21597a(String str, String str2, String[] strArr) {
        this.f3030d = str;
        this.f3031e = str2;
        this.f3032f = strArr;
    }

    @Override // cn.sharesdk.framework.authorize.SSOProcessor
    /* renamed from: a */
    public void mo21356a() {
        boolean z;
        Intent intent = new Intent();
        intent.setClassName("com.sina.weibo", "com.sina.weibo.business.RemoteSSOService");
        try {
            z = this.f2859a.getContext().getApplicationContext().bindService(intent, this, 1);
        } catch (Throwable unused) {
            z = false;
        }
        if (z) {
            return;
        }
        this.f2859a.finish();
        if (this.f2861c != null) {
            this.f2861c.onFailed(new Throwable());
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            Class<?> cls = Class.forName("com.sina.sso.RemoteSSO$Stub");
            Method method = cls.getMethod("asInterface", IBinder.class);
            method.setAccessible(true);
            Object invoke = method.invoke(null, iBinder);
            Method method2 = cls.getMethod("getPackageName", new Class[0]);
            method2.setAccessible(true);
            String valueOf = String.valueOf(method2.invoke(invoke, new Object[0]));
            Method method3 = cls.getMethod("getActivityName", new Class[0]);
            method3.setAccessible(true);
            if (m21598a(valueOf, String.valueOf(method3.invoke(invoke, new Object[0])))) {
                return;
            }
            this.f2859a.finish();
            if (this.f2861c != null) {
                this.f2861c.onFailed(new Throwable());
            }
        } catch (Throwable th) {
            this.f2859a.finish();
            if (this.f2861c != null) {
                this.f2861c.onFailed(th);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f2859a.finish();
        if (this.f2861c != null) {
            this.f2861c.onFailed(new Throwable());
        }
    }

    /* renamed from: a */
    private boolean m21598a(String str, String str2) {
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        intent.putExtra("appKey", this.f3030d);
        intent.putExtra("redirectUri", this.f3031e);
        String[] strArr = this.f3032f;
        if (strArr != null && strArr.length > 0) {
            intent.putExtra("scope", TextUtils.join(",", strArr));
        }
        boolean z = false;
        if (m21596b(intent)) {
            try {
                this.f2859a.startActivityForResult(intent, this.f2860b);
                z = true;
            } catch (Throwable unused) {
            }
            this.f2859a.getContext().getApplicationContext().unbindService(this);
            return z;
        }
        return false;
    }

    /* renamed from: b */
    private boolean m21596b(Intent intent) {
        ResolveInfo resolveActivity = this.f2859a.getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return false;
        }
        try {
            for (Signature signature : AppUtils.m21715b(resolveActivity.activityInfo.packageName, 64).signatures) {
                if ("30820295308201fea00302010202044b4ef1bf300d06092a864886f70d010105050030818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c74643020170d3130303131343130323831355a180f32303630303130323130323831355a30818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c746430819f300d06092a864886f70d010101050003818d00308189028181009d367115bc206c86c237bb56c8e9033111889b5691f051b28d1aa8e42b66b7413657635b44786ea7e85d451a12a82a331fced99c48717922170b7fc9bc1040753c0d38b4cf2b22094b1df7c55705b0989441e75913a1a8bd2bc591aa729a1013c277c01c98cbec7da5ad7778b2fad62b85ac29ca28ced588638c98d6b7df5a130203010001300d06092a864886f70d0101050500038181000ad4b4c4dec800bd8fd2991adfd70676fce8ba9692ae50475f60ec468d1b758a665e961a3aedbece9fd4d7ce9295cd83f5f19dc441a065689d9820faedbb7c4a4c4635f5ba1293f6da4b72ed32fb8795f736a20c95cda776402099054fccefb4a1a558664ab8d637288feceba9508aa907fc1fe2b1ae5a0dec954ed831c0bea4".equals(signature.toCharsString())) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // cn.sharesdk.framework.authorize.SSOProcessor
    /* renamed from: a */
    public void mo21355a(int i, int i2, Intent intent) {
        this.f2859a.finish();
        if (i == this.f2860b) {
            switch (i2) {
                case -1:
                    m21595c(intent);
                    return;
                case 0:
                    m21594d(intent);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: c */
    private void m21595c(Intent intent) {
        if (this.f2861c == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("error");
        if (stringExtra == null) {
            stringExtra = intent.getStringExtra("error_type");
        }
        if (stringExtra == null) {
            this.f2861c.onComplete(intent.getExtras());
        } else if (stringExtra.equals("access_denied") || stringExtra.equals("OAuthAccessDeniedException")) {
            this.f2861c.onCancel();
        } else {
            String stringExtra2 = intent.getStringExtra("error_description");
            if (stringExtra2 != null) {
                stringExtra = stringExtra + ": " + stringExtra2;
            }
            this.f2861c.onFailed(new Throwable(stringExtra));
        }
    }

    /* renamed from: d */
    private void m21594d(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("error");
            int intExtra = intent.getIntExtra("error_code", -1);
            Throwable th = new Throwable(stringExtra + " (" + intExtra + ")");
            if (this.f2861c != null) {
                this.f2861c.onFailed(th);
            }
        } else if (this.f2861c != null) {
            this.f2861c.onCancel();
        }
    }
}
