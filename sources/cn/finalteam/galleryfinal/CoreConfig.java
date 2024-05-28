package cn.finalteam.galleryfinal;

import android.content.Context;
import android.os.Environment;
import android.widget.AbsListView;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CoreConfig {
    private int animRes;
    private Context context;
    private File editPhotoCacheFolder;
    private FunctionConfig functionConfig;
    private ImageLoader imageLoader;
    private AbsListView.OnScrollListener onScrollListener;
    private File takePhotoFolder;
    private ThemeConfig themeConfig;

    private CoreConfig(Builder builder) {
        this.context = builder.context;
        this.imageLoader = builder.imageLoader;
        this.takePhotoFolder = builder.takePhotoFolder;
        this.editPhotoCacheFolder = builder.editPhotoCacheFolder;
        this.themeConfig = builder.themeConfig;
        this.functionConfig = builder.functionConfig;
        if (builder.noAnimcation) {
            this.animRes = -1;
        } else {
            this.animRes = builder.animRes;
        }
        this.onScrollListener = builder.onScrollListener;
        if (this.takePhotoFolder == null) {
            this.takePhotoFolder = new File(Environment.getExternalStorageDirectory(), "/DCIM/GalleryFinal/");
        }
        if (!this.takePhotoFolder.exists()) {
            this.takePhotoFolder.mkdirs();
        }
        if (this.editPhotoCacheFolder == null) {
            this.editPhotoCacheFolder = new File(builder.context.getFilesDir() + "/GalleryFinal/edittemp/");
        }
        if (this.editPhotoCacheFolder.exists()) {
            return;
        }
        this.editPhotoCacheFolder.mkdirs();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Builder {
        private int animRes = C1656R.anim.gf_flip_horizontal_in;
        private Context context;
        private File editPhotoCacheFolder;
        private FunctionConfig functionConfig;
        private ImageLoader imageLoader;
        private boolean noAnimcation;
        private AbsListView.OnScrollListener onScrollListener;
        private File takePhotoFolder;
        private ThemeConfig themeConfig;

        public Builder(Context context, ImageLoader imageLoader, ThemeConfig themeConfig) {
            this.context = context;
            this.imageLoader = imageLoader;
            this.themeConfig = themeConfig;
        }

        public Builder setTakePhotoFolder(File file) {
            this.takePhotoFolder = file;
            return this;
        }

        public Builder setEditPhotoCacheFolder(File file) {
            this.editPhotoCacheFolder = file;
            return this;
        }

        public Builder setFunctionConfig(FunctionConfig functionConfig) {
            this.functionConfig = functionConfig;
            return this;
        }

        public Builder setAnimation(int i) {
            this.animRes = i;
            return this;
        }

        public Builder setNoAnimcation(boolean z) {
            this.noAnimcation = z;
            return this;
        }

        public Builder setPauseOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
            this.onScrollListener = onScrollListener;
            return this;
        }

        public CoreConfig build() {
            return new CoreConfig(this);
        }
    }

    public Context getContext() {
        return this.context;
    }

    public ImageLoader getImageLoader() {
        return this.imageLoader;
    }

    public File getTakePhotoFolder() {
        return this.takePhotoFolder;
    }

    public File getEditPhotoCacheFolder() {
        return this.editPhotoCacheFolder;
    }

    public int getAnimation() {
        return this.animRes;
    }

    public ThemeConfig getThemeConfig() {
        return this.themeConfig;
    }

    public FunctionConfig getFunctionConfig() {
        return this.functionConfig;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbsListView.OnScrollListener getPauseOnScrollListener() {
        return this.onScrollListener;
    }
}
