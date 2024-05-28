package com.sinovatech.unicom.separatemodule.messagenotification.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p083v4.app.NotificationManagerCompat;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.messagenotification.adapter.ListAdapter;
import com.sinovatech.unicom.separatemodule.messagenotification.entity.MessageNotificationEntity;
import com.sinovatech.unicom.separatemodule.messagenotification.p316ui.MyListView;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MessageNotificationActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MessageNotificationActivity";
    public NBSTraceUnit _nbs_trace;
    private ListAdapter adapter;
    private ImageButton backButton;
    private AppCompatActivity context;
    private boolean isCunZaiFirst;
    public boolean isKeDian;
    private ImageView iv_notification;
    private List<MessageNotificationEntity> list;

    /* renamed from: pd */
    private ProgressDialog f18557pd;
    private MyListView setting_listview;
    private TextView tv_notifacation;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 95);
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

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    private void initView() {
        this.isCunZaiFirst = false;
        this.isKeDian = true;
        ((TextView) findViewById(2131298800)).setText("消息通知");
        this.backButton = (ImageButton) findViewById(2131296473);
        this.backButton.setOnClickListener(this);
        this.f18557pd = new CustomePorgressDialog(this);
        this.f18557pd.setCanceledOnTouchOutside(false);
        this.setting_listview = (MyListView) findViewById(2131298063);
        this.list = new ArrayList();
        this.adapter = new ListAdapter(this, this.list, this.isCunZaiFirst, this.f18557pd);
        this.setting_listview.setAdapter((android.widget.ListAdapter) this.adapter);
        this.tv_notifacation = (TextView) findViewById(2131299030);
        this.iv_notification = (ImageView) findViewById(2131297445);
        this.iv_notification.setOnClickListener(this);
        this.tv_notifacation.setOnClickListener(this);
    }

    private void openCloseNotification() {
        try {
            if (NotificationManagerCompat.from(App.getInstance().getApplicationContext()).areNotificationsEnabled()) {
                this.tv_notifacation.setText("已开启");
            } else {
                this.tv_notifacation.setText("已关闭");
            }
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == 2131296473) {
            finish();
        } else if (id == 2131297445 || id == 2131299030) {
            gotoNotificationSetting();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        openCloseNotification();
        getMessageNotification();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    public void gotoNotificationSetting() {
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
            intent.putExtra("android.provider.extra.CHANNEL_ID", getApplicationInfo().uid);
            intent.putExtra("app_package", getPackageName());
            intent.putExtra("app_uid", getApplicationInfo().uid);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent2 = new Intent();
            intent2.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent2.setData(Uri.fromParts("package", getPackageName(), null));
            startActivity(intent2);
        }
    }

    public void setListViewHeightBasedOnChildren() {
        try {
            LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(2131493359, (ViewGroup) null);
            int i = 0;
            for (int i2 = 0; i2 < this.adapter.getCount(); i2++) {
                this.adapter.getItemViewType(i2);
                linearLayout.measure(0, 0);
                i += linearLayout.getMeasuredHeight();
            }
            ViewGroup.LayoutParams layoutParams = this.setting_listview.getLayoutParams();
            layoutParams.height = i + (this.setting_listview.getDividerHeight() * (this.adapter.getCount() - 1));
            this.setting_listview.setLayoutParams(layoutParams);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void getMessageNotification() {
        try {
            if (!SystemServiceUtils.netIsAvailable()) {
                this.list.clear();
                this.adapter.notifyDataSetChanged();
                return;
            }
            if (this.f18557pd != null) {
                this.f18557pd.setMessage("加载中");
                this.f18557pd.setCanceledOnTouchOutside(false);
            } else {
                this.f18557pd = new CustomePorgressDialog(this);
                this.f18557pd.setMessage("加载中");
                this.f18557pd.setCanceledOnTouchOutside(false);
            }
            this.f18557pd.show();
            ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getMessageNotificationList(), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, List<MessageNotificationEntity>>() { // from class: com.sinovatech.unicom.separatemodule.messagenotification.activity.MessageNotificationActivity.3
                @Override // io.reactivex.functions.Function
                public List<MessageNotificationEntity> apply(String str) throws Exception {
                    ArrayList arrayList = new ArrayList();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        int optInt = jSONObject.optInt("status");
                        String optString = jSONObject.optString("msg");
                        if (200 == optInt) {
                            JSONArray optJSONArray = jSONObject.optJSONObject("data").optJSONArray("allSettingVOList");
                            if (optJSONArray != null && optJSONArray.length() > 0) {
                                for (int i = 0; i < optJSONArray.length(); i++) {
                                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("settingList");
                                    String optString2 = optJSONObject.optString("settingType");
                                    if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                            MessageNotificationEntity messageNotificationEntity = new MessageNotificationEntity();
                                            JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                                            if (TextUtils.equals("1", optString2)) {
                                                String optString3 = optJSONObject2.optString("switchType");
                                                String optString4 = optJSONObject2.optString("viceTitle");
                                                String optString5 = optJSONObject2.optString("switchStatus");
                                                String optString6 = optJSONObject2.optString("title");
                                                messageNotificationEntity.setSwitchType(optString3);
                                                messageNotificationEntity.setViceTitle(optString4);
                                                messageNotificationEntity.setSwitchStatus(optString5);
                                                messageNotificationEntity.setTitle(optString6);
                                                messageNotificationEntity.setSettingType(optString2);
                                                MessageNotificationActivity.this.isCunZaiFirst = false;
                                            } else if (TextUtils.equals("2", optString2)) {
                                                String optString7 = optJSONObject2.optString("viceTitle");
                                                String optString8 = optJSONObject2.optString("title");
                                                String optString9 = optJSONObject2.optString("titleUrls");
                                                messageNotificationEntity.setViceTitle(optString7);
                                                messageNotificationEntity.setTitle(optString8);
                                                messageNotificationEntity.setTitleUrls(optString9);
                                                messageNotificationEntity.setSettingType(optString2);
                                            } else if (TextUtils.equals("3", optString2)) {
                                                String optString10 = optJSONObject2.optString("title");
                                                String optString11 = optJSONObject2.optString("titleUrls");
                                                messageNotificationEntity.setTitle(optString10);
                                                messageNotificationEntity.setTitleUrls(optString11);
                                                messageNotificationEntity.setSettingType(optString2);
                                            }
                                            arrayList.add(messageNotificationEntity);
                                        }
                                    }
                                }
                            }
                        } else if (99 != optInt) {
                            if (TextUtils.isEmpty(optString)) {
                                optString = "系统异常，请稍等再试～";
                            }
                            throw new Exception(optString);
                        }
                        return arrayList;
                    } catch (Exception e) {
                        UIUtils.logD(MessageNotificationActivity.TAG, e.getMessage());
                        throw new Exception("系统异常，请稍等再试～");
                    }
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this))).subscribe(new Consumer<List<MessageNotificationEntity>>() { // from class: com.sinovatech.unicom.separatemodule.messagenotification.activity.MessageNotificationActivity.1
                @Override // io.reactivex.functions.Consumer
                public void accept(List<MessageNotificationEntity> list) throws Exception {
                    MessageNotificationActivity.this.list.clear();
                    MessageNotificationActivity.this.list.addAll(list);
                    MessageNotificationActivity.this.adapter.notifyDataSetChanged();
                    if (MessageNotificationActivity.this.f18557pd == null || !MessageNotificationActivity.this.f18557pd.isShowing()) {
                        return;
                    }
                    MessageNotificationActivity.this.f18557pd.dismiss();
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.messagenotification.activity.MessageNotificationActivity.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    MessageNotificationActivity.this.list.clear();
                    if (MessageNotificationActivity.this.adapter != null) {
                        MessageNotificationActivity.this.adapter.notifyDataSetChanged();
                    }
                    if (MessageNotificationActivity.this.f18557pd == null || !MessageNotificationActivity.this.f18557pd.isShowing()) {
                        return;
                    }
                    MessageNotificationActivity.this.f18557pd.dismiss();
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }
}
