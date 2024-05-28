package com.sinovatech.unicom.hub.media.gallery;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.view.PagerAdapter;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.widget.LinearLayoutManager;
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
import androidx.navigation.Navigation;
import com.blankj.utilcode.util.UriUtils;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.hub.C8126R;
import com.sinovatech.unicom.hub.media.utils.MediaCompressUtils;
import com.sinovatech.unicom.hub.media.utils.MediaStoreUtils;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PreviewImageFragment extends Fragment {
    public NBSTraceUnit _nbs_trace;
    private MediaStoreActivity activityContext;
    private ImageView backButton;
    private CheckBox checkMediaBox;
    private TextView completeButton;
    private View contentView;
    private TextView editButton;
    private MediaStoreViewModel mViewModel;
    private CheckBox originalCheckBox;
    private LinearLayout originalCheckLayout;
    private PreviewAdapter previewAdapter;
    private ViewPager previewViewPager;
    private SmallerAdapter smallerAdapter;
    private RecyclerView smallerRecyclerView;
    private List<MediaEntity> checkMediaList = new ArrayList();
    private int previewIndex = 0;
    private String previewType = "";
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
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment");
        Tracker.onResume(this);
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment");
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
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.previewIndex = getArguments().getInt("previewIndex", 0);
            this.previewType = getArguments().getString("previewType", "");
            this.maxCount = getArguments().getInt("count", 9);
            this.mediaStoreType = getArguments().getString("mediaStoreType", "image");
            this.compressSize = getArguments().getInt("compressSize", 300);
            this.compress = getArguments().getString("compress", "yes");
            this.crop = getArguments().getString("crop", "yes");
        }
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment", viewGroup);
        View view = this.contentView;
        if (view == null) {
            View inflate = layoutInflater.inflate(C8126R.C8131layout.hub_media_preview_image, viewGroup, false);
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment");
            return inflate;
        }
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment");
        return view;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.contentView == null) {
            this.contentView = getView();
            initView(this.contentView);
            this.mViewModel = (MediaStoreViewModel) new ViewModelProvider(this.activityContext, new ViewModelProvider.NewInstanceFactory()).get(MediaStoreViewModel.class);
            this.mViewModel.getCurrentBucketLiveData().observe(this, new Observer<BucketEntity>() { // from class: com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment.1
                @Override // android.arch.lifecycle.Observer
                public void onChanged(@Nullable BucketEntity bucketEntity) {
                    if (PreviewImageFragment.this.previewType.equals("bucket") && PreviewImageFragment.this.previewAdapter.dataSource.size() == 0) {
                        PreviewImageFragment.this.previewAdapter.setDataSource(bucketEntity.getMediaList());
                        PreviewImageFragment.this.previewAdapter.notifyDataSetChanged();
                        PreviewImageFragment.this.previewViewPager.setCurrentItem(PreviewImageFragment.this.previewIndex);
                    }
                    if (PreviewImageFragment.this.previewAdapter.dataSource.size() > 0) {
                        if (PreviewImageFragment.this.checkMediaList.contains(PreviewImageFragment.this.previewAdapter.dataSource.get(PreviewImageFragment.this.previewIndex))) {
                            PreviewImageFragment.this.checkMediaBox.setChecked(true);
                        } else {
                            PreviewImageFragment.this.checkMediaBox.setChecked(false);
                        }
                    }
                }
            });
            this.mViewModel.getCheckMediaLiveData().observe(this, new Observer<List<MediaEntity>>() { // from class: com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment.2
                @Override // android.arch.lifecycle.Observer
                public void onChanged(@Nullable List<MediaEntity> list) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(list);
                    if (PreviewImageFragment.this.previewType.equals(WPPayInfoBean.EVENT_TYPE_CHECK) && PreviewImageFragment.this.previewAdapter.dataSource.size() == 0) {
                        PreviewImageFragment.this.previewAdapter.setDataSource(arrayList);
                        PreviewImageFragment.this.previewAdapter.notifyDataSetChanged();
                        PreviewImageFragment.this.previewViewPager.setCurrentItem(PreviewImageFragment.this.previewIndex);
                    }
                    PreviewImageFragment.this.checkMediaList = new ArrayList();
                    PreviewImageFragment.this.checkMediaList.addAll(arrayList);
                    PreviewImageFragment.this.smallerAdapter.setDataSource(arrayList);
                    PreviewImageFragment.this.smallerAdapter.notifyDataSetChanged();
                    if (PreviewImageFragment.this.previewAdapter.dataSource.size() > 0) {
                        if (PreviewImageFragment.this.checkMediaList.contains(PreviewImageFragment.this.previewAdapter.dataSource.get(PreviewImageFragment.this.previewIndex))) {
                            PreviewImageFragment.this.checkMediaBox.setChecked(true);
                        } else {
                            PreviewImageFragment.this.checkMediaBox.setChecked(false);
                        }
                    }
                    if (PreviewImageFragment.this.checkMediaList.size() > 0) {
                        PreviewImageFragment.this.completeButton.setBackgroundColor(-1303756);
                        TextView textView = PreviewImageFragment.this.completeButton;
                        textView.setText("完成(" + PreviewImageFragment.this.checkMediaList.size() + ")");
                        return;
                    }
                    PreviewImageFragment.this.completeButton.setBackgroundColor(-6710887);
                    PreviewImageFragment.this.completeButton.setText("完成");
                }
            });
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mViewModel.getCurrentBucketLiveData().removeObservers(this);
        this.mViewModel.getCheckMediaLiveData().removeObservers(this);
    }

    private void initView(View view) {
        this.backButton = (ImageView) view.findViewById(C8126R.C8129id.preview_back);
        this.checkMediaBox = (CheckBox) view.findViewById(C8126R.C8129id.preview_checkbox);
        this.completeButton = (TextView) view.findViewById(C8126R.C8129id.preview_complete);
        this.editButton = (TextView) view.findViewById(C8126R.C8129id.preview_edit);
        if ("yes".equals(this.crop)) {
            this.editButton.setVisibility(0);
        } else {
            this.editButton.setVisibility(4);
        }
        this.originalCheckBox = (CheckBox) view.findViewById(C8126R.C8129id.preview_original_checkbox);
        this.originalCheckLayout = (LinearLayout) view.findViewById(C8126R.C8129id.preview_original_check_layout);
        if ("yes".equals(this.compress)) {
            this.originalCheckLayout.setVisibility(0);
        } else {
            this.originalCheckLayout.setVisibility(4);
            this.originalCheckBox.setChecked(true);
        }
        this.smallerRecyclerView = (RecyclerView) view.findViewById(C8126R.C8129id.preview_small_recycerlview);
        this.smallerAdapter = new SmallerAdapter();
        this.smallerRecyclerView.setAdapter(this.smallerAdapter);
        this.smallerRecyclerView.setLayoutManager(new LinearLayoutManager(this.activityContext, 0, false));
        this.previewViewPager = (ViewPager) view.findViewById(C8126R.C8129id.preview_viewpager);
        this.previewAdapter = new PreviewAdapter();
        this.previewViewPager.setAdapter(this.previewAdapter);
        this.previewAdapter.notifyDataSetChanged();
        this.previewViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment.3
            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                NBSActionInstrumentation.onPageSelectedEnter(i, this);
                PreviewImageFragment.this.previewIndex = i;
                PreviewImageFragment.this.smallerAdapter.notifyDataSetChanged();
                MediaEntity mediaEntity = PreviewImageFragment.this.previewAdapter.dataSource.get(i);
                if (PreviewImageFragment.this.checkMediaList.contains(mediaEntity)) {
                    PreviewImageFragment.this.checkMediaBox.setChecked(true);
                } else {
                    PreviewImageFragment.this.checkMediaBox.setChecked(false);
                }
                if (mediaEntity.getMediaType().equals("video") || (mediaEntity.getMimeType() != null && (mediaEntity.getMimeType().contains("gif") || mediaEntity.getMimeType().contains("GIF") || mediaEntity.getMimeType().contains("Gif")))) {
                    PreviewImageFragment.this.editButton.setVisibility(4);
                    PreviewImageFragment.this.originalCheckLayout.setVisibility(4);
                } else {
                    PreviewImageFragment.this.editButton.setVisibility(0);
                    PreviewImageFragment.this.originalCheckLayout.setVisibility(0);
                }
                NBSActionInstrumentation.onPageSelectedExit();
            }
        });
        this.backButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                Navigation.findNavController(view2).navigateUp();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.checkMediaBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Tracker.onCheckedChanged(compoundButton, z);
                MediaEntity mediaEntity = PreviewImageFragment.this.previewAdapter.dataSource.get(PreviewImageFragment.this.previewViewPager.getCurrentItem());
                if (z) {
                    if (!PreviewImageFragment.this.checkMediaList.contains(mediaEntity)) {
                        if (PreviewImageFragment.this.checkMediaList.size() < PreviewImageFragment.this.maxCount) {
                            PreviewImageFragment.this.checkMediaList.add(mediaEntity);
                        } else {
                            MediaStoreActivity mediaStoreActivity = PreviewImageFragment.this.activityContext;
                            Toast.makeText(mediaStoreActivity, "最多只能选中" + PreviewImageFragment.this.maxCount + "个文件", 1).show();
                            compoundButton.setChecked(false);
                        }
                    }
                } else if (PreviewImageFragment.this.checkMediaList.contains(mediaEntity)) {
                    PreviewImageFragment.this.checkMediaList.remove(mediaEntity);
                }
                PreviewImageFragment.this.mViewModel.updateCheckMediaLiveData(PreviewImageFragment.this.checkMediaList);
            }
        });
        this.completeButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                if (PreviewImageFragment.this.originalCheckBox.isChecked()) {
                    JSONObject parseDataToJSON = PreviewImageFragment.this.mViewModel.parseDataToJSON(PreviewImageFragment.this.checkMediaList);
                    PreviewImageFragment.this.activityContext.completeChoose(!(parseDataToJSON instanceof JSONObject) ? parseDataToJSON.toString() : NBSJSONObjectInstrumentation.toString(parseDataToJSON));
                } else {
                    MediaCompressUtils.compressMediaImage(PreviewImageFragment.this.activityContext, PreviewImageFragment.this.checkMediaList, PreviewImageFragment.this.compressSize, new MediaCompressUtils.OnCompressListener() { // from class: com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment.6.1
                        @Override // com.sinovatech.unicom.hub.media.utils.MediaCompressUtils.OnCompressListener
                        public void onCompressComplete() {
                            JSONObject parseDataToJSON2 = PreviewImageFragment.this.mViewModel.parseDataToJSON(PreviewImageFragment.this.checkMediaList);
                            PreviewImageFragment.this.activityContext.completeChoose(!(parseDataToJSON2 instanceof JSONObject) ? parseDataToJSON2.toString() : NBSJSONObjectInstrumentation.toString(parseDataToJSON2));
                        }
                    });
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.editButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                try {
                    Uri parse = Uri.parse("file://" + PreviewImageFragment.this.previewAdapter.dataSource.get(PreviewImageFragment.this.previewViewPager.getCurrentItem()).getOriginalPath());
                    File appMediaHubCache = MediaStoreUtils.getAppMediaHubCache(PreviewImageFragment.this.activityContext, "imagethumbnail");
                    UCrop m2255of = UCrop.m2255of(parse, Uri.fromFile(new File(appMediaHubCache, System.currentTimeMillis() + ".jpg")));
                    UCrop.Options options = new UCrop.Options();
                    options.setHideBottomControls(true);
                    options.setFreeStyleCropEnabled(true);
                    m2255of.withOptions(options);
                    m2255of.start(PreviewImageFragment.this.activityContext, PreviewImageFragment.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class PreviewAdapter extends PagerAdapter {
        public List<MediaEntity> dataSource = new ArrayList();

        @Override // android.support.p083v4.view.PagerAdapter
        public int getItemPosition(@NonNull Object obj) {
            return -2;
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public PreviewAdapter() {
        }

        public void setDataSource(List<MediaEntity> list) {
            if (list == null) {
                list = new ArrayList<>();
            }
            this.dataSource = list;
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public int getCount() {
            return this.dataSource.size();
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            final MediaEntity mediaEntity = this.dataSource.get(i);
            PhotoView photoView = new PhotoView(PreviewImageFragment.this.activityContext);
            photoView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            photoView.setScaleType(ImageView.ScaleType.CENTER);
            if ("video".equals(mediaEntity.getMediaType())) {
                Glide.with((FragmentActivity) PreviewImageFragment.this.activityContext).load(mediaEntity.getVideoThumbnail()).into(photoView);
                photoView.setOnPhotoTapListener(new OnPhotoTapListener() { // from class: com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment.PreviewAdapter.1
                    @Override // com.github.chrisbanes.photoview.OnPhotoTapListener
                    public void onPhotoTap(ImageView imageView, float f, float f2) {
                        try {
                            Uri parse = Uri.parse(mediaEntity.getOriginalPath());
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setDataAndType(parse, "video/*");
                            PreviewImageFragment.this.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                Glide.with((FragmentActivity) PreviewImageFragment.this.activityContext).load(mediaEntity.getImagePath()).into(photoView);
                photoView.setOnClickListener(null);
            }
            viewGroup.addView(photoView);
            return photoView;
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (obj != null) {
                viewGroup.removeView((View) obj);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class SmallerAdapter extends RecyclerView.Adapter<SmallerVH> {
        private List<MediaEntity> dataSource = new ArrayList();

        SmallerAdapter() {
        }

        public void setDataSource(List<MediaEntity> list) {
            if (list == null) {
                list = new ArrayList<>();
            }
            this.dataSource = list;
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public SmallerVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new SmallerVH(PreviewImageFragment.this.getLayoutInflater().inflate(C8126R.C8131layout.hub_media_preview_smaller_item, viewGroup, false));
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull SmallerVH smallerVH, final int i) {
            final MediaEntity mediaEntity = this.dataSource.get(i);
            Glide.with((FragmentActivity) PreviewImageFragment.this.activityContext).load("image".equals(mediaEntity.getMediaType()) ? mediaEntity.getImagePath() : mediaEntity.getVideoThumbnail()).into(smallerVH.itemImage);
            smallerVH.strokeView.setVisibility(8);
            if (mediaEntity.getMediaId() == PreviewImageFragment.this.previewAdapter.dataSource.get(PreviewImageFragment.this.previewViewPager.getCurrentItem()).getMediaId()) {
                smallerVH.strokeView.setVisibility(0);
                new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment.SmallerAdapter.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PreviewImageFragment.this.smallerRecyclerView.smoothScrollToPosition(i);
                    }
                }, 200L);
            }
            smallerVH.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.hub.media.gallery.PreviewImageFragment.SmallerAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    int i2 = 0;
                    while (true) {
                        if (i2 >= PreviewImageFragment.this.previewAdapter.dataSource.size()) {
                            break;
                        } else if (PreviewImageFragment.this.previewAdapter.dataSource.get(i2).getMediaId() == mediaEntity.getMediaId()) {
                            PreviewImageFragment.this.previewViewPager.setCurrentItem(i2);
                            break;
                        } else {
                            i2++;
                        }
                    }
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
    public class SmallerVH extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private View strokeView;

        public SmallerVH(View view) {
            super(view);
            this.itemImage = (ImageView) view.findViewById(C8126R.C8129id.smaller_item_image);
            this.strokeView = view.findViewById(C8126R.C8129id.smaller_item_stroke);
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        MediaStoreActivity mediaStoreActivity = this.activityContext;
        if (i2 != -1 || i != 69) {
            if (i2 == 96 && i == 69) {
                Toast.makeText(this.activityContext, UCrop.getError(intent).getMessage(), 1).show();
                return;
            }
            return;
        }
        Uri output = UCrop.getOutput(intent);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.previewAdapter.dataSource);
        ((MediaEntity) arrayList.get(this.previewIndex)).setImagePath(UriUtils.uri2File(output).getAbsolutePath());
        this.previewAdapter.setDataSource(arrayList);
        this.previewAdapter.notifyDataSetChanged();
        this.smallerAdapter.notifyDataSetChanged();
    }
}
