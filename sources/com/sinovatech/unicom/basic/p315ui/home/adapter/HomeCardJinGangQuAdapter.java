package com.sinovatech.unicom.basic.p315ui.home.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentBoxManager;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import io.reactivex.functions.Consumer;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sinovatech.unicom.basic.ui.home.adapter.HomeCardJinGangQuAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeCardJinGangQuAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "HomeCardJinGangQuAdapte";
    private AppCompatActivity mActivity;
    List<MenuEntity> mList;

    public HomeCardJinGangQuAdapter(AppCompatActivity appCompatActivity, List<MenuEntity> list) {
        this.mActivity = appCompatActivity;
        this.mList = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mActivity).inflate(2131493485, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final MenuEntity menuEntity;
        if (viewHolder == null || (menuEntity = this.mList.get(i)) == null) {
            return;
        }
        String menuTitle = menuEntity.getMenuTitle();
        if (menuTitle.length() > 5) {
            menuTitle = menuTitle.substring(0, 4) + "...";
        }
        ShowImageUtils.showImageView(viewHolder.itemView.getContext(), menuEntity.getMenuIconURL(), 2131231795, viewHolder.mIcon);
        viewHolder.mTvName.setText(menuTitle);
        if (TextUtils.equals("1", menuEntity.getIsWarn())) {
            viewHolder.mTvName.setTextColor(Color.parseColor("#E60327"));
        } else {
            viewHolder.mTvName.setTextColor(Color.parseColor("#666666"));
        }
        RxView.clicks(viewHolder.itemView).throttleFirst(1000L, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() { // from class: com.sinovatech.unicom.basic.ui.home.adapter.HomeCardJinGangQuAdapter.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
                IntentManager.generateIntentAndGo(HomeCardJinGangQuAdapter.this.mActivity, menuEntity, "get");
                RecentBoxManager.getInstance().put(menuEntity);
                PvCurrencyLogUtils.addMenu(menuEntity);
                if (menuEntity.isCustom()) {
                    HomeCardJinGangQuAdapter homeCardJinGangQuAdapter = HomeCardJinGangQuAdapter.this;
                    int i2 = i;
                    String menuURL = menuEntity.getMenuURL();
                    String menuTitle2 = menuEntity.getMenuTitle();
                    homeCardJinGangQuAdapter.logDJ(i2, menuURL, "更多配置类", menuTitle2, menuEntity.getMenuId() + "");
                    return;
                }
                HomeCardJinGangQuAdapter homeCardJinGangQuAdapter2 = HomeCardJinGangQuAdapter.this;
                int i3 = i;
                String menuURL2 = menuEntity.getMenuURL();
                String menuTitle3 = menuEntity.getMenuTitle();
                homeCardJinGangQuAdapter2.logDJ(i3, menuURL2, "默认配置类", menuTitle3, menuEntity.getMenuId() + "");
            }
        });
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MenuEntity> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.home.adapter.HomeCardJinGangQuAdapter$ViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIcon;
        private TextView mTvName;

        public ViewHolder(View view) {
            super(view);
            this.mIcon = (ImageView) view.findViewById(2131297537);
            this.mTvName = (TextView) view.findViewById(2131297543);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logDJ(int i, String str, String str2, String str3, String str4) {
        int i2 = 1070100 + i + 1;
        String valueOf = String.valueOf(i2);
        PvCurrencyLogUtils.pvLogHomeJingangqu(valueOf, "首页金刚区位置" + (i + 1), str3, str, str2, "", str4);
        UnicomHomeLogUtils.getInstance().clickLog(String.valueOf(i2), str3);
        MsLogUtil.m7979d("transCode", "-----" + str);
        StatisticsUploadUtils.recordHistoryMenu(App.getInstance(), String.valueOf(i2), "", "导航", str4, str3, str, "", "", "", "", "", str2, "", "", "", UserManager.getInstance().getCurrentPhoneNumber());
    }
}
