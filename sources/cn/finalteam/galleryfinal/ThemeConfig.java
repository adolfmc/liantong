package cn.finalteam.galleryfinal;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ThemeConfig implements Serializable {
    private Drawable bgEditTexture;
    private Drawable bgPreveiw;
    private int checkNornalColor;
    private int checkSelectedColor;
    private int cropControlColor;
    private int fabNornalColor;
    private int fabPressedColor;
    private int iconBack;
    private int iconCamera;
    private int iconCheck;
    private int iconClear;
    private int iconCrop;
    private int iconDelete;
    private int iconFab;
    private int iconFolderArrow;
    private int iconPreview;
    private int iconRotate;
    private int titleBarBgColor;
    private int titleBarIconColor;
    private int titleBarTextColor;
    public static ThemeConfig DEFAULT = new Builder().build();
    public static ThemeConfig DARK = new Builder().setTitleBarBgColor(Color.rgb(56, 66, 72)).setFabNornalColor(Color.rgb(56, 66, 72)).setFabPressedColor(Color.rgb(32, 37, 40)).setCheckSelectedColor(Color.rgb(56, 66, 72)).setCropControlColor(Color.rgb(56, 66, 72)).build();
    public static ThemeConfig CYAN = new Builder().setTitleBarBgColor(Color.rgb(1, 131, 147)).setFabNornalColor(Color.rgb(0, 172, 193)).setFabPressedColor(Color.rgb(1, 131, 147)).setCheckSelectedColor(Color.rgb(0, 172, 193)).setCropControlColor(Color.rgb(0, 172, 193)).build();
    public static ThemeConfig ORANGE = new Builder().setTitleBarBgColor(Color.rgb(255, 87, 34)).setFabNornalColor(Color.rgb(255, 87, 34)).setFabPressedColor(Color.rgb(230, 74, 25)).setCheckSelectedColor(Color.rgb(255, 87, 34)).setCropControlColor(Color.rgb(255, 87, 34)).build();
    public static ThemeConfig GREEN = new Builder().setTitleBarBgColor(Color.rgb(76, 175, 80)).setFabNornalColor(Color.rgb(76, 175, 80)).setFabPressedColor(Color.rgb(56, 142, 60)).setCheckSelectedColor(Color.rgb(76, 175, 80)).setCropControlColor(Color.rgb(76, 175, 80)).build();
    public static ThemeConfig TEAL = new Builder().setTitleBarBgColor(Color.rgb(0, 150, 136)).setFabNornalColor(Color.rgb(0, 150, 136)).setFabPressedColor(Color.rgb(0, 121, 107)).setCheckSelectedColor(Color.rgb(0, 150, 136)).setCropControlColor(Color.rgb(0, 150, 136)).build();

    private ThemeConfig(Builder builder) {
        this.titleBarTextColor = builder.titleBarTextColor;
        this.titleBarBgColor = builder.titleBarBgColor;
        this.titleBarIconColor = builder.titleBarIconColor;
        this.checkNornalColor = builder.checkNornalColor;
        this.checkSelectedColor = builder.checkSelectedColor;
        this.fabNornalColor = builder.fabNornalColor;
        this.fabPressedColor = builder.fabPressedColor;
        this.cropControlColor = builder.cropControlColor;
        this.iconBack = builder.iconBack;
        this.iconCamera = builder.iconCamera;
        this.iconCrop = builder.iconCrop;
        this.iconRotate = builder.iconRotate;
        this.iconClear = builder.iconClear;
        this.iconDelete = builder.iconDelete;
        this.iconFolderArrow = builder.iconFolderArrow;
        this.iconCheck = builder.iconCheck;
        this.iconFab = builder.iconFab;
        this.bgEditTexture = builder.bgEditTexture;
        this.iconPreview = builder.iconPreview;
        this.bgPreveiw = builder.bgPreveiw;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Builder {
        private Drawable bgEditTexture;
        private Drawable bgPreveiw;
        private int titleBarTextColor = -1;
        private int titleBarBgColor = Color.rgb(63, 81, 181);
        private int titleBarIconColor = -1;
        private int checkNornalColor = Color.rgb(210, 210, 215);
        private int checkSelectedColor = Color.rgb(63, 81, 181);
        private int fabNornalColor = Color.rgb(63, 81, 181);
        private int fabPressedColor = Color.rgb(48, 63, 159);
        private int cropControlColor = Color.rgb(63, 81, 181);
        private int iconBack = C1656R.C1658drawable.ic_gf_back;
        private int iconCamera = C1656R.C1658drawable.ic_gf_camera;
        private int iconCrop = C1656R.C1658drawable.ic_gf_crop;
        private int iconRotate = C1656R.C1658drawable.ic_gf_rotate;
        private int iconClear = C1656R.C1658drawable.ic_gf_clear;
        private int iconFolderArrow = C1656R.C1658drawable.ic_gf_triangle_arrow;
        private int iconDelete = C1656R.C1658drawable.ic_delete_photo;
        private int iconCheck = C1656R.C1658drawable.ic_folder_check;
        private int iconFab = C1656R.C1658drawable.ic_folder_check;
        private int iconPreview = C1656R.C1658drawable.ic_gf_preview;

        public Builder setTitleBarTextColor(int i) {
            this.titleBarTextColor = i;
            return this;
        }

        public Builder setTitleBarBgColor(int i) {
            this.titleBarBgColor = i;
            return this;
        }

        public Builder setTitleBarIconColor(int i) {
            this.titleBarIconColor = i;
            return this;
        }

        public Builder setCheckNornalColor(int i) {
            this.checkNornalColor = i;
            return this;
        }

        public Builder setCheckSelectedColor(int i) {
            this.checkSelectedColor = i;
            return this;
        }

        public Builder setCropControlColor(int i) {
            this.cropControlColor = i;
            return this;
        }

        public Builder setFabNornalColor(int i) {
            this.fabNornalColor = i;
            return this;
        }

        public Builder setFabPressedColor(int i) {
            this.fabPressedColor = i;
            return this;
        }

        public Builder setIconBack(int i) {
            this.iconBack = i;
            return this;
        }

        public Builder setIconCamera(int i) {
            this.iconCamera = i;
            return this;
        }

        public Builder setIconCrop(int i) {
            this.iconCrop = i;
            return this;
        }

        public Builder setIconRotate(int i) {
            this.iconRotate = i;
            return this;
        }

        public Builder setIconClear(int i) {
            this.iconClear = i;
            return this;
        }

        public Builder setIconFolderArrow(int i) {
            this.iconFolderArrow = i;
            return this;
        }

        public Builder setIconDelete(int i) {
            this.iconDelete = i;
            return this;
        }

        public Builder setIconCheck(int i) {
            this.iconCheck = i;
            return this;
        }

        public Builder setIconFab(int i) {
            this.iconFab = i;
            return this;
        }

        public Builder setEditPhotoBgTexture(Drawable drawable) {
            this.bgEditTexture = drawable;
            return this;
        }

        public Builder setIconPreview(int i) {
            this.iconPreview = i;
            return this;
        }

        public Builder setPreviewBg(Drawable drawable) {
            this.bgPreveiw = drawable;
            return this;
        }

        public ThemeConfig build() {
            return new ThemeConfig(this);
        }
    }

    public int getTitleBarTextColor() {
        return this.titleBarTextColor;
    }

    public int getTitleBarBgColor() {
        return this.titleBarBgColor;
    }

    public int getCheckNornalColor() {
        return this.checkNornalColor;
    }

    public int getCheckSelectedColor() {
        return this.checkSelectedColor;
    }

    public int getTitleBarIconColor() {
        return this.titleBarIconColor;
    }

    public int getFabNornalColor() {
        return this.fabNornalColor;
    }

    public int getFabPressedColor() {
        return this.fabPressedColor;
    }

    public int getCropControlColor() {
        return this.cropControlColor;
    }

    public int getIconBack() {
        return this.iconBack;
    }

    public int getIconCamera() {
        return this.iconCamera;
    }

    public int getIconCrop() {
        return this.iconCrop;
    }

    public int getIconRotate() {
        return this.iconRotate;
    }

    public int getIconClear() {
        return this.iconClear;
    }

    public int getIconFolderArrow() {
        return this.iconFolderArrow;
    }

    public int getIconDelete() {
        return this.iconDelete;
    }

    public int getIconCheck() {
        return this.iconCheck;
    }

    public int getIconFab() {
        return this.iconFab;
    }

    public int getIconPreview() {
        return this.iconPreview;
    }

    public Drawable getPreviewBg() {
        return this.bgPreveiw;
    }

    public Drawable getEditPhotoBgTexture() {
        return this.bgEditTexture;
    }
}
