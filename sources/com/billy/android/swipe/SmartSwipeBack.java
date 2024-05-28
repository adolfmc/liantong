package com.billy.android.swipe;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import com.billy.android.swipe.consumer.ActivityDoorBackConsumer;
import com.billy.android.swipe.consumer.ActivityShuttersBackConsumer;
import com.billy.android.swipe.consumer.ActivitySlidingBackConsumer;
import com.billy.android.swipe.consumer.BezierBackConsumer;
import com.billy.android.swipe.consumer.StayConsumer;
import com.billy.android.swipe.listener.SimpleSwipeListener;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SmartSwipeBack {
    public static final ArrayList<Activity> ACTIVITIES = new ArrayList<>();
    private static ActivitySwipeBackListener activitySwipeBackListener;
    private static IPreviousFinder mPreviousFinder;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ActivitySwipeBackFilter {
        boolean onFilter(Activity activity);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface IPreviousFinder {
        Activity findPreviousActivity(Activity activity);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface SwipeBackConsumerFactory {
        SwipeConsumer createSwipeBackConsumer(Activity activity);
    }

    public static void activityBack(Application application, SwipeBackConsumerFactory swipeBackConsumerFactory) {
        activityBack(application, swipeBackConsumerFactory, null);
    }

    public static void activityBack(Application application, SwipeBackConsumerFactory swipeBackConsumerFactory, ActivitySwipeBackFilter activitySwipeBackFilter) {
        ActivitySwipeBackListener activitySwipeBackListener2 = activitySwipeBackListener;
        if (activitySwipeBackListener2 == null) {
            activitySwipeBackListener = new ActivitySwipeBackListener(swipeBackConsumerFactory, activitySwipeBackFilter);
        } else {
            application.unregisterActivityLifecycleCallbacks(activitySwipeBackListener2);
            activitySwipeBackListener.mFactory = swipeBackConsumerFactory;
            activitySwipeBackListener.mFilter = activitySwipeBackFilter;
        }
        application.registerActivityLifecycleCallbacks(activitySwipeBackListener);
    }

    public static void activityStayBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter) {
        activityStayBack(application, activitySwipeBackFilter, SmartSwipe.dp2px(20, application), 0, 1);
    }

    public static void activityStayBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter, final int i, final int i2, final int i3) {
        activityBack(application, new SwipeBackConsumerFactory() { // from class: com.billy.android.swipe.SmartSwipeBack.1
            @Override // com.billy.android.swipe.SmartSwipeBack.SwipeBackConsumerFactory
            public SwipeConsumer createSwipeBackConsumer(final Activity activity) {
                return new StayConsumer().setMinVelocity(i2).setEdgeSize(i).enableDirection(i3).addListener(new SimpleSwipeListener() { // from class: com.billy.android.swipe.SmartSwipeBack.1.1
                    @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                    public void onSwipeOpened(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i4) {
                        Activity activity2 = activity;
                        if (activity2 != null) {
                            activity2.finish();
                        }
                    }
                });
            }
        }, activitySwipeBackFilter);
    }

    public static void activitySlidingBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter) {
        activitySlidingBack(application, activitySwipeBackFilter, 0.5f);
    }

    public static void activitySlidingBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter, float f) {
        activitySlidingBack(application, activitySwipeBackFilter, SmartSwipe.dp2px(20, application), 0, Integer.MIN_VALUE, SmartSwipe.dp2px(10, application), f, 1);
    }

    public static void activitySlidingBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter, final int i, final int i2, final int i3, final int i4, final float f, final int i5) {
        if (Build.VERSION.SDK_INT < 21) {
            activityStayBack(application, activitySwipeBackFilter, i, 0, i5);
        } else {
            activityBack(application, new SwipeBackConsumerFactory() { // from class: com.billy.android.swipe.SmartSwipeBack.2
                @Override // com.billy.android.swipe.SmartSwipeBack.SwipeBackConsumerFactory
                public SwipeConsumer createSwipeBackConsumer(final Activity activity) {
                    return new ActivitySlidingBackConsumer(activity).setRelativeMoveFactor(f).setScrimColor(i2).setShadowColor(i3).setShadowSize(i4).setEdgeSize(i).enableDirection(i5).addListener(new SimpleSwipeListener() { // from class: com.billy.android.swipe.SmartSwipeBack.2.1
                        @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                        public void onSwipeOpened(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i6) {
                            Activity activity2 = activity;
                            if (activity2 != null) {
                                activity2.finish();
                                activity.overridePendingTransition(C3336R.anim.anim_none, C3336R.anim.anim_none);
                            }
                        }
                    });
                }
            }, activitySwipeBackFilter);
        }
    }

    public static void activityBezierBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter) {
        activityBezierBack(application, activitySwipeBackFilter, SmartSwipe.dp2px(20, application));
    }

    public static void activityBezierBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter, int i) {
        activityBezierBack(application, activitySwipeBackFilter, i, SmartSwipe.dp2px(200, application), SmartSwipe.dp2px(30, application), -16777216, -1, 1);
    }

    public static void activityBezierBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter, final int i, final int i2, final int i3, final int i4, final int i5, final int i6) {
        activityBack(application, new SwipeBackConsumerFactory() { // from class: com.billy.android.swipe.SmartSwipeBack.3
            @Override // com.billy.android.swipe.SmartSwipeBack.SwipeBackConsumerFactory
            public SwipeConsumer createSwipeBackConsumer(final Activity activity) {
                return new BezierBackConsumer().setColor(i4).setArrowColor(i5).setSize(i2).setOpenDistance(i3).addListener(new SimpleSwipeListener() { // from class: com.billy.android.swipe.SmartSwipeBack.3.1
                    @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                    public void onSwipeRelease(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i7, float f, float f2, float f3) {
                        if (f >= 1.0f) {
                            activity.finish();
                        }
                    }
                }).setEdgeSize(i).enableDirection(i6);
            }
        }, activitySwipeBackFilter);
    }

    public static void activityDoorBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter) {
        activityDoorBack(application, activitySwipeBackFilter, 1, SmartSwipe.dp2px(20, application), Integer.MIN_VALUE, true);
    }

    public static void activityDoorBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter, final int i, final int i2, final int i3, final boolean z) {
        activityBack(application, new SwipeBackConsumerFactory() { // from class: com.billy.android.swipe.SmartSwipeBack.4
            @Override // com.billy.android.swipe.SmartSwipeBack.SwipeBackConsumerFactory
            public SwipeConsumer createSwipeBackConsumer(final Activity activity) {
                return new ActivityDoorBackConsumer(activity).setRefreshable(z).setScrimColor(i3).enableDirection(i).setEdgeSize(i2).addListener(new SimpleSwipeListener() { // from class: com.billy.android.swipe.SmartSwipeBack.4.1
                    @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                    public void onSwipeOpened(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i4) {
                        activity.finish();
                        activity.overridePendingTransition(C3336R.anim.anim_none, C3336R.anim.anim_none);
                    }
                });
            }
        }, activitySwipeBackFilter);
    }

    public static void activityShuttersBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter) {
        activityShuttersBack(application, activitySwipeBackFilter, 1, SmartSwipe.dp2px(20, application), Integer.MIN_VALUE, true);
    }

    public static void activityShuttersBack(Application application, ActivitySwipeBackFilter activitySwipeBackFilter, final int i, final int i2, final int i3, final boolean z) {
        activityBack(application, new SwipeBackConsumerFactory() { // from class: com.billy.android.swipe.SmartSwipeBack.5
            @Override // com.billy.android.swipe.SmartSwipeBack.SwipeBackConsumerFactory
            public SwipeConsumer createSwipeBackConsumer(final Activity activity) {
                return new ActivityShuttersBackConsumer(activity).setRefreshable(z).setScrimColor(i3).enableDirection(i).setEdgeSize(i2).addListener(new SimpleSwipeListener() { // from class: com.billy.android.swipe.SmartSwipeBack.5.1
                    @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                    public void onSwipeOpened(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i4) {
                        activity.finish();
                        activity.overridePendingTransition(C3336R.anim.anim_none, C3336R.anim.anim_none);
                    }
                });
            }
        }, activitySwipeBackFilter);
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class ActivitySwipeBackListener implements Application.ActivityLifecycleCallbacks {
        private SwipeBackConsumerFactory mFactory;
        private ActivitySwipeBackFilter mFilter;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        ActivitySwipeBackListener(SwipeBackConsumerFactory swipeBackConsumerFactory, ActivitySwipeBackFilter activitySwipeBackFilter) {
            this.mFactory = swipeBackConsumerFactory;
            this.mFilter = activitySwipeBackFilter;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            SmartSwipeBack.ACTIVITIES.add(activity);
            if (this.mFactory == null) {
                return;
            }
            ActivitySwipeBackFilter activitySwipeBackFilter = this.mFilter;
            if (activitySwipeBackFilter == null || activitySwipeBackFilter.onFilter(activity)) {
                SmartSwipe.wrap(activity).addConsumer(this.mFactory.createSwipeBackConsumer(activity));
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            SmartSwipeBack.ACTIVITIES.remove(activity);
        }
    }

    public static Activity findPreviousActivity(Activity activity) {
        int indexOf;
        IPreviousFinder iPreviousFinder = mPreviousFinder;
        if (iPreviousFinder != null) {
            return iPreviousFinder.findPreviousActivity(activity);
        }
        if (activity == null || (indexOf = ACTIVITIES.indexOf(activity)) <= 0) {
            return null;
        }
        return ACTIVITIES.get(indexOf - 1);
    }

    public static void setPreviousFinder(IPreviousFinder iPreviousFinder) {
        mPreviousFinder = iPreviousFinder;
    }
}
