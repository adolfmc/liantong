package com.blankj.utilcode.util;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class FragmentUtils {
    private static final String ARGS_ID = "args_id";
    private static final String ARGS_IS_ADD_STACK = "args_is_add_stack";
    private static final String ARGS_IS_HIDE = "args_is_hide";
    private static final String ARGS_TAG = "args_tag";
    private static final int TYPE_ADD_FRAGMENT = 1;
    private static final int TYPE_HIDE_FRAGMENT = 4;
    private static final int TYPE_REMOVE_FRAGMENT = 32;
    private static final int TYPE_REMOVE_TO_FRAGMENT = 64;
    private static final int TYPE_REPLACE_FRAGMENT = 16;
    private static final int TYPE_SHOW_FRAGMENT = 2;
    private static final int TYPE_SHOW_HIDE_FRAGMENT = 8;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnBackClickListener {
        boolean onBackClick();
    }

    private FragmentUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, (String) null, false, false);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, (String) null, z, false);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, boolean z2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, (String) null, z, z2);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, null, false, i2, i3, 0, 0);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, null, z, i2, i3, 0, 0);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, null, false, i2, i3, i4, i5);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, null, z, i2, i3, i4, i5);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (viewArr == null) {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, (String) null, false, viewArr);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (viewArr == null) {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#4 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, (String) null, z, viewArr);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull List<Fragment> list, @IdRes int i, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (list == null) {
            throw new NullPointerException("Argument 'adds' of type List<Fragment> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, (Fragment[]) list.toArray(new Fragment[0]), i, (String[]) null, i2);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment[] fragmentArr, @IdRes int i, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragmentArr == null) {
            throw new NullPointerException("Argument 'adds' of type Fragment[] (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragmentArr, i, (String[]) null, i2);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, str, false, false);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, str, z, false);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, boolean z2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        putArgs(fragment, new Args(i, str, z, z2));
        operateNoAnim(1, fragmentManager, null, fragment);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, str, false, i2, i3, 0, 0);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, str, z, i2, i3, 0, 0);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, str, false, i2, i3, i4, i5);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        putArgs(fragment, new Args(i, str, false, z));
        addAnim(beginTransaction, i2, i3, i4, i5);
        operate(1, fragmentManager, beginTransaction, null, fragment);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (viewArr == null) {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#4 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, fragment, i, str, false, viewArr);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @NonNull View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'add' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (viewArr == null) {
            throw new NullPointerException("Argument 'sharedElements' of type View[] (#5 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        putArgs(fragment, new Args(i, str, false, z));
        addSharedElement(beginTransaction, viewArr);
        operate(1, fragmentManager, beginTransaction, null, fragment);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull List<Fragment> list, @IdRes int i, String[] strArr, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (list == null) {
            throw new NullPointerException("Argument 'adds' of type List<Fragment> (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        add(fragmentManager, (Fragment[]) list.toArray(new Fragment[0]), i, strArr, i2);
    }

    public static void add(@NonNull FragmentManager fragmentManager, @NonNull Fragment[] fragmentArr, @IdRes int i, String[] strArr, int i2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragmentArr == null) {
            throw new NullPointerException("Argument 'adds' of type Fragment[] (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (strArr == null) {
            int length = fragmentArr.length;
            int i3 = 0;
            while (i3 < length) {
                putArgs(fragmentArr[i3], new Args(i, null, i2 != i3, false));
                i3++;
            }
        } else {
            int length2 = fragmentArr.length;
            int i4 = 0;
            while (i4 < length2) {
                putArgs(fragmentArr[i4], new Args(i, strArr[i4], i2 != i4, false));
                i4++;
            }
        }
        operateNoAnim(1, fragmentManager, null, fragmentArr);
    }

    public static void show(@NonNull Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        putArgs(fragment, false);
        operateNoAnim(2, fragment.getFragmentManager(), null, fragment);
    }

    public static void show(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(fragmentManager);
        for (Fragment fragment : fragments) {
            putArgs(fragment, false);
        }
        operateNoAnim(2, fragmentManager, null, (Fragment[]) fragments.toArray(new Fragment[0]));
    }

    public static void hide(@NonNull Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'hide' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        putArgs(fragment, true);
        operateNoAnim(4, fragment.getFragmentManager(), null, fragment);
    }

    public static void hide(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(fragmentManager);
        for (Fragment fragment : fragments) {
            putArgs(fragment, true);
        }
        operateNoAnim(4, fragmentManager, null, (Fragment[]) fragments.toArray(new Fragment[0]));
    }

    public static void showHide(@NonNull Fragment fragment, @NonNull Fragment fragment2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'hide' of type Fragment (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        showHide(fragment, Collections.singletonList(fragment2));
    }

    public static void showHide(int i, @NonNull Fragment... fragmentArr) {
        if (fragmentArr == null) {
            throw new NullPointerException("Argument 'fragments' of type Fragment[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        showHide(fragmentArr[i], fragmentArr);
    }

    public static void showHide(@NonNull Fragment fragment, @NonNull Fragment... fragmentArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragmentArr == null) {
            throw new NullPointerException("Argument 'hide' of type Fragment[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        showHide(fragment, Arrays.asList(fragmentArr));
    }

    public static void showHide(int i, @NonNull List<Fragment> list) {
        if (list == null) {
            throw new NullPointerException("Argument 'fragments' of type List<Fragment> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        showHide(list.get(i), list);
    }

    public static void showHide(@NonNull Fragment fragment, @NonNull List<Fragment> list) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (list == null) {
            throw new NullPointerException("Argument 'hide' of type List<Fragment> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Iterator<Fragment> it = list.iterator();
        while (true) {
            boolean z = false;
            if (it.hasNext()) {
                Fragment next = it.next();
                if (next != fragment) {
                    z = true;
                }
                putArgs(next, z);
            } else {
                operateNoAnim(8, fragment.getFragmentManager(), fragment, (Fragment[]) list.toArray(new Fragment[0]));
                return;
            }
        }
    }

    public static void showHide(@NonNull Fragment fragment, @NonNull Fragment fragment2, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'hide' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        showHide(fragment, Collections.singletonList(fragment2), i, i2, i3, i4);
    }

    public static void showHide(int i, @NonNull List<Fragment> list, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (list == null) {
            throw new NullPointerException("Argument 'fragments' of type List<Fragment> (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        showHide(list.get(i), list, i2, i3, i4, i5);
    }

    public static void showHide(@NonNull Fragment fragment, @NonNull List<Fragment> list, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'show' of type Fragment (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (list == null) {
            throw new NullPointerException("Argument 'hide' of type List<Fragment> (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Iterator<Fragment> it = list.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            Fragment next = it.next();
            if (next != fragment) {
                z = true;
            }
            putArgs(next, z);
        }
        FragmentManager fragmentManager = fragment.getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            addAnim(beginTransaction, i, i2, i3, i4);
            operate(8, fragmentManager, beginTransaction, fragment, (Fragment[]) list.toArray(new Fragment[0]));
        }
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, (String) null, false);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, (String) null, z);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, (String) null, false, i, i2, 0, 0);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, (String) null, z, i, i2, 0, 0);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, (String) null, false, i, i2, i3, i4);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, (String) null, z, i, i2, i3, i4);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, (String) null, false, viewArr);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, boolean z, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, (String) null, z, viewArr);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, (String) null, false);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, (String) null, z);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, null, false, i2, i3, 0, 0);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, null, z, i2, i3, 0, 0);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, null, false, i2, i3, i4, i5);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, null, z, i2, i3, i4, i5);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, (String) null, false, viewArr);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, boolean z, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, (String) null, z, viewArr);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, str, false);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        FragmentManager fragmentManager = fragment.getFragmentManager();
        if (fragmentManager == null) {
            return;
        }
        replace(fragmentManager, fragment2, getArgs(fragment).f8220id, str, z);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, str, false, i, i2, 0, 0);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, str, z, i, i2, 0, 0);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, str, false, i, i2, i3, i4);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z, @AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        FragmentManager fragmentManager = fragment.getFragmentManager();
        if (fragmentManager == null) {
            return;
        }
        replace(fragmentManager, fragment2, getArgs(fragment).f8220id, str, z, i, i2, i3, i4);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragment, fragment2, str, false, viewArr);
    }

    public static void replace(@NonNull Fragment fragment, @NonNull Fragment fragment2, String str, boolean z, View... viewArr) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'srcFragment' of type Fragment (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment2 == null) {
            throw new NullPointerException("Argument 'destFragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        FragmentManager fragmentManager = fragment.getFragmentManager();
        if (fragmentManager == null) {
            return;
        }
        replace(fragmentManager, fragment2, getArgs(fragment).f8220id, str, z, viewArr);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, str, false);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        putArgs(fragment, new Args(i, str, false, z));
        operate(16, fragmentManager, beginTransaction, null, fragment);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, str, false, i2, i3, 0, 0);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, str, z, i2, i3, 0, 0);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 8, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, str, false, i2, i3, i4, i5);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 9, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        putArgs(fragment, new Args(i, str, false, z));
        addAnim(beginTransaction, i2, i3, i4, i5);
        operate(16, fragmentManager, beginTransaction, null, fragment);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        replace(fragmentManager, fragment, i, str, false, viewArr);
    }

    public static void replace(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, View... viewArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        putArgs(fragment, new Args(i, str, false, z));
        addSharedElement(beginTransaction, viewArr);
        operate(16, fragmentManager, beginTransaction, null, fragment);
    }

    public static void pop(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        pop(fragmentManager, true);
    }

    public static void pop(@NonNull FragmentManager fragmentManager, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (z) {
            fragmentManager.popBackStackImmediate();
        } else {
            fragmentManager.popBackStack();
        }
    }

    public static void popTo(@NonNull FragmentManager fragmentManager, Class<? extends Fragment> cls, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        popTo(fragmentManager, cls, z, true);
    }

    public static void popTo(@NonNull FragmentManager fragmentManager, Class<? extends Fragment> cls, boolean z, boolean z2) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (z2) {
            fragmentManager.popBackStackImmediate(cls.getName(), z ? 1 : 0);
        } else {
            fragmentManager.popBackStack(cls.getName(), z ? 1 : 0);
        }
    }

    public static void popAll(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        popAll(fragmentManager, true);
    }

    public static void popAll(@NonNull FragmentManager fragmentManager, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry backStackEntryAt = fragmentManager.getBackStackEntryAt(0);
            if (z) {
                fragmentManager.popBackStackImmediate(backStackEntryAt.getId(), 1);
            } else {
                fragmentManager.popBackStack(backStackEntryAt.getId(), 1);
            }
        }
    }

    public static void remove(@NonNull Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'remove' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        operateNoAnim(32, fragment.getFragmentManager(), null, fragment);
    }

    public static void removeTo(@NonNull Fragment fragment, boolean z) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'removeTo' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        operateNoAnim(64, fragment.getFragmentManager(), z ? fragment : null, fragment);
    }

    public static void removeAll(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        operateNoAnim(32, fragmentManager, null, (Fragment[]) getFragments(fragmentManager).toArray(new Fragment[0]));
    }

    private static void putArgs(Fragment fragment, Args args) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putInt("args_id", args.f8220id);
        arguments.putBoolean("args_is_hide", args.isHide);
        arguments.putBoolean("args_is_add_stack", args.isAddStack);
        arguments.putString("args_tag", args.tag);
    }

    private static void putArgs(Fragment fragment, boolean z) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putBoolean("args_is_hide", z);
    }

    private static Args getArgs(Fragment fragment) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = Bundle.EMPTY;
        }
        return new Args(arguments.getInt("args_id", fragment.getId()), arguments.getBoolean("args_is_hide"), arguments.getBoolean("args_is_add_stack"));
    }

    private static void operateNoAnim(int i, @Nullable FragmentManager fragmentManager, Fragment fragment, Fragment... fragmentArr) {
        if (fragmentManager == null) {
            return;
        }
        operate(i, fragmentManager, fragmentManager.beginTransaction(), fragment, fragmentArr);
    }

    private static void operate(int i, @NonNull FragmentManager fragmentManager, FragmentTransaction fragmentTransaction, Fragment fragment, Fragment... fragmentArr) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (fragment != null && fragment.isRemoving()) {
            Log.e("FragmentUtils", fragment.getClass().getName() + " is isRemoving");
            return;
        }
        int i2 = 0;
        if (i == 4) {
            int length = fragmentArr.length;
            while (i2 < length) {
                fragmentTransaction.hide(fragmentArr[i2]);
                i2++;
            }
        } else if (i == 8) {
            fragmentTransaction.show(fragment);
            int length2 = fragmentArr.length;
            while (i2 < length2) {
                Fragment fragment2 = fragmentArr[i2];
                if (fragment2 != fragment) {
                    fragmentTransaction.hide(fragment2);
                }
                i2++;
            }
        } else if (i == 16) {
            Bundle arguments = fragmentArr[0].getArguments();
            if (arguments == null) {
                return;
            }
            String string = arguments.getString("args_tag", fragmentArr[0].getClass().getName());
            fragmentTransaction.replace(arguments.getInt("args_id"), fragmentArr[0], string);
            if (arguments.getBoolean("args_is_add_stack")) {
                fragmentTransaction.addToBackStack(string);
            }
        } else if (i == 32) {
            int length3 = fragmentArr.length;
            while (i2 < length3) {
                Fragment fragment3 = fragmentArr[i2];
                if (fragment3 != fragment) {
                    fragmentTransaction.remove(fragment3);
                }
                i2++;
            }
        } else if (i != 64) {
            switch (i) {
                case 1:
                    int length4 = fragmentArr.length;
                    while (i2 < length4) {
                        Fragment fragment4 = fragmentArr[i2];
                        Bundle arguments2 = fragment4.getArguments();
                        if (arguments2 == null) {
                            return;
                        }
                        String string2 = arguments2.getString("args_tag", fragment4.getClass().getName());
                        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(string2);
                        if (findFragmentByTag != null && findFragmentByTag.isAdded()) {
                            fragmentTransaction.remove(findFragmentByTag);
                        }
                        fragmentTransaction.add(arguments2.getInt("args_id"), fragment4, string2);
                        if (arguments2.getBoolean("args_is_hide")) {
                            fragmentTransaction.hide(fragment4);
                        }
                        if (arguments2.getBoolean("args_is_add_stack")) {
                            fragmentTransaction.addToBackStack(string2);
                        }
                        i2++;
                    }
                    break;
                case 2:
                    int length5 = fragmentArr.length;
                    while (i2 < length5) {
                        fragmentTransaction.show(fragmentArr[i2]);
                        i2++;
                    }
                    break;
            }
        } else {
            int length6 = fragmentArr.length - 1;
            while (true) {
                if (length6 < 0) {
                    break;
                }
                Fragment fragment5 = fragmentArr[length6];
                if (fragment5 != fragmentArr[0]) {
                    fragmentTransaction.remove(fragment5);
                    length6--;
                } else if (fragment != null) {
                    fragmentTransaction.remove(fragment5);
                }
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    private static void addAnim(FragmentTransaction fragmentTransaction, int i, int i2, int i3, int i4) {
        fragmentTransaction.setCustomAnimations(i, i2, i3, i4);
    }

    private static void addSharedElement(FragmentTransaction fragmentTransaction, View... viewArr) {
        if (Build.VERSION.SDK_INT >= 21) {
            for (View view : viewArr) {
                fragmentTransaction.addSharedElement(view, view.getTransitionName());
            }
        }
    }

    public static Fragment getTop(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getTopIsInStack(fragmentManager, null, false);
    }

    public static Fragment getTopInStack(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getTopIsInStack(fragmentManager, null, true);
    }

    private static Fragment getTopIsInStack(@NonNull FragmentManager fragmentManager, Fragment fragment, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(fragmentManager);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment2 = fragments.get(size);
            if (fragment2 != null) {
                if (z) {
                    Bundle arguments = fragment2.getArguments();
                    if (arguments != null && arguments.getBoolean("args_is_add_stack")) {
                        return getTopIsInStack(fragment2.getChildFragmentManager(), fragment2, true);
                    }
                } else {
                    return getTopIsInStack(fragment2.getChildFragmentManager(), fragment2, false);
                }
            }
        }
        return fragment;
    }

    public static Fragment getTopShow(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getTopShowIsInStack(fragmentManager, null, false);
    }

    public static Fragment getTopShowInStack(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getTopShowIsInStack(fragmentManager, null, true);
    }

    private static Fragment getTopShowIsInStack(@NonNull FragmentManager fragmentManager, Fragment fragment, boolean z) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(fragmentManager);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment2 = fragments.get(size);
            if (fragment2 != null && fragment2.isResumed() && fragment2.isVisible() && fragment2.getUserVisibleHint()) {
                if (z) {
                    Bundle arguments = fragment2.getArguments();
                    if (arguments != null && arguments.getBoolean("args_is_add_stack")) {
                        return getTopShowIsInStack(fragment2.getChildFragmentManager(), fragment2, true);
                    }
                } else {
                    return getTopShowIsInStack(fragment2.getChildFragmentManager(), fragment2, false);
                }
            }
        }
        return fragment;
    }

    public static List<Fragment> getFragments(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = fragmentManager.getFragments();
        return (fragments == null || fragments.isEmpty()) ? Collections.emptyList() : fragments;
    }

    public static List<Fragment> getFragmentsInStack(@NonNull FragmentManager fragmentManager) {
        Bundle arguments;
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(fragmentManager);
        ArrayList arrayList = new ArrayList();
        for (Fragment fragment : fragments) {
            if (fragment != null && (arguments = fragment.getArguments()) != null && arguments.getBoolean("args_is_add_stack")) {
                arrayList.add(fragment);
            }
        }
        return arrayList;
    }

    public static List<FragmentNode> getAllFragments(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getAllFragments(fragmentManager, new ArrayList());
    }

    private static List<FragmentNode> getAllFragments(@NonNull FragmentManager fragmentManager, List<FragmentNode> list) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(fragmentManager);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = fragments.get(size);
            if (fragment != null) {
                list.add(new FragmentNode(fragment, getAllFragments(fragment.getChildFragmentManager(), new ArrayList())));
            }
        }
        return list;
    }

    public static List<FragmentNode> getAllFragmentsInStack(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getAllFragmentsInStack(fragmentManager, new ArrayList());
    }

    private static List<FragmentNode> getAllFragmentsInStack(@NonNull FragmentManager fragmentManager, List<FragmentNode> list) {
        Bundle arguments;
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(fragmentManager);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = fragments.get(size);
            if (fragment != null && (arguments = fragment.getArguments()) != null && arguments.getBoolean("args_is_add_stack")) {
                list.add(new FragmentNode(fragment, getAllFragmentsInStack(fragment.getChildFragmentManager(), new ArrayList())));
            }
        }
        return list;
    }

    public static Fragment findFragment(@NonNull FragmentManager fragmentManager, Class<? extends Fragment> cls) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return fragmentManager.findFragmentByTag(cls.getName());
    }

    public static Fragment findFragment(@NonNull FragmentManager fragmentManager, @NonNull String str) {
        if (fragmentManager != null) {
            if (str == null) {
                throw new NullPointerException("Argument 'tag' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return fragmentManager.findFragmentByTag(str);
        }
        throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean dispatchBackPress(@NonNull Fragment fragment) {
        if (fragment != null) {
            return fragment.isResumed() && fragment.isVisible() && fragment.getUserVisibleHint() && (fragment instanceof OnBackClickListener) && ((OnBackClickListener) fragment).onBackClick();
        }
        throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean dispatchBackPress(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            throw new NullPointerException("Argument 'fm' of type FragmentManager (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        List<Fragment> fragments = getFragments(fragmentManager);
        if (fragments == null || fragments.isEmpty()) {
            return false;
        }
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = fragments.get(size);
            if (fragment != null && fragment.isResumed() && fragment.isVisible() && fragment.getUserVisibleHint() && (fragment instanceof OnBackClickListener) && ((OnBackClickListener) fragment).onBackClick()) {
                return true;
            }
        }
        return false;
    }

    public static void setBackgroundColor(@NonNull Fragment fragment, @ColorInt int i) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View view = fragment.getView();
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    public static void setBackgroundResource(@NonNull Fragment fragment, @DrawableRes int i) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View view = fragment.getView();
        if (view != null) {
            view.setBackgroundResource(i);
        }
    }

    public static void setBackground(@NonNull Fragment fragment, Drawable drawable) {
        if (fragment == null) {
            throw new NullPointerException("Argument 'fragment' of type Fragment (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View view = fragment.getView();
        if (view == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static String getSimpleName(Fragment fragment) {
        return fragment == null ? "null" : fragment.getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Args {

        /* renamed from: id */
        final int f8220id;
        final boolean isAddStack;
        final boolean isHide;
        final String tag;

        Args(int i, boolean z, boolean z2) {
            this(i, null, z, z2);
        }

        Args(int i, String str, boolean z, boolean z2) {
            this.f8220id = i;
            this.tag = str;
            this.isHide = z;
            this.isAddStack = z2;
        }
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class FragmentNode {
        final Fragment fragment;
        final List<FragmentNode> next;

        public FragmentNode(Fragment fragment, List<FragmentNode> list) {
            this.fragment = fragment;
            this.next = list;
        }

        public Fragment getFragment() {
            return this.fragment;
        }

        public List<FragmentNode> getNext() {
            return this.next;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.fragment.getClass().getSimpleName());
            sb.append("->");
            List<FragmentNode> list = this.next;
            sb.append((list == null || list.isEmpty()) ? "no child" : this.next.toString());
            return sb.toString();
        }
    }
}
