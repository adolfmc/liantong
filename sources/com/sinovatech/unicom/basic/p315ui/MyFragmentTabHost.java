package com.sinovatech.unicom.basic.p315ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import java.util.ArrayList;

/* renamed from: com.sinovatech.unicom.basic.ui.MyFragmentTabHost */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyFragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {
    private String animationDirection;
    private boolean mAttached;
    private int mContainerId;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private TabInfo mLastTab;
    private TabHost.OnTabChangeListener mOnTabChangeListener;
    private FrameLayout mRealTabContent;
    private final ArrayList<TabInfo> mTabs;

    public String getAnimationDirection() {
        return this.animationDirection;
    }

    public void setAnimationDirection(String str) {
        this.animationDirection = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.MyFragmentTabHost$TabInfo */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class TabInfo {
        private final Bundle args;
        private final Class<?> clss;
        private Fragment fragment;
        private final String tag;

        TabInfo(String str, Class<?> cls, Bundle bundle) {
            this.tag = str;
            this.clss = cls;
            this.args = bundle;
        }
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.MyFragmentTabHost$DummyTabFactory */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        public DummyTabFactory(Context context) {
            this.mContext = context;
        }

        @Override // android.widget.TabHost.TabContentFactory
        public View createTabContent(String str) {
            View view = new View(this.mContext);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.MyFragmentTabHost$SavedState */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.sinovatech.unicom.basic.ui.MyFragmentTabHost.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        String curTab;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.curTab = parcel.readString();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.curTab);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.curTab + "}";
        }
    }

    public MyFragmentTabHost(Context context) {
        super(context, null);
        this.mTabs = new ArrayList<>();
        initFragmentTabHost(context, null);
    }

    public MyFragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTabs = new ArrayList<>();
        initFragmentTabHost(context, attributeSet);
    }

    private void initFragmentTabHost(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842995}, 0, 0);
        this.mContainerId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    private void ensureHierarchy(Context context) {
        if (findViewById(16908307) == null) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
            TabWidget tabWidget = new TabWidget(context);
            tabWidget.setId(16908307);
            tabWidget.setOrientation(0);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, -2, 0.0f));
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setId(16908305);
            linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(0, 0, 0.0f));
            FrameLayout frameLayout2 = new FrameLayout(context);
            this.mRealTabContent = frameLayout2;
            this.mRealTabContent.setId(this.mContainerId);
            linearLayout.addView(frameLayout2, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public void setup(Context context, FragmentManager fragmentManager) {
        ensureHierarchy(context);
        super.setup();
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        ensureContent();
    }

    public void setup(Context context, FragmentManager fragmentManager, int i) {
        ensureHierarchy(context);
        super.setup();
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mContainerId = i;
        ensureContent();
        this.mRealTabContent.setId(i);
        if (getId() == -1) {
            setId(16908306);
        }
    }

    private void ensureContent() {
        if (this.mRealTabContent == null) {
            this.mRealTabContent = (FrameLayout) findViewById(this.mContainerId);
            if (this.mRealTabContent != null) {
                return;
            }
            throw new IllegalStateException("No tab content FrameLayout found for id " + this.mContainerId);
        }
    }

    @Override // android.widget.TabHost
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.mOnTabChangeListener = onTabChangeListener;
    }

    public void addTab(TabHost.TabSpec tabSpec, Class<?> cls, Bundle bundle) {
        tabSpec.setContent(new DummyTabFactory(this.mContext));
        String tag = tabSpec.getTag();
        TabInfo tabInfo = new TabInfo(tag, cls, bundle);
        if (this.mAttached) {
            tabInfo.fragment = this.mFragmentManager.findFragmentByTag(tag);
            if (tabInfo.fragment != null && !tabInfo.fragment.isDetached()) {
                FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
                beginTransaction.detach(tabInfo.fragment);
                beginTransaction.commitAllowingStateLoss();
            }
        }
        this.mTabs.add(tabInfo);
        addTab(tabSpec);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        FragmentTransaction fragmentTransaction = null;
        for (int i = 0; i < this.mTabs.size(); i++) {
            TabInfo tabInfo = this.mTabs.get(i);
            tabInfo.fragment = this.mFragmentManager.findFragmentByTag(tabInfo.tag);
            if (tabInfo.fragment != null && !tabInfo.fragment.isDetached()) {
                if (!tabInfo.tag.equals(currentTabTag)) {
                    if (fragmentTransaction == null) {
                        fragmentTransaction = this.mFragmentManager.beginTransaction();
                    }
                    fragmentTransaction.detach(tabInfo.fragment);
                } else {
                    this.mLastTab = tabInfo;
                }
            }
        }
        this.mAttached = true;
        FragmentTransaction doTabChanged = doTabChanged(currentTabTag, fragmentTransaction);
        if (doTabChanged != null) {
            doTabChanged.commitAllowingStateLoss();
            this.mFragmentManager.executePendingTransactions();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttached = false;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.curTab = getCurrentTabTag();
        return savedState;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.curTab);
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    public void onTabChanged(String str) {
        FragmentTransaction doTabChanged;
        if (this.mAttached && (doTabChanged = doTabChanged(str, null)) != null) {
            doTabChanged.commitAllowingStateLoss();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.mOnTabChangeListener;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    private FragmentTransaction doTabChanged(String str, FragmentTransaction fragmentTransaction) {
        TabInfo tabInfo = null;
        for (int i = 0; i < this.mTabs.size(); i++) {
            TabInfo tabInfo2 = this.mTabs.get(i);
            if (tabInfo2.tag.equals(str)) {
                tabInfo = tabInfo2;
            }
        }
        if (tabInfo != null && this.mLastTab != tabInfo) {
            if (fragmentTransaction == null) {
                fragmentTransaction = this.mFragmentManager.beginTransaction();
            }
            if ("left".equals(getAnimationDirection())) {
                fragmentTransaction.setCustomAnimations(2130772098, 2130772101);
            } else if ("right".equals(getAnimationDirection())) {
                fragmentTransaction.setCustomAnimations(2130772100, 2130772099);
            } else {
                fragmentTransaction.setCustomAnimations(0, 0);
            }
            TabInfo tabInfo3 = this.mLastTab;
            if (tabInfo3 != null && tabInfo3.fragment != null) {
                fragmentTransaction.detach(this.mLastTab.fragment);
            }
            if (tabInfo != null) {
                if (tabInfo.fragment == null) {
                    tabInfo.fragment = Fragment.instantiate(this.mContext, tabInfo.clss.getName(), tabInfo.args);
                    fragmentTransaction.add(this.mContainerId, tabInfo.fragment, tabInfo.tag);
                } else {
                    fragmentTransaction.attach(tabInfo.fragment);
                }
            }
            this.mLastTab = tabInfo;
        }
        return fragmentTransaction;
    }
}
