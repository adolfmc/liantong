package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SysUpdateObservable {

    /* renamed from: a */
    private static volatile SysUpdateObservable f7417a;

    /* renamed from: b */
    private List<SysUpdateObserver> f7418b;

    public static SysUpdateObservable getInstance() {
        if (f7417a == null) {
            synchronized (SysUpdateObservable.class) {
                if (f7417a == null) {
                    f7417a = new SysUpdateObservable();
                }
            }
        }
        return f7417a;
    }

    private SysUpdateObservable() {
        this.f7418b = null;
        this.f7418b = new ArrayList();
    }

    public void addObserver(SysUpdateObserver sysUpdateObserver) {
        this.f7418b.add(sysUpdateObserver);
    }

    public void init(String str) {
        for (SysUpdateObserver sysUpdateObserver : this.f7418b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.init(str);
            }
        }
    }

    public void updatePhoneInfo(String str) {
        for (SysUpdateObserver sysUpdateObserver : this.f7418b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updatePhoneInfo(str);
            }
        }
    }

    public void updateNetworkInfo(Context context) {
        for (SysUpdateObserver sysUpdateObserver : this.f7418b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updateNetworkInfo(context);
            }
        }
    }

    public void updateNetworkProxy(Context context) {
        for (SysUpdateObserver sysUpdateObserver : this.f7418b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updateNetworkProxy(context);
            }
        }
    }
}
