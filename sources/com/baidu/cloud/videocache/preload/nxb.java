package com.baidu.cloud.videocache.preload;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class nxb {

    /* renamed from: a */
    public int f4920a;

    /* renamed from: b */
    public long f4921b;

    /* renamed from: c */
    public String f4922c;

    /* renamed from: d */
    public String f4923d;

    /* renamed from: e */
    public boolean f4924e;

    /* renamed from: f */
    private PreloadCallback f4925f;

    /* renamed from: g */
    private oia f4926g = oia.IDLE;

    /* renamed from: h */
    private volatile boolean f4927h;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum oia {
        IDLE(0, "idle"),
        PRELOADING(1, "preloading"),
        PAUSED(2, "paused"),
        COMPLETED(3, "completed"),
        PENDING(4, "pending"),
        ERROR(5, "error");
        

        /* renamed from: g */
        int f4935g;

        /* renamed from: h */
        String f4936h;

        oia(int i, String str) {
            this.f4935g = i;
            this.f4936h = str;
        }
    }

    public nxb(int i, String str, long j, PreloadCallback preloadCallback) {
        this.f4920a = i;
        this.f4922c = str;
        this.f4921b = j;
        this.f4925f = preloadCallback;
    }

    /* renamed from: a */
    public String m19752a() {
        return this.f4922c;
    }

    /* renamed from: a */
    public void m19751a(oia oiaVar) {
        this.f4926g = oiaVar;
    }

    /* renamed from: b */
    public PreloadCallback m19750b() {
        return this.f4925f;
    }

    /* renamed from: c */
    public long m19749c() {
        return this.f4921b;
    }

    /* renamed from: d */
    public oia m19748d() {
        return this.f4926g;
    }

    /* renamed from: e */
    public void m19747e() {
        this.f4927h = true;
    }

    /* renamed from: f */
    public boolean m19746f() {
        return this.f4927h;
    }

    public String toString() {
        return "PreloadItem{index=" + this.f4920a + ", preloadSize=" + this.f4921b + ", callback=" + this.f4925f + ", orgUrl='" + this.f4922c + "', proxyUrl='" + this.f4923d + "', isPreloaded=" + this.f4924e + ", cancelPreload=" + this.f4927h + ", status=" + this.f4926g + '}';
    }
}
