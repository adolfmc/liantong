package com.sinovatech.unicom.common;

import org.greenrobot.eventbus.EventBus;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class EventBusUtils {
    public static int EVENTCODE_CLOSE_WEBSOCKET = 2;
    public static int EVENTCODE_REFRESH_WEBSOCKET = 1;
    public static int EVENTCODE_START_WEBSOCKET = 0;
    public static int EVENTMENG_ = 3017;
    public static int EVENTNOTICNEI_ = 3016;
    public static int EVENT_AUDIENCE_LIVELIST = 4001;
    public static int EVENT_AUDIENCE_MESSAGE = 4000;
    public static int EVENT_CHAGNE_NOTIC_NUMBER = 3100;
    public static int EVENT_CHANGE_MAIN_TAB_ICON = 3059;
    public static int EVENT_CLEARALL = 3014;
    public static int EVENT_COLLECTION_CHANGE_STATE = 4003;
    public static int EVENT_COLLECTION_DELETE = 4004;
    public static int EVENT_CUSTOM_MAIN_FINGER_ERROR = 3042;
    public static int EVENT_EDOP_XUNJIAN = 4002;
    public static int EVENT_FUWU_ADDTOPLIST = 3062;
    public static int EVENT_FUWU_REFRESHADAPTER = 3061;
    public static int EVENT_FUWU_REFRESHINIT = 3060;
    public static int EVENT_FUWU_SHOWTAB = 3063;
    public static int EVENT_FUWU_TIAOZHUAN = 3064;
    public static int EVENT_HOMESHORT = 3004;
    public static int EVENT_HOME_CLEAR_CALLBACK = 4006;
    public static int EVENT_HOME_REST_TIMER = 4005;
    public static int EVENT_JIEPIN = 3054;
    public static int EVENT_Jinrizhutui = 3022;
    public static int EVENT_LIVE_RTC = 9876;
    public static int EVENT_MAIN_CLOSE_MAIN_ACTIVITY = 3040;
    public static int EVENT_MAIN_CUSTOM_ACTIVITY_FINISH = 3041;
    public static int EVENT_MAIN_HOME_GUID = 3058;
    public static int EVENT_MAIN_HOME_GUID_RESET_BG = 3065;
    public static int EVENT_MAIN_HOME_GUID_SHOW_DEF_BG = 3066;
    public static int EVENT_MAIN_HOME_REFRESH = 3056;
    public static int EVENT_MAIN_TAB_CHANGE = 3050;
    public static int EVENT_MEMBER_INFO = 3057;
    public static int EVENT_MIANLIU_SHOUQ = 3052;
    public static int EVENT_MIANLIU_XIAOWO = 3051;
    public static int EVENT_NET = 3002;
    public static int EVENT_NOTIC = 3003;
    public static int EVENT_QIANDAOED = 3015;
    public static int EVENT_REDALL = 3013;
    public static int EVENT_REDFIND = 3011;
    public static int EVENT_REDFUWU = 3010;
    public static int EVENT_REDHOME = 3009;
    public static int EVENT_REDUSER = 3012;
    public static int EVENT_RELOAD_WEB = 3053;
    public static int EVENT_SHARE = 3000;
    public static int EVENT_SHARE2 = 3001;
    public static int EVENT_SHOUYE_TIAOZHUAN = 3065;
    public static int EVENT_TMS_APPCoinSubmit = 3024;
    public static int EVENT_TMS_APPLISTBYH5 = 3034;
    public static int EVENT_TMS_AppDownStatus = 3025;
    public static int EVENT_TMS_Banner_Hide = 3027;
    public static int EVENT_TMS_Banner_Show = 3026;
    public static int EVENT_TMS_HomeBanner_Update = 3021;
    public static int EVENT_TMS_SYMLXZZQ = 3030;
    public static int EVENT_TMS_VIDEO = 3023;
    public static int EVENT_TMS_XZYL = 3031;
    public static int EVENT_USER_HEADER = 3032;
    public static int EVENT_USER_YOUXIANG = 3033;
    public static int EVENT_Viewpager_down = 3019;
    public static int EVENT_Viewpager_up = 3020;
    public static int EVENT_WEB_TITLE = 3008;
    public static int EVENT_Weixin = 3018;
    public static int EVENT_YW_lOGIN = 3005;
    public static int EVENT_YW_lOGOUT = 3006;
    public static int EVENT_YW_lOGOUT_NEED = 3007;
    public static int EVENT_toutiao_Banner_Hide = 3029;
    public static int EVENT_toutiao_Banner_Show = 3028;
    public static int EVENT_toutiao_xinxiliu_Hide = 3036;
    public static int EVENT_toutiao_xinxiliu_Show = 3035;

    private EventBusUtils() {
    }

    public static void register(Object obj) {
        EventBus eventBus = EventBus.getDefault();
        if (eventBus.isRegistered(obj)) {
            return;
        }
        eventBus.register(obj);
    }

    public static void unregister(Object obj) {
        EventBus eventBus = EventBus.getDefault();
        if (eventBus.isRegistered(obj)) {
            eventBus.unregister(obj);
        }
    }

    public static void post(EventMessage eventMessage) {
        EventBus.getDefault().post(eventMessage);
    }

    public static void postSticky(EventMessage eventMessage) {
        EventBus.getDefault().postSticky(eventMessage);
    }

    public static void remove(EventMessage eventMessage) {
        EventBus.getDefault().removeStickyEvent(eventMessage);
    }
}
