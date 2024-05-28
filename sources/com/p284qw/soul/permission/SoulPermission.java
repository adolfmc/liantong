package com.p284qw.soul.permission;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CheckResult;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.ActivityCompat;
import android.util.Log;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.bean.Special;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.p284qw.soul.permission.callbcak.CheckStatusCallBack;
import com.p284qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.p284qw.soul.permission.callbcak.RequestPermissionListener;
import com.p284qw.soul.permission.callbcak.SpecialPermissionListener;
import com.p284qw.soul.permission.checker.CheckerFactory;
import com.p284qw.soul.permission.debug.PermissionDebug;
import com.p284qw.soul.permission.request.PermissionRequester;
import java.util.LinkedList;

/* renamed from: com.qw.soul.permission.SoulPermission */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SoulPermission {
    private static final String TAG = "SoulPermission";
    private static volatile boolean alreadyInit;
    private static Application globalContext;
    private static volatile SoulPermission instance;
    private PermissionActivityLifecycle lifecycle;

    public static SoulPermission getInstance() {
        if (instance == null) {
            synchronized (SoulPermission.class) {
                if (instance == null) {
                    instance = new SoulPermission();
                }
            }
        }
        return instance;
    }

    public static void setDebug(boolean z) {
        PermissionDebug.setDebug(z);
    }

    public static void init(@NonNull Application application) {
        if (alreadyInit) {
            PermissionDebug.m8222w(TAG, "already init");
            return;
        }
        alreadyInit = true;
        globalContext = application;
        getInstance().registerLifecycle(globalContext);
        PermissionDebug.m8226d(TAG, "user init");
    }

    @CheckResult
    public Permission checkSinglePermission(@NonNull String str) {
        if (checkPermissions(str).length == 0) {
            return null;
        }
        return checkPermissions(str)[0];
    }

    @CheckResult
    public Permission[] checkPermissions(@NonNull String... strArr) {
        LinkedList linkedList = new LinkedList();
        Activity topActivity = getTopActivity();
        if (topActivity == null) {
            PermissionDebug.m8222w(TAG, " get top activity failed check your app status");
            return new Permission[0];
        }
        for (String str : strArr) {
            linkedList.add(new Permission(str, checkPermission(topActivity, str) ? 0 : -1, ActivityCompat.shouldShowRequestPermissionRationale(topActivity, str)));
        }
        return PermissionTools.convert(linkedList);
    }

    public boolean checkSpecialPermission(Special special) {
        Activity topActivity = getTopActivity();
        if (topActivity == null) {
            PermissionDebug.m8222w(TAG, " get top activity failed check your app status");
            return true;
        }
        return CheckerFactory.create(topActivity, special).check();
    }

    @MainThread
    public void checkAndRequestPermission(@NonNull String str, @NonNull final CheckRequestPermissionListener checkRequestPermissionListener) {
        checkAndRequestPermissions(Permissions.build(str), new CheckRequestPermissionsListener() { // from class: com.qw.soul.permission.SoulPermission.1
            @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
            public void onAllPermissionOk(Permission[] permissionArr) {
                checkRequestPermissionListener.onPermissionOk(permissionArr[0]);
            }

            @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
            public void onPermissionDenied(Permission[] permissionArr) {
                checkRequestPermissionListener.onPermissionDenied(permissionArr[0]);
            }
        });
    }

    @MainThread
    public void checkAndRequestPermissions(@NonNull Permissions permissions, @NonNull CheckRequestPermissionsListener checkRequestPermissionsListener) {
        Permission[] checkPermissions = checkPermissions(permissions.getPermissionsString());
        if (checkPermissions.length == 0) {
            PermissionDebug.m8222w(TAG, "bad status ,check your application status");
            return;
        }
        Permission[] filterRefusedPermissions = filterRefusedPermissions(checkPermissions);
        if (filterRefusedPermissions.length == 0) {
            PermissionDebug.m8226d(TAG, "all permissions ok");
            checkRequestPermissionsListener.onAllPermissionOk(checkPermissions);
        } else if (canRequestRunTimePermission()) {
            requestPermissions(Permissions.build(filterRefusedPermissions), checkRequestPermissionsListener);
        } else {
            PermissionDebug.m8226d(TAG, "some permission refused but can not request");
            checkRequestPermissionsListener.onPermissionDenied(filterRefusedPermissions);
        }
    }

    @MainThread
    public void checkAndRequestPermission(@NonNull Special special, @NonNull SpecialPermissionListener specialPermissionListener) {
        if (checkSpecialPermission(special)) {
            specialPermissionListener.onGranted(special);
            return;
        }
        int i = Build.VERSION.SDK_INT;
        if (C68977.$SwitchMap$com$qw$soul$permission$bean$Special[special.ordinal()] != 1) {
            if (i < 19) {
                specialPermissionListener.onDenied(special);
                return;
            }
        } else if (i < 26) {
            specialPermissionListener.onDenied(special);
            return;
        }
        requestSpecialPermission(special, specialPermissionListener);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qw.soul.permission.SoulPermission$7 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static /* synthetic */ class C68977 {
        static final /* synthetic */ int[] $SwitchMap$com$qw$soul$permission$bean$Special = new int[Special.values().length];

        static {
            try {
                $SwitchMap$com$qw$soul$permission$bean$Special[Special.UNKNOWN_APP_SOURCES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$qw$soul$permission$bean$Special[Special.SYSTEM_ALERT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$qw$soul$permission$bean$Special[Special.NOTIFICATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public Context getContext() {
        return globalContext;
    }

    @CheckResult
    @Nullable
    public Activity getTopActivity() {
        try {
            return this.lifecycle.getActivity();
        } catch (Exception e) {
            if (PermissionDebug.isDebug()) {
                PermissionTools.toast(getContext(), e.toString());
                Log.e(TAG, e.toString());
            }
            return null;
        }
    }

    @Deprecated
    public void goPermissionSettings() {
        goApplicationSettings();
    }

    @Deprecated
    public void goApplicationSettings(int i) {
        PermissionTools.jumpAppDetail(getTopActivity(), i);
    }

    public void goApplicationSettings(@Nullable final GoAppDetailCallBack goAppDetailCallBack) {
        checkStatusBeforeDoSomething(new CheckStatusCallBack() { // from class: com.qw.soul.permission.SoulPermission.2
            @Override // com.p284qw.soul.permission.callbcak.CheckStatusCallBack
            public void onStatusOk(Activity activity) {
                new PermissionRequester(activity).goAppDetail(goAppDetailCallBack);
            }
        });
    }

    public void goApplicationSettings() {
        goApplicationSettings((GoAppDetailCallBack) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void autoInit(Application application) {
        if (globalContext != null) {
            return;
        }
        globalContext = application;
        registerLifecycle(globalContext);
    }

    private SoulPermission() {
    }

    private void registerLifecycle(Application application) {
        PermissionActivityLifecycle permissionActivityLifecycle = this.lifecycle;
        if (permissionActivityLifecycle != null) {
            application.unregisterActivityLifecycleCallbacks(permissionActivityLifecycle);
        }
        this.lifecycle = new PermissionActivityLifecycle();
        application.registerActivityLifecycleCallbacks(this.lifecycle);
    }

    private Permission[] filterRefusedPermissions(Permission[] permissionArr) {
        LinkedList linkedList = new LinkedList();
        for (Permission permission : permissionArr) {
            if (!permission.isGranted()) {
                linkedList.add(permission);
            }
        }
        PermissionDebug.m8226d(TAG, "refusedPermissionList.size" + linkedList.size());
        return PermissionTools.convert(linkedList);
    }

    private boolean canRequestRunTimePermission() {
        return !PermissionTools.isOldPermissionSystem(getTopActivity());
    }

    private boolean checkPermission(Context context, String str) {
        return CheckerFactory.create(context, str).check();
    }

    private void checkStatusBeforeDoSomething(final CheckStatusCallBack checkStatusCallBack) {
        try {
            final Activity activity = this.lifecycle.getActivity();
            if (!PermissionTools.assertMainThread()) {
                PermissionDebug.m8222w(TAG, "do not request permission in other thread");
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.qw.soul.permission.SoulPermission.3
                    @Override // java.lang.Runnable
                    public void run() {
                        checkStatusCallBack.onStatusOk(activity);
                    }
                });
                return;
            }
            checkStatusCallBack.onStatusOk(activity);
        } catch (Exception e) {
            if (PermissionDebug.isDebug()) {
                PermissionTools.toast(getContext(), e.toString());
                Log.e(TAG, e.toString());
            }
        }
    }

    private void requestPermissions(final Permissions permissions, final CheckRequestPermissionsListener checkRequestPermissionsListener) {
        checkStatusBeforeDoSomething(new CheckStatusCallBack() { // from class: com.qw.soul.permission.SoulPermission.4
            @Override // com.p284qw.soul.permission.callbcak.CheckStatusCallBack
            public void onStatusOk(Activity activity) {
                SoulPermission.this.requestRuntimePermission(activity, permissions.getPermissions(), checkRequestPermissionsListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestRuntimePermission(Activity activity, final Permission[] permissionArr, final CheckRequestPermissionsListener checkRequestPermissionsListener) {
        String str = TAG;
        PermissionDebug.m8226d(str, "start to request permissions size= " + permissionArr.length);
        new PermissionRequester(activity).withPermission(permissionArr).request(new RequestPermissionListener() { // from class: com.qw.soul.permission.SoulPermission.5
            @Override // com.p284qw.soul.permission.callbcak.RequestPermissionListener
            public void onPermissionResult(Permission[] permissionArr2) {
                LinkedList linkedList = new LinkedList();
                for (Permission permission : permissionArr2) {
                    if (!permission.isGranted()) {
                        linkedList.add(permission);
                    }
                }
                if (linkedList.size() == 0) {
                    PermissionDebug.m8226d(SoulPermission.TAG, "all permission are request ok");
                    checkRequestPermissionsListener.onAllPermissionOk(permissionArr);
                    return;
                }
                PermissionDebug.m8226d(SoulPermission.TAG, "some permission are refused size=" + linkedList.size());
                checkRequestPermissionsListener.onPermissionDenied(PermissionTools.convert(linkedList));
            }
        });
    }

    private void requestSpecialPermission(final Special special, final SpecialPermissionListener specialPermissionListener) {
        checkStatusBeforeDoSomething(new CheckStatusCallBack() { // from class: com.qw.soul.permission.SoulPermission.6
            @Override // com.p284qw.soul.permission.callbcak.CheckStatusCallBack
            public void onStatusOk(Activity activity) {
                new PermissionRequester(activity).withPermission(special).request(specialPermissionListener);
            }
        });
    }
}
