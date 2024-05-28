package com.sinovatech.unicom.separatemodule.audience.util;

import android.text.TextUtils;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.smallvideo.SearchVideoActivity;
import com.sinovatech.unicom.separatemodule.audience.smallvideo.SmallVideoActivity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PlayerUtil {
    public static boolean isNotPlayerType(String str) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        int hashCode = str.hashCode();
        if (hashCode == -1728134691) {
            if (str.equals("audienceMain")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == -547878989) {
            if (str.equals("searchVideo")) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode != 975628804) {
            if (hashCode == 1221368756 && str.equals("smallVideo")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("audience")) {
                c = 0;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
                return false;
            default:
                return true;
        }
    }

    public static BDCloudVideoView getPlayerByType(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1728134691) {
            if (hashCode != -547878989) {
                if (hashCode != 975628804) {
                    if (hashCode == 1221368756 && str.equals("smallVideo")) {
                        c = 2;
                    }
                } else if (str.equals("audience")) {
                    c = 0;
                }
            } else if (str.equals("searchVideo")) {
                c = 3;
            }
        } else if (str.equals("audienceMain")) {
            c = 1;
        }
        switch (c) {
            case 0:
                return AudienceActivity.bdCloudVideoView;
            case 1:
                return AudienceMainActivity.bdCloudVideoView;
            case 2:
                return SmallVideoActivity.bdCloudVideoView;
            case 3:
                return SearchVideoActivity.bdCloudVideoView;
            default:
                return null;
        }
    }

    public static void setWebPlayStatus(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1728134691) {
            if (hashCode != -547878989) {
                if (hashCode != 975628804) {
                    if (hashCode == 1221368756 && str.equals("smallVideo")) {
                        c = 2;
                    }
                } else if (str.equals("audience")) {
                    c = 0;
                }
            } else if (str.equals("searchVideo")) {
                c = 3;
            }
        } else if (str.equals("audienceMain")) {
            c = 1;
        }
        switch (c) {
            case 0:
                AudienceActivity.bdCloudIsWebPlayer = z;
                break;
            case 1:
                AudienceMainActivity.bdCloudIsWebPlayer = z;
                break;
            case 2:
                SmallVideoActivity.bdCloudIsWebPlayer = z;
                break;
            case 3:
                SearchVideoActivity.bdCloudIsWebPlayer = z;
                break;
        }
        getPlayerByType(str).isInWebView = z;
    }
}
