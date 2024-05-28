package com.sinovatech.unicom.separatemodule.gamecenter.entity;

import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HotGetEntity {
    private String code;
    private DataBean data;
    private String desc;

    public String toString() {
        return "HotGetEntity{desc='" + this.desc + "', code='" + this.code + "', data=" + this.data + '}';
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class DataBean {
        private List<AdvertisementBean> advertisement;
        private List<CarouselBean> carousel;
        private List<IconBean> icon;
        private String more_url;
        private PosterBean poster;
        private String title;

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class PosterBean {
        }

        public String toString() {
            return "DataBean{title='" + this.title + "', more_url='" + this.more_url + "', poster=" + this.poster + ", icon=" + this.icon + ", advertisement=" + this.advertisement + ", carousel=" + this.carousel + '}';
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getMore_url() {
            return this.more_url;
        }

        public void setMore_url(String str) {
            this.more_url = str;
        }

        public PosterBean getPoster() {
            return this.poster;
        }

        public void setPoster(PosterBean posterBean) {
            this.poster = posterBean;
        }

        public List<IconBean> getIcon() {
            return this.icon;
        }

        public void setIcon(List<IconBean> list) {
            this.icon = list;
        }

        public List<AdvertisementBean> getAdvertisement() {
            return this.advertisement;
        }

        public void setAdvertisement(List<AdvertisementBean> list) {
            this.advertisement = list;
        }

        public List<CarouselBean> getCarousel() {
            return this.carousel;
        }

        public void setCarousel(List<CarouselBean> list) {
            this.carousel = list;
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static class IconBean {
            private String android_version;
            private String bubble;
            private String company;
            private String del_status;
            private String deploy_game;
            private Object end_time;
            private String floor_type;
            private String free_flow;
            private String gameType;
            private String gameUrl;
            private String game_id;
            private String game_name;
            private String game_persons_base;
            private String groupCode;

            /* renamed from: id */
            private String f18534id;
            private String ios_version;
            private Object is_action;
            private int location;
            private String main_title;
            private String name;
            private String prop_code;
            private String qqMark;
            private String qq_mark;
            private String resourceId;
            private String resource_id;
            private Object start_time;
            private Object status;
            private String two_game_type;
            private String url;
            private String vice_title;

            public String getGroupCode() {
                return this.groupCode;
            }

            public void setGroupCode(String str) {
                this.groupCode = str;
            }

            public String getQqMark() {
                return this.qqMark;
            }

            public void setQqMark(String str) {
                this.qqMark = str;
            }

            public String getResourceId() {
                return this.resourceId;
            }

            public void setResourceId(String str) {
                this.resourceId = str;
            }

            public String getFloor_type() {
                return this.floor_type;
            }

            public void setFloor_type(String str) {
                this.floor_type = str;
            }

            public String getId() {
                return this.f18534id;
            }

            public void setId(String str) {
                this.f18534id = str;
            }

            public String getBubble() {
                return this.bubble;
            }

            public void setBubble(String str) {
                this.bubble = str;
            }

            public String getDel_status() {
                return this.del_status;
            }

            public void setDel_status(String str) {
                this.del_status = str;
            }

            public String getFree_flow() {
                return this.free_flow;
            }

            public void setFree_flow(String str) {
                this.free_flow = str;
            }

            public String getCompany() {
                return this.company;
            }

            public void setCompany(String str) {
                this.company = str;
            }

            public String getMain_title() {
                return this.main_title;
            }

            public void setMain_title(String str) {
                this.main_title = str;
            }

            public String getVice_title() {
                return this.vice_title;
            }

            public void setVice_title(String str) {
                this.vice_title = str;
            }

            public String getGameType() {
                return this.gameType;
            }

            public void setGameType(String str) {
                this.gameType = str;
            }

            public String getUrl() {
                return this.url;
            }

            public void setUrl(String str) {
                this.url = str;
            }

            public String getProp_code() {
                return this.prop_code;
            }

            public void setProp_code(String str) {
                this.prop_code = str;
            }

            public String getGame_name() {
                return this.game_name;
            }

            public void setGame_name(String str) {
                this.game_name = str;
            }

            public String getTwo_game_type() {
                return this.two_game_type;
            }

            public void setTwo_game_type(String str) {
                this.two_game_type = str;
            }

            public String getDeploy_game() {
                return this.deploy_game;
            }

            public void setDeploy_game(String str) {
                this.deploy_game = str;
            }

            public String getGame_id() {
                return this.game_id;
            }

            public void setGame_id(String str) {
                this.game_id = str;
            }

            public Object getStatus() {
                return this.status;
            }

            public void setStatus(Object obj) {
                this.status = obj;
            }

            public String getQq_mark() {
                return this.qq_mark;
            }

            public void setQq_mark(String str) {
                this.qq_mark = str;
            }

            public int getLocation() {
                return this.location;
            }

            public void setLocation(int i) {
                this.location = i;
            }

            public String getAndroid_version() {
                return this.android_version;
            }

            public void setAndroid_version(String str) {
                this.android_version = str;
            }

            public Object getStart_time() {
                return this.start_time;
            }

            public void setStart_time(Object obj) {
                this.start_time = obj;
            }

            public Object getEnd_time() {
                return this.end_time;
            }

            public void setEnd_time(Object obj) {
                this.end_time = obj;
            }

            public Object getIs_action() {
                return this.is_action;
            }

            public void setIs_action(Object obj) {
                this.is_action = obj;
            }

            public String getIos_version() {
                return this.ios_version;
            }

            public void setIos_version(String str) {
                this.ios_version = str;
            }

            public String getGameUrl() {
                return this.gameUrl;
            }

            public void setGameUrl(String str) {
                this.gameUrl = str;
            }

            public String getGame_persons_base() {
                return this.game_persons_base;
            }

            public void setGame_persons_base(String str) {
                this.game_persons_base = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getResource_id() {
                return this.resource_id;
            }

            public void setResource_id(String str) {
                this.resource_id = str;
            }

            public String toString() {
                return "IconBean{floor_type='" + this.floor_type + "', id='" + this.f18534id + "', bubble='" + this.bubble + "', del_status='" + this.del_status + "', free_flow='" + this.free_flow + "', company='" + this.company + "', main_title='" + this.main_title + "', vice_title='" + this.vice_title + "', gameType='" + this.gameType + "', url='" + this.url + "', prop_code='" + this.prop_code + "', game_name='" + this.game_name + "', two_game_type='" + this.two_game_type + "', deploy_game='" + this.deploy_game + "', game_id='" + this.game_id + "', status=" + this.status + ", qq_mark='" + this.qq_mark + "', location=" + this.location + ", android_version='" + this.android_version + "', start_time=" + this.start_time + ", end_time=" + this.end_time + ", is_action=" + this.is_action + ", ios_version='" + this.ios_version + "', gameUrl='" + this.gameUrl + "', game_persons_base='" + this.game_persons_base + "', name='" + this.name + "', resource_id='" + this.resource_id + "'}";
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class AdvertisementBean {
            private String android_version;
            private Object bubble;
            private Object company;
            private String del_status;
            private String deploy_game;
            private Object end_time;
            private String floor_type;
            private Object free_flow;
            private Object gameType;
            private Object gameUrl;
            private Object game_id;
            private Object game_name;
            private Object game_persons_base;

            /* renamed from: id */
            private String f18532id;
            private String ios_version;
            private Object is_action;
            private int location;
            private Object main_title;
            private Object name;
            private String prop_code;
            private Object qq_mark;
            private Object resource_id;
            private Object start_time;
            private Object status;
            private Object two_game_type;
            private Object url;
            private Object vice_title;

            public String getFloor_type() {
                return this.floor_type;
            }

            public void setFloor_type(String str) {
                this.floor_type = str;
            }

            public String getId() {
                return this.f18532id;
            }

            public void setId(String str) {
                this.f18532id = str;
            }

            public Object getBubble() {
                return this.bubble;
            }

            public void setBubble(Object obj) {
                this.bubble = obj;
            }

            public String getDel_status() {
                return this.del_status;
            }

            public void setDel_status(String str) {
                this.del_status = str;
            }

            public Object getFree_flow() {
                return this.free_flow;
            }

            public void setFree_flow(Object obj) {
                this.free_flow = obj;
            }

            public Object getCompany() {
                return this.company;
            }

            public void setCompany(Object obj) {
                this.company = obj;
            }

            public Object getMain_title() {
                return this.main_title;
            }

            public void setMain_title(Object obj) {
                this.main_title = obj;
            }

            public Object getVice_title() {
                return this.vice_title;
            }

            public void setVice_title(Object obj) {
                this.vice_title = obj;
            }

            public Object getGameType() {
                return this.gameType;
            }

            public void setGameType(Object obj) {
                this.gameType = obj;
            }

            public Object getUrl() {
                return this.url;
            }

            public void setUrl(Object obj) {
                this.url = obj;
            }

            public String getProp_code() {
                return this.prop_code;
            }

            public void setProp_code(String str) {
                this.prop_code = str;
            }

            public Object getGame_name() {
                return this.game_name;
            }

            public void setGame_name(Object obj) {
                this.game_name = obj;
            }

            public Object getTwo_game_type() {
                return this.two_game_type;
            }

            public void setTwo_game_type(Object obj) {
                this.two_game_type = obj;
            }

            public String getDeploy_game() {
                return this.deploy_game;
            }

            public void setDeploy_game(String str) {
                this.deploy_game = str;
            }

            public Object getGame_id() {
                return this.game_id;
            }

            public void setGame_id(Object obj) {
                this.game_id = obj;
            }

            public Object getStatus() {
                return this.status;
            }

            public void setStatus(Object obj) {
                this.status = obj;
            }

            public Object getQq_mark() {
                return this.qq_mark;
            }

            public void setQq_mark(Object obj) {
                this.qq_mark = obj;
            }

            public int getLocation() {
                return this.location;
            }

            public void setLocation(int i) {
                this.location = i;
            }

            public String getAndroid_version() {
                return this.android_version;
            }

            public void setAndroid_version(String str) {
                this.android_version = str;
            }

            public Object getStart_time() {
                return this.start_time;
            }

            public void setStart_time(Object obj) {
                this.start_time = obj;
            }

            public Object getEnd_time() {
                return this.end_time;
            }

            public void setEnd_time(Object obj) {
                this.end_time = obj;
            }

            public Object getIs_action() {
                return this.is_action;
            }

            public void setIs_action(Object obj) {
                this.is_action = obj;
            }

            public String getIos_version() {
                return this.ios_version;
            }

            public void setIos_version(String str) {
                this.ios_version = str;
            }

            public Object getGameUrl() {
                return this.gameUrl;
            }

            public void setGameUrl(Object obj) {
                this.gameUrl = obj;
            }

            public Object getGame_persons_base() {
                return this.game_persons_base;
            }

            public void setGame_persons_base(Object obj) {
                this.game_persons_base = obj;
            }

            public Object getName() {
                return this.name;
            }

            public void setName(Object obj) {
                this.name = obj;
            }

            public Object getResource_id() {
                return this.resource_id;
            }

            public void setResource_id(Object obj) {
                this.resource_id = obj;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class CarouselBean {
            private String android_version;
            private Object bubble;
            private String company;
            private String del_status;
            private String deploy_game;
            private Object end_time;
            private String floor_type;
            private String free_flow;
            private String gameType;
            private String gameUrl;
            private String game_id;
            private String game_name;
            private String game_persons_base;

            /* renamed from: id */
            private String f18533id;
            private String ios_version;
            private Object is_action;
            private int location;
            private Object main_title;
            private String name;
            private String prop_code;
            private String qq_mark;
            private String resource_id;
            private Object start_time;
            private Object status;
            private Object two_game_type;
            private String url;
            private Object vice_title;

            public String getFloor_type() {
                return this.floor_type;
            }

            public void setFloor_type(String str) {
                this.floor_type = str;
            }

            public String getId() {
                return this.f18533id;
            }

            public void setId(String str) {
                this.f18533id = str;
            }

            public Object getBubble() {
                return this.bubble;
            }

            public void setBubble(Object obj) {
                this.bubble = obj;
            }

            public String getDel_status() {
                return this.del_status;
            }

            public void setDel_status(String str) {
                this.del_status = str;
            }

            public String getFree_flow() {
                return this.free_flow;
            }

            public void setFree_flow(String str) {
                this.free_flow = str;
            }

            public String getCompany() {
                return this.company;
            }

            public void setCompany(String str) {
                this.company = str;
            }

            public Object getMain_title() {
                return this.main_title;
            }

            public void setMain_title(Object obj) {
                this.main_title = obj;
            }

            public Object getVice_title() {
                return this.vice_title;
            }

            public void setVice_title(Object obj) {
                this.vice_title = obj;
            }

            public String getGameType() {
                return this.gameType;
            }

            public void setGameType(String str) {
                this.gameType = str;
            }

            public String getUrl() {
                return this.url;
            }

            public void setUrl(String str) {
                this.url = str;
            }

            public String getProp_code() {
                return this.prop_code;
            }

            public void setProp_code(String str) {
                this.prop_code = str;
            }

            public String getGame_name() {
                return this.game_name;
            }

            public void setGame_name(String str) {
                this.game_name = str;
            }

            public Object getTwo_game_type() {
                return this.two_game_type;
            }

            public void setTwo_game_type(Object obj) {
                this.two_game_type = obj;
            }

            public String getDeploy_game() {
                return this.deploy_game;
            }

            public void setDeploy_game(String str) {
                this.deploy_game = str;
            }

            public String getGame_id() {
                return this.game_id;
            }

            public void setGame_id(String str) {
                this.game_id = str;
            }

            public Object getStatus() {
                return this.status;
            }

            public void setStatus(Object obj) {
                this.status = obj;
            }

            public String getQq_mark() {
                return this.qq_mark;
            }

            public void setQq_mark(String str) {
                this.qq_mark = str;
            }

            public int getLocation() {
                return this.location;
            }

            public void setLocation(int i) {
                this.location = i;
            }

            public String getAndroid_version() {
                return this.android_version;
            }

            public void setAndroid_version(String str) {
                this.android_version = str;
            }

            public Object getStart_time() {
                return this.start_time;
            }

            public void setStart_time(Object obj) {
                this.start_time = obj;
            }

            public Object getEnd_time() {
                return this.end_time;
            }

            public void setEnd_time(Object obj) {
                this.end_time = obj;
            }

            public Object getIs_action() {
                return this.is_action;
            }

            public void setIs_action(Object obj) {
                this.is_action = obj;
            }

            public String getIos_version() {
                return this.ios_version;
            }

            public void setIos_version(String str) {
                this.ios_version = str;
            }

            public String getGameUrl() {
                return this.gameUrl;
            }

            public void setGameUrl(String str) {
                this.gameUrl = str;
            }

            public String getGame_persons_base() {
                return this.game_persons_base;
            }

            public void setGame_persons_base(String str) {
                this.game_persons_base = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getResource_id() {
                return this.resource_id;
            }

            public void setResource_id(String str) {
                this.resource_id = str;
            }
        }
    }
}
