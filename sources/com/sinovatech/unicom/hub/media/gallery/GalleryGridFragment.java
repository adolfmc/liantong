package com.sinovatech.unicom.hub.media.gallery;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.navigation.fragment.NavHostFragment;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.hub.C8126R;
import com.sinovatech.unicom.hub.media.gallery.BucketManager;
import com.sinovatech.unicom.hub.media.utils.MediaCompressUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GalleryGridFragment extends Fragment {
    public NBSTraceUnit _nbs_trace;
    private MediaStoreActivity activityContext;
    private ImageView bucketIcon;
    private LinearLayout bucketLayout;
    private TextView bucketTitle;
    private ImageView closeButton;
    private TextView completeButton;
    private View contentView;
    private BucketEntity currentBucketEntity;
    private GalleryAdapter galleryAdapter;
    private ImageView loadingImage;
    private MediaStoreViewModel mViewModel;
    private CheckBox originalCheckBox;
    private LinearLayout originalCheckLayout;
    private TextView previewButton;
    private RecyclerView recyclerView;
    private List<BucketEntity> mediaCollection = new ArrayList();
    private List<MediaEntity> checkMediaList = new ArrayList();
    private String mediaStoreType = "image";
    private int maxCount = 9;
    private String crop = "yes";
    private String compress = "yes";
    private int compressSize = 300;

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContext = (MediaStoreActivity) context;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.maxCount = getArguments().getInt("count", 9);
            this.mediaStoreType = getArguments().getString("mediaStoreType", "image");
            this.compressSize = getArguments().getInt("compressSize", 300);
            this.compress = getArguments().getString("compress", "yes");
            this.crop = getArguments().getString("crop", "yes");
        }
        this.mViewModel = (MediaStoreViewModel) new ViewModelProvider(this.activityContext, new ViewModelProvider.NewInstanceFactory()).get(MediaStoreViewModel.class);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment", viewGroup);
        View view = this.contentView;
        if (view == null) {
            View inflate = layoutInflater.inflate(C8126R.C8131layout.hub_media_gallery_grid, viewGroup, false);
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment");
            return inflate;
        }
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment");
        return view;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.contentView == null) {
            this.contentView = getView();
            initView(this.contentView);
            initClickListener();
            loadMediastore();
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment");
        Tracker.onResume(this);
        super.onResume();
        this.galleryAdapter.notifyDataSetChanged();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mViewModel.getMediaStoreLiveData().removeObservers(this);
        this.mViewModel.getCheckMediaLiveData().removeObservers(this);
    }

    private void initView(View view) {
        this.closeButton = (ImageView) view.findViewById(C8126R.C8129id.gallery_close);
        this.bucketLayout = (LinearLayout) view.findViewById(C8126R.C8129id.gallery_bucket_layout);
        this.bucketTitle = (TextView) view.findViewById(C8126R.C8129id.gallery_bucket_title);
        this.bucketIcon = (ImageView) view.findViewById(C8126R.C8129id.gallery_bucket_icon);
        this.previewButton = (TextView) view.findViewById(C8126R.C8129id.gallery_preview);
        this.originalCheckBox = (CheckBox) view.findViewById(C8126R.C8129id.gallery_original_checkbox);
        this.originalCheckLayout = (LinearLayout) view.findViewById(C8126R.C8129id.gallery_original_check_layout);
        this.completeButton = (TextView) view.findViewById(C8126R.C8129id.gallery_complete);
        this.loadingImage = (ImageView) view.findViewById(C8126R.C8129id.gallery_loading);
        this.loadingImage.setVisibility(8);
        this.recyclerView = (RecyclerView) view.findViewById(C8126R.C8129id.gallery_recyclerview);
        this.galleryAdapter = new GalleryAdapter();
        this.recyclerView.setAdapter(this.galleryAdapter);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this.activityContext, 4));
        this.recyclerView.addItemDecoration(new SpaceItemDecoration());
        this.bucketLayout.setVisibility(8);
    }

    private void initClickListener() {
        this.bucketLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                BucketManager.getInstance(GalleryGridFragment.this.activityContext).showBucketDialog(GalleryGridFragment.this.bucketLayout, GalleryGridFragment.this.mediaCollection, new BucketManager.SelectBucketListener() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.1.1
                    @Override // com.sinovatech.unicom.hub.media.gallery.BucketManager.SelectBucketListener
                    public void onSelectBucket(String str) {
                        int i = 0;
                        while (true) {
                            if (i >= GalleryGridFragment.this.mediaCollection.size()) {
                                break;
                            } else if (((BucketEntity) GalleryGridFragment.this.mediaCollection.get(i)).getBucketId().equals(str)) {
                                GalleryGridFragment.this.currentBucketEntity = (BucketEntity) GalleryGridFragment.this.mediaCollection.get(i);
                                break;
                            } else {
                                i++;
                            }
                        }
                        if (GalleryGridFragment.this.currentBucketEntity != null) {
                            GalleryGridFragment.this.galleryAdapter.setDataSource(GalleryGridFragment.this.currentBucketEntity.getMediaList());
                            GalleryGridFragment.this.galleryAdapter.notifyDataSetChanged();
                            GalleryGridFragment.this.bucketTitle.setText(GalleryGridFragment.this.currentBucketEntity.getBucketName());
                        }
                    }
                });
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.previewButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (GalleryGridFragment.this.checkMediaList.size() > 0) {
                    GalleryGridFragment.this.mViewModel.updateCheckMediaLiveData(GalleryGridFragment.this.checkMediaList);
                    GalleryGridFragment.this.mViewModel.updateCurrentBucketLiveData(GalleryGridFragment.this.currentBucketEntity);
                    Bundle bundle = new Bundle();
                    bundle.putInt("previewIndex", 0);
                    bundle.putString("previewType", WPPayInfoBean.EVENT_TYPE_CHECK);
                    bundle.putString("crop", GalleryGridFragment.this.crop);
                    bundle.putString("compress", GalleryGridFragment.this.compress);
                    bundle.putInt("compressSize", GalleryGridFragment.this.compressSize);
                    bundle.putInt("count", GalleryGridFragment.this.maxCount);
                    NavHostFragment.findNavController(GalleryGridFragment.this).navigate(C8126R.C8129id.action_galleryGridFragment_to_previewImageFragment, bundle);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                GalleryGridFragment.this.activityContext.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.completeButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (GalleryGridFragment.this.originalCheckBox.isChecked()) {
                    JSONObject parseDataToJSON = GalleryGridFragment.this.mViewModel.parseDataToJSON(GalleryGridFragment.this.checkMediaList);
                    GalleryGridFragment.this.activityContext.completeChoose(!(parseDataToJSON instanceof JSONObject) ? parseDataToJSON.toString() : NBSJSONObjectInstrumentation.toString(parseDataToJSON));
                } else {
                    MediaCompressUtils.compressMediaImage(GalleryGridFragment.this.activityContext, GalleryGridFragment.this.checkMediaList, GalleryGridFragment.this.compressSize, new MediaCompressUtils.OnCompressListener() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.4.1
                        @Override // com.sinovatech.unicom.hub.media.utils.MediaCompressUtils.OnCompressListener
                        public void onCompressComplete() {
                            JSONObject parseDataToJSON2 = GalleryGridFragment.this.mViewModel.parseDataToJSON(GalleryGridFragment.this.checkMediaList);
                            GalleryGridFragment.this.activityContext.completeChoose(!(parseDataToJSON2 instanceof JSONObject) ? parseDataToJSON2.toString() : NBSJSONObjectInstrumentation.toString(parseDataToJSON2));
                        }
                    });
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    private void loadMediastore() {
        if ("yes".equals(this.compress)) {
            this.originalCheckLayout.setVisibility(0);
        } else {
            this.originalCheckLayout.setVisibility(4);
            this.originalCheckBox.setChecked(true);
        }
        this.mViewModel.getMediaStoreLiveData().observe(this, new Observer<List<BucketEntity>>() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.5
            @Override // android.arch.lifecycle.Observer
            public void onChanged(@Nullable List<BucketEntity> list) {
                if (list != null) {
                    GalleryGridFragment.this.mediaCollection = list;
                    if (GalleryGridFragment.this.mediaCollection.size() > 0) {
                        GalleryGridFragment galleryGridFragment = GalleryGridFragment.this;
                        galleryGridFragment.currentBucketEntity = (BucketEntity) galleryGridFragment.mediaCollection.get(0);
                        GalleryGridFragment.this.galleryAdapter.setDataSource(GalleryGridFragment.this.currentBucketEntity.getMediaList());
                        GalleryGridFragment.this.galleryAdapter.notifyDataSetChanged();
                        GalleryGridFragment.this.bucketLayout.setVisibility(0);
                        GalleryGridFragment.this.bucketTitle.setText(GalleryGridFragment.this.currentBucketEntity.getBucketName());
                    }
                }
                GalleryGridFragment.this.loadingImage.setVisibility(8);
            }
        });
        this.mViewModel.getCheckMediaLiveData().observe(this, new Observer<List<MediaEntity>>() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.6
            @Override // android.arch.lifecycle.Observer
            public void onChanged(@Nullable List<MediaEntity> list) {
                GalleryGridFragment.this.checkMediaList = list;
                GalleryGridFragment.this.galleryAdapter.notifyDataSetChanged();
                if (GalleryGridFragment.this.checkMediaList.size() > 0) {
                    GalleryGridFragment.this.completeButton.setBackgroundColor(-1303756);
                    GalleryGridFragment.this.previewButton.setTextColor(-13421773);
                    TextView textView = GalleryGridFragment.this.completeButton;
                    textView.setText("完成(" + GalleryGridFragment.this.checkMediaList.size() + ")");
                    return;
                }
                GalleryGridFragment.this.completeButton.setBackgroundColor(-6710887);
                GalleryGridFragment.this.completeButton.setText("完成");
                GalleryGridFragment.this.previewButton.setTextColor(-6710887);
            }
        });
        new RxPermissions(this.activityContext).request("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.7
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    Glide.with((FragmentActivity) GalleryGridFragment.this.activityContext).asGif().load(Integer.valueOf(C8126R.C8128drawable.hub_media_gallery_loading)).into(GalleryGridFragment.this.loadingImage);
                    GalleryGridFragment.this.loadingImage.setVisibility(0);
                    GalleryGridFragment.this.mViewModel.loadMediaStore(GalleryGridFragment.this.activityContext, GalleryGridFragment.this.mediaStoreType);
                    return;
                }
                Toast.makeText(GalleryGridFragment.this.activityContext, "请开启设备的存储权限", 1).show();
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.8
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class GalleryAdapter extends RecyclerView.Adapter<GalleryVH> {
        private List<MediaEntity> dataSource = new ArrayList();

        GalleryAdapter() {
        }

        public void setDataSource(List<MediaEntity> list) {
            if (list == null) {
                list = new ArrayList<>();
            }
            this.dataSource = list;
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public GalleryVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new GalleryVH(GalleryGridFragment.this.getLayoutInflater().inflate(C8126R.C8131layout.hub_media_gallery_grid_item, viewGroup, false));
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull GalleryVH galleryVH, final int i) {
            final MediaEntity mediaEntity = this.dataSource.get(i);
            galleryVH.itemCheckBox.setOnCheckedChangeListener(null);
            galleryVH.itemCheckBox.setChecked(GalleryGridFragment.this.checkMediaList.contains(mediaEntity));
            galleryVH.itemVideoLayout.setVisibility(8);
            if ("video".equals(mediaEntity.getMediaType())) {
                galleryVH.itemVideoDuration.setText(MediaCompressUtils.videoDurationParse(mediaEntity.getVideoDuration()));
                galleryVH.itemVideoLayout.setVisibility(0);
            }
            Glide.with((FragmentActivity) GalleryGridFragment.this.activityContext).load("image".equals(mediaEntity.getMediaType()) ? mediaEntity.getImagePath() : mediaEntity.getVideoThumbnail()).into(galleryVH.itemImageView);
            galleryVH.itemCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.GalleryAdapter.1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    Tracker.onCheckedChanged(compoundButton, z);
                    if (z) {
                        if (GalleryGridFragment.this.checkMediaList.size() < GalleryGridFragment.this.maxCount) {
                            GalleryGridFragment.this.checkMediaList.add(mediaEntity);
                        } else {
                            MediaStoreActivity mediaStoreActivity = GalleryGridFragment.this.activityContext;
                            Toast.makeText(mediaStoreActivity, "最多只能选中" + GalleryGridFragment.this.maxCount + "个文件", 1).show();
                            compoundButton.setChecked(false);
                        }
                    } else {
                        GalleryGridFragment.this.checkMediaList.remove(mediaEntity);
                    }
                    if (GalleryGridFragment.this.checkMediaList.size() > 0) {
                        GalleryGridFragment.this.completeButton.setBackgroundColor(-1303756);
                        GalleryGridFragment.this.previewButton.setTextColor(-13421773);
                        TextView textView = GalleryGridFragment.this.completeButton;
                        textView.setText("完成(" + GalleryGridFragment.this.checkMediaList.size() + ")");
                        return;
                    }
                    GalleryGridFragment.this.completeButton.setBackgroundColor(-6710887);
                    GalleryGridFragment.this.completeButton.setText("完成");
                    GalleryGridFragment.this.previewButton.setTextColor(-6710887);
                }
            });
            galleryVH.itemImageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.hub.media.gallery.GalleryGridFragment.GalleryAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    GalleryGridFragment.this.mViewModel.updateCheckMediaLiveData(GalleryGridFragment.this.checkMediaList);
                    GalleryGridFragment.this.mViewModel.updateCurrentBucketLiveData(GalleryGridFragment.this.currentBucketEntity);
                    Bundle bundle = new Bundle();
                    bundle.putInt("previewIndex", i);
                    bundle.putString("previewType", "bucket");
                    bundle.putString("crop", GalleryGridFragment.this.crop);
                    bundle.putString("compress", GalleryGridFragment.this.compress);
                    bundle.putInt("compressSize", GalleryGridFragment.this.compressSize);
                    bundle.putInt("count", GalleryGridFragment.this.maxCount);
                    NavHostFragment.findNavController(GalleryGridFragment.this).navigate(C8126R.C8129id.action_galleryGridFragment_to_previewImageFragment, bundle);
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
    public class GalleryVH extends RecyclerView.ViewHolder {
        private CheckBox itemCheckBox;
        private ImageView itemImageView;
        private TextView itemVideoDuration;
        private LinearLayout itemVideoLayout;

        public GalleryVH(View view) {
            super(view);
            this.itemImageView = (ImageView) view.findViewById(C8126R.C8129id.gallery_item_image);
            this.itemCheckBox = (CheckBox) view.findViewById(C8126R.C8129id.gallery_item_checkbox);
            this.itemVideoDuration = (TextView) view.findViewById(C8126R.C8129id.gallery_item_videoduration);
            this.itemVideoLayout = (LinearLayout) view.findViewById(C8126R.C8129id.gallery_item_videolayout);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        SpaceItemDecoration() {
        }

        @Override // android.support.p086v7.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            rect.left = (int) (GalleryGridFragment.this.activityContext.getResources().getDisplayMetrics().density * 4.0f);
            rect.bottom = (int) (GalleryGridFragment.this.activityContext.getResources().getDisplayMetrics().density * 4.0f);
            if (recyclerView.getChildLayoutPosition(view) % 4 == 0) {
                rect.left = 0;
            }
        }
    }
}
