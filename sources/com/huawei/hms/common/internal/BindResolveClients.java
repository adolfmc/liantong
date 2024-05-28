package com.huawei.hms.common.internal;

import java.util.ArrayList;
import java.util.ListIterator;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BindResolveClients {

    /* renamed from: b */
    private static final Object f11135b = new Object();

    /* renamed from: a */
    private ArrayList<ResolveClientBean> f11136a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.common.internal.BindResolveClients$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C4896b {

        /* renamed from: a */
        private static final BindResolveClients f11137a = new BindResolveClients();
    }

    public static BindResolveClients getInstance() {
        return C4896b.f11137a;
    }

    public boolean isClientRegistered(ResolveClientBean resolveClientBean) {
        boolean contains;
        synchronized (f11135b) {
            contains = this.f11136a.contains(resolveClientBean);
        }
        return contains;
    }

    public void notifyClientReconnect() {
        synchronized (f11135b) {
            ListIterator<ResolveClientBean> listIterator = this.f11136a.listIterator();
            while (listIterator.hasNext()) {
                listIterator.next().clientReconnect();
            }
            this.f11136a.clear();
        }
    }

    public void register(ResolveClientBean resolveClientBean) {
        if (resolveClientBean == null) {
            return;
        }
        synchronized (f11135b) {
            if (!this.f11136a.contains(resolveClientBean)) {
                this.f11136a.add(resolveClientBean);
            }
        }
    }

    public void unRegister(ResolveClientBean resolveClientBean) {
        if (resolveClientBean == null) {
            return;
        }
        synchronized (f11135b) {
            if (this.f11136a.contains(resolveClientBean)) {
                ListIterator<ResolveClientBean> listIterator = this.f11136a.listIterator();
                while (true) {
                    if (!listIterator.hasNext()) {
                        break;
                    } else if (resolveClientBean.equals(listIterator.next())) {
                        listIterator.remove();
                        break;
                    }
                }
            }
        }
    }

    public void unRegisterAll() {
        synchronized (f11135b) {
            this.f11136a.clear();
        }
    }

    private BindResolveClients() {
        this.f11136a = new ArrayList<>();
    }
}
