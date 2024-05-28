package com.baidu.p120ar.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.UrlUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class UrlUtils {
    private static final String URL_ABILITY_CONF_FETCH = "/ar-client/capacity/conf";
    private static final String URL_AR_AUTH = "/artrack-bos/content/authentication";
    public static String URL_AR_PREFIX = "https://dusee.baidu.com";
    private static final String URL_CLOUD_RECG = "/artrack-bos/content/recognizeimgvis";
    private static final String URL_DEVICE_RECG = "/artrack-bos/content/onlinefeature";
    private static final String URL_GET_LIBRARY = "/ar-client/get_library";
    private static final String URL_LOGO_RECG = "/artrack-bos/content/RecognizeLogoVis";
    private static final String URL_PB_CLOUD_FRAME = "/ar-client/recognize";
    private static final String URL_PERFORMANCE_CTRL = "/artrack-bos/performance/items";
    private static final String URL_PERFORMANCE_STATISTIC = "/artrack-bos/performance/infos";
    private static final String URL_QUERY_RECOMMEND = "/artrack-bos/queryarrecommend";
    private static final String URL_QUERY_RESOURCE = "/artrack-bos/queryarresource";
    private static final String URL_SHARE_UPLOAD = "/artrack-bos/share/shareupload";
    private static final String URL_STATISTIC = "/artrack/count_ar";
    private static final String URL_STEP_LOADING_BATCH = "/artrack-bos/content/zipquery";
    private static final String URL_VPS_CREATE_LOC_SESSION = "/ar-vps-ui/createlocatesession";
    private static final String URL_VPS_CREATE_SESSION = "/ar-vps-ui/createsession";
    private static final String URL_VPS_DESTORY_SESSION = "/ar-vps-ui/sessiondestory";
    private static final String URL_VPS_GET_NAVRES = "/ar-vps-ui/getnavres";
    private static final String URL_VPS_GET_VPSRES = "/ar-vps-ui/getvpsres";
    private static final String URL_VPS_LOCATION = "/ar-vps-ui/getlocation";
    private static final String URL_VPS_LOC_DESTORY_SESSION = "/ar-vps-ui/destroylocatesession";
    private static final String URL_VPS_TRACK_FRAME = "/ar-vps-ui/trackframe";
    private static final String URL_XVISION = "/xvision/xvision_sync";

    public static String getAipAuthUrl() {
        return "https://aip.baidubce.com/rpc/2.0/brain/v1/ar/launchar";
    }

    public static String getChildFacePredictUrl() {
        return "https://mj.baidu.com/child-face";
    }

    public static String getARAuthUrl() {
        return URL_AR_PREFIX + "/artrack-bos/content/authentication";
    }

    public static String getQueryResourceUrl() {
        return URL_AR_PREFIX + "/artrack-bos/queryarresource";
    }

    public static String getRecommendUrl() {
        return URL_AR_PREFIX + "/artrack-bos/queryarrecommend";
    }

    public static String getShareUrl() {
        return URL_AR_PREFIX + "/artrack-bos/share/shareupload";
    }

    public static String getStatisticUrl() {
        return URL_AR_PREFIX + "/artrack/count_ar";
    }

    public static String getPerformanceStatisticUrl() {
        return URL_AR_PREFIX + "/artrack-bos/performance/infos";
    }

    public static String getPerformanceControlUrl() {
        return URL_AR_PREFIX + "/artrack-bos/performance/items";
    }

    public static String getDeviceRecgUrl() {
        return URL_AR_PREFIX + "/artrack-bos/content/onlinefeature";
    }

    public static String getCloudRecgUrl() {
        return URL_AR_PREFIX + "/artrack-bos/content/recognizeimgvis";
    }

    public static String getLogoUrl() {
        return URL_AR_PREFIX + "/artrack-bos/content/RecognizeLogoVis";
    }

    public static String getStepLoadingBatchUrl() {
        return URL_AR_PREFIX + "/artrack-bos/content/zipquery";
    }

    public static String getAbilitySchemeFetchUrl() {
        return URL_AR_PREFIX + "/ar-client/capacity/conf";
    }

    public static String getVPSSessionUrl() {
        return URL_AR_PREFIX + "/ar-vps-ui/createsession";
    }

    public static String getVPSDestoryUrl() {
        return URL_AR_PREFIX + "/ar-vps-ui/sessiondestory";
    }

    public static String getVpsTrackFrameUrl() {
        return URL_AR_PREFIX + "/ar-vps-ui/trackframe";
    }

    public static String getVpsTrackFrameUrlOnLine() {
        return URL_AR_PREFIX + "/ar-vps-ui/trackframe";
    }

    public static String getPBCloudFrameUrl() {
        return URL_AR_PREFIX + "/ar-client/recognize";
    }

    public static String getLibraryUrl() {
        return URL_AR_PREFIX + "/ar-client/get_library";
    }

    public static String getVpsCreateLocSessionUrl() {
        return URL_AR_PREFIX + "/ar-vps-ui/createlocatesession";
    }

    public static String getVpsLocationUrl() {
        return URL_AR_PREFIX + "/ar-vps-ui/getlocation";
    }

    public static String getVPSLocDestoryUrl() {
        return URL_AR_PREFIX + "/ar-vps-ui/destroylocatesession";
    }

    public static String getVpsGetNavResUrl() {
        return URL_AR_PREFIX + "/ar-vps-ui/getnavres";
    }

    public static String getVpsGetVPSResUrlOffline() {
        return URL_AR_PREFIX + "/ar-vps-ui/getvpsres";
    }

    public static String getVpsGetVPSResUrl() {
        return URL_AR_PREFIX + "/ar-vps-ui/getvpsres";
    }

    public static String getXvisionUrl() {
        return URL_AR_PREFIX + "/xvision/xvision_sync";
    }
}
