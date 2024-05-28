package com.sinovatech.unicom.separatemodule.videocenter.entity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.youth.banner.loader.ImageLoader;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyLoader extends ImageLoader {
    @Override // com.youth.banner.loader.ImageLoaderInterface
    public void displayImage(Context context, final Object obj, ImageView imageView) {
        Log.d("bannershuju", "displayImage: " + obj);
        GlideApp.with(context).load((String) obj).listener(new RequestListener<Drawable>() { // from class: com.sinovatech.unicom.separatemodule.videocenter.entity.MyLoader.1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj2, Target<Drawable> target, boolean z) {
                Log.d("bannershuju", "失败: " + obj);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj2, Target<Drawable> target, DataSource dataSource, boolean z) {
                Log.d("bannershuju", "成功: " + obj);
                return false;
            }
        }).into(imageView);
    }
}
