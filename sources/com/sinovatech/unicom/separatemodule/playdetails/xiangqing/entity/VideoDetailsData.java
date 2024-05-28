package com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class VideoDetailsData {
    private List<DataDTO> data;
    private String message;
    private String nextPageNum;
    private String statusCode;

    public List<DataDTO> getData() {
        return this.data;
    }

    public void setData(List<DataDTO> list) {
        this.data = list;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getNextPageNum() {
        return this.nextPageNum;
    }

    public void setNextPageNum(String str) {
        this.nextPageNum = str;
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
    public static class DataDTO {
        private String article_class;
        private String article_url;
        private String behot_time;
        private String bury_count;
        private String comment_count;
        private String comment_url;
        private String digg_count;
        private List<?> filter_words;
        private long group_id;
        private String group_id_str;
        private String has_video;
        private List<?> image_list;
        private String item_id;
        private List<LargeImageListDTO> large_image_list;
        private List<MiddleImageDTO> middle_image;
        private String publish_time;
        private String share_url;
        private String source;
        private String tag;
        private String tip;
        private String title;
        private boolean upload;
        private UserInfoDTO user_info;
        private String video_duration;
        private String video_id;
        private String video_watch_count;

        public boolean isUpload() {
            return this.upload;
        }

        public void setUpload(boolean z) {
            this.upload = z;
        }

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

        public String getBehot_time() {
            return this.behot_time;
        }

        public void setBehot_time(String str) {
            this.behot_time = str;
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

        public String getDigg_count() {
            return this.digg_count;
        }

        public void setDigg_count(String str) {
            this.digg_count = str;
        }

        public List<?> getFilter_words() {
            return this.filter_words;
        }

        public void setFilter_words(List<?> list) {
            this.filter_words = list;
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

        public String getShare_url() {
            return this.share_url;
        }

        public void setShare_url(String str) {
            this.share_url = str;
        }

        public String getSource() {
            return this.source;
        }

        public void setSource(String str) {
            this.source = str;
        }

        public String getTag() {
            return this.tag;
        }

        public void setTag(String str) {
            this.tag = str;
        }

        public String getTip() {
            return this.tip;
        }

        public void setTip(String str) {
            this.tip = str;
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

        public String getVideo_duration() {
            return this.video_duration;
        }

        public void setVideo_duration(String str) {
            this.video_duration = str;
        }

        public String getVideo_id() {
            return this.video_id;
        }

        public void setVideo_id(String str) {
            this.video_id = str;
        }

        public String getVideo_watch_count() {
            return this.video_watch_count;
        }

        public void setVideo_watch_count(String str) {
            this.video_watch_count = str;
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class UserInfoDTO {
            private String avatar_url;
            private String description;
            private boolean follow;
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
