package cn.finalteam.galleryfinal;

import android.support.annotation.IntRange;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class FunctionConfig implements Cloneable {
    private boolean camera;
    protected boolean crop;
    private int cropHeight;
    private boolean cropReplaceSource;
    private boolean cropSquare;
    private int cropWidth;
    protected boolean editPhoto;
    private ArrayList<String> filterList;
    private boolean forceCrop;
    private boolean forceCropEdit;
    protected int maxSize;
    protected boolean mutiSelect;
    private boolean preview;
    private boolean rotate;
    private boolean rotateReplaceSource;
    private ArrayList<String> selectedList;

    private FunctionConfig(Builder builder) {
        this.mutiSelect = builder.mutiSelect;
        this.maxSize = builder.maxSize;
        this.editPhoto = builder.editPhoto;
        this.crop = builder.crop;
        this.rotate = builder.rotate;
        this.camera = builder.camera;
        this.cropWidth = builder.cropWidth;
        this.cropHeight = builder.cropHeight;
        this.cropSquare = builder.cropSquare;
        this.selectedList = builder.selectedList;
        this.filterList = builder.filterList;
        this.rotateReplaceSource = builder.rotateReplaceSource;
        this.cropReplaceSource = builder.cropReplaceSource;
        this.forceCrop = builder.forceCrop;
        this.forceCropEdit = builder.forceCropEdit;
        this.preview = builder.preview;
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class Builder {
        private boolean camera;
        private boolean crop;
        private int cropHeight;
        private boolean cropReplaceSource;
        private boolean cropSquare;
        private int cropWidth;
        private boolean editPhoto;
        private ArrayList<String> filterList;
        private boolean forceCrop;
        private boolean forceCropEdit;
        private int maxSize;
        private boolean mutiSelect;
        private boolean preview;
        private boolean rotate;
        private boolean rotateReplaceSource;
        private ArrayList<String> selectedList;

        protected Builder setMutiSelect(boolean z) {
            this.mutiSelect = z;
            return this;
        }

        public Builder setMutiSelectMaxSize(@IntRange(from = 1, m22209to = 2147483647L) int i) {
            this.maxSize = i;
            return this;
        }

        public Builder setEnableEdit(boolean z) {
            this.editPhoto = z;
            return this;
        }

        public Builder setEnableCrop(boolean z) {
            this.crop = z;
            return this;
        }

        public Builder setEnableRotate(boolean z) {
            this.rotate = z;
            return this;
        }

        public Builder setEnableCamera(boolean z) {
            this.camera = z;
            return this;
        }

        public Builder setCropWidth(@IntRange(from = 1, m22209to = 2147483647L) int i) {
            this.cropWidth = i;
            return this;
        }

        public Builder setCropHeight(@IntRange(from = 1, m22209to = 2147483647L) int i) {
            this.cropHeight = i;
            return this;
        }

        public Builder setCropSquare(boolean z) {
            this.cropSquare = z;
            return this;
        }

        public Builder setSelected(ArrayList<String> arrayList) {
            if (arrayList != null) {
                this.selectedList = (ArrayList) arrayList.clone();
            }
            return this;
        }

        public Builder setSelected(Collection<PhotoInfo> collection) {
            if (collection != null) {
                ArrayList<String> arrayList = new ArrayList<>();
                for (PhotoInfo photoInfo : collection) {
                    if (photoInfo != null) {
                        arrayList.add(photoInfo.getPhotoPath());
                    }
                }
                this.selectedList = arrayList;
            }
            return this;
        }

        public Builder setFilter(ArrayList<String> arrayList) {
            if (arrayList != null) {
                this.filterList = (ArrayList) arrayList.clone();
            }
            return this;
        }

        public Builder setFilter(Collection<PhotoInfo> collection) {
            if (collection != null) {
                ArrayList<String> arrayList = new ArrayList<>();
                for (PhotoInfo photoInfo : collection) {
                    if (photoInfo != null) {
                        arrayList.add(photoInfo.getPhotoPath());
                    }
                }
                this.filterList = arrayList;
            }
            return this;
        }

        public Builder setRotateReplaceSource(boolean z) {
            this.rotateReplaceSource = z;
            return this;
        }

        public Builder setCropReplaceSource(boolean z) {
            this.cropReplaceSource = z;
            return this;
        }

        public Builder setForceCrop(boolean z) {
            this.forceCrop = z;
            return this;
        }

        public Builder setForceCropEdit(boolean z) {
            this.forceCropEdit = z;
            return this;
        }

        public Builder setEnablePreview(boolean z) {
            this.preview = z;
            return this;
        }

        public FunctionConfig build() {
            return new FunctionConfig(this);
        }
    }

    public boolean isMutiSelect() {
        return this.mutiSelect;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public boolean isEditPhoto() {
        return this.editPhoto;
    }

    public boolean isCrop() {
        return this.crop;
    }

    public boolean isRotate() {
        return this.rotate;
    }

    public boolean isCamera() {
        return this.camera;
    }

    public int getCropWidth() {
        return this.cropWidth;
    }

    public int getCropHeight() {
        return this.cropHeight;
    }

    public boolean isCropSquare() {
        return this.cropSquare;
    }

    public boolean isRotateReplaceSource() {
        return this.rotateReplaceSource;
    }

    public boolean isCropReplaceSource() {
        return this.cropReplaceSource;
    }

    public boolean isForceCrop() {
        return this.forceCrop;
    }

    public boolean isForceCropEdit() {
        return this.forceCropEdit;
    }

    public ArrayList<String> getSelectedList() {
        return this.selectedList;
    }

    public ArrayList<String> getFilterList() {
        return this.filterList;
    }

    public boolean isEnablePreview() {
        return this.preview;
    }

    /* renamed from: clone */
    public FunctionConfig m24461clone() {
        try {
            return (FunctionConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
