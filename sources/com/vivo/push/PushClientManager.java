package com.vivo.push;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.vivo.push.p368b.AliasCommand;
import com.vivo.push.p368b.AppCommand;
import com.vivo.push.p368b.BaseAppCommand;
import com.vivo.push.p368b.InitCommand;
import com.vivo.push.p368b.OnLogReceiveCommand;
import com.vivo.push.p368b.TagCommand;
import com.vivo.push.p373f.OnReceiveTask;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.sdk.PushMessageCallback;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.PushPackageUtils;
import com.vivo.push.util.SharePreferenceManager;
import com.vivo.push.util.Utility;
import com.vivo.push.util.VivoPushException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

@NBSInstrumented
/* renamed from: com.vivo.push.m */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class PushClientManager {

    /* renamed from: a */
    private static volatile PushClientManager f21032a;

    /* renamed from: h */
    private Context f21039h;

    /* renamed from: j */
    private String f21041j;

    /* renamed from: m */
    private Boolean f21044m;

    /* renamed from: n */
    private Long f21045n;

    /* renamed from: o */
    private boolean f21046o;

    /* renamed from: q */
    private int f21048q;

    /* renamed from: b */
    private long f21033b = -1;

    /* renamed from: c */
    private long f21034c = -1;

    /* renamed from: d */
    private long f21035d = -1;

    /* renamed from: e */
    private long f21036e = -1;

    /* renamed from: f */
    private long f21037f = -1;

    /* renamed from: g */
    private long f21038g = -1;

    /* renamed from: i */
    private boolean f21040i = true;

    /* renamed from: k */
    private SparseArray<C10963a> f21042k = new SparseArray<>();

    /* renamed from: l */
    private int f21043l = 0;

    /* renamed from: p */
    private IPushClientFactory f21047p = new PushClientFactory();

    private PushClientManager() {
    }

    /* renamed from: a */
    public static synchronized PushClientManager m5648a() {
        PushClientManager pushClientManager;
        synchronized (PushClientManager.class) {
            if (f21032a == null) {
                f21032a = new PushClientManager();
            }
            pushClientManager = f21032a;
        }
        return pushClientManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public final void m5629b() throws VivoPushException {
        Context context = this.f21039h;
        if (context != null) {
            Utility.m5437b(context);
        }
    }

    /* renamed from: a */
    public final synchronized void m5646a(Context context) {
        if (this.f21039h == null) {
            this.f21039h = ContextDelegate.getContext(context);
            this.f21046o = PushPackageUtils.m5464c(context, context.getPackageName());
            SharePreferenceManager.m5455b().m5456a(this.f21039h);
            m5638a(new InitCommand());
            this.f21041j = PushClientController.m5593a().m5588e().mo5527i();
        }
    }

    /* renamed from: c */
    public static List<String> m5622c() {
        String mo5529g = PushClientController.m5593a().m5588e().mo5529g();
        ArrayList arrayList = new ArrayList();
        try {
        } catch (JSONException unused) {
            PushClientController.m5593a().m5588e().mo5528h();
            arrayList.clear();
            LogUtil.m5341d("PushClientManager", "getTags error");
        }
        if (TextUtils.isEmpty(mo5529g)) {
            return arrayList;
        }
        Iterator<String> keys = new JSONObject(mo5529g).keys();
        while (keys.hasNext()) {
            arrayList.add(keys.next());
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m5631a(List<String> list) {
        JSONObject jSONObject;
        try {
            if (list.size() <= 0) {
                return;
            }
            String mo5529g = PushClientController.m5593a().m5588e().mo5529g();
            if (TextUtils.isEmpty(mo5529g)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(mo5529g);
            }
            for (String str : list) {
                jSONObject.put(str, System.currentTimeMillis());
            }
            String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            if (TextUtils.isEmpty(jSONObject2)) {
                PushClientController.m5593a().m5588e().mo5528h();
            } else {
                PushClientController.m5593a().m5588e().mo5533d(jSONObject2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            PushClientController.m5593a().m5588e().mo5528h();
        }
    }

    /* renamed from: b */
    public static void m5623b(List<String> list) {
        JSONObject jSONObject;
        try {
            if (list.size() <= 0) {
                return;
            }
            String mo5529g = PushClientController.m5593a().m5588e().mo5529g();
            if (TextUtils.isEmpty(mo5529g)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(mo5529g);
            }
            for (String str : list) {
                jSONObject.remove(str);
            }
            String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            if (TextUtils.isEmpty(jSONObject2)) {
                PushClientController.m5593a().m5588e().mo5528h();
            } else {
                PushClientController.m5593a().m5588e().mo5533d(jSONObject2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            PushClientController.m5593a().m5588e().mo5528h();
        }
    }

    /* renamed from: d */
    public final boolean m5618d() {
        if (this.f21039h == null) {
            LogUtil.m5341d("PushClientManager", "support:context is null");
            return false;
        }
        this.f21044m = Boolean.valueOf(m5610l());
        return this.f21044m.booleanValue();
    }

    /* renamed from: c */
    public final void m5619c(List<String> list) {
        if (list.contains(this.f21041j)) {
            m5617e();
        }
    }

    /* renamed from: e */
    public final void m5617e() {
        this.f21041j = null;
        PushClientController.m5593a().m5588e().mo5526j();
    }

    /* renamed from: f */
    public final boolean m5616f() {
        return this.f21046o;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m5630a(boolean z) {
        this.f21040i = z;
    }

    /* renamed from: g */
    public final boolean m5615g() {
        return this.f21040i;
    }

    /* renamed from: h */
    public final Context m5614h() {
        return this.f21039h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m5644a(IPushActionListener iPushActionListener, String str, String str2) {
        if (this.f21039h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
        } else if (m5634a(str, str2)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(10001);
            }
        } else {
            PushClientController.m5593a().m5585h().mo5312b();
            if (!m5647a(this.f21033b)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                    return;
                }
                return;
            }
            this.f21033b = SystemClock.elapsedRealtime();
            String packageName = this.f21039h.getPackageName();
            C10963a c10963a = null;
            if (this.f21039h != null) {
                AppCommand appCommand = new AppCommand(true, packageName);
                appCommand.m5804e();
                appCommand.m5807c(str);
                appCommand.m5805d(str2);
                appCommand.m5809b(100);
                if (this.f21046o) {
                    if (m5610l()) {
                        c10963a = m5641a(appCommand, iPushActionListener);
                    } else if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(101);
                    }
                } else {
                    c10963a = m5641a(appCommand, iPushActionListener);
                }
            } else if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
            if (c10963a == null) {
                return;
            }
            c10963a.m5607a(new C10964n(this, c10963a, str, str2));
            c10963a.m5609a();
        }
    }

    /* renamed from: a */
    private C10963a m5641a(AppCommand appCommand, IPushActionListener iPushActionListener) {
        C10963a c10963a = new C10963a(appCommand, iPushActionListener);
        String m5640a = m5640a(c10963a);
        appCommand.m5808b(m5640a);
        c10963a.m5606a(new RunnableC10965o(this, appCommand, m5640a));
        return c10963a;
    }

    /* renamed from: b */
    public final void m5628b(IPushActionListener iPushActionListener, String str, String str2) {
        m5643a(iPushActionListener, str, str2, 11);
    }

    /* renamed from: c */
    public final void m5621c(IPushActionListener iPushActionListener, String str, String str2) {
        m5643a(iPushActionListener, str, str2, 1);
    }

    /* renamed from: a */
    private void m5643a(IPushActionListener iPushActionListener, String str, String str2, int i) {
        if (this.f21039h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
        } else if (m5634a(str, str2)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(10001);
            }
        } else if (!m5647a(this.f21034c)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
            }
        } else {
            this.f21034c = SystemClock.elapsedRealtime();
            C10963a m5642a = m5642a(iPushActionListener, this.f21039h.getPackageName(), str, str2, i);
            if (m5642a == null) {
                return;
            }
            m5642a.m5607a(new C10966p(this));
            m5642a.m5609a();
        }
    }

    /* renamed from: a */
    private C10963a m5642a(IPushActionListener iPushActionListener, String str, String str2, String str3, int i) {
        if (this.f21039h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
            return null;
        }
        AppCommand appCommand = new AppCommand(false, str);
        appCommand.m5807c(str2);
        appCommand.m5805d(str3);
        if (i > 0) {
            appCommand.m5811a(i);
        }
        appCommand.m5804e();
        appCommand.m5809b(100);
        if (this.f21046o) {
            if (!m5610l()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                }
                return null;
            }
            C10963a c10963a = new C10963a(appCommand, iPushActionListener);
            String m5640a = m5640a(c10963a);
            appCommand.m5808b(m5640a);
            c10963a.m5606a(new RunnableC10967q(this, appCommand, m5640a));
            return c10963a;
        } else if (appCommand.m5810a(this.f21039h) == 2) {
            return m5641a(appCommand, iPushActionListener);
        } else {
            return m5641a(appCommand, iPushActionListener);
        }
    }

    /* renamed from: a */
    public final void m5635a(String str, int i, Object... objArr) {
        C10963a m5626b = m5626b(str);
        if (m5626b != null) {
            m5626b.m5608a(i, objArr);
        } else {
            LogUtil.m5341d("PushClientManager", "notifyApp token is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m5633a(String str, String str2, String str3, IPushActionListener iPushActionListener) {
        if (this.f21039h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
        } else if (!TextUtils.isEmpty(this.f21041j) && this.f21041j.equals(str)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
            }
        } else if (TextUtils.isEmpty(str)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(30002);
            }
        } else if (str.length() > 70) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(30003);
            }
        } else if (!m5647a(this.f21035d)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
            }
        } else {
            if (this.f21046o) {
                if (!m5610l()) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(101);
                        return;
                    }
                    return;
                } else if (TextUtils.isEmpty(PushClientController.m5593a().m5585h().mo5312b())) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(30001);
                        return;
                    }
                    return;
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            AliasCommand aliasCommand = new AliasCommand(true, this.f21039h.getPackageName(), arrayList);
            aliasCommand.m5809b(100);
            aliasCommand.m5807c(str2);
            aliasCommand.m5805d(str3);
            this.f21035d = SystemClock.elapsedRealtime();
            String m5640a = m5640a(new C10963a(aliasCommand, iPushActionListener));
            aliasCommand.m5808b(m5640a);
            m5638a(aliasCommand);
            m5620c(m5640a);
        }
    }

    /* renamed from: a */
    private static boolean m5647a(long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        return j == -1 || elapsedRealtime <= j || elapsedRealtime >= j + 2000;
    }

    /* renamed from: a */
    private static boolean m5634a(String str, String str2) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m5625b(String str, String str2, String str3, IPushActionListener iPushActionListener) {
        if (this.f21039h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
        } else if (TextUtils.isEmpty(this.f21041j)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
            }
        } else if (TextUtils.isEmpty(str)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(30002);
            }
        } else if (str.length() > 70) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(30003);
            }
        } else if (!m5647a(this.f21036e)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
            }
        } else {
            if (this.f21046o) {
                if (!m5610l()) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(101);
                        return;
                    }
                    return;
                } else if (TextUtils.isEmpty(PushClientController.m5593a().m5585h().mo5312b())) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(30001);
                        return;
                    }
                    return;
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            AliasCommand aliasCommand = new AliasCommand(false, this.f21039h.getPackageName(), arrayList);
            aliasCommand.m5809b(100);
            aliasCommand.m5807c(str2);
            aliasCommand.m5805d(str3);
            this.f21036e = SystemClock.elapsedRealtime();
            String m5640a = m5640a(new C10963a(aliasCommand, iPushActionListener));
            aliasCommand.m5808b(m5640a);
            m5638a(aliasCommand);
            m5620c(m5640a);
        }
    }

    /* renamed from: i */
    public final String m5613i() {
        return this.f21041j;
    }

    /* renamed from: a */
    public final void m5637a(String str) {
        this.f21041j = str;
        PushClientController.m5593a().m5588e().mo5531e(str);
    }

    /* renamed from: a */
    public final void m5636a(String str, int i) {
        C10963a m5626b = m5626b(str);
        if (m5626b != null) {
            m5626b.m5608a(i, new Object[0]);
        } else {
            LogUtil.m5341d("PushClientManager", "notifyStatusChanged token is null");
        }
    }

    /* renamed from: a */
    private synchronized String m5640a(C10963a c10963a) {
        int i;
        this.f21042k.put(this.f21043l, c10963a);
        i = this.f21043l;
        this.f21043l = i + 1;
        return Integer.toString(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public synchronized C10963a m5626b(String str) {
        if (str != null) {
            try {
                int parseInt = Integer.parseInt(str);
                C10963a c10963a = this.f21042k.get(parseInt);
                this.f21042k.delete(parseInt);
                return c10963a;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m5632a(ArrayList<String> arrayList, String str, String str2, IPushActionListener iPushActionListener) {
        if (this.f21039h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
        } else if (!m5647a(this.f21037f)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
            }
        } else {
            this.f21037f = SystemClock.elapsedRealtime();
            if (arrayList.size() < 0) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(IjkMediaPlayer.FFP_PROP_INT64_SELECTED_AUDIO_STREAM);
                    return;
                }
                return;
            }
            if (arrayList.size() + m5622c().size() > 500) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(IjkMediaPlayer.FFP_PROP_INT64_AUDIO_DECODER);
                    return;
                }
                return;
            }
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                if (it.next().length() > 70) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_DECODER);
                        return;
                    }
                    return;
                }
            }
            if (this.f21046o) {
                if (!m5610l()) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(101);
                        return;
                    }
                    return;
                } else if (TextUtils.isEmpty(PushClientController.m5593a().m5585h().mo5312b())) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(20001);
                        return;
                    }
                    return;
                }
            }
            TagCommand tagCommand = new TagCommand(true, this.f21039h.getPackageName(), arrayList);
            tagCommand.m5809b(500);
            tagCommand.m5807c(str);
            tagCommand.m5805d(str2);
            String m5640a = m5640a(new C10963a(tagCommand, iPushActionListener));
            tagCommand.m5808b(m5640a);
            m5638a(tagCommand);
            m5620c(m5640a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m5624b(ArrayList<String> arrayList, String str, String str2, IPushActionListener iPushActionListener) {
        if (this.f21039h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
        } else if (!m5647a(this.f21038g)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
            }
        } else {
            this.f21038g = SystemClock.elapsedRealtime();
            if (arrayList.size() < 0) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(IjkMediaPlayer.FFP_PROP_INT64_SELECTED_AUDIO_STREAM);
                    return;
                }
                return;
            }
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                if (it.next().length() > 70) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_DECODER);
                        return;
                    }
                    return;
                }
            }
            if (this.f21046o) {
                if (!m5610l()) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(101);
                        return;
                    }
                    return;
                } else if (TextUtils.isEmpty(PushClientController.m5593a().m5585h().mo5312b())) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(20001);
                        return;
                    }
                    return;
                }
            }
            TagCommand tagCommand = new TagCommand(false, this.f21039h.getPackageName(), arrayList);
            tagCommand.m5809b(500);
            tagCommand.m5807c(str);
            tagCommand.m5805d(str2);
            String m5640a = m5640a(new C10963a(tagCommand, iPushActionListener));
            tagCommand.m5808b(m5640a);
            m5638a(tagCommand);
            m5620c(m5640a);
        }
    }

    /* renamed from: a */
    public final int m5645a(Intent intent, PushMessageCallback pushMessageCallback) {
        PushCommand createReceiverCommand = this.f21047p.createReceiverCommand(intent);
        Context context = m5648a().f21039h;
        if (createReceiverCommand == null) {
            LogUtil.m5354a("PushClientManager", "sendCommand, null command!");
            if (context != null) {
                LogUtil.m5343c(context, "[执行指令失败]指令空！");
                return 2805;
            }
            return 2805;
        }
        OnReceiveTask createReceiveTask = this.f21047p.createReceiveTask(createReceiverCommand);
        if (createReceiveTask == null) {
            LogUtil.m5354a("PushClientManager", "sendCommand, null command task! pushCommand = ".concat(String.valueOf(createReceiverCommand)));
            if (context != null) {
                LogUtil.m5343c(context, "[执行指令失败]指令" + createReceiverCommand + "任务空！");
                return 2806;
            }
            return 2806;
        }
        if (context != null && !(createReceiverCommand instanceof OnLogReceiveCommand)) {
            LogUtil.m5356a(context, "[接收指令]".concat(String.valueOf(createReceiverCommand)));
        }
        createReceiveTask.m5700a(pushMessageCallback);
        createReceiveTask.run();
        return createReceiveTask.m5697c();
    }

    /* renamed from: a */
    public final void m5638a(PushCommand pushCommand) {
        Context context = m5648a().f21039h;
        if (pushCommand == null) {
            LogUtil.m5354a("PushClientManager", "sendCommand, null command!");
            if (context != null) {
                LogUtil.m5343c(context, "[执行指令失败]指令空！");
                return;
            }
            return;
        }
        PushClientTask createTask = this.f21047p.createTask(pushCommand);
        if (createTask == null) {
            LogUtil.m5354a("PushClientManager", "sendCommand, null command task! pushCommand = ".concat(String.valueOf(pushCommand)));
            if (context != null) {
                LogUtil.m5343c(context, "[执行指令失败]指令" + pushCommand + "任务空！");
                return;
            }
            return;
        }
        LogUtil.m5341d("PushClientManager", "client--sendCommand, command = ".concat(String.valueOf(pushCommand)));
        PushClientThread.m5483a(createTask);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m5620c(String str) {
        PushClientThread.m5482a(new RunnableC10968r(this, str));
    }

    /* renamed from: j */
    public final int m5612j() {
        return this.f21048q;
    }

    /* renamed from: k */
    public final long m5611k() {
        Context context = this.f21039h;
        if (context == null) {
            return -1L;
        }
        if (this.f21045n == null) {
            this.f21045n = Long.valueOf(Utility.m5448a(context));
        }
        return this.f21045n.longValue();
    }

    /* renamed from: l */
    private boolean m5610l() {
        if (this.f21044m == null) {
            this.f21044m = Boolean.valueOf(m5611k() >= 1230 && Utility.m5432d(this.f21039h));
        }
        return this.f21044m.booleanValue();
    }

    /* compiled from: PushClientManager.java */
    /* renamed from: com.vivo.push.m$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C10963a {

        /* renamed from: a */
        private IPushActionListener f21049a;

        /* renamed from: b */
        private BaseAppCommand f21050b;

        /* renamed from: c */
        private IPushActionListener f21051c;

        /* renamed from: d */
        private Runnable f21052d;

        /* renamed from: e */
        private Object[] f21053e;

        public C10963a(BaseAppCommand baseAppCommand, IPushActionListener iPushActionListener) {
            this.f21050b = baseAppCommand;
            this.f21049a = iPushActionListener;
        }

        /* renamed from: a */
        public final void m5608a(int i, Object... objArr) {
            this.f21053e = objArr;
            IPushActionListener iPushActionListener = this.f21051c;
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(i);
            }
            IPushActionListener iPushActionListener2 = this.f21049a;
            if (iPushActionListener2 != null) {
                iPushActionListener2.onStateChanged(i);
            }
        }

        /* renamed from: a */
        public final void m5606a(Runnable runnable) {
            this.f21052d = runnable;
        }

        /* renamed from: a */
        public final void m5609a() {
            Runnable runnable = this.f21052d;
            if (runnable == null) {
                LogUtil.m5354a("PushClientManager", "task is null");
            } else {
                runnable.run();
            }
        }

        /* renamed from: a */
        public final void m5607a(IPushActionListener iPushActionListener) {
            this.f21051c = iPushActionListener;
        }

        /* renamed from: b */
        public final Object[] m5605b() {
            return this.f21053e;
        }
    }
}
