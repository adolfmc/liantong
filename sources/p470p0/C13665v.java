package p470p0;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.unicom.pay.C10531R;
import java.lang.reflect.Field;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: p0.v */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13665v {

    /* renamed from: a */
    public static final int f27510a = C10531R.C10534id.statusbarutil_fake_status_bar_view;

    /* renamed from: b */
    public static final int f27511b = C10531R.C10534id.statusbarutil_translucent_view;

    /* renamed from: a */
    public static void m162a(Activity activity, @ColorInt int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().clearFlags(67108864);
            activity.getWindow().setStatusBarColor(i);
        } else if (i2 >= 19) {
            activity.getWindow().addFlags(67108864);
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            int i3 = f27510a;
            View findViewById = viewGroup.findViewById(i3);
            if (findViewById != null) {
                if (findViewById.getVisibility() == 8) {
                    findViewById.setVisibility(0);
                }
                findViewById.setBackgroundColor(i);
            } else {
                View view = new View(activity);
                view.setLayoutParams(new LinearLayout.LayoutParams(-1, activity.getResources().getDimensionPixelSize(activity.getResources().getIdentifier("status_bar_height", "dimen", "android"))));
                view.setBackgroundColor(i);
                view.setId(i3);
                viewGroup.addView(view);
            }
            m161b(activity);
        }
    }

    /* renamed from: b */
    public static void m161b(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                childAt.setFitsSystemWindows(true);
                ((ViewGroup) childAt).setClipToPadding(true);
            }
        }
    }

    /* renamed from: c */
    public static void m160c(Activity activity) {
        int i = Build.VERSION.SDK_INT;
        if (i < 19) {
            return;
        }
        if (i >= 19) {
            if (i >= 21) {
                activity.getWindow().addFlags(Integer.MIN_VALUE);
                activity.getWindow().clearFlags(67108864);
                activity.getWindow().addFlags(134217728);
                activity.getWindow().setStatusBarColor(0);
            } else {
                activity.getWindow().addFlags(67108864);
            }
            m161b(activity);
        }
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        int i2 = f27511b;
        View findViewById = viewGroup.findViewById(i2);
        if (findViewById != null) {
            if (findViewById.getVisibility() == 8) {
                findViewById.setVisibility(0);
            }
            findViewById.setBackgroundColor(Color.argb(0, 0, 0, 0));
            return;
        }
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, activity.getResources().getDimensionPixelSize(activity.getResources().getIdentifier("status_bar_height", "dimen", "android"))));
        view.setBackgroundColor(Color.argb(0, 0, 0, 0));
        view.setId(i2);
        viewGroup.addView(view);
    }

    @TargetApi(23)
    /* renamed from: a */
    public static void m163a(Activity activity) {
        Class<?> cls = activity.getWindow().getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Class<?> cls3 = Integer.TYPE;
            cls.getMethod("setExtraFlags", cls3, cls3).invoke(activity.getWindow(), Integer.valueOf(i), Integer.valueOf(i));
        } catch (Exception unused) {
        }
        try {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            declaredField2.setInt(attributes, declaredField.getInt(null) | declaredField2.getInt(attributes));
            activity.getWindow().setAttributes(attributes);
        } catch (Exception unused2) {
        }
        if (Build.VERSION.SDK_INT >= 23) {
            activity.getWindow().getDecorView().setSystemUiVisibility(9216);
        }
    }
}
