package com.huawei.hms.api;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.activity.EnableServiceActivity;
import com.huawei.hms.activity.ForegroundIntentBuilder;
import com.huawei.hms.activity.internal.BusResponseCallback;
import com.huawei.hms.activity.internal.BusResponseResult;
import com.huawei.hms.adapter.AvailableUtil;
import com.huawei.hms.adapter.p213ui.UpdateAdapter;
import com.huawei.hms.common.ErrorDialogFragment;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.api.AvailabilityException;
import com.huawei.hms.common.api.HuaweiApiCallable;
import com.huawei.hms.common.internal.ConnectionErrorMessages;
import com.huawei.hms.common.internal.DialogRedirect;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.note.AppSpoofResolution;
import com.huawei.hms.update.note.DoNothingResolution;
import com.huawei.hms.update.note.NotInstalledHmsResolution;
import com.huawei.hms.update.p225ui.UpdateBean;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.PackageManagerHelper;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.huawei.hms.utils.UIUtil;
import com.huawei.hms.utils.Util;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class HuaweiApiAvailabilityImpl extends HuaweiApiAvailability {

    /* renamed from: a */
    private static final HuaweiApiAvailabilityImpl f10989a = new HuaweiApiAvailabilityImpl();

    /* renamed from: com.huawei.hms.api.HuaweiApiAvailabilityImpl$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class C4855a implements BusResponseCallback {

        /* renamed from: a */
        final /* synthetic */ TaskCompletionSource[] f10990a;

        C4855a(TaskCompletionSource[] taskCompletionSourceArr) {
            this.f10990a = taskCompletionSourceArr;
        }

        @Override // com.huawei.hms.activity.internal.BusResponseCallback
        public BusResponseResult innerError(Activity activity, int i, String str) {
            HMSLog.m14112e("HuaweiApiAvailabilityImpl", "Test foreground bus error: resultCode " + i + ", errMessage" + str);
            this.f10990a[0].setException(new AvailabilityException());
            return null;
        }

        @Override // com.huawei.hms.activity.internal.BusResponseCallback
        public BusResponseResult succeedReturn(Activity activity, int i, Intent intent) {
            HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Test foreground bus success: resultCode " + i + ", data" + intent);
            return null;
        }
    }

    private HuaweiApiAvailabilityImpl() {
    }

    /* renamed from: a */
    private static Intent m15231a(Activity activity, String str) {
        return BridgeActivity.getIntentStartBridgeActivity(activity, str);
    }

    public static HuaweiApiAvailabilityImpl getInstance() {
        return f10989a;
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public Task<Void> checkApiAccessible(HuaweiApi<?> huaweiApi, HuaweiApi<?>... huaweiApiArr) {
        Task<Void> task = new TaskCompletionSource().getTask();
        if (huaweiApi != null) {
            try {
                m15228a(huaweiApi);
            } catch (AvailabilityException e) {
                HMSLog.m14110i("HuaweiApiAvailabilityImpl", "checkApi has AvailabilityException " + e.getMessage());
            }
        }
        if (huaweiApiArr != null) {
            for (HuaweiApi<?> huaweiApi2 : huaweiApiArr) {
                m15228a(huaweiApi2);
            }
        }
        return task;
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public PendingIntent getErrPendingIntent(Context context, ConnectionResult connectionResult) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(connectionResult);
        return getResolveErrorPendingIntent(context, connectionResult.getErrorCode());
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public Dialog getErrorDialog(Activity activity, int i, int i2) {
        Checker.checkNonNull(activity, "activity must not be null.");
        HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Enter getErrorDialog, errorCode: " + i);
        return getErrorDialog(activity, i, i2, null);
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public String getErrorString(int i) {
        HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Enter getErrorString, errorCode: " + i);
        return ConnectionResult.getErrorString(i);
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public Task<Void> getHuaweiServicesReady(Activity activity) {
        Preconditions.checkNotNull(activity);
        TaskCompletionSource[] taskCompletionSourceArr = {new TaskCompletionSource()};
        Task<Void> task = taskCompletionSourceArr[0].getTask();
        int isHuaweiMobileServicesAvailable = isHuaweiMobileServicesAvailable(activity.getApplicationContext(), 30000000);
        Intent resolveErrorIntent = getResolveErrorIntent(activity, isHuaweiMobileServicesAvailable);
        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(activity, ResolutionDelegate.class.getName());
        if (resolveErrorIntent != null) {
            ForegroundIntentBuilder.registerResponseCallback(ResolutionDelegate.CALLBACK_METHOD, new C4855a(taskCompletionSourceArr));
            Bundle bundle = new Bundle();
            bundle.putParcelable("resolution", resolveErrorIntent);
            intentStartBridgeActivity.putExtras(bundle);
            activity.startActivity(intentStartBridgeActivity);
        } else if (isHuaweiMobileServicesAvailable == 3) {
            Intent intent = new Intent();
            intent.setClass(activity, EnableServiceActivity.class);
            activity.startActivity(intent);
        } else if (isHuaweiMobileServicesAvailable == 0) {
            HMSLog.m14110i("HuaweiApiAvailabilityImpl", "The HMS service is available.");
        } else {
            HMSLog.m14112e("HuaweiApiAvailabilityImpl", "Framework can not solve the availability problem.");
            taskCompletionSourceArr[0].setException(new AvailabilityException());
        }
        return task;
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public Intent getResolveErrorIntent(Activity activity, int i) {
        HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Enter getResolveErrorIntent, errorCode: " + i);
        if (i == 1 || i == 2) {
            if (Util.isAvailableLibExist(activity) && AvailableUtil.isInstallerLibExist(activity)) {
                return (Intent) UpdateAdapter.invokeMethod("com.huawei.hms.update.manager.UpdateManager", "getStartUpdateIntent", new Object[]{activity, m15230a(activity.getApplicationContext())});
            }
            return m15231a(activity, NotInstalledHmsResolution.class.getName());
        } else if (i != 6) {
            if (i == 9 && Util.isAvailableLibExist(activity)) {
                return m15231a(activity, AppSpoofResolution.class.getName());
            }
            return null;
        } else {
            return m15231a(activity, BindingFailedResolution.class.getName());
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public PendingIntent getResolveErrorPendingIntent(Activity activity, int i) {
        HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Enter getResolveErrorPendingIntent, errorCode: " + i);
        Intent resolveErrorIntent = getResolveErrorIntent(activity, i);
        if (resolveErrorIntent != null) {
            return PendingIntent.getActivity(activity, 0, resolveErrorIntent, 67108864);
        }
        return null;
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public int isHuaweiMobileNoticeAvailable(Context context) {
        Checker.checkNonNull(context, "context must not be null.");
        if (PackageManagerHelper.PackageStates.NOT_INSTALLED.equals(new PackageManagerHelper(context).getPackageStates(HMSPackageManager.getInstance(context).getHMSPackageNameForMultiService()))) {
            return 1;
        }
        return HMSPackageManager.getInstance(context).isApkUpdateNecessary(20600000) ? 2 : 0;
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public int isHuaweiMobileServicesAvailable(Context context) {
        Checker.checkNonNull(context, "context must not be null.");
        return HuaweiMobileServicesUtil.isHuaweiMobileServicesAvailable(context, HuaweiApiAvailability.getServicesVersionCode());
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public boolean isUserResolvableError(int i) {
        return isUserResolvableError(i, null);
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public boolean isUserResolvableError(int i, PendingIntent pendingIntent) {
        if (i == 0) {
            return false;
        }
        return pendingIntent != null || i == 1 || i == 2 || i == 6 || i == 9;
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public void popupErrNotification(Context context, ConnectionResult connectionResult) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(connectionResult);
        showErrorNotification(context, connectionResult.getErrorCode());
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public void resolveError(Activity activity, int i, int i2) {
        resolveError(activity, i, i2, null);
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public boolean showErrorDialogFragment(Activity activity, int i, int i2) {
        return showErrorDialogFragment(activity, i, i2, null);
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public void showErrorNotification(Context context, int i) {
        Checker.checkNonNull(context, "context must not be null.");
        HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Enter showErrorNotification, errorCode: " + i);
        if (!(context instanceof Activity)) {
            HMSLog.m14110i("HuaweiApiAvailabilityImpl", "context not instanceof Activity");
            return;
        }
        Dialog errorDialog = getErrorDialog((Activity) context, i, 0);
        if (errorDialog == null) {
            HMSLog.m14110i("HuaweiApiAvailabilityImpl", "showErrorNotification errorDialog can not be null");
        } else {
            errorDialog.show();
        }
    }

    /* renamed from: a */
    private static Intent m15229a(Context context, String str) {
        return BridgeActivity.getIntentStartBridgeActivity(context, str);
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public void resolveError(Activity activity, int i, int i2, PendingIntent pendingIntent) {
        Checker.checkNonNull(activity, "activity must not be null.");
        if (pendingIntent != null) {
            HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Enter resolveError, param pendingIntent is not null. and.errorCode: " + i);
        } else {
            HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Enter resolveError, param pendingIntent is  null. get pendingIntent from error code.and.errorCode: " + i);
            pendingIntent = getResolveErrorPendingIntent(activity, i);
        }
        if (pendingIntent != null) {
            HMSLog.m14110i("HuaweiApiAvailabilityImpl", "In resolveError, start pendingIntent.errorCode: " + i);
            try {
                activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i2, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException unused) {
                HMSLog.m14112e("HuaweiApiAvailabilityImpl", "Enter resolveError, start pendingIntent failed.errorCode: " + i);
            }
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public boolean showErrorDialogFragment(Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Dialog errorDialog = getErrorDialog(activity, i, i2, onCancelListener);
        if (errorDialog == null) {
            return false;
        }
        m15232a(activity, errorDialog, "HuaweiMobileServicesErrorDialog", onCancelListener);
        return true;
    }

    /* renamed from: a */
    private UpdateBean m15230a(Context context) {
        UpdateBean updateBean = new UpdateBean();
        updateBean.setHmsOrApkUpgrade(true);
        updateBean.setClientPackageName(HMSPackageManager.getInstance(context).getHMSPackageName());
        updateBean.setClientVersionCode(HuaweiApiAvailability.getServicesVersionCode());
        updateBean.setClientAppId("C10132067");
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(context);
        }
        try {
            updateBean.setClientAppName(ResourceLoaderUtil.getString("hms_update_title"));
        } catch (Exception e) {
            HMSLog.m14112e("HuaweiApiAvailabilityImpl", "getString has Exception:" + e.getMessage());
        }
        return updateBean;
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public Dialog getErrorDialog(Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Checker.checkNonNull(activity, "activity must not be null.");
        HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Enter getErrorDialog, errorCode: " + i);
        return m15233a(activity, i, DialogRedirect.getInstance(activity, m15234a(activity, i), i2), onCancelListener);
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public PendingIntent getErrPendingIntent(Context context, int i, int i2) {
        HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Enter getResolveErrorPendingIntent, errorCode: " + i + " requestCode: " + i2);
        Intent resolveErrorIntent = getResolveErrorIntent(context, i);
        if (resolveErrorIntent != null) {
            return PendingIntent.getActivity(context, i2, resolveErrorIntent, 67108864);
        }
        return null;
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public int isHuaweiMobileServicesAvailable(Context context, int i) {
        Checker.checkNonNull(context, "context must not be null.");
        return HuaweiMobileServicesUtil.isHuaweiMobileServicesAvailable(context, i);
    }

    public PendingIntent getResolveErrorPendingIntent(Context context, int i) {
        HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Enter getResolveErrorPendingIntent, errorCode: " + i);
        Intent resolveErrorIntent = getResolveErrorIntent(context, i);
        if (resolveErrorIntent != null) {
            return PendingIntent.getActivity(context, 0, resolveErrorIntent, 67108864);
        }
        return null;
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public boolean showErrorDialogFragment(Activity activity, int i, Fragment fragment, int i2, DialogInterface.OnCancelListener onCancelListener) {
        return showErrorDialogFragment(activity, i, i2, onCancelListener);
    }

    @Override // com.huawei.hms.api.HuaweiApiAvailability
    public Task<Void> checkApiAccessible(HuaweiApiCallable huaweiApiCallable, HuaweiApiCallable... huaweiApiCallableArr) {
        Task<Void> task = new TaskCompletionSource().getTask();
        if (huaweiApiCallable != null) {
            try {
                m15228a(huaweiApiCallable);
            } catch (AvailabilityException e) {
                HMSLog.m14110i("HuaweiApiAvailabilityImpl", "HuaweiApiCallable checkApi has AvailabilityException " + e.getMessage());
            }
        }
        if (huaweiApiCallableArr != null) {
            for (HuaweiApiCallable huaweiApiCallable2 : huaweiApiCallableArr) {
                m15228a(huaweiApiCallable2);
            }
        }
        return task;
    }

    /* renamed from: a */
    private Intent m15234a(Activity activity, int i) {
        HMSLog.m14110i("HuaweiApiAvailabilityImpl", "getErrorResolutionIntent, errorCode: " + i);
        if (i == 1 || i == 2) {
            if (Util.isAvailableLibExist(activity) && AvailableUtil.isInstallerLibExist(activity)) {
                return (Intent) UpdateAdapter.invokeMethod("com.huawei.hms.update.manager.UpdateManager", "startUpdateIntent", new Object[]{activity});
            }
            return BridgeActivity.getIntentStartBridgeActivity(activity, DoNothingResolution.class.getName());
        } else if (i != 6) {
            if (i == 9 && Util.isAvailableLibExist(activity)) {
                return BridgeActivity.getIntentStartBridgeActivity(activity, AppSpoofResolution.class.getName());
            }
            return null;
        } else {
            return BridgeActivity.getIntentStartBridgeActivity(activity, BindingFailedResolution.class.getName());
        }
    }

    public Intent getResolveErrorIntent(Context context, int i) {
        HMSLog.m14110i("HuaweiApiAvailabilityImpl", "Enter getResolveErrorIntent, errorCode: " + i);
        if (i == 1 || i == 2) {
            if (Util.isAvailableLibExist(context) && AvailableUtil.isInstallerLibExist(context)) {
                return (Intent) UpdateAdapter.invokeMethod("com.huawei.hms.update.manager.UpdateManager", "getStartUpdateIntent", new Object[]{context, m15230a(context.getApplicationContext())});
            }
            return m15229a(context, NotInstalledHmsResolution.class.getName());
        } else if (i != 6) {
            if (i == 9 && Util.isAvailableLibExist(context)) {
                return m15229a(context, AppSpoofResolution.class.getName());
            }
            return null;
        } else {
            return m15229a(context, BindingFailedResolution.class.getName());
        }
    }

    /* renamed from: a */
    private static Dialog m15233a(Activity activity, int i, DialogRedirect dialogRedirect, DialogInterface.OnCancelListener onCancelListener) {
        if (i == 0) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, UIUtil.getDialogThemeId(activity));
        builder.setMessage(ConnectionErrorMessages.getErrorMessage(activity, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        builder.setPositiveButton(ConnectionErrorMessages.getErrorDialogButtonMessage(activity, i), dialogRedirect);
        if (Util.isAvailableLibExist(activity) && AvailableUtil.isInstallerLibExist(activity)) {
            String errorTitle = ConnectionErrorMessages.getErrorTitle(activity, i);
            if (errorTitle != null) {
                builder.setTitle(errorTitle);
            }
        } else {
            String errorTitle2 = ConnectionErrorMessages.getErrorTitle(activity, i);
            if (errorTitle2 != null) {
                builder.setTitle(errorTitle2);
            }
        }
        return builder.create();
    }

    /* renamed from: a */
    private static void m15232a(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        Checker.checkNonNull(activity, "activity must not be null.");
        ErrorDialogFragment.newInstance(dialog, onCancelListener).show(activity.getFragmentManager(), str);
    }

    /* renamed from: a */
    private void m15228a(Object obj) throws AvailabilityException {
        ConnectionResult connectionResult;
        AvailabilityException availabilityException = new AvailabilityException();
        if (obj instanceof HuaweiApi) {
            connectionResult = availabilityException.getConnectionResult((HuaweiApi) obj);
        } else {
            connectionResult = availabilityException.getConnectionResult((HuaweiApiCallable) obj);
        }
        if (connectionResult.getErrorCode() == 0) {
            return;
        }
        HMSLog.m14110i("HuaweiApiAvailabilityImpl", "The service is unavailable: " + availabilityException.getMessage());
        throw availabilityException;
    }
}
