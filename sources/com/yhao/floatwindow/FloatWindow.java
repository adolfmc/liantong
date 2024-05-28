package com.yhao.floatwindow;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class FloatWindow {
    public static int lastX = 0;
    public static int lastY = 0;
    private static C11689B mBuilder = null;
    private static final String mDefaultTag = "default_float_window_tag";
    private static Map<String, IFloatWindow> mFloatWindowMap;

    private FloatWindow() {
    }

    public static IFloatWindow get() {
        return get("default_float_window_tag");
    }

    public static IFloatWindow get(@NonNull String str) {
        Map<String, IFloatWindow> map = mFloatWindowMap;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    @MainThread
    public static C11689B with(@NonNull Context context) {
        C11689B c11689b = new C11689B(context);
        mBuilder = c11689b;
        return c11689b;
    }

    public static void destroy() {
        destroy("default_float_window_tag");
    }

    public static void destroy(String str) {
        Map<String, IFloatWindow> map = mFloatWindowMap;
        if (map == null || !map.containsKey(str)) {
            return;
        }
        mFloatWindowMap.get(str).dismiss();
        mFloatWindowMap.remove(str);
    }

    /* renamed from: com.yhao.floatwindow.FloatWindow$B */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11689B {
        Class[] mActivities;
        Context mApplicationContext;
        boolean mDesktopShow;
        TimeInterpolator mInterpolator;
        private int mLayoutId;
        PermissionListener mPermissionListener;
        int mSlideLeftMargin;
        int mSlideRightMargin;
        View mView;
        ViewStateListener mViewStateListener;
        int xOffset;
        int yOffset;
        int mWidth = -2;
        int mHeight = -2;
        int gravity = 8388661;
        boolean mShow = true;
        int mMoveType = 3;
        long mDuration = 300;
        private String mTag = "default_float_window_tag";
        boolean isDrag = true;

        private C11689B() {
        }

        C11689B(Context context) {
            this.mApplicationContext = context;
        }

        public C11689B setView(@NonNull View view) {
            this.mView = view;
            return this;
        }

        public C11689B setView(@LayoutRes int i) {
            this.mLayoutId = i;
            return this;
        }

        public C11689B setWidth(int i) {
            this.mWidth = i;
            return this;
        }

        public C11689B setHeight(int i) {
            this.mHeight = i;
            return this;
        }

        public C11689B setWidth(int i, float f) {
            int screenHeight;
            if (i == 0) {
                screenHeight = Util.getScreenWidth(this.mApplicationContext);
            } else {
                screenHeight = Util.getScreenHeight(this.mApplicationContext);
            }
            this.mWidth = (int) (screenHeight * f);
            return this;
        }

        public C11689B setHeight(int i, float f) {
            int screenHeight;
            if (i == 0) {
                screenHeight = Util.getScreenWidth(this.mApplicationContext);
            } else {
                screenHeight = Util.getScreenHeight(this.mApplicationContext);
            }
            this.mHeight = (int) (screenHeight * f);
            return this;
        }

        public C11689B setX(int i) {
            this.xOffset = i;
            return this;
        }

        public C11689B setY(int i) {
            this.yOffset = i;
            return this;
        }

        public C11689B setX(int i, float f) {
            int screenHeight;
            if (i == 0) {
                screenHeight = Util.getScreenWidth(this.mApplicationContext);
            } else {
                screenHeight = Util.getScreenHeight(this.mApplicationContext);
            }
            this.xOffset = (int) (screenHeight * f);
            return this;
        }

        public C11689B setY(int i, float f) {
            int screenHeight;
            if (i == 0) {
                screenHeight = Util.getScreenWidth(this.mApplicationContext);
            } else {
                screenHeight = Util.getScreenHeight(this.mApplicationContext);
            }
            this.yOffset = (int) (screenHeight * f);
            return this;
        }

        public C11689B setFilter(boolean z, @NonNull Class... clsArr) {
            this.mShow = z;
            this.mActivities = clsArr;
            return this;
        }

        public C11689B setMoveType(int i) {
            return setMoveType(i, 0, 0);
        }

        public C11689B setMoveType(int i, int i2, int i3) {
            this.mMoveType = i;
            this.mSlideLeftMargin = i2;
            this.mSlideRightMargin = i3;
            return this;
        }

        public C11689B setMoveStyle(long j, @Nullable TimeInterpolator timeInterpolator) {
            this.mDuration = j;
            this.mInterpolator = timeInterpolator;
            return this;
        }

        public C11689B setTag(@NonNull String str) {
            this.mTag = str;
            return this;
        }

        public C11689B setDesktopShow(boolean z) {
            this.mDesktopShow = z;
            return this;
        }

        public C11689B setPermissionListener(PermissionListener permissionListener) {
            this.mPermissionListener = permissionListener;
            return this;
        }

        public C11689B setViewStateListener(ViewStateListener viewStateListener) {
            this.mViewStateListener = viewStateListener;
            return this;
        }

        public void build() {
            if (FloatWindow.mFloatWindowMap == null) {
                Map unused = FloatWindow.mFloatWindowMap = new HashMap();
            }
            if (this.mView == null) {
                this.mView = Util.inflate(this.mApplicationContext, this.mLayoutId);
            }
            FloatWindow.mFloatWindowMap.put(this.mTag, new IFloatWindowImpl(this));
        }
    }
}
