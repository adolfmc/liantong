package cn.finalteam.galleryfinal.widget.zoonview;

import android.annotation.TargetApi;
import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@TargetApi(14)
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IcsScroller extends GingerScroller {
    public IcsScroller(Context context) {
        super(context);
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.GingerScroller, cn.finalteam.galleryfinal.widget.zoonview.ScrollerProxy
    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }
}
