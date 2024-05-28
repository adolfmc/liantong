package com.sinovatech.unicom.separatemodule.messagenotification.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.activity.LoginActivity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.messagenotification.activity.MessageNotificationActivity;
import com.sinovatech.unicom.separatemodule.messagenotification.entity.MessageNotificationEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ListAdapter extends BaseAdapter {
    private Activity context;
    private boolean isCunZaiFirst;
    private List<MessageNotificationEntity> list;

    /* renamed from: pd */
    private ProgressDialog f18558pd;
    private String msg = "";
    private String data = "";

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public ListAdapter(Activity activity, List<MessageNotificationEntity> list, boolean z, ProgressDialog progressDialog) {
        this.context = activity;
        this.list = list;
        this.isCunZaiFirst = z;
        this.f18558pd = progressDialog;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<MessageNotificationEntity> list = this.list;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        final MessageNotificationEntity messageNotificationEntity = this.list.get(i);
        final String settingType = messageNotificationEntity.getSettingType();
        final ViewHodler viewHodler = new ViewHodler();
        if (TextUtils.equals("1", settingType) || TextUtils.equals("2", settingType)) {
            inflate = this.context.getLayoutInflater().inflate(2131493359, (ViewGroup) null);
            viewHodler.viceTitle = (TextView) inflate.findViewById(2131299504);
            viewHodler.top_lin = (LinearLayout) inflate.findViewById(2131298817);
            viewHodler.switchStatus = (ToggleButton) inflate.findViewById(2131298723);
            if (TextUtils.equals("1", settingType)) {
                viewHodler.switchStatus.setVisibility(0);
                viewHodler.switchStatus.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.messagenotification.adapter.ListAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        NBSActionInstrumentation.onClickEventEnter(view2, this);
                        Tracker.onClick(view2);
                        if (App.hasLogined()) {
                            if (((MessageNotificationActivity) ListAdapter.this.context).isKeDian) {
                                ((MessageNotificationActivity) ListAdapter.this.context).isKeDian = false;
                                ListAdapter.this.openSwitch(viewHodler, messageNotificationEntity);
                            }
                        } else {
                            ListAdapter.this.context.startActivity(new Intent(ListAdapter.this.context, LoginActivity.class));
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                viewHodler.switchStatus.setVisibility(8);
            }
        } else {
            inflate = this.context.getLayoutInflater().inflate(2131493360, (ViewGroup) null);
            viewHodler.bottomLineView = inflate.findViewById(2131297325);
        }
        viewHodler.all_layout = (LinearLayout) inflate.findViewById(2131296332);
        viewHodler.title = (TextView) inflate.findViewById(2131298785);
        inflate.setTag(viewHodler);
        if (TextUtils.equals("1", settingType) || TextUtils.equals("2", settingType)) {
            if (TextUtils.isEmpty(messageNotificationEntity.getViceTitle())) {
                viewHodler.viceTitle.setVisibility(8);
            } else {
                viewHodler.viceTitle.setVisibility(0);
                viewHodler.viceTitle.setText(LanguageUtil.getInstance().getText(messageNotificationEntity.getViceTitle(), viewHodler.viceTitle));
            }
            viewHodler.switchStatus.setChecked(TextUtils.equals("1", messageNotificationEntity.getSwitchStatus()));
            if (this.isCunZaiFirst) {
                this.isCunZaiFirst = false;
                viewHodler.top_lin.setVisibility(0);
            } else {
                viewHodler.top_lin.setVisibility(8);
            }
        }
        viewHodler.title.setText(LanguageUtil.getInstance().getText(messageNotificationEntity.getTitle(), viewHodler.title));
        viewHodler.all_layout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.messagenotification.adapter.ListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                String str;
                String str2;
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                if (App.hasLogined()) {
                    String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
                    if (UserManager.getInstance().isYiwang()) {
                        str = currentPhoneNumber;
                        str2 = "异网";
                    } else {
                        str = currentPhoneNumber;
                        str2 = UserManager.getInstance().getLoginType();
                    }
                } else {
                    str = "";
                    str2 = "未登录";
                }
                StatisticsUploadUtils.upload(ListAdapter.this.context, "S2ndpage1066", "设置页面-通用", "", str, messageNotificationEntity.getTitle(), messageNotificationEntity.getTitleUrls(), str2);
                if (TextUtils.equals("2", settingType) || TextUtils.equals("3", settingType)) {
                    IntentManager.generateIntentAndGo(ListAdapter.this.context, messageNotificationEntity.getTitleUrls(), LanguageUtil.getInstance().getText(messageNotificationEntity.getTitle(), viewHodler.title), false, "get");
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        if (i == getCount() - 1 && TextUtils.equals("3", settingType) && viewHodler.bottomLineView != null) {
            viewHodler.bottomLineView.setVisibility(8);
        }
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openSwitch(ViewHodler viewHodler, final MessageNotificationEntity messageNotificationEntity) {
        String str;
        String str2;
        String str3;
        String str4;
        if (viewHodler.switchStatus.isChecked()) {
            str = "1";
            str2 = "开关开启";
        } else {
            str = "0";
            str2 = "开关关闭";
        }
        if (App.hasLogined()) {
            String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
            if (UserManager.getInstance().isYiwang()) {
                str3 = currentPhoneNumber;
                str4 = "异网";
            } else {
                str3 = currentPhoneNumber;
                str4 = UserManager.getInstance().getLoginType();
            }
        } else {
            str3 = "";
            str4 = "未登录";
        }
        StatisticsUploadUtils.upload(this.context, "S2ndpage1066", "设置页面-通用", str2, str3, messageNotificationEntity.getTitle(), "", str4);
        try {
            if (this.f18558pd != null) {
                this.f18558pd.setMessage("加载中");
                this.f18558pd.setCanceledOnTouchOutside(false);
            } else {
                this.f18558pd = new CustomePorgressDialog(this.context);
                this.f18558pd.setMessage("加载中");
                this.f18558pd.setCanceledOnTouchOutside(false);
            }
            this.f18558pd.show();
            HashMap hashMap = new HashMap();
            hashMap.put("switchStatus", str);
            hashMap.put("switchType", messageNotificationEntity.getSwitchType());
            App.getAsyncHttpClient().rxPost(URLSet.getMessageNotificationSwitchStatus(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, Boolean>() { // from class: com.sinovatech.unicom.separatemodule.messagenotification.adapter.ListAdapter.5
                @Override // io.reactivex.functions.Function
                public Boolean apply(String str5) throws Exception {
                    try {
                        JSONObject jSONObject = new JSONObject(str5);
                        String optString = jSONObject.optString("status");
                        ListAdapter.this.msg = jSONObject.optString("msg");
                        ListAdapter.this.data = jSONObject.optString("data");
                        if (!TextUtils.equals("200", optString)) {
                            return false;
                        }
                        return true;
                    } catch (Exception unused) {
                        return false;
                    }
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.messagenotification.adapter.ListAdapter.3
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    if (!bool.booleanValue()) {
                        UIUtils.toastLong(ListAdapter.this.msg);
                    }
                    messageNotificationEntity.setSwitchStatus(ListAdapter.this.data);
                    ListAdapter.this.notifyDataSetChanged();
                    if (ListAdapter.this.f18558pd != null && ListAdapter.this.f18558pd.isShowing()) {
                        ListAdapter.this.f18558pd.dismiss();
                    }
                    ((MessageNotificationActivity) ListAdapter.this.context).isKeDian = true;
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.messagenotification.adapter.ListAdapter.4
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    ListAdapter.this.notifyDataSetChanged();
                    UIUtils.logE("error:", th.getMessage());
                    if (ListAdapter.this.f18558pd != null && ListAdapter.this.f18558pd.isShowing()) {
                        ListAdapter.this.f18558pd.dismiss();
                    }
                    ((MessageNotificationActivity) ListAdapter.this.context).isKeDian = true;
                }
            });
        } catch (Exception e) {
            ((MessageNotificationActivity) this.context).isKeDian = true;
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ViewHodler {
        private LinearLayout all_layout;
        private View bottomLineView;
        private ToggleButton switchStatus;
        private TextView title;
        private LinearLayout top_lin;
        private TextView viceTitle;

        ViewHodler() {
        }
    }
}
