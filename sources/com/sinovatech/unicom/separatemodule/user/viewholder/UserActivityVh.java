package com.sinovatech.unicom.separatemodule.user.viewholder;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemEntity;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder;
import com.sinovatech.unicom.separatemodule.user.UserDataSourceManager;
import com.sinovatech.unicom.separatemodule.user.adapter.UserActAdapter;
import com.sinovatech.unicom.separatemodule.user.adapter.UserResultAdapter;
import com.sinovatech.unicom.separatemodule.user.entity.UserActivityEntity;
import com.sinovatech.unicom.separatemodule.user.entity.UserLightEntity;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserAct;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserLightCollect;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserActivityVh extends RVItemViewHolder {
    private final GridView actGridView;
    private final LinearLayout actLayout;
    private AppCompatActivity activity;
    private final ManagerUserAct managerUserAct;
    private final GridView resultGridView;
    private final LinearLayout resultLayout;

    public UserActivityVh(Activity activity, View view) {
        super(activity, view);
        this.activity = (AppCompatActivity) activity;
        this.resultLayout = (LinearLayout) view.findViewById(2131298289);
        this.actLayout = (LinearLayout) view.findViewById(2131296290);
        this.resultGridView = (GridView) view.findViewById(2131298287);
        this.actGridView = (GridView) view.findViewById(2131296289);
        this.managerUserAct = new ManagerUserAct(this.activity);
        setVisibility(false);
    }

    @Override // com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder
    public void bindData(Object obj) {
        if (obj instanceof RVItemEntity) {
            RVItemEntity rVItemEntity = (RVItemEntity) obj;
            if (rVItemEntity.refresh) {
                rVItemEntity.refresh = false;
                getModelData();
                ManagerUserLightCollect.getInstance().addView(UserDataSourceManager.USERACTIVITY, this.itemView);
            }
        }
    }

    private void getModelData() {
        this.managerUserAct.getUserAct().subscribe(new Consumer<UserActivityEntity>() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserActivityVh.1
            @Override // io.reactivex.functions.Consumer
            public void accept(UserActivityEntity userActivityEntity) throws Exception {
                UserActivityVh.this.setViewData(userActivityEntity);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserActivityVh.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                UserActivityVh.this.setViewData(null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViewData(UserActivityEntity userActivityEntity) {
        if (userActivityEntity == null) {
            userActivityEntity = this.managerUserAct.returnData();
        }
        if (userActivityEntity == null) {
            setVisibility(false);
        } else if (userActivityEntity.getActivity() == null && userActivityEntity.getResult() == null && userActivityEntity.getActivity().size() == 0 && userActivityEntity.getResult().size() == 0) {
            setVisibility(false);
        } else {
            setVisibility(true);
            List<UserActivityEntity.Activity> activity = userActivityEntity.getActivity();
            if (activity.size() == 0 || activity.size() < 4) {
                this.actLayout.setVisibility(8);
            } else {
                this.actLayout.setVisibility(0);
                this.actGridView.setAdapter((ListAdapter) new UserActAdapter(this.activity, activity, this.actLayout));
                this.actGridView.setSelector(new ColorDrawable(0));
            }
            List<UserActivityEntity.Result> result = userActivityEntity.getResult();
            if (result.size() == 0 || result.size() < 3) {
                this.resultLayout.setVisibility(8);
            } else {
                this.resultLayout.setVisibility(0);
                this.resultGridView.setAdapter((ListAdapter) new UserResultAdapter(this.activity, result, this.resultLayout));
                this.resultGridView.setSelector(new ColorDrawable(0));
                this.resultGridView.setHorizontalSpacing(UIUtils.dip2px(5.0f));
            }
            checkData(result, activity);
            lightCollect(result, activity);
        }
    }

    private void lightCollect(List<UserActivityEntity.Result> list, List<UserActivityEntity.Activity> list2) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        if (list.size() >= 3) {
            int i2 = 0;
            while (i2 < 3) {
                StringBuilder sb = new StringBuilder();
                sb.append("514010");
                int i3 = i2 + 1;
                sb.append(i3);
                arrayList.add(new UserLightEntity(sb.toString(), list.get(i2).getTitle()));
                i2 = i3;
            }
        }
        if (list2.size() >= 4) {
            while (i < 4) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("514020");
                int i4 = i + 1;
                sb2.append(i4);
                arrayList.add(new UserLightEntity(sb2.toString(), list2.get(i).getTitle()));
                i = i4;
            }
        }
        ManagerUserLightCollect.getInstance().addCollectData(UserDataSourceManager.USERACTIVITY, arrayList);
    }

    private void checkData(List<UserActivityEntity.Result> list, List<UserActivityEntity.Activity> list2) {
        boolean z;
        boolean z2;
        int i = 0;
        while (true) {
            z = true;
            if (i >= list.size()) {
                z2 = true;
                break;
            }
            String viceTitle = list.get(i).getViceTitle();
            String title = list.get(i).getTitle();
            String imgSrc = list.get(i).getImgSrc();
            if (TextUtils.isEmpty(viceTitle) || TextUtils.isEmpty(title) || TextUtils.isEmpty(imgSrc)) {
                break;
            }
            i++;
        }
        z2 = false;
        for (int i2 = 0; i2 < list2.size(); i2++) {
            String title2 = list2.get(i2).getTitle();
            String imgSrc2 = list2.get(i2).getImgSrc();
            if (TextUtils.isEmpty(title2) || TextUtils.isEmpty(imgSrc2)) {
                z = false;
                break;
            }
        }
        if (list.size() < 3) {
            z2 = false;
        }
        if (list2.size() < 4) {
            z = false;
        }
        if (z2 || z) {
            return;
        }
        setVisibility(false);
    }
}
