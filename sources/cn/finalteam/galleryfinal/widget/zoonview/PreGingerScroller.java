package cn.finalteam.galleryfinal.widget.zoonview;

import android.content.Context;
import android.widget.Scroller;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class PreGingerScroller extends ScrollerProxy {
    private final Scroller mScroller;

    public PreGingerScroller(Context context) {
        this.mScroller = new Scroller(context);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.ScrollerProxy
    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.ScrollerProxy
    public void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.mScroller.fling(i, i2, i3, i4, i5, i6, i7, i8);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.ScrollerProxy
    public void forceFinished(boolean z) {
        this.mScroller.forceFinished(z);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.ScrollerProxy
    public boolean isFinished() {
        return this.mScroller.isFinished();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.ScrollerProxy
    public int getCurrX() {
        return this.mScroller.getCurrX();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.ScrollerProxy
    public int getCurrY() {
        return this.mScroller.getCurrY();
    }
}
