package com.baidu.p120ar.face;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.FaceResultData */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceResultData implements Parcelable, IFaceResultData {
    public static final Parcelable.Creator<FaceResultData> CREATOR = new Parcelable.Creator<FaceResultData>() { // from class: com.baidu.ar.face.FaceResultData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FaceResultData createFromParcel(Parcel parcel) {
            return new FaceResultData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FaceResultData[] newArray(int i) {
            return new FaceResultData[i];
        }
    };
    private int mAlgoImageHeight;
    private int mAlgoImageWidth;
    private float[] mFaceBoxes;
    private int[] mFaceIds;
    private List<PointF> mFacePoints;
    private boolean mFrontCamera;
    private float[] mGenders;
    private List<float[]> mHeadPoses;
    private float[] mNormalizedFaceBoxes;
    private List<PointF> mNormalizedFacePoints;
    private long mTimestamp;
    private boolean mTracked;
    private List<String[]> mTriggers;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FaceResultData() {
        this.mTracked = false;
        this.mFaceBoxes = null;
        this.mFacePoints = null;
        this.mHeadPoses = null;
        this.mFaceIds = null;
        this.mGenders = null;
        this.mFrontCamera = true;
        this.mTriggers = null;
        this.mNormalizedFaceBoxes = null;
        this.mNormalizedFacePoints = null;
    }

    public void setTracked(boolean z) {
        this.mTracked = z;
    }

    public void setAlgoImageWidth(int i) {
        this.mAlgoImageWidth = i;
        this.mNormalizedFaceBoxes = null;
        this.mNormalizedFacePoints = null;
    }

    public void setAlgoImageHeight(int i) {
        this.mAlgoImageHeight = i;
        this.mNormalizedFaceBoxes = null;
        this.mNormalizedFacePoints = null;
    }

    public void setFaceBoxes(float[] fArr) {
        this.mFaceBoxes = fArr;
        this.mNormalizedFaceBoxes = null;
    }

    public void setFacePoints(List<PointF> list) {
        this.mFacePoints = list;
        this.mNormalizedFacePoints = null;
    }

    public void setGenders(float[] fArr) {
        this.mGenders = fArr;
    }

    public void setFaceIds(int[] iArr) {
        this.mFaceIds = iArr;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public boolean isTracked() {
        return this.mTracked;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public int getAlgoImageWidth() {
        return this.mAlgoImageWidth;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public int getAlgoImageHeight() {
        return this.mAlgoImageHeight;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public int getFaceCount() {
        float[] fArr = this.mFaceBoxes;
        if (fArr == null) {
            return 0;
        }
        return fArr.length / 4;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public float[] getFaceBoxes() {
        return this.mFaceBoxes;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public float[] getNormalizedFaceBoxes() {
        float[] fArr;
        int i;
        int i2;
        if (this.mNormalizedFaceBoxes == null && (fArr = this.mFaceBoxes) != null && (i = this.mAlgoImageHeight) > 0 && (i2 = this.mAlgoImageWidth) > 0) {
            this.mNormalizedFaceBoxes = new float[fArr.length];
            float f = i2;
            float f2 = i;
            int length = fArr.length;
            for (int i3 = 0; i3 < length; i3++) {
                if (i3 % 2 == 0) {
                    this.mNormalizedFaceBoxes[i3] = this.mFaceBoxes[i3] / f;
                } else {
                    this.mNormalizedFaceBoxes[i3] = this.mFaceBoxes[i3] / f2;
                }
            }
        }
        return this.mNormalizedFaceBoxes;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public List<PointF> getFacePoints() {
        return this.mFacePoints;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public List<PointF> getSingleFacePoints(int i) {
        List<PointF> list;
        int faceCount = getFaceCount();
        if (i < 0 || i >= faceCount || (list = this.mFacePoints) == null) {
            return null;
        }
        int size = list.size() / faceCount;
        int i2 = i * size;
        return this.mFacePoints.subList(i2, size + i2);
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public List<PointF> getNormalizedFacePoints() {
        if (this.mNormalizedFacePoints == null && this.mFacePoints != null && this.mAlgoImageHeight > 0 && this.mAlgoImageWidth > 0) {
            this.mNormalizedFacePoints = new ArrayList();
            float f = this.mAlgoImageWidth;
            float f2 = this.mAlgoImageHeight;
            int size = this.mFacePoints.size();
            for (int i = 0; i < size; i++) {
                PointF pointF = this.mFacePoints.get(i);
                this.mNormalizedFacePoints.add(new PointF(pointF.x / f, pointF.y / f2));
            }
        }
        return this.mNormalizedFacePoints;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public List<PointF> getSingleNormalizedFacePoints(int i) {
        int faceCount = getFaceCount();
        List<PointF> normalizedFacePoints = getNormalizedFacePoints();
        if (i < 0 || i >= faceCount || normalizedFacePoints == null) {
            return null;
        }
        int size = normalizedFacePoints.size() / faceCount;
        int i2 = i * size;
        return normalizedFacePoints.subList(i2, size + i2);
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public float[] getGenders() {
        return this.mGenders;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public int[] getFaceIds() {
        return this.mFaceIds;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public boolean isFrontCamera() {
        return this.mFrontCamera;
    }

    public void setFrontCamera(boolean z) {
        this.mFrontCamera = z;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public List<String[]> getTriggers() {
        return this.mTriggers;
    }

    public void setTriggers(List<String[]> list) {
        this.mTriggers = list;
    }

    public void setHeadPoses(List<float[]> list) {
        this.mHeadPoses = list;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public List<float[]> getHeadPoses() {
        return this.mHeadPoses;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    @Override // com.baidu.p120ar.face.IFaceResultData
    public long getTimestamp() {
        return this.mTimestamp;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        List<String[]> list;
        parcel.writeByte(this.mTracked ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mAlgoImageWidth);
        parcel.writeInt(this.mAlgoImageHeight);
        parcel.writeFloatArray(this.mFaceBoxes);
        parcel.writeTypedList(this.mFacePoints);
        parcel.writeIntArray(this.mFaceIds);
        parcel.writeFloatArray(this.mGenders);
        parcel.writeByte(this.mFrontCamera ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.mTimestamp);
        List<float[]> list2 = this.mHeadPoses;
        if (list2 == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(list2.size());
            for (float[] fArr : this.mHeadPoses) {
                parcel.writeFloatArray(fArr);
            }
        }
        List<String[]> list3 = this.mTriggers;
        int size = list3 != null ? list3.size() : -1;
        parcel.writeInt(size);
        if (size <= 0 || (list = this.mTriggers) == null) {
            return;
        }
        for (String[] strArr : list) {
            parcel.writeStringArray(strArr);
        }
    }

    protected FaceResultData(Parcel parcel) {
        this.mTracked = false;
        this.mFaceBoxes = null;
        this.mFacePoints = null;
        this.mHeadPoses = null;
        this.mFaceIds = null;
        this.mGenders = null;
        this.mFrontCamera = true;
        this.mTriggers = null;
        this.mNormalizedFaceBoxes = null;
        this.mNormalizedFacePoints = null;
        this.mTracked = parcel.readByte() != 0;
        this.mAlgoImageWidth = parcel.readInt();
        this.mAlgoImageHeight = parcel.readInt();
        this.mFaceBoxes = parcel.createFloatArray();
        this.mFacePoints = parcel.createTypedArrayList(PointF.CREATOR);
        this.mFaceIds = parcel.createIntArray();
        this.mGenders = parcel.createFloatArray();
        this.mFrontCamera = parcel.readByte() != 0;
        this.mTimestamp = parcel.readLong();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            this.mHeadPoses = null;
        } else {
            this.mHeadPoses = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                this.mHeadPoses.add(parcel.createFloatArray());
            }
        }
        int readInt2 = parcel.readInt();
        if (readInt2 < 0) {
            this.mTriggers = null;
            return;
        }
        this.mTriggers = new ArrayList();
        for (int i2 = 0; i2 < readInt2; i2++) {
            this.mTriggers.add(parcel.createStringArray());
        }
    }
}
