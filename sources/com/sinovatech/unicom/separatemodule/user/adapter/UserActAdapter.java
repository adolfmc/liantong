package com.sinovatech.unicom.separatemodule.user.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.user.entity.UserActivityEntity;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserActAdapter extends BaseAdapter {
    private LinearLayout actLayout;
    private Activity activity;
    private List<UserActivityEntity.Activity> activitysList;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public UserActAdapter(Activity activity, List<UserActivityEntity.Activity> list, LinearLayout linearLayout) {
        this.activity = activity;
        this.activitysList = list;
        this.actLayout = linearLayout;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.activitysList.size() >= 4 ? 4 : 0;
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHodler viewHodler;
        if (view == null) {
            viewHodler = new ViewHodler();
            view2 = this.activity.getLayoutInflater().inflate(2131493505, viewGroup, false);
            viewHodler.imageView = (ImageView) view2.findViewById(2131297288);
            viewHodler.textView = (TextView) view2.findViewById(2131298774);
            view2.setTag(viewHodler);
        } else {
            ViewHodler viewHodler2 = (ViewHodler) view.getTag();
            viewHodler2.textView.setText((CharSequence) null);
            viewHodler2.imageView.setImageBitmap(null);
            view2 = view;
            viewHodler = viewHodler2;
        }
        if (TextUtils.isEmpty(this.activitysList.get(i).getTitle()) || TextUtils.isEmpty(this.activitysList.get(i).getImgSrc())) {
            this.actLayout.setVisibility(8);
        } else {
            GlideApp.with(App.getInstance()).load(this.activitysList.get(i).getImgSrc()).placeholder(2131231795).into(viewHodler.imageView);
            viewHodler.textView.setText(this.activitysList.get(i).getTitle());
        }
        view2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.adapter.UserActAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                NBSActionInstrumentation.onClickEventEnter(view3, this);
                Tracker.onClick(view3);
                IntentManager.gotoWebViewActivity(UserActAdapter.this.activity, ((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getGoodsUrl(), "");
                String str = "514020" + (i + 1);
                PvCurrencyLogUtils.sendServicePvLog(str, "我的活动-功能推广" + (i + 1), "My page", ((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getTitle(), ((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getGoodsUrl(), "2", "1", (TextUtils.isEmpty(((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getActType()) ? "" : ((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getActType()) + "," + (TextUtils.isEmpty(((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getActId()) ? "" : ((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getActId()) + "," + (TextUtils.isEmpty(((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getGoodsId()) ? "" : ((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getGoodsId()) + "," + (TextUtils.isEmpty(((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getId()) ? "" : ((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getId()) + "," + (TextUtils.isEmpty(((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getPosition()) ? "" : ((UserActivityEntity.Activity) UserActAdapter.this.activitysList.get(i)).getPosition()));
                UserActAdapter userActAdapter = UserActAdapter.this;
                userActAdapter.clickCollect(str, (UserActivityEntity.Activity) userActAdapter.activitysList.get(i));
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        return view2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickCollect(String str, UserActivityEntity.Activity activity) {
        try {
            UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setTargetUrl(activity.getGoodsUrl()).setCodeId(str).setStoreyCode(TextUtils.isEmpty(str) ? "" : str.substring(0, 3)).setPageName("我的").setPbName(activity.getTitle()).setActId(activity.getActId()).setCommodityId(activity.getGoodsId()).setActType(activity.getActType()).setGoodSid(activity.getGoodsId()).setGoods_onlyid(activity.getId()).setMaintaining_information(activity.getBuriedPointData()).setMaintenance_position_code(activity.getPosition()).setBusinessType(activity.getBusiness_type()).setManrongType(activity.getManrong_type()).setManrongActivity(activity.getManrong_activity()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class ViewHodler {
        private ImageView imageView;
        private TextView textView;

        ViewHodler() {
        }
    }
}
