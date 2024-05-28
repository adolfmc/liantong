package com.bytedance.pangle.service.p182a;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.bytedance.pangle.InterfaceC3786d;
import com.bytedance.pangle.InterfaceC3821f;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.plugin.PluginManager;
import com.bytedance.pangle.service.InterfaceC3928a;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: com.bytedance.pangle.service.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BinderC3929a extends InterfaceC3786d.AbstractBinderC3787a {

    /* renamed from: b */
    private static volatile BinderC3929a f9341b;

    /* renamed from: c */
    private final HashMap<ComponentName, IBinder> f9343c = new HashMap<>();

    /* renamed from: d */
    private final HashMap<ComponentName, C3935b> f9344d = new HashMap<>();

    /* renamed from: e */
    private final C3934a<Intent> f9345e = new C3934a<>();

    /* renamed from: f */
    private final HashMap<ComponentName, InterfaceC3928a> f9346f = new HashMap<>();

    /* renamed from: g */
    private final HashSet<ComponentName> f9347g = new HashSet<>();

    /* renamed from: h */
    private final HashSet<ComponentName> f9348h = new HashSet<>();

    /* renamed from: a */
    private final Handler f9342a = new Handler(Looper.getMainLooper());

    @Override // com.bytedance.pangle.InterfaceC3786d.AbstractBinderC3787a, android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.pangle.service.a.a$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3935b extends HashSet<InterfaceC3821f> {
        C3935b() {
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(@Nullable Object obj) {
            if (super.contains(obj)) {
                return true;
            }
            if (obj instanceof InterfaceC3821f) {
                Iterator<InterfaceC3821f> it = iterator();
                while (it.hasNext()) {
                    try {
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    if (it.next().mo16662a() == ((InterfaceC3821f) obj).mo16662a()) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(@Nullable Object obj) {
            if (super.remove(obj)) {
                return true;
            }
            InterfaceC3821f interfaceC3821f = null;
            Iterator<InterfaceC3821f> it = iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                InterfaceC3821f next = it.next();
                try {
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (next.mo16662a() == ((InterfaceC3821f) obj).mo16662a()) {
                    interfaceC3821f = next;
                    break;
                }
            }
            return super.remove(interfaceC3821f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.pangle.service.a.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3934a<T> extends HashMap<InterfaceC3821f, T> {
        C3934a() {
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final boolean containsKey(@Nullable Object obj) {
            if (super.containsKey(obj)) {
                return true;
            }
            if (obj instanceof InterfaceC3821f) {
                for (InterfaceC3821f interfaceC3821f : keySet()) {
                    try {
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    if (interfaceC3821f.mo16662a() == ((InterfaceC3821f) obj).mo16662a()) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        @Nullable
        public final T remove(@Nullable Object obj) {
            InterfaceC3821f interfaceC3821f;
            T t = (T) super.remove(obj);
            if (t != null) {
                return t;
            }
            Iterator<InterfaceC3821f> it = keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    interfaceC3821f = null;
                    break;
                }
                interfaceC3821f = it.next();
                try {
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (interfaceC3821f.mo16662a() == ((InterfaceC3821f) obj).mo16662a()) {
                    break;
                }
            }
            return (T) super.remove(interfaceC3821f);
        }
    }

    /* renamed from: b */
    public static BinderC3929a m16670b() {
        if (f9341b == null) {
            synchronized (BinderC3929a.class) {
                if (f9341b == null) {
                    f9341b = new BinderC3929a();
                }
            }
        }
        return f9341b;
    }

    private BinderC3929a() {
    }

    @Override // com.bytedance.pangle.InterfaceC3786d
    /* renamed from: a */
    public final ComponentName mo16675a(final Intent intent, final String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return m16665c(intent, str);
        }
        this.f9342a.post(new Runnable() { // from class: com.bytedance.pangle.service.a.a.1
            @Override // java.lang.Runnable
            public final void run() {
                BinderC3929a.this.m16665c(intent, str);
            }
        });
        return intent.getComponent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public synchronized ComponentName m16665c(Intent intent, String str) {
        ComponentName component = intent.getComponent();
        if (!this.f9346f.containsKey(component)) {
            InterfaceC3928a m16664d = m16664d(intent, str);
            if (m16664d == null) {
                return component;
            }
            this.f9346f.put(component, m16664d);
            this.f9347g.add(component);
        }
        InterfaceC3928a interfaceC3928a = this.f9346f.get(component);
        if (interfaceC3928a != null) {
            interfaceC3928a.onStartCommand(intent, 0, 0);
        }
        return component;
    }

    /* renamed from: d */
    private static InterfaceC3928a m16664d(Intent intent, String str) {
        InterfaceC3928a m16663e = m16663e(intent, str);
        if (m16663e != null) {
            m16663e.onCreate();
        }
        return m16663e;
    }

    /* renamed from: e */
    private static InterfaceC3928a m16663e(Intent intent, String str) {
        boolean z;
        ComponentName component = intent.getComponent();
        Plugin plugin = PluginManager.getInstance().getPlugin(str);
        try {
            z = Zeus.loadPlugin(str);
            try {
                InterfaceC3928a interfaceC3928a = (InterfaceC3928a) plugin.mClassLoader.loadClass(component.getClassName()).newInstance();
                interfaceC3928a.attach(plugin);
                return interfaceC3928a;
            } catch (Exception e) {
                e = e;
                ZeusLogger.errReport("Zeus/service_pangle", "newServiceInstance failed! loadPlugin = ".concat(String.valueOf(z)), e);
                throw new RuntimeException(e);
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
        }
    }

    @Override // com.bytedance.pangle.InterfaceC3786d
    /* renamed from: b */
    public final boolean mo16668b(final Intent intent, String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            m16670b().m16678a(intent.getComponent());
            return true;
        }
        this.f9342a.post(new Runnable() { // from class: com.bytedance.pangle.service.a.a.2
            @Override // java.lang.Runnable
            public final void run() {
                BinderC3929a.m16670b().m16678a(intent.getComponent());
            }
        });
        return true;
    }

    /* renamed from: a */
    public final synchronized boolean m16678a(ComponentName componentName) {
        if (this.f9346f.containsKey(componentName)) {
            this.f9348h.add(componentName);
            return m16669b(componentName);
        }
        return false;
    }

    /* renamed from: b */
    private boolean m16669b(ComponentName componentName) {
        if (!this.f9347g.contains(componentName)) {
            if (this.f9344d.get(componentName) == null) {
                m16666c(componentName);
                return true;
            }
            return false;
        } else if (!this.f9348h.contains(componentName) || this.f9344d.containsKey(componentName)) {
            return false;
        } else {
            m16666c(componentName);
            return true;
        }
    }

    /* renamed from: c */
    private void m16666c(ComponentName componentName) {
        InterfaceC3928a remove = this.f9346f.remove(componentName);
        this.f9348h.remove(componentName);
        this.f9343c.remove(componentName);
        this.f9347g.remove(componentName);
        if (remove != null) {
            remove.onDestroy();
        }
    }

    @Override // com.bytedance.pangle.InterfaceC3786d
    /* renamed from: a */
    public final boolean mo16677a(final Intent intent, final InterfaceC3821f interfaceC3821f, final int i, final String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return m16676a(intent, interfaceC3821f, str);
        }
        this.f9342a.post(new Runnable() { // from class: com.bytedance.pangle.service.a.a.3
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    BinderC3929a.this.m16676a(intent, interfaceC3821f, str);
                } catch (RemoteException e) {
                    ZeusLogger.errReport("Zeus/service_pangle", "bindService failed", e);
                }
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized boolean m16676a(Intent intent, InterfaceC3821f interfaceC3821f, String str) {
        ComponentName component = intent.getComponent();
        if (!this.f9346f.containsKey(component)) {
            InterfaceC3928a m16664d = m16664d(intent, str);
            if (m16664d == null) {
                return false;
            }
            this.f9346f.put(component, m16664d);
        }
        InterfaceC3928a interfaceC3928a = this.f9346f.get(component);
        if (!this.f9343c.containsKey(component)) {
            this.f9343c.put(component, interfaceC3928a.onBind(intent));
        }
        IBinder iBinder = this.f9343c.get(component);
        if (iBinder != null) {
            if (this.f9344d.containsKey(component)) {
                if (!this.f9344d.get(component).contains(interfaceC3821f)) {
                    this.f9344d.get(component).add(interfaceC3821f);
                    this.f9345e.put(interfaceC3821f, intent);
                    interfaceC3821f.mo16661a(component, iBinder);
                }
            } else {
                C3935b c3935b = new C3935b();
                c3935b.add(interfaceC3821f);
                this.f9344d.put(component, c3935b);
                this.f9345e.put(interfaceC3821f, intent);
                interfaceC3821f.mo16661a(component, iBinder);
            }
        }
        return true;
    }

    @Override // com.bytedance.pangle.InterfaceC3786d
    /* renamed from: a */
    public final void mo16674a(final InterfaceC3821f interfaceC3821f) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            m16667b(interfaceC3821f);
        } else {
            this.f9342a.post(new Runnable() { // from class: com.bytedance.pangle.service.a.a.4
                @Override // java.lang.Runnable
                public final void run() {
                    BinderC3929a.this.m16667b(interfaceC3821f);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public synchronized void m16667b(InterfaceC3821f interfaceC3821f) {
        for (ComponentName componentName : this.f9344d.keySet()) {
            C3935b c3935b = this.f9344d.get(componentName);
            if (c3935b.contains(interfaceC3821f)) {
                c3935b.remove(interfaceC3821f);
                Intent remove = this.f9345e.remove(interfaceC3821f);
                if (c3935b.size() == 0) {
                    this.f9344d.remove(componentName);
                    InterfaceC3928a interfaceC3928a = this.f9346f.get(componentName);
                    if (interfaceC3928a != null) {
                        interfaceC3928a.onUnbind(remove);
                    }
                }
                m16669b(componentName);
                return;
            }
        }
    }
}
