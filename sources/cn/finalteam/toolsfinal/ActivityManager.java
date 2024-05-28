package cn.finalteam.toolsfinal;

import android.app.Activity;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.Stack;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class ActivityManager {
    private static Stack<Activity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {
    }

    public static ActivityManager getActivityManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        Stack<Activity> stack = activityStack;
        if (stack == null) {
            return null;
        }
        return stack.lastElement();
    }

    public void finishActivity() {
        Stack<Activity> stack = activityStack;
        if (stack == null) {
            return;
        }
        finishActivity(stack.lastElement());
    }

    public void finishActivity(Activity activity) {
        Stack<Activity> stack = activityStack;
        if (stack == null || activity == null) {
            return;
        }
        stack.remove(activity);
        activity.finish();
    }

    public void finishActivity(Class<?> cls) {
        Stack<Activity> stack = activityStack;
        if (stack == null) {
            return;
        }
        Iterator<Activity> it = stack.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            if (next != null && next.getClass().equals(cls)) {
                next.finish();
                it.remove();
            }
        }
    }

    public void finishAllActivity() {
        Stack<Activity> stack = activityStack;
        if (stack == null) {
            return;
        }
        Iterator<Activity> it = stack.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            if (next != null && !next.isFinishing()) {
                next.finish();
            }
        }
        activityStack.clear();
    }

    public Activity getActivity(String str) {
        Iterator<Activity> it = activityStack.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            if (next != null && TextUtils.equals(next.getClass().getName(), str)) {
                return next;
            }
        }
        return null;
    }

    public void appExit(Context context) {
        try {
            finishAllActivity();
            Process.killProcess(Process.myPid());
        } catch (Exception unused) {
        }
    }
}
