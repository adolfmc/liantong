package com.huawei.hms.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.api.BindingFailedResolution;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Util;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BinderAdapter implements ServiceConnection {

    /* renamed from: a */
    private final Context f10941a;

    /* renamed from: b */
    private final String f10942b;

    /* renamed from: c */
    private final String f10943c;

    /* renamed from: d */
    private BinderCallBack f10944d;

    /* renamed from: e */
    private IBinder f10945e;

    /* renamed from: f */
    private final Object f10946f = new Object();

    /* renamed from: g */
    private boolean f10947g = false;

    /* renamed from: h */
    private Handler f10948h = null;

    /* renamed from: i */
    private Handler f10949i = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface BinderCallBack {
        void onBinderFailed(int i);

        void onBinderFailed(int i, Intent intent);

        void onNullBinding(ComponentName componentName);

        void onServiceConnected(ComponentName componentName, IBinder iBinder);

        void onServiceDisconnected(ComponentName componentName);

        void onTimedDisconnected();
    }

    public BinderAdapter(Context context, String str, String str2) {
        this.f10941a = context;
        this.f10942b = str;
        this.f10943c = str2;
    }

    /* renamed from: c */
    private void m15259c() {
        synchronized (this.f10946f) {
            Handler handler = this.f10948h;
            if (handler != null) {
                handler.removeMessages(getConnTimeOut());
                this.f10948h = null;
            }
        }
    }

    /* renamed from: d */
    private void m15258d() {
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.huawei.hms.adapter.BinderAdapter.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (message == null || message.what != BinderAdapter.this.getMsgDelayDisconnect()) {
                    return false;
                }
                HMSLog.m14110i("BinderAdapter", "The serviceConnection has been bind for 1800s, need to unbind.");
                BinderAdapter.this.unBind();
                BinderCallBack m15256f = BinderAdapter.this.m15256f();
                if (m15256f != null) {
                    m15256f.onTimedDisconnected();
                    return true;
                }
                return true;
            }
        });
        this.f10949i = handler;
        handler.sendEmptyMessageDelayed(getMsgDelayDisconnect(), 1800000L);
    }

    /* renamed from: e */
    private void m15257e() {
        HMSLog.m14112e("BinderAdapter", "In connect, bind core service fail");
        try {
            ComponentName componentName = new ComponentName(this.f10941a.getApplicationInfo().packageName, "com.huawei.hms.activity.BridgeActivity");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.putExtra(BridgeActivity.EXTRA_DELEGATE_CLASS_NAME, BindingFailedResolution.class.getName());
            BinderCallBack m15256f = m15256f();
            if (m15256f != null) {
                m15256f.onBinderFailed(-1, intent);
            }
        } catch (RuntimeException e) {
            HMSLog.m14112e("BinderAdapter", "getBindFailPendingIntent failed " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public BinderCallBack m15256f() {
        return this.f10944d;
    }

    /* renamed from: g */
    private void m15255g() {
        Handler handler = this.f10948h;
        if (handler != null) {
            handler.removeMessages(getConnTimeOut());
        } else {
            this.f10948h = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.huawei.hms.adapter.BinderAdapter.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    if (message == null || message.what != BinderAdapter.this.getConnTimeOut()) {
                        return false;
                    }
                    HMSLog.m14112e("BinderAdapter", "In connect, bind core service time out");
                    BinderAdapter.this.m15261b();
                    return true;
                }
            });
        }
        this.f10948h.sendEmptyMessageDelayed(getConnTimeOut(), 10000L);
    }

    /* renamed from: h */
    private void m15254h() {
        HMSLog.m14115d("BinderAdapter", "removeDelayDisconnectTask.");
        synchronized (BinderAdapter.class) {
            Handler handler = this.f10949i;
            if (handler != null) {
                handler.removeMessages(getMsgDelayDisconnect());
            }
        }
    }

    public void binder(BinderCallBack binderCallBack) {
        if (binderCallBack == null) {
            return;
        }
        this.f10944d = binderCallBack;
        m15263a();
    }

    protected int getConnTimeOut() {
        return 0;
    }

    protected int getMsgDelayDisconnect() {
        return 0;
    }

    public String getServiceAction() {
        return this.f10942b;
    }

    public IBinder getServiceBinder() {
        return this.f10945e;
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        HMSLog.m14112e("BinderAdapter", "Enter onNullBinding, than unBind.");
        if (this.f10947g) {
            this.f10947g = false;
            return;
        }
        unBind();
        m15259c();
        BinderCallBack m15256f = m15256f();
        if (m15256f != null) {
            m15256f.onNullBinding(componentName);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HMSLog.m14110i("BinderAdapter", "BinderAdapter Enter onServiceConnected.");
        this.f10945e = iBinder;
        m15259c();
        BinderCallBack m15256f = m15256f();
        if (m15256f != null) {
            m15256f.onServiceConnected(componentName, iBinder);
        }
        m15258d();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        HMSLog.m14110i("BinderAdapter", "Enter onServiceDisconnected.");
        BinderCallBack m15256f = m15256f();
        if (m15256f != null) {
            m15256f.onServiceDisconnected(componentName);
        }
        m15254h();
    }

    public void unBind() {
        Util.unBindServiceCatchException(this.f10941a, this);
    }

    public void updateDelayTask() {
        HMSLog.m14115d("BinderAdapter", "updateDelayTask.");
        synchronized (BinderAdapter.class) {
            Handler handler = this.f10949i;
            if (handler != null) {
                handler.removeMessages(getMsgDelayDisconnect());
                this.f10949i.sendEmptyMessageDelayed(getMsgDelayDisconnect(), 1800000L);
            }
        }
    }

    /* renamed from: a */
    private void m15263a() {
        if (TextUtils.isEmpty(this.f10942b) || TextUtils.isEmpty(this.f10943c)) {
            m15257e();
        }
        Intent intent = new Intent(this.f10942b);
        try {
            intent.setPackage(this.f10943c);
        } catch (IllegalArgumentException unused) {
            HMSLog.m14112e("BinderAdapter", "IllegalArgumentException when bindCoreService intent.setPackage");
            m15257e();
        }
        synchronized (this.f10946f) {
            if (this.f10941a.bindService(intent, this, 1)) {
                m15255g();
                return;
            }
            this.f10947g = true;
            m15257e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m15261b() {
        BinderCallBack m15256f = m15256f();
        if (m15256f != null) {
            m15256f.onBinderFailed(-1);
        }
    }
}
