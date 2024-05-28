package com.baidu.bdhttpdns;

import com.baidu.bdhttpdns.BDHttpDns;
import com.baidu.bdhttpdns.BDHttpDnsResult;
import com.baidu.bdhttpdns.C2436e;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.bdhttpdns.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC2430a implements Runnable {

    /* renamed from: a */
    final /* synthetic */ BDHttpDns.CompletionHandler f4308a;

    /* renamed from: b */
    final /* synthetic */ BDHttpDnsResult.ResolveType f4309b;

    /* renamed from: c */
    final /* synthetic */ C2436e.C2437a f4310c;

    /* renamed from: d */
    final /* synthetic */ BDHttpDns f4311d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2430a(BDHttpDns bDHttpDns, BDHttpDns.CompletionHandler completionHandler, BDHttpDnsResult.ResolveType resolveType, C2436e.C2437a c2437a) {
        this.f4311d = bDHttpDns;
        this.f4308a = completionHandler;
        this.f4309b = resolveType;
        this.f4310c = c2437a;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f4308a.completionHandler(new BDHttpDnsResult(this.f4309b, BDHttpDnsResult.ResolveStatus.STATUS_OK, this.f4310c.m20125b()));
    }
}
