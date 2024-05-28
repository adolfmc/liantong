package com.sinovatech.unicom.separatemodule.user.adapter;

import android.support.p086v7.app.AppCompatActivity;
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
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.user.entity.UserActivityEntity;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserResultAdapter extends BaseAdapter {
    private AppCompatActivity activity;
    List<UserActivityEntity.Result> result;
    private LinearLayout resultLayout;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public UserResultAdapter(AppCompatActivity appCompatActivity, List<UserActivityEntity.Result> list, LinearLayout linearLayout) {
        this.activity = appCompatActivity;
        this.result = list;
        this.resultLayout = linearLayout;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.result.size() >= 3 ? 3 : 0;
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHodler viewHodler;
        if (view == null) {
            viewHodler = new ViewHodler();
            view2 = this.activity.getLayoutInflater().inflate(2131493530, viewGroup, false);
            view2.setLayoutParams(new ViewGroup.LayoutParams((this.activity.getWindowManager().getDefaultDisplay().getWidth() / 3) - (UIUtils.dip2px(5.0f) * 3), -2));
            viewHodler.imageView = (ImageView) view2.findViewById(2131298288);
            viewHodler.bigText = (TextView) view2.findViewById(2131296519);
            viewHodler.smallText = (TextView) view2.findViewById(2131298637);
            view2.setTag(viewHodler);
        } else {
            ViewHodler viewHodler2 = (ViewHodler) view.getTag();
            viewHodler2.bigText.setText((CharSequence) null);
            viewHodler2.smallText.setText((CharSequence) null);
            viewHodler2.imageView.setImageBitmap(null);
            view2 = view;
            viewHodler = viewHodler2;
        }
        if (TextUtils.isEmpty(this.result.get(i).getTitle()) || TextUtils.isEmpty(this.result.get(i).getViceTitle()) || TextUtils.isEmpty(this.result.get(i).getImgSrc())) {
            this.resultLayout.setVisibility(8);
        } else {
            GlideApp.with(App.getInstance()).load(this.result.get(i).getImgSrc()).placeholder(2131231795).into(viewHodler.imageView);
            viewHodler.bigText.setText(this.result.get(i).getTitle());
            viewHodler.smallText.setText(this.result.get(i).getViceTitle());
        }
        view2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.adapter.UserResultAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                NBSActionInstrumentation.onClickEventEnter(view3, this);
                Tracker.onClick(view3);
                IntentManager.gotoWebViewActivity(UserResultAdapter.this.activity, UserResultAdapter.this.result.get(i).getGoodsUrl(), "");
                String str = "514010" + (i + 1);
                PvCurrencyLogUtils.sendServicePvLog(str, "我的活动-精准营销" + (i + 1), "My page", UserResultAdapter.this.result.get(i).getTitle(), UserResultAdapter.this.result.get(i).getGoodsUrl(), "2", "1", (TextUtils.isEmpty(UserResultAdapter.this.result.get(i).getActType()) ? "" : UserResultAdapter.this.result.get(i).getActType()) + "," + (TextUtils.isEmpty(UserResultAdapter.this.result.get(i).getActId()) ? "" : UserResultAdapter.this.result.get(i).getActId()) + "," + (TextUtils.isEmpty(UserResultAdapter.this.result.get(i).getGoodsId()) ? "" : UserResultAdapter.this.result.get(i).getGoodsId()) + "," + (TextUtils.isEmpty(UserResultAdapter.this.result.get(i).getId()) ? "" : UserResultAdapter.this.result.get(i).getId()) + "," + (TextUtils.isEmpty(UserResultAdapter.this.result.get(i).getPosition()) ? "" : UserResultAdapter.this.result.get(i).getPosition()));
                UserResultAdapter userResultAdapter = UserResultAdapter.this;
                userResultAdapter.clickCollect(str, userResultAdapter.result.get(i));
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        return view2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickCollect(String str, UserActivityEntity.Result result) {
        try {
            UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setTargetUrl(result.getGoodsUrl()).setCodeId(str).setStoreyCode(TextUtils.isEmpty(str) ? "" : str.substring(0, 3)).setPageName("我的").setPbName(result.getTitle()).setActId(result.getActId()).setActType(result.getActType()).setGoodSid(result.getGoodsId()).setGoods_onlyid(result.getId()).setCommodityId(result.getGoodsId()).setMaintaining_information(result.getBuriedPointData()).setMaintenance_position_code(result.getPosition()).setBusinessType(result.getBusiness_type()).setManrongType(result.getManrong_type()).setManrongActivity(result.getManrong_activity()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class ViewHodler {
        private TextView bigText;
        private ImageView imageView;
        private TextView smallText;

        ViewHodler() {
        }
    }
}
