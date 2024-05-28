package com.baidu.cloud.videocache.preload;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.cloud.videocache.ProxyCacheManager;
import com.baidu.cloud.videocache.Util;
import com.baidu.cloud.videocache.preload.nxb;
import com.baidu.cloud.videocache.utils.CacheThreadPoolHelper;
import com.baidu.cloud.videocache.utils.Logger;
import com.baidu.cloud.videocache.utils.ThreadPool;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ass extends oia implements IVideoPreload {

    /* renamed from: c */
    private static ass f4912c;

    /* renamed from: d */
    private final Context f4915d;

    /* renamed from: g */
    private boolean f4918g;

    /* renamed from: a */
    public long f4913a = 819200;

    /* renamed from: b */
    private int f4914b = 10000;

    /* renamed from: e */
    private rwd f4916e = new rwd();

    /* renamed from: h */
    private int f4919h = 0;

    /* renamed from: f */
    private AtomicInteger f4917f = new AtomicInteger(0);

    private ass(Context context) {
        this.f4915d = context;
        m19745a();
    }

    /* renamed from: a */
    public static ass m19766a(Context context) {
        if (f4912c == null) {
            synchronized (ass.class) {
                if (f4912c == null) {
                    f4912c = new ass(context);
                }
            }
        }
        return f4912c;
    }

    /* renamed from: a */
    private nxb m19763a(nxb.oia oiaVar, nxb nxbVar) {
        return m19762a(oiaVar, nxbVar, "");
    }

    /* renamed from: a */
    private synchronized nxb m19762a(nxb.oia oiaVar, nxb nxbVar, String str) {
        if (nxbVar == null) {
            return null;
        }
        PreloadCallback m19750b = nxbVar.m19750b();
        nxbVar.m19751a(oiaVar);
        switch (oiaVar) {
            case COMPLETED:
                nxbVar.f4924e = true;
                if (m19750b != null) {
                    m19750b.onPreloadCompleted(nxbVar.f4922c, true, "");
                    break;
                }
                break;
            case ERROR:
                nxbVar.f4924e = false;
                if (m19750b != null) {
                    m19750b.onPreloadCompleted(nxbVar.f4922c, false, str);
                    break;
                }
                break;
        }
        Logger.m19725d("update preload status with desc " + str);
        return nxbVar;
    }

    /* renamed from: a */
    private void m19761a(nxb nxbVar) {
        if (nxbVar.m19748d() == nxb.oia.PRELOADING || this.f4916e.m19735b() <= 0) {
            return;
        }
        if (this.f4917f.get() >= 3) {
            nxbVar.m19751a(nxb.oia.PENDING);
        } else if (nxbVar != null) {
            Logger.m19725d("start preload " + nxbVar);
            m19756b(nxbVar);
        }
    }

    /* renamed from: b */
    private void m19756b(nxb nxbVar) {
        if (nxbVar != null && !TextUtils.isEmpty(nxbVar.f4922c)) {
            CacheThreadPoolHelper.getInstance().execute(new uwb(this, nxbVar));
            return;
        }
        if (nxbVar != null) {
            nxbVar.f4924e = true;
        }
        m19741a(m19744a(101));
    }

    /* renamed from: c */
    private nxb m19755c() {
        if (this.f4916e.m19735b() == 0) {
            return null;
        }
        return this.f4916e.m19738a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public boolean m19754c(nxb nxbVar) {
        boolean z = false;
        if (nxbVar == null) {
            return false;
        }
        String tempCachePath = ProxyCacheManager.getInstance(this.f4915d).getTempCachePath(nxbVar.f4922c);
        if (!TextUtils.isEmpty(tempCachePath) && Util.calculateFileSize(tempCachePath) >= nxbVar.f4921b) {
            z = true;
        }
        if (!z) {
            z = ProxyCacheManager.getInstance(this.f4915d).isCached(nxbVar.f4922c);
        }
        if (z) {
            m19763a(nxb.oia.COMPLETED, nxbVar);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0084, code lost:
        m19763a(r0, r14);
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x00ec -> B:55:0x00f3). Please submit an issue!!! */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.cloud.videocache.preload.nxb m19753d(com.baidu.cloud.videocache.preload.nxb r14) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.videocache.preload.ass.m19753d(com.baidu.cloud.videocache.preload.nxb):com.baidu.cloud.videocache.preload.nxb");
    }

    /* renamed from: a */
    public long m19760a(nxb nxbVar, long j) {
        long m19749c = nxbVar.m19749c();
        return (j != -1 && m19749c > j) ? j : m19749c;
    }

    /* renamed from: a */
    public void m19767a(long j) {
        this.f4913a = j;
    }

    /* renamed from: b */
    public void m19759b(int i) {
        this.f4914b = i;
    }

    @Override // com.baidu.cloud.videocache.preload.oia
    /* renamed from: b */
    protected void mo19739b(Message message) {
        nxb m19755c;
        switch (message.what) {
            case 101:
                if (!this.f4918g && this.f4916e.m19735b() != 0) {
                    m19755c = m19755c();
                    break;
                } else {
                    return;
                }
                break;
            case 102:
                m19755c = (nxb) message.obj;
                if (m19755c == null) {
                    return;
                }
                break;
            default:
                return;
        }
        m19761a(m19755c);
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void cancelPreload(String str) {
        nxb m19736a = this.f4916e.m19736a(str);
        if (m19736a == null) {
            Logger.m19725d("No task or  has completed " + str);
            return;
        }
        Logger.m19725d("cancel preload task " + m19736a);
        if (m19736a.m19748d() == nxb.oia.PRELOADING) {
            m19736a.m19747e();
        } else {
            this.f4916e.m19734b(str);
        }
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public boolean isPreloading() {
        return this.f4918g;
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void pausePreload() {
        Logger.m19725d("pause preload ...");
        this.f4918g = true;
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void preload(String str, long j, PreloadCallback preloadCallback) {
        if (TextUtils.isEmpty(str) || j <= 0) {
            return;
        }
        if (this.f4916e.m19735b() > 50) {
            if (preloadCallback != null) {
                preloadCallback.onPreloadCompleted(str, false, "preload queue over length retry later.");
            }
        } else if (Util.analyzeMediaType(str) != 0) {
            if (preloadCallback != null) {
                preloadCallback.onPreloadCompleted(str, false, "preload do not support hls/dash now.");
            }
        } else {
            nxb m19736a = this.f4916e.m19736a(str);
            if (m19736a == null) {
                int i = this.f4919h;
                this.f4919h = i + 1;
                m19736a = new nxb(i, str, j, preloadCallback);
                this.f4916e.m19737a(m19736a);
            }
            if (m19736a.m19748d() != nxb.oia.PRELOADING) {
                m19741a(m19743a(102, m19736a));
            }
        }
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void preload(List list, PreloadCallback preloadCallback) {
        if (list == null || list.size() == 0) {
            return;
        }
        if (this.f4916e.m19735b() > 50) {
            Logger.m19725d("preload queue over length50retry later.");
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String str = (String) list.get(i);
            if (Util.analyzeMediaType(str) != 0) {
                if (preloadCallback != null) {
                    preloadCallback.onPreloadCompleted(str, false, "preload do not support hls/dash now.");
                }
            } else if (this.f4916e.m19736a(str) == null) {
                int i2 = this.f4919h;
                this.f4919h = i2 + 1;
                this.f4916e.m19737a(new nxb(i2, str, this.f4913a, preloadCallback));
            }
        }
        m19741a(m19744a(101));
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void release() {
        this.f4918g = true;
        this.f4916e.m19733c();
        m19740b();
        ThreadPool.destroy();
        f4912c = null;
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void resumePreload() {
        Logger.m19725d("resume preload ...");
        this.f4918g = false;
        m19741a(m19744a(101));
    }
}
