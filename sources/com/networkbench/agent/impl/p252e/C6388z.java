package com.networkbench.agent.impl.p252e;

import android.graphics.Rect;
import android.support.p086v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsSpinner;
import android.widget.TabHost;
import android.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.z */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6388z {

    /* renamed from: a */
    public static List<Class<?>> f16135a = new ArrayList();

    static {
        f16135a.add(AbsSpinner.class);
        f16135a.add(AbsListView.class);
        f16135a.add(TabHost.class);
        try {
            Class.forName("android.support.v7.widget.RecyclerView");
            f16135a.add(RecyclerView.class);
        } catch (ClassNotFoundException unused) {
        }
        try {
            Class.forName("android.widget.Toolbar");
            f16135a.add(Toolbar.class);
        } catch (ClassNotFoundException unused2) {
        }
    }

    /* renamed from: a */
    public static View m10186a(View view, MotionEvent motionEvent) {
        View m10182b = m10182b(view, motionEvent);
        if (m10182b == null || (m10182b instanceof ViewGroup)) {
            return null;
        }
        View m10180c = m10180c(m10182b);
        if (m10180c == null) {
            View m10187a = m10187a(m10182b);
            if (m10187a != null) {
                return m10187a;
            }
            if (m10182b.isClickable()) {
                return m10182b;
            }
            return null;
        }
        return m10180c;
    }

    /* renamed from: b */
    private static View m10182b(View view, MotionEvent motionEvent) {
        ArrayList<View> m10179d = m10179d(view);
        ArrayList arrayList = new ArrayList();
        Rect rect = new Rect();
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        View view2 = null;
        for (View view3 : m10179d) {
            if (view3.onFilterTouchEventForSecurity(motionEvent) && view3.getVisibility() == 0) {
                view3.getGlobalVisibleRect(rect);
                if (rect.contains(rawX, rawY)) {
                    arrayList.add(view3);
                    view2 = view3;
                }
            }
        }
        Log.d("abc", "allResult size:" + arrayList.size());
        return view2;
    }

    /* renamed from: a */
    private static View m10184a(List<View> list) {
        if (list.size() <= 0) {
            return null;
        }
        View view = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) != null && view.getWidth() > list.get(i).getWidth() && view.getHeight() > list.get(i).getHeight()) {
                view = list.get(i);
            }
        }
        return view;
    }

    /* renamed from: d */
    private static ArrayList<View> m10179d(View view) {
        if (!(view instanceof ViewGroup)) {
            ArrayList<View> arrayList = new ArrayList<>();
            arrayList.add(view);
            return arrayList;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(m10179d(childAt));
            arrayList2.addAll(arrayList3);
        }
        return arrayList2;
    }

    /* renamed from: a */
    public static View m10187a(View view) {
        if (view == null) {
            return null;
        }
        return m10183b(view);
    }

    /* renamed from: b */
    public static synchronized View m10183b(View view) {
        synchronized (C6388z.class) {
            View view2 = null;
            if (view == null) {
                return null;
            }
            if (view.isClickable()) {
                return view;
            }
            ViewParent parent = view.getParent();
            if ((parent instanceof View) && view != null) {
                view2 = m10183b((View) parent);
            }
            return view2;
        }
    }

    /* renamed from: c */
    public static View m10180c(View view) {
        if (view == null) {
            return null;
        }
        return m10185a(view, f16135a);
    }

    /* renamed from: a */
    public static synchronized View m10185a(View view, List<Class<?>> list) {
        synchronized (C6388z.class) {
            View view2 = null;
            if (view == null) {
                return null;
            }
            if (m10181b(view, list)) {
                return view;
            }
            ViewParent parent = view.getParent();
            if ((parent instanceof View) && view != null) {
                view2 = m10185a((View) parent, list);
            }
            return view2;
        }
    }

    /* renamed from: b */
    public static boolean m10181b(View view, List<Class<?>> list) {
        for (Class<?> cls : list) {
            if (cls.isInstance(view)) {
                return true;
            }
        }
        return false;
    }
}
