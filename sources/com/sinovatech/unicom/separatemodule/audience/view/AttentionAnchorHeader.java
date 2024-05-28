package com.sinovatech.unicom.separatemodule.audience.view;

import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.billy.android.swipe.SmartSwipeRefresh;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AttentionAnchorHeader implements SmartSwipeRefresh.SmartSwipeRefreshHeader {
    private AppCompatActivity activity;
    private BaseQuickAdapter<AttentionVideoEntity, BaseViewHolder> adapter;
    private List<AttentionVideoEntity> list;
    private RecyclerView rlv;
    private View view;

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onDataLoading() {
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public long onFinish(boolean z) {
        return 0L;
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onInit(boolean z) {
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onProgress(boolean z, float f) {
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onReset() {
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onStartDragging() {
    }

    public AttentionAnchorHeader(final AppCompatActivity appCompatActivity, List<AttentionVideoEntity> list) {
        this.activity = appCompatActivity;
        this.list = list;
        this.adapter = new BaseQuickAdapter<AttentionVideoEntity, BaseViewHolder>(2131493271, list) { // from class: com.sinovatech.unicom.separatemodule.audience.view.AttentionAnchorHeader.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, AttentionVideoEntity attentionVideoEntity) {
                GlideApp.with((FragmentActivity) appCompatActivity).load(attentionVideoEntity.getHeadImage()).placeholder(2131231806).error(2131231806).into((CircularImage) baseViewHolder.setText(2131299021, attentionVideoEntity.getNikeName()).setText(2131299120, attentionVideoEntity.getType()).setBackgroundRes(2131299466, AttentionAnchorHeader.this.getBg(attentionVideoEntity.getDataType())).setBackgroundRes(2131299120, AttentionAnchorHeader.this.getTypeBg(attentionVideoEntity.getDataType())).getView(2131297332));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTypeBg(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1593830013) {
            if (str.equals("zbPlayBack")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != -1099478762) {
            if (hashCode == 1221368756 && str.equals("smallVideo")) {
                c = 3;
            }
            c = 65535;
        } else {
            if (str.equals("dsjPlayBack")) {
                c = 1;
            }
            c = 65535;
        }
        switch (c) {
            case 1:
            case 2:
                return 2131230871;
            case 3:
                return 2131230872;
            default:
                return 2131230870;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBg(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1593830013) {
            if (str.equals("zbPlayBack")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != -1099478762) {
            if (hashCode == 1221368756 && str.equals("smallVideo")) {
                c = 3;
            }
            c = 65535;
        } else {
            if (str.equals("dsjPlayBack")) {
                c = 1;
            }
            c = 65535;
        }
        switch (c) {
            case 1:
            case 2:
                return 2131230866;
            case 3:
                return 2131230867;
            default:
                return 2131230868;
        }
    }

    public BaseQuickAdapter<AttentionVideoEntity, BaseViewHolder> getAdapter() {
        return this.adapter;
    }

    public RecyclerView getRecyclerView() {
        return this.rlv;
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public View getView() {
        if (this.view == null) {
            this.view = LayoutInflater.from(this.activity).inflate(2131493272, (ViewGroup) null);
            this.rlv = (RecyclerView) this.view.findViewById(2131298397);
            this.rlv.setLayoutManager(new LinearLayoutManager(this.activity, 0, false));
            this.rlv.setAdapter(this.adapter);
        }
        return this.view;
    }
}
