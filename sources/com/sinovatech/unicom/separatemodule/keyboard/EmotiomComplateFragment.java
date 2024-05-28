package com.sinovatech.unicom.separatemodule.keyboard;

import android.app.Activity;
import android.os.Bundle;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class EmotiomComplateFragment extends Fragment {
    public NBSTraceUnit _nbs_trace;
    private EmotionPagerAdapter emotionPagerGvAdapter;
    private int emotion_map_type = 1;
    private EmojiIndicatorView ll_point_group;
    private ViewPager vp_complate_emotion_layout;

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.keyboard.EmotiomComplateFragment");
        Tracker.onResume(this);
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.keyboard.EmotiomComplateFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.keyboard.EmotiomComplateFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.keyboard.EmotiomComplateFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.keyboard.EmotiomComplateFragment", viewGroup);
        View inflate = layoutInflater.inflate(2131493123, viewGroup, false);
        initView(inflate);
        initListener();
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.keyboard.EmotiomComplateFragment");
        return inflate;
    }

    protected void initView(View view) {
        this.vp_complate_emotion_layout = (ViewPager) view.findViewById(2131299543);
        this.ll_point_group = (EmojiIndicatorView) view.findViewById(2131297763);
        initEmotion();
    }

    protected void initListener() {
        this.vp_complate_emotion_layout.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.separatemodule.keyboard.EmotiomComplateFragment.1
            int oldPagerPos = 0;

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                NBSActionInstrumentation.onPageSelectedEnter(i, this);
                EmotiomComplateFragment.this.ll_point_group.playByStartPointToNext(this.oldPagerPos, i);
                this.oldPagerPos = i;
                NBSActionInstrumentation.onPageSelectedExit();
            }
        });
    }

    private void initEmotion() {
        int screenWidth = UIUtils.getScreenWidth((Activity) getActivity());
        int dip2px = UIUtils.dip2px(getActivity(), 12.0f);
        int i = (screenWidth - (dip2px * 8)) / 7;
        int i2 = (i * 2) + (dip2px * 6);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("[哈哈]");
        arrayList2.add("[笑哭]");
        arrayList2.add("[奸诈]");
        arrayList2.add("[难过]");
        arrayList2.add("[色]");
        arrayList2.add("[捂脸]");
        arrayList2.add("[皱眉]");
        arrayList2.add("[发呆]");
        arrayList2.add("[坏笑]");
        arrayList2.add("[委屈]");
        arrayList2.add("[可爱]");
        arrayList2.add("[爱心]");
        arrayList2.add("[赞]");
        arrayList.add(createEmotionGridView(arrayList2, screenWidth, dip2px, i, i2));
        this.ll_point_group.initIndicator(arrayList.size());
        this.emotionPagerGvAdapter = new EmotionPagerAdapter(arrayList);
        this.vp_complate_emotion_layout.setAdapter(this.emotionPagerGvAdapter);
        this.vp_complate_emotion_layout.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, i2));
    }

    private GridView createEmotionGridView(List<String> list, int i, int i2, int i3, int i4) {
        GridView gridView = new GridView(getActivity());
        gridView.setSelector(17170445);
        gridView.setNumColumns(7);
        gridView.setPadding(i2, i2, i2, i2);
        gridView.setHorizontalSpacing(i2);
        gridView.setBackgroundColor(-1);
        gridView.setVerticalSpacing(i2 * 2);
        gridView.setLayoutParams(new ViewGroup.LayoutParams(i, i4));
        gridView.setAdapter((ListAdapter) new EmotionGridViewAdapter(getActivity(), list, i3, this.emotion_map_type));
        gridView.setOnItemClickListener(GlobalOnItemClickManagerUtils.getInstance(getActivity()).getOnItemClickListener(this.emotion_map_type));
        return gridView;
    }

    public static <T extends Fragment> T newInstance(Class cls, Bundle bundle) {
        T t;
        try {
            t = (T) cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            t = null;
            t.setArguments(bundle);
            return t;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            t = null;
            t.setArguments(bundle);
            return t;
        }
        t.setArguments(bundle);
        return t;
    }
}
