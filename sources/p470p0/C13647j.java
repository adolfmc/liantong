package p470p0;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: p0.j */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13647j {
    /* renamed from: a */
    public static void m180a(Context context, int i, ImageView imageView) {
        Glide.with(context).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) new RequestOptions().centerInside().diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH)).into(imageView);
    }

    /* renamed from: a */
    public static void m179a(Context context, String str, ImageView imageView) {
        Glide.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH)).transition(new DrawableTransitionOptions().crossFade(500)).into(imageView);
    }

    /* renamed from: a */
    public static void m178a(Context context, String str, ImageView imageView, int i) {
        Glide.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().error(i).diskCacheStrategy(DiskCacheStrategy.ALL)).transition(new DrawableTransitionOptions().crossFade(500)).into(imageView);
    }

    /* renamed from: a */
    public static void m177a(Context context, String str, SimpleTarget<Drawable> simpleTarget) {
        Glide.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.NORMAL)).into((RequestBuilder<Drawable>) simpleTarget);
    }
}
