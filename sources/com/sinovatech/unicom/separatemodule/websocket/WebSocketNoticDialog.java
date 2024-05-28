package com.sinovatech.unicom.separatemodule.websocket;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.eventbus.NoticEvent;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.notice.LogStatistic;
import com.sinovatech.unicom.separatemodule.notice.NoticMainActivity;
import com.sinovatech.unicom.separatemodule.notice.PushMessageEntity;
import com.sinovatech.unicom.separatemodule.notice.PushMsgDao;
import com.sinovatech.unicom.separatemodule.notice.PushServer;
import com.sinovatech.unicom.separatemodule.notice.utils.Message;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WebSocketNoticDialog {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface clickInterface {
        void click();
    }

    public static void insertGameMesssage(String str, String str2, String str3, String str4) {
        PushMessageEntity pushMessageEntity = new PushMessageEntity();
        pushMessageEntity.setDate(System.currentTimeMillis() + "");
        pushMessageEntity.setEndTime(System.currentTimeMillis() + "");
        pushMessageEntity.setTitle(str);
        pushMessageEntity.setContent(str2);
        pushMessageEntity.setMsgType(str4);
        pushMessageEntity.setUrl(str3);
        pushMessageEntity.setId(System.currentTimeMillis() + "");
        pushMessageEntity.setUserMobile("0");
        pushMessageEntity.setNewMsgType("");
        PushMsgDao pushMsgDao = new PushMsgDao(App.getInstance());
        if (!pushMsgDao.getPushMessageRecordExist(pushMessageEntity)) {
            pushMsgDao.insertPushMessageRecord(pushMessageEntity);
        }
        show(WebSocketManager.PUSHLOCALGAME, pushMessageEntity, false);
    }

    public static void show(String str, final PushMessageEntity pushMessageEntity, final boolean z) {
        int i;
        final Activity topActivity = SoulPermission.getInstance().getTopActivity();
        final Dialog dialog = new Dialog(topActivity, 2131952240);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        Window window = dialog.getWindow();
        window.setWindowAnimations(2131952267);
        window.setGravity(48);
        window.setAttributes(attributes);
        View inflate = topActivity.getLayoutInflater().inflate(2131493551, (ViewGroup) null);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131299584);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(2131299587);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(2131299583);
        if (WebSocketManager.PUSHYINGYETING.equals(str) || WebSocketManager.PUSHLOCALGAME.equals(str)) {
            TextView textView = (TextView) inflate.findViewById(2131299586);
            TextView textView2 = (TextView) inflate.findViewById(2131299585);
            TextView textView3 = (TextView) inflate.findViewById(2131299581);
            TextView textView4 = (TextView) inflate.findViewById(2131299856);
            ImageView imageView = (ImageView) inflate.findViewById(2131299582);
            if ("true".equals(pushMessageEntity.getBannerState())) {
                textView4.setVisibility(0);
                textView3.setMaxLines(1);
            } else {
                textView4.setVisibility(8);
                textView3.setMaxLines(2);
            }
            if ("T1".equals(pushMessageEntity.getMsgType())) {
                imageView.setImageResource(2131232001);
            } else if ("T2".equals(pushMessageEntity.getMsgType())) {
                imageView.setImageResource(2131232002);
            } else if ("T4".equals(pushMessageEntity.getMsgType())) {
                imageView.setImageResource(2131231996);
            }
            textView.setText(pushMessageEntity.getTitle());
            textView3.setText(pushMessageEntity.getContent());
            try {
                Integer.parseInt(pushMessageEntity.getDate());
                System.currentTimeMillis();
                textView2.setText("现在");
            } catch (Exception e) {
                e.printStackTrace();
            }
            linearLayout2.setVisibility(0);
            i = 8;
            linearLayout3.setVisibility(8);
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.websocket.WebSocketNoticDialog.1
                /* JADX WARN: Type inference failed for: r5v7, types: [com.sinovatech.unicom.separatemodule.websocket.WebSocketNoticDialog$1$1] */
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    IntentManager.generateIntentAndGo(topActivity, pushMessageEntity.getUrl(), pushMessageEntity.getTitle(), true, "get");
                    dialog.cancel();
                    LogStatistic.sendPushClickLog(topActivity, pushMessageEntity.getId());
                    EventBusUtils.post(new NoticEvent(EventBusUtils.EVENT_NOTIC));
                    try {
                        new Thread() { // from class: com.sinovatech.unicom.separatemodule.websocket.WebSocketNoticDialog.1.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() {
                                super.run();
                                try {
                                    Message message = new Message();
                                    message.setId(pushMessageEntity.getId());
                                    message.setMsgType("0");
                                    message.setUrl(pushMessageEntity.getUrl());
                                    new PushServer(topActivity).sendPushMsgReadStatusLog(message);
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }.start();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    new PushMsgDao(topActivity).updatePushMessageRecordStatus(pushMessageEntity, "1");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.websocket.WebSocketNoticDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    linearLayout.performClick();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } else {
            i = 8;
        }
        if (WebSocketManager.PUSHKEFUXIAOXI.equals(str)) {
            linearLayout2.setVisibility(i);
            linearLayout3.setVisibility(0);
            TextView textView5 = (TextView) inflate.findViewById(2131298441);
            TextView textView6 = (TextView) inflate.findViewById(2131298444);
            TextView textView7 = (TextView) inflate.findViewById(2131298443);
            TextView textView8 = (TextView) inflate.findViewById(2131298439);
            final TextView textView9 = (TextView) inflate.findViewById(2131298442);
            GlideApp.with(topActivity).load(pushMessageEntity.getSaleImgUrl()).placeholder(2131623943).error(2131623943).into((ImageView) inflate.findViewById(2131298440));
            String title = pushMessageEntity.getTitle();
            if (TextUtils.isEmpty(title)) {
                title = "";
            }
            textView5.setText(title);
            textView8.setText(pushMessageEntity.getContent());
            String msgType = pushMessageEntity.getMsgType();
            if (TextUtils.equals("T5", msgType)) {
                textView6.setVisibility(0);
                textView6.setText("业务经理");
            } else if (TextUtils.equals("T6", msgType)) {
                textView6.setVisibility(0);
                textView6.setText("智家工程师");
            } else {
                textView6.setVisibility(8);
            }
            textView9.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.websocket.-$$Lambda$WebSocketNoticDialog$Vw99mIpoCJnM2XNhhQIUCG8lygM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WebSocketNoticDialog.lambda$show$0(z, topActivity, pushMessageEntity, view);
                }
            });
            textView8.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.websocket.-$$Lambda$WebSocketNoticDialog$Juuc2RkfTyeOQXyUaaMW8NWn-lg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    textView9.performClick();
                }
            });
            StatisticsUploadUtils.upload("S2ndpage1012", "站内信消息", "消息展示", pushMessageEntity.getId(), "横幅提醒弹窗", pushMessageEntity.getSaleDialogUrl());
        }
        dialog.setContentView(inflate);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.separatemodule.websocket.WebSocketNoticDialog.3
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                dialogInterface.cancel();
                return false;
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(UIUtils.getScreenWidth(topActivity), -2);
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.websocket.WebSocketNoticDialog.4
            @Override // java.lang.Runnable
            public void run() {
                Dialog dialog2;
                Activity activity = topActivity;
                if (activity == null || activity.isFinishing() || (dialog2 = dialog) == null || !dialog2.isShowing()) {
                    return;
                }
                dialog.dismiss();
            }
        }, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$0(boolean z, Activity activity, PushMessageEntity pushMessageEntity, View view) {
        if (z) {
            NoticMainActivity.gotoNotice(activity, 2);
        } else {
            IntentManager.generateIntentAndGo(activity, pushMessageEntity.getSaleDialogUrl());
        }
        StatisticsUploadUtils.upload("S2ndpage1012", "站内信消息", "消息点击", pushMessageEntity.getId(), "横幅提醒弹窗", pushMessageEntity.getSaleDialogUrl());
    }
}
