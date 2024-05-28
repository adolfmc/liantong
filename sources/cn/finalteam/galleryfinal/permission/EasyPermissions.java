package cn.finalteam.galleryfinal.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.p083v4.app.ActivityCompat;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.content.ContextCompat;
import cn.finalteam.galleryfinal.utils.ILogger;
import com.bytedance.applog.tracker.Tracker;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EasyPermissions {
    private static final String TAG = "EasyPermissions";

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {
        void onPermissionsDenied(List<String> list);

        void onPermissionsGranted(List<String> list);
    }

    public static boolean hasPermissions(Context context, String... strArr) {
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                return true;
            }
            if (!(ContextCompat.checkSelfPermission(context, strArr[i]) == 0)) {
                return false;
            }
            i++;
        }
    }

    public static void requestPermissions(Object obj, String str, int i, String... strArr) {
        requestPermissions(obj, str, 17039370, 17039360, i, strArr);
    }

    public static void requestPermissions(final Object obj, String str, @StringRes int i, @StringRes int i2, final int i3, final String... strArr) {
        checkCallingObjectSuitability(obj);
        boolean z = false;
        for (String str2 : strArr) {
            z = z || shouldShowRequestPermissionRationale(obj, str2);
        }
        if (z) {
            new AlertDialog.Builder(getActivity(obj)).setMessage(str).setPositiveButton(i, new DialogInterface.OnClickListener() { // from class: cn.finalteam.galleryfinal.permission.EasyPermissions.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i4) {
                    Tracker.onClick(dialogInterface, i4);
                    EasyPermissions.executePermissionsRequest(obj, strArr, i3);
                }
            }).setNegativeButton(i2, new DialogInterface.OnClickListener() { // from class: cn.finalteam.galleryfinal.permission.EasyPermissions.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i4) {
                    Tracker.onClick(dialogInterface, i4);
                    PermissionCallbacks permissionCallbacks = (PermissionCallbacks) obj;
                    if (permissionCallbacks != null) {
                        permissionCallbacks.onPermissionsDenied(new ArrayList());
                    }
                }
            }).create().show();
        } else {
            executePermissionsRequest(obj, strArr, i3);
        }
    }

    public static void onRequestPermissionsResult(int i, String[] strArr, int[] iArr, Object obj) {
        checkCallingObjectSuitability(obj);
        PermissionCallbacks permissionCallbacks = (PermissionCallbacks) obj;
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
        if (!arrayList.isEmpty()) {
            permissionCallbacks.onPermissionsGranted(arrayList);
        }
        if (!arrayList2.isEmpty()) {
            permissionCallbacks.onPermissionsDenied(arrayList2);
        }
        if (arrayList.isEmpty() || !arrayList2.isEmpty()) {
            return;
        }
        runAnnotatedMethods(obj, i);
    }

    private static boolean shouldShowRequestPermissionRationale(Object obj, String str) {
        if (obj instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) obj, str);
        }
        if (obj instanceof Fragment) {
            return ((Fragment) obj).shouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void executePermissionsRequest(Object obj, String[] strArr, int i) {
        checkCallingObjectSuitability(obj);
        if (obj instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) obj, strArr, i);
        } else if (obj instanceof Fragment) {
            ((Fragment) obj).requestPermissions(strArr, i);
        }
    }

    private static Activity getActivity(Object obj) {
        if (obj instanceof Activity) {
            return (Activity) obj;
        }
        if (obj instanceof Fragment) {
            return ((Fragment) obj).getActivity();
        }
        return null;
    }

    private static void runAnnotatedMethods(Object obj, int i) {
        Method[] declaredMethods;
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(AfterPermissionGranted.class) && ((AfterPermissionGranted) method.getAnnotation(AfterPermissionGranted.class)).value() == i) {
                if (method.getParameterTypes().length > 0) {
                    throw new RuntimeException("Cannot execute non-void method " + method.getName());
                }
                try {
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    method.invoke(obj, new Object[0]);
                } catch (IllegalAccessException e) {
                    ILogger.m22038e("EasyPermissions", "runDefaultMethod:IllegalAccessException", e);
                } catch (InvocationTargetException e2) {
                    ILogger.m22038e("EasyPermissions", "runDefaultMethod:InvocationTargetException", e2);
                }
            }
        }
    }

    private static void checkCallingObjectSuitability(Object obj) {
        if (!(obj instanceof Fragment) && !(obj instanceof Activity)) {
            throw new IllegalArgumentException("Caller must be an Activity or a Fragment.");
        }
        if (!(obj instanceof PermissionCallbacks)) {
            throw new IllegalArgumentException("Caller must implement PermissionCallbacks.");
        }
    }
}
