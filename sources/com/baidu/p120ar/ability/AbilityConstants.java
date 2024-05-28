package com.baidu.p120ar.ability;

import com.baidu.p120ar.ARType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ability.AbilityConstants */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AbilityConstants {
    public static final String ABILITY_ANIME = "ability_anime";
    public static final String ABILITY_BEAUTY_FILTER = "ability_beauty_filter";
    public static final String ABILITY_BODY_DETECT = "ability_body_detect";
    public static final String ABILITY_CAPTURE = "ability_capture";
    public static final String ABILITY_CHILD_FACE = "ability_face_child";
    public static final String ABILITY_CLOUD_IR = "ability_cloud_image_recognition";
    public static final String ABILITY_COMMON_FILTER = "ability_common_filter";
    public static final String ABILITY_FACE_FILTER = "ability_face_filter";
    public static final String ABILITY_FACE_MODEL = "ability_face_model";
    public static final String ABILITY_GENDER_TRANS = "ability_gender_trans";
    public static final String ABILITY_GESTURE = "ability_gesture";
    public static final String ABILITY_HAIR_SEG = "ability_hair_segmentation";
    public static final String ABILITY_HAND_SKELETON = "ability_hand_skeleton";
    public static final String ABILITY_HEAD_SEG = "ability_head_segmentation";
    public static final String ABILITY_IMG_SEG = "ability_image_segmentation";
    public static final String ABILITY_IMU = "ability_imu";
    public static final String ABILITY_LOCAL_IR = "ability_local_image_recognition";
    public static final String ABILITY_LOGO_IR = "ability_logo_recognition";
    public static final String ABILITY_LUT_FILTER = "ability_lut_filter";
    public static final String ABILITY_MAKEUP_FILTER = "ability_makeup_filter";
    public static final String ABILITY_MARKER = "ability_marker";
    public static final String ABILITY_OBJ_DETECT = "ability_object_detect";
    public static final String ABILITY_POSE = "ability_pose";
    public static final String ABILITY_SKY_SEG = "ability_sky_segmentation";
    public static final String ABILITY_TRACK2D = "ability_image_track";
    public static final String ABILITY_TRACK3D = "ability_3d_track";
    public static final String ABILITY_VO = "ability_vo";
    public static final String ABILITY_VPAS = "ability_vpas";
    public static final String CLASS_ANIME = "com.baidu.ar.anime.AnimeAR";
    public static final String CLASS_BODY_DETECT = "com.baidu.ar.stretch.StretchAR";
    public static final String CLASS_CAPTURE = "com.baidu.ar.capture.FamilyWithChildAR";
    public static final String CLASS_CHILD_FACE = "com.baidu.ar.child.ChildAR";
    public static final String CLASS_CLOUD_IR = "com.baidu.ar.cloud.CloudAR";
    public static final String CLASS_FACE = "com.baidu.ar.face.FaceAR";
    public static final String CLASS_GENDER_TRANS = "com.baidu.ar.gendertrans.GenderTransAR";
    public static final String CLASS_GESTURE = "com.baidu.ar.gesture.GestureAR";
    public static final String CLASS_HAND_SKELETON = "com.baidu.ar.hand.HandAR";
    public static final String CLASS_HEAD_SEGMENTATION = "com.baidu.ar.headseg.HeadSegAR";
    public static final String CLASS_IMU = "com.baidu.ar.imu.ImuAR";
    public static final String CLASS_LOCAL_IR = "com.baidu.ar.recg.RecgAR";
    public static final String CLASS_LOGO_REC = "com.baidu.ar.logo.LogoAR";
    public static final String CLASS_MARKER = "com.baidu.ar.vps.marker.MarkerAR";
    public static final String CLASS_OBJ_DETECT = "com.baidu.ar.objdetect.ObjDetectAR";
    public static final String CLASS_POSE_TRACK = "com.baidu.ar.pose.PoseAR";
    public static final String CLASS_SEGMENTATION = "com.baidu.ar.seg.SegAR";
    public static final String CLASS_TRACK2D = "com.baidu.ar.track2d.Track2DAR";
    public static final String CLASS_TRACK3D = "com.baidu.ar.track3d.Track3DAR";
    public static final String CLASS_VO = "com.baidu.ar.vo.VOAR";
    public static final String CLASS_VPAS = "com.baidu.ar.vpas.VpasAR";
    public static final String DETECTOR_FACE = "FaceDetector";
    public static final String MDL_ADJUST_HAIR_SEG = "adjust_hair_segmentation";
    public static final int MDL_GESTURE_CONTROL = 5001;
    public static final int MDL_IMG_SEG_CONTROL = 5011;
    public static final String MDL_START_BODY_DETECT = "start_body_detecting";
    public static final String MDL_START_GESTURE = "start_gesture";
    public static final String MDL_START_HAIR_SEG = "start_hair_segmentation";
    public static final String MDL_START_IMG_SEG = "start_image_segmentation";
    public static final String MDL_START_OBJ_DETECT = "start_obj_detect";
    public static final String MDL_START_POSE_TRACK = "start_body_tracking";
    public static final String MDL_START_SKY_SEG = "start_sky_segmentation";
    public static final String MDL_STOP_BODY_DETECT = "stop_body_detecting";
    public static final String MDL_STOP_GESTURE = "stop_gesture";
    public static final String MDL_STOP_HAIR_SEG = "stop_hair_segmentation";
    public static final String MDL_STOP_IMG_SEG = "stop_image_segmentation";
    public static final String MDL_STOP_OBJ_DETECT = "stop_obj_detect";
    public static final String MDL_STOP_POSE_TRACK = "stop_body_tracking";
    public static final String MDL_STOP_SKY_SEG = "stop_sky_segmentation";
    public static final int MSG_TYPE_LOGO_HIT = 3007;
    public static final int MSG_TYPE_LOGO_START = 3005;
    public static final int MSG_TYPE_LOGO_STOP = 3006;
    public static final HashMap<String, String> ABILITY_2_CLASS_MAP = new HashMap() { // from class: com.baidu.ar.ability.AbilityConstants.1
        {
            put("ability_image_track", "com.baidu.ar.track2d.Track2DAR");
            put("ability_3d_track", "com.baidu.ar.track3d.Track3DAR");
            put("ability_imu", "com.baidu.ar.imu.ImuAR");
            put("ability_vo", "com.baidu.ar.vo.VOAR");
            put("ability_face_child", "com.baidu.ar.child.ChildAR");
            put("ability_vpas", "com.baidu.ar.vpas.VpasAR");
            put("ability_logo_recognition", "com.baidu.ar.logo.LogoAR");
            put("ability_local_image_recognition", "com.baidu.ar.recg.RecgAR");
            put("ability_cloud_image_recognition", "com.baidu.ar.cloud.CloudAR");
            put("ability_face_filter", "com.baidu.ar.face.FaceAR");
            put("ability_makeup_filter", "com.baidu.ar.face.FaceAR");
            put("ability_face_model", "com.baidu.ar.face.FaceAR");
            put("ability_gesture", "com.baidu.ar.gesture.GestureAR");
            put("ability_pose", "com.baidu.ar.pose.PoseAR");
            put("ability_body_detect", "com.baidu.ar.stretch.StretchAR");
            put("ability_object_detect", "com.baidu.ar.objdetect.ObjDetectAR");
            put("ability_image_segmentation", "com.baidu.ar.seg.SegAR");
            put("ability_hair_segmentation", "com.baidu.ar.seg.SegAR");
            put("ability_sky_segmentation", "com.baidu.ar.seg.SegAR");
            put("ability_head_segmentation", "com.baidu.ar.headseg.HeadSegAR");
            put("ability_hand_skeleton", "com.baidu.ar.hand.HandAR");
            put("ability_anime", "com.baidu.ar.anime.AnimeAR");
            put("ability_gender_trans", "com.baidu.ar.gendertrans.GenderTransAR");
            put("ability_capture", "com.baidu.ar.capture.FamilyWithChildAR");
            put("ability_marker", "com.baidu.ar.vps.marker.MarkerAR");
        }
    };
    public static final List<String> FILTER_ABILITY_LIST = new ArrayList<String>() { // from class: com.baidu.ar.ability.AbilityConstants.2
        {
            add("ability_face_filter");
            add("ability_makeup_filter");
            add("ability_beauty_filter");
            add("ability_lut_filter");
        }
    };
    public static final HashMap<String, String> START_ABILITY_2_ABILITY_MAP = new HashMap() { // from class: com.baidu.ar.ability.AbilityConstants.3
        {
            put("start_gesture", "ability_gesture");
            put("start_image_segmentation", "ability_image_segmentation");
            put("start_body_tracking", "ability_pose");
            put("start_body_detecting", "ability_body_detect");
            put("start_hair_segmentation", "ability_hair_segmentation");
            put("start_sky_segmentation", "ability_sky_segmentation");
            put("start_obj_detect", "ability_object_detect");
        }
    };
    public static final HashMap<String, String> STOP_ABILITY_2_ABILITY_MAP = new HashMap() { // from class: com.baidu.ar.ability.AbilityConstants.4
        {
            put("stop_gesture", "ability_gesture");
            put("stop_image_segmentation", "ability_image_segmentation");
            put("stop_body_tracking", "ability_pose");
            put("stop_body_detecting", "ability_body_detect");
            put("stop_hair_segmentation", "ability_hair_segmentation");
            put("stop_sky_segmentation", "ability_sky_segmentation");
            put("stop_obj_detect", "ability_object_detect");
        }
    };
    public static final HashMap<ARType, String> ARTYPE_2_ABILITY_MAP = new HashMap() { // from class: com.baidu.ar.ability.AbilityConstants.5
        {
            put(ARType.IMU, "ability_imu");
            put(ARType.TRACK_2D, "ability_image_track");
            put(ARType.TRACK_3D, "ability_3d_track");
            put(ARType.VO, "ability_vo");
            put(ARType.FACE, "ability_face_model");
            put(ARType.CLOUD_IR, "ability_cloud_image_recognition");
            put(ARType.ON_DEVICE_IR, "ability_local_image_recognition");
            put(ARType.VPAS, "ability_vpas");
            put(ARType.MARKER, "ability_marker");
        }
    };
    public static final HashMap<String, String> DETECTOR_2_CLASS_MAP = new HashMap() { // from class: com.baidu.ar.ability.AbilityConstants.6
        {
            put("FaceDetector", "com.baidu.ar.face.FaceAR");
        }
    };
}
