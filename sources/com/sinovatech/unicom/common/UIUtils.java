package com.sinovatech.unicom.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.sinovatech.unicom.basic.p315ui.home.util.ScreenUtil;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.toast.CustomToast;
import java.util.List;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UIUtils {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum ScreenHeightMode {
        OnlyContent,
        ContentAndStatusbar,
        FullScreen
    }

    public static boolean isFastClick() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime >= 1000;
        lastClickTime = currentTimeMillis;
        return z;
    }

    public static float getPixelRatio(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int dip2px(Activity activity, float f) {
        return (int) ((f * activity.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int dip2px2(Activity activity, float f) {
        float f2 = CustomDensityHandler.sNonCompatDensity;
        if (f2 == 0.0f) {
            f2 = activity.getResources().getDisplayMetrics().density;
        }
        return (int) ((f * f2) + 0.5f);
    }

    public static int dip2px(float f) {
        return (int) TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    public static int px2dp(Activity activity, float f) {
        return (int) ((f / activity.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dp(float f) {
        return (int) ((f / App.getInstance().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dp2(float f) {
        float f2 = CustomDensityHandler.sNonCompatDensity;
        if (f2 == 0.0f) {
            f2 = App.getInstance().getResources().getDisplayMetrics().density;
        }
        return (int) ((f / f2) + 0.5f);
    }

    public static int sp2px(float f) {
        return (int) TypedValue.applyDimension(2, f, Resources.getSystem().getDisplayMetrics());
    }

    public static int getScreenWidth(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }

    public static int getScreenWidth(Activity activity) {
        return activity.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @TargetApi(17)
    public static boolean checkDeviceHasNavigationBar(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "force_fsg_nav_bar", 0) == 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0076, code lost:
        if (r6 == com.sinovatech.unicom.common.UIUtils.ScreenHeightMode.FullScreen) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getScreenHeight(android.app.Activity r5, com.sinovatech.unicom.common.UIUtils.ScreenHeightMode r6) {
        /*
            int r0 = getStatusBarHeight(r5)
            int r1 = getNavigationBarHeight(r5)
            int r2 = getScreenHeightCanUse(r5)
            int r3 = getFullScreenHeight(r5)
            com.sinovatech.unicom.common.UIUtils$ScreenHeightMode r4 = com.sinovatech.unicom.common.UIUtils.ScreenHeightMode.OnlyContent
            if (r6 != r4) goto L46
            if (r3 != r2) goto L1a
            int r2 = r3 - r0
            goto L7b
        L1a:
            int r6 = r2 + r0
            if (r3 != r6) goto L20
            goto L7b
        L20:
            int r1 = r1 + r2
            if (r3 != r1) goto L42
            boolean r6 = com.sinovatech.unicom.common.BrandUtil.isXiaoMi()
            if (r6 == 0) goto L31
            boolean r6 = checkDeviceHasNavigationBar(r5)
            if (r6 == 0) goto L31
            int r2 = r2 - r0
            goto L7b
        L31:
            boolean r6 = com.sinovatech.unicom.common.BrandUtil.isXiaoMi()
            if (r6 == 0) goto L40
            boolean r5 = checkDeviceHasNavigationBar(r5)
            if (r5 != 0) goto L40
            int r2 = r3 - r0
            goto L7b
        L40:
            int r2 = r2 - r0
            goto L7b
        L42:
            int r1 = r1 + r0
            if (r3 != r1) goto L7a
            goto L7b
        L46:
            com.sinovatech.unicom.common.UIUtils$ScreenHeightMode r4 = com.sinovatech.unicom.common.UIUtils.ScreenHeightMode.ContentAndStatusbar
            if (r6 != r4) goto L74
            if (r3 != r2) goto L4d
            goto L78
        L4d:
            int r6 = r2 + r0
            if (r3 != r6) goto L52
            goto L78
        L52:
            int r1 = r1 + r2
            if (r3 != r1) goto L6f
            boolean r6 = com.sinovatech.unicom.common.BrandUtil.isXiaoMi()
            if (r6 == 0) goto L62
            boolean r6 = checkDeviceHasNavigationBar(r5)
            if (r6 == 0) goto L62
            goto L7b
        L62:
            boolean r6 = com.sinovatech.unicom.common.BrandUtil.isXiaoMi()
            if (r6 == 0) goto L7b
            boolean r5 = checkDeviceHasNavigationBar(r5)
            if (r5 != 0) goto L7b
            goto L78
        L6f:
            int r1 = r1 + r0
            if (r3 != r1) goto L7a
            r2 = r6
            goto L7b
        L74:
            com.sinovatech.unicom.common.UIUtils$ScreenHeightMode r5 = com.sinovatech.unicom.common.UIUtils.ScreenHeightMode.FullScreen
            if (r6 != r5) goto L7a
        L78:
            r2 = r3
            goto L7b
        L7a:
            r2 = 0
        L7b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.common.UIUtils.getScreenHeight(android.app.Activity, com.sinovatech.unicom.common.UIUtils$ScreenHeightMode):int");
    }

    @TargetApi(17)
    public static int getScreenRealHeight(@Nullable Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        return point.y;
    }

    public static int getScreenHeightCanUse(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getFullScreenHeight(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics);
        } else {
            defaultDisplay.getMetrics(displayMetrics);
        }
        return displayMetrics.heightPixels;
    }

    public static boolean isFullScreenDevice(Context context) {
        WindowManager windowManager;
        float f;
        float f2;
        try {
            if (Build.VERSION.SDK_INT < 21 || (windowManager = (WindowManager) context.getSystemService("window")) == null) {
                return false;
            }
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (point.x < point.y) {
                f = point.x;
                f2 = point.y;
            } else {
                f = point.y;
                f2 = point.x;
            }
            return f2 / f >= 1.97f;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getNavigationBarHeight(Activity activity) {
        try {
            int identifier = activity.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            if (identifier > 0) {
                return activity.getResources().getDimensionPixelSize(identifier);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        try {
            int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                return activity.getResources().getDimensionPixelSize(identifier);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getNotchHeight(Activity activity) {
        int identifier = activity.getResources().getIdentifier("notch_height", "dimen", "android");
        if (identifier > 0) {
            return activity.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static void setStatusBarMode(Activity activity, boolean z, boolean z2) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                if (z && Build.VERSION.SDK_INT >= 23) {
                    Window window = activity.getWindow();
                    window.clearFlags(67108864);
                    window.getDecorView().setSystemUiVisibility(9472);
                    window.addFlags(Integer.MIN_VALUE);
                    window.setStatusBarColor(0);
                    return;
                }
                Window window2 = activity.getWindow();
                window2.clearFlags(67108864);
                window2.getDecorView().setSystemUiVisibility(1280);
                window2.addFlags(Integer.MIN_VALUE);
                if (z2) {
                    window2.setStatusBarColor(0);
                } else {
                    window2.setStatusBarColor(-16777216);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setStatusWhite(Activity activity) {
        setStatusBarMode(activity, false, true);
    }

    public static void setStatusBlack(Activity activity) {
        setStatusBarMode(activity, true, true);
    }

    public static void setFullScreenAndHideStateBar(Activity activity) {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                activity.getWindow().addFlags(67108864);
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                attributes.layoutInDisplayCutoutMode = 1;
                activity.getWindow().setAttributes(attributes);
                activity.getWindow().setFlags(1024, 1024);
            } else {
                setStatusBlack(activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setNavBarVisibility(@NonNull Activity activity, boolean z) {
        try {
            if (Build.VERSION.SDK_INT < 19) {
                return;
            }
            setNavBarVisibility(activity.getWindow(), z);
        } catch (Exception unused) {
            MsLogUtil.m7978e("setNavBarVisibility()");
        }
    }

    public static void setNavBarVisibility(@NonNull Window window, boolean z) {
        try {
            if (Build.VERSION.SDK_INT < 19) {
                return;
            }
            ViewGroup viewGroup = (ViewGroup) window.getDecorView();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                int id = childAt.getId();
                if (id != -1 && "navigationBarBackground".equals(getResNameById(id))) {
                    childAt.setVisibility(z ? 0 : 4);
                }
            }
            if (z) {
                viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() & (-4611));
            } else {
                viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() | 4610);
            }
        } catch (Exception unused) {
            MsLogUtil.m7978e("setNavBarVisibility()");
        }
    }

    private static String getResNameById(int i) {
        try {
            return App.getInstance().getResources().getResourceEntryName(i);
        } catch (Exception unused) {
            return "";
        }
    }

    public static void setStatusBarColor(Activity activity) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                Window window = activity.getWindow();
                window.addFlags(Integer.MIN_VALUE);
                window.clearFlags(67108864);
                window.setStatusBarColor(-1);
                window.getDecorView().setSystemUiVisibility(8192);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setStatusBarColor(Activity activity, int i) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                activity.getWindow().setStatusBarColor(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toast(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (Build.VERSION.SDK_INT == 25) {
                    MsLogUtil.m7979d("customToast", "android 7.1.1");
                    CustomToast.make().showToast(App.getInstance(), str, 0);
                } else {
                    MsLogUtil.m7979d("customToast", "android other");
                    Toast.makeText(App.getInstance(), str, 0).show();
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("UIUtils", "toast : " + e.getMessage());
        }
    }

    public static void toastLong(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (Build.VERSION.SDK_INT == 25) {
                    CustomToast.make().showToast(App.getInstance(), str, 1);
                } else {
                    Toast.makeText(App.getInstance(), str, 1).show();
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("UIUtils", "toastLong : " + e.getMessage());
        }
    }

    public static void toastCenter(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (Build.VERSION.SDK_INT == 25) {
                    CustomToast.make().showToastCenter(App.getInstance(), str, 0);
                } else {
                    Toast makeText = Toast.makeText(App.getInstance(), str, 0);
                    makeText.setGravity(17, 0, 0);
                    makeText.show();
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("UIUtils", "toastCenter : " + e.getMessage());
        }
    }

    public static void toastCenterLong(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (Build.VERSION.SDK_INT == 25) {
                    CustomToast.make().showToastCenter(App.getInstance(), str, 1);
                } else {
                    Toast makeText = Toast.makeText(App.getInstance(), str, 1);
                    makeText.setGravity(17, 0, 0);
                    makeText.show();
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("UIUtils", "toastCenterLong : " + e.getMessage());
        }
    }

    public static void showCenterOnlyTextToast(Context context, String str, int i) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(2131493544, (ViewGroup) null);
            ((TextView) linearLayout.findViewById(2131298807)).setText(str);
            if (Build.VERSION.SDK_INT == 25) {
                CustomToast.make().showToastView(context, linearLayout, i);
            } else {
                Toast toast = new Toast(context);
                toast.setGravity(17, 0, 0);
                toast.setDuration(i);
                toast.setView(linearLayout);
                toast.show();
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("UIUtils", "showCenterOnlyTextToast : " + e.getMessage());
        }
    }

    public static void showCenterOnlyTextFuWuToast(Context context, String str, int i, int i2) {
        LinearLayout linearLayout;
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (str.equals("保存成功")) {
                linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(2131493543, (ViewGroup) null);
            } else {
                linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(2131493542, (ViewGroup) null);
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(context.getResources().getColor(2131099698));
            gradientDrawable.setCornerRadius(context.getResources().getDimensionPixelSize(2131165300));
            gradientDrawable.setAlpha(i2);
            ((LinearLayout) linearLayout.findViewById(2131297605)).setBackground(gradientDrawable);
            ((TextView) linearLayout.findViewById(2131298807)).setText(str);
            if (Build.VERSION.SDK_INT == 25) {
                CustomToast.make().showToastView(context, linearLayout, i);
                return;
            }
            Toast toast = new Toast(context);
            toast.setGravity(17, 0, 0);
            toast.setDuration(i);
            toast.setView(linearLayout);
            toast.show();
        } catch (Exception e) {
            MsLogUtil.m7977e("UIUtils", "showCenterOnlyTextFuWuToast : " + e.getMessage());
        }
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
            return "";
        }
    }

    public static void logD(String str) {
        if (URLSet.Debug_mode) {
            Log.d("Unicom", str);
        }
    }

    public static void logDJ(String str) {
        if (URLSet.Debug_mode) {
            LogUtils.json("Unicom_json", str);
        }
    }

    public static void logD(String str, String str2) {
        if (URLSet.Debug_mode) {
            Log.d(str, str2);
        }
    }

    public static void logDJ(String str, String str2) {
        if (URLSet.Debug_mode) {
            LogUtils.json(str, str2);
        }
    }

    public static void logE(String str) {
        if (URLSet.Debug_mode) {
            Log.e(SoulPermission.getInstance().getTopActivity().getLocalClassName(), str);
        }
    }

    public static void logE(String str, String str2) {
        if (URLSet.Debug_mode) {
            Log.e(str, str2);
        }
    }

    public static void setWindowsBg(Activity activity, float f) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.alpha = f;
        activity.getWindow().setAttributes(attributes);
    }

    public static boolean isFoldScreen(Activity activity) {
        try {
            if ((activity.getResources().getConfiguration().screenLayout & 15) == 3) {
                if (("HUAWEI".equalsIgnoreCase(Build.MANUFACTURER) || "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER) || "HONOR".equalsIgnoreCase(Build.MANUFACTURER) || "samsung".equalsIgnoreCase(Build.MANUFACTURER) || "OPPO".equalsIgnoreCase(Build.MANUFACTURER)) && Build.VERSION.SDK_INT >= 24) {
                    return !activity.isInMultiWindowMode();
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkPermissions(String... strArr) {
        try {
            for (Permission permission : SoulPermission.getInstance().checkPermissions(strArr)) {
                if (!permission.isGranted()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            MsLogUtil.m7979d("UIUtils", e.getMessage());
            return true;
        }
    }

    public static Boolean checkIsVisible(Context context, View view) {
        if (view != null) {
            try {
                Rect rect = new Rect(0, 0, ScreenUtil.getScreenMetrics(context).x, ScreenUtil.getScreenMetrics(context).y);
                int[] iArr = new int[2];
                view.getLocationInWindow(iArr);
                if (view.getLocalVisibleRect(rect) && iArr[1] > 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }
        return false;
    }

    public static Boolean checkIsVisibles(Context context, View view) {
        if (view != null) {
            try {
                Rect rect = new Rect(0, 0, ScreenUtil.getScreenMetrics(context).x, ScreenUtil.getScreenMetrics(context).y);
                view.getLocationInWindow(new int[2]);
                if (view.getLocalVisibleRect(rect)) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }
        return false;
    }

    public static boolean isVisibleLocal(View view) {
        Rect rect = new Rect();
        view.getLocalVisibleRect(rect);
        return rect.top == 0;
    }

    public static int[] getViewLocationInWindow(View view) {
        int[] iArr = new int[2];
        if (view != null) {
            try {
                view.getLocationInWindow(iArr);
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }
        return iArr;
    }

    public static Bitmap fastblur(Context context, Bitmap bitmap, float f, float f2) {
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, Math.round(bitmap.getWidth() * f), Math.round(bitmap.getHeight() * f), false);
        Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap);
        RenderScript create = RenderScript.create(context);
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        Allocation createFromBitmap = Allocation.createFromBitmap(create, createScaledBitmap);
        Allocation createFromBitmap2 = Allocation.createFromBitmap(create, createBitmap);
        create2.setRadius(f2);
        create2.setInput(createFromBitmap);
        create2.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(createBitmap);
        return createBitmap;
    }

    public static void relaunchApp() {
        Intent launchAppIntent = getLaunchAppIntent(App.getInstance().getPackageName());
        if (launchAppIntent == null) {
            MsLogUtil.m7977e("AppUtils", "Didn't exist launcher activity.");
            return;
        }
        launchAppIntent.addFlags(335577088);
        App.getInstance().startActivity(launchAppIntent);
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public static Intent getLaunchAppIntent(String str) {
        String launcherActivity = getLauncherActivity(str);
        if (TextUtils.isEmpty(launcherActivity)) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setClassName(str, launcherActivity);
        return intent.addFlags(268435456);
    }

    public static String getLauncherActivity(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(str);
        List<ResolveInfo> queryIntentActivities = App.getInstance().getPackageManager().queryIntentActivities(intent, 0);
        return (queryIntentActivities == null || queryIntentActivities.size() == 0) ? "" : queryIntentActivities.get(0).activityInfo.name;
    }

    public static boolean isShowDialog(Activity activity, Dialog dialog) {
        return (activity == null || activity.isDestroyed() || activity.isFinishing() || dialog == null || dialog.isShowing()) ? false : true;
    }

    public static boolean showDialog(Activity activity, Dialog dialog) {
        if (activity == null || activity.isDestroyed() || activity.isFinishing() || dialog == null || dialog.isShowing()) {
            return false;
        }
        dialog.show();
        return true;
    }

    public static boolean isNumeric(String str) {
        return (str == null || str.isEmpty() || !Pattern.compile("[0-9]*").matcher(str).matches()) ? false : true;
    }

    public static boolean isDismissDialog(Activity activity, Dialog dialog) {
        return (activity == null || activity.isDestroyed() || activity.isFinishing() || dialog == null || !dialog.isShowing()) ? false : true;
    }

    public static boolean dismissDialog(Activity activity, Dialog dialog) {
        if (activity == null || activity.isDestroyed() || activity.isFinishing() || dialog == null || !dialog.isShowing()) {
            return false;
        }
        dialog.dismiss();
        return true;
    }

    public static void setGJR(View... viewArr) {
        setGJR(true, viewArr);
    }

    public static void setGJR(boolean z, View... viewArr) {
        if (viewArr != null) {
            try {
                if (isGongJiRi()) {
                    Paint paint = new Paint();
                    ColorMatrix colorMatrix = new ColorMatrix();
                    colorMatrix.setSaturation(z ? 0.0f : 1.0f);
                    paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
                    for (View view : viewArr) {
                        if (view != null) {
                            view.setLayerType(2, paint);
                        }
                    }
                }
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }
    }

    public static boolean isGongJiRi() {
        String string = App.getSharePreferenceUtil().getString(ConfigManager.config_gongjiri_start_time);
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        String string2 = App.getSharePreferenceUtil().getString(ConfigManager.config_gongjiri_end_time);
        if (TextUtils.isEmpty(string2)) {
            return false;
        }
        long string2Millis = TimeUtils.string2Millis(string);
        long string2Millis2 = TimeUtils.string2Millis(string2);
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis >= string2Millis && currentTimeMillis <= string2Millis2;
    }

    public static void setTextViewColor(TextView textView, String str, String str2, int i, int i2) {
        try {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(str)), i, i2, 33);
            textView.setText(spannableStringBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearUnicomClipText(Context context) {
        try {
            final ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.common.UIUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ClipData primaryClip = clipboardManager.getPrimaryClip();
                        if (primaryClip == null || primaryClip.getItemCount() <= 0) {
                            return;
                        }
                        String charSequence = primaryClip.getItemAt(0).getText().toString();
                        MsLogUtil.m7979d("wokouling", "clearUnicomClipText: " + charSequence);
                        if (TextUtils.isEmpty(charSequence)) {
                            return;
                        }
                        if (charSequence.contains("CNCC_NEW") || charSequence.contains("8.0 chinaunicom:/!")) {
                            if (Build.VERSION.SDK_INT >= 28) {
                                clipboardManager.clearPrimaryClip();
                            } else {
                                clipboardManager.setText(null);
                            }
                            MsLogUtil.m7979d("wokouling", "clearUnicomClipText: 清理沃口令");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 200L);
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static boolean isViVOFoldableDevice() {
        try {
            Class<?> cls = Class.forName("android.util.FtDeviceInfo");
            if (cls == null) {
                return false;
            }
            Object invoke = cls.getMethod("getDeviceType", new Class[0]).invoke(cls, new Object[0]);
            Log.d("fold", "getDeviceType=" + invoke);
            return "foldable".equals(invoke);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isXiaoMiFoldableDevice() {
        try {
            return getInvoke("persist.sys.muiltdisplay_type", "0").equals("2");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getInvoke(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static boolean isBigSystemFontSize(Context context) {
        try {
            float f = context.getResources().getConfiguration().fontScale;
            MsLogUtil.m7979d("isSystemDefaultFontSize", f + "系统字体");
            return f > 1.2f;
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
            return false;
        }
    }
}
