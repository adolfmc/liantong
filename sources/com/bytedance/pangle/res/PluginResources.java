package com.bytedance.pangle.res;

import android.app.Application;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.TypedValue;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.C3947g;
import com.bytedance.pangle.util.C3951j;
import java.io.File;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

@Keep
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PluginResources extends Resources {
    public String pluginPkg;

    public PluginResources(Resources resources, String str) {
        super(appendHostRes(resources), resources.getDisplayMetrics(), Zeus.getAppApplication().getResources().getConfiguration());
        this.pluginPkg = str;
    }

    public static AssetManager appendHostRes(Resources resources) {
        File parentFile;
        Application appApplication = Zeus.getAppApplication();
        if (C3947g.f9374a == null && (parentFile = appApplication.getCacheDir().getParentFile()) != null) {
            C3947g.f9374a = parentFile.getAbsolutePath();
        }
        String str = C3947g.f9374a;
        String m16636a = C3947g.m16636a(Zeus.getAppApplication());
        List<String> m16609b = C3951j.m16609b();
        C3905a c3905a = new C3905a();
        AssetManager assets = resources.getAssets();
        AssetManager assets2 = Zeus.getAppApplication().getAssets();
        HashSet hashSet = new HashSet(C3951j.m16611a(assets));
        List<String> m16611a = C3951j.m16611a(assets2);
        for (String str2 : m16609b) {
            if (!hashSet.contains(str2)) {
                assets = c3905a.m16729a(assets, str2, true);
            }
        }
        for (String str3 : m16611a) {
            if (!isOtherPlugin(str3, str, m16636a) && !hashSet.contains(str3) && !m16609b.contains(str3)) {
                assets = c3905a.m16729a(assets, str3, false);
            }
        }
        ZeusLogger.m16792i("Zeus/resources_pangle", "pluginAssets = " + C3951j.m16608b(assets));
        return assets;
    }

    private static boolean isOtherPlugin(String str, String str2, String str3) {
        String packageResourcePath = Zeus.getAppApplication().getPackageResourcePath();
        if (!TextUtils.isEmpty(str3)) {
            packageResourcePath = packageResourcePath.replaceFirst(str2, str3);
            str = str.replaceFirst(str2, str3);
        }
        ZeusLogger.m16794d("Zeus/resources_pangle", str + " " + packageResourcePath + " " + str2 + " " + str3);
        if (TextUtils.equals(str, packageResourcePath) || str.contains("/tinker/patch-")) {
            return false;
        }
        if (TextUtils.isEmpty(str2) || !str.contains(str2)) {
            return !TextUtils.isEmpty(str3) && str.contains(str3);
        }
        return true;
    }

    @Override // android.content.res.Resources
    @NonNull
    public CharSequence getText(int i) {
        try {
            return super.getText(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public Typeface getFont(int i) {
        try {
            return super.getFont(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public CharSequence getQuantityText(int i, int i2) {
        try {
            return super.getQuantityText(i, i2);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public String getString(int i) {
        try {
            return super.getString(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public String getString(int i, Object... objArr) {
        try {
            return super.getString(i, objArr);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public String getQuantityString(int i, int i2, Object... objArr) {
        try {
            return super.getQuantityString(i, i2, objArr);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public String getQuantityString(int i, int i2) {
        try {
            return super.getQuantityString(i, i2);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i, CharSequence charSequence) {
        try {
            return super.getText(i, charSequence);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public CharSequence[] getTextArray(int i) {
        try {
            return super.getTextArray(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public String[] getStringArray(int i) {
        try {
            return super.getStringArray(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public int[] getIntArray(int i) {
        try {
            return super.getIntArray(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public TypedArray obtainTypedArray(int i) {
        try {
            return super.obtainTypedArray(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public float getDimension(int i) {
        try {
            return super.getDimension(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelOffset(int i) {
        try {
            return super.getDimensionPixelOffset(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelSize(int i) {
        try {
            return super.getDimensionPixelSize(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public float getFraction(int i, int i2, int i3) {
        try {
            return super.getFraction(i, i2, i3);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        try {
            return super.getDrawable(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i, @Nullable Resources.Theme theme) {
        try {
            return super.getDrawable(i, theme);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @Nullable
    public Drawable getDrawableForDensity(int i, int i2) {
        try {
            return super.getDrawableForDensity(i, i2);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @Nullable
    public Drawable getDrawableForDensity(int i, int i2, @Nullable Resources.Theme theme) {
        try {
            return super.getDrawableForDensity(i, i2, theme);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public Movie getMovie(int i) {
        try {
            return super.getMovie(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public int getColor(int i) {
        try {
            return super.getColor(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public int getColor(int i, @Nullable Resources.Theme theme) {
        try {
            return super.getColor(i, theme);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public ColorStateList getColorStateList(int i) {
        try {
            return super.getColorStateList(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public ColorStateList getColorStateList(int i, @Nullable Resources.Theme theme) {
        try {
            return super.getColorStateList(i, theme);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public boolean getBoolean(int i) {
        try {
            return super.getBoolean(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public int getInteger(int i) {
        try {
            return super.getInteger(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public float getFloat(int i) {
        try {
            return super.getFloat(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public XmlResourceParser getLayout(int i) {
        try {
            return super.getLayout(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public XmlResourceParser getAnimation(int i) {
        try {
            return super.getAnimation(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public XmlResourceParser getXml(int i) {
        try {
            return super.getXml(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public InputStream openRawResource(int i) {
        try {
            return super.openRawResource(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    @NonNull
    public InputStream openRawResource(int i, TypedValue typedValue) {
        try {
            return super.openRawResource(i, typedValue);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int i) {
        try {
            return super.openRawResourceFd(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public void getValue(int i, TypedValue typedValue, boolean z) {
        try {
            super.getValue(i, typedValue, z);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public void getValueForDensity(int i, int i2, TypedValue typedValue, boolean z) {
        try {
            super.getValueForDensity(i, i2, typedValue, z);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public void getValue(String str, TypedValue typedValue, boolean z) {
        try {
            super.getValue(str, typedValue, z);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public String getResourceName(int i) {
        try {
            return super.getResourceName(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public String getResourcePackageName(int i) {
        try {
            return super.getResourcePackageName(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public String getResourceTypeName(int i) {
        try {
            return super.getResourceTypeName(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    @Override // android.content.res.Resources
    public String getResourceEntryName(int i) {
        try {
            return super.getResourceEntryName(i);
        } catch (Resources.NotFoundException e) {
            throw handleException(e);
        }
    }

    private Resources.NotFoundException handleException(Resources.NotFoundException notFoundException) {
        return new Resources.NotFoundException(("Resources#Assets: " + C3951j.m16608b(getAssets())) + "," + notFoundException.getMessage());
    }
}
