package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Special;
import com.p284qw.soul.permission.callbcak.SpecialPermissionListener;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/MusicPlayer")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MediaAudioPlayerJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() {
        String str;
        String str2;
        String optString = this.parameterJO.optString("type");
        final MediaPlayerEntity mediaPlayerEntity = new MediaPlayerEntity();
        final JSONObject jSONObject = new JSONObject();
        char c = 65535;
        try {
            switch (optString.hashCode()) {
                case -2129411402:
                    if (optString.equals("startPlay")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1252408828:
                    if (optString.equals("addMusic")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1024243566:
                    if (optString.equals("removeMusics")) {
                        c = 4;
                        break;
                    }
                    break;
                case -947188603:
                    if (optString.equals("removePlayer")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1442020093:
                    if (optString.equals("createPlayer")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1714697814:
                    if (optString.equals("stopPlay")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    MediaAudioPlayerManager.getInstance(this.activityContext).createMedia(new MediaAudioPlayerManager.SetOnMediaPlayerSates() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerJSPlugin.1
                        @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerManager.SetOnMediaPlayerSates
                        public void onMediaPlayerState(String str3, String str4) {
                            try {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("state", str3);
                                jSONObject2.put("msg", str4);
                                MediaAudioPlayerJSPlugin.this.callbackSuccess(jSONObject2);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    if (this.f18589wv != null) {
                        String title = TextUtils.isEmpty(this.f18589wv.getTitle()) ? "" : this.f18589wv.getTitle();
                        if (TextUtils.isEmpty(this.f18589wv.getUrl())) {
                            str = title;
                            str2 = "";
                        } else {
                            str = title;
                            str2 = this.f18589wv.getUrl();
                        }
                    } else {
                        str = "";
                        str2 = "";
                    }
                    TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", str, "百度播放器", str2, "com.baidu.cloud.cloudplayer", "1");
                    return;
                case 1:
                    final JSONObject optJSONObject = this.parameterJO.optJSONObject("data");
                    final JSONArray optJSONArray = optJSONObject.optJSONArray("audioList");
                    SoulPermission.getInstance().checkAndRequestPermission(Special.SYSTEM_ALERT, new SpecialPermissionListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerJSPlugin.2
                        @Override // com.p284qw.soul.permission.callbcak.SpecialPermissionListener
                        public void onDenied(Special special) {
                        }

                        @Override // com.p284qw.soul.permission.callbcak.SpecialPermissionListener
                        public void onGranted(Special special) {
                            try {
                                if (optJSONArray != null && optJSONArray.length() > 0) {
                                    String optString2 = optJSONObject.optString("iconTargetUrl");
                                    String optString3 = optJSONObject.optString("playIconUrl");
                                    String optString4 = optJSONObject.optString("closeDuration");
                                    if (!TextUtils.isEmpty(optString2)) {
                                        mediaPlayerEntity.setIconTargetUrl(optString2);
                                    }
                                    if (!TextUtils.isEmpty(optString3)) {
                                        mediaPlayerEntity.setPlayIconUrl(optString3);
                                    }
                                    if (!TextUtils.isEmpty(optString4)) {
                                        mediaPlayerEntity.setCloseDuration(optString4);
                                    }
                                    MediaAudioPlayerManager.getInstance(MediaAudioPlayerJSPlugin.this.activityContext).setMediaPlayerData(optJSONArray, mediaPlayerEntity, new MediaAudioPlayerManager.OnMediaPlayerCode() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerJSPlugin.2.1
                                        @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer.MediaAudioPlayerManager.OnMediaPlayerCode
                                        public void onMusicState(String str3, String str4) {
                                            JSONObject jSONObject2 = new JSONObject();
                                            try {
                                                jSONObject2.put("code", str3);
                                                jSONObject2.put("msg", str4);
                                                MediaAudioPlayerJSPlugin.this.callbackSuccess(jSONObject2);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                    return;
                                }
                                jSONObject.put("code", "03");
                                jSONObject.put("msg", "音频列表为空");
                                MediaAudioPlayerJSPlugin.this.callbackSuccess(jSONObject);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    return;
                case 2:
                    MediaAudioPlayerManager.getInstance(this.activityContext).startMediaPlayer();
                    return;
                case 3:
                    MediaAudioPlayerManager.getInstance(this.activityContext).stopMediaPlayer();
                    return;
                case 4:
                    MediaAudioPlayerManager.getInstance(this.activityContext).resetMediaDataSource();
                    return;
                case 5:
                    MediaAudioPlayerManager.getInstance(this.activityContext).destroyMediaPlayer();
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("AudioMediaPlayer", e.getMessage());
            callbackFail("10", "程序异常:" + e.getMessage());
        }
    }
}
