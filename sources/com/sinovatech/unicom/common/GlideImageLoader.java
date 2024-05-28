package com.sinovatech.unicom.common;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.widget.GFImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.p318ui.GlideRequests;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GlideImageLoader implements ImageLoader {
    @Override // cn.finalteam.galleryfinal.ImageLoader
    public void clearMemoryCache() {
    }

    @Override // cn.finalteam.galleryfinal.ImageLoader
    public void displayImage(Activity activity, String str, GFImageView gFImageView, Drawable drawable, int i, int i2) {
        GlideRequests with = GlideApp.with(activity);
        with.load("file://" + str).placeholder(drawable).error(drawable).override(i, i2).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(gFImageView);
    }
}
