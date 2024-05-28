package com.sina.weibo.sdk.api;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.sina.weibo.sdk.p311b.C7092c;
import java.io.ByteArrayOutputStream;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ImageObject extends MediaObject {
    private static final int CONTENT_LENGTH_LIMIT = 1048576;
    public static final Parcelable.Creator<ImageObject> CREATOR = new Parcelable.Creator<ImageObject>() { // from class: com.sina.weibo.sdk.api.ImageObject.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ImageObject createFromParcel(Parcel parcel) {
            return new ImageObject(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ImageObject[] newArray(int i) {
            return new ImageObject[i];
        }
    };
    private static final int PATH_LENGTH_LIMIT = 512;
    private static final String TAG = "ImageObject";
    private static final long serialVersionUID = 8760548583231081050L;
    public byte[] imageData;
    public String imagePath;

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ImageObject() {
    }

    protected ImageObject(Parcel parcel) {
        this.imageData = parcel.createByteArray();
        this.imagePath = parcel.readString();
    }

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.imageData);
        parcel.writeString(this.imagePath);
    }

    public void setImageData(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            this.imageData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            C7092c.m8071b("ImageObject", "ImageObject :" + e.getMessage());
        }
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    public boolean checkArgs() {
        String str;
        byte[] bArr = this.imageData;
        if ((bArr != null && bArr.length != 0) || ((str = this.imagePath) != null && str.length() != 0)) {
            byte[] bArr2 = this.imageData;
            if (bArr2 != null && bArr2.length > 1048576) {
                C7092c.m8071b("ImageObject", "checkArgs fail, content is too large");
                return false;
            }
            String str2 = this.imagePath;
            if (str2 != null && str2.length() > 512) {
                C7092c.m8071b("ImageObject", "checkArgs fail, path is invalid");
                return false;
            }
            String str3 = this.imagePath;
            if (str3 == null || getFileSize(str3) <= 1048576) {
                return true;
            }
            C7092c.m8071b("ImageObject", "checkArgs fail, image content is too large");
            return false;
        }
        C7092c.m8071b("ImageObject", "checkArgs fail, all arguments are null");
        return false;
    }

    private int getFileSize(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        File file = new File(str);
        if (file.exists()) {
            return (int) file.length();
        }
        return 0;
    }
}
