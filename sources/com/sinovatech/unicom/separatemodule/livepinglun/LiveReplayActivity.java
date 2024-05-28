package com.sinovatech.unicom.separatemodule.livepinglun;

import android.app.ProgressDialog;
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
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.keyboard.EmojiEvent;
import com.sinovatech.unicom.separatemodule.keyboard.EmotionMainFragment;
import com.sinovatech.unicom.separatemodule.livepinglun.LiveReplayAdapter;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveReplayEntity;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveSvaeReplayEntity;
import io.reactivex.functions.Consumer;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveReplayActivity extends BaseActivity {
    public static final int Loading = 1;
    public static final int NoMoreData = 3;
    public static final int Nodata = 2;
    public static final int loadingEnd = 0;
    public static int loadingType;
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private LiveReplayAdapter adapter;
    private ImageView closeImage;
    private String commentId;
    private LinearLayout contentRootLayout;
    private int currentRepalyPosition;
    private EmotionMainFragment emotionMainFragment;
    private boolean isOnResume;
    private boolean isSlidingToLast;
    private TextView jingcaiPinglunNum;
    private LinearLayoutManager linearLayoutManager;
    List<LiveReplayEntity.ReplyListEntity> mList;
    private RecyclerView mRecylerView;
    private ManagerPinglun managerPinglun;
    private String newsId;
    private String newsTitle;

    /* renamed from: pd */
    private ProgressDialog f18550pd;
    private LinearLayout showKeyboardBtn;
    private String commentType = "00";
    public int currentPageNum = 1;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        JniLib.m15918cV(this, bundle, 91);
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

    /* renamed from: com.sinovatech.unicom.separatemodule.livepinglun.LiveReplayActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C89331 extends RecyclerView.OnScrollListener {
        C89331() {
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (i == 0 && linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.getItemCount() - 1 && LiveReplayActivity.this.isSlidingToLast) {
                LiveReplayActivity liveReplayActivity = LiveReplayActivity.this;
                liveReplayActivity.initRelayList(LiveReplayActivity.this.currentPageNum + "");
            }
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            try {
                if (i2 > 0) {
                    LiveReplayActivity.this.isSlidingToLast = true;
                } else {
                    LiveReplayActivity.this.isSlidingToLast = false;
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
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

    private void initListener() {
        this.closeImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayActivity$IAimiEvdy4D_7F93JLz-ljkiONo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LiveReplayActivity.this.activityContext.finish();
            }
        });
        this.showKeyboardBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayActivity$DA349DL8WlmubeXP7uY99uRkGAQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LiveReplayActivity.lambda$initListener$1(LiveReplayActivity.this, view);
            }
        });
        this.adapter.setHuifuClickInterface(new LiveReplayAdapter.IReplayInterface() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayActivity$iQ1-98FrJeVa3Dis9yyqmae4YTI
            @Override // com.sinovatech.unicom.separatemodule.livepinglun.LiveReplayAdapter.IReplayInterface
            public final void onHuifuClick(int i) {
                LiveReplayActivity.lambda$initListener$2(LiveReplayActivity.this, i);
            }
        });
    }

    public static /* synthetic */ void lambda$initListener$1(LiveReplayActivity liveReplayActivity, View view) {
        liveReplayActivity.currentRepalyPosition = 0;
        liveReplayActivity.commentType = "00";
        liveReplayActivity.emotionMainFragment.showSoftIntut();
        liveReplayActivity.showKeyboardBtn.setVisibility(8);
    }

    public static /* synthetic */ void lambda$initListener$2(LiveReplayActivity liveReplayActivity, int i) {
        liveReplayActivity.currentRepalyPosition = i;
        liveReplayActivity.commentType = "01";
        liveReplayActivity.emotionMainFragment.showSoftIntut();
        liveReplayActivity.showKeyboardBtn.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRelayList(final String str) {
        int i = loadingType;
        if (i == 1 || i == 2 || i == 3) {
            return;
        }
        loadingType = 1;
        this.f18550pd.show();
        this.managerPinglun.getReplayList(this.commentId, str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayActivity$GtJUMz9Rajj0aadAh7yv5T_OBe0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveReplayActivity.lambda$initRelayList$3(LiveReplayActivity.this, str, (LiveReplayEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayActivity$FtRyk4rNOfpnACpYkdLHYY3cr5g
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveReplayActivity.lambda$initRelayList$4(LiveReplayActivity.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$initRelayList$3(LiveReplayActivity liveReplayActivity, String str, LiveReplayEntity liveReplayEntity) throws Exception {
        loadingType = 0;
        liveReplayActivity.f18550pd.dismiss();
        if ("0000".equals(liveReplayEntity.getCode())) {
            List<LiveReplayEntity.ReplyListEntity> replyList = liveReplayEntity.getReplyList();
            LiveReplayEntity.ReplyListEntity replyListEntity = new LiveReplayEntity.ReplyListEntity();
            if ("1".equals(str)) {
                LiveReplayEntity.CommentDetailEntity commentDetail = liveReplayEntity.getCommentDetail();
                liveReplayActivity.newsTitle = commentDetail.getNewsTitle();
                liveReplayActivity.newsId = commentDetail.getNewsId();
                replyListEntity.setId(commentDetail.getId());
                replyListEntity.setReplyerMobile(commentDetail.getNickName());
                replyListEntity.setUploadImg(commentDetail.getUploadImg());
                replyListEntity.setReplyedHeadImg(commentDetail.getHeadPhoto());
                replyListEntity.setReplyerNick(commentDetail.getUserNickReal());
                replyListEntity.setProductImg(commentDetail.getProductImg());
                replyListEntity.setCityName(commentDetail.getCityName());
                replyListEntity.setReplyContent(commentDetail.getCommentContent());
                replyListEntity.setReplyTime(commentDetail.getCommentTime());
                replyListEntity.setRepalyNum(commentDetail.getReplyNum() + "");
                replyListEntity.setGoodFlag(commentDetail.getGoodFlag());
                replyListEntity.setPageGoodNum(commentDetail.getPageGoodNum());
                replyListEntity.setUserStar(commentDetail.getUserStar());
                replyListEntity.setCommentId(commentDetail.getId());
                replyListEntity.setIsDelete(commentDetail.getIsDelete());
                replyList.add(0, replyListEntity);
            }
            liveReplayActivity.mList.addAll(replyList);
            liveReplayActivity.adapter.update(liveReplayActivity.mList);
            liveReplayActivity.currentPageNum++;
            if (replyList.size() < 20) {
                loadingType = 3;
            }
            if (replyList.size() == 0) {
                loadingType = 2;
            }
            liveReplayActivity.jingcaiPinglunNum.setText("(" + liveReplayActivity.mList.size() + ")");
            return;
        }
        UIUtils.toast("评论加载失败");
        loadingType = 2;
    }

    public static /* synthetic */ void lambda$initRelayList$4(LiveReplayActivity liveReplayActivity, Throwable th) throws Exception {
        loadingType = 2;
        liveReplayActivity.f18550pd.dismiss();
    }

    private void uploadImage(final String str, final String str2) {
        this.managerPinglun.getUploadImage(str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayActivity$1D6t2YNCKLkYDwagNC1yxLA9z-w
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveReplayActivity.this.saveRepaly(str, new JSONObject((String) obj).optString("upLoadImgName"), str2);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayActivity$HUR73poy65WWbd9Z6N2F3007sls
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                LiveReplayActivity.this.f18550pd.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveRepaly(String str, String str2, final String str3) {
        LiveReplayEntity.ReplyListEntity replyListEntity = this.adapter.getList().get(this.currentRepalyPosition);
        this.managerPinglun.getSaveReplay(this.newsTitle, this.newsId, this.commentId, replyListEntity.getReplyerMobile(), replyListEntity.getId(), replyListEntity.getReplyerRole(), str2, replyListEntity.getReplyContent(), str, this.commentType).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayActivity$t8AINXD9JzwN1f8noXKiIjT0rGY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveReplayActivity.lambda$saveRepaly$7(LiveReplayActivity.this, str3, (LiveSvaeReplayEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayActivity$dS-ti5SYGt9HLzZbM1sB4lJV7SY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveReplayActivity.lambda$saveRepaly$8(LiveReplayActivity.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$saveRepaly$7(LiveReplayActivity liveReplayActivity, String str, LiveSvaeReplayEntity liveSvaeReplayEntity) throws Exception {
        liveReplayActivity.f18550pd.dismiss();
        if ("0000".equals(liveSvaeReplayEntity.getCode())) {
            LiveSvaeReplayEntity.ReplyDTOEntity replyDTO = liveSvaeReplayEntity.getReplyDTO();
            List<LiveReplayEntity.ReplyListEntity> list = liveReplayActivity.adapter.getList();
            LiveReplayEntity.ReplyListEntity replyListEntity = new LiveReplayEntity.ReplyListEntity();
            replyListEntity.setReplyerHeadImg(replyDTO.getReplyerHeadImg());
            replyListEntity.setReplyerNick(replyDTO.getReplyerNick());
            replyListEntity.setProductImg(replyDTO.getProductImg());
            replyListEntity.setCityName(replyDTO.getCityName());
            replyListEntity.setReplyContent(replyDTO.getReplyContent());
            replyListEntity.setReplyTime(replyDTO.getReplyTime());
            replyListEntity.setRepalyNum("0");
            replyListEntity.setGoodFlag("0");
            replyListEntity.setPageGoodNum(0);
            replyListEntity.setUploadImg(str);
            replyListEntity.setIsDelete("1");
            replyListEntity.setReplyedNick(replyDTO.getReplyedNick());
            list.add(1, replyListEntity);
            liveReplayActivity.adapter.update(list);
            liveReplayActivity.mRecylerView.smoothScrollToPosition(0);
            TextView textView = liveReplayActivity.jingcaiPinglunNum;
            textView.setText("(" + list.size() + ")");
            return;
        }
        UIUtils.toast(liveSvaeReplayEntity.getDesc());
    }

    public static /* synthetic */ void lambda$saveRepaly$8(LiveReplayActivity liveReplayActivity, Throwable th) throws Exception {
        liveReplayActivity.f18550pd.dismiss();
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
        this.mRecylerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayActivity$55qoKhRQjDQTWek52HVYBivw86w
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return LiveReplayActivity.lambda$initEmotionMainFragment$9(LiveReplayActivity.this, view, motionEvent);
            }
        });
    }

    public static /* synthetic */ boolean lambda$initEmotionMainFragment$9(LiveReplayActivity liveReplayActivity, View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && liveReplayActivity.emotionMainFragment.getEmotionIsshown()) {
            liveReplayActivity.emotionMainFragment.hideSoftIntut();
            liveReplayActivity.showKeyboardBtn.setVisibility(0);
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
            this.f18550pd.show();
            JSONObject jSONObject = new JSONObject(emojiEvent.getContent());
            String optString = jSONObject.optString("content");
            String optString2 = jSONObject.optString("image");
            if (!TextUtils.isEmpty(optString2)) {
                uploadImage(optString, "data:image/jpeg;base64," + optString2);
            } else {
                saveRepaly(optString, "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.emotionMainFragment.clearContent();
        this.emotionMainFragment.hideSoftIntut();
        this.showKeyboardBtn.setVisibility(0);
    }
}
