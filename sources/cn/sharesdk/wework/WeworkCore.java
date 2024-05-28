package cn.sharesdk.wework;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wework.WKCallbacks;
import cn.sharesdk.wework.model.BaseMessage;
import cn.sharesdk.wework.model.WKAuthMessage;
import cn.sharesdk.wework.model.WKBaseMessage;
import cn.sharesdk.wework.model.WKBaseRespMessage;
import cn.sharesdk.wework.utils.OpenDataUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wework.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeworkCore {

    /* renamed from: c */
    private static final ArrayList<String> f3311c = new ArrayList<String>() { // from class: cn.sharesdk.wework.a.1
        {
            add("com.tencent.weworklocal");
            add("com.tencent.wework");
            add("com.tencent.wwgovernment");
        }
    };

    /* renamed from: d */
    private static final ArrayList<String> f3312d = new ArrayList<String>() { // from class: cn.sharesdk.wework.a.2
        {
            add("com.tencent.wework");
        }
    };

    /* renamed from: a */
    private Context f3313a;

    /* renamed from: b */
    private String f3314b;

    /* renamed from: e */
    private final Map<String, WKEventHandler> f3315e = new HashMap();

    /* renamed from: f */
    private WKCallbacks.SessionKeyUpdateCallback f3316f = null;

    /* renamed from: g */
    private BroadcastReceiver f3317g = new BroadcastReceiver() { // from class: cn.sharesdk.wework.a.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                if (WeworkCore.this.f3314b.equals(intent.getScheme())) {
                    final BaseMessage m21173b = BaseMessage.m21173b(intent.getData());
                    m21173b.m21175a(WeworkCore.this.f3318h.getString("sk", ""));
                    m21173b.mo21165b(intent.getExtras());
                    final String str = ((WKBaseRespMessage) m21173b).f3362q;
                    if (!TextUtils.isEmpty(str)) {
                        WeworkCore.this.m21200b(str);
                    }
                    if (m21173b instanceof WKBaseRespMessage) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: cn.sharesdk.wework.a.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    SSDKLog.m21740b().m21744a("Wework Auth CallBack start", new Object[0]);
                                    ((WKEventHandler) WeworkCore.this.f3315e.get(m21173b.f3345a)).handleResp(m21173b);
                                } catch (Throwable unused) {
                                }
                                WeworkCore.this.f3315e.remove(m21173b.f3345a);
                                try {
                                    if (TextUtils.isEmpty(str)) {
                                        return;
                                    }
                                    WeworkCore.this.f3316f.onResult(str);
                                } catch (Throwable th) {
                                    SSDKLog.m21740b().m21742a(th);
                                }
                            }
                        });
                    }
                }
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
            }
        }
    };

    /* renamed from: h */
    private SharedPreferences f3318h;

    public WeworkCore(Context context) {
        this.f3318h = null;
        this.f3313a = context;
        this.f3318h = context.getSharedPreferences("wxwork_wwapi_store", 0);
    }

    /* renamed from: a */
    public void m21204a(String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addDataScheme(str);
        intentFilter.addAction(str);
        if (Build.VERSION.SDK_INT < 32) {
            this.f3313a.registerReceiver(this.f3317g, intentFilter);
        } else {
            this.f3313a.registerReceiver(this.f3317g, intentFilter, 2);
        }
        this.f3314b = str;
    }

    /* renamed from: b */
    public void m21200b(String str) {
        this.f3318h.edit().putString("sk", str).commit();
    }

    @SuppressLint({"WrongConstant"})
    /* renamed from: a */
    public boolean m21206a(BaseMessage baseMessage, int i) {
        baseMessage.m21175a(this.f3318h.getString("sk", ""));
        if (baseMessage.mo21167a()) {
            ArrayList<String> arrayList = f3312d;
            if ((baseMessage instanceof WKBaseMessage) && TextUtils.equals(baseMessage.f3347c, "com.tencent.mm")) {
                arrayList = f3311c;
            }
            Context context = this.f3313a;
            if (context != null && TextUtils.equals(context.getPackageName(), "com.tencent.mm")) {
                arrayList = f3311c;
            }
            baseMessage.m21174a(this.f3316f != null);
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (m21197c(next)) {
                    Intent intent = new Intent("com.tencent.wework.apihost");
                    intent.setClassName(next, "com.tencent.wework.apihost.WWAPIActivity");
                    intent.addFlags(411041792);
                    try {
                        baseMessage.m21172b(next);
                        baseMessage.m21177a(this.f3313a);
                        Bundle m21176a = BaseMessage.m21176a(baseMessage, i);
                        long m21164a = OpenDataUtils.m21164a(this.f3313a, this.f3313a.getPackageName(), next, m21176a);
                        if (m21164a > 0) {
                            intent.putExtra("data_id", m21164a);
                            intent.putExtra("data_pkg", this.f3313a.getPackageName());
                        } else {
                            intent.putExtras(m21176a);
                        }
                        if (Build.VERSION.SDK_INT >= 23) {
                            intent.putExtra("PendingIntent", PendingIntent.getBroadcast(this.f3313a, 0, new Intent(this.f3313a, this.f3317g.getClass()), 201326592));
                        } else {
                            intent.putExtra("PendingIntent", PendingIntent.getBroadcast(this.f3313a, 0, new Intent(this.f3313a, this.f3317g.getClass()), 134217728));
                        }
                        baseMessage.mo21169a(intent, next);
                        this.f3313a.startActivity(intent);
                        return true;
                    } catch (Throwable th) {
                        SSDKLog.m21740b().m21742a(th);
                    }
                }
            }
            return false;
        }
        return false;
    }

    /* renamed from: c */
    private boolean m21197c(String str) {
        return "011A40266C8C75D181DDD8E4DDC50075".equals(m21195d(str));
    }

    @SuppressLint({"WrongConstant"})
    /* renamed from: d */
    private String m21195d(String str) {
        try {
            return m21203a(AppUtils.m21715b(str, 64).signatures[0].toByteArray());
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: a */
    private String m21203a(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
            return m21199b(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    private String m21199b(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(Integer.toHexString((b & 240) >>> 4));
            sb.append(Integer.toHexString(b & 15));
        }
        return sb.toString().toUpperCase();
    }

    /* renamed from: a */
    public boolean m21205a(BaseMessage baseMessage, WKEventHandler wKEventHandler) {
        if ((!(baseMessage instanceof WKAuthMessage.C1885a) || m21208a()) && m21206a(baseMessage, 0)) {
            if (baseMessage instanceof WKBaseMessage) {
                this.f3315e.put(baseMessage.f3345a, wKEventHandler);
                return true;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m21208a() {
        for (String str : m21202b()) {
            if (m21197c(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private List<String> m21202b() {
        ArrayList<String> arrayList = f3311c;
        Context context = this.f3313a;
        return (context == null || TextUtils.equals(context.getPackageName(), "com.tencent.mm")) ? arrayList : f3312d;
    }
}
