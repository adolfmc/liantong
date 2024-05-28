package com.baidu.minivideo.arface.bean;

import android.graphics.Point;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DuArQualityData {

    /* renamed from: X */
    private static final String f7467X = "x";

    /* renamed from: Y */
    private static final String f7468Y = "y";
    public static List<List<Point>> sAllCurvesDefault = null;
    public static List<Point> sBlueCurvesDefault = null;
    public static List<Point> sComCurvesDefault = null;
    public static List<Point> sGreenCurvesDefault = null;
    public static float sIntensityBrightnessDefault = 0.03f;
    public static float sIntensityContrastDefault = 0.86f;
    public static float sIntensityCurvesDefault = 0.9f;
    public static float sIntensitySaturationDefault = 0.79f;
    public static float sIntensitySharpnessDefault = 0.7f;
    public static float sIntensitySmoothDefault = 0.65f;
    public static float sIntensitywhiteDefault = 0.2f;
    public static float sMaleIntensitySmoothDefault = 0.65f;
    public static float sMaleIntensitywhiteDefault;
    public static List<Point> sRedCurvesDefault;
    public float mIntensitySmooth = sIntensitySmoothDefault;
    public float mIntensitywhite = sIntensitywhiteDefault;
    public float mMaleIntensitySmooth = sMaleIntensitySmoothDefault;
    public float mMaleIntensitywhite = sMaleIntensitywhiteDefault;
    public float mIntensitySharpness = sIntensitySharpnessDefault;
    public float mIntensityBrightness = sIntensityBrightnessDefault;
    public float mIntensityContrast = sIntensityContrastDefault;
    public float mIntensitySaturation = sIntensitySaturationDefault;
    public float mIntensityCurves = sIntensityCurvesDefault;
    public List<Point> mComCurves = sComCurvesDefault;
    public List<Point> mRedCurves = sRedCurvesDefault;
    public List<Point> mGreenCurves = sGreenCurvesDefault;
    public List<Point> mBlueCurves = sBlueCurvesDefault;

    static {
        initDefaultCurves();
    }

    public static void initDefaultCurves() {
        sComCurvesDefault = new ArrayList();
        sComCurvesDefault.add(new Point(0, 0));
        sComCurvesDefault.add(new Point(75, 61));
        sComCurvesDefault.add(new Point(175, 172));
        sComCurvesDefault.add(new Point(255, 255));
        sRedCurvesDefault = new ArrayList();
        sRedCurvesDefault.add(new Point(0, 0));
        sRedCurvesDefault.add(new Point(121, 122));
        sRedCurvesDefault.add(new Point(255, 255));
        sGreenCurvesDefault = new ArrayList();
        sGreenCurvesDefault.add(new Point(0, 0));
        sGreenCurvesDefault.add(new Point(125, 117));
        sGreenCurvesDefault.add(new Point(255, 255));
        sBlueCurvesDefault = new ArrayList();
        sBlueCurvesDefault.add(new Point(0, 0));
        sBlueCurvesDefault.add(new Point(127, 111));
        sBlueCurvesDefault.add(new Point(255, 255));
        sAllCurvesDefault = new ArrayList();
        sAllCurvesDefault.add(sComCurvesDefault);
        sAllCurvesDefault.add(sRedCurvesDefault);
        sAllCurvesDefault.add(sGreenCurvesDefault);
        sAllCurvesDefault.add(sBlueCurvesDefault);
    }

    public static DuArQualityData parseJson(JSONObject jSONObject) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        if (jSONObject == null || jSONObject.length() == 0) {
            return null;
        }
        DuArQualityData duArQualityData = new DuArQualityData();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("female");
            if (jSONObject2 != null) {
                duArQualityData.mIntensitySmooth = (float) jSONObject2.optDouble("intensity_smooth");
                duArQualityData.mIntensitywhite = (float) jSONObject2.optDouble("intensity_white");
            }
            JSONObject jSONObject3 = jSONObject.getJSONObject("male");
            if (jSONObject3 != null) {
                duArQualityData.mMaleIntensitySmooth = (float) jSONObject3.optDouble("intensity_smooth");
                duArQualityData.mMaleIntensitywhite = (float) jSONObject3.optDouble("intensity_white");
            }
            duArQualityData.mIntensitySharpness = (float) jSONObject.optDouble("intensity_sharpness");
            duArQualityData.mIntensityBrightness = (float) jSONObject.optDouble("intensity_brightness");
            duArQualityData.mIntensityContrast = (float) jSONObject.optDouble("intensity_contrast");
            duArQualityData.mIntensitySaturation = (float) jSONObject.optDouble("intensity_saturation");
            JSONObject optJSONObject = jSONObject.optJSONObject("curves");
            if (optJSONObject != null && optJSONObject.length() > 0) {
                duArQualityData.mIntensityCurves = (float) optJSONObject.optDouble("intensity_curves");
                JSONArray jSONArray = optJSONObject.getJSONArray("composite");
                if (jSONArray != null && jSONArray.length() > 0) {
                    duArQualityData.mComCurves = new CopyOnWriteArrayList();
                    for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                        JSONObject jSONObject4 = jSONArray.getJSONObject(i9);
                        if (jSONObject4 == null || jSONObject4.length() <= 0) {
                            i7 = 0;
                            i8 = 0;
                        } else {
                            i8 = jSONObject4.optInt("x");
                            i7 = jSONObject4.optInt("y");
                        }
                        duArQualityData.mComCurves.add(new Point(i8, i7));
                    }
                }
                JSONArray jSONArray2 = optJSONObject.getJSONArray("red");
                if (jSONArray2 != null && jSONArray2.length() > 0) {
                    duArQualityData.mRedCurves = new CopyOnWriteArrayList();
                    for (int i10 = 0; i10 < jSONArray2.length(); i10++) {
                        JSONObject jSONObject5 = jSONArray2.getJSONObject(i10);
                        if (jSONObject5 == null || jSONObject5.length() <= 0) {
                            i5 = 0;
                            i6 = 0;
                        } else {
                            i6 = jSONObject5.optInt("x");
                            i5 = jSONObject5.optInt("y");
                        }
                        duArQualityData.mRedCurves.add(new Point(i6, i5));
                    }
                }
                JSONArray jSONArray3 = optJSONObject.getJSONArray("green");
                if (jSONArray3 != null && jSONArray3.length() > 0) {
                    duArQualityData.mGreenCurves = new CopyOnWriteArrayList();
                    for (int i11 = 0; i11 < jSONArray3.length(); i11++) {
                        JSONObject jSONObject6 = jSONArray3.getJSONObject(i11);
                        if (jSONObject6 == null || jSONObject6.length() <= 0) {
                            i3 = 0;
                            i4 = 0;
                        } else {
                            i4 = jSONObject6.optInt("x");
                            i3 = jSONObject6.optInt("y");
                        }
                        duArQualityData.mGreenCurves.add(new Point(i4, i3));
                    }
                }
                JSONArray jSONArray4 = optJSONObject.getJSONArray("blue");
                if (jSONArray4 != null && jSONArray4.length() > 0) {
                    duArQualityData.mBlueCurves = new CopyOnWriteArrayList();
                    for (int i12 = 0; i12 < jSONArray4.length(); i12++) {
                        JSONObject jSONObject7 = jSONArray4.getJSONObject(i12);
                        if (jSONObject7 == null || jSONObject7.length() <= 0) {
                            i = 0;
                            i2 = 0;
                        } else {
                            i2 = jSONObject7.optInt("x");
                            i = jSONObject7.optInt("y");
                        }
                        duArQualityData.mBlueCurves.add(new Point(i2, i));
                    }
                }
            }
            return duArQualityData;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static String qualityToJson(DuArQualityData duArQualityData) {
        if (duArQualityData == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("intensity_smooth", decimalScale(duArQualityData.mIntensitySmooth));
            jSONObject2.put("intensity_white", decimalScale(duArQualityData.mIntensitywhite));
            jSONObject.put("female", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("intensity_smooth", decimalScale(duArQualityData.mMaleIntensitySmooth));
            jSONObject3.put("intensity_white", decimalScale(duArQualityData.mMaleIntensitywhite));
            jSONObject.put("male", jSONObject3);
            jSONObject.put("intensity_sharpness", decimalScale(duArQualityData.mIntensitySharpness));
            jSONObject.put("intensity_brightness", decimalScale(duArQualityData.mIntensityBrightness));
            jSONObject.put("intensity_contrast", decimalScale(duArQualityData.mIntensityContrast));
            jSONObject.put("intensity_saturation", decimalScale(duArQualityData.mIntensitySaturation));
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("intensity_curves", decimalScale(duArQualityData.mIntensityCurves));
            putCurvesData("composite", jSONObject4, duArQualityData.mComCurves);
            putCurvesData("red", jSONObject4, duArQualityData.mRedCurves);
            putCurvesData("green", jSONObject4, duArQualityData.mGreenCurves);
            putCurvesData("blue", jSONObject4, duArQualityData.mBlueCurves);
            jSONObject.put("curves", jSONObject4);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static double decimalScale(float f) {
        return new BigDecimal(Double.toString(f)).setScale(2, 4).doubleValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isQualityPramChanged(org.json.JSONObject r10, org.json.JSONObject r11) {
        /*
            Method dump skipped, instructions count: 200
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.minivideo.arface.bean.DuArQualityData.isQualityPramChanged(org.json.JSONObject, org.json.JSONObject):boolean");
    }

    public void setIntensitySmooth(float f) {
        this.mIntensitySmooth = f;
    }

    public void setIntensitywhite(float f) {
        this.mIntensitywhite = f;
    }

    public void setIntensitySharpness(float f) {
        this.mIntensitySharpness = f;
    }

    public void setIntensityBrightness(float f) {
        this.mIntensityBrightness = f;
    }

    public void setIntensityContrast(float f) {
        this.mIntensityContrast = f;
    }

    public void setIntensitySaturation(float f) {
        this.mIntensitySaturation = f;
    }

    public void setIntensityCurves(float f) {
        this.mIntensityCurves = f;
    }

    public void setComCurves(List<Point> list) {
        this.mComCurves = list;
    }

    public void setRedCurves(List<Point> list) {
        this.mRedCurves = list;
    }

    public void setGreenCurves(List<Point> list) {
        this.mGreenCurves = list;
    }

    public void setBlueCurves(List<Point> list) {
        this.mBlueCurves = list;
    }

    private static void putCurvesData(String str, JSONObject jSONObject, List<Point> list) {
        try {
            if (TextUtils.isEmpty(str) || jSONObject == null || list == null || list.size() <= 0) {
                return;
            }
            JSONArray jSONArray = new JSONArray();
            for (Point point : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("x", point.x);
                jSONObject2.put("y", point.y);
                jSONArray.put(jSONObject2);
            }
            if (jSONArray.length() > 0) {
                jSONObject.put(str, jSONArray);
            }
        } catch (JSONException unused) {
        }
    }
}
