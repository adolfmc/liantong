package com.baidu.p120ar.statistic;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.StatisticLabels */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class StatisticLabels {
    StatisticLabels() {
    }

    public static Map<String, String> getDefineLables() {
        HashMap hashMap = new HashMap();
        hashMap.put("event_sdk_start", "调用SDK");
        hashMap.put("event_sdk_end", "结束调用SDK");
        hashMap.put("first_load_start_query", "发起查包请求");
        hashMap.put("first_load_query_success", "查包成功");
        hashMap.put("load_start_download", "开始下载资源包");
        hashMap.put("load_download_success", "资源包下载成功");
        hashMap.put("load_start_query", "分布加载");
        hashMap.put("first_load_file_manage_success", "完成解压");
        hashMap.put("event_case_first", "首次使用项目");
        hashMap.put("event_case_start", "使用项目");
        hashMap.put("event_case_end", "结束项目");
        hashMap.put("cloud_imgsearch_success", "云识图触发");
        hashMap.put("ondevice_imgsearch_success", "端识图触发");
        hashMap.put("imu_set_success", "IMU放置");
        hashMap.put("tracked", "2D跟踪");
        hashMap.put("slam_track_on", "SLAM放置");
        hashMap.put("event_lbsnpc_activties", "步导NPC动作触发");
        hashMap.put("event_face_masks_on", "人脸贴纸");
        hashMap.put("event_face_masks_off", "人脸贴纸跟丢");
        hashMap.put("event_face_expression", "表情触发");
        hashMap.put("model_camera_switch", "前后摄像头切换");
        hashMap.put("recommend_entrance_click", "切换case入口点击");
        hashMap.put("recommend_case_click", "case切换");
        hashMap.put("model_phone_shake", "摇一摇");
        hashMap.put("speech_type_result", "语音");
        hashMap.put("capture_screen", "拍照");
        hashMap.put("capture_screen_save", "拍照保存");
        hashMap.put("capture_screen_share", "拍照分享");
        hashMap.put("capture_video", "开始录制");
        hashMap.put("capture_video_save", "录制保存");
        hashMap.put("capture_video_share", "录制分享");
        hashMap.put("screenshot", "截屏");
        hashMap.put("event_campermission_show", "相机权限-弹窗");
        hashMap.put("event_campermission_yes", "相机权限-确认");
        hashMap.put("event_opencamera_no", "相机权限-取消");
        hashMap.put("event_micpermission_show", "麦克风权限-弹窗");
        hashMap.put("event_micpermission_yes", "麦克风权限-确认");
        hashMap.put("event_micpermission_no", "麦克风权限-取消");
        return hashMap;
    }
}
