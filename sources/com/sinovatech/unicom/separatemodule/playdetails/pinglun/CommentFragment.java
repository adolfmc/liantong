package com.sinovatech.unicom.separatemodule.playdetails.pinglun;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity;
import com.sinovatech.unicom.separatemodule.videocenter.utils.LiuZPTLog;
import java.lang.reflect.Type;
import java.util.Map;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CommentFragment extends Fragment implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private FragmentManager childFragmentManager;
    private String comment_count;
    private String comment_url;
    private HuoShanFragment huoShanFragment;
    private LianTongFragment lianTongFragment;
    private LinearLayout ll1;
    private LiuZPTLog logUpdater;
    private TextView mBtHuoShan;
    private TextView mBtLianTong;
    private FrameLayout mFl;
    private Map<String, String> map;
    private String title;
    private String videoUrl;
    private String video_duration;
    private String video_id;

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
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.CommentFragment");
        Tracker.onResume(this);
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.CommentFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.CommentFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.CommentFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.CommentFragment", viewGroup);
        View inflate = layoutInflater.inflate(2131493122, viewGroup, false);
        initView(inflate);
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.CommentFragment");
        return inflate;
    }

    @SuppressLint({"ResourceAsColor"})
    private void initView(View view) {
        this.mBtHuoShan = (TextView) view.findViewById(2131296539);
        this.mBtLianTong = (TextView) view.findViewById(2131296541);
        this.mBtHuoShan.setOnClickListener(this);
        this.mBtLianTong.setOnClickListener(this);
        this.ll1 = (LinearLayout) view.findViewById(2131297659);
        this.ll1.setOnClickListener(this);
        this.mFl = (FrameLayout) view.findViewById(2131298208);
        this.childFragmentManager = getChildFragmentManager();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        try {
            int id = view.getId();
            if (id == 2131296539) {
                setTabSelete(0);
                this.ll1.setVisibility(0);
            } else if (id == 2131296541) {
                setTabSelete(1);
                this.ll1.setVisibility(8);
            } else if (id == 2131297659) {
                setTabSelete(1);
                new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.CommentFragment.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CommentFragment.this.lianTongFragment.ShowKeyBoard(CommentFragment.this.getActivity());
                    }
                }, 200L);
                this.ll1.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @SuppressLint({"ResourceAsColor"})
    private void setTabSelete(int i) {
        FragmentTransaction fragmentTransaction;
        try {
            FragmentTransaction beginTransaction = this.childFragmentManager.beginTransaction();
            hideFragment(beginTransaction);
            switch (i) {
                case 0:
                    this.mBtHuoShan.setBackgroundResource(2131231106);
                    this.mBtHuoShan.setTextColor(Color.parseColor("#ffff1e66"));
                    this.mBtLianTong.setTextColor(Color.parseColor("#ff666666"));
                    this.mBtLianTong.setBackground(null);
                    if (this.huoShanFragment == null) {
                        this.huoShanFragment = new HuoShanFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("comment_url", this.comment_url);
                        this.huoShanFragment.setArguments(bundle);
                        fragmentTransaction = beginTransaction;
                        fragmentTransaction.add(2131298208, this.huoShanFragment);
                    } else {
                        fragmentTransaction = beginTransaction;
                        fragmentTransaction.show(this.huoShanFragment);
                    }
                    if (this.map != null) {
                        String str = VideoDetailsActivity.bdVideo.getCurrentPosition() + "";
                        this.logUpdater.setLogByCommon(this.videoUrl, "", "视频详情", "2", "点击按钮", "19", "视频详情", "点击精评", this.map.get("storey"), this.map.get("content_id"), str, str, this.video_duration, this.map.get("tab_id"), this.map.get("tab_name"), "");
                    }
                    UIUtils.logD("评论", "setTabSelete: " + this.comment_url);
                    break;
                case 1:
                    this.mBtLianTong.setBackgroundResource(2131231106);
                    this.mBtLianTong.setTextColor(Color.parseColor("#ffff1e66"));
                    this.mBtHuoShan.setTextColor(Color.parseColor("#ff666666"));
                    this.mBtHuoShan.setBackground(null);
                    if (this.lianTongFragment == null) {
                        this.lianTongFragment = new LianTongFragment();
                        Bundle bundle2 = new Bundle();
                        if (!TextUtils.isEmpty(this.video_id) && !TextUtils.isEmpty(this.title)) {
                            bundle2.putString("id", this.video_id);
                            bundle2.putString("newsTitle", this.title);
                        }
                        this.lianTongFragment.setArguments(bundle2);
                        beginTransaction.add(2131298208, this.lianTongFragment);
                    } else {
                        beginTransaction.show(this.lianTongFragment);
                    }
                    if (this.map == null) {
                        fragmentTransaction = beginTransaction;
                        break;
                    } else {
                        this.logUpdater.setLogByCommon(this.videoUrl, "", "视频详情", "2", "点击按钮", "19", "视频详情", "点击联通评论", this.map.get("storey"), this.map.get("content_id"), "0", "0", this.video_duration, this.map.get("tab_id"), this.map.get("tab_name"), "");
                        fragmentTransaction = beginTransaction;
                        break;
                    }
                default:
                    fragmentTransaction = beginTransaction;
                    break;
            }
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        try {
            if (this.huoShanFragment != null) {
                fragmentTransaction.hide(this.huoShanFragment);
            }
            if (this.lianTongFragment != null) {
                fragmentTransaction.hide(this.lianTongFragment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(VideoDetailsEntity.DataDTO dataDTO, String str, String str2) {
        if (dataDTO == null) {
            return;
        }
        try {
            this.comment_url = dataDTO.getComment_url();
            this.comment_count = dataDTO.getComment_count();
            this.video_id = dataDTO.getGroup_id_str();
            this.title = dataDTO.getTitle();
            if (TextUtils.equals("0", this.comment_count)) {
                this.mBtLianTong.performClick();
                this.mBtHuoShan.setVisibility(8);
                this.mBtLianTong.setVisibility(8);
            } else {
                setTabSelete(0);
            }
            this.logUpdater = new LiuZPTLog((VideoDetailsActivity) getActivity());
            this.videoUrl = str;
            this.video_duration = dataDTO.getVideo().getVideo_duration() + "";
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, String>>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.CommentFragment.2
            }.getType();
            this.map = (Map) (!(gson instanceof Gson) ? gson.fromJson(str2, type) : NBSGsonInstrumentation.fromJson(gson, str2, type));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
