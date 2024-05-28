package com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity;

import java.io.Serializable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class VideoDetailsEntity implements Serializable {
    private DataDTO data;
    private String message;
    private String statusCode;

    public DataDTO getData() {
        return this.data;
    }

    public void setData(DataDTO dataDTO) {
        this.data = dataDTO;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class DataDTO implements Serializable {
        private String article_class;
        private String article_url;
        private String bury_count;
        private String comment_count;
        private String comment_url;
        private List<CoverImageListDTO> cover_image_list;
        private String cover_mode;
        private String detail_source;
        private String digg_count;
        private String digg_status;
        private String duration;
        private long group_id;
        private String group_id_str;
        private String has_video;
        private List<?> image_list;
        private String item_id;
        private List<LargeImageListDTO> large_image_list;
        private List<MiddleImageDTO> middle_image;
        private String publish_time;
        private String share_count;
        private String share_url;
        private String tag;
        private String title;
        private UserInfoDTO user_info;
        private VideoDTO video;
        private String video_id;
        private String watch_count;

        public String getArticle_class() {
            return this.article_class;
        }

        public void setArticle_class(String str) {
            this.article_class = str;
        }

        public String getArticle_url() {
            return this.article_url;
        }

        public void setArticle_url(String str) {
            this.article_url = str;
        }

        public String getBury_count() {
            return this.bury_count;
        }

        public void setBury_count(String str) {
            this.bury_count = str;
        }

        public String getComment_count() {
            return this.comment_count;
        }

        public void setComment_count(String str) {
            this.comment_count = str;
        }

        public String getComment_url() {
            return this.comment_url;
        }

        public void setComment_url(String str) {
            this.comment_url = str;
        }

        public List<CoverImageListDTO> getCover_image_list() {
            return this.cover_image_list;
        }

        public void setCover_image_list(List<CoverImageListDTO> list) {
            this.cover_image_list = list;
        }

        public String getCover_mode() {
            return this.cover_mode;
        }

        public void setCover_mode(String str) {
            this.cover_mode = str;
        }

        public String getDetail_source() {
            return this.detail_source;
        }

        public void setDetail_source(String str) {
            this.detail_source = str;
        }

        public String getDigg_count() {
            return this.digg_count;
        }

        public void setDigg_count(String str) {
            this.digg_count = str;
        }

        public String getDigg_status() {
            return this.digg_status;
        }

        public void setDigg_status(String str) {
            this.digg_status = str;
        }

        public String getDuration() {
            return this.duration;
        }

        public void setDuration(String str) {
            this.duration = str;
        }

        public long getGroup_id() {
            return this.group_id;
        }

        public void setGroup_id(long j) {
            this.group_id = j;
        }

        public String getGroup_id_str() {
            return this.group_id_str;
        }

        public void setGroup_id_str(String str) {
            this.group_id_str = str;
        }

        public String getHas_video() {
            return this.has_video;
        }

        public void setHas_video(String str) {
            this.has_video = str;
        }

        public List<?> getImage_list() {
            return this.image_list;
        }

        public void setImage_list(List<?> list) {
            this.image_list = list;
        }

        public String getItem_id() {
            return this.item_id;
        }

        public void setItem_id(String str) {
            this.item_id = str;
        }

        public List<LargeImageListDTO> getLarge_image_list() {
            return this.large_image_list;
        }

        public void setLarge_image_list(List<LargeImageListDTO> list) {
            this.large_image_list = list;
        }

        public List<MiddleImageDTO> getMiddle_image() {
            return this.middle_image;
        }

        public void setMiddle_image(List<MiddleImageDTO> list) {
            this.middle_image = list;
        }

        public String getPublish_time() {
            return this.publish_time;
        }

        public void setPublish_time(String str) {
            this.publish_time = str;
        }

        public String getShare_count() {
            return this.share_count;
        }

        public void setShare_count(String str) {
            this.share_count = str;
        }

        public String getShare_url() {
            return this.share_url;
        }

        public void setShare_url(String str) {
            this.share_url = str;
        }

        public String getTag() {
            return this.tag;
        }

        public void setTag(String str) {
            this.tag = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public UserInfoDTO getUser_info() {
            return this.user_info;
        }

        public void setUser_info(UserInfoDTO userInfoDTO) {
            this.user_info = userInfoDTO;
        }

        public VideoDTO getVideo() {
            return this.video;
        }

        public void setVideo(VideoDTO videoDTO) {
            this.video = videoDTO;
        }

        public String getVideo_id() {
            return this.video_id;
        }

        public void setVideo_id(String str) {
            this.video_id = str;
        }

        public String getWatch_count() {
            return this.watch_count;
        }

        public void setWatch_count(String str) {
            this.watch_count = str;
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class UserInfoDTO implements Serializable {
            private String avatar_url;
            private String description;
            private boolean follow;
            private String follower_count;
            private String home_page;
            private String media_id;
            private String name;
            private String user_id;
            private boolean user_verified;

            public String getAvatar_url() {
                return this.avatar_url;
            }

            public void setAvatar_url(String str) {
                this.avatar_url = str;
            }

            public String getDescription() {
                return this.description;
            }

            public void setDescription(String str) {
                this.description = str;
            }

            public boolean isFollow() {
                return this.follow;
            }

            public void setFollow(boolean z) {
                this.follow = z;
            }

            public String getFollower_count() {
                return this.follower_count;
            }

            public void setFollower_count(String str) {
                this.follower_count = str;
            }

            public String getHome_page() {
                return this.home_page;
            }

            public void setHome_page(String str) {
                this.home_page = str;
            }

            public String getMedia_id() {
                return this.media_id;
            }

            public void setMedia_id(String str) {
                this.media_id = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getUser_id() {
                return this.user_id;
            }

            public void setUser_id(String str) {
                this.user_id = str;
            }

            public boolean isUser_verified() {
                return this.user_verified;
            }

            public void setUser_verified(boolean z) {
                this.user_verified = z;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class VideoDTO {
            private String poster_url;
            private int status;
            private double video_duration;
            private String video_id;
            private List<VideoListDTO> video_list;

            public String getPoster_url() {
                return this.poster_url;
            }

            public void setPoster_url(String str) {
                this.poster_url = str;
            }

            public int getStatus() {
                return this.status;
            }

            public void setStatus(int i) {
                this.status = i;
            }

            public double getVideo_duration() {
                return this.video_duration;
            }

            public void setVideo_duration(double d) {
                this.video_duration = d;
            }

            public String getVideo_id() {
                return this.video_id;
            }

            public void setVideo_id(String str) {
                this.video_id = str;
            }

            public List<VideoListDTO> getVideo_list() {
                return this.video_list;
            }

            public void setVideo_list(List<VideoListDTO> list) {
                this.video_list = list;
            }

            /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
            /* loaded from: E:\11480076_dexfile_execute.dex */
            public static class VideoListDTO {
                private String backup_url_1;
                private int bitrate;
                private String definition;
                private String main_url;
                private int size;
                private int vheight;
                private int video_duration;
                private String vtype;
                private int vwidth;

                public String getBackup_url_1() {
                    return this.backup_url_1;
                }

                public void setBackup_url_1(String str) {
                    this.backup_url_1 = str;
                }

                public int getBitrate() {
                    return this.bitrate;
                }

                public void setBitrate(int i) {
                    this.bitrate = i;
                }

                public String getDefinition() {
                    return this.definition;
                }

                public void setDefinition(String str) {
                    this.definition = str;
                }

                public String getMain_url() {
                    return this.main_url;
                }

                public void setMain_url(String str) {
                    this.main_url = str;
                }

                public int getSize() {
                    return this.size;
                }

                public void setSize(int i) {
                    this.size = i;
                }

                public int getVheight() {
                    return this.vheight;
                }

                public void setVheight(int i) {
                    this.vheight = i;
                }

                public int getVideo_duration() {
                    return this.video_duration;
                }

                public void setVideo_duration(int i) {
                    this.video_duration = i;
                }

                public String getVtype() {
                    return this.vtype;
                }

                public void setVtype(String str) {
                    this.vtype = str;
                }

                public int getVwidth() {
                    return this.vwidth;
                }

                public void setVwidth(int i) {
                    this.vwidth = i;
                }
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class CoverImageListDTO {
            private String height;
            private String mimetype;
            private String uri;
            private String url;
            private List<UrlListDTO> url_list;
            private String width;

            public String getHeight() {
                return this.height;
            }

            public void setHeight(String str) {
                this.height = str;
            }

            public String getMimetype() {
                return this.mimetype;
            }

            public void setMimetype(String str) {
                this.mimetype = str;
            }

            public String getUri() {
                return this.uri;
            }

            public void setUri(String str) {
                this.uri = str;
            }

            public String getUrl() {
                return this.url;
            }

            public void setUrl(String str) {
                this.url = str;
            }

            public List<UrlListDTO> getUrl_list() {
                return this.url_list;
            }

            public void setUrl_list(List<UrlListDTO> list) {
                this.url_list = list;
            }

            public String getWidth() {
                return this.width;
            }

            public void setWidth(String str) {
                this.width = str;
            }

            /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
            /* loaded from: E:\11480076_dexfile_execute.dex */
            public static class UrlListDTO {
                private String url;

                public String getUrl() {
                    return this.url;
                }

                public void setUrl(String str) {
                    this.url = str;
                }
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class LargeImageListDTO {
            private String height;
            private String mimetype;
            private String uri;
            private String url;
            private List<UrlListDTO> url_list;
            private String width;

            public String getHeight() {
                return this.height;
            }

            public void setHeight(String str) {
                this.height = str;
            }

            public String getMimetype() {
                return this.mimetype;
            }

            public void setMimetype(String str) {
                this.mimetype = str;
            }

            public String getUri() {
                return this.uri;
            }

            public void setUri(String str) {
                this.uri = str;
            }

            public String getUrl() {
                return this.url;
            }

            public void setUrl(String str) {
                this.url = str;
            }

            public List<UrlListDTO> getUrl_list() {
                return this.url_list;
            }

            public void setUrl_list(List<UrlListDTO> list) {
                this.url_list = list;
            }

            public String getWidth() {
                return this.width;
            }

            public void setWidth(String str) {
                this.width = str;
            }

            /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
            /* loaded from: E:\11480076_dexfile_execute.dex */
            public static class UrlListDTO {
                private String url;

                public String getUrl() {
                    return this.url;
                }

                public void setUrl(String str) {
                    this.url = str;
                }
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class MiddleImageDTO {
            private String height;
            private String mimetype;
            private String uri;
            private String url;
            private List<UrlListDTO> url_list;
            private String width;

            public String getHeight() {
                return this.height;
            }

            public void setHeight(String str) {
                this.height = str;
            }

            public String getMimetype() {
                return this.mimetype;
            }

            public void setMimetype(String str) {
                this.mimetype = str;
            }

            public String getUri() {
                return this.uri;
            }

            public void setUri(String str) {
                this.uri = str;
            }

            public String getUrl() {
                return this.url;
            }

            public void setUrl(String str) {
                this.url = str;
            }

            public List<UrlListDTO> getUrl_list() {
                return this.url_list;
            }

            public void setUrl_list(List<UrlListDTO> list) {
                this.url_list = list;
            }

            public String getWidth() {
                return this.width;
            }

            public void setWidth(String str) {
                this.width = str;
            }

            /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
            /* loaded from: E:\11480076_dexfile_execute.dex */
            public static class UrlListDTO {
                private String url;

                public String getUrl() {
                    return this.url;
                }

                public void setUrl(String str) {
                    this.url = str;
                }
            }
        }
    }
}
