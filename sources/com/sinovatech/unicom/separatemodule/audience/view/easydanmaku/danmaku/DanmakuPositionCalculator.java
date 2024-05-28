package com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku;

import android.widget.FrameLayout;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.DanmakuView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DanmakuPositionCalculator {
    private static final String TAG = "DanPositionCalculator";
    private boolean[] mBottoms;
    private DanmakuManager mDanmakuManager;
    private List<DanmakuView> mLastDanmakus = new ArrayList();
    private boolean[] mTops;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DanmakuPositionCalculator(DanmakuManager danmakuManager) {
        this.mDanmakuManager = danmakuManager;
        int maxDanmakuLine = danmakuManager.getConfig().getMaxDanmakuLine();
        this.mTops = new boolean[maxDanmakuLine];
        this.mBottoms = new boolean[maxDanmakuLine];
    }

    private int getLineHeightWithPadding() {
        return (int) (this.mDanmakuManager.getConfig().getLineHeight() * 1.35f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMarginTop(DanmakuView danmakuView) {
        switch (danmakuView.getDanmaku().mode) {
            case scroll:
                return getScrollY(danmakuView);
            case top:
                return getTopY(danmakuView);
            case bottom:
                return getBottomY(danmakuView);
            default:
                return -1;
        }
    }

    private int getScrollY(DanmakuView danmakuView) {
        UIUtils.logD("test", "mLastDanmakus.size=" + this.mLastDanmakus.size());
        int i = 0;
        if (this.mLastDanmakus.size() == 0) {
            this.mLastDanmakus.add(danmakuView);
            UIUtils.logD("test", "0");
            return 0;
        }
        while (i < this.mLastDanmakus.size()) {
            DanmakuView danmakuView2 = this.mLastDanmakus.get(i);
            int calTimeDisappear = calTimeDisappear(danmakuView2);
            int calTimeArrive = calTimeArrive(danmakuView);
            boolean isFullyShown = isFullyShown(danmakuView2);
            UIUtils.logD("test", "timeDisappear =" + calTimeDisappear + "|timeArrive=" + calTimeArrive + "|isFullyShown=" + isFullyShown + "|i=" + i);
            if (calTimeDisappear <= calTimeArrive && isFullyShown) {
                this.mLastDanmakus.set(i, danmakuView);
                return i * getLineHeightWithPadding();
            }
            i++;
        }
        int maxDanmakuLine = this.mDanmakuManager.getConfig().getMaxDanmakuLine();
        if (maxDanmakuLine == 0 || i < maxDanmakuLine) {
            this.mLastDanmakus.add(danmakuView);
            UIUtils.logD("test", "i * getLineHeightWithPadding()=" + (getLineHeightWithPadding() * i));
            return i * getLineHeightWithPadding();
        }
        UIUtils.logD("test", "-1");
        return -1;
    }

    private int getTopY(DanmakuView danmakuView) {
        final int i = 0;
        while (true) {
            boolean[] zArr = this.mTops;
            if (i >= zArr.length) {
                return -1;
            }
            if (!zArr[i]) {
                zArr[i] = true;
                danmakuView.addOnExitListener(new DanmakuView.OnExitListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.-$$Lambda$DanmakuPositionCalculator$oJlcbxIdMeQYN11qh4cIPkN8hNA
                    @Override // com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.DanmakuView.OnExitListener
                    public final void onExit(DanmakuView danmakuView2) {
                        DanmakuPositionCalculator.lambda$getTopY$0(DanmakuPositionCalculator.this, i, danmakuView2);
                    }
                });
                return i * getLineHeightWithPadding();
            }
            i++;
        }
    }

    public static /* synthetic */ void lambda$getTopY$0(DanmakuPositionCalculator danmakuPositionCalculator, int i, DanmakuView danmakuView) {
        danmakuPositionCalculator.mTops[i] = false;
    }

    private int getBottomY(DanmakuView danmakuView) {
        final int i = 0;
        while (true) {
            boolean[] zArr = this.mBottoms;
            if (i >= zArr.length) {
                return -1;
            }
            if (!zArr[i]) {
                zArr[i] = true;
                danmakuView.addOnExitListener(new DanmakuView.OnExitListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.-$$Lambda$DanmakuPositionCalculator$EkMq9yeQdKoVgKhleHUNl7P-olo
                    @Override // com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.DanmakuView.OnExitListener
                    public final void onExit(DanmakuView danmakuView2) {
                        DanmakuPositionCalculator.lambda$getBottomY$1(DanmakuPositionCalculator.this, i, danmakuView2);
                    }
                });
                return getParentHeight() - ((i + 1) * getLineHeightWithPadding());
            }
            i++;
        }
    }

    public static /* synthetic */ void lambda$getBottomY$1(DanmakuPositionCalculator danmakuPositionCalculator, int i, DanmakuView danmakuView) {
        danmakuPositionCalculator.mBottoms[i] = false;
    }

    private boolean isFullyShown(DanmakuView danmakuView) {
        if (danmakuView == null) {
            return true;
        }
        int textLength = danmakuView.getTextLength();
        return danmakuView.getLeft() + ((textLength * 2) / 3) <= textLength;
    }

    private int calTimeDisappear(DanmakuView danmakuView) {
        if (danmakuView == null) {
            return 0;
        }
        return (int) ((danmakuView.getTextLength() - danmakuView.getScrollX()) / calSpeed(danmakuView));
    }

    private int calTimeArrive(DanmakuView danmakuView) {
        return (int) (getParentWidth() / calSpeed(danmakuView));
    }

    private float calSpeed(DanmakuView danmakuView) {
        return ((danmakuView.getTextLength() + getParentWidth()) + 0.0f) / this.mDanmakuManager.getDisplayDuration(danmakuView.getDanmaku());
    }

    private int getParentHeight() {
        FrameLayout frameLayout = this.mDanmakuManager.mDanmakuContainer.get();
        if (frameLayout == null || frameLayout.getHeight() == 0) {
            return 1080;
        }
        return frameLayout.getHeight();
    }

    private int getParentWidth() {
        FrameLayout frameLayout = this.mDanmakuManager.mDanmakuContainer.get();
        if (frameLayout == null || frameLayout.getWidth() == 0) {
            return 1920;
        }
        return frameLayout.getWidth();
    }
}
