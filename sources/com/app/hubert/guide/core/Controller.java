package com.app.hubert.guide.core;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.app.hubert.guide.core.GuideLayout;
import com.app.hubert.guide.lifecycle.FragmentLifecycleAdapter;
import com.app.hubert.guide.lifecycle.ListenerFragment;
import com.app.hubert.guide.lifecycle.V4ListenerFragment;
import com.app.hubert.guide.listener.OnGuideChangedListener;
import com.app.hubert.guide.listener.OnPageChangedListener;
import com.app.hubert.guide.model.GuidePage;
import com.app.hubert.guide.util.LogUtil;
import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.List;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class Controller {
    private static final String LISTENER_FRAGMENT = "listener_fragment";
    private static final String TAG = "Controller";
    private Activity activity;
    private boolean alwaysShow;
    private int current;
    private GuideLayout currentLayout;
    private Fragment fragment;
    private List<GuidePage> guidePages;
    private int indexOfChild;
    private boolean isShowing;
    private String label;
    private FrameLayout mParentView;
    private OnGuideChangedListener onGuideChangedListener;
    private OnPageChangedListener onPageChangedListener;
    private int showCounts;

    /* renamed from: sp */
    private SharedPreferences f4065sp;
    private android.support.p083v4.app.Fragment v4Fragment;

    public Controller(Builder builder) {
        this.indexOfChild = -1;
        this.activity = builder.activity;
        this.fragment = builder.fragment;
        this.v4Fragment = builder.v4Fragment;
        this.onGuideChangedListener = builder.onGuideChangedListener;
        this.onPageChangedListener = builder.onPageChangedListener;
        this.label = builder.label;
        this.alwaysShow = builder.alwaysShow;
        this.guidePages = builder.guidePages;
        this.showCounts = builder.showCounts;
        try {
            View view = builder.anchor;
            view = view == null ? this.activity.findViewById(16908290) : view;
            if (view instanceof FrameLayout) {
                this.mParentView = (FrameLayout) view;
            } else {
                FrameLayout frameLayout = new FrameLayout(this.activity);
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                this.indexOfChild = viewGroup.indexOfChild(view);
                viewGroup.removeView(view);
                if (this.indexOfChild >= 0) {
                    viewGroup.addView(frameLayout, this.indexOfChild, view.getLayoutParams());
                } else {
                    viewGroup.addView(frameLayout, view.getLayoutParams());
                }
                frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
                this.mParentView = frameLayout;
            }
        } catch (Exception unused) {
            Log.d(TAG, "Controller() called with: builder = [" + builder + "]");
        }
        this.f4065sp = this.activity.getSharedPreferences("NewbieGuide", 0);
    }

    public void show() {
        try {
            final int i = this.f4065sp.getInt(this.label, 0);
            if ((this.alwaysShow || i < this.showCounts) && !this.isShowing) {
                this.isShowing = true;
                this.mParentView.post(new Runnable() { // from class: com.app.hubert.guide.core.Controller.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Controller.this.guidePages != null && Controller.this.guidePages.size() != 0) {
                            Controller.this.current = 0;
                            Controller.this.showGuidePage();
                            if (Controller.this.onGuideChangedListener != null) {
                                Controller.this.onGuideChangedListener.onShowed(Controller.this);
                            }
                            Controller.this.addListenerFragment();
                            Controller.this.f4065sp.edit().putInt(Controller.this.label, i + 1).apply();
                            return;
                        }
                        throw new IllegalStateException("there is no guide to show!! Please add at least one Page.");
                    }
                });
            }
        } catch (Exception unused) {
            Log.d(TAG, "show() called");
        }
    }

    public void showPage(int i) {
        if (i >= 0) {
            try {
                if (i <= this.guidePages.size() - 1) {
                    if (this.current == i) {
                        return;
                    }
                    this.current = i;
                    if (this.currentLayout != null) {
                        this.currentLayout.setOnGuideLayoutDismissListener(new GuideLayout.OnGuideLayoutDismissListener() { // from class: com.app.hubert.guide.core.Controller.2
                            @Override // com.app.hubert.guide.core.GuideLayout.OnGuideLayoutDismissListener
                            public void onGuideLayoutDismiss(GuideLayout guideLayout) {
                                Controller.this.showGuidePage();
                            }
                        });
                        this.currentLayout.remove();
                        return;
                    }
                    showGuidePage();
                    return;
                }
            } catch (Exception unused) {
                Log.d(TAG, "showPage() called with: position = [" + i + "]");
                return;
            }
        }
        throw new InvalidParameterException("The Guide page position is out of range. current:" + i + ", range: [ 0, " + this.guidePages.size() + " )");
    }

    public void showPreviewPage() {
        int i = this.current - 1;
        this.current = i;
        showPage(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showGuidePage() {
        try {
            GuideLayout guideLayout = new GuideLayout(this.activity, this.guidePages.get(this.current), this);
            guideLayout.setOnGuideLayoutDismissListener(new GuideLayout.OnGuideLayoutDismissListener() { // from class: com.app.hubert.guide.core.Controller.3
                @Override // com.app.hubert.guide.core.GuideLayout.OnGuideLayoutDismissListener
                public void onGuideLayoutDismiss(GuideLayout guideLayout2) {
                    Controller.this.showNextOrRemove();
                }
            });
            this.mParentView.addView(guideLayout, new FrameLayout.LayoutParams(-1, -1));
            this.currentLayout = guideLayout;
            if (this.onPageChangedListener != null) {
                this.onPageChangedListener.onPageChanged(this.current);
            }
            this.isShowing = true;
        } catch (Exception unused) {
            Log.d(TAG, "showGuidePage() called");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNextOrRemove() {
        try {
            if (this.current < this.guidePages.size() - 1) {
                this.current++;
                showGuidePage();
                return;
            }
            if (this.onGuideChangedListener != null) {
                this.onGuideChangedListener.onRemoved(this);
            }
            removeListenerFragment();
            this.isShowing = false;
        } catch (Exception unused) {
            Log.d(TAG, "showNextOrRemove() called");
        }
    }

    public void resetLabel() {
        resetLabel(this.label);
    }

    public void resetLabel(String str) {
        this.f4065sp.edit().putInt(str, 0).apply();
    }

    public void remove() {
        try {
            if (this.currentLayout != null && this.currentLayout.getParent() != null) {
                ViewGroup viewGroup = (ViewGroup) this.currentLayout.getParent();
                viewGroup.removeView(this.currentLayout);
                if (!(viewGroup instanceof FrameLayout)) {
                    ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
                    View childAt = viewGroup.getChildAt(0);
                    viewGroup.removeAllViews();
                    if (childAt != null) {
                        if (this.indexOfChild > 0) {
                            viewGroup2.addView(childAt, this.indexOfChild, viewGroup.getLayoutParams());
                        } else {
                            viewGroup2.addView(childAt, viewGroup.getLayoutParams());
                        }
                    }
                }
                if (this.onGuideChangedListener != null) {
                    this.onGuideChangedListener.onRemoved(this);
                }
                this.currentLayout = null;
            }
            this.isShowing = false;
        } catch (Exception unused) {
            Log.d(TAG, "remove() called");
        }
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addListenerFragment() {
        try {
            if (this.fragment != null && Build.VERSION.SDK_INT > 16) {
                compatibleFragment(this.fragment);
                FragmentManager childFragmentManager = this.fragment.getChildFragmentManager();
                ListenerFragment listenerFragment = (ListenerFragment) childFragmentManager.findFragmentByTag(LISTENER_FRAGMENT);
                if (listenerFragment == null) {
                    listenerFragment = new ListenerFragment();
                    childFragmentManager.beginTransaction().add(listenerFragment, LISTENER_FRAGMENT).commitAllowingStateLoss();
                }
                listenerFragment.setFragmentLifecycle(new FragmentLifecycleAdapter() { // from class: com.app.hubert.guide.core.Controller.4
                    @Override // com.app.hubert.guide.lifecycle.FragmentLifecycleAdapter, com.app.hubert.guide.lifecycle.FragmentLifecycle
                    public void onDestroyView() {
                        LogUtil.m20451i("ListenerFragment.onDestroyView");
                        Controller.this.remove();
                    }
                });
            }
            if (this.v4Fragment == null || !this.v4Fragment.isAdded()) {
                return;
            }
            android.support.p083v4.app.FragmentManager childFragmentManager2 = this.v4Fragment.getChildFragmentManager();
            V4ListenerFragment v4ListenerFragment = (V4ListenerFragment) childFragmentManager2.findFragmentByTag(LISTENER_FRAGMENT);
            if (v4ListenerFragment == null) {
                v4ListenerFragment = new V4ListenerFragment();
                childFragmentManager2.beginTransaction().add(v4ListenerFragment, LISTENER_FRAGMENT).commitAllowingStateLoss();
            }
            v4ListenerFragment.setFragmentLifecycle(new FragmentLifecycleAdapter() { // from class: com.app.hubert.guide.core.Controller.5
                @Override // com.app.hubert.guide.lifecycle.FragmentLifecycleAdapter, com.app.hubert.guide.lifecycle.FragmentLifecycle
                public void onDestroyView() {
                    LogUtil.m20451i("v4ListenerFragment.onDestroyView");
                    Controller.this.remove();
                }
            });
        } catch (Exception unused) {
            Log.e(TAG, "addListenerFragment() called");
        }
    }

    private void removeListenerFragment() {
        try {
            if (this.fragment != null && Build.VERSION.SDK_INT > 16) {
                FragmentManager childFragmentManager = this.fragment.getChildFragmentManager();
                ListenerFragment listenerFragment = (ListenerFragment) childFragmentManager.findFragmentByTag(LISTENER_FRAGMENT);
                if (listenerFragment != null) {
                    childFragmentManager.beginTransaction().remove(listenerFragment).commitAllowingStateLoss();
                }
            }
            if (this.v4Fragment != null) {
                android.support.p083v4.app.FragmentManager childFragmentManager2 = this.v4Fragment.getChildFragmentManager();
                V4ListenerFragment v4ListenerFragment = (V4ListenerFragment) childFragmentManager2.findFragmentByTag(LISTENER_FRAGMENT);
                if (v4ListenerFragment != null) {
                    childFragmentManager2.beginTransaction().remove(v4ListenerFragment).commitAllowingStateLoss();
                }
            }
        } catch (Exception unused) {
            Log.d(TAG, "removeListenerFragment() called");
        }
    }

    private void compatibleFragment(Fragment fragment) {
        try {
            Field declaredField = Fragment.class.getDeclaredField("mChildFragmentManager");
            declaredField.setAccessible(true);
            declaredField.set(fragment, null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e2) {
            throw new RuntimeException(e2);
        }
    }
}
