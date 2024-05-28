package com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.CachedDanmakuViewPool;
import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.utils.EasyL;
import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.utils.ScreenUtil;
import java.lang.ref.WeakReference;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DanmakuManager {
    private static final int RESULT_FULL_POOL = 2;
    private static final int RESULT_NULL_ROOT_VIEW = 1;
    private static final int RESULT_OK = 0;
    private static final String TAG = "DanmakuManager";
    private static final int TOO_MANY_DANMAKU = 2;
    private static DanmakuManager sInstance;
    private Config mConfig;
    WeakReference<FrameLayout> mDanmakuContainer;
    private Pool<DanmakuView> mDanmakuViewPool;
    private DanmakuPositionCalculator mPositionCal;

    private DanmakuManager() {
    }

    public static DanmakuManager getInstance() {
        if (sInstance == null) {
            sInstance = new DanmakuManager();
        }
        return sInstance;
    }

    public void init(final Context context, final FrameLayout frameLayout) {
        if (this.mDanmakuViewPool == null) {
            this.mDanmakuViewPool = new CachedDanmakuViewPool(60000L, 100, new CachedDanmakuViewPool.ViewCreator() { // from class: com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.-$$Lambda$DanmakuManager$gFeveOd1A6CUldvY2I0-GXUv-js
                @Override // com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.CachedDanmakuViewPool.ViewCreator
                public final Object create() {
                    DanmakuView createDanmakuView;
                    createDanmakuView = DanmakuViewFactory.createDanmakuView(context, frameLayout);
                    return createDanmakuView;
                }
            });
        }
        setDanmakuContainer(frameLayout);
        ScreenUtil.init(context);
        this.mConfig = new Config();
        this.mPositionCal = new DanmakuPositionCalculator(this);
    }

    public Config getConfig() {
        if (this.mConfig == null) {
            this.mConfig = new Config();
        }
        return this.mConfig;
    }

    private DanmakuPositionCalculator getPositionCalculator() {
        if (this.mPositionCal == null) {
            this.mPositionCal = new DanmakuPositionCalculator(this);
        }
        return this.mPositionCal;
    }

    public void resetPositionCalculator() {
        this.mPositionCal = null;
    }

    public void setDanmakuViewPool(Pool<DanmakuView> pool) {
        Pool<DanmakuView> pool2 = this.mDanmakuViewPool;
        if (pool2 != null) {
            pool2.release();
        }
        this.mDanmakuViewPool = pool;
    }

    public void setMaxDanmakuSize(int i) {
        Pool<DanmakuView> pool = this.mDanmakuViewPool;
        if (pool == null) {
            return;
        }
        pool.setMaxSize(i);
    }

    public void setDanmakuContainer(FrameLayout frameLayout) {
        if (frameLayout == null) {
            throw new NullPointerException("Danmaku container cannot be null!");
        }
        this.mDanmakuContainer = new WeakReference<>(frameLayout);
    }

    public int send(Danmaku danmaku) {
        Pool<DanmakuView> pool = this.mDanmakuViewPool;
        if (pool == null) {
            throw new NullPointerException("Danmaku view pool is null. Did you call init() first?");
        }
        DanmakuView danmakuView = pool.get();
        if (danmakuView == null) {
            EasyL.m7991w(TAG, "show: Too many danmaku, discard");
            return 2;
        }
        WeakReference<FrameLayout> weakReference = this.mDanmakuContainer;
        if (weakReference == null || weakReference.get() == null) {
            EasyL.m7991w(TAG, "show: Root view is null.");
            return 1;
        }
        danmakuView.setDanmaku(danmaku);
        danmakuView.setTextSize(0, danmaku.size);
        try {
            danmakuView.setTextColor(Color.parseColor(danmaku.color));
        } catch (Exception e) {
            e.printStackTrace();
            danmakuView.setTextColor(-1);
        }
        danmakuView.setBackground(danmakuView.getResources().getDrawable(2131230933, null));
        danmakuView.setPadding(UIUtils.dip2px(10.0f), UIUtils.dip2px(1.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(1.0f));
        int marginTop = getPositionCalculator().getMarginTop(danmakuView);
        UIUtils.logD("test", "marginTop=" + marginTop);
        if (marginTop == -1) {
            String str = TAG;
            EasyL.m7996d(str, "send: screen is full, too many danmaku [" + danmaku + "]");
            return 2;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) danmakuView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new FrameLayout.LayoutParams(-2, -2);
        }
        layoutParams.width = -2;
        layoutParams.topMargin = marginTop;
        danmakuView.setLayoutParams(layoutParams);
        danmakuView.setMinHeight((int) (getConfig().getLineHeight() * 1.35d));
        danmakuView.show(this.mDanmakuContainer.get(), getDisplayDuration(danmaku));
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDisplayDuration(Danmaku danmaku) {
        Config config = getConfig();
        switch (danmaku.mode) {
            case top:
                return config.getDurationTop();
            case bottom:
                return config.getDurationBottom();
            default:
                return config.getDurationScroll();
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Config {
        private int durationBottom;
        private int durationScroll;
        private int durationTop;
        private int lineHeight;
        private int maxScrollLine;

        public int getLineHeight() {
            return this.lineHeight;
        }

        public void setLineHeight(int i) {
            this.lineHeight = i;
        }

        public int getMaxScrollLine() {
            return this.maxScrollLine;
        }

        public int getDurationScroll() {
            if (this.durationScroll == 0) {
                this.durationScroll = 10000;
            }
            return this.durationScroll;
        }

        public void setDurationScroll(int i) {
            this.durationScroll = i;
        }

        public int getDurationTop() {
            if (this.durationTop == 0) {
                this.durationTop = 5000;
            }
            return this.durationTop;
        }

        public void setDurationTop(int i) {
            this.durationTop = i;
        }

        public int getDurationBottom() {
            if (this.durationBottom == 0) {
                this.durationBottom = 5000;
            }
            return this.durationBottom;
        }

        public void setDurationBottom(int i) {
            this.durationBottom = i;
        }

        public int getMaxDanmakuLine() {
            if (this.maxScrollLine == 0) {
                this.maxScrollLine = 12;
            }
            return this.maxScrollLine;
        }

        public void setMaxScrollLine(int i) {
            this.maxScrollLine = i;
        }
    }
}
