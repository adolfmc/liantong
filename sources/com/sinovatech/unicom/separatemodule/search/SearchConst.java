package com.sinovatech.unicom.separatemodule.search;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SearchConst {
    public static final String AndroidCode = "113000005";
    public static final String HotProductCode = "200000003";
    public static final String ProductAdvertise = "200000002";
    public static final String ProductAutoCompleteCode = "200000001";
    public static final int SEARCH_NEW_ACT = 2;
    public static final String SEARCH_NEW_ACT_TYPE = "activity";
    public static final int SEARCH_NEW_AMUSEMENT = 4;
    public static final String SEARCH_NEW_AMUSEMENT_TYPE = "amusement";
    public static final int SEARCH_NEW_GAME = 8;
    public static final String SEARCH_NEW_GAME_TYPE = "game";
    public static final String SEARCH_NEW_GOODS_ALL_TYPE = "goods_all";
    public static final int SEARCH_NEW_LETTERS = 9;
    public static final String SEARCH_NEW_LETTERS_TYPE = "Journalism";
    public static final int SEARCH_NEW_LIVE = 16;
    public static final String SEARCH_NEW_LIVEBROADCAST_TYPE = "livebroadcast";
    public static final String SEARCH_NEW_LIVE_TYPE = "live";
    public static final int SEARCH_NEW_MUSIC = 7;
    public static final String SEARCH_NEW_MUSIC_TYPE = "music";
    public static final String SEARCH_NEW_NEED_LOGIN = "1";
    public static final int SEARCH_NEW_NULL = 14;
    public static final String SEARCH_NEW_NULL_TYPE = "search_null";
    public static final int SEARCH_NEW_OTHER = 6;
    public static final String SEARCH_NEW_OTHER_TYPE = "other";
    public static final int SEARCH_NEW_PHONE = 15;
    public static final String SEARCH_NEW_PHONE_TYPE = "phone";
    public static final int SEARCH_NEW_READING = 5;
    public static final String SEARCH_NEW_READING_TYPE = "book";
    public static final int SEARCH_NEW_RECOMMEND = 10;
    public static final String SEARCH_NEW_RECOMMEND_TYPE = "recommend";
    public static final int SEARCH_NEW_SERVICE = 1;
    public static final String SEARCH_NEW_SERVICE_TYPE = "business";
    public static final int SEARCH_NEW_SHOP = 0;
    public static final int SEARCH_NEW_SHOP_SC = 12;
    public static final String SEARCH_NEW_SHOP_SC_TYPE = "goods_sc";
    public static final String SEARCH_NEW_SHOP_TYPE = "goods";
    public static final int SEARCH_NEW_SMALL_VIDEO = 13;
    public static final String SEARCH_NEW_SMALL_VIDEO_TYPE = "smallVideo";
    public static final int SEARCH_NEW_VIDEO = 3;
    public static final String SEARCH_NEW_VIDEO_TYPE = "video";
    public static final int SEARCH_NEW_WOKNOW = 11;
    public static final String SEARCH_NEW_WOKNOW_TYPE = "woKnow";
    public static final String SearchServiceType = "SearchServiceType";
    public static final String SearchShopType = "SearchShopType";
    private static final String TAG = "SearchConst";

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
