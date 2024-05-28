package com.sinovatech.unicom.separatemodule.livepinglun;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentTransaction;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.keyboard.EmojiEvent;
import com.sinovatech.unicom.separatemodule.keyboard.EmotionMainFragment;
import com.sinovatech.unicom.separatemodule.livepinglun.LiveCommentAdapter;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveCommentEntity;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveSaveCommentEntity;
import io.reactivex.functions.Consumer;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveCommentActivity extends BaseActivity {
    public static final int Loading = 1;
    public static final int NoMoreData = 3;
    public static final int Nodata = 2;
    public static final int loadingEnd = 0;
    public static int loadingType;
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private ImageView closeImage;
    private LinearLayout contentRootLayout;
    public int currentPageNum = 1;
    private EmotionMainFragment emotionMainFragment;

    /* renamed from: id */
    private String f18548id;
    private boolean isOnResume;
    private boolean isSlidingToLast;
    private LinearLayoutManager linearLayoutManager;
    private LiveCommentAdapter liveListAdapter;
    private List<LiveCommentEntity.CommentListBean> mList;
    private RecyclerView mRecylerView;
    private ManagerPinglun managerPinglun;
    private String newsTitle;
    private TextView numText;

    /* renamed from: pd */
    private ProgressDialog f18549pd;
    private LinearLayout showKeyboardBtn;
    private String subTitle;
    private TextView tipsText;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        JniLib.m15918cV(this, bundle, 89);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.livepinglun.LiveCommentActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C89301 implements LiveCommentAdapter.IHuifuDelInterface {
        C89301() {
        }

        @Override // com.sinovatech.unicom.separatemodule.livepinglun.LiveCommentAdapter.IHuifuDelInterface
        public void delHuifu() {
            if (LiveCommentActivity.this.numText != null) {
                int i = 0;
                try {
                    i = LiveCommentActivity.this.liveListAdapter.getList().size();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                TextView textView = LiveCommentActivity.this.numText;
                textView.setText("(" + i + ")");
                LiveCommentActivity.this.setTipsVisable();
            }
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.livepinglun.LiveCommentActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C89312 extends RecyclerView.OnScrollListener {
        C89312() {
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (i == 0 && linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.getItemCount() - 1 && LiveCommentActivity.this.isSlidingToLast) {
                LiveCommentActivity liveCommentActivity = LiveCommentActivity.this;
                liveCommentActivity.loadCommentList(LiveCommentActivity.this.currentPageNum + "");
            }
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            try {
                if (i2 > 0) {
                    LiveCommentActivity.this.isSlidingToLast = true;
                } else {
                    LiveCommentActivity.this.isSlidingToLast = false;
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static /* synthetic */ void lambda$onCreate$1(LiveCommentActivity liveCommentActivity) {
        liveCommentActivity.currentPageNum = 1;
        loadingType = 0;
        liveCommentActivity.mList.clear();
        liveCommentActivity.loadCommentList(liveCommentActivity.currentPageNum + "");
    }

    public static /* synthetic */ void lambda$onCreate$2(LiveCommentActivity liveCommentActivity, View view) {
        liveCommentActivity.emotionMainFragment.showSoftIntut();
        liveCommentActivity.showKeyboardBtn.setVisibility(8);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.isOnResume = true;
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.isOnResume = false;
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        this.isOnResume = false;
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
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
        this.f18549pd.show();
        this.managerPinglun.getCommentList(this.f18548id, str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentActivity$2VO4um2MuhXfmqa4mlbgNGcrFhg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveCommentActivity.lambda$loadCommentList$4(LiveCommentActivity.this, (LiveCommentEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentActivity$Cg8en7zAV5xRPCYVo0F7tuqArkY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveCommentActivity.lambda$loadCommentList$5(LiveCommentActivity.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$loadCommentList$4(LiveCommentActivity liveCommentActivity, LiveCommentEntity liveCommentEntity) throws Exception {
        loadingType = 0;
        liveCommentActivity.f18549pd.dismiss();
        if ("0000".equals(liveCommentEntity.getCode())) {
            List<LiveCommentEntity.CommentListBean> commentList = liveCommentEntity.getCommentList();
            liveCommentActivity.mList.addAll(commentList);
            liveCommentActivity.liveListAdapter.update(liveCommentActivity.mList);
            TextView textView = liveCommentActivity.numText;
            textView.setText("(" + liveCommentEntity.getCommentList().size() + ")");
            liveCommentActivity.setTipsVisable();
            liveCommentActivity.currentPageNum = liveCommentActivity.currentPageNum + 1;
            if (commentList.size() < 20) {
                loadingType = 3;
            }
            if (commentList.size() == 0) {
                loadingType = 2;
            }
        }
    }

    public static /* synthetic */ void lambda$loadCommentList$5(LiveCommentActivity liveCommentActivity, Throwable th) throws Exception {
        loadingType = 2;
        liveCommentActivity.setTipsVisable();
        liveCommentActivity.f18549pd.dismiss();
        th.printStackTrace();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTipsVisable() {
        if (this.liveListAdapter.getList().size() > 0) {
            this.tipsText.setVisibility(8);
        } else {
            this.tipsText.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveComment(String str, String str2, final String str3) {
        this.managerPinglun.getSaveComment(this.f18548id, this.newsTitle, this.subTitle, str, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentActivity$OIBW88LqBWlyiKawrfY3qzJSRfc
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveCommentActivity.lambda$saveComment$6(LiveCommentActivity.this, str3, (LiveSaveCommentEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentActivity$4bEfjx0YNLdFFiWw0nbZFrD9Mds
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveCommentActivity.lambda$saveComment$7(LiveCommentActivity.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$saveComment$6(LiveCommentActivity liveCommentActivity, String str, LiveSaveCommentEntity liveSaveCommentEntity) throws Exception {
        if ("0000".equals(liveSaveCommentEntity.getCode())) {
            List<LiveCommentEntity.CommentListBean> list = liveCommentActivity.liveListAdapter.getList();
            LiveSaveCommentEntity.CommentDetailEntity commentDetail = liveSaveCommentEntity.getCommentDetail();
            LiveCommentEntity.CommentListBean commentListBean = new LiveCommentEntity.CommentListBean();
            commentListBean.setId(commentDetail.getId());
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
            liveCommentActivity.liveListAdapter.update(list);
            liveCommentActivity.setTipsVisable();
            liveCommentActivity.mRecylerView.smoothScrollToPosition(0);
            TextView textView = liveCommentActivity.numText;
            textView.setText("(" + list.size() + ")");
        }
        liveCommentActivity.f18549pd.dismiss();
    }

    public static /* synthetic */ void lambda$saveComment$7(LiveCommentActivity liveCommentActivity, Throwable th) throws Exception {
        liveCommentActivity.f18549pd.dismiss();
        th.printStackTrace();
    }

    private void uploadImage(final String str, final String str2) {
        this.managerPinglun.getUploadImage(str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentActivity$RklAF_mywplIABcHhX-Kng9q7Dg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveCommentActivity.this.saveComment(str, new JSONObject((String) obj).optString("upLoadImgName"), str2);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentActivity$4PynPa4ObrIU_KKPdu6UuOVF1aE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveCommentActivity.lambda$uploadImage$9(LiveCommentActivity.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$uploadImage$9(LiveCommentActivity liveCommentActivity, Throwable th) throws Exception {
        liveCommentActivity.f18549pd.dismiss();
        th.printStackTrace();
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
        this.mRecylerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentActivity$gEEqTooZJkeHFniXr7X6aTkXGtA
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return LiveCommentActivity.lambda$initEmotionMainFragment$10(LiveCommentActivity.this, view, motionEvent);
            }
        });
    }

    public static /* synthetic */ boolean lambda$initEmotionMainFragment$10(LiveCommentActivity liveCommentActivity, View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && liveCommentActivity.emotionMainFragment.getEmotionIsshown()) {
            liveCommentActivity.emotionMainFragment.hideSoftIntut();
            liveCommentActivity.showKeyboardBtn.setVisibility(0);
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
            this.f18549pd.show();
            if (!TextUtils.isEmpty(optString2)) {
                uploadImage(optString, "data:image/jpeg;base64," + optString2);
                PvCurrencyLogUtils.pvLogLive("", 2, "", "评论-发送(" + optString + ")", "视频", "", "视频");
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

    public static void initActivity(Activity activity, String str, String str2, String str3) {
        Intent intent = new Intent(activity, LiveCommentActivity.class);
        intent.putExtra("id", str);
        intent.putExtra("newsTitle", str2);
        intent.putExtra("subTitle", str3);
        activity.startActivity(intent);
    }
}
