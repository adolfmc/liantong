package com.huawei.hms.common.webserverpic;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class WebServerPic {
    public static final Parcelable.Creator<WebServerPic> CREATOR = new WebServerPicCreator();

    /* renamed from: a */
    private final Uri f11180a;

    /* renamed from: b */
    private final int f11181b;

    /* renamed from: c */
    private final int f11182c;

    public WebServerPic(Uri uri, int i, int i2) throws IllegalArgumentException {
        this.f11180a = uri;
        this.f11181b = i;
        this.f11182c = i2;
        if (uri == null) {
            throw new IllegalArgumentException("url is not able to be null");
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width and height should be positive or 0");
        }
    }

    public final int getHeight() {
        return this.f11182c;
    }

    public final Uri getUrl() {
        return this.f11180a;
    }

    public final int getWidth() {
        return this.f11181b;
    }

    public final String toString() {
        return String.format(Locale.ENGLISH, "Image %dx%d %s", Integer.valueOf(this.f11181b), Integer.valueOf(this.f11182c), this.f11180a.toString());
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getUrl(), i, false);
        SafeParcelWriter.writeInt(parcel, 2, getWidth());
        SafeParcelWriter.writeInt(parcel, 3, getHeight());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public WebServerPic(Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }
}
