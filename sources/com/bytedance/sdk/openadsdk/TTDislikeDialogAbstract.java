package com.bytedance.sdk.openadsdk;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.sdk.openadsdk.dislike.TTDislikeListView;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class TTDislikeDialogAbstract extends Dialog {

    /* renamed from: mb */
    private View f9486mb;

    /* renamed from: ox */
    private TTDislikeController f9487ox;

    public abstract int getLayoutId();

    public abstract ViewGroup.LayoutParams getLayoutParams();

    public abstract int[] getTTDislikeListViewIds();

    @Deprecated
    public void startPersonalizePromptActivity() {
    }

    public TTDislikeDialogAbstract(Context context) {
        super(context);
    }

    public TTDislikeDialogAbstract(Context context, int i) {
        super(context, i);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.f9486mb = LayoutInflater.from(getContext()).inflate(getLayoutId(), (ViewGroup) null);
            if (this.f9486mb == null) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            View view = this.f9486mb;
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(-1, -1);
            }
            setContentView(view, layoutParams);
            m16570mb();
        } catch (Exception unused) {
            Log.e("TTDislikeDialogAbstract", "getLayoutId布局文件id可能异常，请检查");
        }
    }

    /* renamed from: mb */
    protected void m16570mb() {
        if (this.f9487ox == null || this.f9486mb == null) {
            return;
        }
        int[] tTDislikeListViewIds = getTTDislikeListViewIds();
        if (tTDislikeListViewIds == null || tTDislikeListViewIds.length <= 0) {
            throw new IllegalArgumentException("dislike选项列表为空，请设置TTDislikeListView");
        }
        for (int i : tTDislikeListViewIds) {
            View findViewById = this.f9486mb.findViewById(i);
            if (findViewById == null) {
                throw new IllegalArgumentException("getTTDislikeListViewIds提供的id找不到view，请检查");
            }
            if (!(findViewById instanceof TTDislikeListView)) {
                throw new IllegalArgumentException("getTTDislikeListViewIds找到的view类型异常，请检查");
            }
            ((TTDislikeListView) findViewById).setDislikeInfo(this.f9487ox);
        }
    }

    public void setDislikeModel(TTDislikeController tTDislikeController) {
        this.f9487ox = tTDislikeController;
        m16570mb();
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }
}
