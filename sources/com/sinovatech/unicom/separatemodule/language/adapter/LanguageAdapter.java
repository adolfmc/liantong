package com.sinovatech.unicom.separatemodule.language.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LanguageAdapter extends RecyclerView.Adapter<LanguageViewHolder> {
    private Activity activity;
    private String currentLanuage = "";
    private List<LanguageEntity> list;
    private onItemClickListener onItemClickListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface onItemClickListener {
        void onItemClickListener(int i, LanguageEntity languageEntity);
    }

    public LanguageAdapter(Activity activity, List<LanguageEntity> list) {
        this.activity = activity;
        this.list = list;
    }

    public void setCurrentLanuage(String str) {
        if (TextUtils.isEmpty(str)) {
            str = LanguageUtil.CHN_CN;
        }
        this.currentLanuage = str;
    }

    public void setOnItemClickListener(onItemClickListener onitemclicklistener) {
        this.onItemClickListener = onitemclicklistener;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LanguageViewHolder(LayoutInflater.from(this.activity).inflate(2131493270, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull LanguageViewHolder languageViewHolder, final int i) {
        final LanguageEntity languageEntity = this.list.get(i);
        if (languageEntity != null) {
            languageViewHolder.mTvLanguageName.setText(languageEntity.getLanguageName());
            languageViewHolder.mTvLanguageNameOriginal.setText(languageEntity.getLanguageDesc());
            languageViewHolder.mTvPlaceholder.setText(LanguageUtil.maxLanguageLengthName);
            if (TextUtils.equals(this.currentLanuage, languageEntity.getLanguageCode())) {
                Drawable drawable = App.getInstance().getResources().getDrawable(2131231793);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                languageViewHolder.mTvLanguageNameOriginal.setCompoundDrawables(null, null, drawable, null);
            } else {
                languageViewHolder.mTvLanguageNameOriginal.setCompoundDrawables(null, null, null, null);
            }
            languageViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.language.adapter.LanguageAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (LanguageAdapter.this.onItemClickListener != null) {
                        languageEntity.setSelect(true);
                        LanguageAdapter.this.onItemClickListener.onItemClickListener(i, languageEntity);
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class LanguageViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvLanguageName;
        private TextView mTvLanguageNameOriginal;
        private TextView mTvPlaceholder;

        public LanguageViewHolder(View view) {
            super(view);
            this.mTvLanguageName = (TextView) view.findViewById(2131297569);
            this.mTvLanguageNameOriginal = (TextView) view.findViewById(2131297571);
            this.mTvPlaceholder = (TextView) view.findViewById(2131297570);
        }
    }
}
