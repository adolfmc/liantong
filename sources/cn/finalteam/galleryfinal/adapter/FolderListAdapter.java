package cn.finalteam.galleryfinal.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import cn.finalteam.galleryfinal.C1656R;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoFolderInfo;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;
import cn.finalteam.toolsfinal.adapter.ViewHolderAdapter;
import java.util.List;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class FolderListAdapter extends ViewHolderAdapter<FolderViewHolder, PhotoFolderInfo> {
    private Activity mActivity;
    private FunctionConfig mFunctionConfig;
    private PhotoFolderInfo mSelectFolder;

    public FolderListAdapter(Activity activity, List<PhotoFolderInfo> list, FunctionConfig functionConfig) {
        super(activity, list);
        this.mFunctionConfig = functionConfig;
        this.mActivity = activity;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.finalteam.toolsfinal.adapter.ViewHolderAdapter
    public FolderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FolderViewHolder(inflate(C1656R.C1660layout.gf_adapter_folder_list_item, viewGroup));
    }

    @Override // cn.finalteam.toolsfinal.adapter.ViewHolderAdapter
    public void onBindViewHolder(FolderViewHolder folderViewHolder, int i) {
        PhotoFolderInfo photoFolderInfo = getDatas().get(i);
        PhotoInfo coverPhoto = photoFolderInfo.getCoverPhoto();
        String photoPath = coverPhoto != null ? coverPhoto.getPhotoPath() : "";
        folderViewHolder.mIvCover.setImageResource(C1656R.C1658drawable.ic_gf_default_photo);
        GalleryFinal.getCoreConfig().getImageLoader().displayImage(this.mActivity, photoPath, folderViewHolder.mIvCover, this.mActivity.getResources().getDrawable(C1656R.C1658drawable.ic_gf_default_photo), 200, 200);
        folderViewHolder.mTvFolderName.setText(photoFolderInfo.getFolderName());
        folderViewHolder.mTvPhotoCount.setText(this.mActivity.getString(C1656R.string.folder_photo_size, new Object[]{Integer.valueOf(photoFolderInfo.getPhotoList() != null ? photoFolderInfo.getPhotoList().size() : 0)}));
        if (GalleryFinal.getCoreConfig().getAnimation() > 0) {
            folderViewHolder.mView.startAnimation(AnimationUtils.loadAnimation(this.mActivity, GalleryFinal.getCoreConfig().getAnimation()));
        }
        folderViewHolder.mIvFolderCheck.setImageResource(GalleryFinal.getGalleryTheme().getIconCheck());
        PhotoFolderInfo photoFolderInfo2 = this.mSelectFolder;
        if (photoFolderInfo2 == photoFolderInfo || (photoFolderInfo2 == null && i == 0)) {
            folderViewHolder.mIvFolderCheck.setVisibility(0);
            folderViewHolder.mIvFolderCheck.setColorFilter(GalleryFinal.getGalleryTheme().getCheckSelectedColor());
            return;
        }
        folderViewHolder.mIvFolderCheck.setVisibility(8);
    }

    public void setSelectFolder(PhotoFolderInfo photoFolderInfo) {
        this.mSelectFolder = photoFolderInfo;
    }

    public PhotoFolderInfo getSelectFolder() {
        return this.mSelectFolder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class FolderViewHolder extends ViewHolderAdapter.ViewHolder {
        GFImageView mIvCover;
        ImageView mIvFolderCheck;
        TextView mTvFolderName;
        TextView mTvPhotoCount;
        View mView;

        public FolderViewHolder(View view) {
            super(view);
            this.mView = view;
            this.mIvCover = (GFImageView) view.findViewById(C1656R.C1659id.iv_cover);
            this.mTvFolderName = (TextView) view.findViewById(C1656R.C1659id.tv_folder_name);
            this.mTvPhotoCount = (TextView) view.findViewById(C1656R.C1659id.tv_photo_count);
            this.mIvFolderCheck = (ImageView) view.findViewById(C1656R.C1659id.iv_folder_check);
        }
    }
}
