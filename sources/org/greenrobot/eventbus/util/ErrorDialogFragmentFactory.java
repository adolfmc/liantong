package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.p083v4.app.Fragment;
import org.greenrobot.eventbus.util.ErrorDialogFragments;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class ErrorDialogFragmentFactory<T> {
    protected final ErrorDialogConfig config;

    protected abstract T createErrorFragment(ThrowableFailureEvent throwableFailureEvent, Bundle bundle);

    protected ErrorDialogFragmentFactory(ErrorDialogConfig errorDialogConfig) {
        this.config = errorDialogConfig;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T prepareErrorFragment(ThrowableFailureEvent throwableFailureEvent, boolean z, Bundle bundle) {
        Bundle bundle2;
        if (throwableFailureEvent.isSuppressErrorUi()) {
            return null;
        }
        if (bundle != null) {
            bundle2 = (Bundle) bundle.clone();
        } else {
            bundle2 = new Bundle();
        }
        if (!bundle2.containsKey("de.greenrobot.eventbus.errordialog.title")) {
            bundle2.putString("de.greenrobot.eventbus.errordialog.title", getTitleFor(throwableFailureEvent, bundle2));
        }
        if (!bundle2.containsKey("de.greenrobot.eventbus.errordialog.message")) {
            bundle2.putString("de.greenrobot.eventbus.errordialog.message", getMessageFor(throwableFailureEvent, bundle2));
        }
        if (!bundle2.containsKey("de.greenrobot.eventbus.errordialog.finish_after_dialog")) {
            bundle2.putBoolean("de.greenrobot.eventbus.errordialog.finish_after_dialog", z);
        }
        if (!bundle2.containsKey("de.greenrobot.eventbus.errordialog.event_type_on_close") && this.config.defaultEventTypeOnDialogClosed != null) {
            bundle2.putSerializable("de.greenrobot.eventbus.errordialog.event_type_on_close", this.config.defaultEventTypeOnDialogClosed);
        }
        if (!bundle2.containsKey("de.greenrobot.eventbus.errordialog.icon_id") && this.config.defaultDialogIconId != 0) {
            bundle2.putInt("de.greenrobot.eventbus.errordialog.icon_id", this.config.defaultDialogIconId);
        }
        return createErrorFragment(throwableFailureEvent, bundle2);
    }

    protected String getTitleFor(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
        return this.config.resources.getString(this.config.defaultTitleId);
    }

    protected String getMessageFor(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
        return this.config.resources.getString(this.config.getMessageIdForThrowable(throwableFailureEvent.throwable));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Support extends ErrorDialogFragmentFactory<Fragment> {
        public Support(ErrorDialogConfig errorDialogConfig) {
            super(errorDialogConfig);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.greenrobot.eventbus.util.ErrorDialogFragmentFactory
        public Fragment createErrorFragment(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
            ErrorDialogFragments.Support support = new ErrorDialogFragments.Support();
            support.setArguments(bundle);
            return support;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @TargetApi(11)
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Honeycomb extends ErrorDialogFragmentFactory<android.app.Fragment> {
        public Honeycomb(ErrorDialogConfig errorDialogConfig) {
            super(errorDialogConfig);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.greenrobot.eventbus.util.ErrorDialogFragmentFactory
        public android.app.Fragment createErrorFragment(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
            ErrorDialogFragments.Honeycomb honeycomb = new ErrorDialogFragments.Honeycomb();
            honeycomb.setArguments(bundle);
            return honeycomb;
        }
    }
}
