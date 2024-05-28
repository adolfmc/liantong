package com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class GuanZhuEntity {
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
    public static class DataDTO {
        private String description;
        private UserDTO user;

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public UserDTO getUser() {
            return this.user;
        }

        public void setUser(UserDTO userDTO) {
            this.user = userDTO;
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class UserDTO {
            private String avatar_url;
            private int create_time;
            private String home_page;
            private boolean is_followed;
            private boolean is_following;
            private String last_update;
            private long media_id;
            private String name;
            private String screen_name;
            private int type;
            private String user_auth_info;
            private long user_id;
            private boolean user_verified;

            public int getCreate_time() {
                return this.create_time;
            }

            public void setCreate_time(int i) {
                this.create_time = i;
            }

            public String getUser_auth_info() {
                return this.user_auth_info;
            }

            public void setUser_auth_info(String str) {
                this.user_auth_info = str;
            }

            public boolean isIs_followed() {
                return this.is_followed;
            }

            public void setIs_followed(boolean z) {
                this.is_followed = z;
            }

            public int getType() {
                return this.type;
            }

            public void setType(int i) {
                this.type = i;
            }

            public String getHome_page() {
                return this.home_page;
            }

            public void setHome_page(String str) {
                this.home_page = str;
            }

            public String getAvatar_url() {
                return this.avatar_url;
            }

            public void setAvatar_url(String str) {
                this.avatar_url = str;
            }

            public String getScreen_name() {
                return this.screen_name;
            }

            public void setScreen_name(String str) {
                this.screen_name = str;
            }

            public long getUser_id() {
                return this.user_id;
            }

            public void setUser_id(long j) {
                this.user_id = j;
            }

            public String getLast_update() {
                return this.last_update;
            }

            public void setLast_update(String str) {
                this.last_update = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public long getMedia_id() {
                return this.media_id;
            }

            public void setMedia_id(long j) {
                this.media_id = j;
            }

            public boolean isIs_following() {
                return this.is_following;
            }

            public void setIs_following(boolean z) {
                this.is_following = z;
            }

            public boolean isUser_verified() {
                return this.user_verified;
            }

            public void setUser_verified(boolean z) {
                this.user_verified = z;
            }
        }
    }
}
