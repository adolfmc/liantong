package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TextObject extends MediaObject {
    public static final Parcelable.Creator<TextObject> CREATOR = new Parcelable.Creator<TextObject>() { // from class: com.sina.weibo.sdk.api.TextObject.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final TextObject createFromParcel(Parcel parcel) {
            return new TextObject(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final TextObject[] newArray(int i) {
            return new TextObject[i];
        }
    };
    private static final long serialVersionUID = -5610314414793811821L;
    public String text;

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TextObject() {
    }

    protected TextObject(Parcel parcel) {
        this.text = parcel.readString();
    }

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.text);
    }
}
