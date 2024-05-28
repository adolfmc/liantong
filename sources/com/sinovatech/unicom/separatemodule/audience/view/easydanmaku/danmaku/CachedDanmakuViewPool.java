package com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku;

import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.DanmakuView;
import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.utils.EasyL;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CachedDanmakuViewPool implements Pool<DanmakuView> {
    private static final String TAG = "CachedDanmakuViewPool";
    private ViewCreator<DanmakuView> mCreator;
    private long mKeepAliveTime;
    private int mMaxSize;
    private LinkedList<DanmakuViewWithExpireTime> mCache = new LinkedList<>();
    private ScheduledExecutorService mChecker = Executors.newSingleThreadScheduledExecutor();
    private int mInUseSize = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ViewCreator<T> {
        T create();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CachedDanmakuViewPool(long j, int i, ViewCreator<DanmakuView> viewCreator) {
        this.mKeepAliveTime = j;
        this.mMaxSize = i;
        this.mCreator = viewCreator;
        scheduleCheckUnusedViews();
    }

    private void scheduleCheckUnusedViews() {
        this.mChecker.scheduleWithFixedDelay(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.-$$Lambda$CachedDanmakuViewPool$EY0qITdjAheNsf2YDtCUpT5tS5k
            @Override // java.lang.Runnable
            public final void run() {
                CachedDanmakuViewPool.lambda$scheduleCheckUnusedViews$0(CachedDanmakuViewPool.this);
            }
        }, 1000L, 1000L, TimeUnit.MILLISECONDS);
    }

    public static /* synthetic */ void lambda$scheduleCheckUnusedViews$0(CachedDanmakuViewPool cachedDanmakuViewPool) {
        EasyL.m7992v(TAG, "scheduleCheckUnusedViews: mInUseSize=" + cachedDanmakuViewPool.mInUseSize + ", mCacheSize=" + cachedDanmakuViewPool.mCache.size());
        long currentTimeMillis = System.currentTimeMillis();
        while (!cachedDanmakuViewPool.mCache.isEmpty()) {
            DanmakuViewWithExpireTime first = cachedDanmakuViewPool.mCache.getFirst();
            if (currentTimeMillis <= first.expireTime) {
                return;
            }
            cachedDanmakuViewPool.mCache.remove(first);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.Pool
    public DanmakuView get() {
        DanmakuView danmakuView;
        if (this.mCache.isEmpty()) {
            if (this.mInUseSize >= this.mMaxSize) {
                return null;
            }
            danmakuView = this.mCreator.create();
        } else {
            danmakuView = this.mCache.poll().danmakuView;
        }
        danmakuView.addOnExitListener(new DanmakuView.OnExitListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.-$$Lambda$CachedDanmakuViewPool$iN7Yrme-XDNHw7E-urCR2GpjXp8
            @Override // com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.DanmakuView.OnExitListener
            public final void onExit(DanmakuView danmakuView2) {
                CachedDanmakuViewPool.lambda$get$1(CachedDanmakuViewPool.this, danmakuView2);
            }
        });
        this.mInUseSize++;
        return danmakuView;
    }

    public static /* synthetic */ void lambda$get$1(CachedDanmakuViewPool cachedDanmakuViewPool, DanmakuView danmakuView) {
        long currentTimeMillis = System.currentTimeMillis() + cachedDanmakuViewPool.mKeepAliveTime;
        danmakuView.restore();
        DanmakuViewWithExpireTime danmakuViewWithExpireTime = new DanmakuViewWithExpireTime();
        danmakuViewWithExpireTime.danmakuView = danmakuView;
        danmakuViewWithExpireTime.expireTime = currentTimeMillis;
        cachedDanmakuViewPool.mCache.offer(danmakuViewWithExpireTime);
        cachedDanmakuViewPool.mInUseSize--;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.Pool
    public void release() {
        this.mCache.clear();
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.Pool
    public int count() {
        return this.mCache.size() + this.mInUseSize;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.Pool
    public void setMaxSize(int i) {
        this.mMaxSize = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class DanmakuViewWithExpireTime {
        private DanmakuView danmakuView;
        private long expireTime;

        private DanmakuViewWithExpireTime() {
        }
    }
}
