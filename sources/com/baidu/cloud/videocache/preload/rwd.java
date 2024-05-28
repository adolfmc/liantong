package com.baidu.cloud.videocache.preload;

import android.text.TextUtils;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class rwd {

    /* renamed from: a */
    private TreeMap f4940a = new TreeMap();

    /* renamed from: a */
    public nxb m19738a() {
        Map.Entry firstEntry = this.f4940a.firstEntry();
        if (firstEntry == null || firstEntry.getValue() == null) {
            return null;
        }
        nxb nxbVar = (nxb) firstEntry.getValue();
        for (Map.Entry entry : this.f4940a.entrySet()) {
            nxb nxbVar2 = (nxb) entry.getValue();
            if (nxbVar.f4920a < nxbVar2.f4920a) {
                nxbVar = nxbVar2;
            }
        }
        return nxbVar;
    }

    /* renamed from: a */
    public nxb m19736a(String str) {
        return (nxb) this.f4940a.get(str);
    }

    /* renamed from: a */
    public void m19737a(nxb nxbVar) {
        if (nxbVar == null || TextUtils.isEmpty(nxbVar.f4922c) || this.f4940a.containsKey(nxbVar.f4922c)) {
            return;
        }
        this.f4940a.put(nxbVar.f4922c, nxbVar);
    }

    /* renamed from: b */
    public int m19735b() {
        return this.f4940a.size();
    }

    /* renamed from: b */
    public void m19734b(String str) {
        if (this.f4940a.containsKey(str)) {
            this.f4940a.remove(str);
        }
    }

    /* renamed from: c */
    public void m19733c() {
        this.f4940a.clear();
    }
}
