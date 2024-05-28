package com.sinovatech.unicom.separatemodule.videocenter.entity;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NewsInfoEntity {
    private String avatar_url;
    private ArrayList<String> bannerImgList;
    private ArrayList<String> bannerUrlList;
    private String comment_count;
    private String coverImageUrl;
    private String digg_count;
    private boolean follow;
    private long group_id;
    private String group_id_str;
    private String home_page;
    private boolean isAddView;
    private boolean isDing;
    private boolean isStick;
    private boolean isUpload;
    private String item_id;
    private String name;
    private String share_url;
    private String title;
    private String user_id;
    private String video_duration;
    private String video_id;
    private String video_watch_count;
    private int view = -1;

    public boolean isStick() {
        return this.isStick;
    }

    public void setStick(boolean z) {
        this.isStick = z;
    }

    public ArrayList<String> getBannerUrlList() {
        return this.bannerUrlList;
    }

    public void setBannerUrlList(ArrayList<String> arrayList) {
        this.bannerUrlList = arrayList;
    }

    public ArrayList<String> getBannerImgList() {
        return this.bannerImgList;
    }

    public void setBannerImgList(ArrayList<String> arrayList) {
        this.bannerImgList = arrayList;
    }

    public boolean isAddView() {
        return this.isAddView;
    }

    public void setAddView(boolean z) {
        this.isAddView = z;
    }

    public int getView() {
        return this.view;
    }

    public void setView(int i) {
        this.view = i;
    }

    public boolean isUpload() {
        return this.isUpload;
    }

    public void setUpload(boolean z) {
        this.isUpload = z;
    }

    public boolean isDing() {
        return this.isDing;
    }

    public void setDing(boolean z) {
        this.isDing = z;
    }

    public String getGroup_id_str() {
        return this.group_id_str;
    }

    public void setGroup_id_str(String str) {
        this.group_id_str = str;
    }

    public long getGroup_id() {
        return this.group_id;
    }

    public void setGroup_id(long j) {
        this.group_id = j;
    }

    public String getItem_id() {
        return this.item_id;
    }

    public void setItem_id(String str) {
        this.item_id = str;
    }

    public String getVideo_id() {
        return this.video_id;
    }

    public void setVideo_id(String str) {
        this.video_id = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getCoverImageUrl() {
        return this.coverImageUrl;
    }

    public void setCoverImageUrl(String str) {
        this.coverImageUrl = str;
    }

    public String getVideo_watch_count() {
        return this.video_watch_count;
    }

    public void setVideo_watch_count(String str) {
        this.video_watch_count = str;
    }

    public String getVideo_duration() {
        return this.video_duration;
    }

    public void setVideo_duration(String str) {
        this.video_duration = str;
    }

    public String getShare_url() {
        return this.share_url;
    }

    public void setShare_url(String str) {
        this.share_url = str;
    }

    public String getDigg_count() {
        return this.digg_count;
    }

    public void setDigg_count(String str) {
        this.digg_count = str;
    }

    public String getComment_count() {
        return this.comment_count;
    }

    public void setComment_count(String str) {
        this.comment_count = str;
    }

    public String getAvatar_url() {
        return this.avatar_url;
    }

    public void setAvatar_url(String str) {
        this.avatar_url = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
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

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String str) {
        this.user_id = str;
    }
}
