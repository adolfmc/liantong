package com.bumptech.glide.load.model.stream;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import java.io.InputStream;
import java.net.URL;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UrlLoader implements ModelLoader<URL, InputStream> {
    private final ModelLoader<GlideUrl, InputStream> glideUrlLoader;

    @Override // com.bumptech.glide.load.model.ModelLoader
    public boolean handles(@NonNull URL url) {
        return true;
    }

    public UrlLoader(ModelLoader<GlideUrl, InputStream> modelLoader) {
        this.glideUrlLoader = modelLoader;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public ModelLoader.LoadData<InputStream> buildLoadData(@NonNull URL url, int i, int i2, @NonNull Options options) {
        return this.glideUrlLoader.buildLoadData(new GlideUrl(url), i, i2, options);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class StreamFactory implements ModelLoaderFactory<URL, InputStream> {
        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        @NonNull
        public ModelLoader<URL, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new UrlLoader(multiModelLoaderFactory.build(GlideUrl.class, InputStream.class));
        }
    }
}
