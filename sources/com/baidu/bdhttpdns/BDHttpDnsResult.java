package com.baidu.bdhttpdns;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BDHttpDnsResult {

    /* renamed from: a */
    private ResolveType f4302a;

    /* renamed from: b */
    private final ResolveStatus f4303b;

    /* renamed from: c */
    private ArrayList<String> f4304c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum ResolveStatus {
        STATUS_OK,
        STATUS_ERR_CACHE_MISS,
        STATUS_ERR_DNS_RESOLVE
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum ResolveType {
        RESOLVE_NONE,
        RESOLVE_FROM_HTTPDNS_CACHE,
        RESOLVE_FROM_HTTPDNS_EXPIRED_CACHE,
        RESOLVE_FROM_DNS_CACHE,
        RESOLVE_FROM_DNS
    }

    public BDHttpDnsResult(ResolveStatus resolveStatus) {
        this.f4302a = ResolveType.RESOLVE_NONE;
        this.f4303b = resolveStatus;
    }

    public BDHttpDnsResult(ResolveType resolveType, ResolveStatus resolveStatus, ArrayList<String> arrayList) {
        this.f4302a = ResolveType.RESOLVE_NONE;
        this.f4302a = resolveType;
        this.f4303b = resolveStatus;
        this.f4304c = arrayList;
    }

    public ArrayList<String> getIpv4List() {
        return this.f4304c;
    }

    public ResolveStatus getResolveStatus() {
        return this.f4303b;
    }

    public ResolveType getResolveType() {
        return this.f4302a;
    }
}
