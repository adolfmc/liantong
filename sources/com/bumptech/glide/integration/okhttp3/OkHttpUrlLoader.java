package com.bumptech.glide.integration.okhttp3;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import java.io.InputStream;
import okhttp3.Call;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class OkHttpUrlLoader implements ModelLoader<GlideUrl, InputStream> {
    private final Call.Factory client;

    @Override // com.bumptech.glide.load.model.ModelLoader
    public boolean handles(@NonNull GlideUrl glideUrl) {
        return true;
    }

    public OkHttpUrlLoader(@NonNull Call.Factory factory) {
        this.client = factory;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public ModelLoader.LoadData<InputStream> buildLoadData(@NonNull GlideUrl glideUrl, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(glideUrl, new OkHttpStreamFetcher(this.client, glideUrl));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private static volatile Call.Factory internalClient;
        private final Call.Factory client;

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        private static Call.Factory getInternalClient() {
            if (internalClient == null) {
                synchronized (Factory.class) {
                    if (internalClient == null) {
                        internalClient = NBSOkHttp3Instrumentation.init();
                    }
                }
            }
            return internalClient;
        }

        public Factory() {
            this(getInternalClient());
        }

        public Factory(@NonNull Call.Factory factory) {
            this.client = factory;
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        @NonNull
        public ModelLoader<GlideUrl, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new OkHttpUrlLoader(this.client);
        }
    }
}
