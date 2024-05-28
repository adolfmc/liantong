package cn.finalteam.galleryfinal.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import cn.finalteam.galleryfinal.C1656R;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.PhotoSelectActivity;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;
import cn.finalteam.toolsfinal.adapter.ViewHolderAdapter;
import java.util.List;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class PhotoListAdapter extends ViewHolderAdapter<PhotoViewHolder, PhotoInfo> {
    private Activity mActivity;
    private int mRowWidth;
    private int mScreenWidth;
    private List<PhotoInfo> mSelectList;

    public PhotoListAdapter(Activity activity, List<PhotoInfo> list, List<PhotoInfo> list2, int i) {
        super(activity, list);
        this.mSelectList = list2;
        this.mScreenWidth = i;
        this.mRowWidth = this.mScreenWidth / 3;
        this.mActivity = activity;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.finalteam.toolsfinal.adapter.ViewHolderAdapter
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = inflate(C1656R.C1660layout.gf_adapter_photo_list_item, viewGroup);
        setHeight(inflate);
        return new PhotoViewHolder(inflate);
    }

    @Override // cn.finalteam.toolsfinal.adapter.ViewHolderAdapter
    public void onBindViewHolder(PhotoViewHolder photoViewHolder, int i) {
        try {
            PhotoInfo photoInfo = getDatas().get(i);
            String photoPath = photoInfo != null ? photoInfo.getPhotoPath() : "";
            photoViewHolder.mIvThumb.setImageResource(C1656R.C1658drawable.ic_gf_default_photo);
            Drawable drawable = this.mActivity.getResources().getDrawable(C1656R.C1658drawable.ic_gf_default_photo);
            if (PhotoSelectActivity.TAKEPHOTO.equals(photoPath)) {
                photoViewHolder.mIvThumb.setImageResource(C1656R.C1658drawable.takephoto);
            } else {
                GalleryFinal.getCoreConfig().getImageLoader().displayImage(this.mActivity, photoPath, photoViewHolder.mIvThumb, drawable, this.mRowWidth, this.mRowWidth);
            }
            photoViewHolder.mView.setAnimation(null);
            if (GalleryFinal.getCoreConfig().getAnimation() > 0) {
                photoViewHolder.mView.setAnimation(AnimationUtils.loadAnimation(this.mActivity, GalleryFinal.getCoreConfig().getAnimation()));
            }
            photoViewHolder.mIvCheck.setImageResource(GalleryFinal.getGalleryTheme().getIconCheck());
            if (GalleryFinal.getFunctionConfig().isMutiSelect()) {
                photoViewHolder.mIvCheck.setVisibility(0);
                if (this.mSelectList.contains(photoInfo)) {
                    photoViewHolder.mIvCheck.setBackgroundColor(GalleryFinal.getGalleryTheme().getCheckSelectedColor());
                    return;
                } else {
                    photoViewHolder.mIvCheck.setBackgroundColor(GalleryFinal.getGalleryTheme().getCheckNornalColor());
                    return;
                }
            }
            photoViewHolder.mIvCheck.setVisibility(8);
        } catch (Exception e) {
            Log.e("onBindViewHolder: ", e.getMessage());
        }
    }

    private void setHeight(View view) {
        try {
            view.setLayoutParams(new AbsListView.LayoutParams(-1, (this.mScreenWidth / 3) - 8));
        } catch (Exception e) {
            Log.e("setHeight: ", e.getMessage());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class PhotoViewHolder extends ViewHolderAdapter.ViewHolder {
        public ImageView mIvCheck;
        public GFImageView mIvThumb;
        View mView;

        public PhotoViewHolder(View view) {
            super(view);
            this.mView = view;
            this.mIvThumb = (GFImageView) view.findViewById(C1656R.C1659id.iv_thumb);
            this.mIvCheck = (ImageView) view.findViewById(C1656R.C1659id.iv_check);
        }
    }
}
