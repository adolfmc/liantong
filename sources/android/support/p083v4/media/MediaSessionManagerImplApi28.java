package android.support.p083v4.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import android.support.annotation.RequiresApi;
import android.support.p083v4.media.MediaSessionManager;
import android.support.p083v4.util.ObjectsCompat;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(28)
/* renamed from: android.support.v4.media.MediaSessionManagerImplApi28 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
    MediaSessionManager mObject;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaSessionManagerImplApi28(Context context) {
        super(context);
        this.mObject = (MediaSessionManager) context.getSystemService("media_session");
    }

    @Override // android.support.p083v4.media.MediaSessionManagerImplApi21, android.support.p083v4.media.MediaSessionManagerImplBase, android.support.p083v4.media.MediaSessionManager.MediaSessionManagerImpl
    public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        if (remoteUserInfoImpl instanceof RemoteUserInfoImplApi28) {
            return this.mObject.isTrustedForMediaControl(((RemoteUserInfoImplApi28) remoteUserInfoImpl).mObject);
        }
        return false;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v4.media.MediaSessionManagerImplApi28$RemoteUserInfoImplApi28 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static final class RemoteUserInfoImplApi28 implements MediaSessionManager.RemoteUserInfoImpl {
        final MediaSessionManager.RemoteUserInfo mObject;

        /* JADX INFO: Access modifiers changed from: package-private */
        public RemoteUserInfoImplApi28(String str, int i, int i2) {
            this.mObject = new MediaSessionManager.RemoteUserInfo(str, i, i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.mObject = remoteUserInfo;
        }

        @Override // android.support.p083v4.media.MediaSessionManager.RemoteUserInfoImpl
        public String getPackageName() {
            return this.mObject.getPackageName();
        }

        @Override // android.support.p083v4.media.MediaSessionManager.RemoteUserInfoImpl
        public int getPid() {
            return this.mObject.getPid();
        }

        @Override // android.support.p083v4.media.MediaSessionManager.RemoteUserInfoImpl
        public int getUid() {
            return this.mObject.getUid();
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mObject);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof RemoteUserInfoImplApi28) {
                return this.mObject.equals(((RemoteUserInfoImplApi28) obj).mObject);
            }
            return false;
        }
    }
}
