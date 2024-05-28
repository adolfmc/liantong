package pub.devrel.easypermissions;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.p083v4.app.ActivityCompat;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.content.ContextCompat;
import android.support.p086v7.app.AlertDialog;
import android.util.Log;
import com.bytedance.applog.tracker.Tracker;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class EasyPermissions {
    private static final String DIALOG_TAG = "RationaleDialogFragmentCompat";
    private static final String TAG = "EasyPermissions";

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {
        void onPermissionsDenied(int i, List<String> list);

        void onPermissionsGranted(int i, List<String> list);
    }

    public static boolean hasPermissions(@NonNull Context context, @NonNull String... strArr) {
        if (Build.VERSION.SDK_INT < 23) {
            Log.w("EasyPermissions", "hasPermissions: API version < M, returning true by default");
            return true;
        }
        for (String str : strArr) {
            if (!(ContextCompat.checkSelfPermission(context, str) == 0)) {
                return false;
            }
        }
        return true;
    }

    public static void requestPermissions(@NonNull Object obj, int i, @NonNull String... strArr) {
        executePermissionsRequest(obj, strArr, i);
    }

    public static void requestPermissions(@NonNull Object obj, @NonNull String str, int i, @NonNull String... strArr) {
        requestPermissions(obj, str, 17039370, 17039360, i, strArr);
    }

    @SuppressLint({"NewApi"})
    public static void requestPermissions(@NonNull Object obj, @NonNull String str, @StringRes int i, @StringRes int i2, int i3, @NonNull String... strArr) {
        checkCallingObjectSuitability(obj);
        boolean z = false;
        for (String str2 : strArr) {
            z = z || shouldShowRequestPermissionRationale(obj, str2);
        }
        if (z) {
            if (getSupportFragmentManager(obj) != null) {
                showRationaleDialogFragmentCompat(getSupportFragmentManager(obj), str, i, i2, i3, strArr);
                return;
            } else if (getFragmentManager(obj) != null) {
                showRationaleDialogFragment(getFragmentManager(obj), str, i, i2, i3, strArr);
                return;
            } else {
                showRationaleAlertDialog(obj, str, i, i2, i3, strArr);
                return;
            }
        }
        executePermissionsRequest(obj, strArr, i3);
    }

    @RequiresApi(11)
    private static void showRationaleDialogFragmentCompat(@NonNull FragmentManager fragmentManager, @NonNull String str, @StringRes int i, @StringRes int i2, int i3, @NonNull String... strArr) {
        RationaleDialogFragmentCompat.newInstance(i, i2, str, i3, strArr).show(fragmentManager, "RationaleDialogFragmentCompat");
    }

    @RequiresApi(api = 11)
    private static void showRationaleDialogFragment(@NonNull android.app.FragmentManager fragmentManager, @NonNull String str, @StringRes int i, @StringRes int i2, int i3, @NonNull String... strArr) {
        RationaleDialogFragment.newInstance(i, i2, str, i3, strArr).show(fragmentManager, "RationaleDialogFragmentCompat");
    }

    private static void showRationaleAlertDialog(@NonNull final Object obj, @NonNull String str, @StringRes int i, @StringRes int i2, final int i3, @NonNull final String... strArr) {
        Activity activity = getActivity(obj);
        if (activity == null) {
            throw new IllegalStateException("Can't show rationale dialog for null Activity");
        }
        new AlertDialog.Builder(activity).setCancelable(false).setMessage(str).setPositiveButton(i, new DialogInterface.OnClickListener() { // from class: pub.devrel.easypermissions.EasyPermissions.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i4) {
                Tracker.onClick(dialogInterface, i4);
                EasyPermissions.executePermissionsRequest(obj, strArr, i3);
            }
        }).setNegativeButton(i2, new DialogInterface.OnClickListener() { // from class: pub.devrel.easypermissions.EasyPermissions.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i4) {
                Tracker.onClick(dialogInterface, i4);
                Object obj2 = obj;
                if (obj2 instanceof PermissionCallbacks) {
                    ((PermissionCallbacks) obj2).onPermissionsDenied(i3, Arrays.asList(strArr));
                }
            }
        }).create().show();
    }

    public static boolean somePermissionPermanentlyDenied(@NonNull Object obj, @NonNull List<String> list) {
        for (String str : list) {
            if (permissionPermanentlyDenied(obj, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean permissionPermanentlyDenied(@NonNull Object obj, @NonNull String str) {
        return !shouldShowRequestPermissionRationale(obj, str);
    }

    public static void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr, @NonNull Object... objArr) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str = strArr[i2];
            if (iArr[i2] == 0) {
                arrayList.add(str);
            } else {
                arrayList2.add(str);
            }
        }
        for (Object obj : objArr) {
            if (!arrayList.isEmpty() && (obj instanceof PermissionCallbacks)) {
                ((PermissionCallbacks) obj).onPermissionsGranted(i, arrayList);
            }
            if (!arrayList2.isEmpty() && (obj instanceof PermissionCallbacks)) {
                ((PermissionCallbacks) obj).onPermissionsDenied(i, arrayList2);
            }
            if (!arrayList.isEmpty() && arrayList2.isEmpty()) {
                runAnnotatedMethods(obj, i);
            }
        }
    }

    @TargetApi(23)
    private static boolean shouldShowRequestPermissionRationale(@NonNull Object obj, @NonNull String str) {
        if (obj instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) obj, str);
        }
        if (obj instanceof Fragment) {
            return ((Fragment) obj).shouldShowRequestPermissionRationale(str);
        }
        if (obj instanceof android.app.Fragment) {
            return ((android.app.Fragment) obj).shouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(23)
    public static void executePermissionsRequest(@NonNull Object obj, @NonNull String[] strArr, int i) {
        checkCallingObjectSuitability(obj);
        if (obj instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) obj, strArr, i);
        } else if (obj instanceof Fragment) {
            ((Fragment) obj).requestPermissions(strArr, i);
        } else if (obj instanceof android.app.Fragment) {
            ((android.app.Fragment) obj).requestPermissions(strArr, i);
        }
    }

    @TargetApi(11)
    private static Activity getActivity(@NonNull Object obj) {
        if (obj instanceof Activity) {
            return (Activity) obj;
        }
        if (obj instanceof Fragment) {
            return ((Fragment) obj).getActivity();
        }
        if (obj instanceof android.app.Fragment) {
            return ((android.app.Fragment) obj).getActivity();
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    @Nullable
    private static FragmentManager getSupportFragmentManager(@NonNull Object obj) {
        if (obj instanceof FragmentActivity) {
            return ((FragmentActivity) obj).getSupportFragmentManager();
        }
        if (obj instanceof Fragment) {
            return ((Fragment) obj).getChildFragmentManager();
        }
        return null;
    }

    @Nullable
    private static android.app.FragmentManager getFragmentManager(@NonNull Object obj) {
        if (obj instanceof Activity) {
            if (Build.VERSION.SDK_INT >= 11) {
                return ((Activity) obj).getFragmentManager();
            }
            return null;
        } else if (obj instanceof android.app.Fragment) {
            if (Build.VERSION.SDK_INT >= 17) {
                return ((android.app.Fragment) obj).getChildFragmentManager();
            }
            return ((android.app.Fragment) obj).getFragmentManager();
        } else {
            return null;
        }
    }

    private static void runAnnotatedMethods(@NonNull Object obj, int i) {
        Method[] declaredMethods;
        Class<?> cls = obj.getClass();
        if (isUsingAndroidAnnotations(obj)) {
            cls = cls.getSuperclass();
        }
        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(AfterPermissionGranted.class) && ((AfterPermissionGranted) method.getAnnotation(AfterPermissionGranted.class)).value() == i) {
                if (method.getParameterTypes().length > 0) {
                    throw new RuntimeException("Cannot execute method " + method.getName() + " because it is non-void method and/or has input parameters.");
                }
                try {
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    method.invoke(obj, new Object[0]);
                } catch (IllegalAccessException e) {
                    Log.e("EasyPermissions", "runDefaultMethod:IllegalAccessException", e);
                } catch (InvocationTargetException e2) {
                    Log.e("EasyPermissions", "runDefaultMethod:InvocationTargetException", e2);
                }
            }
        }
    }

    private static void checkCallingObjectSuitability(@Nullable Object obj) {
        if (obj == null) {
            throw new NullPointerException("Activity or Fragment should not be null");
        }
        boolean z = obj instanceof Activity;
        boolean z2 = obj instanceof Fragment;
        boolean z3 = obj instanceof android.app.Fragment;
        boolean z4 = Build.VERSION.SDK_INT >= 23;
        if (z2 || z) {
            return;
        }
        if (z3 && z4) {
            return;
        }
        if (z3) {
            throw new IllegalArgumentException("Target SDK needs to be greater than 23 if caller is android.app.Fragment");
        }
        throw new IllegalArgumentException("Caller must be an Activity or a Fragment.");
    }

    private static boolean isUsingAndroidAnnotations(@NonNull Object obj) {
        if (obj.getClass().getSimpleName().endsWith("_")) {
            try {
                return Class.forName("org.androidannotations.api.view.HasViews").isInstance(obj);
            } catch (ClassNotFoundException unused) {
                return false;
            }
        }
        return false;
    }
}
