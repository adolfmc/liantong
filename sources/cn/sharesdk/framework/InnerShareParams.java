package cn.sharesdk.framework;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mabeijianxi.smallvideorecord2.MediaRecorderActivity;
import com.mob.tools.utils.Hashon;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class InnerShareParams {
    protected static final String ACTIVITY = "activity";
    protected static final String ADDRESS = "address";
    protected static final String AUTHOR = "author";
    protected static final String COMMENT = "comment";
    protected static final String CONTENT_TYPE = "contentType";
    protected static final String CUSTOM_FLAG = "customFlag";
    protected static final String DOUYIN_MIX_FILE = "douyin_mix_file";
    protected static final String EXECUTE_URL = "executeUrl";
    protected static final String EXT_INFO = "extInfo";
    protected static final String FILE_IMAGE = "file_image";
    protected static final String FILE_PATH = "filePath";
    protected static final String FILE_STICKER = "file_sticker";
    protected static final String FILE_VIDEO = "file_video";
    protected static final String GROPU_ID = "groupID";
    protected static final String HASHTAG = "HASHTAG";
    protected static final String HASHTAGS = "HASHTAGS";
    protected static final String HIDDEN = "hidden";
    protected static final String IMAGE_ARRAY = "imageArray";
    protected static final String IMAGE_DATA = "imageData";
    protected static final String IMAGE_FILE_PROVIDER_PATH = "image_provider_path";
    protected static final String IMAGE_LIST = "imageList";
    protected static final String IMAGE_PATH = "imagePath";
    protected static final String IMAGE_URI_LIST = "imageUriList";
    protected static final String IMAGE_URL = "imageUrl";
    protected static final String IMAGE_URL_LIST = "imageUrlList";
    protected static final String INSTALL_URL = "installUrl";
    protected static final String IS_FAMILY = "isFamily";
    protected static final String IS_FRIEND = "isFriend";
    protected static final String IS_LOG_EVEN = "isLogEven";
    protected static final String IS_PUBLIC = "isPublic";
    protected static final String IS_SHARE_TENCENT_WEIBO = "isShareTencentWeibo";
    protected static final String KAKAO_CUSTOM_TEMPLATE = "kaokao_custom_template";
    protected static final String KAKAO_CUSTOM_TEMPLATEID = "kakao_custom_templateid";
    protected static final String KAKAO_TEMPLATE_ADDBUTTON_MOBILEWEBURL = "kaokao_template_button_mobileweburl";
    protected static final String KAKAO_TEMPLATE_ADDBUTTON_TITLE = "kakao_template_button_title";
    protected static final String KAKAO_TEMPLATE_ADDBUTTON_WEBURL = "kakao_template_button_weburl";
    protected static final String KAKAO_TEMPLATE_COMMENTCOUNT = "kakao_template_commentcount";
    protected static final String KAKAO_TEMPLATE_DISCOUNTPRICE = "kakao_template_discountprice";
    protected static final String KAKAO_TEMPLATE_DISCOUNTRATE = "kakao_template_discountrate";
    protected static final String KAKAO_TEMPLATE_LIKECOUNT = "kakao_template_likecount";
    protected static final String KAKAO_TEMPLATE_MOBILEWEBURL = "kakao_template_mobileweburl";
    protected static final String KAKAO_TEMPLATE_PRODUCTNAME = "kakao_template_productname";
    protected static final String KAKAO_TEMPLATE_REGULARPRICE = "kaokao_template_regularprice";
    protected static final String KAKAO_TEMPLATE_SHARECOUNT = "kakao_template_sharecount";
    protected static final String KAKAO_TEMPLATE_WEBURL = "kakao_template_weburl";
    protected static final String LATITUDE = "latitude";
    protected static final String LC_CREATE_AT = "lc_create_at";
    protected static final String LC_DISPLAY_NAME = "lc_display_name";
    protected static final String LC_IMAGE = "lc_image";
    protected static final String LC_OBJECT_TYPE = "lc_object_type";
    protected static final String LC_SUMMARY = "lc_summary";
    protected static final String LC_URL = "lc_url";
    protected static final String LINKEDIN_DESCRIPTION = "linkedinDescription";
    protected static final String LONGITUDE = "longitude";
    protected static final String LOOPSHARE_PARAMS_MOBID = "loopshare_params_mobid";
    protected static final String MUSIC_URL = "musicUrl";
    protected static final String NOTEBOOK = "notebook";
    protected static final String QQ_MINI_PROGRAM_APPID = "mini_program_appid";
    protected static final String QQ_MINI_PROGRAM_PATH = "mini_program_path";
    protected static final String QQ_MINI_PROGRAM_TYPE = "mini_program_type";
    protected static final String QUOTE = "QUOTE";
    protected static final String SAFETY_LEVEL = "safetyLevel";
    protected static final String SCENCE = "scene";
    protected static final String SHARE_TO_PUBLISH = "shareToPublish";
    protected static final String SHARE_TYPE = "shareType";
    protected static final String SITE = "site";
    protected static final String SITE_URL = "siteUrl";
    protected static final String STACK = "stack";
    protected static final String SUBREDDIT = "sr";
    protected static final String TAGS = "tags";
    protected static final String TAG_POSITION = "douyin_tag_position";
    protected static final String TEXT = "text";
    protected static final String TITLE = "title";
    protected static final String TITLE_URL = "titleUrl";
    protected static final String URL = "url";
    protected static final String VENUE_DESCRIPTION = "venueDescription";
    protected static final String VENUE_NAME = "venueName";
    protected static final String VIDEO_ARRAY = "videoArray";
    protected static final String VIDEO_PATH_OASIS = "video_path_oasis";
    protected static final String VIDEO_URI = "video_uri";
    protected static final String VIDEO_URI_OASIS = "video_uri_oasis";
    protected static final String VIDEO_URL = "video_url";
    protected static final String WX_MINIPROGRAM_MINIPROGRAM_TYPE = "wxMiniProgramType";
    protected static final String WX_MINIPROGRAM_PATH = "wxPath";
    protected static final String WX_MINIPROGRAM_USER_NAME = "wxUserName";
    protected static final String WX_MINIPROGRAM_WITH_SHARETICKET = "wxWithShareTicket";
    protected static final String WX_RESERVED = "wx_reserved";
    protected static final String WX_SCENE = "wx_scene";
    protected static final String WX_TEMPLATEID = "wx_templateid";
    private HashMap<String, Object> params;

    public InnerShareParams() {
        this.params = new HashMap<>();
    }

    public InnerShareParams(HashMap<String, Object> hashMap) {
        this();
        if (hashMap != null) {
            this.params.putAll(hashMap);
        }
    }

    public InnerShareParams(String str) {
        this(new Hashon().fromJson(str));
    }

    public void set(String str, Object obj) {
        this.params.put(str, obj);
    }

    public <T> T get(String str, Class<T> cls) {
        Object obj = this.params.get(str);
        if (obj != null) {
            return cls.cast(obj);
        }
        if (Byte.class.equals(cls) || Byte.TYPE.equals(cls)) {
            return cls.cast(new Byte((byte) 0));
        }
        if (Short.class.equals(cls) || Short.TYPE.equals(cls)) {
            return cls.cast(new Short((short) 0));
        }
        if (Integer.class.equals(cls) || Integer.TYPE.equals(cls)) {
            return cls.cast(new Integer(0));
        }
        if (Long.class.equals(cls) || Long.TYPE.equals(cls)) {
            return cls.cast(new Long(0L));
        }
        if (Float.class.equals(cls) || Float.TYPE.equals(cls)) {
            return cls.cast(new Float(0.0f));
        }
        if (Double.class.equals(cls) || Double.TYPE.equals(cls)) {
            return cls.cast(new Double(0.0d));
        }
        if (Boolean.class.equals(cls) || Boolean.TYPE.equals(cls)) {
            return cls.cast(false);
        }
        if (HashMap.class.equals(cls) || Map.class.equals(cls)) {
            return cls.cast(new HashMap());
        }
        return null;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> hashMap = this.params;
        return hashMap == null ? new HashMap<>() : hashMap;
    }

    public String toString() {
        try {
            return new Hashon().fromHashMap(this.params);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return null;
        }
    }

    public void setText(String str) {
        set("text", str);
    }

    public String getText() {
        return (String) get("text", String.class);
    }

    public void setImagePath(String str) {
        set("imagePath", str);
    }

    public String getImagePath() {
        return (String) get("imagePath", String.class);
    }

    public void setImageUrl(String str) {
        set("imageUrl", str);
    }

    public String getImageUrl() {
        return (String) get("imageUrl", String.class);
    }

    public void setFilePath(String str) {
        set("filePath", str);
    }

    public String getFilePath() {
        return (String) get("filePath", String.class);
    }

    public String getTitle() {
        return (String) get("title", String.class);
    }

    public void setTitle(String str) {
        set("title", str);
    }

    public String getNotebook() {
        return (String) get("notebook", String.class);
    }

    public void setNotebook(String str) {
        set("notebook", str);
    }

    public String getStack() {
        return (String) get("stack", String.class);
    }

    public void setStack(String str) {
        set("stack", str);
    }

    public String[] getTags() {
        return (String[]) get("tags", String[].class);
    }

    public void setTags(String[] strArr) {
        set("tags", strArr);
    }

    public boolean isPublic() {
        return ((Boolean) get("isPublic", Boolean.class)).booleanValue();
    }

    public void setPublic(boolean z) {
        set("isPublic", Boolean.valueOf(z));
    }

    public boolean isFriend() {
        return ((Boolean) get("isFriend", Boolean.class)).booleanValue();
    }

    public void setFriend(boolean z) {
        set("isFriend", Boolean.valueOf(z));
    }

    public boolean isFamily() {
        return ((Boolean) get("isFamily", Boolean.class)).booleanValue();
    }

    public void setFamily(boolean z) {
        set("isFamily", Boolean.valueOf(z));
    }

    public int getSafetyLevel() {
        return ((Integer) get("safetyLevel", Integer.class)).intValue();
    }

    public void setSafetyLevel(int i) {
        set("safetyLevel", Integer.valueOf(i));
    }

    public int getContentType() {
        return ((Integer) get("contentType", Integer.class)).intValue();
    }

    public void setContentType(int i) {
        set("contentType", Integer.valueOf(i));
    }

    public int getHidden() {
        return ((Integer) get("hidden", Integer.class)).intValue();
    }

    public void setHidden(int i) {
        set("hidden", Integer.valueOf(i));
    }

    public void setVenueName(String str) {
        set("venueName", str);
    }

    public String getVenueName() {
        return (String) get("venueName", String.class);
    }

    public String getVenueDescription() {
        return (String) get("venueDescription", String.class);
    }

    public void setVenueDescription(String str) {
        set("venueDescription", str);
    }

    public String getLinkedinDsscription() {
        return (String) get("linkedinDescription", String.class);
    }

    public void setLinkedinDescription(String str) {
        set("linkedinDescription", str);
    }

    public float getLatitude() {
        return ((Float) get("latitude", Float.class)).floatValue();
    }

    public void setLatitude(float f) {
        set("latitude", Float.valueOf(f));
    }

    public float getLongitude() {
        return ((Float) get("longitude", Float.class)).floatValue();
    }

    public void setLongitude(float f) {
        set("longitude", Float.valueOf(f));
    }

    public String getTitleUrl() {
        return (String) get("titleUrl", String.class);
    }

    public void setTitleUrl(String str) {
        set("titleUrl", str);
    }

    public String getComment() {
        return (String) get("comment", String.class);
    }

    public void setComment(String str) {
        set("comment", str);
    }

    public String getUrl() {
        return (String) get("url", String.class);
    }

    public void setUrl(String str) {
        set("url", str);
    }

    public String getAddress() {
        return (String) get("address", String.class);
    }

    public void setAddress(String str) {
        set("address", str);
    }

    public String getMusicUrl() {
        return (String) get("musicUrl", String.class);
    }

    public void setMusicUrl(String str) {
        set("musicUrl", str);
    }

    public String getSite() {
        return (String) get("site", String.class);
    }

    public void setSite(String str) {
        set("site", str);
    }

    public String getSiteUrl() {
        return (String) get("siteUrl", String.class);
    }

    public void setSiteUrl(String str) {
        set("siteUrl", str);
    }

    public String getGroupId() {
        return (String) get("groupID", String.class);
    }

    public void setGroupId(String str) {
        set("groupID", str);
    }

    public String getAuthor() {
        return (String) get("author", String.class);
    }

    public void setAuthor(String str) {
        set("author", str);
    }

    public Bitmap getImageData() {
        return (Bitmap) get("imageData", Bitmap.class);
    }

    public void setImageData(Bitmap bitmap) {
        set("imageData", bitmap);
    }

    public int getShareType() {
        return ((Integer) get("shareType", Integer.class)).intValue();
    }

    public void setShareType(int i) {
        set("shareType", Integer.valueOf(i));
    }

    public int getScence() {
        return ((Integer) get("scene", Integer.class)).intValue();
    }

    public void setScence(int i) {
        set("scene", Integer.valueOf(i));
    }

    public String getExtInfo() {
        return (String) get("extInfo", String.class);
    }

    public void setExtInfo(String str) {
        set("extInfo", str);
    }

    public String[] getCustomFlag() {
        return (String[]) get("customFlag", String[].class);
    }

    public void setCustomFlag(String[] strArr) {
        set("customFlag", strArr);
    }

    public boolean isShareTencentWeibo() {
        return ((Boolean) get("isShareTencentWeibo", Boolean.class)).booleanValue();
    }

    public void setShareTencentWeibo(boolean z) {
        set("isShareTencentWeibo", Boolean.valueOf(z));
    }

    public String[] getImageArray() {
        return (String[]) get("imageArray", String[].class);
    }

    public void setImageArray(String[] strArr) {
        set("imageArray", strArr);
    }

    public String[] getVideoPathArray() {
        return (String[]) get("videoArray", String[].class);
    }

    public void setVideoPathArray(String[] strArr) {
        set("videoArray", strArr);
    }

    public String getWxUserName() {
        return (String) get("wxUserName", String.class);
    }

    public void setWxUserName(String str) {
        set("wxUserName", str);
    }

    public String getWxPath() {
        return (String) get("wxPath", String.class);
    }

    public void setWxPath(String str) {
        set("wxPath", str);
    }

    public boolean getWxWithShareTicket() {
        return ((Boolean) get("wxWithShareTicket", Boolean.class)).booleanValue();
    }

    public void setWxWithShareTicket(boolean z) {
        set("wxWithShareTicket", Boolean.valueOf(z));
    }

    public int getWxMiniProgramType() {
        return ((Integer) get("wxMiniProgramType", Integer.class)).intValue();
    }

    public void setWxMiniProgramType(int i) {
        set("wxMiniProgramType", Integer.valueOf(i));
    }

    public boolean getOpenCustomEven() {
        return ((Boolean) get("isLogEven", Boolean.class)).booleanValue();
    }

    public void setOpenCustomEven(boolean z) {
        set("isLogEven", Boolean.valueOf(z));
    }

    public void setSubreddit(String str) {
        set("sr", str);
    }

    public String getSubreddit() {
        return (String) get("sr", String.class);
    }

    public void setLcSummary(String str) {
        set("lc_summary", str);
    }

    public String getLcSummary() {
        return (String) get("lc_summary", String.class);
    }

    public void setLcImage(JSONObject jSONObject) {
        set("lc_image", jSONObject);
    }

    public JSONObject getLcImage() {
        return (JSONObject) get("lc_image", JSONObject.class);
    }

    public void setLcObjectType(String str) {
        set("lc_object_type", str);
    }

    public String getLcObjectType() {
        return (String) get("lc_object_type", String.class);
    }

    public void setLcDisplayName(String str) {
        set("lc_display_name", str);
    }

    public String getLcDisplayName() {
        return (String) get("lc_display_name", String.class);
    }

    public void setLcCreateAt(String str) {
        set("lc_create_at", str);
    }

    public String getLcCreateAt() {
        return (String) get("lc_create_at", String.class);
    }

    public void setLcUrl(String str) {
        set("lc_url", str);
    }

    public String getLcUrl() {
        return (String) get("lc_url", String.class);
    }

    public void setActivity(Activity activity) {
        set("activity", activity);
    }

    public Activity getActivity() {
        return (Activity) get("activity", Activity.class);
    }

    public void setQuote(String str) {
        set("QUOTE", str);
    }

    public String getQuote() {
        return (String) get("QUOTE", String.class);
    }

    public void setHashtag(String str) {
        set("HASHTAG", str);
    }

    public void setHashtags(String[] strArr) {
        set("HASHTAGS", strArr);
    }

    public void setDouyinShareToPublish(boolean z) {
        set("shareToPublish", Boolean.valueOf(z));
    }

    public String getHashtag() {
        return (String) get("HASHTAG", String.class);
    }

    public String[] getHashtags() {
        return (String[]) get("HASHTAGS", String[].class);
    }

    public boolean getDouyinShareToPublish() {
        return ((Boolean) get("shareToPublish", Boolean.class)).booleanValue();
    }

    public void setQQMiniProgramAppid(String str) {
        set("mini_program_appid", str);
    }

    public String getQQMiniProgramAppid() {
        return (String) get("mini_program_appid", String.class);
    }

    public void setQQMiniProgramPath(String str) {
        set("mini_program_path", str);
    }

    public String getQQMiniProgramPath() {
        return (String) get("mini_program_path", String.class);
    }

    public void setQQMiniProgramType(String str) {
        set("mini_program_type", str);
    }

    public String getQQMiniProgramType() {
        return (String) get("mini_program_type", String.class);
    }

    public void setLoopshareCustomParams(HashMap<String, Object> hashMap) {
        set("loopshare_params_mobid", hashMap);
    }

    public HashMap<String, Object> getLoopshareCustomParams() {
        return (HashMap) get("loopshare_params_mobid", HashMap.class);
    }

    public void setImageUrlList(List<String> list) {
        set("imageUrlList", list);
    }

    public void setImageUriList(List<Uri> list) {
        set("imageUriList", list);
    }

    public void setVideoUriOasis(Uri uri) {
        set("video_uri_oasis", uri);
    }

    public void setVideoPathOasis(String str) {
        set("video_path_oasis", str);
    }

    public Uri getVideoUriOasis() {
        return (Uri) get("video_uri_oasis", Uri.class);
    }

    public String getVideoPathOasis() {
        return (String) get("video_path_oasis", String.class);
    }

    public List<String> getImageUrlList() {
        return (List) get("imageUrlList", List.class);
    }

    public List<Uri> getImageUriList() {
        return (List) get("imageUriList", List.class);
    }

    public void setFileImage(File file) {
        set("file_image", file);
    }

    public File getFileImage() {
        return (File) get("file_image", File.class);
    }

    public void setFileVideo(File file) {
        set("file_video", file);
    }

    public File getFileVideo() {
        return (File) get("file_video", File.class);
    }

    public void setFileSticker(File file) {
        set("file_sticker", file);
    }

    public File getStickerFile() {
        return (File) get("file_sticker", File.class);
    }

    public void setVideoUri(Uri uri) {
        set(MediaRecorderActivity.VIDEO_URI, uri);
    }

    public Uri getVideoUri() {
        return (Uri) get(MediaRecorderActivity.VIDEO_URI, Uri.class);
    }

    public void setVideoUrl(String str) {
        set("video_url", str);
    }

    public String getVideoUrl() {
        return (String) get("video_url", String.class);
    }

    public void setKakaoWebUrl(String str) {
        set("kakao_template_weburl", str);
    }

    public String getKakaoWebUrl() {
        return (String) get("kakao_template_weburl", String.class);
    }

    public void setKakaoMobileWebUrl(String str) {
        set("kakao_template_mobileweburl", str);
    }

    public String getKakaoMobileweburl() {
        return (String) get("kakao_template_mobileweburl", String.class);
    }

    public void setKakaoLikecount(int i) {
        set("kakao_template_likecount", Integer.valueOf(i));
    }

    public int getKakaoLikecount() {
        return ((Integer) get("kakao_template_likecount", Integer.class)).intValue();
    }

    public void setKakaoCommentcount(int i) {
        set("kakao_template_commentcount", Integer.valueOf(i));
    }

    public int getKakaoCommentcount() {
        return ((Integer) get("kakao_template_commentcount", Integer.class)).intValue();
    }

    public void setKakaoSharecount(int i) {
        set("kakao_template_sharecount", Integer.valueOf(i));
    }

    public int getKakaoSharecount() {
        return ((Integer) get("kakao_template_sharecount", Integer.class)).intValue();
    }

    public void setKakaoAddbuttonWeburl(String str) {
        set("kakao_template_button_weburl", str);
    }

    public String getKakaoAddbuttonWeburl() {
        return (String) get("kakao_template_button_weburl", String.class);
    }

    public void setKakaoAddbuttonMobileweburl(String str) {
        set("kaokao_template_button_mobileweburl", str);
    }

    public String getKakaoAddbuttonMobileweburl() {
        return (String) get("kaokao_template_button_mobileweburl", String.class);
    }

    public void setKakaoAddbuttonTitle(String str) {
        set("kakao_template_button_title", str);
    }

    public String getKakaoAddbuttonTitle() {
        return (String) get("kakao_template_button_title", String.class);
    }

    public void setKakaoRegularprice(int i) {
        set("kaokao_template_regularprice", Integer.valueOf(i));
    }

    public int getKakaoRegularprice() {
        return ((Integer) get("kaokao_template_regularprice", Integer.class)).intValue();
    }

    public void setKakaoProductname(String str) {
        set("kakao_template_productname", str);
    }

    public String getKakaoTemplateProductname() {
        return (String) get("kakao_template_productname", String.class);
    }

    public void setKakaoDiscountprice(int i) {
        set("kakao_template_discountprice", Integer.valueOf(i));
    }

    public int getKakaoDiscountprice() {
        return ((Integer) get("kakao_template_discountprice", Integer.class)).intValue();
    }

    public void setKakaoDiscountrate(int i) {
        set("kakao_template_discountrate", Integer.valueOf(i));
    }

    public int getKakaoDiscountrate() {
        return ((Integer) get("kakao_template_discountrate", Integer.class)).intValue();
    }

    public void setKakaoCustomTemplate(HashMap<String, String> hashMap) {
        set("kaokao_custom_template", hashMap);
    }

    public HashMap<String, String> getKakaoCustomTemplate() {
        return (HashMap) get("kaokao_custom_template", HashMap.class);
    }

    public void setKakaoCustomTemplateId(String str) {
        set("kakao_custom_templateid", str);
    }

    public String getKakaoCustomTemplateId() {
        return (String) get("kakao_custom_templateid", String.class);
    }

    public void setDYMixFileArray(String[] strArr) {
        set("douyin_mix_file", strArr);
    }

    public String[] getDYMixFileArray() {
        return (String[]) get("douyin_mix_file", String[].class);
    }

    public void setTagPosition(int i) {
        set("douyin_tag_position", Integer.valueOf(i));
    }

    public int getTagPosition() {
        return ((Integer) get("douyin_tag_position", Integer.class)).intValue();
    }

    public void setWxTemplateid(String str) {
        set("wx_templateid", str);
    }

    public String getWxTemplateid() {
        return (String) get("wx_templateid", String.class);
    }

    public void setWxReserved(String str) {
        set("wx_reserved", str);
    }

    public String getWxReserved() {
        return (String) get("wx_reserved", String.class);
    }

    public void setImageFileProviderPath(String str) {
        set("image_provider_path", str);
    }

    public String getImageFileProviderPath() {
        return (String) get("image_provider_path", String.class);
    }
}
