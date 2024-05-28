package com.huawei.hms.adapter.sysobs;

import android.content.Intent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class SystemManager {

    /* renamed from: a */
    private static SystemManager f10965a = new SystemManager();

    /* renamed from: b */
    private static final Object f10966b = new Object();

    /* renamed from: c */
    private static SystemNotifier f10967c = new C4847a();

    private SystemManager() {
    }

    public static SystemManager getInstance() {
        return f10965a;
    }

    public static SystemNotifier getSystemNotifier() {
        return f10967c;
    }

    public void notifyNoticeResult(int i) {
        f10967c.notifyNoticeObservers(i);
    }

    public void notifyResolutionResult(Intent intent, String str) {
        f10967c.notifyObservers(intent, str);
    }

    public void notifyUpdateResult(int i) {
        f10967c.notifyObservers(i);
    }

    /* renamed from: com.huawei.hms.adapter.sysobs.SystemManager$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class C4847a implements SystemNotifier {

        /* renamed from: a */
        private final List<SystemObserver> f10968a = new ArrayList();

        C4847a() {
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemNotifier
        public void notifyNoticeObservers(int i) {
            synchronized (SystemManager.f10966b) {
                Iterator<SystemObserver> it = this.f10968a.iterator();
                while (it.hasNext()) {
                    if (it.next().onNoticeResult(i)) {
                        it.remove();
                    }
                }
            }
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemNotifier
        public void notifyObservers(Intent intent, String str) {
            synchronized (SystemManager.f10966b) {
                Iterator<SystemObserver> it = this.f10968a.iterator();
                while (it.hasNext()) {
                    if (it.next().onSolutionResult(intent, str)) {
                        it.remove();
                    }
                }
            }
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemNotifier
        public void registerObserver(SystemObserver systemObserver) {
            if (systemObserver == null || this.f10968a.contains(systemObserver)) {
                return;
            }
            synchronized (SystemManager.f10966b) {
                this.f10968a.add(systemObserver);
            }
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemNotifier
        public void unRegisterObserver(SystemObserver systemObserver) {
            synchronized (SystemManager.f10966b) {
                this.f10968a.remove(systemObserver);
            }
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemNotifier
        public void notifyObservers(int i) {
            synchronized (SystemManager.f10966b) {
                Iterator<SystemObserver> it = this.f10968a.iterator();
                while (it.hasNext()) {
                    if (it.next().onUpdateResult(i)) {
                        it.remove();
                    }
                }
            }
        }
    }
}
