package cn.finalteam.galleryfinal.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.finalteam.galleryfinal.C1656R;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.PhotoEditActivity;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;
import cn.finalteam.toolsfinal.adapter.ViewHolderAdapter;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.List;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class PhotoEditListAdapter extends ViewHolderAdapter<ViewHolder, PhotoInfo> {
    private PhotoEditActivity mActivity;
    private int mRowWidth;

    public PhotoEditListAdapter(PhotoEditActivity photoEditActivity, List<PhotoInfo> list, int i) {
        super(photoEditActivity, list);
        this.mActivity = photoEditActivity;
        this.mRowWidth = i / 5;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.finalteam.toolsfinal.adapter.ViewHolderAdapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(inflate(C1656R.C1660layout.gf_adapter_edit_list, viewGroup));
    }

    @Override // cn.finalteam.toolsfinal.adapter.ViewHolderAdapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        PhotoInfo photoInfo = getDatas().get(i);
        String photoPath = photoInfo != null ? photoInfo.getPhotoPath() : "";
        viewHolder.mIvPhoto.setImageResource(C1656R.C1658drawable.ic_gf_default_photo);
        viewHolder.mIvDelete.setImageResource(GalleryFinal.getGalleryTheme().getIconDelete());
        GalleryFinal.getCoreConfig().getImageLoader().displayImage(this.mActivity, photoPath, viewHolder.mIvPhoto, this.mActivity.getResources().getDrawable(C1656R.C1658drawable.ic_gf_default_photo), 100, 100);
        if (!GalleryFinal.getFunctionConfig().isMutiSelect()) {
            viewHolder.mIvDelete.setVisibility(8);
        } else {
            viewHolder.mIvDelete.setVisibility(0);
        }
        viewHolder.mIvDelete.setOnClickListener(new OnDeletePhotoClickListener(i));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class ViewHolder extends ViewHolderAdapter.ViewHolder {
        ImageView mIvDelete;
        GFImageView mIvPhoto;

        public ViewHolder(View view) {
            super(view);
            this.mIvPhoto = (GFImageView) view.findViewById(C1656R.C1659id.iv_photo);
            this.mIvDelete = (ImageView) view.findViewById(C1656R.C1659id.iv_delete);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class OnDeletePhotoClickListener implements View.OnClickListener {
        private int position;

        public OnDeletePhotoClickListener(int i) {
            this.position = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PhotoInfo photoInfo;
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            try {
                photoInfo = PhotoEditListAdapter.this.getDatas().remove(this.position);
            } catch (Exception e) {
                e.printStackTrace();
                photoInfo = null;
            }
            PhotoEditListAdapter.this.notifyDataSetChanged();
            PhotoEditListAdapter.this.mActivity.deleteIndex(this.position, photoInfo);
            NBSActionInstrumentation.onClickEventExit();
        }
    }
}
