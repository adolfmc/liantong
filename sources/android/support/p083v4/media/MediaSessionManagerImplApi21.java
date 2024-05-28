package android.support.p083v4.media;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.p083v4.media.MediaSessionManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(21)
/* renamed from: android.support.v4.media.MediaSessionManagerImplApi21 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class MediaSessionManagerImplApi21 extends MediaSessionManagerImplBase {
    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaSessionManagerImplApi21(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // android.support.p083v4.media.MediaSessionManagerImplBase, android.support.p083v4.media.MediaSessionManager.MediaSessionManagerImpl
    public boolean isTrustedForMediaControl(@NonNull MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        return hasMediaControlPermission(remoteUserInfoImpl) || super.isTrustedForMediaControl(remoteUserInfoImpl);
    }

    private boolean hasMediaControlPermission(@NonNull MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        return getContext().checkPermission("android.permission.MEDIA_CONTENT_CONTROL", remoteUserInfoImpl.getPid(), remoteUserInfoImpl.getUid()) == 0;
    }
}
