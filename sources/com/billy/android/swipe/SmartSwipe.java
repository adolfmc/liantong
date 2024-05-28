package com.billy.android.swipe;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SmartSwipe {
    private static IWrapperFactory factory;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface IWrapperFactory {
        SmartSwipeWrapper createWrapper(Context context);
    }

    public static SmartSwipeWrapper wrap(Activity activity) {
        SmartSwipeWrapper peekWrapperFor = peekWrapperFor(activity);
        if (peekWrapperFor != null) {
            return peekWrapperFor;
        }
        View decorView = activity.getWindow().getDecorView();
        if (decorView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) decorView;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.findViewById(16908290) != null) {
                    return wrap(childAt);
                }
            }
        }
        return wrap(decorView.findViewById(16908290));
    }

    public static SmartSwipeWrapper peekWrapperFor(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        for (View findViewById = decorView.findViewById(16908290); findViewById != null && findViewById != decorView; findViewById = (View) findViewById.getParent()) {
            if (findViewById.getParent() instanceof SmartSwipeWrapper) {
                return (SmartSwipeWrapper) findViewById.getParent();
            }
        }
        return null;
    }

    public static SmartSwipeWrapper wrap(Activity activity, int i) {
        View findViewById;
        if (activity == null || (findViewById = activity.findViewById(i)) == null) {
            return null;
        }
        return wrap(findViewById);
    }

    public static SmartSwipeWrapper wrap(View view) {
        SmartSwipeWrapper createNewWrapper;
        SmartSwipeWrapper peekWrapperFor = peekWrapperFor(view);
        if (peekWrapperFor != null) {
            return peekWrapperFor;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (view.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            createNewWrapper = createNewWrapper(view.getContext());
            int indexOfChild = viewGroup.indexOfChild(view);
            viewGroup.removeView(view);
            viewGroup.addView(createNewWrapper, indexOfChild, layoutParams);
        } else {
            createNewWrapper = createNewWrapper(view.getContext());
            if (layoutParams != null) {
                createNewWrapper.setLayoutParams(layoutParams);
            }
        }
        createNewWrapper.setContentView(view);
        return createNewWrapper;
    }

    public static SmartSwipeWrapper peekWrapperFor(View view) {
        if (view.getParent() instanceof SmartSwipeWrapper) {
            return (SmartSwipeWrapper) view.getParent();
        }
        return null;
    }

    public static void switchDirectionEnable(View view, boolean z, int i) {
        enableOrDisableFor(peekWrapperFor(view), z, i);
    }

    public static void switchDirectionEnable(Activity activity, boolean z, int i) {
        enableOrDisableFor(peekWrapperFor(activity), z, i);
    }

    private static void enableOrDisableFor(SmartSwipeWrapper smartSwipeWrapper, boolean z, int i) {
        if (smartSwipeWrapper != null) {
            smartSwipeWrapper.enableDirection(i, z);
        }
    }

    public static int dp2px(int i, Context context) {
        return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static int ensureBetween(int i, int i2, int i3) {
        return Math.max(i2, Math.min(i, i3));
    }

    public static float ensureBetween(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f, f3));
    }

    public static double ensureBetween(double d, double d2, double d3) {
        return Math.max(d2, Math.min(d, d3));
    }

    private static SmartSwipeWrapper createNewWrapper(Context context) {
        SmartSwipeWrapper createWrapper;
        IWrapperFactory iWrapperFactory = factory;
        return (iWrapperFactory == null || (createWrapper = iWrapperFactory.createWrapper(context)) == null) ? new SmartSwipeWrapper(context) : createWrapper;
    }

    public static void setFactory(IWrapperFactory iWrapperFactory) {
        factory = iWrapperFactory;
    }

    static {
        try {
            if (initFactoryByClassName("com.billy.android.swipe.androidx.WrapperFactory")) {
                return;
            }
            initFactoryByClassName("com.billy.android.swipe.support.WrapperFactory");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static boolean initFactoryByClassName(String str) {
        try {
            Class<?> cls = Class.forName(str);
            if (cls != null) {
                Object newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                if (newInstance instanceof IWrapperFactory) {
                    setFactory((IWrapperFactory) newInstance);
                    return true;
                }
                return true;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
