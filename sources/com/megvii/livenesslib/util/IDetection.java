package com.megvii.livenesslib.util;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.megvii.kas.livenessdetection.Detector;
import com.megvii.livenesslib.C5351R;
import com.megvii.livenesslib.FaceEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IDetection {
    private FaceEntity faceEntity;
    public View[] mAnimViews;
    private Context mContext;
    public int mCurShowIndex;
    public ArrayList<Detector.DetectionType> mDetectionSteps;
    private HashMap<Integer, Drawable> mDrawableCache;
    private int num;
    private View rootView;
    private String showNodHead;

    public IDetection(Context context, View view) {
        this.num = 3;
        this.mCurShowIndex = -1;
        this.showNodHead = "";
        this.mContext = context;
        this.rootView = view;
        this.mDrawableCache = new HashMap<>();
    }

    public IDetection(Context context, View view, String str, String str2, FaceEntity faceEntity) {
        this.num = 3;
        this.mCurShowIndex = -1;
        this.showNodHead = "";
        this.mContext = context;
        this.rootView = view;
        this.showNodHead = str2;
        this.faceEntity = faceEntity;
        this.mDrawableCache = new HashMap<>();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.num = Integer.valueOf(str).intValue();
            if (this.num <= 0) {
                this.num = 3;
            }
        } catch (Exception unused) {
            this.num = 3;
        }
    }

    public void animationInit() {
        int[] iArr;
        for (int i : new int[]{C5351R.C5352drawable.liveness_head_pitch, C5351R.C5352drawable.liveness_head_yaw, C5351R.C5352drawable.liveness_mouth_open_closed, C5351R.C5352drawable.liveness_eye_open_closed}) {
            this.mDrawableCache.put(Integer.valueOf(i), this.mContext.getResources().getDrawable(i));
        }
    }

    public void viewsInit() {
        this.mAnimViews = new View[2];
        this.mAnimViews[0] = this.rootView.findViewById(C5351R.C5353id.liveness_layout_first_layout);
        this.mAnimViews[1] = this.rootView.findViewById(C5351R.C5353id.liveness_layout_second_layout);
        for (View view : this.mAnimViews) {
            view.setVisibility(4);
        }
    }

    public void changeType(Detector.DetectionType detectionType, long j) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, C5351R.anim.liveness_rightin);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.mContext, C5351R.anim.liveness_leftout);
        int i = this.mCurShowIndex;
        if (i != -1) {
            this.mAnimViews[i].setVisibility(4);
            this.mAnimViews[this.mCurShowIndex].setAnimation(loadAnimation2);
        } else {
            this.mAnimViews[0].setVisibility(4);
            this.mAnimViews[0].startAnimation(loadAnimation2);
        }
        int i2 = this.mCurShowIndex;
        this.mCurShowIndex = (i2 != -1 && i2 == 0) ? 1 : 0;
        initAnim(detectionType, this.mAnimViews[this.mCurShowIndex]);
        this.mAnimViews[this.mCurShowIndex].setVisibility(0);
        this.mAnimViews[this.mCurShowIndex].startAnimation(loadAnimation);
    }

    private void initAnim(Detector.DetectionType detectionType, View view) {
        ImageView imageView = (ImageView) view.findViewById(C5351R.C5353id.detection_step_image);
        imageView.setImageDrawable(getDrawRes(detectionType));
        ((AnimationDrawable) imageView.getDrawable()).start();
        ((TextView) view.findViewById(C5351R.C5353id.detection_step_name)).setText(getDetectionName(detectionType));
    }

    private Drawable getDrawRes(Detector.DetectionType detectionType) {
        int i;
        switch (detectionType) {
            case POS_PITCH:
            case POS_PITCH_UP:
            case POS_PITCH_DOWN:
                i = C5351R.C5352drawable.liveness_head_pitch;
                break;
            case POS_YAW_LEFT:
            case POS_YAW_RIGHT:
            case POS_YAW:
                i = C5351R.C5352drawable.liveness_head_yaw;
                break;
            case MOUTH:
                i = C5351R.C5352drawable.liveness_mouth_open_closed;
                break;
            case BLINK:
                i = C5351R.C5352drawable.liveness_eye_open_closed;
                break;
            default:
                i = -1;
                break;
        }
        Drawable drawable = this.mDrawableCache.get(Integer.valueOf(i));
        if (drawable != null) {
            return drawable;
        }
        Drawable drawable2 = this.mContext.getResources().getDrawable(i);
        this.mDrawableCache.put(Integer.valueOf(i), drawable2);
        return drawable2;
    }

    private String getDetectionName(Detector.DetectionType detectionType) {
        int i = C53681.f12356xbe235f44[detectionType.ordinal()];
        if (i != 1) {
            switch (i) {
                case 4:
                    return "左转";
                case 5:
                    return "右转";
                case 6:
                    return "左右摇头";
                case 7:
                    return "张嘴";
                case 8:
                    return "眨眼";
                default:
                    return null;
            }
        }
        return "缓慢点头";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void detectionTypeInit() {
        ArrayList arrayList = new ArrayList();
        FaceEntity faceEntity = this.faceEntity;
        if (faceEntity == null || TextUtils.isEmpty(faceEntity.getActionNum())) {
            arrayList.add(Detector.DetectionType.BLINK);
            arrayList.add(Detector.DetectionType.MOUTH);
            arrayList.add(Detector.DetectionType.POS_YAW);
            if (!TextUtils.equals("1", this.showNodHead)) {
                arrayList.add(Detector.DetectionType.POS_PITCH);
            }
        } else {
            if (this.faceEntity.getActionNum().contains("1")) {
                arrayList.add(Detector.DetectionType.BLINK);
            }
            if (this.faceEntity.getActionNum().contains("2")) {
                arrayList.add(Detector.DetectionType.MOUTH);
            }
            if (this.faceEntity.getActionNum().contains("3")) {
                arrayList.add(Detector.DetectionType.POS_YAW);
            }
            if (this.faceEntity.getActionNum().contains("4")) {
                arrayList.add(Detector.DetectionType.POS_PITCH);
            }
        }
        Collections.shuffle(arrayList);
        this.num = this.num > arrayList.size() ? arrayList.size() : this.num;
        this.mDetectionSteps = new ArrayList<>(this.num);
        for (int i = 0; i < this.num; i++) {
            this.mDetectionSteps.add(arrayList.get(i));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void detectionTypeInit2() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Detector.DetectionType.BLINK);
        arrayList.add(Detector.DetectionType.MOUTH);
        arrayList.add(Detector.DetectionType.POS_PITCH);
        arrayList.add(Detector.DetectionType.POS_YAW);
        Collections.shuffle(arrayList);
        this.mDetectionSteps = new ArrayList<>();
        this.mDetectionSteps.add(arrayList.get(0));
    }

    public void onDestroy() {
        this.rootView = null;
        this.mContext = null;
        HashMap<Integer, Drawable> hashMap = this.mDrawableCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }
}
