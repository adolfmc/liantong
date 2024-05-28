package android.support.p083v4.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import androidx.versionedparcelable.VersionedParcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.media.AudioAttributesImpl */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface AudioAttributesImpl extends VersionedParcelable {
    Object getAudioAttributes();

    int getContentType();

    int getFlags();

    int getLegacyStreamType();

    int getRawLegacyStreamType();

    int getUsage();

    int getVolumeControlStream();

    @NonNull
    Bundle toBundle();
}
