package com.baidu.cloud.mediaprocess.basefilters;

import android.opengl.GLES20;
import android.util.Log;
import com.baidu.cloud.mediaprocess.gles.GlUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ImageFilterGroup extends ImageFilter {

    /* renamed from: h */
    public static final String f4530h = "ImageFilterGroup";

    /* renamed from: i */
    public int f4531i;

    /* renamed from: j */
    public int[] f4532j;
    public List<ImageFilter> mFilters;
    public List<ImageFilter> mMergedFilters;

    public ImageFilterGroup() {
        resetFilters(null);
    }

    public ImageFilterGroup(List<ImageFilter> list) {
        resetFilters(list);
    }

    public void addFilter(ImageFilter imageFilter) {
        if (imageFilter == null) {
            return;
        }
        this.mFilters.add(imageFilter);
        updateMergedFilters();
    }

    /* renamed from: b */
    public final void m19973b() {
        int[] iArr = this.f4532j;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4532j = null;
        }
    }

    @Override // com.baidu.cloud.mediaprocess.basefilters.ImageFilter
    public void draw(int i) {
        draw(i, 0);
    }

    @Override // com.baidu.cloud.mediaprocess.basefilters.ImageFilter
    public void draw(int i, int i2) {
        List<ImageFilter> list;
        if (!isInitialized() || (list = this.mFilters) == null || list.size() <= 0) {
            return;
        }
        int size = this.mFilters.size();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = size - 1;
            if (i3 >= i5) {
                this.mFilters.get(i5).draw(i, i2);
                return;
            }
            GlUtil.bindTextureToFBO(this.f4532j[i4], 3553, this.f4531i);
            this.mFilters.get(i3).draw(i, this.f4531i);
            i = this.f4532j[i4];
            i4 = 1 - i4;
            i3++;
        }
    }

    public List<ImageFilter> getFilters() {
        return this.mFilters;
    }

    public List<ImageFilter> getMergedFilters() {
        return this.mMergedFilters;
    }

    @Override // com.baidu.cloud.mediaprocess.basefilters.ImageFilter
    public void onInit() {
        super.onInit();
        this.f4531i = GlUtil.createFrameBufferObject();
        for (ImageFilter imageFilter : this.mFilters) {
            imageFilter.init();
        }
    }

    @Override // com.baidu.cloud.mediaprocess.basefilters.ImageFilter
    public void onInputSizeChanged(int i, int i2) {
        int size = this.mFilters.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.mFilters.get(i3).onInputSizeChanged(i, i2);
        }
    }

    @Override // com.baidu.cloud.mediaprocess.basefilters.ImageFilter
    public void onOutputSizeChanged(int i, int i2) {
        if (i == this.mOutputWidth && i2 == this.mOutputHeight) {
            return;
        }
        this.mOutputWidth = i;
        this.mOutputHeight = i2;
        int size = this.mFilters.size();
        Log.i(f4530h, String.format("There are %d filters in this filter-chain.", Integer.valueOf(size)));
        for (int i3 = 0; i3 < size; i3++) {
            this.mFilters.get(i3).onOutputSizeChanged(i, i2);
        }
        m19973b();
        if (this.mFilters.size() <= 1) {
            return;
        }
        this.f4532j = new int[2];
        for (int i4 = 0; i4 < 2; i4++) {
            this.f4532j[i4] = GlUtil.createTextureObject(3553, i, i2);
        }
    }

    @Override // com.baidu.cloud.mediaprocess.basefilters.ImageFilter
    public void onReleased() {
        int[] iArr = this.f4532j;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4532j = null;
        }
        GlUtil.destroyFramebufferObject(this.f4531i);
        for (ImageFilter imageFilter : this.mFilters) {
            imageFilter.release();
        }
        super.onReleased();
    }

    public void resetFilters(List<ImageFilter> list) {
        List<ImageFilter> list2 = this.mFilters;
        if (list2 != null) {
            list2.clear();
        } else {
            this.mFilters = new ArrayList();
        }
        if (list != null) {
            this.mFilters.addAll(list);
        }
        updateMergedFilters();
    }

    public void updateMergedFilters() {
        if (this.mFilters == null) {
            return;
        }
        List<ImageFilter> list = this.mMergedFilters;
        if (list == null) {
            this.mMergedFilters = new ArrayList();
        } else {
            list.clear();
        }
        for (ImageFilter imageFilter : this.mFilters) {
            if (imageFilter instanceof ImageFilterGroup) {
                ImageFilterGroup imageFilterGroup = (ImageFilterGroup) imageFilter;
                imageFilterGroup.updateMergedFilters();
                List<ImageFilter> mergedFilters = imageFilterGroup.getMergedFilters();
                if (mergedFilters != null && !mergedFilters.isEmpty()) {
                    this.mMergedFilters.addAll(mergedFilters);
                }
            } else {
                this.mMergedFilters.add(imageFilter);
            }
        }
    }
}
