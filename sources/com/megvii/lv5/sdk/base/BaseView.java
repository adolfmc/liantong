package com.megvii.lv5.sdk.base;

import android.app.Activity;
import android.content.Context;
import android.media.projection.MediaProjection;
import com.megvii.lv5.sdk.manager.MegLiveDetectConfig;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface BaseView {
    Activity getActivity();

    Context getContext();

    MediaProjection getScreenRecordContext();

    MegLiveDetectConfig getUserDetectConfig();
}
