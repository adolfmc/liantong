package com.sinovatech.unicom.separatemodule.playdetails.pinglun;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.app.FragmentTransaction;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.separatemodule.keyboard.EmojiEvent;
import com.sinovatech.unicom.separatemodule.keyboard.EmotionMainFragment;
import com.sinovatech.unicom.separatemodule.livepinglun.LiveCommentAdapter;
import com.sinovatech.unicom.separatemodule.livepinglun.ManagerPinglun;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveCommentEntity;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveSaveCommentEntity;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LianTongFragment extends BaseFragment {
    public static final int Loading = 1;
    public static final int NoMoreData = 3;
    public static final int Nodata = 2;
    public static final int loadingEnd = 0;
    public static int loadingType;
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private LinearLayout contentRootLayout;
    public int currentPageNum = 1;
    private EmotionMainFragment emotionMainFragment;
    private View fragmentCacheView;

    /* renamed from: id */
    private String f18593id;
    private boolean isOnResume;
    private boolean isSlidingToLast;
    private LinearLayoutManager linearLayoutManager;
    private LiveCommentAdapter liveListAdapter;
    private List<LiveCommentEntity.CommentListBean> mList;
    private RecyclerView mRecylerView;
    private ManagerPinglun managerPinglun;
    private String newsTitle;
    private LinearLayout showKeyboardBtn;
    private String subTitle;
    private TextView tipsText;

    @Override // com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment, android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.LianTongFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.LianTongFragment");
    }

    @Override // com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
    }

    @Override // com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f18593id = arguments.getString("id");
            this.newsTitle = arguments.getString("newsTitle");
            this.subTitle = arguments.getString("subTitle");
        }
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment, android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.LianTongFragment", viewGroup);
        View view = this.fragmentCacheView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.fragmentCacheView);
            }
            View view2 = this.fragmentCacheView;
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.LianTongFragment");
            return view2;
        }
        View inflate = layoutInflater.inflate(2131493127, viewGroup, false);
        this.fragmentCacheView = inflate;
        EventBusUtils.register(this);
        initView(inflate);
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.LianTongFragment");
        return inflate;
    }

    private void initView(View view) {
        try {
            this.activityContext = (AppCompatActivity) getActivity();
            this.subTitle = TextUtils.isEmpty(this.subTitle) ? this.newsTitle : this.subTitle;
            this.tipsText = (TextView) view.findViewById(2131297661);
            this.tipsText.setVisibility(8);
            this.managerPinglun = new ManagerPinglun(this.activityContext);
            this.mRecylerView = (RecyclerView) view.findViewById(2131297662);
            this.linearLayoutManager = new LinearLayoutManager(this.activityContext);
            this.mList = new ArrayList();
            this.liveListAdapter = new LiveCommentAdapter(this.activityContext, this.mList);
            this.mRecylerView.setLayoutManager(this.linearLayoutManager);
            this.mRecylerView.setAdapter(this.liveListAdapter);
            this.mRecylerView.setOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.LianTongFragment.1
                @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (i == 0 && linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.getItemCount() - 1 && LianTongFragment.this.isSlidingToLast) {
                        LianTongFragment lianTongFragment = LianTongFragment.this;
                        lianTongFragment.loadCommentList(LianTongFragment.this.currentPageNum + "");
                    }
                }

                @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
                public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    try {
                        if (i2 > 0) {
                            LianTongFragment.this.isSlidingToLast = true;
                        } else {
                            LianTongFragment.this.isSlidingToLast = false;
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            });
            this.liveListAdapter.setHuiFuClickListener(new LiveCommentAdapter.IHuifuClickInterface() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.-$$Lambda$LianTongFragment$3es5V_VDlSPWdrX_sBBTfRHW1gk
                @Override // com.sinovatech.unicom.separatemodule.livepinglun.LiveCommentAdapter.IHuifuClickInterface
                public final void clickHuuifu() {
                    LianTongFragment.lambda$initView$0(LianTongFragment.this);
                }
            });
            this.contentRootLayout = (LinearLayout) view.findViewById(2131297637);
            this.showKeyboardBtn = (LinearLayout) view.findViewById(2131297659);
            this.showKeyboardBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.-$$Lambda$LianTongFragment$onCvcekdp_hBpCvfog74XazWGbc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    LianTongFragment.lambda$initView$1(LianTongFragment.this, view2);
                }
            });
            initEmotionMainFragment("");
            loadingType = 0;
            this.currentPageNum = 1;
            loadCommentList(this.currentPageNum + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$initView$0(LianTongFragment lianTongFragment) {
        lianTongFragment.currentPageNum = 1;
        loadingType = 0;
        lianTongFragment.mList.clear();
        lianTongFragment.loadCommentList(lianTongFragment.currentPageNum + "");
    }

    public static /* synthetic */ void lambda$initView$1(LianTongFragment lianTongFragment, View view) {
        lianTongFragment.emotionMainFragment.showSoftIntut();
        lianTongFragment.showKeyboardBtn.setVisibility(8);
    }

    public void ShowKeyBoard(FragmentActivity fragmentActivity) {
        if (fragmentActivity != null) {
            try {
                this.activityContext = (AppCompatActivity) fragmentActivity;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (this.emotionMainFragment != null) {
            this.emotionMainFragment.showSoftIntut();
            this.showKeyboardBtn.setVisibility(8);
            return;
        }
        initEmotionMainFragment("");
        this.emotionMainFragment.showSoftIntut();
        this.showKeyboardBtn.setVisibility(8);
    }

    @Override // com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.LianTongFragment");
        super.onResume();
        this.isOnResume = true;
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.LianTongFragment");
    }

    @Override // com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
        this.isOnResume = false;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStop() {
        super.onStop();
        this.isOnResume = false;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadCommentList(String str) {
        int i = loadingType;
        if (i == 1 || i == 2 || i == 3) {
            return;
        }
        loadingType = 1;
        this.managerPinglun.getCommentList(this.f18593id, str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.-$$Lambda$LianTongFragment$3CsJvLzPGh8nvO67uhSs9_9J1WE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LianTongFragment.lambda$loadCommentList$2(LianTongFragment.this, (LiveCommentEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.-$$Lambda$LianTongFragment$EYGa_JnPagHA0jWxl3Z1K51KHyY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LianTongFragment.lambda$loadCommentList$3(LianTongFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$loadCommentList$2(LianTongFragment lianTongFragment, LiveCommentEntity liveCommentEntity) throws Exception {
        loadingType = 0;
        if ("0000".equals(liveCommentEntity.getCode())) {
            List<LiveCommentEntity.CommentListBean> commentList = liveCommentEntity.getCommentList();
            lianTongFragment.mList.addAll(commentList);
            lianTongFragment.liveListAdapter.update(lianTongFragment.mList);
            lianTongFragment.setTipsVisable();
            lianTongFragment.currentPageNum++;
            if (commentList.size() < 20) {
                loadingType = 3;
            }
            if (commentList.size() == 0) {
                loadingType = 2;
            }
        }
    }

    public static /* synthetic */ void lambda$loadCommentList$3(LianTongFragment lianTongFragment, Throwable th) throws Exception {
        loadingType = 2;
        lianTongFragment.setTipsVisable();
        th.printStackTrace();
    }

    private void setTipsVisable() {
        if (this.liveListAdapter.getList().size() > 0) {
            this.tipsText.setVisibility(8);
        } else {
            this.tipsText.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveComment(String str, String str2, final String str3) {
        this.managerPinglun.getSaveComment(this.f18593id, this.newsTitle, this.subTitle, str, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.-$$Lambda$LianTongFragment$ZNwHXBxnFBi04SRblqdCKaySzYE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LianTongFragment.lambda$saveComment$4(LianTongFragment.this, str3, (LiveSaveCommentEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.-$$Lambda$LianTongFragment$8xAAdiuHRKMVoI4rYz3m4yJiLZk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ((Throwable) obj).printStackTrace();
            }
        });
    }

    public static /* synthetic */ void lambda$saveComment$4(LianTongFragment lianTongFragment, String str, LiveSaveCommentEntity liveSaveCommentEntity) throws Exception {
        if ("0000".equals(liveSaveCommentEntity.getCode())) {
            List<LiveCommentEntity.CommentListBean> list = lianTongFragment.liveListAdapter.getList();
            LiveSaveCommentEntity.CommentDetailEntity commentDetail = liveSaveCommentEntity.getCommentDetail();
            LiveCommentEntity.CommentListBean commentListBean = new LiveCommentEntity.CommentListBean();
            commentListBean.setHeadPhoto(commentDetail.getHeadPhoto());
            commentListBean.setUserNickReal(commentDetail.getUserNickReal());
            commentListBean.setProductImg(commentDetail.getProductImg());
            commentListBean.setCityName(commentDetail.getCityName());
            commentListBean.setCommentContent(commentDetail.getCommentContent());
            commentListBean.setUploadImg(str);
            commentListBean.setCommentTime("刚刚");
            commentListBean.setReplyNum(0);
            commentListBean.setGoodFlag("0");
            commentListBean.setPageGoodNum(0);
            commentListBean.setIsDelete("1");
            list.add(0, commentListBean);
            lianTongFragment.liveListAdapter.update(list);
            lianTongFragment.setTipsVisable();
            lianTongFragment.mRecylerView.smoothScrollToPosition(0);
        }
    }

    private void uploadImage(final String str, final String str2) {
        this.managerPinglun.getUploadImage(str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.-$$Lambda$LianTongFragment$gGkDOeJWCLkL9C-qriSPPpxvsM8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LianTongFragment.this.saveComment(str, new JSONObject((String) obj).optString("upLoadImgName"), str2);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.-$$Lambda$LianTongFragment$kdfrwUeh_TrjV1akmlnDzudfpSA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ((Throwable) obj).printStackTrace();
            }
        });
    }

    public void initEmotionMainFragment(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("msg", str);
        this.emotionMainFragment = (EmotionMainFragment) EmotionMainFragment.newInstance(EmotionMainFragment.class, bundle);
        this.emotionMainFragment.bindToContentView(this.contentRootLayout);
        FragmentTransaction beginTransaction = this.activityContext.getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(2131297638, this.emotionMainFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commitAllowingStateLoss();
        RecyclerView recyclerView = this.mRecylerView;
        if (recyclerView != null) {
            recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.-$$Lambda$LianTongFragment$u724z_vGkhxyvxKvxJgIp0cdzfg
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return LianTongFragment.lambda$initEmotionMainFragment$8(LianTongFragment.this, view, motionEvent);
                }
            });
        }
    }

    public static /* synthetic */ boolean lambda$initEmotionMainFragment$8(LianTongFragment lianTongFragment, View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && lianTongFragment.emotionMainFragment.getEmotionIsshown()) {
            lianTongFragment.emotionMainFragment.hideSoftIntut();
            lianTongFragment.showKeyboardBtn.setVisibility(0);
            return true;
        }
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusEvent(EmojiEvent emojiEvent) {
        if (!this.isOnResume || TextUtils.isEmpty(emojiEvent.getContent())) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(emojiEvent.getContent());
            String optString = jSONObject.optString("content");
            String optString2 = jSONObject.optString("image");
            if (!TextUtils.isEmpty(optString2)) {
                uploadImage(optString, "data:image/jpeg;base64," + optString2);
            } else {
                saveComment(optString, "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.emotionMainFragment.clearContent();
        this.emotionMainFragment.hideSoftIntut();
        this.showKeyboardBtn.setVisibility(0);
    }
}
