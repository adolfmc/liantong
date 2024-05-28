package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p314po.BindNumberEntity;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerMail */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerMail {
    private AppCompatActivity activityContext;

    /* renamed from: pd */
    private ProgressDialog f18424pd;
    private RefreshComplete refreshComplete;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerMail$RefreshComplete */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface RefreshComplete {
        void complete();
    }

    public ManagerMail(AppCompatActivity appCompatActivity, RefreshComplete refreshComplete) {
        this.activityContext = appCompatActivity;
        this.refreshComplete = refreshComplete;
        this.f18424pd = new CustomePorgressDialog(appCompatActivity);
    }

    public void showDialog() {
        Dialog dialog = new Dialog(this.activityContext, 2131952272);
        dialog.setContentView(createBindPhoneNumberView(dialog));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        attributes.y = UIUtils.dip2px(this.activityContext, 200.0f);
        Window window = dialog.getWindow();
        window.setGravity(48);
        window.setAttributes(attributes);
        window.setWindowAnimations(2131952271);
        dialog.show();
    }

    private View createBindPhoneNumberView(final Dialog dialog) {
        final List<BindNumberEntity> userBindPhoneNumber = UserManager.getInstance().getUserBindPhoneNumber();
        View inflate = this.activityContext.getLayoutInflater().inflate(2131493510, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(2131299340);
        ((TextView) inflate.findViewById(2131299336)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMail.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                ManagerMail.this.generateBindPhoneIntentAndGo();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        ((TextView) inflate.findViewById(2131299337)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMail.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                ManagerMail.this.generateBindPhoneIntentAndGo();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMail.3
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                NBSActionInstrumentation.onItemClickEnter(view, i, this);
                Tracker.onItemClick(adapterView, view, i, j);
                dialog.cancel();
                ManagerMail.this.changeNumber(((BindNumberEntity) userBindPhoneNumber.get(i)).getName());
                NBSActionInstrumentation.onItemClickExit();
            }
        });
        listView.setAdapter((ListAdapter) new BaseAdapter() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMail.4
            @Override // android.widget.Adapter
            public Object getItem(int i) {
                return null;
            }

            @Override // android.widget.Adapter
            public long getItemId(int i) {
                return 0L;
            }

            @Override // android.widget.Adapter
            public View getView(int i, View view, ViewGroup viewGroup) {
                LinearLayout linearLayout = (LinearLayout) ManagerMail.this.activityContext.getLayoutInflater().inflate(2131493511, (ViewGroup) null);
                BindNumberEntity bindNumberEntity = (BindNumberEntity) userBindPhoneNumber.get(i);
                ((TextView) linearLayout.findViewById(2131299339)).setText(bindNumberEntity.getName());
                ImageView imageView = (ImageView) linearLayout.findViewById(2131299338);
                if (bindNumberEntity.getName().equals(UserManager.getInstance().getCurrentPhoneNumber())) {
                    imageView.setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                }
                return linearLayout;
            }

            @Override // android.widget.Adapter
            public int getCount() {
                return userBindPhoneNumber.size();
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void generateBindPhoneIntentAndGo() {
        StatisticsUploadUtils.upload(this.activityContext, "51", "我的-头部", "按钮", "0", "切换号码", "");
        Intent intent = new Intent(this.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(URLSet.getBindPhoneNumberURL());
        webParamsEntity.setTitle("绑定号码");
        webParamsEntity.setNeedTitle(true);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        new AvoidOnResult(this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMail.5
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent2) {
                ManagerMail.this.refreshBindPhoneNumber();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBindPhoneNumber() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("version", this.activityContext.getString(2131886969));
        requestParams.put("desmobile", UserManager.getInstance().getPassBackDesmobile());
        App.getAsyncHttpClient().post(URLSet.getRefreshBindNumber(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMail.6
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                ManagerMail.this.f18424pd.setMessage("正在刷新 请稍候...");
                ManagerMail.this.f18424pd.show();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str) {
                super.onSuccess(i, str);
                ManagerMail.this.handlerContent(i, str);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str) {
                super.onFailure(th, str);
                th.printStackTrace();
                UIUtils.toastLong("刷新失败 请重试!");
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                if (ManagerMail.this.f18424pd != null) {
                    ManagerMail.this.f18424pd.dismiss();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeNumber(String str) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("version", this.activityContext.getString(2131886969));
        requestParams.put("desmobile", UserManager.getInstance().getPassBackDesmobile());
        requestParams.put("mobile", str);
        App.clearPersistentCookiesList();
        UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
        App.getAsyncHttpClient().post(URLSet.getChangeNumber(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMail.7
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                ManagerMail.this.f18424pd.setMessage("正在刷新 请稍候...");
                ManagerMail.this.f18424pd.show();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str2) {
                super.onSuccess(i, str2);
                ManagerMail.this.handlerContent(i, str2);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                ManagerMail.this.f18424pd.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerContent(int i, String str) {
        try {
            if (i == 200) {
                String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
                HashMap<String, String> handleLoginResponse = LoginManager.handleLoginResponse(UserManager.getInstance().getUserAccountName(), str, "Change_Type");
                if ("ok".equals(handleLoginResponse.get("ok"))) {
                    if (!currentPhoneNumber.equals(UserManager.getInstance().getCurrentPhoneNumber())) {
                        this.refreshComplete.complete();
                    }
                } else {
                    String str2 = handleLoginResponse.get("description");
                    if (str2 != null && !str2.equals("")) {
                        UIUtils.toastLong(str2);
                    } else {
                        UIUtils.toastLong("切换号码失败 请重试!");
                    }
                }
            } else {
                UIUtils.toastLong("切换号码失败 请重试!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
