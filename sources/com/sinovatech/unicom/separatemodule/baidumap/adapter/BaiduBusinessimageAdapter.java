package com.sinovatech.unicom.separatemodule.baidumap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.p318ui.GlideApp;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduBusinessimageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> list;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private String pitches;
    private String pitches2;
    private String pitches3;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemClickListener {
        void onItemClick();
    }

    public BaiduBusinessimageAdapter(Context context, List<String> list, String str, String str2, String str3) {
        this.mContext = context;
        this.list = list;
        this.pitches = str;
        this.pitches2 = str2;
        this.pitches3 = str3;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(this.mContext).inflate(2131493010, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyHolder myHolder = (MyHolder) viewHolder;
        GlideApp.with(this.mContext).load(this.list.get(i)).placeholder(2131230995).error(2131230995).into(myHolder.mImage);
        if (i == 2) {
            if (i == 0) {
                if (TextUtils.isEmpty(this.pitches)) {
                    myHolder.mTextlayout.setVisibility(8);
                } else {
                    myHolder.mTextlayout.setVisibility(0);
                    myHolder.mTitle.setText(this.pitches);
                }
            } else if (i == 1) {
                if (TextUtils.isEmpty(this.pitches2)) {
                    myHolder.mTextlayout.setVisibility(8);
                } else {
                    myHolder.mTextlayout.setVisibility(0);
                    myHolder.mTitle.setText(this.pitches2);
                }
            } else if (i == 2) {
                if (TextUtils.isEmpty(this.pitches3)) {
                    myHolder.mTextlayout.setVisibility(8);
                } else {
                    myHolder.mTextlayout.setVisibility(0);
                    myHolder.mTitle.setText(this.pitches3);
                }
            }
        } else if (i == 1) {
            if (i == 0) {
                if (TextUtils.isEmpty(this.pitches)) {
                    myHolder.mTextlayout.setVisibility(8);
                } else {
                    myHolder.mTextlayout.setVisibility(0);
                    myHolder.mTitle.setText(this.pitches);
                }
            } else if (i == 1) {
                if (TextUtils.isEmpty(this.pitches2)) {
                    myHolder.mTextlayout.setVisibility(8);
                } else {
                    myHolder.mTextlayout.setVisibility(0);
                    myHolder.mTitle.setText(this.pitches2);
                }
            }
        } else if (i == 0 && i == 0) {
            if (TextUtils.isEmpty(this.pitches)) {
                myHolder.mTextlayout.setVisibility(8);
            } else {
                myHolder.mTextlayout.setVisibility(0);
                myHolder.mTitle.setText(this.pitches);
            }
        }
        myHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduBusinessimageAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (BaiduBusinessimageAdapter.this.mOnItemClickListener != null) {
                    BaiduBusinessimageAdapter.this.mOnItemClickListener.onItemClick();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class MyHolder extends RecyclerView.ViewHolder {
        private final ImageView mImage;
        private final RelativeLayout mTextlayout;
        private final TextView mTitle;

        public MyHolder(View view) {
            super(view);
            this.mImage = (ImageView) view.findViewById(2131297258);
            this.mTitle = (TextView) view.findViewById(2131298785);
            this.mTextlayout = (RelativeLayout) view.findViewById(2131298773);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
