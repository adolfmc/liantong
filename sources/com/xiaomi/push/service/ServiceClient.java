package com.xiaomi.push.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11169au;
import com.xiaomi.push.C11310dy;
import com.xiaomi.push.C11371fl;
import com.xiaomi.push.C11372fm;
import com.xiaomi.push.C11374fn;
import com.xiaomi.push.C11377fq;
import com.xiaomi.push.C11389fx;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.C11472m;
import com.xiaomi.push.C11479r;
import com.xiaomi.push.C11649x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ServiceClient {

    /* renamed from: a */
    private static ServiceClient f23377a;

    /* renamed from: a */
    private static String f23378a;

    /* renamed from: a */
    private Context f23381a;

    /* renamed from: a */
    private boolean f23384a;

    /* renamed from: b */
    private Messenger f23385b;

    /* renamed from: b */
    private static String f23379b = C11389fx.m3749a(5) + "-";

    /* renamed from: a */
    private static long f23376a = 0;

    /* renamed from: a */
    private Messenger f23382a = null;

    /* renamed from: a */
    private final BroadcastReceiver f23380a = new BroadcastReceiver() { // from class: com.xiaomi.push.service.ServiceClient.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            C11169au.m4854a();
        }
    };

    /* renamed from: a */
    private List<Message> f23383a = new ArrayList();

    /* renamed from: b */
    private boolean f23386b = false;

    public static ServiceClient getInstance(Context context) {
        if (f23377a == null) {
            f23377a = new ServiceClient(context);
        }
        return f23377a;
    }

    public boolean isMiuiPushServiceEnabled() {
        return this.f23384a;
    }

    private ServiceClient(Context context) {
        this.f23384a = false;
        this.f23381a = context.getApplicationContext();
        C11479r.m2931a(this.f23381a);
        m2914a(this.f23381a);
        if (m2915a()) {
            AbstractC11049b.m5270c("use miui push service");
            this.f23384a = true;
        }
    }

    /* renamed from: a */
    private void m2914a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                C11169au.m4851a(context);
            } else {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                intentFilter.addCategory("android.intent.category.DEFAULT");
                C11472m.m2949a(context.getApplicationContext(), this.f23380a, intentFilter, 2);
            }
        } catch (Throwable th) {
            AbstractC11049b.m5282a("add network status listener failed:" + th);
        }
    }

    public void setMessenger(Messenger messenger) {
        this.f23382a = messenger;
    }

    /* renamed from: a */
    private Map<String, String> m2906a(List<NameValuePair> list) {
        HashMap hashMap = new HashMap();
        if (list != null && list.size() > 0) {
            for (NameValuePair nameValuePair : list) {
                if (nameValuePair != null) {
                    hashMap.put(nameValuePair.getName(), nameValuePair.getValue());
                }
            }
        }
        return hashMap;
    }

    @Deprecated
    public int openChannel(String str, String str2, String str3, String str4, String str5, boolean z, List<NameValuePair> list, List<NameValuePair> list2) {
        return openChannel(str, str2, str3, str4, str5, m2906a(list), m2906a(list2), z);
    }

    public int openChannel(String str, String str2, String str3, String str4, String str5, Map<String, String> map, Map<String, String> map2, boolean z) {
        Intent m2918a = m2918a();
        m2918a.setAction(AbstractC11555an.f23578d);
        m2911a(m2918a, str, str2, str3, str4, str5, z, map, map2);
        startServiceSafely(m2918a);
        return 0;
    }

    public boolean sendMessage(C11374fn c11374fn, boolean z) {
        if (C11169au.m4849a(this.f23381a)) {
            Intent m2918a = m2918a();
            String m4069a = C11310dy.m4069a();
            if (!TextUtils.isEmpty(m4069a)) {
                String[] strArr = null;
                C11371fl c11371fl = new C11371fl("pf", null, strArr, strArr);
                C11371fl c11371fl2 = new C11371fl("sent", null, strArr, strArr);
                c11371fl2.m3828a(m4069a);
                c11371fl.m3830a(c11371fl2);
                c11374fn.m3797a(c11371fl);
            }
            Bundle mo3776a = c11374fn.mo3776a();
            if (mo3776a != null) {
                AbstractC11049b.m5270c("SEND:" + c11374fn.mo3775a());
                m2918a.setAction(AbstractC11555an.f23579e);
                m2918a.putExtra(AbstractC11555an.f23566J, f23378a);
                m2918a.putExtra("ext_packet", mo3776a);
                m2918a.putExtra("ext_encrypt", z);
                return startServiceSafely(m2918a);
            }
            return false;
        }
        return false;
    }

    public boolean notifyMessage(Bundle bundle, String str, String str2) {
        if (bundle == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            AbstractC11049b.m5282a("Failed to notify message: bundle|userId|chid may be empty");
            return false;
        }
        Intent m2918a = m2918a();
        if (bundle != null) {
            m2918a.setAction(AbstractC11555an.f23589o);
            m2918a.putExtras(bundle);
            AbstractC11049b.m5266e("notify: chid=" + str2 + " bundle:" + bundle);
            return startServiceSafely(m2918a);
        }
        return false;
    }

    public boolean sendMessage(byte[] bArr, String str, String str2) {
        String str3;
        if (!C11169au.m4849a(this.f23381a) || bArr == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            AbstractC11049b.m5282a("Failed to send message: message|userId|chid may be empty, or the network is unavailable.");
            return false;
        }
        Intent m2918a = m2918a();
        if (bArr != null) {
            m2918a.setAction(AbstractC11555an.f23579e);
            m2918a.putExtra(AbstractC11555an.f23566J, f23378a);
            m2918a.putExtra("ext_raw_packet", bArr);
            int indexOf = str.indexOf("@");
            String str4 = null;
            String substring = indexOf != -1 ? str.substring(0, indexOf) : null;
            int lastIndexOf = str.lastIndexOf("/");
            if (lastIndexOf != -1) {
                str4 = str.substring(indexOf + 1, lastIndexOf);
                str3 = str.substring(lastIndexOf + 1);
            } else {
                str3 = null;
            }
            m2918a.putExtra(AbstractC11555an.f23593s, substring);
            m2918a.putExtra(AbstractC11555an.f23594t, str4);
            m2918a.putExtra(AbstractC11555an.f23595u, str3);
            StringBuilder sb = new StringBuilder();
            sb.append(f23379b);
            long j = f23376a;
            f23376a = 1 + j;
            sb.append(j);
            String sb2 = sb.toString();
            m2918a.putExtra("ext_pkt_id", sb2);
            m2918a.putExtra("ext_chid", str2);
            AbstractC11049b.m5266e("SEND: chid=" + str2 + ", packetId=" + sb2);
            return startServiceSafely(m2918a);
        }
        return false;
    }

    public boolean batchSendMessage(C11374fn[] c11374fnArr, boolean z) {
        if (C11169au.m4849a(this.f23381a)) {
            Intent m2918a = m2918a();
            Bundle[] bundleArr = new Bundle[c11374fnArr.length];
            for (int i = 0; i < c11374fnArr.length; i++) {
                String m4069a = C11310dy.m4069a();
                if (!TextUtils.isEmpty(m4069a)) {
                    String[] strArr = null;
                    C11371fl c11371fl = new C11371fl("pf", null, strArr, strArr);
                    C11371fl c11371fl2 = new C11371fl("sent", null, strArr, strArr);
                    c11371fl2.m3828a(m4069a);
                    c11371fl.m3830a(c11371fl2);
                    c11374fnArr[i].m3797a(c11371fl);
                }
                AbstractC11049b.m5270c("SEND:" + c11374fnArr[i].mo3775a());
                bundleArr[i] = c11374fnArr[i].mo3776a();
            }
            if (bundleArr.length > 0) {
                m2918a.setAction(AbstractC11555an.f23581g);
                m2918a.putExtra(AbstractC11555an.f23566J, f23378a);
                m2918a.putExtra("ext_packets", bundleArr);
                m2918a.putExtra("ext_encrypt", z);
                return startServiceSafely(m2918a);
            }
            return false;
        }
        return false;
    }

    public boolean sendIQ(C11372fm c11372fm) {
        if (C11169au.m4849a(this.f23381a)) {
            Intent m2918a = m2918a();
            Bundle mo3776a = c11372fm.mo3776a();
            if (mo3776a != null) {
                AbstractC11049b.m5270c("SEND:" + c11372fm.mo3775a());
                m2918a.setAction(AbstractC11555an.f23580f);
                m2918a.putExtra(AbstractC11555an.f23566J, f23378a);
                m2918a.putExtra("ext_packet", mo3776a);
                return startServiceSafely(m2918a);
            }
            return false;
        }
        return false;
    }

    public boolean sendPresence(C11377fq c11377fq) {
        if (C11169au.m4849a(this.f23381a)) {
            Intent m2918a = m2918a();
            Bundle mo3776a = c11377fq.mo3776a();
            if (mo3776a != null) {
                AbstractC11049b.m5270c("SEND:" + c11377fq.mo3775a());
                m2918a.setAction(AbstractC11555an.f23582h);
                m2918a.putExtra(AbstractC11555an.f23566J, f23378a);
                m2918a.putExtra("ext_packet", mo3776a);
                return startServiceSafely(m2918a);
            }
            return false;
        }
        return false;
    }

    public boolean closeChannel() {
        Intent m2918a = m2918a();
        m2918a.setAction(AbstractC11555an.f23583i);
        return startServiceSafely(m2918a);
    }

    public boolean closeChannel(String str) {
        Intent m2918a = m2918a();
        m2918a.setAction(AbstractC11555an.f23583i);
        m2918a.putExtra(AbstractC11555an.f23596v, str);
        return startServiceSafely(m2918a);
    }

    public boolean closeChannel(String str, String str2) {
        Intent m2918a = m2918a();
        m2918a.setAction(AbstractC11555an.f23583i);
        m2918a.putExtra(AbstractC11555an.f23596v, str);
        m2918a.putExtra(AbstractC11555an.f23593s, str2);
        return startServiceSafely(m2918a);
    }

    @Deprecated
    public boolean forceReconnection(String str, String str2, String str3, String str4, String str5, boolean z, List<NameValuePair> list, List<NameValuePair> list2) {
        return forceReconnection(str, str2, str3, str4, str5, z, m2906a(list), m2906a(list2));
    }

    public boolean forceReconnection(String str, String str2, String str3, String str4, String str5, boolean z, Map<String, String> map, Map<String, String> map2) {
        Intent m2918a = m2918a();
        m2918a.setAction(AbstractC11555an.f23584j);
        m2911a(m2918a, str, str2, str3, str4, str5, z, map, map2);
        return startServiceSafely(m2918a);
    }

    @Deprecated
    public void resetConnection(String str, String str2, String str3, String str4, String str5, boolean z, List<NameValuePair> list, List<NameValuePair> list2) {
        resetConnection(str, str2, str3, str4, str5, z, m2906a(list), m2906a(list2));
    }

    public void resetConnection(String str, String str2, String str3, String str4, String str5, boolean z, Map<String, String> map, Map<String, String> map2) {
        Intent m2918a = m2918a();
        m2918a.setAction(AbstractC11555an.f23585k);
        m2911a(m2918a, str, str2, str3, str4, str5, z, map, map2);
        startServiceSafely(m2918a);
    }

    @Deprecated
    public void updateChannelInfo(String str, List<NameValuePair> list, List<NameValuePair> list2) {
        updateChannelInfo(str, m2906a(list), m2906a(list2));
    }

    public void updateChannelInfo(String str, Map<String, String> map, Map<String, String> map2) {
        Intent m2918a = m2918a();
        m2918a.setAction(AbstractC11555an.f23586l);
        if (map != null) {
            String m2905a = m2905a(map);
            if (!TextUtils.isEmpty(m2905a)) {
                m2918a.putExtra(AbstractC11555an.f23560D, m2905a);
            }
        }
        if (map2 != null) {
            String m2905a2 = m2905a(map2);
            if (!TextUtils.isEmpty(m2905a2)) {
                m2918a.putExtra(AbstractC11555an.f23561E, m2905a2);
            }
        }
        m2918a.putExtra(AbstractC11555an.f23596v, str);
        startServiceSafely(m2918a);
    }

    /* renamed from: a */
    private void m2911a(Intent intent, String str, String str2, String str3, String str4, String str5, boolean z, Map<String, String> map, Map<String, String> map2) {
        intent.putExtra(AbstractC11555an.f23593s, str);
        intent.putExtra(AbstractC11555an.f23596v, str2);
        intent.putExtra(AbstractC11555an.f23600z, str3);
        intent.putExtra(AbstractC11555an.f23558B, str5);
        intent.putExtra(AbstractC11555an.f23557A, str4);
        intent.putExtra(AbstractC11555an.f23559C, z);
        intent.putExtra(AbstractC11555an.f23566J, f23378a);
        intent.putExtra(AbstractC11555an.f23570N, this.f23382a);
        if (map != null && map.size() > 0) {
            String m2905a = m2905a(map);
            if (!TextUtils.isEmpty(m2905a)) {
                intent.putExtra(AbstractC11555an.f23560D, m2905a);
            }
        }
        if (map2 == null || map2.size() <= 0) {
            return;
        }
        String m2905a2 = m2905a(map2);
        if (TextUtils.isEmpty(m2905a2)) {
            return;
        }
        intent.putExtra(AbstractC11555an.f23561E, m2905a2);
    }

    /* renamed from: a */
    private String m2905a(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append(":");
            sb.append(entry.getValue());
            if (i < map.size()) {
                sb.append(",");
            }
            i++;
        }
        return sb.toString();
    }

    /* renamed from: a */
    private boolean m2915a() {
        if (C11649x.f23812a) {
            return false;
        }
        try {
            PackageInfo packageInfo = this.f23381a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode >= 104;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    private String m2917a() {
        try {
            return this.f23381a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 106 ? "com.xiaomi.push.service.XMPushService" : "com.xiaomi.xmsf.push.service.XMPushService";
        } catch (Exception unused) {
            return "com.xiaomi.xmsf.push.service.XMPushService";
        }
    }

    /* renamed from: a */
    private Intent m2918a() {
        if (isMiuiPushServiceEnabled()) {
            Intent intent = new Intent();
            intent.setPackage("com.xiaomi.xmsf");
            intent.setClassName("com.xiaomi.xmsf", m2917a());
            intent.putExtra(AbstractC11555an.f23562F, this.f23381a.getPackageName());
            m2916a();
            return intent;
        }
        Intent intent2 = new Intent(this.f23381a, XMPushService.class);
        intent2.putExtra(AbstractC11555an.f23562F, this.f23381a.getPackageName());
        m2904b();
        return intent2;
    }

    public static String getSession() {
        return f23378a;
    }

    public static void setSession(String str) {
        f23378a = str;
    }

    /* renamed from: a */
    private void m2916a() {
        this.f23381a.getPackageManager().setComponentEnabledSetting(new ComponentName(this.f23381a, XMPushService.class), 2, 1);
    }

    /* renamed from: b */
    private void m2904b() {
        this.f23381a.getPackageManager().setComponentEnabledSetting(new ComponentName(this.f23381a, XMPushService.class), 1, 1);
    }

    public void checkAlive() {
        Intent m2918a = m2918a();
        m2918a.setAction("com.xiaomi.push.check_alive");
        startServiceSafely(m2918a);
    }

    public boolean startServiceSafely(Intent intent) {
        try {
            if (!C11469j.m2974a() && Build.VERSION.SDK_INT >= 26) {
                m2912a(intent);
                return true;
            }
            this.f23381a.startService(intent);
            return true;
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
            return false;
        }
    }

    /* renamed from: a */
    private synchronized void m2912a(Intent intent) {
        if (this.f23386b) {
            Message m2913a = m2913a(intent);
            if (this.f23383a.size() >= 50) {
                this.f23383a.remove(0);
            }
            this.f23383a.add(m2913a);
            return;
        }
        if (this.f23385b == null) {
            Context context = this.f23381a;
            ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.xiaomi.push.service.ServiceClient.2
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    synchronized (ServiceClient.this) {
                        ServiceClient.this.f23385b = new Messenger(iBinder);
                        ServiceClient.this.f23386b = false;
                        for (Message message : ServiceClient.this.f23383a) {
                            try {
                                ServiceClient.this.f23385b.send(message);
                            } catch (RemoteException e) {
                                AbstractC11049b.m5276a(e);
                            }
                        }
                        ServiceClient.this.f23383a.clear();
                    }
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    ServiceClient.this.f23385b = null;
                    ServiceClient.this.f23386b = false;
                }
            };
            Context context2 = this.f23381a;
            context.bindService(intent, serviceConnection, 1);
            this.f23386b = true;
            this.f23383a.clear();
            this.f23383a.add(m2913a(intent));
        } else {
            try {
                this.f23385b.send(m2913a(intent));
            } catch (RemoteException unused) {
                this.f23385b = null;
                this.f23386b = false;
            }
        }
    }

    /* renamed from: a */
    private Message m2913a(Intent intent) {
        Message obtain = Message.obtain();
        obtain.what = 17;
        obtain.obj = intent;
        return obtain;
    }
}
