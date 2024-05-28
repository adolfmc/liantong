package com.sinovatech.unicom.p318ui;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

@NBSInstrumented
@GlideModule
/* renamed from: com.sinovatech.unicom.ui.CustomAppGlideModule */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomAppGlideModule extends AppGlideModule {
    @Override // com.bumptech.glide.module.AppGlideModule, com.bumptech.glide.module.AppliesOptions
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder glideBuilder) {
        JniLib.m15918cV(this, context, glideBuilder, 183);
    }

    @Override // com.bumptech.glide.module.AppGlideModule
    public boolean isManifestParsingEnabled() {
        return JniLib.m15917cZ(this, 184);
    }

    @Override // com.bumptech.glide.module.LibraryGlideModule, com.bumptech.glide.module.RegistersComponents
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().connectTimeout(5L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).writeTimeout(10L, TimeUnit.SECONDS);
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(!(writeTimeout instanceof OkHttpClient.Builder) ? writeTimeout.build() : NBSOkHttp3Instrumentation.builderInit(writeTimeout)));
    }
}
