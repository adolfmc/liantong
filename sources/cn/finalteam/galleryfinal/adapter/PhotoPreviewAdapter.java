package cn.finalteam.galleryfinal.adapter;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import cn.finalteam.galleryfinal.C1656R;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.adapter.ViewHolderRecyclingPagerAdapter;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.zoonview.PhotoView;
import cn.finalteam.toolsfinal.DeviceUtils;
import java.util.List;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class PhotoPreviewAdapter extends ViewHolderRecyclingPagerAdapter<PreviewViewHolder, PhotoInfo> {
    private Activity mActivity;
    private DisplayMetrics mDisplayMetrics;

    public PhotoPreviewAdapter(Activity activity, List<PhotoInfo> list) {
        super(activity, list);
        this.mActivity = activity;
        this.mDisplayMetrics = DeviceUtils.getScreenPix(this.mActivity);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.finalteam.galleryfinal.adapter.ViewHolderRecyclingPagerAdapter
    public PreviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PreviewViewHolder(getLayoutInflater().inflate(C1656R.C1660layout.gf_adapter_preview_viewpgaer_item, (ViewGroup) null));
    }

    @Override // cn.finalteam.galleryfinal.adapter.ViewHolderRecyclingPagerAdapter
    public void onBindViewHolder(PreviewViewHolder previewViewHolder, int i) {
        PhotoInfo photoInfo = getDatas().get(i);
        String photoPath = photoInfo != null ? photoInfo.getPhotoPath() : "";
        previewViewHolder.mImageView.setImageResource(C1656R.C1658drawable.ic_gf_default_photo);
        GalleryFinal.getCoreConfig().getImageLoader().displayImage(this.mActivity, photoPath, previewViewHolder.mImageView, this.mActivity.getResources().getDrawable(C1656R.C1658drawable.ic_gf_default_photo), this.mDisplayMetrics.widthPixels / 2, this.mDisplayMetrics.heightPixels / 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class PreviewViewHolder extends ViewHolderRecyclingPagerAdapter.ViewHolder {
        PhotoView mImageView;

        public PreviewViewHolder(View view) {
            super(view);
            this.mImageView = (PhotoView) view;
        }
    }
}
