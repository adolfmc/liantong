package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.entity.ActivityTimeEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LuckyDrawResultEntity;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceInnerRecylerView;
import com.sinovatech.unicom.separatemodule.audience.view.PopupActivityDialog;
import com.sinovatech.unicom.separatemodule.audience.view.RedPacketDialog;
import com.sinovatech.unicom.separatemodule.audience.view.RedPacketNoneDialog;
import com.sinovatech.unicom.separatemodule.audience.view.RedPacketResultDialog;
import com.sinovatech.unicom.separatemodule.audience.view.animview.RedRainLayout;
import io.reactivex.functions.Consumer;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CountDownView extends FrameLayout implements RedRainLayout.ChoujiangListener {
    private String acId;
    private int acType;
    private String anchorId;
    private String anchorName;
    private Context context;
    private int countdown;
    private int endCount;
    @SuppressLint({"HandlerLeak"})
    Handler handler;
    private boolean isAttention;
    private boolean isCanChoujiang;
    private boolean isDialogHD;
    private ImageView ivImg;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private RedPacketNoneDialog noneDialog;
    private PopupActivityDialog popupDialog;
    private String popupHref;
    private String popupImg;
    private boolean preDiffTime;
    private int preTime;
    private RedPacketDialog redPacketDialog;
    private RedRainLayout redRainLayout;
    private RedPacketResultDialog resultDialog;
    private RelativeLayout rlCountDown;
    private TextView tvGuanzhu;
    private TextView tvTime;

    static /* synthetic */ int access$1310(CountDownView countDownView) {
        int i = countDownView.preTime;
        countDownView.preTime = i - 1;
        return i;
    }

    static /* synthetic */ int access$1410(CountDownView countDownView) {
        int i = countDownView.endCount;
        countDownView.endCount = i - 1;
        return i;
    }

    static /* synthetic */ int access$810(CountDownView countDownView) {
        int i = countDownView.countdown;
        countDownView.countdown = i - 1;
        return i;
    }

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CountDownView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.preTime = 0;
        this.preDiffTime = true;
        this.endCount = 0;
        this.acType = -1;
        this.handler = new HandlerC85012();
        initView(context);
    }

    public boolean getAttention() {
        return this.isAttention;
    }

    public void setAttention(boolean z) {
        this.isAttention = z;
    }

    public TextView getTvGuanzhu() {
        return this.tvGuanzhu;
    }

    public void setTvGuanzhu(TextView textView) {
        this.tvGuanzhu = textView;
    }

    public boolean isDialogHD() {
        return this.isDialogHD;
    }

    public void setDialogHD(boolean z) {
        this.isDialogHD = z;
    }

    private void initView(final Context context) {
        View inflate = LayoutInflater.from(context).inflate(2131493275, (ViewGroup) null);
        this.context = context;
        this.ivImg = (ImageView) inflate.findViewById(2131297329);
        this.tvTime = (TextView) inflate.findViewById(2131298858);
        this.rlCountDown = (RelativeLayout) inflate.findViewById(2131298371);
        this.rlCountDown.setVisibility(8);
        this.redRainLayout = (RedRainLayout) inflate.findViewById(2131298372);
        this.redPacketDialog = new RedPacketDialog(context);
        this.resultDialog = new RedPacketResultDialog(context);
        this.noneDialog = new RedPacketNoneDialog(context);
        this.managerAudienceLoadData = new ManagerAudienceLoadData((AppCompatActivity) context);
        this.redRainLayout.setChoujiangListener(this);
        this.rlCountDown.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.CountDownView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (CountDownView.this.acType == 1 && CountDownView.this.isCanChoujiang) {
                    CountDownView.this.rlCountDown.clearAnimation();
                    CountDownView.this.rlCountDown.setVisibility(8);
                    if (!CountDownView.this.getAttention()) {
                        CustomDialogManager.show((Activity) ((AudienceActivity) context), "温馨提示", "关注直播间后才可参与抽奖!", true, "取消", "关注", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.CountDownView.1.1
                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                            public void onBackKeyDown() {
                            }

                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                            public void onCancel() {
                            }

                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                            public void onClickCancel() {
                            }

                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                            public void onShow() {
                            }

                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                            public void onClickOk() {
                                try {
                                    CountDownView.this.isDialogHD = true;
                                    CountDownView.this.getTvGuanzhu().performClick();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else {
                        CountDownView countDownView = CountDownView.this;
                        countDownView.goChoujiang(countDownView.acId, CountDownView.this.acType, CountDownView.this.anchorId, CountDownView.this.anchorName);
                    }
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        addView(inflate);
    }

    public void releaseHandler() {
        this.handler.removeMessages(10001);
        this.redRainLayout.stop();
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.view.animview.CountDownView$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class HandlerC85012 extends Handler {
        HandlerC85012() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Log.i("lln", "handleMessage走了吗");
            if (message.what == 10001) {
                if (CountDownView.this.countdown > 180 || CountDownView.this.countdown <= 60) {
                    if (CountDownView.this.countdown > 60 || CountDownView.this.countdown <= 0) {
                        if (CountDownView.this.countdown == 0) {
                            if (CountDownView.this.acType == 0) {
                                if (CountDownView.this.redPacketDialog.isShowing()) {
                                    CountDownView.this.redPacketDialog.dismiss();
                                }
                                CountDownView.this.rlCountDown.setVisibility(8);
                                CountDownView.this.redRainLayout.setVisibility(0);
                                CountDownView.this.redRainLayout.startRedRainAnimation();
                            } else if (CountDownView.this.acType == 1) {
                                CountDownView.this.isCanChoujiang = true;
                                CountDownView.this.rlCountDown.setVisibility(0);
                                Message message2 = new Message();
                                message2.what = 10002;
                                CountDownView.this.handler.sendMessageDelayed(message2, 1000L);
                                CountDownView.this.rlCountDown.startAnimation(AnimationUtils.loadAnimation(CountDownView.this.context, 2130771986));
                                Log.i("CountDownView", CountDownView.this.preTime + "宝箱====" + CountDownView.this.endCount);
                            } else {
                                if (CountDownView.this.popupDialog == null) {
                                    CountDownView countDownView = CountDownView.this;
                                    countDownView.popupDialog = new PopupActivityDialog(countDownView.context, CountDownView.this.popupImg, new PopupActivityDialog.IPopupListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.CountDownView.2.1
                                        @Override // com.sinovatech.unicom.separatemodule.audience.view.PopupActivityDialog.IPopupListener
                                        public void callback() {
                                            if (!TextUtils.isEmpty(CountDownView.this.popupHref)) {
                                                AudienceActivity.IntentGo((AppCompatActivity) CountDownView.this.context, CountDownView.this.popupHref);
                                            } else if (!CountDownView.this.getAttention()) {
                                                CustomDialogManager.show((Activity) ((AudienceActivity) CountDownView.this.context), "温馨提示", "关注直播间后才可参与抽奖!", true, "取消", "关注", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.CountDownView.2.1.1
                                                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                                                    public void onBackKeyDown() {
                                                    }

                                                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                                                    public void onCancel() {
                                                    }

                                                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                                                    public void onClickCancel() {
                                                    }

                                                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                                                    public void onShow() {
                                                    }

                                                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                                                    public void onClickOk() {
                                                        try {
                                                            CountDownView.this.isDialogHD = true;
                                                            CountDownView.this.getTvGuanzhu().performClick();
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                });
                                            } else {
                                                CountDownView.this.goChoujiang(CountDownView.this.acId, CountDownView.this.acType, CountDownView.this.anchorId, CountDownView.this.anchorName);
                                            }
                                        }
                                    });
                                }
                                CountDownView.this.popupDialog.setImg(CountDownView.this.popupImg);
                                CountDownView.this.popupDialog.show();
                            }
                            CountDownView.this.tvTime.setText("点我抽奖");
                        }
                    } else {
                        if (CountDownView.this.acType == 0) {
                            if (!CountDownView.this.redPacketDialog.isShowing() && !CountDownView.this.redPacketDialog.isClosed()) {
                                CountDownView.this.redPacketDialog.setCountdown(CountDownView.this.countdown);
                                CountDownView.this.redPacketDialog.show();
                            }
                        } else {
                            int unused = CountDownView.this.acType;
                        }
                        TextView textView = CountDownView.this.tvTime;
                        textView.setText(CountDownView.this.countdown + "s");
                    }
                } else {
                    TextView textView2 = CountDownView.this.tvTime;
                    textView2.setText(CountDownView.this.countdown + "s");
                }
                CountDownView.access$810(CountDownView.this);
                CountDownView.access$1310(CountDownView.this);
                if (!CountDownView.this.preDiffTime && CountDownView.this.preTime == 0 && CountDownView.this.acType != 2) {
                    CountDownView.this.rlCountDown.setVisibility(0);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(CountDownView.this.preTime);
                sb.append("到了预热时间");
                sb.append(!CountDownView.this.preDiffTime);
                Log.i("CountDownView", sb.toString());
                if (CountDownView.this.countdown >= 0) {
                    Message message3 = new Message();
                    message3.what = 10001;
                    CountDownView.this.handler.sendMessageDelayed(message3, 1000L);
                }
            } else if (message.what == 10002) {
                if (CountDownView.this.endCount <= 1) {
                    CountDownView.this.rlCountDown.clearAnimation();
                    CountDownView.this.rlCountDown.setVisibility(8);
                    CountDownView.this.handler.removeMessages(10002);
                }
                CountDownView.access$1410(CountDownView.this);
                Log.i("CountDownView", CountDownView.this.preTime + "宝箱----->" + CountDownView.this.endCount);
                if (CountDownView.this.endCount >= 0) {
                    Message message4 = new Message();
                    message4.what = 10002;
                    CountDownView.this.handler.sendMessageDelayed(message4, 1000L);
                }
            }
        }
    }

    public void setJsonData(String str, String str2, ActivityTimeEntity activityTimeEntity) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.anchorId = str;
        this.anchorName = str2;
        if (activityTimeEntity != null) {
            try {
                int i = 8;
                if (activityTimeEntity.getRespCode().equals("0000")) {
                    this.endCount = (int) ((activityTimeEntity.getEndTime().longValue() - activityTimeEntity.getBeginTime().longValue()) / 1000);
                    if (activityTimeEntity.getIfPartakeActivity().equals("NO")) {
                        this.acId = activityTimeEntity.getAcId();
                        long currentTimeMillis = System.currentTimeMillis();
                        if (activityTimeEntity.getAcType().equals("0")) {
                            this.acType = 0;
                            this.ivImg.setImageResource(2131231510);
                        } else if (activityTimeEntity.getAcType().equals("1")) {
                            this.acType = 1;
                            this.ivImg.setImageResource(2131231509);
                        } else if (activityTimeEntity.getAcType().equals("2")) {
                            this.acType = 2;
                            this.popupImg = activityTimeEntity.getPopupActivityImg();
                            this.popupHref = activityTimeEntity.getPopupActivityHref();
                            this.rlCountDown.setVisibility(8);
                        }
                        if (currentTimeMillis < activityTimeEntity.getBeginTime().longValue()) {
                            if (currentTimeMillis > activityTimeEntity.getPreheatTime().longValue()) {
                                RelativeLayout relativeLayout = this.rlCountDown;
                                if (this.acType != 2) {
                                    i = 0;
                                }
                                relativeLayout.setVisibility(i);
                            } else {
                                this.preTime = ((int) (activityTimeEntity.getPreheatTime().longValue() - currentTimeMillis)) / 1000;
                                this.preDiffTime = false;
                            }
                            this.countdown = (int) ((activityTimeEntity.getBeginTime().longValue() - currentTimeMillis) / 1000);
                            Message message = new Message();
                            message.what = 10001;
                            message.obj = Integer.valueOf(this.countdown);
                            this.handler.removeMessages(message.what);
                            this.handler.sendMessageDelayed(message, 1000L);
                            if (activityTimeEntity.getAcType().equals("0") && !TextUtils.isEmpty(activityTimeEntity.getDurationTime())) {
                                this.redRainLayout.setTotalAccount(Integer.valueOf(activityTimeEntity.getDurationTime()).intValue() * 60 * 2);
                            }
                            if (this.acType != 2) {
                                this.tvTime.setText(LongToDate(activityTimeEntity.getBeginTime().longValue()) + "开奖");
                                return;
                            }
                            return;
                        } else if (currentTimeMillis >= activityTimeEntity.getBeginTime().longValue() && currentTimeMillis < activityTimeEntity.getEndTime().longValue()) {
                            if (activityTimeEntity.getAcType().equals("0") && !TextUtils.isEmpty(activityTimeEntity.getDurationTime())) {
                                this.redRainLayout.setTotalAccount(Integer.valueOf(activityTimeEntity.getDurationTime()).intValue() * 60 * 2);
                            }
                            this.countdown = 0;
                            Message message2 = new Message();
                            message2.what = 10001;
                            this.handler.removeMessages(message2.what);
                            this.handler.sendMessage(message2);
                            return;
                        } else if (currentTimeMillis > activityTimeEntity.getEndTime().longValue()) {
                            Log.i("lln", "来晚了，活动已结束");
                            this.rlCountDown.setVisibility(8);
                            return;
                        } else {
                            return;
                        }
                    }
                    this.rlCountDown.setVisibility(8);
                    return;
                }
                this.rlCountDown.setVisibility(8);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String LongToDate(long j) {
        return new SimpleDateFormat(JtDateUtil.dateFormatHM).format(new Date(Long.parseLong(String.valueOf(j))));
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.view.animview.RedRainLayout.ChoujiangListener
    public void requestChoujiang() {
        AudienceInnerRecylerView.isInnerRecylerView = false;
        Log.d("requestChoujiang", "红包雨开始抽奖");
        if (!getAttention()) {
            CustomDialogManager.show((Activity) ((AudienceActivity) this.context), "温馨提示", "关注直播间后才可参与抽奖!", true, "取消", "关注", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.CountDownView.3
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onBackKeyDown() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onShow() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickOk() {
                    try {
                        CountDownView.this.isDialogHD = true;
                        CountDownView.this.getTvGuanzhu().performClick();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            goChoujiang(this.acId, this.acType, this.anchorId, this.anchorName);
        }
    }

    public void goGZchoujiang() {
        goChoujiang(this.acId, this.acType, this.anchorId, this.anchorName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goChoujiang(String str, int i, String str2, String str3) {
        if (TextUtils.isEmpty(str) || i == -1 || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        this.isCanChoujiang = false;
        this.managerAudienceLoadData.getLuckyDrawResult(str, this.acType, str2, str3).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.-$$Lambda$CountDownView$SRbWx_haNVTNgMqrLG9qf6uSyLM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                CountDownView.lambda$goChoujiang$0(CountDownView.this, (LuckyDrawResultEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.-$$Lambda$CountDownView$ijFFXvvCp2RtigaL79say0zk1Po
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                CountDownView.lambda$goChoujiang$1(CountDownView.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$goChoujiang$0(CountDownView countDownView, LuckyDrawResultEntity luckyDrawResultEntity) throws Exception {
        Log.i("lln", "抽奖结果==" + luckyDrawResultEntity.toString());
        countDownView.isDialogHD = false;
        countDownView.changeResult(luckyDrawResultEntity);
    }

    public static /* synthetic */ void lambda$goChoujiang$1(CountDownView countDownView, Throwable th) throws Exception {
        countDownView.isDialogHD = false;
        th.printStackTrace();
        Log.i("lln", "抽奖结果错误==" + th.getMessage());
    }

    private void changeResult(LuckyDrawResultEntity luckyDrawResultEntity) {
        if (luckyDrawResultEntity != null) {
            if (luckyDrawResultEntity.getRespCode().equals("0000")) {
                this.resultDialog.setName(luckyDrawResultEntity.getPrizeName()).setImg(luckyDrawResultEntity.getPrizeImg()).setInfo(luckyDrawResultEntity.getReceivePrompt()).show();
                return;
            }
            String respCode = luckyDrawResultEntity.getRespCode();
            String str = "未中奖，再接再厉吧~";
            int i = this.acType;
            if (i == 0) {
                if (respCode.equals("0001")) {
                    str = "已参与过此活动，欢迎下次再来";
                } else if (respCode.equals("0002")) {
                    str = "黑名单号码暂不支持";
                } else if (respCode.equals("0003")) {
                    str = "操作过于频繁，请稍候再试";
                } else if (respCode.equals("0004")) {
                    str = "本轮红包已抢完，欢迎下次再来";
                } else if (respCode.equals("0005")) {
                    str = "本轮红包已结束，欢迎下次再来";
                } else if (respCode.equals("0006")) {
                    str = "您已领取红包，可去【我的】-【我的账户】查询";
                } else if (respCode.equals("0007")) {
                    str = "系统繁忙，请稍候再试";
                } else if (respCode.equals("1000")) {
                    str = "未中奖，再接再厉吧~";
                } else if (respCode.equals("9999")) {
                    str = "出了一点儿小问题，请稍候再试";
                }
            } else if (i == 1) {
                if (respCode.equals("0001")) {
                    str = "已参与过此活动，欢迎下次再来";
                } else if (respCode.equals("0002")) {
                    str = "黑名单号码暂不支持";
                } else if (respCode.equals("0003")) {
                    str = "操作过于频繁，请稍候再试";
                } else if (respCode.equals("0004")) {
                    str = "操作过于频繁，请稍候再试";
                } else if (respCode.equals("0005")) {
                    str = "本轮奖品已结束，欢迎下次再来";
                } else if (respCode.equals("0006")) {
                    str = "您已领取奖品，可去【我的】-【我的账户】查询";
                } else if (respCode.equals("0007")) {
                    str = "系统繁忙，请稍候再试";
                } else if (respCode.equals("1000")) {
                    str = "未中奖，再接再厉吧~";
                } else if (respCode.equals("9999")) {
                    str = "出了一点儿小问题，请稍候再试";
                }
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.noneDialog.setMsg(str).show();
        }
    }
}
