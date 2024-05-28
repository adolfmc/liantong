package com.bytedance.pangle.fragment;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import com.bytedance.applog.tracker.Tracker;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import com.bytedance.pangle.util.MethodUtils;
import java.lang.reflect.InvocationTargetException;

@Keep
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ZeusFragmentV4 extends Fragment {
    Application.ActivityLifecycleCallbacks callbacks = new C3835b(this);

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        Tracker.onResume(this);
        super.onResume();
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    public ZeusFragmentV4() {
        C3834a.m16853a(getClass());
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public Context getContext() {
        try {
            return ZeusTransformUtils.wrapperContext(super.getContext(), (String) MethodUtils.invokeStaticMethod(getClass(), "_GET_PLUGIN_PKG", new Object[0]));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return super.getContext();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return super.getContext();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return super.getContext();
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Zeus.getAppApplication().registerActivityLifecycleCallbacks(this.callbacks);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDetach() {
        Zeus.getAppApplication().unregisterActivityLifecycleCallbacks(this.callbacks);
        super.onDetach();
    }
}
