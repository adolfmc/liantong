package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoMoreConfigEntity;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class VideoRingMoreDialog {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CallBack {
        void click(String str);

        void pingLun();

        void share();
    }

    public static void show(final AppCompatActivity appCompatActivity, final List<VideoMoreConfigEntity> list, final String str, final CallBack callBack) {
        final Dialog dialog = new Dialog(appCompatActivity, 2131952070);
        LinearLayout linearLayout = (LinearLayout) appCompatActivity.getLayoutInflater().inflate(2131493311, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) linearLayout.findViewById(2131298400);
        recyclerView.setLayoutManager(new GridLayoutManager(appCompatActivity, 5));
        BaseQuickAdapter<VideoMoreConfigEntity, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<VideoMoreConfigEntity, BaseViewHolder>(2131493251, list) { // from class: com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, VideoMoreConfigEntity videoMoreConfigEntity) {
                baseViewHolder.setText(2131299021, videoMoreConfigEntity.getConfName());
                ImageView imageView = (ImageView) baseViewHolder.getView(2131297398);
                int type = VideoRingMoreDialog.getType(videoMoreConfigEntity.getConfIcon());
                GlideApp.with((FragmentActivity) appCompatActivity).load(type == 0 ? videoMoreConfigEntity.getConfIcon() : Integer.valueOf(type)).into(imageView);
            }
        };
        recyclerView.setAdapter(baseQuickAdapter);
        for (int i = 0; i < list.size(); i++) {
            PvCurrencyLogUtils.pvLogLive("", 3, "", list.get(i).getConfName(), "视频", "", "视频");
        }
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$VideoRingMoreDialog$Z6cX3EeFXbIJUq_vIVIsaNne53Y
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i2) {
                VideoRingMoreDialog.lambda$show$0(list, appCompatActivity, str, callBack, dialog, baseQuickAdapter2, view, i2);
            }
        });
        linearLayout.findViewById(2131298897).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$VideoRingMoreDialog$qUBUSzWHXFApSFvuDnM_ZntXQtA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(linearLayout);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setWindowAnimations(2131952266);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = UIUtils.getScreenWidth((Activity) appCompatActivity);
        attributes.height = -2;
        window.setAttributes(attributes);
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$0(List list, AppCompatActivity appCompatActivity, String str, CallBack callBack, Dialog dialog, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        VideoMoreConfigEntity videoMoreConfigEntity = (VideoMoreConfigEntity) list.get(i);
        int type = getType(videoMoreConfigEntity.getConfIcon());
        if (type == 0) {
            Intent intent = new Intent(appCompatActivity, WebDetailActivity.class);
            WebParamsEntity webParamsEntity = new WebParamsEntity();
            webParamsEntity.setUrl(videoMoreConfigEntity.getConfUrl());
            webParamsEntity.setType(str);
            webParamsEntity.setNeedTitle(true);
            webParamsEntity.setRequestType("get");
            intent.putExtra(WebFragment.webParams, webParamsEntity);
            appCompatActivity.startActivityForResult(intent, 1221);
            if (callBack != null) {
                callBack.click(videoMoreConfigEntity.getConfName());
            }
            PvCurrencyLogUtils.pvLogLive("", 2, "", ((VideoMoreConfigEntity) list.get(i)).getConfName(), "视频", "", "视频");
        } else if (2131231610 == type) {
            if (callBack != null) {
                callBack.pingLun();
            }
        } else if (callBack != null) {
            callBack.share();
        }
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getType(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != 109400031) {
            if (hashCode == 950398559 && str.equals("comment")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("share")) {
                c = 0;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return 2131231618;
            case 1:
                return 2131231610;
            default:
                return 0;
        }
    }
}
