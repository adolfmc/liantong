package com.baidu.bdhttpdns;

import com.baidu.bdhttpdns.BDHttpDns;
import com.baidu.bdhttpdns.BDHttpDnsResult;
import com.baidu.bdhttpdns.C2436e;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.bdhttpdns.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC2431b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ BDHttpDns.CompletionHandler f4312a;

    /* renamed from: b */
    final /* synthetic */ BDHttpDnsResult.ResolveType f4313b;

    /* renamed from: c */
    final /* synthetic */ C2436e.C2437a f4314c;

    /* renamed from: d */
    final /* synthetic */ BDHttpDns f4315d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2431b(BDHttpDns bDHttpDns, BDHttpDns.CompletionHandler completionHandler, BDHttpDnsResult.ResolveType resolveType, C2436e.C2437a c2437a) {
        this.f4315d = bDHttpDns;
        this.f4312a = completionHandler;
        this.f4313b = resolveType;
        this.f4314c = c2437a;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f4312a.completionHandler(new BDHttpDnsResult(this.f4313b, BDHttpDnsResult.ResolveStatus.STATUS_OK, this.f4314c.m20125b()));
    }
}
