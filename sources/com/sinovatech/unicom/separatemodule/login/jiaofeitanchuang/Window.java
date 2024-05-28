package com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Functions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Window.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0007J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0016H\u0007R\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\t\u001a\u0004\u0018\u00010\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\n\u0010\u0006R!\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\b\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0011\u001a\u0004\u0018\u00010\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\b\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, m1890d2 = {"Lcom/sinovatech/unicom/separatemodule/login/jiaofeitanchuang/Window;", "", "()V", "mParams", "Ljava/lang/reflect/Field;", "getMParams", "()Ljava/lang/reflect/Field;", "mParams$delegate", "Lkotlin/Lazy;", "mViewsField", "getMViewsField", "mViewsField$delegate", "windowManagerClass", "Ljava/lang/Class;", "getWindowManagerClass", "()Ljava/lang/Class;", "windowManagerClass$delegate", "windowManagerInstance", "getWindowManagerInstance", "()Ljava/lang/Object;", "windowManagerInstance$delegate", "getParams", "", "Landroid/view/WindowManager$LayoutParams;", "getViews", "Landroid/view/View;", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class Window {
    public static final Window INSTANCE = new Window();
    private static final Lazy windowManagerClass$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Functions) new Functions<Class<?>>() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.Window$windowManagerClass$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Functions
        @Nullable
        public final Class<?> invoke() {
            try {
                return Class.forName("android.view.WindowManagerGlobal");
            } catch (Throwable th) {
                Log.w("WindowManagerSpy", th);
                return null;
            }
        }
    });
    private static final Lazy windowManagerInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Functions) new Functions<Object>() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.Window$windowManagerInstance$2
        @Override // kotlin.jvm.functions.Functions
        @Nullable
        public final Object invoke() {
            Class windowManagerClass;
            windowManagerClass = Window.INSTANCE.getWindowManagerClass();
            if (windowManagerClass != null) {
                return windowManagerClass.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            }
            return null;
        }
    });
    private static final Lazy mViewsField$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Functions) new Functions<Field>() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.Window$mViewsField$2
        @Override // kotlin.jvm.functions.Functions
        @Nullable
        public final Field invoke() {
            Class windowManagerClass;
            windowManagerClass = Window.INSTANCE.getWindowManagerClass();
            if (windowManagerClass != null) {
                Field declaredField = windowManagerClass.getDeclaredField("mViews");
                declaredField.setAccessible(true);
                return declaredField;
            }
            return null;
        }
    });
    private static final Lazy mParams$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Functions) new Functions<Field>() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.Window$mParams$2
        @Override // kotlin.jvm.functions.Functions
        @Nullable
        public final Field invoke() {
            Class windowManagerClass;
            windowManagerClass = Window.INSTANCE.getWindowManagerClass();
            if (windowManagerClass != null) {
                Field declaredField = windowManagerClass.getDeclaredField("mParams");
                declaredField.setAccessible(true);
                return declaredField;
            }
            return null;
        }
    });

    private final Field getMParams() {
        return (Field) mParams$delegate.getValue();
    }

    private final Field getMViewsField() {
        return (Field) mViewsField$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getWindowManagerClass() {
        return (Class) windowManagerClass$delegate.getValue();
    }

    private final Object getWindowManagerInstance() {
        return windowManagerInstance$delegate.getValue();
    }

    private Window() {
    }

    @SuppressLint({"PrivateApi", "ObsoleteSdkInt", "DiscouragedPrivateApi"})
    @NotNull
    public final List<View> getViews() {
        try {
            Object windowManagerInstance = getWindowManagerInstance();
            if (windowManagerInstance != null) {
                Field mViewsField = INSTANCE.getMViewsField();
                if (mViewsField == null) {
                    Void r0 = null;
                } else {
                    Object obj = mViewsField.get(windowManagerInstance);
                    if (obj != null) {
                        return (ArrayList) obj;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<android.view.View> /* = java.util.ArrayList<android.view.View> */");
                }
            }
        } catch (Throwable th) {
            Log.w("WindowManagerSpy", th);
        }
        return new ArrayList();
    }

    @SuppressLint({"PrivateApi", "ObsoleteSdkInt", "DiscouragedPrivateApi"})
    @NotNull
    public final List<WindowManager.LayoutParams> getParams() {
        try {
            Object windowManagerInstance = getWindowManagerInstance();
            if (windowManagerInstance != null) {
                Field mParams = INSTANCE.getMParams();
                if (mParams == null) {
                    Void r0 = null;
                } else {
                    Object obj = mParams.get(windowManagerInstance);
                    if (obj != null) {
                        return (ArrayList) obj;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<android.view.WindowManager.LayoutParams> /* = java.util.ArrayList<android.view.WindowManager.LayoutParams> */");
                }
            }
        } catch (Throwable th) {
            Log.w("WindowManagerSpy", th);
        }
        return new ArrayList();
    }
}
