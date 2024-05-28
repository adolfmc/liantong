package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.common.ThumbnailLoader;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.entity.FileModel;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.FileUtils;
import java.io.IOException;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SelectWechatItemAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Activity activity;
    private List<FileModel> dataList;
    private boolean isScrolling;
    private ThumbnailLoader thumbnailLoader;

    public SelectWechatItemAdapter(Activity activity, List<FileModel> list, boolean z) {
        this.dataList = list;
        this.activity = activity;
        this.isScrolling = z;
        this.thumbnailLoader = new ThumbnailLoader(activity);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493552, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tv_name.setText(this.dataList.get(i).getFileName());
        viewHolder.tv_size.setText(this.dataList.get(i).getFileSize());
        viewHolder.iv_shipin.setVisibility(8);
        switch (this.dataList.get(i).getNums()) {
            case 1:
                jiazai(this.dataList.get(i), viewHolder);
                break;
            case 2:
                jiazai(this.dataList.get(i), viewHolder);
                break;
            case 3:
                viewHolder.iv_shipin.setVisibility(0);
                jiazai(this.dataList.get(i), viewHolder);
                viewHolder.tv_size.setText(String.valueOf(this.dataList.get(i).getVideoTime()));
                break;
            case 4:
                viewHolder.iv_bg.setImageResource(2131100056);
                break;
            case 5:
                viewHolder.iv_bg.setImageResource(2131100059);
                break;
            case 6:
                viewHolder.iv_bg.setImageResource(2131100058);
                break;
            case 7:
                viewHolder.iv_bg.setImageResource(2131100057);
                break;
        }
        if (this.dataList.get(i).isXuanZhong()) {
            viewHolder.iv_change.setImageResource(2131232633);
        } else {
            viewHolder.iv_change.setImageResource(2131232632);
        }
        viewHolder.iv_change.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.adapter.SelectWechatItemAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (!((FileModel) SelectWechatItemAdapter.this.dataList.get(i)).isXuanZhong()) {
                    if (SelectWechatFileActivity.num >= 0 && SelectWechatFileActivity.num < 20) {
                        viewHolder.iv_change.setImageResource(2131232633);
                        ((FileModel) SelectWechatItemAdapter.this.dataList.get(i)).setXuanZhong(true);
                        SelectWechatFileActivity.num++;
                    }
                } else {
                    viewHolder.iv_change.setImageResource(2131232632);
                    ((FileModel) SelectWechatItemAdapter.this.dataList.get(i)).setXuanZhong(false);
                    SelectWechatFileActivity.num--;
                }
                SelectWechatFileActivity.updateSizeUI();
                if (SelectWechatFileActivity.num >= 0) {
                    int i2 = SelectWechatFileActivity.num;
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    private void jiazai(FileModel fileModel, ViewHolder viewHolder) {
        if (!this.isScrolling) {
            if (fileModel.isDataFile()) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.activity.getContentResolver(), fileModel.getUri());
                    if (bitmap != null) {
                        GlideApp.with(this.activity).asDrawable().load((Drawable) new BitmapDrawable(bitmap)).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(false).apply((BaseRequestOptions<?>) FileUtils.gildeOptions()).into(viewHolder.iv_bg);
                        return;
                    }
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            GlideApp.with(this.activity).asDrawable().load(fileModel.getUri().getPath()).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(false).apply((BaseRequestOptions<?>) FileUtils.gildeOptions()).into(viewHolder.iv_bg);
            return;
        }
        viewHolder.iv_bg.setImageResource(0);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onViewRecycled(@NonNull ViewHolder viewHolder) {
        super.onViewRecycled((SelectWechatItemAdapter) viewHolder);
        ImageView imageView = viewHolder.iv_bg;
        if (imageView != null) {
            GlideApp.with(this.activity).clear(imageView);
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<FileModel> list = this.dataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_bg;
        private ImageView iv_change;
        private ImageView iv_shipin;

        /* renamed from: rl */
        private RelativeLayout f18581rl;
        private TextView tv_name;
        private TextView tv_size;

        ViewHolder(@NonNull View view) {
            super(view);
            this.f18581rl = (RelativeLayout) view.findViewById(2131298315);
            this.tv_name = (TextView) view.findViewById(2131299021);
            this.iv_change = (ImageView) view.findViewById(2131297362);
            this.tv_size = (TextView) view.findViewById(2131299080);
            this.iv_bg = (ImageView) view.findViewById(2131297350);
            this.iv_shipin = (ImageView) view.findViewById(2131297493);
        }
    }
}
