package com.sinovatech.unicom.hub.media.gallery;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.hub.C8126R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BucketManager {
    public static String TAG = "BucketManager";
    private static BucketManager instance;
    private Activity activityContext;
    private PopupWindow bucketDialog;
    private SelectBucketListener selectBucketListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    interface SelectBucketListener {
        void onSelectBucket(String str);
    }

    public static synchronized BucketManager getInstance(Activity activity) {
        BucketManager bucketManager;
        synchronized (BucketManager.class) {
            if (instance == null) {
                synchronized (BucketManager.class) {
                    if (instance == null) {
                        instance = new BucketManager(activity);
                    }
                }
            }
            bucketManager = instance;
        }
        return bucketManager;
    }

    public BucketManager(Activity activity) {
        this.activityContext = activity;
    }

    public void showBucketDialog(View view, List<BucketEntity> list, SelectBucketListener selectBucketListener) {
        this.selectBucketListener = selectBucketListener;
        this.bucketDialog = new PopupWindow(this.activityContext);
        this.bucketDialog.setFocusable(true);
        this.bucketDialog.setOutsideTouchable(true);
        this.bucketDialog.setBackgroundDrawable(new ColorDrawable(0));
        this.bucketDialog.setWidth(this.activityContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels);
        this.bucketDialog.setHeight((int) (this.activityContext.getResources().getDisplayMetrics().density * 400.0f));
        LinearLayout linearLayout = (LinearLayout) this.activityContext.getLayoutInflater().inflate(C8126R.C8131layout.hub_media_bucket_dialog, (ViewGroup) null);
        initBucketDialog(linearLayout, list);
        this.bucketDialog.setContentView(linearLayout);
        this.bucketDialog.showAsDropDown(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissBucketDialog() {
        PopupWindow popupWindow = this.bucketDialog;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.bucketDialog.dismiss();
    }

    private void initBucketDialog(View view, List<BucketEntity> list) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(C8126R.C8129id.gallery_bucket_recyclerview);
        recyclerView.setAdapter(new BucketAdapter(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.activityContext, 1, false));
        recyclerView.addItemDecoration(new SpaceItemDecoration(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BucketAdapter extends RecyclerView.Adapter<BucketVH> {
        private List<BucketEntity> dataSource;

        public BucketAdapter(List<BucketEntity> list) {
            this.dataSource = list == null ? new ArrayList<>() : list;
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public BucketVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new BucketVH(BucketManager.this.activityContext.getLayoutInflater().inflate(C8126R.C8131layout.hub_media_bucket_item, viewGroup, false));
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull BucketVH bucketVH, int i) {
            final BucketEntity bucketEntity = this.dataSource.get(i);
            if (BucketManager.this.activityContext != null && !BucketManager.this.activityContext.isDestroyed()) {
                Glide.with(BucketManager.this.activityContext).load(bucketEntity.getMediaList().get(0).getOriginalPath()).into(bucketVH.bucketImage);
            }
            bucketVH.bucketName.setText(bucketEntity.getBucketName());
            TextView textView = bucketVH.bucketCount;
            textView.setText(bucketEntity.getMediaList().size() + "");
            bucketVH.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.hub.media.gallery.BucketManager.BucketAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    BucketManager.this.dismissBucketDialog();
                    BucketManager.this.selectBucketListener.onSelectBucket(bucketEntity.getBucketId());
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.dataSource.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class BucketVH extends RecyclerView.ViewHolder {
        private TextView bucketCount;
        private ImageView bucketImage;
        private TextView bucketName;

        public BucketVH(View view) {
            super(view);
            this.bucketImage = (ImageView) view.findViewById(C8126R.C8129id.bucket_image);
            this.bucketName = (TextView) view.findViewById(C8126R.C8129id.bucket_name);
            this.bucketCount = (TextView) view.findViewById(C8126R.C8129id.bucket_count);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private Activity activityContext;

        public SpaceItemDecoration(Activity activity) {
            this.activityContext = activity;
        }

        @Override // android.support.p086v7.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            rect.bottom = (int) (this.activityContext.getResources().getDisplayMetrics().density * 2.0f);
            if (recyclerView.getChildLayoutPosition(view) == recyclerView.getChildCount() - 1) {
                rect.bottom = 0;
            }
        }
    }
}
