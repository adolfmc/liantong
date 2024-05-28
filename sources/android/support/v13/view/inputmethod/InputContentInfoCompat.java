package android.support.v13.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.inputmethod.InputContentInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class InputContentInfoCompat {
    private final InputContentInfoCompatImpl mImpl;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    interface InputContentInfoCompatImpl {
        @NonNull
        Uri getContentUri();

        @NonNull
        ClipDescription getDescription();

        @Nullable
        Object getInputContentInfo();

        @Nullable
        Uri getLinkUri();

        void releasePermission();

        void requestPermission();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static final class InputContentInfoCompatBaseImpl implements InputContentInfoCompatImpl {
        @NonNull
        private final Uri mContentUri;
        @NonNull
        private final ClipDescription mDescription;
        @Nullable
        private final Uri mLinkUri;

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @Nullable
        public Object getInputContentInfo() {
            return null;
        }

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void releasePermission() {
        }

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void requestPermission() {
        }

        InputContentInfoCompatBaseImpl(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
            this.mContentUri = uri;
            this.mDescription = clipDescription;
            this.mLinkUri = uri2;
        }

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @NonNull
        public Uri getContentUri() {
            return this.mContentUri;
        }

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @NonNull
        public ClipDescription getDescription() {
            return this.mDescription;
        }

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @Nullable
        public Uri getLinkUri() {
            return this.mLinkUri;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @RequiresApi(25)
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static final class InputContentInfoCompatApi25Impl implements InputContentInfoCompatImpl {
        @NonNull
        final InputContentInfo mObject;

        InputContentInfoCompatApi25Impl(@NonNull Object obj) {
            this.mObject = (InputContentInfo) obj;
        }

        InputContentInfoCompatApi25Impl(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
            this.mObject = new InputContentInfo(uri, clipDescription, uri2);
        }

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @NonNull
        public Uri getContentUri() {
            return this.mObject.getContentUri();
        }

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @NonNull
        public ClipDescription getDescription() {
            return this.mObject.getDescription();
        }

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @Nullable
        public Uri getLinkUri() {
            return this.mObject.getLinkUri();
        }

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @Nullable
        public Object getInputContentInfo() {
            return this.mObject;
        }

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void requestPermission() {
            this.mObject.requestPermission();
        }

        @Override // android.support.v13.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void releasePermission() {
            this.mObject.releasePermission();
        }
    }

    public InputContentInfoCompat(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
        if (Build.VERSION.SDK_INT >= 25) {
            this.mImpl = new InputContentInfoCompatApi25Impl(uri, clipDescription, uri2);
        } else {
            this.mImpl = new InputContentInfoCompatBaseImpl(uri, clipDescription, uri2);
        }
    }

    private InputContentInfoCompat(@NonNull InputContentInfoCompatImpl inputContentInfoCompatImpl) {
        this.mImpl = inputContentInfoCompatImpl;
    }

    @NonNull
    public Uri getContentUri() {
        return this.mImpl.getContentUri();
    }

    @NonNull
    public ClipDescription getDescription() {
        return this.mImpl.getDescription();
    }

    @Nullable
    public Uri getLinkUri() {
        return this.mImpl.getLinkUri();
    }

    @Nullable
    public static InputContentInfoCompat wrap(@Nullable Object obj) {
        if (obj != null && Build.VERSION.SDK_INT >= 25) {
            return new InputContentInfoCompat(new InputContentInfoCompatApi25Impl(obj));
        }
        return null;
    }

    @Nullable
    public Object unwrap() {
        return this.mImpl.getInputContentInfo();
    }

    public void requestPermission() {
        this.mImpl.requestPermission();
    }

    public void releasePermission() {
        this.mImpl.releasePermission();
    }
}
