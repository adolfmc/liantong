package cn.finalteam.galleryfinal;

import android.app.Activity;
import android.widget.AbsListView;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class PauseOnScrollListener implements AbsListView.OnScrollListener {
    private final AbsListView.OnScrollListener externalListener;
    private final boolean pauseOnFling;
    private final boolean pauseOnScroll;

    public abstract void pause();

    public abstract void resume();

    public Activity getActivity() {
        return Global.mPhotoSelectActivity;
    }

    public PauseOnScrollListener(boolean z, boolean z2) {
        this(z, z2, null);
    }

    protected PauseOnScrollListener(boolean z, boolean z2, AbsListView.OnScrollListener onScrollListener) {
        this.pauseOnScroll = z;
        this.pauseOnFling = z2;
        this.externalListener = onScrollListener;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                resume();
                break;
            case 1:
                if (this.pauseOnScroll) {
                    pause();
                    break;
                }
                break;
            case 2:
                if (this.pauseOnFling) {
                    pause();
                    break;
                }
                break;
        }
        AbsListView.OnScrollListener onScrollListener = this.externalListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        AbsListView.OnScrollListener onScrollListener = this.externalListener;
        if (onScrollListener != null) {
            onScrollListener.onScroll(absListView, i, i2, i3);
        }
    }
}
