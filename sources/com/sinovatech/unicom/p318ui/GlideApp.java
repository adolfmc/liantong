package com.sinovatech.unicom.p318ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.p083v4.app.FragmentActivity;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.fort.andjni.JniLib;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.ui.GlideApp */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class GlideApp {
    @Nullable
    public static File getPhotoCacheDir(@NonNull Context context, @NonNull String str) {
        Object m15920cL = JniLib.m15920cL(context, str, 185);
        if (m15920cL == null) {
            return null;
        }
        return (File) m15920cL;
    }

    @VisibleForTesting
    @SuppressLint({"VisibleForTests"})
    @Deprecated
    public static void init(Glide glide) {
        JniLib.m15918cV(glide, 186);
    }

    @VisibleForTesting
    @SuppressLint({"VisibleForTests"})
    public static void tearDown() {
        JniLib.m15918cV(187);
    }

    @NonNull
    public static GlideRequests with(@NonNull Activity activity) {
        Object m15920cL = JniLib.m15920cL(activity, 188);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequests) m15920cL;
    }

    @NonNull
    @Deprecated
    public static GlideRequests with(@NonNull Fragment fragment) {
        Object m15920cL = JniLib.m15920cL(fragment, 189);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequests) m15920cL;
    }

    @NonNull
    public static GlideRequests with(@NonNull Context context) {
        Object m15920cL = JniLib.m15920cL(context, 190);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequests) m15920cL;
    }

    @NonNull
    public static GlideRequests with(@NonNull android.support.p083v4.app.Fragment fragment) {
        Object m15920cL = JniLib.m15920cL(fragment, 191);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequests) m15920cL;
    }

    @NonNull
    public static GlideRequests with(@NonNull FragmentActivity fragmentActivity) {
        Object m15920cL = JniLib.m15920cL(fragmentActivity, 192);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequests) m15920cL;
    }

    @NonNull
    public static GlideRequests with(@NonNull View view) {
        Object m15920cL = JniLib.m15920cL(view, 193);
        if (m15920cL == null) {
            return null;
        }
        return (GlideRequests) m15920cL;
    }

    private GlideApp() {
    }

    @Nullable
    public static File getPhotoCacheDir(@NonNull Context context) {
        return Glide.getPhotoCacheDir(context);
    }

    @NonNull
    public static Glide get(@NonNull Context context) {
        return Glide.get(context);
    }

    @VisibleForTesting
    @SuppressLint({"VisibleForTests"})
    public static void init(@NonNull Context context, @NonNull GlideBuilder glideBuilder) {
        Glide.init(context, glideBuilder);
    }
}
