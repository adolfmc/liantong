package com.mob.tools.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.HashSet;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ActivityTracker implements PublicMemberKeeper {

    /* renamed from: a */
    private static ActivityTracker f15040a;

    /* renamed from: b */
    private HashSet<Tracker> f15041b = new HashSet<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface EachTracker extends PublicMemberKeeper {
        void each(Tracker tracker);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface Tracker extends PublicMemberKeeper {
        void onCreated(Activity activity, Bundle bundle);

        void onDestroyed(Activity activity);

        void onPaused(Activity activity);

        void onResumed(Activity activity);

        void onSaveInstanceState(Activity activity, Bundle bundle);

        void onStarted(Activity activity);

        void onStopped(Activity activity);
    }

    private ActivityTracker(Context context) {
        m11307a(context);
    }

    public static synchronized ActivityTracker getInstance(Context context) {
        ActivityTracker activityTracker;
        synchronized (ActivityTracker.class) {
            if (f15040a == null) {
                f15040a = new ActivityTracker(context);
            }
            activityTracker = f15040a;
        }
        return activityTracker;
    }

    /* renamed from: a */
    private void m11307a(Context context) {
        try {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.mob.tools.utils.ActivityTracker.1
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    ActivityTracker.this.m11308a(activity, bundle);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                    ActivityTracker.this.m11309a(activity);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    ActivityTracker.this.m11303b(activity);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    ActivityTracker.this.m11299c(activity);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    ActivityTracker.this.m11297d(activity);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    ActivityTracker.this.m11295e(activity);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    ActivityTracker.this.m11302b(activity, bundle);
                }
            });
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
        }
    }

    public void addTracker(Tracker tracker) {
        synchronized (this.f15041b) {
            this.f15041b.add(tracker);
        }
    }

    public void removeTracker(Tracker tracker) {
        synchronized (this.f15041b) {
            this.f15041b.remove(tracker);
        }
    }

    /* renamed from: a */
    private void m11306a(EachTracker eachTracker) {
        Tracker[] trackerArr;
        try {
            synchronized (this.f15041b) {
                trackerArr = (Tracker[]) this.f15041b.toArray(new Tracker[this.f15041b.size()]);
            }
            for (Tracker tracker : trackerArr) {
                if (tracker != null) {
                    eachTracker.each(tracker);
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m11308a(final Activity activity, final Bundle bundle) {
        m11306a(new EachTracker() { // from class: com.mob.tools.utils.ActivityTracker.2
            @Override // com.mob.tools.utils.ActivityTracker.EachTracker
            public void each(Tracker tracker) {
                tracker.onCreated(activity, bundle);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m11309a(final Activity activity) {
        m11306a(new EachTracker() { // from class: com.mob.tools.utils.ActivityTracker.3
            @Override // com.mob.tools.utils.ActivityTracker.EachTracker
            public void each(Tracker tracker) {
                tracker.onStarted(activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m11303b(final Activity activity) {
        m11306a(new EachTracker() { // from class: com.mob.tools.utils.ActivityTracker.4
            @Override // com.mob.tools.utils.ActivityTracker.EachTracker
            public void each(Tracker tracker) {
                tracker.onResumed(activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m11299c(final Activity activity) {
        m11306a(new EachTracker() { // from class: com.mob.tools.utils.ActivityTracker.5
            @Override // com.mob.tools.utils.ActivityTracker.EachTracker
            public void each(Tracker tracker) {
                tracker.onPaused(activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m11297d(final Activity activity) {
        m11306a(new EachTracker() { // from class: com.mob.tools.utils.ActivityTracker.6
            @Override // com.mob.tools.utils.ActivityTracker.EachTracker
            public void each(Tracker tracker) {
                tracker.onStopped(activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m11295e(final Activity activity) {
        m11306a(new EachTracker() { // from class: com.mob.tools.utils.ActivityTracker.7
            @Override // com.mob.tools.utils.ActivityTracker.EachTracker
            public void each(Tracker tracker) {
                tracker.onDestroyed(activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m11302b(final Activity activity, final Bundle bundle) {
        m11306a(new EachTracker() { // from class: com.mob.tools.utils.ActivityTracker.8
            @Override // com.mob.tools.utils.ActivityTracker.EachTracker
            public void each(Tracker tracker) {
                tracker.onSaveInstanceState(activity, bundle);
            }
        });
    }
}
