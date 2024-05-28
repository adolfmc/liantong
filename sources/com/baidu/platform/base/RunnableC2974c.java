package com.baidu.platform.base;

import com.baidu.mapapi.search.core.SearchResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BaseSearch.java */
/* renamed from: com.baidu.platform.base.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2974c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ SearchParser f7503a;

    /* renamed from: b */
    final /* synthetic */ SearchResult f7504b;

    /* renamed from: c */
    final /* synthetic */ Object f7505c;

    /* renamed from: d */
    final /* synthetic */ BaseSearch f7506d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2974c(BaseSearch baseSearch, SearchParser searchParser, SearchResult searchResult, Object obj) {
        this.f7506d = baseSearch;
        this.f7503a = searchParser;
        this.f7504b = searchResult;
        this.f7505c = obj;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f7503a != null) {
            this.f7506d.f7494a.lock();
            try {
                this.f7503a.mo17486a(this.f7504b, this.f7505c);
            } finally {
                this.f7506d.f7494a.unlock();
            }
        }
    }
}
