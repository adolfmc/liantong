package com.sinovatech.unicom.p318ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.fort.andjni.JniLib;
import java.io.File;
import java.net.URL;

/* renamed from: com.sinovatech.unicom.ui.GlideRequests */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GlideRequests extends RequestManager {
    @Override // com.bumptech.glide.RequestManager
    @NonNull
    public GlideRequests addDefaultRequestListener(RequestListener<Object> requestListener) {
        Object m15920cL = JniLib.m15920cL(this, requestListener, 295);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequests) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    public synchronized GlideRequests applyDefaultRequestOptions(@NonNull RequestOptions requestOptions) {
        Object m15920cL = JniLib.m15920cL(this, requestOptions, 296);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequests) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    /* renamed from: as */
    public <ResourceType> GlideRequest<ResourceType> mo7976as(@NonNull Class<ResourceType> cls) {
        Object m15920cL = JniLib.m15920cL(this, cls, 297);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<Bitmap> asBitmap() {
        Object m15920cL = JniLib.m15920cL(this, 298);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<Drawable> asDrawable() {
        Object m15920cL = JniLib.m15920cL(this, 299);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<File> asFile() {
        Object m15920cL = JniLib.m15920cL(this, 300);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<GifDrawable> asGif() {
        Object m15920cL = JniLib.m15920cL(this, 301);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<File> download(@Nullable Object obj) {
        Object m15920cL = JniLib.m15920cL(this, obj, 302);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager
    @CheckResult
    @NonNull
    public GlideRequest<File> downloadOnly() {
        Object m15920cL = JniLib.m15920cL(this, 303);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable Bitmap bitmap) {
        Object m15920cL = JniLib.m15920cL(this, bitmap, 304);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable Drawable drawable) {
        Object m15920cL = JniLib.m15920cL(this, drawable, 305);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable Uri uri) {
        Object m15920cL = JniLib.m15920cL(this, uri, 306);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable File file) {
        Object m15920cL = JniLib.m15920cL(this, file, 307);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@RawRes @DrawableRes @Nullable Integer num) {
        Object m15920cL = JniLib.m15920cL(this, num, 308);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable Object obj) {
        Object m15920cL = JniLib.m15920cL(this, obj, 309);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable String str) {
        Object m15920cL = JniLib.m15920cL(this, str, 310);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @Deprecated
    public RequestBuilder<Drawable> load(@Nullable URL url) {
        Object m15920cL = JniLib.m15920cL(this, url, 311);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @NonNull
    public RequestBuilder<Drawable> load(@Nullable byte[] bArr) {
        Object m15920cL = JniLib.m15920cL(this, bArr, 312);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequest) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    public synchronized GlideRequests setDefaultRequestOptions(@NonNull RequestOptions requestOptions) {
        Object m15920cL = JniLib.m15920cL(this, requestOptions, 313);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequests) m15920cL;
    }

    @Override // com.bumptech.glide.RequestManager
    public void setRequestOptions(@NonNull RequestOptions requestOptions) {
        JniLib.m15918cV(this, requestOptions, 314);
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    public /* bridge */ /* synthetic */ RequestManager addDefaultRequestListener(RequestListener requestListener) {
        return addDefaultRequestListener((RequestListener<Object>) requestListener);
    }

    public GlideRequests(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode requestManagerTreeNode, @NonNull Context context) {
        super(glide, lifecycle, requestManagerTreeNode, context);
    }
}
