package com.sinovatech.unicom.separatemodule.fuchuang;

import android.content.Intent;
import android.os.Handler;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.TBProgressView;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.fuchuang.ManagerFuchuang;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerFuchuang {
    private String acId;
    private AppCompatActivity activityContext;
    private TextView bottomText;
    private ImageView closeIcon;
    private ImageView icon;
    private Disposable subscribe;
    private String taskId;
    private TBProgressView tbProgressView;
    private TextView timeText;
    private TextView topText;
    private String url;
    private RelativeLayout view;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum uptype {
        time_show,
        progress_show,
        time_complete,
        progress_complete,
        request_fail
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setUIData$9(Throwable th) throws Exception {
    }

    public ManagerFuchuang(AppCompatActivity appCompatActivity, RelativeLayout relativeLayout, String str, String str2, String str3) {
        this.activityContext = appCompatActivity;
        this.view = relativeLayout;
        initView();
        this.acId = str2;
        this.taskId = str3;
        this.url = str;
        loadData(str2, str3);
    }

    private void initView() {
        this.topText = (TextView) this.view.findViewById(2131297046);
        this.bottomText = (TextView) this.view.findViewById(2131297040);
        this.icon = (ImageView) this.view.findViewById(2131297042);
        this.timeText = (TextView) this.view.findViewById(2131297044);
        this.tbProgressView = (TBProgressView) this.view.findViewById(2131297043);
        this.closeIcon = (ImageView) this.view.findViewById(2131297041);
    }

    private void loadData(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        hashMap.put("mobile", UserManager.getInstance().getCurrentPhoneNumber());
        hashMap.put("acId", str);
        hashMap.put("taskId", str2);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getFuchuangUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.-$$Lambda$ManagerFuchuang$CfG3vBWid9rXG5HT35NUeWPyyA8
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ManagerFuchuang.HuoDongEntity dataEntity;
                dataEntity = ManagerFuchuang.this.getDataEntity((String) obj);
                return dataEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.-$$Lambda$ManagerFuchuang$GaH_mschvxRgOrS4LHA5p1QdCgw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ManagerFuchuang.this.setUIData((ManagerFuchuang.HuoDongEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.-$$Lambda$ManagerFuchuang$7_QSoPtwu6qG0AFLrkW16LQuXjU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ManagerFuchuang.lambda$loadData$2(ManagerFuchuang.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$loadData$2(ManagerFuchuang managerFuchuang, Throwable th) throws Exception {
        th.printStackTrace();
        managerFuchuang.upPv(uptype.request_fail);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUIData(final HuoDongEntity huoDongEntity) {
        if ("0000".equals(huoDongEntity.getCode())) {
            this.view.setVisibility(0);
            GlideApp.with((FragmentActivity) this.activityContext).load(huoDongEntity.getIconUrl()).into(this.icon);
            this.bottomText.setText(huoDongEntity.getTaskMsgStart());
            this.topText.setVisibility(4);
            this.closeIcon.setVisibility(8);
            this.closeIcon.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.-$$Lambda$ManagerFuchuang$J_nAV6rzo_xPBe3jd072N7LmPX4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ManagerFuchuang.this.view.setVisibility(8);
                }
            });
            if (huoDongEntity.isTime()) {
                upPv(uptype.time_show);
                this.timeText.setVisibility(0);
                this.tbProgressView.setVisibility(8);
                if (huoDongEntity.getTotleTime() > 9) {
                    TextView textView = this.timeText;
                    textView.setText("00:00:" + huoDongEntity.getTotleTime());
                } else {
                    TextView textView2 = this.timeText;
                    textView2.setText("00:00:0" + huoDongEntity.getTotleTime());
                }
            } else {
                upPv(uptype.progress_show);
                this.timeText.setVisibility(8);
                this.tbProgressView.setVisibility(0);
                this.tbProgressView.setForegroundColors(-22012, -22012);
                this.tbProgressView.setBgColors(-3701502, -3701502);
                this.tbProgressView.setProgress(0);
            }
            Disposable disposable = this.subscribe;
            if (disposable != null) {
                disposable.dispose();
            }
            this.subscribe = ((ObservableSubscribeProxy) Observable.interval(1L, 1L, TimeUnit.SECONDS).map(new Function<Long, Integer>() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.ManagerFuchuang.1
                @Override // io.reactivex.functions.Function
                public Integer apply(Long l) throws Exception {
                    return Integer.valueOf(huoDongEntity.getTotleTime() - l.intValue());
                }
            }).take(huoDongEntity.getTotleTime() + 1).subscribeOn(Schedulers.m1934io()).unsubscribeOn(Schedulers.m1934io()).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.-$$Lambda$ManagerFuchuang$PKiwv5tQ6H-wXYrmFg4_fz3z0pY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ManagerFuchuang.lambda$setUIData$8(ManagerFuchuang.this, huoDongEntity, (Integer) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.-$$Lambda$ManagerFuchuang$mkjwjJ89b7ypzIkNFUYhFwxvlVM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ManagerFuchuang.lambda$setUIData$9((Throwable) obj);
                }
            });
            return;
        }
        this.view.setVisibility(8);
        upPv(uptype.request_fail);
    }

    public static /* synthetic */ void lambda$setUIData$8(final ManagerFuchuang managerFuchuang, HuoDongEntity huoDongEntity, Integer num) throws Exception {
        if (num.intValue() >= 0) {
            if (huoDongEntity.isTime()) {
                managerFuchuang.upPv(uptype.time_complete);
                if (num.intValue() > 9) {
                    TextView textView = managerFuchuang.timeText;
                    textView.setText("00:00:" + num);
                } else {
                    TextView textView2 = managerFuchuang.timeText;
                    textView2.setText("00:00:0" + num);
                }
                if (num.intValue() == 0) {
                    managerFuchuang.bottomText.setText("已完成");
                    managerFuchuang.timeText.setText("返回领奖");
                    managerFuchuang.topText.setVisibility(0);
                    managerFuchuang.topText.setText(huoDongEntity.getTaskMsgComplete());
                    new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.-$$Lambda$ManagerFuchuang$fKIqmA9YgtjLyk_iLH-8BcSCorg
                        @Override // java.lang.Runnable
                        public final void run() {
                            ManagerFuchuang.lambda$setUIData$4(ManagerFuchuang.this);
                        }
                    }, 2400L);
                    managerFuchuang.closeIcon.setVisibility(0);
                    Intent intent = new Intent();
                    intent.putExtra("result", "0000");
                    managerFuchuang.activityContext.setResult(-1, intent);
                    managerFuchuang.timeText.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.-$$Lambda$ManagerFuchuang$bwU9rxD4p2inPDa8eL1MoyRmPmU
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ManagerFuchuang.this.activityContext.finish();
                        }
                    });
                    return;
                }
                return;
            }
            managerFuchuang.upPv(uptype.time_complete);
            int totleTime = huoDongEntity.getTotleTime();
            managerFuchuang.tbProgressView.setProgress(((totleTime - num.intValue()) * 100) / totleTime);
            if (num.intValue() == 0) {
                managerFuchuang.bottomText.setText("已完成");
                managerFuchuang.closeIcon.setVisibility(0);
                managerFuchuang.timeText.setVisibility(0);
                managerFuchuang.timeText.setText("返回领奖");
                managerFuchuang.tbProgressView.setVisibility(8);
                managerFuchuang.topText.setVisibility(0);
                managerFuchuang.topText.setText(huoDongEntity.getTaskMsgComplete());
                new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.-$$Lambda$ManagerFuchuang$BzRizlj2LlKuHy9vjlYWb3MtTp0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ManagerFuchuang.lambda$setUIData$6(ManagerFuchuang.this);
                    }
                }, 2400L);
                Intent intent2 = new Intent();
                intent2.putExtra("result", "0000");
                managerFuchuang.activityContext.setResult(-1, intent2);
                managerFuchuang.timeText.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.-$$Lambda$ManagerFuchuang$BXAyuFB2qLCHtBogi3dXfijhT7E
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ManagerFuchuang.this.activityContext.finish();
                    }
                });
            }
        }
    }

    public static /* synthetic */ void lambda$setUIData$4(ManagerFuchuang managerFuchuang) {
        try {
            managerFuchuang.topText.setVisibility(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setUIData$6(ManagerFuchuang managerFuchuang) {
        try {
            managerFuchuang.topText.setVisibility(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HuoDongEntity getDataEntity(String str) {
        JSONObject optJSONObject;
        HuoDongEntity huoDongEntity = new HuoDongEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            huoDongEntity.setCode(jSONObject.optString("code"));
            if ("0000".equals(huoDongEntity.getCode()) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                huoDongEntity.setCurrentNum(optJSONObject.optString("currentNum"));
                huoDongEntity.setIconUrl(optJSONObject.optString("iconUrl"));
                huoDongEntity.setShowType(optJSONObject.optString("showType"));
                huoDongEntity.setTaskMsgComplete(optJSONObject.optString("taskMsgComplete"));
                huoDongEntity.setTaskMsgStart(optJSONObject.optString("taskMsgStart"));
                huoDongEntity.setTotleNum(optJSONObject.optString("totleNum"));
                huoDongEntity.setTotleTime(optJSONObject.optInt("totleTime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            upPv(uptype.time_show);
        }
        return huoDongEntity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class HuoDongEntity {
        private String code;
        private String currentNum;
        private String iconUrl;
        private String showType;
        private String taskMsgComplete;
        private String taskMsgStart;
        private String totleNum;
        private int totleTime;

        private HuoDongEntity() {
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public String getIconUrl() {
            return this.iconUrl;
        }

        public void setIconUrl(String str) {
            this.iconUrl = str;
        }

        public String getShowType() {
            return this.showType;
        }

        public void setShowType(String str) {
            this.showType = str;
        }

        public String getTotleNum() {
            return this.totleNum;
        }

        public void setTotleNum(String str) {
            this.totleNum = str;
        }

        public String getCurrentNum() {
            return this.currentNum;
        }

        public void setCurrentNum(String str) {
            this.currentNum = str;
        }

        public int getTotleTime() {
            return this.totleTime;
        }

        public void setTotleTime(int i) {
            this.totleTime = i;
        }

        public String getTaskMsgStart() {
            return this.taskMsgStart;
        }

        public void setTaskMsgStart(String str) {
            this.taskMsgStart = str;
        }

        public String getTaskMsgComplete() {
            return this.taskMsgComplete;
        }

        public void setTaskMsgComplete(String str) {
            this.taskMsgComplete = str;
        }

        public boolean isTime() {
            return "1".equals(this.showType);
        }
    }

    private void upPv(uptype uptypeVar) {
        switch (uptypeVar) {
            case time_show:
                StatisticsUploadUtils.uploadRealTime(this.activityContext, "fuchuang01", this.taskId, "倒计时-展示", "0", "", this.url);
                return;
            case progress_show:
                StatisticsUploadUtils.uploadRealTime(this.activityContext, "fuchuang01", this.taskId, "进度条-展示", "0", "", this.url);
                return;
            case time_complete:
                StatisticsUploadUtils.uploadRealTime(this.activityContext, "fuchuang01", this.taskId, "倒计时-完成", "0", "", this.url);
                return;
            case progress_complete:
                StatisticsUploadUtils.uploadRealTime(this.activityContext, "fuchuang01", this.taskId, "进度条-完成", "0", "", this.url);
                return;
            case request_fail:
                StatisticsUploadUtils.uploadRealTime(this.activityContext, "fuchuang01", this.taskId, "任务失败", "0", "", this.url);
                return;
            default:
                return;
        }
    }
}
